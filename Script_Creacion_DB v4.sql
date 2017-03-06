/*==============================================================*/
/* DBMS NAME:      MYSQL 5.0                                    */
/* CREATED ON:     27/02/2017 01:46:27 P.M.                     */
/*==============================================================*/

USE APP_PERFILES;

DROP TABLE IF EXISTS AMIGOS;

DROP TABLE IF EXISTS AUTENTICACION;

DROP TABLE IF EXISTS INTERES_CATEGORIA;

DROP TABLE IF EXISTS CATEGORIA;

DROP TABLE IF EXISTS ROL_PERMISOS;

DROP TABLE IF EXISTS PERMISOS;

DROP TABLE IF EXISTS ROLES;

DROP TABLE IF EXISTS USUARIO_INTERES;

DROP TABLE IF EXISTS INTERES;

DROP TABLE IF EXISTS USUARIO_PERFIL;

DROP TABLE IF EXISTS PERFIL;

DROP TABLE IF EXISTS VISIBILIDAD_INFORMACION;

DROP TABLE IF EXISTS USUARIO;

DROP TABLE IF EXISTS USUARIO_ROL;



/*==============================================================*/
/* TABLE: AMIGOS                                                */
/*==============================================================*/
CREATE TABLE AMIGOS
(
   ID_USUARIO           INT NOT NULL,
   ID_AMIGO             INT NOT NULL,
   ESTATUS_RELACION     INT NOT NULL,
   PRIMARY KEY (ID_USUARIO, ID_AMIGO)
);

/*==============================================================*/
/* TABLE: AUTENTICACION                                         */
/*==============================================================*/
CREATE TABLE AUTENTICACION
(
   ID_USUARIO           INT NOT NULL,   
   CORREO               VARCHAR(80) NOT NULL UNIQUE,
   PASS                 VARCHAR(80) NOT NULL,
   PRIMARY KEY (ID_USUARIO)
);

/*==============================================================*/
/* TABLE: CATEGORIA                                             */
/*==============================================================*/
CREATE TABLE CATEGORIA
(
   ID_CATEGORIA          INT NOT NULL,
   DESCRIPCION_CATEGORIA VARCHAR(1024) NOT NULL,
   FECHA_REG_CATEGORIA   DATETIME NOT NULL,
   PRIMARY KEY (ID_CATEGORIA)
);

/*==============================================================*/
/* TABLE: PERMISOS                                              */
/*==============================================================*/
CREATE TABLE PERMISOS
(
   ID_PERMISO                INT NOT NULL,
   DESCRIPCION_PERMISO       VARCHAR(1024) NOT NULL,
   FECHA_REG_PERMISO         DATETIME NOT NULL,
   PRIMARY KEY (ID_PERMISO)
);

/*==============================================================*/
/* TABLE: INTERES                                               */
/*==============================================================*/
CREATE TABLE INTERES
(
   ID_INTERES           INT NOT NULL,
   DESCRIPCION_INTERES  VARCHAR(1024) NOT NULL,
   FECHA_REG_INTERES    DATETIME NOT NULL,
   PRIMARY KEY (ID_INTERES)
);

/*==============================================================*/
/* TABLE: INTERES_CATEGORIA                                     */
/*==============================================================*/
CREATE TABLE INTERES_CATEGORIA
(
   ID_INTERES           INT NOT NULL,
   ID_CATEGORIA         INT NOT NULL,
   PRIMARY KEY (ID_INTERES, ID_CATEGORIA)
);

/*==============================================================*/
/* TABLE: ROLES                                                 */
/*==============================================================*/
CREATE TABLE ROLES
(
   ID_ROL                INT NOT NULL,
   DESCRIPCION_ROL       VARCHAR(1024) NOT NULL,
   FECHA_REGISTRO_ROL    DATETIME NOT NULL,
   PRIMARY KEY (ID_ROL)
);

/*==============================================================*/
/* TABLE: ROL_PERMISOS                                          */
/*==============================================================*/
CREATE TABLE ROL_PERMISOS
(
   ID_PERMISO           INT NOT NULL, 
   ID_ROL               INT NOT NULL,
   PRIMARY KEY (ID_PERMISO, ID_ROL)
);

