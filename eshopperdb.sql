-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: eshopper
-- ------------------------------------------------------
-- Server version	5.7.39-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `cart_item_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (4,23,1,6,1),(5,25,1,6,1),(6,19,1,3,NULL),(7,42,1,3,NULL);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adress` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'1223 trần hưng đạo','hà nội','john','doe','2022-10-02 18:15:51.506000','1233213211',1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender_category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'pants','men','joggerPants1','Jogger Pants',31),(2,'pants','men','chinoPants2','Chino Pants',35),(3,'pants','men','sweetPants3','Sweet Pants',39),(4,'pants','men','worksPanst4','Works Pants',42),(5,'t_shirt','men','dryColorTShirt1','Dry Color T-Shirt',27),(6,'t_shirt','men','doraemonTShirt2','Doraemon T-Shirt',44),(7,'t_shirt','men','stripedTShirt3','Stripped T-Shirt',33),(8,'jeans','men','slimJeans1','Slim Jeans',50),(9,'jeans','men','skinnyJeans2','Skinny Jeans',58),(10,'jeans','men','stretchJeans3','Stretch Heans',54),(11,'shorts','men','chinoShorts1','Chino Shorts',36),(12,'shorts','men','stretchShorts2','Stretch Shorts',25),(13,'shorts','men','cottonShorts3','Cotton Shorts',39),(14,'shorts','men','utilityShorts4','Utility Shorts',42),(15,'jackets','men','jerseyJacket1','Jersey Jacket',47),(16,'jackets','men','overShirtJacket2','Over Shirt Jacket',45),(17,'jackets','men','denimTruckerJacket3','Denim Trucker Jacket',44),(18,'jackets','men','comfortJacket4','Comfort Jacket',38),(19,'pants','women','denimPants5','Deim Pants',61),(20,'pants','women','stretchPants6','Stretch Pants',54),(21,'pants','women','heattechPants7','Heattech Pants',46),(22,'pants','women','sweetPants8','Sweet Pants',37),(23,'jeans','women','baggyJeans5','Baggy Jeans',29),(24,'jeans','women','stretchJeans6','Stretch Jeans',66),(25,'jeans','women','maternityJeans7','Maternity Jeans',57),(26,'jeans','women','jBrandJeans8','JBrand Jeans',23),(27,'t_shirt','women','uNeckTShirt5','U-Neck T-Shirt',45),(28,'t_shirt','women','vNeckTShirt6','V-Neck T-Shirt',37),(29,'t_shirt','women','dolmanTShirt7','Dolman T-Shirt',64),(30,'t_shirt','women','cottonTShirt8','Cotton T-Shirt',47),(31,'jackets','women','fluffyJacket5','Fluffy Jacker',60),(32,'jackets','women','compactJacket6','Compact Jacket',57),(33,'jackets','women','jerseyJacket7','Jersey Jacket',55),(34,'jackets','women','denimJacket8','Deim Jacket',77),(35,'shorts','women','linenCottonShorts5','Linen Cotton Shorts',33),(36,'shorts','women','bikerShorts6','Biker Shorts',43),(37,'shorts','women','cottonShorts7','Cotton Shorts',56),(38,'skirts','women','satinSkirt1','Statin Skirt',62),(39,'skirts','women','narrowSkirt2','Narrow Skirt',52),(40,'skirts','women','longSkirt3','Long Skirt',34),(41,'tops','baby','sweatShirtB1','Sweat Shirt',24),(42,'tops','baby','mickeyTShirtB2','Mickey T-Shirt',27),(43,'tops','baby','fleecePulloverB3','Fleece Pullover',29),(44,'tops','baby','disneyTShirtB4','Disney T-Shirt',33),(45,'bottoms','baby','knittedLeggingsB5','Knightted Leggings',32),(46,'bottoms','baby','relaxedLeggingsB6','Relaxed Leggings',38),(47,'bottoms','baby','fleeceLeggingsB7','Fleece Leggings',42),(48,'bottoms','baby','heattechLeggingsB8','Heattech Leggings',46),(49,'one_pieces','baby','onepieceOutfitB9','Onepiece Outfit',25),(50,'one_pieces','baby','kumaOutfitB10','Kuma Outfit',36),(51,'one_pieces','baby','woodlandOutfitB11','Woodland Outfit',54),(52,'one_pieces','baby','rabbitOutfitB12','Rabbit Outfit',46);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'abc@gmail.com','123456','user1'),(2,'xyz@gmail.com','123456789','user2');
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

-- Dump completed on 2022-10-20 17:13:39
