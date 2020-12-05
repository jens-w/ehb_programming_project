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
  PRIMARY KEY (`id`)
);

LOCK TABLES `users` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad
)
VALUES (
  1,
  "Admin",
  "Admin",
  "admin@localhost",
  "abc123",
  "\\public\\images\\account\\accountinfo\\avatar_default.png"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad
)
VALUES (
  2,
  "Tom",
  "Verhulst",
  "tom.verhulst@hotmail.com",
  "abc123",
  "\\public\\images\\account\\accountinfo\\avatar_default.png"
);
INSERT INTO `users` (
  id,
  voornaam,
  familienaam,
  email,
  userkey,
  avatarpad
)
VALUES (
  3,
  "Jos",
  "Dewolf",
  "jos.dewolf@outlook.com",
  "def456",
  "\\public\\images\\account\\accountinfo\\avatar_default.png"
);
UNLOCK TABLES;

DROP TABLE IF EXISTS `opleidingen`;
CREATE TABLE `opleidingen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

LOCK TABLES `opleidingen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
INSERT INTO `opleidingen` (
  `id`,
  `naam`
)
VALUES (
  1,
  "testopleiding"
);
UNLOCK TABLES;




/***
    create tables that depend on other tables existing
***/

DROP TABLE IF EXISTS `rollen`;
CREATE TABLE `rollen` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `rol_userId_idx` (`userId`),
  CONSTRAINT `rol__userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
);

LOCK TABLES `rollen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `vakken`;
CREATE TABLE `vakken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) DEFAULT NULL,
  `jaar` int DEFAULT NULL,
  `opleidingId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vak_opleidingId_idx` (`opleidingId`),
  CONSTRAINT `vak__opleidingId` FOREIGN KEY (`opleidingId`) REFERENCES `opleidingen` (`id`)
);

LOCK TABLES `vakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `docenten`;
CREATE TABLE `docenten` (
  `userId` int NOT NULL,
  PRIMARY KEY (`userId`),
  KEY `docent_userId_idx` (`userId`),
  CONSTRAINT `docent__userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
);

LOCK TABLES `docenten` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `studenten`;
CREATE TABLE `studenten` (
  `userId` int NOT NULL,
  `opleidingId` int NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `student_userId_idx` (`userId`),
  KEY `student_opleidingId_idx` (`opleidingId`),
  CONSTRAINT `student__opleidingId` FOREIGN KEY (`opleidingId`) REFERENCES `opleidingen` (`id`),
  CONSTRAINT `student__userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
);

LOCK TABLES `studenten` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
INSERT INTO `studenten` (
  `userId`,
  `opleidingId`
)
VALUES (
  2,
  1
);
INSERT INTO `studenten` (
  `userId`,
  `opleidingId`
)
VALUES (
  3,
  1
);
UNLOCK TABLES;

DROP TABLE IF EXISTS `hoofdstukken`;
CREATE TABLE `hoofdstukken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titel` varchar(255) DEFAULT NULL,
  `nummer` float DEFAULT NULL,
  `vakId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hoofdstuk_vakId_idx` (`vakId`),
  CONSTRAINT `hoofdstuk__vakId` FOREIGN KEY (`vakId`) REFERENCES `vakken` (`id`)
);

LOCK TABLES `hoofdstukken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `studentenvakken`;
CREATE TABLE `studentenvakken` (
  `userId` int NOT NULL,
  `vakId` int NOT NULL,
  KEY `studentenvak_userId_idx` (`userId`),
  KEY `studentenvak_vakId_idx` (`vakId`),
  PRIMARY KEY (userId, vakId),
  CONSTRAINT `studentenvak__userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `studentenvak__vakId` FOREIGN KEY (`vakId`) REFERENCES `vakken` (`id`)
);

LOCK TABLES `studentenvakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `docentenvakken`;
CREATE TABLE `docentenvakken` (
  `userId` int NOT NULL,
  `vakId` int NOT NULL,
  KEY `docentenvak_userId_idx` (`userId`),
  KEY `docentenvak_vakId_idx` (`vakId`),
  PRIMARY KEY (userId, vakId),
  CONSTRAINT `docentenvak__userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `docentenvak__vakId` FOREIGN KEY (`vakId`) REFERENCES `vakken` (`id`)
);

LOCK TABLES `docentenvakken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `docentenvakken`;
CREATE TABLE `docentenvakken` (
  `userId` int NOT NULL,
  `vakId` int NOT NULL,
  KEY `docentenvak_userId_idx` (`userId`),
  KEY `docentenvak_vakId_idx` (`vakId`),
  PRIMARY KEY (userId, vakId),
  CONSTRAINT `docentenvak__userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `docentenvak__vakId` FOREIGN KEY (`vakId`) REFERENCES `vakken` (`id`)
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
  `hoofdstukId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vraag_hoofdstukId_idx` (`hoofdstukId`),
  CONSTRAINT `vraag__hoofdstukId` FOREIGN KEY (`hoofdstukId`) REFERENCES `hoofdstukken` (`id`)
);

