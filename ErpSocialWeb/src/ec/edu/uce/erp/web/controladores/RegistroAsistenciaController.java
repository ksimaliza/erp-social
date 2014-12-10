package ec.edu.uce.erp.web.controladores;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.persistence.PostLoad;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JasperPrint;

import org.primefaces.event.CaptureEvent;
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
	
	public void oncapture(CaptureEvent captureEvent) {
        
        byte[] data = captureEvent.getData();
         
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "img" +
                                     File.separator + "photocam" + File.separator + "aa" + ".png";
         
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }
        catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }
	
	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
}
