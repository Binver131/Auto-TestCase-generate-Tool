/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : atg

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 24/05/2020 10:40:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for models
-- ----------------------------
DROP TABLE IF EXISTS `models`;
CREATE TABLE `models`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `model_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `model_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Model_Class` enum('A','B','C') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`No`) USING BTREE,
  INDEX `model_id`(`model_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of models
-- ----------------------------
INSERT INTO `models` VALUES (1, 'M1.0', 'PFD', '主显示面板', 'A');
INSERT INTO `models` VALUES (2, 'M2.0', 'ND', '导航显示面板', 'B');

-- ----------------------------
-- Table structure for requirementtable
-- ----------------------------
DROP TABLE IF EXISTS `requirementtable`;
CREATE TABLE `requirementtable`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `requirement_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_input` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirement_output` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `model` int(11) NULL DEFAULT NULL,
  `RowRequirement` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE,
  INDEX `model`(`model`) USING BTREE,
  INDEX `RowRequirement`(`RowRequirement`) USING BTREE,
  CONSTRAINT `requirementtable_ibfk_1` FOREIGN KEY (`model`) REFERENCES `models` (`No`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `requirementtable_ibfk_2` FOREIGN KEY (`RowRequirement`) REFERENCES `rowrequirement` (`No`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of requirementtable
-- ----------------------------
INSERT INTO `requirementtable` VALUES (1, 'R1.1', 'requirement1', 'null', '5,6,7', '1,2,3,4', '8,9', 1, 1);
INSERT INTO `requirementtable` VALUES (2, 'R1.2', 'requirement2', 'null', '5,6', '1,2,3', '8', 1, 1);
INSERT INTO `requirementtable` VALUES (3, 'R1.3', 'requirement3', 'null', '5', '1', '8', 1, 1);

-- ----------------------------
-- Table structure for rowrequirement
-- ----------------------------
DROP TABLE IF EXISTS `rowrequirement`;
CREATE TABLE `rowrequirement`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `model` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE,
  INDEX `model`(`model`) USING BTREE,
  CONSTRAINT `rowrequirement_ibfk_1` FOREIGN KEY (`model`) REFERENCES `models` (`No`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rowrequirement
-- ----------------------------
INSERT INTO `rowrequirement` VALUES (1, '油门报警', '原始需求内容，原始需求内容，原始需求内容', 1);

-- ----------------------------
-- Table structure for testcasetable
-- ----------------------------
DROP TABLE IF EXISTS `testcasetable`;
CREATE TABLE `testcasetable`  (
  `testcase_id` int(11) NOT NULL AUTO_INCREMENT,
  `requirementid` int(11) NULL DEFAULT NULL,
  `testcase_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_input` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_output` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcase_evaluate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`testcase_id`) USING BTREE,
  INDEX `testcase_requirementid`(`requirementid`) USING BTREE,
  CONSTRAINT `testcasetable_ibfk_1` FOREIGN KEY (`requirementid`) REFERENCES `requirementtable` (`No`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of testcasetable
-- ----------------------------
INSERT INTO `testcasetable` VALUES (1, 1, 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (2, 1, 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (3, 1, 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (4, 1, 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (5, 1, 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (6, 1, 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (7, 1, 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (8, 1, 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (9, 2, 'wait,off', '1,0,65535', 'off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (10, 2, 'wait,off', '-66626,0,0', 'off', 'auto', 'equal');
INSERT INTO `testcasetable` VALUES (11, 3, 'process', '8', 'on', 'auto', 'equal');

-- ----------------------------
-- Table structure for typetable
-- ----------------------------
DROP TABLE IF EXISTS `typetable`;
CREATE TABLE `typetable`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type_range` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `model` int(11) NULL DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `typerowname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE,
  INDEX `model`(`model`) USING BTREE,
  CONSTRAINT `typetable_ibfk_1` FOREIGN KEY (`model`) REFERENCES `models` (`No`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of typetable
-- ----------------------------
INSERT INTO `typetable` VALUES (1, 'int', '[-65536,65535]', 1, '4', 'int');
INSERT INTO `typetable` VALUES (2, 'emmu', '[wait,process]', 1, '4', 'emmu');
INSERT INTO `typetable` VALUES (3, 'emmu', '[off,on]', 1, '4', 'emmu');

-- ----------------------------
-- Table structure for variablestable
-- ----------------------------
DROP TABLE IF EXISTS `variablestable`;
CREATE TABLE `variablestable`  (
  `variables_id` int(11) NOT NULL AUTO_INCREMENT,
  `variables_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `variables_type` int(11) NULL DEFAULT NULL,
  `model` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`variables_id`) USING BTREE,
  INDEX `variables_type`(`variables_type`) USING BTREE,
  INDEX `variablestable_ibfk_2`(`model`) USING BTREE,
  CONSTRAINT `variablestable_ibfk_1` FOREIGN KEY (`variables_type`) REFERENCES `typetable` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `variablestable_ibfk_2` FOREIGN KEY (`model`) REFERENCES `models` (`No`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of variablestable
-- ----------------------------
INSERT INTO `variablestable` VALUES (1, 'S_A', 1, 1);
INSERT INTO `variablestable` VALUES (2, 'S_B', 1, 1);
INSERT INTO `variablestable` VALUES (3, 'S_C', 1, 1);
INSERT INTO `variablestable` VALUES (4, 'S_D', 1, 1);
INSERT INTO `variablestable` VALUES (5, 'MODE', 2, 1);
INSERT INTO `variablestable` VALUES (6, 'SENSOR', 3, 1);
INSERT INTO `variablestable` VALUES (7, 'PROTECT_SWITCH', 3, 1);
INSERT INTO `variablestable` VALUES (8, 'MAIN_WARNING', 3, 1);
INSERT INTO `variablestable` VALUES (9, 'PROTECTOR', 3, 1);

SET FOREIGN_KEY_CHECKS = 1;
