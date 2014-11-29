/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.controladores.componentes.BuscarUsuarioComponent;
import ec.edu.uce.erp.web.datamanager.ReporteInventarioDataManager;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean(name="reporteInventarioController")
public class ReporteInventarioController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteInventarioController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	/**
	 * Exportar datos del reporte
	 */
	private String tipoReporte;
	
	private BuscarUsuarioComponent buscarUsuarioComponent;
	
	@ManagedProperty(value="#{reporteInventarioDataManager}")
	private ReporteInventarioDataManager reporteInventarioDataManager;
	
	/**
	 * @param reporteInventarioDataManager the reporteInventarioDataManager to set
	 */
	public void setReporteInventarioDataManager(ReporteInventarioDataManager reporteInventarioDataManager) {
		this.reporteInventarioDataManager = reporteInventarioDataManager;
	}
	
	public ReporteInventarioController () {}
	
	@PostConstruct
	public void inicializarObjetos() {
		this.buscarUsuarioComponent = new BuscarUsuarioComponent(servicioInventario, 
				this.reporteInventarioDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
		this.reporteInventarioDataManager.cargarCatalogos();
	}
	
	@Override
	public void refrescarFormulario() {
	}
	
	public void generarReporte () {
		
		slf4jLogger.info("generarReporte...");
		
		try {
			
			reporteInventarioDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(reporteInventarioDataManager.getIdDcTipoBienSelec());
			reporteInventarioDataManager.getVistaBienBuscar().setCatBienPk(reporteInventarioDataManager.getIdCategoriaBienSeleccionado());
			reporteInventarioDataManager.getVistaBienBuscar().setLinBienPk(reporteInventarioDataManager.getIdLineaBienSeleccionado());
//			reporteInventarioDataManager.getVistaBienBuscar().setBieEstado(reporteInventarioDataManager.getEstadoActivo());
			reporteInventarioDataManager.getVistaBienBuscar().setPerCi(this.buscarUsuarioComponent.getVistaEmpleadoSeleccionado().getPerCi());
			reporteInventarioDataManager.getVistaBienBuscar().setEmrPk(this.reporteInventarioDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			reporteInventarioDataManager.getVistaBienBuscar().setTraEstado(reporteInventarioDataManager.getEstadoActivo());
			
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(reporteInventarioDataManager.getVistaBienBuscar());
			
			if (CollectionUtils.isEmpty(listVistaBien)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.reporte.vacio");
			} else {
				
				Map<String, Object> mapParametros = new HashMap<String, Object>();
				mapParametros.put("nombreEmpresa", this.reporteInventarioDataManager.getUsuarioSession().getEmpresaTbl().getEmrNombre());
				mapParametros.put("tituloActa", "Reporte bienes");
				mapParametros.put("fechaGeneracionActa", new Date());
				mapParametros.put("total", String.valueOf(listVistaBien.size()));
				
				if (StringUtils.isNotBlank(reporteInventarioDataManager.getUsuarioSession().getEmpresaTbl().getEmrFotoNombre())) {
					
					String imgPath = JsfUtil.descargarArchivo(
							reporteInventarioDataManager.getUsuarioSession().getEmpresaTbl().getEmrFotoNombre(),
							reporteInventarioDataManager.getUsuarioSession().getEmpresaTbl().getEmrFoto());
					
					slf4jLogger.info("imgPath... {}", imgPath);
					
					mapParametros.put("imagesRealPath", getServletContext().getRealPath(imgPath));
					
				} else {
					mapParametros.put("imagesRealPath", null);
				}
				
				JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), listVistaBien, "reporteBienInventario", mapParametros);
				ReporteUtil.generarReporte(jasperPrint, this.tipoReporte, "reporteBienInventario");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void onCompleteBuscarUsuarioComponent () {
		slf4jLogger.info("onCompleteBuscarUsuarioComponent");
	}
	
	public void limpiarFiltrosBusqueda () {
		this.reporteInventarioDataManager.limpiarFiltrosBusqueda();
		this.buscarUsuarioComponent.setVistaEmpleadoSeleccionado(new VistaEmpleado());
	}
	
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the buscarUsuarioComponent
	 */
	public BuscarUsuarioComponent getBuscarUsuarioComponent() {
		return buscarUsuarioComponent;
	}

	/**
	 * @param buscarUsuarioComponent the buscarUsuarioComponent to set
	 */
	public void setBuscarUsuarioComponent(
			BuscarUsuarioComponent buscarUsuarioComponent) {
		this.buscarUsuarioComponent = buscarUsuarioComponent;
	}
	
}
