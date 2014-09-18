package ec.edu.uce.erp.web.controladores;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JasperPrint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.ejb.persistence.vo.RegistroAsistenciaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
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
		RegistroAsistenciaVO registroAsistenciaVO;
		try {
			registroAsistenciaVO=new RegistroAsistenciaVO();
			registroAsistenciaVO.setEmpleadoDTO(registroAsistenciaDataManager.getEmpleado());
			registroAsistenciaVO.setRegistroDTO(registroAsistenciaDataManager.getRegistro());
			RegistroDTO reg=servicioAsistencia.createOrUpdateRegistroAsistencia(registroAsistenciaVO);
			if(reg.getRasTipoEntrada()=="Atraso")
			{
				registroAsistenciaDataManager.setDesactivarImprimir(false);
				registroAsistenciaDataManager.setRegistro(reg);
			}
			else
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
		registroAsistenciaDataManager.setDesactivarImprimir(true);
	}
	
	public void imprimir()
	{
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		
		mapParametros.put("nombreUsuario", registroAsistenciaDataManager.getEmpleado().getAemUsuario());
		mapParametros.put("horaRegistro", registroAsistenciaDataManager.getRegistro().getRasHoraInicio());
		mapParametros.put("tiempoAtraso", (registroAsistenciaDataManager.getRegistro().getRasHoraInicio().getTime()-new Date().getTime())/(1000*60));
		mapParametros.put("horaImpresion", UtilAplication.fechaActualConFormato("yyyy-MM-dd hh:mm:ss"));
		
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "imprimirAtraso", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.registroAsistenciaDataManager.getFormatoPdf(), "imprimirAtraso");
	}
	
	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
}
