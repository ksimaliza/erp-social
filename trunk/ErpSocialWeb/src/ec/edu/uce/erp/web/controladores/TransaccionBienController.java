/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
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
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.enums.EnumTipoBien;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.view.VistaActaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.controladores.componentes.BuscarUsuarioComponent;
import ec.edu.uce.erp.web.datamanager.VistaBienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="transaccionBienController")
public class TransaccionBienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(TransaccionBienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{vistaBienDataManager}")
	private VistaBienDataManager vistaBienDataManager;
	
	private BuscarUsuarioComponent buscarUsuarioComponent;
	
	/**
	 * @param vistaBienDataManager the vistaBienDataManager to set
	 */
	public void setVistaBienDataManager(VistaBienDataManager vistaBienDataManager) {
		this.vistaBienDataManager = vistaBienDataManager;
	}
	
	public TransaccionBienController () {}
	
	@PostConstruct
	public void inicializarObjetos() {
		
		this.buscarUsuarioComponent = new BuscarUsuarioComponent(servicioInventario, 
				this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
	}
	
	/**
	 * Buscar bienes a trav&eacute;s de una vista
	 */
	public void buscarVistaBien () {
		
		slf4jLogger.info("buscarVistaBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienBuscar().setEmrPk(this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.vistaBienDataManager.getVistaBienBuscar().setTraEstado(this.vistaBienDataManager.getEstadoActivo());
			this.vistaBienDataManager.getVistaBienBuscar().setPerCi(this.buscarUsuarioComponent.getVistaEmpleadoSeleccionado().getPerCi());
			
			this.vistaBienDataManager.getVistaBienBuscar().setBieEstado(this.vistaBienDataManager.getEstadoActivo());
			
			if (StringUtils.isNotBlank(this.vistaBienDataManager.getIdDcTipoBienSelec())) {
				this.vistaBienDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(this.vistaBienDataManager.getIdDcTipoBienSelec());
			}
			
			this.vistaBienDataManager.getVistaBienBuscar().setCatBienPk(this.vistaBienDataManager.getIdCategoriaBienSeleccionado());
			this.vistaBienDataManager.getVistaBienBuscar().setLinBienPk(this.vistaBienDataManager.getIdLineaBienSeleccionado());
			
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.vistaBienDataManager.getVistaBienBuscar());
			
			this.vistaBienDataManager.getListVistaBien().clear();
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.vistaBienDataManager.setListVistaBien(listVistaBien);
			} else {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	
	public String asignarBien () {
		
		slf4jLogger.info("asignarBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienEditar().setUsuarioRegistro(this.vistaBienDataManager.getUsuarioSession());
			this.vistaBienDataManager.getVistaBienEditar().setEmpAsignadoFk(this.vistaBienDataManager.getIdCustudioAsignado());
			this.vistaBienDataManager.getVistaBienEditar().setNpNombreEmpresa(this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrNombre());
			
			List<VistaBien> listVistaBienAsignar = new ArrayList<VistaBien>();
			listVistaBienAsignar.add(this.vistaBienDataManager.getVistaBienEditar());
			
			List<VistaBien> listVistaBien = servicioInventario.asignarBien(listVistaBienAsignar);
			
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.buscarVistaBien();
				MensajesWebController.aniadirMensajeInformacion("Bien asignado correctamente con el c\u00F3digo: " + listVistaBien.iterator().next().getBieCodigo());
				this.vistaBienDataManager.setIdCustudioAsignado(null);
				this.vistaBienDataManager.setVistaBienEditar(new VistaBien());
			}
			
		} catch (SeguridadesException e) {
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", e);
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
		return "administracionBien";
		
	}
	
	public void reasignarBien () {
		
		slf4jLogger.info("reasignarBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienEditar().setEmpAsignadoFk(this.vistaBienDataManager.getIdCustudioReasignado());
			List<VistaBien> listVistaBienReasignar = new ArrayList<>();
			listVistaBienReasignar.add(this.vistaBienDataManager.getVistaBienEditar());
			List<VistaBien> colVistaBien = servicioInventario.reasignarBien(listVistaBienReasignar);
			
			if (colVistaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("Bien reasignado correctamente");
				this.vistaBienDataManager.setIdCustudioReasignado(null);
			}
			
		} catch (SeguridadesException e) {
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", e);
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void devolverBien () {
		
		slf4jLogger.info("devolverBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienEditar().setEmpAsignadoFk(this.vistaBienDataManager.getUsuarioSession().getIdUsuario());
			VistaBien vistaBien = servicioInventario.devolverBien(this.vistaBienDataManager.getVistaBienEditar());
			if (vistaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("Bien devuelto correctamente");
			}
			
		} catch (Exception e) {
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", e);
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void darBajaBien () {
		slf4jLogger.info("darBajaBien");
		
		try {
			this.vistaBienDataManager.getVistaBienEditar().setDetCatalogoTipoBaja(this.vistaBienDataManager.getIdDcBajaBienSelec());
			VistaBien vistaBien = servicioInventario.darBajaBien(this.vistaBienDataManager.getVistaBienEditar());
			
			if (vistaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("Baja realizada correctamente");
			}
			
		} catch (Exception e) {
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", e);
			slf4jLogger.info("error al darBajaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void validarAsignacionCustodio () {
		slf4jLogger.info("validarAsignacionCustodio");
		
		if (this.vistaBienDataManager.getVistaBienEditar().getEmpAsignadoFk().intValue() == this.vistaBienDataManager.getIdCustudioReasignado().intValue()){
			MensajesWebController.aniadirMensajeError("El custodio a reemplazar no puede ser el mismo");
			this.vistaBienDataManager.setIdCustudioReasignado(0);
		}
		
	}
	
	public void obtenerTrazabilidadBien () {
		slf4jLogger.info("obtenerTrazabilidadBien");
		
		try {
			VistaTransaccion vistaTransaccion = new VistaTransaccion();
			vistaTransaccion.setBieFk(this.vistaBienDataManager.getVistaBienEditar().getBiePk());
			this.vistaBienDataManager.setListVistaTransaccion(servicioInventario.obtenerVistaTransaccionCriterios(vistaTransaccion));
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al obtenerTrazabilidadBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void generarActaBien () {
		
		try {
			
			VistaActaBien vistaActaBien = new VistaActaBien();
			vistaActaBien.setBiePk(this.vistaBienDataManager.getVistaBienEditar().getBiePk());
			vistaActaBien.setEmrPk(this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			List<VistaActaBien> listVistaActaBien = servicioInventario.obtenerActaBien(vistaActaBien);
			
			if (CollectionUtils.isEmpty(listVistaActaBien)) {
				
				MensajesWebController.aniadirMensajeAdvertencia("No hay datos para generar el acta del bien");
				
			} else {
				
				Map<String, Object> mapParametros = new HashMap<String, Object>();
				mapParametros.put("nombreEmpresa", this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrNombre());
				mapParametros.put("nombreCustodio", listVistaActaBien.iterator().next().getNombresCompletos());
				mapParametros.put("identificacionCustodio", listVistaActaBien.iterator().next().getPerCi());
				mapParametros.put("fechaGeneracionActa", listVistaActaBien.iterator().next().getActBieFechaGen());
				mapParametros.put("total", String.valueOf(listVistaActaBien.size()));
				
				if (StringUtils.isNotBlank(vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrFotoNombre())) {
					
					String imgPath = JsfUtil.descargarArchivo(
							vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrFotoNombre(),
									vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrFoto());
					
					mapParametros.put("imagesRealPath", getServletContext().getRealPath(imgPath));
					
				} else {
					mapParametros.put("imagesRealPath", null);
				}
				
				if (this.vistaBienDataManager.getVistaBienEditar().getDetBienTipBieNivel1().equals(EnumTipoBien.ASIGNADO.getId())) {
					mapParametros.put("tituloActa", "Acta asignaci\u00F3n bien");
					mapParametros.put("tipoMovimiento", "Asignaci\u00F3n de bienes");
				} else if (this.vistaBienDataManager.getVistaBienEditar().getDetBienTipBieNivel1().equals(EnumTipoBien.REASIGNADO.getId())) {
					mapParametros.put("tituloActa", "Acta reasignaci\u00F3n bien");
					mapParametros.put("tipoMovimiento", "Reasignaci\u00F3n de bienes");
				}
				
				JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), listVistaActaBien, "actaAsignacionBien", mapParametros);
				ReporteUtil.generarReporte(jasperPrint, this.vistaBienDataManager.getFormatoPdf(), "actaBien");
				
			}
			
		} catch (SeguridadesException e) {
			
			slf4jLogger.info("error al obtenerTrazabilidadBien {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
			
		}
		
	}
	
	public void limpiarFiltrosBusqueda () {
		this.vistaBienDataManager.setVistaBienBuscar(new VistaBien());
		this.vistaBienDataManager.setIdDcTipoBienSelec(null);
		this.vistaBienDataManager.setIdCategoriaBienSeleccionado(null);
		this.vistaBienDataManager.setIdLineaBienSeleccionado(null);
		this.vistaBienDataManager.getDcLineaBien().clear();
		this.buscarUsuarioComponent.setVistaEmpleadoSeleccionado(new VistaEmpleado());
	}
	
	public void onCompleteBuscarUsuarioComponent () {
		slf4jLogger.info("onCompleteBuscarUsuarioComponent");
	}
	
	/**
	 * @return the fechaTransaccion
	 */
	public String getFechaTransaccion() {
		
		return UtilAplication.fechaActualConFormato("yyyy-MM-dd hh:mm a");
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

	@Override
	public void refrescarFormulario() {
		this.buscarVistaBien();
	}

}
