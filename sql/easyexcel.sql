
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `oldScore` int(0) NOT NULL COMMENT '旧分数',
  `currentScore` int(0) NULL DEFAULT NULL COMMENT '当前分数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '覃瑾瑜', 2, 815562);
INSERT INTO `user` VALUES (2, '高炎彬', 8907860, 42442602);
INSERT INTO `user` VALUES (3, '石嘉熙', 665342, 86753);
INSERT INTO `user` VALUES (4, '赵越彬', 426, 999);
INSERT INTO `user` VALUES (5, '廖智渊', 733840, 6529367);
INSERT INTO `user` VALUES (6, '傅明辉', 5, 57647299);
INSERT INTO `user` VALUES (7, '龚越彬', 43, 78204464);
INSERT INTO `user` VALUES (8, '陆烨霖', 1681509, 3);
INSERT INTO `user` VALUES (9, '黎晋鹏', 2, 134809752);
INSERT INTO `user` VALUES (10, '雷昊焱', 20, 3489470);
INSERT INTO `user` VALUES (11, '唐子骞', 62, 20720);
INSERT INTO `user` VALUES (12, '杜健雄', 2594, 3);
INSERT INTO `user` VALUES (13, '刘烨霖', 25, 120);
INSERT INTO `user` VALUES (14, '吕远航', 1, 59870);

SET FOREIGN_KEY_CHECKS = 1;
