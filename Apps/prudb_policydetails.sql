-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prudb
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `policydetails`
--

DROP TABLE IF EXISTS `policydetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policydetails` (
  `pd_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contract_hdr_entid` varchar(255) DEFAULT NULL,
  `chdrsel` varchar(255) DEFAULT NULL,
  `chdrtype` varchar(255) DEFAULT NULL,
  `agntsel` varchar(255) DEFAULT NULL,
  `billcd` varchar(255) DEFAULT NULL,
  `billcurr` varchar(255) DEFAULT NULL,
  `billfreq` varchar(255) DEFAULT NULL,
  `cntcurr` varchar(255) DEFAULT NULL,
  `mop` varchar(255) DEFAULT NULL,
  `hpropdte` varchar(255) DEFAULT NULL,
  `occdate` varchar(255) DEFAULT NULL,
  `plansuff` varchar(255) DEFAULT NULL,
  `register` varchar(255) DEFAULT NULL,
  `srcebus` varchar(255) DEFAULT NULL,
  `datime` varchar(255) DEFAULT NULL,
  `owner_entid` varchar(255) DEFAULT NULL,
  `owner_party` varchar(255) DEFAULT NULL,
  `life_entid` varchar(255) DEFAULT NULL,
  `life_party` varchar(255) DEFAULT NULL,
  `anbage` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `occup` varchar(255) DEFAULT NULL,
  `relation` varchar(255) DEFAULT NULL,
  `selection` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `smoking` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `coverage_entid` varchar(255) DEFAULT NULL,
  `coverage_parent` varchar(255) DEFAULT NULL,
  `covr_mortcls` varchar(255) DEFAULT NULL,
  `covr_rcesdte` varchar(255) DEFAULT NULL,
  `covr_pcesdte` varchar(255) DEFAULT NULL,
  `covr_pcessage` varchar(255) DEFAULT NULL,
  `covr_pcesstrm` varchar(255) DEFAULT NULL,
  `covr_rcessage` varchar(255) DEFAULT NULL,
  `covr_rcesstrm` varchar(255) DEFAULT NULL,
  `covr_sumin` varchar(255) DEFAULT NULL,
  `covr_instprm` varchar(255) DEFAULT NULL,
  `covr_crtable` varchar(255) DEFAULT NULL,
  `covr_lmpcnt` varchar(255) DEFAULT NULL,
  `covr_rundte` varchar(255) DEFAULT NULL,
  `client_entid` varchar(255) DEFAULT NULL,
  `client_party` varchar(255) DEFAULT NULL,
  `client_clntnum` varchar(255) DEFAULT NULL,
  `cltaddr01` varchar(255) DEFAULT NULL,
  `cltdobx` varchar(255) DEFAULT NULL,
  `cltpcode` varchar(255) DEFAULT NULL,
  `lgivname` varchar(255) DEFAULT NULL,
  `lsurname` varchar(255) DEFAULT NULL,
  `marryd` varchar(255) DEFAULT NULL,
  `salutl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policydetails`
--

LOCK TABLES `policydetails` WRITE;
/*!40000 ALTER TABLE `policydetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `policydetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-03 16:36:15
