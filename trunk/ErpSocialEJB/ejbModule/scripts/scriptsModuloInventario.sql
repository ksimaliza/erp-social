/*SCRIPTS PARA EL MODULO DE INVENTARIOS*/

/*Agregar columnas faltantes tabla proveedor*/
-- Email
alter table proveedor
add column prov_email  VARCHAR(50) null;

-- Cedula o Ruc 
alter table proveedor
add column prov_documento_identificacion  VARCHAR(20) not null default '0000000000';

-- Email
alter table proveedor
add column prov_notas  VARCHAR(250) null;

-- Columnas y relacion catalogo pais ciudad
alter table proveedor
add column cab_catalogo_pais_cuidad VARCHAR(5) null;

alter table proveedor
add column det_catalogo_pais_cuidad VARCHAR(5) null;

alter table proveedor
add constraint FK_PROVEEDOR_REFERENCE_DETALLE foreign key (cab_catalogo_pais_cuidad, det_catalogo_pais_cuidad)
references DETALLE_CATALOGO_TBL (CAB_CATALOGO_FK, DET_CATALOGO_NIVEL1)
on delete restrict on update restrict;

/*Agregar columnas faltantes tabla bien*/
-- Notas
alter table bien_tbl
add column bie_notas  VARCHAR(500) null;

-- crear cabecera catalogo tipo ingreso
INSERT INTO cabecera_catalogo_tbl
(cab_catalogo_pk, cab_catalogo_descripcion) VALUES 
('TIING', 'Tipo de ingreso, módulo de inventarios');

-- crear detalle catalolo tipo de ingreso
INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TIING', 'ADJUD', 'Tipo ingreso adjudicacion', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TIING', 'DONAC', 'Tipo ingreso donación', '1');

-- crear cabecera catalogo pais ecuador
INSERT INTO cabecera_catalogo_tbl
(cab_catalogo_pk, cab_catalogo_descripcion) VALUES 
('TCECU', 'País Ecuador');

-- crear detalle catalolo tipo de ingreso
INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'ESMER', 'Esmeraldas', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'TULCA', 'Tulcán', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'IBARR', 'Ibarra', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'NULOJ', 'Nueva Loja', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'PORTO', 'Portoviejo', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'SANDO', 'Santo Domingo', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'QUITO', 'Quito', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'TENA', 'Tena', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'FRAOR', 'Francisco de Orellana', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'GUAYA', 'Guayaquil', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'BABAH', 'Babahoyo', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'LATAC', 'Latacunga', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'GUARA', 'Guaranda', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'AMBAT', 'Ambato', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'PUYO', 'Puyo', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'RIOBA', 'Riobamba', '1');

--
INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'MACAS', 'Macas', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'SANEL', 'Santa Elena', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'AZOGU', 'Azogues', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'CUENC', 'Cuenca', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'MACHA', 'Machala', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'LOJA', 'Loja', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'ZAMOR', 'Zamora', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'PBAMO', 'Puerto Baquerizo Moreno', '1');


--Tipo del bien: Ingresado, asignado, reasignado, devuelto se manejar dentro de un cat&acute;logo
-- Cabecera catalogo
INSERT INTO cabecera_bien_tbl(
cab_bien_pk, cab_bien_descripcion, cab_bien_archivo) VALUES 
('TIBIE', 'Tipo del bien: Ingresado, asignado, reasignado, devuelto.', 'N/A');

-- Detalle catalogo
INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBIE', 'INGRE', 'Ingresado', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBIE', 'ASIGN', 'Asignado', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBIE', 'REASI', 'Reasignado', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBIE', 'DEVUE', 'Devuelto', '1');
    
