# AOM（Auto Operation Platform）自动化运维平台

> 主旨：方便开发及运维人员的中间件部署，一键式安装平台所支持的所有中间件。


## 开发计划

1. jwt token续签
   1. 使用 Redis 进行 存储token（取MD5）并设置过期时间为 token 过期时间的两倍
   2. 返回 token 到前端
   3. 前端每次请求接口 请求头中携带 X-Token
   4. 后段验证Token，如果验证失败（仅时间过期，但 Redis 中存在），那么生成新的 Token，返回给前端
   5. 前端设置Token
2. 用户注销登录
3. 接口开发