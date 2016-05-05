-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table ` branches`
--

DROP TABLE IF EXISTS ` branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ` branches` (
  `bid` int(11) NOT NULL,
  ` Address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table ` branches`
--

LOCK TABLES ` branches` WRITE;
/*!40000 ALTER TABLE ` branches` DISABLE KEYS */;
INSERT INTO ` branches` VALUES (11,'Haifa'),(22,'Tel Aviv'),(33,'Ramat Gan'),(44,'Beer - Sheva');
/*!40000 ALTER TABLE ` branches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table ` clients history`
--

DROP TABLE IF EXISTS ` clients history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ` clients history` (
  `cid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`cid`,`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table ` clients history`
--

LOCK TABLES ` clients history` WRITE;
/*!40000 ALTER TABLE ` clients history` DISABLE KEYS */;
INSERT INTO ` clients history` VALUES (200940997,1001,'2015-10-20'),(200940997,1002,'2015-10-11'),(302632195,1001,'2016-01-01'),(302632195,1002,'2015-07-02'),(302632195,1003,'2015-09-06'),(305003659,1003,'2015-05-10'),(305003659,1004,'2016-02-01'),(312143969,1002,'2016-03-05'),(312143969,1004,'2015-11-11');
/*!40000 ALTER TABLE ` clients history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointments to client`
--

DROP TABLE IF EXISTS `appointments to client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments to client` (
  `cid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  PRIMARY KEY (`cid`,`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments to client`
--

LOCK TABLES `appointments to client` WRITE;
/*!40000 ALTER TABLE `appointments to client` DISABLE KEYS */;
INSERT INTO `appointments to client` VALUES (302632195,11,'2016-08-01','10:00:00'),(302632195,22,'2016-08-02','09:00:00'),(305003659,11,'2016-08-03','10:00:00'),(305003659,22,'2016-08-04','11:00:00'),(305003659,44,'2016-08-05','09:30:00'),(312143969,22,'2016-08-02','10:00:00'),(312143969,44,'2016-08-05','11:00:00');
/*!40000 ALTER TABLE `appointments to client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `available appointments`
--

DROP TABLE IF EXISTS `available appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `available appointments` (
  `sid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  PRIMARY KEY (`sid`,`bid`,`Time`,`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `available appointments`
--

LOCK TABLES `available appointments` WRITE;
/*!40000 ALTER TABLE `available appointments` DISABLE KEYS */;
INSERT INTO `available appointments` VALUES (1001,11,'2016-08-06','10:00:00'),(1001,11,'2016-08-06','10:30:00'),(1001,11,'2016-08-06','11:00:00'),(1002,22,'2016-08-06','10:00:00'),(1002,22,'2016-08-07','10:00:00'),(1002,22,'2016-08-07','17:30:00'),(1004,44,'2016-08-07','12:00:00');
/*!40000 ALTER TABLE `available appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examination result`
--

DROP TABLE IF EXISTS `examination result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examination result` (
  `Examination Code` int(11) NOT NULL,
  `Details` varchar(45) DEFAULT NULL,
  `Picture` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Examination Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examination result`
--

LOCK TABLES `examination result` WRITE;
/*!40000 ALTER TABLE `examination result` DISABLE KEYS */;
INSERT INTO `examination result` VALUES (90000,'catheterization, positive','attached'),(90001,'Scoliosis , There is severe Scoliosis.','attached'),(90002,'Upper Respiratory Tract Infection , he has it','not attached');
/*!40000 ALTER TABLE `examination result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examination type`
--

DROP TABLE IF EXISTS `examination type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examination type` (
  `Examination code` int(11) NOT NULL,
  `Examination name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Examination code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examination type`
--

LOCK TABLES `examination type` WRITE;
/*!40000 ALTER TABLE `examination type` DISABLE KEYS */;
INSERT INTO `examination type` VALUES (2001,'Electrocardiography'),(2002,'physiotherapy'),(2003,'Papilloma'),(2004,'sinusitis'),(2005,'Hearing test'),(2006,'orthodontics'),(2007,'psoriasis'),(2008,'Tova');
/*!40000 ALTER TABLE `examination type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical field`
--

DROP TABLE IF EXISTS `medical field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical field` (
  `Medical field` varchar(45) NOT NULL,
  PRIMARY KEY (`Medical field`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical field`
--

LOCK TABLES `medical field` WRITE;
/*!40000 ALTER TABLE `medical field` DISABLE KEYS */;
INSERT INTO `medical field` VALUES ('Audiology'),('Cardioligy'),('Dentist'),('Dermatology'),('Gynecology'),('Neurology'),('Othopedy'),('Otorhinolaryngolgy');
/*!40000 ALTER TABLE `medical field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physician`
--

DROP TABLE IF EXISTS `physician`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `physician` (
  `pscName` varchar(45) NOT NULL,
  `pscSpecialization` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pscName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='test table for prototype';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician`
--

LOCK TABLES `physician` WRITE;
/*!40000 ALTER TABLE `physician` DISABLE KEYS */;
INSERT INTO `physician` VALUES ('Shay Shahar','la'),('Yakir Karandian','Gynecology');
/*!40000 ALTER TABLE `physician` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reference details`
--

DROP TABLE IF EXISTS `reference details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference details` (
  `cid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `Examination Code` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Urgency` varchar(45) DEFAULT NULL,
  `Comments` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cid`,`sid`,`Examination Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference details`
--

LOCK TABLES `reference details` WRITE;
/*!40000 ALTER TABLE `reference details` DISABLE KEYS */;
INSERT INTO `reference details` VALUES (200940997,1001,90002,'2016-07-03','High','He bearly breath'),(302632195,1004,90000,'2016-07-01','Critical','first priority'),(305003659,1002,90001,'2016-07-02','Medium','None');
/*!40000 ALTER TABLE `reference details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialists`
--

DROP TABLE IF EXISTS `specialists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialists` (
  `wid` int(11) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Speciality` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialists`
--

LOCK TABLES `specialists` WRITE;
/*!40000 ALTER TABLE `specialists` DISABLE KEYS */;
INSERT INTO `specialists` VALUES (1001,'Dr. Moshe Moshe','Otorhinolaryngolgy'),(1002,'Dr. Mark Markuson','Orthopedy'),(1003,'Dr. Yakir Kandian','Gynecology'),(1004,'Dr. Yoram David','Cardioligy');
/*!40000 ALTER TABLE `specialists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserName` varchar(20) NOT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `Privilege` varchar(20) DEFAULT NULL,
  `First Name` varchar(20) DEFAULT NULL,
  `Family Name` varchar(20) DEFAULT NULL,
  `wid` int(11) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Branch` int(11) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('eliElyahu','mylab','Lab Worker','Eli','Cohen',1007,'eli@ihealth.org',0,0),('gilGl','123Gil','General Manager','Gil','Galili',1012,'gil@ihealth.org',0,0),('Kandian8','5431','Specialist','Yakir','Kandian',1003,'yakir@ihealth.org',33,0),('MarkMarkuzon1','4321','Specialist','Mark','Markuzon',1002,'mark@ihealth.org',22,0),('mosheMoshe','1234','Specialist','Moshe','Moshe',1001,'moshe@ihealth.org',11,0),('rona','ronron','Branch Manager','Ron','Aviv',1008,'ron@ihealth.org',11,0),('yoram910','652798s','Specialist','Yoram','David',1004,'yoram@ihealth.org',44,0),('yossi0123','0123yos','Dispatcher','Yossi','Yosef',1005,'yossi@ihealth.org',0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-05 20:35:57
