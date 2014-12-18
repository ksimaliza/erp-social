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
				this.reporteDefuncionDataManager
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
			cat.setCatCodigo(reporteDefuncionDataManager.getCodigoProvincia());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);
			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteDefuncionDataManager
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
			cat.setCatCodigo(reporteDefuncionDataManager.getCodigoCanton());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);

			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteDefuncionDataManager
						.setListParroquia(listaCatalogo);
			}

		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCiudad {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	public void buscar() {
		slf4jLogger.info("buscarDefuncion");
		List<DefuncionListDTO> listResultado=new ArrayList<DefuncionListDTO>();
		try {
			reporteDefuncionDataManager.getDefuncionListDTO().setDefParroquia(reporteDefuncionDataManager.getCodigoParroquia());
			reporteDefuncionDataManager.getDefuncionListDTO().setDefProvincia(reporteDefuncionDataManager.getCodigoProvincia());
			reporteDefuncionDataManager.getDefuncionListDTO().setDefCanton(reporteDefuncionDataManager.getCodigoCanton());
			listResultado = this.servicioEucaristia.readDefuncionReport(reporteDefuncionDataManager.getDefuncionListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
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
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("fechaActual", find(reporteDefuncionDataManager.getCodigoParroquia(), reporteDefuncionDataManager.getListParroquia()).getCatDescripcion() + ",  " + full.format(fechaActual));
			mapParametros.put("parroquia",find(reporteDefuncionDataManager.getCodigoParroquia(), reporteDefuncionDataManager.getListParroquia()).getCatDescripcion());
			mapParametros.put("provincia", find(reporteDefuncionDataManager.getCodigoProvincia(), reporteDefuncionDataManager.getListProvincia()).getCatDescripcion());
			mapParametros.put("desde", pequena.format(reporteDefuncionDataManager.getDefuncionListDTO().getFechaDesde()));
			mapParametros.put("hasta", pequena.format(reporteDefuncionDataManager.getDefuncionListDTO().getFechaHasta()));
			mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(),reporteDefuncionDataManager.getDefuncionListDTOs(), "reporteDefuncion", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteDefuncionDataManager.getFormatoPdf(), "reporteDefuncion");
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
