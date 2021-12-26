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

 Date: 26/12/2021 10:22:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `a_id` int NOT NULL COMMENT 'ID',
  `a_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号',
  `a_password` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `apply_id` int NOT NULL COMMENT 'ID',
  `apply_time` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `apply_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '申请状态',
  `apply_identity_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证',
  `apply_identity_code_front` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证正面',
  `apply_identity_code_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证背面',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户申请跑腿表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `c_id` int NOT NULL COMMENT '评论ID',
  `o_id` int NOT NULL COMMENT '订单ID',
  `u_id` int NOT NULL COMMENT '用户ID',
  `c_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `c_type` enum('差评','好评') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '差好评',
  `c_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '评论内容',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `o_id` int NOT NULL COMMENT 'ID',
  `u_id` int NOT NULL COMMENT '用户ID',
  `ts_id` int NULL DEFAULT NULL COMMENT '订单子类',
  `ot_id` int NULL DEFAULT NULL COMMENT '订单类型',
  `o_tile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `o_state` int NULL DEFAULT NULL COMMENT '订单状态(0过期，1正常，2已接单，3结束)',
  `o_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单图片',
  `o_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单描述',
  `o_end_time` datetime NULL DEFAULT NULL COMMENT '订单结束时间',
  `o_create_time` datetime NULL DEFAULT NULL COMMENT '订单发布时间',
  PRIMARY KEY (`o_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_type
-- ----------------------------
DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type`  (
  `ot_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ot_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类型名',
  PRIMARY KEY (`ot_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_type_son
-- ----------------------------
DROP TABLE IF EXISTS `order_type_son`;
CREATE TABLE `order_type_son`  (
  `ts_id` int NOT NULL COMMENT 'ID',
  `ts_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '子类型名',
  `ts_price` int NULL DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`ts_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单子类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for receive
-- ----------------------------
DROP TABLE IF EXISTS `receive`;
CREATE TABLE `receive`  (
  `r_id` int NOT NULL COMMENT 'ID',
  `r_begin_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `r_end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `r_seate` int NULL DEFAULT NULL COMMENT '跑腿状态（0结束，1开始）',
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '跑腿接单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int NOT NULL COMMENT 'id',
  `u_nick` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `u_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号',
  `u_password` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `u_email` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `u_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机',
  `u_head_img` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `u_gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
