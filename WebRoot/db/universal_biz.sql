/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : universal_biz

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-05-24 22:48:13
*/

use gasstation_cloud_db;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `addressId` int(11) NOT NULL AUTO_INCREMENT,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `altitude` decimal(19,2) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `latitude` decimal(12,8) DEFAULT NULL,
  `longitude` decimal(12,8) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `mobile` varchar(20) DEFAULT NULL COMMENT '联系手机',
  `state` varchar(255) DEFAULT NULL,
  `corpNames` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------



-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `audit` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `lookNum` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updateDate` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for bizorder
-- ----------------------------
DROP TABLE IF EXISTS `bizorder`;
CREATE TABLE `bizorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addingFreight` decimal(19,2) DEFAULT NULL,
  `createPartyId` int(11) DEFAULT NULL,
  `cusCorpPartyId` int(11) DEFAULT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `driverName` varchar(255) DEFAULT NULL,
  `driverPartyId` int(11) DEFAULT NULL,
  `driverTel` varchar(255) DEFAULT NULL,
  `endPointName1` varchar(255) DEFAULT NULL,
  `endPointName2` varchar(255) DEFAULT NULL,
  `endPointName3` varchar(255) DEFAULT NULL,
  `endPointPartyId1` int(11) DEFAULT NULL,
  `endPointPartyId2` int(11) DEFAULT NULL,
  `endPointPartyId3` int(11) DEFAULT NULL,
  `loadingGW` decimal(19,2) DEFAULT NULL,
  `loadingNW` decimal(19,2) DEFAULT NULL,
  `loadingTW` decimal(19,2) DEFAULT NULL,
  `loadingTime` varchar(255) DEFAULT NULL,
  `logisticsName` varchar(255) DEFAULT NULL,
  `logisticsPartyId` int(11) DEFAULT NULL,
  `planLoadingString` varchar(255) DEFAULT NULL,
  `plateNo` varchar(255) DEFAULT NULL,
  `quantity` decimal(19,2) DEFAULT NULL,
  `requiredString` varchar(255) DEFAULT NULL,
  `settleWeight` decimal(19,2) DEFAULT NULL,
  `shippingOrderId` int(11) DEFAULT NULL,
  `sourceName` varchar(255) DEFAULT NULL,
  `sourcePartyId` int(11) DEFAULT NULL,
  `supCorpPartyId` int(11) DEFAULT NULL,
  `supplierName` varchar(255) DEFAULT NULL,
  `tankNo` varchar(255) DEFAULT NULL,
  `totalSettleAmount` decimal(19,2) DEFAULT NULL,
  `unLoadOnRoad` varchar(255) DEFAULT NULL,
  `unLoadingGW` decimal(19,2) DEFAULT NULL,
  `unLoadingNW` decimal(19,2) DEFAULT NULL,
  `unLoadingTW` decimal(19,2) DEFAULT NULL,
  `unloadingTime` varchar(255) DEFAULT NULL,
  `weightGagFee` decimal(19,2) DEFAULT NULL,
  `transportFee` int(11) DEFAULT NULL,
  `freight` decimal(19,2) DEFAULT NULL,
  `money` decimal(19,2) DEFAULT NULL,
  `unitPrice` decimal(19,2) DEFAULT NULL,
  `purchaseCost` decimal(19,2) DEFAULT NULL,
  `freightPrice` decimal(19,2) DEFAULT NULL,
  `gasType` varchar(255) DEFAULT NULL,
  `trackNumber` varchar(255) DEFAULT NULL,
  `inStockFlag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bizorder
-- ----------------------------

