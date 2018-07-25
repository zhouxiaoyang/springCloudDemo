package com.coco.zuul.filter;

import com.coco.zuul.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @Description:  管理员操作拦截器
 * @Author: Winston Yang
 * @Date: Create in 14:49 2018/7/24
 * @Modified by:
 */
@Component
public class AdminFilter extends ZuulFilter{
    private static Logger logger=LoggerFactory.getLogger(AdminFilter.class);
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String uri=request.getRequestURI();
        //logger.info(uri +"   URL--------------");
        //以 /admin 开头的链接必须登录后才可以访问
        if (uri.indexOf("/admin/")!=-1){
            logger.info("开始拦截------------");
            return  true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Cookie cookie= CookieUtil.get(request,"loginToken");
        if (cookie==null || StringUtils.isEmpty(cookie.getValue()) ||
                StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(cookie.getValue()))){
            logger.info("未放行----!!!!!!!");
            //设置为false  验证未通过  不放行
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        //logger.info("放行-----------");
        return null;
    }
}
