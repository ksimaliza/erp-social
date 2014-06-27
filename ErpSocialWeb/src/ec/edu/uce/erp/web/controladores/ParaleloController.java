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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ParaleloDataManager;


@ViewScoped
@ManagedBean (name = "paraleloController")

public class ParaleloController extends BaseController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ParaleloController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{paraleloDataManager}")
	private ParaleloDataManager paraleloDataManager;
	
	public void setParaleloDataManager(ParaleloDataManager paraleloDataManager) {
		this.paraleloDataManager = paraleloDataManager;
	}
	
		public ParaleloController() {
		
			}
		@PostConstruct
		private void init()
		{
			buscarParalelo();
		}
			
/*
 * Medodos
 */

public void registrarParalelo () {
	
	slf4jLogger.info("registrarParalelo");
	try {
		paraleloDataManager.getParaleloInstancia().setParEmpresa(getEmpresaCode());
		ParaleloDTO paraleloNuevo = this.servicioMatricula.createOrUpdateParalelo(this.paraleloDataManager.getParaleloInstancia());
		if (paraleloNuevo != null) {
			paraleloDataManager.setParaleloInstancia(new ParaleloDTO());
			MensajesWebController.aniadirMensajeInformacion("erp.matricula.paralelo.registrar.exito");
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info(e.toString());
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}

public void buscarParalelo () {
	slf4jLogger.info("buscarParalelo");
	
	List<ParaleloDTO> listaparalelo=null;
	
	try {
		paraleloDataManager.getParaleloBuscar().setParEmpresa(getEmpresaCode());				
		listaparalelo = this.servicioMatricula.buscarParalelo(paraleloDataManager.getParaleloBuscar());
		
		if (CollectionUtils.isEmpty(listaparalelo) && listaparalelo.size()==0) {
			MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
		} else {
			this.paraleloDataManager.setParaleloDTOs(listaparalelo);
		}
		
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al buscarParalelo {} ", e);
		MensajesWebController.aniadirMensajeError(e.getMessage());
	}
	
}

public void cargarDatosParalelo (ParaleloDTO paralelo) {
	try {
		ParaleloDTO paraleloEncontrado = servicioMatricula.obtenerParaleloPorId(paralelo.getParCodigo());
		
		this.paraleloDataManager.setParaleloInstancia(paraleloEncontrado);
								
	} catch (SeguridadesException e) {
		slf4jLogger.info("Error al cargarDatosParalelo seleccionado {}", e.getMessage());
		MensajesWebController.aniadirMensajeError("Error al cargarDatosParalelo seleccionado");
	}
}

public ParaleloDataManager getParaleloDataManager() {
	return paraleloDataManager;
}



}
