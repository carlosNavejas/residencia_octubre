﻿
INSERT INTO regiones VALUES(1,'Acapulco');
INSERT INTO regiones VALUES(2,'Costa Chica');
INSERT INTO regiones VALUES(3,'Costa Grande');
INSERT INTO regiones VALUES(4,'Centro');
INSERT INTO regiones VALUES(5,'La Montaña');
INSERT INTO regiones VALUES(6,'Norte');
INSERT INTO regiones VALUES(7,'Tierra Caliente');

INSERT INTO municipios VALUES(1,'Acapulco de Juárez',1);

INSERT INTO municipios VALUES(12,'Ayutla de los Libres',2);
INSERT INTO municipios VALUES(13,'Azoyú',2);
INSERT INTO municipios VALUES(18,'Copala',2);
INSERT INTO municipios VALUES(25,'Cuautepec',2);
INSERT INTO municipios VALUES(23,'Cuajinicuilapa',2);
INSERT INTO municipios VALUES(30,'Florencio Villarreal',2);
INSERT INTO municipios VALUES(36,'Igualapa',2);
INSERT INTO municipios VALUES(80,'Juchitán',2);
INSERT INTO municipios VALUES(77,'Marquelia',2);
INSERT INTO municipios VALUES(46,'Ometepec',2);
INSERT INTO municipios VALUES(52,'San Luis Acatlán',2);
INSERT INTO municipios VALUES(53,'San Marcos',2);
INSERT INTO municipios VALUES(56,'Tecoanapa',2);
INSERT INTO municipios VALUES(62,'Tlacoachistlahuaca',2);
INSERT INTO municipios VALUES(71,'Xochistlahuaca',2);

INSERT INTO municipios VALUES(11,'Atoyac de Álvarez',3);
INSERT INTO municipios VALUES(14,'Benito Juárez',3);
INSERT INTO municipios VALUES(16,'Coahuayutla de José María Izazaga',3);
INSERT INTO municipios VALUES(21,'Coyuca de Benítez',3);
INSERT INTO municipios VALUES(68,'La Unión de Isidoro Montes de Oca',3);
INSERT INTO municipios VALUES(48,'Petatlán',3);
INSERT INTO municipios VALUES(57,'Técpan de Galeana',3);
INSERT INTO municipios VALUES(38,'Zihuatanejo de Azueta',3);

INSERT INTO municipios VALUES(2,'Ahuacuotzingo',4);
INSERT INTO municipios VALUES(28,'Chilapa de Álvarez',4);
INSERT INTO municipios VALUES(29,'Chilpancingo de los Bravo',4);
INSERT INTO municipios VALUES(75,'Eduardo Neri',4);
INSERT INTO municipios VALUES(32,'General Heliodoro Castillo',4);
INSERT INTO municipios VALUES(79,'José Joaquín de Herrera',4);
INSERT INTO municipios VALUES(39,'Juan R. Escudero',4);
INSERT INTO municipios VALUES(40,'Leonardo Bravo',4);
INSERT INTO municipios VALUES(42,'Mártir de Cuilapan',4);
INSERT INTO municipios VALUES(44,'Mochitlán',4);
INSERT INTO municipios VALUES(51,'Quechultenango',4);
INSERT INTO municipios VALUES(61,'Tixtla de Guerrero',4);
INSERT INTO municipios VALUES(74,'Zitlala',4);

INSERT INTO municipios VALUES(76,'Acatepec',5);
INSERT INTO municipios VALUES(4,'Alcozauca de Guerrero',5);
INSERT INTO municipios VALUES(5,'Alpoyeca',5);
INSERT INTO municipios VALUES(9,'Atlamajalcingo del Monte',5);
INSERT INTO municipios VALUES(10,'Atlixtac',5);
INSERT INTO municipios VALUES(78,'Cochoapa el Grande',5);
INSERT INTO municipios VALUES(20,'Copanatoyac',5);
INSERT INTO municipios VALUES(24,'Cualác',5);
INSERT INTO municipios VALUES(33,'Huamuxtitlán',5);
INSERT INTO municipios VALUES(81,'Iliatenco',5);
INSERT INTO municipios VALUES(41,'Malinaltepec',5);
INSERT INTO municipios VALUES(43,'Metlatónoc',5);
INSERT INTO municipios VALUES(45,'Olinalá',5);
INSERT INTO municipios VALUES(63,'Tlacoapa',5);
INSERT INTO municipios VALUES(65,'Tlalixtaquilla de Maldonado',5);
INSERT INTO municipios VALUES(66,'Tlapa de Comonfort',5);
INSERT INTO municipios VALUES(69,'Xalpatláhuac',5);
INSERT INTO municipios VALUES(70,'Xochihuehuetlán',5);
INSERT INTO municipios VALUES(72,'Zapotitlán Tablas',5);

