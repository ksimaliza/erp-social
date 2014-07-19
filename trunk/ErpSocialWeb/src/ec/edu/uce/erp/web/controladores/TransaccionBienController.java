/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.enums.EnumTipoBien;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.MessagesWebApplicacion;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
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
	
	/**
	 * @param vistaBienDataManager the vistaBienDataManager to set
	 */
	public void setVistaBienDataManager(VistaBienDataManager vistaBienDataManager) {
		this.vistaBienDataManager = vistaBienDataManager;
	}
	
	public TransaccionBienController () {}
	

	/**
	 * Buscar bienes a trav&eacute;s de una vista
	 */
	public void buscarVistaBien () {
		
		slf4jLogger.info("buscarVistaBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienBuscar().setEmrPk(this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.vistaBienDataManager.getVistaBienBuscar().setTraEstado(this.vistaBienDataManager.getEstadoActivo());
			this.vistaBienDataManager.getVistaBienBuscar().setPerCi(this.vistaBienDataManager.getIdCIEmpleadoSeleccionado());
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
			VistaBien vistaBien = servicioInventario.asignarBien(this.vistaBienDataManager.getVistaBienEditar());
			
			if (vistaBien != null) {
				int posicion = this.vistaBienDataManager.getListVistaBien().indexOf(this.vistaBienDataManager.getVistaBienEditar());
				this.vistaBienDataManager.getListVistaBien().remove(this.vistaBienDataManager.getVistaBienEditar());
				this.vistaBienDataManager.getListVistaBien().add(posicion, vistaBien);
				MensajesWebController.aniadirMensajeInformacion("Bien asignado correctamente");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
		return "administracionBien";
		
	}
	
	public void reasignarBien () {
		
		slf4jLogger.info("reasignarBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienEditar().setEmpAsignadoFk(this.vistaBienDataManager.getIdCustudioReasignado());
			VistaBien vistaBien = servicioInventario.reasignarBien(this.vistaBienDataManager.getVistaBienEditar());
			
			if (vistaBien != null) {
				int posicion = this.vistaBienDataManager.getListVistaBien().indexOf(this.vistaBienDataManager.getVistaBienEditar());
				this.vistaBienDataManager.getListVistaBien().remove(this.vistaBienDataManager.getVistaBienEditar());
				this.vistaBienDataManager.getListVistaBien().add(posicion, vistaBien);
				this.vistaBienDataManager.setIdCustudioReasignado(0);
				MensajesWebController.aniadirMensajeInformacion("Bien reasignado correctamente");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void devolverBien () {
		
		slf4jLogger.info("devolverBien");
		
		try {
			// los bienes devueltos se asignan a la bodega
			this.vistaBienDataManager.getVistaBienEditar().setEmpAsignadoFk(this.vistaBienDataManager.getUsuarioSession().getIdUsuario());
			VistaBien vistaBien = servicioInventario.devolverBien(this.vistaBienDataManager.getVistaBienEditar());
			if (vistaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("Bien devuelto correctamente");
			}
			
		} catch (Exception e) {
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
		
		List<VistaBien> listVistaBien = new ArrayList<VistaBien>();
		listVistaBien.add(this.vistaBienDataManager.getVistaBienEditar());
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("nombreEmpresa", this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrNombre());
		mapParametros.put("nombreCustodio", this.vistaBienDataManager.getVistaBienEditar().getNombresCompletos());
		mapParametros.put("identificacionCustodio", this.vistaBienDataManager.getVistaBienEditar().getPerCi());
		mapParametros.put("total", String.valueOf(listVistaBien.size()));
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		if (this.vistaBienDataManager.getVistaBienEditar().getDetBienTipBieNivel1().equals(EnumTipoBien.ASIGNADO.getId())) {
			mapParametros.put("tituloActa", MessagesWebApplicacion.getString("erp.bien.actas.reporte.asignacion.titulo"));
			mapParametros.put("tipoMovimiento", MessagesWebApplicacion.getString("erp.bien.actas.reporte.tipo.movimiento.asignacion"));
		} else if (this.vistaBienDataManager.getVistaBienEditar().getDetBienTipBieNivel1().equals(EnumTipoBien.REASIGNADO.getId())) {
			mapParametros.put("tituloActa", MessagesWebApplicacion.getString("erp.bien.actas.reporte.reasignacion.titulo"));
			mapParametros.put("tipoMovimiento", MessagesWebApplicacion.getString("erp.bien.actas.reporte.tipo.movimiento.reasignacion"));
		}
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), listVistaBien, "actaAsignacionBien", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, "pdf", "actaBien");
		
	}
	
	public void buscarEmpleado () {
		slf4jLogger.info("buscarEmpleado");
		
		try {
			this.vistaBienDataManager.getListVistaEmpleado().clear();
			this.vistaBienDataManager.getVistaEmpleadoBuscar().setEmrFk(this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			List<VistaEmpleado> listVistaEmpleado = servicioInventario.obtenerEmpleadoEmpresa(this.vistaBienDataManager.getVistaEmpleadoBuscar());
			if (CollectionUtils.isEmpty(listVistaEmpleado)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.vistaBienDataManager.setListVistaEmpleado(listVistaEmpleado);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarEmpleado {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void asignarDatosEmpleadoSeleccionado (SelectEvent event) {
		VistaEmpleado vistaEmpleado = (VistaEmpleado)event.getObject();
		this.vistaBienDataManager.setIdCIEmpleadoSeleccionado(vistaEmpleado.getPerCi());
		slf4jLogger.info("buscarEmpleado");
	}
	
	public void limpiarFiltrosBusqueda () {
		this.vistaBienDataManager.setVistaBienBuscar(new VistaBien());
		this.vistaBienDataManager.setIdCIEmpleadoSeleccionado(null);
		this.vistaBienDataManager.setIdDcTipoBienSelec(null);
		this.vistaBienDataManager.setIdCategoriaBienSeleccionado(null);
		this.vistaBienDataManager.setIdLineaBienSeleccionado(null);
		this.vistaBienDataManager.getDcLineaBien().clear();
	}
	

	/**
	 * @return the fechaTransaccion
	 */
	public String getFechaTransaccion() {
		
		return UtilAplication.fechaActualConFormato("yyyy-MM-dd hh:mm a");
	}


	
}
