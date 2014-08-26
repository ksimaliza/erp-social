package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
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
	
	
	public void registrarParametro () {
		
		slf4jLogger.info("registrarParametro");
		try {
			parametroAsistenciaDataManager.getParametroInsertar().setPasEntidad(getEmpresaCode());
			ParametroDTO parametroNuevo = this.servicioAsistencia.createOrUpdateParametro(this.parametroAsistenciaDataManager.getParametroInsertar());
			if (parametroNuevo != null) {
				parametroAsistenciaDataManager.setParametroInsertar(new ParametroDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.parametro.registrar.exito");
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarParametros () {
		slf4jLogger.info("buscarParametros");
		
		try {
			List<ParametroDTO> listParametro = servicioAsistencia.buscarParametro(this.parametroAsistenciaDataManager.getParametroBuscar());
			
			if (CollectionUtils.isEmpty(listParametro)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.parametroAsistenciaDataManager.getListParametro().clear();
			} else {
				this.parametroAsistenciaDataManager.setListParametro(listParametro);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarParametros {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void actualizarParametro () {
		
		slf4jLogger.info("actualizarParametro");
		
		try {
			servicioAsistencia.actualizarParametroAsistencia(this.parametroAsistenciaDataManager.getParametroActualizar());
			MensajesWebController.aniadirMensajeInformacion("erp.parametro.actualizar.exito");
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al actualizarParametro {}", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void cargarDatosParametro (ParametroDTO parametro) {
		try {
			ParametroDTO parametroEncontrado = servicioAsistencia.obtenerParametroPorId(parametro.getPasCodigo());
			this.parametroAsistenciaDataManager.setParametroActualizar(parametroEncontrado);
			
												
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosParametro seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosParametro seleccionado");
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}
