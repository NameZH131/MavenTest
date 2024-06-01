/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : school_server_jstest7

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 01/06/2024 12:31:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
                           `c_id` int(11) NOT NULL,
                           `c_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'web前端开发');
INSERT INTO `course` VALUES (2, '汇编');
INSERT INTO `course` VALUES (3, 'English');

-- ----------------------------
-- Table structure for serve
-- ----------------------------
DROP TABLE IF EXISTS `serve`;
CREATE TABLE `serve`  (
                          `s_id` int(11) NOT NULL,
                          `c_id` int(11) NOT NULL,
                          `t_id` int(11) NOT NULL,
                          `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                          INDEX `fk_serve_teacher_1`(`t_id`) USING BTREE,
                          INDEX `fk_serve_course_1`(`c_id`) USING BTREE,
                          INDEX `fk_serve_student_1`(`s_id`) USING BTREE,
                          CONSTRAINT `serve_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `course` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                          CONSTRAINT `serve_ibfk_2` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                          CONSTRAINT `serve_ibfk_3` FOREIGN KEY (`t_id`) REFERENCES `teacher` (`t_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of serve
-- ----------------------------
INSERT INTO `serve` VALUES (1, 1, 1, '3.0');
INSERT INTO `serve` VALUES (1, 3, 3, '3.2');
INSERT INTO `serve` VALUES (2, 1, 1, '3.3');
INSERT INTO `serve` VALUES (2, 2, 2, '3.4');
INSERT INTO `serve` VALUES (3, 1, 1, '3.6');
INSERT INTO `serve` VALUES (1, 2, 4, '0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
                            `s_id` int(11) NOT NULL,
                            `s_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `s_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `s_age` int(11) NULL DEFAULT NULL,
                            `s_gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `s_grade` int(11) NULL DEFAULT NULL,
                            PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '123', '小小怪', 18, '男', 2);
INSERT INTO `student` VALUES (2, '123', '大大怪', 19, '女', 3);
INSERT INTO `student` VALUES (3, '123', '开心超人', 20, '女', 4);
INSERT INTO `student` VALUES (4, '123', '小灰灰', 6, '男', 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
                            `t_id` int(11) NOT NULL,
                            `t_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `t_age` int(11) NULL DEFAULT NULL,
                            `t_gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            `t_workgae` int(11) NULL DEFAULT NULL,
                            `t_major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                            PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 'web副院长', 50, '男', 20, 'web');
INSERT INTO `teacher` VALUES (2, '汇编姜喵喵', 51, '男', 21, '底层');
INSERT INTO `teacher` VALUES (3, 'English谢大大', 52, '男', 22, '语言逻辑');
INSERT INTO `teacher` VALUES (4, '来开班会的辅导员', 20, '女', 1, '马克思理论');

SET FOREIGN_KEY_CHECKS = 1;