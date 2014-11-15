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
				this.reporteConfirmacionDataManager
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
			cat.setCatCodigo(reporteConfirmacionDataManager.getCodigoProvincia());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);
			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteConfirmacionDataManager
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
			cat.setCatCodigo(reporteConfirmacionDataManager.getCodigoCanton());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);

			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteConfirmacionDataManager
						.setListParroquia(listaCatalogo);
			}

		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCiudad {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	public void buscar() {
		slf4jLogger.info("buscarConfirmacion");
		List<ConfirmacionListDTO> listResultado=new ArrayList<ConfirmacionListDTO>();
		try {
			reporteConfirmacionDataManager.getConfirmacionListDTO().setConParroquia(reporteConfirmacionDataManager.getCodigoParroquia());
			reporteConfirmacionDataManager.getConfirmacionListDTO().setConProvincia(reporteConfirmacionDataManager.getCodigoProvincia());
			reporteConfirmacionDataManager.getConfirmacionListDTO().setConCanton(reporteConfirmacionDataManager.getCodigoCanton());
			listResultado = this.servicioEucaristia.readConfirmacionReport(reporteConfirmacionDataManager.getConfirmacionListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
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
		DateFormat pequeña = DateFormat.getDateInstance(DateFormat.SHORT);
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("fechaActual", find(reporteConfirmacionDataManager.getCodigoParroquia(), reporteConfirmacionDataManager.getListParroquia()).getCatDescripcion() + ",  " + full.format(fechaActual));
		mapParametros.put("parroquia",find(reporteConfirmacionDataManager.getCodigoParroquia(), reporteConfirmacionDataManager.getListParroquia()).getCatDescripcion());
		mapParametros.put("provincia", find(reporteConfirmacionDataManager.getCodigoProvincia(), reporteConfirmacionDataManager.getListProvincia()).getCatDescripcion());
			mapParametros.put("desde", pequeña.format(reporteConfirmacionDataManager.getConfirmacionListDTO().getFechaDesde()));
			mapParametros.put("hasta", pequeña.format(reporteConfirmacionDataManager.getConfirmacionListDTO().getFechaHasta()));
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteConfirmacionDataManager.getConfirmacionListDTOs(), "reporteConfirmacion", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteConfirmacionDataManager.getFormatoPdf(), "reporteConfirmacion");
	}

	private CatalogoEucaristiaDTO find(Integer code,List<CatalogoEucaristiaDTO> list)
	{
		CatalogoEucaristiaDTO obj=null;
		for(CatalogoEucaristiaDTO cat:list)
		{
			if(cat.getCatCodigo()==code)
				obj=cat;
		}
		return obj;
		
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
