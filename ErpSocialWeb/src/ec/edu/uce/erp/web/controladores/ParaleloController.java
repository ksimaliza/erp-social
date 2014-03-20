package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ParaleloDataManager;


@ViewScoped
@ManagedBean (name = "ParaleloController")

public class ParaleloController {
	
private static final Logger slf4jLogger = LoggerFactory.getLogger(ParaleloController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{ParaleloDataManager}")
	private ParaleloDataManager paraleloDataManager;
	
	public void setParaleloDataManager(ParaleloDataManager paraleloDataManager) {
		this.paraleloDataManager = paraleloDataManager;
	}
	
public ParaleloController() {
		
	}
	
/*
 * Medodos
 */

public void registrarParalelo () {
	
	slf4jLogger.info("registrarParalelo");
	try {
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
}
