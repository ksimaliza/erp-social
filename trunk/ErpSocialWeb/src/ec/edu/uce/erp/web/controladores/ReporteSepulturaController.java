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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReporteSepulturaDataManager;

@ViewScoped
@ManagedBean(name = "reporteSepulturaController")
public class ReporteSepulturaController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ReportePartidaBautizoController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;

	@EJB
	private ServicioAdministracion servicioAdministracion;

	@ManagedProperty(value = "#{reporteSepulturaDataManager}")
	private ReporteSepulturaDataManager reporteSepulturaDataManager;

	public ReporteSepulturaDataManager getReporteSepulturaDataManager() {
		return reporteSepulturaDataManager;
	}

	public void setReporteSepulturaDataManager(
			ReporteSepulturaDataManager reporteSepulturaDataManager) {
		this.reporteSepulturaDataManager = reporteSepulturaDataManager;
	}

	
	public ReporteSepulturaController() {

	}
	
	@PostConstruct
	public void inicializarObjetos() {
	}

	
	public void buscar() {
		slf4jLogger.info("buscarSepultura");
		List<SepulturaListDTO> listResultado=new ArrayList<SepulturaListDTO>();
		try {
			
			listResultado = this.servicioEucaristia.readSepulturaReport(reporteSepulturaDataManager.getSepulturaListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				reporteSepulturaDataManager.setExportDesactivado(false);
				this.reporteSepulturaDataManager.setSepulturaListDTOs(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSepultura {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void exportar() {
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
		DateFormat pequeña = DateFormat.getDateInstance(DateFormat.SHORT);
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("fechaActual", full.format(fechaActual));
			mapParametros.put("desde", pequeña.format(reporteSepulturaDataManager.getSepulturaListDTO().getFechaDesde()));
			mapParametros.put("hasta", pequeña.format(reporteSepulturaDataManager.getSepulturaListDTO().getFechaHasta()));
			mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteSepulturaDataManager.getSepulturaListDTOs(), "reportePartidasBautizos", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteSepulturaDataManager.getFormatoPdf(), "reportePartidasBautizos");
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
	
}
