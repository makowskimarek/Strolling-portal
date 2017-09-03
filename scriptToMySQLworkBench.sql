-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db1
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `ad_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `stroll_starttime` datetime NOT NULL,
  `stroll_endtime` datetime NOT NULL,
  `ad_endtime` datetime NOT NULL,
  `privacy` varchar(10) NOT NULL,
  PRIMARY KEY (`ad_id`),
  KEY `ad_user_id_idx` (`user_id`),
  KEY `ad_location_id_idx` (`location_id`),
  CONSTRAINT `ad_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ad_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES (3,2,11,'asdasd','2017-06-18 19:39:39','2017-06-18 19:39:41','2017-06-18 19:39:42','all'),(4,2,19,'','2017-06-20 10:31:00','2017-06-20 10:31:00','2017-06-20 10:31:00','hide'),(5,2,21,'','2017-06-20 10:35:00','2017-06-20 10:35:00','2017-06-20 10:35:00','hide'),(6,2,22,'','2017-06-01 00:00:00','2017-06-01 11:37:00','2017-06-01 00:00:00','hide'),(7,8,23,'','2017-06-20 13:02:00','2017-06-20 13:02:00','2017-06-20 13:02:00','hide');
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banned_users`
--

DROP TABLE IF EXISTS `banned_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banned_users` (
  `ban_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `banned_user_id` int(11) NOT NULL,
  PRIMARY KEY (`ban_id`),
  KEY `banned_users_user_id_idx` (`user_id`),
  KEY `banned_users_banned_user_id_idx` (`banned_user_id`),
  CONSTRAINT `banned_users_banned_user_id` FOREIGN KEY (`banned_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `banned_users_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banned_users`
--

LOCK TABLES `banned_users` WRITE;
/*!40000 ALTER TABLE `banned_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `banned_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_inv_notification`
--

DROP TABLE IF EXISTS `friend_inv_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_inv_notification` (
  `friend_inv_notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`friend_inv_notification_id`),
  KEY `friend_inv_notification_user_id_idx` (`user_id`),
  CONSTRAINT `friend_inv_notification_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_inv_notification`
--

LOCK TABLES `friend_inv_notification` WRITE;
/*!40000 ALTER TABLE `friend_inv_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_inv_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  KEY `user1_id_idx` (`user1_id`),
  KEY `user2_id_idx` (`user2_id`),
  CONSTRAINT `user1_id` FOREIGN KEY (`user1_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `user2_id` FOREIGN KEY (`user2_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

INSERT INTO `friends` VALUES (1,2),(1,3),(2,3);
--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` double NOT NULL,
  `longtitude` double NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,45,5,'centrum katowic'),(2,24,-31,'obok huty'),(3,234,0,'przy bloku'),(4,0,46,'nad nilem'),(5,34,24,'nad renem'),(6,34,0,'w czarnej bramie'),(7,0,52,'obok bazy ninja'),(8,15,0,'za rogiem'),(9,0,15,'skrzyzowanie 45 i 74'),(10,1,1,'gdzies gdzie cie nie ma'),(11,1,2,'za sloncem'),(12,5,0,'srodek centrum'),(13,5,0,'obok zabrza'),(14,5,0,'przy fontannie'),(15,5,0,'obok stajni'),(16,0,0,'za rogiem'),(17,5,0,'przy wysypisku smieci'),(18,5,0,'3 stawy'),(19,0,0,'za tramwajem prosto'),(20,0,0,'za marsem'),(21,0,0,'przy pomniku zaby'),(22,0,0,'straszny pomnik'),(23,0,0,NULL);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `status` varchar(5) NOT NULL,
  `msg_time` datetime NOT NULL,
  `message` varchar(500) NOT NULL,
  PRIMARY KEY (`msg_id`),
  KEY `messages_sender_id_idx` (`sender_id`),
  KEY `messages_receiver_id_idx` (`receiver_id`),
  CONSTRAINT `messages_receiver_id` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `messages_sender_id` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,1,2,'read','2017-06-20 13:54:55','Yo Yo'),(2,2,1,'read','2017-06-20 13:54:56','yo co tam'),(3,1,2,'read','2017-06-20 13:54:57','a nic'),(4,2,1,'read','2017-06-20 13:54:58','to nara'),(5,1,2,'read','2017-06-20 13:54:59','cu'),(6,3,2,'read','2017-06-20 13:54:59','siema brachu'),(7,1,2,'nread','2017-06-20 13:54:57','Tak trzymac'),(8,3,2,'read','2017-06-20 13:54:57','Tak trzymac'),(9,3,2,'read','2017-06-20 13:54:57','Tak trzymac');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages_notifications`
--


DROP TABLE IF EXISTS `messages_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages_notifications` (
  `message_notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) NOT NULL,
  PRIMARY KEY (`message_notification_id`),
  KEY `messages_notifications_message_id_idx` (`message_id`),
  CONSTRAINT `messages_notifications_message_id` FOREIGN KEY (`message_id`) REFERENCES `messages` (`msg_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages_notifications`
--

LOCK TABLES `messages_notifications` WRITE;
/*!40000 ALTER TABLE `messages_notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `event_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `notifications_user_id_idx` (`user_id`),
  CONSTRAINT `notifications_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,1,'checked','Stroll',4,2),(2,1,'checked','Friend',0,2),(3,10,'notChecked','Friend',0,2),(4,9,'notChecked','Stroll',5,2),(5,1,'checked','Stroll',6,2),(6,10,'notChecked','Friend',0,8),(7,10,'notChecked','Stroll',7,8);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participants`
--

DROP TABLE IF EXISTS `participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participants` (
  `user_id` int(11) NOT NULL,
  `stroll_id` int(11) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  KEY `participants_user_id_idx` (`user_id`),
  KEY `participants_stroll_id_idx` (`stroll_id`),
  CONSTRAINT `participants_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participants`
--

LOCK TABLES `participants` WRITE;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
INSERT INTO `participants` VALUES (5,5,NULL,NULL),(1,1,NULL,NULL),(1,1,NULL,NULL),(1,22,NULL,NULL),(3,22,NULL,NULL),(1,27,NULL,NULL),(3,27,NULL,NULL),(1,27,NULL,NULL),(1,27,NULL,NULL),(1,28,NULL,NULL),(3,28,NULL,NULL),(1,36,NULL,NULL),(2,36,NULL,NULL),(1,37,NULL,NULL),(3,37,NULL,NULL),(1,38,NULL,NULL),(3,38,NULL,NULL),(1,39,NULL,NULL),(2,39,NULL,NULL),(1,40,NULL,NULL),(2,40,NULL,NULL),(2,41,NULL,NULL),(1,41,NULL,NULL),(2,42,NULL,NULL),(1,42,NULL,NULL),(2,43,NULL,NULL),(1,43,NULL,NULL),(1,44,NULL,NULL),(2,44,NULL,NULL);
/*!40000 ALTER TABLE `participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photos`
--

DROP TABLE IF EXISTS `photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photos` (
  `photo_id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_url` LONGBLOB NOT NULL,
  `took_time` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`photo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photos`
--

LOCK TABLES `photos` WRITE;
/*!40000 ALTER TABLE `photos` DISABLE KEYS */;
INSERT INTO `photos` VALUES (1,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:10'),(2,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:14'),(3,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:15'),(4,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:16'),(5,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:17'),(6,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:17'),(7,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:18'),(8,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:19'),(9,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:19'),(10,'http://static4.businessinsider.com/image/56c640526e97c625048b822a-480/donald-trump.jpg','2017-05-23 08:31:25');
/*!40000 ALTER TABLE `photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stroll`
--

DROP TABLE IF EXISTS `stroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stroll` (
  `stroll_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_id` int(11) NOT NULL,
  `info` varchar(500) DEFAULT NULL,
  `data_start` datetime DEFAULT NULL,
  `data_end` datetime DEFAULT NULL,
  `status` varchar(5) NOT NULL,
  `privacy` varchar(3) NOT NULL,
  PRIMARY KEY (`stroll_id`),
  KEY `stroll_location_id_idx` (`location_id`),
  CONSTRAINT `stroll_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stroll`
--

LOCK TABLES `stroll` WRITE;
/*!40000 ALTER TABLE `stroll` DISABLE KEYS */;
INSERT INTO `stroll` VALUES (36,11,'info','2017-06-20 12:27:23','2017-06-20 12:27:25','activ','All'),(37,1,'info','2017-06-20 12:27:27','2017-06-20 12:27:29','activ','All'),(38,2,'jakies info na temat spaceru','2017-06-20 13:54:54','2017-06-20 13:54:57','activ','All'),(39,3,'jakies info na temat spaceru','2017-06-20 13:54:56','2017-06-20 13:54:58','activ','All'),(40,4,'jakies info na temat spaceru','2017-06-20 13:54:57','2017-06-20 13:54:59','activ','All'),(41,19,'jakies info na temat spaceru','2017-06-20 10:31:00','2017-06-20 10:31:00','activ','All'),(42,5,'jakies info na temat spaceru','2017-06-20 10:31:00','2017-06-20 10:31:00','activ','All'),(43,22,'jakies info na temat spaceru','2017-06-01 00:00:00','2017-06-01 11:37:00','activ','All'),(44,4,NULL,NULL,NULL,'jakis','All');
/*!40000 ALTER TABLE `stroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strolls_notifications`
--

DROP TABLE IF EXISTS `strolls_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strolls_notifications` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `stroll_id` int(11) NOT NULL,
  `type` varchar(3) NOT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `stroll_id_idx` (`stroll_id`),
  CONSTRAINT `notification_id` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`notification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stroll_id` FOREIGN KEY (`stroll_id`) REFERENCES `stroll` (`stroll_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strolls_notifications`
--

LOCK TABLES `strolls_notifications` WRITE;
/*!40000 ALTER TABLE `strolls_notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `strolls_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `nick` varchar(15) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'11','111111','11@11.11'),(2,'111','111111','11@11.11'),(3,'111111','111111','111111@11111.11'),(4,'1111111','111111','111111@11111.11'),(5,'1','111111','11@11.11'),(6,'1234','111111','1234@1.1'),(7,'Rafal','111111','11@11.11'),(8,'Marek','111111','11@11.11'),(9,'Kacper','111111','11@11.11'),(10,'Dawid','111111','11@11.11');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_data` (
  `user_id` int(11) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(40) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_data_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
INSERT INTO `user_data` VALUES (1,'1','1','11','1989-05-11'),(2,'2','2','22','2017-05-01'),(3,'3','3','33','2017-05-11'),(4,'4','4','33','2017-05-17'),(5,'5','5','33','1973-05-09'),(6,'6','6','33','2017-05-10'),(7,'Rafal','Grzelec','Gliwice','2017-05-04'),(8,'Kacper','Kowalik','Szczekocin','1986-05-22'),(9,'Marek','Makowski','Katowice','2011-05-12'),(10,'Dawid','Kubów','Pscim Dolny','2009-05-14');
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `user_id` int(11) NOT NULL,
  `photo_id` int(11) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `preferences` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `photo_id_idx` (`photo_id`),
  KEY `user_profile_location_id_idx` (`location_id`),
  CONSTRAINT `user_profile_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `user_profile_photo_id` FOREIGN KEY (`photo_id`) REFERENCES `photos` (`photo_id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `user_profile_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,1,'1',1,1),(2,2,'2',2,2),(3,3,'3',3,3),(4,4,'4',4,4),(5,5,'5',5,5),(6,6,'6',6,6),(7,7,'Jakis madry opis opisujacy mnie lepiej niz innych',7,7),(8,8,'Jakis madry opis opisujacy mnie lepiej niz innych',8,8),(9,9,'Jakis madry opis opisujacy mnie lepiej niz innych',9,9),(10,10,'Jakis madry opis opisujacy mnie lepiej niz innych',10,10);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-25 21:56:39
