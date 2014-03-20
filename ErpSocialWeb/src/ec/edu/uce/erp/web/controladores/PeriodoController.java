package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.PeriodoDataManager;

@ViewScoped
@ManagedBean (name = "PeriodoController")

public class PeriodoController {
private static final Logger slf4jLogger = LoggerFactory.getLogger(PeriodoController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{PeriodoDataManager}")
	private PeriodoDataManager periodoDataManager;
	
	public void setPeriodoDataManager(PeriodoDataManager periodoDataManager) {
		this.periodoDataManager = periodoDataManager;
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
}
