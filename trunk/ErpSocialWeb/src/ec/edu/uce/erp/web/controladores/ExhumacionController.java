package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
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
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ExhumacionVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ExhumacionDataManager;

@ViewScoped
@ManagedBean (name = "exhumacionController")
public class ExhumacionController extends BaseController{
	
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ExhumacionController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	
	@ManagedProperty(value="#{exhumacionDataManager}")
	private ExhumacionDataManager exhumacionDataManager;


	public ExhumacionDataManager getExhumacionDataManager() {
		return exhumacionDataManager;
	}


	public void setExhumacionDataManager(ExhumacionDataManager exhumacionDataManager) {
		this.exhumacionDataManager = exhumacionDataManager;
	}


	public ExhumacionController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarAutorizacion();
	
	}
	
	
	public void registrarExhumacion () {
		
		slf4jLogger.info("registrarExhumacion");
		ExhumacionVO exhumacionVO;
		AutorizaExhumacionDTO autorizaExhumacionDTO;
				
		try {
			
			exhumacionVO=new ExhumacionVO();
			autorizaExhumacionDTO=new AutorizaExhumacionDTO();
			
			
			exhumacionVO.setDifunto(exhumacionDataManager.getDifuntoInsertar());
			
			exhumacionVO.setExumacionDTO(exhumacionDataManager.getExumacionDTO());			
			
			autorizaExhumacionDTO.setAutCodigo(exhumacionDataManager.getAutorizaCodigo());
			
			exhumacionVO.setAutorizaExhumacionDTO(autorizaExhumacionDTO);
			
			if(exhumacionDataManager.getFechaSepelio().getTime()>exhumacionDataManager.getFechaExhumacion().getTime())
			{
				MensajesWebController.aniadirMensajeError("Ingrese fecha de Exhumación Correcta");
				return;
			}			
			
			
			exhumacionVO.getExumacionDTO().setExuFechaExhumacion(new Timestamp(exhumacionDataManager.getFechaExhumacion().getTime()));
			exhumacionVO.getExumacionDTO().setExuFechaCepelio(new Timestamp(exhumacionDataManager.getFechaSepelio().getTime()));
			ExumacionDTO exhumacionNuevo=this.servicioEucaristia.createOrUpdateExhumacion(exhumacionVO);
						
			if (exhumacionNuevo != null) {
				exhumacionDataManager.setExumacionDTO(new ExumacionDTO());
				exhumacionDataManager.setDifuntoInsertar(new Persona());
				exhumacionDataManager.setAutorizaCodigo(0);
				exhumacionDataManager.setFechaExhumacion(new Date());
				exhumacionDataManager.setFechaSepelio(new Date());
											
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.exhumacion.registrar.exito");
			}
			buscarExhumacion();
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	


	
	public void buscarDifunto () {
		slf4jLogger.info("buscarDifunto");
		
		List<Persona> listaDifunto=null;
		DefuncionListDTO difunto=new DefuncionListDTO();
		List<DefuncionListDTO> list=null;
		
		try {
			if(exhumacionDataManager.getDifuntoInsertar().getPerCi()!=null && exhumacionDataManager.getDifuntoInsertar().getPerCi()!="" )
			{
				exhumacionDataManager.getDifuntoInsertar().setPerNombres(null);
				exhumacionDataManager.getDifuntoInsertar().setPerApellidos(null);
				listaDifunto=this.servicioAdministracion.buscarPersona(exhumacionDataManager.getDifuntoInsertar());					
				difunto.setPerCi(exhumacionDataManager.getDifuntoInsertar().getPerCi());
				list=this.servicioEucaristia.buscarDefuncion(difunto);
				
				if ((CollectionUtils.isEmpty(listaDifunto) && listaDifunto.size()==0)||CollectionUtils.isEmpty(list) && list.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					exhumacionDataManager.setDifuntoInsertar(listaDifunto.get(0));
					exhumacionDataManager.setDefuncionListDTO(list.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBautizado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarAutorizacion () {
		slf4jLogger.info("buscarAutorizacion");
		
		List<AutorizaExhumacionListDTO> listaAutoriza=null;
		
		try {
							
			listaAutoriza=this.servicioEucaristia.buscarAutorizacion(new AutorizaExhumacionListDTO());
			
			if (CollectionUtils.isEmpty(listaAutoriza) && listaAutoriza.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.exhumacionDataManager.setAutorizaExhumacionListDTOs(listaAutoriza);
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarAutorizacion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarExhumacion () {
		slf4jLogger.info("buscarExhumacion");
		
		List<ExumacionListDTO> listaExhumacion=null;
		
		try {
			
			listaExhumacion=this.servicioEucaristia.buscarExhumacion(exhumacionDataManager.getExumacionListDTO());
			
			if (CollectionUtils.isEmpty(listaExhumacion) && listaExhumacion.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.exhumacionDataManager.setExumacionListDTOs(listaExhumacion);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarExhumacion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	public void cargarDatosExhumacion (ExumacionListDTO exhumacion) {
		try {
			
			ExhumacionVO exhumacionEncontrado=servicioEucaristia.obtenerExhumacionPorId(exhumacion);
			this.exhumacionDataManager.setDifuntoInsertar(exhumacionEncontrado.getDifunto());
			this.exhumacionDataManager.setAutorizaCodigo(exhumacionEncontrado.getExumacionDTO().getExuAutoriza());
			this.exhumacionDataManager.setExumacionDTO(exhumacionEncontrado.getExumacionDTO());
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosExhumacion {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosExhumacion seleccionado");
		}
	}
	
	
		
	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
