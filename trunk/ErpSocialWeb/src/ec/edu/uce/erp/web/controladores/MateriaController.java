package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.MateriaDataManager;

@ViewScoped
@ManagedBean (name = "MateriaController")

public class MateriaController {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MateriaController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{MateriaDataManager}")
	private MateriaDataManager materiaDataManager;
	
	public void setMateriaDataManager(MateriaDataManager materiaDataManager) {
		this.materiaDataManager = materiaDataManager;
	}
	

	public MateriaController() {
		
	}
	
	/*
	 * Medodos
	 */
	
	public void registrarMateria () {
		
		slf4jLogger.info("registrarMateria");
		try {
			MateriaDTO materiaNueva = this.servicioMatricula.createOrUpdateMateria(this.materiaDataManager.getMateriaInstancia());
			if (materiaNueva != null) {
				materiaDataManager.setMateriaInstancia(new MateriaDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.materia.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
}