---- Estado del bien: Se definira como activo, inactivo dentro de un catalogo.
---- Cabecera catalogo
--INSERT INTO cabecera_bien_tbl(
--cab_bien_pk, cab_bien_descripcion, cab_bien_archivo) VALUES 
--('ESBIE', 'Estado del bien: Se definira como activo, inactivo', 'N/A');
--
---- Detalle catalogo
--INSERT INTO detalle_bien_tbl(
--            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
--    VALUES ('ESBIE', 'ACTIV', 'Activo', '1');
--
--INSERT INTO detalle_bien_tbl(
--            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
--    VALUES ('ESBIE', 'INACT', 'Inactivo', '1');
    
-- Estado de Conservacion.- Se manejara dentro de una catalogo bueno, malo, regular.
-- Cabecera catalogo
INSERT INTO cabecera_bien_tbl(
cab_bien_pk, cab_bien_descripcion, cab_bien_archivo) VALUES 
('ESCON', 'Estado de Conservacion.- Bueno, malo, regular', 'N/A');

-- Detalle catalogo
INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('ESCON', 'BUENO', 'Bueno', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('ESCON', 'MALO', 'Malo', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('ESCON', 'REGUL', 'Regular', '1');

/*==============================================================*/
/* Table: CATEGORIA_BIEN_TBL                                    */
/*==============================================================*/
create table CATEGORIA_BIEN_TBL (
   CAT_BIEN_PK          SERIAL               not null,
   CAT_BIEN_NOMBRE      VARCHAR(50)          not null,
   CAT_BIEN_DESCRIPCION VARCHAR(200)         null,
   CAT_BIEN_ESTADO      VARCHAR(2)           null,
   constraint PK_CATEGORIA_BIEN_TBL primary key (CAT_BIEN_PK)
);

/*==============================================================*/
/* Table: LINEA_BIEN_TBL                                        */
/*==============================================================*/
create table LINEA_BIEN_TBL (
   LIN_BIEN_PK          SERIAL               not null,
   CAT_BIEN_PK          INT4                 not null,
   LIN_BIEN_NOMBRE      VARCHAR(50)          not null,
   LIN_BIEN_DESCRIPCION VARCHAR(200)         null,
   LIN_BIEN_ESTADO      VARCHAR(2)           not null,
   constraint PK_LINEA_BIEN_TBL primary key (LIN_BIEN_PK, CAT_BIEN_PK)
);

alter table LINEA_BIEN_TBL
   add constraint FK_LINEA_BI_REFERENCE_CATEGORI foreign key (CAT_BIEN_PK)
      references CATEGORIA_BIEN_TBL (CAT_BIEN_PK)
      on delete restrict on update restrict;

/*==============================================================*/
/* Table: MARCA_BIEN_TBL                                        */
/*==============================================================*/
create table MARCA_BIEN_TBL (
   MAR_BIEN_PK          SERIAL               not null,
   MAR_BIEN_NOMBRE      VARCHAR(50)          not null,
   MAR_BIEN_DESCRIPCION VARCHAR(200)         null,
   MAR_BIEN_ESTADO      VARCHAR(2)           not null,
   constraint PK_MARCA_BIEN_TBL primary key (MAR_BIEN_PK)
);

/*==============================================================*/
/* alter: BIEN_TBL                                    */
/*==============================================================*/

alter table bien_tbl
drop column cab_bien_est_fk;

alter table bien_tbl
drop column det_bien_est_nivel1;

alter table bien_tbl
drop column cab_bien_tip_bie_fk, 
drop column det_bien_tip_bie_nivel1,
drop column cab_bien_est_conserv_fk,
drop column det_bien_est_conserv_nivel1_fk;

alter table BIEN_TBL
add column CAT_BIEN_PK INT4 null;

alter table BIEN_TBL
add column LIN_BIEN_PK INT4 null;

alter table BIEN_TBL
add column MAR_BIEN_PK INT4 null;

ALTER TABLE BIEN_TBL
ADD COLUMN EMR_PK INT4 null;

alter table bien_tbl
add column bie_codigo varchar (50) null;

alter table BIEN_TBL
   add constraint FK_BIEN_TBL_REFERENCE_LINEA_BI foreign key (LIN_BIEN_PK, CAT_BIEN_PK)
      references LINEA_BIEN_TBL (LIN_BIEN_PK, CAT_BIEN_PK)
      on delete restrict on update restrict;

alter table BIEN_TBL
   add constraint FK_BIEN_TBL_REFERENCE_MARCA_BI foreign key (MAR_BIEN_PK)
      references MARCA_BIEN_TBL (MAR_BIEN_PK)
      on delete restrict on update restrict;
      
alter table BIEN_TBL
   add constraint FK_BIEN_TBL_REFERENCE_EMPRESA_ foreign key (EMR_PK)
      references EMPRESA_TBL (EMR_PK)
      on delete restrict on update restrict;

/*==============================================================*/
/* alter: TRANSACCION_TBL                                    */
/*==============================================================*/
ALTER TABLE TRANSACCION_TBL
ADD COLUMN TRA_FECHA_INICIO TIMESTAMP NULL;

ALTER TABLE TRANSACCION_TBL
ADD COLUMN TRA_FECHA_FIN TIMESTAMP NULL;

ALTER TABLE TRANSACCION_TBL
ADD COLUMN CAB_BIEN_TIP_BIE_FK  VARCHAR(5)           null,
ADD COLUMN DET_BIEN_TIP_BIE_NIVEL1 VARCHAR(5)           null,
ADD COLUMN CAB_BIEN_EST_CONSERV_FK VARCHAR(5)           null,
ADD COLUMN DET_BIEN_EST_CONSERV_NIVEL1_FK VARCHAR(5)           null;

alter table TRANSACCION_TBL
   add constraint FK_BIEN_TBL_RELATIONS_DET_BIE foreign key (CAB_BIEN_TIP_BIE_FK, DET_BIEN_TIP_BIE_NIVEL1)
      references DETALLE_BIEN_TBL (CAB_BIEN_FK, DET_BIEN_NIVEL1)
      on delete restrict on update restrict;

alter table TRANSACCION_TBL
   add constraint FK_BIEN_TBL_RELATIONS_DET_CON foreign key (CAB_BIEN_EST_CONSERV_FK, DET_BIEN_EST_CONSERV_NIVEL1_FK)
      references DETALLE_BIEN_TBL (CAB_BIEN_FK, DET_BIEN_NIVEL1)
      on delete restrict on update restrict;
      

alter table transaccion_tbl
add column emp_asignado_fk integer null;

alter table transaccion_tbl
add column emp_reasignado_fk integer null;

alter table transaccion_tbl
   add constraint FK_TRANSACCION_TBL_REF_EMPLEADO_ASIG foreign key (emp_asignado_fk)
      references empleado_tbl (emp_pk)
      on delete restrict on update restrict;
      
alter table transaccion_tbl
   add constraint FK_TRANSACCION_TBL_REF_EMPLEADO_REASIG foreign key (emp_reasignado_fk)
      references empleado_tbl (emp_pk)
      on delete restrict on update restrict;


/*==============================================================*/
/* Vista bien                                  */
/*==============================================================*/
create view bien_view as
select bie_pk, emr_pk, 
bie_nombre, bie_modelo, bie_color, bie_costo_venta, bie_fecha_asig, bie_ubicacion, bie_notas, bie_estado,
b.cat_bien_pk, cb.cat_bien_descripcion, cb.cat_bien_estado,
b.lin_bien_pk, lb.lin_bien_descripcion, lb.lin_bien_estado,
b.mar_bien_pk, mb.mar_bien_descripcion, mb.mar_bien_estado,
t.cab_bien_tip_bie_fk, t.det_bien_tip_bie_nivel1, t.cab_bien_est_conserv_fk, t.det_bien_est_conserv_nivel1_fk
from bien_tbl b
inner join categoria_bien_tbl cb on cb.cat_bien_pk=b.cat_bien_pk
inner join linea_bien_tbl lb on lb.lin_bien_pk = b.lin_bien_pk and lb.cat_bien_pk = cb.cat_bien_pk
inner join marca_bien_tbl mb on mb.mar_bien_pk = b.mar_bien_pk
left join transaccion_tbl t on t.bie_fk = b.bie_pk;