-- ----------------------------
-- Table structure for corporation
-- ----------------------------
DROP TABLE IF EXISTS `corporation`;
CREATE TABLE `corporation` (
  `partyId` int(11) NOT NULL,
  `activateDate` varchar(255) DEFAULT NULL,
  `addressDes` varchar(255) DEFAULT NULL,
  `addressId` int(11) DEFAULT NULL,
  `authenticatedParty` int(11) DEFAULT NULL,
  `buildDate` varchar(255) DEFAULT NULL,
  `corpName` varchar(255) DEFAULT NULL,
  `corpShortName` varchar(255) DEFAULT NULL,
  `corporationTax` varchar(255) DEFAULT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `fullPinyin` varchar(255) DEFAULT NULL,
  `hasBeenAuthenticated` bit(1) NOT NULL,
  `license` varchar(255) DEFAULT NULL,
  `parentCorp` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `selfAccounting` bit(1) NOT NULL,
  `simplePinyin` varchar(255) DEFAULT NULL,
  `webChatId` varchar(255) DEFAULT NULL,
  `webUrl` varchar(255) DEFAULT NULL,
  `detail` longtext,
  `parentCorpName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`partyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of corporation
-- ----------------------------
INSERT INTO `corporation` VALUES ('1', null, '浙江省台州市临海市下桥路99号', '1', null, '2015-01-07', '浙江海圳荣液化石油气工业有限公司', '海圳荣', '', null, '', null, 'haizhenrong', '', '9133108261000210XD', '', null, '', 'hzr', '', '', '城镇燃气供应、贮存，瓶装燃气批发、零售，为船舶提供码头设施、在港区内提供货物装卸服务、港口设施租赁服务，钢瓶修理，厨房用具、消防器材销售。（依法须经批准的项目，经相关部门批准后方可开展经营活动）', null);


-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `belgon2PartyId` int(11) DEFAULT NULL,
  `bizManager` int(11) DEFAULT NULL,
  `bizStartDate` varchar(255) DEFAULT NULL,
  `corpPartyId` int(11) DEFAULT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createParty` int(11) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `serialNo` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `cusName` varchar(60) NOT NULL COMMENT '客户名称',
  `cusShortName` varchar(30) DEFAULT NULL COMMENT '客户简称',
  `cusContact` varchar(30) DEFAULT NULL COMMENT '联系人',
  `cusContactTel` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `cusContactIdNo` varchar(30) DEFAULT NULL COMMENT '联系人身份证',
  `receiptPlace` varchar(120) DEFAULT NULL COMMENT '送气地址',
  `cusCategory` varchar(20) DEFAULT NULL COMMENT '客户类型(居民户 ,企事业)',
  `cusType` varchar(20) DEFAULT NULL COMMENT '行业性质（工业，商业，企事业）',
  `gasCardNo` varchar(20) DEFAULT NULL COMMENT '用气证号',
  `saleType` varchar(20) DEFAULT NULL COMMENT '销售类型（批发|直销）',
  `bottleRentType` varchar(20) DEFAULT NULL COMMENT '钢瓶租用方式（全额押金|部分押金|免押金）',
  `cycleDays` int(11) DEFAULT NULL COMMENT '用气周期（天）',
  `payCycle` varchar(20) DEFAULT NULL COMMENT '结算周期（现结|月结|周结）',
  `priceType` varchar(20) DEFAULT NULL COMMENT '定价方式（浮动价|月价|半月价|季度价|年价|议价）',
  `corpCreditCode` varchar(30) DEFAULT NULL COMMENT '企业信用代码',
  `parentSerialNo` varchar(30) DEFAULT NULL COMMENT '上级客户编码',
  `parentCusName` varchar(60) DEFAULT NULL COMMENT '上级客户名称',
  `parentCusId` int(11) DEFAULT NULL COMMENT '上级客户Id',
  `payCustomer` varchar(60) DEFAULT NULL COMMENT '结算客户名称',
  `payCustomerId` int(11) DEFAULT NULL COMMENT '结算客户Id',
  `logoffDate` varchar(30) DEFAULT NULL COMMENT '销户时间',
  `logoffParty` int(11) DEFAULT NULL COMMENT '销户人',
  `bindOpenIds` varchar(255) DEFAULT NULL COMMENT '绑定的微信Id,可能有多个,逗号分隔,支持子女给父母定气',
  `state` varchar(20) DEFAULT NULL COMMENT '状态',
  `buytimes` int(11) DEFAULT NULL COMMENT '购买次数',
  `bizRep` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121705 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------


-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `byOrder` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic
-- ----------------------------

-- ----------------------------
-- Table structure for endpoint
-- ----------------------------
DROP TABLE IF EXISTS `endpoint`;
CREATE TABLE `endpoint` (
  `partyId` int(11) NOT NULL,
  `addressId` varchar(255) DEFAULT NULL,
  `fullPinyin` varchar(255) DEFAULT NULL,
  `pointName` varchar(255) DEFAULT NULL,
  `pointShortName` varchar(255) DEFAULT NULL,
  `pointType` varchar(255) DEFAULT NULL,
  `simplePinyin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`partyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of endpoint
-- ----------------------------

-- ----------------------------
-- Table structure for fillingrecord
-- ----------------------------
DROP TABLE IF EXISTS `fillingrecord`;
CREATE TABLE `fillingrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `belgon2PartyId` int(11) DEFAULT NULL,
  `carNo` varchar(255) DEFAULT NULL,
  `cardBalance` decimal(19,2) DEFAULT NULL,
  `cardType` varchar(255) DEFAULT NULL,
  `discount` decimal(19,2) DEFAULT NULL,
  `fillTime` datetime DEFAULT NULL,
  `fillType` varchar(255) DEFAULT NULL,
  `gunNo` varchar(255) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `plateNo` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `receiptTotal` decimal(19,2) DEFAULT NULL,
  `receivable` decimal(19,2) DEFAULT NULL,
  `recordId` varchar(255) DEFAULT NULL,
  `stationName` varchar(64) DEFAULT NULL,
  `stationNo` varchar(64) DEFAULT NULL,
  `ttc` varchar(255) DEFAULT NULL,
  `volume` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fillingrecord
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `address3` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `wehchat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `des` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `perm` varchar(255) NOT NULL DEFAULT 'no',
  `url` varchar(255) DEFAULT NULL,
  `layer` varchar(255) DEFAULT NULL,
  `menuOrder` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('11', '公司管理', '100', 'corporation:list', '/corporation/list.do', '2', '0', null);
INSERT INTO `menu` VALUES ('12', '公司管理-新增', '11', 'corporation:addDo', '/corporation/addDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('13', '公司管理-修改', '11', 'corporation:updateDo', '/corporation/updateDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('14', '公司管理-删除', '11', 'corporation:del', '/corporation/del.do', '3', '0', null);
INSERT INTO `menu` VALUES ('15', '我的员工', '8', 'person:list', '/person/list.do', '2', '0', null);
INSERT INTO `menu` VALUES ('16', '我的员工-新增', '15', 'person:addDo', '/person/addDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('17', '我的员工-修改', '15', 'person:updateDo', '/person/updateDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('18', '我的员工-删除', '15', 'person:del', '/person/del.do', '3', '0', null);
INSERT INTO `menu` VALUES ('19', '我的员工-列表', '15', 'person:list', '/person/list.do', '3', '0', null);
INSERT INTO `menu` VALUES ('20', '公司管理-列表', '11', 'corporation:list', '/corporation/list.do', '3', '0', null);
INSERT INTO `menu` VALUES ('28', '业务执行', '0', 'no', '', '1', '6', 'icon06.png');
INSERT INTO `menu` VALUES ('65', '人员管理', '100', 'allPerson:list', '/allPerson/list.do', '2', '0', null);
INSERT INTO `menu` VALUES ('66', '人员管理-新增', '65', 'allPerson:addDo', '/allPerson/addDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('67', '人员管理-修改', '65', 'allPerson:updateDo', '/allPerson/updateDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('68', '人员管理-删除', '65', 'allPerson:del', '/allPerson/del.do', '3', '0', null);
INSERT INTO `menu` VALUES ('69', '人员管理-列表', '65', 'allPerson:list', '/allPerson/list.do', '3', '0', null);
INSERT INTO `menu` VALUES ('90', '角色管理', '100', 'role:list', '/role/list.do', '2', '0', null);
INSERT INTO `menu` VALUES ('91', '角色管理-新增', '90', 'role:addDo.do', '/role/addDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('92', '角色管理-修改', '90', 'role:updateDo', '/role/updateDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('93', '角色管理-删除', '90', 'role:del', '/role/deldo', '3', '0', null);
INSERT INTO `menu` VALUES ('94', '角色管理-列表', '90', 'role:list', '/role/list.do', '3', '0', null);
INSERT INTO `menu` VALUES ('95', '菜单管理', '100', 'menu:list', '/menu/list.do', '2', '0', null);
INSERT INTO `menu` VALUES ('96', '菜单管理-新增', '95', 'menu:addDo.do', '/menu/addDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('97', '菜单管理-修改', '95', 'menu:updateDo', '/menu/updateDo.do', '3', '0', null);
INSERT INTO `menu` VALUES ('98', '菜单管理-删除', '95', 'menu:del', '/menu/del.do', '3', '0', null);
INSERT INTO `menu` VALUES ('99', '菜单管理-列表', '95', 'menu:listJson', '/menu/listJson.do', '3', '0', null);
INSERT INTO `menu` VALUES ('100', '基础设施', '0', 'no', '', '1', '8', 'icon08.png');
INSERT INTO `menu` VALUES ('232', '数据字典', '100', 'dic:list', '/dic/list.do', '2', null, null);
INSERT INTO `menu` VALUES ('233', '数据字典-列表', '232', 'dic:list', '/dic/list.do', '3', null, null);
INSERT INTO `menu` VALUES ('234', '数据字典-新增', '232', 'dic:addDo', '/dic/addDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('235', '数据字典-修改', '232', 'dic:updateDo', '/dic/updateDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('236', '数据字典-删除', '232', 'dic:del', '/dic/del.do', '3', null, null);
INSERT INTO `menu` VALUES ('237', '加气记录', '28', 'fillingRecord:list', '/fillingRecord/list.do', '2', null, null);
INSERT INTO `menu` VALUES ('238', '加气记录-列表', '237', 'fillingRecord:list', '/fillingRecord/list.do', '3', null, null);
INSERT INTO `menu` VALUES ('239', '加气记录-新增', '237', 'fillingRecord:addDo', '/fillingRecord/addDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('240', '加气记录-修改', '237', 'fillingRecord:updateDo', '/fillingRecord/updateDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('241', '加气记录-删除', '237', 'fillingRecord:del', '/fillingRecord/del.do', '3', null, null);
INSERT INTO `menu` VALUES ('242', '价格记录', '28', 'priceList:list', '/priceList/list.do', '2', null, null);
INSERT INTO `menu` VALUES ('243', '价格记录-列表', '242', 'priceList:list', '/priceList/list.do', '3', null, null);
INSERT INTO `menu` VALUES ('244', '价格记录-新增', '242', 'priceList:addDo', '/priceList/addDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('245', '价格记录-修改', '242', 'priceList:updateDo', '/priceList/updateDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('246', '价格记录-删除', '242', 'priceList:del', '/priceList/del.do', '3', null, null);
INSERT INTO `menu` VALUES ('247', '充值记录', '28', 'rechargeRecord:list', '/rechargeRecord/list.do', '2', null, null);
INSERT INTO `menu` VALUES ('248', '充值记录-列表', '247', 'rechargeRecord:list', '/rechargeRecord/list.do', '3', null, null);
INSERT INTO `menu` VALUES ('249', '充值记录-新增', '247', 'rechargeRecord:addDo', '/rechargeRecord/addDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('250', '充值记录-修改', '247', 'rechargeRecord:updateDo', '/rechargeRecord/updateDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('251', '充值记录-删除', '247', 'rechargeRecord:del', '/rechargeRecord/del.do', '3', null, null);
INSERT INTO `menu` VALUES ('252', '上下班记录', '28', 'shiftRecord:list', '/shiftRecord/list.do', '2', null, null);
INSERT INTO `menu` VALUES ('253', '上下班记录-列表', '252', 'shiftRecord:list', '/shiftRecord/list.do', '3', null, null);
INSERT INTO `menu` VALUES ('254', '上下班记录-新增', '252', 'shiftRecord:addDo', '/shiftRecord/addDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('255', '上下班记录-修改', '252', 'shiftRecord:updateDo', '/shiftRecord/updateDo.do', '3', null, null);
INSERT INTO `menu` VALUES ('256', '上下班记录-删除', '252', 'shiftRecord:del', '/shiftRecord/del.do', '3', null, null);

-- ----------------------------
-- Table structure for party
-- ----------------------------
DROP TABLE IF EXISTS `party`;
CREATE TABLE `party` (
  `partyId` int(11) NOT NULL AUTO_INCREMENT,
  `createdByUser` int(11) DEFAULT NULL,
  `createdDate` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `lastModifiedByUserLogin` int(11) DEFAULT NULL,
  `lastModifiedDate` varchar(255) DEFAULT NULL,
  `partyTypeId` int(11) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  PRIMARY KEY (`partyId`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of party
-- ----------------------------
INSERT INTO `party` VALUES ('4', '1', '2015-01-07 20:55:14', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('10', '4', '2015-01-11 20:45:00', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('17', '4', '2015-01-15 14:05:33', 'SourcePoint', null, null, '3', '1');
INSERT INTO `party` VALUES ('18', '4', '2015-01-15 14:24:34', 'EndPoint', null, null, '4', '1');
INSERT INTO `party` VALUES ('24', '4', '2015-01-27 22:06:17', 'EndPoint', null, null, '4', '1');
INSERT INTO `party` VALUES ('25', '4', '2015-02-03 13:52:05', 'Truck', null, null, '3', '1');
INSERT INTO `party` VALUES ('26', '4', '2015-02-03 13:56:34', 'Truck', null, null, '3', '1');
INSERT INTO `party` VALUES ('28', '4', '2017-12-30 09:32:55', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('29', '4', '2018-04-21 15:06:14', 'EndPoint', null, null, '4', '1');
INSERT INTO `party` VALUES ('30', '4', '2018-05-24 22:50:56', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('31', '4', '2018-06-12 23:12:54', 'EndPoint', null, null, '4', '1');
INSERT INTO `party` VALUES ('32', '4', '2018-06-12 23:16:18', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('33', '4', '2018-06-21 09:28:32', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('34', '4', '2018-07-06 14:53:57', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('35', '4', '2018-07-06 15:06:13', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('36', '4', '2018-07-06 15:07:20', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('37', '4', '2018-07-06 15:08:29', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('38', '4', '2018-07-06 15:11:15', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('39', '12', '2018-07-09 11:00:52', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('40', '12', '2018-07-09 11:01:28', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('41', '12', '2018-07-16 10:24:39', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('42', '12', '2018-07-16 10:25:21', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('43', '4', '2018-09-15 15:03:47', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('44', '4', '2018-09-15 15:05:08', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('45', '4', '2018-10-04 11:00:08', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('46', '4', '2018-10-22 09:47:56', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('47', '4', '2018-10-22 09:50:20', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('48', '4', '2018-10-22 14:14:41', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('49', '4', '2018-10-30 13:44:46', 'SourcePoint', null, null, '3', '1');
INSERT INTO `party` VALUES ('50', '4', '2018-10-30 13:45:57', 'SourcePoint', null, null, '3', '1');
INSERT INTO `party` VALUES ('51', '4', '2018-10-30 13:46:55', 'Corporation', null, null, '2', '1');
INSERT INTO `party` VALUES ('52', '4', '2018-10-30 13:48:06', 'Corporation', null, null, '2', '1');
INSERT INTO `party` VALUES ('56', '12', '2018-11-22 17:49:10', 'EndPoint', null, null, '4', '1');
INSERT INTO `party` VALUES ('57', '12', '2018-11-22 17:51:23', 'EndPoint', null, null, '4', '1');
INSERT INTO `party` VALUES ('58', '12', '2018-12-29 10:17:36', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('59', '4', '2019-01-01 11:48:23', 'EndPoint', null, null, '4', '1');
INSERT INTO `party` VALUES ('60', '12', '2019-01-29 17:00:18', 'Person', null, null, '1', '1');
INSERT INTO `party` VALUES ('61', '4', '2019-03-18 16:16:53', 'Person', null, null, '1', '1');

-- ----------------------------
-- Table structure for partyattribute
-- ----------------------------
DROP TABLE IF EXISTS `partyattribute`;
CREATE TABLE `partyattribute` (
  `partyAttributeId` int(11) NOT NULL AUTO_INCREMENT,
  `attrName` varchar(255) DEFAULT NULL,
  `attrValue` varchar(255) DEFAULT NULL,
  `partyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`partyAttributeId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of partyattribute
-- ----------------------------

-- ----------------------------
-- Table structure for partytype
-- ----------------------------
DROP TABLE IF EXISTS `partytype`;
CREATE TABLE `partytype` (
  `partyTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `hasTable` varchar(255) DEFAULT NULL,
  `parentTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`partyTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of partytype
-- ----------------------------
INSERT INTO `partytype` VALUES ('1', 'Person', 'Y', null);
INSERT INTO `partytype` VALUES ('2', 'Corporation', 'Y', null);

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `partyId` int(11) NOT NULL AUTO_INCREMENT,
  `QQ` varchar(255) DEFAULT NULL,
  `active` varchar(255) DEFAULT NULL,
  `audit` varchar(255) DEFAULT NULL,
  `birthDate` varchar(255) DEFAULT NULL,
  `cardId` bigint(20) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `corpPartyId` int(11) DEFAULT NULL,
  `deceasedDate` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employmentStatusEnumId` int(11) DEFAULT NULL,
  `existingCustomer` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `firstNameLocal` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `isOpen` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `lastNameLocal` varchar(255) DEFAULT NULL,
  `logDate` varchar(255) DEFAULT NULL,
  `maritalStatus` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `middleNameLocal` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `monthsWithEmployer` int(11) DEFAULT NULL,
  `mothersMaidenName` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `otherLocal` varchar(255) DEFAULT NULL,
  `passportExpireDate` varchar(255) DEFAULT NULL,
  `passportNumber` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `personalTitle` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `residenceStatusEnumId` int(11) DEFAULT NULL,
  `salutation` varchar(255) DEFAULT NULL,
  `socialSecurityNumber` varchar(32) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  `totalYearsWorkExperience` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `yearsWithEmployer` int(11) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL COMMENT '微信openid',
  PRIMARY KEY (`partyId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('4', '2122', null, null, '2015-01-07', null, null, '1', null, '514580856@qq.com', null, null, '管理员', null, '女', null, '否', null, null, '2019-05-24 22:47:08', null, null, null, '3122', null, null, null, null, null, null, null, 'JdVa0oOqQAr0ZMdtcTwHrQ==', null, null, null, null, null, null, null, 'admin', null, null, 'oW2s4005yfhPKHBWd8W0lcVKyAhI');

-- ----------------------------
-- Table structure for personrole
-- ----------------------------
DROP TABLE IF EXISTS `personrole`;
CREATE TABLE `personrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personrole
-- ----------------------------
INSERT INTO `personrole` VALUES ('1', '1', '2');
INSERT INTO `personrole` VALUES ('2', '2', '1');
INSERT INTO `personrole` VALUES ('11', '8', '7');
INSERT INTO `personrole` VALUES ('12', '4', '2');
INSERT INTO `personrole` VALUES ('23', '5', '3');
INSERT INTO `personrole` VALUES ('24', '5', '1');
INSERT INTO `personrole` VALUES ('26', '9', '8');
INSERT INTO `personrole` VALUES ('28', '11', '9');
INSERT INTO `personrole` VALUES ('29', '10', '8');
INSERT INTO `personrole` VALUES ('38', '14', '8');
INSERT INTO `personrole` VALUES ('39', '14', '4');
INSERT INTO `personrole` VALUES ('48', '13', '8');
INSERT INTO `personrole` VALUES ('49', '13', '4');
INSERT INTO `personrole` VALUES ('50', '13', '3');
INSERT INTO `personrole` VALUES ('65', '22', '8');
INSERT INTO `personrole` VALUES ('66', '22', '3');
INSERT INTO `personrole` VALUES ('67', '22', '1');
INSERT INTO `personrole` VALUES ('68', '21', '8');
INSERT INTO `personrole` VALUES ('69', '21', '3');
INSERT INTO `personrole` VALUES ('70', '21', '1');
INSERT INTO `personrole` VALUES ('71', '7', '8');
INSERT INTO `personrole` VALUES ('72', '7', '3');
INSERT INTO `personrole` VALUES ('73', '7', '1');
INSERT INTO `personrole` VALUES ('74', '23', '8');
INSERT INTO `personrole` VALUES ('75', '23', '3');
INSERT INTO `personrole` VALUES ('76', '23', '1');
INSERT INTO `personrole` VALUES ('107', '25', '10');
INSERT INTO `personrole` VALUES ('108', '24', '10');
INSERT INTO `personrole` VALUES ('118', '12', '10');
INSERT INTO `personrole` VALUES ('119', '12', '8');
INSERT INTO `personrole` VALUES ('120', '12', '6');
INSERT INTO `personrole` VALUES ('121', '12', '5');
INSERT INTO `personrole` VALUES ('122', '12', '3');
INSERT INTO `personrole` VALUES ('123', '12', '2');
INSERT INTO `personrole` VALUES ('124', '12', '1');
INSERT INTO `personrole` VALUES ('125', '18', '8');
INSERT INTO `personrole` VALUES ('126', '18', '3');
INSERT INTO `personrole` VALUES ('127', '17', '8');
INSERT INTO `personrole` VALUES ('128', '17', '3');
INSERT INTO `personrole` VALUES ('129', '6', '8');
INSERT INTO `personrole` VALUES ('130', '6', '3');
INSERT INTO `personrole` VALUES ('131', '19', '8');
INSERT INTO `personrole` VALUES ('132', '19', '3');
INSERT INTO `personrole` VALUES ('133', '20', '8');
INSERT INTO `personrole` VALUES ('134', '20', '3');
INSERT INTO `personrole` VALUES ('135', '27', '10');
INSERT INTO `personrole` VALUES ('136', '27', '8');
INSERT INTO `personrole` VALUES ('137', '27', '5');
INSERT INTO `personrole` VALUES ('138', '27', '1');
INSERT INTO `personrole` VALUES ('144', '26', '11');
INSERT INTO `personrole` VALUES ('145', '26', '8');
INSERT INTO `personrole` VALUES ('146', '26', '6');
INSERT INTO `personrole` VALUES ('147', '26', '5');
INSERT INTO `personrole` VALUES ('148', '26', '3');
INSERT INTO `personrole` VALUES ('149', '26', '1');
INSERT INTO `personrole` VALUES ('157', '28', '11');
INSERT INTO `personrole` VALUES ('158', '28', '1');
INSERT INTO `personrole` VALUES ('159', '15', '8');
INSERT INTO `personrole` VALUES ('160', '15', '5');
INSERT INTO `personrole` VALUES ('161', '15', '3');
INSERT INTO `personrole` VALUES ('167', '16', '11');
INSERT INTO `personrole` VALUES ('168', '16', '5');
INSERT INTO `personrole` VALUES ('171', '29', '12');

-- ----------------------------
-- Table structure for pricelist
-- ----------------------------
DROP TABLE IF EXISTS `pricelist`;
CREATE TABLE `pricelist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `belgon2PartyId` int(11) DEFAULT NULL,
  `gunNo` varchar(255) DEFAULT NULL,
  `newPrice` decimal(18,2) DEFAULT NULL,
  `oldPrice` decimal(18,2) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `stationName` varchar(64) DEFAULT NULL,
  `stationNo` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pricelist
-- ----------------------------

-- ----------------------------
-- Table structure for quotation
-- ----------------------------
DROP TABLE IF EXISTS `quotation`;
CREATE TABLE `quotation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cusCorpPartyId` int(11) DEFAULT NULL,
  `effectString` varchar(255) DEFAULT NULL,
  `expiredString` varchar(255) DEFAULT NULL,
  `fobPrice` int(11) DEFAULT NULL,
  `offerCorpPartyId` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quotation
-- ----------------------------

-- ----------------------------
-- Table structure for quotationitem
-- ----------------------------
DROP TABLE IF EXISTS `quotationitem`;
CREATE TABLE `quotationitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cifPrice` int(11) DEFAULT NULL,
  `endPointPartyId` int(11) DEFAULT NULL,
  `offerCorpPartyId` int(11) DEFAULT NULL,
  `quotationId` int(11) NOT NULL,
  `sourcePartyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quotationitem
-- ----------------------------

-- ----------------------------
-- Table structure for rechargerecord
-- ----------------------------
DROP TABLE IF EXISTS `rechargerecord`;
CREATE TABLE `rechargerecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` decimal(19,2) DEFAULT NULL,
  `belgon2PartyId` int(11) DEFAULT NULL,
  `bizAmount` decimal(19,2) DEFAULT NULL,
  `bizType` varchar(255) DEFAULT NULL,
  `carNo` varchar(255) DEFAULT NULL,
  `cardType` varchar(255) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `rebate` decimal(19,2) DEFAULT NULL,
  `recordId` varchar(255) DEFAULT NULL,
  `stationName` varchar(64) DEFAULT NULL,
  `stationNo` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rechargerecord
-- ----------------------------


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '普通');
INSERT INTO `role` VALUES ('2', '管理员');

-- ----------------------------
-- Table structure for rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `rolemenu`;
CREATE TABLE `rolemenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7705 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolemenu
-- ----------------------------
INSERT INTO `rolemenu` VALUES ('7637', '28', '1');
INSERT INTO `rolemenu` VALUES ('7638', '237', '1');
INSERT INTO `rolemenu` VALUES ('7639', '238', '1');
INSERT INTO `rolemenu` VALUES ('7640', '239', '1');
INSERT INTO `rolemenu` VALUES ('7641', '240', '1');
INSERT INTO `rolemenu` VALUES ('7642', '241', '1');
INSERT INTO `rolemenu` VALUES ('7643', '242', '1');
INSERT INTO `rolemenu` VALUES ('7644', '243', '1');
INSERT INTO `rolemenu` VALUES ('7645', '244', '1');
INSERT INTO `rolemenu` VALUES ('7646', '245', '1');
INSERT INTO `rolemenu` VALUES ('7647', '246', '1');
INSERT INTO `rolemenu` VALUES ('7648', '247', '1');
INSERT INTO `rolemenu` VALUES ('7649', '248', '1');
INSERT INTO `rolemenu` VALUES ('7650', '249', '1');
INSERT INTO `rolemenu` VALUES ('7651', '250', '1');
INSERT INTO `rolemenu` VALUES ('7652', '251', '1');
INSERT INTO `rolemenu` VALUES ('7653', '252', '1');
INSERT INTO `rolemenu` VALUES ('7654', '253', '1');
INSERT INTO `rolemenu` VALUES ('7655', '254', '1');
INSERT INTO `rolemenu` VALUES ('7656', '255', '1');
INSERT INTO `rolemenu` VALUES ('7657', '256', '1');
INSERT INTO `rolemenu` VALUES ('7658', '28', '2');
INSERT INTO `rolemenu` VALUES ('7659', '237', '2');
INSERT INTO `rolemenu` VALUES ('7660', '238', '2');
INSERT INTO `rolemenu` VALUES ('7661', '239', '2');
INSERT INTO `rolemenu` VALUES ('7662', '240', '2');
INSERT INTO `rolemenu` VALUES ('7663', '241', '2');
INSERT INTO `rolemenu` VALUES ('7664', '242', '2');
INSERT INTO `rolemenu` VALUES ('7665', '243', '2');
INSERT INTO `rolemenu` VALUES ('7666', '244', '2');
INSERT INTO `rolemenu` VALUES ('7667', '245', '2');
INSERT INTO `rolemenu` VALUES ('7668', '246', '2');
INSERT INTO `rolemenu` VALUES ('7669', '247', '2');
INSERT INTO `rolemenu` VALUES ('7670', '248', '2');
INSERT INTO `rolemenu` VALUES ('7671', '249', '2');
INSERT INTO `rolemenu` VALUES ('7672', '250', '2');
INSERT INTO `rolemenu` VALUES ('7673', '251', '2');
INSERT INTO `rolemenu` VALUES ('7674', '252', '2');
INSERT INTO `rolemenu` VALUES ('7675', '253', '2');
INSERT INTO `rolemenu` VALUES ('7676', '254', '2');
INSERT INTO `rolemenu` VALUES ('7677', '255', '2');
INSERT INTO `rolemenu` VALUES ('7678', '256', '2');
INSERT INTO `rolemenu` VALUES ('7679', '100', '2');
INSERT INTO `rolemenu` VALUES ('7680', '11', '2');
INSERT INTO `rolemenu` VALUES ('7681', '12', '2');
INSERT INTO `rolemenu` VALUES ('7682', '13', '2');
INSERT INTO `rolemenu` VALUES ('7683', '14', '2');
INSERT INTO `rolemenu` VALUES ('7684', '20', '2');
INSERT INTO `rolemenu` VALUES ('7685', '65', '2');
INSERT INTO `rolemenu` VALUES ('7686', '66', '2');
INSERT INTO `rolemenu` VALUES ('7687', '67', '2');
INSERT INTO `rolemenu` VALUES ('7688', '68', '2');
INSERT INTO `rolemenu` VALUES ('7689', '69', '2');
INSERT INTO `rolemenu` VALUES ('7690', '90', '2');
INSERT INTO `rolemenu` VALUES ('7691', '91', '2');
INSERT INTO `rolemenu` VALUES ('7692', '92', '2');
INSERT INTO `rolemenu` VALUES ('7693', '93', '2');
INSERT INTO `rolemenu` VALUES ('7694', '94', '2');
INSERT INTO `rolemenu` VALUES ('7695', '95', '2');
INSERT INTO `rolemenu` VALUES ('7696', '96', '2');
INSERT INTO `rolemenu` VALUES ('7697', '97', '2');
INSERT INTO `rolemenu` VALUES ('7698', '98', '2');
INSERT INTO `rolemenu` VALUES ('7699', '99', '2');
INSERT INTO `rolemenu` VALUES ('7700', '232', '2');
INSERT INTO `rolemenu` VALUES ('7701', '233', '2');
INSERT INTO `rolemenu` VALUES ('7702', '234', '2');
INSERT INTO `rolemenu` VALUES ('7703', '235', '2');
INSERT INTO `rolemenu` VALUES ('7704', '236', '2');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addressId1` int(11) DEFAULT NULL,
  `addressId2` int(11) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL,
  `routeMarking` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route
-- ----------------------------

-- ----------------------------
-- Table structure for shiftrecord
-- ----------------------------
DROP TABLE IF EXISTS `shiftrecord`;
CREATE TABLE `shiftrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `belgon2PartyId` int(11) DEFAULT NULL,
  `confirmTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `recordId` varchar(255) DEFAULT NULL,
  `shiftNo` varchar(50) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `stationName` varchar(64) DEFAULT NULL,
  `stationNo` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiftrecord
-- ----------------------------

-- ----------------------------
-- Table structure for shippingorder
-- ----------------------------
DROP TABLE IF EXISTS `shippingorder`;
CREATE TABLE `shippingorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `driverName` varchar(255) DEFAULT NULL,
  `driverPartyId` int(11) DEFAULT NULL,
  `driverTel` varchar(255) DEFAULT NULL,
  `endPointName` varchar(255) DEFAULT NULL,
  `endPointPartyId` int(11) DEFAULT NULL,
  `loadingGW` decimal(19,2) DEFAULT NULL,
  `loadingNW` decimal(19,2) DEFAULT NULL,
  `loadingTW` decimal(19,2) DEFAULT NULL,
  `loadingTime` varchar(255) DEFAULT NULL,
  `planLoadingString` varchar(255) DEFAULT NULL,
  `requiredString` varchar(255) DEFAULT NULL,
  `settleWeight` decimal(19,2) DEFAULT NULL,
  `sourceName` varchar(255) DEFAULT NULL,
  `sourcePartyId` int(11) DEFAULT NULL,
  `tankNo` varchar(255) DEFAULT NULL,
  `transportCorpPartyId` int(11) DEFAULT NULL,
  `transportOwner` int(11) DEFAULT NULL,
  `truckNo` varchar(255) DEFAULT NULL,
  `unLoadOnRoad` varchar(255) DEFAULT NULL,
  `unLoadingGW` decimal(19,2) DEFAULT NULL,
  `unLoadingNW` decimal(19,2) DEFAULT NULL,
  `unLoadingTW` decimal(19,2) DEFAULT NULL,
  `unloadingTime` varchar(255) DEFAULT NULL,
  `arriveLoadTime` varchar(255) DEFAULT NULL,
  `arriveUnLoadTime` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shippingorder
-- ----------------------------

-- ----------------------------
-- Table structure for shippingorderunload
-- ----------------------------
DROP TABLE IF EXISTS `shippingorderunload`;
CREATE TABLE `shippingorderunload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `arrivedTime` varchar(255) DEFAULT NULL,
  `endUnload` varchar(255) DEFAULT NULL,
  `leavingTime` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `shippingOrderId` int(11) NOT NULL,
  `startUnload` varchar(255) DEFAULT NULL,
  `unLoadingGW` decimal(19,2) DEFAULT NULL,
  `unLoadingNW` decimal(19,2) DEFAULT NULL,
  `unLoadingTW` decimal(19,2) DEFAULT NULL,
  `endPointPartyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shippingorderunload
-- ----------------------------

-- ----------------------------
-- Table structure for sourcepoint
-- ----------------------------
DROP TABLE IF EXISTS `sourcepoint`;
CREATE TABLE `sourcepoint` (
  `partyId` int(11) NOT NULL,
  `addressId` varchar(255) DEFAULT NULL,
  `annualAbility` decimal(19,2) DEFAULT NULL,
  `calorificValue` decimal(19,2) DEFAULT NULL,
  `dailyAbility` decimal(19,2) DEFAULT NULL,
  `fullPinyin` varchar(255) DEFAULT NULL,
  `gasificationRate` decimal(19,2) DEFAULT NULL,
  `simplePinyin` varchar(255) DEFAULT NULL,
  `sourceName` varchar(255) DEFAULT NULL,
  `sourceShortName` varchar(255) DEFAULT NULL,
  `sourceType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`partyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sourcepoint
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `belong2CorpPartyId` int(11) DEFAULT NULL,
  `bizManager` int(11) DEFAULT NULL,
  `bizStartDate` varchar(255) DEFAULT NULL,
  `corpPartyId` int(11) DEFAULT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createParty` int(11) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `serialNo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------

-- ----------------------------
-- Table structure for transporter
-- ----------------------------
DROP TABLE IF EXISTS `transporter`;
CREATE TABLE `transporter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `belgon2PartyId` int(11) DEFAULT NULL,
  `bizManager` int(11) DEFAULT NULL,
  `bizStartDate` varchar(255) DEFAULT NULL,
  `corpPartyId` int(11) DEFAULT NULL,
  `createDate` varchar(255) DEFAULT NULL,
  `createParty` int(11) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `serialNo` varchar(255) DEFAULT NULL,
  `truckSize` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transporter
-- ----------------------------

-- ----------------------------
-- Table structure for truck
-- ----------------------------
DROP TABLE IF EXISTS `truck`;
CREATE TABLE `truck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `corpPartyId` int(11) DEFAULT NULL,
  `lastTankerNo` varchar(255) DEFAULT NULL,
  `nextCheckDate` varchar(255) DEFAULT NULL,
  `plateNo` varchar(255) DEFAULT NULL,
  `tonnage` decimal(19,2) DEFAULT NULL,
  `truckType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of truck
-- ----------------------------
