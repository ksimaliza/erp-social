package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.SacerdoteVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.datamanager.SacerdoteDataManager;

@ViewScoped
@ManagedBean (name = "sacerdoteController")
public class SacerdoteController extends BaseController{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final Logger slf4jLogger = LoggerFactory.getLogger(DocenteController.class);
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{sacerdoteDataManager}")
	private SacerdoteDataManager sacerdoteDataManager;

	public SacerdoteDataManager getSacerdoteDataManager() {
		return sacerdoteDataManager;
	}

	public void setSacerdoteDataManager(SacerdoteDataManager sacerdoteDataManager) {
		this.sacerdoteDataManager = sacerdoteDataManager;
	}
	
	
	public SacerdoteController() {
	
	}
	
public void registrarSacerdote () {
		
		slf4jLogger.info("registrarSacerdote");
		SacerdoteVO sacerdoteVO;
		try {
			
			sacerdoteVO=new SacerdoteVO();
			sacerdoteVO.setSacerdoteDTO(sacerdoteDataManager.getSacerdoteInsertar());
			sacerdoteVO.setPersona(sacerdoteDataManager.getSacerdotePersonaInsertar());
			SacerdoteDTO sacerdoteNuevo= this.servicioEucaristia.createOrUpdateSacerdote(sacerdoteVO);
			
					
			if (sacerdoteNuevo != null) {
				sacerdoteDataManager.setSacerdoteInsertar(new SacerdoteDTO());
				sacerdoteDataManager.setSacerdotePersonaInsertar(new Persona());
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.sacerdote.registrar.exito");
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
							
			listaSacerdote=this.servicioEucaristia.buscarSacerdote(sacerdoteDataManager.getSacerdoteBuscar());
			
			if (CollectionUtils.isEmpty(listaSacerdote) && listaSacerdote.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.sacerdoteDataManager.setSacerdoteListDTOs(listaSacerdote);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarSacerdote2 () {
		slf4jLogger.info("buscarSacerdote");
		
		List<Persona> listaSacerdote=null;
		
		try {
			sacerdoteDataManager.getSacerdotePersonaInsertar().setPerApellidos(null);
			sacerdoteDataManager.getSacerdotePersonaInsertar().setPerNombres(null);

			listaSacerdote=this.servicioAdministracion.buscarPersona(sacerdoteDataManager.getSacerdotePersonaInsertar());
							
			if (CollectionUtils.isEmpty(listaSacerdote) && listaSacerdote.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.sacerdoteDataManager.setSacerdotePersonaInsertar(listaSacerdote.get(0));
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBautizado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void cargarDatosSacerdote (SacerdoteListDTO sacerdote) {
		try {
			SacerdoteVO sacerdoteEncontrado=servicioEucaristia.obtenerSacerdotePorId(sacerdote.getSacPersona(), sacerdote.getSacCodigo());
			this.sacerdoteDataManager.setSacerdoteInsertar(sacerdoteEncontrado.getSacerdoteDTO());
			this.sacerdoteDataManager.setSacerdotePersonaInsertar(sacerdoteEncontrado.getPersona());
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosSacerdote seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosSacerdote seleccionado");
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		sacerdoteDataManager.getSacerdotePersonaInsertar().setPerFoto(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), JsfUtil.getRandomName(event.getFile().getFileName().split("\\.")[1])));
		sacerdoteDataManager.getSacerdotePersonaInsertar().setPerFotoByte(event.getFile().getContents());
    }

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
}
