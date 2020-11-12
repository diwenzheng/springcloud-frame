## ========= OPEN_API_DB ============

CREATE DATABASE IF NOT EXISTS OPEN_API_DB DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE OPEN_API_DB;


/*
 Navicat Premium Data Transfer

 Source Server         : 10.18.2.112
 Source Server Type    : MySQL
 Source Server Version : 100413
 Source Host           : 10.18.2.112:3306
 Source Schema         : OPEN_API_DB

 Target Server Type    : MySQL
 Target Server Version : 100413
 File Encoding         : 65001

 Date: 31/07/2020 17:34:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(30) DEFAULT NULL COMMENT 'app名称',
  `namespace_name` varchar(20) DEFAULT NULL COMMENT 'namespace名称',
  `modify_author` varchar(20) DEFAULT NULL COMMENT '修改者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of author
-- ----------------------------
BEGIN;
INSERT INTO `author` VALUES (1, 'service_provision_01', 'general', 'admin', '2020-05-27 14:29:42', '2020-07-31 16:10:38');
INSERT INTO `author` VALUES (2, 'service_provision_01', 'tremblant', 'admin', '2020-05-27 15:05:24', '2020-07-31 16:07:17');
INSERT INTO `author` VALUES (3, 'service_provision_01', 'multi-sim', 'admin', '2020-05-27 15:05:35', '2020-07-31 16:10:42');
INSERT INTO `author` VALUES (6, 'diameter_01', 'diameter_alarm', 'admin', '2020-05-27 15:08:29', '2020-07-31 16:11:47');
INSERT INTO `author` VALUES (7, 'ecs_01', 'general', 'admin', '2020-05-27 15:08:48', '2020-07-31 16:08:51');
INSERT INTO `author` VALUES (8, 'ecs_01', 'ecs_alarm', 'admin', '2020-05-27 15:09:06', '2020-07-31 16:11:55');
INSERT INTO `author` VALUES (9, 'carrier_apns_01', 'general', 'admin', '2020-05-27 15:09:37', '2020-07-31 16:09:59');
INSERT INTO `author` VALUES (10, 'esim_client_01', 'general', 'admin', '2020-05-27 15:09:48', '2020-05-27 15:09:48');
INSERT INTO `author` VALUES (11, 'esim_client_01', 'esimclient_alarm', 'admin', '2020-05-27 15:10:04', '2020-07-31 16:12:08');
INSERT INTO `author` VALUES (12, 'soap_01', 'general', 'admin', '2020-05-27 15:10:22', '2020-07-31 17:13:53');
INSERT INTO `author` VALUES (13, 'soap_01', 'soap_alarm', 'admin', '2020-05-27 15:10:34', '2020-07-31 16:12:15');
INSERT INTO `author` VALUES (14, 'websheet_server_01', 'general', 'admin', '2020-05-27 15:10:50', '2020-05-27 15:10:50');
INSERT INTO `author` VALUES (15, 'es_common', 'general', 'admin', '2020-05-27 15:11:07', '2020-05-27 15:11:07');
INSERT INTO `author` VALUES (16, 'prepose_interface_01', 'heartInfo', 'admin', '2020-05-27 15:11:26', '2020-07-31 16:14:40');
INSERT INTO `author` VALUES (17, 'prepose_interface_01', 'heartToBusiness', 'admin', '2020-05-27 15:11:29', '2020-07-31 16:14:40');
INSERT INTO `author` VALUES (18, 'prepose_interface_01', 'general', 'admin', '2020-06-18 02:30:50', '2020-06-18 02:32:03');
INSERT INTO `author` VALUES (19, 'prepose_interface_01', 'prepose_test_imsi', 'admin', '2020-06-18 02:32:35', '2020-07-31 17:03:44');
INSERT INTO `author` VALUES (20, 'ecs_01', 'bundle', 'admin', '2020-06-01 08:23:19', '2020-07-31 16:10:10');
INSERT INTO `author` VALUES (21, 'carrier_apns_01', 'carrierapns_alarm', 'admin', '2020-07-31 15:55:50', '2020-07-31 16:12:01');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
