###获取验证码

URL
```
GET  /project/getCode
```

参数
```
无
```

返回
```
```

###用户登录

URL
```
POST  /project/user/login
```

参数
```
tel:"13412341234",
password:"123456",
uuid:"af7145165813446a97b656f179064ae1",
identifyingCode:"A12B"

```

返回
```
{
	"code": 0,
	"msg": "张三登录成功!",
	"data": null
}	
```

###获取用户列表

URL
```
GET  /project/admin/getUserList
```

参数

```
currentPage:"1",
pageSize:"10"
```

返回
```
{
  "code": 0,
  "msg": "操作成功!",
  "data": {
    "userList": [
      {
        "password": "123456",
        "tel": "13412341234",
        "id": 1,
        "username": "张三"
      },
      {
        "password": "12345456",
        "address": "bbbb",
        "tel": "127672672",
        "id": 2,
        "username": "aaa"
      },
      {
        "password": "12345456",
        "address": "bbbb",
        "tel": "127672672",
        "id": 4,
        "username": "aaa"
      }
    ],
    "size": 0
  }
}
```

###添加用户

URL
```
POST  /project/admin/addUser
```

参数

```
tel:"13412341234",
username:"张三",
password:"12233",
address:"xxxx"
```

返回
```
{
  "code": 0,
  "msg": "操作成功!",
  "data": null
}
```

###修改用户信息

URL
```
POST  /project/admin/updateUser
```

参数

```
id:"2",
tel:"13412341234",
username:"张三",
password:"12233",
address:"xxxx"
```

返回
```
{
  "code": 0,
  "msg": "操作成功!",
  "data": null
}
```

###删除用户

URL
```
POST  /project/admin/deleteUser
```

参数

```
id:"2",

```

返回
```
{
  "code": 0,
  "msg": "操作成功!",
  "data": null
}
```