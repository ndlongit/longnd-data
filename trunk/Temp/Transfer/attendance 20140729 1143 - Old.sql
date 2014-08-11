-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.17


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

--
-- Definition of table `approval`
--

DROP TABLE IF EXISTS `approval`;
CREATE TABLE `approval` (
  `approvalId` int(11) NOT NULL AUTO_INCREMENT,
  `timesheetId` int(11) DEFAULT NULL,
  `approverId` int(11) DEFAULT NULL,
  `approvalTimeStamp` datetime DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  `attendId` int(11) DEFAULT NULL,
  `isDeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`approvalId`),
  KEY `userRel_idx` (`approverId`),
  KEY `timesheetID_idx` (`timesheetId`),
  KEY `statusRel_idx` (`statusId`),
  KEY `attendance Id_idx` (`attendId`),
  CONSTRAINT `attendance Id` FOREIGN KEY (`attendId`) REFERENCES `attendance` (`attendId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `statusRel` FOREIGN KEY (`statusId`) REFERENCES `status` (`statusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `timesheetID` FOREIGN KEY (`timesheetId`) REFERENCES `timesheet` (`timesheetId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userRel` FOREIGN KEY (`approverId`) REFERENCES `useraccount` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `approval`
--

/*!40000 ALTER TABLE `approval` DISABLE KEYS */;
INSERT INTO `approval` (`approvalId`,`timesheetId`,`approverId`,`approvalTimeStamp`,`statusId`,`attendId`,`isDeleted`) VALUES 
 (1,1,1,'2014-02-25 00:00:00',1,NULL,0),
 (2,1,1,'2014-02-26 16:36:47',1,NULL,0),
 (3,1,1,'2014-02-26 16:37:09',1,NULL,0),
 (4,1,1,'2014-02-26 16:38:11',1,NULL,0);
/*!40000 ALTER TABLE `approval` ENABLE KEYS */;


--
-- Definition of table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `attendId` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `reasontypeId` int(11) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `projectId` int(11) DEFAULT NULL,
  `isDeleted` int(11) DEFAULT '0',
  PRIMARY KEY (`attendId`),
  KEY `user_id_idx` (`user_id`),
  KEY `reason_type_id_idx` (`reasontypeId`),
  KEY `project id_idx` (`projectId`),
  CONSTRAINT `project id` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reason_type_id` FOREIGN KEY (`reasontypeId`) REFERENCES `reasontype` (`reason_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `useraccount` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` (`attendId`,`user_id`,`startdate`,`enddate`,`reasontypeId`,`description`,`projectId`,`isDeleted`) VALUES 
 (1,1,NULL,NULL,2,NULL,NULL,0);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;


--
-- Definition of table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(100) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`roleId`,`rolename`,`description`) VALUES 
 (1,'ROLE_ADMIN','role administrator'),
 (2,'ROLE_MANAGER','role manager'),
 (3,'ROLE_STAFF','role staff');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;


--
-- Definition of table `contract_type`
--

DROP TABLE IF EXISTS `contract_type`;
CREATE TABLE `contract_type` (
  `contract_typeId` int(11) NOT NULL AUTO_INCREMENT,
  `contract_name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`contract_typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contract_type`
--

/*!40000 ALTER TABLE `contract_type` DISABLE KEYS */;
INSERT INTO `contract_type` (`contract_typeId`,`contract_name`,`description`,`isDeleted`) VALUES 
 (1,'Permanence','No period',0x00),
 (2,'Part Time','Work in evening',0x01),
 (3,'Probation','testing time test',0x01),
 (4,'New ','test for fun\n',0x01),
 (5,'new type','for fun hahaha',0x01),
 (6,'111','111',0x01),
 (7,'sdfadsf','fsdafasdf',0x01),
 (8,'asdfasdf','sdafasdf',0x01),
 (9,'123','123 adf fffff',0x01),
 (10,'New','Testing',0x01),
 (11,'Old','N/A',0x01),
 (12,'Fridaytest123','123456',0x01),
 (13,'Test contract1','hihi haha',0x01),
 (14,'Temporary','3 months',0x01),
 (15,'ABC','Testing',0x01),
 (16,'Temporary contract','2 months',0x00),
 (17,'Test','123',0x01),
 (18,'Part-time','Evening',0x00),
 (19,'Test','123456',0x00),
 (20,'Testtttt','123456',0x01),
 (21,'123456','654321',0x01);
/*!40000 ALTER TABLE `contract_type` ENABLE KEYS */;


--
-- Definition of table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentCode` varchar(20) DEFAULT NULL,
  `departmentName` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`departmentId`,`departmentCode`,`departmentName`,`description`,`isDeleted`) VALUES 
 (1,'POG','IT','Information Technology',0x00),
 (2,'COG','Admin','Administration',0x00),
 (3,'ABC','Accounting','Accountant',0x00),
 (6,'YYY','TestFriday','123',0x01),
 (7,'ZZZ','Friday123','456',0x01),
 (8,'DEFG','Consulting-test','Doing test',0x01),
 (9,'BBB','AABBCC','123',0x01),
 (10,NULL,'Department Testing','123',0x01),
 (11,NULL,'TEsting','',0x01),
 (12,NULL,'admin','',0x01),
 (13,NULL,'Testing','dfgh',0x01),
 (14,NULL,'Testing department','test',0x01),
 (15,'SSS','Sales','123',0x01),
 (16,'QWE','Testt','',0x01),
 (17,'ABC','Test','123',0x01),
 (18,'ABC','Tetttt','1397986',0x01),
 (19,'ZXC','Consulting','testing',0x01);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


--
-- Definition of table `employ_grade`
--

DROP TABLE IF EXISTS `employ_grade`;
CREATE TABLE `employ_grade` (
  `idemploy_grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `employ_gradecol` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`idemploy_grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employ_grade`
--

/*!40000 ALTER TABLE `employ_grade` DISABLE KEYS */;
INSERT INTO `employ_grade` (`idemploy_grade_id`,`employ_gradecol`,`description`,`isDeleted`) VALUES 
 (1,'G1','Programmer',0x00),
 (2,'G2','Junior staff',0x00),
 (3,'G3','Senior staff',0x00),
 (4,'G4','Manager',0x00),
 (5,'G6','Testinggg',0x01),
 (6,'Gtest','ABCDEF',0x01),
 (7,'G6 test','abc',0x01),
 (8,'G7','456',0x01),
 (9,'G7','',0x01),
 (10,'G5','123456789',0x01),
 (11,'G7','Test',0x01),
 (12,'G5','AAA',0x01),
 (13,'G5','Testing',0x01),
 (14,'G6','654978',0x01),
 (15,'G5','abcdefghiklmn',0x01);
/*!40000 ALTER TABLE `employ_grade` ENABLE KEYS */;


--
-- Definition of table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `projectId` int(11) NOT NULL AUTO_INCREMENT,
  `projectcode` varchar(20) DEFAULT NULL,
  `projectname` varchar(250) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`projectId`),
  KEY `statusIDRel_idx` (`statusId`),
  CONSTRAINT `statusIDRel` FOREIGN KEY (`statusId`) REFERENCES `status` (`statusId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project`
--

/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`projectId`,`projectcode`,`projectname`,`startdate`,`enddate`,`statusId`,`isDeleted`) VALUES 
 (1,'PJ001','KAKAO','2014-03-01 00:00:00','2014-03-31 00:00:00',10,0x00),
 (2,'PJ002','CAFE','2014-03-15 00:00:00','2014-03-31 00:00:00',10,0x00),
 (3,'PJ003','IBSV Attendance','2014-07-01 00:00:00','2015-07-31 00:00:00',10,0x00),
 (4,'PJ004','PDF Infra','2014-05-12 00:00:00','2014-07-01 00:00:00',10,0x00),
 (5,'PJ005','Non','2014-07-01 00:00:00','2014-07-05 00:00:00',10,0x01),
 (6,'PJ006','ABC','2014-07-03 00:00:00','2014-08-07 00:00:00',10,0x01),
 (7,'PJ007','ABC','2014-07-03 00:00:00','2014-07-19 00:00:00',10,0x01),
 (8,'PJ008','CAFESUA','2014-07-01 00:00:00','2014-07-16 00:00:00',10,0x00),
 (9,'PJ009','ccccccc','2014-07-01 00:00:00','2014-07-03 00:00:00',11,0x01),
 (10,'PJ010','cafesuada','2014-05-01 00:00:00','2014-09-18 00:00:00',11,0x00),
 (11,'PJ011','CoCa','2013-01-01 00:00:00','2013-02-02 00:00:00',11,0x01),
 (12,'PJ012','Fridaytest','2015-05-01 00:00:00','2014-05-30 00:00:00',10,0x01),
 (13,'PJ013','Fridaytest1','2014-07-31 00:00:00','2014-07-01 00:00:00',10,0x01),
 (14,'PJ014','Fridaytest','2014-07-31 00:00:00','2014-07-01 00:00:00',10,0x01),
 (15,'PJ015','Fridaytest','2014-07-31 00:00:00','2014-07-01 00:00:00',10,0x01),
 (16,'PJ016','Fridaytest','2014-07-31 00:00:00','2014-06-01 00:00:00',10,0x01),
 (17,'PJ016','Testing','2014-07-20 00:00:00','2014-07-31 00:00:00',10,0x00),
 (18,'PJ018','fridaytest11','2014-07-01 00:00:00','2014-07-31 00:00:00',10,0x01),
 (19,'PJ019','fridaytest','2014-07-01 00:00:00','2014-07-31 00:00:00',10,0x01),
 (20,'PJ020','Fridaytest2','2014-08-01 00:00:00','2014-08-31 00:00:00',10,0x01),
 (21,'PJ021','Testing1','2015-06-01 00:00:00','2015-06-30 00:00:00',10,0x01),
 (22,'PJ 002','Testting2','2014-07-31 00:00:00','2014-07-01 00:00:00',10,0x01),
 (23,'PJ004','Testing4','2014-07-31 00:00:00','2014-07-01 00:00:00',10,0x01),
 (24,'PJ 009','Testing2','2014-07-31 00:00:00','2014-07-01 00:00:00',10,0x01),
 (25,'PJ009','Testing2','2014-07-01 00:00:00','2014-07-31 00:00:00',10,0x01),
 (26,'PJ0025','Testing4','2014-07-01 00:00:00','2014-07-31 00:00:00',10,0x01),
 (27,'PJ021','Testing','2014-08-01 00:00:00','2014-08-31 00:00:00',10,0x01),
 (28,'PJ019','Testing2','2014-08-01 00:00:00','2014-08-25 00:00:00',10,0x01),
 (29,'PJ030','Testing4','2014-07-31 00:00:00','2014-07-01 00:00:00',10,0x01),
 (30,'PJ031','Test5','2014-07-16 00:00:00','2014-07-31 00:00:00',10,0x01),
 (31,'PJ035','Test2','2014-07-01 00:00:00','2014-07-31 00:00:00',10,0x01),
 (32,'PJ033','Testtt','2014-07-01 00:00:00','2014-07-31 00:00:00',10,0x01),
 (33,'PJ034','testing','2014-07-01 00:00:00','2014-07-31 00:00:00',10,0x01),
 (34,'PJ035','Test4','2014-07-18 00:00:00','2014-07-31 00:00:00',10,0x01),
 (35,'PJ009','Test7','2014-07-24 00:00:00','2014-08-22 00:00:00',10,0x01),
 (36,'PJ011','Test4','2014-07-23 00:00:00','2014-07-31 00:00:00',10,0x00),
 (37,'PJ010','Test5','2014-07-24 00:00:00','2014-08-04 00:00:00',10,0x01),
 (38,'PJ031','Test5','2014-08-13 00:00:00','2014-08-21 00:00:00',10,0x00),
 (39,'PJ051','123','2014-07-24 00:00:00','2014-07-31 00:00:00',10,0x01),
 (40,'PJ050','Test6','2014-07-23 00:00:00','2014-07-31 00:00:00',10,0x01),
 (41,'PJ071','Test10','2014-07-23 00:00:00','2014-07-31 00:00:00',10,0x01),
 (42,'PJ071','Test10','2014-09-01 00:00:00','2014-09-15 00:00:00',10,0x01),
 (43,'PJ035','Test11','2014-09-18 00:00:00','2014-09-30 00:00:00',10,0x01),
 (44,'PJ035','Test11','2015-01-01 00:00:00','2015-01-30 00:00:00',10,0x01),
 (45,'PJ075','Test12','2015-02-02 00:00:00','2015-02-28 00:00:00',10,0x01),
 (46,'PJ020','Test6','2014-11-01 00:00:00','2014-11-19 00:00:00',10,0x00),
 (47,'PJ021','Test7','2015-01-01 00:00:00','2015-07-31 00:00:00',10,0x01),
 (48,'PJ021','Test7','2015-08-17 00:00:00','2015-08-31 00:00:00',10,0x01),
 (49,'PJ011','Test4','2014-12-01 00:00:00','2014-12-20 00:00:00',10,0x01),
 (50,'PJ016','Testt','2014-08-08 00:00:00','2015-07-01 00:00:00',10,0x01),
 (51,'PJ039','Test8','2014-07-24 00:00:00','2014-07-31 00:00:00',10,0x01),
 (52,'PJ048','Test9','2014-09-01 00:00:00','2014-10-31 00:00:00',10,0x00),
 (53,'PJ012','Test7','2014-12-01 00:00:00','2014-12-15 00:00:00',10,0x01),
 (54,'PJ003','Test11','2014-08-01 00:00:00','2014-08-15 00:00:00',10,0x01),
 (55,'PJ055','Test again','2015-01-01 00:00:00','2015-01-15 00:00:00',10,0x01),
 (56,'PJ56','Testing ','2015-02-01 00:00:00','2015-02-15 00:00:00',10,0x01),
 (57,'PJ057','Testt','2014-07-31 00:00:00','2014-08-31 00:00:00',10,0x01),
 (58,'PJ057','Testt','2014-07-31 00:00:00','2014-08-31 00:00:00',10,0x01),
 (59,'PJ078','Testt','2014-08-01 00:00:00','2014-08-15 00:00:00',10,0x01),
 (60,'PJ012','Test111','2014-07-30 00:00:00','2014-07-31 00:00:00',10,0x01),
 (61,'PJ053','Test11','2014-07-29 00:00:00','2014-07-31 00:00:00',10,0x01),
 (62,'PJ37','Testt','2014-09-01 00:00:00','2014-09-15 00:00:00',10,0x01),
 (63,'PJ045','Testtttttt','2014-07-30 00:00:00','2014-07-31 00:00:00',10,0x01);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;


--
-- Definition of table `project_assignment`
--

DROP TABLE IF EXISTS `project_assignment`;
CREATE TABLE `project_assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `projectIDRel_idx` (`projectId`),
  KEY `roleIDRel_idx` (`roleId`),
  KEY `userIDRel_idx` (`userId`),
  CONSTRAINT `projectIDRel` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_assignment_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `useraccount` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `roleIDRel` FOREIGN KEY (`roleId`) REFERENCES `authorities` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_assignment`
--

/*!40000 ALTER TABLE `project_assignment` DISABLE KEYS */;
INSERT INTO `project_assignment` (`id`,`projectId`,`userId`,`startdate`,`enddate`,`roleId`,`isDeleted`) VALUES 
 (1,1,6,'2014-03-01 00:00:00','2014-03-31 00:00:00',1,0x00),
 (2,2,6,'2014-03-15 00:00:00','2014-04-30 00:00:00',2,0x00),
 (3,3,8,'2014-03-15 00:00:00','2015-03-15 00:00:00',1,0x01),
 (4,1,7,'2014-03-01 00:00:00','2014-03-31 00:00:00',1,0x00),
 (5,1,5,'2014-03-01 00:00:00','2014-03-31 00:00:00',1,0x00),
 (6,2,4,'2014-03-15 00:00:00','2014-04-30 00:00:00',2,0x00),
 (7,3,22,'2014-05-01 00:00:00','2014-05-15 00:00:00',3,0x00),
 (8,2,23,'2014-05-01 00:00:00','2014-05-30 00:00:00',3,0x00),
 (9,3,7,'2014-05-01 00:00:00','2014-05-15 00:00:00',3,0x00),
 (10,3,21,'2014-05-01 00:00:00','2014-05-15 00:00:00',3,0x00),
 (11,4,8,'2014-05-12 00:00:00','2014-07-31 00:00:00',3,0x01),
 (12,3,8,'2014-07-01 00:00:00','2014-07-31 00:00:00',3,0x00),
 (13,3,6,'2014-07-01 00:00:00','2015-07-01 00:00:00',3,0x01),
 (14,3,6,'2014-07-01 00:00:00','2014-07-03 00:00:00',3,0x01),
 (15,3,5,'2014-07-01 00:00:00','2015-05-31 00:00:00',3,0x01),
 (16,3,5,'2014-07-01 00:00:00','2010-05-31 00:00:00',3,0x01),
 (17,4,6,'2014-07-01 00:00:00','2015-07-31 00:00:00',3,0x00),
 (18,8,5,'2014-07-01 00:00:00','2014-07-31 00:00:00',3,0x00),
 (19,1,8,'2014-07-01 00:00:00','2014-03-31 00:00:00',3,0x00),
 (20,1,17,'2014-07-17 00:00:00','2014-07-24 00:00:00',3,0x00),
 (21,17,5,'2014-07-31 00:00:00','2014-09-29 00:00:00',3,0x01),
 (22,17,25,'2014-07-31 00:00:00','2014-09-29 00:00:00',3,0x01),
 (23,21,25,'2015-07-01 00:00:00','2015-07-31 00:00:00',3,0x01),
 (24,36,25,'2014-07-21 00:00:00','2014-07-31 00:00:00',3,0x01),
 (25,34,5,'2014-07-13 00:00:00','2014-07-31 00:00:00',3,0x01),
 (26,34,5,'2014-07-13 00:00:00','2014-07-31 00:00:00',3,0x01),
 (27,34,5,'2014-07-13 00:00:00','2014-08-01 00:00:00',3,0x01),
 (28,34,5,'2014-07-13 00:00:00','2014-08-01 00:00:00',3,0x01),
 (29,38,25,'2014-07-24 00:00:00','2014-08-04 00:00:00',3,0x01),
 (30,38,25,'2014-07-24 00:00:00','2014-08-04 00:00:00',3,0x01),
 (31,37,25,'2014-07-24 00:00:00','2014-08-04 00:00:00',3,0x01),
 (32,37,25,'2014-07-24 00:00:00','2014-08-04 00:00:00',3,0x01),
 (33,38,25,'2014-07-24 00:00:00','2014-08-04 00:00:00',3,0x01),
 (34,38,25,'2014-07-24 00:00:00','2014-08-04 00:00:00',3,0x01),
 (35,40,19,'2014-07-23 00:00:00','2014-07-31 00:00:00',3,0x01),
 (36,40,19,'2014-07-23 00:00:00','2014-07-31 00:00:00',3,0x01),
 (37,40,19,'2014-07-23 00:00:00','2014-07-31 00:00:00',3,0x01),
 (38,41,19,'2014-07-23 00:00:00','2014-07-31 00:00:00',3,0x01),
 (39,41,19,'2014-07-23 00:00:00','2014-07-31 00:00:00',3,0x01),
 (40,42,24,'2014-09-01 00:00:00','2014-09-15 00:00:00',3,0x01),
 (41,42,24,'2014-09-01 00:00:00','2014-09-15 00:00:00',3,0x01),
 (42,42,24,'2014-09-01 00:00:00','2014-09-15 00:00:00',3,0x01),
 (43,42,24,'2014-09-01 00:00:00','2014-09-15 00:00:00',3,0x01),
 (44,10,22,'2014-05-01 00:00:00','2014-09-18 00:00:00',3,0x01),
 (45,10,22,'2014-05-01 00:00:00','2014-09-18 00:00:00',3,0x00),
 (46,10,22,'2014-05-01 00:00:00','2014-09-18 00:00:00',3,0x00),
 (47,43,18,'2014-09-18 00:00:00','2014-09-30 00:00:00',3,0x01),
 (48,43,18,'2014-09-18 00:00:00','2014-09-30 00:00:00',3,0x01),
 (49,43,18,'2014-09-18 00:00:00','2014-09-30 00:00:00',3,0x01),
 (50,43,18,'2014-09-18 00:00:00','2014-09-30 00:00:00',3,0x01),
 (51,44,20,'2015-01-01 00:00:00','2015-01-30 00:00:00',3,0x01),
 (52,44,27,'2015-01-01 00:00:00','2015-01-30 00:00:00',3,0x01),
 (53,44,27,'2015-01-01 00:00:00','2015-01-30 00:00:00',3,0x01),
 (54,45,5,'2015-02-02 00:00:00','2015-02-28 00:00:00',3,0x01),
 (55,45,5,'2015-02-02 00:00:00','2015-02-28 00:00:00',3,0x01),
 (56,46,27,'2014-11-01 00:00:00','2014-11-20 00:00:00',3,0x01),
 (57,46,27,'2014-11-01 00:00:00','2014-11-20 00:00:00',3,0x01),
 (58,38,25,'2014-08-13 00:00:00','2014-08-21 00:00:00',3,0x01),
 (59,38,25,'2014-08-13 00:00:00','2014-08-21 00:00:00',3,0x01),
 (60,46,27,'2014-11-01 00:00:00','2014-11-20 00:00:00',3,0x01),
 (61,46,27,'2014-11-01 00:00:00','2014-11-20 00:00:00',3,0x01),
 (62,38,25,'2014-08-13 00:00:00','2014-08-21 00:00:00',3,0x00),
 (63,38,27,'2014-11-01 00:00:00','2014-11-20 00:00:00',3,0x01),
 (64,46,25,'2014-11-01 00:00:00','2014-11-20 00:00:00',3,0x00),
 (65,36,5,'2014-07-21 00:00:00','2014-07-31 00:00:00',3,0x01),
 (66,47,5,'2015-01-01 00:00:00','2015-07-31 00:00:00',3,0x01),
 (67,47,5,'2015-01-01 00:00:00','2015-07-31 00:00:00',3,0x01),
 (68,48,6,'2015-08-17 00:00:00','2015-08-31 00:00:00',3,0x01),
 (69,49,27,'2014-12-01 00:00:00','2014-12-20 00:00:00',3,0x01),
 (70,36,25,'2014-12-01 00:00:00','2014-12-20 00:00:00',3,0x01),
 (71,46,19,'2014-10-01 00:00:00','2014-10-20 00:00:00',3,0x00),
 (72,51,5,'2014-07-24 00:00:00','2014-07-31 00:00:00',3,0x01),
 (73,51,5,'2014-07-24 00:00:00','2014-07-31 00:00:00',3,0x01),
 (74,52,27,'2014-07-31 00:00:00','2014-08-31 00:00:00',3,0x01),
 (75,52,27,'2014-07-31 00:00:00','2014-08-31 00:00:00',3,0x00),
 (76,52,34,'2014-08-01 00:00:00','2014-08-31 00:00:00',3,0x01),
 (77,52,34,'2014-08-01 00:00:00','2014-08-31 00:00:00',3,0x01),
 (78,52,34,'2014-07-25 00:00:00','2014-08-13 00:00:00',3,0x01),
 (79,52,34,'2014-09-01 00:00:00','2014-09-30 00:00:00',3,0x00),
 (80,52,18,'2014-07-25 00:00:00','2014-08-27 00:00:00',3,0x00),
 (81,53,25,'2014-11-28 00:00:00','2014-12-10 00:00:00',3,0x01),
 (82,52,5,'2014-07-25 00:00:00','2014-07-31 00:00:00',3,0x01),
 (83,52,5,'2014-07-25 00:00:00','2014-07-31 00:00:00',3,0x01),
 (84,52,5,'2014-07-25 00:00:00','2014-07-31 00:00:00',3,0x01),
 (85,52,5,'2014-07-25 00:00:00','2014-07-31 00:00:00',3,0x01),
 (86,52,5,'2014-07-25 00:00:00','2014-07-31 00:00:00',3,0x01),
 (87,36,5,'2014-07-29 00:00:00','2014-07-31 00:00:00',3,0x00),
 (88,36,5,'2014-07-29 00:00:00','2014-07-31 00:00:00',3,0x00),
 (89,52,51,'2014-09-01 00:00:00','2014-10-31 00:00:00',3,0x00),
 (90,17,5,'2014-07-23 00:00:00','2014-07-31 00:00:00',3,0x01),
 (91,52,53,'2014-07-30 00:00:00','2014-07-31 00:00:00',3,0x01);
/*!40000 ALTER TABLE `project_assignment` ENABLE KEYS */;


--
-- Definition of table `reasontype`
--

DROP TABLE IF EXISTS `reasontype`;
CREATE TABLE `reasontype` (
  `reason_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `reason_type_name` varchar(250) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`reason_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reasontype`
--

/*!40000 ALTER TABLE `reasontype` DISABLE KEYS */;
INSERT INTO `reasontype` (`reason_type_id`,`reason_type_name`,`description`,`isDeleted`) VALUES 
 (1,'Vacation',NULL,0x00),
 (2,'Unpaid Vacation',NULL,0x00),
 (3,'Late for work',NULL,0x00),
 (4,'Leave work early',NULL,0x00),
 (5,'Go out for private reason',NULL,0x00);
/*!40000 ALTER TABLE `reasontype` ENABLE KEYS */;


--
-- Definition of table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `statusId` int(11) NOT NULL AUTO_INCREMENT,
  `statusName` varchar(250) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL,
  PRIMARY KEY (`statusId`),
  KEY `statusName` (`statusName`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` (`statusId`,`statusName`,`description`,`groupId`) VALUES 
 (1,'Approved','Approved',1),
 (2,'Rejected','Rejected',1),
 (3,'Pending','Pending',1),
 (4,'Saved','Saved',1),
 (5,'Working','Working day',2),
 (6,'Public Holiday','Public Holiday',2),
 (7,'Paid Vacation','Paid Vacation',2),
 (8,'Non-Working','Non-Working',2),
 (9,'Not Project','Not Project Hours',2),
 (10,'Opening','Project is opening',3),
 (11,'Closed','Project is closed',3);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;


--
-- Definition of table `timesheet`
--

DROP TABLE IF EXISTS `timesheet`;
CREATE TABLE `timesheet` (
  `timesheetId` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `statusId` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `total_smallleave` double(11,2) DEFAULT NULL,
  `total_workinghours` double(11,2) DEFAULT '0.00',
  `total_overtimehours` double(11,2) DEFAULT '0.00',
  `total_midnighthours` double(11,2) DEFAULT '0.00',
  `total_workingdays` int(11) DEFAULT '0',
  `total_paidvocationdays` int(11) DEFAULT '0',
  `isDeleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`timesheetId`),
  KEY `userid_idx` (`user_id`),
  KEY `statusid_idx` (`statusId`),
  CONSTRAINT `statusid` FOREIGN KEY (`statusId`) REFERENCES `status` (`statusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userid` FOREIGN KEY (`user_id`) REFERENCES `useraccount` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timesheet`
--

/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` (`timesheetId`,`user_id`,`statusId`,`month`,`year`,`total_smallleave`,`total_workinghours`,`total_overtimehours`,`total_midnighthours`,`total_workingdays`,`total_paidvocationdays`,`isDeleted`) VALUES 
 (1,1,1,3,2014,NULL,60.00,10.00,NULL,NULL,NULL,0x00),
 (2,6,NULL,3,2014,NULL,8.00,4.50,373.50,1,0,0x00),
 (3,6,NULL,5,2014,NULL,21.05,2.45,0.00,3,0,0x00),
 (4,7,NULL,4,2014,NULL,NULL,NULL,NULL,1,0,0x00),
 (5,5,NULL,4,2014,NULL,0.00,0.00,0.00,1,0,0x00),
 (6,6,NULL,5,2013,NULL,1.00,0.00,NULL,NULL,NULL,0x00),
 (7,6,NULL,4,2014,NULL,54.57,11.00,331.22,12,1,0x00),
 (8,8,NULL,4,2014,NULL,80.00,3.65,0.00,8,5,0x00),
 (9,19,NULL,4,2014,NULL,0.00,0.00,0.00,0,0,0x00),
 (10,8,NULL,5,2014,NULL,69.64,4.30,0.00,9,0,0x00),
 (11,7,NULL,5,2014,NULL,0.00,0.00,0.00,0,0,0x00),
 (12,21,NULL,5,2014,NULL,0.07,0.00,0.00,1,0,0x00),
 (13,22,NULL,5,2014,NULL,6.07,0.00,0.00,2,0,0x00),
 (14,8,NULL,6,2014,NULL,0.00,0.00,0.00,0,0,0x00),
 (15,8,NULL,7,2014,NULL,24.00,0.52,0.00,2,2,0x00),
 (16,21,NULL,7,2014,NULL,0.00,0.00,0.00,0,0,0x00),
 (17,25,NULL,7,2014,NULL,174.72,4.49,0.00,20,4,0x00),
 (18,6,NULL,7,2014,NULL,0.00,0.00,0.00,0,0,0x00),
 (19,22,NULL,7,2014,NULL,0.00,0.00,0.00,0,0,0x00),
 (20,24,NULL,7,2014,NULL,0.00,0.00,0.00,0,0,0x00),
 (21,34,NULL,7,2014,NULL,0.00,0.00,0.00,0,0,0x00);
/*!40000 ALTER TABLE `timesheet` ENABLE KEYS */;


--
-- Definition of table `timesheet_detail`
--

DROP TABLE IF EXISTS `timesheet_detail`;
CREATE TABLE `timesheet_detail` (
  `timesheetdetailId` int(11) NOT NULL AUTO_INCREMENT,
  `timesheet_id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `workingdate` date DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `lunch` int(11) DEFAULT NULL,
  `totalworkinghour` double(11,2) DEFAULT NULL,
  `date_status` int(11) DEFAULT NULL,
  `small_leave` double(11,2) DEFAULT NULL,
  `leave_recovery` double(11,2) DEFAULT NULL,
  `regular_worktime` double(11,2) DEFAULT NULL,
  `regular_resttime` double(11,2) DEFAULT NULL,
  `overtime_work` double(11,2) DEFAULT NULL,
  `overtime_rest` double(11,2) DEFAULT NULL,
  `midnight_work` double(11,2) DEFAULT NULL,
  `midnight_rest` double(11,2) DEFAULT NULL,
  `overtime_approver` varchar(55) DEFAULT NULL,
  `not_charge_hour` double(11,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT b'0',
  PRIMARY KEY (`timesheetdetailId`),
  KEY `timesheet_id_idx` (`timesheet_id`),
  KEY `projectID_idx` (`project_id`),
  KEY `userIDRel_idx` (`userId`),
  KEY `approveStatus` (`status`),
  KEY `workingStatus` (`date_status`),
  KEY `overtimeApproveUser` (`overtime_approver`),
  CONSTRAINT `approveStatus` FOREIGN KEY (`status`) REFERENCES `status` (`statusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `projectID` FOREIGN KEY (`project_id`) REFERENCES `project` (`projectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `timesheetRel` FOREIGN KEY (`timesheet_id`) REFERENCES `timesheet` (`timesheetId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userIDRel` FOREIGN KEY (`userId`) REFERENCES `useraccount` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `workingStatus` FOREIGN KEY (`date_status`) REFERENCES `status` (`statusId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timesheet_detail`
--

/*!40000 ALTER TABLE `timesheet_detail` DISABLE KEYS */;
INSERT INTO `timesheet_detail` (`timesheetdetailId`,`timesheet_id`,`userId`,`project_id`,`workingdate`,`starttime`,`endtime`,`lunch`,`totalworkinghour`,`date_status`,`small_leave`,`leave_recovery`,`regular_worktime`,`regular_resttime`,`overtime_work`,`overtime_rest`,`midnight_work`,`midnight_rest`,`overtime_approver`,`not_charge_hour`,`remark`,`status`,`isDeleted`) VALUES 
 (4,2,6,1,'2014-03-31','2014-03-15 16:00:00','2014-03-31 18:00:00',0,NULL,5,1.00,NULL,8.00,1.00,4.50,0.00,373.50,0.00,'',0.00,'',2,0x00),
 (5,3,6,2,'2014-03-04','2014-04-01 01:00:00','2014-04-01 11:27:00',0,NULL,5,0.00,NULL,8.00,1.00,2.45,1.00,0.00,0.00,'',0.00,'',1,0x00),
 (6,3,6,1,'2014-03-01','2014-04-01 05:00:00','2014-04-01 13:46:00',1,NULL,5,741.73,NULL,7.77,1.00,0.00,0.00,0.00,0.00,'',0.00,'abc',2,0x00),
 (13,3,6,1,'2014-03-02','2014-04-02 09:41:00','2014-04-02 15:58:00',1,NULL,5,756.40,NULL,5.28,1.00,0.00,0.00,0.00,7.50,'',0.00,'test checkout 2nd',3,0x00),
 (14,4,7,2,'2014-04-02','2014-04-02 16:04:13',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,0x00),
 (17,5,5,1,'2014-04-02','2014-04-02 16:30:51','2014-04-02 16:31:23',NULL,NULL,5,NULL,NULL,0.00,0.00,0.00,0.00,0.00,0.00,NULL,NULL,'zzzz',3,0x00),
 (18,7,6,2,'2014-03-03','2014-04-03 16:09:00','2014-04-03 16:12:00',0,NULL,9,759.60,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',0.05,'asdsadas',3,0x00),
 (23,7,6,1,'2014-04-04','2014-04-04 00:00:00','2014-04-04 00:00:00',0,NULL,9,0.00,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (24,8,8,3,'2014-04-07','2014-04-07 11:19:00','2014-04-07 19:30:00',1,NULL,9,3.63,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',7.18,'',3,0x00),
 (25,7,6,1,'2014-04-07','2014-04-07 08:30:00','2014-04-07 19:30:00',1,NULL,5,0.00,NULL,8.00,0.00,2.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (26,8,8,3,'2014-04-16','2014-04-16 10:11:00','2014-04-16 19:57:00',1,NULL,9,0.00,NULL,0.00,0.00,0.77,0.00,0.00,0.00,'',8.00,'',3,0x00),
 (27,7,6,2,'2014-04-16','2014-04-02 00:00:00','2014-04-16 08:30:00',1,NULL,5,0.00,NULL,8.00,0.00,4.50,0.00,331.00,0.00,'',0.00,'',3,0x00),
 (43,7,6,1,'2014-04-01','2014-04-16 05:22:00','2014-04-16 19:05:00',1,NULL,5,356.87,NULL,8.00,0.00,4.50,0.00,0.22,0.00,'',0.00,'',3,0x00),
 (45,7,6,2,'2014-04-02','2014-04-02 00:00:00','2014-04-02 00:00:00',0,NULL,5,0.00,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (47,8,8,3,'2014-04-17','2014-04-17 09:44:00','2014-04-17 19:12:00',1,NULL,5,0.00,NULL,8.00,0.00,0.47,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (48,8,8,3,'2014-04-15','2014-04-15 08:30:00','2014-04-15 17:30:00',1,NULL,7,0.00,NULL,8.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (49,7,6,2,'2014-04-17','2014-04-17 10:20:00','2014-04-17 19:13:00',1,NULL,5,1.95,NULL,7.88,0.00,0.00,0.00,0.00,0.00,'',0.00,'work Attendance',3,0x00),
 (50,8,8,3,'2014-04-14','2014-04-14 08:30:00','2014-04-14 17:30:00',1,NULL,7,0.00,NULL,8.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (51,8,8,3,'2014-04-13','2014-04-13 08:30:00','2014-04-13 17:30:00',1,NULL,7,0.00,NULL,8.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (52,8,8,3,'2014-04-12','2014-04-12 08:30:00','2014-04-12 17:30:00',1,NULL,7,0.00,NULL,8.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (53,8,8,3,'2014-04-11','2014-04-11 09:25:00','2014-04-11 18:37:00',1,NULL,7,0.00,NULL,8.00,0.00,0.20,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (54,8,8,3,'2014-04-10','2014-04-10 08:30:00','2014-04-10 17:30:00',1,NULL,9,0.00,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'Sato-san',8.00,'dont have project',3,0x00),
 (55,8,8,3,'2014-04-18','2014-04-18 08:51:00','2014-04-18 08:30:00',0,NULL,5,0.35,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (56,7,6,2,'2014-04-18','2014-04-18 09:14:00','2014-04-18 08:30:00',0,NULL,5,0.73,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (57,8,8,3,'2014-04-21','2014-04-21 10:40:00','2014-04-21 08:30:00',0,NULL,5,2.17,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (58,7,6,2,'2014-04-21','2014-04-21 10:40:00','2014-04-21 18:38:00',1,NULL,5,3.20,NULL,6.97,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (59,8,8,3,'2014-04-22','2014-04-22 09:18:00','2014-04-22 19:00:00',1,NULL,5,0.00,NULL,8.00,0.00,0.70,0.00,0.00,0.00,'Satoyoshi-san',0.00,'',3,0x00),
 (60,7,6,2,'2014-04-22','2014-04-22 09:19:00','2014-04-22 08:30:00',0,NULL,5,0.82,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (61,8,8,3,'2014-04-23','2014-04-23 09:43:00','2014-04-23 18:45:00',1,NULL,5,0.00,NULL,8.00,0.00,0.03,0.00,0.00,0.00,'',0.00,'finish Add Empl form',3,0x00),
 (62,7,6,2,'2014-04-23','2014-04-23 09:50:00','2014-04-23 18:50:00',1,NULL,5,0.00,NULL,8.00,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (63,7,6,2,'2014-04-15',NULL,NULL,NULL,NULL,7,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (64,8,8,3,'2014-04-24','2014-04-24 08:46:00','2014-04-24 19:00:00',1,NULL,5,0.00,NULL,8.00,0.00,1.23,0.00,0.00,0.00,'',0.00,'make edit employee form',3,0x00),
 (65,7,6,2,'2014-04-24','2014-04-24 09:17:00','2014-04-24 18:00:00',1,NULL,5,1.07,NULL,7.72,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (66,8,8,3,'2014-04-25','2014-04-25 09:45:00','2014-04-25 19:00:00',1,NULL,5,0.00,NULL,8.00,0.00,0.25,0.00,0.00,0.00,'',0.00,'continue edit employee',3,0x00),
 (67,7,6,2,'2014-04-25','2014-04-25 09:47:00',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'',3,0x00),
 (68,8,8,3,'2014-04-28','2014-04-28 08:51:00',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'',3,0x00),
 (69,7,6,2,'2014-04-29','2014-04-29 08:35:00',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'',3,0x00),
 (70,10,8,3,'2014-05-05','2014-05-05 10:45:00','2014-05-05 18:25:00',1,NULL,5,3.58,NULL,6.67,0.00,0.00,0.00,0.00,0.00,'',0.00,'Update AS follow Satosan\'s requirement',3,0x00),
 (71,12,21,NULL,'2014-05-05','2014-05-05 15:06:44','2014-05-05 15:10:56',0,NULL,5,14.54,NULL,0.07,0.00,0.00,0.00,0.00,0.00,NULL,0.00,'small leave',3,0x00),
 (72,10,8,3,'2014-05-06','2014-05-06 08:31:00','2014-05-06 18:00:00',1,NULL,5,0.00,NULL,8.00,0.00,0.48,0.00,0.00,0.00,'',0.00,'Update AS\'s screen',3,0x00),
 (73,11,7,1,'2014-05-06','2014-05-06 14:09:31',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,0x00),
 (74,13,22,NULL,'2014-05-06','2014-05-06 14:11:00','2014-05-06 19:00:00',0,NULL,5,8.87,NULL,4.82,0.00,0.00,0.00,0.00,0.00,'',0.00,'test update timesheet',3,0x00),
 (75,10,8,3,'2014-05-07','2014-05-07 08:56:00','2014-05-07 17:53:00',1,NULL,5,0.48,NULL,7.95,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (76,13,22,NULL,'2014-05-07','2014-05-07 17:47:00','2014-05-07 19:02:00',0,NULL,5,16.03,NULL,1.25,0.00,0.00,0.00,0.00,0.00,'',0.00,'check out many times',3,0x00),
 (77,10,8,3,'2014-05-08','2014-05-08 10:00:00','2014-05-08 19:05:00',1,NULL,5,0.00,NULL,8.00,0.00,0.08,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (78,13,22,NULL,'2014-05-08','2014-05-08 10:01:43',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,0x00),
 (79,12,21,NULL,'2014-05-08','2014-05-08 19:40:06',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,0x00),
 (80,10,8,3,'2014-05-09','2014-05-09 08:53:00','2014-05-09 18:24:00',1,NULL,5,0.00,NULL,8.00,0.00,0.52,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (81,10,8,3,'2014-05-12','2014-05-12 09:00:00','2014-05-12 18:30:00',1,NULL,5,0.00,NULL,8.00,0.00,0.50,0.00,0.00,0.00,'',0.00,'overview pdf foundation project',3,0x00),
 (82,10,8,3,'2014-05-13','2014-05-13 10:01:00','2014-05-13 19:49:00',1,NULL,5,0.00,NULL,8.00,0.00,0.80,0.00,0.00,0.00,'',0.00,'make reading config file class',3,0x00),
 (83,10,8,4,'2014-05-14','2014-05-14 10:34:00','2014-05-14 18:35:00',1,NULL,5,3.05,NULL,7.02,0.00,0.00,0.00,0.00,0.00,'',0.00,'implement 1st class',3,0x00),
 (84,10,8,4,'2014-05-15','2014-05-15 09:35:00','2014-05-15 20:30:00',1,NULL,5,0.00,NULL,8.00,0.00,1.92,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (85,15,8,3,'2014-07-01','2014-07-01 08:45:00','2014-07-01 18:16:00',1,NULL,5,0.00,NULL,8.00,0.00,0.52,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (86,17,25,NULL,'2014-07-01','2014-07-01 16:53:00','2014-07-01 18:00:00',0,NULL,5,15.27,NULL,1.12,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (87,17,25,NULL,'2014-07-02','2014-07-02 08:27:00','2014-07-02 18:11:00',1,NULL,5,0.00,NULL,8.00,0.50,0.73,0.50,0.00,0.00,'',0.00,'N/A',3,0x00),
 (88,15,8,3,'2014-07-02','2014-07-02 08:43:00',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'',3,0x00),
 (91,15,8,3,'2014-05-31',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (92,15,8,3,'2014-05-16',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (93,15,8,3,'2014-05-19',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (94,15,8,3,'2014-05-01',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (95,15,8,3,'2014-05-25',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (96,15,8,3,'2014-05-04',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (97,15,8,3,'2014-05-03',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (98,15,8,3,'2014-05-30',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (99,15,8,3,'2014-05-27',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (100,15,8,3,'2014-05-29',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (101,15,8,3,'2014-05-17',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (102,15,8,3,'2014-05-20',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (103,15,8,3,'2014-05-10',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (104,15,8,3,'2014-05-22',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (105,15,8,3,'2014-05-02',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (106,15,8,3,'2014-05-21',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (107,15,8,3,'2014-05-18',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (108,15,8,3,'2014-05-26',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (109,15,8,3,'2014-05-28',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (110,15,8,3,'2014-05-11',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (111,15,8,3,'2014-05-23',NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (112,15,8,3,'2014-05-24',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (113,18,6,2,'2014-07-02','2014-07-02 16:48:00','2014-07-02 17:04:00',0,NULL,9,40.03,NULL,0.00,8.00,0.00,8.00,0.00,8.00,'tom',0.27,'',3,0x00),
 (114,17,25,NULL,'2014-07-03','2014-07-03 09:29:00','2014-07-03 18:00:00',1,NULL,5,1.97,NULL,7.52,0.50,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (115,17,25,NULL,'2014-07-04','2014-07-04 08:42:00','2014-07-04 16:00:00',1,NULL,5,1.90,NULL,6.30,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (116,19,22,3,'2014-07-04','2014-07-04 11:27:23',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,0x00),
 (117,17,25,NULL,'2014-07-07','2014-07-07 08:54:00','2014-07-07 18:00:00',1,NULL,5,0.00,NULL,8.00,0.00,0.10,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (118,17,25,NULL,'2014-07-08','2014-07-08 08:30:00','2014-07-08 18:00:00',1,NULL,5,0.00,NULL,8.00,0.00,0.50,0.50,0.00,0.00,'',0.00,'',3,0x00),
 (119,19,22,3,'2014-07-08','2014-07-08 16:59:29',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,0x00),
 (120,17,25,NULL,'2014-07-09','2014-07-09 08:36:00','2014-07-09 18:00:00',1,NULL,5,0.00,NULL,8.00,0.00,0.40,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (121,17,25,NULL,'2014-07-10','2014-07-10 08:42:00','2014-07-10 18:30:00',1,NULL,5,0.00,NULL,8.00,0.00,0.80,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (122,17,25,NULL,'2014-07-11','2014-07-11 08:37:00','2014-07-11 17:53:00',1,NULL,5,0.00,NULL,8.00,1.00,0.27,0.00,0.00,0.00,'',0.00,'N/A',3,0x00),
 (123,17,25,17,'2014-07-14','2014-07-14 08:34:00','2014-07-14 18:30:00',1,NULL,5,0.00,NULL,8.00,0.00,0.93,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (124,17,25,17,'2014-07-15','2014-07-15 09:20:00','2014-07-15 17:38:00',1,NULL,5,1.53,NULL,7.30,0.00,0.00,0.00,0.00,0.00,'',0.00,'N/A',3,0x00),
 (125,20,24,NULL,'2014-07-15','2014-07-15 15:15:30',NULL,NULL,NULL,9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,0x00),
 (126,17,25,17,'2014-07-16','2014-07-16 08:34:00','2014-07-16 18:00:00',1,NULL,5,0.00,NULL,8.00,0.50,0.43,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (127,17,25,36,'2014-07-17','2014-07-17 08:18:00','2014-07-17 17:00:00',1,NULL,5,0.80,NULL,7.70,0.50,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (128,17,25,36,'2014-07-18','2014-07-18 10:37:00','2014-07-18 17:03:00',1,NULL,5,5.18,NULL,5.43,0.50,0.00,0.00,0.00,0.00,'',0.00,'N/A',3,0x00),
 (129,17,25,36,'2014-07-21','2014-07-21 08:36:00','2014-07-21 17:32:00',1,NULL,5,0.67,NULL,7.93,0.50,0.00,0.00,0.00,0.00,'',0.00,'N/A',3,0x00),
 (130,17,25,46,'2014-07-22','2014-07-22 08:34:00','2014-07-22 17:00:00',1,NULL,5,0.63,NULL,7.43,0.00,0.00,0.00,0.00,0.00,'',0.00,'N/A',3,0x00),
 (131,17,25,46,'2014-07-20',NULL,NULL,NULL,NULL,7,NULL,NULL,8.00,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (132,17,25,46,'2014-07-19',NULL,NULL,NULL,NULL,7,NULL,NULL,8.00,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (133,17,25,46,'2014-07-23','2014-07-23 08:34:00','2014-07-23 16:19:00',1,NULL,5,8.82,NULL,6.75,7.50,0.00,0.00,0.00,0.00,'',0.00,'N/A',3,0x00),
 (134,17,25,46,'2014-07-13',NULL,NULL,NULL,NULL,7,NULL,NULL,8.00,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (135,17,25,46,'2014-07-12',NULL,NULL,NULL,NULL,7,NULL,NULL,8.00,0.00,NULL,0.00,NULL,0.00,'',NULL,'',3,0x00),
 (136,17,25,46,'2014-07-24','2014-07-24 09:25:00','2014-07-24 16:35:00',1,NULL,5,2.75,NULL,6.17,0.00,0.00,0.00,0.00,0.00,'',0.00,'N/A',3,0x00),
 (137,15,8,3,'2014-07-24','2014-07-24 10:01:45',NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,0x00),
 (138,17,25,46,'2014-07-25','2014-07-25 08:40:00','2014-07-25 18:00:00',1,NULL,5,0.00,NULL,8.00,0.00,0.33,0.00,0.00,0.00,'',0.00,'N/A',3,0x00),
 (139,17,25,46,'2014-07-28','2014-07-28 08:45:00','2014-07-28 16:49:00',1,NULL,5,1.18,NULL,7.07,0.00,0.00,0.00,0.00,0.00,'',0.00,'',3,0x00),
 (140,21,34,52,'2014-07-28','2014-07-28 13:07:00','2014-07-28 13:09:00',0,NULL,9,12.58,NULL,0.00,0.00,0.00,0.00,0.00,0.00,'',0.03,'',3,0x00);
/*!40000 ALTER TABLE `timesheet_detail` ENABLE KEYS */;


--
-- Definition of trigger `calculateTimeSheetTrigger`
--

DROP TRIGGER /*!50030 IF EXISTS */ `calculateTimeSheetTrigger`;

DELIMITER $$

CREATE DEFINER = `root`@`%` TRIGGER `calculateTimeSheetTrigger` AFTER UPDATE ON `timesheet_detail` FOR EACH ROW UPDATE timesheet 

SET total_workinghours = (SELECT SUM(regular_worktime) FROM timesheet_detail WHERE timesheet_id = NEW.timesheet_id),

 total_overtimehours = (SELECT SUM(overtime_work) FROM timesheet_detail WHERE timesheet_id = NEW.timesheet_id),

 total_midnighthours = (SELECT SUM(midnight_work) FROM timesheet_detail WHERE timesheet_id = NEW.timesheet_id),

 total_workingdays = (SELECT COUNT(*) FROM timesheet_detail WHERE timesheet_id = NEW.timesheet_id AND date_status = 5),

 total_paidvocationdays = (SELECT COUNT(*) FROM timesheet_detail WHERE timesheet_id = NEW.timesheet_id AND date_status = 7)

WHERE timesheetId = NEW.timesheet_id $$

DELIMITER ;

--
-- Definition of table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
CREATE TABLE `useraccount` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `usercode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  `fullname` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `job_title` varchar(55) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phonenumber` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idcard_number` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idcard_issuedate` datetime DEFAULT NULL,
  `idcard_issuedby` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `taxcode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dependant` int(11) DEFAULT NULL,
  `socialInsurance` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `grade` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contractType_id` int(11) NOT NULL,
  `startDate` datetime NOT NULL,
  `probation` int(11) DEFAULT NULL,
  `salary` int(9) DEFAULT NULL,
  `bank` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bank_branch` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bank_accountname` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bank_accountnumber` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `roleId` int(11) NOT NULL,
  `frontside_img` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mac_address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` bit(1) DEFAULT b'1',
  PRIMARY KEY (`UserId`),
  KEY `department_id` (`department_id`),
  KEY `roleId` (`roleId`),
  KEY `contractType_id` (`contractType_id`),
  CONSTRAINT `useraccount_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `authorities` (`roleId`),
  CONSTRAINT `useraccount_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`departmentId`),
  CONSTRAINT `useraccount_ibfk_3` FOREIGN KEY (`contractType_id`) REFERENCES `contract_type` (`contract_typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `useraccount`
--

/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` (`UserId`,`usercode`,`username`,`password`,`email`,`active`,`fullname`,`job_title`,`birthday`,`address`,`phonenumber`,`idcard_number`,`idcard_issuedate`,`idcard_issuedby`,`taxcode`,`dependant`,`socialInsurance`,`department_id`,`grade`,`contractType_id`,`startDate`,`probation`,`salary`,`bank`,`bank_branch`,`bank_accountname`,`bank_accountnumber`,`roleId`,`frontside_img`,`mac_address`,`gender`) VALUES 
 (1,NULL,'Thi-san','password',NULL,0x00,'Nguyen Truong Thi',NULL,NULL,'6 Ngo Thoi Nhiem Street, Dist 12, Khanh Hoa provin','554555','2342341123',NULL,'manager',NULL,NULL,NULL,NULL,NULL,1,'2014-01-01 00:00:00',NULL,22000,NULL,NULL,NULL,NULL,1,NULL,NULL,0x01),
 (2,NULL,'Guillaume-san','password',NULL,0x00,'Guillaume',NULL,NULL,'6 Dinh Phong Phu Street, Dist Thu Duc, Nha Trang p','222111','2342211232',NULL,'manager',NULL,NULL,NULL,NULL,NULL,1,'2014-01-01 00:00:00',NULL,480000,NULL,NULL,NULL,NULL,2,NULL,NULL,0x01),
 (3,NULL,'Thanh-san','password',NULL,0x00,'Le Phuoc Thanh',NULL,NULL,'3 LeHong Phong Street, Dist 10, HCM City','421232','4444523232',NULL,'manager',NULL,NULL,NULL,NULL,NULL,1,'2014-01-01 00:00:00',NULL,65000,NULL,NULL,NULL,NULL,3,NULL,NULL,0x01),
 (4,NULL,'Dinh-san','password','8',0x00,'Nguyen Thi Dinh',NULL,'2014-03-04 00:00:00','21 Ly Chinh Thang, Dist 3, HCM City','234323','5344241232','2014-03-20 00:00:00','8','8',8,'8',2,'1',2,'2014-03-04 00:00:00',8,2000,'88','8','8','8',2,NULL,NULL,0x01),
 (5,'565656','kien','202cb962ac59075b964b07152d234b70','kienlt@gmail.com',0x01,'Lưu Trí Kiên','Senior Developer','2014-03-05 00:00:00','123 Truong Dinh Street, Dist 3, HCM City','234234','2342341232','2014-03-25 00:00:00','Ho Chi Minh','011123423',3,'012345678',1,'1',1,'2014-03-12 00:00:00',3,11000000,'Vietcombank','Quan 3','LUU TRI KIEN','007100112233',1,'Desert.jpg','',0x01),
 (6,'123456','Test User','202cb962ac59075b964b07152d234b70','test@yahoo.com',0x01,'Test User','Programmer','2014-03-03 00:00:00','113 Lý Chính Thắng','0989777556','0143445667','2014-03-02 00:00:00','Can Tho','0204335565',3,'012345678',3,'1',19,'2014-03-02 00:00:00',3,9000000,'vietcombank','Quan 1','TEST USER','0071004056789',1,'Lighthouse.jpg','28-D2-44-62-68-89',0x01),
 (7,'231236','satosan','202cb962ac59075b964b07152d234b70','sato@yaho.com',0x01,'Yoshihito Satoyoshi','General Director','2014-03-02 00:00:00','123 Nguyễn Thị Minh Khai Q.1','0903111222','023112233','2014-03-04 00:00:00','Tokyo - Japan','01234567',3,'012345678',2,'4',1,'2014-03-03 00:00:00',3,100000000,'vietcombank','Quận 1','YOSHIHITO SATOYOSHI','0071004011223',1,'Hydrangeas.jpg',NULL,0x01),
 (8,'252124','clngoc','202cb962ac59075b964b07152d234b70','clngoc@yahoo.com',0x01,'Cao Lê Ngọc','Project Manager','1982-06-07 00:00:00','8 Lo D c/cu Nguyễn Thiện Thuật F1 Q3','0903708904','023664056','1999-07-13 00:00:00','HCM','012345678',2,'123456789',1,'3',1,'2013-10-20 00:00:00',2,50000000,'Vietcombank','vietcombank','CAO LE NGOC','007123460000',1,'Koala.jpg','20-89-84-93-63-28',0x01),
 (17,'111222','tthoa','202cb962ac59075b964b07152d234b70','tthoa@yahoo.com',0x01,'Trần Thị Hoa','Senior Developer','1984-04-01 00:00:00','111 Lý Chính Thắng F5 Q3','0909123456','012345678','1999-04-01 00:00:00','Ho Chi Minh','012345678',2,'012345678',1,'1',16,'2013-10-01 00:00:00',2,10000000,'VietcomBank','CN Quan 1','TRAN THI HOA','00711122000099',3,'Lighthouse.jpg','',0x00),
 (18,'444555','lphu','202cb962ac59075b964b07152d234b70','phule@rocketmail.com',0x01,'Lê Phú','Analysis Project','1980-04-01 00:00:00','100 Nguyễn Thị Nhỏ Q. Tân Bình','0903123456','023112233','1999-04-01 00:00:00','Ho Chi Minh','00123456',2,'0123456789',1,'2',1,'2013-10-20 00:00:00',2,1000000,'Vietcombank','Quan 9','LE PHU','0071004077889',3,'Penguins.jpg',NULL,0x01),
 (19,'333444','nvcanh','202cb962ac59075b964b07152d234b70','canh@gmail.com',0x01,'Nguyễn Văn Cảnh','Senior Developer','1984-04-18 00:00:00','1 Nguyễn Thái Bình Q.1','0903123456','023112233','2014-04-01 00:00:00','Ho Chi Minh','012345678',2,'012345678',3,'1',1,'2014-04-01 00:00:00',2,10000000,'vietcombank','quan 1','NGUYEN VAN CANH','0071004100000',3,'Jellyfish.jpg','',0x01),
 (20,'913832','nhviet','202cb962ac59075b964b07152d234b70','viet@yahoo.com',0x01,'Nguyen Hoang Viet','Senior Developer','2014-04-01 00:00:00','100 Hoang Van Thu','0902345678','023112233','2014-04-01 00:00:00','Ho Chi Minh','012345678',2,'012345678',1,'1',18,'2014-04-01 00:00:00',2,18000000,'VietcomBank','Quan 1','NGUYEN HOANG VIET','71001100000',3,'Penguins.jpg','',0x01),
 (21,'913859','furukisan','202cb962ac59075b964b07152d234b70','furuki@yahoo.com',0x01,'Naoto Furuki','Deputy Director','2014-05-01 00:00:00','111 Phạm Hồng Thái Q1','0903123456','0123456','2014-05-01 00:00:00','Tokyo','0123456',1,'012345678',2,'4',1,'2013-10-01 00:00:00',2,90000000,'Vietcombank','Quan 1','NAOTO FURUKI','0070015077878',1,'116.jpg','00-23-54-6C-92-09',0x01),
 (22,'913963','staff','202cb962ac59075b964b07152d234b70','staff@yahoo.com',0x01,'Staff for Test','Accountant','2014-05-01 00:00:00','1 Nguyễn Thị Minh Khai Q3','0909123456','023112233','2014-05-01 00:00:00','Ha Noi','0123456',1,'0123456789',3,'2',1,'2014-05-01 00:00:00',2,8000000,'Vietcombank','Quan 3','STAFF TEST','0070012344565',1,'Desert.jpg','',0x00),
 (23,'555664','manager','202cb962ac59075b964b07152d234b70','manager@gmail.com',0x01,'Manager for Test','Project Manager','2014-05-01 00:00:00','2 Nam Kỳ Khởi Nghĩa Q3','0908123456','023445566','2014-05-01 00:00:00','Hai Phong','0123456',0,'0123456789',1,'2',1,'2014-05-01 00:00:00',2,10000000,'Vietcombank','Quan 1','MANAGER TEST','0071000433454',2,'IMG_0986.jpg','',0x01),
 (24,'913967','htnthao','202cb962ac59075b964b07152d234b70','htnthao@gmail.com',0x01,'Huỳnh Thị Ngọc Thảo','Accountant','1982-07-01 00:00:00','111 Nguyễn Văn Trỗi Q.PN','0903123456','023445566','1999-07-01 00:00:00','HCM','',2,'',3,'2',1,'2014-03-01 00:00:00',2,10000000,'Vietcombank','Quan 1','HUYNH THI NGOC THAO','00701234567',1,'thaosan.jpg','20-89-84-F9-EF-F1',0x00),
 (25,'121212','ldkanh','202cb962ac59075b964b07152d234b70','ldkanh@rocketmail.com',0x01,'Lê Đặng Kim Anh','Consulting','1988-07-01 00:00:00','10 Hồ Biểu Chánh','0909123456','012334455','1999-07-02 00:00:00','HCM','012345678',2,'012345678',3,'2',18,'2014-06-01 00:00:00',2,10000000,'Vietcombank','Quan 3','LE DANG KIM ANH','00712345677',1,'Penguins.jpg','F0-DE-F1-26-43-DB',0x00),
 (27,'343434','Mondaytest','81dc9bdb52d04dc20036dbd8313ed055','123@gmail.com',0x01,'Mondaytest','Consulting','1985-07-01 00:00:00','103 Dinh Tien Hoang','12345678910','654321','2009-07-21 00:00:00','HCM','12123535',2,'9876543210',2,'2',19,'2014-03-21 00:00:00',2,10000000,'Vietcombank','quan 1','Monday test','1122334455',3,'Penguins.jpg','',0x00),
 (34,'343434','Tester','202cb962ac59075b964b07152d234b70','',0x01,'Tester','','1992-11-08 00:00:00','1992-11-08 00:00:00','4567893218','250904098','2010-09-07 00:00:00','','',0,'',1,'1',19,'2014-03-01 00:00:00',4,NULL,'','quan 10','','',3,'Hydrangeas.jpg','F0-DE-F1-26-43-DB',0x00),
 (42,'121212','Tester','202cb962ac59075b964b07152d234b70','',0x01,'456','',NULL,'','','',NULL,'','',NULL,'',17,'4',16,'2014-07-09 00:00:00',NULL,NULL,'','','','',2,'',NULL,0x01),
 (43,'999999','long','202cb962ac59075b964b07152d234b70','long@gmail.com',0x01,'Long','IT','1992-11-08 00:00:00','123 Nguyen Van Troi','4567893218','250908795','2010-09-07 00:00:00','','',2,'',3,'2',18,'2014-03-01 00:00:00',2,NULL,'','','','',1,'','',0x01),
 (48,'567890','AAA','202cb962ac59075b964b07152d234b70','',0x01,'AAAA','','2014-07-01 00:00:00','','98654712358','',NULL,'','',NULL,'',3,'1',18,'2014-07-01 00:00:00',1,NULL,'','','','',3,'','',0x01),
 (51,'123654','Testt','202cb962ac59075b964b07152d234b70','',0x01,'Testt','','1996-07-01 00:00:00','','','',NULL,'','',NULL,'',3,'4',18,'2014-07-10 00:00:00',2,NULL,'','','','',2,'','',0x01),
 (53,'875325','ptkyen','202cb962ac59075b964b07152d234b70','',0x01,'Phạm Thị Kim Yến','','1988-07-21 00:00:00','','','',NULL,'','',NULL,'',1,'2',1,'2014-07-01 00:00:00',NULL,NULL,'','','','',3,'','',0x00);
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
