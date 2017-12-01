-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;

-- use database

use seckill;

-- create seckill stock table
CREATE TABLE seckill(
seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '库存id',
  `name` VARCHAR(128) NOT NULL COMMENT '名字',
  `number` int NOT NULL COMMENT '库存数量',
  `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE =InnoDb AUTO_INCREMENT=1000 DEFAULT CHARSET =utf8 COMMENT ='秒杀库存表';

# 初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
('1000元秒杀iPhone','10','2017-8-22 00:00:00','2017-8-22 10:00:00'),
('500元秒杀iPad2','20','2017-8-22 00:00:00','2017-8-22 10:00:00'),
('400元秒杀小米','30','2017-8-22 00:00:00','2017-8-22 10:00:00'),
('300元秒杀三星','40','2017-8-22 00:00:00','2017-8-22 10:00:00'),
('200元秒杀华为','50','2017-8-22 00:00:00','2017-8-22 10:00:00');


# 秒杀成功明细表
# 用户登录相关信息

CREATE TABLE success_killes(
seckill_id BIGINT NOT NULL COMMENT '库存ID',
user_phone BIGINT NOT NULL COMMENT '用户手机号',
state TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识：-1无效，0成功，1已发货，2已收货',
crate_time TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone),/*联合主建*/
KEY idx_create_time(crate_time)
)ENGINE =innoDb DEFAULT CHARSET =utf8 COMMENT '秒杀成功明细表';

# 连接mysql

mysql -uroot -p