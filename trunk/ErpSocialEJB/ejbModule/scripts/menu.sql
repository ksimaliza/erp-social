--Modulo(12)
INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Control Asistencia', 'Control Asistencia', '2014-06-06', 1);
--Modulo(5)    
  INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Administración', 'Administración', '2014-06-06', 1);  
    
--Modulo(11)    
 INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Matrícula', 'Matrícula', '2014-06-06', 1);
 
--Modulo(13)    
 INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Despacho Parroquial y Cementerio', 'Despacho Parroquial y Cementerio', '2014-06-06', 1);    
   
--Modulo(14)    
 INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Administración Inventario', 'Administración Inventario', '2014-06-06', 1);    
 
   
    
    
    
    
--Menu(1)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Cambiar clave', 'Cambiar clave', '/paginas/admModulo/administracionModulo.xhtml', '2014-06-06');  
   
--Menu(3)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Modulos', 'Administrar Modulos', '/paginas/admModulo/administracionModulo.xhtml', '2014-06-06');
    
--Menu(4)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Empresa', 'Administrar Empresa', '/paginas/admEmpresa/administracionEmpresa.xhtml', '2014-06-06');
    
    
--Menu(5)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Perfil', 'Administrar Perfil', '/paginas/admPerfil/administracionPerfil.xhtml', '2014-06-06');
    
--Menu(10)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Parametro', 'Administrar Parametro', '/paginas/admParametro/administracionParametro.xhtml', '2014-06-06');
    
--Menu(15)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Estudiante', 'Matricula Estudiante', '/paginas/matEstudiante/matriculaEstudiante.xhtml', '2014-06-06');    
    
--Menu(22)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Profesor', 'Matricula Profesor', '/paginas/matProfesor/matriculaProfesor.xhtml', '2014-06-06');    

--Menu(24)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Menú', 'Administrar Menú', '/paginas/admMenu/administracionMenu.xhtml', '2014-06-06');    
    
--Menu(29)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Docente', 'Matricula Docente', '/paginas/matDocente/matriculaDocente.xhtml', '2014-06-06');  

--Menu(31)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Materia', 'Matricula Materia', '/paginas/matMateria/matriculaMateria.xhtml', '2014-06-06');

--Menu(34)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Paralelo', 'Matricula Paralelo', '/paginas/matParalelo/matriculaParalelo.xhtml', '2014-06-06');
 
    
    
    
--Menu(35)
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Permiso', 'Asistencia Permiso', '/paginas/asisPermiso/asistenciaPermiso.xhtml', '2014-06-06');
--Menu(36)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Parametro', 'Asistencia Parametro', '/paginas/asisParametro/asistenciaParametro.xhtml', '2014-06-06');    
--Menu(37)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Día Laboral', 'Asistencia Día Laboral', '/paginas/asisDiaLaboral/asistenciaDiaLaboral.xhtml', '2014-06-06');    

    
    
    
    
--Modulo Menu   
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,35)
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,36)
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,37)

    