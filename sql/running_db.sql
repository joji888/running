/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 101.43.53.80:3306
 Source Schema         : running_db

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 18/04/2022 22:08:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `a_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `a_role` enum('root','admin') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色',
  `a_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号',
  `a_password` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'root', 'root', 'dc8e57766a4c2824576e96c4c637faeb050716ac1e4bfb1dfd839e41b51259cf');
INSERT INTO `admin` VALUES (2, 'admin', 'admin', '998ed4d621742d0c2d85ed84173db569afa194d4597686cae947324aa58ab4bb');
INSERT INTO `admin` VALUES (5, 'admin', 'admin01', '0d797573d9a4cfd32c71597e8dbf9f2ae23086c8e0870d5b0c90f29538e442f7');
INSERT INTO `admin` VALUES (6, 'root', 'admin02', '77176a3f47a0378521c227297905c6262ec898f327ccddb686c37d5ff8f079f4');
INSERT INTO `admin` VALUES (7, 'admin', 'admin123', '49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c');
INSERT INTO `admin` VALUES (8, 'admin', 'admin123', '49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c');

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `apply_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `u_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `apply_time` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `apply_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '申请状态（0 未审核 1 驳回 2同意）',
  `apply_reject_info` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '驳回信息',
  `apply_identity_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `apply_identity_code_front` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证正面',
  `apply_identity_code_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证背面',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户申请跑腿表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES (1, 1, '2021-12-20 08:00:00', '2', '请上传正确的身份证', '123456789123456789', 'dgsdgsd', 'jhhjghjgh');
INSERT INTO `apply` VALUES (2, 2, '2021-12-20 08:00:00', '0', '', '123456789123456789', 'dgsdgsd', 'jhhjghjgh');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `c_id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `o_id` int NOT NULL COMMENT '订单ID',
  `u_id` int NOT NULL COMMENT '用户ID',
  `c_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `c_type` enum('差评','好评') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '差好评',
  `c_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '评论内容',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, 1, 1, '2021-12-20 16:00:00', '好评', '好运');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `o_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `u_id` int NOT NULL COMMENT '用户ID',
  `ts_id` int NULL DEFAULT NULL COMMENT '订单子类',
  `ot_id` int NULL DEFAULT NULL COMMENT '订单类型',
  `o_tile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `o_price` int NULL DEFAULT NULL COMMENT '价格',
  `o_state` int NULL DEFAULT NULL COMMENT '订单状态(0过期，1正常，2已接单，3结束)',
  `o_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单图片',
  `o_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单描述',
  `o_end_time` datetime NULL DEFAULT NULL COMMENT '订单结束时间',
  `o_create_time` datetime NULL DEFAULT NULL COMMENT '订单发布时间',
  PRIMARY KEY (`o_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (9, 2, 3, 3, 'test', 1231231, 2, 'file/93c78658b6394ab0b62f92ae263682be.jpg', '12313', '2021-12-30 12:53:00', '2021-12-30 12:41:00');
INSERT INTO `order` VALUES (10, 2, 3, 3, '333', 1212, 3, 'file/47db7f94f66d49608f00f630319a3600.jpg', '122', '2021-12-31 17:12:00', '2021-12-30 12:44:00');
INSERT INTO `order` VALUES (11, 1, 3, 4, '找个叫vue的东西', 54, 1, 'file/58758c16df90421b8afc7d7a9b19d6c4.jpg', 'Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式JavaScript框架。 [5]  与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用（SPA）提供驱动。', '2021-12-29 16:00:00', '2021-12-02 16:00:00');
INSERT INTO `order` VALUES (12, 1, 3, 3, '送花', 80, 1, 'file/80c46f410b6c490aa6b10ddaec0a6dc9.jpg', '朋友过生日，想送个花，但是不在身边，希望来个小哥哥帮忙送一下。地址：XXX,时间：XXX,谢谢', '2021-12-28 16:00:00', '2021-11-30 16:00:00');
INSERT INTO `order` VALUES (13, 1, 10, 7, '帮忙送个文件', 50, 1, 'file/2677a00e6bbf4924833871ac8f720743.jpg', '机密文件，请勿拆封。地址：XXXX', '2021-12-29 16:00:00', '2021-12-02 16:00:00');
INSERT INTO `order` VALUES (14, 1, 3, 3, '物品', 2222, 1, 'file/b2af6737a97647cab6d660dcbd71dd3d.png', '希望好朋友早日康复。地址：xxxx.一定要看着他吃下去。', '2021-12-31 08:00:00', '2021-12-30 08:00:00');
INSERT INTO `order` VALUES (15, 2, 3, 4, '送个档案', 10, 1, 'file/bc13b359e8e0437981b3d0b1c85758a1.png', '麻烦去XXX帮我吧档案取回来。取件码：XXXXXX', '2021-12-30 12:43:00', '2021-12-30 12:37:00');
INSERT INTO `order` VALUES (16, 2, 10, 7, '送个文件', 20, 1, 'file/fbfb3b3d26b546c6b59aaa03d1de55ee.png', '公司文件，我已离职，给老板送过去，顺便祝他生活愉快。地址：xxxxx,一定嘚让他好好看。', '2022-01-18 17:00:00', '2021-12-30 12:39:00');
INSERT INTO `order` VALUES (17, 2, 1, 3, '网抑云', 99, 1, 'file/5c6a6a9724cf44f28a8f5bcbd8c87f38.jpg', '突然就抑云了', '2021-12-31 08:27:00', '2021-12-31 07:04:00');
INSERT INTO `order` VALUES (18, 2, 1, 3, '就是六', 12, 1, 'file/cde30bccd0c54e24807df3cc9a3c720b.jpg', 'qqq', '2021-12-31 17:36:00', '2021-12-31 07:45:00');
INSERT INTO `order` VALUES (19, 2, 1, 3, '就是六', 12, 1, 'file/fb668d2597854477be73983aa4b57381.jpg', '123', '2021-12-31 08:46:00', '2021-12-31 07:53:00');
INSERT INTO `order` VALUES (20, 2, 1, 3, '就是六', 10, 1, 'file/244cf3dd970f406093d480e03c04cf68.jpg', '吃鸡', '2021-12-31 09:31:00', '2021-12-31 07:54:00');

-- ----------------------------
-- Table structure for order_type
-- ----------------------------
DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type`  (
  `ot_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ot_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类型名',
  PRIMARY KEY (`ot_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_type
-- ----------------------------
INSERT INTO `order_type` VALUES (3, '送花');
INSERT INTO `order_type` VALUES (4, '代取');
INSERT INTO `order_type` VALUES (5, '代课');
INSERT INTO `order_type` VALUES (6, '接人');
INSERT INTO `order_type` VALUES (7, '配送');

-- ----------------------------
-- Table structure for order_type_son
-- ----------------------------
DROP TABLE IF EXISTS `order_type_son`;
CREATE TABLE `order_type_son`  (
  `ts_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ot_id` int NULL DEFAULT NULL COMMENT '类型名',
  `ts_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '子类型名',
  `ts_price` int NULL DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`ts_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单子类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_type_son
-- ----------------------------
INSERT INTO `order_type_son` VALUES (1, 3, '电脑', 50);
INSERT INTO `order_type_son` VALUES (2, 2, '药品', 20);
INSERT INTO `order_type_son` VALUES (3, 1, '档案', 100);
INSERT INTO `order_type_son` VALUES (10, 2, '文件', 100);

-- ----------------------------
-- Table structure for receive
-- ----------------------------
DROP TABLE IF EXISTS `receive`;
CREATE TABLE `receive`  (
  `r_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `o_id` int NULL DEFAULT NULL COMMENT '订单ID',
  `u_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `ru_id` int NULL DEFAULT NULL COMMENT '接单者',
  `r_begin_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `r_end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `r_seate` int NULL DEFAULT NULL COMMENT '跑腿状态（0结束，1放弃，2完成，3超时，4跑腿中）',
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '跑腿接单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of receive
-- ----------------------------
INSERT INTO `receive` VALUES (1, 1, 1, 4, '2021-12-28 16:00:00', '2021-12-29 16:00:00', 2);
INSERT INTO `receive` VALUES (3, 1, 2, 4, '2021-12-29 16:00:00', '2021-12-29 16:00:00', 0);
INSERT INTO `receive` VALUES (4, 1, 2, 4, '2021-12-29 16:00:00', '2021-12-29 16:00:00', 3);
INSERT INTO `receive` VALUES (5, 4, 2, 3, '2021-12-30 01:08:07', '2021-12-30 01:18:07', 4);
INSERT INTO `receive` VALUES (6, 3, 2, 3, '2021-12-30 01:24:41', '2021-12-30 01:34:41', 4);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `u_nick` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `u_role` enum('user','running') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'user' COMMENT '角色',
  `u_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号',
  `u_password` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `u_email` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `u_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机',
  `u_head_img` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `u_gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '小菲菲', 'running', 'wuqi', '123', '123', '123', 'file/479f9870d384490da9297270deb71c53.jpg', '女');
INSERT INTO `user` VALUES (2, '小猪猪', 'running', 'user', '173af653133d964edfc16cafe0aba33c8f500a07f3ba3f81943916910c257705', NULL, NULL, 'file/10f6a653dac844dd9aba6c8e1c207d9d.jpg', '男');
INSERT INTO `user` VALUES (4, '光头强', 'running', '110', '220112', NULL, NULL, 'file/96bc006f7e2b4757ac16482b3f4e250d.png', '男');
INSERT INTO `user` VALUES (7, '用户100569', 'user', '15226382287', '49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c', '2799489580@qq.com', '15226382287', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
