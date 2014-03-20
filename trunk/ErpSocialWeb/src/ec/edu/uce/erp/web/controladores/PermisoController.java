package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.PermisoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.PermisoDataManager;


@ViewScoped
@ManagedBean (name = "PermisoController")
public class PermisoController {
private static final Logger slf4jLogger = LoggerFactory.getLogger(PermisoController.class);
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{PermisoDataManager}")
	private PermisoDataManager permisoDataManager;
	
	public void setPermisoDataManager(PermisoDataManager permisoDataManager) {
		this.permisoDataManager = permisoDataManager;
	}
	
	public PermisoController ()
	{
		
	}
	
	/*
	 * Medodos
	 */

	public void registrarPermiso () {
		
		slf4jLogger.info("registrarPermiso");
		try {
			PermisoDTO permisoNuevo = this.servicioAsistencia.createOrUpdatePermiso(this.permisoDataManager.getPermisoInstancia());
			if (permisoNuevo != null) {
				permisoDataManager.setPermisoInstancia(new PermisoVO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.permiso.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
}
