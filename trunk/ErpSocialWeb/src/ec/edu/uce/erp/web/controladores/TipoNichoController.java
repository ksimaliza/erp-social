package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.TipoNichoDataManager;

@ViewScoped
@ManagedBean (name = "tipoNichoController")
public class TipoNichoController extends BaseController {
	
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(TipoNichoController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@ManagedProperty(value="#{tipoNichoDataManager}")
	private TipoNichoDataManager tipoNichoDataManager;

	public TipoNichoDataManager getTipoNichoDataManager() {
		return tipoNichoDataManager;
	}

	public void setTipoNichoDataManager(TipoNichoDataManager tipoNichoDataManager) {
		this.tipoNichoDataManager = tipoNichoDataManager;
	}
	
	public TipoNichoController() {
		
	}
	
	
public void registrarTipoNicho () {
		
		slf4jLogger.info("registrarTipoNicho");
		try {
			this.tipoNichoDataManager.getTipoNichoInsertar().setTniEmpresa(getEmpresaTbl().getEmrPk());
			TipoNichoDTO tipoNichoNuevo=this.servicioEucaristia.createOrUpdateTipoNicho(this.tipoNichoDataManager.getTipoNichoInsertar());
			
			if (tipoNichoNuevo != null) {
				tipoNichoDataManager.setTipoNichoInsertar(new TipoNichoDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.tipo.nicho.registrar.exito");
			}
			
			buscarTipoNicho();
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarTipoNicho () {
		slf4jLogger.info("buscarTipoNicho");
		
		List<TipoNichoDTO> listaTipoNicho=null;
		
		try {
			this.tipoNichoDataManager.getTipoNichoBuscar().setTniEmpresa(getEmpresaTbl().getEmrPk());
			listaTipoNicho = this.servicioEucaristia.buscarTipoNicho(tipoNichoDataManager.getTipoNichoBuscar());
			
			if (CollectionUtils.isEmpty(listaTipoNicho) && listaTipoNicho.size()==0) {
				this.tipoNichoDataManager.setTipoNichoDTOs(new ArrayList<TipoNichoDTO>());
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.tipoNichoDataManager.setTipoNichoDTOs(listaTipoNicho);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarTipoNicho {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosTipoNicho (TipoNichoDTO tipoNichoDTO) {
		try {
			TipoNichoDTO tipoNichoEncontrado = servicioEucaristia.obtenerTipoNichoPorId(tipoNichoDTO.getTniCodigo());
			this.tipoNichoDataManager.setTipoNichoInsertar(tipoNichoEncontrado);
									
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargar los cargarDatosTipoNicho seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosTipoNicho seleccionado");
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	


}
