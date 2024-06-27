-- MariaDB dump 10.19  Distrib 10.5.23-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: appdb
-- ------------------------------------------------------
-- Server version	5.6.51

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `appdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `appdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `appdb`;

--
-- Table structure for table `admission`
--

DROP TABLE IF EXISTS `admission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admission` (
  `admission_id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`admission_id`),
  KEY `FKo204p6ebip9vhi9au181lqbcj` (`course_id`),
  KEY `FKq3acjsqfwl5jksp7utfmyjhju` (`student_id`),
  CONSTRAINT `FKo204p6ebip9vhi9au181lqbcj` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FKq3acjsqfwl5jksp7utfmyjhju` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admission`
--

LOCK TABLES `admission` WRITE;
/*!40000 ALTER TABLE `admission` DISABLE KEYS */;
/*!40000 ALTER TABLE `admission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admission_seq`
--

DROP TABLE IF EXISTS `admission_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admission_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admission_seq`
--

LOCK TABLES `admission_seq` WRITE;
/*!40000 ALTER TABLE `admission_seq` DISABLE KEYS */;
INSERT INTO `admission_seq` VALUES (1);
/*!40000 ALTER TABLE `admission_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` bigint(20) NOT NULL,
  `course_description` varchar(255) DEFAULT NULL,
  `course_duration` varchar(255) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `fees` double NOT NULL,
  `no_of_seats` int(11) NOT NULL,
  `institute_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `FKbyyciv7mbmmkp7e6t9db0m9w4` (`institute_id`),
  CONSTRAINT `FKbyyciv7mbmmkp7e6t9db0m9w4` FOREIGN KEY (`institute_id`) REFERENCES `institute` (`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_seq`
--

DROP TABLE IF EXISTS `course_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_seq`
--

LOCK TABLES `course_seq` WRITE;
/*!40000 ALTER TABLE `course_seq` DISABLE KEYS */;
INSERT INTO `course_seq` VALUES (1);
/*!40000 ALTER TABLE `course_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institute`
--

DROP TABLE IF EXISTS `institute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institute` (
  `institute_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `institute_address` varchar(255) DEFAULT NULL,
  `institute_description` varchar(255) DEFAULT NULL,
  `institute_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `no_of_courses_available` bigint(20) NOT NULL,
  PRIMARY KEY (`institute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institute`
--

LOCK TABLES `institute` WRITE;
/*!40000 ALTER TABLE `institute` DISABLE KEYS */;
/*!40000 ALTER TABLE `institute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institute_seq`
--

DROP TABLE IF EXISTS `institute_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institute_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institute_seq`
--

LOCK TABLES `institute_seq` WRITE;
/*!40000 ALTER TABLE `institute_seq` DISABLE KEYS */;
INSERT INTO `institute_seq` VALUES (1);
/*!40000 ALTER TABLE `institute_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `bidding_price` double NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sold` bit(1) NOT NULL,
  `team_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdvd6ljes11r44igawmpm1mc5s` (`team_id`),
  CONSTRAINT `FKdvd6ljes11r44igawmpm1mc5s` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` bigint(20) NOT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `eligibility` varchar(255) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `marks_diploma` int(11) NOT NULL,
  `markshsc` int(11) NOT NULL,
  `markssslc` int(11) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `mother_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `UK_bkix9btnoi1n917ll7bplkvg5` (`user_id`),
  CONSTRAINT `FKk5m148xqefonqw7bgnpm0snwj` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_seq`
--

DROP TABLE IF EXISTS `student_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_seq`
--

LOCK TABLES `student_seq` WRITE;
/*!40000 ALTER TABLE `student_seq` DISABLE KEYS */;
INSERT INTO `student_seq` VALUES (1);
/*!40000 ALTER TABLE `student_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `maximum_budget` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (1);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-27  6:08:14
