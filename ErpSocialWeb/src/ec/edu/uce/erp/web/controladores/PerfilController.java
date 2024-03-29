/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.PerfilDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name = "perfilController")
public class PerfilController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{perfilDataManager}")
	private PerfilDataManager perfilDataManager;
	
	/**
	 * @param perfilDataManager the perfilDataManager to set
	 */
	public void setPerfilDataManager(PerfilDataManager perfilDataManager) {
		this.perfilDataManager = perfilDataManager;
	}
	
	private List<Object> modulosSeleccionadosCol;
	
	public PerfilController () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		this.modulosSeleccionadosCol = new ArrayList<Object>();
	}
	
	/*
	 * Medodos
	 */
	
	public void registrarPerfil () {
		
		slf4jLogger.info("registrarPerfil");
		
		try {
			
			this.perfilDataManager.getPerfilInstancia().setSegtModulos(this.asignarModulosSeleccionados());
			this.perfilDataManager.getPerfilInstancia().setUsuarioRegistro(this.perfilDataManager.getUsuarioSession());
			
			Perfil perfilDTO = servicioAdministracion.registrarPerfil(this.perfilDataManager.getPerfilInstancia());
			
			if (perfilDTO != null) {
				MensajesWebController.aniadirMensajeInformacion("Perfil Registrado Correctamente");
				this.perfilDataManager.setPerfilInstancia(new Perfil());
				this.perfilDataManager.getListaPerfiles().add(perfilDTO);
			} else {
				MensajesWebController.aniadirMensajeError("Error al registrar el perfil");
			}
			
			this.getModulosSeleccionadosCol().clear();
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al registrar el perfil {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 */
	public void buscarPerfiles () {
		
		slf4jLogger.info("buscarPerfiles");
		
		try {
			
			List<Perfil> perfilCol = this.servicioAdministracion.buscarPerfiles(this.perfilDataManager.getPerfilBuscar());
			
			if (CollectionUtils.isEmpty(perfilCol) || perfilCol.size()<1) {
				
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.perfilDataManager.setListaPerfiles(new ArrayList<Perfil>());
				this.perfilDataManager.setPerfilBuscar(new Perfil());
				
			} else {
				this.perfilDataManager.setListaPerfiles(perfilCol);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarPerfiles {}", e.getMessage());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	/**
	 * Actualizar un perfil en la BD
	 */
	public void actualizarPerfil () {
		
		slf4jLogger.info("actualizarPerfil");
		
		try {
			this.perfilDataManager.getPerfilEditar().setUsuarioRegistro(this.perfilDataManager.getUsuarioSession());
			this.perfilDataManager.getPerfilEditar().setSegtModulos(this.asignarModulosSeleccionados());
			this.servicioAdministracion.actualizarPerfil(this.perfilDataManager.getPerfilEditar());
			MensajesWebController.aniadirMensajeInformacion("erp.perfil.informacion.actualizar");
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizarPerfill {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Modulo> asignarModulosSeleccionados() {
		
		slf4jLogger.info("asignarModulosSeleccionados");
		
		List<Modulo> moduloCol = null;
		
		if (CollectionUtils.isNotEmpty(getModulosSeleccionadosCol())) {
			
			moduloCol = (List<Modulo>) CollectionUtils.collect(getModulosSeleccionadosCol(), new Transformer() {
				
				@Override
				public Object transform(Object arg0) {
					Modulo moduloDTO = new Modulo();
					moduloDTO.setIdModulo(Integer.valueOf(arg0.toString()));
					return moduloDTO;
				}
			});
		}
		
		return moduloCol;
	}
	
	public void cargarDatosPerfil (Perfil perfil) {
		try {
			Perfil perfilEncontrado = servicioAdministracion.obtenerPerfilPorId(perfil.getIdPerfil());
			this.asignarDatosEditarPerfil(perfilEncontrado);
			this.perfilDataManager.setPerfilEditar(perfilEncontrado);
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargar los datos del perfil seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargar los datos del perfil seleccionado");
		}
	}
	
	private void asignarDatosEditarPerfil(Perfil perfil) {
		
		slf4jLogger.info("asignarDatosEditarPerfil");
		
		getModulosSeleccionadosCol().clear();
		if (CollectionUtils.isNotEmpty(perfil.getSegtModulos())) {
			
			for (Modulo modulo : perfil.getSegtModulos()) {
				getModulosSeleccionadosCol().add(modulo.getIdModulo());
			}
			
		}
		
	}
	
	public void activarPerfil () {
		
		slf4jLogger.info("activarPerfil");
		
		try {
			this.perfilDataManager.getPerfilEditar().setUsuarioRegistro(this.perfilDataManager.getUsuarioSession());
			this.perfilDataManager.getPerfilEditar().setEstado(perfilDataManager.getEstadoActivo());
			this.servicioAdministracion.actualizarPerfil(this.perfilDataManager.getPerfilEditar());
			MensajesWebController.aniadirMensajeInformacion("erp.perfil.informacion.actualizar");
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al activarPerfil {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void inactivarPerfil () {
		
		slf4jLogger.info("inactivarPerfil");
		
		try {
			this.perfilDataManager.getPerfilEditar().setUsuarioRegistro(this.perfilDataManager.getUsuarioSession());
			this.perfilDataManager.getPerfilEditar().setEstado(perfilDataManager.getEstadoInactivo());
			this.servicioAdministracion.actualizarPerfil(this.perfilDataManager.getPerfilEditar());
			MensajesWebController.aniadirMensajeInformacion("erp.perfil.informacion.actualizar");
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al inactivarPerfil {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void validarInactivarPerfil (Perfil perfil) {
		
		if (perfil != null) {
			
		}
		
	}
	
	public void cargarModulosPerfil (Perfil perfil) {
		try {
			if (CollectionUtils.isEmpty(perfil.getNpColModulos())) {
				Modulo modulo = new Modulo();
				modulo.setNpIdPerfil(perfil.getIdPerfil());
					perfil.setNpColModulos(this.servicioAdministracion.buscarModulos(modulo));
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarModulosPerfil {}", e);
			MensajesWebController.aniadirMensajeError("No se pudo obtener los modulos asignados al perfil");
		}
	}

	/*
	 * Propiedades
	 */
	

	/**
	 * @return the modulosSeleccionadosCol
	 */
	public List<Object> getModulosSeleccionadosCol() {
		return modulosSeleccionadosCol;
	}

	/**
	 * @param modulosSeleccionadosCol the modulosSeleccionadosCol to set
	 */
	public void setModulosSeleccionadosCol(List<Object> modulosSeleccionadosCol) {
		this.modulosSeleccionadosCol = modulosSeleccionadosCol;
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
