CREATE DATABASE IF NOT EXISTS `HYSDB`;

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

INSERT INTO `Eventos` (`idEvento`, `titulo`, `usuario`, `maxAsistentes`, `inicio`, `fin`, `localidad`, `descripcion`, `descripcionDetallada`, `categoria`, `local`) VALUES
(1, 'Coloquio sobre 50 Sobras de Grey', 'Alejandro', 50, '25/07/2015', '25/07/2015', 'Pontevedra', 'Charla sobre los efectos de este tipo de noveles en la sociedad', 'Se pretende realizar una charca en la que las personas pueden participar y expresar las opinion sobre el impacto de este tipo de novelas en los distintos sectores sociales según edades, ocupaciones, etc...', 'Libros', 'Cafe & te La Olivica'),
(2, 'Presentación y firma: Exportación 2.0 de Rafael Olano', 'Evaristo', 250, '20/08/2015', '20/08/2015', 'Vigo', 'Presentación y firma', 'Se celebrará la presentación del libro Exportación 2.0 de Rafael Olano en la Casa del Libro de Vigo con la posibilidad de obtener la firma de Rafael Olano en los ejemplares', 'Libros', 'Casa del Libro de Vigo'),
(3, 'Presentación de Crisantemo era la respuesta', 'Pablo', 150, '13/05/2015', '13/05/2015', 'Madrid', 'Presentación del libro', 'Presentación e incorporación del ejemplar Crisantemo era la respuesta del autor David B. Andrada, Helena Lopez y Carmelo Segura', 'Libros', 'Fuencarral, 119'),
(4, 'Concierto de Niño y Pistola', 'Diego', 300, '07/07/2015', '07/07/2015', 'Ourense', 'Concierto de Niño y Pistola', 'Concierto en la Sala Berlín del grupo formado en Baiona en 2004 por Manuel H. Portolés, Enrique Esmerode, Ramón Martín y Álvaro A. Rivera', 'Conciertos', 'Sala Berlín, Ourense'),
(5, 'Charla sobre Juegos de Tronos', 'Pablo', 50, '13/10/2015', '13/10/2015', 'Vigo', 'Charla abierta sobre la última temporada de Juego de Tronos', 'Se pretende discutir sobre el estreno de la última temporada de Juegos de Tronos, calidad de la temporada, espectativas que se tenían, exito o fracaso, etc.', 'Series', 'Cafetería Gallaecia en Travesia 16'),
(6, 'La que se avecina, si o no?', 'Pablo', 45, '01/03/2016', '01/03/2016', 'Madrid', 'Discusión sobre si se debe acabar la que se avecina o debe continuar.', 'Se pretende obtener una idea generalizada de si se debe retirar la serie La que se avecina o por lo contrario se deben continuar retransmitiendo los episodios una y otra vez hasta el infito', 'Series', 'Tetería Moja tu pasta, Gran Vía 69'),
(7, 'Coloquio Ken Follet', 'Pablin', 78, '23/09/2015', '23/09/2015', 'Barcelona', 'Charla sobre los títulos publicados del autor Ken Follet', 'Se pretende hablar sobre la imaginación de este autor para llevar a cabo sus publicaciones y sacar una idea de sus posibles inspiraciones.', 'Libros', 'La charquita, Av. Diagonal 33'),
(8, 'Convención de Star Trek', 'Pablin', 250, '30/11/2015', '1/12/2015', 'Madrid', 'Linea clásica o reboot??', 'Se trata de analizar a fondo las distintas vertientes y la viabilidad de la nueva línea temporal de la saga.', 'Peliculas', 'Madrid Arena'),
(9, 'Cuentacuentos y firma de ejemplares', 'Adolfo', 125, '20/04/2015', '28/04/2015', 'Vigo', 'Cuentacuentos, firma de ejemplares, charlas y coloquis.', 'La Feria del Libro continúa con su programación en A Ferrería. La actividad de cuentacuentos y la firma de ejemplares por parte de escritores protagonizan cada jornada. ', 'Libros', 'Biblioteca Pública de Vigo'),
(10, 'FreakZone', 'Evaristo', 317, '30/03/2015', '03/04/2015', 'Ponferrada', 'Congreso nacional sobre manga y anime.', 'Coloquios de creación de manga, torneos de videojuegos, concurso de disfraces y presentaciones y firmas de ejemplares', 'Libros', 'Obra social Caja España, Rio Selmo \r\n3');
