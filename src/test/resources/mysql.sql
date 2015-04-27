CREATE DATABASE IF NOT EXISTS `HYSDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `HYSDB`;

DROP TABLE IF EXISTS `Eventos`;
CREATE TABLE IF NOT EXISTS `Eventos` (
  `idEvento` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `usuario` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `maxAsistentes` int(100) NOT NULL,
  `inicio` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `fin` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `localidad` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE latin1_spanish_ci NOT NULL,
  `descripcionDetallada` varchar(1000) COLLATE latin1_spanish_ci NOT NULL,
  `categoria` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `local` varchar(150) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`idEvento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='Contiene eventos' AUTO_INCREMENT=11 ;

GRANT USAGE ON *.* TO 'root'@'localhost' IDENTIFIED BY PASSWORD '*4ACFE3202A5FF5CF467898FC58AAB1D615029441';

GRANT ALL PRIVILEGES ON `HYSDB`.* TO 'root'@'localhost' IDENTIFIED BY PASSWORD '*4ACFE3202A5FF5CF467898FC58AAB1D615029441' WITH GRANT OPTION;