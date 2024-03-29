/*SCRIPTS PARA EL MODULO DE INVENTARIOS*/

---------------------------------INICIO---------------------------------
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
---------------------------------FIN---------------------------------

---------------------------------INICIO---------------------------------
/*Agregar columnas faltantes tabla bien*/
-- Notas
alter table bien_tbl
add column bie_notas  VARCHAR(500) null;
---------------------------------FIN---------------------------------

/*Catalogos*/
---------------------------------INICIO---------------------------------
-- crear cabecera catalogo tipo ingreso
INSERT INTO cabecera_catalogo_tbl
(cab_catalogo_pk, cab_catalogo_descripcion) VALUES 
('TIING', 'Tipo de ingreso, m�dulo de inventarios');

-- crear detalle catalolo tipo de ingreso
INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TIING', 'ADJUD', 'Tipo ingreso adjudicacion', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TIING', 'DONAC', 'Tipo ingreso donaci�n', '1');

-- crear cabecera catalogo pais ecuador
INSERT INTO cabecera_catalogo_tbl
(cab_catalogo_pk, cab_catalogo_descripcion) VALUES 
('TCECU', 'Pa�s Ecuador');

-- crear detalle catalolo tipo de ingreso
INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'ESMER', 'Esmeraldas', '1');

INSERT INTO detalle_catalogo_tbl
(cab_catalogo_fk, det_catalogo_nivel1, det_catalogo_descripcion, det_catalogo_estado) VALUES 
('TCECU', 'TULCA', 'Tulc�n', '1');

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
('TIBIE', 'Tipo del bien: Ingresado, asignado, reasignado, devuelto. baja', 'N/A');

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
    
INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBIE', 'BAJA', 'Baja', '1');
    
-- Tipo ingreso: Se definira como donacion o compra dentro de un catalogo.
-- Cabecera catalogo
INSERT INTO cabecera_bien_tbl(
cab_bien_pk, cab_bien_descripcion, cab_bien_archivo) VALUES 
('TIPIN', 'Tipo ingreso: Se definir� como donaci�n o compra', 'N/A');

-- Detalle catalogo
INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIPIN', 'COMPR', 'Compra', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIPIN', 'DONAC', 'Donaci�n', '1');
    
-- Estado de Conservacion.- Se manejara dentro de una catalogo bueno, malo, regular.
-- Cabecera catalogo
INSERT INTO cabecera_bien_tbl(
cab_bien_pk, cab_bien_descripcion, cab_bien_archivo) VALUES 
('ESCON', 'Estado de Conservacion.- Bueno, malo, regular', 'N/A');

