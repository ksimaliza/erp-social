package ec.edu.uce.erp.web.controladores;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReporteConfirmacionDataManager;


@ViewScoped
@ManagedBean(name = "reporteConfirmacionController")
public class ReporteConfirmacionController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ReporteConfirmacionController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;

	@EJB
	private ServicioAdministracion servicioAdministracion;

	@ManagedProperty(value = "#{reporteConfirmacionDataManager}")
	private ReporteConfirmacionDataManager reporteConfirmacionDataManager;
	

	public ReporteConfirmacionDataManager getReporteConfirmacionDataManager() {
		return reporteConfirmacionDataManager;
	}

	public void setReporteConfirmacionDataManager(
			ReporteConfirmacionDataManager reporteConfirmacionDataManager) {
		this.reporteConfirmacionDataManager = reporteConfirmacionDataManager;
	}

	public ReporteConfirmacionController() {

	}
	
	@PostConstruct
	public void inicializarObjetos() {
	}

	
	public void buscar() {
		slf4jLogger.info("buscarConfirmacion");
		List<ConfirmacionListDTO> listResultado=new ArrayList<ConfirmacionListDTO>();
		try {
			reporteConfirmacionDataManager.getConfirmacionListDTO().setConEmpresa(getEmpresaTbl().getEmrPk());
			listResultado = this.servicioEucaristia.readConfirmacionReport(reporteConfirmacionDataManager.getConfirmacionListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				this.reporteConfirmacionDataManager.setConfirmacionListDTOs(new ArrayList<ConfirmacionListDTO>());
				reporteConfirmacionDataManager.setExportDesactivado(true);
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				reporteConfirmacionDataManager.setExportDesactivado(false);
				this.reporteConfirmacionDataManager.setConfirmacionListDTOs(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarConfirmacion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void exportar() {
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
		DateFormat pequena = DateFormat.getDateInstance(DateFormat.SHORT);
		ConfirmacionListDTO confirmacion= new ConfirmacionListDTO();
		confirmacion.setConEmpresa(getEmpresaTbl().getEmrPk());
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("fechaActual",String.valueOf(full.format(fechaActual).charAt(0)).toUpperCase() +full.format(fechaActual).substring(1));
		mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
		if(reporteConfirmacionDataManager.getConfirmacionListDTO().getFechaDesde()==null){
			try {
				reporteConfirmacionDataManager.getConfirmacionListDTO().setFechaDesde(this.servicioEucaristia.obtenerFechaMinConfirmacion(confirmacion));
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al buscarMinimaFecha {} ", e);
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
			
		}
		mapParametros.put("desde", pequena.format(reporteConfirmacionDataManager.getConfirmacionListDTO().getFechaDesde()));
		if(reporteConfirmacionDataManager.getConfirmacionListDTO().getFechaHasta()==null){
			try {
				reporteConfirmacionDataManager.getConfirmacionListDTO().setFechaHasta(this.servicioEucaristia.obtenerFechaMaxConfirmacion(confirmacion));
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al buscarMaximaFecha {} ", e);
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
		}
		mapParametros.put("hasta", pequena.format(reporteConfirmacionDataManager.getConfirmacionListDTO().getFechaHasta()));
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteConfirmacionDataManager.getConfirmacionListDTOs(), "reporteConfirmacion", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteConfirmacionDataManager.getFormatoExcel(), "reporteConfirmacion");
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
