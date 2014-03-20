package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.NivelDataManager;

@ViewScoped
@ManagedBean (name = "NivelController")
public class NivelController {

private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{NivelDataManager}")
	private NivelDataManager nivelDataManager;
	
	public void setNivelDataManager(NivelDataManager nivelDataManager) {
		this.nivelDataManager = nivelDataManager;
	}
	
public NivelController() {
		
	}
	
	/*
	 * Medodos
	 */
	
	public void registrarNivel () {
		
		slf4jLogger.info("registrarNivel");
		try {
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
}
