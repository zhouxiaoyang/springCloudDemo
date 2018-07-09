package com.chinamobile.projectuser.enums;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 10:20 2018/4/26
 * @Modified by:
 */
public enum ResultEnum {
    SUCCESS(0, "操作成功!"),
    FAIL(1, "操作失败!"),
    OTHER(2,"其他异常!"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
