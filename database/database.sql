-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: bill-gates-database.mysql.database.azure.com    Database: bill
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill_0`
--

DROP TABLE IF EXISTS `bill_0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_0` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` varchar(5) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_0`
--

LOCK TABLES `bill_0` WRITE;
/*!40000 ALTER TABLE `bill_0` DISABLE KEYS */;
INSERT INTO `bill_0` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `split_bill_id`) VALUES (268,1.20,'2022-11-19 23:38:28','ALL','もしもし','A','B','Home',268),(283,0.12,'2022-11-19 23:38:28','CAD','Scawdiaw','A','B','Home',283),(284,0.12,'2022-11-19 23:38:28','CAD','awdkpoawkdp','A','B','Home',284),(285,0.12,'2022-11-19 23:38:28','CAD','awdioaior','A','B','Home',285),(290,0.12,'2022-11-19 23:38:28','CAD','eji','A','B','Home',290),(291,0.12,'2022-11-19 23:38:28','CAD','pejgp\'ejhrgip','A','B','Home',2),(292,0.12,'2022-11-19 23:38:28','CAD','[rgj[','A','B','Home',292),(293,0.12,'2022-11-19 23:38:28','CAD','erjg[','A','B','Home',2),(294,0.12,'2022-11-19 23:38:28','CAD','[jke[ri','A','B','Home',2),(295,0.12,'2022-11-19 23:38:28','CAD','gji','A','B','Home',2),(296,0.12,'2022-11-19 23:38:28','CAD','erijg','A','B','Home',2),(297,0.12,'2022-11-19 23:38:28','CAD','ejri[gp','A','B','Home',2),(298,0.12,'2022-11-19 23:38:28','CAD','jergjioerjgp','A','B','Home',2),(299,0.12,'2022-11-19 23:38:28','CAD','rjgp\njerp','A','B','Home',2),(300,0.12,'2022-11-19 23:38:28','CAD','gjep','A','B','Home',2),(301,0.12,'2022-11-19 23:38:28','CAD','rjgp','A','B','Home',2),(302,0.12,'2022-11-19 23:38:28','CAD','erjgi','A','B','Home',2),(303,0.12,'2022-11-19 23:38:28','CAD','perjpg','A','B','Home',2),(304,0.12,'2022-11-19 23:38:28','CAD','\'wjerpig\'','A','B','Home',2),(305,0.12,'2022-11-19 23:38:28','CAD','ewjg','A','B','Home',2),(306,0.12,'2022-11-19 23:38:28','CAD','werjg','A','B','Home',2),(307,0.12,'2022-11-19 23:38:28','CAD','gire','A','B','Home',2),(308,0.12,'2022-11-19 23:38:28','CAD','pgier','A','B','Home',2),(309,0.12,'2022-11-19 23:38:28','CAD','gjpirejg','A','B','Home',2),(310,0.12,'2022-11-19 23:38:28','CAD','reigjpe','A','B','Home',2),(311,0.12,'2022-11-19 23:38:28','CAD','r\"gjier','A','B','Home',2),(312,0.12,'2022-11-19 23:38:28','CAD','ierjgp','A','B','Home',2),(313,0.12,'2022-11-19 23:38:28','CAD','ergi','A','B','Home',2),(314,0.12,'2022-11-19 23:38:28','CAD','rejig','A','B','Home',2),(315,0.12,'2022-11-19 23:38:28','CAD','jp','A','B','Home',2),(316,0.12,'2022-11-19 23:38:28','CAD',' ijfwejfiwejfw而房价\n wejfip wjfi我iefwp we ','A','B','Home',2),(317,0.12,'2022-11-19 23:38:28','CAD','f怕wejf陪我机票为j排位j排位','A','B','Home',2),(318,0.12,'2022-11-19 23:38:28','CAD',' ','A','B','Home',2),(319,0.12,'2022-11-19 23:38:28','CAD',' 为【','A','B','Home',2),(320,0.12,'2022-11-19 23:38:28','CAD','ewjf皮we发w二篇j【陪我发\n脾胃\n排位','A','B','Home',2),(321,0.12,'2022-11-19 23:38:28','CAD',' ','A','B','Home',2),(322,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(323,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(324,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(325,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(326,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(327,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(328,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(329,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(330,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(331,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(332,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(333,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(334,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(335,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(336,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(337,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(338,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(339,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(340,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(341,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(342,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(343,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(344,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(345,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(346,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(347,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(348,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(349,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(350,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(351,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(352,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(353,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(354,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(355,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(356,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(357,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(358,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(359,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(364,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(365,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(366,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(367,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(368,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2),(373,0.12,'2022-11-19 23:38:28','CAD','None','A','B','Home',2);
/*!40000 ALTER TABLE `bill_0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_0_268`
--

DROP TABLE IF EXISTS `bill_0_268`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_0_268` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_0_268`
--

LOCK TABLES `bill_0_268` WRITE;
/*!40000 ALTER TABLE `bill_0_268` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_0_268` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_0_283`
--

DROP TABLE IF EXISTS `bill_0_283`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_0_283` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_0_283`
--

LOCK TABLES `bill_0_283` WRITE;
/*!40000 ALTER TABLE `bill_0_283` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_0_283` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_0_284`
--

DROP TABLE IF EXISTS `bill_0_284`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_0_284` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_0_284`
--

LOCK TABLES `bill_0_284` WRITE;
/*!40000 ALTER TABLE `bill_0_284` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_0_284` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_0_285`
--

DROP TABLE IF EXISTS `bill_0_285`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_0_285` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_0_285`
--

LOCK TABLES `bill_0_285` WRITE;
/*!40000 ALTER TABLE `bill_0_285` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_0_285` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_0_290`
--

DROP TABLE IF EXISTS `bill_0_290`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_0_290` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_0_290`
--

LOCK TABLES `bill_0_290` WRITE;
/*!40000 ALTER TABLE `bill_0_290` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_0_290` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_0_292`
--

DROP TABLE IF EXISTS `bill_0_292`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_0_292` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_0_292`
--

LOCK TABLES `bill_0_292` WRITE;
/*!40000 ALTER TABLE `bill_0_292` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_0_292` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011`
--

DROP TABLE IF EXISTS `bill_10011`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011`
--

