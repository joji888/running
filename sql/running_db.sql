CREATE TABLE `admin`  (
  `a_id` int NOT NULL COMMENT 'ID',
  `a_account` varchar(32) NULL COMMENT '账号',
  `a_password` varchar(256) NULL COMMENT '密码',
  PRIMARY KEY (`a_id`)
) COMMENT = '管理员表';

CREATE TABLE `apply`  (
  `apply_id` int NOT NULL COMMENT 'ID',
  `apply_time` datetime NULL COMMENT '申请时间',
  `apply_state` varchar(255) NULL COMMENT '申请状态',
  `apply_identity_code` varchar(255) NULL COMMENT '身份证',
  `apply_identity_code_front` varchar(255) NULL COMMENT '身份证正面',
  `apply_identity_code_back` varchar(255) NULL COMMENT '身份证背面',
  PRIMARY KEY (`apply_id`)
) COMMENT = '用户申请跑腿表';

CREATE TABLE `comment`  (
  `c_id` int NOT NULL COMMENT '评论ID',
  `o_id` int NOT NULL COMMENT '订单ID',
  `u_id` int NOT NULL COMMENT '用户ID',
  `c_time` datetime NULL COMMENT '评论时间',
  `c_type` enum('差评','好评') NULL COMMENT '差好评',
  `c_content` text NULL COMMENT '评论内容',
  PRIMARY KEY (`c_id`)
) COMMENT = '评论表';

CREATE TABLE `order`  (
  `o_id` int NOT NULL COMMENT 'ID',
  `u_id` int NOT NULL COMMENT '用户ID',
  `ts_id` int NULL COMMENT '订单子类',
  `ot_id` int NULL COMMENT '订单类型',
  `o_tile` varchar(255) NULL COMMENT '标题',
  `o_state` int NULL COMMENT '订单状态(0过期，1正常，2已接单，3结束)',
  `o_image` varchar(255) NULL COMMENT '订单图片',
  `o_describe` varchar(255) NULL COMMENT '订单描述',
  `o_end_time` datetime NULL COMMENT '订单结束时间',
  `o_create_time` datetime NULL COMMENT '订单发布时间',
  PRIMARY KEY (`o_id`)
) COMMENT = '订单表';

CREATE TABLE `order_type`  (
  `ot_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ot_name` varchar(50) NOT NULL COMMENT '类型名',
  PRIMARY KEY (`ot_id`)
) COMMENT = '订单类型表';

CREATE TABLE `order_type_son`  (
  `ts_id` int NOT NULL COMMENT 'ID',
  `ts_name` varchar(100) NULL COMMENT '子类型名',
  `ts_price` int NULL COMMENT '单价',
  PRIMARY KEY (`ts_id`)
) COMMENT = '订单子类型表';

CREATE TABLE `payment`  () COMMENT = '支付表';

CREATE TABLE `receive`  (
  `r_id` int NOT NULL COMMENT 'ID',
  `r_begin_time` datetime NULL COMMENT '开始时间',
  `r_end_time` datetime NULL COMMENT '结束时间',
  `r_seate` int NULL COMMENT '跑腿状态（0结束，1开始）',
  PRIMARY KEY (`r_id`)
) COMMENT = '跑腿接单表';

CREATE TABLE `record`  () COMMENT = '管理员操作记录表';

CREATE TABLE `running`  ();

CREATE TABLE `user`  (
  `u_id` int NOT NULL COMMENT 'id',
  `u_nick` varchar(100) NULL COMMENT '昵称',
  `u_account` varchar(32) NULL COMMENT '账号',
  `u_password` varchar(256) NULL COMMENT '密码',
  `u_email` varchar(256) NULL COMMENT '邮箱',
  `u_phone` varchar(20) NULL COMMENT '手机',
  `u_head_img` varchar(256) NULL COMMENT '头像',
  `u_gender` enum('男','女') NULL COMMENT '性别',
  PRIMARY KEY (`u_id`)
) COMMENT = '用户表';

