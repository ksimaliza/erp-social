package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
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
		SacerdoteListDTO sacerdoteAux=new SacerdoteListDTO();
		try {
			 sacerdoteAux.setPerCi(sacerdoteDataManager.getSacerdotePersonaInsertar().getPerCi());
			 List<SacerdoteListDTO> listaSacerdotes= servicioEucaristia.buscarSacerdote(sacerdoteAux);
			 Boolean esSacerdote=false;
			 for (SacerdoteListDTO sacerdoteListDTO : listaSacerdotes) {
				if (sacerdoteListDTO.getPerCi().equals(sacerdoteDataManager.getSacerdotePersonaInsertar().getPerCi()))
					esSacerdote=true;
				}
			 
		   if (!CollectionUtils.isEmpty(listaSacerdotes) && listaSacerdotes.size()!=0 && sacerdoteDataManager.getSacerdoteInsertar().getSacCodigo()==null && esSacerdote)
		   {
			   
			   MensajesWebController.aniadirMensajeAdvertencia("Yá se registró sacerdote con la misma cédula");
			   return;
		   }
			
			sacerdoteVO=new SacerdoteVO();
			sacerdoteVO.setSacerdoteDTO(sacerdoteDataManager.getSacerdoteInsertar());
			sacerdoteVO.setPersona(sacerdoteDataManager.getSacerdotePersonaInsertar());
			SacerdoteDTO sacerdoteNuevo= this.servicioEucaristia.createOrUpdateSacerdote(sacerdoteVO);
			
					
			if (sacerdoteNuevo != null) {
				sacerdoteDataManager.setSacerdoteInsertar(new SacerdoteDTO());
				sacerdoteDataManager.setSacerdotePersonaInsertar(new Persona());
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.sacerdote.registrar.exito");
			}
			buscarSacerdote();
			RequestContext.getCurrentInstance().execute(
					"dlgNuevoSacerdote.hide(), dlgEditarSacerdote.hide()");	
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
				this.sacerdoteDataManager.setSacerdoteListDTOs(new ArrayList<SacerdoteListDTO>());
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
			if(sacerdoteDataManager.getSacerdotePersonaInsertar().getPerCi()!=null && sacerdoteDataManager.getSacerdotePersonaInsertar().getPerCi()!="" )
			{
				sacerdoteDataManager.getSacerdotePersonaInsertar().setPerApellidos(null);
				sacerdoteDataManager.getSacerdotePersonaInsertar().setPerNombres(null);
				listaSacerdote=this.servicioAdministracion.buscarPersona(sacerdoteDataManager.getSacerdotePersonaInsertar());
								
				if (CollectionUtils.isEmpty(listaSacerdote) && listaSacerdote.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.sacerdoteDataManager.setSacerdotePersonaInsertar(listaSacerdote.get(0));
								
				}
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
			sacerdoteEncontrado.getPersona().getPerFotoVerificar();
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosSacerdote seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosSacerdote seleccionado");
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		sacerdoteDataManager.getSacerdotePersonaInsertar().setPerFoto(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), event.getFile().getFileName()));
		sacerdoteDataManager.getSacerdotePersonaInsertar().setPerFotoByte(event.getFile().getContents());
    }

	public void limpiarFormulario()
	{
		sacerdoteDataManager.setSacerdoteInsertar(new SacerdoteDTO());
		sacerdoteDataManager.setSacerdotePersonaInsertar(new Persona());
	}
	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
}
