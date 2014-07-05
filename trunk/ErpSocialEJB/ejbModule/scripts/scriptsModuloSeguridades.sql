-- tabla para almacenar el menu del usuario
create table SEGT_MENU_USUARIO (
   ID_MENU_USUARIO      SERIAL               not null,
   ID_USUARIO           INT4                 not null,
   ID_MENU              INT4                 null,
   constraint PK_SEGT_MENU_USUARIO primary key (ID_MENU_USUARIO)
);

alter table SEGT_MENU_USUARIO
   add constraint FK_SEGT_MEN_REFERENCE_SEGT_USU foreign key (ID_USUARIO)
      references SEGT_USUARIO (ID_USUARIO)
      on delete restrict on update restrict;

alter table SEGT_MENU_USUARIO
   add constraint FK_SEGT_MEN_REFERENCE_SEGT_MEN foreign key (ID_MENU)
      references SEGT_MENU (ID_MENU)
      on delete restrict on update restrict;


-- reiniciar en 100 la secuencias para no tener problemas con insert manuales
SELECT setval('erpt_parametro_id_parametro_seq',100);
SELECT setval('segt_menu_id_menu_seq',500);
SELECT setval('segt_modulo_id_modulo_seq',500);
SELECT setval('segt_perfil_id_perfil_seq',500);

-- insert parametro tiempo cambiar clave usuario
INSERT INTO erpt_parametro
(id_parametro, nombre_parametro, descripcion_parametro, valor_parametro,fecha_registro, estado) VALUES 
(1, 'Tiempo validez contraseña', 'Tiempo en días de validez contraseña', '30', LOCALTIMESTAMP, '1');

-- columna para ordenar el menu
alter table segt_menu
add column orden int null;

/*==============================================================*/
/* INSERT PARA MENUS*/
/*==============================================================*/

--CREAR OPCIONES DE MENU PARA EL MODULO DE INVENTARIO
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (1, 'Catalogos', 'Catalogos Inventario', '/paginas/admInventario/catalogosInventario/administracionCatalogos.xhtml',LOCALTIMESTAMP,1);
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (2, 'Proveedores', 'Administración de proveedores', '/paginas/admInventario/administracionProveedor.xhtml',LOCALTIMESTAMP,2);
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (3, 'Categoria Bien', 'Categoria Bien', '/paginas/admInventario/administracionCategoriaBien.xhtml',LOCALTIMESTAMP,3);
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (4, 'Linea Bien', 'Linea Bien', '/paginas/admInventario/administracionLineaBien.xhtml',LOCALTIMESTAMP,4);
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (5, 'Marca Bien', 'Marca Bien', '/paginas/admInventario/administracionMarcaBien.xhtml',LOCALTIMESTAMP,5);
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (6, 'Bienes', 'Administracion Bienes', '/paginas/admInventario/administracionBien.xhtml',LOCALTIMESTAMP,6);
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (7, 'Transacciones bienes', 'Transacciones bienes', '/paginas/admInventario/transaccionesBien.xhtml',LOCALTIMESTAMP,7);
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (8, 'Transacciones masivas', 'Transacciones masivas', '/paginas/admInventario/transaccionesMasivasBien.xhtml',LOCALTIMESTAMP,8);
INSERT INTO segt_menu (id_menu, nombre_menu, desc_menu, url_menu, fecha_registro, orden) VALUES (9, 'Inventario', 'Inventario', '/paginas/admInventario/administracionInventario.xhtml',LOCALTIMESTAMP,9);

--CREAR MODULO DE INVENTARIO
INSERT INTO segt_modulo(id_modulo, nombre_modulo, desc_modulo, fecha_registro, estado) VALUES (1, 'Inventario', 'Inventario', LOCALTIMESTAMP, '1');

--INSERT TABLA  RELACION MODULO MENU
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 1);
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 2);
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 3);
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 4);
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 5);
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 6);
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 7);
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 8);
INSERT INTO segt_modulo_menu(id_modulo, id_menu) VALUES (1, 9);

--CREAR PERFIL PARA LA BODEGA
INSERT INTO segt_perfil(id_perfil, nombre_perfil, desc_perfil, fecha_registro, estado) VALUES (1, 'Bodega', 'Perfil para el usuario de la bodega', LOCALTIMESTAMP, '1');

--INSERT TABLA  RELACION MODULO PERFIL
INSERT INTO segt_modulo_perfil(id_perfil, id_modulo) VALUES (1, 1);