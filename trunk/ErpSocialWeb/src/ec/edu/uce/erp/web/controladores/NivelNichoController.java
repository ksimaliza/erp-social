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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.NivelNichoDataManager;

@ViewScoped
@ManagedBean (name = "nivelNichoController")
public class NivelNichoController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(NivelNichoController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@ManagedProperty(value="#{nivelNichoDataManager}")
	private NivelNichoDataManager nivelNichoDataManager;

	public NivelNichoDataManager getNivelNichoDataManager() {
		return nivelNichoDataManager;
	}

	public void setNivelNichoDataManager(NivelNichoDataManager nivelNichoDataManager) {
		this.nivelNichoDataManager = nivelNichoDataManager;
	}
	
	
	public NivelNichoController() {
		
	}

public void registrarNivelNicho () {
		
		slf4jLogger.info("registrarNivelNicho");
		try {
			
			NivelNichoDTO NivelNichoNuevo=this.servicioEucaristia.createOrUpdateNivelNicho(this.nivelNichoDataManager.getNivelNichoInsertar());
			
			if (NivelNichoNuevo != null) {
				nivelNichoDataManager.setNivelNichoInsertar(new NivelNichoDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.nivel.nicho.registrar.exito");
			}
			buscarNivelNicho();
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarNivelNicho () {
		slf4jLogger.info("buscarNivelNicho");
		
		List<NivelNichoDTO> listaNivelNicho=null;
		
		try {
			listaNivelNicho = this.servicioEucaristia.buscarNivelNicho(nivelNichoDataManager.getNivelNichoBuscar());
			
			if (CollectionUtils.isEmpty(listaNivelNicho) && listaNivelNicho.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.nivelNichoDataManager.setNivelNichoDTOs(listaNivelNicho);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarNivelNicho {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosNivelNicho (NivelNichoDTO nivelNichoDTO) {
		try {
			NivelNichoDTO nivelNichoEncontrado = servicioEucaristia.obtenerNivelNichoPorId(nivelNichoDTO.getNniCodigo());
			this.nivelNichoDataManager.setNivelNichoInsertar(nivelNichoEncontrado);
									
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargar los cargarDatosNivelNicho seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosNivelNicho seleccionado");
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	



}
