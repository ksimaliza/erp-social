/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Parametro;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ParametroDataManager;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean (name = "parametroController")
public class ParametroController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ParametroController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{parametroDataManager}")
	private ParametroDataManager parametroDataManager;
	
	/**
	 * @param parametroDataManager the parametroDataManager to set
	 */
	public void setParametroDataManager(ParametroDataManager parametroDataManager) {
		this.parametroDataManager = parametroDataManager;
	}
	
	public ParametroController () {}
	
	public void buscarParametros () {
		slf4jLogger.info("buscarParametros");
		
		try {
			List<Parametro> listParametro = servicioAdministracion.buscarParametrosCriterios(this.parametroDataManager.getParametroBuscar());
			
			if (CollectionUtils.isEmpty(listParametro)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.parametroDataManager.getListParametro().clear();
			} else {
				this.parametroDataManager.setListParametro(listParametro);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarParametros {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void actualizarParametro () {
		
		slf4jLogger.info("actualizarParametro");
		
		try {
			
			this.parametroDataManager.getParametroEditar().setUsuarioRegistro(this.parametroDataManager.getUsuarioSession());
			servicioAdministracion.actualizarParametro(this.parametroDataManager.getParametroEditar());
			MensajesWebController.aniadirMensajeInformacion("erp.parametro.actualizar.exito");
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizarParametro {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void activarParametro () {
		slf4jLogger.info("activarParametro");
		try {
			
			this.parametroDataManager.getParametroEditar().setUsuarioRegistro(this.parametroDataManager.getUsuarioSession());
			this.parametroDataManager.getParametroEditar().setEstado(this.parametroDataManager.getEstadoActivo());
			servicioAdministracion.actualizarParametro(this.parametroDataManager.getParametroEditar());
			MensajesWebController.aniadirMensajeInformacion("erp.parametro.actualizar.exito");
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizarParametro {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void inactivarParametro () {
		slf4jLogger.info("inactivarParametro");
		try {
			
			this.parametroDataManager.getParametroEditar().setUsuarioRegistro(this.parametroDataManager.getUsuarioSession());
			this.parametroDataManager.getParametroEditar().setEstado(this.parametroDataManager.getEstadoInactivo());
			servicioAdministracion.actualizarParametro(this.parametroDataManager.getParametroEditar());
			MensajesWebController.aniadirMensajeInformacion("erp.parametro.actualizar.exito");
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizarParametro {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
