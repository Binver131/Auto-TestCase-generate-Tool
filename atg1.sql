/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : atg1

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 15/06/2020 15:33:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model`  (
  `modelID` int(11) NOT NULL AUTO_INCREMENT,
  `modelIdentifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `modelName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `modelContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modelClass` enum('A','B','C') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`modelID`) USING BTREE,
  INDEX `model_id`(`modelIdentifier`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of model
-- ----------------------------
INSERT INTO `model` VALUES (1, 'M1.0', 'PFD', '主显示面板', 'A');
INSERT INTO `model` VALUES (2, 'M2.0', 'ND', '导航显示面板', 'B');
INSERT INTO `model` VALUES (3, 'V1.0', 'EICAS', '发动机显示和机组警告系统需求模型', 'C');

-- ----------------------------
-- Table structure for requirement
-- ----------------------------
DROP TABLE IF EXISTS `requirement`;
CREATE TABLE `requirement`  (
  `requirementID` int(11) NOT NULL AUTO_INCREMENT,
  `requirementIdentifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirementName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirementContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirementCondition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirementInput` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `requirementOutput` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modelID` int(11) NULL DEFAULT NULL,
  `rowRequirementID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`requirementID`) USING BTREE,
  INDEX `model`(`modelID`) USING BTREE,
  INDEX `RowRequirement`(`rowRequirementID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of requirement
-- ----------------------------
INSERT INTO `requirement` VALUES (1, 'R1.1', 'requirement1', 'null', '5,6,7', '1,2,3,4', '8,9', 1, 1);
INSERT INTO `requirement` VALUES (2, 'R1.2', 'requirement2', 'null', '5,6', '1,2,3', '8', 1, 1);
INSERT INTO `requirement` VALUES (3, 'R1.3', 'requirement3', 'null', '5', '1', '8', 1, 1);

-- ----------------------------
-- Table structure for rowrequirement
-- ----------------------------
DROP TABLE IF EXISTS `rowrequirement`;
CREATE TABLE `rowrequirement`  (
  `rowRequirementID` int(11) NOT NULL AUTO_INCREMENT,
  `rowRequirementIdentifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rowRequirementName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rowRequirementContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modelID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`rowRequirementID`) USING BTREE,
  INDEX `model`(`modelID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rowrequirement
-- ----------------------------
INSERT INTO `rowrequirement` VALUES (1, 'row1', '油门报警', '原始需求内容，原始需求内容，原始需求内容', 1);

-- ----------------------------
-- Table structure for testcase
-- ----------------------------
DROP TABLE IF EXISTS `testcase`;
CREATE TABLE `testcase`  (
  `testcaseID` int(11) NOT NULL AUTO_INCREMENT,
  `requirementID` int(11) NULL DEFAULT NULL,
  `testcaseCondition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcaseInput` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcaseOutput` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcaseType` enum('auto','manmade') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `testcaseEvaluate` enum('equal') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`testcaseID`) USING BTREE,
  INDEX `testcase_requirementid`(`requirementID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of testcase
-- ----------------------------
INSERT INTO `testcase` VALUES (1, 1, 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcase` VALUES (2, 1, 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcase` VALUES (3, 1, 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcase` VALUES (4, 1, 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcase` VALUES (5, 1, 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcase` VALUES (6, 1, 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcase` VALUES (7, 1, 'wait,off,on', '0,0,0,0', 'on,on', 'auto', 'equal');
INSERT INTO `testcase` VALUES (8, 1, 'wait,on,off', '1,2,3,4', 'off,off', 'auto', 'equal');
INSERT INTO `testcase` VALUES (9, 2, 'wait,off', '1,0,65535', 'off', 'auto', 'equal');
INSERT INTO `testcase` VALUES (10, 2, 'wait,off', '-66626,0,0', 'off', 'auto', 'equal');
INSERT INTO `testcase` VALUES (11, 3, 'process', '8', 'on', 'auto', 'equal');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `typeID` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `typeRange` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `modelID` int(11) NULL DEFAULT NULL,
  `typeSize` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `typeRowName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`typeID`) USING BTREE,
  INDEX `model`(`modelID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, 'int', '-65536,65535', 1, '4', 'int');
INSERT INTO `type` VALUES (2, 'emmu', 'wait,process', 1, '4', 'emmu');
INSERT INTO `type` VALUES (3, 'emmu2', 'off,on', 1, '4', 'emmu');

-- ----------------------------
-- Table structure for variable
-- ----------------------------
DROP TABLE IF EXISTS `variable`;
CREATE TABLE `variable`  (
  `variableID` int(11) NOT NULL AUTO_INCREMENT,
  `variableName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `typeID` int(11) NULL DEFAULT NULL,
  `modelID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`variableID`) USING BTREE,
  INDEX `variables_type`(`typeID`) USING BTREE,
  INDEX `variablestable_ibfk_2`(`modelID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of variable
-- ----------------------------
INSERT INTO `variable` VALUES (1, 'S_A', 1, 1);
INSERT INTO `variable` VALUES (2, 'S_B', 1, 1);
INSERT INTO `variable` VALUES (3, 'S_C', 1, 1);
INSERT INTO `variable` VALUES (4, 'S_D', 1, 1);
INSERT INTO `variable` VALUES (5, 'MODE', 2, 1);
INSERT INTO `variable` VALUES (6, 'SENSOR', 3, 1);
INSERT INTO `variable` VALUES (7, 'PROTECT_SWITCH', 3, 1);
INSERT INTO `variable` VALUES (8, 'MAIN_WARNING', 3, 1);
INSERT INTO `variable` VALUES (9, 'PROTECTOR', 3, 1);

SET FOREIGN_KEY_CHECKS = 1;
