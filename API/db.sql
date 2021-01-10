SET names utf8mb4;
SET charset utf8mb4;
DROP DATABASE IF EXISTS `coursequiz`;
CREATE DATABASE  `coursequiz` CHARSET `utf8mb4`;
USE `coursequiz`;

/***
    create tables that don't depend on other tables existing
***/

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(100) DEFAULT NULL,
  `familienaam` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `userkey` varchar(100) DEFAULT NULL,
  `avatarpad` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

LOCK TABLES `users` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `opleidingen`;
CREATE TABLE `opleidingen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

LOCK TABLES `opleidingen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;




/***
    create tables that depend on other tables existing
***/

DROP TABLE IF EXISTS `rollen`;
CREATE TABLE `rollen` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `rol` int NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `rol_userid_idx` (`userid`),
  CONSTRAINT `rol__userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
);

LOCK TABLES `rollen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `vakken`;
CREATE TABLE `vakken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) DEFAULT NULL,
  `jaar` int DEFAULT NULL,
  `opleidingid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vak_opleidingid_idx` (`opleidingid`),
  CONSTRAINT `vak__opleidingid` FOREIGN KEY (`opleidingid`) REFERENCES `opleidingen` (`id`)
);

LOCK TABLES `vakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `docenten`;
CREATE TABLE `docenten` (
  `userid` int NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `docent_userid_idx` (`userid`),
  CONSTRAINT `docent__userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
);

LOCK TABLES `docenten` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `studenten`;
CREATE TABLE `studenten` (
  `userid` int NOT NULL,
  `opleidingid` int NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `student_userid_idx` (`userid`),
  KEY `student_opleidingid_idx` (`opleidingid`),
  CONSTRAINT `student__opleidingid` FOREIGN KEY (`opleidingid`) REFERENCES `opleidingen` (`id`),
  CONSTRAINT `student__userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
);

LOCK TABLES `studenten` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `hoofdstukken`;
CREATE TABLE `hoofdstukken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titel` varchar(255) DEFAULT NULL,
  `nummer` float DEFAULT NULL,
  `vakid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hoofdstuk_vakid_idx` (`vakid`),
  CONSTRAINT `hoofdstuk__vakid` FOREIGN KEY (`vakid`) REFERENCES `vakken` (`id`)
);

LOCK TABLES `hoofdstukken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `studentenvakken`;
CREATE TABLE `studentenvakken` (
  `userid` int NOT NULL,
  `vakid` int NOT NULL,
  KEY `studentenvak_userid_idx` (`userid`),
  KEY `studentenvak_vakid_idx` (`vakid`),
  PRIMARY KEY (userid, vakid),
  CONSTRAINT `studentenvak__userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `studentenvak__vakid` FOREIGN KEY (`vakid`) REFERENCES `vakken` (`id`)
);

LOCK TABLES `studentenvakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `docentenvakken`;
CREATE TABLE `docentenvakken` (
  `userid` int NOT NULL,
  `vakid` int NOT NULL,
  KEY `docentenvak_userid_idx` (`userid`),
  KEY `docentenvak_vakid_idx` (`vakid`),
  PRIMARY KEY (userid, vakid),
  CONSTRAINT `docentenvak__userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `docentenvak__vakid` FOREIGN KEY (`vakid`) REFERENCES `vakken` (`id`)
);

LOCK TABLES `docentenvakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `vragen`;
CREATE TABLE `vragen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraag` varchar(255) DEFAULT NULL,
  `aantalAntwoordenTonen` int DEFAULT NULL,
  `juisteAntwoordTonen` tinyint DEFAULT NULL,
  `hoofdstukid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vraag_hoofdstukid_idx` (`hoofdstukid`),
  CONSTRAINT `vraag__hoofdstukid` FOREIGN KEY (`hoofdstukid`) REFERENCES `hoofdstukken` (`id`)
);

LOCK TABLES `vragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `vragenmeerderemultiplechoice`;
CREATE TABLE `vragenmeerderemultiplechoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraagid` int DEFAULT NULL,
  `aantalJuisteAntwoordenNodig` int DEFAULT NULL,
  `minimumAantalJuisteAntwoordenTonen` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vraagmeerderemultiple_vraagid_idx` (`vraagid`),
  CONSTRAINT `vraagmeerderemultiple__vraagid` FOREIGN KEY (`vraagid`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `vragenmeerderemultiplechoice` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `vragensimpelemultiplechoice`;
CREATE TABLE `vragensimpelemultiplechoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraagid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vraagsimpelemultiple_vraagid_idx` (`vraagid`),
  CONSTRAINT `vraagsimpelemultiple__vraagid` FOREIGN KEY (`vraagid`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `vragensimpelemultiplechoice` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `antwoorden`;
CREATE TABLE `antwoorden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraagid` int NOT NULL,
  `antwoord` varchar(255) DEFAULT NULL,
  `isJuist` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `antwoord_vraagid_idx` (`vraagid`),
  CONSTRAINT `antwoord__vraagid` FOREIGN KEY (`vraagid`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `antwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `quizzen`;
