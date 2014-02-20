/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.enums.EnumTipoTransaccion;
import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.EncriptacionUtil;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.FactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;

/**
 * @author
 *
 */
@Stateless
public class ServicioAdministracionImpl implements ServicioAdministracion {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioAdministracionImpl.class);
	
	@EJB 
	private FactoryDAO factoryDAO;
	
	/*
	 * Metodos para administracion de empresas
	 */

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Empresa registrarEmpresa(Empresa empresa) throws SeguridadesException {
		slf4jLogger.info("registrarEmpresa");
		
		Empresa empresaNueva = null;
		
		try {
			
			if (empresaRepetida(empresa)) {
				throw new SeguridadesException("Ya se encuentra registrada una empresa con ese RUC");
			}
			
			if (StringUtils.isEmpty(empresa.getEmrEstado())) {
				empresa.setEmrEstado(ConstantesApplication.ESTADO_ACTIVO);
			}
			
			empresaNueva = factoryDAO.getEmpresaDAOImpl().create(empresa);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(empresa.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "registrarEmpresa", EnumTipoTransaccion.CREATE));
			
		} catch (Exception e) {
			slf4jLogger.info("Error al registrar la empresa {}", e.getMessage());
			throw new SeguridadesException(e);
		}
		return empresaNueva;
	}
	
	private Boolean empresaRepetida (Empresa empresa) throws SeguridadesException {
		
		Empresa empresaPlantilla = new Empresa();
		empresaPlantilla.setEmrRuc(empresa.getEmrRuc());
		
		List<Empresa> dtos = factoryDAO.getEmpresaDAOImpl().obtenerEmpresaCriterios(empresaPlantilla);
		
		if (CollectionUtils.isEmpty(dtos)) {
			return Boolean.FALSE;
		} 
		
		return Boolean.TRUE;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Empresa actualizarEmpresa(Empresa empresa) throws SeguridadesException {
		
		slf4jLogger.info("actualizarEmpresa");
		
		Empresa empresaActualizada = null;
		
		try {
			empresaActualizada = factoryDAO.getEmpresaDAOImpl().update(empresa);
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(empresa.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "actualizarEmpresa", EnumTipoTransaccion.UPDATE));
			
		} catch (Exception e) {
			slf4jLogger.info("Error al actualizar la empresa {}", e.getMessage());
			throw new SeguridadesException("No se pudo actualizar la empresa");
		}
		
		return empresaActualizada;
	}

	@Override
	public List<Empresa> buscarEmpresa(Empresa empresa) throws SeguridadesException {
		slf4jLogger.info("buscarEmpresa");
		try {
			return factoryDAO.getEmpresaDAOImpl().obtenerEmpresaCriterios(empresa);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscar la empresa {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener las empresas de la base de datos");
		}
	}

	/*
	 * Metodos modulos
	 */
	
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	@Override
	public Modulo registrarModulo(Modulo modulo) throws SeguridadesException {
		
		Modulo moduloNuevo = null;
		
		slf4jLogger.info("registrarModulo");
		try {
			modulo.setIdModulo(null);
			modulo.setEstado(ConstantesApplication.ESTADO_ACTIVO);
			modulo.setFechaRegistro(UtilAplication.obtenerFechaActual());
			moduloNuevo = factoryDAO.getModuloDAOImpl().create(modulo);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(modulo.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "registrarModulo", EnumTipoTransaccion.CREATE));
			
		} catch (Exception e) {
			slf4jLogger.info("Error al registrar el modulo {}", e.getMessage());
			throw new SeguridadesException("No se pudo registrar el m\u00F3dulo");
		} 
		
		return moduloNuevo;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Modulo actualizarModulo(Modulo modulo) throws SeguridadesException {
		slf4jLogger.info("actualizarModulo");
		
		Modulo moduloActualizado = null;
		
		try {
			
			modulo.setFechaModificacion(UtilAplication.obtenerFechaActual());
			moduloActualizado = factoryDAO.getModuloDAOImpl().update(modulo);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(modulo.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "actualizarModulo", EnumTipoTransaccion.UPDATE));
			
		} catch (Exception e) {
			slf4jLogger.info("Error al actualizar el modulo {}", e.getMessage());
			throw new SeguridadesException ("No se pudo actualizar el modulo en la base de datos");
		}
		
		return moduloActualizado;
	}

	@Override
	public List<Modulo> buscarModulos(Modulo modulo) throws SeguridadesException {
		
		slf4jLogger.info("buscarModulos");
		
		List<Modulo> moduloCol = null;
		
		try {
			moduloCol = factoryDAO.getModuloDAOImpl().obtenerModuloCriterios(modulo);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscar los modulos de la bd {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener los modulos de la base de datos");
		}
		
		return moduloCol;
		
	}

	
	/*
	 * Metodos perfiles
	 */
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Perfil registrarPerfil(Perfil perfil) throws SeguridadesException {
		slf4jLogger.info("registrarPerfil");
		
		Perfil perfilNuevo = null;
		
		try {
			perfil.setEstado(ConstantesApplication.ESTADO_ACTIVO);
			perfil.setFechaRegistro(UtilAplication.obtenerFechaActual());
			perfilNuevo = factoryDAO.getPerfilDAOImpl().create(perfil);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(perfil.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "registrarPerfil", EnumTipoTransaccion.CREATE));
			
			
		} catch (Exception e) {
			slf4jLogger.info("Error al registrar el perfil {}", e.getMessage());
			throw new SeguridadesException("No se pudo registrar el perfil");
		}
		
		return perfilNuevo;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Perfil actualizarPerfil(Perfil perfil) throws SeguridadesException {
		slf4jLogger.info("actualizarPerfil");
		
		Perfil perfilActualizado = null;
		
		try {
			
			perfil.setFechaModificacion(UtilAplication.obtenerFechaActual());
			perfilActualizado = factoryDAO.getPerfilDAOImpl().update(perfil);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(perfil.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "actualizarPerfil", EnumTipoTransaccion.UPDATE));
					
		} catch (Exception e) {
			slf4jLogger.info("No se pudo actualizar el perfil {}" , e.getMessage());
			throw new SeguridadesException("No se pudo actualizar el perfil");
		}
		
		return perfilActualizado;
	}
	
	@Override
	public List<Perfil> buscarPerfiles(Perfil perfil) throws SeguridadesException {
		slf4jLogger.info("buscarPerfiles");
		
		List<Perfil> perfilCol = null;
		 
		try {
			perfilCol = factoryDAO.getPerfilDAOImpl().obtenerPerfilCriterios(perfil);
			
			if (CollectionUtils.isNotEmpty(perfilCol)) {
				CollectionUtils.select(perfilCol, new Predicate() {
					
					@Override
					public boolean evaluate(Object arg0) {
						Perfil perfil = (Perfil)arg0;
						perfil.getSegtModulos().size();
						perfil.getSegtUsuarios().size();
						return true;
					}
				});
			}
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscar los perfiles de la bd {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener los perfiles de la base de datos");
		}
		
		return perfilCol;
	}
	
	@Override
	public List<Perfil> buscarPerfileEmpresa(Perfil perfil) throws SeguridadesException {
		slf4jLogger.info("buscarPerfileEmpresa");
		try {
			return factoryDAO.getPerfilDAOImpl().obtenerPerfilEmpresa(perfil);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarPerfileEmpresa de la bd {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener los perfiles de la base de datos");
		}
	}
	
	/*
	* Administracion de menu
	*/
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Menu registrarMenu(Menu menu) throws SeguridadesException {
		slf4jLogger.info("registrarMenu");
		
		Menu menuNuevo = null;
		
		try {
			menu.setFechaRegistro(UtilAplication.obtenerFechaActual());
			menuNuevo = factoryDAO.getMenuDAOImpl().create(menu);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(menu.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "registrarMenu", EnumTipoTransaccion.CREATE));
			
		} catch (Exception e) {
			slf4jLogger.info("Error al registrar el menu {}", e.getMessage());
			throw new SeguridadesException("No se pudo registrar el menu");
		}
		
		return menuNuevo;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Menu actualizarMenu(Menu menu) throws SeguridadesException {
		slf4jLogger.info("actualizarMenu");
		
		Menu menuActualizado = null;
		
		try {
			menu.setFechaModificacion(UtilAplication.obtenerFechaActual());
			menuActualizado = factoryDAO.getMenuDAOImpl().update(menu);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(menu.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "actualizarMenu", EnumTipoTransaccion.UPDATE));
			
		} catch (Exception e) {
			slf4jLogger.info("No se pudo actualizar el menu {}" , e.getMessage());
			throw new SeguridadesException("No se pudo actualizar el menu");
		}
		
		return menuActualizado;
	}

	@Override
	public List<Menu> buscarMenu(Menu menu) throws SeguridadesException {
		slf4jLogger.info("buscarMenu");
		try {
			return factoryDAO.getMenuDAOImpl().buscarMenuCriterios(menu);
		} catch (Exception e) {
			slf4jLogger.info("No se pudo buscarMenu {}" , e.getMessage());
			throw new SeguridadesException("No se pudo buscar el menu en la base de datos");
		}
	}

	
	
	/*
	 * Metodos para la administracion del usuario
	 */
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	@Override
	public Usuario registrarUsuario(Usuario usuario) throws SeguridadesException {
		slf4jLogger.info("registrarUsuario");
		
		Usuario usuarioNuevo = null;
		
		try {
			
			if (this.verificarPersonaRepetida(usuario)) {
				throw new SeguridadesException("Ya existe una persona registrada con ese num de cedula");
			} 
			
			usuario.setFechaRegistro(UtilAplication.obtenerFechaActual());
			usuario.setPassUsuario(EncriptacionUtil.getInstancia().encriptar(usuario.getLoginUsuario()));
			usuario.setEstado(ConstantesApplication.ESTADO_ACTIVO);
			usuarioNuevo = factoryDAO.getUsuarioDAOImpl().create(usuario);
			
			Persona persona = this.asignarDatosPersona(usuarioNuevo);
			persona.setSegtUsuario(new Usuario());
			persona.getSegtUsuario().setIdUsuario(usuarioNuevo.getIdUsuario());
			
			factoryDAO.getPersonaDAOImpl().create(persona);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(usuario.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "registrarUsuario", EnumTipoTransaccion.CREATE));
			
		} catch (Exception e) {
			slf4jLogger.info("Error al registrar el usuario {}", e.getMessage());
			throw new SeguridadesException("No se pudo registrar el usuario");
		}
		
		return usuarioNuevo;
	}
	
	private Persona asignarDatosPersona (Usuario usuario) {
		
		Persona persona = new Persona();
		persona.setPerNombres(usuario.getNombresUsuario());
		persona.setPerApellidos(usuario.getApellidosUsuario());
		persona.setPerCi(usuario.getCiUsuario());
		persona.setPerEmail(usuario.getEmailUsuario());
		
		return persona;
	}
	
	/**
	 * Validar si una persona ya se encuentra registrada en la bd mediate la CI
	 * @param persona
	 * @return
	 */
	private Boolean verificarPersonaRepetida (Usuario usuario) throws SeguridadesException {
		
		if (StringUtils.isNotBlank(usuario.getCiUsuario())) {
			
			Persona persona = new Persona();
			persona.setPerCi(usuario.getCiUsuario());
			
			List<Persona> personasEncontras = factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(persona);
			if (CollectionUtils.isEmpty(personasEncontras)) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) throws SeguridadesException {
		slf4jLogger.info("actualizarUsuario");
		
		Usuario usuarioActualizado = null;
		
		try {
			usuario.setFechaModificacion(UtilAplication.obtenerFechaActual());
			usuarioActualizado = factoryDAO.getUsuarioDAOImpl().update(usuario);
			
			factoryDAO.getHistoricoTransaccioneDAOImpl()
					.registrarHistoricoTransaccion(new AuditoriaDTO(usuario.getUsuarioRegistro()
							.getIdUsuario(), ServicioAdministracionImpl.class.getName(), "actualizarUsuario", EnumTipoTransaccion.UPDATE));
			
		} catch (Exception e) {
			slf4jLogger.info("No se pudo actualizar el usuario {}" , e.getMessage());
			throw new SeguridadesException("No se pudo actualizar el usuario");
		}
		
		return usuarioActualizado;
	}

	@Override
	public List<Usuario> buscarUsuarios(Usuario usuario) throws SeguridadesException {
		slf4jLogger.info("buscarUsuarios");
		
		List<Usuario> usuariosCol = null;
		
		try {
			usuariosCol = factoryDAO.getUsuarioDAOImpl().obtenerUsuarioCriterios(usuario);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarUsuarios {}" , e.getMessage());
			throw new SeguridadesException("Error al buscarUsuarios");
		}
		
		return usuariosCol;
	}
	

}
