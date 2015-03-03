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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReporteExhumacionDataManager;

@ViewScoped
@ManagedBean(name = "reporteExhumacionController")
public class ReporteExhumacionController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ReporteExhumacionController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;

	@EJB
	private ServicioAdministracion servicioAdministracion;

	@ManagedProperty(value = "#{reporteExhumacionDataManager}")
	private ReporteExhumacionDataManager reporteExhumacionDataManager;

	public ReporteExhumacionDataManager getReporteExhumacionDataManager() {
		return reporteExhumacionDataManager;
	}

	public void setReporteExhumacionDataManager(
			ReporteExhumacionDataManager reporteExhumacionDataManager) {
		this.reporteExhumacionDataManager = reporteExhumacionDataManager;
	}
	
	public ReporteExhumacionController() {

	}
	
	@PostConstruct
	public void inicializarObjetos() {
		
	}
	
	public void buscar() {
		slf4jLogger.info("buscarExumacion");
		List<ExumacionListDTO> listResultado=new ArrayList<ExumacionListDTO>();
		try {
			reporteExhumacionDataManager.getExumacionListDTO().setExuEmpresa(getEmpresaTbl().getEmrPk());
			listResultado = this.servicioEucaristia.readExhumacionReport(reporteExhumacionDataManager.getExumacionListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				this.reporteExhumacionDataManager.setExumacionListDTOs(new ArrayList<ExumacionListDTO>());
				reporteExhumacionDataManager.setExportDesactivado(true);
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				reporteExhumacionDataManager.setExportDesactivado(false);
				this.reporteExhumacionDataManager.setExumacionListDTOs(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBautizo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void exportar() {
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
		DateFormat pequena = DateFormat.getDateInstance(DateFormat.SHORT);
		ExumacionListDTO exumacion= new ExumacionListDTO();
		exumacion.setExuEmpresa(getEmpresaTbl().getEmrPk());
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("fechaActual",String.valueOf(full.format(fechaActual).charAt(0)).toUpperCase() +full.format(fechaActual).substring(1));
			if(reporteExhumacionDataManager.getExumacionListDTO().getFechaDesde()==null){
				try {
					reporteExhumacionDataManager.getExumacionListDTO().setFechaDesde(this.servicioEucaristia.obtenerFechaMinExhumacion(exumacion));
				} catch (SeguridadesException e) {
					slf4jLogger.info("Error al buscarMinimaFecha {} ", e);
					MensajesWebController.aniadirMensajeError(e.getMessage());
				}
				
			}
			mapParametros.put("desde", pequena.format(reporteExhumacionDataManager.getExumacionListDTO().getFechaDesde()));
			if(reporteExhumacionDataManager.getExumacionListDTO().getFechaHasta()==null){
				try {
					reporteExhumacionDataManager.getExumacionListDTO().setFechaHasta(this.servicioEucaristia.obtenerFechaMaxExhumacion(exumacion));
				} catch (SeguridadesException e) {
					slf4jLogger.info("Error al buscarMaximaFecha {} ", e);
					MensajesWebController.aniadirMensajeError(e.getMessage());
				}
			}
			mapParametros.put("hasta", pequena.format(reporteExhumacionDataManager.getExumacionListDTO().getFechaHasta()));
			mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteExhumacionDataManager.getExumacionListDTOs(), "reporteExhumacion", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteExhumacionDataManager.getFormatoExcel(), "reporteExhumacion");
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
