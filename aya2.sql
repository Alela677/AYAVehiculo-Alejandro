-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: aya2
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `correo_electronico` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `UK_qd514pd71j0t1mabyvi78q977` (`dni`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (13,'RUIZ CAMPOS','C/GUADALAJARA','12345678A','cliente1.jpg','LUCAS',662938271,'a_alejandro.leal.astorga@iespablopicasso.es'),(14,'GARCIA TORRES','C/ESTEPONA','12345678B','cliente2.jpg','DOLORES',662338275,'a_anarosa.fuentes.perez@iespablopicasso.es'),(15,'LEAL RAMIREZ','C/LA DEFENSA','12345678C','cliente3.jpg','CARLA',661948638,'anar27fp@gmail.com'),(16,'GORDILLO GARCIA','C/CARDILLO','12345678D','cliente4.jpg','ALFONSO',663439273,'ignacio.lorenzo@iespablopicasso.es');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `concesionario`
--

DROP TABLE IF EXISTS `concesionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `concesionario` (
  `idConcesionario` int NOT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `nif` varchar(9) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idConcesionario`),
  UNIQUE KEY `UK_hlecb43nl4imttns84wbk3clc` (`nif`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concesionario`
--

LOCK TABLES `concesionario` WRITE;
/*!40000 ALTER TABLE `concesionario` DISABLE KEYS */;
INSERT INTO `concesionario` VALUES (1,'Malaga','G12345678','AYA Vehiculos');
/*!40000 ALTER TABLE `concesionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id` int NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `contraseña` varchar(255) DEFAULT NULL,
  `departamento` varchar(255) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `fechaAlta` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `concesionario` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t541bvxya6g80ufk1uxa894mt` (`dni`),
  KEY `FKa22eactqsiooyfu6wdt3ut80y` (`concesionario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (8,'ROBLES GARCIA','TRABAJADOR','9c4f630e11562a69b1fa24bb36583345','MECANICOS','12345678A','2022/09/15','empleado1.jpg','LUIS',1),(9,'RUIZ LEAL','TRABAJADOR','9c4f630e11562a69b1fa24bb36583345','VENTAS','12345678B','2022/12/22','empleado2.jpg','MARIA',1),(10,'AGUILAR ASTORGA','JEFE MECANICOS','3273dbc9bc476832e0270b680383f6f8','MECANICOS','12345678C','2022/11/12','empleado3.jpg','CARLOS',1),(11,'ORTEGA GARCIA','TRABAJADOR','37432e59e81005c5b1ebc6304156a8ba','VENTAS','12345678D','2022/10/17','empleado4.jpg','LAURA',1),(12,'LUNA CAMPOS','DIRECTOR','7c5921e7e2371d21651bac5fca4ebc66','JEFE','12345678E','2023/1/20','empleado5.jpeg','FRANCISCO',1);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidades` (
  `id` int NOT NULL,
  `especialidad` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidades`
--

LOCK TABLES `especialidades` WRITE;
/*!40000 ALTER TABLE `especialidades` DISABLE KEYS */;
INSERT INTO `especialidades` VALUES (23,'COCHE');
/*!40000 ALTER TABLE `especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidades_empleados`
--

DROP TABLE IF EXISTS `especialidades_empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidades_empleados` (
  `especialidades_id` int NOT NULL,
  `empleados_id` int NOT NULL,
  PRIMARY KEY (`empleados_id`,`especialidades_id`),
  KEY `FK1jx6l7cgnk9g0q9tuf7st63my` (`especialidades_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidades_empleados`
--

LOCK TABLES `especialidades_empleados` WRITE;
/*!40000 ALTER TABLE `especialidades_empleados` DISABLE KEYS */;
INSERT INTO `especialidades_empleados` VALUES (23,8);
/*!40000 ALTER TABLE `especialidades_empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (25);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propuesta`
--

DROP TABLE IF EXISTS `propuesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propuesta` (
  `idPropuesta` int NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecharPropuesta` varchar(255) DEFAULT NULL,
  `marcaVehiculo` varchar(255) DEFAULT NULL,
  `modeloVehiculo` varchar(255) DEFAULT NULL,
  `nombreCliente` varchar(255) DEFAULT NULL,
  `nombreEmpleado` varchar(255) DEFAULT NULL,
  `precioPorpuesta` double DEFAULT NULL,
  `cliente_idCliente` int DEFAULT NULL,
  `empleado_id` int DEFAULT NULL,
  `vehiculo` int DEFAULT NULL,
  PRIMARY KEY (`idPropuesta`),
  KEY `FKhgy9rmc2rb35lsoao7ugieiqm` (`cliente_idCliente`),
  KEY `FKdx46pmj7mgogg2euvtk7ktmsu` (`empleado_id`),
  KEY `FKdc2n5kr2gsnb5l21vfk81fy5j` (`vehiculo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propuesta`
--

LOCK TABLES `propuesta` WRITE;
/*!40000 ALTER TABLE `propuesta` DISABLE KEYS */;
INSERT INTO `propuesta` VALUES (19,'FINALIZADA','2023/01/27','SEAT','IBIZA','LUCAS','MARIA',17300,13,9,6),(20,'CERRADA','2023/01/25','CUPRA','URBAN REBEL','CARLA','MARIA',29000,15,9,5),(21,'ABIERTA','2023/01/22','KIA','RIO','DOLORES','LAURA',17900,14,11,3);
/*!40000 ALTER TABLE `propuesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reparacion`
--

DROP TABLE IF EXISTS `reparacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reparacion` (
  `idReparacion` int NOT NULL,
  `coste` double DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `nombreCliente` varchar(255) DEFAULT NULL,
  `nombreMecanico` varchar(255) DEFAULT NULL,
  `pieza` varchar(255) DEFAULT NULL,
  `tiempo` varchar(255) DEFAULT NULL,
  `empleado` int DEFAULT NULL,
  `vehiculo_id` int DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  `estado_reparacion` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idReparacion`),
  KEY `FKgrei8b4wuvuc9go8usms1j6ai` (`empleado`),
  KEY `FKf8hwgu1or0ygr6x1vtuljwykb` (`vehiculo_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparacion`
--

LOCK TABLES `reparacion` WRITE;
/*!40000 ALTER TABLE `reparacion` DISABLE KEYS */;
INSERT INTO `reparacion` VALUES (22,150,'DISPONIBLE','2023/01/24','SEAT','1234ABC','IBIZA','LUCAS','LUIS','TUBO ESCAPE','3',8,17,'ibiza.jpg','ASIGNADA A LUIS'),(25,100,'DISPONIBLE','2023/02/28','SEAT','1234ABC','IBIZA','LUCAS','LUIS','RUEDAS','2',8,17,'ibiza.jpg','ASIGNADA A LUIS'),(26,200,'DISPONIBLE','2023/03/02','CUPRA','4321CBA','URBAN REBEL','DOLORES','LUIS','PINTURA','4',8,26,'cupra.jpg','ASIGNADA A LUIS'),(27,150,'DISPONIBLE','2023/03/03','SEAT','1234ABC','IBIZA','LUCAS','LUIS','PINTURA','3',8,17,'ibiza.jpg','ASIGNADA A LUIS');
/*!40000 ALTER TABLE `reparacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `id` int NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `cliente_idCliente` int DEFAULT NULL,
  `concesionario` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf2jv2vx2jckiuam6pdek7bn43` (`cliente_idCliente`),
  KEY `FKqh453evn6c9ai7mv42mu4uyms` (`concesionario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (2,'NEGRO','bmw.jpg','BMW',NULL,'I7',137000,NULL,1),(3,'AZUL','kia-rio.jpeg','KIA',NULL,'RIO',17900,NULL,1),(4,'VERDE','focus.jpeg','FORD',NULL,'FOCUS',29776,NULL,1),(5,'VERDE','cupra.jpg','CUPRA',NULL,'URBAN REBEL',29000,NULL,1),(6,'ROJO','ibiza.jpg','SEAT',NULL,'IBIZA',17300,NULL,1),(7,'GRIS','audia3.jpg','AUDI',NULL,'A3',23500,NULL,1),(17,'ROJO','ibiza.jpg','SEAT','1234ABC','IBIZA',0,13,1),(26,'VERDE',NULL,'CUPRA','4321CBA','URBAN REBEL',0,14,1);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id` int NOT NULL,
  `fechaVenta` varchar(255) DEFAULT NULL,
  `marcaVehiculo` varchar(255) DEFAULT NULL,
  `modeloVehiculo` varchar(255) DEFAULT NULL,
  `nombreCliente` varchar(255) DEFAULT NULL,
  `nombreEmpleado` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `vendedor` int DEFAULT NULL,
  `vehiculo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpdhkc3wih7epcdqn61v7evg8x` (`vendedor`),
  KEY `FK8udh6q19rcw57np2vif1821qx` (`vehiculo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (18,'2023/01/27','SEAT','IBIZA','LUCAS','MARIA',17300,9,6),(26,'2023/02/28','CUPRA','URBAN REBEL','DOLORES','LAURA',29000,11,5),(27,'2023/03/04','AUDI','A3','CARLA','MARIA',23500,9,7);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-08 18:34:14
