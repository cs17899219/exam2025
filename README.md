### 用户积分转账接口

一、题目背景

系统中存在用户积分账户，每个用户都有一定数量的积分。
现在需要在现有的 Spring Boot 工程 中，开发一个接口，用于实现用户之间的积分转移。

项目已经具备基础工程和数据库连接配置，建表语句仅供参考；
不限制 ORM 框架；
不限制返回体结构，可自定义格式；
可以自行设计参数校验、异常处理逻辑；

二、数据库表结构(仅供参考)

CREATE TABLE user_points (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    points INT NOT NULL DEFAULT 0 COMMENT '当前积分',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间'
) COMMENT='用户积分表';

三、接口要求

接口路径
POST /api/points/transfer

请求参数示例
{
  "fromUserId": 1001,
  "toUserId": 1002,
  "points": 50
}

响应参数示例
{
  "code": 0,
  "message": "success",
  "data": {
    "fromUserId": 1001,
    "fromUserPoints": 450,
    "toUserId": 1002,
    "toUserPoints": 550
  }
}

四、功能说明

实现用户之间积分的转移功能：

将 fromUserId 用户的部分积分转移给 toUserId 用户；
转账后，返回双方的最新积分;