LOCK TABLES `vragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `vragenmeerderemultiplechoice`;
CREATE TABLE `vragenmeerderemultiplechoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraagId` int DEFAULT NULL,
  `aantalJuisteAntwoordenNodig` int DEFAULT NULL,
  `minimumAantalJuisteAntwoordenTonen` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vraagmeerderemultiple_vraagId_idx` (`vraagId`),
  CONSTRAINT `vraagmeerderemultiple__vraagId` FOREIGN KEY (`vraagId`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `vragenmeerderemultiplechoice` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `vragensimpelemultiplechoice`;
CREATE TABLE `vragensimpelemultiplechoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraagId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vraagsimpelemultiple_vraagId_idx` (`vraagId`),
  CONSTRAINT `vraagsimpelemultiple__vraagId` FOREIGN KEY (`vraagId`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `vragensimpelemultiplechoice` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `antwoorden`;
CREATE TABLE `antwoorden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraagId` int NOT NULL,
  `antwoord` varchar(255) DEFAULT NULL,
  `isJuist` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `antwoord_vraagId_idx` (`vraagId`),
  CONSTRAINT `antwoord__vraagId` FOREIGN KEY (`vraagId`) REFERENCES `vragen` (`id`)
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
  `vakId` int NOT NULL,
  `hoofdstukId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quiz_vakId_idx` (`vakId`),
  KEY `quiz_hoofdstukId_idx` (`hoofdstukId`),
  CONSTRAINT `quiz__vakId` FOREIGN KEY (`vakId`) REFERENCES `vakken` (`id`),
  CONSTRAINT `quiz__hoofdstukId` FOREIGN KEY (`hoofdstukId`) REFERENCES `hoofdstukken` (`id`)
);

LOCK TABLES `quizzen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `quizvragen`;
CREATE TABLE `quizvragen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quizId` int NOT NULL,
  `vraagId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quizvraag_quizId_idx` (`quizId`),
  KEY `quizvraag_vraagId_idx` (`vraagId`),
  CONSTRAINT `quizvraag__quizId` FOREIGN KEY (`quizId`) REFERENCES `quizzen` (`id`),
  CONSTRAINT `quizvraag__vraagId` FOREIGN KEY (`vraagId`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `quizvragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `quizvraagantwoorden`;
CREATE TABLE `quizvraagantwoorden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quizVraagId` int NOT NULL,
  `antwoordId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `quizvraagantwoord_quizVraagId_idx` (`quizVraagId`),
  KEY `quizvraagantwoord_antwoordId_idx` (`antwoordId`),
  CONSTRAINT `quizvraagantwoord__antwoordId` FOREIGN KEY (`antwoordId`) REFERENCES `antwoorden` (`id`),
  CONSTRAINT `quizvraagantwoord__quizVraagId` FOREIGN KEY (`quizVraagId`) REFERENCES `quizvragen` (`id`)
);

LOCK TABLES `quizvraagantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `afgenomenquizzen`;
CREATE TABLE `afgenomenquizzen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quizId` int NOT NULL,
  `userId` int NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `score` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `afgenomenquiz_quizId_idx` (`quizId`),
  KEY `afgenomenquiz_userId_idx` (`userId`),
  CONSTRAINT `afgenomenquiz__quizId` FOREIGN KEY (`quizId`) REFERENCES `quizzen` (`id`),
  CONSTRAINT `afgenomenquiz__userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
);

LOCK TABLES `afgenomenquizzen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `afgenomenquizvragen`;
CREATE TABLE `afgenomenquizvragen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `afgenomenQuizId` int NOT NULL,
  `vraagId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `afgenomenvraag_quizId_idx` (`afgenomenQuizId`),
  KEY `afgenomenvraag_vraagId_idx` (`vraagId`),
  CONSTRAINT `afgenomenvraag__afgenomenQuizId` FOREIGN KEY (`afgenomenQuizId`) REFERENCES `afgenomenquizzen` (`id`),
  CONSTRAINT `afgenomenvraag__vraagId` FOREIGN KEY (`vraagId`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `afgenomenquizvragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;


DROP TABLE IF EXISTS `afgenomenquizantwoorden`;
CREATE TABLE `afgenomenquizantwoorden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `afgenomenQuizVraagId` int NOT NULL,
  `antwoordId` int NOT NULL,
  `juistBeantwoord` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `afgenomenantwoord_afgenomenQuizVraagId_idx` (`afgenomenQuizVraagId`),
  KEY `afgenomenantwoord_antwoordId_idx` (`antwoordId`),
  CONSTRAINT `afgenomenantwoord__afgenomenQuizVraagId` FOREIGN KEY (`afgenomenQuizVraagId`) REFERENCES `afgenomenquizvragen` (`id`),
  CONSTRAINT `afgenomenantwoord__antwoordId` FOREIGN KEY (`antwoordId`) REFERENCES `antwoorden` (`id`)
);

LOCK TABLES `afgenomenquizantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `beantwoordevragen`;
CREATE TABLE `beantwoordevragen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vraagId` int NOT NULL,
  `juistBeantwoord` tinyint DEFAULT NULL,
  `jaarMaand` datetime DEFAULT NULL,
  `isBeantwoord` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `beantwoordevraag_vraagId_idx` (`vraagId`),
  CONSTRAINT `beantwoordevraag__vraagId` FOREIGN KEY (`vraagId`) REFERENCES `vragen` (`id`)
);

LOCK TABLES `beantwoordevragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

DROP TABLE IF EXISTS `gegevenantwoorden`;
CREATE TABLE `gegevenantwoorden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `antwoordId` int NOT NULL,
  `aantalJuistGekozen` int DEFAULT NULL,
  `aantalFoutGekozen` int DEFAULT NULL,
  `jaarMaand` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gegevenantwoord_antwoordId_idx` (`antwoordId`),
  CONSTRAINT `gegevenantwoord__antwoordId` FOREIGN KEY (`antwoordId`) REFERENCES `antwoorden` (`id`)
);

LOCK TABLES `gegevenantwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...);*/
UNLOCK TABLES;

CREATE user IF NOT EXISTS quiz IDENTIFIED BY 'quiz';
GRANT SELECT,INSERT,UPDATE,DELETE ON * TO quiz;