CREATE TABLE `quizzen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) DEFAULT NULL,
  `omschrijving` varchar(255) DEFAULT NULL,
  `isBeschikbaar` tinyint DEFAULT NULL,
  `vakid` int NOT NULL,
  `hoofdstukid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quiz_vakid_idx` (`vakid`),
  KEY `quiz_hoofdstukid_idx` (`hoofdstukid`),
  CONSTRAINT `quiz__vakid` FOREIGN KEY (`vakid`) REFERENCES `vakken` (`id`),
  CONSTRAINT `quiz__hoofdstukid` FOREIGN KEY (`hoofdstukid`) REFERENCES `hoofdstukken` (`id`)
);

LOCK TABLES `quizzen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `quizvragen`;
CREATE TABLE `quizvragen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quizid` int NOT NULL,
  `vraagid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quizvraag_quizid_idx` (`quizid`),
  KEY `quizvraag_vraagid_idx` (`vraagid`),
  CONSTRAINT `quizvraag__quizid` FOREIGN KEY (`quizid`) REFERENCES `quizzen` (`id`),
  CONSTRAINT `quizvraag__vraagid` FOREIGN KEY (`vraagid`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `quizvragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `quizvraagantwoorden`;
CREATE TABLE `quizvraagantwoorden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quizVraagid` int NOT NULL,
  `antwoordid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quizvraagantwoord_quizVraagid_idx` (`quizVraagid`),
  KEY `quizvraagantwoord_antwoordid_idx` (`antwoordid`),
  CONSTRAINT `quizvraagantwoord__antwoordid` FOREIGN KEY (`antwoordid`) REFERENCES `antwoorden` (`id`),
  CONSTRAINT `quizvraagantwoord__quizVraagid` FOREIGN KEY (`quizVraagid`) REFERENCES `quizvragen` (`id`)
);

LOCK TABLES `quizvraagantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `afgenomenquizzen`;
CREATE TABLE `afgenomenquizzen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quizid` int NOT NULL,
  `userid` int NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `score` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `afgenomenquiz_quizid_idx` (`quizid`),
  KEY `afgenomenquiz_userid_idx` (`userid`),
  CONSTRAINT `afgenomenquiz__quizid` FOREIGN KEY (`quizid`) REFERENCES `quizzen` (`id`),
  CONSTRAINT `afgenomenquiz__userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
);

LOCK TABLES `afgenomenquizzen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `afgenomenquizvragen`;
CREATE TABLE `afgenomenquizvragen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `afgenomenQuizid` int NOT NULL,
  `vraagid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `afgenomenvraag_quizid_idx` (`afgenomenQuizid`),
  KEY `afgenomenvraag_vraagid_idx` (`vraagid`),
  CONSTRAINT `afgenomenvraag__afgenomenQuizid` FOREIGN KEY (`afgenomenQuizid`) REFERENCES `afgenomenquizzen` (`id`),
  CONSTRAINT `afgenomenvraag__vraagid` FOREIGN KEY (`vraagid`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `afgenomenquizvragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `afgenomenquizantwoorden`;
CREATE TABLE `afgenomenquizantwoorden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `afgenomenQuizVraagid` int NOT NULL,
  `antwoordid` int NOT NULL,
  `juistBeantwoord` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `afgenomenantwoord_afgenomenQuizVraagid_idx` (`afgenomenQuizVraagid`),
  KEY `afgenomenantwoord_antwoordid_idx` (`antwoordid`),
  CONSTRAINT `afgenomenantwoord__afgenomenQuizVraagid` FOREIGN KEY (`afgenomenQuizVraagid`) REFERENCES `afgenomenquizvragen` (`id`),
  CONSTRAINT `afgenomenantwoord__antwoordid` FOREIGN KEY (`antwoordid`) REFERENCES `antwoorden` (`id`)
);

LOCK TABLES `afgenomenquizantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `beantwoordevragen`;
CREATE TABLE `beantwoordevragen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraagid` int NOT NULL,
  `juistBeantwoord` tinyint DEFAULT NULL,
  `jaarMaand` datetime DEFAULT NULL,
  `isBeantwoord` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `beantwoordevraag_vraagid_idx` (`vraagid`),
  CONSTRAINT `beantwoordevraag__vraagid` FOREIGN KEY (`vraagid`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `beantwoordevragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `gegevenantwoorden`;
CREATE TABLE `gegevenantwoorden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `antwoordid` int NOT NULL,
  `aantalJuistGekozen` int DEFAULT NULL,
  `aantalFoutGekozen` int DEFAULT NULL,
  `jaarMaand` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gegevenantwoord_antwoordid_idx` (`antwoordid`),
  CONSTRAINT `gegevenantwoord__antwoordid` FOREIGN KEY (`antwoordid`) REFERENCES `antwoorden` (`id`)
);

