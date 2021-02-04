-- 表中有数据时，不可drop掉表
-- drop table  IF exists u_request_xx

CREATE TABLE `u_request_xx` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `sender_id` varchar(50) NOT NULL COMMENT '发送方id',
  `sender_username` varchar(50) NOT NULL COMMENT '发送方用户名',
  `recipient_id` varchar(50) NOT NULL COMMENT '接受方id',
  `recipient_username` varchar(50) NOT NULL COMMENT '接受方用户名',
  `request_state` varchar(2) NOT NULL COMMENT '请求验证状态，1：未处理；2：通过；3不通过；',
  `is_valid` varchar(2) DEFAULT '1' COMMENT '是否有效，字典(0否；1是)，缺省值为1',
  `created_date` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  `modified_date` decimal(13,0) DEFAULT NULL COMMENT '最后修改时间',
  `deleted_date` decimal(13,0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='好友请求信息表'


-- drop table  IF exists u_user_friend_ref

CREATE TABLE `u_user_friend_ref` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `friend_id` varchar(50) NOT NULL COMMENT '好友id',
  `friend_username` varchar(50) NOT NULL COMMENT '好友用户名',
  `is_valid` varchar(2) DEFAULT '1' COMMENT '是否有效，字典(0否；1是)，缺省值为1',
  `created_date` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  `modified_date` decimal(13,0) DEFAULT NULL COMMENT '最后修改时间',
  `deleted_date` decimal(13,0) DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_friend_id` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT=' 好友关联表'