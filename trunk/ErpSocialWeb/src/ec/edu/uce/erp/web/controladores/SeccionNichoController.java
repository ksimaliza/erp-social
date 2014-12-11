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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.SeccionNichoDataManager;


@ViewScoped
@ManagedBean (name = "seccionNichoController")

public class SeccionNichoController extends BaseController {
	private static final long serialVersionUID = 1L;

private static final Logger slf4jLogger = LoggerFactory.getLogger(SeccionNichoController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@ManagedProperty(value="#{seccionNichoDataManager}")
	private SeccionNichoDataManager seccionNichoDataManager;

	public SeccionNichoDataManager getSeccionNichoDataManager() {
		return seccionNichoDataManager;
	}

	public void setSeccionNichoDataManager(SeccionNichoDataManager seccionNichoDataManager) {
		this.seccionNichoDataManager = seccionNichoDataManager;
	}
	
	
	public SeccionNichoController() {
		
	}

public void registrarSeccionNicho () {
		
		slf4jLogger.info("registrarSeccionNicho");
		try {
			this.seccionNichoDataManager.getSeccionNichoInsertar().setEucCatalogo(this.servicioEucaristia.obtenerSeccionNichoPorId(29));
			CatalogoEucaristiaDTO SeccionNichoNuevo=this.servicioEucaristia.createOrUpdateSeccionNicho(this.seccionNichoDataManager.getSeccionNichoInsertar());
			
			if (SeccionNichoNuevo != null) {
				seccionNichoDataManager.setSeccionNichoInsertar(new CatalogoEucaristiaDTO());
				MensajesWebController.aniadirMensajeInformacion("Se ha registrado correctamente nueva sección");
			}
			buscarSeccionNicho();
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarSeccionNicho () {
		slf4jLogger.info("buscarSeccionNicho");
		
		List<CatalogoEucaristiaDTO> listaSeccionNicho=null;
		
		try {
			listaSeccionNicho = this.servicioEucaristia.buscarSeccionNicho(seccionNichoDataManager.getSeccionNichoBuscar());
			
			if (CollectionUtils.isEmpty(listaSeccionNicho) && listaSeccionNicho.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.seccionNichoDataManager.setSeccionNichoDTOs(listaSeccionNicho);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSeccionNicho {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosSeccionNicho (CatalogoEucaristiaDTO seccionNichoDTO) {
		try {
			CatalogoEucaristiaDTO seccionNichoEncontrado = servicioEucaristia.obtenerSeccionNichoPorId(seccionNichoDTO.getCatCodigo());
			this.seccionNichoDataManager.setSeccionNichoInsertar(seccionNichoEncontrado);
									
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargar los cargarDatosSeccionNicho seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosSeccionNicho seleccionado");
		}
	}
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
