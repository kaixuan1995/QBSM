-- MySQL dump 10.13  Distrib 5.5.45, for Win64 (x86)
--
-- Host: localhost    Database: qbsm
-- ------------------------------------------------------
-- Server version	5.5.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_alarm`
--

DROP TABLE IF EXISTS `t_alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_alarm` (
  `alarm_isdel` int(11) NOT NULL DEFAULT '0',
  `alarm_period` varchar(50) NOT NULL,
  `alarm_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`alarm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_alarm`
--

LOCK TABLES `t_alarm` WRITE;
/*!40000 ALTER TABLE `t_alarm` DISABLE KEYS */;
INSERT INTO `t_alarm` VALUES (0,'7',1);
/*!40000 ALTER TABLE `t_alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_apply`
--

DROP TABLE IF EXISTS `t_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_apply` (
  `apply_id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_time` varchar(15) NOT NULL,
  `user_id_fk` int(11) NOT NULL,
  `apply_urgent` varchar(3) NOT NULL,
  `apply_state` varchar(11) NOT NULL,
  `apply_type` char(5) NOT NULL,
  `storehouse_id_fk` int(11) NOT NULL,
  `apply_remark` varchar(200) DEFAULT NULL,
  `apply_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`apply_id`),
  KEY `user_id_fk` (`user_id_fk`),
  KEY `storehouse_id_fk` (`storehouse_id_fk`),
  CONSTRAINT `t_apply_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `t_apply_ibfk_2` FOREIGN KEY (`storehouse_id_fk`) REFERENCES `t_storehouse` (`storehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_apply`
--

LOCK TABLES `t_apply` WRITE;
/*!40000 ALTER TABLE `t_apply` DISABLE KEYS */;
INSERT INTO `t_apply` VALUES (1,'2016-03-30',1,'是','已领用','1',1,NULL,0),(2,'2016-03-30',2,'是','待审核','3',1,NULL,0),(3,'2016-5-10',2,'是','待审核','3',1,NULL,0),(4,'2016-05-10',2,'是','采购中','4',1,NULL,0),(5,'2016-06-10',2,'是','采购中','1',1,NULL,0),(6,'2016-03-30',2,'是','采购中','1',1,NULL,0),(7,'2016-03-30',1,'是','已领用','2',1,NULL,0),(8,'2016-5-10',1,'是','已领用','3',1,NULL,0),(9,'2016-05-10',1,'是','待审核','4',1,NULL,0),(10,'2016-05-10',1,'是','已领用','4',1,NULL,0);
/*!40000 ALTER TABLE `t_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_apply_goods`
--

DROP TABLE IF EXISTS `t_apply_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_apply_goods` (
  `apply_goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `inventory_id_fk` int(11) NOT NULL,
  `goods_id_fk` int(11) NOT NULL,
  `apply_id_fk` int(11) NOT NULL,
  `apply_goods_count` int(11) NOT NULL,
  `apply_goods_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`apply_goods_id`),
  KEY `inventory_id_fk` (`inventory_id_fk`),
  KEY `goods_id_fk` (`goods_id_fk`),
  KEY `t_apply_goods_ibfk_3` (`apply_id_fk`),
  CONSTRAINT `t_apply_goods_ibfk_1` FOREIGN KEY (`inventory_id_fk`) REFERENCES `t_inventory` (`inventory_id`),
  CONSTRAINT `t_apply_goods_ibfk_2` FOREIGN KEY (`goods_id_fk`) REFERENCES `t_goods` (`goods_id`),
  CONSTRAINT `t_apply_goods_ibfk_3` FOREIGN KEY (`apply_id_fk`) REFERENCES `t_apply` (`apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_apply_goods`
--

LOCK TABLES `t_apply_goods` WRITE;
/*!40000 ALTER TABLE `t_apply_goods` DISABLE KEYS */;
INSERT INTO `t_apply_goods` VALUES (1,1,1,1,19,0),(2,1,2,1,20,0),(3,1,3,1,21,0),(4,1,4,1,22,0),(5,1,5,1,23,0),(6,2,1,2,30,0),(7,2,2,2,30,0),(8,2,3,2,30,0),(9,2,4,2,30,0),(10,2,5,2,30,0);
/*!40000 ALTER TABLE `t_apply_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_apply_picture`
--

DROP TABLE IF EXISTS `t_apply_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_apply_picture` (
  `apply_picture_id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_id` int(11) NOT NULL,
  `apply_picture_url` varchar(50) NOT NULL,
  PRIMARY KEY (`apply_picture_id`),
  KEY `apply_id` (`apply_id`),
  CONSTRAINT `t_apply_picture_ibfk_1` FOREIGN KEY (`apply_id`) REFERENCES `t_apply` (`apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_apply_picture`
--

LOCK TABLES `t_apply_picture` WRITE;
/*!40000 ALTER TABLE `t_apply_picture` DISABLE KEYS */;
INSERT INTO `t_apply_picture` VALUES (1,1,'1');
/*!40000 ALTER TABLE `t_apply_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_applygoods`
--

DROP TABLE IF EXISTS `t_applygoods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_applygoods` (
  `applygoods_id` int(11) NOT NULL AUTO_INCREMENT,
  `applygoods_time` varchar(15) NOT NULL,
  `applygoods_people` int(11) NOT NULL,
  `applygoods_name` varchar(11) NOT NULL,
  `applygoods_brand` varchar(50) NOT NULL,
  `applygoods_unit` varchar(10) NOT NULL,
  `applygoods_type` varchar(5) NOT NULL,
  `applygoods_cas` int(11) NOT NULL,
  `applygoods_state` varchar(10) NOT NULL,
  `applygoods_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`applygoods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_applygoods`
--

LOCK TABLES `t_applygoods` WRITE;
/*!40000 ALTER TABLE `t_applygoods` DISABLE KEYS */;
INSERT INTO `t_applygoods` VALUES (1,'1',1,'1','1','1','1',1,'1',0);
/*!40000 ALTER TABLE `t_applygoods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_applynewgoods`
--

DROP TABLE IF EXISTS `t_applynewgoods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_applynewgoods` (
  `applygoods_id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_time` varchar(15) DEFAULT NULL,
  `user_id_fk` int(11) NOT NULL,
  `user_lianxiren` varchar(11) DEFAULT NULL,
  `goods_name` varchar(11) DEFAULT NULL,
  `goods_brand` varchar(50) DEFAULT NULL,
  `goods_unit` varchar(10) DEFAULT NULL,
  `goods_standard` varchar(10) DEFAULT NULL,
  `type_id_fk` int(11) DEFAULT NULL,
  `goods_cas` varchar(11) DEFAULT NULL,
  `apply_state` varchar(10) DEFAULT '待审核',
  `apply_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`applygoods_id`),
  KEY `goodstype_id_fk` (`type_id_fk`),
  KEY `t_applynewgoods_ibfk_2` (`user_id_fk`),
  CONSTRAINT `t_applynewgoods_ibfk_1` FOREIGN KEY (`type_id_fk`) REFERENCES `t_goodstype` (`type_id`),
  CONSTRAINT `t_applynewgoods_ibfk_2` FOREIGN KEY (`user_id_fk`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_applynewgoods`
--

LOCK TABLES `t_applynewgoods` WRITE;
/*!40000 ALTER TABLE `t_applynewgoods` DISABLE KEYS */;
INSERT INTO `t_applynewgoods` VALUES (12,'2016-04-09',0,'亦鸣','一坨屎','中华','吨','色谱纯，4L/瓶',1,'46','审核通过',0),(13,'2016-04-09',0,'亦鸣','一坨屎','中华','吨','色谱纯，4L/瓶',2,'123','审核通过',0),(14,'2016-04-09',0,'亦鸣','一坨屎','中华','吨','色谱纯，4L/瓶',2,'123','审核通过',0),(15,'2016-04-09',0,'亦鸣','一坨屎','中华','吨','色谱纯，4L/瓶',1,'123','审核通过',0),(17,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','4llll',3,'123','审核通过',0),(18,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核通过',0),(19,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核通过',0),(20,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核通过',0),(21,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核失败',0),(22,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核失败',0),(23,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核失败',0),(24,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核失败',0),(25,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核通过',0),(26,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核通过',0),(27,'2016-04-10',0,'肖亦鸣','傻逼','中华','吨','色谱纯，4L/瓶',1,'123','审核通过',0);
/*!40000 ALTER TABLE `t_applynewgoods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_barcode`
--

DROP TABLE IF EXISTS `t_barcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_barcode` (
  `barcode_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id_fk` int(11) NOT NULL,
  `barcode_validity` varchar(15) NOT NULL,
  `user_id_fk` int(11) NOT NULL,
  `barcode_price` double NOT NULL,
  `barcode_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`barcode_id`),
  KEY `goods_id_fk` (`goods_id_fk`),
  KEY `user_id_fk` (`user_id_fk`),
  CONSTRAINT `t_barcode_ibfk_1` FOREIGN KEY (`goods_id_fk`) REFERENCES `t_goods` (`goods_id`),
  CONSTRAINT `t_barcode_ibfk_2` FOREIGN KEY (`user_id_fk`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2016033011 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_barcode`
--

LOCK TABLES `t_barcode` WRITE;
/*!40000 ALTER TABLE `t_barcode` DISABLE KEYS */;
INSERT INTO `t_barcode` VALUES (2016033001,1,'2016-09-09',1,200,0),(2016033002,2,'2016-4-7',2,300,0),(2016033003,3,'2016-4-6',1,500,0),(2016033004,4,'2016-09-09',1,200,0),(2016033005,5,'2016-4-7',1,300,0),(2016033006,6,'2016-4-6',1,500,0),(2016033007,7,'2016-09-09',1,200,0),(2016033008,8,'2016-09-09',1,200,0),(2016033009,9,'2016-09-09',1,200,0),(2016033010,10,'2016-09-09',1,200,0);
/*!40000 ALTER TABLE `t_barcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goods`
--

DROP TABLE IF EXISTS `t_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) DEFAULT NULL,
  `goods_brand` varchar(10) DEFAULT NULL,
  `goods_unit` varchar(4) DEFAULT NULL,
  `goods_standard` varchar(10) DEFAULT NULL,
  `type_id_fk` int(11) NOT NULL,
  `goods_cas` varchar(20) DEFAULT NULL,
  `goods_min` int(11) DEFAULT NULL,
  `goods_max` int(11) DEFAULT NULL,
  `goods_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`goods_id`),
  KEY `type_id_fk` (`type_id_fk`),
  CONSTRAINT `t_goods_ibfk_1` FOREIGN KEY (`type_id_fk`) REFERENCES `t_goodstype` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goods`
--

LOCK TABLES `t_goods` WRITE;
/*!40000 ALTER TABLE `t_goods` DISABLE KEYS */;
INSERT INTO `t_goods` VALUES (1,'乙腈','Merck','瓶','色谱纯，4L/瓶',1,'zb_110',555,20000,0),(2,'乙腈','CNW','瓶','色谱纯，4L/瓶',2,'zb_119',8,200,0),(3,'甲醇','Merck','瓶','色谱纯，4L/瓶',3,'zb_120',8,200,0),(4,'甲醇','CNW','瓶','色谱纯，4L/瓶',4,'',8,200,0),(5,'乙酸乙酯','CNW','瓶','色谱纯，4L/瓶',1,'',4,100,0),(6,'环己烷','CNW','瓶','色谱纯，4L/瓶',1,'',4,100,0),(7,'正己烷','CNW','瓶','色谱纯，4L/瓶',1,'',4,100,0),(8,'丙酮','fulltime','瓶','色谱纯，4L/瓶',1,'',4,100,0),(9,'二氯甲烷','CNW','瓶','色谱纯，4L/瓶',1,'',4,100,0),(10,'异丙醇','Honeywell','瓶','色谱纯，4L/瓶',1,'',4,100,0),(11,'叔丁基甲醚','CNW','瓶','色谱纯，4L/瓶',1,'',4,100,0),(21,'氯化钠','西陇','瓶','分析纯，500g/瓶',1,'',4,100,0),(22,'无水硫酸钠','西陇','瓶','分析纯，500g/瓶',1,'',4,100,0),(23,'无水硫酸镁','国药','瓶','分析纯，500g/瓶',1,'',4,100,0),(26,'傻逼','中华',NULL,'色谱纯，4L/瓶',1,'123',NULL,NULL,0),(27,'异辛烷','CNW','瓶','色谱纯，4L/瓶',1,'',4,100,0),(30,'七氟丁醇',' CNW','瓶',' 100g/瓶',1,'',4,100,0),(36,'枪头','艾本德','包','5ml，500个/包',2,'',4,100,0),(37,'1.5mL离心管','上海生工','包','1000个/包，',2,'140925AB',4,100,0),(39,'进样小瓶','Agilent','盒','100个/盒',2,'货号：5182-0714',4,100,0),(40,'流动相滤膜','津腾','盒','100片/盒',2,'',4,100,0),(43,'封口膜','Parafilm','盒','4IN*125FT',2,'',4,100,0),(45,'顶空进样瓶垫片','CNW','','100片/包',2,'',4,100,0),(53,'八氯二丙醚','Dr.E ','瓶','0.25g/瓶',3,'CAS：127-90-2',4,100,0),(54,'氯氰菊酯','Dr.E ','瓶','0.1g/瓶',3,'CAS：52315-07-8',4,100,0),(72,'黄曲霉毒素总量免疫亲和柱（B1/B2/G1/G2）','华安麦科','盒','3mL，15支/盒',4,'',4,100,0),(73,'赭曲霉毒素A免疫亲和柱','华安麦科','盒','3mL，15支/盒',4,'',4,100,0),(93,'气相进样针','Agilent','根','10uL',5,'货号:5181-1267',4,100,0),(94,'进样针','岛津','根','10uL',5,'货号：221-34618',4,100,0),(95,'气质进样针','Agilent','根','10uL',5,'货号：G4513-80204',4,100,0),(96,'顶空进样针','岛津','根','',5,'货号：225-09419',4,100,0),(97,'进样针座','Agilent','包','1个/包',5,'货号：G1329-87017',4,100,0),(103,'隔垫','Agilent','盒','绿色，50个/盒',5,'P/N:5183-4759',4,100,0),(104,'岛津石墨垫','SGE','个','GVF16-004，',5,'P/N:072653',4,100,0),(105,'岛津石墨垫','SGE','个','GVF16-005，',5,'P/N:072654',4,100,0),(106,'Thermo灯丝','Thermo','个','',5,'货号：1R120404-1940',4,100,0),(107,'NCI灯丝','Agilent','个','',5,'货号：G7005-60072',4,100,0),(108,'GCQQQ灯丝','Agilent','个','',5,'货号：G7005-60061',4,100,0),(110,'岛津不分流衬管','岛津','盒','5根/盒',5,'P/N:221-48335-01',4,100,0),(111,'不分流衬管',' Agilent','根',' 4mm/',5,'货号：5062-3587',4,100,0),(113,'空气过滤网','AB','片','',5,'货号：1003712',4,100,0),(114,'离子源卡锁','AB','个','',5,'货号：27440',4,100,0),(116,'机械泵油','Agilent','瓶','1 L/瓶',5,'货号：9499202',4,100,0),(117,'机械泵油','Agilent','瓶','1 L/瓶',5,'货号6040-0834',4,100,0),(119,'戴安滤纸','DIONEX','盒','100PCS/盒',5,'P/N:056780',4,100,0),(120,'密封圈','Thermo','袋','50个/袋',5,'P/N:061687',4,100,0),(122,'丁腈手套','CNW','盒','L码，100只/盒',6,'',4,100,0),(123,'丁腈手套','CNW','盒','M码，100只/盒',6,'',4,100,0),(124,'丁腈手套','CNW','盒','S码，100只/盒',6,'',4,100,0),(125,'PE手套','生工/华帆','包','M码，50只/包',6,'',4,100,0),(126,'口罩','麦迪康','盒','50个/盒',6,'',4,100,0),(127,'抽纸','清风','提','3包/提',7,'',4,100,0),(128,'打印纸','宜客','包','A4，500张/包',7,'',4,100,0),(130,'订书针','可得优','盒','加厚，500个/盒',7,'',4,100,0),(131,'订书针','三方牌','盒','普通，1000个/盒',7,'',4,100,0),(132,'燕尾夹','得力','盒','32mm,24个/盒',7,'',4,100,0),(133,'燕尾夹','得力','盒','51mm，12个/盒',7,'',4,100,0),(134,'记号笔','智牌','盒','双头型，10支/盒',7,'',4,100,0),(135,'写字笔','智牌','盒','12支/盒',7,'',4,100,0),(138,'移动硬盘','','个','500G',7,'',4,100,0),(139,'电池','南孚','个','7号',7,'',4,100,0),(140,'公牛插座','公牛','个','16A',7,'',4,100,0),(141,'鼠标','双飞燕','个','',7,'',4,100,0),(142,'胶水','得力','瓶','50mL/瓶',7,'',4,100,0),(143,'计算器','','个','',7,'',4,100,0),(144,'起钉器','得力','个','',7,'',4,100,0);
/*!40000 ALTER TABLE `t_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_goodstype`
--

DROP TABLE IF EXISTS `t_goodstype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_goodstype` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(10) NOT NULL,
  `type_description` varchar(100) NOT NULL,
  `type_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_goodstype`
--

LOCK TABLES `t_goodstype` WRITE;
/*!40000 ALTER TABLE `t_goodstype` DISABLE KEYS */;
INSERT INTO `t_goodstype` VALUES (1,'试剂','试剂描述',0),(2,'一次性消耗品','一次性消耗品描述',0),(3,'SPE小柱','SPE小柱描述',0),(4,'仪器配件','仪器配件描述',0),(5,'劳保用品','劳保用品描述',0),(6,'办公用品','办公用品描述',0),(7,'实验器皿','实验器皿描述',0),(8,'酒精','酒精瓶',1),(9,'12','123',1),(10,'123','321313',1);
/*!40000 ALTER TABLE `t_goodstype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_inventory`
--

DROP TABLE IF EXISTS `t_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_inventory` (
  `inventory_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id_fk` int(11) NOT NULL,
  `barcode_id_fk` int(11) NOT NULL,
  `inventory_count` int(11) NOT NULL,
  `inventory_residue` int(11) NOT NULL,
  `user_id_fk` int(11) NOT NULL,
  `inventory_time` varchar(15) NOT NULL,
  `storehouse_id_fk` int(11) NOT NULL,
  `place_id_fk` int(11) NOT NULL,
  `inventory_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`inventory_id`),
  KEY `goods_id_fk` (`goods_id_fk`),
  KEY `barcode_id_fk` (`barcode_id_fk`),
  KEY `user_id_fk` (`user_id_fk`),
  KEY `storehouse_id_fk` (`storehouse_id_fk`),
  KEY `place_id_fk` (`place_id_fk`),
  CONSTRAINT `t_inventory_ibfk_1` FOREIGN KEY (`goods_id_fk`) REFERENCES `t_goods` (`goods_id`),
  CONSTRAINT `t_inventory_ibfk_2` FOREIGN KEY (`barcode_id_fk`) REFERENCES `t_barcode` (`barcode_id`),
  CONSTRAINT `t_inventory_ibfk_3` FOREIGN KEY (`user_id_fk`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `t_inventory_ibfk_4` FOREIGN KEY (`storehouse_id_fk`) REFERENCES `t_storehouse` (`storehouse_id`),
  CONSTRAINT `t_inventory_ibfk_5` FOREIGN KEY (`place_id_fk`) REFERENCES `t_place` (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_inventory`
--

LOCK TABLES `t_inventory` WRITE;
/*!40000 ALTER TABLE `t_inventory` DISABLE KEYS */;
INSERT INTO `t_inventory` VALUES (1,1,2016033001,10,15,1,'2016-03-30',1,1,0),(2,2,2016033002,10,15,2,'2016-03-10',1,1,0),(3,3,2016033003,15,115,1,'2016-03-22',1,1,0),(4,4,2016033003,20,555,1,'2016-03-22',1,1,0),(5,5,2016033005,10,15,1,'2016-03-30',1,1,0),(6,6,2016033006,10,15,2,'2016-03-10',1,1,0),(7,7,2016033007,15,115,1,'2016-03-22',1,1,0),(8,8,2016033008,20,555,1,'2016-03-22',1,1,0),(9,9,2016033009,20,555,1,'2016-03-22',1,1,0),(10,10,2016033010,20,555,1,'2016-03-22',1,1,0);
/*!40000 ALTER TABLE `t_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_log`
--

DROP TABLE IF EXISTS `t_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_log` (
  `log_id` int(11) NOT NULL,
  `log_content` varchar(100) NOT NULL,
  `log_date` varchar(30) NOT NULL,
  `log_reason` varchar(255) DEFAULT NULL,
  `log_who_fk` varchar(14) DEFAULT NULL,
  `log_type` int(11) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log`
--

LOCK TABLES `t_log` WRITE;
/*!40000 ALTER TABLE `t_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_message`
--

DROP TABLE IF EXISTS `t_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_fk` int(11) NOT NULL,
  `message_type` int(11) NOT NULL,
  `message_state` int(11) NOT NULL,
  `message_time` varchar(15) NOT NULL,
  `apply_id_fk` int(11) NOT NULL,
  `message_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `user_id_fk` (`user_id_fk`),
  KEY `apply_id_fk` (`apply_id_fk`),
  CONSTRAINT `t_message_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `t_message_ibfk_2` FOREIGN KEY (`apply_id_fk`) REFERENCES `t_apply` (`apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_message`
--

LOCK TABLES `t_message` WRITE;
/*!40000 ALTER TABLE `t_message` DISABLE KEYS */;
INSERT INTO `t_message` VALUES (1,1,1,1,'2016-10-25',1,0),(2,1,1,1,'2016-10-25',1,0),(3,1,1,1,'2016-10-25',1,0),(4,1,1,1,'2016-10-25',1,0),(5,1,1,1,'2016-10-25',1,0),(6,1,1,1,'2016-10-25',1,0),(7,1,1,1,'2016-10-25',1,0),(8,1,1,1,'2016-10-25',1,0),(9,1,1,1,'2016-10-25',1,0),(10,1,1,1,'2016-10-25',1,0),(11,1,1,1,'2016-10-25',1,0),(12,1,1,1,'2016-10-25',1,0);
/*!40000 ALTER TABLE `t_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_office`
--

DROP TABLE IF EXISTS `t_office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_office` (
  `office_id` int(11) NOT NULL AUTO_INCREMENT,
  `office_name` varchar(255) NOT NULL,
  `storehouse_id_fk` int(11) NOT NULL,
  `office_createtime` varchar(15) DEFAULT '2016-05-10',
  `office_isdel` int(11) DEFAULT '0',
  PRIMARY KEY (`office_id`),
  KEY `storehouse_id_fk` (`storehouse_id_fk`),
  CONSTRAINT `t_office_ibfk_1` FOREIGN KEY (`storehouse_id_fk`) REFERENCES `t_storehouse` (`storehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_office`
--

LOCK TABLES `t_office` WRITE;
/*!40000 ALTER TABLE `t_office` DISABLE KEYS */;
INSERT INTO `t_office` VALUES (1,'食品室',2,'2016-01-01',0),(2,'服装室',3,'2016-10-15',0),(3,'酒精室',1,'2016-10-22',0),(4,'苹果室',2,'2016-05-10',1),(5,'啪啪啪',2,'2016-04-06',1),(6,'麻醉药室',4,'2016-04-06',1),(7,'药品室',1,'2016-01-01',0);
/*!40000 ALTER TABLE `t_office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_performance`
--

DROP TABLE IF EXISTS `t_performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_performance` (
  `performance_id` int(11) NOT NULL AUTO_INCREMENT,
  `performance_time` varchar(15) NOT NULL,
  `user_id_fk` int(11) NOT NULL,
  `goods_id_fk` int(11) NOT NULL,
  `performance_result` int(11) NOT NULL,
  `performance_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`performance_id`),
  KEY `user_id_fk` (`user_id_fk`),
  KEY `goods_id_fk` (`goods_id_fk`),
  CONSTRAINT `t_performance_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `t_performance_ibfk_2` FOREIGN KEY (`goods_id_fk`) REFERENCES `t_goods` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_performance`
--

LOCK TABLES `t_performance` WRITE;
/*!40000 ALTER TABLE `t_performance` DISABLE KEYS */;
INSERT INTO `t_performance` VALUES (1,'2015-01-04',1,1,1,0),(2,'2015-01-04',1,1,1,0),(3,'2015-01-04',1,1,1,0),(4,'2015-01-04',1,1,1,0),(5,'2015-01-04',1,1,1,0),(6,'2015-01-04',1,1,1,0),(7,'2015-01-04',1,1,1,0),(8,'2015-01-04',1,1,1,0),(9,'2015-01-04',1,1,1,0),(10,'2015-01-04',1,1,1,0);
/*!40000 ALTER TABLE `t_performance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_place`
--

DROP TABLE IF EXISTS `t_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_place` (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place_name` varchar(255) NOT NULL,
  `storehouse_id_fk` int(11) NOT NULL,
  `place_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`place_id`),
  KEY `storehouse_id_fk` (`storehouse_id_fk`),
  CONSTRAINT `t_place_ibfk_1` FOREIGN KEY (`storehouse_id_fk`) REFERENCES `t_storehouse` (`storehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_place`
--

LOCK TABLES `t_place` WRITE;
/*!40000 ALTER TABLE `t_place` DISABLE KEYS */;
INSERT INTO `t_place` VALUES (1,'苹果',1,0),(2,'感冒药',1,0),(3,'50°酒精',4,0),(5,'80°酒精',4,0),(6,'汉服',3,0),(7,'安眠药 ',1,0),(8,'香蕉',1,0),(10,'婚纱',3,0),(11,'100°酒精',4,0),(12,'苹果',2,0);
/*!40000 ALTER TABLE `t_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_privilege`
--

DROP TABLE IF EXISTS `t_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_privilege` (
  `privilege_id` int(11) NOT NULL AUTO_INCREMENT,
  `privilege_name` varchar(255) NOT NULL,
  `privilege_path` varchar(255) NOT NULL,
  `privilege_node` int(11) NOT NULL,
  `privilege_rootnode` int(11) NOT NULL,
  `privilege_isroort` varchar(255) NOT NULL,
  `privilege_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`privilege_id`),
  KEY `privilege_rootnode` (`privilege_rootnode`),
  CONSTRAINT `t_privilege_ibfk_1` FOREIGN KEY (`privilege_rootnode`) REFERENCES `t_privilege` (`privilege_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_privilege`
--

LOCK TABLES `t_privilege` WRITE;
/*!40000 ALTER TABLE `t_privilege` DISABLE KEYS */;
INSERT INTO `t_privilege` VALUES (1,'1','1',1,1,'1',0);
/*!40000 ALTER TABLE `t_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `role_creater` varchar(255) NOT NULL,
  `role_createdate` varchar(15) NOT NULL,
  `role_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (0,'系统管理员','管理员','2016-01-01',0),(1,'采购人员','采购人员','2016-01-01',0),(2,'检测人员','检测人员','2016-01-01',0),(3,'仓库管理员','仓库管理员','2016-01-01',0),(4,'供应商','供应商','2016-01-01',0);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_privilege`
--

DROP TABLE IF EXISTS `t_role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_privilege` (
  `role_privilege_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id_fk` int(11) NOT NULL,
  `privilege_id_fk` int(11) NOT NULL,
  `role_privilege_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_privilege_id`),
  KEY `user_id_fk` (`role_id_fk`),
  KEY `privilege_id_fk` (`privilege_id_fk`),
  CONSTRAINT `t_role_privilege_ibfk_2` FOREIGN KEY (`privilege_id_fk`) REFERENCES `t_privilege` (`privilege_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_privilege`
--

LOCK TABLES `t_role_privilege` WRITE;
/*!40000 ALTER TABLE `t_role_privilege` DISABLE KEYS */;
INSERT INTO `t_role_privilege` VALUES (1,1,1,0);
/*!40000 ALTER TABLE `t_role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_storehouse`
--

DROP TABLE IF EXISTS `t_storehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_storehouse` (
  `storehouse_id` int(11) NOT NULL AUTO_INCREMENT,
  `storehouse_name` varchar(10) NOT NULL,
  `storehouse_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`storehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_storehouse`
--

LOCK TABLES `t_storehouse` WRITE;
/*!40000 ALTER TABLE `t_storehouse` DISABLE KEYS */;
INSERT INTO `t_storehouse` VALUES (1,'药物库',0),(2,'水果库',0),(3,'服装库',0),(4,'酒精库',0);
/*!40000 ALTER TABLE `t_storehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_supplier_goodstype`
--

DROP TABLE IF EXISTS `t_supplier_goodstype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_supplier_goodstype` (
  `supplier_goodstype_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_fk` int(11) NOT NULL,
  `type_id_fk` int(11) NOT NULL,
  `supplier_goodstype_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`supplier_goodstype_id`),
  KEY `user_id_fk` (`user_id_fk`),
  KEY `type_id_fk` (`type_id_fk`),
  CONSTRAINT `t_supplier_goodstype_ibfk_1` FOREIGN KEY (`user_id_fk`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `t_supplier_goodstype_ibfk_2` FOREIGN KEY (`type_id_fk`) REFERENCES `t_goodstype` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_supplier_goodstype`
--

LOCK TABLES `t_supplier_goodstype` WRITE;
/*!40000 ALTER TABLE `t_supplier_goodstype` DISABLE KEYS */;
INSERT INTO `t_supplier_goodstype` VALUES (1,136,1,0),(14,147,1,0),(15,147,2,0),(18,150,1,0),(19,150,2,0),(20,150,3,0);
/*!40000 ALTER TABLE `t_supplier_goodstype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_type`
--

DROP TABLE IF EXISTS `t_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_type` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(10) NOT NULL,
  `type_description` varchar(100) NOT NULL,
  `type_isdel` int(11) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_type`
--

LOCK TABLES `t_type` WRITE;
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
INSERT INTO `t_type` VALUES (1,'酒精批发商','酒精批发商',0),(2,'毒药批发商','毒药批发商',0),(3,'苹果批发商','苹果批发商',0);
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  `user_password` varchar(16) DEFAULT NULL,
  `user_address` varchar(30) DEFAULT NULL,
  `user_lianxiren` varchar(5) DEFAULT NULL,
  `user_phone` varchar(12) DEFAULT NULL,
  `user_fax` varchar(13) DEFAULT NULL,
  `role_id_fk` int(11) DEFAULT NULL,
  `office_id_fk` int(11) DEFAULT NULL,
  `user_createtime` varchar(15) DEFAULT NULL,
  `user_lastlogintime` varchar(15) DEFAULT NULL,
  `user_lastloginip` varchar(20) DEFAULT NULL,
  `user_isdel` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  KEY `role_id_fk` (`role_id_fk`),
  KEY `office_id_fk` (`office_id_fk`),
  CONSTRAINT `t_user_ibfk_2` FOREIGN KEY (`office_id_fk`) REFERENCES `t_office` (`office_id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'111','438443117@qq.com','111','江西省赣州市章贡区湖边镇江边巷','肖亦鸣','18770913803','1190000000',0,1,'2016-01-01','2016-04-10','127.0.0.1',0),(2,'222','438443117@qq.com','222','江西省赣州市章贡区湖边镇江边巷','肖亦鸣哈哈','18770913803','110',3,1,'2016-01-01','2016-01-01','192.168.1.1',0),(136,'2222','438443117@qq.com','222','江西省赣州市章贡区湖边镇江边巷','肖亦鸣','18770913803','111',1,1,'2016-01-01','2016-01-01','192.168.1.1',0),(147,'222','438443117@qq.com',NULL,'江西省赣州市章贡区湖边镇江边巷','肖亦..','18770913803','111',4,NULL,'2016-04-06',NULL,NULL,0),(148,'xiao','438443117@qq.com','438443117@qq.com','<a href=\"www.baidu.com\">百度</a>','蓝点 1','18770913803','110',0,1,'2016-04-06',NULL,NULL,0),(150,'2220021313','438443117@qq.com',NULL,'江西农业大学','肖亦鸣','18770913803','1111',4,NULL,'2016-04-08',NULL,NULL,1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_voucher`
--

DROP TABLE IF EXISTS `t_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_voucher` (
  `voucher_id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_createtime` varchar(15) NOT NULL,
  `voucher_brokerage` int(11) NOT NULL,
  `storehouse_id_fk` int(11) NOT NULL,
  `user_id_fk` int(11) NOT NULL,
  `voucher_remark` varchar(255) DEFAULT NULL,
  `voucher_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`voucher_id`),
  KEY `storehouse_id_fk` (`storehouse_id_fk`),
  KEY `user_id_fk` (`user_id_fk`),
  CONSTRAINT `t_voucher_ibfk_1` FOREIGN KEY (`storehouse_id_fk`) REFERENCES `t_storehouse` (`storehouse_id`),
  CONSTRAINT `t_voucher_ibfk_2` FOREIGN KEY (`user_id_fk`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_voucher`
--

LOCK TABLES `t_voucher` WRITE;
/*!40000 ALTER TABLE `t_voucher` DISABLE KEYS */;
INSERT INTO `t_voucher` VALUES (1,'2016-10-05',1,1,1,'1',0),(2,'2016-10-05',1,1,1,'1',0),(3,'2016-10-05',1,1,1,'1',0),(4,'2016-10-05',1,1,1,'1',0),(5,'2016-10-05',1,1,1,'1',0),(6,'2016-10-05',1,1,1,'1',0),(7,'2016-10-05',1,1,1,'1',0),(8,'2016-10-05',1,1,1,'1',0),(9,'2016-10-05',1,1,1,'1',0),(10,'2016-10-05',1,1,1,'1',0),(11,'2016-10-05',1,1,1,'1',0);
/*!40000 ALTER TABLE `t_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_voucher_goods`
--

DROP TABLE IF EXISTS `t_voucher_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_voucher_goods` (
  `voucher_goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_id_fk` int(11) NOT NULL,
  `goods_id_fk` int(11) NOT NULL,
  `voucher_goods_count` int(11) NOT NULL,
  `barcode_id_fk` int(11) NOT NULL,
  `voucher_goods_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`voucher_goods_id`),
  KEY `voucher_id_fk` (`voucher_id_fk`),
  KEY `goods_id_fk` (`goods_id_fk`),
  KEY `barcode_id_fk` (`barcode_id_fk`),
  CONSTRAINT `t_voucher_goods_ibfk_1` FOREIGN KEY (`voucher_id_fk`) REFERENCES `t_voucher` (`voucher_id`),
  CONSTRAINT `t_voucher_goods_ibfk_2` FOREIGN KEY (`goods_id_fk`) REFERENCES `t_goods` (`goods_id`),
  CONSTRAINT `t_voucher_goods_ibfk_3` FOREIGN KEY (`barcode_id_fk`) REFERENCES `t_barcode` (`barcode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_voucher_goods`
--

LOCK TABLES `t_voucher_goods` WRITE;
/*!40000 ALTER TABLE `t_voucher_goods` DISABLE KEYS */;
INSERT INTO `t_voucher_goods` VALUES (1,1,1,30,2016033001,0),(2,1,2,30,2016033002,0),(3,2,1,30,2016033001,0),(4,2,2,30,2016033002,0),(5,3,1,30,2016033001,0),(6,3,2,30,2016033002,0),(7,3,1,30,2016033001,0),(8,3,2,30,2016033002,0),(9,3,1,30,2016033001,0),(10,3,2,30,2016033002,0);
/*!40000 ALTER TABLE `t_voucher_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_wordbook`
--

DROP TABLE IF EXISTS `t_wordbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_wordbook` (
  `wordbook_id` int(11) NOT NULL AUTO_INCREMENT,
  `wordbook_type` varchar(10) NOT NULL,
  `wordbook_number` int(11) NOT NULL,
  `wordbook_name` varchar(255) NOT NULL,
  `wordbook_isdel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`wordbook_id`),
  KEY `wordbook_number` (`wordbook_number`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wordbook`
--

LOCK TABLES `t_wordbook` WRITE;
/*!40000 ALTER TABLE `t_wordbook` DISABLE KEYS */;
INSERT INTO `t_wordbook` VALUES (1,'单位',1,'瓶',0),(2,'单位',2,'盒',0),(3,'规格',3,'100ml/支',0),(6,'单位',4,'个',0);
/*!40000 ALTER TABLE `t_wordbook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-11 16:54:45
