CREATE DATABASE  IF NOT EXISTS `ejercicios_sql` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ejercicios_sql`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ejercicios_sql
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamento` (
  `codDepto` varchar(4) NOT NULL,
  `nombreDpto` varchar(20) NOT NULL,
  `ciudad` varchar(15) DEFAULT NULL,
  `codDirector` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`codDepto`),
  KEY `FK_EmpDir` (`codDirector`),
  CONSTRAINT `FK_EmpDir` FOREIGN KEY (`codDirector`) REFERENCES `empleado` (`nDIEmp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES ('1000','GERENCIA','CALI','31.840.269'),('1500','PRODUCCIÓN','CALI','16.211.383'),('2000','VENTAS','CALI','31.178.144'),('2100','VENTAS','POPAYAN','31.751.219'),('2200','VENTAS','BUGA','768.782'),('2300','VENTAS','CARTAGO','737.689'),('3000','INVESTIGACIÓN','CALI','16.759.060'),('3500','MERCADEO','CALI','22.222.222'),('4000','MANTENIMIENTO','CALI','333.333.333'),('4100','MANTENIMIENTO','POPAYAN','888.888'),('4200','MANTENIMIENTO','BUGA','11.111.111'),('4300','MANTENIMIENTO','CARTAGO','444.444'),('6000','TRANSPORTE','CALI',NULL),('7000','COMPRAS','CALI',NULL);
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `nDIEmp` varchar(12) NOT NULL,
  `nomEmp` varchar(30) NOT NULL,
  `sexEmp` char(1) NOT NULL,
  `fecNac` date NOT NULL,
  `fecIncorporacion` date NOT NULL,
  `salEmp` float NOT NULL,
  `comisionE` float NOT NULL,
  `cargoE` varchar(15) NOT NULL,
  `jefeID` varchar(12) DEFAULT NULL,
  `codDepto` varchar(4) NOT NULL,
  PRIMARY KEY (`nDIEmp`),
  KEY `FK_Empl` (`jefeID`),
  KEY `FK_Dpto` (`codDepto`),
  CONSTRAINT `FK_Dpto` FOREIGN KEY (`codDepto`) REFERENCES `departamento` (`codDepto`),
  CONSTRAINT `FK_Empl` FOREIGN KEY (`jefeID`) REFERENCES `empleado` (`nDIEmp`),
  CONSTRAINT `empleado_chk_1` CHECK ((`sexEmp` in (_utf8mb4'F',_utf8mb4'M')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('1.130.222','José Giraldo','M','1985-01-20','2000-11-01',1200000,400000,'Asesor','22.222.222','3500'),('1.130.333','Pedro Blanco','M','1987-10-28','2000-10-01',800000,3000000,'Vendedor','31.178.144','2000'),('1.130.444','Jesús Alfonso','M','1988-03-14','2000-10-01',800000,3500000,'Vendedor','31.178.144','2000'),('1.130.555','Julián Mora','M','1989-07-03','2000-10-01',800000,3100000,'Vendedor','31.178.144','2200'),('1.130.666','Manuel Millán','M','1990-12-08','2004-06-01',800000,3700000,'Vendedor','31.178.144','2300'),('1.130.777','Marcos Cortez','M','1986-06-23','2000-04-16',2550000,500000,'Mecánico','333.333.333','4000'),('1.130.782','Antonio Gil','M','1980-01-23','2010-04-16',850000,1500000,'Técnico','16.211.383','1500'),('1.751.219','Melissa Roa','F','1960-06-19','2001-03-16',2250000,2500000,'Vendedor','31.178.144','2100'),('11.111.111','Irene Díaz','F','1979-09-28','2004-06-01',1050000,200000,'Mecánico','333.333.333','4200'),('16.211.383','Luis Pérez','M','1956-02-25','2000-01-01',5050000,0,'Director','31.840.269','1500'),('16.759.060','Darío Casas','M','1960-04-05','1992-11-01',4500000,500000,'Investigador','31.840.269','3000'),('19.709.802','William Daza','M','1982-10-09','1999-12-16',2250000,1000000,'Investigador','16.759.060','3000'),('22.222.222','Carla López','F','1975-05-11','2005-07-16',4500000,500000,'Jefe Mercadeo','31.840.269','3500'),('22.222.333','Carlos Rozo','M','1975-05-11','2001-09-16',750000,500000,'Vigilante','31.840.269','3500'),('31.174.099','Diana Solarte','F','1957-11-19','1990-05-16',1250000,500000,'Secretaria','31.840.269','1000'),('31.178.144','Rosa Angulo','F','1957-03-15','1998-08-16',3250000,3500000,'Jefe Ventas','31.840.269','2000'),('31.840.269','María Rojas','F','1959-01-15','1990-05-16',6250000,1500000,'Gerente',NULL,'1000'),('333.333.333','Elisa Rojas','F','1979-09-28','2004-06-01',3000000,1000000,'Jefe Mecánicos','31.840.269','4000'),('333.333.334','Marisol Pulido','F','1979-10-01','1990-05-16',3250000,1000000,'Investigador','16.759.060','3000'),('333.333.335','Ana Moreno','F','1992-01-05','2004-06-01',1200000,400000,'Secretaria','16.759.060','3000'),('333.333.336','Carolina Ríos','F','1992-02-15','2000-10-01',1250000,500000,'Secretaria','16.211.383','1500'),('333.333.337','Edith Muñoz','F','1992-03-31','2000-10-01',800000,3600000,'Vendedor','31.178.144','2100'),('444.444','Abel Gómez','M','1939-12-24','2000-10-01',1050000,200000,'Mecánico','333.333.333','4300'),('737.689','Mario Llano','M','1945-08-30','1990-05-16',2250000,2500000,'Vendedor','31.178.144','2300'),('768.782','Joaquín Rosas','M','1947-07-07','1990-05-16',2250000,2500000,'Vendedor','31.178.144','2200'),('888.888','Iván Duarte','M','1955-08-12','1998-05-16',1050000,200000,'Mecánico','333.333.333','4100');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `empleado_BEFORE_INSERT` BEFORE INSERT ON `empleado` FOR EACH ROW BEGIN
IF NEW.nDIEmp = NEW.jefeID THEN
	SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'El empleado no puede ser jefe de sí mismo';
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `empleado_gana_mas`
--

DROP TABLE IF EXISTS `empleado_gana_mas`;
/*!50001 DROP VIEW IF EXISTS `empleado_gana_mas`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `empleado_gana_mas` AS SELECT 
 1 AS `nomEmp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `empleado_gana_menos`
--

DROP TABLE IF EXISTS `empleado_gana_menos`;
/*!50001 DROP VIEW IF EXISTS `empleado_gana_menos`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `empleado_gana_menos` AS SELECT 
 1 AS `nomEmp`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `empleado_vista`
--

DROP TABLE IF EXISTS `empleado_vista`;
/*!50001 DROP VIEW IF EXISTS `empleado_vista`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `empleado_vista` AS SELECT 
 1 AS `nDIEmp`,
 1 AS `nomEmp`,
 1 AS `sexEmp`,
 1 AS `fecNac`,
 1 AS `fecIncorporacion`,
 1 AS `salEmp`,
 1 AS `comisionE`,
 1 AS `cargoE`,
 1 AS `jefeID`,
 1 AS `codDepto`,
 1 AS `id`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'ejercicios_sql'
--

--
-- Dumping routines for database 'ejercicios_sql'
--
/*!50003 DROP FUNCTION IF EXISTS `empleado_gana_menos_f` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `empleado_gana_menos_f`() RETURNS varchar(30) CHARSET utf8mb4
    READS SQL DATA
BEGIN
RETURN (SELECT nomEmp FROM empleado_gana_menos);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `sumar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `sumar`(a INT, b INT) RETURNS int
    NO SQL
BEGIN
RETURN a + b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `empleado_ajuste_sueldo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `empleado_ajuste_sueldo`(porcentaje DOUBLE)
BEGIN
UPDATE empleado SET salEmp = salEmp - (salEmp * porcentaje) WHERE TRUE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `empleado_obtener_por_codigo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `empleado_obtener_por_codigo`(cod INT)
BEGIN
SELECT * FROM empleado_vista WHERE id = cod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `informe` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `informe`()
BEGIN
SELECT 
    empleado_gana_menos_f() AS 'El que gana menos',
    (SELECT 
            nomEmp
        FROM
            empleado_gana_mas) AS 'El que gana más',
    (SELECT 
            MAX(salEmp) + MIN(salEmp)
        FROM
            Empleado) AS suma;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sumar_p` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sumar_p`(a INT, b INT, OUT resultado INT)
BEGIN
SET resultado = a + b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `empleado_gana_mas`
--

/*!50001 DROP VIEW IF EXISTS `empleado_gana_mas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `empleado_gana_mas` AS select `empleado`.`nomEmp` AS `nomEmp` from `empleado` order by `empleado`.`salEmp` desc limit 1 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `empleado_gana_menos`
--

/*!50001 DROP VIEW IF EXISTS `empleado_gana_menos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `empleado_gana_menos` AS select `empleado`.`nomEmp` AS `nomEmp` from `empleado` order by `empleado`.`salEmp` limit 1 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `empleado_vista`
--

/*!50001 DROP VIEW IF EXISTS `empleado_vista`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `empleado_vista` AS select `empleado`.`nDIEmp` AS `nDIEmp`,`empleado`.`nomEmp` AS `nomEmp`,`empleado`.`sexEmp` AS `sexEmp`,`empleado`.`fecNac` AS `fecNac`,`empleado`.`fecIncorporacion` AS `fecIncorporacion`,`empleado`.`salEmp` AS `salEmp`,`empleado`.`comisionE` AS `comisionE`,`empleado`.`cargoE` AS `cargoE`,`empleado`.`jefeID` AS `jefeID`,`empleado`.`codDepto` AS `codDepto`,(replace(`empleado`.`nDIEmp`,'.','') * 1) AS `id` from `empleado` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-14 14:23:46
