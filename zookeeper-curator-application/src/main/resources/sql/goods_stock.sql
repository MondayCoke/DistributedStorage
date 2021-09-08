/*
Navicat MySQL Data Transfer

Source Server         : 192.168.221.128
Source Server Version : 80025
Source Host           : 192.168.221.128:3306
Source Database       : world

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2021-08-09 15:47:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods_stock
-- ----------------------------
DROP TABLE IF EXISTS `goods_stock`;
CREATE TABLE `goods_stock` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `goods_no` int NOT NULL COMMENT '商品编号',
  `stock` int DEFAULT NULL COMMENT '库存',
  `isActive` smallint DEFAULT NULL COMMENT '是否上架（1上，0不是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
