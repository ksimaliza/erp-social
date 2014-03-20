package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.vo.FaltaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.FaltaDataManager;


@ViewScoped
@ManagedBean (name = "FaltaController")
public class FaltaController {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(FaltaController.class);
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{FaltaDataManager}")
	private FaltaDataManager faltaDataManager;
	
	public void setFaltaDataManager(FaltaDataManager faltaDataManager) {
		this.faltaDataManager = faltaDataManager;
	}
	

	public FaltaController () {}
	

	/*
	 * Medodos
	 */
	
	public void registrarFalta () {
		
		slf4jLogger.info("registrarFalta");
		try {
			FaltaDTO faltaNuevo = this.servicioAsistencia.createOrUpdateFalta(this.faltaDataManager.getFaltaInstancia());
			if (faltaNuevo != null) {
				faltaDataManager.setFaltaInstancia(new FaltaVO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.falta.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
}
