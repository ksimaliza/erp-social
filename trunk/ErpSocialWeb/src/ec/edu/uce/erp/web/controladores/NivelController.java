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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.NivelDataManager;

@ViewScoped
@ManagedBean (name = "nivelController")
public class NivelController extends BaseController {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final Logger slf4jLogger = LoggerFactory.getLogger(NivelController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{nivelDataManager}")
	private NivelDataManager nivelDataManager;
	
	public void setNivelDataManager(NivelDataManager nivelDataManager) {
		this.nivelDataManager = nivelDataManager;
	}
	
	
		public NivelController() {
				
			}
		
		@PostConstruct
		private void init()
		{
			buscarNivel();
		}
	
	/*
	 * Medodos
	 */
	
	public void registrarNivel () {
		
		slf4jLogger.info("registrarNivel");
		try {
			this.nivelDataManager.getNivelInstancia().setNivEmpresa(getEmpresaCode());
			NivelDTO nivelNuevo = this.servicioMatricula.createOrUpdateNivel(this.nivelDataManager.getNivelInstancia());
			if (nivelNuevo != null) {
				nivelDataManager.setNivelInstancia(new NivelDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.nivel.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarNivel () {
		slf4jLogger.info("buscarNivel");
		
		List<NivelDTO> listaNivel=null;
		
		try {
			
			nivelDataManager.getNivelBuscar().setNivEmpresa(getEmpresaCode());
							
			listaNivel = this.servicioMatricula.buscarNivel(nivelDataManager.getNivelBuscar());
			
			if (CollectionUtils.isEmpty(listaNivel) && listaNivel.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.nivelDataManager.setNivelDTOs(listaNivel);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarNivel {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosNivel (NivelDTO nivel) {
		try {
			NivelDTO nivelEncontrado = servicioMatricula.obtenerNivelPorId(nivel.getNivCodigo());
			
			this.nivelDataManager.setNivelInstancia(nivelEncontrado);
									
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargar los cargarDatosNivel seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosMateria seleccionado");
		}
	}


	public NivelDataManager getNivelDataManager() {
		return nivelDataManager;
	}
	
}
