/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : op_log_starter

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 18/07/2020 21:27:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary key ID',
  `op_ip` varchar(255) NOT NULL COMMENT 'Operation user ip',
  `op_type` varchar(255) NOT NULL COMMENT 'Operation type: add、update、delete',
  `op_desc` varchar(255) NOT NULL COMMENT 'Operation description',
  `op_request` varchar(2000) DEFAULT NULL COMMENT 'Operation request paramter',
  `op_result` varchar(2000) DEFAULT NULL COMMENT 'Operation return result',
  `op_start_time` datetime DEFAULT NULL COMMENT 'Operation start time',
  `op_end_time` datetime DEFAULT NULL COMMENT 'Operation end time',
  `user_id` varchar(255)  COMMENT 'Operation user id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
