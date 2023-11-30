# summary

* 一个邮箱只能注册一次
* 删除账号会清理昵称和邮箱，并变更状态

### how to run

```shell
git clone ...
cd demo
docker-compose up -d
```

### swagger

* open url `http://localhost:18080/swagger-ui/index.html` in browser
* error code
    * 0 success
    * -1 fail
    * -100 invalid request
    * -101 email already register

### mock stmp server

* open url `http://localhost:15000/` in browser
* after registering a user, you will find a welcome email in above console.