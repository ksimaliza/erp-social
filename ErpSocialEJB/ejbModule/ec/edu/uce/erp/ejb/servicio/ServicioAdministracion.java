/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Parametro;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;

/**
 * @author
 *
 */
@Local
public interface ServicioAdministracion {
	
	/*
	 * servicios para administrar empresas
	 */
	
	/**
	 * Registrar una empresa
	 * @param empresa
	 * @return
	 * @throws SeguridadesException
	 */
	Empresa registrarEmpresa (Empresa empresa) throws SeguridadesException;
	
	/**
	 * Actualizar una empresa
	 * @param empresa
	 * @return
	 * @throws SeguridadesException
	 */
	Empresa actualizarEmpresa (Empresa empresa) throws SeguridadesException;
	
	/**
	 * Buscar empresas por criterios
	 * @param empresa
	 * @return
	 * @throws SeguridadesException
	 */
	List<Empresa> buscarEmpresa (Empresa empresa) throws SeguridadesException;
	
	/**
	 * Obtener empresa por ID (primary key)
	 * @param empresa
	 * @return
	 * @throws SeguridadesException
	 */
	Empresa obtenerEmpresaPorId (Integer id) throws SeguridadesException; 
	
	/*
	 * servicios para administrar las modulos
	 */
	/**
	 * Registrar modulo
	 * @param modulo
	 * @return
	 * @throws SeguridadesException
	 */
	Modulo registrarModulo (Modulo modulo) throws SeguridadesException;
	
	/**
	 * Actualizar <code>Modulo</code> en la base de datos
	 * @param modulo
	 * @return
	 * @throws SeguridadesException
	 */
	Modulo actualizarModulo (Modulo modulo) throws SeguridadesException;
	
	/**
	 * Buscar <code>Modulo</code> en la base de datos
	 * @param modulo
	 * @return
	 * @throws SeguridadesException
	 */
	List<Modulo> buscarModulos (Modulo modulo) throws SeguridadesException;
	
	/*
	 * servicios para administrar los menus
	 */
	/**
	 * Registrar <code>Menu</code> en la base de datos
	 * @param menu
	 * @return
	 * @throws SeguridadesException
	 */
	Menu registrarMenu (Menu menu) throws SeguridadesException;
	
	/** 
	 * Actualizar <code>Menu</code> en la base de datos
	 * @param menu
	 * @return
	 * @throws SeguridadesException
	 */
	Menu actualizarMenu (Menu menu) throws SeguridadesException;
	
	/**
	 * Buscar <code>Menu</code> en la base de datos
	 * @param menu
	 * @return
	 * @throws SeguridadesException
	 */
	List<Menu> buscarMenu (Menu menu) throws SeguridadesException;
	
	/*
	 * servicios para administrar las perfiles
	 */
	
	/**
	 * Registrar <code>Perfil</code> en la base de datos
	 * @param perfil
	 * @return
	 * @throws SeguridadesException
	 */
	Perfil registrarPerfil (Perfil perfil) throws SeguridadesException;
	
	/**
	 * Actualizar <code>Perfil</code> en la base de datos
	 * @param perfil
	 * @return
	 * @throws SeguridadesException
	 */
	Perfil actualizarPerfil (Perfil perfil) throws SeguridadesException;
	
	/**
	 * Buscar <code>Perfil</code> por criterios
	 * @param perfil
	 * @return
	 * @throws SeguridadesException
	 */
	List<Perfil> buscarPerfiles (Perfil perfil) throws SeguridadesException;
	
	/**
	 * Consultar <code>Perfil</code> por el id de una empresa
	 * @param perfil
	 * @return
	 * @throws SeguridadesException
	 */
	List<Perfil> buscarPerfileEmpresa (Perfil perfil) throws SeguridadesException;
	
	/**
	 * Obtener <code>Perfil</code> por el id (Primary key)
	 * @param id
	 * @return
	 * @throws SeguridadesException
	 */
	Perfil obtenerPerfilPorId (Integer id) throws SeguridadesException; 
	
	/*
	 * servicios para administrar usuarios
	 */
	/**
	 * Registrar usuario
	 * @param usuario
	 * @return
	 * @throws SeguridadesException
	 */
	Usuario registrarUsuario (Usuario usuario) throws SeguridadesException;
	
	/**
	 * Actualizar <code>Usuario</code> en la base de datos
	 * @param usuario
	 * @return
	 * @throws SeguridadesException
	 */
	Usuario actualizarUsuario (Usuario usuario) throws SeguridadesException;
	
	/**
	 * Buscar <code>Usuario</code> en la base de datos
	 * @param usuario
	 * @return
	 * @throws SeguridadesException
	 */
	List<Usuario> buscarUsuarios (Usuario usuario) throws SeguridadesException;
	
	/**
	 * Buscar <code>Empleado</code> en la base de datos
	 * @param empleadoListDTO
	 * @return
	 * @throws SeguridadesException
	 */
	List<EmpleadoListDTO> obtenerEmpleadoEmpresa (EmpleadoListDTO empleadoListDTO) throws SeguridadesException;
	
	/*
	 * Servicio para administracion de catalogos
	 */
	
	/**
	 * Buscar <code>DetalleCatalogo</code> por criterios
	 * @param detalleCatalogo
	 * @return
	 * @throws SeguridadesException
	 */
	List<DetalleCatalogo> buscarDetalleCatalogoCriterios (DetalleCatalogo detalleCatalogo) throws SeguridadesException;
	
	/*
	 * Servicios para la administracion de parametros
	 */
	
	/**
	 * Buscar <code>Parametro</code> de la BD por criterios
	 * @param parametro
	 * @return
	 * @throws SeguridadesException
	 */
	List<Parametro> buscarParametrosCriterios (Parametro parametro) throws SeguridadesException;
	
	/**
	 * Actualizar <code>Parametro</code> de la BD 
	 * @param parametro
	 * @return
	 * @throws SeguridadesException
	 */
	Parametro actualizarParametro (Parametro parametro) throws SeguridadesException;

}
