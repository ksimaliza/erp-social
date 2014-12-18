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
				this.reporteMatrimonioDataManager
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
			cat.setCatCodigo(reporteMatrimonioDataManager.getCodigoProvincia());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);
			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteMatrimonioDataManager
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
			cat.setCatCodigo(reporteMatrimonioDataManager.getCodigoCanton());
			listaCatalogo = this.servicioEucaristia.buscarCatalogo(cat);

			if (CollectionUtils.isEmpty(listaCatalogo)
					&& listaCatalogo.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteMatrimonioDataManager
						.setListParroquia(listaCatalogo);
			}

		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCiudad {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	public void buscar() {
		slf4jLogger.info("buscarBautizo");
		List<MatrimonioListDTO> listResultado=new ArrayList<MatrimonioListDTO>();
		try {
			reporteMatrimonioDataManager.getMatrimonioListDTO().setMatParroquia(reporteMatrimonioDataManager.getCodigoParroquia());
			reporteMatrimonioDataManager.getMatrimonioListDTO().setMatProvincia(reporteMatrimonioDataManager.getCodigoProvincia());
			reporteMatrimonioDataManager.getMatrimonioListDTO().setMatCanton(reporteMatrimonioDataManager.getCodigoCanton());
			listResultado = this.servicioEucaristia.readMatrimonioReport(reporteMatrimonioDataManager.getMatrimonioListDTO());
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
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
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("fechaActual", find(reporteMatrimonioDataManager.getCodigoParroquia(), reporteMatrimonioDataManager.getListParroquia()).getCatDescripcion() + ",  " + full.format(fechaActual));
			mapParametros.put("parroquia",find(reporteMatrimonioDataManager.getCodigoParroquia(), reporteMatrimonioDataManager.getListParroquia()).getCatDescripcion());
			mapParametros.put("provincia", find(reporteMatrimonioDataManager.getCodigoProvincia(), reporteMatrimonioDataManager.getListProvincia()).getCatDescripcion());
			mapParametros.put("desde", pequena.format(reporteMatrimonioDataManager.getMatrimonioListDTO().getFechaDesde()));
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
