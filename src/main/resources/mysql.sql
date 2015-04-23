CREATE DATABASE IF NOT EXISTS `HaveYouSeen_DB`;

CREATE TABLE IF NOT EXISTS `eventos` (
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
  PRIMARY KEY (`idevento`)
);

GRANT ALL PRIVILEGES ON `HaveYouSeen_DB`.* TO 'root'@'localhost' IDENTIFIED BY PASSWORD '*4ACFE3202A5FF5CF467898FC58AAB1D615029441' WITH GRANT OPTION;