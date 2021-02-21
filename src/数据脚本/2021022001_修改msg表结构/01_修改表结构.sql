-- drop table  IF exists `u_msg_xx`
CREATE TABLE `u_msg_xx` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `sender_id` varchar(50) NULL COMMENT '发送方id',
  `recipient_id` varchar(50)  NULL COMMENT '接受方id',
  `sender_username` varchar(50)  NULL COMMENT '发送方用户名',
  `recipient_username` varchar(50)  NULL COMMENT '接受方用户名',
  `msg` varchar(255)  NULL COMMENT '消息正文',
  `withdraw_flag` varchar(2)  NULL COMMENT '撤回标志，1：已撤回；2：未撤回；',
  `readed_flag` varchar(2)  NULL COMMENT '已读标志，1：已读；2：未读；',
  `is_valid` varchar(2) DEFAULT '1' COMMENT '是否有效，字典(0否；1是)，缺省值为1',
  `created_date` decimal(13,0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_sender_id` (`sender_id`),
  KEY `index_recipient_id` (`recipient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息信息表'