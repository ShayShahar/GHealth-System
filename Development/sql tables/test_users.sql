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

-- Dump completed on 2016-05-02 16:36:51
