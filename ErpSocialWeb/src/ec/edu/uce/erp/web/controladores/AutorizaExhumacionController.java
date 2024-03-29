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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.AutorizacionExhumacionVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.datamanager.AutorizaExhumacionDataManager;

@ViewScoped
@ManagedBean (name = "autorizaExhumacionController")
public class AutorizaExhumacionController extends BaseController{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final Logger slf4jLogger = LoggerFactory.getLogger(AutorizaExhumacionController.class);
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{autorizaExhumacionDataManager}")
	private AutorizaExhumacionDataManager autorizaExhumacionDataManager;

	public AutorizaExhumacionDataManager getAutorizaExhumacionDataManager() {
		return autorizaExhumacionDataManager;
	}

	public void setAutorizaExhumacionDataManager(
			AutorizaExhumacionDataManager autorizaExhumacionDataManager) {
		this.autorizaExhumacionDataManager = autorizaExhumacionDataManager;
	}
	
	
	
	public AutorizaExhumacionController () {
		
	}
	
	
	public void registrarAutorizacionExhumacion () {
		
		slf4jLogger.info("registrarAutorizacionExhumacion");
		AutorizacionExhumacionVO autorizacionExhumacionVO;
				
		AutorizaExhumacionListDTO autorizaExhumacionAux=new AutorizaExhumacionListDTO();
		try {
			 autorizaExhumacionAux.setPerCi(autorizaExhumacionDataManager.getAutorizaExhuPerInsertar().getPerCi());
			 autorizaExhumacionAux.setAutEmpresa(getEmpresaTbl().getEmrPk());
			 List<AutorizaExhumacionListDTO> listaAutorizaExhumacions= servicioEucaristia.buscarAutorizacion(autorizaExhumacionAux);
			 Boolean esAutorizaExhumacion=false;
			 for (AutorizaExhumacionListDTO autorizaExhumacionListDTO : listaAutorizaExhumacions) {
				if (autorizaExhumacionListDTO.getPerCi().equals(autorizaExhumacionDataManager.getAutorizaExhuPerInsertar().getPerCi()))
					esAutorizaExhumacion=true;
				}
			 
		   if (!CollectionUtils.isEmpty(listaAutorizaExhumacions) && listaAutorizaExhumacions.size()!=0 && autorizaExhumacionDataManager.getAutorizaExhumacionDTO().getAutCodigo()==null && esAutorizaExhumacion)
		   {
			   
			   MensajesWebController.aniadirMensajeAdvertencia("erp.despauto.autorizacion.persona.repetida");
			   return;
		   }
			
			autorizacionExhumacionVO=new AutorizacionExhumacionVO();
			autorizacionExhumacionVO.setPersona(autorizaExhumacionDataManager.getAutorizaExhuPerInsertar());
			autorizacionExhumacionVO.setAutorizaExhumacionDTO(autorizaExhumacionDataManager.getAutorizaExhumacionDTO());
			autorizacionExhumacionVO.getAutorizaExhumacionDTO().setAutEmpresa(getEmpresaTbl().getEmrPk());
			AutorizaExhumacionDTO autorizacionNuevo=this.servicioEucaristia.createOrUpdateAutorizacion(autorizacionExhumacionVO);
						
			if (autorizacionNuevo != null) {
				autorizaExhumacionDataManager.setAutorizaExhumacionDTO(new AutorizaExhumacionDTO());
				autorizaExhumacionDataManager.setAutorizaExhuPerInsertar(new Persona());
				
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.autorizacion.registrar.exito");
			}
			buscarAutorizacion();
			RequestContext.getCurrentInstance().execute(
					"dlgNuevaAutorizaExhumacion.hide(), dlgEditarAutorizaExhumacion.hide()");	
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarAutorizacion () {
		slf4jLogger.info("buscarAutorizacion");
		
		List<AutorizaExhumacionListDTO> listaAutoriza=null;
		
		try {
			autorizaExhumacionDataManager.getAutorizaExhuBuscar().setAutEmpresa(getEmpresaTbl().getEmrPk());			
			listaAutoriza=this.servicioEucaristia.buscarAutorizacion(autorizaExhumacionDataManager.getAutorizaExhuBuscar());
			
			if (CollectionUtils.isEmpty(listaAutoriza) && listaAutoriza.size()==0) {
				this.autorizaExhumacionDataManager.setAutorizaExhumacionListDTOs(new ArrayList<AutorizaExhumacionListDTO>());
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.autorizaExhumacionDataManager.setAutorizaExhumacionListDTOs(listaAutoriza);
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarAutorizacion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarAutorizacion2 () {
		slf4jLogger.info("buscarAutorizacion2");
		
		List<Persona> listaAutoriza=null;
		
		try {
							
			listaAutoriza=this.servicioAdministracion.buscarPersona(autorizaExhumacionDataManager.getAutorizaExhuPerInsertar());
			
			if (CollectionUtils.isEmpty(listaAutoriza) && listaAutoriza.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				autorizaExhumacionDataManager.setAutorizaExhuPerInsertar(listaAutoriza.get(0));
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarAutorizacion2 {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosAutoriza (AutorizaExhumacionListDTO autorizaExhumacionListDTO) {
		try {
			AutorizacionExhumacionVO autorizaEncontrado= servicioEucaristia.obtenerAutorizacionPorId(autorizaExhumacionListDTO.getAutPersona(), autorizaExhumacionListDTO.getAutCodigo());
			this.autorizaExhumacionDataManager.setAutorizaExhumacionDTO(autorizaEncontrado.getAutorizaExhumacionDTO());
			this.autorizaExhumacionDataManager.setAutorizaExhuPerInsertar(autorizaEncontrado.getPersona());
			autorizaEncontrado.getPersona().getPerFotoVerificar();
			
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosAutoriza seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosAutoriza seleccionado");
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		autorizaExhumacionDataManager.getAutorizaExhuPerInsertar().setPerFoto(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), event.getFile().getFileName()));
		autorizaExhumacionDataManager.getAutorizaExhuPerInsertar().setPerFotoByte(event.getFile().getContents());
    }

	public void limpiarFormulario()
	{
		autorizaExhumacionDataManager.setAutorizaExhumacionDTO(new AutorizaExhumacionDTO());
		autorizaExhumacionDataManager.setAutorizaExhuPerInsertar(new Persona());
	}
	
	

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}



