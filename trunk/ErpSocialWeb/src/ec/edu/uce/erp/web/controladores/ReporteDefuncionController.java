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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReporteDefuncionDataManager;

@ViewScoped
@ManagedBean(name = "reporteDefuncionController")
public class ReporteDefuncionController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ReporteDefuncionController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;

	@EJB
	private ServicioAdministracion servicioAdministracion;

	@ManagedProperty(value = "#{reporteDefuncionDataManager}")
	private ReporteDefuncionDataManager reporteDefuncionDataManager;

	public ReporteDefuncionDataManager getReporteDefuncionDataManager() {
		return reporteDefuncionDataManager;
	}

	public void setReporteDefuncionDataManager(
			ReporteDefuncionDataManager reporteDefuncionDataManager) {
		this.reporteDefuncionDataManager = reporteDefuncionDataManager;
	}
	
	public ReporteDefuncionController() {

	}
	
	@PostConstruct
	public void inicializarObjetos() {
	}
	
	public void buscar() {
		slf4jLogger.info("buscarDefuncion");
		List<DefuncionListDTO> listResultado=new ArrayList<DefuncionListDTO>();
		try {
			reporteDefuncionDataManager.getDefuncionListDTO().setDefEmpresa(getEmpresaTbl().getEmrPk());
			listResultado = this.servicioEucaristia.readDefuncionReport(reporteDefuncionDataManager.getDefuncionListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				this.reporteDefuncionDataManager.setDefuncionListDTOs(new ArrayList<DefuncionListDTO>());
				reporteDefuncionDataManager.setExportDesactivado(true);
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				reporteDefuncionDataManager.setExportDesactivado(false);
				this.reporteDefuncionDataManager.setDefuncionListDTOs(listResultado);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDefuncion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void exportar() {
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
		DateFormat pequena = DateFormat.getDateInstance(DateFormat.SHORT);
		DefuncionListDTO defuncion= new DefuncionListDTO();
		defuncion.setDefEmpresa(getEmpresaTbl().getEmrPk());
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("fechaActual",String.valueOf(full.format(fechaActual).charAt(0)).toUpperCase() +full.format(fechaActual).substring(1));
			if(reporteDefuncionDataManager.getDefuncionListDTO().getFechaDesde()==null){
				try {
					reporteDefuncionDataManager.getDefuncionListDTO().setFechaDesde(this.servicioEucaristia.obtenerFechaMinDefuncion(defuncion));
				} catch (SeguridadesException e) {
					slf4jLogger.info("Error al buscarMinimaFecha {} ", e);
					MensajesWebController.aniadirMensajeError(e.getMessage());
				}
				
			}
			mapParametros.put("desde", pequena.format(reporteDefuncionDataManager.getDefuncionListDTO().getFechaDesde()));
			if(reporteDefuncionDataManager.getDefuncionListDTO().getFechaHasta()==null){
				try {
					reporteDefuncionDataManager.getDefuncionListDTO().setFechaHasta(this.servicioEucaristia.obtenerFechaMaxDefuncion(defuncion));
				} catch (SeguridadesException e) {
					slf4jLogger.info("Error al buscarMaximaFecha {} ", e);
					MensajesWebController.aniadirMensajeError(e.getMessage());
				}
			}mapParametros.put("hasta", pequena.format(reporteDefuncionDataManager.getDefuncionListDTO().getFechaHasta()));
			mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteDefuncionDataManager.getDefuncionListDTOs(), "reporteDefuncion", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteDefuncionDataManager.getFormatoPdf(), "reporteDefuncion");
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}


}
