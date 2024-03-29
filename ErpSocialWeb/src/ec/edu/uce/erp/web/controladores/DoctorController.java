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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.DoctorVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.datamanager.DoctorDataManager;

@ViewScoped
@ManagedBean (name = "doctorController")
public class DoctorController extends BaseController {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final Logger slf4jLogger = LoggerFactory.getLogger(DocenteController.class);
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{doctorDataManager}")
	private DoctorDataManager doctorDataManager;

	public DoctorDataManager getDoctorDataManager() {
		return doctorDataManager;
	}

	public void setDoctorDataManager(DoctorDataManager doctorDataManager) {
		this.doctorDataManager = doctorDataManager;
	}

	public DoctorController() {
	
	}
	
	
public void registrarDoctor () {
		
		slf4jLogger.info("registrarDoctor");
		DoctorVO DoctorVO;
		
		DoctorListDTO doctorAux=new DoctorListDTO();
		try {
			 doctorAux.setPerCi(doctorDataManager.getDoctorPersonaInsertar().getPerCi());
			 doctorAux.setDocEmpresa(getEmpresaTbl().getEmrPk());
			 List<DoctorListDTO> listaDoctores= servicioEucaristia.buscarDoctor(doctorAux);
			 Boolean esDoctor=false;
			 for (DoctorListDTO doctorListDTO : listaDoctores) {
				if (doctorListDTO.getPerCi().equals(doctorDataManager.getDoctorPersonaInsertar().getPerCi()))
					esDoctor=true;
				}
		   if (!CollectionUtils.isEmpty(listaDoctores) && listaDoctores.size()!=0 && doctorDataManager.getDoctorInsertar().getDocCodigo()==null && esDoctor)
		   {
			   MensajesWebController.aniadirMensajeAdvertencia("erp.despacho.doctor.cedula.repetida");
			   return;
		   }
			DoctorVO=new DoctorVO();
			DoctorVO.setDoctorDTO(doctorDataManager.getDoctorInsertar());
			DoctorVO.getDoctorDTO().setDocEmpresa(getEmpresaTbl().getEmrPk());
			DoctorVO.setPersona(doctorDataManager.getDoctorPersonaInsertar());
			DoctorDTO DoctorNuevo= this.servicioEucaristia.createOrUpdateDoctor(DoctorVO);
			
					
			if (DoctorNuevo != null) {
				doctorDataManager.setDoctorInsertar(new DoctorDTO());
				doctorDataManager.setDoctorPersonaInsertar(new Persona());
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.doctor.registrar.exito");
			}
			buscarDoctor();
			RequestContext.getCurrentInstance().execute(
					"dlgNuevoDoctor.hide(), dlgEditarDoctor.hide()");	
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarDoctor () {
		slf4jLogger.info("buscarDoctor");
		
		List<DoctorListDTO> listaDoctor=null;
		
		try {
			doctorDataManager.getDoctorBuscar().setDocEmpresa(getEmpresaTbl().getEmrPk());				
			listaDoctor=this.servicioEucaristia.buscarDoctor(doctorDataManager.getDoctorBuscar());
			
			if (CollectionUtils.isEmpty(listaDoctor) && listaDoctor.size()==0) {
				this.doctorDataManager.setDoctorListDTOs(new ArrayList<DoctorListDTO>());
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.doctorDataManager.setDoctorListDTOs(listaDoctor);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDoctor {} ", e);
			MensajesWebController.aniadirMensajeError("Error al buscar Doctor");
		}
		
	}
	
	public void buscarDoctor2 () {
		slf4jLogger.info("buscarDoctor2");
		
		List<Persona> listaDoctor=null;
		
		try {
			if(doctorDataManager.getDoctorPersonaInsertar()!=null && doctorDataManager.getDoctorPersonaInsertar().getPerCi()!="" )
			{
				doctorDataManager.getDoctorPersonaInsertar().setPerApellidos(null);
				doctorDataManager.getDoctorPersonaInsertar().setPerNombres(null);
				
				listaDoctor=this.servicioAdministracion.buscarPersona(doctorDataManager.getDoctorPersonaInsertar());
				
				if (CollectionUtils.isEmpty(listaDoctor) && listaDoctor.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.doctorDataManager.setDoctorPersonaInsertar(listaDoctor.get(0));
					
				}
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDoctor2 {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void cargarDatosDoctor (DoctorListDTO doctor) {
		try {
			DoctorVO doctorEncontrado=servicioEucaristia.obtenerDoctorPorId(doctor.getDocPersona(), doctor.getDocCodigo());
			this.doctorDataManager.setDoctorInsertar(doctorEncontrado.getDoctorDTO());
			this.doctorDataManager.setDoctorPersonaInsertar(doctorEncontrado.getPersona());
			doctorEncontrado.getPersona().getPerFotoVerificar();
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosDoctor seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosDoctor seleccionado");
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		doctorDataManager.getDoctorPersonaInsertar().setPerFoto(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), event.getFile().getFileName()));
		doctorDataManager.getDoctorPersonaInsertar().setPerFotoByte(event.getFile().getContents());
    }

	public void limpiarFormulario()
	{
		doctorDataManager.setDoctorInsertar(new DoctorDTO());
		doctorDataManager.setDoctorPersonaInsertar(new Persona());
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}
