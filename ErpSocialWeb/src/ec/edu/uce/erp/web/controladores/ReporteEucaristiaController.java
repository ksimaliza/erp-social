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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReporteEucaristiaDataManager;

@ViewScoped
@ManagedBean(name = "reporteEucaristiaController")
public class ReporteEucaristiaController extends LoginController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ReporteEucaristiaController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;

	@EJB
	private ServicioAdministracion servicioAdministracion;

	@ManagedProperty(value = "#{reporteEucaristiaDataManager}")
	private ReporteEucaristiaDataManager reporteEucaristiaDataManager;

	
	public ReporteEucaristiaDataManager getReporteEucaristiaDataManager() {
		return reporteEucaristiaDataManager;
	}

	public void setReporteEucaristiaDataManager(
			ReporteEucaristiaDataManager reporteEucaristiaDataManager) {
		this.reporteEucaristiaDataManager = reporteEucaristiaDataManager;
	}

	public ReporteEucaristiaController() {

	}
	
	@PostConstruct
	public void inicializarObjetos() {
	/*	buscarProvincia();*/
	}

		public void buscar() {
		slf4jLogger.info("buscarEucaristia");
		List<EucaristiaListDTO> listResultado=new ArrayList<EucaristiaListDTO>();
		try {
			listResultado = this.servicioEucaristia.readEucaristiaReport(reporteEucaristiaDataManager.getEucaristiaListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				this.reporteEucaristiaDataManager.setEucaristiaListDTOs( new ArrayList<EucaristiaListDTO>());
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				reporteEucaristiaDataManager.setExportDesactivado(true);
			} else {
				reporteEucaristiaDataManager.setExportDesactivado(false);
				this.reporteEucaristiaDataManager.setEucaristiaListDTOs(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarEucaristia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void exportar() {
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
		DateFormat pequeña = DateFormat.getDateInstance(DateFormat.SHORT);
		Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("fechaActual", full.format(fechaActual));
			mapParametros.put("empresa", this.getUsuario().getEmpresaTbl().getEmrNombre().toUpperCase());
			if(reporteEucaristiaDataManager.getEucaristiaListDTO().getFechaDesde()==null){
				Date fechaDesde=reporteEucaristiaDataManager.getEucaristiaListDTOs().get(0).getEucFechaHora();
				for (EucaristiaListDTO eucaristiaListDTO : reporteEucaristiaDataManager.getEucaristiaListDTOs()) {
					if(eucaristiaListDTO.getEucFechaHora().before(fechaDesde)) fechaDesde= eucaristiaListDTO.getEucFechaHora();
				}
				mapParametros.put("desde", pequeña.format(fechaDesde));
			}else mapParametros.put("desde", pequeña.format(reporteEucaristiaDataManager.getEucaristiaListDTO().getFechaDesde()));
			if(reporteEucaristiaDataManager.getEucaristiaListDTO().getFechaHasta()==null){
				Date fechaHasta=reporteEucaristiaDataManager.getEucaristiaListDTOs().get(0).getEucFechaHora();
				for (EucaristiaListDTO eucaristiaListDTO : reporteEucaristiaDataManager.getEucaristiaListDTOs()) {
					if(eucaristiaListDTO.getEucFechaHora().after(fechaHasta)) fechaHasta= eucaristiaListDTO.getEucFechaHora();
				}
					mapParametros.put("hasta", pequeña.format(fechaHasta));
				}else mapParametros.put("hasta", pequeña.format(reporteEucaristiaDataManager.getEucaristiaListDTO().getFechaHasta()));
			
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
			
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteEucaristiaDataManager.getEucaristiaListDTOs(), "reporteEucaristia", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteEucaristiaDataManager.getFormatoPdf(), "reporteEucaristia");
	}
	
	
	
	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
