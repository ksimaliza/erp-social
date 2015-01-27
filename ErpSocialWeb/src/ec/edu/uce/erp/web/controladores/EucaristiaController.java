package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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

import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.FiltroFechaDTO;
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
	buscar();
	buscarSacerdote();
}

public void registrarEucaristia () {
	
	slf4jLogger.info("registrarEucaristia");
	EucaristiaVO eucaristiaVO;
	SacerdoteDTO sacerdoteDTO;
		
	try {
		
		eucaristiaVO= new EucaristiaVO();
		sacerdoteDTO=new SacerdoteDTO();
		
		sacerdoteDTO.setSacCodigo(eucaristiaDataManager.getCodigoSacerdote());
		
		eucaristiaDataManager.getEucaristiaInsertar().setEucFechaHora(new Timestamp(eucaristiaDataManager.getFecha().getTime()));
		eucaristiaDataManager.getEucaristiaInsertar().setEucEmpresa(getEmpresaTbl().getEmrPk());
		eucaristiaVO.setEucaristiaDTO(eucaristiaDataManager.getEucaristiaInsertar());
		eucaristiaVO.setSacerdoteDTO(sacerdoteDTO);
		
		EucaristiaDTO eucaristiaNuevo= this.servicioEucaristia.createOrUpdateEucaristia(eucaristiaVO); 
					
		if (eucaristiaNuevo != null) {
			eucaristiaDataManager.setEucaristiaInsertar(new EucaristiaDTO());
			eucaristiaDataManager.setCodigoSacerdote(0);
			eucaristiaDataManager.setFecha(new Date());
						
			MensajesWebController.aniadirMensajeInformacion("erp.despacho.eucaristia.registrar.exito");
		}
		buscar();
	} catch (SeguridadesException e) {
		slf4jLogger.info(e.toString());
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}


public void buscar() {
	slf4jLogger.info("buscarEucaristia");
	List<EucaristiaListDTO> listResultado=new ArrayList<EucaristiaListDTO>();
	FiltroFechaDTO filtro;
	
	try {
		filtro=new FiltroFechaDTO();
		if(this.eucaristiaDataManager.getFechaBuscar()!=null)
		{	
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.eucaristiaDataManager.getFechaBuscar());
		
		int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    
	    filtro.setAnio(year);
	    filtro.setMes(month+1);
	    filtro.setDia(day);
		}
		eucaristiaDataManager.getEucaristiaListDTO().setEucEmpresa(getEmpresaTbl().getEmrPk());
		listResultado = this.servicioEucaristia.buscarEucaristia(eucaristiaDataManager.getEucaristiaListDTO(), filtro);
			
		if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
			this.eucaristiaDataManager.setEucaristiaListDTOs(new ArrayList<EucaristiaListDTO>());
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
		SacerdoteListDTO sacerdoteListDTO= new SacerdoteListDTO();
		sacerdoteListDTO.setSacEstado(ConstantesApplication.ESTADO_ACTIVO);
		sacerdoteListDTO.setSacEmpresa(getEmpresaTbl().getEmrPk());
		listaSacerdote=this.servicioEucaristia.buscarSacerdote(sacerdoteListDTO);
		
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

public void buscarSacerdoteEditar () {
	slf4jLogger.info("buscarSacerdoteEditar");
	
	List<SacerdoteListDTO> listaSacerdote=null;
	
	try {
		this.eucaristiaDataManager.setSacerdoteDTOs(new ArrayList<SacerdoteListDTO>());
		buscarSacerdote();
		SacerdoteListDTO sacerdoteListDTO= new SacerdoteListDTO();
		sacerdoteListDTO.setSacCodigo(this.eucaristiaDataManager.getCodigoSacerdote());
		listaSacerdote=this.servicioEucaristia.buscarSacerdote(sacerdoteListDTO);
		List<SacerdoteListDTO> listaSacerdoteActivos=this.eucaristiaDataManager.getSacerdoteDTOs();
		Boolean estaActivo=false;
		for (SacerdoteListDTO sacerdote : listaSacerdoteActivos) {
			if(sacerdote.getSacCodigo().equals(listaSacerdote.get(0).getSacCodigo()))estaActivo=true;
		}
		if(!estaActivo) this.eucaristiaDataManager.getSacerdoteDTOs().add(listaSacerdote.get(0));
		
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al buscarSacerdote {} ", e);
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}

public void cargarDatosEucaristia (EucaristiaListDTO eucaristiaListDTO) {
	try {
		EucaristiaVO eucaristiaEncontrado=servicioEucaristia.obtenerEucaristiaPorId(eucaristiaListDTO);
		this.eucaristiaDataManager.setFecha(eucaristiaEncontrado.getEucaristiaDTO().getEucFechaHora());
		this.eucaristiaDataManager.setEucaristiaInsertar(eucaristiaEncontrado.getEucaristiaDTO());
		this.eucaristiaDataManager.setCodigoSacerdote(eucaristiaEncontrado.getEucaristiaDTO().getEucSacerdoteBean().getSacCodigo());		
						
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al cargarDatosEucaristia {}", e.getMessage());
		MensajesWebController.aniadirMensajeError("Error al cargarDatosEucaristia seleccionado");
	}
}

public void cargarDatosEucaristiaEditar (EucaristiaListDTO eucaristiaListDTO) {
	cargarDatosEucaristia (eucaristiaListDTO);
	buscarSacerdoteEditar();
}
public void limpiarFormulario()
{
	eucaristiaDataManager.setEucaristiaInsertar(new EucaristiaDTO());
	eucaristiaDataManager.setCodigoSacerdote(0);
	eucaristiaDataManager.setFecha(new Date());
	buscarSacerdote();
}

@Override
public void refrescarFormulario() {
	buscar();
	// TODO Auto-generated method stub
	
}


}
