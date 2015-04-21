package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
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
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ParametroAsistenciaDataManager;


@ViewScoped
@ManagedBean (name = "parametroAsistenciaController")

public class ParametroAsistenciaController extends BaseController {

private static final long serialVersionUID = 1L;

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ParametroAsistenciaController.class);
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{parametroAsistenciaDataManager}")
	private ParametroAsistenciaDataManager parametroAsistenciaDataManager;

	public ParametroAsistenciaDataManager getParametroAsistenciaDataManager() {
		return parametroAsistenciaDataManager;
	}

	public void setParametroAsistenciaDataManager(
			ParametroAsistenciaDataManager parametroAsistenciaDataManager) {
		this.parametroAsistenciaDataManager = parametroAsistenciaDataManager;
	}
	
	public ParametroAsistenciaController() {
		
	}
	
	@PostConstruct
	private void init()
	{
		buscarEmpleado();
		//buscarCatalogo();
	}

	
	public void buscarEmpleado() {
		slf4jLogger.info("buscarEmpleado");
		List<EmpleadoListDTO> listResultado;
		EmpleadoListDTO empleadoListDTO;
		try {
			listResultado=new ArrayList<EmpleadoListDTO>();
			
			empleadoListDTO=new EmpleadoListDTO();
			empleadoListDTO.setEmpPk(getEmpresaCode());
			
			listResultado = this.servicioAsistencia.readEmpleado(empleadoListDTO);
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.parametroAsistenciaDataManager.getEmpleadoList().clear();
			} else {
				this.parametroAsistenciaDataManager.setEmpleadoList(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}finally{
			empleadoListDTO=null;
			listResultado=null;
		}
	}
	
//	public void buscarCatalogo() {
//		slf4jLogger.info("buscarCatalogo");
//		List<CatalogoAsistenciaDTO> listResultado;
//		try {
//			listResultado=new ArrayList<CatalogoAsistenciaDTO>();
//			
//			listResultado = this.servicioAsistencia.readAll();
//			
//			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
//				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
//				this.parametroAsistenciaDataManager.setCatalogoList(new ArrayList<CatalogoAsistenciaDTO>());
//			} else {
//				this.parametroAsistenciaDataManager.setCatalogoList(listResultado);
//			}
//			
//		} catch (SeguridadesException e) {
//			slf4jLogger.info("Error al buscar el empleado {} ", e);
//			MensajesWebController.aniadirMensajeError(e.getMessage());
//		}
//		finally{
//			listResultado=null;
//		}
//	}
	
	public void registrarParametro () {
		
		slf4jLogger.info("registrarParametro");
		ParametroDTO parametroNuevo;
		try {
			parametroAsistenciaDataManager.getParametroInsertar().setPasEntidad(getEmpresaCode());
			
			parametroNuevo = this.servicioAsistencia.createOrUpdateParametro(this.parametroAsistenciaDataManager.getParametroInsertar());
			if (parametroNuevo != null) {
				parametroAsistenciaDataManager.setParametroInsertar(new ParametroDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.parametro.registrar.exito");
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		finally{
			parametroNuevo=null;
		}
		
	}
	
	public void buscarParametros () {
		ParametroDTO parametroDTO;
		List<ParametroDTO> listParametro;
		try {
			parametroDTO=new ParametroDTO();
			parametroDTO.setPasEntidad(getEmpresaCode());
			
			listParametro = servicioAsistencia.readParametro();
			
			this.parametroAsistenciaDataManager.setParametroList(listParametro);
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarParametros {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		finally{
			parametroDTO=null;
			listParametro=null;
		}
	}
	
	public void actualizarParametro () {
		
		slf4jLogger.info("actualizarParametro");
		ParametroDTO parametroDTO;
		try {
			parametroDTO=new ParametroDTO();
			parametroDTO.setPasCodigo(parametroAsistenciaDataManager.getParametroActualizar().getPasCodigo());
			//parametroDTO.setPasCatalogo(parametroAsistenciaDataManager.getCatalogoCodigo());
			parametroDTO.setPasEntidad(getEmpresaCode());
			parametroDTO.setPasValor(parametroAsistenciaDataManager.getParametroActualizar().getPasValor());
			
			servicioAsistencia.actualizarParametroAsistencia(parametroDTO);
			MensajesWebController.aniadirMensajeInformacion("erp.parametro.actualizar.exito");
			
			buscarParametros();
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizarParametro {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		finally{
			parametroDTO=null;
		}
	}
	
	public void cargarDatosParametro (ParametroDTO parametro) {
		ParametroDTO parametroEncontrado;
		
		try {
			parametroEncontrado = servicioAsistencia.readParametroById(parametro.getPasCodigo());
			this.parametroAsistenciaDataManager.setParametroActualizar(parametroEncontrado);
												
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosParametro seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosParametro seleccionado");
		}
		finally{
			parametroEncontrado=null;
			parametro=null;
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}
