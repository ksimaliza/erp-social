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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReporteMatrimonioDataManager;

@ViewScoped
@ManagedBean(name = "reporteMatrimonioController")
public class ReporteMatrimonioController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory
			.getLogger(ReporteMatrimonioController.class);

	@EJB
	private ServicioEucaristia servicioEucaristia;

	@EJB
	private ServicioAdministracion servicioAdministracion;

	@ManagedProperty(value = "#{reporteMatrimonioDataManager}")
	private ReporteMatrimonioDataManager reporteMatrimonioDataManager;

	public ReporteMatrimonioDataManager getReporteMatrimonioDataManager() {
		return reporteMatrimonioDataManager;
	}

	public void setReporteMatrimonioDataManager(
			ReporteMatrimonioDataManager reporteMatrimonioDataManager) {
		this.reporteMatrimonioDataManager = reporteMatrimonioDataManager;
	}
	public ReporteMatrimonioController() {

	}
	
	@PostConstruct
	public void inicializarObjetos() {
	
	}
	
	public void buscar() {
		slf4jLogger.info("buscarBautizo");
		List<MatrimonioListDTO> listResultado=new ArrayList<MatrimonioListDTO>();
		try {
			reporteMatrimonioDataManager.getMatrimonioListDTO().setMatEmpresa(getEmpresaTbl().getEmrPk());
			listResultado = this.servicioEucaristia.readMatrimonioReport(reporteMatrimonioDataManager.getMatrimonioListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				this.reporteMatrimonioDataManager.setMatrimonioListDTOs(new ArrayList<MatrimonioListDTO>());
				reporteMatrimonioDataManager.setExportDesactivado(true);
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				reporteMatrimonioDataManager.setExportDesactivado(false);
				this.reporteMatrimonioDataManager.setMatrimonioListDTOs(listResultado);
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
		MatrimonioListDTO matrimonio= new MatrimonioListDTO();
		matrimonio.setMatEmpresa(getEmpresaTbl().getEmrPk());
		
		    Map<String, Object> mapParametros = new HashMap<String, Object>();
		    mapParametros.put("fechaActual",String.valueOf(full.format(fechaActual).charAt(0)).toUpperCase() +full.format(fechaActual).substring(1));
		    if(reporteMatrimonioDataManager.getMatrimonioListDTO().getFechaDesde()==null){
				try {
					reporteMatrimonioDataManager.getMatrimonioListDTO().setFechaDesde(this.servicioEucaristia.obtenerFechaMinMatrimonio(matrimonio));
				} catch (SeguridadesException e) {
					slf4jLogger.info("Error al buscarMinimaFecha {} ", e);
					MensajesWebController.aniadirMensajeError(e.getMessage());
				}
				
			}
		    mapParametros.put("desde", pequena.format(reporteMatrimonioDataManager.getMatrimonioListDTO().getFechaDesde()));
		    
		    if(reporteMatrimonioDataManager.getMatrimonioListDTO().getFechaHasta()==null){
				try {
					reporteMatrimonioDataManager.getMatrimonioListDTO().setFechaHasta(this.servicioEucaristia.obtenerFechaMaxMatrimonio(matrimonio));
				} catch (SeguridadesException e) {
					slf4jLogger.info("Error al buscarMaximaFecha {} ", e);
					MensajesWebController.aniadirMensajeError(e.getMessage());
				}
			}
			mapParametros.put("hasta", pequena.format(reporteMatrimonioDataManager.getMatrimonioListDTO().getFechaHasta()));
			mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteMatrimonioDataManager.getMatrimonioListDTOs(), "reportePartidasMatrimonio", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteMatrimonioDataManager.getFormatoPdf(), "reportePartidasMatrimonio");
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
