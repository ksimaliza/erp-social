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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReporteComunionDataManager;


@ViewScoped
@ManagedBean(name = "reporteComunionController")
public class ReporteComunionController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ReporteComunionController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;

	@EJB
	private ServicioAdministracion servicioAdministracion;

	@ManagedProperty(value = "#{reporteComunionDataManager}")
	private ReporteComunionDataManager reporteComunionDataManager;

	
	public ReporteComunionDataManager getReporteComunionDataManager() {
		return reporteComunionDataManager;
	}


	public void setReporteComunionDataManager(
			ReporteComunionDataManager reporteComunionDataManager) {
		this.reporteComunionDataManager = reporteComunionDataManager;
	}

	public ReporteComunionController() {

	}
	
	@PostConstruct
	public void inicializarObjetos() {
	}

	
	
	public void buscar() {
		slf4jLogger.info("buscarComunion");
		List<ComunionListDTO> listResultado=new ArrayList<ComunionListDTO>();
		try {
			
			reporteComunionDataManager.getComunionListDTO().setPcoEmpresa(getEmpresaTbl().getEmrPk());
			listResultado = this.servicioEucaristia.readComunionReport(reporteComunionDataManager.getComunionListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				this.reporteComunionDataManager.setComunionListDTOs(new ArrayList<ComunionListDTO>());
				reporteComunionDataManager.setExportDesactivado(true);
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				reporteComunionDataManager.setExportDesactivado(false);
				this.reporteComunionDataManager.setComunionListDTOs(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarComunion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void exportar() {
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
		DateFormat pequena = DateFormat.getDateInstance(DateFormat.SHORT);
		ComunionListDTO comunion= new ComunionListDTO();
		comunion.setPcoEmpresa(getEmpresaTbl().getEmrPk());
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
				mapParametros.put("fechaActual",String.valueOf(full.format(fechaActual).charAt(0)).toUpperCase() +full.format(fechaActual).substring(1));
				mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
				if(reporteComunionDataManager.getComunionListDTO().getFechaDesde()==null){
					try {
						reporteComunionDataManager.getComunionListDTO().setFechaDesde(this.servicioEucaristia.obtenerFechaMinComunion(comunion));
					} catch (SeguridadesException e) {
						slf4jLogger.info("Error al buscarMinimaFecha {} ", e);
						MensajesWebController.aniadirMensajeError(e.getMessage());
					}
				}
				mapParametros.put("desde", pequena.format(reporteComunionDataManager.getComunionListDTO().getFechaDesde()));
				if(reporteComunionDataManager.getComunionListDTO().getFechaHasta()==null){
					try {
						reporteComunionDataManager.getComunionListDTO().setFechaHasta(this.servicioEucaristia.obtenerFechaMaxComunion(comunion));
					} catch (SeguridadesException e) {
						slf4jLogger.info("Error al buscarMaximaFecha {} ", e);
						MensajesWebController.aniadirMensajeError(e.getMessage());
					}
				}
				mapParametros.put("hasta", pequena.format(reporteComunionDataManager.getComunionListDTO().getFechaHasta()));
				mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteComunionDataManager.getComunionListDTOs(), "reportePrimeraComunion", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteComunionDataManager.getFormatoPdf(), "reportePrimeraComunion");
	}
	

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
