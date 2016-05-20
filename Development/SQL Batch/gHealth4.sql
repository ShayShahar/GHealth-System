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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments` (
  `appID` int(11) NOT NULL AUTO_INCREMENT,
  `appDate` date NOT NULL,
  `appInviteDate` datetime NOT NULL,
  `appTime` int(11) NOT NULL,
  `appReview` varchar(2500) DEFAULT NULL,
  `appPrice` int(11) DEFAULT NULL,
  `appMissed` int(11) DEFAULT '0',
  `appStatus` int(11) DEFAULT '0',
  `specialist` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`appID`),
  UNIQUE KEY `appID_UNIQUE` (`appID`),
  KEY `specialist_idx` (`specialist`),
  KEY `client_idx` (`client`),
  CONSTRAINT `client` FOREIGN KEY (`client`) REFERENCES `clients` (`clientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `specialist` FOREIGN KEY (`specialist`) REFERENCES `specialists` (`specialistID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (16,'2016-05-19','2016-05-19 20:06:40',1,NULL,NULL,0,0,15,1),(17,'2016-05-19','2016-05-19 20:07:02',3,NULL,NULL,0,0,15,1),(18,'2016-05-19','2016-05-19 20:08:27',2,NULL,NULL,0,0,16,1),(19,'2016-05-19','2016-05-19 20:08:37',5,NULL,NULL,1,1,16,1),(20,'2016-05-19','2016-05-19 20:08:48',10,NULL,NULL,0,0,16,1),(21,'2016-05-19','2016-05-19 20:10:22',7,NULL,NULL,0,0,16,1),(22,'2016-05-19','2016-05-19 23:19:00',4,NULL,NULL,0,0,10,1);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approvalreference`
--

DROP TABLE IF EXISTS `approvalreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approvalreference` (
  `appRefID` int(11) DEFAULT NULL,
  `appRefClientID` int(11) NOT NULL,
  PRIMARY KEY (`appRefClientID`),
  CONSTRAINT `appRefClientID` FOREIGN KEY (`appRefClientID`) REFERENCES `clients` (`clientID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approvalreference`
--

LOCK TABLES `approvalreference` WRITE;
/*!40000 ALTER TABLE `approvalreference` DISABLE KEYS */;
INSERT INTO `approvalreference` VALUES (921123,1),(741852,2);
/*!40000 ALTER TABLE `approvalreference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branches` (
  `branchName` varchar(45) NOT NULL,
  `branchAddress` varchar(45) DEFAULT NULL,
  `manager` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`branchName`),
  UNIQUE KEY `manager_UNIQUE` (`manager`),
  KEY `manager_idx` (`manager`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branches`
--

LOCK TABLES `branches` WRITE;
/*!40000 ALTER TABLE `branches` DISABLE KEYS */;
INSERT INTO `branches` VALUES ('IHealth 1','Haifa',NULL),('IHealth 2','Tel Aviv',NULL),('IHealth 3','Jerusalem','111111111'),('Mini IHealth','Ramat - Gan',NULL);
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
  `clientStatus` int(11) DEFAULT NULL,
  `joinDate` date DEFAULT NULL,
  `leftDate` date DEFAULT NULL,
  PRIMARY KEY (`clientID`),
  KEY `personID_idx` (`person`),
  CONSTRAINT `person` FOREIGN KEY (`person`) REFERENCES `person` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'123456789','Clalit',1,'2016-05-06','2016-05-17'),(2,'741852963','Maccabi',0,'2016-05-08',NULL),(3,'147258369','Meuhedet',0,'2016-05-11',NULL),(4,'852963741','Maccabi',1,'2016-05-11',NULL),(5,'784512963','Bikur Rofe',0,'2016-05-15','2016-05-15');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dates`
--

DROP TABLE IF EXISTS `dates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dates` (
  `dateDate` date NOT NULL,
  `specID` int(11) NOT NULL,
  `appointments` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`dateDate`,`specID`),
  KEY `specialist_idx` (`specID`),
  CONSTRAINT `specID` FOREIGN KEY (`specID`) REFERENCES `specialists` (`specialistID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dates`
--

LOCK TABLES `dates` WRITE;
/*!40000 ALTER TABLE `dates` DISABLE KEYS */;
INSERT INTO `dates` VALUES ('2016-05-19',10,'000100000000000000'),('2016-05-19',15,'101000000000000000'),('2016-05-19',16,'010010100100000000');
/*!40000 ALTER TABLE `dates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examination`
--

DROP TABLE IF EXISTS `examination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examination` (
  `exID` int(11) NOT NULL AUTO_INCREMENT,
  `exDetails` varchar(1500) DEFAULT NULL,
  `exPicture0` longblob,
  `exPicture1` longblob,
  `exPicture2` longblob,
  `exPicture3` longblob,
  PRIMARY KEY (`exID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examination`
--

LOCK TABLES `examination` WRITE;
/*!40000 ALTER TABLE `examination` DISABLE KEYS */;
INSERT INTO `examination` VALUES (1,'sdfsdfds\n\nyakir!!!!','ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\Û\0„\0	\Z&!# / #\'),,,150*5&+,)	\n\n\Z\Z,$,4,*,)*,,/)/,,/)-,,,,-,,),)*,,),,,*,,,4,,,,,,,))),ÿÀ\0\0\0\Å\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0I\0	\0\0\0\0!1AQ\"Saq‘\Ñ23Rr’¡£±Á\Ò#BTs”²ðb¢\á$4C‚ƒñ“cÿ\Ä\0\Z\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\02\0\0\0\0\0\0\0\0A23Qqð!1R‘±a\Ñ#’¡Á\"\áÿ\Ú\0\0\0?\0¼¥M¥\Î!­h›³™/:—¤t^ò\à\r\Ó\"f\ì&¬tž[\Äiv\0`8Àƒ±I´R´T ½•«\":š r’©¼´yg\ß\êR$œ7]ù÷ö oc`\êFö6¥+\èú¼•_AÝ‰\"\ÅP\ÌS¨`Á†8\ã²@[õ\æZ\ä\Èû\Ø\Ø:‘½ƒ©Jú>¯%W\Ðwb>«\ÉUôØš\Ès^\ä\èO“\"\ïc`\êMU¬\Æ\Í\è8e3Ššû@	4\ê\03%Ž\êL\×\Ðy%Ôªœ\0Á¯\Ã6\ç!\ÅC©š÷\n\Í20µS\ÃŒ‰81\é\\\áT\äŒ3\0À’F\ZÔ¯ \ÎóVd»½~eÁÄ‘¯3Øºý\âI4jÉƒ\Þ\Ô\ÌÀb1X\ë5\îe ù?b­”Áœ‰º`&2Ï›<Bla•\ì\"gÜ§\rÊ¿£Wo\Û\Ï\Æ\Ï<±\Í\rÜ­@I\Þj\â.\ä\ì¯fL’eF±s^\ä\ê\ß\'\ìA\Ês—ô™˜˜»1ŽY.ðº{Aœ¡¤\Î1v)\Ãr\Ï\ä*\Î\Þ<\åô\ÎXt`’7)RO\ÔÕˆ€\Ð\0\É\Âu6&±s^\äj\ß\'\ìC\á”ù¶÷§,q\Ë,9`”\ÛC3.\éž) ˜Œ°\Ï%)›”¨ªNSÆ’1\Ã>÷\ÅË™/¹šœ]g\í\ë$œ\'\"N#,¶\Ö.k\Üj\ß\'\ì@6\ÚXsÿ\0!\×t\rZË›d\'é†¸\0 ‰)sûpŒÃŽE¤bN\Ö7©=OA\Öh\0Q©\Å+%R9µ\îC§,“!\rƒ©5gªÇ‰h8a\Æc˜zž*\Ï\èjü•OD£\èjü•OD©\ÖCÔˆ\ÕË“*mR\r¸02K\íž)­DÒ‡\ê\Øb	\Î0û9-\Ð\Õù*ž‰M\×\Ü\íW€F¡Žgr\æÄ¥V”¡+¿©Ù©¨¯\Z“‹ir_Ff4}œT}\×T\Þ\Ä|\Ä6d$j\ÂN9Õ¡·^\æ‚`# c\ì’:‰KÜ‹¹\n¿\×ÚŽ\ä]\ÈUþ¾\ÕKþ>¯®?¹ýOþk{\ê\çûWÜ­¦Ñ½³¡\nMª…\ÈdvD\Äj\Å\ÐB:0I\ä—Á\äkMN¤¤¼›oÝ–\Ú\Óp;œ3\Õy:ý-Xd\Ù\Åù\Æ<cw\ìF®˜\ÍQ\Ù\ì\"\Ñj³\Ð{ê¶›\ÙQ\Î\ê:œ–4]’ÓŒIZ*_\Ã\Û#œZ*[e¹ÿ\0™«\Û\Ð|\ãj¦\Åñ¥\ÞH±Ã»S]\æ7dÒ•IúÀ§\æ?Í³ÏšSt“…3sMH\Ù&£Œž¹O³øod R\ÙxU\\ü’\Ïð\Ö\Êq5-¤\í6º\Äõ—.{øXß\Êñ¥+\Ý\á\ìt¬f/œ†­x%#X€p\È\ÊNFf5\ëS\ÃK\')lü\Õ_›œ.ÿ\0†V^R\Ûùª¿2\Ä\Èb–”u\×_\0w\ÙewQ\Ïf²yl\Ò\â\Ü8‡IC…\×qL\å&\î##\Z¤?á—”¶þj¯Ì’\ïá½“3V\Úg6\Ê\ÆuÌ—c†*Ai\ZÅ¤i\âA__‹Ñ‰)ú\ZN¤¸8¿d\ë8œñ\Ù±Œð\Ê\Ë\Ê[5W\æGøee\å-¿š«ó(§F¾i4\í\ÖeIY>\à(ý\ãI~z\Ñó£¸\Z?y\Ò_Ÿ´|\è\rbK¸*?y\Ò_Ÿ´|\è\î\n\Þt—\ç\í:sZ…‘\î\Þt—\ç\í:\çp”~ó¤?hù\Ô\Ø\\×¡d;…£÷#ùûGÎ¹\Ü-¼\é\Ï\Ú>t°º6X\×n\"\Þt—\ç­:l\î.\Þ4—\ç\í:c¦²\î6\Þt—\ç­:Oqô¾ó¤¿=hù\Ô\è1¬F\éý\È\ÒûÎ’üõ£\çL»r¬ûÎ‘üõN­‘¬DM\ÑxzŸˆÿ\0Ô…\\\\KI.1‰q$œ±$\æyÐ¯áºº\"š{Ï©™\Ý\Ûeö ]p\Z—\\\ímk›u\Äs\Ý%Yn·Lh\èk¬ud—Pym:c‹òÚŒ›\Íp}Ö°\ÝÀˆ .nƒBðª¶ZsTt\Æ\Ê\Ý_Ú·@R”šZq7ŒF#\ÃQŠ\âË¼‹*ò\ã\Þev–Ò hª\r³:\ÔmNÞšC8S\Å\Ðû¯\âY03™9…sc\Ý6hö:¹·S¯qô\îM©Î¾À\à\×9¬&\ë]\r8À\ã(tw\n\"µZa².Ó¨Z\Ü™nGfT»á¢°«\Âk8&›®\æŸ¹\ît­¼\Ì-O\â5 ²\Ív½¡¯`®+œ\r\ë\ìfõw1\r{L\å„f½Ñ¦ô+­´T\á\Ôi’^w=P¥Q\Üu&´\0\Ö`\à\ì†\Â6+\Ë5…­¥½À\"]±I\ç\ÝUº–ò\Þúó_zª\í\âœÁ£N«\\\Óv\éYü¸.\Ú7Ui­e¦\Êuk6¸#|&m+·\Øc \éÅ¹Îµ\èT¬\r\\,£bw‚€Øƒž\Ô\'ÀóJ» ¶µ—Ejû\ãƒ@võQ\à:\\	2\î2\È H\ØpôýXº“ƒƒ®¶A ™º&HÖ‘Nˆ\0*[\n	v;t ´\"÷\îRotõ¡\0@\\„óR\åþŸR’,¼\É%‹·ÿ\0p¸j~\áI8XK\é©Ð’_û•$X«\ÓU\ë°B«H\êÄ½ªMˆ¹\Ô\Øê¸÷42f\é9¶F6…%\ÏI/ý\à¥&’ƒ¥_QŒšL\ß8¶@1\Ì\\\à6kSK\Òõ‘&„·×¬_¿Y\ÝB\ì]¼æ¸¾fO\Î\0™VN¢ž¾]û\Åf x6tv!twŒ\è\ìBº†\ê\èVKyõ\Ñ\çüõ–9:ß¡«o{–À?\ÏY·\èj\ÙžŽ‚ª1<Yw’,hp\×y’\ÐgÔ”\Æe•Œk]6.sp\é§\Î.R\Â\î÷\Ît·œ6z—w\Ã&wÒ¹¿\Æ~\åŠûWwö\íF\á=K—\Ç7T|M©ž3z\Çje\ÚJ0j\Óeö\ÏT¦]I‡PP-:Ž\" mF>¯Š\\’ð>qB\Ï2¥§¡\ØÜ\Óü¤Œ|\É\Æ\Ð{{ÚŽ>WÞ—-\rGl	«¼_Z¯ß«ÿ\0ÄŠP\Òõ>£Ú¦\äX›¿Ö¸ksi6\ë#±([˜~\Ó}\Þô¹$o¡p¿§­5¾¬:\áR@³S\ÊIuQ´¤Îgj›,\ÔSe\Ü\á$“´u$er ðl\è\ìB\à\éôv!]\Ãut*\å¼úXù\Û7\á\Öý\r[+\ëgÿ\0Yeò+~†­CÔª1<Yw’,¨p\×y“K’@u8~\É÷\Î(7µ¹£\î§9Ÿß›4‡SgÌ‘x\í÷¢ÿ\09ýõ¡ \à\ïþúJY\ÕFO8c\Ó\ç!<jj\ßÖ‘¿N±\êø…=$ð1y¶:8ÀÇ¹Hn”i;`I‚t¨\åÀ\çÚ’û+]©A$Ñ¤šr’–-€\åûóª\Ç\ØF§±ñ%\ã\Åÿ\0Ž©Ç­EÉ±o¿.oª—‡–\àoŸ\ï#\Ö\ÊUžD†\×õ˜	¤M‰\×ù\ÒI\\¥g{Ž^`\ÒOY†úÔ–\è\çkº:Lÿ\0K~d\ÓCE‘\\b$@ñ‰\r‘ÁX¶À5¹\ßð=x¸õ£\èúS;\Û\\v»Ž©b\æNB\ëE˜5¡ƒ\È©\ë`Z—V\ÌR•]ñ$b3Ž}J\Â\Ùi¦ÁõŽkFWDO@?­UZ´©¨\àn\Ól\Ý¶%\Ç_©#\'p\â¬pVJ\ï;Kk$G1œ°Az\ÛsU…pžoZO\æ)%\É7”¦cc:\ßO£±oƒ§\ÑØ…\r\Õ\Ñ2\Þ}N\Òÿ\0Yfò+~†­3\Ü\Ëõvo\"®\ß»\í\Ó3\ç\×\îTø®4»\Ét8k¼\É\è:\×w\áŠŠö˜\Ãaú˜ut@N\Ísu‰N´\ïOH‰ÿ\0\Ä\×œ\æ\Ò\Üùñžuû‡\Úy¹{’š\ê\ÎÀ6ñ€\ÙÃ \"px DGŸ\ÃTÆ£\Ñ)L\ÐU\ê8\0À\Þo¥®bH‡]:²Vr®wÇ»\"C@´‚\\±r2Q*]TŒ\àtŒù¹’\è5\î’2\Äz\æ:Ö‚†ˆ¤\Ü\È\'ùG\ÄÏ©N§dn2HÈ™\'•ƒ™š‰—cp‡± ;\×\Þú\Ô\ÚZ-\çcv—|\Z½\\Vµ\Óg|ö\ÎÁ‰\êLý\ÓSK^\Zobc6\"&g£b\Ö\åÍ›\ãJO\Ê%\0\ÆI\Ú!¾\ìTšw[h\çŒzÊƒf·5\í¼/\ísKGY|É›F•`À\çcG­F’1piÙ–®´sª-/¤\í7÷ºgtY\Õ:`’`Ib1\r“`£UÒ¶‡8\nmm\ÜIt\Äl‰\'£û)m ÷÷\î\'\Ï%/¤LZƒ»I÷ô³Úœ\ÒwÚŒ{Œq)\ÍÖŸ\Ätz-M(\êŽ4\é\Ãu»l\ÎIé¢\é­3f°\ÅðK\ß.\rceÎˆ—d5fz×i]\ÓT¬\\õlv­t—	\É\Î\ÂG6¥iz4ß\ÙGW¯\ä¹ÿ\0\ÌÍµ£M\Òß›J™zù7Z³8a’³F\Ò3\Ç÷Ì¼\ër6]ö´‰¹H:‹Èº\Z0.=Kf\Û!i\â»y>³Øºi·kœ˜¨BÑ‹òo©Q×\Ü \ÝÖ¼9ù—88€@&3RÍŒ\ÛCÙ¾T²Te\Ó~R›\ËÀÀ.OFY·9ý\é\ÇŽ=©\ÆZ\0\ÌGPõk[4Ž[¶¥w\à\Ø\Ý|R\çyŒÀ\Ãh*C)\ß\îs\Ù\0\ß\Ò-€/a‰V´û\ë–l:p\Éf™Š6x:~ObOÁSò~\Ð\ÃutE4÷ŸQ‹UP\ÛM˜‘\"\í]qö·§ndH.¨Iõ\0UEª8M–n÷µs\Ên>µ\âqs c‚¦\Åñ¥\ÞH´\Ãð—y–Ö«3\é¶õGŠcUóW\Ùh\'25kR¬º1\ÎÄ‚\î€\ë2¤,Æ‘Ý¥K9cXhº\Zß¹¯‡km¡\í‚\ÑLñ“©¸Œ5`~¸\\Î¤•ŽQ\ÑÀ	uÖù\è—Hõ®N\rú\Æa\î0\Þ(\ÑöF†´Î•·>Œæ§”{Þ±?Ë¦¯˜¸\é\æ\ã{±Z´“vgB¡SGI/ß†\Ýka£‡19•\ÇUœ\É)®I\Ãzp8\êŒö$Ö¶Ra\ÎŒ\Ý1‰™€²nÆ‘\ã^otZ2\ßV«wŠ®¦\Â\ÓzûÀ` ˆ\â8dÞ´÷¼\ê\ÒÑ§ˆi\Þ@.ƒt	\0˜À€L&Z\ê¯`y\ç¯^#¦V¶Ô\Ô\æ\éKI>${„sGù‹Nú|ZL\ÞÇòI\ê	+s^ ¢Á„!\å¸g$\ÒzeK¡¹ö\ç%\æó‰\Ö`	>`\ív\Ë5^ªút\äeöœ Ñ‹º”¨\Û\èg*³ª\í\â\ß%öBg¯Q\å\Ï\Ä6$¼cš•GF†‰qÀbI€:N¯:¢¶\î\êf\éN\Ò\èmWŸ³,…6\ãð‘\Î\Ò*Zmô-KÀ£PS½\04f.´Co]{q‰0šQO™²8J^^\n\×þlý}³tt©Ð©Z‹M¡´\æñ¦[t@“\Æ&2\ÙT;³\Òõa/nùK\nO.¦\î+\Å@o6ð\ãCp\Ç	\Ùdô\è\"\ÉP±¥€\Ñhq†±µoTu[¸š¼\0›¦s:KuA´Ÿ,a¥\r<G0\Ô\Äa\âŒyõ@Q¤\æ­\Ì\é\Ô\Ó\Ã\ÎþFY\æ»úgô¹«Ý¦¦\æ°8–Ux©x\ËZM\ZDµ§SDDm¬Y\Òw°n\"q9N\Ø\æUÕ­\Õ+\Ýq-cCXÜƒZ\02\Íeƒ³û#‚N\ì\Ôñ“ÕªpðF÷q:I\Îa§u]LœI\ÚrZ‘W÷ý\Ö+rŽkfp˜ƒ\ÌA\ì`\Üq¢è­Ÿ™%©WSL]•‘ˆš–VA#‚] ·\ßñN\Î=)a\ÉdAYKÁSò~´ü?\'\à½$7WDQ\Ïyõ!iX\ß\ìÓ•ÚŸ \'\Ü\í\Ø\îMi \r{4\Ä]©ú–\ÝD2¦\Å/Î—y\"\Óÿ\0)w™\Ùgc\Ùp\à	\Ï=G´*Gi\ê\Ö*A\Ýý:d\É\È\ë†0/ddaW–£L\\ö†ŒI&\0|\Ë¥÷BÊ”j0\âpi‚—bc1†0vŽp8\çgT.ü^Ñ»«ª\Z2\Ã²užDgÐ¥Z7SUÀ†Atby\à›\î;v\0Qm:¬tRlº \Äƒf\ç a+\Ð45zO\r}71\Í8^iz—=Ÿ3l“‹C”j\×{EðÀ\èÄ‹\Ñ:\àO¾S”t#d9À9€\Ï<gJ\îº\Éfú¡\ÏbŸÓ³\ç!d4¯ñN«¤Yé¶˜ñ\ß\ÇHoz\Ó\Óybô#\æuQ\Âb+n\ÆË›ðG¢šL¦\Ò\çµ£2H\0y\Ê\Îi_\âE–ŒŠw«¼x˜2\á\à\åúCJU®oV¨ú‡ùŒ\ÐÜ›\æ\n®Ñ¤\Z\Ù\ÆHÔ°\×J^Eœ?¥IiWŸô¾\ïø6š_ø‰j­!®pŠ}ôs\Ô8Ï“ue+[¹Ä—;2I’O9*–®”{°\Ï\îJi\ÞaÙ©d¨J^3dKñ\Z4VŽ?\×©¬Ò›©²YX\Ãõ”]P\ÈÄ€j:\àÇ¡¦6]Uv\Í\ÒU5\ßh½q\Õ-™$\Ùlkn`ÁQp£ö\ÝS›ºõy“–kq\Ö}eoPQ*\'ˆœ\ßy»±&\Ð\â!²ÓŸ^¯2“c°‰*uŸGÏ‘õ+*V(õŒª%\äjI¿G³Xcÿ\05+:Yß³©v\Ïd\ÄaóF\ÓÒ¬¨\ÓhDy\Ï	\Íhr6$HÑ–f\çöµ\0\àb<•¦²²¥Ib CfÞ¥odt7×œ­\ÔÙªd¦\ÕÛ‡¯\á~\Õ G¹\"“®2F£­n¹¬”;C\Ó-\Ú3¯û¡\Åd@\Õ/O\Éø\ÄQðT¼‘\î^’«¢(§¼ú•\Úy\àU³`EO\Ð\ÒÃ¯\ÔO›•ºzmuK0q\0CóòŠYH7¿ e‡^¬\Õ6+.òEž„»Ì­\Óõ\îòd‚\Z\ZD´	\ÌS¼\Ùq\Ãp\0\Æk\"\\/\ì\ïIŒ\ë™\çõ­†“§Iô\Ëó0ñbœH+\'n£u\Æ\Ze‰q\é’¸.9pv\'\îv\Ü(T7¸­“‹@tF\Ë\Íošòn \Æ\Õ{XC˜\×84ƒ ¶p\Ç^\n;yˆ1\Æ\×\ç\ZúR\Ì, ô~ð\\óƒe¦¡+–lvFµi³I;uª\Ô\à@¼D\rY(oµŸr\Â|\ÙÛˆüV\Ê\Ô×6K«¤j;]Ð¡¾¸Ì’Jh\Þw^I\ÚvEÔ”c\äQÔ­R«¼\ÛcF£7B[,‡X8\Æ=?eaCGœ:¿²²³\ØvkÁb\êXÁD­¡a\æ8\å\Ï\æV´,\í\Â­‡>¥6•–&×\ÄŒŒ§©Ø‰\ËP$\âÌ†\ÌB\Ó)\ÜØ•„Ñ³Lˆ\Ô1\Ï5*›(Ö†‚\'a–¹\\ß¸8)tiº11ˆ–®Žµ¥™‰§H¯¢18\â—F\Îvl\Ã\àvkH§\Zó²p9by¶jR\è2@\"÷X‚gdŽ¼‘F\á±\Û–	™“\ä\à1\ËÙž\Å`(\Æ-1\îQh²\éÃ›ûžµ0?ã«³™t\Å$il\ç#0|Ýšõ§\Í@r8þõS5*yºFp€u·\ÌG1a\î>d>\Ô}†Ã˜Modk\Ã<r\ë\ëMT­uk\Ç2\\\èø*^H÷.Pð4¼‘\î^¢«¢(\'¼ú”Û®ªÆº\Ì^\æ´qñq\0MÁ\ãT‚\Ùgšôz\Úg¨¯`\Üm¹\Õ/5¦O0·mZŽO\Äg¢;.-~t»Èµ\Ãp—yŸ>²\Ûewú\'¯¶z¥d\í•)<ñ*žpŒq‰X¯«¸?žˆ\ìG§\â3\Ñ‹•«ÀùûI\ÅÃ¦G¹\"\èEK¤Æ°vgNk\ì.O\Äg¢;À©øŒôGbn|h\êch=\'_Zv\Ï|Þ±Ô¾\Æ\àTüFz#±\nŸˆ\ÏDv)°¹ò5Œj˜ÿ\0R\Ø\êQ…Fy\È\é\Ör_Wp*~#=ØŽO\Äg¢;Ì#\åºU©e¾Sô\Çj•N\ÕD5©`u<j\çŸZúkSñ\èŽ\Äp*~#=Ø±Ô®fZ\Ã\æ\æ[¨‚«G9Æ£~`¤%GUj8\í¨\Ì5\ç%}À©øŒôGb8?žˆ\ìQ¨\\Æ±Ÿ:ý#C®£ÿ\0Ñ¿¥&†– šô„jÑ¬\Çì¯ x?žˆ\ìG§\â3\Ñˆ¨¡¦\Ï§§,\ç:\Ôý´þe\×i‹)ÿ\0~\ÏÓ¾2pÙŠ÷O\Äg¢;À©øŒôGb\ÏVŒtúr\Ï÷‹>\\£:³]n³k´Y¿ú³\æ^\×À©øŒôGb8?žˆ\ìS …\Ïn\èl\äøk4\ßo\Í\å9õ§>Ÿ³}\âÿ\0¶™ø¯h\àTüFz#±\nŸˆ\ÏDv&‚\"\çˆ?f\å¨cÿ\0\êÁ¢qLWÓ–w\Ýþ›y\ÅZ}Y•\î\Ü\nŸˆ\ÏDv#Sñ\èŽ\Ä\ÑB\ç•YO\ÔQò¸!NÒ‚*?ñ*~²º½47WDP\Ïyõ4;‰\ïªyý\ïZµ”\ÜO}SÈ§\ïzÕªl_\Z]\äZ\á¸K¼ÁB\å:B\0B€„ !B\0B€„ !B\0B€ó=+\áø•YB4¯„\âUýe\Ñ\Ãut(¥¼úš\Ä÷\Õ<Š~÷­Z\Ên\'¾©\äS÷½j\Õ6/.ò-p\Ü%\Þ`„!r\0„!\0!@B„\0„!\0!@B„\0„!\0!@yž•ðüJ¿¬¡\ZW\Â?ñ*þ²…\èáººR\Þ}M\â{\êžE?{Ö­e7\ßTò)ûÞµp©±|iw‘k†\á.ó\"”\è\"„B\0B!€ˆD \"„B\0B!€ˆD \"…\Ø@y–•ðüJ¿¬¡\ZW\Â?ñ*þ²…\èáººR\Þ}Iú•Rô\Ù?U$°¼]—6Kd¨\ê*\î\ËJ«X\Ði8’\Ù$\ë2FR£nF•R\×\ïo\r\ï/Hqfž\í]]\ß\îFp12Ó…\Ïƒ™\ËVJ›Æ—y¸n\ï2U\ä}˜\ìDU\ä}˜\ìVõ¬•\\Öð¨Lb\æ‡\ìa˜\ÄÍ‚bÃ£j±Ä¹ó†w\âÁa]Ç¼q\Ä)\ÐW\Å^GÙŽ\ÄE^GÙŽ\ÅiR\É\\µ„U£Xö“\ãœ\â\Øu\ØÀ0\'	\Ì\æ˜}š\Ó!¦¤‡x´4GÕ¸I–\ãÇ¹k\0¢¯#\ì\Çb\"¯#\ì\Çb·§fªIpyp\â^0­ƒu  œ6¨ŸEVÀo™9Ž\Å\æa­\"	»ˆ\Ë\rx™jU\ä}˜\ìDU\ä}˜\ìZd 31W‘öc±W‘öc±i€\Ì\Å^GÙŽ\ÄE^GÙŽÅ¦B3yf;yf;™\ÌU\ä}˜\ìDU\ä}˜\ìZd 31W‘öc±W‘öc±i€\Ì\Å^GÙŽ\ÄE^GÙŽÅ¦B3yf;+Uš¹}\æS-Á \Í2\æž;I† –	\ÚFpV\Í\'\Ò-!\Äó\äl\ãd…\'t^§\â?õ!z8n®ˆ¢žó\êiw\Þ\Õÿ\0¯\Ü\åªY]Á÷µ\ë÷9j•./.ò-p\Ü$B1\ÐB„\0„!\0!@B„\0„!\0!@B„˜n‹\Ã\ÔüGþ¤#t^§\â?õ!z8n®ˆ¢žó\êiw\Þ\Õÿ\0¯\Ü\åª^}¹ý\Ð;]\Å¼ö€ˆ˜\íV\Ý\Þ7’öƒ\åU¸Œ=I\ÔrŠð;\èW§\Zi6jÐ²\Þ7’öƒ\åGwä½ ùV’·§\àÝ´\Ò\æjÐ²\Þ7’öƒ\åGwä½ ùSd­\éøM.f­)\Ý\ãy/h>Twx\ÞK\Ú•6JÞŸ´\Ò\æjÐ²\Þ7’öƒ\åGwä½ ùSd­\éøM.f­)\Ý\ãy/h>Twx\ÞK\Ú•6JÞŸ´\Ò\æjÐ²\Þ7’öƒ\åGwä½ ùSd­\éøM.f­)\Ý\ãy/h>Twx\ÞK\Ú•6JÞŸ´\Ò\æjÐ²\Þ7’öƒ\åGwä½ ùSd­\éøM.f­)\Ý\ãy/h>Twx\ÞK\Ú•6JÞŸ´\Ò\æjÐ²\Þ7’öƒ\åGwä½ ùSd­\éøM.f­)\Ý\ãy/h>Twx\ÞK\Ú•6JÞŸ´\Ò\æf÷E\á\ê~#ÿ\0R\ZZ\Ö*Tsðœ\çD\ÌI˜”+¨«$Ÿ\"¦^-´ÿ\Ù','ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\Û\0„\0	( \Z%!1\"\')+/..383-7(-.+\n\n\n\r\Z- %+./--/--+----.+-/-/-./0------+-------.+-----------ÿÀ\0\0\Ç\0ý\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0I\0\0\0\0\0!1AQ\Ñ\"23RSTaq‘“¡¢#Brs±²ÁC‚”\Òðb\á$4Dñ„’\Âÿ\Ä\0\Z\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0.\0\0\0\0\0\0\0!1AQ\"qð2a‘¡#B±\Ññÿ\Ú\0\0\0?\0”\Æ\í²\Ït\ÅCP½¡d%›?o{žaoY\åµ9ÿ\0[‹»\ê¨\ïŸH³Ç—&^{ï¾¼þªó\Ö\Õ\ï\Òþ#þ£MjŽ\å÷÷ô:ú¼%Kª\ë\ï“\Ñqm\Ä1\ÅF\×Í™3\Æœ\×\\ºó\\~z\ÒýqE\ã¢÷‰\Ó^m­£B\Ä*‹– \ÎN€T\Æ4ª\ì\â•\ëŠ/¼Nš:\â‹\ÇE\ï¦¸\Ïc]«\ä3ü4v5\Ú\ÞC?\É\ÓV$\ì\ÝqE\ã¢÷‰\ÓG\\Qx\è½\ât\×\ìkµ¼†“¦ŽÆ»[\Ègù:h\Í\×^:/x4u\ÅŽ‹\Þ\'MqžÆ»[\Èq\'Mv·\â>Nš³u\ÅŽ‹\Þ\'MqE\ã¢÷‰\Ó\\g±®\ÖòG\É\ÓGc]­\ä8“¦€\ì\ÝqE\ã¢÷‰\ÓG\\Qx\è½\ât\×\ìkµ¼‡òt\Ñ\Ø\×ky#\ä\é :\î\'„~.\\1\Ðh\Ò\rú\ßqô{)5\á9\Ì|0hñƒA›\æ\ÔÛ§\Í\\›±®\ÖòG\É\ÓGc]­\ä8“¦€\ë\Â6¾»\nFc’\î·öocþ_Í®Ã„­\ÊøO?Öƒa­ùu;¿\æ¹/c]­\ä8“¦ŽÆ»[\Èq\'M\ÙW„Q\Ø^homm\"~[kY\ëŠ/¼Nš\ã=v·\â>Nš;\Z\ío!\Ä|4f\ëŠ/¼Nš:\â‹\ÇE\ï¦¸\Ïc]­\ä8“¦ŽÆ»[\Èq\'MÙº\â‹\ÇE\ï¦Ž¸¢ñ\Ñ{\Ä\é®3\Ø\×ky#\äé£±®\ÖòG\É\Ó@vn¸¢ñ\Ñ{\Äé£®(¼t^ñ:kŒö5\Ú\ÞCˆù:h\ìkµ¼‡òt\Ð›®(¼t^ñ:h\ëŠ/¼Nš\ã=v·\â>Nšcˆ\àn:9\ã\ÃI†‘&œlP\n	b\r\ì\0\0\ï<”t\ëŠ/¼Nš:\â‹\ÇE\ï¦¸\ßc-«\ä\ïp\ß\ßGc=«\ä\ïp\ß\ß@vN¸¢ñ\Ñ{\Äé£®(¼t^ñ:kŸ£=«\ä\ïp\äþº©\Ï@HOˆ\âñm%³dœµ·_,—µýU!¢¸Ï‚\Ã>\ë›\0\ä^\æ\æÚ›r›þuµ;ô¿ˆÿ\0¨\ÓZ{\r\Â%ŽQ\"a¡\\¡2 \'.d{‡>»J\Ó¶\ÖYc‘¢X\ÊÈ#.·\nÄ›\\w[¯\È*ŠÐ˜¿¥-–\ç2\âv„f\ä\Ø$¹u76Å´\Ó\ã\Ïu1?J»*AfŸ½Hn$\ç2\ß_·~|\æ¼\ïE\èEúQ\Ùy@8½¦H\0´€¶–$‹\Ú\äy¼û\ë3ý)\ì\Æ\0^\ÐL¨ª\n\Ån\æ÷6½³m{r5¼õE\èn\Ê[(©FŸ\Ê\Ù419 ¡Z\í\æ\çü³‡úT\Ùh\áú§he1¶Sb\ä\\_[gÝº\Ê4\ß<Q@z})l¾\\^\Ò:\0;Y Z\ç¶\ÔòžKü	>”vK™±zš\Ø{B:\æúÜ4\Ðo¿ž¨ =\nÿ\0J;$¡N7‚‹u72ùüGš\âÖ°­\é3c’\ç\Åö\ì\ÌGHR\Æý®¼ú\Ûq!I\ÐZ¼ûE\èI~“¶C<v4\æ\Ï{\Ãr3°m\rù6ô\ë{[¥-Ž\r\Ã\â.\r\Ôõ5ò\Þa1\înÊ»Î–\Ò\Õ\çŠ(Bb>”ödŽd|V:údU„¢Gee%Í˜\ßy\'\ÍnB¥\r’ ?ÀZ\Êð³/zxÎ™´¾{›o°ôŸ=\Ñ@z>”¶X°\êœs\0hY”et`\Ùokö–ó\é¾\éOf\0ª1X\à«\á>}XÍ ²mG«Jó\Ý¤v_\ÓÌŠ06.f¯#BÙØ‘½Ž\à@õS®Í›+\Â\Äû“\Ó^d¢€ô\ßfÍ•\áb}\Éé£³f\Êð±>\äô×™( =7Ù³exXŸrzh\ìÙ²¼,O¹=5\æJ(Möl\Ù^\'Üžš£p»\éˆ\Úx,\\-/‡Že”´eH2+(°\å\ß\\vŠ´·\Ò1a‰œ\\Ü¨À<¢\ä›rk¸D¿H˜3{bq	|\Ý\ÊH\0\ÌI\Ýk_]õÅ¨ ;n\é+ŒY±3?2˜\ä\Ê5¸\ä\ßþiº¸¶%\Ã;¸±#\ÐN”­©ß¥üGýFšÓ­©ß¥üGýFš\ÐQ@m\ZÜ\Î@öÔØ\á\'\Í\ÑQø~\é~ðü\êÖ•›>IB¨†Bÿ\0¡¿„Ÿ7Ea¿„Ÿ7EX\Õk$\n\Ëÿ\0ªa\Õ\Ø~\Ü7EH\á8,›¥„zsôS\à5§P\íM\Õxjee^Jö\Ñà´÷Oz3~\â™&\Æsö“\ã\ÑV<v0É¾J´µðCkÁ¼sö\Ó\æ\è¥ƒR·\Í\ÑSqµ8\ê‘\Ôdn_^\n\È‰\Ï\ÑKK\ã\"ùú*\ÅÓ•ž©›S–=2hªK\ã\"ùú)à¬ƒø‘|ýt\ã/M1\Â:\Ü\Þ_ð\nªpbCüH¾nŠPðJO\Ï\ÑS\Íô¬´­[±\ê[\ì*+ópq\×ø‘Ÿþ\Ý\ÕöCŽU>ÞŠ²ebu§I„¸­\nM“E1¶{o8‹b;}¤ÞŠ²É‚%‚Àiº³\ç\Ï,kEF>\È\ßn?›¢œ/e?Ä‹\çè«—–¶Š`+\×fð\Å\ÓÀ©¼d??E1Ú¼’\ÎÎŒ.†k\ë\é\Ð*†Rƒ†þuý\ë¦fidŒe\Ó~\ÄQB¢Š+\Ø (¢Š\Ö\Ô\ï\Òþ#þ£Mi\Ö\Ô\ï\Òþ#þ£MhŠ( ÂŽ\Ý~òþuq1\Ø\Õ?	Ý§\Þ_\ÌU\Îg¬\Z\Î\â\rY\íH™))$­\â[\Öm´ˆ±C%©×¬Èµ¨Z²6•¬@jš\"\Ç\nkV–Ô‘zEš¡Dµ[ŒU0•AIELA5\ÅbG¦(ö¡¤5\Ã\Ó\ä\ßZtÞ\Ã\ÇRp\nÙ‚\Ù)	G†§k…*‹J^‚J‹Œu)ƒ°‹\ÓZAvµ\çj\á}LcH¶•\\žc­©y¶…\é‘{\ÖB»!ˆ´\Ízk·¦&\r|%ý\êMa½Fp†+Cü\ËûÖœN/$~eJ½Q^¸\n(¢€uµ;ô¿ˆÿ\0¨\ÓZuµ;ô¿ˆÿ\0¨\ÓZ\0¢Š(0\Ý\Úý\áùÕ¢Y*¯t¿x~u<òkYu¶ˆc„[\Ó\ÜqŽ#ˆ4®wG\Z´²o\íPL±‚5Q\Û\áó\åT\ÎFfZ\Ò™Bl\ÛûžQcL!X`Ã†\Ã#;u^É“\'´™Z`Ê¡²\å\ç_5p\É\Â;¦\Î\Ø4\Ó\Ê\ê$;X«¸e3i2£ƒ\Ì¬\Ìt:\0N”ËªT\î?\è«4;\Z%X“\ÒF½4\ã²\àƒ\'SF\\ƒ&N„\å6^/”žK\î6¬\Ð\Ôboo?Á½þ%l«ñg‹ã²¸ˆ¶^;#ˆ³x9È¶o5\ïH+©\Ü\Ê}\Z”l‚\0›Äµ‰€\Èx’A\ÌM\×Í¯¦ ñ¸pm¹<÷|<ŽŠ\Ê\áM‰\n\Â\Ì5\å¯§“ˆ³6}$ñ\Æ\ä;±4ºAPŠ\Ü\È	\ÎEH\Òþh\Ô¡R¸\Î.\"eKg•–Nf\É(yûb5\Ð\nO¿µ£7§$®‡\r\r \ÚV‘m\ÄmZ3Ï£/·Â±<·\Ôj\â5\ËÓšüÈ§\"«%.•^¤`^m|\ÕYª$sZ@\Æô¶ÍƒŒ•P\ÞÍ¼ë¦†\×õÚ¯‡#ºD\Ø\ë«)E\Æ^¡!Gf\È-{[\Ï\ÍRƒfº(\ãV“½-šú\Í\ëÜ¾\Ú\Ñ\Éø&\Ì\Ï5\ÅCM½\êQT\Þ\Ì,y«2@+Lÿ\0C §1kw‚Ô‹\é­E\îK\Âª+…ƒ\ê?™zV`¦\\$\Äf†\Ã\Â_Þ«†\re\Ì:(¢½²Š( mNý/\â?\ê4ÖmNý/\â?\ê4Ö€(¢Šx{¥ôÎ¦Þ¡`\î—\Ò?:°ˆõ¬\Ù\Ý4C6†6d(·Ï®K¡±dbwŽ\Ô7^üõ7±v„(\rdYL€.%\ã\ãC2È®\Ùa\Êy\ìN„ó˜•JXO£«g<g|e‘‘¤ ‚¢QªÊ ‹\å\"þq\\7G$\\\'\Ñ\ßyb–\ä[\×Q©È¸#-\Æû0\ß\ê4óhG:žU´Œ\Ò3™[\Â\×w\Ã}S\à—‹^¨\Ïõ\æul.8cl‚\Ã8d\rõF\àö„‹o½Å´mN2Y\Æ,u.)\é\"Q®6u%SB\r‰7¯?>Žx \åk\è{º}t2\É&©ý~ÿ\0~„¤\Ù\'Š\ãt+›\'t™¯k÷\í\ç¨\\^È\Îf]@°<\Û\íùŸmY\àÃ´¬¨«\Û1T[1\'Ï¸S|N£afBU…Á\ÔhE\ë3J<«F\çË‡Lª¾/\Ùõ\ÓY0 Õ–d¾\íy\Ï ¨ù`ŸUk†wä¤ V\å\Ù\ë\Ì)¹À•\ïmoöTÕ‘ð\Þa\ìˆ\Ã¨\0=\\þš\×\rK÷2d\Óc’\åømW	óŒ›\í¼\îõÓŒ&-\Ô+*ž\× c bÅ¤”\0AJ1\Æ\ê\0\å¥q\ß\ê·+•VrB•\\ÁÙ…ôT&\ã’ã–‘\â\Ê#Ì²\É;©Ž1\ÜI\Z·h7\î@«\í±\Ý[±\Æ5½žLIdq‰n\Â\ì \"-«IZR.„Û¶YýlTù®E;Ál€\Å]I\0\ém\áor\Ã}\Ïm®þZ£axMŒ„¦9$°d\ßqmH \î§v\rc«œu9û\r{\Ä4µûž^š\é76\ÚU\à\å(8ö[6v	RR\n\0\Ì¬¬\×\Ì#^\×ß¿}Ip‡N\î\Ç#°U˜\Ò_’ÀznEG\à\ä[ñ\ÑbÁX‚¤\é»K[Zu-¤Ñ•^À¶¸±°\ÝË¸|+T£*á¤Š”S2«„kñe­vKÿ\0Ÿµ+qS[gg YÝ´Ð‘›RlB\îUÝ¸_]j\r—)°7\Ó!<¶\ç\Òþjð586;]„\æKŠŒ\Å\éRŒj;µLO’\ÖD«šKj“\Åk\Î*A ¦ûn;C÷/\ï[#%½+”QEz (¢Š\Ö\Ô\ï\Òþ#þ£Mi\Ö\Ô\ï\Òþ#þ£MhŠ( ƒº_Hü\ê\Òª\ÐwK\é[£:V-S\è†h¦¶d­2\ëK\0VGúi¹n4!Ô«©\0†S¼y·}\à€il+>\ZXd\åŸ\ïl[eu7c09®¤X¤E4¾´ö)!@Ìˆ`\ÃFWPÁYXj\r†›Á5\ÛM¼K¦Zqv[vf\ÑWD‘1`\Ên\ÊnE1Ò¤ \ÙòLHQ˜Ø³-\Æá¼³\Z¥ð?:\â†W;$*el\ÊË¥­\Ë6ý*\í#R©|\Ä\Ãv‡BòW«Á\èd\ë\á>£MŸ\Ö\Çk±„ú\nC©	\îE‡;i\ë·þªd@§¶nOO-¿\Ë\ëLq\ÎH²\êy|Y¡6øF–È©p\ê»þ°üƒ\ÕLq\nNó§7\'³–Ÿ ¸\'Ÿ\ÍH$YšÃŸ\Úy+l%L\ã*¢-\áb{K%\Ã!\ßrŽ¥[q\ÓM\â›mps$ˆ Zþ~»\×\Ð9*\ÆJ‡3¯s\n„‰4\í\äÜ…®\rÀºŸUW\åK\æ;÷[\ÐZõ#\ÌZ\ê9²3Š±ó\Þo5!Œ\Ù\à\ê45&`¾žUd\ÄF„„ûEtŽq“Ok‘^p“\ÇG‡s‡)“Š\\\â,:®E9Np\0/a–\í¼\å¹å«ž\Ì\ÚK+£¤ $Šl\Â\×\Þ;R9\ë5su\Ø\í)šA—$!—põ„¢\åº³jG0©-…\Äá¢™\äšU•Z>\"\0ŠcKY÷‡­9ù:\åw¡Û®=\Ï-â’¾<BX³œ¹ÿ\0q¶\í\äiþkP;Kkv\ÌI\Z\0\äzll<ý®ý-³øE•ob;£Ú‚\ÇB5…0\Ú\ê	¸u\Ô_!º°\ç½ô½Á\å®y2/ImJ×‹\è£D’V‘®sMñoKlK“¯=aP\âÉ‚¹Pýp\Zn¨N% ?yz¼\Ç\ËU.-¡?}zœJ²\Ç\ævË%eŠ(¯l\ÌQE\0\ëjw\éÿ\0Q¦´\ëjw\éÿ\0Q¦´EP\nA\Ý/¤~uhI-Uh{¥ôÎ¬\ë.¡]\Ç-%h\ÒRD\Òy«<`‰C\È\Å.²Z™$•¶j‰BÈ“D¤[MŽñ’\ã#fŠP³(‘u\Êtº\î6\ZiHÉ·%Š³\'3…!\í€\í›Lƒ}È·œ1­ñ¡™„,Ð•ÊŠ\Í*G,±Ç”\Ëu6|’;5·\å\Ò\æºbÄ§Ä•¥\îuÅ–q¢÷°ñ‰0\ÑC?Õ Q˜Ý²ƒqo§ž—\ÄCpyo¶™E%±e2ÂŽÀ¨*S\Ëÿ\0ð¦pI¶A\É\á\Å|\îY·–N^\ç\ÕcŠPIuDIK\ÙT\è;¦\ä[V\ËT¿=­\ÎyIö|m[m\Ì>\\;,kb\ä%•nlç¶°`ElË‘\"B\Ý\î5y\ÈP\Zü¼–­0Â¥~_ñ\ç÷8\Êw=Ÿ \ßiP©”f\Õ\Ü\ÛS\Ì=¦¡cŽù‡2ŸY½ÿ\0j”yx\Æ\és\0wØ›ú–þº08]mþ\Ý}y\ï]%=·}„·r†o`5´\ØÔ´\Ø\Äñ¢\Ö\'œœ¹´\Ú|Em.3\îüir-öywý\ì\ÕM\ï\Éz^)°fÁÊ›@r4$o\0ò\Ú\ã\ÛJ\Ä#6\\ül™\â˜SÀÔ‘pÙ€\×\ÍRH\ÍBÄª\æ(¼ŠZÄ‘é°­1\ËÓ‰VQ\Å —6¹¥·#]Æ»C3«G)\ãO†Šö,I\"<€8t1¡V»©1\r¸Z\ËqOñ›CŒQ˜ö\ÃC¦Œ-\Ý_\é¯>þz[‡\Í:‘.«™^YÀhB˜\Û0\"ûù¼õ]\Ã\Ïu\Zßž{iz\Ùù\á»ß³\Â\Õcôò4‡lA§X\Êj,\ËN¡’¨\ãHÎAŽ\0oª\×1¡?yzY¥¨¸÷ù‡\ïS†7’/õ/,ª+\ÔQEz\ç0¢Š([S¿Køú5§[S¿Køú5 \n(¢€R\é}#ó«+-V°ý\Òý\áùÕ \ÖMOh†3Öª)\ä\Ò\\]qREY„°¨SJ-Od\0ZOl(-U\Z.\ê)¹\ÊX\é˜.o\æ`9©\Â\Ò\È4\"\ÊCT0#¨\Ü\rE_¶²b\éš\ì^H¹cŒƒMÝ°^kú*\íÔŠ\\©«\\‹\Øý‘Ó»\×\\ÿ\0³û·Q)E\Ê#6°-§(\Þ}4\Ñ“¨\Ì\ê\Ê\×]²\ßq\"\ãB+Ž£ð\ìyž\èð\Ï_Oø„\àª\\£©\âqŠ¬\í{\Ø_}‡A4\×‰ #1%ô!Høš¢af˜µ\Û96\ÈÅ†g\\\â\à¨6 \Ùo\É\ÌMLpcª_3“#e@\ÌA9E\Õ@\Ô\è´¿%c–…\é\á¹;kýš¡­Žiìª²~H†l\à}†\Ñqo€¦X©<q“¬Šl9,¡¯­;\Æbs›€? OET‘\×*\ËMx\ã\ÎET€·-s˜\é\ç{\Ú\Ú\ç\Ó\áyTœ¸IwþÚœñÀ—\Ïþ–|F-Db\ì\0Ý©\ÜH¨\ì6Þ†ú¬\ÓG,°©.¤)\Ô\Üw\"\Â\çuª\'o8–8\"i\âO^\Ö7&G\ã	»\Û6V½üÖ¤°˜µV\âiaxCq\Í\nª³D`£3\\17,o\É[±h±E\\˜rþ&\ßTOì¯Šn$Ç†ÁN\ZOeF\â\ã\ÌIn{\ç7=GE´Ó‰\'\ËL\ÈÎ²\ægVeeP2\î:yª\"H@Å‡°Í¾$rs‹³M¹5Ò”À\"\â„j¢\êK#I>R\Ås\0l\Ö\Îm¦•¥\ã\Ã%TeŽ·$]\Øûo\í‘§b§Ôµ\çVƒ,U™c^Bnu\æÖ¢ž0¤m÷°7ú\Ø[nõR³bó35¬^Ù˜³H\ä\0\0\Ú\ç{)ÔºKlW|™^Gl\Þ\ïOV*C¥92Vy·e”‹Q[d}_¬T»5Em³õ\Ì*ø:!¢Š(¯PQ@:Úú_\Ä\Ôi­:Úú_\Ä\Ôi­\0QE˜n\í~ðü\ê\Ú\ÕSÂž\Ýo\á-ýµp“\Üÿ\0\è¬Z»µ@E\èX¯GT\Ç\á|¢•…ðnŠ\Ëñ/Q&ø\Ä_– \íV86ŒF<¥þW\èªþ\"hÁÑ¾\r\ÑTÀ\å\Êi‘$\nÔª=58„ð¾\r\ÑX¤ð¾\r\ÑZiû$’R#B\rÁ\ån\"“–$rK¢’Í˜°º©-lºk~P|Ü·l¸\Äð¾\r\Ñ[ubx_\è¢s]…øµ\0•6nÜ’Í»M\æÜœ–\ß\èf\ÃP\âå•€Œ©P\Ìû\Ï&[r{zt1i\á|¢ËŠ…c#µ°\ÞP’»Ô‘¡\Ðn¤r¸¿‰7gHI¢^2B!mY]­lÄžm/¯%¨iLa£“+e/\Å\ç\È\åT.…ûcö¥´µ)\Ç\çÐµ—‘dAË¢a\ÉM‹\Ïð5\Ù\Ê\Õ$Lò9\nD´¾J\Ñ&N\è¥!9þ¢¸»ö9˜\ÉXe \âŸ\àkP¼ÿ\0J—°1–•+E™9þ¢¶\ê¤\çøŠ‡~Ä¡aZ´´\ÞLRóü\riÇ¯?À\ÑA’.\ÓT~\ÓrW\Ö)\Ï\Z¼ÿ\0Mv“.M\r\Í\Ç=uÆªK‚ª(¢¶’QE\0\ëjw\éÿ\0Q¦´\ëjw\éÿ\0Q¦´EPG¼zEJ™*%wŠ|¹dVÌ••–›–¬fªm°–\ÉQ\í%hg\ÒÕ 5…+š²\r$\rl\Z¯´`k9© ï¥´ÿ\0žJM§\"ÿ\0gqÀžq¯&†›	¡\Õ\ëTlF–;™¿)ž\Är\ï¦\ë-ô&þ­uôQw¿5\éµ.\ËF<›<Šm~\Öü\Ç0\Z_vð=f“m¿öŠV\\.ûÛ©8’ú0*MŠX¨ÿ\0óù\Ô\ÜZ´L¡FU\ë|ô‘ˆ?-üÛ«ª\í(+šŒÔŽj3Sh\Ï[¤¬†¦\ÒE‰­kPk5IN\ÚR¦’Ÿu^=µQ]@QE\Üt,ø‰šW\nª3\Ç@¦š\Í#ude6e`U”ów\Z˜Á\íwÁ\ãŽ& Œ\Ñ\Ë-•Ád`Ù•\0ŽF4û\Ã\Ü\\’<–\Ã.v¾^¦\Â\è\0\0º–\"Ã”\Ðj*\Ç×¶/Ÿ\rý&û(\ë\ÛÏ†þ“	ý”tRù\êo¯l_>úL\'öV\Ýz\âùð\ß\Òa?²¡ @\ç©ö-€e\Â\â\Ø0XA1A.¢›m]©&!óË“0\\£$q\Ä,	;}Nµ\ÝG\rð\Æ8\Âc Œ¨Bn\ãP¹\"\âÜžÊŠÿ\0@\Æy&3úy¿¶’\Å\ì\Ù\â¦‚x”›’)#Ry®Àk]¬ð\Ú;\é´0¶±\Ó2\Ú\Ö\Zó\r}>m\Õô™Âœ>#\0bLDR¾x\ÈUpÄu6›\êh\ã°±r(xð¸¹…\Õ\Ö	]Xs‚+\Ö\Þ;È±¿\Ó\Ïýµ\Ôö`L1Œ†&Š\ÕÕ\Í\Õ\0*Eù\ïþj/\r¢ý_‡7\îe\ß²y¼\Þz\é\é¤úBŠ_ª8\Ñ\à\Ö<ÿ\0\ácµ\ßÿ\0O>¾žÖ‘bNd1q—QwŒE#H£”Š\í¸~\Â-›‡kZú­Ûžÿ\0†\ëk³8]†]£Œ—ª\"Q,P°\nJf\Íbt$\\QÇ‡\ÃD©ò¼œÃ­\ÜPÿ\0\Ä\Æþ<ú|µ§útÑ¸¡ZN÷E\"\Èÿ\0uH¹õWfN\Z\Æ˜ü-õµ\Ø8×»\\ûGD.\ß\á^ö†Ï˜b\"a	ŸŒp\àªfŽ\Ã1\Zš\ä\ãjŽª\\œô\ìl_’c7y<ÿ\0\ÛLq;6u‘\á\Ä#I¢#E\"3\ÖU\"í¿“ž»i\á´9‰\\~\Z\Ä\è¦E9F›½ŸŸ=Bp“…¸g\Æ\ì\é\"7I)w@\È\0,FƒP*\è9·\Ù\Ì\æ\àö(…¶,-ÿ\0m>¾s\ÚÐ›4\êLu€#þ\ÞV\×Öº\n\ìóð\ÞN\\v|b\æQnNMÿ\0Ÿš\ÇC\ÃhÀ6\Ç\áI¾—t\Ç£ÿ\0|÷¢­œPlÙƒ¬M¢VDFx\Ò5·ik‘¡\ÖÜ†–ÿ\0B\ÆZ\ÝGŒ¾›°ÒŽ{\Üå®›Š\án\íxqxŠ®\ã2g,m\ÃBjg¯h6\Ç\álI\"ò) yó_@\ïJ \â˜Ý—ˆŒ“\r‰®\ï‘}5%m¾³\Ä\Å2†L.)Õ€*\ËÌ¬\â[][…\Ü-‚\\\"Š‚Vu´jŒ.uR¢\×\Ô\ÜSü\r0\â:¦6\Äp¢²–\\\ÆÑª€o¸‹sR8ð\Ø\Ï$\ÆO7öÖ˜•ˆKË‡\ÄÆ£{¼2¢‹\è.\ÄXWjnEÉ´0£w\ÚCmu#^jŠ\áw —g\âb8¨%w[\"£)\'¶R¢\Ãy\Ó}F\Ô8Z’•´¬f­X\Ô$\r(¢Š¸\n(¢€uµ;ô¿ˆÿ\0¨\ÓZ( \n(¢€(¢Š\0¢Š(Š( \n(¢€\Í\è½P¬Q@›\Ö( \n\Í\ëP¢±E\0QEEP½bŠ(Š( \n(¢€ÿ\Ù','ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\Û\0„\0	( \Z%!1!%)+...383-7(-.+\n\n\n\r\Z/% %---------------------------------------/----------ÿÀ\0\0±\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\0H\0		\0\0\0!1A\"Qaq2‘¡±#BRÁð3r4bs‚²\Â\Ñ5ƒ’\áñCDct“¢³´\Ãÿ\Ä\0\Z\0\0\0\0\0\0\0\0\0\0\0\0\0ÿ\Ä\07\0\0\0\0\0!1A\"Qaq2‘ð¡±Á\Ñ\áñ#3BCR’$ÿ\Ú\0\0\0?\0üý\Û5179JœŠf\Ñk>\âQ\Ì$å¹ \Ü@–¬\Å¥r&2‹Kl3e(nKœª÷’\ç\Û*9\\—\ËV™>i©\àb4õ}\áw4öª·\Ã\Ø\'µYg\Ïgi–a­\âŠù-·j­²\ÑÅµš\à†þR,Ñ·Ur\Î=º«ä³‰knE WÒŽJ¼3ZW\0º*\Ñ\ØZ©i”\ØH\ã\n‰–\Ð\Å]À9yj\Ó!‹4W =J\æ¢d`\Õj6D|›‚˜M”-–QqIr±”\â\nL\årƒ-$´Ea\\\ÐJ«”Í…ª¹H2&)m…s@Up‹n¢-3¢\Îj›+q\Óf¢a\Äùlæ£¨oƒKSe\Üj\ÏLf\ÌWB…5vZ¹\ÓöóZ;«rBz­0“$†XUt²&•»!XÕ­%S™º\\\Ôs*\Ç³\æ©H#o¦ª\ÜK¹¤\ÅZw%.˜\Å[l¤\'¨µM„Šh¨\\Ö´ðP½Õ«L£¶ImE%–Œ¸¨‚>Z°»¼T\\\r³š6R1{šˆ¶eEY\Í\Ze0ô-„<D\ÖYH\Èv(\n\re&‚L$ƒ„¥ÜŒ\ÐZ¦Á6†\ä©@\ÙÛ³B\äA»:j>W,U³\rn¤°‡,±bMfœÀ”²?¥Ò\Âx¢Ó»\Í\\´z\Í€0U\é\è\Ç\Z€už˜¤bfŠ¬SˆG•\Ô\Ø<Wr³°\åÁÄ³á¤·Y·\Í™\"møª6C¾*ˆ	r\Z\å¾*\"[ ˆ­We\rZ\él\â¶~\è\Éªôò†{RgI\Ã%ž[Xž*t|Š^Z´Á3hf‰–4%\ã\nKuN@ºµqdðª÷d$š+–\Í=š¥\"$\nÝº\'+Í”ª\Üd\Ú\\ý\Ì\Î\Ð™¦_\0u\rhE.E…„¦Í…¡¸7–\è‹i)re\\jÍº¨¼t.*\çR\Å\ØÕ»SR5P\È#O¦ ©;†Ù«:h¬ù¤®\Ãm­tca©—¤\Û½žOmƒ\rÔ­Àó¦UºŽ¹\æõ³\\y¬Œ@/Y…¡h;ƒµcªQ\"`\í\é³5{0UÁ4µ\\`S\n\Úbpj\ã»\ãL\ËÚ›8°nU\ÑÜ•®ŒgtDJüA·i¡ªÓ‰g\çš\ÕñVxð…/%R\'tve©—¹®iñC$X›R\Ë\Ói\åf®À\ÔZ‚*’ \ÇöJ¹\"[³\â5Q\É_±Š§†¤³\"™`Y»–3K½‚A¾$\Öm¶3\\È«AQh[(*¥dc\Ó4¶Á	J¹w7mh[\ãv\éM„†\ÑiM„†\í$R÷´\Ç \ÛE:9L\×\Â\ÅhQ²*\'-\Ø ‚i°{]Æ£\Õtò»k·B¬\\p\ÍuÁ\Ù\ÕIÇŸ¾²x®\\äœ‡EÜµ8 ¸v:lâ­²XÍ»u.K[9©K\è\í	4ú-\\Š\'O5©+a=V˜–†PwÁh…®\Ð\Ül\ÔT¦\È\Ù\âú‚¯ES‹ŠÈ±{–\ë2–Jaúfœ–0)\ÔäŠ¹L\éÊŽS‰w!ê¬\ÄE\Ñ\n\Ú/”\n7ÁgušpvŸZ¤ò]†¬ 5Re\í[\æª«\Õ\'€\Ôd°¿MH\ÕÁV\r¨µšS‰‹B+\æ! bÔš-\ØŒ%ˆ¥¹\ÜKt·\"˜[iB\Ø#“Jlƒ¶:q1I•t‚Pl­§\é\ê£\"²N³c•4òEØ¹`z+¸kJ“Z¨\Ç*÷[U¡dj2}h¬\Ì-ó0´\ÚP•ðckdžL\Ö\ÅI¾XÄ€^ð˜™S°W\Ðô–q$@ü\èl\âªtPj-‘\ÈWQ\Òý*¬E!4\éM»#Q…\Â\Þ3þiòÁ¦(>€¹·¤e0\ÜÖªq}J½Æ—B;\Ö\ÈA \×ô\á*NVW@\î<\âŠ­žõ\Í\Ô\ê\ã\ä[–O:z6k›ø¨Ø§2ŸN\Ò\íIz§p\\Š\Z‹(±¶Zgœ\Ô\èe­iŽ¡ò>&¬\èÈ§S\Õ]Ù‡a›šYú’\Å\Ñ.rÍ¡I†£vw\Õ\Û\ÚgÎ¯{ŒŠºsˆó§:ª\ÄS¶Š•[\à–5rüš72Žü^Õ‡i\ÈgN´¹²\Ó	RÜ™n7\ÓZ[\éé¢¹4\Âež+6¦œ#” †´š\rq\êUM\0¡’›‚Me^\'\ØXõ,`S;€;\Ñcpµ7jˆ¶\îÅƒT““²\Å]6£Š\ß\ZnÁEôý=ˆÉ¦¨\ÙŒ=gL`1V•‚\ØÅº_Mi2\r>œ¬\\b\\þ\Â\"˜ª\Ù\äqJ÷DÕºŠ@\Øô¶,\0*+°®¢ŠM\" E\"sI­‹Bi\ëZE±Ñ§Z\ß	.@bš­8š\Ó¢!EÐ¸§wÑ±M:ŸT\0@5\Í\Ökch±3g™½­“šóòR›»b\\”†¥»¢\0¿+\Å2>.Jb\×/c\'4\Å\"”€Úº	£qi\Z\á<\È4¼¡Šw3u«Tu\Öe1»\rš¸»;”˜.£1Zœ“‰·fiN@\ï>»¦‘M \ï ·\ï)SµÁ²\î7mk™ŒcŠ_$A¬9É§Ó’ˆ\Ô\Êú$óR¦®IxP[\Ê\Ö,\\Zú‰É8º«Yv¶^\ä€j†\ê8xE\Ï l\é\'\Ç6\Þ¨Œ®†(ûª¯ [J}E¹³Ú·\é¨5\Éq\Ùé“§ˆ\âº*°\åœ4¹G!D| \"Š1LeÂ¦™@(\ÜRX(\Í\Û@Š\É5pŒÛ¶WÚ…)A\\—7s\\\0 –©$S@·UÆ»dB\×õ\àVE{d4O\Ô|^•ËŽ®jw|4\Ê)\Ôk«G\\­\Én(CQ\ÕÐ“´­b|L\ÔjK\àQ÷\ÒhÐ•\î˜\Î+-V\Ú\Â\árq\èl9(+üB%M£‹£\Úb™RŒe,°k] ¸\æ™\"¶\n“<ùÒ’L\Ò\Ü\\@Yº#8ªSÝ„¹³¤u9¦*NA\Æm\Üf\r\rJ[wu:	Y\Ó(Ñ“#¨„\ßcR¢qv)\Î\çn@µ–E’kjó®q\ÈØ£\ç±9­J®3‰XÙ‘¡›V·b‚ö(õ==RÊ€G¾8Ÿ:(\ê£“GJgU©\r\Ë\ä¼Í–P\Æe\ÔT§SŒ»:·+€W\î3Dqù\ÖhRŠá‰­¢¯vº\Ûlª›š\\\ã>Ww\rl°\Â\ÍJP\Þ\ìY)h\íˆ\â»:m$câ†®&\á[\ÜA´;\Ð\í\íoz¨$H«ˆ¸Š“š°\Ûu\Îdk\ZžE\Í\Ðõ\àƒ\ïOÜ¬*3e\Í=ó¥Îµø«Ÿ\\\Ô+/z§ÀL\Êj1N†VJ¸ˆ\r1i£!nBz“š½-œ“µ&±N–\åfS˜§ÄŽõÊ©Kk³\"˜ž¢ý\Æ18£„ ‘;\Æ\Ã\ét¾bk«B±|ŽÛ·µ«LÚŠ)a•4×…eub11‹Î¦ƒ¾]i3\Îõ4ÌŽ\Õ#RM¦™–¤Dþ,s]h×ŠFk1–Äš\ä\ê*§7`’9i@\íK¥Q§`¬?n\Ò®\Ý¬Q3¨i\È\íXµrIÜ«{\Â>•£O]8Ý”\Ñ)\ÄñYkT\Ý ’%u±ŠmŒH›f\Ñ&kK—A\ÑcƒP);Ym‹[º&;Ž}\'Št©Kj—F\"Ï’¿L\Âk-x\Ê1º\"E\ë\è\Ø*\Ø€\ãÖ°Bq–&tiö•Zq\Ûdþ·ýH\Úþ¤t\åwuÏˆp\'÷};ó[c¥US\ÚÑ¦=±u˜ô\Ý×†ðYT\à\0\ãÓ¿½e®£I\íyc%Ú±¶#r›iñ“1šÎ«5\Ç7U¨uùI\Z°Œ[ŠÓ§]Lk’¢[#Š\ÙøŽ\ÈjF‘Œ\Ð~*rv#,h€i±®\â²\\OµZÒ£¬ºc\è\ìgGe®dŠ”%)+•\ÉE4‘Úµ5\"Y.P6\Ò\È\Ä@\ë·öƒ5Î­&ž\nŸ½.µ‚‚L™£üL\é¬\ZtZH×Ž\æþþò6X•q\ê[¨ö”U”ü¾Ej{6¬[\îò¯ùYg\ï\ÈYõ\äóZ\êÕH^\'&Q9m’þýŒ›À\Ö4AkÚ‘1\\\Êñr•\ÐI¤oJŒÖ&‚U²ð‚ŽYzÖ“w0}N+\Ð.\Ëq’4wlGZŽÀFSÁ€xõ®V·Eª§7\ÈBI]¡½ \Ò9\å\\IÒ”]¦…»£-\Ôbj*ew¬ÀÔ†Á4\Ø\Þ>\Ä\Ýs7\Ê\ÄS\'UË‚šB,ÙªK\íp±O\Ó\Ò\Ý!œ Owik·’²hµ\ÍV\ã\\½K\Ý0l-¨\ÍH%1+]>—t®\ÃHówH\Ó\é]AG€öƒÐœE*4\Õ\É+£\ë\Ö\Ó{”.\ì[¨ô\Ök‰ð\Ûk4\î\æ6¨,}d¡©PƒÜ¸\à2¾–ÁU†¹½‡®G~\'Ö¦§UV¼/&\Ú÷\nNRYe.²0MrgJùFsJ\à*$~ý¿ÿ\0\"Ó´¾7\è\É\ÐkO\ÔJ\â‘V–ü²£;ze¯‰É¤ÂŠržM QÚºp¦¢¬\Zˆ\Å\Í\'¥&qw´©³µ\è#…I\Ó\êsA&Û±qM»!\Ý\rµº|$\èA©F–ùY\Óø\ÓG£±§\n0+³J‚H\Î\ÞO\î\Ð\rˆ\ê5¬õ!‚Ô¬yþ¡ûC´ErªR\ß-©\'|µzF\"\n`pA\ÈûfŽ¦š¤Va‚©Õ­A\î¦\í÷\ÔóúŽ©n\Í\ï…q˜n!\Ã6AÀòõö¤KI)C|¡\Û\Óv\Äeª,ñ‚­X2 Á\Î\ègˆŠ\Íò›M>:\êÐ¥¨ƒX\â\ß3º¥*Sƒ\Éò>TþñU‹|?#\Ì\ë4M$²×›ó¸LR\áR\Ê\Æ6Š]/J\ç.\n/˜@$ð\Äý+\Òö]\Zµ>(´¼\ìj\Ó\Ór\ZÔ¾šJ¹¸Å²\n,\Î\Ñ6\Î@_\Æf½ôó©›\Ç_\Ñ\ÓTj¸«?O!>™z\ÒðŸö€\r\á” œŽ\'w\Êü¥AÊŽ£j²m4¸·_\ÌL\éÕµ÷b\×0ú\ëd\í\Þy\Zó\ïý;<:Ua+ñgo\Õ~FIi\çlY‚øsAS±õ]ekñ”ÿ\0C5Js§m\Ê\×\ÔZ+šEm+†W\0&,Ú±Sw²\0jgƒG\Ý\Ø$¬\î\Ïz‘\Ý\nl%\Åi‹Q%‚\í‚z	š¹dS\ä\Ò0-EMe‹v@]ŠUv¡‰‘ú­G455‹‚n<Ó¤£¥]>I¸*ß­[‘„ÿ\0´32A#j‘\Ú	\ÏùW;b‹K\æÁ¸w²ž\ìYÀ\ä	\ær~\Ô*RWÚ¬¿B\\=\æb%GŸ\éJŠŠ~!x¸\rº\ë©Þ˜¦N +	µ9Ò§Z/6ý‚v\èV@f°\Ú\èORÝU\Ëj\r°¤xO<Ò©I)=Æ¨ð{^™«%pw\0\È§¾]¡ûš‘§R\ã.yŽ³«ƒŠ\Éyˆ©#\ÈuNº·l²[}—d\r¯‚H?,}³ÇŸ®¿\Ã\ÅN\í6†\é\æ\á+\Å\Ú]Ÿ\ä\Ù5B\çÇ¾»[D‘.I\Ø\r§4MR£+¦j–®¶¢%“ôs\ÕR$05²–¢W‹2T„ \í$%¨\êSÞ)\ã7+\ä…\Ôú\Ã&\nÀ<Áü±\\*¡VM¥z>NõÊ§R7\ßk®uym\Ä0ò\æ}<\ë#•mÉŽŸaÁ¿°öžû2ü\Ä÷\íù\Ål£\Ús‡†¡‡SÙ³£˜»¬ó¡;«hm\Üp]CU[0\ÄËŸ|\éŽ®·j\ÛI+<K\" \0\0\àô®d¦Û»d\ç \Ý\â‰r\\§)|N\ç\ÖA|“Š\èi4³»\à(Sr\Ö\ëÔ…¶÷²¹]\ÆH˜\î{b»“œ\è\Ó\î\Õ^}Ÿ¶Zvô5)NšqŒ¹\Ôk\ä¶\áZ\Þ\àH\"F\îG\Åf§Ú•U©V\â<ú\Û9ó:™|2|\ê=k\á\Üð\Æ\Ö1#™\Ñ\Òv¾žmGkIpü—ð65\ã{]™\ÕÜµu…\Ø;†ý({vÑ¥	Bv},–W\Ë\È\rLœIœ\Óõ„S³\Äy\ÇÒ¸”«UÙ¶n\ëžz˜e¹¬±«Ú°\ÃSÔ”Ð«Œ®§GežxDOJ\ëP§¡¥«7\ë\ÉÒ¡\Ü\Û/ 5«bý²È–\í\\b\03\èz\Ñ=>›UGÂ’o­º«J›«’\îi…ˆø· ÄŒ¬Oo½eúm\É^Uö_\Úž‚rW¹õ­e·0&ˆ ™‰ÁúMb\Öÿ\0§k\é\é÷{’\ç£ú]\Ý~~…U\ÑÔ¦·r…uW¹ž\ÕÅ„Lœ°ofE5¦ˆðkYÖ¶òi‘„\æ\á[T0\æ©i\Úa\"=\Ý`bkdiX7`cS\ëLQ`\\wN«1´`KÊ–\îK|X5»™3ßŒý\Å\"S!M£ú\Ïj9Bö”V\nJFR\Ó2 \Êÿ\0¨\à\Ð\ßl›°jè©¥]\Ê˜Œ7¸ÁüÁ¤Ô¼gn‚š°\îƒXKÀY\Ë\Ü\Özð‡u\Înd\îz5ó\í\\\ÇU®\r	š\Ôjš1C\ÞN\\²9µ\ì@.rGŸ½l\ÓY\Í\'À‰6\Èz*ü\"Ê¾0\ÌÍ»v\Ö\Ü	ôœ{\n\Ý\Z’\ï6·‹$‡\ïRS~Ÿ¹\èW¨þ\ÎÝ…²\Ç	É¬5T¤Ü§\ÅÙ¿³õ”¨É©ðµ¯k\ÂÁ€&Dñ=\à\ÕF³w”ôº—{®4ýM°fGzt5iË›‰©\Ù4*GÃËº\Ñu6>A\í\È«\åL–ªK½ŽS\å\å\ÙÚ<Û¡,[©\çzGL»ñ\Üp\ÖÕˆ/‰ŸA\â?®µ8ÁlYf\Ú:Šm«\äô-¨?hs\n¤d\Änòž9ò«\Ó\èuV\èE\Éñ\í\ï\ëú«_QUx\ä\Ú\'j×•#\ß\ÙvN®+t Ì®\æ\Å-7Hm»¯1µ&\í–>Dg¿¤\×KMþžH\î¨\í\ékþwOK)s€\Z¾’„\rš…$˜\nË°÷\æO8ò§\Ïý98&\ã?kÿ\0?\Ñs\ÒI\\-®…a-†¹¨i˜ðn[r\Äm\ßXÁ\í g5¿O\Ù=\Ô-W? ú$•®¬tÀ¨¸U\àm…À¤³Ïµ¶“„¶\ÝúdD%Ÿ\'šWw\'\éšó5“s·¹\ÍÝœ†½xÝ»À\ä\Ç\Î+=:on\Øõ*R\Ë6n3¥´Y\ìg·rM2\Z•§\Ý\æ\ã\"·Y›E¦ÒZÀ¹r<D\ä,õ\ít½™Fœk?~gRŽŽo vÆ¢\à0¨W. …\"\É¢\Ô\è)NQš\\<ôºû\ÉU´pº’X\ê\Z½*6Ádf\î)h\æ\'i&\"A¬±ŸgQ©\ÝÔŠº¿9þý‹…-,¥\ÐJÅ¶³do €v\Õ’ù9\É\Åoz}=\Þ\ÑKr\\_\Íò\ì\íúô	Ñ¥ÎŸ6\âþ¡º²[\0»‹„`5\Ä>\ÙùTn8ú\ä\éM¸]\Ë\Ö\éñ\ío\Ï´ü<ú’mkô\ãuÄ´K´›±¹ À;ðš)\ë)¨©_\â\áóõôó`Nj\Ës\ç\ÈV\î¥.’¯µ	2\nü`ŒÖ³U\ì\í\nI?8\ØEM-9b<úu7E°\n¸u\È\ì>DO1\\we:’wOïƒ¨£*v¿\Ï\ï\ZI\Åf„6«#*~\Î\ÓÉ¢	Þ½Q\Í]\0\Ú,DÑ´‘˜¬÷3è¿¯õü¨ôô©m¼T\ã²º\Ù\áù¿OzÓ§\ÑÑ•Kš(P„¤e:³\à’1]\Øi4ñ…¬u¡¥¢–PÍ¾¬\Ä\î\Î>\ÜSc£Ó¸mÛƒLt”%X±\Óu\È\Û\Ë’\Ñh\ÚsÛ€~µ\æ;S±Ü¤&•Ž.³AixKú>¡§\ÜQHX\0äªƒ3Žy\\\rG`\ëT£\ãmð¹K\Ï>fi§¾\'–k‡*#\'	E§\ålýpt¶)v\Ô\ï‰PH‰\Ü}–Hÿ\0\î\Ûö5\Ð\ÓÓ–\ÙJÞŸ_\èLš$õ\Æ J‘ûA´ö\ÌýOÚµi›O§\î·%Š\ïµJ¯¤–U_S$\ç·\åA^†÷–’÷_¸\èÒ¨\ã¹E\Ûüÿ\0¹Õ´¢ý†·¼LN\å\Î\Ò8\Ïz\ÍB•d“O\Ï\Ë\Ú\å\Æme t]Ay¿g$\Çb7k¯ieQ6ñ\Ö\Ëü\Z¡*x”‹}7N……›mw|7ñD‘\ÄV\Ø\Ñ\ìé½²·\Î\éÿ\0Fn}¬ò”î¡•\Z\ïaò\â;y\Ì¯h<I\écF½\àÔ•ñ\Ö\Ë\Ö\Ö8šŠ½\åG&E\×\Ý‘p“$„fB|÷ÿ\0zö—ª¡m‘Ú¥Ï…8\ßÿ\0\×Qºz«Œ\é\à\\Ûµ/`3\ãƒ\'\é\Äö®º’Š²\à\Ùk ¿‰u©fú­\ïŠSg…¶»ò6\ìm£\0\rsµ\ZøÑ§«][9õJŸ…¬ª|K2\Ì~$vD+»q$\Éº]¥*ñ\Û÷.Zµ¾\ß\èT+\îVŠwüºOT@¬¬X‰\0¨\Ý{\ç˜µ×ŒH5tüð\Ín;\Öš~½h\Û\Ùlmx\ÜlƒÀø¹?<\ÖIPU…9mv\Çøfy\Ær\Âb­ª\ï%‚·\'$n> ÷®.\Æq“\ïjã†¸_¿sœ´\Ùñ	\êï­ ®QYfI˜3Ž+WûM)\êöSKe“y­ú†š3©¶<:RP°BEÆƒ1Ìœcù\Ñ\èt°†¦jŸ	uË¿ô]\nV¨\ÔxEn½b\âÀ\ç> \Û\Äúž\ÞÕ›S\Ú:\Ý<ó\'×•ú+–¢¬:\n¶¸Ê³ \àdœq\Å\\;rxSŽ=\Åþ.\\4NÕ–mð€#\éŸ½y\íL\ã=C©e\å\îem^\à–\Ê\í%¾X˜ÌŸ,\n\×\ÙzhV­(Õ¾\ÕÍ¹ô\ÇP©\Å7fK\êY±l»0ÿ\0b&}½«\Õ\ê§ðªV¶å„¼ñ›cŽ~F\é\ÉY(ù‡m;²ûIb ‰ñ@$“9žÙ¤vœ\ã\Z\nM¨\É6¸v\ã=0\ß\ÏË¨\Z‰\Çbk\r\\“v\Û+n?7¬žÑŸ\ëÊ¸”uu)\ÉMJ\ï\×&U”spw‘¤gƒ>u»ý\â«V•šò¶–ª£\Ã\Ê\Ð\é\Êóš\ÎÚ±—\"\í,\äGµ\Ü\ëúS\àˆ¼\ØÊºT´¤“H\×\n%#V4D0=È¤\ËA©o\à`-=Oþ¡ô\Z\ÞÊ‡|ýÈž+7uýJÅ¨¯0\×MµFh\É\íA\î­Z(·Z\×À\ê\Ó$\Ýy9­õ+8K©j\\]†ô÷Bˆ<\×zHww;Z}L6\\¥\Ñ\î(oùþ•›Á[1y*©˜¼-“pº¼p$\âI\ï5\È\Ô\é\ëJ®\ÙF\é.S\Ûô\Ã9\ZžòS³Wü‹u¢Ý½¢\ê“*JŸ\ÝÀ·ly\×\Z¾‡[=J®¡µ®·\ç\×\ï\èc\î\ê\î\ÝaÎ\Ô\Ë/\í\r¢{›s9\ÌO\ÐV-WgTµ•%—ñfùövú¢¥	¥˜?]w?6¤¢7s>±\á\Í.\çþ9¬ý\à\Ë%~G:\Ò©im©f,¡D…‰}¹\â²i—w)\Êo…’\ÒÝ„rZ\Ë™+D2ú\Ã{\Ì\ÑTŠ¨·Z\ßÁ\ë;>P†S“ºw_}K\Z®\æ\ÞÀ\åU\ãr€¤m,\Äûÿ\0õ¤©N•\â¸\ê]]	+\Åt²_;•ô½AVIT\È\"``÷#óT\ë\Æ[rýO=ª\ÑT¡/­¼saþ‘£´¶­½ÀKlf\n¢<3ÿ\0¬ñZ\Ý]4S\ïa&þ‹­\ìÿ\0Ÿ0¤Ÿ‰™»øu.KØ½\r9Ã‚pyQ>|MlfF¼T\àœ=¿\éü—*	ð/o¦Þ·-p¶¢\ØÁKWÝ\Äó\î(¨ö}Jt¼qò‹\ê)Ñ”]Ú¹#Y\ÖÝ»\Ë¬Ã¿\ÃGB\ë,w\×Ê¼÷\Ñz\ÉB\Ã^\ê÷÷Ùª\Z•Y‰\é¿üX·~\Öû\'\ÂT\Ãp\à`÷T¸ö9-•£t-\ëa5iD‹bÂ²¬¯m\ß|ý\ë—mZ\'\ÜñõùˆiBOh\Ú\é>\ï	)ÌLÈ˜\ì\çLZ\ímE¹6º;]q\×\Ü7¨¬\ãsmv‘®1\0ø”*\än3;»>uÔ…¡j*´¹V\ç\ë\Ðt)I\æriµ2\Ý\Ä·øƒ»¶óˆ\'ó‚<¨k\Ðð\ïS\ï-Ñ¼Š­§š[£+¡[w”¤m”Œ\à\0\ÒÀù\ÈýkŸ¦\×V¡ºÏ›/o?Q4\ë¸\ß!†žË’\à¨30\0\ï¶G>µ\Ù\Ój\é(Ú” ›ç›¯k\Ú\æºuRX°]>¨®\ëb\Ùu?6y‚	8\"pk©©P\ÔÁS”7/;¬zš*UU#f†¡J·\Ç\æ|«ƒ«\ìwN›©I\î²\ÊxfGKÉ‚ø¦Y\r\ÉÀ\Çbk\Í\ÑUkIRVW}ptŠ•JM\Â\íô}\Ö\Úaü‚° )’#=«\×vvi¡>öo\ÕE\Ùq×‡\èfŽ–t\ãºwH—zÅ…oŠ¨L@qº(Nö“Js\ÐÖ«\ntœ•ñä¬ºùÜŽ”d\Ö\×\É\Û}Qd…R\"6©<–€û\èÒµºò­H\ÎR•–µø\ç¦Y²3Õ¢÷4\ÝÙ]¯ˆ!s\ç\êN{W?öZ9¿v¼„¯ºZP–?Å€ Iþ±\Íz\Ý\'gi\é\ÓOP““ó|zaôó4Ó£^@ôZ´pp8ÁLŸ(\Ô\Õ\è´ó£\'AZItwùu%J0\Ûx\n¥§eQ¿ð’+BÕœ\"\ï\ìf‚•îº…€\ÐH\È#ÿ\0C\çN]r\Å]\Øó\êV\âM\à]ô\Ö\É$‰\'\ß\íR§ij¦\ïº\ÅKQUõ;§\ê—v\Ðw\ád}Gz\Å*3v\Üð!±­%ûdm\à61\ßÒ—QTó\ä—\Õt\È\×7…¨&?J*$¡gÔ»Œhú:¯ˆ¸‚H\nw”Ö‰v\Åu\î?_\Èr\ÔKn\Ô%¯+œ	®\×gj¡(\í|±\Ô+\Ù\í\Ð(Ko¶`Ä·¡0ò;\Ó{R›’RR\ã¥\Æ\ê¤\å\Ôy:e–ø¸%e§\ÈúW#ýÖ½;F÷_xGU4­p_\æ\å\ÂO\Ðö­\Ð\í•5¶GCñªKk:U€&Aü©:\ÉQœ7\è\ÉZ0’¸Õ»{Bli\nÄœ‚\Êv\'¼f—\Ùt©¶ûÔ®V†4”šw¶ŽŠ&“Ÿ6\æk\ÐT\Óiò\ÚV;N…$\î†:k\é\Ðz\åÈ’eQHC\ÏÚ¹:¾\É\Ò\ÍnXö%J’§â¼\ËwÐ‹mq.–‚Ž£hgNÒªNØƒ3qšòZ®\Î\îg¹f<\'û›n­\ÖYVö\ã\ëÈŸZ\êW¾;•€û©rT_.\0÷\í1K§\nnv“vw\Åú[ò9ê´°J°\áIb˜«	\Þ\Ég\ÄÄ<ºž¢1ŽÄºyõó\Ç\è\âeb¯I\ëBÕ¨Aû`c~|J0w¶\é\'1\Åt)k)\Ùs~}†§n$®\Ç[»·V¤ƒ¾H.²6±\Ç2\"CT\Ü%[7yö\ábß¥\Í­š\"\ç\Â\Z}\äZ°/|\Å-\ÚÃ‰²\Î9˜\" È®¿\á\éQñB9_oC\\hR‚¼b@\èýi\ï|+h­‹2\\\ÛR7aFð`€A™a\\o\ÅJ\Ê\Ñ]z¾?>œyô2÷—\á¾¼¦˜¥»¬žVØ“$–ø…X\àˆ-2ô¢©JpKeF¬³7›¿\ÕØ©øcdþú“†±\ï7	ˆrŽc`vò\ãŠ\çj53rNOcï©žm\Ë,Õ›\Å\Ê[Wr\à\0cÂ­\Èö\Ü1\æiu\ëN4\Òoü\Ä\Þo\Ãr}\Ý{„¨’Ið¨9>@/\åR4›º\êN;^\Ä2m²§>\\ò(”;–±“hjÈ¾\ÂKn˜\ã\Û§ýÖ¥\ít¼‚i¬ô\Ín¢]\rµv–&|j«\ÛU$”r\×AÎ»m;SKqÑ‰\Ô\Éftrqúš\Å.Ç«Qo§\Ó\Í\ç\ï\Üôz-\\6ZK$[p\í°\0Dü\Ås\çZªNR“\Ï\'Jz5\ÎQFš\Ó1Yi8*„ù8¡¥:Š\Í$e\Ôvu\ÞqÃ·—¯´Û´P–} \0<F	ú\×I^ºJ\Ý~o\Ñy¯¶ye9\ÂN=G,k\Ö\ËC\Ë\Ã\0=\ày\Ö\×þŒá¾¬ö·œZ\ß~Æ…£¼o\'fs[­Óº:¬òL\Ãq4u;\ZµW\Z·Q\ÂÅ½ú\æ\å=Gk>´š:o„ŒLvžsM‡cÊMÑ©F°üú—øY\ÅòR\×Ø»t)[Š€Žò`÷&µÿ\0ýQ\ãj\éÀj5Z\èyû-r\ß\Ïu$fD	4˜GX¢Ú¶_W÷€ª\rEös §–­b­ ¯Z[\Ú_!2£Q»\Ø.!¼,W\ìQ\\Y¼e\\\Ì\âP\Ïl\ÛX7ÿ\0”ÿ\0Ö—¯,U˜‚8ÜŒ‚aúÖ†›ÁE=gTðB/‡[\Ìy}…f§C\Å\âyŠ:v;\ße€\ï\ï[{©m\\p^\æVxøe‰P\ÊÃ‰‚8ÿ\0Å»ZGy9I:ž¿B;±ý©d\ÜT\Ë7\Ê\Æ000G$ú\Ò\ê»A+›šº\Î\\A\á\Ì\ÌÎ³\Çk´_!Ý˜\Ò\Þ!g\Ê>†ph\æ\Û\Ã½v°\ê\è\ÍË¦ò,J\Â8\çvjš*sµ¿œ¾\ï\Ï\Ò\Ô$ÁúúR\éö…Jž,\Z#Vo\ÔZ·Œ\'<A\Î<\ëm=eJwŽ\ëUe\\o¤_øMu¾d`\Ë22•¸\äO¡\äw¤WñÁ½\Ö\áû´\"|\Ünæ½¯D\æJ±0Wp\Ï?0Z\ål\Ù)\ß\Ûöý\09©I’X™óƒùóU	[„-¢m‘šNÕ–o(\Ä9$€=Mt!6ðM—[º\Öiœ)º¾%lÂƒ1qGoB&T\å±ý\Ø\Û_I8mk)«\á\Ñ\ë\îÝ”fp6\íN Àž2\ØWGI^´&¤§{+y\à\Ê\çZŒ“iùdkðùDÓ‚H\Ù0xQU\\{©o÷¼\é½\äœ/{¥›|­õ·\Ú*¥FÒ‹\"õ\íZÝ¸6AÁú\ç$\Ï\ëB—\ß\ë\Ó\è­\î*\ï„)§\'r&<\Æ=\rg©8\ÚÏ‘“*\ë˜ 7£\ÃImª—\Îñ´\\Á™)\ám\Ó\Ì\ÒöS\â2vè¼ºÿ\0Žl^\ç`ª\ÚÓ›¬Ws»Fx™a \Ìw\íÁ®®—QNŽ\éµvøû°\Ý=XÒ»’¸Ö³ñmÛ³´4 ¼\0; g\æšf£´#\'\áV\âÏ¯¯\Ôe]J—\Ã:\É\0ZB%Œ(rJ\Æ\îx#Þ±\×Ó­C\ßN7^¶ý¬&¬¡k›\è¯ð²Å†óF\0úóŸJ\r5-Gyhøc\Õõ±)óƒZ½Hº|<\'Á@\É?\×5\Ó\Ôj¡¹I\á\Ë	<ÿ\0&žñ$\Ûó\Â#h4vm\Ý,\îB\É!™ˆ™Ï­?C£¥Z_ò¯e\Ïc´\ÖR\Ëùu\rYfX¶°\Í\'\×4™Â“4ÚŠI\Þ\Ö\Æ_\ì{)8§&±œ‹\Þ\nOpH#\0\È\àGiïƒŠ\ÝR¦š‡vñg˜òJ³¤¥¸\ÎW Æ‘¶\Ãå¼±ñ÷­_‹IFš’\Ï/úó\ZÑœ”Sù“/\Û!\ÈPLfs\ÔùV•QA\Ê-uþ‘§~\Û\Åtñ9˜¶<\ér«%™4…¹Ir\Ê6õWcd\àyŠwx§\Â\à­\ÊYHø\é‘ñ3\ìb§w	¿$qLV\í»`œ7\Þjœ ŸR’K©Gn\èt2}G˜÷¯\é4¬snžCõ%š\ç\×\íI§\r’\"Á2Ýòd\ÉòýkKÜ¢Šk!¬\È\Ã\rÀb)s~+¼\Û\Ñ\njQ\\ˆI=ø\nr–xkC¶õ\0•*±ÿ\0[™ý~ô½‹7W\"\Ü\ÑSJ«µˆ\Â c¾I>B°\ÍI¼ðUŽd¦\ÎU*V–\à®; ºˆ,ÄŸjm\î“\ÊO\×öf\ì\èv\á¾ ºx»ðI\0c\ïM\×h[§‰\'üŒtöðU\Õ\Ýmcƒœw\ËÒ¸0§(¼r¾‘­>17.9øhHÚŠw\îHÏ¡Œ—MT\ï,¾²ú·÷Ám\Ùg‘~¥\ÓÀekm£>GýR;UÑ®\ÜZš\Ã)nh[\â¶€c\æ‘#\ÅB˜ô@Þ¦¸§6\Ë>¹\Ô¬)}Þª\"<6=\êFƒ\Ýv±÷\è¸žƒªm$0Œ	)¼Óª\é\î°ø:]›ªmœowô.¶¸c\á¼\Ì\ÞQXcFK-¥\ÚT×‚\â\É\Õ\Ø<N\â$ñ’n\æ´Â•“||\ÌZþÒŒ£²û~„®§©&\Ø`ef#-\î\'½t#\'ÁÀ“\êL±p†\Ý\ÆdFú\ÕMbÀ6\\\Ó1¸@R> \Ãw\'\ÓV¥•Ð‰œ¸IŽ\'1\ãŸÝŸ¥8\ÅI);®¶Õ‹`‡,\ÌI\ÚÀ\è+N£I	$\ã+ü‘#<\å\Óõ%\Ú\Ê\áüy%Iƒ\ß\Å\'<R{˜ó\Õp2÷VH\Ýq\0…X?‹ÐŠe*U#”\Ú+o¡½\rƒñn)»\á\n¥AR\Ù\"J–ý\ß.+K\íZÐ¤¯—~½-\ä7½•¬OnC	*9y\Ç35‹SW¼¨\ê[\äwwd\Ç\Ñn\Ûx‘št*8Á´y3Ô‚Ú‚9\"<ò;úPÒ”ªrô]Tm8­P[[Ý‹\êu,Í“ƒH\"?ž)«Ìµ†kOl¶\âQ,yWW³\âšn\Ü.x7i¸n\Æt\èûa\\ü*y\ÏpbºªµE¹\åzgªß¡ª5|%tmK´€6\à÷ƒY\'QÔ·\áÚ³·²3I\Þ_ñðb\í\Å&ÿ\083\íZK=²y\Z¦\Ö&V\0ñ2Ï©ƒG\ßSó/¼§ÕŒÝ´4÷\×Ê¢\0#˜?\\WºØ”\å”aÙ³\Ä;w©£§TcfA\Ï&=¼ø­•\ÆòŠù\rrƒ\\ô°f/t\ÆPoˆ\Óú\æ£{ùm³ºMA\Þˆ§©¥8y‚¹(2#&\åJòs-ô\â‚.Q~…ß–i\\n\Ü\Zc#Þ•Y[\r\×ä¨©j\â‚\Ì‡0\Þñú\ÐGn\Ûu¶gO\â0{w1\ë\çA¿m›E¯BO\Ð\'$ÁþušµM\ì;^P\ÌF w\'¾\0–\åµq’\'›tþ¢-!Cm~!¨ž\çøDw5–¥UšjN\Ý~úŒs9\Õtf\È\r¹Iv,v¿r`Døy84ý­Ol¢\ÒIYýÿ\0 ?	?¦\èžò$\"—ycž¬*Œ±€8ñZ»—un,ƒŠ¹+«\Ùe$ZWU\"	o™ü\ä.û¹\ã“Z£r.Ú¤\ÔU¶›¯€Ikh32@É‰¢nþŠ—u¾\0ÊŠ\Æ\í¯\ÎõubW8\Ü³R,Ù¾\å\Ó\ér\\WB6#\Þ@„œžØ¢…E\Í\ÑM\Ó2x‹À…13–8^>ÿ\0J©)aG\í¨º{÷ó\Åm¥¤©Uø\Øi\åS„XN£ñ-|6p$\Én\æ\rÇ“\Éû\Ñÿ\0²V‹MDz\Ð\×X±&ý\Äd\î\ßZ\ìQ\ìzQ‚uMð\ì\Ú{o7“o\Ô$\0‚?OZu.\Í\ÓÑ¼£‘”ô4£žE\Î7A“[6¥ˆ~Ø¯ü”ºW];^Ù¾<Qœ‚®k®\ìµ_\ÅIg\È\Å_Hª;\Óú\r:`I¹\0~f¸¯A^”]\âs\åB¤yF5=Z\ä\ÉUE\Âž<€Š\\\âª+7ò3¨XÅ½Hx²³)\'™?•k\ÐÑ§Ä¼ùôòù£³‰u#®ž\ä.\00`˜,xô4q£¾N1ó\åþ\àF7•—Õ†¿£6\Ê|M¿\Å”dN8õó­R\Ów[\\\íœûÿ\0‘Î„©´\ål\æ\Æuª­p€\ÃnÙœ\â}\â£\Õ8\Í\Ê\Ê\Ü+_‚»\æ\ì#n\Ù`™\Ç\çŠ\ÑJ³QJ¿\Üd*;Y\r»ƒ\Â\ÉÉˆ™™÷Št´û\\clþ‹ï‘Ž“M$¬K\Ô9Y•\ï‰\æ=\ë#©8Tñ\ç‘¤¢\ï!½5•a¹€ñ>óA\ÞÝ·d*U3Á\é—Â«¼J‘\ã½|¦…F\è\'=¼ž©^´›\í|\ã\æ]‘\àcÊ±\â8?ZZMpF\Ò\ÔZ²¡†\â|©‡\naf3Ÿ1@\Ø89Ô´\èª08dŽ\â®\ÕIx¬IY£*\á÷$yŠ“ž\Þ‡2\å\íb]vU´¡\0\ÈóÉ¬\í\Û,k•\Åú¾Q¼6\Ú\Ø &xÁƒ\å\ß\ëQ\Þ\åM·v\ßÁX¹3\ÌA‘’cô¥Iyƒ‹\Ñ\ë6:´|§#\Ìwj\r¥,2Ö‹©‡R#ŒVZ‘Ú’±:…\é\ào\È\çŽ\æ³\ÕohE}F¹,\á;ž\Â	ÿ\0y¼ý+=\nuªK\r¯kþH$\í„<\Ú\ÛW´.×€,«6\Â	_\ÝU,\âöŸ›Ò»\ÝÄ¡F\Õy\é|i¨=\èkðÿ\0\á«Ê‘¶\áq¹˜ó˜`L\ãµ64a(®¨ºp†\ß0\Ú\ÏÂ¶Û€Wü$\Óôªü4Sº¹C\ËuŽýœ|¦\à&<\Îdˆ*>\ÅH\ìµ\ÐŽÜ\ê.\Ê\é\Ú\î>\ZD0?¾ñ\0\æH\î*\ÔSWˆ\rF\Î]môoŒ–U,•/ñ.¨só\ÛTP\'l@,GÚ¦\Ù7e\äV\Ç+X\è\íð’\ã˜ý\ÙlNs\åÞ‚Qqñ>	²\Ê\ì\åÿ\0\Ã\å~uu`Qþ\"£üz\Ó#¹/»kÿ\0\n^aº\ÑFR$™ö#»\Ô;B0¦¢\ãó:Tu*0µˆ—ºu\Ûgö€ˆ1ûÔ«\Ú*9sÖ¨•zv‚\Ý\Û$°\0þ\ëH,\Òb\"yû\n\å\ê;V«ª”oª\n„¥©³‹³÷ý¿À‡V±f\Ö-;“\Ä0#\Ï\Ì}«© \Õ\ê\êGþH¥\ë\ç÷ó:„i¬J\âú+.ƒ´I°\'\Ð\ßÒµT\íJJ2\ë\è\Ø\nkcm¬\n0\"`\Âg\éG*±Œw\\C©·ø\îzE:õ&óÀ¸ÖœžJ\Z{LO\Ê{yOq\ëXµô÷=ÑŽ|Ìº¸õHoQ¦ÁPv‚f@˜žA<yWl¡ñ+¿#š³\Ê¸\èŠb\ãÁ]¿)=‹€¤œšu*•X²û\èdOø\Í 4\Ï\Þ<£ò\â›,\æ\ä³\è®7~\ã\ëŸ?Ö…\åÜ™o!¬B‰\Ý\rˆò\çó\âŸB¤a-\î÷\éo\Ü(Kl®f\Õ\ÂZ\'v=€\äý)ÚDq+\îÇ·_sEJ©ð\ïc7×!ñ8$ŽL‰¬³¯*®\î\ê\Ø3\ÎnY5r\è1¶@#¸\É&~ô*Rµ\äôºnzñóük\â¦X\Ï\É0>æšƒt÷+>\Ýa.X›,\0\ØÁ\îAÏ–qýb£`\Ù\ÅX\Õ\ÂÊ…œ[€¨`\Ì\0‘ˆ\ïJ‚\È2“Š¹\'U¶ sG\Ô\\n}m\È÷¡”:—½«`\0$Ü\Ó\æŠ°ß‚\Ø{ú‹·ˆ$\0\'M\ÅfE·r\×E\é{Uˆ\Ó\0(3¦H#µ=òm¨\ãÔŠ\\\×tòŒC@a\å\ÜP\ÉÓ‹³;§v U `Lö\Ç\çHš\ÜW-”p\ÚF\Â– z\ÇVž×œ‘¬Ø™^öXm\Û$Lx[Dv\â¶R„\é½\Ü\Î9*\è-Z\×³jØ·v\é\é?*\ÚIgeó$\íÇ­l\Ý*˜¿¨\×\'Qm=‡Kü,ú[\èt\Ë6ÀpÁ\î\Zì·¶N\Óqxö<SÛ¨•–}\ßô¦£˜ž‘“\re—\Ô22þGw\åUM»J6ù¦¿Ÿ\Èb“\ê‰ÿ\0ˆ´›ô÷P”<ù\"Š¤7E¢\ÞQ\ãô½\Z\åÔ³\à\í¨wFË·%M’\ã¸»·8\íYU)[Ã†\'g\å\Ð^{z›«q\Ô\\µj|r/9¸\í\nID\ÄQB3Œ›“A&÷;”z[M§fñi¼H%ˆó\Éöšu8»x†C)S`\0\Å1$°ƒ°¥îœ²Z\Éœ\äÀ›nø–ø>\âÖ¦Þ¨«yñM\×{÷R\ì[\Ûda2¬Ä±ÛŸ°\ïY\å)+\Ü\ÏV)»Ë \Âzr\ÖË—L!Ï…1 |þ†)šj®G4\ÝIS\ÊbøvÓ›«ñO\ÅF<ˆ\0\Ã\ÎDœMh†®{\ï!Ñ­+»±N¶Ý¶—ap˜Ú±œH>ÔºšŸ\ë]	e{…\Ò]TG\ß\03ô¬ôªnŸüœ\n…KüD»š;ª\ÅbX	=ü¸q]¸v…5dŽ=T…\0¸Ï•\0©\Äò;\â´OYM\íSQ4\ÚË½‹ˆPG\Ì6?tO­s*j!V6k\'>¥XJ.\á\íµ¶G(sˆGs\\\Ý\îöHÎ¦º GJb§oqþ¯Dš½\Õ7Y¬\ÜS²\ÊT1¶dN\é‰Á?\Å\Å5«‚µa	ñ{,w;W8&££u%\Ó\ßBö\Æ\Ü\ä›HnGŠ3\ÇõŠ\ÓV4¶5œûZß»(R¶rôö‚ÀHhˆŒsù\Íd\äF\Çk¡\rM’\Z<¨\â\Ê\Úz¾«­v°^\êü=\ØY2\ÛOð#Ú™À\Æ\î²y\Ý^¦\ê›LÎ·ƒ»Z}û\Õ\É\ÜJM.E.^r»T“$bF&>¦4h¥F“R\r·”R À‚Œ\ç1Rù*Í…AˆÎ–\ËH%XV\Ù%A <Os\éT\é9+¹®\Ðj4¬ 3J\Îû\Ùv’|%ÂŒ\âcÚ«º—˜n›\\\Z«¿j·ˆö€\ÉŽk=ZRE8¾§\Ú+\ëñ$°|@w\'ú\íUµ¨\ÝrB·J\ÔYøqŸk°\Z@ÁRZ{T£N\Ñ[º–X®«Y¦¶\Û\r…uÞ­¹I\n\äNTŒA\Ï>‘²¥Àõ+´O¥\ÕY]R¾–\Ò\âÁ>°†\ã‰c&7sÞŠ\íIØ¶¼X\Ó”„C±­\\I†8\îOsCß¥‹\0\çgfz?\ã;²¯¸ÿ\0\n«=\Ïþš‚ß•5T‹\à%$ø\êßˆ.\\K¨\"\áÛ¹“g<\í\r\âyŠ[©|Y•¹ù\Õþ%\ÓZe\Ü\Ím\ÔAFS\âO VF\á\È\æ;\ÔubG4¹\'~¹kS¬\Ö_Û¿vÕ¶J\È\Ñ#¸Á\'µRœdÝ‰v\Ú%\èºe\Ñ\Ó\í\\EPU™ˆ#sµ¶‘ª¹\ÎDœb—\ãsHn›Ä“¿\ß$\Ãÿ\0ˆ\Û*üK¥2\0¶%™\n‰Sxõú19[+ó4\Ê\ÒNñ³ó½ÿ\0#ôKˆ4\ëpY{Œn’Ï†\åÁ  +}(”Ì»•\ìy»kûVº\è¶$(EymDYbg¾\â\0\È>Tº‹p©-\îÆº-ý²\Æ\â92fxUjÝ¼\çÌšâ•˜I¤¸$õ\rE»ww²«	†bq¾bA\âŠs{bŠI\É\Ù,õ=‹´ø•[Û‘]xh(+9¬›ã¦¦£\âg\\‡~±5u4”$¼%N…&¼#V/wU–\àa®\â ò­Á®UZ.Ÿ&	St\Ø~­¬ø…nª	\ØF\à\0-\'Ž\çžôˆ©Y\ç\0n`nj.°b|9\0œ…’pðL\Ð*ip‰û\Ö\é\r°»ö’\Ü(e,s\0ñ\ëD£$¯Á\\Att›»v»ÀS\êMm§Q\Ùd\ÓJW\ë6¥\çi$#‘\nV$ŸâŠ\\ù`T\\´0¬3\'¶TfF\ÐgvH\'©6¿\"’\æ\Ûð\ÅZw·¿ sMŒm\Ò\ß0_4ú‚«‡*31$qT\ÖBN\Ü^†2|D÷\\8ƒÞ |\äµ\Öÿ\0¹?\áÿ\0¥4\á<¿›úò}®G´¿-fŸ\Ä…u¼zm P¡§Q\ÑqY\ê\ï&Š<~¹øcû¥ö¥6Dóß¾Ofÿ\0–³\ÖTò\Ú?\ï¿ó¡—\Â%•µ\éðøÈ›r³G‘°ø‘\è?ÿ\0zÙ§\éZ\á\Ë5\Ç\âù\"ñþñýMg­ñ°5?oðŸúe¯­Hr\"—\'\éß‰?ö^\íÿ\0§\Ô\à\Ó#ñ{Ÿ\Þ7ø\ëY:\ß,ýSüšÿ\0£Ÿñ\ZvŸ†k¥ðŽt¯\ît\ß\áÎŠ?\n¸<O\á\ßô‹\ì—ÿ\0\Øzp\Ùp\Ê]cý.\ßÿ\08Ÿñ-gÿ\0\Ó÷3Ë§¸Ÿ\á\Ïôž£þ#ÿ\0QŠG:?ýœ\Þÿ\0\ÌÔ\ÑC‡\ì\Ï\Ït¼V\Í\'ý¢4O5Ô­\ÉÐª+8¢¯O\â²\×\äMC\ío#\ëú\Ò*pg™iÿ\0\ì\Ïû\Õÿ\0‰k<9—\ßBñü\Ä?|šŸðÖ‰ñòŒt¾þóþZ¾„‡\æ;®\äÿ\0±ÿ\0ù­S\àdú€ÿ\0\Ümÿ\0´Ò™ETÿ\0­{“\Ç÷_FýiO\â	|%=÷v½…E\ËO„C¿ó7¹¦!\äÿ\Ù',NULL);
/*!40000 ALTER TABLE `examination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examinationtype`
--

DROP TABLE IF EXISTS `examinationtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examinationtype` (
  `typeID` int(11) NOT NULL,
  `typeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examinationtype`
--

LOCK TABLES `examinationtype` WRITE;
/*!40000 ALTER TABLE `examinationtype` DISABLE KEYS */;
INSERT INTO `examinationtype` VALUES (2000,'Papiloma'),(2001,'Synositis'),(2002,'Hearing Test'),(2003,'Electrocardiography'),(2004,'physiotherapy'),(2005,'orthodontics'),(2006,'Tova'),(2007,'psoriasis');
/*!40000 ALTER TABLE `examinationtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalinfo`
--

DROP TABLE IF EXISTS `medicalinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicalinfo` (
  `meClientID` int(11) NOT NULL,
  `meType` varchar(45) DEFAULT NULL,
  `meInfo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`meClientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalinfo`
--

LOCK TABLES `medicalinfo` WRITE;
/*!40000 ALTER TABLE `medicalinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicalinfo` ENABLE KEYS */;
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
INSERT INTO `person` VALUES ('111111111','Yakir','Karandian',NULL,NULL,NULL),('123456789','Yossi','Bitton','none','052-2222222','Karmiel'),('147258369','Barak','Itzhaki','none','03-987412','Jerusalem'),('213245658','Assaf','Tzar',NULL,NULL,'Nofit'),('222222221','gilad','levi','giladosh@gmail.com','052-2245532','Haifa'),('222222222','gili','Torjeman','gilitor@gmail.com','052-5555666','Ramot'),('222222223','Malca','Tzarfati','Malci@gmail.com','054-4567123','zofit'),('302632195','Shay','Shahar','shayscal90@gmail.com','0509443347','Haifa'),('305003659','Ra','Cohen','none',NULL,NULL),('333333331','Liza','Cohen','Lizi12@gmail.com','054-6565997','Zihron Yaaqov'),('333333332','Noa','Perez','Noap1@gmail.com','052-2239997','Karmiel'),('333333333','Miki','Mizrahi','Mikzrahi@gmail.com','052-2888887','Haifa'),('444444441','Tiran','Barbanel','Tiraaa@gmail.com','052-2323233','Ramat Gan'),('444444442','Viki','Mizrahi','Viki7878@gmail.com','052-5555522','Natania'),('444444443','Michal','Cohen','Micallanjelo@gmail.com','052-9999988','Binyamina'),('741852963','Dani','Danieli','asdad@walla.com','052-9878233','Jerusalem'),('784512963','Israel','Israeli','none','052-12365478','Hadera'),('852963741','Moni','Mushonov','none','054-1231234','Tel Aviv'),('987654321','Yossi','Bitton','none','054-4445554','Karmiel'),('999999910','Moshe','Moshe','moshemoshe@gmail.com','03-435341','Haifa'),('999999911','Yehoram','Arbel','yoar@gmail.com','02-435435','Nofit'),('999999912','Hassan','Nasrallah','none','054-45343324','Zichron Yakov'),('999999913','Muhamad','Death','none','054-2342341','Somewhere'),('999999914','Itzak','Zohar','none','050-2342341','Tel Aviv'),('999999915','Taleb','Tawatha','none','04-345341','Haifa'),('999999991','Yossi','Benayun','yossi@gmail.com','050-1431233','Haifa'),('999999992','Itay','Shecter','itay@gmail.com','04-3242341','Haifa'),('999999993','Eran','Zehavi','eran@walla.com','03-2355114','Ramat Gan'),('999999994','Tal','Ben - Haim','tal@braude.ac.il','08-1342351','Ramat Gan'),('999999995','Christiano','Ronaldo','cr7@gmail.com','054-234211','Ramat Gan'),('999999996','Bar','Refaeli','barbar@gmail.com','052-536321','Tel Aviv'),('999999997','Gal','Gadot','galg@walla.co.il','03-6520234','Tel Aviv'),('999999998','Pini','Balili','pinhas@gmail.com','04-235214','Tel Aviv'),('999999999','Eyal','Bercovich','eyalbe@gmail.com','055-4354352','Haifa');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reference`
--

DROP TABLE IF EXISTS `reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference` (
  `refID` int(11) NOT NULL AUTO_INCREMENT,
  `refDate` date DEFAULT NULL,
  `refComments` varchar(2000) DEFAULT NULL,
  `refUrgency` varchar(45) DEFAULT NULL,
  `refStatus` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `specialist_id` int(11) DEFAULT NULL,
  `examination_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`refID`),
  KEY `client_idx` (`client_id`),
  KEY `client_id_idx` (`client_id`),
  KEY `specialist_idx` (`specialist_id`),
  KEY `examination_id_idx` (`examination_id`),
  KEY `type_id_idx` (`type_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `clients` (`clientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `examination_id` FOREIGN KEY (`examination_id`) REFERENCES `examination` (`exID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `specialist_id` FOREIGN KEY (`specialist_id`) REFERENCES `specialists` (`specialistID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `examinationtype` (`typeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
INSERT INTO `reference` VALUES (1,'2016-05-11','Some Comments','HIGH',0,1,16,1,2000);
/*!40000 ALTER TABLE `reference` ENABLE KEYS */;
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
INSERT INTO `specialists` VALUES (1,'Cardiology','999999991','Mini IHealth'),(2,'Cardiology','999999992','IHealth 2'),(3,'Neurology','999999993','IHealth 2'),(4,'Gastroenterology','999999994','Mini IHealth'),(5,'Gastroenterology','999999995','IHealth 2'),(6,'Gastroenterology','999999996','IHealth 1'),(7,'Microbiology','999999997','Mini IHealth'),(9,'Microbiology','999999998','IHealth 1'),(10,'Allergology','999999999','IHealth 3'),(11,'Psychiatry','999999910','IHealth 1'),(12,'Psychiatry','999999911','IHealth 2'),(13,'Orthopaedics','999999912','IHealth 1'),(14,'Child psychiatry','999999913','IHealth 3'),(15,'Dermatology','999999914','IHealth 3'),(16,'Dermatology','213245658','IHealth 3');
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
INSERT INTO `users` VALUES ('asaf','12',0,'Specialist','213245658'),('bar','11',0,'Specialist','999999996'),('christiano','11',0,'Specialist','999999995'),('eran','11',0,'Specialist','999999993'),('eyal','11',0,'Specialist','999999999'),('gal','11',0,'Specialist','999999997'),('gilad','22',0,'Branch','222222221'),('gili','22',0,'Branch','222222222'),('hassan','11',0,'Specialist','999999912'),('itay','11',0,'Specialist','999999992'),('itzak','11',0,'Specialist','999999914'),('liza','33',0,'Dispatcher','333333331'),('malca','22',0,'Branch','222222223'),('michal','44',0,'LabWorker','444444443'),('miki','33',0,'Dispatcher','333333333'),('moshe','11',0,'Specialist','999999910'),('muhamad','11',0,'Specialist','999999913'),('noa','33',0,'Dispatcher','333333332'),('pini','11',0,'Specialist','999999998'),('raz','1234',0,'LabWorker','305003659'),('shay','11',0,'Dispatcher','302632195'),('shay2','11',0,'General','999999999'),('tal','11',0,'Specialist','999999994'),('tiran','44',0,'LabWorker','444444441'),('viki','44',0,'LabWorker','444444442'),('yakir','123',0,'Branch','111111111'),('yehoram','11',0,'Specialist','999999911'),('yossi','11',0,'Specialist','999999991');
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

-- Dump completed on 2016-05-20 22:06:24
