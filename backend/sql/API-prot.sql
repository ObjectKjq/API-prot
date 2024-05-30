CREATE DATABASE `my_db`;

USE `my_db`;

DROP TABLE IF EXISTS `interface_info`;
-- 创建接口表
CREATE TABLE `interface_info` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '接口名',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `isDelete` int(11) DEFAULT '0' COMMENT '逻辑删除',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `path` varchar(255) DEFAULT NULL COMMENT '接口地址',
  `url` varchar(255) DEFAULT NULL COMMENT '访问地址',
  `requestHeader` text COMMENT '请求头',
  `responseHeader` text COMMENT '响应头',
  `status` int(11) DEFAULT '0' COMMENT '接口状态0关闭1开启',
  `method` varchar(255) DEFAULT NULL COMMENT '请求类型',
  `userId` bigint(20) DEFAULT NULL COMMENT '创建人',
  `requestParams` text COMMENT '请求参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `interface_info`(`id`,`name`,`createTime`,`updateTime`,`isDelete`,`description`,`path`,`url`,`requestHeader`,`responseHeader`,`status`,`method`,`userId`,`requestParams`) values 
(1770112476620853249,'获取用户名','2024-05-25 16:45:00','2024-05-25 16:45:02',0,'获取用户名','','/api/name/json','{\"Content-Type\":\"application/json\"}','{\"Content-Type\":\"application/json\"}',1,'POST',1766678405290979331,'[{\"name\":\"name\",\"type\":\"string\"}]');

DROP TABLE IF EXISTS `user`;
-- 创建用户表
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userAccount` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `userPassword` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `unionId` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信开放平台id',
  `mpOpenId` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公众号openId',
  `userName` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT 'qwe' COMMENT '用户昵称',
  `userAvatar` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `userProfile` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户简介',
  `userRole` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `accessKey` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '调用的标识',
  `secretKey` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密钥',
  PRIMARY KEY (`id`),
  KEY `idx_unionId` (`unionId`)
) ENGINE=InnoDB AUTO_INCREMENT=1766678405290979334 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

insert  into `user`(`id`,`userAccount`,`userPassword`,`unionId`,`mpOpenId`,`userName`,`userAvatar`,`userProfile`,`userRole`,`createTime`,`updateTime`,`isDelete`,`accessKey`,`secretKey`) values 
(1766678405290979331,'kjq','2170836ca489471cda1db8c6a87b4d02',NULL,NULL,'kjq','https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/unicloudlogo.png',NULL,'admin','2024-03-18 14:15:50','2024-03-18 19:43:36',0,'1ec5f329cd249063ffd7c784aaa5815d','1ec5f329cd249063ffd7c784aaa5815d');

DROP TABLE IF EXISTS `user_interface_info`;
-- 创建接口与用户关系表，表示用户是否被分配调用次数
CREATE TABLE `user_interface_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` bigint(20) DEFAULT NULL COMMENT '调用用户',
  `interfaceInfoId` bigint(20) DEFAULT NULL COMMENT '接口',
  `totalNum` int(11) DEFAULT NULL COMMENT '调用次数',
  `leftNum` int(11) DEFAULT NULL COMMENT '剩余次数',
  `status` int(11) DEFAULT '0' COMMENT '状态int0-正常，1-禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `isDelete` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

insert  into `user_interface_info`(`id`,`userId`,`interfaceInfoId`,`totalNum`,`leftNum`,`status`,`createTime`,`updateTime`,`isDelete`) values 
(1,1766678405290979331,1770112476620853249,36567,63466,0,'2024-03-20 17:46:49','2024-03-20 17:46:51',0),
(2,1766678405290979331,1769791920415494146,4,4,0,NULL,NULL,0),
(3,1766678405290979332,1769791920415494146,5,4,0,NULL,NULL,0),
(4,1766678405290979333,1769791920415494146,6,4,0,NULL,NULL,0);

