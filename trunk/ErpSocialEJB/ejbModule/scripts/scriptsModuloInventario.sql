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
