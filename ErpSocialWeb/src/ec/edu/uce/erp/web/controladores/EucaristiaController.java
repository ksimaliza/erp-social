package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EucaristiaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.EucaristiaDataManager;

@ViewScoped
@ManagedBean (name = "eucaristiaController")
public class EucaristiaController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EucaristiaController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@ManagedProperty(value="#{eucaristiaDataManager}")
	private EucaristiaDataManager eucaristiaDataManager;

	public EucaristiaDataManager getEucaristiaDataManager() {
		return eucaristiaDataManager;
	}

	public void setEucaristiaDataManager(EucaristiaDataManager eucaristiaDataManager) {
		this.eucaristiaDataManager = eucaristiaDataManager;
	}
	
	public EucaristiaController() {
		
	}
	
	

@PostConstruct
private void init(){
	
	buscarSacerdote();
}

public void registrarEucaristia () throws ParseException {
	
	slf4jLogger.info("registrarEucaristia");
	EucaristiaVO eucaristiaVO;
	SacerdoteDTO sacerdoteDTO;
		
	try {
		
		eucaristiaVO= new EucaristiaVO();
		sacerdoteDTO=new SacerdoteDTO();
		
		sacerdoteDTO.setSacCodigo(eucaristiaDataManager.getCodigoSacerdote());
		
		eucaristiaDataManager.getEucaristiaInsertar().setEucFechaHora(new Timestamp(eucaristiaDataManager.getFecha().getTime()));
		
		eucaristiaVO.setEucaristiaDTO(eucaristiaDataManager.getEucaristiaInsertar());
		eucaristiaVO.setSacerdoteDTO(sacerdoteDTO);
		
		EucaristiaDTO eucaristiaNuevo= this.servicioEucaristia.createOrUpdateEucaristia(eucaristiaVO); 
					
		if (eucaristiaNuevo != null) {
			eucaristiaDataManager.setEucaristiaInsertar(new EucaristiaDTO());
			eucaristiaDataManager.setCodigoSacerdote(0);
			eucaristiaDataManager.setFecha(new Date());
						
			MensajesWebController.aniadirMensajeInformacion("erp.despacho.eucaristia.registrar.exito");
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info(e.toString());
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}


public void buscar() {
	slf4jLogger.info("buscarEucaristia");
	List<EucaristiaListDTO> listResultado=new ArrayList<EucaristiaListDTO>();
	try {
		listResultado = this.servicioEucaristia.buscarEucaristia(eucaristiaDataManager.getEucaristiaListDTO());
			
		if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
			MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
		} else {
			this.eucaristiaDataManager.setEucaristiaListDTOs(listResultado);
			
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al buscarEucaristia {} ", e);
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
}


public void buscarSacerdote () {
	slf4jLogger.info("buscarSacerdote");
	
	List<SacerdoteListDTO> listaSacerdote=null;
	
	try {
						
		listaSacerdote=this.servicioEucaristia.buscarSacerdote(new SacerdoteListDTO());
		
		if (CollectionUtils.isEmpty(listaSacerdote) && listaSacerdote.size()==0) {
			MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
		} else {
			this.eucaristiaDataManager.setSacerdoteDTOs(listaSacerdote);
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al buscarSacerdote {} ", e);
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}


public void cargarDatosEucaristia (EucaristiaListDTO eucaristiaListDTO) {
	try {
		EucaristiaVO eucaristiaEncontrado=servicioEucaristia.obtenerEucaristiaPorId(eucaristiaListDTO);
		this.eucaristiaDataManager.setEucaristiaInsertar(eucaristiaEncontrado.getEucaristiaDTO());
		this.eucaristiaDataManager.setCodigoSacerdote(eucaristiaEncontrado.getEucaristiaDTO().getEucSacerdoteBean().getSacCodigo());		
						
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al cargarDatosEucaristia {}", e.getMessage());
		MensajesWebController.aniadirMensajeError("Error al cargarDatosEucaristia seleccionado");
	}
}

@Override
public void refrescarFormulario() {
	// TODO Auto-generated method stub
	
}


}
