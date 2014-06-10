package ec.edu.uce.erp.web.controladores;

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
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.BautizoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.PartidaBautizoDataManager;

@ViewScoped
@ManagedBean (name = "partidaBautizoController")

public class PartidaBautizoController extends BaseController{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaBautizoController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@ManagedProperty(value="#{partidaBautizoDataManager}")
	private PartidaBautizoDataManager partidaBautizoDataManager;

	public PartidaBautizoDataManager getPartidaBautizoDataManager() {
		return partidaBautizoDataManager;
	}

	public void setPartidaBautizoDataManager(
			PartidaBautizoDataManager partidaBautizoDataManager) {
		this.partidaBautizoDataManager = partidaBautizoDataManager;
	}
	
	public PartidaBautizoController() {
	
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarSacerdote();
		buscarDoctor();
		
	}
	
	
	public void registrarBautizo () {
		
		slf4jLogger.info("registrarBautizo");
		BautizoVO bautizoDTO;
		SacerdoteDTO sacerdoteDTO;
		DoctorDTO doctorDTO;
		try {
			
			bautizoDTO=new BautizoVO();
			sacerdoteDTO=new SacerdoteDTO();
			doctorDTO=new DoctorDTO();
			bautizoDTO.setBautizado(partidaBautizoDataManager.getBautizadoInsertar());
			bautizoDTO.setMadrina(partidaBautizoDataManager.getMadrinaInsertar());
			bautizoDTO.setPadrino(partidaBautizoDataManager.getPadrinoInsertar());
			
			bautizoDTO.setBautizo(partidaBautizoDataManager.getBautizoDTO());
			
			
			sacerdoteDTO.setSacCodigo(partidaBautizoDataManager.getSacerdoteCodigo());
			bautizoDTO.setSacerdote(sacerdoteDTO);
			doctorDTO.setDocCodigo(partidaBautizoDataManager.getDoctorCodigo());
			bautizoDTO.setDoctorVO(doctorDTO);
			
			BautizoDTO bautizoNuevo=this.servicioEucaristia.createOrUpdateBautizo(bautizoDTO);
						
			if (bautizoNuevo != null) {
				partidaBautizoDataManager.setBautizadoInsertar(new Persona());
				partidaBautizoDataManager.setMadrinaInsertar(new Persona());
				partidaBautizoDataManager.setPadrinoInsertar(new Persona());
							
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.partida.bautizo.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
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
				this.partidaBautizoDataManager.setSacerdoteListDTO(listaSacerdote);			
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarDoctor () {
		slf4jLogger.info("buscarDoctor");
		
		List<DoctorListDTO> listaDoctor=null;
		
		try {
							
			listaDoctor=this.servicioEucaristia.buscarDoctor(new DoctorListDTO());
			
			if (CollectionUtils.isEmpty(listaDoctor) && listaDoctor.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaBautizoDataManager.setDoctorListDTO(listaDoctor);
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDoctor {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	

}
