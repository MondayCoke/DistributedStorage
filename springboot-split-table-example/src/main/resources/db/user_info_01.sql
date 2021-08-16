/*
Navicat MySQL Data Transfer

Source Server         : 192.168.221.128
Source Server Version : 80025
Source Host           : 192.168.221.128:3306
Source Database       : world

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2021-08-04 22:48:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info_01
-- ----------------------------
DROP TABLE IF EXISTS `user_info_01`;
CREATE TABLE `user_info_01` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(30) NOT NULL,
  `passwd` varchar(30) NOT NULL,
  `sex` varchar(3) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `biz_id` bigint NOT NULL COMMENT '业务id',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user_info_02
-- ----------------------------
DROP TABLE IF EXISTS `user_info_02`;
CREATE TABLE `user_info_02` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(30) NOT NULL,
  `passwd` varchar(30) NOT NULL,
  `sex` varchar(3) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `biz_id` bigint NOT NULL COMMENT '业务id',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user_info_03
-- ----------------------------
DROP TABLE IF EXISTS `user_info_03`;
CREATE TABLE `user_info_03` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(30) NOT NULL,
  `passwd` varchar(30) NOT NULL,
  `sex` varchar(3) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `biz_id` bigint NOT NULL COMMENT '业务id',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user_info_04
-- ----------------------------
DROP TABLE IF EXISTS `user_info_04`;
CREATE TABLE `user_info_04` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(30) NOT NULL,
  `passwd` varchar(30) NOT NULL,
  `sex` varchar(3) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `biz_id` bigint NOT NULL COMMENT '业务id',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