INSERT INTO municipios VALUES(6,'Apaxtla',6);
INSERT INTO municipios VALUES(8,'Atenango del Río',6);
INSERT INTO municipios VALUES(15,'Buenavista de Cuéllar',6);
INSERT INTO municipios VALUES(17,'Cocula',6);
INSERT INTO municipios VALUES(19,'Copalillo',6);
INSERT INTO municipios VALUES(26,'Cuetzala del Progreso',6);
INSERT INTO municipios VALUES(34,'Huitzuco de los Figueroa',6);
INSERT INTO municipios VALUES(35,'Iguala de la Independencia',6);
INSERT INTO municipios VALUES(31,'General Canuto A. Neri',6);
INSERT INTO municipios VALUES(37,'Ixcateopan de Cuauhtémoc',6);
INSERT INTO municipios VALUES(47,'Pedro Ascencio Alquisiras',6);
INSERT INTO municipios VALUES(49,'Pilcaya',6);
INSERT INTO municipios VALUES(55,'Taxco de Alarcón',6);
INSERT INTO municipios VALUES(58,'Teloloapan',6);
INSERT INTO municipios VALUES(59,'Tepecoacuilco de Trujano',6);
INSERT INTO municipios VALUES(60,'Tetipac',6);

INSERT INTO municipios VALUES(3,'Ajuchitlán del Progreso',7);
INSERT INTO municipios VALUES(7,'Arcelia',7);
INSERT INTO municipios VALUES(22,'Coyuca de Catalán',7);
INSERT INTO municipios VALUES(27,'Cutzamala de Pinzón',7);
INSERT INTO municipios VALUES(50,'Pungarabato',7);
INSERT INTO municipios VALUES(54,'San Miguel Totolapan',7);
INSERT INTO municipios VALUES(64,'Tlalchapa',7);
INSERT INTO municipios VALUES(67,'Tlapehuala',7);
INSERT INTO municipios VALUES(73,'Zirándaro',7);


INSERT INTO `direcciones` VALUES (1,'MORELOS','44',NULL,35),(2,'flor de azar','77',NULL,64),(3,'Insurgentes','90',NULL,69),(4,'Cerrada de lopez','87',NULL,6),(5,'MORELOS','44',NULL,69),(6,'horticultores','23',NULL,73),(7,'cerrada sin nombre','77',NULL,49),(8,'INSURGENTES','127',NULL,61);

INSERT INTO `escuelas` (id_escuela,apellido_mdir,apellido_pdir,clave_escuela,matricula,nombre_dir,nombre_escuela,telefono,tipo,turno,direccion_id) VALUES (1,'Lopez','Salgado','12ACC0001Y',NULL,'Raul','RAFAEL RAMIREZ CASTAÑEDA','7441233344','Primaria','Matutino',1),(2,'Perez','Salgado','12DST0144J',NULL,'Juan','NABOR A. OJEDA CABALLERO','7474577889','Secundaria','Vespertino',2),(3,'Gatica','Gatica','12DJN2801Q',NULL,'Leonardo','NACIONES UNIDAS','7441233344','Primaria','Vespertino',3),(4,'Rebolledo','Nava','12DJN2834H',NULL,'Daniel','NARCISO BASSOLS','7455232412','Secundaria','Vespertino',4),(5,'Lopez','Nava','12DCC0427Z',NULL,'Edgar','NARCISO MENDOZA','7441233344','Primaria','Vespertino',5),(6,'Tizapa','Lopez','12DCC0761C',NULL,'Juan jose','NETZAHUALCOYOTL','7441233345','Secundaria','Vespertino',6),(7,'Salgado','Nuñez','12DBA0029D',NULL,'Roberto','NICOLAS BRAVO','7441245667','Primaria','Vespertino',7),(8,'Sanches','Martinez','12DPR0556Z',NULL,'David','NICOLAS SALINAS SOTELO','7441233344','Secundaria','Matutino',8);

INSERT INTO usuarios VALUES (1,'Dircio','Nava','$2a$10$xHaBQZ1tl0bjxSEvbh2Fw.v4X3PO5hvKFcrJQU7kO58FLatkLpc1e','carlangasdircionava@gmail.com',NULL,_binary '','2019-11-18 21:32:30.000000','Carlos',NULL);
INSERT INTO roles(nombrerol,user_idd) values("ROLE_ADMIN",1)