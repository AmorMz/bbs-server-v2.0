# 轻量级论坛 MuziBBS

## 技术栈

前端：Vue、Element UI、buefy

后端：

1. Spring Boot
2. MyBatis

数据库：

1. MySQL
2. Druid连接池

缓存：

1. Redis

搜索：

1. ElasticSearch

消息队列：

1. RabbitMQ

其他：

1. 微信第三方登录
2. 阿里云OSS
3. JWT
4. 其他工具包

## 需求分析

TODO

## 数据库设计

**bbs_billboard**	系统公告表

| 字段        | 类型/长度  | 约束     | 注释                     |
| ----------- | ---------- | -------- | ------------------------ |
| id          | int        | 自增     | 主键                     |
| content     | varchar255 | 不为null | 公告内容                 |
| create_time | datetime   |          | 发布时间                 |
| is_delete   | bit        | 默认0    | 逻辑删除 0-未删除 1-删除 |

**bbs_post**	帖子/文章表

| 字段          | 类型/长度  | 约束           | 注释                     |
| ------------- | ---------- | -------------- | ------------------------ |
| id            | int        | 自增           | 主键                     |
| title         | varchar255 | 不为null       | 帖子标题                 |
| content       | longtext   | 不为null       | 帖子内容 md              |
| user_id       | int        | pk bbs_user id | 发帖用户id               |
| comment_count | int        | 默认0          | 评论数量(楼中楼不计入)   |
| collect_count | int        | 默认0          | 收藏数量                 |
| view_count    | int        | 默认0          | 浏览数量                 |
| top           | bit        | 默认0          | 是否置顶 0-否 1-置顶     |
| essence       | bit        | 默认0          | 是否加精 0-否 1-加精     |
| is_delete     | bit        | 默认0          | 逻辑删除 0-未删除 1-删除 |
| create_time   | datetime   |                | 发布时间                 |
| modify_time   | datetime   |                | 修改时间                 |
| like_count    | int 11     |                | 点赞数量                 |

**bbs_comment**	评论表

| 字段        | 类型/长度    | 约束     | 注释                                 |
| ----------- | ------------ | -------- | ------------------------------------ |
| id          | int          | 自增     | 主键                                 |
| content     | varchar 1000 | 不为null | 评论内容                             |
| user_id     | int          | 不为null | 评论用户id                           |
| post_id     | int          | 不为null | 评论帖子的id                         |
| type        | int          |          | 0-普通评论 1-楼中楼评论              |
| target_id   | int          |          | 目标id 0-普通评论 楼中楼为comment_id |
| is_delete   | bit          | 默认0    | 逻辑删除 0-未删除 1-删除             |
| create_time | datetime     |          | 发布时间                             |

**bbs_tag**	标签表

| 字段        | 类型/长度 | 约束     | 注释                     |
| ----------- | --------- | -------- | ------------------------ |
| id          | int       | 自增     | 主键                     |
| name        | varchar30 | 不为null | 标签名                   |
| post_count  | int       | 默认0    | 标签下帖子数量           |
| is_delete   | bit       | 默认0    | 逻辑删除 0-未删除 1-删除 |
| create_time | datetime  |          | 发布时间                 |
| modify_time | datetime  |          | 修改时间                 |

**bbs_post_tag**	帖子标签表

| 字段    | 类型/长度 | 约束        | 注释   |
| ------- | --------- | ----------- | ------ |
| id      | int       | 自增        | 主键   |
| tag_id  | int       | bbs_tag pk  | 标签id |
| post_id | int       | bbs_post pk | 帖子id |

**bbs_follow**	关注表

| 字段        | 类型/长度 | 约束     | 注释         |
| ----------- | --------- | -------- | ------------ |
| id          | int       | 自增     | 主键         |
| parent_id   | int       | 不为null | 被关注用户id |
| follwer_id  | int       | 不为null | 关注的用户id |
| create_time | datetime  |          | 关注时间     |

**bbs_user**	用户表

| 字段        | 类型/长度   | 约束     | 注释                        |
| ----------- | ----------- | -------- | --------------------------- |
| id          | int         | 自增     | 主键                        |
| account     | varchar15   | 不为null | 账号                        |
| nick_name   | varchar20   |          | 昵称                        |
| password    | varchar100  |          | 密码md5加密                 |
| salt        | varchar20   |          | 密码盐                      |
| avatar      | varchar1000 |          | 头像url                     |
| email       | varchar255  |          | 邮箱                        |
| phone       | varchar255  |          | 手机号码                    |
| score       | int         |          | 积分                        |
| about       | varchar255  |          | 关于/个性签名/简介          |
| role        | int         |          | 用户角色 0-普通用户 1-admin |
| status      | bit         |          | 状态 0-正常 1-封禁          |
| create_time |             |          | 加入时间                    |
| modify_time |             |          | 修改资料时间                |
| is_delete   |             |          | 是否注销 0-否 1-是          |



