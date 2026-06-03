-- Estructura de Tablas para base de VIAJES ms de reservas
 
use  viajes;

--
-- Estructura de tabla `hoteles`
--

DROP TABLE IF EXISTS `hoteles`;
 
CREATE TABLE `hoteles` (
  `idHotel` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `categoria` int(10) unsigned NOT NULL,
  `precio` double NOT NULL,
  `disponible` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`idHotel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
 

--
-- Estructura de tabla `reservas`
--

DROP TABLE IF EXISTS `reservas`;

CREATE TABLE `reservas` (
  `idreserva` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `hotel` int(10) unsigned NOT NULL,
  `vuelo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idreserva`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


 

--
-- Estructura de tabla `vuelos`
--

DROP TABLE IF EXISTS `vuelos`;

CREATE TABLE `vuelos` (
  `idvuelo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `company` varchar(45) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `precio` double NOT NULL,
  `plazas` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idvuelo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
 

 
