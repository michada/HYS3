CREATE DATABASE IF NOT EXISTS `HaveYouSeen_DB` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci; 
USE `HaveYouSeen_DB`;

CREATE TABLE IF NOT EXISTS `Asistentes` (
  `usuario` int(100) NOT NULL,
  `evento` int(100) NOT NULL,
  KEY `usuario` (`usuario`),
  KEY `evento` (`evento`),
  KEY `usuario_2` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Contiene una relación entre el evento y sus asistentes';

CREATE TABLE IF NOT EXISTS `Eventos` (
  `idEvento` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `usuario` int(100) NOT NULL,
  `maxAsistentes` int(100) NOT NULL,
  `inicio` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `fin` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idEvento`),
  UNIQUE KEY `idEvento` (`idEvento`),
  KEY `usuario` (`usuario`),
  KEY `usuario_2` (`usuario`),
  KEY `usuario_3` (`usuario`),
  KEY `usuario_4` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla que contiene los datos del evento y una relación con el usuario creador';

CREATE TABLE IF NOT EXISTS `Usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `login` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `password` varbinary(100) DEFAULT NULL,
  `nombre` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `idUsuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Tabla que contiene los datos de los usuarios registrados';


GRANT USAGE ON *.* TO 'Admin'@'localhost' IDENTIFIED BY PASSWORD '*4ACFE3202A5FF5CF467898FC58AAB1D615029441';

GRANT ALL PRIVILEGES ON `HaveYouSeen\_DB`.* TO 'Admin'@'localhost' WITH GRANT OPTION;