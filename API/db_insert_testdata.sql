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
  "Admin",
  "Admin",
  "admin@localhost",
  "r6CyOsIRfBfUvvcdbMnb",
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
  `opleidingId`
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
UNLOCK TABLES;

LOCK TABLES `studenten` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
INSERT INTO `studenten` (
  `userid`,
  `opleidingId`
)
VALUES (
  2,
  1
);
INSERT INTO `studenten` (
  `userid`,
  `opleidingId`
)
VALUES (
  3,
  1
);
UNLOCK TABLES;

LOCK TABLES `hoofdstukken` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
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
UNLOCK TABLES;

LOCK TABLES `vragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `vragenmeerderemultiplechoice` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `vragensimpelemultiplechoice` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `antwoorden` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `quizzen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
UNLOCK TABLES;

LOCK TABLES `quizvragen` WRITE;
/* INSERT INTO tablename(field1,field2,...) VALUES ('value1','value2',...); */
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
