--MODULOS

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
 
    
--MODULO PERFIL

    
---Modulo Perfil
INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 1);
INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 4);
    
  INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 5);
    
    
    INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 11);
    
    INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 12);
    INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 13);

    INSERT INTO segt_modulo_perfil(id_perfil, id_modulo)
    VALUES (1, 14);

   
   
    
--MENUS    
    
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
    VALUES ('Matricula Nivel', 'Matricula Nivel', '/paginas/matNivel/matriculaNivel.xhtml', '2014-06-06');    
  
--Menu(36)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Período', 'Matricula Período', '/paginas/matPeriodo/matriculaPeriodo.xhtml', '2014-06-06');
    
 --Menu(37)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Empleado', 'Asistencia Empleado', '/paginas/asisEmpleado/asistenciaEmpleado.xhtml', '2014-06-06'); 
   
 --Menu(38)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Falta', 'Asistencia Falta', '/paginas/asisFalta/asistenciaFalta.xhtml', '2014-06-06');    
    
   --Menu(40)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Matricula Representante', 'Matricula Representante', '/paginas/matRepresentante/matriculaRepresentante.xhtml', '2014-06-06');    
     
 --Menu(41)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Eucaristia', 'Despacho Eucaristia', '/paginas/despEucaristia/despachoEucaristia.xhtml', '2014-06-06');    
    
--Menu(42)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Partida de Bautizo', 'Despacho Partida de Bautizo', '/paginas/despPartidaBautizo/despachoPartidaBautizo.xhtml', '2014-06-06');
    
--Menu(43)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Partida de Primera Comunión', 'Despacho Partida de Primera Comunión', '/paginas/despPartidaPrimeraComunion/despachoPartidaPrimeraComunion.xhtml', '2014-06-06');
    
--Menu(44)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Partida de Confirmación', 'Despacho Partida de Confirmación', '/paginas/despPartidaConfirmacion/despachoPartidaConfirmacion.xhtml', '2014-06-06');
        
--Menu(45)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Partida de Matrimonio', 'Despacho Partida de Matrimonio', '/paginas/despPartidaMatrimonio/despachoPartidaMatrimonio.xhtml', '2014-06-06');

--Menu(46)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Defunción', 'Despacho Defunción', '/paginas/despDefuncion/despachoDefuncion.xhtml', '2014-06-06');    

--Menu(47)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Sepultura', 'Despacho Sepultura', '/paginas/despSepultura/despachoSepultura.xhtml', '2014-06-06');    
--Menu(49)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Sección Nicho', 'Despacho Sección Nicho', '/paginas/despSeccionNicho/despachoSeccionNicho.xhtml', '2014-06-06'); 

--Menu(50)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Tipo Nicho', 'Despacho Tipo Nicho', '/paginas/despTipoNicho/despachoTipoNicho.xhtml', '2014-06-06');
    
 --Menu(51)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Nivel Nicho', 'Despacho Nivel Nicho', '/paginas/despNivelNicho/despachoNivelNicho.xhtml', '2014-06-06');   

--Menu(52)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Nicho', 'Despacho Nicho', '/paginas/despNicho/despachoNicho.xhtml', '2014-06-06');    

--Menu(53)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Exhumacion', 'Despacho Exhumacion', '/paginas/despExhumacion/despachoExhumacion.xhtml', '2014-06-06'); 

--Menu(54)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Contrato', 'Despacho Contrato', '/paginas/despContrato/despachoContrato.xhtml', '2014-06-06');    

--Menu(55)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Despacho Pago Contrato', 'Despacho Pago Contrato', '/paginas/despPagoContrato/despachoPagoContrato.xhtml', '2014-06-06');
    
--Menu(57)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Catálogos', 'Administración Catálogos', '/paginas/admInventario/catalogosInventario/administracionCatalogos.xhtml', '2014-06-06');
    
 --Menu(62)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Bien', 'Administración Bien', '/paginas/admInventario/administracionBien.xhtml', '2014-06-06');

     --Menu(64)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Ingreso Bien', 'Administración Ingreso Bien', '/paginas/admInventario/administracionIngresoBien.xhtml', '2014-06-06');
    
   --Menu(65)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Inventario', 'Administración Inventario', '/paginas/admInventario/administracionInventario.xhtml', '2014-06-06');
    
  --Menu(66)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Línea Bien', 'Administración Línea Bien', '/paginas/admInventario/administracionLineaBien.xhtml', '2014-06-06'); 
 
    --Menu(67)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Marca Bien', 'Administración Marca Bien ', '/paginas/admInventario/administracionMarcaBien.xhtml', '2014-06-06');
    
   --Menu(68)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Administración Proveedor', 'Administración Proveedor', '/paginas/admInventario/administracionProveedor.xhtml', '2014-06-06');  
    
    --Menu(69)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Parametro', 'Asistencia Parametro', '/paginas/asisParametro/asistenciaParametro.xhtml', '2014-06-06');
    
     --Menu(70)    
INSERT INTO segt_menu(nombre_menu, desc_menu, url_menu, fecha_registro)
    VALUES ('Asistencia Tipo', 'Asistencia Tipo', '/paginas/asisTipo/asistenciaTipo.xhtml', '2014-06-06');
    
    
    
    
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
VALUES (1,1);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,2);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,3);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,4);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,5);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (11,6);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (11,7);    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (5,8);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (11,9);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (11,10);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (11,11);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (11,12);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (11,13); 

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,14);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,15);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (11,16);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,17);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,18);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,19);    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,20);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,21);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,22);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,23);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,24);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,25); 

INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,26);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,27);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,28);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,29);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (13,30);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (14,31);    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (14,32);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (14,33);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (14,34);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (14,35);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (14,36);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (14,37);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,38);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,39); 
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,40);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,41); 
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,42); 
    
    
    
    
    



    
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,35);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,36);
INSERT INTO segt_modulo_menu (id_modulo,id_menu)
VALUES (12,37);

    