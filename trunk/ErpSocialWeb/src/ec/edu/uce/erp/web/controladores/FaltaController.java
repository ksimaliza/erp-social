package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
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
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.FaltaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.FaltaDataManager;


@ViewScoped
@ManagedBean (name = "faltaController")
public class FaltaController extends BaseController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(FaltaController.class);
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{faltaDataManager}")
	private FaltaDataManager faltaDataManager;
	
	public void setFaltaDataManager(FaltaDataManager faltaDataManager) {
		this.faltaDataManager = faltaDataManager;
	}

	public FaltaDataManager getFaltaDataManager() {
		return faltaDataManager;
	}

	public FaltaController () {}
	


	@PostConstruct
	private void init()
	{
		buscarEmpleado();
	}
	
	/*
	 * Medodos
	 */
	
	public void registrarFalta () {
		
		slf4jLogger.info("registrarFalta");
		FaltaVO faltaVO=new FaltaVO();
		try {
			faltaDataManager.getEmpleadoDTOInsertar().setAemCodigo(Integer.valueOf(faltaDataManager.getEmpleadoCodigo().toString()));
			faltaDataManager.getFaltaInsertar().setFalFecha(new Timestamp(faltaDataManager.getFechaFalta().getTime()));
			faltaVO.setEmpleado(faltaDataManager.getEmpleadoDTOInsertar());
			faltaVO.setFalta(faltaDataManager.getFaltaInsertar());
			FaltaDTO faltaNuevo = this.servicioAsistencia.createOrUpdateFalta(faltaVO);
			
			if (faltaNuevo != null) {
				faltaDataManager.setFaltaInsertar(new FaltaDTO());
				faltaDataManager.setFechaFalta(null);
				faltaDataManager.setEmpleadoCodigo(null);
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.falta.registrar.exito");
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
				this.faltaDataManager.setEmpleadoList(listResultado);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void buscar() {
		slf4jLogger.info("buscarEmpleado");
		List<FaltaListDTO> listResultado=new ArrayList<FaltaListDTO>();
		try {
			listResultado = this.servicioAsistencia.readFalta(faltaDataManager.getFaltaBuscar());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.faltaDataManager.setFaltaList(listResultado);
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
