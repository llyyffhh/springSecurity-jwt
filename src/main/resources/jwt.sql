
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) NOT NULL DEFAULT '',
  `menu_desc` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '/user/add', '添加用户');
INSERT INTO `sys_menu` VALUES (2, '/user/edit', '修改用户');
INSERT INTO `sys_menu` VALUES (3, '/user/delete', '删除用户');
INSERT INTO `sys_menu` VALUES (4, '/user/list', '查询用户列表');
INSERT INTO `sys_menu` VALUES (5, '/work/add', '布置任务');
INSERT INTO `sys_menu` VALUES (6, '/work/edit', '修改任务');
INSERT INTO `sys_menu` VALUES (7, '/work/do', '执行任务');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL DEFAULT '',
  `role_desc` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 'admin', '管理员权限');
INSERT INTO `sys_role` VALUES (2, 'manager', '经理权限');
INSERT INTO `sys_role` VALUES (3, 'employee', '普通员工权限');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `menu_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3);
INSERT INTO `sys_role_menu` VALUES (4, 1, 4);
INSERT INTO `sys_role_menu` VALUES (5, 1, 5);
INSERT INTO `sys_role_menu` VALUES (6, 1, 6);
INSERT INTO `sys_role_menu` VALUES (7, 1, 7);
INSERT INTO `sys_role_menu` VALUES (8, 2, 4);
INSERT INTO `sys_role_menu` VALUES (9, 2, 5);
INSERT INTO `sys_role_menu` VALUES (10, 2, 6);
INSERT INTO `sys_role_menu` VALUES (11, 2, 7);
INSERT INTO `sys_role_menu` VALUES (12, 3, 7);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(128) NOT NULL DEFAULT '',
  `enabled` tinyint(2) NOT NULL DEFAULT '1',
  `role_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$zoe8QI1Fp7zRGSDHvihW1eRTRV60hEoDvFwbmtBfvBwa1X7r8Idv2', 1, 1);
INSERT INTO `sys_user` VALUES (2, 'user1', '$2a$10$NsXvqha1rvxqeLQ.nErUcuY.NHHGrXd0P6/G2OB9Fe8ZXYkimFDSS', 1, 2);
INSERT INTO `sys_user` VALUES (3, 'user2', '$2a$10$jQ.ikQUvpUvN/.PH9TYiou0MwgwmqoJsYPxUhgwLoJFz/5M9t53Ui', 1, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