## 接口需求文档

### API `/xxx`

> 简单功能描述

**请求方法** 	 `/xxx`

登录/admin

详细描述：详细描述

参数：

-
-

返回：

-
-



### 系统公告 `/billboards`

> 获取最新系统公告

**GET** 	 `/`

详细描述：获取最新系统公告

参数：无

返回：

- id
- content 公告内容
- createTime 创建时间



### 帖子 `/posts`

> 获取帖子总数

**请求方法** 	 `/count`

详细描述：获取帖子总数 用于分页

参数：无

返回：

- total 帖子总数



> 获取某个用户发布帖子总数

**请求方法** 	 `/count/{userId}`

详细描述：获取某个用户发布帖子的总数 用于分页

参数：userId 用户id

返回：

- total 帖子总数



> 分页查询所有帖子(用于首页的展示)

**GET** 	 `/{tab}?page=xx&limit=xx`

详细描述：详细描述

参数：

- page 当前页数
- limit 每页条数
- tab 排序规则 最新/最热

返回：PostListVO

- id
- title 帖子标题
- commentCount 评论数量
- collectCount 收藏数量
- viewCount 浏览数量
- top 是否置顶
- essencs 是否加精
- tags[] 帖子标签
- createTime 发布时间
- user
    - id
    - username 账号
    - nickName 昵称
    - avatar 头像url

> 发表一篇帖子

**POST** 	 `/`

详细描述：发布一篇帖子

参数：

- PostDTO
    - title 标题
    - content 内容
    - tags[] 标签

返回：

- message提示

> 根据帖子id查询帖子详细信息

**GET** 	 `/{postId}`

详细描述：根据帖子postId查询帖子详细信息

参数：

- postId 帖子id

返回：

PostDetailVO

- id
- title 帖子标题
- content 内容
- commentCount 评论数量
- collectCount 收藏数量
- viewCount 浏览数量
- top 是否置顶
- essencs 是否加精
- tags[] 帖子标签
- modifyTime 最后修改于 修改时间

> 根据username分页查询某用户发布的所有帖子(用于个人中心展示)

**GET** 	 `/user/{username}?page=xx&limit=xx`

详细描述：分页查询某用户发布的所有帖子(用于个人中心展示)

参数：

- username用户账号

- page 当前页数
- limit 每页条数

返回：PostCenterVO

- id
- title 帖子标题
- commentCount 评论数量
- collectCount 收藏数量
- viewCount 浏览数量
- top 是否置顶
- essencs 是否加精
- tags[] 帖子标签
- createTime 发布时间



### 用户关系 `/relationship`

> 判断当前用户是否关注某用户

登录

**GET** 	 `/validate/{userId}`

详细描述：判断当前用户是否关注userId用户

参数：

- userId 被关注用户的ID

返回：

- favorited boolean 是否关注



> 关注某用户

**GET** 	 `/follow/{userId}`

登录

详细描述：当前用户关注userId用户

参数：

- userId 关注用户id

返回：

- message



> 取关某用户

**GET** 	 `/unfollow/{userId}`

登录

当前登录用户取消关注某用户

参数：

- userId 关注用户id

返回：

- message提示





### 认证接口 `/auth`

> 平台用户注册

**POST** 	 `/register`

详细描述：用户注册

参数：registerDTO

- username 账号
- nickname昵称
- password 密码
- checkPass 确定密码

返回：

- message



> 平台用户登录

**POST** 	 `/login`

详细描述：用户登录

参数：loginDTO

- username 账号
- password 密码
- rememberMe 记住我

返回：

- message



> 退出登录

**GET** 	 `/logout`

需登录

详细描述：退出登录

参数：无

返回：

- message



### 用户	`/user`

> 获取当前登录用户的信息

登录

**GET** 	 `/info`

需登录

详细描述：获取当前登录用户的信息

参数：无

返回：userInfoVO

- username 账号
- nickname 昵称
- avatar 头像





### 评论 `/comment`

> 加载某帖子下所有的评论 分页

**GET** 	 `/{postId}?page=xx&limit=xx`

详细描述：详细描述

参数：

- postId 帖子id
- page 当前页
- limit 每页条数

返回：

commentVO

- id 主评论id
- content 内容
- createTime 发布时间
- user 作者
    - user_id 发表者id
    - username 发表用户的账号
    - nickname 发表用户的昵称
    - avatar 发表用户的头像
- List<commentVO>
    - 楼中楼套娃





> 发表评论

**POST** 	 `/{postId}`

登录

详细描述：在postId帖子下发表评论

参数：commentDTO

- content
- targetId 0-回复帖子 1-回复楼中楼帖子的id

返回：

- message
