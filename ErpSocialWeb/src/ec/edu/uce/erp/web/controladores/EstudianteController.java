package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.EstudianteDataManager;


@ViewScoped
@ManagedBean (name = "EstudianteController")
public class EstudianteController {
	
		private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilController.class);
		
		@EJB
		private ServicioMatricula servicioMatricula;
		
		@ManagedProperty(value="#{EstudianteDataManager}")
		private EstudianteDataManager estudianteDataManager;
		
		public void setPerfilDataManager(EstudianteDataManager estudianteDataManager) {
			this.estudianteDataManager = estudianteDataManager;
		}
		

		public EstudianteController () {}
		
		
		/*
		 * Medodos
		 */
		
		public void registrarEstudiante () {
			
			slf4jLogger.info("registrarEstudiante");
			try {
				EstudianteDTO estudianteNuevo = this.servicioMatricula.createOrUpdateEstudiante(this.estudianteDataManager.getEstudianteInstancia());
				if (estudianteNuevo != null) {
					estudianteDataManager.setEstudianteInstancia(new EstudianteVO());
					MensajesWebController.aniadirMensajeInformacion("erp.matricula.estudiante.registrar.exito");
				}
				
			} catch (SeguridadesException e) {
				slf4jLogger.info(e.toString());
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
			
		}
}
