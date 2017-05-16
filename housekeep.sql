/*
Navicat MySQL Data Transfer

Source Server         : housekeep
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : housekeep

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-05-16 16:59:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `apartment`
-- ----------------------------
DROP TABLE IF EXISTS `apartment`;
CREATE TABLE `apartment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apartmentName` varchar(255) NOT NULL,
  `companyName` varchar(255) NOT NULL,
  `allNum` varchar(255) NOT NULL,
  `rentNum` varchar(255) NOT NULL,
  `appointNum` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apartment
-- ----------------------------
INSERT INTO `apartment` VALUES ('1', '测试公寓', '测试公寓公司', '10', '3', '7');

-- ----------------------------
-- Table structure for `apartmentinfo`
-- ----------------------------
DROP TABLE IF EXISTS `apartmentinfo`;
CREATE TABLE `apartmentinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apartmentId` int(11) NOT NULL,
  `apartmentDay` timestamp NOT NULL,
  `rentAll` int(11) NOT NULL,
  `appointAll` int(11) NOT NULL,
  `rentNumber` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apartmentinfo
-- ----------------------------
INSERT INTO `apartmentinfo` VALUES ('1', '1', '2017-05-12 00:00:00', '10', '2', '0');
INSERT INTO `apartmentinfo` VALUES ('2', '1', '2017-05-16 15:43:55', '10', '4', '1');
INSERT INTO `apartmentinfo` VALUES ('3', '1', '2017-05-16 15:43:57', '10', '0', '2');
INSERT INTO `apartmentinfo` VALUES ('4', '1', '2017-05-16 15:44:01', '10', '1', '1');

-- ----------------------------
-- Table structure for `appoint`
-- ----------------------------
DROP TABLE IF EXISTS `appoint`;
CREATE TABLE `appoint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `houseId` int(11) NOT NULL,
  `ownerId` int(11) NOT NULL,
  `renterName` varchar(255) NOT NULL,
  `renterPhone` varchar(255) NOT NULL,
  `renterSex` varchar(11) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `houseId` (`houseId`),
  KEY `ownerId` (`ownerId`),
  CONSTRAINT `appoint_ibfk_1` FOREIGN KEY (`houseId`) REFERENCES `house` (`id`),
  CONSTRAINT `appoint_ibfk_2` FOREIGN KEY (`ownerId`) REFERENCES `owner` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appoint
-- ----------------------------
INSERT INTO `appoint` VALUES ('1', '2', '2', '阿萨啊', '13322569987', '先生', '2017-05-16', '上午');
INSERT INTO `appoint` VALUES ('2', '2', '2', 'qwe', '15536984563', '先生', '2017-05-16', '上午');

-- ----------------------------
-- Table structure for `house`
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(20) NOT NULL,
  `district` varchar(20) NOT NULL,
  `bussiness` varchar(20) NOT NULL,
  `community` varchar(20) NOT NULL,
  `buildingNum` varchar(11) NOT NULL,
  `initNum` varchar(11) NOT NULL,
  `houseNum` varchar(20) NOT NULL,
  `acrea` double NOT NULL,
  `price` double NOT NULL,
  `floor` varchar(255) NOT NULL,
  `orient` varchar(20) NOT NULL,
  `decoration` varchar(20) NOT NULL,
  `rentType` varchar(20) NOT NULL,
  `rentTag` varchar(20) NOT NULL,
  `ownerId` int(11) NOT NULL,
  `renterId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('1', '北京', '朝阳', '酒仙桥', '酒仙桥五街坊', '103', '4', '101', '33', '2600', '1', '东', '精装', '整租', '待出租', '2', '1');
INSERT INTO `house` VALUES ('2', '北京', '东城', '东单', '北湾子胡同', '2', '1', '203', '26', '3200', '2', '南', '简装', '整租', '待出租', '2', null);
INSERT INTO `house` VALUES ('3', '北京', '朝阳', '三里屯', '三里屯东里', '16', '3', '501', '18', '2400', '5', '东', '精装', '整租', '已租出', '3', null);
INSERT INTO `house` VALUES ('4', '北京', '海淀', '中关村', '东南小区', '8', '2', '302', '12', '1800', '3', '北', '精装', '合租', '待出租', '4', null);
INSERT INTO `house` VALUES ('5', '北京', '昌平', '沙河', '北街家园', '603', '1', '403', '21', '3000', '4', '南', '简装', '合租', '已租出', '5', '2');
INSERT INTO `house` VALUES ('6', '北京', '朝阳', '三里屯', '三里屯南里', '421', '5', '603', '23', '3600', '6', '南', '简装', '合租', '待出租', '7', null);

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `houseId` int(11) NOT NULL,
  `ownerId` int(11) NOT NULL,
  `renterName` varchar(11) NOT NULL,
  `orderType` varchar(11) NOT NULL,
  `orderDate` timestamp NOT NULL,
  `renterTel` varchar(255) NOT NULL,
  `orderPrice` varchar(255) NOT NULL,
  `ownerTel` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '1', '1', 'asd', 'asd', '2017-05-15 16:56:50', '11111', '123', '12312');

-- ----------------------------
-- Table structure for `owner`
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(255) NOT NULL,
  `ownerTel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES ('1', '房东1', '13322331133');
INSERT INTO `owner` VALUES ('2', '房东2', '13525364466');
INSERT INTO `owner` VALUES ('3', '房东3', '13355669988');
INSERT INTO `owner` VALUES ('4', '房东4', '15236554498');
INSERT INTO `owner` VALUES ('5', '房东5', '15535569985');
INSERT INTO `owner` VALUES ('6', '房东6', '15835446879');
INSERT INTO `owner` VALUES ('7', '房东7', '15332468895');
INSERT INTO `owner` VALUES ('8', '房东8', '13659885623');

-- ----------------------------
-- Table structure for `renter`
-- ----------------------------
DROP TABLE IF EXISTS `renter`;
CREATE TABLE `renter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `renterName` varchar(255) NOT NULL,
  `renterTel` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of renter
-- ----------------------------
INSERT INTO `renter` VALUES ('1', '租客1', '15233624563');
INSERT INTO `renter` VALUES ('2', '租客2', '15323654879');
INSERT INTO `renter` VALUES ('3', '', '');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `passWord` varchar(255) NOT NULL,
  `apartmentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test1', '111', null);
INSERT INTO `user` VALUES ('2', 'test2', '111111', '1');
INSERT INTO `user` VALUES ('3', 'test3', '123456', null);
INSERT INTO `user` VALUES ('4', 'test4', '123111', null);
INSERT INTO `user` VALUES ('5', '测试号', '111111', null);
INSERT INTO `user` VALUES ('6', '新用户', '123', null);