/*==============================================================*/
/* TABLE: USUARIO                                               */
/*==============================================================*/
CREATE TABLE USUARIO
(
   ID_USUARIO               INT NOT NULL AUTO_INCREMENT,
   NOMBRE_USUARIO           VARCHAR(1024) NOT NULL,
   APELLIDO_USUARIO         VARCHAR(1024) NOT NULL,
   ALIAS_USUARIO            VARCHAR(1024) NOT NULL,
   SEXO_USUARIO             VARCHAR(1024) NOT NULL,
   TELEFONO_USUARIO         VARCHAR(1024) NOT NULL,
   ACTIVO		    BOOLEAN NOT NULL,
   FECHA_REGISTRO_USUARIO   DATETIME NOT NULL,
   FECHA_NACIMIENTO_USUARIO DATETIME NOT NULL,
   PRIMARY KEY (ID_USUARIO)
);

/*==============================================================*/
/* TABLE: USUARIO_INTERES                                       */
/*==============================================================*/
CREATE TABLE USUARIO_INTERES
(
   ID_INTERES           INT NOT NULL,
   ID_USUARIO           INT NOT NULL,
   PRIMARY KEY (ID_INTERES, ID_USUARIO)
);

/*==============================================================*/
/* TABLE: VISIBILIDAD_INFORMACION                               */
/*==============================================================*/
CREATE TABLE VISIBILIDAD_INFORMACION
(
   ID_USUARIO           INT NOT NULL,
   VIS_ALIAS            INT,
   VIS_CORREO           INT,
   VIS_SEXO             INT,
   VIS_TELEFONO         INT,
   VIS_FECHA_NAC        INT,
   PRIMARY KEY (ID_USUARIO)
);

/*==============================================================*/
/* TABLE: USUARIO_ROL                                           */
/*==============================================================*/
CREATE TABLE USUARIO_ROL
(
   ID_ROL               INT NOT NULL,
   ID_USUARIO           INT NOT NULL,
   PRIMARY KEY (ID_ROL, ID_USUARIO)
);

/* Relación Amigos y Usuario */
ALTER TABLE AMIGOS ADD CONSTRAINT FK_RELATIONSHIP_13 FOREIGN KEY (ID_USUARIO)
      REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE AMIGOS ADD CONSTRAINT FK_RELATIONSHIP_14 FOREIGN KEY (ID_AMIGO)
      REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE ON UPDATE CASCADE;
/* Relación Autenticación y Usuario */
ALTER TABLE AUTENTICACION ADD CONSTRAINT FK_RELATIONSHIP_1 FOREIGN KEY (ID_USUARIO)
      REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE ON UPDATE CASCADE;
/* Relación Interés y Categoría */
ALTER TABLE INTERES_CATEGORIA ADD CONSTRAINT FK_RELATIONSHIP_11 FOREIGN KEY (ID_CATEGORIA)
      REFERENCES CATEGORIA (ID_CATEGORIA) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE INTERES_CATEGORIA ADD CONSTRAINT FK_RELATIONSHIP_12 FOREIGN KEY (ID_INTERES)
      REFERENCES INTERES (ID_INTERES) ON DELETE RESTRICT ON UPDATE RESTRICT;
/* Relación Roles y Permisos */
ALTER TABLE ROL_PERMISOS ADD CONSTRAINT FK_RELATIONSHIP_7 FOREIGN KEY (ID_ROL)
      REFERENCES ROLES (ID_ROL) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE ROL_PERMISOS ADD CONSTRAINT FK_RELATIONSHIP_8 FOREIGN KEY (ID_PERMISO)
      REFERENCES PERMISOS (ID_PERMISO) ON DELETE RESTRICT ON UPDATE RESTRICT;
/* Relación Usuario e Interés */
ALTER TABLE USUARIO_INTERES ADD CONSTRAINT FK_RELATIONSHIP_16 FOREIGN KEY (ID_USUARIO)
      REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE USUARIO_INTERES ADD CONSTRAINT FK_RELATIONSHIP_17 FOREIGN KEY (ID_INTERES)
      REFERENCES INTERES (ID_INTERES) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE VISIBILIDAD_INFORMACION ADD CONSTRAINT FK_RELATIONSHIP_3 FOREIGN KEY (ID_USUARIO)
      REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE ON UPDATE CASCADE;
