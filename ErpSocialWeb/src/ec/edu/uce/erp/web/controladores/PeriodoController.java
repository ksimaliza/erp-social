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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.PeriodoDataManager;

@ViewScoped
@ManagedBean (name = "periodoController")

public class PeriodoController extends BaseController {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final Logger slf4jLogger = LoggerFactory.getLogger(PeriodoController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{periodoDataManager}")
	private PeriodoDataManager periodoDataManager;
	
	public void setPeriodoDataManager(PeriodoDataManager periodoDataManager) {
		this.periodoDataManager = periodoDataManager;
	}
	
	public PeriodoDataManager getPeriodoDataManager() {
		return periodoDataManager;
	}

	public PeriodoController ()
	{
		
	}
	
	/*
	 * Medodos
	 */

	public void registrarPeriodo () {
		
		slf4jLogger.info("registrarPeriodo");
		try {
			
			periodoDataManager.getPeriodoInstancia().setPerEmpresa(getEmpresaCode());
			PeriodoDTO periodoNuevo = this.servicioMatricula.createOrUpdatePeriodo(this.periodoDataManager.getPeriodoInstancia());
			if (periodoNuevo != null) {
				periodoDataManager.setPeriodoInstancia(new PeriodoDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.periodo.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarPeriodo () {
		slf4jLogger.info("buscarPeriodo");
		
		List<PeriodoDTO> listaperiodos=null;
		
		try {
							
			periodoDataManager.getPeriodoBuscar().setPerEmpresa(getEmpresaCode());
			listaperiodos = this.servicioMatricula.buscarPeriodo(periodoDataManager.getPeriodoBuscar());
			
			if (CollectionUtils.isEmpty(listaperiodos) && listaperiodos.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.periodoDataManager.setPeriodoLista(listaperiodos);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPeriodo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosPeriodo (PeriodoDTO periodoDTO) {
		try {
			PeriodoDTO periodoEncontrado = servicioMatricula.obtenerPeriodoPorId(periodoDTO.getPerCodigo());
			
			this.periodoDataManager.setPeriodoInstancia(periodoEncontrado);
									
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosPeriodo seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosPeriodo seleccionado");
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
}
