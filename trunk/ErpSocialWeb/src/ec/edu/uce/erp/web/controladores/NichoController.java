package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.NichoDataManager;

@ViewScoped
@ManagedBean (name = "nichoController")
public class NichoController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(NichoController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@ManagedProperty(value="#{nichoDataManager}")
	private NichoDataManager nichoDataManager;

	public NichoDataManager getNichoDataManager() {
		return nichoDataManager;
	}

	public void setNichoDataManager(NichoDataManager nichoDataManager) {
		this.nichoDataManager = nichoDataManager;
	}
	
public NichoController() {
		
	}
	
	

@PostConstruct
private void init(){
	
	buscarNivelNicho();
	buscarSeccion();
	buscarTipoNicho();
}

public void registrarNicho () {
	
	slf4jLogger.info("registrarNicho");
	NichoDTO nicho;
	NivelNichoDTO nivelNicho;
	TipoNichoDTO tipoNicho;
	CatalogoEucaristiaDTO seccionNicho;
	
	try {
		
		nicho=new NichoDTO();
		nivelNicho=new NivelNichoDTO();
		tipoNicho=new TipoNichoDTO();
		seccionNicho=new CatalogoEucaristiaDTO();
		
		nivelNicho.setNniCodigo(nichoDataManager.getCodigoNivelNicho());
		tipoNicho.setTniCodigo(nichoDataManager.getCodigoTipoNicho());
		seccionNicho.setCatCodigo(nichoDataManager.getCodigoSeccion());
		
		nicho.setEucNivelNicho(nivelNicho);
		nicho.setEucTipoNicho(tipoNicho);
		nicho.setNicSeccion(seccionNicho.getCatCodigo());
		nicho.setNicDescripcion(nichoDataManager.getNichoInsertar().getNicDescripcion());
		
		NichoDTO nichoNuevo=this.servicioEucaristia.createOrUpdateNicho(nicho);
					
		if (nichoNuevo != null) {
			MensajesWebController.aniadirMensajeInformacion("erp.despacho.nicho.registrar.exito");
			nichoDataManager.setCodigoNivelNicho(0);
			nichoDataManager.setNichoInsertar(new NichoDTO());
			nichoDataManager.setCodigoSeccion(0);
			nichoDataManager.setCodigoTipoNicho(0);
			
		}
		buscar();
		
	} catch (SeguridadesException e) {
		slf4jLogger.info(e.toString());
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}


public void buscar() {
	slf4jLogger.info("buscarNicho");
	List<NichoListDTO> listResultado=new ArrayList<NichoListDTO>();
	try {
		listResultado = this.servicioEucaristia.buscarNicho(nichoDataManager.getNichoListDTO());
		
		if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
			MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
		} else {
			this.nichoDataManager.setNichoDTOs(listResultado);
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al buscar el buscarNicho {} ", e);
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
}


public void buscarNivelNicho () {
	slf4jLogger.info("buscarNivelNicho");
	
	List<NivelNichoDTO> listaNivelNicho=null;
	
	try {
		listaNivelNicho = this.servicioEucaristia.buscarNivelNicho(new NivelNichoDTO());
		
		if (CollectionUtils.isEmpty(listaNivelNicho) && listaNivelNicho.size()==0) {
			MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
		} else {
			
			this.nichoDataManager.setNivelNichoDTOs(listaNivelNicho);
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al buscarNivelNicho {} ", e);
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}

public void buscarTipoNicho () {
	slf4jLogger.info("buscarTipoNicho");
	
	List<TipoNichoDTO> listaTipoNicho=null;
	
	try {
		listaTipoNicho = this.servicioEucaristia.buscarTipoNicho(new TipoNichoDTO());
		
		if (CollectionUtils.isEmpty(listaTipoNicho) && listaTipoNicho.size()==0) {
			MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
		} else {
			this.nichoDataManager.setTipoNichoDTOs(listaTipoNicho);
			
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al buscarTipoNicho {} ", e);
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}

public void buscarSeccion  () {
	slf4jLogger.info("buscarCatalogo");
	
	List<CatalogoEucaristiaDTO> listaCatalogo=null;
	
	try {
		CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
		cat.setCatCodigo(29);
		listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
		
		if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
			MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
		} else {
			this.nichoDataManager.setSeccionEucaristiaDTOs(listaCatalogo);
						
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al buscarCatalogo {} ", e);
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}

public void cargarDatosNicho (NichoListDTO nicho) {
	try {
		
		NichoDTO nichoEncontrado=servicioEucaristia.obtenerNichoPorId(nicho);
		this.nichoDataManager.setNichoInsertar(nichoEncontrado);
		this.nichoDataManager.setCodigoNivelNicho(nichoEncontrado.getEucNivelNicho().getNniCodigo());
		this.nichoDataManager.setCodigoSeccion(nichoEncontrado.getNicSeccion());
		this.nichoDataManager.setCodigoTipoNicho(nichoEncontrado.getEucTipoNicho().getTniCodigo());
			
						
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al cargarDatosNicho {}", e.getMessage());
		MensajesWebController.aniadirMensajeError("Error al cargarDatosNicho seleccionado");
	}
}


public void cancel()
{
	nichoDataManager.setCodigoNivelNicho(0);
	nichoDataManager.setNichoInsertar(new NichoDTO());
	nichoDataManager.setCodigoSeccion(0);
	nichoDataManager.setCodigoTipoNicho(0);
	RequestContext.getCurrentInstance().execute("dlgNuevoContrato.hide()");
}

@Override
public void refrescarFormulario() {
	// TODO Auto-generated method stub
	
}


	

}
