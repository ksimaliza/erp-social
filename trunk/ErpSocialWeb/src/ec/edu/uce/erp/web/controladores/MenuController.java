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
import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.MenuDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name="menuController")
public class MenuController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MenuController.class);
	
	@EJB
	ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{menuDataManager}")
	private MenuDataManager menuDataManager;
	
	private List<Object> modulosSeleccionados;
	private Boolean dialogVisible = Boolean.FALSE;
	
	/**
	 * @param menuDataManager the menuDataManager to set
	 */
	public void setMenuDataManager(MenuDataManager menuDataManager) {
		this.menuDataManager = menuDataManager;
	}
	
	public MenuController (){}
	
	@PostConstruct
	public void inicializarObjetos () {
		this.modulosSeleccionados = new ArrayList<Object>();
	}
	
	/*
	 * Metodos
	 */
	
	/**
	 * Registrar menu en la BD
	 */
	public void registrarMenu () {
		
		slf4jLogger.info("registrarMenu");
		try {
			
			this.menuDataManager.getMenuInstancia().setSegtModulos(this.asignarModulosSeleccionados());
			Menu menuNuevo = this.servicioAdministracion.registrarMenu(this.menuDataManager.getMenuInstancia());
			
			if (menuNuevo != null) {
				menuDataManager.setMenuInstancia(new Menu());
				menuDataManager.getListaMenu().add(menuNuevo);
				dialogVisible = Boolean.FALSE;
				getModulosSeleccionados().clear();
				MensajesWebController.aniadirMensajeInformacion("erp.menu.informacion.registrar");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al registrar el menu {}", e.getMessage());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarMenu () {
		slf4jLogger.info("buscarMenu");
		
		try {
			List<Menu> menuCol = servicioAdministracion.buscarMenu(menuDataManager.getMenuBuscar());
			
			if (CollectionUtils.isEmpty(menuCol)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				menuDataManager.setListaMenu(new ArrayList<Menu>());
			} else {
				menuDataManager.setListaMenu(menuCol);
			}
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void actualizarMenu () {
		
		slf4jLogger.info("actualizarMenu");
		
		try {
			
			this.menuDataManager.getMenuEditar().setSegtModulos(this.asignarModulosSeleccionados());
			this.servicioAdministracion.actualizarMenu(this.menuDataManager.getMenuEditar());
			this.menuDataManager.setMenuEditar(new Menu());
			MensajesWebController.aniadirMensajeInformacion("erp.menu.informacion.actualizar");
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizar el menu {}", e.getMessage());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void asignarDatosMenu (Menu menu) {
		
		try {
			
			getModulosSeleccionados().clear();
			if (CollectionUtils.isNotEmpty(menu.getSegtModulos())) {
				for (Modulo modulo : menu.getSegtModulos()) {
					getModulosSeleccionados().add(modulo.getIdModulo());
				}
			}
			
		} catch (Exception e) {
			slf4jLogger.info("Error al cargar los datos para editar el menu seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("erp.menu.error.datos.editar");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Modulo> asignarModulosSeleccionados() {
		
		List<Modulo> moduloCol = null;
		
		if (CollectionUtils.isNotEmpty(getModulosSeleccionados())) {
			
			moduloCol = (List<Modulo>) CollectionUtils.collect(getModulosSeleccionados(), new Transformer() {
				
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
	
	/*
	 * Propiedades
	 */
	
	/**
	 * @return the modulosSeleccionados
	 */
	public List<Object> getModulosSeleccionados() {
		return modulosSeleccionados;
	}

	/**
	 * @param modulosSeleccionados the modulosSeleccionados to set
	 */
	public void setModulosSeleccionados(List<Object> modulosSeleccionados) {
		this.modulosSeleccionados = modulosSeleccionados;
	}

	/**
	 * @return the dialogVisible
	 */
	public Boolean getDialogVisible() {
		return dialogVisible;
	}

	/**
	 * @param dialogVisible the dialogVisible to set
	 */
	public void setDialogVisible(Boolean dialogVisible) {
		this.dialogVisible = dialogVisible;
	}

}
