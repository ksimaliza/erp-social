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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ConfirmacionVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.PartidaConfirmacionDataManager;

@ViewScoped
@ManagedBean (name = "partidaConfirmacionController")

public class PartidaConfirmacionController  extends BaseController{
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaConfirmacionController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{partidaConfirmacionDataManager}")
	private PartidaConfirmacionDataManager partidaConfirmacionDataManager;

	public PartidaConfirmacionDataManager getPartidaConfirmacionDataManager() {
		return partidaConfirmacionDataManager;
	}

	public void setPartidaConfirmacionDataManager(
			PartidaConfirmacionDataManager partidaConfirmacionDataManager) {
		this.partidaConfirmacionDataManager = partidaConfirmacionDataManager;
	}

	public PartidaConfirmacionController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarSacerdote();
		buscarTipo ();
		
	}
public void registrarConfirmacion () {
		
		slf4jLogger.info("registrarConfirmacion");
		ConfirmacionVO confirmacionVO;
		SacerdoteDTO sacerdoteDTO;
		
		try {
			
			confirmacionVO=new ConfirmacionVO();
			sacerdoteDTO=new SacerdoteDTO();
			
			confirmacionVO.setConfirmado(partidaConfirmacionDataManager.getConfirmadoInsertar());
			confirmacionVO.setMad_pad(partidaConfirmacionDataManager.getMad_padInsertar());
			
			confirmacionVO.setConfirmacion(partidaConfirmacionDataManager.getConfirmacionDTO());
									
			
			sacerdoteDTO.setSacCodigo(partidaConfirmacionDataManager.getSacerdoteCodigo());
			confirmacionVO.setSacerdote(sacerdoteDTO);
			confirmacionVO.getConfirmacion().setConFechaAprobacionCurso(new Timestamp(partidaConfirmacionDataManager.getFechaApCInsertar().getTime()));
			confirmacionVO.getConfirmacion().setConFecha(new Timestamp(partidaConfirmacionDataManager.getFechaComunionInsertar().getTime()));
			ConfirmacionDTO confirmacionNuevo= this.servicioEucaristia.createOrUpdateConfirmacion(confirmacionVO);
				
						
			if (confirmacionNuevo != null) {
				
				partidaConfirmacionDataManager.setConfirmadoInsertar(new Persona());
											
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.partida.confirmacion.registrar.exito");
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
				this.partidaConfirmacionDataManager.setSacerdoteListDTO(listaSacerdote);
					
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	/*public void buscarDoctor () {
		slf4jLogger.info("buscarDoctor");
		
		List<DoctorListDTO> listaDoctor=null;
		
		try {
							
			listaDoctor=this.servicioEucaristia.buscarDoctor(new DoctorListDTO());
			
			if (CollectionUtils.isEmpty(listaDoctor) && listaDoctor.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setDoctorListDTO(listaDoctor);
											
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDoctor {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}*/
	
	public void buscarConfirmado () {
		slf4jLogger.info("buscarConfirmado");
		
		List<Persona> listaConfirmado=null;
		BautizoListDTO bautizo=new BautizoListDTO();
		List<BautizoListDTO> list=null;
		
		try {
			partidaConfirmacionDataManager.getConfirmadoInsertar().setPerNombres(null);
			partidaConfirmacionDataManager.getConfirmadoInsertar().setPerApellidos(null);
			listaConfirmado=this.servicioAdministracion.buscarPersona(partidaConfirmacionDataManager.getConfirmadoInsertar());					
			bautizo.setPerCi(partidaConfirmacionDataManager.getConfirmadoInsertar().getPerCi());
			list=this.servicioEucaristia.buscarPartidaBautizo(bautizo);
			
			if (CollectionUtils.isEmpty(listaConfirmado) && listaConfirmado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setConfirmadoInsertar(listaConfirmado.get(0));
				this.partidaConfirmacionDataManager.setBautizoListDTO(list.get(0));			
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBautizado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	
	public void buscarMadrina () {
		slf4jLogger.info("buscarMadrina");
		
		List<Persona> listaMad_Pad=null;
		
		try {
			partidaConfirmacionDataManager.getMad_padInsertar().setPerApellidos(null);
			partidaConfirmacionDataManager.getMad_padInsertar().setPerNombres(null);
			listaMad_Pad=this.servicioAdministracion.buscarPersona(partidaConfirmacionDataManager.getMad_padInsertar());
										
			if (CollectionUtils.isEmpty(listaMad_Pad) && listaMad_Pad.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setMad_padInsertar(listaMad_Pad.get(0));
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadrina {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarPartidaConfirmacion () {
		slf4jLogger.info("buscarPartidaConfirmacion");
		
		List<ConfirmacionListDTO> listaConfirmacion=null;
		
		try {
			
			listaConfirmacion=this.servicioEucaristia.buscarPartidaConfirmacion(partidaConfirmacionDataManager.getConfirmacionListDTO());
								
			
			if (CollectionUtils.isEmpty(listaConfirmacion) && listaConfirmacion.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setConfirmacionListDTOs(listaConfirmacion);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPartidaBautizo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosConfirmacion (ConfirmacionListDTO confirmacion) {
		try {
			
			ConfirmacionVO confirmacionEncontrada=servicioEucaristia.obtenerConfirmacionPorId(confirmacion.getConConfirmado(), confirmacion.getConCodigo());
			this.partidaConfirmacionDataManager.setConfirmadoInsertar(confirmacionEncontrada.getConfirmado());
			this.partidaConfirmacionDataManager.setConfirmacionDTO(confirmacionEncontrada.getConfirmacion());
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosConfirmacion {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosConfirmacion seleccionado");
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
				this.partidaConfirmacionDataManager.setTipoEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarTipo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	
}
