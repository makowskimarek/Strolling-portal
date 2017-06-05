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
SET GLOBAL time_zone = '+1:00';

--
-- Table structure for table `ad`
--

DROP TABLE IF EXISTS `ad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ad` (
  `ad_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `stroll_starttime` datetime NOT NULL,
  `ad_endtime` datetime NOT NULL,
  `privacy` varchar(5) NOT NULL,
  PRIMARY KEY (`ad_id`),
  KEY `ad_user_id_idx` (`user_id`),
  KEY `ad_location_id_idx` (`location_id`),
  CONSTRAINT `ad_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ad_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ad`
--

LOCK TABLES `ad` WRITE;
/*!40000 ALTER TABLE `ad` DISABLE KEYS */;
/*!40000 ALTER TABLE `ad` ENABLE KEYS */;
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
  CONSTRAINT `banned_users_banned_user_id` FOREIGN KEY (`banned_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `banned_users_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  CONSTRAINT `user1_id` FOREIGN KEY (`user1_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user2_id` FOREIGN KEY (`user2_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `location_id` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longtitude` double NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,45,5,NULL),(2,24,-31,NULL),(3,234,0,NULL),(4,0,46,NULL),(5,34,24,NULL),(6,34,0,NULL),(7,0,52,NULL),(8,15,0,NULL),(9,0,15,NULL),(10,1,1,NULL);
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
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `status` varchar(3) NOT NULL,
  `msg_time` datetime NOT NULL,
  PRIMARY KEY (`msg_id`),
  KEY `messages_sender_id_idx` (`sender_id`),
  KEY `messages_receiver_id_idx` (`receiver_id`),
  CONSTRAINT `messages_receiver_id` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `messages_sender_id` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
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
  `status` varchar(3) NOT NULL,
  `type` varchar(3) NOT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `notifications_user_id_idx` (`user_id`),
  CONSTRAINT `notifications_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
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
  CONSTRAINT `participants_stroll_id` FOREIGN KEY (`stroll_id`) REFERENCES `stroll` (`stroll_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `participants_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participants`
--

LOCK TABLES `participants` WRITE;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
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
  `photo_url` varchar(100) NOT NULL,
  `took_time` datetime NOT NULL,
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
  `ad_id` int(11) DEFAULT NULL,
  `privacy` varchar(3) NOT NULL,
  PRIMARY KEY (`stroll_id`),
  KEY `stroll_location_id_idx` (`location_id`),
  CONSTRAINT `stroll_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stroll`
--

LOCK TABLES `stroll` WRITE;
/*!40000 ALTER TABLE `stroll` DISABLE KEYS */;
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
INSERT INTO `user` VALUES (1,'11','111111','11@11.11'),(2,'111','111111','11@11.11'),(3,'111111','111111','111111@11111.11'),(4,'1111111','111111','111111@11111.11'),(5,'1','111111','11@11.11'),(6,'1234','111111','1234@1.1'),(7,'2','111111','11@11.11'),(8,'4','111111','11@11.11'),(9,'5','111111','11@11.11'),(10,'6','111111','11@11.11');
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
  CONSTRAINT `user_data_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
INSERT INTO `user_data` VALUES (1,'1','1','11','1989-05-11'),(2,'2','2','22','2017-05-01'),(3,'3','3','33','2017-05-11'),(4,'4','4','33','2017-05-17'),(5,'5','5','33','1973-05-09'),(6,'6','6','33','2017-05-10'),(7,'7','7','33','2017-05-04'),(8,'8','8','33','1986-05-22'),(9,'9','9','33','2011-05-12'),(10,'10','10','33','2009-05-14');
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
  CONSTRAINT `user_profile_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_profile_photo_id` FOREIGN KEY (`photo_id`) REFERENCES `photos` (`photo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_profile_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,1,'1',1,1),(2,2,'2',2,2),(3,3,'3',3,3),(4,4,'4',4,4),(5,5,'5',5,5),(6,6,'6',6,6),(7,7,'7',7,7),(8,8,'8',8,8),(9,9,'9',9,9),(10,10,'10',10,10);
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

-- Dump completed on 2017-06-05 23:44:21
