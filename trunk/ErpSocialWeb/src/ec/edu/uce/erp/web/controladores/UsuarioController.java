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
	
	/**
	 * Obtener los perfiles asociados a una empresa
	 */
	public void obtenerPerfilesEmpresa () {
		
		if (idEmpresaSeleccionada != null) {
			
			Perfil perfilPlantilla = new Perfil();
			perfilPlantilla.setNpEmpresaDTO(new Empresa());
			perfilPlantilla.getNpEmpresaDTO().setEmrPk(idEmpresaSeleccionada);
			
			try {
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
					this.catalogoPerfiles.clear();
					MensajesWebController.aniadirMensajeAdvertencia("No se encontraron perfiles para la empresa seleccionada");
				}
				
			} catch (SeguridadesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
