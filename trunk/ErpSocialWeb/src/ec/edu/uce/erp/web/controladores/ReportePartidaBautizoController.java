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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReportePartidaBautizoDataManager;

@ViewScoped
@ManagedBean(name = "reportePartidaBautizoController")
public class ReportePartidaBautizoController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ReportePartidaBautizoController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;

	@EJB
	private ServicioAdministracion servicioAdministracion;

	@ManagedProperty(value = "#{reportePartidaBautizoDataManager}")
	private ReportePartidaBautizoDataManager reportePartidaBautizoDataManager;
	
	public ReportePartidaBautizoDataManager getReportePartidaBautizoDataManager() {
		return reportePartidaBautizoDataManager;
	}

	public void setReportePartidaBautizoDataManager(
			ReportePartidaBautizoDataManager reportePartidaBautizoDataManager) {
		this.reportePartidaBautizoDataManager = reportePartidaBautizoDataManager;
	}

	public ReportePartidaBautizoController() {

	}
	
	@PostConstruct
	public void inicializarObjetos() {
	}

	

	
	public void buscar() {
		slf4jLogger.info("buscarBautizo");
		List<BautizoListDTO> listResultado=new ArrayList<BautizoListDTO>();
		try {
			reportePartidaBautizoDataManager.getBautizoListDTO().setBauEmpresa(getEmpresaTbl().getEmrPk());
			listResultado = this.servicioEucaristia.readBautizoReport(reportePartidaBautizoDataManager.getBautizoListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				this.reportePartidaBautizoDataManager.setBautizoListDTOs(new ArrayList<BautizoListDTO>());
				this.reportePartidaBautizoDataManager.setExportDesactivado(true);
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				reportePartidaBautizoDataManager.setExportDesactivado(false);
				this.reportePartidaBautizoDataManager.setBautizoListDTOs(listResultado);
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
		BautizoListDTO bautizo= new BautizoListDTO();
		bautizo.setBauEmpresa(getEmpresaTbl().getEmrPk());
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("fechaActual", full.format(fechaActual));
			if(reportePartidaBautizoDataManager.getBautizoListDTO().getFechaDesde()==null){
				try {
					reportePartidaBautizoDataManager.getBautizoListDTO().setFechaDesde(this.servicioEucaristia.obtenerFechaMinBautizo(bautizo));
				} catch (SeguridadesException e) {
					slf4jLogger.info("Error al buscarMinimaFecha {} ", e);
					MensajesWebController.aniadirMensajeError(e.getMessage());
				}
				
			}
			mapParametros.put("desde", pequena.format(reportePartidaBautizoDataManager.getBautizoListDTO().getFechaDesde()));
			if(reportePartidaBautizoDataManager.getBautizoListDTO().getFechaHasta()==null){
				try {
					reportePartidaBautizoDataManager.getBautizoListDTO().setFechaHasta(this.servicioEucaristia.obtenerFechaMaxBautizo(bautizo));
				} catch (SeguridadesException e) {
					slf4jLogger.info("Error al buscarMaximaFecha {} ", e);
					MensajesWebController.aniadirMensajeError(e.getMessage());
				}
			}
			mapParametros.put("hasta", pequena.format(reportePartidaBautizoDataManager.getBautizoListDTO().getFechaHasta()));
			mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
			
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reportePartidaBautizoDataManager.getBautizoListDTOs(), "reportePartidasBautizos", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reportePartidaBautizoDataManager.getFormatoPdf(), "reportePartidasBautizos");
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