LOCK TABLES `gegevenantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

CREATE user IF NOT EXISTS quiz IDENTIFIED BY 'quiz';
GRANT SELECT,INSERT,UPDATE,DELETE ON * TO quiz;




SET names utf8mb4;
SET charset utf8mb4;
USE `coursequiz`;

LOCK TABLES `users` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  1,
  "Vuurmeester",
  "De koning",
  "admin@ehb.be",
  "r6CyOsIRfBfUvvcdbMnb",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "58442ceedc665c8ef9a8d4a56152f841"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  2,
  "Tom",
  "Verhulst",
  "tom.verhulst@hotmail.com",
  "rghRtk4lZmJLTsFJFBQ8",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "abc12345"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  3,
  "Jos",
  "Dewolf",
  "jos.dewolf@outlook.com",
  "ogIM23NkPlfdxeeGZeX0",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "abc12345"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  4,
  "Lisbeth",
  "de Backer",
  "ldebacker@sky.net",
  "K42TupeM9cnkVRNEm2Fb",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "abc12345"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  5,
  "Joske",
  "De Grappenmaker",
  "docent@ehb.be",
  "GYPHz5hVQwqU06fsWywD",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "1e8ac30a0364f39ce77f56ffa422d06d"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  6,
  "Coby",
  "Potappel-Stroop",
  "student1@ehb.be",
  "zA7qMS1eQAsWTRxj3ITb",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "9b80410f78764ed54c9875c7ecf424fa"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  7,
  "Willie",
  "Wortel",
  "student2@ehb.be",
  "dE1Kwt982BCamiHBa55h",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "8a4cd29df240f66814776f9ed32f421c"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  8,
  "Conny",
  "Plassen",
  "student3@ehb.be",
  "iu9jDatxuDNX92dskZkD",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "c5c01046bb6d67814a8862b28d7e73dc"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  9,
  "Philip",
  "Heynen",
  "student4@ehb.be",
  "M4wWiToDNYuGH430GC4k",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "eff91bdc04f447b62abcf403fde1bb76"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  10,
  "Henny",
  "Spekken-Bonen",
  "student5@ehb.be",
  "SGaX2U9LnTuNnV2KbKtt",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "097cc3d7d94ef8043ec10e55b910e4c3"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  11,
  "Patty",
  "Koot",
  "student6@ehb.be",
  "kt9xj9VwNjESIZk2B8aD",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "538609203034dfe1347bd54068caa045"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  12,
  "Panda",
  "De Haan",
  "student7@ehb.be",
  "8o1RJMmEK6URLiwjqQOI",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "f00c7b42e50e8b66afe9f2d1a1cabc4f"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  13,
  "Lee",
  "King",
  "student8@ehb.be",
  "H3fx8Q06TTvHxc1VWebx",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "26363e4cb9b85f17600cedaf1946fae1"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  14,
  "Peter",
  "Celie",
  "student9@ehb.be",
  "I4BaclPNFS8Vi1I32nsF",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "1c75fb58aabddbe4ad0f652e633cd3cb"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad,
  password
)
VALUES (
  15,
  "Matthias",
  "De Keizer",
  "student10@ehb.be",
  "n6mxIQYrp1gRQPFcFFPy",
  "\\public\\images\\account\\accountinfo\\avatar_default.png",
  "71b8fabd4a435f5adbc57b798232ef88"
);
UNLOCK TABLES;

LOCK TABLES `opleidingen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `opleidingen` (
  `id`,
  `naam`
)
VALUES (
  1,
  "testopleiding"
);
INSERT INTO `opleidingen` (
  `id`,
  `naam`
)
VALUES (
  2,
  "testopleiding2"
);
UNLOCK TABLES;

