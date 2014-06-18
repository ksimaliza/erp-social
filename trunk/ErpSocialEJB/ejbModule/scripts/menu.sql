--MODULOS

--Modulo(2)
INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Opciones Usuario', 'Opciones Usuario', '2014-06-06', 1);

--Modulo(3)
INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Control Asistencia', 'Control Asistencia', '2014-06-06', 1);
--Modulo(4)    
  INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Administración', 'Administración', '2014-06-06', 1);  
    
--Modulo(5)    
 INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Matrícula', 'Matrícula', '2014-06-06', 1);
 
--Modulo(6)    
 INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Despacho Parroquial y Cementerio', 'Despacho Parroquial y Cementerio', '2014-06-06', 1);    
   
--Modulo(7)    
 INSERT INTO segt_modulo(nombre_modulo, desc_modulo, fecha_registro, estado)
    VALUES ('Administración Inventario', 'Administración Inventario', '2014-06-06', 1);    
 
    
   
---Modulo Perfil
INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 2);
    
  INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 3);
    
    
    INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 4);
    
    INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 5);
    INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 6);

    INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 7);

   
   
    
--MENUS    
    
--Menu(2)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Cambiar clave', 'Cambiar clave', '/paginas/usuario/cambiarClave.xhtml', '2014-06-06');  
   
--Menu(3)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Modulos', 'Administrar Modulos', '/paginas/admModulo/administracionModulo.xhtml', '2014-06-06');
    
--Menu(4)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Empresa', 'Administrar Empresa', '/paginas/admEmpresa/administracionEmpresa.xhtml', '2014-06-06');
    
    
--Menu(5)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Perfil', 'Administrar Perfil', '/paginas/admPerfil/administracionPerfil.xhtml', '2014-06-06');
    
--Menu(6)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Parametro', 'Administrar Parametro', '/paginas/admParametro/administracionParametro.xhtml', '2014-06-06');
    
    --Menu(7)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administrar Menú', 'Administrar Menú', '/paginas/admMenu/administracionMenu.xhtml', '2014-06-06');    

    
--Menu(8)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Estudiante', 'Matricula Estudiante', '/paginas/matEstudiante/matriculaEstudiante.xhtml', '2014-06-06');    
    
--Menu(9)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Profesor', 'Matricula Profesor', '/paginas/matProfesor/matriculaProfesor.xhtml', '2014-06-06');    

    
--Menu(10)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Docente', 'Matricula Docente', '/paginas/matDocente/matriculaDocente.xhtml', '2014-06-06');  

--Menu(11)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Materia', 'Matricula Materia', '/paginas/matMateria/matriculaMateria.xhtml', '2014-06-06');

--Menu(12)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Paralelo', 'Matricula Paralelo', '/paginas/matParalelo/matriculaParalelo.xhtml', '2014-06-06');
 
--Menu(13)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Nivel', 'Matricula Nivel', '/paginas/matNivel/matriculaNivel.xhtml', '2014-06-06');    
  
--Menu(14)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Período', 'Matricula Período', '/paginas/matPeriodo/matriculaPeriodo.xhtml', '2014-06-06');
     
    
    --Menu(15)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Representante', 'Matricula Representante', '/paginas/matRepresentante/matriculaRepresentante.xhtml', '2014-06-06');    
  
        --Menu(16)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Nivel Paralelo', 'Matricula Nivel Paralelo', '/paginas/matNivelParalelo/matriculaNivelParalelo.xhtml', '2014-06-06');
    
     --Menu(17)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Asinacion', 'Matricula Asinacion', '/paginas/matAsinacion/matriculaAsinacion.xhtml', '2014-06-06');
    
       --Menu(45)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Registro', 'Matricula Registro', '/paginas/matMatricula/matriculaRegistro.xhtml', '2014-06-06');

--Menu(45)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Reporte Curso', 'Matricula Reporte Curso', '/paginas/matReporte/matReporteCurso.xhtml', '2014-06-06');
    
    
---Asistencia    
 	--Menu(18)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Empleado', 'Asistencia Empleado', '/paginas/asisEmpleado/asistenciaEmpleado.xhtml', '2014-06-06'); 
 	--Menu(19)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Falta', 'Asistencia Falta', '/paginas/asisFalta/asistenciaFalta.xhtml', '2014-06-06');    
    --Menu(20)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Parametro', 'Asistencia Parametro', '/paginas/asisParametro/asistenciaParametro.xhtml', '2014-06-06');
    --Menu(21)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Tipo', 'Asistencia Tipo', '/paginas/asisTipo/asistenciaTipo.xhtml', '2014-06-06');
	--Menu(22)
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Permiso', 'Asistencia Permiso', '/paginas/asisPermiso/asistenciaPermiso.xhtml', '2014-06-06');
	--Menu(23)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Día Laboral', 'Asistencia Día Laboral', '/paginas/asisDiaLaboral/asistenciaDiaLaboral.xhtml', '2014-06-06');
    --Menu
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Horario', 'Asistencia Horario', '/paginas/asisHorario/asistenciaHorario.xhtml', '2014-06-06');    


