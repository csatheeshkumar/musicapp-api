-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: musicapp
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `album_info`
--

DROP TABLE IF EXISTS `album_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(255) NOT NULL,
  `year` int(11) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `poster_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album_info`
--

LOCK TABLES `album_info` WRITE;
/*!40000 ALTER TABLE `album_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `album_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist_info`
--

DROP TABLE IF EXISTS `artist_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(255) NOT NULL,
  `followers` int(11) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `profile_pic_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_info`
--

LOCK TABLES `artist_info` WRITE;
/*!40000 ALTER TABLE `artist_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `artist_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song_info`
--

DROP TABLE IF EXISTS `song_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `song_url` varchar(255) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song_info`
--

LOCK TABLES `song_info` WRITE;
/*!40000 ALTER TABLE `song_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `song_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song_info_mapping`
--

DROP TABLE IF EXISTS `song_info_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song_info_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ablum_id` int(11) NOT NULL,
  `artist_id` int(11) NOT NULL,
  `song_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ablum_id_fk1_idx` (`ablum_id`),
  KEY `artisti_id_fk2_idx` (`artist_id`),
  KEY `song_id_fk3_idx` (`song_id`),
  CONSTRAINT `ablum_id_fk1` FOREIGN KEY (`ablum_id`) REFERENCES `album_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `artisti_id_fk2` FOREIGN KEY (`artist_id`) REFERENCES `artist_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `song_id_fk3` FOREIGN KEY (`song_id`) REFERENCES `song_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song_info_mapping`
--

LOCK TABLES `song_info_mapping` WRITE;
/*!40000 ALTER TABLE `song_info_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `song_info_mapping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-14 12:44:07