LOCK TABLES `rollen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
/* admin */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  1,
  3
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  2,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  3,
  1
);
/* docent */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  4,
  2
);
/* docent */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  5,
  2
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  6,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  7,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  8,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  9,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  10,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  11,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  12,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  13,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  14,
  1
);
/* student */
INSERT INTO `rollen` (
  `userid`,
  `rol`
)
VALUES (
  15,
  1
);
UNLOCK TABLES;

LOCK TABLES `vakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `vakken` (
  `id`,
  `naam`,
  `jaar`,
  `opleidingid`
)
VALUES (
  1,
  "testvak numero uno",
  1,
  1
);
INSERT INTO `vakken` (
  `id`,
  `naam`,
  `jaar`,
  `opleidingid`
)
VALUES (
  2,
  ".NET essentials",
  1,
  1
);
UNLOCK TABLES;

LOCK TABLES `docenten` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `docenten` (
  `userid`
)
VALUES (
  4
);
INSERT INTO `docenten` (
  `userid`
)
VALUES (
  5
);
UNLOCK TABLES;

LOCK TABLES `studenten` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  2,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  3,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  6,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  7,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  8,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  9,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  10,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  11,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  12,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  13,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  14,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingid`
)
VALUES (
  15,
  1
);
UNLOCK TABLES;

LOCK TABLES `hoofdstukken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `hoofdstukken` (
  `id`,
  `titel`,
  `nummer`,
  `vakid`
)
VALUES (
  1,
  "vak",
  0.0,
  1
);
INSERT INTO `hoofdstukken` (
  `id`,
  `titel`,
  `nummer`,
  `vakid`
)
VALUES (
  2,
  "vak",
  0.0,
  1
);
UNLOCK TABLES;

LOCK TABLES `studentenvakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `studentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  3,
  1
);
INSERT INTO `studentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  3,
  2
);
INSERT INTO `studentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  2,
  2
);
INSERT INTO `studentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  12,
  2
);
INSERT INTO `studentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  9,
  1
);
INSERT INTO `studentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  1,
  2
);
INSERT INTO `studentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  8,
  2
);
UNLOCK TABLES;

LOCK TABLES `docentenvakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `docentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  4,
  1
);
INSERT INTO `docentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  4,
  2
);
INSERT INTO `docentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  5,
  1
);
INSERT INTO `docentenvakken` (
  `userid`,
  `vakid`
)
VALUES (
  5,
  2
);
UNLOCK TABLES;

