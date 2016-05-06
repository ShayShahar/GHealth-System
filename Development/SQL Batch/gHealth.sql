-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: ghealth
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branches` (
  `branchName` varchar(45) NOT NULL,
  `branchAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`branchName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branches`
--

LOCK TABLES `branches` WRITE;
/*!40000 ALTER TABLE `branches` DISABLE KEYS */;
INSERT INTO `branches` VALUES ('IHealth 1','Haifa'),('IHealth 2','Tel Aviv'),('IHealth 3','Jerusalem'),('Mini IHealth','Ramat - Gan');
/*!40000 ALTER TABLE `branches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `clientID` int(11) NOT NULL AUTO_INCREMENT,
  `person` varchar(9) NOT NULL,
  `clientClinic` varchar(45) NOT NULL,
  PRIMARY KEY (`clientID`),
  KEY `personID_idx` (`person`),
  CONSTRAINT `person` FOREIGN KEY (`person`) REFERENCES `person` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `personID` varchar(9) NOT NULL,
  `personName` varchar(45) DEFAULT NULL,
  `personFamily` varchar(45) DEFAULT NULL,
  `personEmail` varchar(45) DEFAULT NULL,
  `personPhone` varchar(45) DEFAULT NULL,
  `personAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('213245658','Assaf','Tzar',NULL,NULL,'Nofit'),('302632195','Shay','Shahar','shayscal90@gmail.com','0509443347','Haifa'),('999999910','Moshe','Moshe','moshemoshe@gmail.com','03-435341','Haifa'),('999999911','Yehoram','Arbel','yoar@gmail.com','02-435435','Nofit'),('999999912','Hassan','Nasrallah','none','054-45343324','Zichron Yakov'),('999999913','Muhamad','Death','none','054-2342341','Somewhere'),('999999914','Itzak','Zohar','none','050-2342341','Tel Aviv'),('999999915','Taleb','Tawatha','none','04-345341','Haifa'),('999999991','Yossi','Benayun','yossi@gmail.com','050-1431233','Haifa'),('999999992','Itay','Shecter','itay@gmail.com','04-3242341','Haifa'),('999999993','Eran','Zehavi','eran@walla.com','03-2355114','Ramat Gan'),('999999994','Tal','Ben - Haim','tal@braude.ac.il','08-1342351','Ramat Gan'),('999999995','Christiano','Ronaldo','cr7@gmail.com','054-234211','Ramat Gan'),('999999996','Bar','Refaeli','barbar@gmail.com','052-536321','Tel Aviv'),('999999997','Gal','Gadot','galg@walla.co.il','03-6520234','Tel Aviv'),('999999998','Pini','Balili','pinhas@gmail.com','04-235214','Tel Aviv'),('999999999','Eyal','Bercovich','eyalbe@gmail.com','055-4354352','Haifa');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialists`
--

DROP TABLE IF EXISTS `specialists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialists` (
  `specialistID` int(11) NOT NULL,
  `specialistType` varchar(100) NOT NULL,
  `personID` varchar(45) NOT NULL,
  `branchName` varchar(45) NOT NULL,
  PRIMARY KEY (`specialistID`),
  KEY `branchName_idx` (`branchName`),
  KEY `personID_idx` (`personID`),
  CONSTRAINT `branchName` FOREIGN KEY (`branchName`) REFERENCES `branches` (`branchName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pesron` FOREIGN KEY (`personID`) REFERENCES `person` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialists`
--

LOCK TABLES `specialists` WRITE;
/*!40000 ALTER TABLE `specialists` DISABLE KEYS */;
INSERT INTO `specialists` VALUES (1,'Cardiology','999999991','Mini IHealth'),(2,'Cardiology','999999992','IHealth 2'),(3,'Neurology','999999993','IHealth 2'),(4,'Gastroenterology','999999994','Mini IHealth'),(5,'Gastroenterology','999999995','IHealth 2'),(6,'Gastroenterology','999999996','IHealth 1'),(7,'Microbiology','999999997','Mini IHealth'),(9,'Microbiology','999999998','IHealth 1'),(10,'Allergology','999999999','IHealth 3'),(11,'Psychiatry','999999910','IHealth 1'),(12,'Psychiatry','999999911','IHealth 2'),(13,'Orthopaedics','999999912','IHealth 1'),(14,'Child psychiatry','999999913','IHealth 3'),(15,'Dermatology','999999914','IHealth 3');
/*!40000 ALTER TABLE `specialists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userName` varchar(45) NOT NULL,
  `userPassword` varchar(45) DEFAULT NULL,
  `userStatus` tinyint(4) DEFAULT '0',
  `userPrivilege` varchar(45) DEFAULT NULL,
  `personID` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`userName`),
  KEY `personID_idx` (`personID`),
  CONSTRAINT `personID` FOREIGN KEY (`personID`) REFERENCES `person` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('assaf','1234',0,'Specialist','213245658'),('shay','shahar',0,'Dispatcher','302632195');
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

-- Dump completed on 2016-05-06 21:19:25
