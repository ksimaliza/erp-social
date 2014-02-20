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
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.UsuarioDataManager;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean (name = "usuarioController")
public class UsuarioController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(UsuarioController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{usuarioDataManager}")
	private UsuarioDataManager usuarioDataManager;
	
	/**
	 * @param usuarioDataManager the usuarioDataManager to set
	 */
	public void setUsuarioDataManager(UsuarioDataManager usuarioDataManager) {
		this.usuarioDataManager = usuarioDataManager;
	}
	
	private Integer idEmpresaSeleccionada;
	private List<SelectItem> catalogoPerfiles;
	
	public UsuarioController () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		this.catalogoPerfiles = new ArrayList<SelectItem>();
	}
	
	/*
	 * Metodos
	 */
	
	/**
	 * Registrar un usuario en la BD
	 */
	public void registrarUsuario () {
		slf4jLogger.info("registrarUsuario");
		try {
			
			this.usuarioDataManager.getUsuarioInstancia().getEmpresaTbl().setEmrPk(idEmpresaSeleccionada);
			this.servicioAdministracion.registrarUsuario(usuarioDataManager.getUsuarioInstancia());
			this.usuarioDataManager.setUsuarioInstancia(new Usuario());
			this.usuarioDataManager.getUsuarioInstancia().setSegtPerfil(new Perfil());
			MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.registrar");
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarUsuario () {
		slf4jLogger.info("buscarUsuario");
		
		try {
			
			List<Usuario> usuariosCol = this.servicioAdministracion.buscarUsuarios(this.usuarioDataManager.getUsuarioBuscar());
			
			if (CollectionUtils.isEmpty(usuariosCol)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.usuarioDataManager.getListaUsuario().clear();
			} else {
				this.usuarioDataManager.setListaUsuario(usuariosCol);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarUsuario {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void actualizarUsuario () {
		
		slf4jLogger.info("actualizarUsuario");
		
		Usuario usuarioUpdate = null;
		Usuario usuarioClone = SerializationUtils.clone(this.usuarioDataManager.getUsuarioEditar());
		
		try {
			
			this.usuarioDataManager.getUsuarioEditar().getEmpresaTbl().setEmrPk(idEmpresaSeleccionada);
			usuarioUpdate = this.servicioAdministracion.actualizarUsuario(usuarioDataManager.getUsuarioEditar());
			this.usuarioDataManager.setUsuarioEditar(new Usuario());
			this.usuarioDataManager.getUsuarioEditar().setSegtPerfil(new Perfil());
			MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.actualizar");
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizarUsuario {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		} finally {
			if (usuarioUpdate == null) {
				this.usuarioDataManager.setUsuarioEditar(usuarioClone);
			}
		}
		
	}
	
	/**
	 * Obtener los perfiles asociados a una empresa
	 */
	public void obtenerPerfilesEmpresa () {
		
		if (idEmpresaSeleccionada != null) {
			
			Perfil perfilPlantilla = new Perfil();
			perfilPlantilla.setNpEmpresaDTO(new Empresa());
			perfilPlantilla.getNpEmpresaDTO().setEmrPk(idEmpresaSeleccionada);
			
			try {
				
				this.catalogoPerfiles.clear();
				
				List<Perfil> perfilCol = this.servicioAdministracion.buscarPerfileEmpresa(perfilPlantilla);
				
				if (CollectionUtils.isNotEmpty(perfilCol)) {
					
					CollectionUtils.collect(perfilCol, new Transformer() {
						
						@Override
						public Object transform(Object arg0) {
							Perfil perfilDTO = (Perfil)arg0;
							return new SelectItem(perfilDTO.getIdPerfil(), perfilDTO.getNombrePerfil());
						}
					}, catalogoPerfiles);
				} else {
					MensajesWebController.aniadirMensajeAdvertencia("erp.usuario.mensaje.error.perfiles");
				}
				
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al obtenerPerfilesEmpresa {}", e.toString());
				MensajesWebController.aniadirMensajeAdvertencia("erp.usuario.mensaje.error.perfiles");
			}
			
		}
		
	}
	
	public void cargarDatosUsuario (Usuario usuario) {
		
		if (usuario != null) {
			this.idEmpresaSeleccionada = usuario.getEmpresaTbl().getEmrPk();
			obtenerPerfilesEmpresa();
		}
		
	}
	
	public void desactivarUsuario () {
		
		slf4jLogger.info("desactivarUsuario");
		
		try {
			this.usuarioDataManager.getUsuarioEditar().setEstado(usuarioDataManager.getEstadoInactivo());
			Usuario usuario = servicioAdministracion.actualizarUsuario(this.usuarioDataManager.getUsuarioEditar());
			
			if (usuario != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.actualizar");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al desactivarUsuario {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	public void activarUsuario () {
		
		slf4jLogger.info("activarUsuario");
		
		try {
			this.usuarioDataManager.getUsuarioEditar().setEstado(usuarioDataManager.getEstadoActivo());
			Usuario usuario = servicioAdministracion.actualizarUsuario(this.usuarioDataManager.getUsuarioEditar());
			
			if (usuario != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.usuario.mensaje.actualizar");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al desactivarUsuario {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	/*
	 * Propiedades
	 */

	/**
	 * @return the idEmpresaSeleccionada
	 */
	public Integer getIdEmpresaSeleccionada() {
		return idEmpresaSeleccionada;
	}

	/**
	 * @param idEmpresaSeleccionada the idEmpresaSeleccionada to set
	 */
	public void setIdEmpresaSeleccionada(Integer idEmpresaSeleccionada) {
		this.idEmpresaSeleccionada = idEmpresaSeleccionada;
	}

	/**
	 * @return the catalogoPerfiles
	 */
	public List<SelectItem> getCatalogoPerfiles() {
		return catalogoPerfiles;
	}

	/**
	 * @param catalogoPerfiles the catalogoPerfiles to set
	 */
	public void setCatalogoPerfiles(List<SelectItem> catalogoPerfiles) {
		this.catalogoPerfiles = catalogoPerfiles;
	}

}