LOCK TABLES `vragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `vragen` (
  `id`,
  `vraag`,
  `aantalAntwoordenTonen`,
  `juisteAntwoordTonen`,
  `hoofdstukid`
) 
VALUES (
  1,
  'Welke technieken kunnen gebruikt worden om de cos phi bij inductieve belasting te verbeteren?',
  5,
  true,
  1
);
INSERT INTO `vragen`(
  `id`,
  `vraag`,
  `aantalAntwoordenTonen`,
  `juisteAntwoordTonen`,
  `hoofdstukid`
) 
VALUES (
  2,
  'Hoe heette de eerste amerikaanse man in de ruimte?',
  5,
  true,
  1
);
INSERT INTO `vragen`(
  `id`,
  `vraag`,
  `aantalAntwoordenTonen`,
  `juisteAntwoordTonen`,
  `hoofdstukid`
) 
VALUES (
  3,
  'Wat zei zware Zulien zijn zuster zaliger?',
  5,
  true,
  1
);
INSERT INTO `vragen`(
  `id`,
  `vraag`,
  `aantalAntwoordenTonen`,
  `juisteAntwoordTonen`,
  `hoofdstukid`
) 
VALUES (
  4,
  'welk element zit niet in een menselijke scheet?',
  5,
  true,
  1
);
INSERT INTO `vragen`(
  `id`,
  `vraag`,
  `aantalAntwoordenTonen`,
  `juisteAntwoordTonen`,
  `hoofdstukid`
) 
VALUES (
  5,
  'Welke film werd niet geregisserd door Fritz Lang?',
  5,
  true,
  1
);
UNLOCK TABLES;

LOCK TABLES `vragenmeerderemultiplechoice` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `vragenmeerderemultiplechoice`(
  `id`,
  `vraagid`,
  `aantalJuisteAntwoordenNodig`,
  `minimumAantalJuisteantwoordenTonen`
) 
VALUES (
  1,
  1,
  3,
  3
);
INSERT INTO `vragenmeerderemultiplechoice`(
  `id`,
  `vraagid`,
  `aantalJuisteAntwoordenNodig`,
  `minimumAantalJuisteantwoordenTonen`
) 
VALUES (
  3,
  3,
  3,
  3
);
UNLOCK TABLES;

LOCK TABLES `vragensimpelemultiplechoice` WRITE;
/* INSERT INTO tablename(field1,field3,...) VALUES ('value1','value3',...); */
INSERT INTO `vragensimpelemultiplechoice`(
  `id`,
  `vraagid`
) 
VALUES (
  1,
  3
);
INSERT INTO `vragensimpelemultiplechoice`(
  `id`,
  `vraagid`
) 
VALUES (
  2,
  4
);
INSERT INTO `vragensimpelemultiplechoice`(
  `id`,
  `vraagid`
) 
VALUES (
  3,
  5
);
UNLOCK TABLES;

LOCK TABLES `antwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  1,
  1,
  'elektromotoren',
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  2,
  1,
  'condensatoren',
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  3,
  1,
  'synchrone draaistroommotor',
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  4,
  1,
  'spoelen',
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  5,
  1,
  'weerstanden',
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  6,
  2,
  "Yuri Gagarin",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  7,
  2,
  "John Glen",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  8,
  2,
  'Neil Armstrong',
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  9,
  2,
  "David Bowie",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  10,
  2,
  'Alan Shepard',
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  11,
  3,
  "Zo'n zeveraars!",
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  12,
  3,
  "Ze zijn zijle zeker zot!",
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  13,
  3,
  "Wat doet gij hier?",
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  14,
  3,
  "Ach, wat is het ergste dat kan gebeuren?",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  15,
  3,
  "Wilkommen, bienvenue, welcome.",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  16,
  4,
  "stikstof",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  17,
  4,
  "waterstof",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  18,
  4,
  "koolmonoxide",
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  19,
  4,
  "zuurstof",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  20,
  4,
  "methaan",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  21,
  5,
  "M",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  22,
  5,
  "Metropolis",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  23,
  5,
  "Das Testament des Dr Mabuse",
  false
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  24,
  5,
  "Die Blechtrommel",
  true
);
INSERT INTO `antwoorden`(
  `id`,
  `vraagid`,
  `antwoord`,
  `isJuist`
)
VALUES (
  25,
  5,
  "Die Nibelungen: Siegfried",
  false
);
UNLOCK TABLES;

LOCK TABLES `quizzen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `quizzen` (
  `id`,
  `naam`,
  `omschrijving`,
  `isBeschikbaar`,
  `vakid`,
  `hoofdstukid`
) 
VALUES (
  1,
  "De Demo Quiz",
  "een demoquiz",
  true,
  1,
  1
); 
UNLOCK TABLES;

LOCK TABLES `quizvragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `quizvragen` (
  `id`,
  `quizid`,
  `vraagid`
) 
VALUES (
  1,
  1,
  1
); 
INSERT INTO `quizvragen` (
  `id`,
  `quizid`,
  `vraagid`
) 
VALUES (
  2,
  1,
  2
); 
INSERT INTO `quizvragen` (
  `id`,
  `quizid`,
  `vraagid`
) 
VALUES (
  3,
  1,
  3
); 
INSERT INTO `quizvragen` (
  `id`,
  `quizid`,
  `vraagid`
) 
VALUES (
  4,
  1,
  4
); 
INSERT INTO `quizvragen` (
  `id`,
  `quizid`,
  `vraagid`
) 
VALUES (
  5,
  1,
  5
); 
UNLOCK TABLES;

LOCK TABLES `quizvraagantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `afgenomenquizzen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `afgenomenquizvragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `afgenomenquizantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `beantwoordevragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `gegevenantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;