/* Relación Usuario y Roles */
ALTER TABLE USUARIO_ROL ADD CONSTRAINT FK_RELATIONSHIP_18 FOREIGN KEY (ID_ROL)
      REFERENCES ROLES (ID_ROL) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE USUARIO_ROL ADD CONSTRAINT FK_RELATIONSHIP_19 FOREIGN KEY (ID_USUARIO)
      REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE ON UPDATE CASCADE;
	  

INSERT INTO `APP_PERFILES`.`ROLES`
(`ID_ROL`,
`DESCRIPCION_ROL`,
`FECHA_REGISTRO_ROL`)
VALUES
(1,'ADMINISTRADOR',NOW()),(2,'USUARIO',NOW()),(3,'USUARIO VIP',NOW()) ;


INSERT INTO `APP_PERFILES`.`PERMISOS`
(`ID_PERMISO`,
`DESCRIPCION_PERMISO`,
`FECHA_REG_PERMISO`)
VALUES
(1,'MODIFICAR PERFIL PROPIO',NOW()),(2,'MODIFICAR PERFIL EXTERNO',NOW()),(3,'ELIMINAR PERFIL',NOW()),
(4,'BUSCAR USUARIO',NOW()),(5,'VER SUGERENCIAS AMIGOS',NOW()),(6,'VER SUGERENCIAS AMIGOS VIP',NOW()), (7,'ENVIAR SOLICITUD AMISTAD',NOW()),
(8,'CANCELAR SOLICITUD AMISTAD',NOW()),(9,'ELIMINAR AMIGO',NOW()),(10,'BLOQUEAR AMIGO',NOW()),(11,'BLOQUEAR USUARIO',NOW()),
(12,'ELIMINAR USUARIO',NOW());


