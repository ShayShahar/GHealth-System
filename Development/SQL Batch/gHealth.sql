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
  `specialist` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  PRIMARY KEY (`appID`),
  UNIQUE KEY `appID_UNIQUE` (`appID`),
  KEY `specialist_idx` (`specialist`),
  KEY `client_idx` (`client`),
  CONSTRAINT `client` FOREIGN KEY (`client`) REFERENCES `clients` (`clientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `specialist` FOREIGN KEY (`specialist`) REFERENCES `specialists` (`specialistID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (4,'2016-05-16','2016-05-07 19:53:29',4,NULL,NULL,0,10,1),(6,'2016-05-16','2016-05-07 19:55:12',15,NULL,NULL,0,1,1),(9,'2016-05-16','2016-05-07 19:58:14',18,NULL,NULL,0,10,1),(16,'2016-05-10','2016-05-09 22:04:14',2,NULL,NULL,0,2,1),(17,'2016-05-09','2016-05-11 15:42:05',2,NULL,NULL,0,2,1),(18,'2016-05-16','2016-05-11 17:17:17',15,NULL,NULL,0,1,1),(21,'2016-05-15','2016-05-14 19:48:23',4,NULL,NULL,0,10,1);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
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
  KEY `manager_idx` (`manager`),
  CONSTRAINT `manager` FOREIGN KEY (`manager`) REFERENCES `person` (`personID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branches`
--

LOCK TABLES `branches` WRITE;
/*!40000 ALTER TABLE `branches` DISABLE KEYS */;
INSERT INTO `branches` VALUES ('IHealth 1','Haifa',NULL),('IHealth 2','Tel Aviv',NULL),('IHealth 3','Jerusalem',NULL),('Mini IHealth','Ramat - Gan',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'123456789','Clalit',1,'2016-05-06','2016-05-11'),(2,'741852963','Maccabi',0,'2016-05-08',NULL),(3,'147258369','Meuhedet',0,'2016-05-11',NULL),(4,'852963741','Maccabi',1,'2016-05-11',NULL);
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
INSERT INTO `dates` VALUES ('2016-05-10',2,'010000000000000000'),('2016-05-12',2,'010000000000000000'),('2016-05-15',1,'111111111111111111'),('2016-05-15',10,'000100000000000000'),('2016-05-16',1,'111111111111111111'),('2016-05-16',10,'000000000000000001'),('2016-05-17',1,'111111111111111111');
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
INSERT INTO `examination` VALUES (1,'sdfsdfds','ÿ\Øÿ\à\0JFIF\0\0H\0H\0\0ÿ\Û\0C\0\r	\n\n\r\n\r \' .)10.)-,3:J>36F7,-@WAFLNRSR2>ZaZP`JQROÿ\Û\0C&&O5-5OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOÿÀ\0_p\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0	\nÿ\Ä\0µ\0\0\0}\0!1AQa\"q2‘¡#B±ÁR\Ñð$3br‚	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyzƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹º\Â\Ã\Ä\Å\Æ\Ç\È\É\Ê\Ò\Ó\Ô\Õ\Ö\×\Ø\Ù\Ú\á\â\ã\ä\å\æ\ç\è\é\êñòóôõö÷øùúÿ\Ä\0\0\0\0\0\0\0\0	\nÿ\Ä\0µ\0\0w\0!1AQaq\"2B‘¡±Á	#3Rðbr\Ñ\n$4\á%ñ\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz‚ƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹º\Â\Ã\Ä\Å\Æ\Ç\È\É\Ê\Ò\Ó\Ô\Õ\Ö\×\Ø\Ù\Ú\â\ã\ä\å\æ\ç\è\é\êòóôõö÷øùúÿ\Ú\0\0\0?\0azL\ÔY¥\ÝVI(\"‚ÃµEº”\Z@J:sN\ê*-Ô»\èL\Ô\àµz]þô8\n#0s\ëL.h9CL-Qn¤-@‰wLy3Q“I@\ÝJƒsS@\ÍJƒ)€©<\ÔüÒ£§sH3G4í‡­\Ðmši\æ¤ \Òb˜\rQ\Üö¥‚ò	w\ÜqŒçŽµ›«\Ý\Éª\ÂûH9nG‚±DÄ»2ü¥ÍŽ•-Œ\í)\ÍY…‚Š\Ä\Ò\îR;DŽgU`2$\ã¶jd\ÕaûSB\Ç\Óa\î\Î?Æ€6\î Ôƒ©‰@\ïš\r\Å\0Zg÷¨žJ®\Óf£i	¦\Í%F^¢/HZ¦–¦õ¤ n¤\Í6Š`;4f’Š\0\\\ÒfŠJ\0\\\ÒfŒR\í £üQ¶€#¥§\Í?\Ê8\é@‘“\Í?e:\":*` Ž(Ž¤\nS±Š\\PK‚›õ©Bæ”§\Z®M<F½\èqN\íEÀk`Ò£‘\Ö$i$8U5.\0\ëU¯\í\Ú\ê\ÜÄ’¼dŸ¼¦\ÆGq\àG‹;F@$cž*`A\0÷¬Y4\É\ím¾œ.NXm\ã?Ò©Â·\Ì!i/$eV$‘@\Zz\ã ;°CEC\ZI<Œ¢0H^{ñ“Q\ê_[Ûƒ%ßš	\áqUl–ö\ê	\"I#Bn\Æ3H–\rþ“¿#¶=i\ÂE\æ\ÌÁ@=a[ÿ\0iÊ€C4eðHÀo§\Ó	\Õ/Y“j°ðO\0fÀ\é!™žZ† üÁ€\ãó«5N\Çz@‘Í·p85o<P\0MFòl\\õ=\0õ4I E\Ëú\ê 	m\ï÷»\î\Ð\0\ÇÊŒ³±\åb\èò$÷s<ˆI9Ù»‘Ž;þT\í~\ëlk\0\Î\æ ûj`±6«;\Èwª¼\Ò\ê¯\0\"L\09\é\íSh+?©s\Ðý*\r}\n¤‡<þ*Þ„¿ñ.SþÑ§\Ô:YX¾O\ÔQ˜\È\è#ü*ÁJ¾ar\î:\Û\êiˆf\ÖTÿ\0À_üj6”+mÌ›½	¢\æQ>M»\'_óô¤±¸†p\Â*õ#­\0&g~¯\å¯û¹4ªy*#$õ$MZ\Åi\ØD!÷P7ÐƒHU;Åø\rJcC\ÕTþ\Óv}	À=©þA«\È\0(eôŠBÞ«\ÝMöbAO—zgÒ¬\Ý]Án¤K(S\íÉ¬\Ù\Ó\Ë)\æù’—\Îó\Ã=1\íZMŽÆ…üW±•±žGs\Ë5B\ÇÉ´@\Ãs+œ/$c¿9Àëš´/\Ð\ß}˜m Œ†\rúSM¤R\Zœ®j7\n½Hõ DT”L“Jñ…ed\ê\rXò…\0W£?•\Í OJ\0ƒm(\\\Ô\â:_/Ò€#D\ÍLR*`Ô‚€ŒRw§\âŒPv¦âŠŠK˜!b²\È‚\îÇµ0²³ou8\í¥TR¬?ˆç¡¥›WM\ÄB\è2¹‡O\\\×9<\Ï4¬òI’NI\ÇZ— .kwp]<f\È^Xž¿…f.\ÝÃŸ\Ì\Ð\ìL‡\'q5†W*À‚;T^\ã4\Å\ë-³\ÛB˜\Ç\ï{sW#\Ð\ÉKq\nº\à\áT·\Ì:~°•¾C¹±\Î:TŒÓ‚°\È\çjœ\'š`iÅª\Í|¬H9Êž™õ­½\"g½´\Þ\Ç2A\ã\Èd7b·4\r@C0I$\",m\Æ\àg¾)¦ÿ\0”Ã­*ÀÆ¬A$w1ï…·.qš|Ž‘).@8\Î=j„V6\çµgâ¢›Rfe	bvŽõ—o¬\Ì0\Î\Ê\Ê\ç$c‘\íE\Ækùx¤)E\ìh\ÅX|§ñŸÂŸo*\\Ã¾1ŽpG½\Ê6TÎ¸¦\â˜\í£&)6“@\Å¥BŽ\åÕ˜\à\Õ(ˆ\Ð;i\é5ebµH±úR¸\Ì\\b˜S³WU\ë\Í8FjVŽŒ‘RÀÀ`.8¤\"€)˜EJ‰On\r\ãŠ\0Œõ§m¤+\Í \È4½¨\'4\Â\Ä\Ð	õ cOÞ¥Š]¹\äÓ†\04­&\Úq4\Òh9Q\\\Û\î°\æ¨ý•^\ÐÛv¯\01üˆ5}€=j7^Þ£¥\0sZ›\Í	kp	(IW\ìE?M³i¢Ë‚°1\Ëz¾?¥M\â,4pþ5£¥G·Oƒ\Ý ”ò\Ý#TQ8þ1ŠŽ\ÚÙ­­€ÁÜ¿šºG¿¦ƒ”ö\é\î(šžqBp„d„\Z_<Ž\n’zn#\0\Ó\Ø\r¸P¥ ù×§\ÔPB\à\îc¹¿—Ò¢»œÁnòµ!t‘T/¯-\Ò\'˜É‘Œ\ã\åÏ¹ y\î\ï‘æ‚»	\ãð5±x\ÞeÝµ±pd\ÎöQ\í\Ð\nÅ´»[{\ÃrFC{{Tö]\înË€À}\æþsŒT¦2\ï‰\ZŒpjÎŠ[û5h\\\å¹\ÎS\\\Ô÷/:²»3mI\Ít\Þ.\Úr©bŽy\ëMnboµÀ$1\Ë \nj‚\ê/m9ŽxÂ@9ú\Ö}ôÜžLŠ2Ä‡8ü\Z†K¿8”#Í’\ßJNAcG[‘fHü’|2ÿ\0Z›G±’\ÙY\ä-Ð†\Íd=Ü²<y%‚“…\ÏJ\éRh--ã•Á\Ç@½>¾ôÓ»¸™6(\ÛQK}n–\â`Û\éõ÷¬·\Ô%»˜¤o\å¦2Jœø\Õó$+¤\"¹\ë­BGBŒ@C€\Ä\ç_ZÕ´im\íÜ—,Ü„\à\ç\Üõ\èRLM3?“H¾Ñ£©¥V-³.\ÂFJúTW(³@Ñ³2ƒ\ÝN\rs·š…Á“\ìï¹–2:ûðÈ¨eŽ\×\äW•\Æé´¯=3øVE\Ìò3*±V!÷gûÆºRh\î,Äœ«®v±¡\ãÖ¹Ø£2·\Ýc8=ˆÉ©`N/\Zb®K¾9\È\Â\â¯\Ú:Y\ÂÂ¬\Ò‚{\Õc4\Z\Ü:FªDc\0ù­\Ý6\Ñí‘¼\äùò>lõü;U /¯#š§©Ä¯\n–lmlòp¶j\è©\ß\Û\Ì\È^Ùˆ~\à“\Ïÿ\0^˜Š\ÖW)\Å$Ày!\ç=ó\éZ7ˆ!2-ŽÀW=ž\è\ã˜0g 9#§?Z\Ø.&Ž(\×s2°\çŽzš\0º¤2 ®{Õ›zÒƒ(‘ÀE\ÚTŽ:žþµ~\ç`¶}Ë¸Ò²õI£Q¸\ãnó\Ó\ë\ëC\æŸs%\Â56‘Óƒ\Óù\Z¹U\ìb\Ùl™\à°Œð*bqBiiœšp\Í0—m\Ç5—C,q\Êvù™\0š,\ïgT3+Ô§2_HÅƒsÀ`\Z\ëux\âÁ\ÕH.\å\0\ä\×\"\'~C/½L†‰\Ìy~k\r‘•\êÇ¯\áÖ«°qð~\\\ã§Zš= ©\àdW^8Å¸a)e$eTt\ä\ÖMŒ\Ê\"+&\\Œ»Q°-“œ“Wnÿ\0xF\Ô\ÓqŽ”\Øc\ËutùŠeXž‡>•ITÅ†PO\'·¥HU“©ùºzRl>`\ãñ©ý\ÚÅ¸\çvJ\ã½\05!fE‘\È\nO\'<Â´’PbH¦e\Ý\Ë»ó\ëY\ï&~U\àuÇ½Dò“ .[x\Í4u=óXZÀ«L\ÅA+?z]^H\Ú\Õ&‰\ÊLª\çEqöÚ„°2¶\æ 0;s\×né®¥-\n6G\Ì\Çy$úñN\àWy\ßxòÝ†9\ä\çš{C\Ò; ðx\ïžÿ\0\çÖ£•ŒŽû—\Ëlò1\ÓÚ™,_3d\î\àñRÌ¾[ô*{sÒ¶ô½a ·\Û)\0\Èƒÿ\0×¬„t’ó!V\ã\0úR­¼b³H:/b\Î)¦k\Çq‘9VQ\åzU}\Z2ºr1-\É•x-Z\nÅž¢‰‚\Å6\Ò\ÞÃ½NJ¨\É8*Ž¯6\Í9\Ê8Rÿ\0(9þ^ô024Û€úŠª\Ê³0\ä\í\éùWB¥….\á\Î3\Ís\Z|\Ñ\ÅeM£W“ý\Ò=\É5½£\Æ¢;/# 60H\ï‘õ©Lf€Œ\Ó\Â\àP§­.\áŠ`!Z1K\ïM,(\0\'\ÆÉ¥-L-L0¤\äw§Iš7Œb—u!p:(\0\Í&i©þ ~”\Ý\Ã\ßò 	3Fj=\Þ\Çò£yþ\é¦„\ÓM7/ý\Ñøš>sý\Ñú\Ò\04\ÃC+ž ¨Í±bI–N}é‹\âÅ“Ž\rk\é\ÇýŽˆ+\ÄJ©4h¹û½\Îkj\Â-¶ñ\rÍ‚£=8\éHe¢*6Lý{\Z—\Ën\ÌFwSøb*\ëÁE.\ß,\ç±\ëJ\Åú…†\È\n\ä©\Å\0TÔ¤uŒEdœrq\ëT®4Õ·\ÓÌ#ƒŒ\È§¦\r^¹¸û:‰$…™Óªz\È\Õu6ŠVCÁ99¤\Ægh\æ0\ê¬Aec\Æx\éUg„Å…\ÉR\ÆqÞ7²ócª;†–K†f9ry¬À|`ùN\n•%z\ã­j\Ù\Ý2Y\ÃLÑ³)R\Ã#ƒ“\Éük9C\ßq9\ÇsH\'”F‘\î!@\à\nw,‹¾v%\Æ99\ÇŠCœ.Ñœb¹¤\ÎOZw•¸@\ç1’3´ã¯¹¤\"4Š\Ã\æ#bž÷R;³™–õ4\ÙQ7½L\Ó\"\ÌH&‹q¯&¹®H\áG½,wC˜ÑŠ\å@;zõÿ\0\ZªŠ¼\à18\ã\ØÔ¬@R–\'¨\èqŠ.?˜%M’\Ä\Ïñ=«X]“¦´€Ÿ-X @\äœs\×\ÐV:©‘”:\ã“W^\r¶©q\æ\ì\àôO\Ã\Ót:‹#\Ã,Yw‚Uzþ•\Ï^AŸ!†BŠÀ•\Ïñcž*Üš\î3\ä´<L@\ÜŒŠÆ½·w—Îƒ~\Æ²ÇG\\g\Ó5r,Y\Üù¶-fŒ»z2“\É\ë\Óú\Õ2ý¥žo—q\ØN>\\û\ÕEó\ÂE†fùGrz~U±=¹ƒMûR\Å!\'þ=>µ+Q†–e’o³†ý\Ùf\ÉA\Ç<­n$ªIqvFÁ\'ûñ\\\Ä¥0<®¨=;õ©l.Ù¢ˆ…\' Œõž\ç\è)¦#« \0I\àsU®n¢Š\ß\Í&Cž¿•P,?4rE(L	~Xt$\Ð{T½…Ú‘ª>FNF±\àö\à\Z«…‰&Cr#»µ—÷ \ã%ø\Û\ëƒL[‰i%ùÈ€\à\í93Š³•‡\Î\ÉÛ€\ßtƒ\É#\ß?•DƒËºky#r\ÒðT\0ô>ý(@Ä·Ö£Í“<…\'ŸÒ²\î-ÁAÈ„\ÄûÀSœu\ãÖ¶¥B±…I`8]\Ç²ž\ÂE<p…¸\0³r|Žh`7M¸žyò\êT„t­h\áHÁÁ\'=I9¬´\Öm¡²V—&A\Õc\éU¢ñ —ŒNG#EÀ\ß\n;S\ÂW;¹)\ç\äñ‚£qV´}ZKË\"v\Ãº@\Æ\n.\ëû\å²Pv	?¼\0À};\ÖL\Émush`¹-?\Ìeü§®0M\\\Ö ¶iö\Ï\çM(\0¤Y\n=:ÿ\0ž•ˆöžDL\×Á\áF9Œ¬?{ñ¤\Ø\r¼¿h\ä–•\ÈhTrW\Õvq”E\Âõ¥‰Q²À‘Ž§µ2\Ä\'sŽõ\ÆJUŠm¤õ5uw\Én6Ž[ŸÊ¢ˆ‚>P¸\Äz“R¼\å…O—\×³n\â8WÁQ†QP\ËØŒ3“\Ç\\\Zµªp$\ç’\rU¾*$m®H\Ç~¢œA·~l\Z@\Ä÷<û\Ò*–n¸«I·“3\ï!“T÷­TTwo‘O<°4¬Ñ«m\Æ\'¦0?.kJ\Ö\â\Ð\ÙI¾gVP1\n \ã¾y÷¬§•™ByŒPdj`4!Áþ\";zV¦–?~÷%cg‰s· o\'Ú²P9m«\É5n4‘ \ÜW“ü©¡4gV/\Íó\ät\ïÿ\0êª¦\ß\Ì\0¢¶\Í\àtúÿ\0…1eg\n¿\É÷}ªi.Ycnbƒ3œ\Z\0tŠ\"Pò:i³¶E9bx\Çjdn’ü®\Ås\ßühó\0“• ‡Dº’YP3üò9,½‚ÿ\0×®…Š©\0‘Ï½fh\ÖV‘Gû\Ð\ÈË¿“Ï½kJƒn\ì—š\Ñ!.\\<Œf¨\ês\ËˆI\0]\Ù\É˜\à6}_\ætB‘¨|ýzš©¨\ÝÊ±¢Hñ\Éüd \Ï\'ùpzT\ÊJÀd*ºË¼±.\Ìl?]M½Ú­™†/˜\Æå·_\Ï\"¹uu2FpUwcq9\ï\×£$\ÐÛ‰D¡<±û´†•v§g!º¸Tt+ƒ†^‚¶T¹þù\Ö†\Ö)\å\Þ<\Ù‚	\ëÞ·zV‹T f~€(üsL!½G\åJM0µ;\0¸?\Þý)¤\ï\Z7R •õcM(=[þú4\â\Ô\Òi€›û£ñ \0:\0?\n3Iš\0u”\à	 4˜§•õ¨n\'Kx÷¾y \0:’hJJ‰¥c Ž%Aù³\ÆN\0 ¹\Å \Ð7\â›\å<%oÀ [\'\Â3\\ö¸s¨\íöº8xG°¤2@\Äpy¤,½I\Ïö}:R3\í¼ ^\Ô\ì yr0‹Ÿ\åPCoc‘\Üz{Ó·–ÿ\0V¤ûž7\Éf9”\îÿ\0dt\ãLE[\ÖóB®\Ôh\æF`N=1X\Ú\Ú\ÃÂ¤eŠ¸\ÜH=\Åt3³Am#¨(\àÀÿ\0\ëWt\å‰b\å•xRxâ³žƒERY&(½ø Ô–\á^C\æI·\'­U-™*\ÜJ…zqÖ d \ã${þ4Î›gn2*lb7\ÆH\Çªý\0\É\Ía–\Z\Ø–Q2m=‡Q\íL’5X\Ã\ïH\Î=)ƒ”c€=ª6—<p57ŽÛ”9iŠ7¾	\'œS›\r\Ð¯­#¨Á\Þ\Ô\Æ4lTðsÒ¤tum¬\Ã8\ìsU\ÆH\Æ2j^ho\âÁŽ‚‹\Û,§\Ã,i*9U\Î\ã\éõýi0\Æ\ÝJ)ir8\ÇlQsn1F\Ä\äÀV¨W·¾±U¶L§\Î\äm\ÇN˜\ëœW7¨ †ðÆŸtrs€}ñMI\äE(®B°\ÆqU]\Ç_˜“Á¢÷XŠÁv\Ùós\ÇJÔŽ\í^\Åm\å\Ú7Ëž˜\äŸ\éŠËŠ)]F™l\'¾SúSŠ¼^\\Û•˜œŒJ°<­a…‚3þzP“ˆ\Ó\äPdŽ”\æš)X¼‘r\ÃŽŠ\Þ\Ô×•Zf)\Âô\ÇJ–ÀŽIÜª€@·oÊ¯i–-u™\Þm™`>\î@\É\ë\íY²*ƒxúÖŽ£ö{ˆ²™*GcM06\Ú\ÒH,\ïgHÀ`UŽ{~µ\r\ÜISJ\ê]•Õ»dš\Ï7Ò´O\Èì§žy\æ©˜æ«›°\åõ³Y\ÙG¸\'\êq\\û\Í4sy\ÒN\Ìºž[\ØþµEX±ô\Å9brU‰À÷4œ®S`äªŒž;P\Î\Ø\0ƒù\ÓgM²¸\äzSF\î\Î\Z¤®›Ù•F:UˆdhÜ‡\Æ3ŒqUU\ä\\\ãiúŠF\r¹?ZM0.}¥\ËFò¶æ„$ô©µ\rR[\æS1VÀ\ÇŠ\Î2œò‡ð4\Ð\Ê\Í÷˜qJ\Ì	a;˜\Æzu\ã½,\Ì#pTpzSbC\ç&y,qS\\Æª¿!Sƒ\Îy¨oP-DüµÙ…ažSR\ÏQ•‹\åOZ­i32†d_—€}+S\í[”\ÄÁJ·§ø\Ô=Q.YwùqŽµOP\Ò&s€¢µZ\r˜$I\î=ª/.‰2JU—Œc8ªL\ä]\æO— ‘šm½»O06\Üåˆ«R{\Õ]…\Õ\Ï9\Â÷«öö‰\ÙDYY¬N\Üf\ìM½¦Ifù\Ôg\0~´Û„aq·ls\Íhym\n\ìcÀ\ãžõVý0‘††zòhR»RY\ÈR.<{\Ô\É$Š¿3dÒ¡³ s·ó§3#¬HI\åH\Ç@M);å˜š‰Ý™±Áút¦\ï${Ž\Ô \Ë1\nÝ¹©C\0o›*¸bœTÖ¥Ì‡’zPÀÑŠú@¸\ÎÍ£\åö\î\ã[\Ö>!Žko&\éY\Â<\Å9\í\é\\¼1¹˜	½yb¬¯¹Ø«sƒG5„=nC9B\\…\Ï\ÞÖ‹›‰&*Kn\0õ™\Z+HÏŸœõb¤Ž<HpA\ç¦Ee\'mA$X!¹\Í,LU[”û šž@\Ë ]\Ç:\ã¥2\àL€0>\èÁ\Ï\ãS\ÌDd,²m¸­ý?R\"%Ž[$¤ŽŒs\Ï\åXd™ƒ>O¶>ER\Ø\ä\íü*\ã;\íN\n\äƒ\ÜTdf°¦\ÕQ-#‡OWFr\Ä\â©A¨\\\Ù\Ün¸2<Mœ¨r}«£™\êqM5Z\Î[k\Ës3†A\Æ]\Î\Ó4Ë³f«\åA%¹¸c…s\Í0-:\ÔfDS‚\ê	÷¬k=Cc˜\î,\Ã\ì\à˜\ãÏ½hIi¥k\ÜE£X\ÎJ‘·4\\\"Hÿ\0\ç¢\ßB’Y\à†=\ï\"\àzÔ\Ïm\"¡G\\¸3F¡Ÿa*Ç³$u\'\0~4ZúD1G²b¿8,\Êy§†\î	\"WF$™SX\×NÏ§\Û?˜\ÞlLP8\'\ç¹ÿ\0\Z˜\Üýš\Â\ÂHó¸n\È$\ãÓŸÎ§›Q“_›†º…\á•\Ö\0§\×õ§\\J^\è’É…rN\Ìü\ß\ã\çM\Ô/\á“\ä\ÄY\Ï/ž£\ÓU‘ešŒ\Æ\Ò\Êr\ÛXòO°?CEÀ½o=´V\æ\á#uY>flu5,7M*oû<ª§¡8\äQ4QyX\ÄTlÃ•\ë€?\Õí†©[\Í=“ÿ\0…\å?v%ü_ÿ\0­V¶\àÒ”¦#ŽÕŒ‡T;”n\È\àt­ôš\àmW”óaC#XÚ€\'Z*{\È\ë]^\ÚHl\Ï3DFæ˜‚;¶’›)›	*;u\ä\ä\Õ\é¡YQ‘‡1šÉ‹C³³\ÊLŽ\ÚA==ø¦#C\äþó£k˜4=¬Kn\ÞY`}\Ä\Õin-\"Fgˆ\çu\'\è9^\Â35mJ@\Ùf~S\êÂ¹»Œ\0œ`sõ«ºŒ%\É2\ÈwŒ1$Š§2¨\É=I÷®w+»–Š8‡|U¨‰U,\ÎzzS\íV4´£>•fxFó Vü:b†À‹¢0W<T]‰=‡`#\ÚF\ÒEV`I\äf‡°\Ø\Ö\ÏRF¥7\'o\'<\Ó\ÙCu\Ï¨¥p\Ù\\zR\0‰X© žõ\í¬A\Ïi	Ž1µ€¨\Ø.~`	\Çj\ç\0\nY\á¸‘\Üv§(p~´\ìP¬\Ä{P2[[¶…\rÁG<þ\â¶¶\Ö+r°Æ»H\à\n? }«œ †`Œ8\ïS[\Ã$òya•p3–8­I­š¹wO\Þ\í  rMUo½“Ðž‚–&\Ç\ÊÁ\é\ÍJñp[>¸¨½€‹§(XÀÈ§±xùq€:b¸E\îþù<š…dfR›°§¯\'p-N\Ø\Í\Ë)ÇµCŒœ\Ðõ©<öRNüÓ°ò#óÚ„\È6x¦‡\Ýy\ëNÊ9\Î(Àž” c¹\ëL\0³dŸ¥#œ(D=\ÇZx“¯8ªÙ©œf‹\0“õ\È9È¨ûµ,\ã\n*üƒ\ØÖ±\ØƒÖ—w\Ý4Ÿ\ÆG¨¤ÿ\0–CLƒ\É”\Ö?(4¿ò\Ð´)¸\Ìdz\Z\0±o’·\Ê	\íW¥Q¯ž ò\0È¨´÷?g`1ƒÖž\Ã\Ìl–8ó®yn\"p‘•\Ï3\É\Æ={Rü™+×¥ h\Ø*°Š$]\ÊdQ€z\ã5 ZV\0x\Ü:­$È²ZÆ¾sw5P\È\\9\Ô\á;˜E\É\Ù\Ó\éB\ášÁ\Ç9§5ó6T\î\r\á\ãŠcc\Ê\ÊÈ§¦FzTWP–dòˆ$ý\á\ØP€•™¥‰6«°S\É\ÏZ¡y—bw|£€:\æ¯\Æ?pRW%>ö~U\â2&\×\nGcÿ\0\×U ´F\Û?j‘m\æ`\Û|¼n$t¤T3l@X7\'j\í\Ü\0Áû\ÙUQ‚\Ã{z\Ór³”ª§#4ôŒ\ÊÁPriª¸œ!9\ãŠ\ÐAU\Ûn¦A\ë\ïTÝ€ #b\ÅqÈ«\Öq´%‰—qVl\ã]Ò¤€\Ù;s\è{\ÐG˜Ñ”\î\çp\çj‡.q‹ vl/¶QHÁL\ÃªGSœdS\ì\ác´Ÿ/Í‘–\"”Þ†\n9\éžþ\Õ7³\ÉJ}­†^9©\"“¬\Ì7¨•+DÛ·–#‘×Š¨\ác/€\0\0óùT\Þ\àH¯¶@e8\Ç;GzY&%\Ê\äu\è*YdRB\íx\íQ\0\à“úQ`.\Ç(š0€sž´Œ:`\äµ@IŽ=Ê¼\æŸôv\Îz:\Z,‘n3¬|‘ßœf’\ácóGÜ½rGqN“*»;Ü‘U”}\ä>™5Qop/[j/\Ø\â (\ìÜŠ§pó\Ü\\¼ŠIc\ÎqŒS\Ò0$\ädu«\ÞpŽ0¨\ÏJ~\ÒÀP°¹¸¶¹\îù\Ç>ü\ZÚ–\æY,\ÚTœ˜\ä÷{·súb²\'b!ß’wd— ¢)\äŽ&X‹*¾7\085¯>€J²¤ò¤a¼\Æo¾W ô«Z5ü\ì©v\åTg±;³\ëú\ÖD\Ò?]˜õ©¢ò\ÝA.H\ëŽô”šÞ¢\É\róiƒÁ\'\Ì8É§¥\ÈE \È+žBþ­gHWˆ˜\åA\ã¥2\á\ÄQ©YC–\ä\ãµMõ\Ð\r@*[\Û\Ç~\í²p\ÝX™üª\r2\î8/°f`\ÆB	ÿ\0õueE;¡c‚øN;\ÑKd\Å+)o½\ÉªO[\ÚE&±,rNq\È=9\É\ç¥h‰\"‚\Ù\Èû\Õ\Ä%¬÷O\nùû™Á\à\ä•Á­‰\"\ÓlW\ì÷Œ³L‘\ä““úÖªB$šö\ÒmaL³Ž%\î\àjûk:x;Vm\ç\Ñ5_K‚\ÚS$ñ\Ûoš<¨\È+B\Þ\î/’€ö\n1\Í^\áÄš\Ê\ÊŠ79\ë\é[K­Z™†\â\ê„`’‡\nky›UR\Ïå·˜0\è¹=i/VHÈ—1H„ô.3Ç·Ö—0\Ù\Ð6©k#¤pNŒ\Ìy9½M-\ÌDmÑ–^qº²­n-omZ[Ôˆ³ƒ¸œ\Ó‹y40\\³œm\ä21C…cj\Ê\édˆ@dd%‰þ#ŸÓŠÆ¼šT™\ÃÊ®¡¿ƒò\ëE”°E—yw\çf?™H°89ùC¨ô¬§++\å\Ã}þ†¢\åp	\àsõ¦•Ë²“‘\ÍD\Êþð’ù\ìjR,Pe2H‹´r~¾•©w\"GjŸg%_\Ä\äA\éYq÷W=·Ò¦ \íÝœŒz\Õ6\Ñdò\Ü\å?Æ©HO˜ÀdŒõ­8\×1ƒÿ\0L\ë;yß€O^\Ô=Šc\n’F\'¥? &\Æ^ôƒ\åbX\äö\Å=²Ñ™Ux\ÏLõ¤Iªm\äþ”›”¨\Î¥\n	`¿=j\'ˆùž[ž0\Íò\r\ÄÔ‘6,z\ÔS€{X™\n.ö<ò)\"o-Iq\ÔrsÖ†Ž™3Gq#[ù…P°\ä{Ÿ\Óõªm:¸oÝ¢§õ¦A0\Û\"¦2ÃœŽ@ö¤\n°\Ï\çEÀ„„‰ö‡%”sH“©\Ý\æøTb\"Ã¨ÝŽ”\ÇMµÿ\0J,€œ\É <œ÷¤“\æ\0\Æ21Ž*¸l€\rI»¥°\ÄS†\ÇCOi\0#r®(ƒ\ïÇ¶iþc“fFx\éLDa²\ß(Á=i_…\ìy4£\åoZcHH\Û\Å\0(b\ïjÈ™[À#\0qU”óÖ”\à’A¢ÀH\ê<Ã´¹©\È\\Ò W#¾iÁŽ:\Ñ`%”†ˆz\Õp>Cõ©€\ÈÛšŒ/\Þú\Õ\Ãañ!õ taF\0U>”\ìaÏ¸«\ßù\æi@\åÖƒ\åŸcJs\æý\áH	¬²Û‘wdŽ\0\îjúÅµ.@\É# ™h\Ån9­Ww6¡®zš1\rˆ¢–?+s\È\â‘$D#vH\Ï*<(@\n.\ìu¨\Ü–\Ç¯5Jwml\ç #š®¡”*C{ö§£I\é\ê*6tg\èU±ù\Õ*‡|\"ô=O¥I{^BÏ–\ØJ\îªRJq÷\Î•O§M²Ge\\`\ã­\r0%r¨3’\ØÉ©%ƒ‚\0\éÚª\\\0˜Ø•=\Ï¤Š\á€Ã± R\Ô\Æv+4cª1N´…D\ß2÷÷úS•¸\ÜFNz\í\095*µn\à3l\"UH\Ø\0’sŸzž$‡\Ë\\‚?É¦ª©Á\É9\Îx©yE%°1Œôú\Ór`+Ÿ/wO©qH.Wy\0½†9¾i$pã ¢&\Ú@@+ŒŠ@$2˜/ 1\ÅH\ïl\ÛTdp2	¨\âF\0\0ÁA\ç¯5i\ÝmU¦+\É#§z¯&\0ü\Ë\Ó˜\Æeb\ØAÁú\Õ\É\ç`\à9 °\ã*e\Î\ÖbA\àšq\Ð[^òm;sž)\ï\å\ÊIÚ©\è;SU BH^œgÖš-Á\Ë3ü½°rj´TGhv…;‰\É\É\ÎÂ¦X™[G¯4\ØJ¢\rÅ†¯&š³\'\Ê3\Ï4›l	2\Î¦q\Î:bŸ6\Ù6´„m/j‚I\È!zã¹¤†Oœ\ã§¯’`LN\ç\r	\è¥Hð‰˜8f$g±´a”ˆ\Ï#ôõdY]K¸$lTr:\àŠz¶l­)P½CqP,Že,¯—ð­W\Ó\î\n¾\è!0\0ª©¤\ÞyxKf\'®XV‘‹‹\Èdp2q\Æ3R¶¶€\Ê9\ÎGz•4mE[&Õ¶E?û+Ui2ae\ì3W\ÊÀñ\ä\åX@\ì+;k\îÐ²ƒ\Î+i´A—iˆöašbh÷«\Ö!\ÓûÂ…‚\åŒ†ÿ\0” zš„*©Á«ÿ\0\ÙW\Çø\0\0\äÂ˜úUÚŸS\Û\ç\ãG+.‘¶\×\èÒ¤k—¹¸ûU\Ãeò9\0\ncéš‚®~\Î\ä{Qö;°ª¢	\0\ïòš\Zh\rIu\Öv@\Ê6¡v\ãŒSmµ\Ê]¼\ãs¸ý\ØÛ‘Ÿ_jÌ’Ý‹`W\ê1KL¬Svq\Óµ<\Ì\Ù\äy\çš\àu\Èr)³‡\âDÃŽ\"‚\Ùm¨C\í`ÃƒSqŽÆ±mŒ}MUi„’sŸ­2pT/\É\è:\ne¯–ò\âBÀg°¦•µ\êH‚7Ù\Ø\ëP f`S#h98«S\ìP\ÛzcUNb\ía\ÎK\Å(Œ”g;À\ã5#@Ÿ,9\ÜzT‘ÄªS/\Ò\ç½D\á™òW*8=ª€EasÊ¥•\×fsþ\"ªÉ†\Î\à(G\Äx|šV­©Œ\0rDUJ©(\0ù­iª\n˜òTB9Çµf[–_z©lS$*B£sÞšS‚Ao© ³g\è\rW\Ëe{ŸAP–¤‡÷‘œw\ì*vC#–·/\äjb•wccv­ND‹»,:ž”ß\0;g\ÃÁ\ëP9\áIÿ\0\n²\Æ\ÞM\Ì?Z¥9DP\Ñ/RA$ÐµH¾S\Î2h`[¡\ïU\Ã:÷\Îi\ë0\0\äg#š«\æ_›\È\Å8ot#\×&€žÿ\08¡Uû£½0!68¡w\0\ç\ÐR•ˆ\ê{\Z’(ö1#œ\n$rùœõ\Í\n®$cúÓžP¯òò;\Ó7\î8\É”µ\0\ÚC\ä\Òy\'<\nv\0A+\î\ÈÁ´\ÄFÄ†ùGJCœT®UW=h·®$\Â\àzÓ¸˜ÛœóÖ>$f¤xŠ¹³\ïPW¸\æ„\èù`{SúÏ®jÁ\ïÒ§nªj¢&7øH÷¥\Ï(h\æa\íF2ŸCV0—_QL\'÷h\ß\Ý85)\æE>´Í¿+¯¾h	)0a\ØÖš\Ì\ì\ì\ê~^˜¬\ÖU5dM±UG\\V5À¶\Ò6\Ð\0\r†$S¶£Ç¸±üT =zdw£{\É!Û…ldóYrˆ»u%.0v\ä-ùt¬ç—€Áp{5[\ÎW2°eQ\Îzš­*Fñ\Î\Ò>\è5H\Ç\Ïf#9\Î1\ÔU›b\ç\ì\Û{`d\ÕO8o\ËR\0À_J—\í\0É¼(\0v\Úc%š7w\ËÁy™6òm\Æ2{T&VóX·CÚ§i\Ì\È84­a\rYJ©úÔ«\"HÛ‰QžMS—\n¼zzóDyû¸›C4€Aµb|“øb”\Ë\"€ŽH\îsÞª©X\Ø\ì\Ý\ÇP=i\Í.ð<\Æ\äúTXD¬û\ÆwŽÆŸù„n`\ëÒ©²²\Èw\î\â¯f\Ã3\íJ@<\ÈPþ\í—\n§š\áe\n\Ì\á\\ý\ì\Ô\"XšCŒz\àUk™¨\'\'$P£¨É¦L1\Ü7pÀñUþ`\Çvvú\ÒE+–\n\\…\ãÖ¬,e\Õ\"‹\çg<(š´º\0ÂŽ\\,G9\íZ0Y‡uÄ‹#\É?•kiú3G\nø\çhT\ÒX\Ù\ÂIfgo÷«UK¹<\Æ\Û\âYdÀ\Ær•%µˆ’,ÕûN¦+FCj§ˆTýrj»\ÝÀŸv(ÿ\0«äˆ¹ˆ\rô`\ålm¸õÿ\03N\Z\Å\ÚªŽÇ¢\Ä)QE\éÿ\0¾EW“Tnv€?*vHw,6»©v”¤j?¥DúÎª\ßòõ(úqT\ÛQ˜Ž¡7òž­ú\Ð2\ã\êz™\ëusÿ\0}\Z¯¯\Ï[‹ƒÿ\0j¬oI\ê¹üj3wŸ\á™£A]ö,µ\Ý\Ù\ë,\Ç\êÆ£7\ÞOû\ê 79þùšO´\î\Ì\Ò\Ðw}‰L\Ó¬\ß÷\Õ\'›\'v?÷\ÕBfø\æhóþ(²_5ÿ\0½ú\Ó\ãvw^I\ÅV\Ôð\åvc«6(³o$¬¡#88\ïÚ¬,³\'[¤\Ó5\r˜;þ¢­ùHz¢ŸÂ­-lT¹º\í2ºþbžpÿ\0\ë-m\ß=\Ôm?¦+2xÅµð*1Ý»gÿ\0¯Wâ´·‘E*O÷X\åMF\âlkX\Û\Ê\Ç\ä’\"Npy\çñ¨%Ó®a%\ã0=×‚?\n·5¬ñ\ÆL¹#\È?Z}à¹‹=$^TJŒX)\Ì\È\Ç%¾SŸ»Þ?\å¸;ŽÑŽN3]&¡\n\Ý[7\îHr\Í;\í\Â/\\õ¬¥N\å©Hh‰RÁ\éQD\è6\îPXþ´\Äb2s“œ\Ò\ÃjZ]À1\0Œ‘\ÏŸMF‡©,YY@ô TfPT8\ãŽz\Ô\åX$?|vª\Ò\ä[\ä	\à·ZKQ•eR\0\éƒH¨A\å‡4\É3¸\Å\'*@\ç>õ¥„n³â˜1Zü£¿]š5„³£n\'+ƒ÷EYœn\Ø\å¨Ç¾*ƒIÈ¤ô#-\íP\Æ\ÇÂ»‰%”gŠ\'…`u+†\'œžÔ<’3.s\ÍV¹Ÿ\Ì\ä¾ƒÒ’M±%P»™\åPF\ÐsùQ±UT{dŽ†‹y\ì\åDh[¨b\æ–d`¡™‰rzGB³m\ê9¨œ0”]\Ü\ãÖ¦™b	\0ñÒš	•n„ò*®\á™ð\0ds€*›D\èpFsW¢RT?q\Ô{P\Ðù’d´ŸSB`Qi£Œ°ˆsŒdT!Ý²	<\Ñ\å‚@¶i+\î*’CB…\ÜÍ–=½)r;t¨°\ÌN)\àH\íÚ€R	\êi»·I’E/–\ç®@÷§ù_.H\éÚ€\Í\×5 \n¸&ˆ\Î™\'µ&\Çs§¾(°†\ä³n\ê\à(¸Áe# õ¨\Äd¨v9ö¥\Ã\Î3‘Rõ\Ë4r!’¥R*Œ\ÄŸCVaRT‚6¸=}~µ\Â\Ñ\ä±\0ÒŽšB­X¹1Þ«a\Ç%±W\"\\\ä\ïZ-\Æ\íùÁ \'ZN¼„lv\â™\æH=ª¹„?i\Ú=©vbOb)«!ÁÝ»\ÛP$rzþ¢Žd1v|„z\ZkœG\ÏqN\Þ\Ý\ÏQJþ[@÷©“L\nªì­j\ÈPH9÷iðD±L$+\æ\Û2,a‹`§\\azQ\Êy²,˜Ï½F\ÜFgi=ûÖŒv	p­\Ðc\Øcõ«)¤.Ò†RHä¿\Óü(\åv™A$ V\Ï8=V›Œ3À­Ÿ\ìˆ6—ó˜s\×ðüj_\ì„Ü¸¸\0ŸT\ëG#£ž9\Üd\ë\Î*R\Èj¶\ß\ë\ï[Bq¸¥\ÌG\×<~uF\ïD¿Œ\î	±Œ\çÿ\0¯IÅ…\ÊL\Êx+Ò¤Aû„=EF!\Ús+c\îÿ\0K»X„\ÈSÁ÷¨c\åmŒœwäŽ”À¥\Û*\ÙÇ «rhÏœ0\0\ãµ{J°µš\r÷R²ðYBO_j\"œ„d¨|\Íò\ãÚ…!nKõ±}a1¬°\Ì\Z7À\Éê¤\ÔW=#¶\ì7LãŠ§†…˜¨\äz\ÓQº\ä\âH»†S?ZÑ°\Óæ¼™#\n	\0G\n=Oô\ÒöV\Ò\ÜÈ±Û\ìÃG\ÜûWY§\évú|{œî”™±\Éö‚‹H \ÓaÀ9\å˜õ\'\Ô\ÓgŸiMi¨\ÝÉ§¹E*\Öm\Í\È?w¨f¸\Ý\Ër;{U‰\ÈS\ïE\Åa.n\rP–F\'¥w\ç\éPn\ËóAD\Ø\ÅE \Ôi¥ˆ<\Zl’\ã*y\ã€cM\Êw\Â\Ô\Ü\ÐU‡ˆÙ¾\èýj6I\rÁj>N	Ò ¸ÿ\0ZyÏ½$õ‘f–›GZ`:¥HdnB\Ôqœš¹»–À¤\Ø2¸\Î\ìµ¥ke<¾L‘\ÆJŽ}2sþª-(s…ñ®¿H”\0Y\Ú<D1”\êž”ÐŠ6\Ñ\Ë4LŠ8\r\ÔU\à\ËÞµµ),Ž;}¸<ò\Ä7|£\Ø\àZÁ±ž\Î6n¤w«ƒ¾„H5<ûV	Ã¯Ì‡\ÜQ¦Ï½‡ñÖ­¸Vd\Û\ÞM@\ÌO¡\ëZ\ì\ÉZ«¡ðkù[O¿1ÿ\0ª¾+e[z\íUõ–Kf,»‚òG¨\ïúPÖ‚ORÅ´©\"+®aš\Ä\Öl½Ð’5Sò=.“pmnž\ÂV\Î\Óò7¨­÷†;\Ëv·›£t#¨=L—<JøY\È\0\ë&w}\éi#r‰µ]Àjš\ê\Ýì¤’)xtùIõ÷ªÍ•\Ø y\nòH®;w5F™£‰O—0?­A;–¶!`Nz\æ˜]\Ê|Ž?:³n±”l+ö\Éô¨\Ø©¢q‚TþU4)+ O)Ny\ÜGjÔ’X–`\Ðô©e³q±•Áû½©ûN\àEx‹·\Ø;ò¬¶%r¥23Á­{Ð¬Hí·¥f‚­»j¦{U6S\ÙrÒ´a°2¹\ã4\Ór~@PŠ\Ø\'ö©–·ŽÑž•\îRB\\ú\ã­I$°,kò•‰\êzU\à«!ó¹‰\ç\åþµJ2‘…lg¹\éô«8„F¢}\ì\Ã8\Î\0©oP!u¸Ê¨;q\Æi\ÞF>G\Ú\Ì9ã¡¨dyS\æS\ÇsO†ð\ç\ç‚MV½\0”X\Ï\ÝôÁ\íH\ÅvÇž1LiGš*S\Î2?Zar™`8<EM€\É,s‘ž)ŸºWÖ!4¤3òk b) \æ\Ô\r\Õ%º•¸C\×\'ü)\\•˜ü«òñƒÈ ÕŽ\à\0§‘œ–<\0©¡¹h›)\ådRµ\ã’H\Ú2{Fô¢\ÈEh\ÐI ^@õ«(\Ò*„\n\çi#Öš/f#§Ÿj¸3IÇ«QdÀw—p\Ã)O\ÕM µºc\Äl>§›#Y\ÔÓ­Ø™\Æ\îq\ÏÖšH	£¶¸V\nûUO_˜9¡v2Æ¾û\ÇW9n½\é\àÒ²\Ê\Û»}\Â\ç\æ•QŽx=EFkq—4\å\ÜTmb£Ú•\Å-‰”t\0ý\r*\É(\êó\Í5r,\Çñ4\å*9Ú¼{Pf!–F\è3j7Kÿ\0<Áÿ\0€\nM\ÅI\ÅN“¼@™u‰\ïM2ú\ÅýñVLÀŽEDÄ¹;qŽ´„Iüóò\"¤[€½6ÿ\0\ZdjqÖ§\0c\n\â‹\à¬vF v\É$Š—ûV\áˆ%Ð‘ÓU\ÎaI:S¸‹CT›$þ\ì“×Š?´¥*lX8?\ãT\ÝU†H¦E8â‹°4—T˜6â±“ø\ëR.¯ R¾J\àöjš\Å÷F~”×‰\0\ÈQE\ØnobºÇŸjŽ‡?Ê©\Ë\"¯¥GðûR—\'œqQn;\Î:\ÒÜ¸y\\7©\é[VR¬Q$2¾\ÉUNWpü+>\ÙH&*¹\Ç\nxÁõ¨®£@s»vNOzQ¥bMfø]C•«3\ÈHP g€:\ÖtºU\ÌqòŽO¦\Ú_µ\ÛZ¨_±\É0\Îå©`”ø®\ZEòm#‘^cÁy3\ëÿ\0×­\Z¸Ó´\Ù&˜(ó\ìƒû\Çú\nê …-!Û‚;³ž¬}j+­4\ÛA¸‹y\å\Î\á’j9µ«f\Øò1?\ì®Aüj’H†\Û.#\\šÌ¸Ÿ\Ìc\éSM©\Ù\È\0]\ïž\ÊúÕ›<™rcŠPž\àšX%z£3\åúô©d™v¤NO²\Zƒì—\Ï\Ù\ä\0÷a\çHi»qÖ¢-V›O¹#•Qø\çùPº|£«ûö\çÿ\0e eVnµÕ¹-nŠ­´®=B7?˜¤k\'\ÊN	\í\å\ZVL\ÓsV¾\Íóc2ûeÿ\0×©ÁOü¼þômý3LÄ¸\\ú\ÕI\ïZ­\Ïj\ë\ÊÈ’ñŒ\0\Ü~`Uc¤\ä£~U)XD4\å\ëRy\r\Ýò¥\Ø’Œ)Œjô©£Ž´\Ü/cùÓ„\Æ6ô4\0°Œ7\Ðdþ·§\Ü\ÞZ\é \Æ\è\ë&G–N\íX\È1¹\ê\Ä(þf¥ûC$X‘·F¤.\ß\Ô\Ó]\ïoo\\A90\Å0\n\Ã?3\Ð{So5²qH(žÕ–o<·_)B…;†jµ\Ü\Ò\\\ÜY\×qö¦–„ò\Ý\êjlÜŽ‘*ûQ\ä¸I\Z\în}«\'.z\È?*A\å\Ï\åK™•žp\'„\ãµ\\w\é\\Ö‰v\"w\\‚Lš\Ún~\å»þ\'Î¶Œ´2”u2ux\Z*\æ.M\å±öþÒ·4»¡un®8a\Ãz¥qm=\Ô2\Æ\èeÁ\äô#½I§ÀlIA\È#9\Îri-\Æö\'ñ5‰»\Ó\ìKû\Èx|w_þµs!\í!»1\Îk´I[\Ì\00òde#5{`–€#.7#c’øV5£¥Ê‹\Ò\Ä#—\\òz\ç§G¹þW\nr\0=jó4Jƒ\È8«6·<\çŽqÀ\ë\\­;\\²+“ó ©¡ëŠ²N\ØrTŸ\á^\éÿ\0\ÓU\É8\ï\ÇZlól‹\Ì@¿7oJv½€±9*¤ó¾•B7\ÎN\ÜwcŒU\ë\Òv·‡?…QI	`ƒ\Óð­\Z\ÐlW\Ü€C+{S¢ƒz(\äœóR¢«ª•\Ï‘Šc–R€;¹9¢ýJ\Óc1 \Ï¥@^En;sŒu§:°q»ûÔ‘4P¨l\à÷ç ¥ ¬3±\ÜP”c\È\ÍA25¬ød ™«¦ñFS+\ZŽƒ½C;-\Ò\Æ73ši»\ê,€G…9?Z%$*…AŽ\àsQ4+\Z·\ï\Ó=i\Ñ\È\Ä`‘€9\Åh\Ö]­‚sORWÿ\0¯A\ç¿zŒ“š\Øe˜dO5X\à`Šu\Ó•À;ºú\Õla½j\Í\Þ>\Ò[ñÏ¥aG÷\Ôu\æ’s¶V\í\Íÿ\0¬_sù\Ñ0\ÌíŽ¹¦1 ó’iÄƒ…\Ç\ëOM\Ó\rÅ³³\ÄIb6q\ëWbð\Ñ\'2\\\ìZžd;3\Í\Ðô\ëS@¸’GCÇ¥o&—aa*¶£3J’tÀ\Æ(-\Ù\Éþ6Bß¦E%4>Vs=Á\ëF7°\'\0w÷®–/[ùÛ¦™öz`.Z˜h\Ú}¹f§~¡Kp?*9r³–<`“V!RÀ\"ŽIÀ­É­\ìZ/³ª\Û\Å\"\Ì\Î\År¼ð5r{m&C³–-ñ&\'%Û¹4÷\"H\çg¶ky<¹À-Œ\á[¥FÁBü«Æ·muHme¼Œ\çÎmÒ²’\ê\"Û®,‹§\Ý_\Ð\É\ä*`“À\Í)N}\rt°[„Ó¦™lM¹%’\Ù-“\Ïòª2š\îAöd|¶\ß1@8¥p\äf@84»±œw¦t¢¨Œýj}Àš{ýjAÒ€ò:\Ò`w¥¢\r#ˆ\Î\0\ÅM^‚€X\î$R’Ozo\ãV-,®o	[h‹\íû\Ç8€+’i²|\Êy\Îq[_ð]$>l²Ûª\ç\rûÌ•«\nº\rª\"y\î:³1\çðj,¨\èbÁp|§,†IÀ\ÜzzS\Z\Ú\ì@‘0V\è\Å‘®<Aamÿ\0Ö‘\'º\Ç\Ï\çL—Å…¿\å†ïªƒü\é*is\Z8.T+8‘\Â\ã(z:»c¦It^w\Â8 9üv©[’ú@÷2¬V\êÕ¦·\âjG\âke\Û´k\ï»$þ8«°‹6\Z\"•Q=µ¹¿•´ÿ\0:¸ú=ª6R\Û8þ\ìœ~Y¬¦ñŒó\È~ux\É?†5ü©\è-MÅ€G\ÂÙ‘Žœü©\ÏÛˆ¡\Û\ï\åÿ\0`Ÿ·dOû\çÿ\0¯Iÿ\0	¤½•#þ4]™¶\âý‡È=öTA5\0O˜\Òcý”ÿ\0\ëVRx\Æy(Ú¹\îAÀýk3PñF§<\Ä[¹H‡LpO½+ \ågDb\Õ%o\Ü\Ü^\æM«Oû©ŒN!\ì®4\ëÚ¹ÿ\0–\Íù\Ònj\ßó\Ùÿ\0:.;Œ–:‹\ÌA\ÑÀªsh—’ÿ\0¬¾Vú\ÊMs?\ÛÚ¨\ë3þt\r{T=goÎ‹…³\á—ÿ\0žðÿ\0\ßýjŠ_þõ\Äô\ÜsY-«^\Îq-\Ô\Û{€kB\ÓYµµ‹i´i[û\Îôh%‡\ÃþaÁ˜ÀTš˜\éZE°ÿ\0I»va\ÕC\ä)Ä¾d{MŠôg¦\rexÿ\0‰u¨§z¨<ú4<Cfò{±?\Ô\Õ+‹˜¤‚\Å#\ïZ\ë;¤U–É¸\ã\ît«‰{9dH\×\×h€å´’Ÿ–}”š$´’Ìˆ©þð\Çó®šmF\ÞUò\æŸpÿ\0`Ÿ\éY\×bÿ\0–V’\Ë\é¼\à~´€\Ç{\'P«cv@\ãó¨.q(‰¥,T’v®y­³%Ä¼b8—¦OøU+$2~\é\Î\ì\ç\æ\éO•ô\ÌgbNj×·²·kt.…™—$\î4Zi[I’\çGE+^4T\0*€aN0\î\'.\ÆHÑ£c\ÒïŠ±‰n>þ\æú·øV˜ž)Ä…RIµ\\ˆžfAok*R\×j\Ìcz\çRõ\Öõ¤„’SÁ\Åo¬\é\Z\æbªv‚A=(R@\Ó\'^òðZ¼@€\Û\Ï#<\ëU\ÛXµm\Ç\Ü*B]>\ê?žp$\n\Ã\'9«h\n=Íˆ%Irž:Š‹Sš\à&XÕ¼\Å(¤Œ{TvF%†1ƒŒŽõ™®\Ì\Â\éQ¹P¹QS7\îŽ+R¥O;q?(Xzt0\ìD\Þ\Íê£Š¢Žd˜\Í\"\î#Fx«ˆ³˜K9#žµ\Ç%dX·{L\à°8\ÇJ !‘£\ÇÖ­¹¸–2¸\àñ»4±\ÅÁ\Ó!@\r	\Ù%\á 6Oj¥»¶89«W²\0üw\ÌpBKcžØªllsùqª\ç\0v\É\ëL6†3ÀP	¨y•wdg·=)p‹\rÀS\Æjl!\ÛÀQ¸.{sT¥fó``ô¢iò\äl\Ï\çM?>H9\ÇN*\Ôl‹\ÆG\Ìr[¯*\ÅdÊžJ’8˜òq“Ú’X›q\'\0\â¨c¼\âÀ)aÁ\Îi\ÌÀ6A\ãÚªA\"¥+´ñš,wg¤\êy¤\äsJ=i€»Jz³rl|ÿ\0\0?N*¹ o4\íû¨€#\î\ã\ëL\â•O©ü\é\Óß°\ëùSbÏœ¼w\é\éW-Q_S]\ã\åq\Ç<h\è¼R-¼K^ª\í\0`\éHo-Á\æ\èŸø\ÅXmKJ$\ï\0·rR£}CL\'¨3\ßË¬~F„>|<29\0q”\Ü3øÕˆ\ï,#³0\ëÁ8«‘\êZlH\Ç\0g)\ÃZ\ÓD€€6c§—\ÎhùzµŠ\"\å¤_¢š|·k$^}¸D¹\Ü\Ì1¸\ã¥[]kJ/\åœôòêŽ±7› Ž1¶7\0¨\Æ8\Æsü¨KP¹‰\Z\ÝL†\êøAZº4)ý\Ó\'\n*¶‚ª.®®›\îÂ„Š\Ð\Ó#òôY&þ)ø?‰\Çô­63Ý™\×1í™¤`7\ëøP]\æ8\í\Ñ>ñb\ß\áW/˜5\Ù¥Bª.5ûhGE\Úó4›\Øë¯£“\ìp«8\Â\í\ÜOlõ\Íb\ïX´™%<0Go©bÆµ\î¼‘\ØFvúöþUƒ­°Ž\ÊDSÔª\ãüý*å¦§:\r;4ÁKZœ\ã—úÔ‚¢ZRsKIKLµ4tæ¦¯A@Q“]¶‹m-†šª-$˜Jw™#+†ôáˆ®B\Î#5\Ìq\â Wo­]½ªY\é¶e¼\ÖÀ\Â\0\ëýj¢TQ\â[\ÄHQZ)\âWnwŽ	ü	Î‰m\Ën(û}6Ÿ\çZ^&—6öP;³‹7\äõ¬¶§8¸uôM”‹\æ[R\Ù\n\àzm4\ásl­Ÿ,\íô\ÚÕšVßµ\Ó~F–ø\Ï\Ú\Û>˜4†^’\æc¶ £ý\ÃN6\ÃþX\ÇpÜš \ßÜ°ü\ÍI‘¸\Å$„g\Ç\0ZY­ÂÑ–c\Ð\àñG›nHÊ°¨\ny¦6p«–fZci\Í\Zo‘\ä	ý\å‡\æ)\ê–žØ·ÊŽƒ\ÙXÒ‹›`€yn\Ç<’§ò¬Í–Á±\ç¹´:[ò\Î\Äý\r+vI‘¥Ü±0\\ýÝ¦‰\'WbDW\Ð/õªF;OùøsøRmµÿ\0ž\Íÿ\0|\Ñp4>\ÓÛ¶Ï…‚>÷Ö¢Y\Õd\Þ\Ð\Ý<\çUZw•ÿ\0\ïšU[Lý\ç?Z.†ºóTD–\êUQ“þ~=S›mvŒw`)š}¬+ºU\Æ>T÷5\Ñ\ÛX\ß\ÛÛ­\Ä\ÌÊ‚¡Ÿ’µZ]\Él\Ã>\Z\ÔG\Ïýtÿ\0\ëR\rß“‚aQþýo\Û\Ü\É\ÔÛ™e\ç[.\áY:”Mö©?w\å\Ø*?„÷J¹\0ð\ä\Ãýe\Ôø\Õ\Û£>Æ¿M½N\Ì\ZÒ›Ã³&˜·2\ÜCX÷\0\äñšM*\ÒXg\0Ja¶õ¢\Ê\×\rYY´m>)œIqq.À>\ïs\éô¦\êvvXD\éL¯€\\\ä€=\Íj\Üyž5#yÚ¤\ÝMaxš\äø­\â?,+\Ï\×ü\æ…nP’\Õbš4;pjAmô,vÍ™H8Ç­_µ˜[\å\Ð\ã#š–4Xº¶{i6?\à}hK\n¯y¯¥\Ê\ÇÅ¸©\å³S\Ú\ÞÛ˜Q™¶†m¼ú\Ñ…$VÔ¦ž\Ð+Æ ¡\êH¨,\ïfrCs\é\éZ:¼[¬Ø©nx\Ís3\Ç !ºzô¥94\Çtt²\Ì\Ë\Ù,{»ñœ~U›-­\Ä\îK\Ì\ÅOv\ãôª\æ\î\àðeü†*y\ï¹?SY¹Ü¤¬\\ðB¸yQÉ¨\ÚKEnC\È\Ú5T(<w£eMÀ–K±\Â\Çh=qU\É,FzÔ¸]¤ZŒ`J¹\éšlYj1\ÃC \Ûå”ÿ\0z™ª\Í\r\Üñ¼_0P>µ¬‘‹”ò†\Æ[Ò£n@RÀŽ¹\ïW&\íbR\ê=`™\Ì\nŽ¾\Õv\ß\È\ÌÀ”œ\à³A?5\îDcËvª÷®V\Û:[oÚ±ƒ¹Ž:\Ô3H-\åd\'§\ÎsPAvD ®\íÃ§µ%Ç–ï¹œ©c\Î;Ð£® ë¹‹\àûŸZIfWŒ¨{¿LúU=ÁNv“\Æj\Ü\È”˜\Æ\Õ_\Ç5§. @c.§p\Ú©\ëQ<Å²¸\ÊjÑ·]Áv®\ì‘ÿ\0×¨V\Çý_\ï?\Öt\â­DA™›ŽzR¬¼\nry\í\ÐUˆa \Þ\Ûøæ¢¹¬\Û2sŒqI§p\æe\ÈÁ\È\Í0«°!ˆú“Aa»nî¦œ\ÎÀ`l!z‘Þ€\"hÔ¶\ârG$v©|´	¸\àöõ¦\Æ~q»ŒœúR–S.\ÒO¨Á\àPÀ«“\ëJqž1L\ïNŒUŒÁ\ÏOJ°ø1G\ÇoÊ«Žs\ÍN\ÜE° ‰T{ñ[:<Q¥\Ä\×‡\Úv¤&\àI¬h\×/¹üë¦²¸\íµ\ÚÄ…\ÞÇ·­)l5¹y ³–A¶M\Ì2F;T‰ƒFqÚ“œ¨Ÿbžašf»=;ÿ\0…b_\Ï­\Ñ@Ÿw….(w7CY\ÞlV\ç\'\åúU\Å]4(>U¾O\Ý\Â)\æ±&h\ìô\ß2Rw\ÈÀ:w\ÅA§5½þ¡jA\Ý÷{\n›Ýš+Y~H\á¶ó¹\0Ç©¬\ë\Ý6öbvlbrQ\Æ0+?\ÄAšõc…Œv=M[ð\Ô|“\Ì@EB\êO&š2\ÛIºƒKº65Ã°Dƒ§^õ§\r¤\ÚA÷;KÃŒZÉ·\Ô\Z\ëX[_²À\ê\Ò™—\'oÿ\0ª·.óp\Ê2òa\êN?­S%#&÷K¼\Þ\é\â\Ä$\îÝ¼tüêž\Ú5\éK)!Uºþ_Ö·üOº-\"\Þ\É€\ÛG‚Š\Â\Òu(¬®\ä’&•°‰\ÇjSZ\ÜÙšE7\ÒÆ¿Â@üy¬-vBUGfr*·srÖ’\\\\•Ë—ƒØžµ—«\\\Ì“aÙœ~?ýjIj)¿t¡J)(\ëVb¤ÁÞž\r E&”gÒ€‡\Êi«\ÐS›8\Å5z\n\0\ÑÑ¤K{Ä¹–6d†qÚ­\ëS‹­S\í\Ü/#+\Ï\Ýö£D\Öl,­š	÷,…\ÉfÛGj³u}£O2´ÁxÜ¸9ükD‘{\Z\ì\Þm\ì2+	qó\íYfN>k \ÇûÞ´I\'úKm \0\Ã\Õ6Ë ¿º˜”^œp((‡\Í\0ÇžûÆ“Ìˆ\ã6\'ó4Ïµ\ÝG›\Ðú\nwÛ®øý\æ}\Ñ@ \éh@ösZ\Ð\ÞÆ²	c$P0‘“´§¨Á\àŠ\Æ\×}¤þô¤{Û–r‡ê¢„\Â\ÇGý£\äI`\Ø=z\0ý*¼zªZ\Ë$¥cb\Ã(ùô=«Î—=þø\"\Þ\\*\í]˜Š(¸XzÎ½M§9\Îwb”\Ü!?5™oø4\ß\í¼dºø\0Ò”j`gx\ç\Ñ\0l}V\Ë`?\å\×ÿ\04Ÿnº\è$Qÿ\0±]^I A&	õQþ\0A\éd?Zž\ÔI$ª©mŒœu\éC%ñÁ‘¸õe\Æ)\î$ˆËŽNw-4#¤}1G¸Œ½ûÒ‹%YDÍ¨)q\Èfj\åL~é\Ä\Ó~\Í)þüjù¼‰·™Öµ­»\Í\æÉª¢?ª<\Ò\Éœ‰¨F\ÍÔ±l’~µÉ­´Ã OŸs6Jùzœ\Ñv;‘Cjò\â;¦r}\Zº-?LŠ/9\Ì\ç?xö®4ù\Õòd\0ú‚jü?hÛ²K»†\\c\È\\Í«!-\rv{m.þk‹‰\ã‡\ÜÝ’MsŸ5\Õ\Ì\×N~ûqš¾¶ð®HK\ânME:ªF@J™>€Œ‰†%8¨nee@ˆ\Ý\×\éS„f—iM6\æ\Ýd¼X£<\ã“\è*\Å\"µ¥´³Ž¹5±1G‰‚¸\Îá‘Œ}\rA<ñ\ÚDN>Qýj‡\Û\çÝ’A˜¥°÷6\á¾òŒ‘0.]~]\ßÀÕ‰p»Y‚ö<T\ÆA<BE\ê½j)9æ¡¶	¡9\íšsŽM\"G!9TcøT¢\ÚWöú\ÒWny&œ\Éf;H7\áOx\íbùX)ürh°LŒ‡\Ïôªò›Š°þ^ü ;}\rW|\î\ÈP€™\Ê\Ò\ÈÅŠ®9ª±\Ì\Ë(ry\Í\\ò‹\Û\rÆ©IDû[µZB6\æ¸,ŠxÛŽ€U7b\ÌËŒŸLñD`\Ëk\È\ÎGµ¿—Œ‘\Æ+­ \Ãn¶FÀM9 ;\r\Ø8\ÆjhØ2i\ç$(©-­Õ¬™\ãŸ\Äÿ\0œU\Å\\lÕ´Ò´û\Ût4«¼d\Ô\íR\Ê\Þ’HÙ·öª¶s›H\Ú=À\áŽýi·—†`£=5£JÄ«\ÜdPÂ±\ï%·É•o¥M%¬8I¤‘Z>Q€9ª%™§•ü£ ükCs_\é;0Ÿ!9üª±il\0+prq}ûb\éöL]®1\ìOÎ©#¥Ìƒ\î>ö«7\Ä\Åpza\ÆáŽœ\Ó\ê.‚E¦X\Ëp\'c¹\ÅY}\n\Ý‰¹`§©Àª¶c0Í°`23\Û\éR›\Âñc¸ŒSMl\Éi“Å¢[.|»’OC\Æy¨Ÿ@vE\Ñý\ßþ½6\Ú\ã\0Ä€s\ÔÒ›²`Xqš/\Â\Ô\çß¯JE7­=\ÆW\"‘F©,C÷ª\Ú\"˜|p:w¨Ñ€¶\Ç_AB\Ë\É\'¾I­£·!·Ðµcn³\\Fb,\Ø9 J\é!#yeŽ\êqø\×?§]%«<\é\Ú>\\[\æC<q\ÆU˜¨Á\ì;þ•”þ-Ž\ÅÈ‘¡´Œ·\à»ó\ë\\õ¶\Û\Ý`V}\Ç#°­^t†\ÞR¬wch¬ý\Zf¹VùB`nÔ²‘sW¿µI£‚a¸\"\çsÉ©´mdyg‰…\ã\îâ±¦\Õ6M!!˜\ãr\çúÖ©”Ã¡›’ K\"ñ¥C\Z3µ9\Ë$‡Æµtµû‡\áò\Z@\Ïùð?¥cE©y\Í-§Û»¹•\äÖ¶µsöKh¡\n­G\Èx¥P¼3ýFkƒÈ0>¦º\"<\ëûn¹s+}Ž+?Ft{3\"[¤>kc\ÜòkOJ~¥y1\êTD¯ú‘ü¨Z°\ègø¢\à=ûño?eøf-òFŸ2\\Ÿz«¬^¼\æ\îB\ï%\Ú	\îÿ\0¨V§†#\ÄÐž›Pµ%n;TTif\èwNJ\çõ7\rz\à·yþ¼\ï1oÖ¹ù\Îû‰4\"g±§H8})™;Ó…5{Ó¨iA4‚—\é@¡;fZ…Ïµ0)\Ë‹!b½yõ­ö‚9-\Õ\ÒEÝ€I‚=…c\ÝÇ‹¦\n:\àôõª²F©‘‰c8{Š¶—€.	V>¤óQ$\ÏZI£\Û+s@\r_,\Ëõô\Åc\Îw\ÓüjŸ=	ü(ùó\Êþ”°QG\Ï\×\Ó/¦*ŸAùQù~T8ˆ\ËùÒ˜€\è\à\ç\×«þT¿•\0N°ƒ\Æ\à>˜4¦ÜŸOÎ«† \äc4\ï5ýhÇ‘\ÎA9ÇµIm‹yw²\î\ã\àUA#–Àb*Õ¼>l[›9õ E§¼\É\0˜ô9&¨M.ù2\Äý=*y\"H°K1\ÕûÆ›niò€`ò¼\Z°\ëžMb\Ø\Ü5¹,9t©\ÄrN<\ÉK1n™\è4\ÄÑ¨žH`d‘\0÷j|·\ÖÑŒE\"t\íT\ì¬\ì°\Ïs2Œpuý+V?\ì[x$Ú©,˜;7+0ýjµb3Vö0y-¡©–ú 2±J\Ã\ÙkoJ\Õ4ÀR9l\áV#hdLsþMh\ÝOjdo•[h)\\õ¡\\L\ãf\Õ\Ò<!ð\Ú\é.\â•U†p\Ã<\Ön¨Á\îµ ödF¾ÃšZ±\ìTº¹Š¸r\äqEŒe i\ä?3ÿ\0* WÏ»\ãø›´®¤ªÆ£# Â¥Îž)å‘¤d\'&«2‘Á}kMfaü4?—:\âEõ\ïE†Q´}²\í=b­\Åy9\æªM[\ÈU\ÎA­WeK*ðùÛš™s\É<;TOz\å’~-U–ûŠXŸj•-%o¾Á}ª@l’\Ë\'.\çùTj7~•v;8W\ï\Õ`,qðŠ£ð§`(\Çm+Zz\é\î[Àj¸eQÔŠ\í#°¢ÀV˜}ˆpsÚªÜ +‘\ÕN*\È(\Ó1w)\È9\íU€P]²˜;MP‹–v77h\Ñl“\ÔóÖŸ.vÿ\0óÏž~õtº%®\Í\"\Ø\É]ß™\Ïõ«\æô6Ç¶™tl\ÜqbwU6Ù£ƒÉºP¡\r¸r	®”Û\îƒùRT=c_\ÄP´p\×R\âR6:žõ\nHYù\Èõ\Þ}Ž?ùæ¿•\'\ÙcÀ´\î8¥,—C`\ëøÕ:U¯¤bD\È\Ï÷…uf\Ù=\åLh\n\î)\ä·Û²œªƒß“K4ŸhTRTR3]9¶‡¼ø¦¬q\"\ã\ØP7y.$ŒÃœ\ÒG£»\Çû8®‘’\äÄŸZ–\Õ1ô5-69\"\Ì2d\ÜT,$ÀÊ~•\Ò<v\ï\ÆX~4\Ó\r¾1»ó4XV9m„b˜\ìsZþJõ\Ø(h\Ô);F>•@d•`˜É¨\Ùq\Ïb*Üª».ÿ\0d\â«;0\áT\ã\×«jÄ¤Í>\Þ\æ(\áT`¨\Çt™\0âµ£›‚\èwž¼ú\Õ\Í[^½³d\ç¨5\"jsFÍµ”¬‡$š\ÊÅš\Z\Ä\Å\íbm£{jÅ 6\Ú9“f†p=Oÿ\0Z³$ž+™<Ë‚\ê:(QV¦\Ôc–\ãPÊ«\ÉùO\à)44Gˆì±½«\'\0\âµ5\×\ÄPÛ¦\0Q\ÈN\Û8\ÝdyNW¶\ÓLš\â\ÚñÝž\íc\ç#5GÐ‡G\Ûý¬®\Ã+<~B¬k\Æ[›Â‰‘@Á4\í4YY¼²…™Ÿ…\èT’\Ígºû\\—pÍ½”“Ÿ¥WQ-²,Û ?\ê\ãÁôú\ÔÖ²‹o\rMtr\Zr\Òg\êxý+[•’¶¶¹ˆ;ð›\0õkY¾€i0Y[ÊŒ\ÊsÀ¢ \ÎjúM\é\êk¦\Ñ\Ç\ç0\ê±>µ\ÍN#3ƒó|˜ƒŠ\×Ó¯£\0–ý\ß=\éH\ë¢~\ÔFrQqš\Å\à’H?^k´-sŒ³\à\àõ˜c\Ò\ÚJ¤ˆš¹7\Ë\è:_”v?Eþ’zZ½.Ë³ÿ\0.N\Är²L\"ô;³\Í—û§ó¦oKF§]Dô³j,¬xeþ\ï\ëF\åþ\àü\éV\ËR?ò\èG\áJ4ýXÿ\0Ë§\ëÿ\0×¢Á\Ê\Ä\Êª\04¯´+*Ž€ž´ñ¥k¥¨ü\ÇøÔ‹¦jP5Í¸ò—“µ¹¦(²„\Ö\à\ÌX7sRmj\Í\Üe.6#ó^8\æ›&™—\Ô\nfƒ#¡\Å7{xÔ­kuÿ\0<òª¤\È\nœj\0“{zÑ½½¿!QOÒ\Ï\é@y­\èŸ÷Àÿ\0\n<\æôOû\äT\Û=)7ŸJ\0±\ç\î§ýóGþ\Â~U_yô¤\Þ}(Çœ¸Ÿ•qþ\ê~U_\Ìö¤ó=¨ÇœºŸ•{Ž\Ñÿ\0_\Ìö¤ó=¨v•œa°@\íŒTl\íŠg˜=)D‚€$…\Ú3ò\ãØ‘H\ÓMž¿ ¦\ä\Zž\Þ\àÃ•`¨ \Ù\Î\É03S¾E-ü¢iT\Âp\0Á\ç0X\\nÚ¬§¿ø\Óe…\ßQ\0z*¬IgEW[¨YÕŠ¡\ÜsœWUt->[©”\Ã\å®F\Ü\ÝE\ÕÔ\Ó5kR\Õn\'³\È\àÀQB\Ð”¤M|¥¾\è;›\è94¯¨]N\Z?0\ÜcÚ£]c‘ˆ\Ã8\Ú3ú\ÒÛ¡BI\ëR\ÆOinco1ˆ$žõ\Å\Êù„HãŠ´ù\Î;\â©O;F6Àc¾Þ¿0goR=±OYƒ\ã8â£ŠIŠ\æFfB{óšI\Ä)°p\êc~†¯[ö@¬b³#|Œq\È÷rq\\\é@‰‹\è)¦@:ž*0²±\àb”B þñÀúœŸ\ÈQaŠfô¦–‘\Î\Ñ\×Ó½.ø×„R}Û\Ð1¦`h=@\â¶ÿ\0­`¡<\Ój\å”ðBe£iKü£¥4¬4ç¾³¹dp¬˜\Çx\àœ~†³\Ò±«ƒÁ\ÇÖ¶ôE<sHÊŠC	\ÆGn½Z\Èf\Ì?¸ž½iˆ\ï\áUX\Õ`\0\0&\Ðyþ´\è\×÷kÀ\éN\ÛÀ\àTŒ\Ç\è\r1•ÿ\0\ë\Ô\Ø>£­!\Î01@\Îð{\Òn#¯j‘‰\ìüi„0H 0>\í1œª9‚šc…\Å\04\ã°þ\í9\È\èpj6\0ô4Àc\Ø~u+\ÜO#=\ê\Ü3‚\r ªÂ˜È§¦?\n\0#°¥\'¨<gÖ—w¦Œû\Òô\ãš`.®hÛšNqÞœ	8 òÁ\ì?*QwùR\àŸZxZ\0$…@~•2 ã•¨À4õ\'§\"€$ƒ\×o\åO[t\îò¦.zdŒT \äðM\0Fö«Õ‘JÀb¬-¼RŒ´q·\ÔS·8cJ\È\àŒ\Þ\Ý\é\0§N¶o½GþHt{=Àýž:‘%|\"úÔ‘Î¥¸}\Ô\0Á¦Áõ\Ñý—l\ÝmÒ¬‡$\Ó\è)ÁsL\n©¤\ÚF\ÙKt\êjU³„yKVr}³ô¥\ÉÁÿ\0\n\0„ZD>\ê/Öž-\ã\Ç\ÜZ“?\äS‡J\0`Qü+Š@½€¥G¡[ÿ\0:\0Q@4¾P\\aA \î\ã?.\â;P‚\ãøj¾«\ì¹Ï¢\çõ©÷6G<\ëP\ßo–\Æ\â4RKFÀc\é@#°‘ùgV?Ò­¶±qµc…cŠ5UTVs±-3v;\ÓDj—\'†e ÿ\0²+9\ä1±\ã<Ò†´\ÙT²’9¦!\r\Çû\0Rý ¨xõ\â (\Èþ”óƒŠC&(*h3!è¿­CŽ>\èüñK´“\Âú\Ð¾|g€2hó£\êT­E·ƒ”ÿ\0Ç³H¸(S,}B’=…/›9À?J„¨\îùÑ·‘ÃŠ\0“ÌºŸÊ”<Lp¸\çÚ¢sÂœÓ¢_Ÿ<\Ð—Ÿj\0\ÇÒšeµ=b?ª™£4\î+LV“p¸SùUy\í$‡‘–__JLÕ›{£\Ë\'ÌŸ¨£F8¤x›rþ½?\Ïn\Ãõ­³¶|Ø»ú\Z„\ÙÄ¿\Â:vatS>{T¦òv\êøú\0*_\"1\ÑE¥¨\Ë9f,:zþ\éüªR@\æ˜\Z•ö\ìO\ï*Ý†s¨O!¶Š9#Aó<¼(\Çzª1,ü„R~‡µwZ]\â\ÛX*­¸–\Ö4Ã»X’£§<óŸÀ\Õ+u8GO¸³š4ºUDaò²}\Üw¬\édS)X\Æ#\é\ÏS]n³+^\éò$¶­A–€\äŽ8o\Ëõ®Q-\ÚFYpz\å‡ZO]††EòŸ÷O\éV£cñÛŠ¬\ÊW\0\äz\Ô\à`ª2N1Hd)=MFe«\Ðh³\ÊG4Ps“ù\nÖ´\Òô›r\Ì\×þ\ÙÀü…\0s@\É!\Äh\Ì\Ù¨X¿\Ïò·\É÷¸\é^€—¶Ð KuŽ1\è£“®Ci}“u\í \ãÞ€9Û&¤N1P©\"žhJ\Ë,Z4m²H6/¡&£…6„Lr]Aü\é–\Ì7Œ†=\Æj\Ô¿\Û\àŽA†iœ\ã½1\ÒJ@ qJex?CY\Âns\Å8L¤äš›»\æ~\Ã/qŸÎª°8#ñ¦I\î?\n\0°\ÎÀ“¼þT&G¡\íU<\ÆqMiOªÂ€,™:Ãšauõâª–\Ê\ã#4…ˆ\ç#Þ€,1\ßÿ\0¯Q4\Ë\Ün*#!\Ï$ô¦³‚1Ö€%f\\šŒ¸<L/‘\Ð\Zn\ìP›…º\ã5\É\'¥4óô[\é‘F}\ÍDXñ\Å81¸ 	ŒÓ\Æ*-\íœs@v÷<P¹\ç½;¿?Ê¢V#øM8HOðš\0›#±§Œu\ÍVz¯j9\î‘@†\çó©€\ÆMVY1\ÈSOY7„4guHž¦«‰?\Ù\Å(•‡ðqõ Jþ¼\äR\ìWu}¤7¨¨DÀ?SJf\'Š\0²\"œ3ÀS’g,£FOµUó›Sƒõ¥Sò§ÍœH\Å\0_V\Ç$Sƒyª«0\'ŸÆœ\'ý(\Ø`q\Ô~Rx\Î3U¼ø¶\ã\æ¥	\ÈÁh‚\Ë\í\éUÄ‹Œv\ë\Í<H§3\Å\0N\n¯\'­\nÉœ\îzb¡ó@$Ò€\ë\ßØŠ\0›(G^¾Ô¡\Ð½\ÓÚ \Þ§\çJ$\Ï$œ\Ð«\Ä-\ï\çE\Î*Š\Ê\ÈS×¯½t(µ\Ë%\äx=ñú\Zç˜¡$ô\Ïj\0wžþ ý@4ñpq†T#ý\Ð?•V%IÀlSyþÿ\0\éL{\áo½ü\éC[ô1¶=7U@3\æJ?ß ;mOð8üsG—h{\È?\à#üj6:\æ“\æô¢\àXZ\ç>dŸ÷Àÿ\0\ZC\r±ÿ\0–²~+ÿ\0×¨P‚\Ê\àw#œUŸ*\Ïþ~\äö\Ëÿ\0¯@\rAŽ.~\"\ç\íò?\áQžvÁ)ÙŸ¾WúT\ÞD®\×ñF¢\à({\Ü\ä{\çü)\Ù\Ð`>\àPJ69Ta Ä½\r4ô4g0ú\È?*?sýùüV\Ü}\r¾´\\A#o¹(Ï£Œgñ¦2²:‘LH\Ý\Æ@\â¤Y^1±\Ô:\á?\ÓÒ\ïf6#úÔ­z\í\ÕW5Qˆ\ÏËœz\ZM\Þ\Ô\\,X3±ô¤ó[Ö \Éô¥\çÒ’\ï\'©¥$\ÔX4ôz°>”)y&‰¢L\áF\àzß°¿k‹0Ÿht’%\áO@Es+q,v\Ï¹8b?M¦\Çö™\n³2\íþ!Ö‹\\F\î§yYùE˜\Êç‘‘´\ç\\\ë[K‚\è¯\\¡\Î*Æ£“•R~fäž¦«A¼…ˆõÿ\0?Ö€d»g ž[cd»N\Æ\Ø\ÕOSó7ùüª&ù³@7\Òÿ\0|þtŸm\ï7\çQyT\áµ!ûk÷cN\Í\Óq¦3Ú¤[@{P{$8\éÚ”IZ‹§+°©K„}\å ¸\æ#¡\äV–œ\î÷{d•Þ¬&l¼ù`š¹j€ª(\0zP\Ög\ÆrI§yÍŸñ¦\ã=±N\Û\êE\çQö†ƒLÀ\Î$Rœ“\È\é@\Çy\ìG4‚\\ô8\Å7\Î}©0Ö€¸w<SI½GsQ’@\È\Í4¹.1Ž´) ñž(\Ü\0\à\ã>õaÀ9\æœ1\Ôr;÷ \Þ{“N\Ü\Ø\ÏZiÂž	›ó”!9>ôõ\ã=\ê4\È\ë\É4ÃŸÒðG­\ç4À8\0š]¤\àƒßµ\0Hz`\Ç4g\ÐqL9¹ö¥\Æ1\ÈÁ \ãõ§…\ãüñQƒ\É·LS†\î\ÆhÁi\ë\Î1ŠŒ\íO@\Ï~\Ã(\\cŽ)\àƒ¥B)\éŠxf\'µ\0L“šx˜\à\ç½D€\ÇÞœû~´0§Êž™þU\Z±ï¥(v\ìhm«À=i\ØPO4\Äb\0\àÃ¥81\Æs×§¡ mV(=ªE„eAö5c$)\Ç4Š@8\0\â€%Nï”Ž\ã\'\"”2–5	€¹÷\ïH’(‘“;OÝ ‘kv \Ó÷\Æ\Ã\å\ï\Ð\âª\Ï)i7Ä·sþED·*=›ÐŒõ¨ùxÀ\ÃP\Z3Œcñ5O\Í\Øf<÷\Å1n\â|\ÒFÞœ\Z\0\Ñ.‡•À=sH?:\ÎK\È|’ª\ç±\â’[\è\Ð\ì]®}²h\ä\Ñ\Ç4Mœ£š\ã5M>K	ˆû\Ñò?¯·Öºu¸Þ›Ž{\ZŽ\æH\ÚŽP¬Œ9ùA -†iŸZ×¼°€sl\ÒºW5š\ÐJ:\Æ\ß\\P`‘\ÐÓ·7­7cÿ\0u¿*]ý\Ó@¸úš’,¯\åDíŠˆ#wr\Úå­‡\î‘A=I\ë@6“§\éö¶ªn£Ži,O8ö«·I–)­b¶?*\äN¥9\ê\ÔÆ¾™º±¤\á°\Òƒvö,y§¥F0¶±ús\Ís\Æ\êO\ï\ZasL\r\í¬K7–Ì€ö\ÎqPy6iÔ»}MS21\ïM\Üh\äŸf+„R¾ùª\ä\ÏQdÑš\0“8þ3Fñ\ß\'\êj:1@\ß\íK¼zS1KŠ\0]Þ”žc{Râ‘—ŒŠ\0Uv\'“V#lj 54oÚšY¢m\Þd]OoZ[[¨­ØŒ¬\Änðµ*KŽ\"¥\Ê09\n~¿\ãLE‹™£¹U‘#,ƒ\ï4ƒjþýj»°m¤®\"^@\Æ7§¥`\ê\Øfqù\Ô2\Ì3œ\îoZwBHü\Ç\æ4\è¡,€œóMµ®$\Ü\ßtu5ª¨¡q\ÇN”†R[sš™-ry«j6\ç\åZ~8ùFy \Ø2JF€s“øT›O 4õŒƒ\Îz`\Ð{@ §\01\Å)\Ïö¤\ØGB@ô\â€ª¸\É\É4\à?Ö“¨\0Œ\ïFFy\Çô€S\ØH\n‘÷±ýi1\Ç+G\ÊGL\n\0PPŒ\äRô\Î>”Ÿ/;G\ÔR0 ;\n;/š\Ç8\ÆqG;pzRGcš\0c»š0OZpc\É\"“q\Æqš\0i#<QŸn”»†1ÔŠB\Ã=p(7Ç§½€ñA9\ç­! ñ\Ç\0\ï”t\'\Ü(ç¹¦äƒŠs@8žOÒ€s\íIœ\ã\'ò ¸dzb\ë\Í(\'Ò˜\\\Ðúô¥\Ó9\'Ò€’3\Å*±\Î}*! \ãj¶s\Ï¥\å~\ëq\í@rsøS³\Æ@5vÛ­¥(öV 	ƒg¨ \ÓûdŠ‡q\ì\èE.\ì\à\íc@Ý½…=Gn†«H\è„\àôÈ¥\Ùò\ÛÖ€-À%ˆ4£\Óqüê¹‘±€œö±NY>\Ø?Ö€,)*~ñ#¡&¤?*ô\ÍU¹\êX\Üñ¥/ \è®ß•\0X20uõ©–<n;Vx¸¹\Æ<²=3Až\ár<}\Ú\0\Ò\ÜH\ÇF¤Â“‚ONæ³…\Å\È ¾§\æ\\œ\ä¨?J\0\Ð;G¹ ·m¤ŸNÕž¦\çw\Í À>\é\ådm\Å\æc\ÏfiV2\ÙSŽ*°Ÿyý\Þ6ú¯5±aw2–\Ï¤X“\ËÚ‘ñúP\ÌñH\Ò\Ï^¹ªòJ¬6¬d\ï\çNònŽ=i\ÉTž½\0Se“\Ì\ÜÒ„^›Tvüi\È$o˜¶\äÇ¥YkUŸ(±>†š¶û\×hF_ø\0@\Ð\Ê3¹@=¹¨Z&+\ëô«†\Øÿ\0tžƒ9”[}Ó†\'‡C@OnÇœÊ¡{y3\Óô­Ö·$}Gza¶#±\ã¯\Ë@-t\Ó|—=\é[¦p™ô\â“\Èfÿ\0–_­\0`ùF“\ÉoJ\ßkt\Ýþ©†z\ät¤6«À\Ø*\0Áò\'”ks\ì\Êy6;šCj§¤dÂ€1<–£\Éj\Ú‡#1þX¥û\Ïú¶\ã\é@žKQä·¥mýŒªi¿e¹VöÀ\Í\0cy-\éG’Þ•´\Ö\Ê=?G\ÙrO½01|¦ô£\Êlt­¯³\îœ})Eªóœý1@žS\ÔyO\ØVÐµ(zdg¥N– \à\ìÚ‹Î˜\\óŠo—(þ]0¶c52Z¯}¹\ëE€\åG™ÿ\0<\Ûò§„œýØ¤?ð]O\Ù×¯¾ZqŒu `[]¶\0‰½y©¡\Ò\î—\Ú¦k¢h¢\êO\áš\nFz8º\Ðl\Ù@GlU…€(Á\ç¾jÇ”«üe\î)T é¸ŸzA\åNÞ´\ï\'\åÜ S¤\r¼œv\Ð\Ã‚zw \Â€\à\äöô¥HÙ‰\æ¤\0“ŽG¤,\02y\ì(\ß-—9ŠhŒ\í\È8>)û\Êòr}úR\n«»?•\07œô\ëH‹@§4ƒ•\'½&þp=ph¹\äô\ãÞ·L­;p\'“\É4‡nüòA•\0 \'©\Æ3\Ð\Z	v¦7ð8>¢‘¹lž\n\0qQ‘øRdœúú\nic´Á¤\Þ6\ç\ï@×¿=iŽ\Ò\ëŽ1\ï@pO\Þ\ï\ÓÒ\n}¾”Œ\Ø\\sÇ \éH\änÂž)¬A\à·\á@•\àô\ë\Å&áƒ3LhÚ¸\ÇÒš\\zøPÛœi¤ò\íNWô\Î\êLðr2(¸<cÛšPTr\âšOB2i7Äœq@SžN@\"H<zQž™\ãÞ€\å\ãži\ÊGLqI‚Fzÿ\0AI¿œc4\0ðH\ä\ZPÜœði™ÀÁ\Å\0®\ìc\ìhbFòA{P®œ\ä\Ô@\Ý2=©A\Ëg§° 	7Œ\ã9d\à\à1>µ\Ï-ƒH¼hq\"œt¥R§D2x-Ó²Ò‡ã© )*\ì,?N\Ý\Ü\0\nª	U\ÈúŠx‘°=ºPþf\æ\Âô\ë×šw˜I\0ô\Õ_–\è¼\Ñ\æ`Gj\0°$>\\u\çÿ\0\ÕJŒw\â¡G\Æ\äú\Zpr\ã ¾”7™åœºO­‰À	yú\ÕW;rIÁ\âž$!F\\¥\0X\r¸(8\ç„r0ñ\×ÿ\0\×P·«ð9 ‘08 	šO›€>¾ô©Œ\ä¦\æ¡ÝŒƒ\È=y¦ð\Ç\å\0P\æQœ€=)Ž\à®J\Î:ð¨\Ë0¸\å±-ŒúPøò\È]£œqM/Y‰Ž£\"‚\ê•9\ì:b‘\\nÁö P¹CÇ¿­\ÉPrAõ\Å#±ô\ç\ëJ:sœS@8;”Ÿz@ù\è§4\ÞIn}\éb¤(<\Z\0˜2q\Ô\ã\Ûù\ÓCn\àŽµNB\äB33p(L)\å\àv¤*ª2Á\é\Æ)¥‰\äÿ\0J21†\Î(\Êž:õ9§&ÑžAúv¨\Ø(Á\ä\ç¦iK\r½pAýh@>SµG¶GZM¤g¥C1qI5!\å¸fÏ± 	\n\ã\åaùL` d÷éš¤#“ \Éõ\äÓ³•Ä£®?ýT\0ðt\n\Ýó\Ó—÷e[ n\è¨„ˆ3žž™æ¢™\Õs´\0I\ë\é@6\Éœ\Ï w«7†$‘Z B0ùGÿ\0^ª@!iJ\Ü9˜õ©n\ã–@Wò2ÿ\0Z`H…	Tb†`\ÜmÇ®MGmaµ¸<SŒa›»GsÖ€¶3Ê¸\'¨£m¸\'÷dŽüqQ¾\0b\ÞÀÑ°‘÷¸÷\æ€$a\í\Ë$ÿ\0³N\ÒLdx\Ïz\íP»\Ôc¦ 9,{\Ð_\Ë<\îUõ\Å9¼±\Ï\ß˜Bƒ\Ã8\èz\n›\0P[<\â€%.‹ó}\ìŽ:\åM,z)¦†fnYTõ¡‡AÏ¡õ \î”d\åI#\Å4´­\'lôÀ=i¼\È\É¹”}\r\0\'˜P‘°\0=\èi\Øõ\àf‰\ã8\ÍºÛ„\È8¤K¾2S¾ô\Öwƒ=†:PX“€\Ù\ã½&9\Í\09™¤\èªy\à\ä\Ôg;½A\íC3œN:qÖ“\Ì*¿1\Ç\ÐSü\Ü\ã“Jûú\ä\0y\éK\É ƒ€}©6ð\âyõ€c£•ûüvæ”¡P3\×ÐšRrv«g#ša\ÆzóÖ€®I\ë\ß4\í©øz\Òc$\çûR2 \ïŸÂ€[žy¤¥±´ŸZLz3\ïÖ•Ž9n\í@Fˆv\æ”6\è(Ý“\Æ0G9 x`G½\0’3\Æ)£\É :S…\'\Z;dAô \Ã1Û¹õ¤V\Ãq‘N7\0\ïŠ…À\æ€§šSòŽ™\çÖ—9n0=\é\Ädöü\04±#ø¨$ž}\éÀ`–/vò  \íô¤>\Ç=Ojz\ÉÏ¥/V\Î9 ‚\Ùw\ê=iÀúù¤\êI-ŠvFG ú\n\0\È9¤I\È\èM.~c\Ç^\ÄÑØš\0DÀÿ\0X§x©LñŒú\Ô`‚¯¹§q€y4\0¬[ \'\Ô\nVló´x¤%O\Ý\æ€F\â?—4\0\å;G…Ž\îß•!8\äÂ”|Ã@f#…À\È\ïA9\È\'\'¹é¡¹#Š3€@üs@\Þ]·w¡e%‰#4\Ìn¦Ÿœ\0¡H \ç\ç\0S[@¤Ý~he\'<ž?*\0yfV\0“·¿4›‰d‘õ¤Ï¯9÷¦†8\ç¨õ 	D€‚½\0¤\r´úç¸¨\×\'\'’})x ç± 	ŽpsŽ†“~Jµ!lc>©™(B\ã<dóÞ‚þ\Üõ¤gõ¦ƒ\Îh@\ì*E\Ü.3÷»f£ld8>”¤ \ä(>\Ô&xøR\à¾A=sÞ«\åzøR\ãwE\äw(\Û\Û\0´¡”ô*ËŸ§¨¥ö#“\Í\0=™[†\ï1I\Ó\0e\ã€jPF\ìdúŒP\ÌW‚\Êµ 	‚Z3ú\ÔD¯B\nŸZRPŒ2Ž(PT`ªö9¤Gˆ\îÁ#\Ôóœ\ÔLS\ÔsB¨\Æ0=Nh\\\07g¯sH\é\ÇN@E0ýÞ¬~”\Ü|¼1Ç¹ ²¸;›wªžEJ\× Bb \0>†£8Û’Ày\æ¡$o÷¦\Èòñ€Ÿ&*x\ÊþnXž*+©\à\Ð\Æ\Ê1\Ç\"‰–0˜*:µ\0JYwÝ½¦4™oÆš®\Ä\r\éIŽ£\æ\È#“H	¾s“€=³L\\œ\åG>Ôžc\É»ñŠD}\ê=\0+$d\àr}\éIŸ>¢c\' sF\Õ\Û\ÛÞ˜\n$P9PÞ´¦E8\Èt\â£\ÚC¯¤V,x\à\ÒW+¸•R\nhV\Îs´CIÀõ‚—p8û\Üu ÛŒ`œõ\É4\r zg®ZL©\íKµœ€X\n\06†\äO±¤ù3\Ê\ä\ÒŒñ\ÎGjLq»\0óÒ€9‡eºiYI\äñ\ÎH\â›×¼{P£þ]i	ùrs»Ûµ8€I$\0ýhÊ¶	4\0ŒK>?ZnÀz‘\×ÖŽwƒ÷}\è óõ \0 $d“NÂŽ½	\ä‘\ë\ÏCHI\ã? \ç¨$?G\Én”N\'\×<P8P6Œõ  c½F	ù\Ó\ØerFqÔŽ\Ôß—nG©\Í\0 \äñI€GN(b6€1šqp$q@p8\Ïµ¸!†qAw\ëK€AÀ=(q\Øó\Í)À\È\'ô¦†þG·£¶±\éÉ \ÛÛœv§c\å\ïƒH§s\0:R©\çiý(:)\çu>ô\ì9\\¥4ŽŒ\ß0\'ƒ\ë@’zð§»øSK\Ç=iY‰\ãq\Ï\\f€ôýx¥\È8?©4¹\ã\æ\äf°Ž™\í@\Ç^¼f—ƒ\ìG¥7Œ\íÀ\Èô§q¸\ÈaÞ€XòI Q’F\éKÀF*q\Î¥\Ú\Åw1\È\0\Òrw¿Zv	ä“š=\é\ë€y?Ž(ƒŒÿ\0ZU\ÈÏ¿¸\É\Æ{Ð„½\É\é@þ“4º\r\Ãñâ˜¸Ý€{PAÁ\è_0˜À\ìE#0\ê8¦” @9£ ®:}(\ØžGµ(\ç¡úS@!‡û\Òr\ã@	0À$u8¤€Oñ¨Kv4\ä<›ó “šRpx;qÞ£ß‚O½80\äóÖ€zœú\â“#°8¦šù^Ànôn\Ça\×Ó½\0HIb\Éô\Üs‘ŒúRpNzjUw\'9ý(\0\Ú{.½ Àúÿ\0:p\\\àZQcN\Ô\0\ê~\ïÁ\ÆO§z¨Á\'4ŸN}º\ÐÀŒ\ÝjL0)„`õ\ï@\0r?*\Ùa¥)8\ê~´„Ž\ç8¤\0G\Ì8ýivŒŒÊc\äŠLŽÀþtW^9~>”oÂ“9\çú\ÒÁ\í@\årpF)¾[\Ï?(nû‰¤\r\Ïõ ù9%ˆ\0\Ñ.ò¹-šq\'##“@rpH\â€\n>ðzâž¹\\\ì|Ž¼ú\Ò9hR¥k	8\ïƒÖ”©\â“w8\â\Ë{\Ó\Ã¡\Î=)¹9\'(\ã‘\Í\"‘\Èó@bI\'†;\Ò|Ç…À\ï×­8\åq¸\Å3 À}>”À ž3õ\íH1‚H\àûÓ†ÁNA„QÇ¿j\0L\Ý\04£\ã®zPIR>^qHH\ë»,=¨\Ø$†\Îi¹\äŽ¤\Z1ocÒ—o\ÌF@ã  d\àÊ—-\Ï;\ZL‚œ‚i\n•&sž@ pS9\è8\Îqô\íH\ãª2€WÒ”÷\Î})\'vR(\'ˆ§\Ôn\Å* <n \ç¥\04\î\0œ\0\çH\Ù8\Ü}¨$tl\ß…H8\0ñ\ÏZ\0^‹‚¤Sƒqõ\ë\íHÀ©\n\ÝM.p1µO¡\0m\'\0“Ú§@x#ƒLV\Ëò\äN\êN8\Ïz\0RŠ\ÆIS\Æictö¤ù·\ãh?gp<Š\0ÿ\Ù',NULL,NULL,NULL);
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
INSERT INTO `examinationtype` VALUES (0,'Check');
/*!40000 ALTER TABLE `examinationtype` ENABLE KEYS */;
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
INSERT INTO `person` VALUES ('123456789','Yossi','Bitton','none','052-2222222','Karmiel'),('147258369','Barak','Itzhaki','none','03-987412','Jerusalem'),('213245658','Assaf','Tzar',NULL,NULL,'Nofit'),('302632195','Shay','Shahar','shayscal90@gmail.com','0509443347','Haifa'),('305003659','Ra','Cohen','none',NULL,NULL),('741852963','Dani','Danieli','asdad@walla.com','052-9878233','Jerusalem'),('852963741','Moni','Mushonov','none','054-1231234','Tel Aviv'),('987654321','Yossi','Bitton','none','054-4445554','Karmiel'),('999999910','Moshe','Moshe','moshemoshe@gmail.com','03-435341','Haifa'),('999999911','Yehoram','Arbel','yoar@gmail.com','02-435435','Nofit'),('999999912','Hassan','Nasrallah','none','054-45343324','Zichron Yakov'),('999999913','Muhamad','Death','none','054-2342341','Somewhere'),('999999914','Itzak','Zohar','none','050-2342341','Tel Aviv'),('999999915','Taleb','Tawatha','none','04-345341','Haifa'),('999999991','Yossi','Benayun','yossi@gmail.com','050-1431233','Haifa'),('999999992','Itay','Shecter','itay@gmail.com','04-3242341','Haifa'),('999999993','Eran','Zehavi','eran@walla.com','03-2355114','Ramat Gan'),('999999994','Tal','Ben - Haim','tal@braude.ac.il','08-1342351','Ramat Gan'),('999999995','Christiano','Ronaldo','cr7@gmail.com','054-234211','Ramat Gan'),('999999996','Bar','Refaeli','barbar@gmail.com','052-536321','Tel Aviv'),('999999997','Gal','Gadot','galg@walla.co.il','03-6520234','Tel Aviv'),('999999998','Pini','Balili','pinhas@gmail.com','04-235214','Tel Aviv'),('999999999','Eyal','Bercovich','eyalbe@gmail.com','055-4354352','Haifa');
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
INSERT INTO `reference` VALUES (1,'2016-05-11','Some Comments','HIGH',0,1,16,1,0);
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
INSERT INTO `users` VALUES ('asaf','12',0,'Specialist','213245658'),('raz','1234',0,'LabWorker','305003659'),('shay','11',0,'Dispatcher','302632195'),('shay2','11',0,'General','999999999');
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

-- Dump completed on 2016-05-15 13:19:55
