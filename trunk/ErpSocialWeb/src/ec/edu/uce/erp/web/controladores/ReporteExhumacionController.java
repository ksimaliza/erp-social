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
				this.reporteExhumacionDataManager
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
			cat.setCatCodigo(reporteExhumacionDataManager.getCodigoProvincia());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);
			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteExhumacionDataManager
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
			cat.setCatCodigo(reporteExhumacionDataManager.getCodigoCanton());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);

			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteExhumacionDataManager
						.setListParroquia(listaCatalogo);
			}

		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCiudad {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	public void buscar() {
		slf4jLogger.info("buscarBautizo");
		List<ExumacionListDTO> listResultado=new ArrayList<ExumacionListDTO>();
		try {
			reporteExhumacionDataManager.getExumacionListDTO().setCodigoparroquia(reporteExhumacionDataManager.getCodigoParroquia());
			reporteExhumacionDataManager.getExumacionListDTO().setCodigoprovincia(reporteExhumacionDataManager.getCodigoProvincia());
			reporteExhumacionDataManager.getExumacionListDTO().setCodigocanton(reporteExhumacionDataManager.getCodigoCanton());
			listResultado = this.servicioEucaristia.readExhumacionReport(reporteExhumacionDataManager.getExumacionListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
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
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("fechaActual", find(reporteExhumacionDataManager.getCodigoParroquia(), reporteExhumacionDataManager.getListParroquia()).getCatDescripcion() + ",  " + full.format(fechaActual));
			mapParametros.put("parroquia",find(reporteExhumacionDataManager.getCodigoParroquia(), reporteExhumacionDataManager.getListParroquia()).getCatDescripcion());
			mapParametros.put("provincia", find(reporteExhumacionDataManager.getCodigoProvincia(), reporteExhumacionDataManager.getListProvincia()).getCatDescripcion());
			mapParametros.put("desde", pequena.format(reporteExhumacionDataManager.getExumacionListDTO().getFechaDesde()));
			mapParametros.put("hasta", pequena.format(reporteExhumacionDataManager.getExumacionListDTO().getFechaHasta()));
			mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteExhumacionDataManager.getExumacionListDTOs(), "reporteExhumacion", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteExhumacionDataManager.getFormatoPdf(), "reporteExhumacion");
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
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


}
