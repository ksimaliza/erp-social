package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.ejb.persistence.vo.RegistroAsistenciaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.RegistroAsistenciaDataManager;


@ViewScoped
@ManagedBean(name = "registroAsistenciaController")
public class RegistroAsistenciaController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(RegistroAsistenciaController.class);
	
	@ManagedProperty(value="#{registroAsistenciaDataManager}")
	private RegistroAsistenciaDataManager registroAsistenciaDataManager;
	
	
	public RegistroAsistenciaDataManager getRegistroAsistenciaDataManager() {
		return registroAsistenciaDataManager;
	}

	public void setRegistroAsistenciaDataManager(
			RegistroAsistenciaDataManager registroAsistenciaDataManager) {
		this.registroAsistenciaDataManager = registroAsistenciaDataManager;
	}

	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	
	
	public void registrar()
	{
		slf4jLogger.info("registrar");
		try {
			RegistroAsistenciaVO registroAsistenciaVO=new RegistroAsistenciaVO();
			registroAsistenciaVO.setEmpleadoDTO(registroAsistenciaDataManager.getEmpleado());
			registroAsistenciaVO.setRegistroDTO(registroAsistenciaDataManager.getRegistro());
			servicioAsistencia.createOrUpdateRegistroAsistencia(registroAsistenciaVO);
			clear();
			MensajesWebController.aniadirMensajeInformacion("Guardado Exitosamente");
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	private void clear()
	{
		registroAsistenciaDataManager.setEmpleado(new EmpleadoDTO());
		registroAsistenciaDataManager.setRegistro(new RegistroDTO());
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
}
