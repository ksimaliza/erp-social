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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
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
		buscarProvincia();
	}

	public void buscarProvincia() {
		slf4jLogger.info("buscarCatalogo");

		List<CatalogoEucaristiaDTO> listaCatalogo = null;

		try {
			CatalogoEucaristiaDTO cat = new CatalogoEucaristiaDTO();
			cat.setCatCodigo(1);
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);

			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteComunionDataManager
						.setListProvincia(listaCatalogo);
			}

		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCatalogo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}

	public void buscarCanton() {
		slf4jLogger.info("buscarCanton");
		List<CatalogoEucaristiaDTO> listaCatalogo = null;
		try {
			CatalogoEucaristiaDTO cat = new CatalogoEucaristiaDTO();
			cat.setCatCodigo(reporteComunionDataManager.getCodigoProvincia());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);
			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteComunionDataManager
						.setListCanton(listaCatalogo);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCanton {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	public void buscarParroquia() {
		slf4jLogger.info("buscarParroquia");

		List<CatalogoEucaristiaDTO> listaCatalogo = null;

		try {
			CatalogoEucaristiaDTO cat = new CatalogoEucaristiaDTO();
			cat.setCatCodigo(reporteComunionDataManager.getCodigoCanton());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);

			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteComunionDataManager
						.setListParroquia(listaCatalogo);
			}

		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCiudad {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	public void buscar() {
		slf4jLogger.info("buscarComunion");
		List<ComunionListDTO> listResultado=new ArrayList<ComunionListDTO>();
		try {
			reporteComunionDataManager.getComunionListDTO().setPcoParroquia(reporteComunionDataManager.getCodigoParroquia());
			reporteComunionDataManager.getComunionListDTO().setPcoProvincia(reporteComunionDataManager.getCodigoProvincia());
			reporteComunionDataManager.getComunionListDTO().setPcoCanton(reporteComunionDataManager.getCodigoCanton());
			
			listResultado = this.servicioEucaristia.readComunionReport(reporteComunionDataManager.getComunionListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
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
		DateFormat pequeña = DateFormat.getDateInstance(DateFormat.SHORT);
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		for (int i=0 ;i>=reporteComunionDataManager.getListParroquia().size();i++)
		{
			if (reporteComunionDataManager.getListParroquia().get(i).getCatCodigo() == (Integer) reporteComunionDataManager.getCodigoParroquia())
			{
				mapParametros.put("fechaActual", reporteComunionDataManager.getListParroquia().get(i).getCatDescripcion() + ",  " + full.format(fechaActual));
				mapParametros.put("parroquia", reporteComunionDataManager.getListParroquia().get(i).getCatDescripcion());
			}
		}	
		for (int j=0 ;j>=reporteComunionDataManager.getListProvincia().size();j++)
			if (reporteComunionDataManager.getListProvincia().get(j).getCatCodigo() == (Integer) reporteComunionDataManager.getCodigoProvincia())
				mapParametros.put("provincia", reporteComunionDataManager.getListProvincia().get(j).getCatDescripcion());
			
			mapParametros.put("desde", pequeña.format(reporteComunionDataManager.getComunionListDTO().getFechaDesde()));
			mapParametros.put("hasta", pequeña.format(reporteComunionDataManager.getComunionListDTO().getFechaHasta()));
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteComunionDataManager.getComunionListDTOs(), "reportePrimeraComunion", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteComunionDataManager.getFormatoPdf(), "reportePrimeraComunion");
	}
	
	


	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
