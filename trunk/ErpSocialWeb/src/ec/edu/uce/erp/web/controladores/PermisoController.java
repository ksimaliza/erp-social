package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.PermisoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.PermisoDataManager;


@ViewScoped
@ManagedBean (name = "permisoController")
public class PermisoController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PermisoController.class);
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{permisoDataManager}")
	private PermisoDataManager permisoDataManager;
	
	public PermisoDataManager getPermisoDataManager() {
		return permisoDataManager;
	}

	public void setPermisoDataManager(PermisoDataManager permisoDataManager) {
		this.permisoDataManager = permisoDataManager;
	}
	
	public PermisoController ()
	{
		
	}
	
	@PostConstruct
	private void init()
	{
		buscarEmpleado();
	}
	
	
	/*Medodos*/
	public void registrarPermiso () {
		
		slf4jLogger.info("registrarPermiso");
		try {
			PermisoVO permisoVO=new PermisoVO();
			permisoVO.setPermiso(permisoDataManager.getPermiso());
			permisoDataManager.getEmpleado().setAemCodigo(Integer.valueOf(permisoDataManager.getEmpleadoCodigo().toString()));
			permisoVO.setEmpleado(permisoDataManager.getEmpleado());
			PermisoDTO permiso= this.servicioAsistencia.createOrUpdatePermiso(permisoVO);
			if (permiso != null) {
				permisoVO=new PermisoVO();
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.permiso.registrar.exito");
			}			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}		
	}
	
	
	public void buscarEmpleado() {
		slf4jLogger.info("buscarEmpleado");
		List<EmpleadoListDTO> listResultado=new ArrayList<EmpleadoListDTO>();
		try {
			listResultado = this.servicioAsistencia.readEmpleado(new EmpleadoListDTO());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.permisoDataManager.setEmpleadoList(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void buscar() {
		slf4jLogger.info("buscar");
		List<PermisoListDTO> listResultado=new ArrayList<PermisoListDTO>();
		try {
			listResultado = this.servicioAsistencia.readPermiso(permisoDataManager.getPermisoBuscar());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.permisoDataManager.setPermisoList(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

	
}
