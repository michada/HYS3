CREATE DATABASE IF NOT EXISTS `HaveYouSeen_DB`;

DROP TABLE IF EXISTS `eventos`;
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

INSERT INTO `HaveYouSeen_DB`.`eventos` (`idEvento`, `titulo`, `usuario`, `maxAsistentes`, `inicio`, `fin`, `localidad`, `descripcion`, `descripcionDetallada`, `categoria`, `local`) VALUES (NULL, 'Coloquio sobre 50 Sobras de Grey', 'Alejandro', '50', '25/07/2015', '25/07/2015', 'Pontevedra', 'Charla sobre los efectos de este tipo de noveles en la sociedad', 'Se pretende realizar una charca en la que las personas pueden participar y expresar las opinion sobre el impacto de este tipo de novelas en los distintos sectores sociales según edades, ocupaciones, etc...', 'Libros', 'Cafe & te La Olivica');
INSERT INTO `HaveYouSeen_DB`.`eventos` (`idEvento`, `titulo`, `usuario`, `maxAsistentes`, `inicio`, `fin`, `localidad`, `descripcion`, `descripcionDetallada`, `categoria`, `local`) VALUES (NULL, 'Coloquio sobre 50 Sobras de Grey', 'Alejandro', '50', '25/07/2015', '25/07/2015', 'Pontevedra', 'Charla sobre los efectos de este tipo de noveles en la sociedad', 'Se pretende realizar una charca en la que las personas pueden participar y expresar las opinion sobre el impacto de este tipo de novelas en los distintos sectores sociales según edades, ocupaciones, etc...', 'Libros', 'Cafe & te La Olivica');
INSERT INTO `HaveYouSeen_DB`.`eventos` (`idEvento`, `titulo`, `usuario`, `maxAsistentes`, `inicio`, `fin`, `localidad`, `descripcion`, `descripcionDetallada`, `categoria`, `local`) VALUES (NULL, 'Coloquio sobre 50 Sobras de Grey', 'Alejandro', '50', '25/07/2015', '25/07/2015', 'Pontevedra', 'Charla sobre los efectos de este tipo de noveles en la sociedad', 'Se pretende realizar una charca en la que las personas pueden participar y expresar las opinion sobre el impacto de este tipo de novelas en los distintos sectores sociales según edades, ocupaciones, etc...', 'Libros', 'Cafe & te La Olivica');
INSERT INTO `HaveYouSeen_DB`.`eventos` (`idEvento`, `titulo`, `usuario`, `maxAsistentes`, `inicio`, `fin`, `localidad`, `descripcion`, `descripcionDetallada`, `categoria`, `local`) VALUES (NULL, 'Coloquio sobre 50 Sobras de Grey', 'Alejandro', '50', '25/07/2015', '25/07/2015', 'Pontevedra', 'Charla sobre los efectos de este tipo de noveles en la sociedad', 'Se pretende realizar una charca en la que las personas pueden participar y expresar las opinion sobre el impacto de este tipo de novelas en los distintos sectores sociales según edades, ocupaciones, etc...', 'Libros', 'Cafe & te La Olivica');