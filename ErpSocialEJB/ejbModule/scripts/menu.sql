--Modulo(12)
INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Control Asistencia', 'Control Asistencia', '2014-06-06', 1);
    
    
--Menu(35)
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Permiso', 'Asistencia Permiso', '/paginas/asisPermiso/asistenciaPermiso.xhtml', '2014-06-06');
--Menu(36)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Parametro', 'Asistencia Parametro', '/paginas/asisParametro/asistenciaParametro.xhtml', '2014-06-06');    
--Menu(37)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia D�a Laboral', 'Asistencia D�a Laboral', '/paginas/asisDiaLaboral/asistenciaDiaLaboral.xhtml', '2014-06-06');    

    
--Modulo Menu   
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,35)
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,36)
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,37)

    