LOCK TABLES `bill_10011` WRITE;
/*!40000 ALTER TABLE `bill_10011` DISABLE KEYS */;
INSERT INTO `bill_10011` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `split_bill_id`) VALUES (1,9999999999999.99,'2023-01-20 01:00:00','AMD','buy the Earth','Rayyyyyy','Godddddd','The Center of the Universe',1),(7,123.00,'2022-12-09 01:00:00','BTN','awdaewd','aefae','faefae','aef',7),(8,12345.60,'2022-12-01 01:00:00','CAD','Test Description','awde','efaef','faefaefaef',8),(9,123.35,'2022-12-03 02:00:00','CAD','ajdaoijoiejfiojae','aefaefa','aefaef','aefaef',9),(10,4235.00,'2022-12-14 01:00:00','CAD','Test','Scott ','Ray','VOID',10),(13,0.00,'2022-11-19 23:38:28','USD','','','','',13),(14,0.00,'2022-12-05 18:50:30','CAD','','scott','','',14),(15,0.00,'2022-12-05 18:53:10','AMD','','','','',15),(16,2132.00,'2022-12-05 23:58:07','AED','2 Dollars','Scott','Ray','UofT',16),(17,0.00,'2022-12-14 00:01:24','CNY','','','','',17),(19,0.00,'2022-12-06 01:31:46','ZAR','','','','',19),(20,0.00,'2022-12-06 01:31:48','AMD','','','','',-1),(21,0.00,'2022-12-06 12:40:22','CAD','','','','',-1),(22,0.00,'2022-12-06 12:40:23','CAD','','','','',-1),(23,0.00,'2022-12-06 02:30:00','CAD','','','','',-1),(24,0.00,'2022-12-06 12:40:25','CAD','','','','',-1),(25,0.00,'2022-12-07 12:40:26','CAD','','','','',25),(26,0.00,'2022-12-08 15:41:24','CAD','juiu','','','',26);
/*!40000 ALTER TABLE `bill_10011` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_1`
--

DROP TABLE IF EXISTS `bill_10011_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_1` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_1`
--

LOCK TABLES `bill_10011_1` WRITE;
/*!40000 ALTER TABLE `bill_10011_1` DISABLE KEYS */;
INSERT INTO `bill_10011_1` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `payee`, `paid_back`) VALUES (2,124.00,'2022-12-04 10:49:39','AFN','DREAM','A','B','HOME','Scott',1),(3,213.40,'2022-12-05 21:30:00','AED','Test Description','DREAM LAND','WUHUUUUU','JFAOIEFJO','RAy',1),(5,0.00,'2022-12-01 00:28:03','CNY','','','','','Charlotte',1);
/*!40000 ALTER TABLE `bill_10011_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_10`
--

DROP TABLE IF EXISTS `bill_10011_10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_10` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_10`
--

LOCK TABLES `bill_10011_10` WRITE;
/*!40000 ALTER TABLE `bill_10011_10` DISABLE KEYS */;
INSERT INTO `bill_10011_10` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `payee`, `paid_back`) VALUES (1,0.00,'2022-12-06 12:41:56','CAD','','','','','JAOIDjwioj',0);
/*!40000 ALTER TABLE `bill_10011_10` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_13`
--

DROP TABLE IF EXISTS `bill_10011_13`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_13` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_13`
--

LOCK TABLES `bill_10011_13` WRITE;
/*!40000 ALTER TABLE `bill_10011_13` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_13` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_14`
--

DROP TABLE IF EXISTS `bill_10011_14`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_14` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_14`
--

LOCK TABLES `bill_10011_14` WRITE;
/*!40000 ALTER TABLE `bill_10011_14` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_14` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_15`
--

DROP TABLE IF EXISTS `bill_10011_15`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_15` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_15`
--

LOCK TABLES `bill_10011_15` WRITE;
/*!40000 ALTER TABLE `bill_10011_15` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_15` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_16`
--

DROP TABLE IF EXISTS `bill_10011_16`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_16` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_16`
--

LOCK TABLES `bill_10011_16` WRITE;
/*!40000 ALTER TABLE `bill_10011_16` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_16` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_17`
--

DROP TABLE IF EXISTS `bill_10011_17`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_17` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_17`
--

LOCK TABLES `bill_10011_17` WRITE;
/*!40000 ALTER TABLE `bill_10011_17` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_17` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_19`
--

DROP TABLE IF EXISTS `bill_10011_19`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_19` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_19`
--

LOCK TABLES `bill_10011_19` WRITE;
/*!40000 ALTER TABLE `bill_10011_19` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_19` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_2`
--

DROP TABLE IF EXISTS `bill_10011_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_2` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_2`
--

LOCK TABLES `bill_10011_2` WRITE;
/*!40000 ALTER TABLE `bill_10011_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_25`
--

DROP TABLE IF EXISTS `bill_10011_25`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_25` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_25`
--

LOCK TABLES `bill_10011_25` WRITE;
/*!40000 ALTER TABLE `bill_10011_25` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_25` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_26`
--

DROP TABLE IF EXISTS `bill_10011_26`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_26` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_26`
--

LOCK TABLES `bill_10011_26` WRITE;
/*!40000 ALTER TABLE `bill_10011_26` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_26` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_4`
--

DROP TABLE IF EXISTS `bill_10011_4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_4` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_4`
--

LOCK TABLES `bill_10011_4` WRITE;
/*!40000 ALTER TABLE `bill_10011_4` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_5`
--

DROP TABLE IF EXISTS `bill_10011_5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_5` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_5`
--

LOCK TABLES `bill_10011_5` WRITE;
/*!40000 ALTER TABLE `bill_10011_5` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_6`
--

DROP TABLE IF EXISTS `bill_10011_6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_6` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_6`
--

LOCK TABLES `bill_10011_6` WRITE;
/*!40000 ALTER TABLE `bill_10011_6` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_7`
--

DROP TABLE IF EXISTS `bill_10011_7`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_7` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_7`
--

LOCK TABLES `bill_10011_7` WRITE;
/*!40000 ALTER TABLE `bill_10011_7` DISABLE KEYS */;
INSERT INTO `bill_10011_7` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `payee`, `paid_back`) VALUES (1,0.00,'2022-12-06 12:41:23','CAD','','','','','gyuigfuyftuo',1);
/*!40000 ALTER TABLE `bill_10011_7` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_8`
--

DROP TABLE IF EXISTS `bill_10011_8`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_8` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_8`
--

LOCK TABLES `bill_10011_8` WRITE;
/*!40000 ALTER TABLE `bill_10011_8` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_8` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10011_9`
--

DROP TABLE IF EXISTS `bill_10011_9`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10011_9` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10011_9`
--

LOCK TABLES `bill_10011_9` WRITE;
/*!40000 ALTER TABLE `bill_10011_9` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10011_9` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10012`
--

DROP TABLE IF EXISTS `bill_10012`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10012` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10012`
--

LOCK TABLES `bill_10012` WRITE;
/*!40000 ALTER TABLE `bill_10012` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10012` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10012_2`
--

DROP TABLE IF EXISTS `bill_10012_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10012_2` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10012_2`
--

LOCK TABLES `bill_10012_2` WRITE;
/*!40000 ALTER TABLE `bill_10012_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10012_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10012_5`
--

DROP TABLE IF EXISTS `bill_10012_5`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10012_5` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10012_5`
--

LOCK TABLES `bill_10012_5` WRITE;
/*!40000 ALTER TABLE `bill_10012_5` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10012_5` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10012_6`
--

DROP TABLE IF EXISTS `bill_10012_6`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10012_6` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10012_6`
--

LOCK TABLES `bill_10012_6` WRITE;
/*!40000 ALTER TABLE `bill_10012_6` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10012_6` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10012_7`
--

DROP TABLE IF EXISTS `bill_10012_7`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10012_7` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10012_7`
--

LOCK TABLES `bill_10012_7` WRITE;
/*!40000 ALTER TABLE `bill_10012_7` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10012_7` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10013`
--

DROP TABLE IF EXISTS `bill_10013`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10013` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10013`
--

LOCK TABLES `bill_10013` WRITE;
/*!40000 ALTER TABLE `bill_10013` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10013` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10014`
--

DROP TABLE IF EXISTS `bill_10014`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10014` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10014`
--

LOCK TABLES `bill_10014` WRITE;
/*!40000 ALTER TABLE `bill_10014` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10014` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10015`
--

DROP TABLE IF EXISTS `bill_10015`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10015` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10015`
--

LOCK TABLES `bill_10015` WRITE;
/*!40000 ALTER TABLE `bill_10015` DISABLE KEYS */;
INSERT INTO `bill_10015` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `split_bill_id`) VALUES (1,999.00,'2022-12-07 22:17:27','CAD','','','','',-1),(2,200.00,'2022-11-09 00:00:00','CAD','','Eva','Restaurant','Toronto',-1),(3,100.00,'2022-11-10 00:00:00','CNY','','Eva','','California',-1),(4,10000.00,'2022-11-11 00:00:00','USD','','Eva','','California',-1),(5,1.00,'2022-11-12 00:00:00','ANG','','Eva','','California',-1),(6,10.00,'2022-11-13 00:00:00','AMD','','Eva','','California',-1),(7,20.00,'2022-11-14 00:00:00','EUR','','Eva','','California',-1),(8,0.00,'2022-11-15 00:00:00','CAD','','Eva','','Toronto',-1),(9,0.00,'2022-11-16 00:00:00','CAD','','Eva','','Toronto',-1),(10,0.00,'2022-11-17 00:00:00','CAD','','Eva','','Toronto',-1),(11,3.00,'2022-11-18 00:00:00','CAD','','Eva','','Toronto',-1),(12,4.00,'2022-11-19 00:00:00','CAD','','Eva','Shopping Mall','Toronto',-1),(13,1.00,'2022-11-20 00:00:00','CAD','','Eva','','Toronto',-1),(14,12.00,'2022-11-21 00:00:00','CAD','','Eva','','Vancouver',-1),(15,34.33,'2022-11-22 00:00:00','CAD','','Eva','','Vancouver',-1),(16,35.70,'2022-11-23 00:00:00','CAD','','Eva','Shopping Mall','Vancouver',-1),(17,0.90,'2022-11-24 00:00:00','CAD','','Eva','','Vancouver',-1),(18,222.00,'2022-11-25 00:00:00','USD','','Eva','','Vancouver',-1),(19,5555.00,'2022-11-26 00:00:00','USD','','Eva','','Vancouver',-1),(20,1111111.00,'2022-11-27 00:00:00','USD','','Eva','Bar','California',-1),(21,8.00,'2022-11-28 00:00:00','USD','','Eva','','California',-1),(22,9999.00,'2022-11-29 00:00:00','USD','','Eva','','California',-1),(23,0.00,'2022-11-30 00:00:00','USD','','Eva','Hotel','California',-1);
/*!40000 ALTER TABLE `bill_10015` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10016`
--

DROP TABLE IF EXISTS `bill_10016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10016` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10016`
--

LOCK TABLES `bill_10016` WRITE;
/*!40000 ALTER TABLE `bill_10016` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10017`
--

DROP TABLE IF EXISTS `bill_10017`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10017` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10017`
--

LOCK TABLES `bill_10017` WRITE;
/*!40000 ALTER TABLE `bill_10017` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10017` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10018`
--

DROP TABLE IF EXISTS `bill_10018`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10018` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10018`
--

LOCK TABLES `bill_10018` WRITE;
/*!40000 ALTER TABLE `bill_10018` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10018` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10019`
--

DROP TABLE IF EXISTS `bill_10019`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10019` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10019`
--

LOCK TABLES `bill_10019` WRITE;
/*!40000 ALTER TABLE `bill_10019` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10019` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10020`
--

DROP TABLE IF EXISTS `bill_10020`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10020` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10020`
--

LOCK TABLES `bill_10020` WRITE;
/*!40000 ALTER TABLE `bill_10020` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10020` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10021`
--

DROP TABLE IF EXISTS `bill_10021`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10021` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10021`
--

LOCK TABLES `bill_10021` WRITE;
/*!40000 ALTER TABLE `bill_10021` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10021` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10022`
--

DROP TABLE IF EXISTS `bill_10022`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10022` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10022`
--

LOCK TABLES `bill_10022` WRITE;
/*!40000 ALTER TABLE `bill_10022` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10022` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10023`
--

DROP TABLE IF EXISTS `bill_10023`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10023` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10023`
--

LOCK TABLES `bill_10023` WRITE;
/*!40000 ALTER TABLE `bill_10023` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10023` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10024`
--

DROP TABLE IF EXISTS `bill_10024`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10024` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10024`
--

LOCK TABLES `bill_10024` WRITE;
/*!40000 ALTER TABLE `bill_10024` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10024` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10026`
--

DROP TABLE IF EXISTS `bill_10026`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10026` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10026`
--

LOCK TABLES `bill_10026` WRITE;
/*!40000 ALTER TABLE `bill_10026` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10026` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10028`
--

DROP TABLE IF EXISTS `bill_10028`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10028` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10028`
--

LOCK TABLES `bill_10028` WRITE;
/*!40000 ALTER TABLE `bill_10028` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10028` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10029`
--

DROP TABLE IF EXISTS `bill_10029`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10029` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10029`
--

LOCK TABLES `bill_10029` WRITE;
/*!40000 ALTER TABLE `bill_10029` DISABLE KEYS */;
INSERT INTO `bill_10029` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `split_bill_id`) VALUES (4,10000.00,'2022-12-05 01:36:09','CAD','dnlkedf','edfnjkwerf','efwjlw','fk;wrepf',4);
/*!40000 ALTER TABLE `bill_10029` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10029_4`
--

DROP TABLE IF EXISTS `bill_10029_4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10029_4` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10029_4`
--

LOCK TABLES `bill_10029_4` WRITE;
/*!40000 ALTER TABLE `bill_10029_4` DISABLE KEYS */;
INSERT INTO `bill_10029_4` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `payee`, `paid_back`) VALUES (1,0.00,'2022-12-05 01:36:30','CAD','fn,ewrf','mewlfn','frwml','ferwkf','frw;kf',1);
/*!40000 ALTER TABLE `bill_10029_4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_10030`
--

DROP TABLE IF EXISTS `bill_10030`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_10030` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_10030`
--

LOCK TABLES `bill_10030` WRITE;
/*!40000 ALTER TABLE `bill_10030` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_10030` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777808`
--

DROP TABLE IF EXISTS `bill_7777808`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777808` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777808`
--

LOCK TABLES `bill_7777808` WRITE;
/*!40000 ALTER TABLE `bill_7777808` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_7777808` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777809`
--

DROP TABLE IF EXISTS `bill_7777809`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777809` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777809`
--

LOCK TABLES `bill_7777809` WRITE;
/*!40000 ALTER TABLE `bill_7777809` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_7777809` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777810`
--

DROP TABLE IF EXISTS `bill_7777810`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777810` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777810`
--

LOCK TABLES `bill_7777810` WRITE;
/*!40000 ALTER TABLE `bill_7777810` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_7777810` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777811`
--

DROP TABLE IF EXISTS `bill_7777811`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777811` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777811`
--

LOCK TABLES `bill_7777811` WRITE;
/*!40000 ALTER TABLE `bill_7777811` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_7777811` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777812`
--

DROP TABLE IF EXISTS `bill_7777812`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777812` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777812`
--

LOCK TABLES `bill_7777812` WRITE;
/*!40000 ALTER TABLE `bill_7777812` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_7777812` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777812_1`
--

DROP TABLE IF EXISTS `bill_7777812_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777812_1` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777812_1`
--

LOCK TABLES `bill_7777812_1` WRITE;
/*!40000 ALTER TABLE `bill_7777812_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_7777812_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777813`
--

DROP TABLE IF EXISTS `bill_7777813`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777813` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777813`
--

LOCK TABLES `bill_7777813` WRITE;
/*!40000 ALTER TABLE `bill_7777813` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_7777813` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777816`
--

DROP TABLE IF EXISTS `bill_7777816`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777816` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777816`
--

LOCK TABLES `bill_7777816` WRITE;
/*!40000 ALTER TABLE `bill_7777816` DISABLE KEYS */;
INSERT INTO `bill_7777816` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `split_bill_id`) VALUES (1,15.00,'2022-12-09 23:06:24','CAD','new albums','me','the store','toronto',-1);
/*!40000 ALTER TABLE `bill_7777816` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_7777817`
--

DROP TABLE IF EXISTS `bill_7777817`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_7777817` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_7777817`
--

LOCK TABLES `bill_7777817` WRITE;
/*!40000 ALTER TABLE `bill_7777817` DISABLE KEYS */;
INSERT INTO `bill_7777817` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `split_bill_id`) VALUES (2,50.02,'2022-12-09 23:15:12','CAD','hello','','','',-1),(3,99.00,'2022-12-09 23:20:49','CAD','','','','',-1);
/*!40000 ALTER TABLE `bill_7777817` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_999`
--

DROP TABLE IF EXISTS `bill_999`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_999` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` varchar(5) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `split_bill_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999`
--

LOCK TABLES `bill_999` WRITE;
/*!40000 ALTER TABLE `bill_999` DISABLE KEYS */;
INSERT INTO `bill_999` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `split_bill_id`) VALUES (1,12.88,'2022-11-22 15:54:40','CAD','星BUG w/ friends','Credit','Starbucks','College Street',1),(2,8.24,'2022-11-22 13:04:52','AED','BUG王者 - Whopper Meal','Credit','Burger King','College Spadina Intersection',2),(3,7.73,'2022-11-22 08:22:23','CAD','McDonalds - McGirdles Offer','Credit','McDonalds','470 Yonge',-1),(4,19301.00,'2022-11-20 16:37:55','CAD','KungFu Tea w/ friends','Credit','KungFu Tea','College Street',-1),(5,1.20,'2022-11-18 18:54:54','CAD','KFC Crazy Friday 疯狂星期五','Credit','KFC','Yonge Metro 附近',-1),(6,14.58,'2022-11-17 21:49:14','CNY','Shoppers - Cookies and Chips','Credit','Shoppers','Yonge Carlton Shoppers',-1),(7,10.00,'2022-11-14 21:02:07','CAD','Charity Donation','Debit','Charity','Online',-1),(8,9.59,'2022-11-02 23:31:14','CAD','Robarts Library - Spaghetti','Credit','トロント大学','Robarts Library',-1),(9,1500.00,'2022-11-01 20:14:58','CAD','House Rent - Magical Place','Debit','Landlord','Online',-1),(10,23.79,'2022-11-03 19:00:25','CAD','The Burgernator - Fully Loaded Set ','Credit','Burgernator','Spadina-ish',-1),(16,6666666666.00,'2022-12-08 18:22:40','CAD','CSC207さいこう','Team Bill Gates','CSC207 教职团队','Ray\'s HOME',16),(18,0.00,'2022-12-08 20:00:05','CAD','','','','',-1);
/*!40000 ALTER TABLE `bill_999` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_999_1`
--

DROP TABLE IF EXISTS `bill_999_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_999_1` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999_1`
--

LOCK TABLES `bill_999_1` WRITE;
/*!40000 ALTER TABLE `bill_999_1` DISABLE KEYS */;
INSERT INTO `bill_999_1` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `payee`, `paid_back`) VALUES (1,66.00,'2022-12-07 16:25:18','ALL','','credit card','test','UofT','Charlotte',1);
/*!40000 ALTER TABLE `bill_999_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_999_13`
--

DROP TABLE IF EXISTS `bill_999_13`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_999_13` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999_13`
--

LOCK TABLES `bill_999_13` WRITE;
/*!40000 ALTER TABLE `bill_999_13` DISABLE KEYS */;
INSERT INTO `bill_999_13` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `payee`, `paid_back`) VALUES (1,8.00,'2022-12-07 19:55:03','CAD','BUGKING','Charlotte','BUGKING','DREAM','Scott',1);
/*!40000 ALTER TABLE `bill_999_13` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_999_14`
--

DROP TABLE IF EXISTS `bill_999_14`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_999_14` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999_14`
--

LOCK TABLES `bill_999_14` WRITE;
/*!40000 ALTER TABLE `bill_999_14` DISABLE KEYS */;
INSERT INTO `bill_999_14` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `payee`, `paid_back`) VALUES (1,37290370925.00,'2022-12-07 17:42:54','CAD','aefaefea','faefa','efargaargsrgfgewhtewthewth','aef','SCOTOTOTOTOTOTOT',1);
/*!40000 ALTER TABLE `bill_999_14` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_999_15`
--

DROP TABLE IF EXISTS `bill_999_15`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_999_15` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999_15`
--

LOCK TABLES `bill_999_15` WRITE;
/*!40000 ALTER TABLE `bill_999_15` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_999_15` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_999_16`
--

DROP TABLE IF EXISTS `bill_999_16`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_999_16` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999_16`
--

LOCK TABLES `bill_999_16` WRITE;
/*!40000 ALTER TABLE `bill_999_16` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_999_16` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_999_17`
--

DROP TABLE IF EXISTS `bill_999_17`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_999_17` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999_17`
--

LOCK TABLES `bill_999_17` WRITE;
/*!40000 ALTER TABLE `bill_999_17` DISABLE KEYS */;
INSERT INTO `bill_999_17` (`entry_id`, `value`, `date`, `currency`, `description`, `from`, `to`, `location`, `payee`, `paid_back`) VALUES (1,12.00,'2022-12-08 19:37:15','CAD','oaiejoiaefoij','ME','BugerKing','aeaef','Scott',1);
/*!40000 ALTER TABLE `bill_999_17` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_999_2`
--

DROP TABLE IF EXISTS `bill_999_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_999_2` (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(16,2) NOT NULL,
  `date` timestamp NOT NULL,
  `currency` char(3) NOT NULL,
  `description` text NOT NULL,
  `from` text NOT NULL,
  `to` text NOT NULL,
  `location` text NOT NULL,
  `payee` text NOT NULL,
  `paid_back` tinyint(1) NOT NULL,
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999_2`
--

LOCK TABLES `bill_999_2` WRITE;
/*!40000 ALTER TABLE `bill_999_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_999_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL,
  `bill_id` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7777818 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `username`, `password`, `bill_id`) VALUES (0,'Scott','12345678',0),(999,'test','test',999),(10011,'a','a',10011),(10012,'Ruby','123456',10012),(10013,'ff','f',10013),(10014,'aa','a',10014),(10015,'123','123456789',10015),(10016,'iuhhu','hihiu',10016),(10017,'dgdgf','dxfdv',10017),(10018,'fzsx','xdgvxgv',10018),(10019,'S','123',10019),(10020,'1234','4566',10020),(10021,'12','12',10021),(10022,'hfdhjsdh','sdsfd',10022),(10023,'ee','dd',10023),(10024,'dd','sdfas',10024),(10026,'gfcg','kjnjk',10026),(10028,'Ray','123',10028),(10029,'111','111',10029),(10030,'aaa','aaa',10030),(7777808,'e11','111',7777808),(7777809,'xinxaing','jaiodwjio',7777809),(7777810,'啥','6',7777810),(7777811,'gaoxinxi','666',7777811),(7777812,'1007','616',7777812),(7777813,'ajoiedjio','aefaf',7777813),(7777816,'inorr','123456',7777816),(7777817,'inorrr','123456',7777817);
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

-- Dump completed on 2022-12-13 23:16:55
