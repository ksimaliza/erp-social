package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.datamanager.EstudianteDataManager;

@ViewScoped
@ManagedBean (name = "EmpleadoController")
public class EmpleadoController {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{EmpleadoDataManager}")
	private EstudianteDataManager estudianteDataManager;
	
	public void setPerfilDataManager(EstudianteDataManager estudianteDataManager) {
		this.estudianteDataManager = estudianteDataManager;
	}
	

	
}