--Eucaristia    
 --Menu(24)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Eucaristia', 'Despacho Eucaristia', '/paginas/despEucaristia/despachoEucaristia.xhtml', '2014-06-06');    
    
--Menu(25)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Partida de Bautizo', 'Despacho Partida de Bautizo', '/paginas/despPartidaBautizo/despachoPartidaBautizo.xhtml', '2014-06-06');
    
--Menu(26)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Partida de Primera Comunión', 'Despacho Partida de Primera Comunión', '/paginas/despPartidaPrimeraComunion/despachoPartidaPrimeraComunion.xhtml', '2014-06-06');
    
--Menu(27)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Partida de Confirmación', 'Despacho Partida de Confirmación', '/paginas/despPartidaConfirmacion/despachoPartidaConfirmacion.xhtml', '2014-06-06');
        
--Menu(28)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Partida de Matrimonio', 'Despacho Partida de Matrimonio', '/paginas/despPartidaMatrimonio/despachoPartidaMatrimonio.xhtml', '2014-06-06');

--Menu(29)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Defunción', 'Despacho Defunción', '/paginas/despDefuncion/despachoDefuncion.xhtml', '2014-06-06');    

--Menu(30)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Sepultura', 'Despacho Sepultura', '/paginas/despSepultura/despachoSepultura.xhtml', '2014-06-06');    
--Menu(31)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Sección Nicho', 'Despacho Sección Nicho', '/paginas/despSeccionNicho/despachoSeccionNicho.xhtml', '2014-06-06'); 

--Menu(32)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Tipo Nicho', 'Despacho Tipo Nicho', '/paginas/despTipoNicho/despachoTipoNicho.xhtml', '2014-06-06');
    
 --Menu(33)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Nivel Nicho', 'Despacho Nivel Nicho', '/paginas/despNivelNicho/despachoNivelNicho.xhtml', '2014-06-06');   

--Menu(34)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Nicho', 'Despacho Nicho', '/paginas/despNicho/despachoNicho.xhtml', '2014-06-06');    

--Menu(35)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Exhumacion', 'Despacho Exhumacion', '/paginas/despExhumacion/despachoExhumacion.xhtml', '2014-06-06'); 

--Menu(36)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Contrato', 'Despacho Contrato', '/paginas/despContrato/despachoContrato.xhtml', '2014-06-06');    

--Menu(37)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Pago Contrato', 'Despacho Pago Contrato', '/paginas/despPagoContrato/despachoPagoContrato.xhtml', '2014-06-06');

--Menu(46)
    INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Sacerdote', 'Despacho Sacerdote', '/paginas/despSacerdote/despachoSacerdote.xhtml', '2014-06-06');
    
--Menu(47)
    INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Doctor', 'Despacho Doctor', '/paginas/despDoctor/despachoDoctor.xhtml', '2014-06-06');    
    
--Menu(38)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Catálogos', 'Administración Catálogos', '/paginas/admInventario/catalogosInventario/administracionCatalogos.xhtml', '2014-06-06');
    
 --Menu(39)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Bien', 'Administración Bien', '/paginas/admInventario/administracionBien.xhtml', '2014-06-06');

     --Menu(40)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Ingreso Bien', 'Administración Ingreso Bien', '/paginas/admInventario/administracionIngresoBien.xhtml', '2014-06-06');
    
   --Menu(41)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Inventario', 'Administración Inventario', '/paginas/admInventario/administracionInventario.xhtml', '2014-06-06');
    
  --Menu(42)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Línea Bien', 'Administración Línea Bien', '/paginas/admInventario/administracionLineaBien.xhtml', '2014-06-06'); 
 
    --Menu(43)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Marca Bien', 'Administración Marca Bien ', '/paginas/admInventario/administracionMarcaBien.xhtml', '2014-06-06');
    
   --Menu(44)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Proveedor', 'Administración Proveedor', '/paginas/admInventario/administracionProveedor.xhtml', '2014-06-06');  
    
 
    
    
    
--Modulo Menu   
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (2,2);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (3,18);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (3,19);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (3,20);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (3,21);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (3,22);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (3,23);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (4,3);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (4,4);    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (4,5);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (4,6);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (4,7);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,8);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,9);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,10); 

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,11);

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,12);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,13);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,14);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,15);    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,16);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,17);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,24);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,25);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,26);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,27); 

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,28);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,29);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,30);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,31);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,32);    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,33);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,34);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,35);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,36);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,37);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (7,38);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (7,39);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (7,40); 
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (7,41);

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (7,42); 
    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (7,43);     
    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (7,44);   

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,45);   

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,46);   

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (6,47);   



    


    