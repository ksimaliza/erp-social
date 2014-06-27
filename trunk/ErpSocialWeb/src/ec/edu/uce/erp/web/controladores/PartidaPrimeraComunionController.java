package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ComunionVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.PartidaPrimeraComunionDataManager;

@ViewScoped
@ManagedBean (name = "partidaComunionController")

public class PartidaPrimeraComunionController extends BaseController{
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaPrimeraComunionController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{partidaPrimeraComunionDataManager}")
	private PartidaPrimeraComunionDataManager partidaPrimeraComunionDataManager;

		public PartidaPrimeraComunionDataManager getPartidaPrimeraComunionDataManager() {
		return partidaPrimeraComunionDataManager;
	}

	public void setPartidaPrimeraComunionDataManager(
			PartidaPrimeraComunionDataManager partidaPrimeraComunionDataManager) {
		this.partidaPrimeraComunionDataManager = partidaPrimeraComunionDataManager;
	}

	public PartidaPrimeraComunionController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarSacerdote();
		buscarTipo ();
		
	}
	
public void registrarPrimeraComunion () {
		
		slf4jLogger.info("registrarPrimeraComunion");
		ComunionVO comunionVO;
		SacerdoteDTO sacerdoteDTO;
		DoctorDTO doctorDTO;
		
		try {
			
			comunionVO=new ComunionVO();
			sacerdoteDTO=new SacerdoteDTO();
			doctorDTO=new DoctorDTO();
			
			
			comunionVO.setAsignado(partidaPrimeraComunionDataManager.getAsignadoInsertar());
			comunionVO.setMad_pad(partidaPrimeraComunionDataManager.getMad_padInsertar());
			comunionVO.setComunion(partidaPrimeraComunionDataManager.getPrimeraComunionInsertar());		
					
			sacerdoteDTO.setSacCodigo(partidaPrimeraComunionDataManager.getSacerdoteCodigo());
			doctorDTO.setDocCodigo(partidaPrimeraComunionDataManager.getDoctorCodigo());
			comunionVO.setSacerdote(sacerdoteDTO);
			comunionVO.setDoctor(doctorDTO);
					
			comunionVO.getComunion().setPcoFechaAprobacionCurso(new Timestamp(partidaPrimeraComunionDataManager.getFechaApComInsertar().getTime()));
			comunionVO.getComunion().setPcoFechaHora(new Timestamp(partidaPrimeraComunionDataManager.getFechaComunionInsertar().getTime()));
			
			PrimeraComunionDTO comunionNuevo=this.servicioEucaristia.createOrUpdateComunion(comunionVO);	
						
			if (comunionNuevo != null) {
				
				partidaPrimeraComunionDataManager.setAsignadoInsertar(new Persona());
															
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.partida.comunion.registrar.exito");
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
				this.partidaPrimeraComunionDataManager.setSacerdoteListDTO(listaSacerdote);
					
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
				this.partidaPrimeraComunionDataManager.setDoctorListDTO(listaDoctor);
											
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDoctor {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarAsignado () {
		slf4jLogger.info("buscarAsignado");
		
		List<Persona> listaAsignado=null;
		BautizoListDTO bautizo=new BautizoListDTO();
		List<BautizoListDTO> list=null;
		
		try {
			partidaPrimeraComunionDataManager.getAsignadoInsertar().setPerNombres(null);
			partidaPrimeraComunionDataManager.getAsignadoInsertar().setPerApellidos(null);
			listaAsignado=this.servicioAdministracion.buscarPersona(partidaPrimeraComunionDataManager.getAsignadoInsertar());
			bautizo.setPerCi(partidaPrimeraComunionDataManager.getAsignadoInsertar().getPerCi());
			list=this.servicioEucaristia.buscarPartidaBautizo(bautizo);		
			
			if (CollectionUtils.isEmpty(listaAsignado) && listaAsignado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaPrimeraComunionDataManager.setAsignadoInsertar(listaAsignado.get(0));
				this.partidaPrimeraComunionDataManager.setBautizoListDTO(list.get(0));	
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarAsignado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	
	public void buscarMadrina () {
		slf4jLogger.info("buscarMadrina");
		
		List<Persona> listaMad_Pad=null;
		
		try {
			partidaPrimeraComunionDataManager.getMad_padInsertar().setPerApellidos(null);
			partidaPrimeraComunionDataManager.getMad_padInsertar().setPerNombres(null);
			listaMad_Pad=this.servicioAdministracion.buscarPersona(partidaPrimeraComunionDataManager.getMad_padInsertar());
										
			if (CollectionUtils.isEmpty(listaMad_Pad) && listaMad_Pad.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaPrimeraComunionDataManager.setMad_padInsertar(listaMad_Pad.get(0));
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadrina {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarPartidaComunion () {
		slf4jLogger.info("buscarPartidaComunion");
		
		List<ComunionListDTO> listaComunion=null;
		
		try {
			
			listaComunion=this.servicioEucaristia.buscarPartidaComunion(partidaPrimeraComunionDataManager.getComunionListDTO());
								
			
			if (CollectionUtils.isEmpty(listaComunion) && listaComunion.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaPrimeraComunionDataManager.setComunionListDTOs(listaComunion);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPartidaPrimeraComunion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosComunion (ComunionListDTO comunion) {
		try {
			
			ComunionVO comunionEncontrada=servicioEucaristia.obtenerComunionPorId(comunion.getPcoAsignado(), comunion.getPcoCodigo());
			this.partidaPrimeraComunionDataManager.setAsignadoInsertar(comunionEncontrada.getAsignado());
			this.partidaPrimeraComunionDataManager.setPrimeraComunionInsertar(comunionEncontrada.getComunion());
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosComunion {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosComunion seleccionado");
		}
	}
	
	public void buscarTipo () {
		slf4jLogger.info("buscarTipo");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(11);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaPrimeraComunionDataManager.setTipoEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarTipo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
}
