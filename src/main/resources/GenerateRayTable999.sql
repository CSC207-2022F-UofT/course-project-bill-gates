-- MySQL dump 10.13  Distrib 8.0.31, for macos12.6 (arm64)
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_999`
--

LOCK TABLES `bill_999` WRITE;
/*!40000 ALTER TABLE `bill_999` DISABLE KEYS */;
INSERT INTO `bill_999` VALUES (1,12.88,'2022-11-22 15:54:40','CAD','Starbucks w/ friends','Credit','Starbucks','College Street',-1),(2,8.24,'2022-11-22 13:04:52','CAD','Burger King - Whopper Meal','Credit','Burger King','College Spadina Intersection',-1),(3,7.73,'2022-11-22 08:22:23','CAD','McDonalds - McGirdles Offer','Credit','McDonalds','470 Yonge',-1),(4,14.80,'2022-11-20 16:37:55','CAD','KungFu Tea w/ friends','Credit','KungFu Tea','College Street',-1),(5,15.81,'2022-11-18 18:54:54','CAD','KFC Crazy Thursday 疯狂星期四','Credit','KFC','Yonge Metro 附近',-1),(6,14.58,'2022-11-17 21:49:14','CAD','Shoppers - Cookies and Chips','Credit','Shoppers','Yonge Carlton Shoppers',-1),(7,10.00,'2022-11-14 21:02:07','CAD','Charity Donation','Debit','Charity','Online',-1),(8,9.59,'2022-11-02 23:31:14','CAD','Robarts Library - Spaghetti','Credit','トロント大学','Robarts Library',-1),(9,1500.00,'2022-11-01 20:14:58','CAD','House Rent - Magical Place','Debit','Landlord','Online',-1),(10,23.79,'2022-11-03 19:00:25','CAD','The Burgernator - Fully Loaded Set ','Credit','Burgernator','Spadina-ish',-1);
/*!40000 ALTER TABLE `bill_999` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-24  1:02:39
