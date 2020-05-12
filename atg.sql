/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 127.0.0.1:3306
 Source Schema         : atg

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 12/05/2020 12:18:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for requirementtable
-- ----------------------------
DROP TABLE IF EXISTS `requirementtable`;
CREATE TABLE `requirementtable`  (
  `requirement_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `requirement_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_input` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_output` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`requirement_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of requirementtable
-- ----------------------------
INSERT INTO `requirementtable` VALUES ('R1.1', 'requirement1', 'null', '5,6,7', '1,2,3,4', '8,9');

-- ----------------------------
-- Table structure for testcasetable
-- ----------------------------
DROP TABLE IF EXISTS `testcasetable`;
CREATE TABLE `testcasetable`  (
  `testcase_id` int(11) NOT NULL AUTO_INCREMENT,
  `testcase_requirementid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_input` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_output` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_evaluate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`testcase_id`) USING BTREE,
  INDEX `testcase_requirementid`(`testcase_requirementid`) USING BTREE,
  CONSTRAINT `testcasetable_ibfk_1` FOREIGN KEY (`testcase_requirementid`) REFERENCES `requirementtable` (`requirement_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of testcasetable
-- ----------------------------
INSERT INTO `testcasetable` VALUES (1, 'R1.1', 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (2, 'R1.1', 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (3, 'R1.1', 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (4, 'R1.1', 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (5, 'R1.1', 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (6, 'R1.1', 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (7, 'R1.1', 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (8, 'R1.1', 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');

-- ----------------------------
-- Table structure for typetable
-- ----------------------------
DROP TABLE IF EXISTS `typetable`;
CREATE TABLE `typetable`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type_range` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of typetable
-- ----------------------------
INSERT INTO `typetable` VALUES (1, 'int', '[-65536,65535]');
INSERT INTO `typetable` VALUES (2, 'emmu', '[wait,process]');
INSERT INTO `typetable` VALUES (3, 'emmu', '[off,on]');

-- ----------------------------
-- Table structure for variablestable
-- ----------------------------
DROP TABLE IF EXISTS `variablestable`;
CREATE TABLE `variablestable`  (
  `variables_id` int(11) NOT NULL AUTO_INCREMENT,
  `variables_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `variables_type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`variables_id`) USING BTREE,
  INDEX `variables_type`(`variables_type`) USING BTREE,
  CONSTRAINT `variablestable_ibfk_1` FOREIGN KEY (`variables_type`) REFERENCES `typetable` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of variablestable
-- ----------------------------
INSERT INTO `variablestable` VALUES (1, 'S_A', 1);
INSERT INTO `variablestable` VALUES (2, 'S_B', 1);
INSERT INTO `variablestable` VALUES (3, 'S_C', 1);
INSERT INTO `variablestable` VALUES (4, 'S_D', 1);
INSERT INTO `variablestable` VALUES (5, 'MODE', 2);
INSERT INTO `variablestable` VALUES (6, 'SENSOR', 3);
INSERT INTO `variablestable` VALUES (7, 'PROTECT_SWITCH', 3);
INSERT INTO `variablestable` VALUES (8, 'MAIN_WARNING', 3);
INSERT INTO `variablestable` VALUES (9, 'PROTECTOR', 3);

SET FOREIGN_KEY_CHECKS = 1;