-- Detalle catalogo

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('ESCON', 'MUYBU', 'Muy Bueno', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('ESCON', 'BUENO', 'Bueno', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('ESCON', 'MALO', 'Malo', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('ESCON', 'REGUL', 'Regular', '1');

-- Tipo de baja.- Se manejara dentro de una catalogo Tipo del bajas: Perdida, robo, total, donaci�n
-- Cabecera catalogo
INSERT INTO cabecera_bien_tbl(
cab_bien_pk, cab_bien_descripcion, cab_bien_archivo) VALUES 
('TIBAJ', 'Tipo del bajas: Perdida, robo, total, donaci�n.', 'N/A');


-- Detalle catalogo
INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBAJ', 'PERDI', 'Perdida', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBAJ', 'ROBO', 'Robo', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBAJ', 'TOTAL', 'Total', '1');

INSERT INTO detalle_bien_tbl(
            cab_bien_fk, det_bien_nivel1, det_bien_descripcion, det_bien_estado)
    VALUES ('TIBAJ', 'DONAC', 'Donaci�n', '1');
---------------------------------FIN---------------------------------

/*TABLAS ADICIONALES*/
---------------------------------INICIO---------------------------------
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
---------------------------------FIN---------------------------------

/*ALTER VARIAS TABLAS*/
---------------------------------INICIO---------------------------------
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

alter table bien_tbl
add column bie_estado_uso varchar (5) null;

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
add column tra_descripcion VARCHAR(250) null;

alter table transaccion_tbl
add column tra_num_caso VARCHAR(50) null;

alter table transaccion_tbl
   add constraint FK_TRANSACCION_TBL_REF_EMPLEADO_ASIG foreign key (emp_asignado_fk)
      references empleado_tbl (emp_pk)
      on delete restrict on update restrict;
      
alter table transaccion_tbl
   add constraint FK_TRANSACCION_TBL_REF_EMPLEADO_REASIG foreign key (emp_reasignado_fk)
      references empleado_tbl (emp_pk)
      on delete restrict on update restrict;
      
ALTER TABLE TRANSACCION_TBL
ADD COLUMN CAB_BIEN_TIP_BAJ_FK  VARCHAR(5)           null,
ADD COLUMN DET_BIEN_TIP_BAJ_NIVEL1 VARCHAR(5)           null;

alter table TRANSACCION_TBL
   add constraint FK_TRANSACCION_TBL_RELATIONS_DET_BIE foreign key (CAB_BIEN_TIP_BAJ_FK, DET_BIEN_TIP_BAJ_NIVEL1)
      references DETALLE_BIEN_TBL (CAB_BIEN_FK, DET_BIEN_NIVEL1)
      on delete restrict on update restrict;
---------------------------------FIN---------------------------------

/*VISTAS*/
---------------------------------INICIO---------------------------------
/*==============================================================*/
/* Vista bien                                  */
/*==============================================================*/
DROP view IF EXISTS bien_view;
create view bien_view as
select bie_pk, emr_pk, 
b.bie_nombre, b.bie_modelo, b.bie_color, bie_costo_venta, bie_ubicacion, bie_notas, 
bie_estado, CASE WHEN bie_estado='1' THEN 'ACTIVO' ELSE 'INACTIVO' END as bie_estado_string, 
bie_estado_uso, CASE WHEN bie_estado_uso='1' THEN 'EN USO' ELSE 'SIN USO' END as bie_estado_uso_string, 
b.cat_bien_pk, b.bie_codigo, b.lin_bien_pk, lb.lin_bien_nombre, lb.lin_bien_estado, lb.lin_bien_indice, b.mar_bien_pk, 
mb.mar_bien_nombre, mb.mar_bien_estado, cb.cat_bien_nombre, cb.cat_bien_estado, cb.cat_bien_indice,
t.cab_bien_tip_bie_fk, t.det_bien_tip_bie_nivel1,
t.cab_bien_est_conserv_fk, t.det_bien_est_conserv_nivel1_fk, t.cab_bien_tip_baj_fk, t.det_bien_tip_baj_nivel1, 
t.tra_estado, t.emp_asignado_fk, t.emp_reasignado_fk, t.tra_descripcion, t.tra_fecha_inicio,
per.per_ci, (per.per_nombres || ' ' || per.per_apellidos) as nombres_completos,
dbti.det_bien_descripcion as tipo_ingreso_bien, dbec.det_bien_descripcion as estado_conservacion_bien
from bien_tbl b
inner join categoria_bien_tbl cb on cb.cat_bien_pk=b.cat_bien_pk
inner join linea_bien_tbl lb on lb.lin_bien_pk = b.lin_bien_pk and lb.cat_bien_pk = cb.cat_bien_pk
inner join marca_bien_tbl mb on mb.mar_bien_pk = b.mar_bien_pk
left join transaccion_tbl t on t.bie_fk = b.bie_pk
inner join detalle_bien_tbl dbti on dbti.cab_bien_fk = t.cab_bien_tip_bie_fk and dbti.det_bien_nivel1 = t.det_bien_tip_bie_nivel1
inner join detalle_bien_tbl dbec on dbec.cab_bien_fk = t.cab_bien_est_conserv_fk and dbec.det_bien_nivel1 = t.det_bien_est_conserv_nivel1_fk
left join  empleado_tbl emp on emp.emp_pk = t.emp_asignado_fk 
left JOIN persona_tbl per ON emp.per_fk = per.per_pk;

/*==============================================================*/
/* Vista Empleado                                  */
/*==============================================================*/
DROP view IF EXISTS empleado_view;
CREATE VIEW empleado_view as
SELECT per.per_pk, per.per_ci, per.per_nombres, per.per_apellidos, (per.per_nombres || ' ' || per.per_apellidos) as nombres_completos, per.per_direccion, per.per_telefono, per.per_celular,
    per.per_email, per.per_foto, emp.emp_codigo, emp.emp_pk, emr.emr_pk, emr.tip_empresa_fk,
    emr.emr_nombre, emr.emr_direccion, emr.emr_ruc, emp.emr_fk
   FROM empleado_tbl emp
   inner JOIN persona_tbl per ON emp.per_fk = per.per_pk
   inner JOIN empresa_tbl emr ON emr.emr_pk = emp.emr_fk;

/*==============================================================*/
/* Vista transaccion para llevar la trazabilidad del bien       */
/*==============================================================*/
DROP view IF EXISTS transaccion_view;
CREATE VIEW transaccion_view as
select t.tra_pk, t.bie_fk, tra_estado, t.tra_fecha_inicio, t.tra_fecha_fin, 
t.cab_bien_tip_bie_fk, t.det_bien_tip_bie_nivel1, dbtb.det_bien_descripcion as tip_bie_descripcion, 
t.cab_bien_est_conserv_fk, t.det_bien_est_conserv_nivel1_fk, dbec.det_bien_descripcion as est_conserv_descripcion, 
t.cab_bien_tip_baj_fk, t.det_bien_tip_baj_nivel1, dbtipBaj.det_bien_descripcion as tipo_baja_descripcion, 
t.emp_asignado_fk, eva.nombres_completos as nombre_emp_asignado,
t.emp_reasignado_fk, evra.nombres_completos as nombre_emp_reasignado
from transaccion_tbl t
left join detalle_bien_tbl dbtb on dbtb.cab_bien_fk = t.cab_bien_tip_bie_fk and dbtb.det_bien_nivel1 = t.det_bien_tip_bie_nivel1
left join detalle_bien_tbl dbec on dbec.cab_bien_fk = t.cab_bien_est_conserv_fk and dbec.det_bien_nivel1= t.det_bien_est_conserv_nivel1_fk
left join detalle_bien_tbl dbtipBaj on dbtipBaj.cab_bien_fk = t.cab_bien_tip_baj_fk and dbtipBaj.det_bien_nivel1= t.det_bien_tip_baj_nivel1
left join empleado_view eva on eva.emp_pk = t.emp_asignado_fk
left join empleado_view evra on evra.emp_pk = t.emp_reasignado_fk
order by t.tra_fecha_inicio asc;
---------------------------------FIN---------------------------------


/*==============================================================*/
/* 2014 - 07 - 16       */
/*==============================================================*/
alter table categoria_bien_tbl
add column cat_bien_indice integer not null default 0;

alter table linea_bien_tbl
add column lin_bien_indice integer not null default 0;

alter table bien_tbl
add column cab_bien_tip_ing_fk varchar null;

alter table bien_tbl
add column det_bien_tip_ing_nivel1 varchar null;

alter table BIEN_TBL
   add constraint FK_BIEN_TBL_RELATIONS_DET_TIP_ING foreign key (cab_bien_tip_ing_fk, det_bien_tip_ing_nivel1)
      references DETALLE_BIEN_TBL (CAB_BIEN_FK, DET_BIEN_NIVEL1)
      on delete restrict on update restrict;

--alter table PROVEEDOR
--add column EMR_PK INT4 null;
--
--create unique index PROVEEDOR_EMPRESA_PK on PROVEEDOR (
--PROV_PK
--);
--
--alter table PROVEEDOR
--   add constraint FK_PROVEEDO_REFERENCE_EMPRESA_ foreign key (EMR_PK)
--      references EMPRESA_TBL (EMR_PK)
--      on delete restrict on update restrict;


	DROP view IF EXISTS bien_view;

alter table bien_tbl drop column bie_ubicacion;

alter table transaccion_tbl add column bie_ubicacion varchar (50) null;

-- volver a ejecutar vista

CREATE SEQUENCE bien_codigo_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE bien_codigo_seq OWNER TO postgres;


/*==============================================================*/
/* Table: ACTA_BIEN_TBL                                         */
/*==============================================================*/
create table ACTA_BIEN_TBL (
   ACT_BIE_PK           SERIAL               not null,
   ACT_BIE_NUM          VARCHAR(50)          not null,
   ACT_BIE_FECHA_GEN    DATE                 null,
   constraint PK_ACTA_BIEN_TBL primary key (ACT_BIE_PK)
);

/*==============================================================*/
/* Table: TRANSACCION_ACTA_BIEN                                 */
/*==============================================================*/
create table TRANSACCION_ACTA_BIEN (
   TRA_ACT_BIE          SERIAL               not null,
   ACT_BIE_PK           INT4                 null,
   TRA_PK               INT4                 null,
   constraint PK_TRANSACCION_ACTA_BIEN primary key (TRA_ACT_BIE)
);

alter table TRANSACCION_ACTA_BIEN
   add constraint FK_TRANSACC_REFERENCE_ACTA_BIE foreign key (ACT_BIE_PK)
      references ACTA_BIEN_TBL (ACT_BIE_PK)
      on delete restrict on update restrict;

alter table TRANSACCION_ACTA_BIEN
   add constraint FK_TRANSACC_REFERENCE_TRANSACC foreign key (TRA_PK)
      references TRANSACCION_TBL (TRA_PK)
      on delete restrict on update restrict;
      
      
DROP view IF EXISTS acta_bien_view;
create view acta_bien_view as
select bie_pk, emr_pk, 
b.bie_nombre, b.bie_modelo, b.bie_color, bie_costo_venta, bie_ubicacion, bie_notas, 
bie_estado, CASE WHEN bie_estado='1' THEN 'ACTIVO' ELSE 'INACTIVO' END as bie_estado_string, 
bie_estado_uso, CASE WHEN bie_estado_uso='1' THEN 'EN USO' ELSE 'SIN USO' END as bie_estado_uso_string, 
b.cat_bien_pk, b.bie_codigo, b.lin_bien_pk, lb.lin_bien_nombre, lb.lin_bien_estado, b.mar_bien_pk, 
mb.mar_bien_nombre, mb.mar_bien_estado, cb.cat_bien_nombre, cb.cat_bien_estado, 
t.cab_bien_tip_bie_fk, t.det_bien_tip_bie_nivel1,
t.cab_bien_est_conserv_fk, t.det_bien_est_conserv_nivel1_fk, t.cab_bien_tip_baj_fk, t.det_bien_tip_baj_nivel1, 
t.tra_estado, t.emp_asignado_fk, t.emp_reasignado_fk, t.tra_descripcion, t.tra_fecha_inicio,
per.per_ci, (per.per_nombres || ' ' || per.per_apellidos) as nombres_completos,
dbti.det_bien_descripcion as tipo_ingreso_bien, dbec.det_bien_descripcion as estado_conservacion_bien,
ab.act_bie_num, ab.act_bie_fecha_gen
from bien_tbl b
inner join categoria_bien_tbl cb on cb.cat_bien_pk=b.cat_bien_pk
inner join linea_bien_tbl lb on lb.lin_bien_pk = b.lin_bien_pk and lb.cat_bien_pk = cb.cat_bien_pk
inner join marca_bien_tbl mb on mb.mar_bien_pk = b.mar_bien_pk
left join transaccion_tbl t on t.bie_fk = b.bie_pk
inner join detalle_bien_tbl dbti on dbti.cab_bien_fk = t.cab_bien_tip_bie_fk and dbti.det_bien_nivel1 = t.det_bien_tip_bie_nivel1
inner join detalle_bien_tbl dbec on dbec.cab_bien_fk = t.cab_bien_est_conserv_fk and dbec.det_bien_nivel1 = t.det_bien_est_conserv_nivel1_fk
left join  empleado_tbl emp on emp.emp_pk = t.emp_asignado_fk 
left JOIN persona_tbl per ON emp.per_fk = per.per_pk
inner join transaccion_acta_bien tab on tab.tra_pk = t.tra_pk
inner join acta_bien_tbl ab on ab.act_bie_pk = tab.act_bie_pk
;


--SCRIPT 2014 / 12 / 02

/*==============================================================*/
/* Table: CATALOGO_TIPO_TBL                                     */
/*==============================================================*/
create table CATALOGO_TIPO_TBL (
   CATALOGO_TIPO_ID     INT8                 not null,
   CATALOGO_TIPO_NOMBRE VARCHAR(50)          not null,
   CATALOGO_TIPO_DESC   VARCHAR(100)         null,
   CATALOGO_TIPO_ESTADO VARCHAR(1)           null,
   constraint PK_CATALOGO_TIPO_TBL primary key (CATALOGO_TIPO_ID)
);

/*==============================================================*/
/* Table: CATALOGO_VALOR_TBL                                    */
/*==============================================================*/
create table CATALOGO_VALOR_TBL (
   CATALOGO_VALOR_ID    VARCHAR(3)           not null,
   CATALOGO_TIPO_ID     INT4                 not null,
   CATALOGO_VALOR_NOMBRE VARCHAR(50)          not null,
   CATALOGO_VALOR_ESTADO VARCHAR(1)           null,
   constraint PK_CATALOGO_VALOR_TBL primary key (CATALOGO_VALOR_ID, CATALOGO_TIPO_ID)
);

alter table CATALOGO_VALOR_TBL
   add constraint FK_CATALOGO_REFERENCE_CATALOGO foreign key (CATALOGO_TIPO_ID)
      references CATALOGO_TIPO_TBL (CATALOGO_TIPO_ID)
      on delete restrict on update restrict;

/*==============================================================*/
/* Table: PARAMETRO_EMPRESA_TBL                                 */
/*==============================================================*/
create table PARAMETRO_EMPRESA_TBL (
   ID_PARAMETRO         SERIAL               not null,
   EMR_PK               INT4                 null,
   CATALOGO_VALOR_ID    VARCHAR(3)           null,
   CATALOGO_TIPO_ID     INT4                 null,
   NOMBRE_PARAMETRO     VARCHAR(50)          not null,
   DESCRIPCION_PARAMETRO VARCHAR(200)         null,
   VALOR_PARAMETRO      VARCHAR(50)          not null,
   FECHA_REGISTRO       TIMESTAMP            not null,
   FECHA_MODIFICACION   TIMESTAMP            null,
   ESTADO               CHAR(1)              not null,
   constraint PK_PARAMETRO_EMPRESA_TBL primary key (ID_PARAMETRO)
);

alter table PARAMETRO_EMPRESA_TBL
   add constraint FK_PARAMETR_REFERENCE_EMPRESA_ foreign key (EMR_PK)
      references EMPRESA_TBL (EMR_PK)
      on delete restrict on update restrict;

alter table PARAMETRO_EMPRESA_TBL
   add constraint FK_PARAMETR_REFERENCE_CATALOGO foreign key (CATALOGO_VALOR_ID, CATALOGO_TIPO_ID)
      references CATALOGO_VALOR_TBL (CATALOGO_VALOR_ID, CATALOGO_TIPO_ID)
      on delete restrict on update restrict;
      
      
INSERT INTO catalogo_tipo_tbl(
            catalogo_tipo_id, catalogo_tipo_nombre, catalogo_tipo_desc, catalogo_tipo_estado)
    VALUES (1, 'METODO GENERAR CODIGO BIEN', 'METODO PARA GENERAR EL CODIGO BIEN', '1');
    
INSERT INTO catalogo_valor_tbl(
            catalogo_valor_id, catalogo_tipo_id, catalogo_valor_nombre, catalogo_valor_estado)
    VALUES ('SCB', 1, 'GENERAR POR SECUENCIA', '1');

-- HASTA AQUI


-- --SCRIPTS PARA CREAR LA SECUENCIA E INSERT PARA LA TABLA DONDE SE CONSULTA

CREATE SEQUENCE "CAMBIAR POR NOMBRE DE LA SECUENCIA" -- DEBE SER UNICO
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "CAMBIAR POR NOMBRE DE LA SECUENCIA" OWNER TO postgres;

INSERT INTO parametro_empresa_tbl(
        id_parametro, emr_pk, catalogo_valor_id, catalogo_tipo_id, nombre_parametro, 
        descripcion_parametro, valor_parametro, fecha_registro, estado)
VALUES (nextval('parametro_empresa_tbl_id_parametro_seq'), "CAMBIAR POR EL ID DE LA EMPRESA", 'SCB', 1, 'Nombre secuencia generar codigo bien', 
        'Nombre secuencia generar codigo bien', "CAMBIAR POR EL NOMBRE DE LA SECUENCIA DE LA EMPRESA", LOCALTIMESTAMP, '1');

-- EJEMPLO  ejemplo de secuencias
CREATE SEQUENCE bien_codigo_seq_empresa_dos
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE bien_codigo_seq OWNER TO postgres;
        
INSERT INTO parametro_empresa_tbl(
            id_parametro, emr_pk, catalogo_valor_id, catalogo_tipo_id, nombre_parametro, 
            descripcion_parametro, valor_parametro, fecha_registro, estado)
    VALUES (nextval('parametro_empresa_tbl_id_parametro_seq'), 2, 'SCB', 1, 'Nombre secuencia generar codigo bien', 
            'Nombre secuencia generar codigo bien', 'bien_codigo_seq_empresa_dos', LOCALTIMESTAMP, '1');