INSERT INTO `APP_PERFILES`.`ROL_PERMISOS`
(`ID_PERMISO`,
`ID_ROL`)
VALUES
(1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(1,2),(3,2),(5,2),(7,2),(9,2),(10,2),(1,3),(3,3),(5,3),
(6,3),(7,3),(8,3),(9,3),(10,3);


INSERT INTO `APP_PERFILES`.`INTERES`
(`ID_INTERES`,
`DESCRIPCION_INTERES`,
`FECHA_REG_INTERES`)
VALUES
(1,'DESARROLLO DE SOFTWARE',NOW()),(2,'IDIOMAS',NOW()),(3,'FUTBOL',NOW()),(4,'NATACION',NOW()),(5,'TENIS',NOW()),(6,'AUTOMOVILISMO',NOW()),
(7,'CICLISMO',NOW()),(8,'TROPICAL',NOW()),(9,'HARD ROCK',NOW()),(10,'JAZZ',NOW()),(11,'CLASICA',NOW());

INSERT INTO `APP_PERFILES`.`CATEGORIA`
(`ID_CATEGORIA`,
`DESCRIPCION_CATEGORIA`,
`FECHA_REG_CATEGORIA`)
VALUES
(1,'TRABAJO',NOW()),
(2,'NEGOCIOS',NOW()),
(3,'DEPORTES',NOW()),
(4,'MUSICA',NOW());

INSERT INTO `APP_PERFILES`.`INTERES_CATEGORIA`
(`ID_INTERES`,
`ID_CATEGORIA`)
VALUES
(1,1),(2,2),(3,3),(4,3),(5,3),(6,3),(7,3),(8,4),(9,4),(10,4),(11,4);


INSERT INTO `APP_PERFILES`.`USUARIO`
(`NOMBRE_USUARIO`,
`APELLIDO_USUARIO`,
`ALIAS_USUARIO`,
`SEXO_USUARIO`,
`TELEFONO_USUARIO`,
`ACTIVO`,
`FECHA_REGISTRO_USUARIO`,
`FECHA_NACIMIENTO_USUARIO`
)
VALUES
('ADMINISTRADOR','ADMIN','ADMIN','M','2112233',1,NOW(),NOW()),
('Camilo','Beltran','Drk','M','2112233',1,NOW(),NOW()),
('Andrea','Giraldo','Wipo','F','2112233',1,NOW(),NOW()),
('Breyner','Garzón','Kratos','M','2112233',1,NOW(),NOW()),
('Esteban','Rodriguez','Mutsurini','M','2112233',1,NOW(),NOW()),
('Diana','Sanchez','Dnx','F','2112233',1,NOW(),NOW()),
('Carolina','Cely','caro','F','2112233',1,NOW(),NOW()),
('Erika','Merchan','Eri','F','2112233',1,NOW(),NOW()),
('Sebastian','Layos','layos','M','2112233',1,NOW(),NOW()),
('Jesús','Ucrox','ucrox','M','2112233',1,NOW(),NOW()),
('Maria José','Villamil','Mariajo','F','2112233',1,NOW(),NOW()),
('Hector','Torres','hector','M','2112233',1,NOW(),NOW()),
('William','Cobos','Memo','M','2112233',1,NOW(),NOW()),
('Aura','Garcia','Aura','F','2112233',1,NOW(),NOW()),
('José','Martinez','jose','M','2112233',1,NOW(),NOW()),
('Alejandra','Barragan','aleja','F','2112233',1,NOW(),NOW()),
('Juan David','Beltrán','Juanda','M','2112233',1,NOW(),NOW());


INSERT INTO `APP_PERFILES`.`AUTENTICACION`
(`ID_USUARIO`,
`PASS`,
`CORREO`)
VALUES
(1,'123456','ADMIN@ADMIN.COM'),
(2,'123456','draiko16@gmail.com'),
(3,'123456','wipo@gmail.com'),
(4,'123456','kratos@gmail.com'),
(5,'123456','mutsurini@gmail.com.co'),
(6,'123456','dnx@gmail.com'),
(7,'123456','caro@gmail.com'),
(8,'123456','eri@gmail.com'),
(9,'123456','layos@gmail.com'),
(10,'123456','ucrox@gmail.com.ar'),
(11,'123456','mariajo@gmail.com'),
(12,'123456','hector@gmail.com'),
(13,'123456','memow@gmail.com'),
(14,'123456','aruag@gmail.com'),
(15,'123456','jose@gmail.com'),
(16,'123456','alejab@gmail.com'),
(17,'123456','juanda@gmail.com.es');

INSERT INTO `APP_PERFILES`.`VISIBILIDAD_INFORMACION`
(`ID_USUARIO`,
`VIS_ALIAS`,
`VIS_CORREO`,
`VIS_SEXO`,
`VIS_TELEFONO`,
`VIS_FECHA_NAC`)
VALUES
(1,1,1,1,1,1),
(2,1,1,1,1,1),
(3,1,1,1,1,1),
(4,1,1,1,1,1),
(5,1,1,1,1,1),
(6,1,1,1,1,1),
(7,1,1,1,1,1),
(8,1,1,1,1,1),
(9,1,1,1,1,1),
(10,1,1,1,1,1),
(11,1,1,1,1,1),
(12,1,1,1,1,1),
(13,1,1,1,1,1),
(14,1,1,1,1,1),
(15,1,1,1,1,1),
(16,1,1,1,1,1),
(17,1,1,1,1,1);

INSERT INTO APP_PERFILES.AMIGOS
(`ID_USUARIO`, `ID_AMIGO`, `ESTATUS_RELACION`) 
VALUES
(1, 3, 1),
(3, 1, 1),
(2, 5, 1),
(5, 2, 1),
(8, 9, 1),
(9, 8, 2),
(1, 5, 1),
(5, 1, 1),
(5, 8, 1),
(8, 5, 2),
(6, 2, 1),
(2, 6, 1),
(13, 5, 1),
(5, 13, 1),
(4, 14, 1),
(14, 4, 2),
(6, 13, 1),
(13, 6, 1),
(17, 2, 1),
(2, 17, 1),
(16, 11, 1),
(11, 16, 1),
(7, 10, 1),
(10, 7, 1),
(10, 6, 1),
(6, 10, 1),
(7, 9, 1),
(9, 7, 1),
(12, 3, 1),
(3, 12, 1),
(12, 5, 1),
(5, 12, 1),
(4, 3, 1),
(3, 4, 1),
(17, 3, 1),
(3, 17, 1);
