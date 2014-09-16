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
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.enums.EnumTipoBien;
import ec.edu.uce.erp.common.enums.EnumTipoTransaccionMasiva;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;
import ec.edu.uce.erp.web.controladores.componentes.BuscarUsuarioComponent;
import ec.edu.uce.erp.web.datamanager.TransaccionMasivaBienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="transaccionMasivaBienController")
public class TransaccionMasivaBienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(TransaccionMasivaBienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{transaccionMasivaBienDataManager}")
	private TransaccionMasivaBienDataManager transaccionMasivaBienDataManager;
	
	private List<SelectItem> dcTipoTransaccion;
	private List<SelectItem> dcCategoriaBien;
	private List<SelectItem> dcLineaBien;
	private List<SelectItem> dcEmpleadosEmpresa;
	
	private Integer idCategoriaBienSeleccionado;
	private Integer idLineaBienSeleccionado;
	private String idTransaccionSeleccionado;
	private Integer idCustudioAsignado;
	private Integer idCustudioReasignado;
	
	private Boolean verTablaResultadoAsignar = Boolean.FALSE;
	
	private BuscarUsuarioComponent buscarUsuarioComponent;
	
	/**
	 * @param transaccionMasivaBienDataManager the transaccionMasivaBienDataManager to set
	 */
	public void setTransaccionMasivaBienDataManager(
			TransaccionMasivaBienDataManager transaccionMasivaBienDataManager) {
		this.transaccionMasivaBienDataManager = transaccionMasivaBienDataManager;
	}
	
	@Override
	public void refrescarFormulario() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		this.transaccionMasivaBienDataManager.setListVistaBienTramitar(new ArrayList<VistaBien>());
		this.transaccionMasivaBienDataManager.setListVistaBienTramitado(new ArrayList<VistaBien>());
		this.transaccionMasivaBienDataManager.setListVistaBienTraslado(new ArrayList<VistaBien>());
		this.dcLineaBien = new ArrayList<SelectItem>();
		this.buscarUsuarioComponent = new BuscarUsuarioComponent(servicioInventario);
	}

	/**
	 * Buscar bienes a trav&eacute;s de una vista
	 */
	public void buscarVistaBienAsignar () {
		
		slf4jLogger.info("buscarVistaBien");
		
		try {
				
			this.transaccionMasivaBienDataManager.getListVistaBien().clear();
			this.transaccionMasivaBienDataManager.getVistaBienBuscar().setEmrPk(this.transaccionMasivaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.transaccionMasivaBienDataManager.getVistaBienBuscar().setCatBienPk(this.idCategoriaBienSeleccionado);
			this.transaccionMasivaBienDataManager.getVistaBienBuscar().setLinBienPk(this.idLineaBienSeleccionado);
			this.transaccionMasivaBienDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(EnumTipoBien.INGRESADO.getId());
			this.transaccionMasivaBienDataManager.getVistaBienBuscar().setBieEstado(this.transaccionMasivaBienDataManager.getEstadoActivo());
			this.transaccionMasivaBienDataManager.getVistaBienBuscar().setTraEstado(this.transaccionMasivaBienDataManager.getEstadoActivo());
			
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.transaccionMasivaBienDataManager.getVistaBienBuscar());
			
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.transaccionMasivaBienDataManager.setListVistaBien(listVistaBien);
			} else {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				
			}
			
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	/**
	 * Buscar bienes para traslado
	 */
	public void buscarVistaBienTraslado () {
		
		slf4jLogger.info("buscarVistaBienTraslado");
		
		try {
			
			this.transaccionMasivaBienDataManager.getListVistaBienTraslado().clear();
			this.transaccionMasivaBienDataManager.getListVistaBienTramitar().clear();
			
			if (this.buscarUsuarioComponent.getVistaEmpleadoSeleccionado()==null || 
					this.buscarUsuarioComponent.getVistaEmpleadoSeleccionado().getEmpPk()==null) {
				MensajesWebController.aniadirMensajeAdvertencia("Debe seleccionar un custodio para realizar la busqueda");
			} else {
				
				this.transaccionMasivaBienDataManager.getListVistaBien().clear();
				this.transaccionMasivaBienDataManager.getVistaBienBuscar().setEmrPk(this.transaccionMasivaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
				this.transaccionMasivaBienDataManager.getVistaBienBuscar().setCatBienPk(this.idCategoriaBienSeleccionado);
				this.transaccionMasivaBienDataManager.getVistaBienBuscar().setLinBienPk(this.idLineaBienSeleccionado);
//				this.transaccionMasivaBienDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(EnumTipoBien.INGRESADO.getId());
				this.transaccionMasivaBienDataManager.getVistaBienBuscar().setBieEstado(this.transaccionMasivaBienDataManager.getEstadoActivo());
				this.transaccionMasivaBienDataManager.getVistaBienBuscar().setTraEstado(this.transaccionMasivaBienDataManager.getEstadoActivo());
				this.transaccionMasivaBienDataManager.getVistaBienBuscar().setPerCi(this.buscarUsuarioComponent.getVistaEmpleadoSeleccionado().getPerCi());
				
				List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.transaccionMasivaBienDataManager.getVistaBienBuscar());
				
				if (CollectionUtils.isNotEmpty(listVistaBien)) {
					this.transaccionMasivaBienDataManager.setListVistaBienTraslado(listVistaBien);
				} else {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
					
				}
			}
			
			
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void limpiarFiltrosBusqueda () {
		this.transaccionMasivaBienDataManager.setVistaBienBuscar(new VistaBien());
		this.transaccionMasivaBienDataManager.getListVistaBien().clear();
		this.transaccionMasivaBienDataManager.getListVistaBienTraslado().clear();
		this.idCategoriaBienSeleccionado = null;
		this.idLineaBienSeleccionado = null;
		this.idTransaccionSeleccionado = null;
		this.buscarUsuarioComponent.setVistaEmpleadoSeleccionado(null);
	}
	
	public void asignarElementoTramitar (VistaBien vistaBien) {
		if (vistaBien !=null) {
			
			if (vistaBien.getIsSelected()) {
				this.transaccionMasivaBienDataManager.getListVistaBienTramitar().add(vistaBien);
			} else {
				this.transaccionMasivaBienDataManager.getListVistaBienTramitar().remove(vistaBien);
			}
			
		}
	}
	
	public String asignarBienMasivo () {
		
		slf4jLogger.info("asignarBienMasivo");
		
		try {
			
			if (CollectionUtils.isNotEmpty(this.transaccionMasivaBienDataManager.getListVistaBienTramitar())) {
				
				List<VistaBien> listVistaBien = new ArrayList<VistaBien>();
				
				for (VistaBien vistaBien : this.transaccionMasivaBienDataManager.getListVistaBienTramitar()) {
					vistaBien.setBieUbicacion(this.transaccionMasivaBienDataManager.getVistaBienEditar().getBieUbicacion());
					vistaBien.setUsuarioRegistro(this.transaccionMasivaBienDataManager.getUsuarioSession());
					vistaBien.setEmpAsignadoFk(this.idCustudioAsignado);
					vistaBien.setNpNombreEmpresa(this.transaccionMasivaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrNombre());
					VistaBien vistaBienAsignado = servicioInventario.asignarBien(vistaBien);
					listVistaBien.add(vistaBienAsignado);
				}
				
				if (CollectionUtils.isNotEmpty(listVistaBien)) {
					MensajesWebController.aniadirMensajeInformacion("Bienes asignados correctamente");
					this.idCustudioAsignado = null;
					this.transaccionMasivaBienDataManager.getListVistaBienTramitar().clear();
					this.transaccionMasivaBienDataManager.getListVistaBien().clear();
					this.transaccionMasivaBienDataManager.getListVistaBienTramitado().clear();
					this.transaccionMasivaBienDataManager.getListVistaBienTramitado().addAll(listVistaBien);
				}
				
			}
			
		} catch (SeguridadesException e) {
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", e);
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
		return "administracionBien";
		
	}
	
	public void trasladoBienMasivo () {
		
		slf4jLogger.info("trasladoBienMasivo");
		
		try {
			
			if (CollectionUtils.isNotEmpty(this.transaccionMasivaBienDataManager.getListVistaBienTramitar())) {
				for (VistaBien vistaBien  : this.transaccionMasivaBienDataManager.getListVistaBienTramitar()) {
					vistaBien.setEmpAsignadoFk(this.idCustudioReasignado);
					VistaBien vistaBienTraslado = servicioInventario.reasignarBien(this.transaccionMasivaBienDataManager.getVistaBienEditar());
					if (vistaBienTraslado != null) {
						this.transaccionMasivaBienDataManager.getListVistaBienTramitado().add(vistaBienTraslado);
					}
				}
				if (CollectionUtils.isNotEmpty(this.transaccionMasivaBienDataManager.getListVistaBienTramitado())) {
					MensajesWebController.aniadirMensajeInformacion("Bienes trasladados correctament");
				}
			}
			
			this.limpiarFiltrosBusqueda();
			
		} catch (SeguridadesException e) {
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", e);
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void validarAsignacionCustodio () {
		slf4jLogger.info("validarAsignacionCustodio");
		
		if (this.transaccionMasivaBienDataManager.getVistaBienEditar().getEmpAsignadoFk().intValue() == this.idCustudioReasignado.intValue()){
			MensajesWebController.aniadirMensajeError("El custodio a reemplazar no puede ser el mismo");
			this.idCustudioReasignado = 0;
		}
		
	}
	
	public void asignarElementoTrasladar () {
		if (CollectionUtils.isNotEmpty(this.transaccionMasivaBienDataManager.getListVistaBienTramitar())) {
			this.transaccionMasivaBienDataManager.setVistaBienEditar(this.transaccionMasivaBienDataManager.getListVistaBienTramitar().iterator().next());
		}
	}
	
	public void generarActaBien () {
		
		List<VistaBien> listVistaBien = new ArrayList<VistaBien>();
		listVistaBien.addAll(this.transaccionMasivaBienDataManager.getListVistaBienTramitado());
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("nombreEmpresa", this.transaccionMasivaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrNombre());
		mapParametros.put("nombreCustodio", this.transaccionMasivaBienDataManager.getListVistaBienTramitado().iterator().next().getNombresCompletos());
		mapParametros.put("identificacionCustodio", this.transaccionMasivaBienDataManager.getListVistaBienTramitado().iterator().next().getPerCi());
		mapParametros.put("total", String.valueOf(listVistaBien.size()));
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		if (this.transaccionMasivaBienDataManager.getListVistaBienTramitado().iterator().next().getDetBienTipBieNivel1().
				equals(EnumTipoBien.ASIGNADO.getId())) {
			mapParametros.put("tituloActa", "Acta asignaci\u00F3n bien");
			mapParametros.put("tipoMovimiento", "Asignaci\u00F3n de bienes");
		} else if (this.transaccionMasivaBienDataManager.getListVistaBienTramitado().iterator().next().getDetBienTipBieNivel1().
				equals(EnumTipoBien.REASIGNADO.getId())) {
			mapParametros.put("tituloActa", "Acta reasignaci\u00F3n bien");
			mapParametros.put("tipoMovimiento", "Reasignaci\u00F3n de bienes");
		}
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), listVistaBien, "actaAsignacionBien", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.transaccionMasivaBienDataManager.getFormatoPdf(), "actaBien");
		
	}
	
	/**
	 * @return the fechaTransaccion
	 */
	public String getFechaTransaccion() {
		
		return UtilAplication.fechaActualConFormato("yyyy-MM-dd hh:mm a");
	}
	
	/**
	 * @return the dcTipoTransaccion
	 */
	public List<SelectItem> getDcTipoTransaccion() {
		
		if (CollectionUtils.isEmpty(dcTipoTransaccion)) {
			slf4jLogger.info("cargar getDcTipoTransaccion");
			dcTipoTransaccion = new ArrayList<SelectItem>();
			for (EnumTipoTransaccionMasiva enumTipoTransaccionMasiva : EnumTipoTransaccionMasiva.values()) {
				dcTipoTransaccion.add(new SelectItem(enumTipoTransaccionMasiva.getId(), enumTipoTransaccionMasiva.getLabel()));
			}
		}
		
		return dcTipoTransaccion;
	}
	
	/**
	 * @return the dcCategoriaBien
	 */
	public List<SelectItem> getDcCategoriaBien() {
		
		try {
			if (CollectionUtils.isEmpty(dcCategoriaBien)) {
				slf4jLogger.info("cargar dcCategoriaBien");
				dcCategoriaBien = UtilSelectItems.getInstancia().cargarSelectItemCategoriaBien(servicioInventario);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargar dcCategoriaBien {}", e.getCause().getMessage());
			e.printStackTrace();
		}
		
		return dcCategoriaBien;
	}
	
	public void cargarDcLineaBien () {
		
		try {
			this.dcLineaBien.clear();
			
			if (idCategoriaBienSeleccionado!=null && idCategoriaBienSeleccionado>0) {
				
				slf4jLogger.info("cargarDcLineaBien");
				
				LineaBien lineaBien = new LineaBien();
				lineaBien.setCatBienPk(idCategoriaBienSeleccionado);
				lineaBien.setLinBienEstado(this.transaccionMasivaBienDataManager.getEstadoActivo());
				List<LineaBien> listLineaBien = servicioInventario.buscarLineaBienCriterios(lineaBien);
				if (CollectionUtils.isEmpty(listLineaBien)){
					MensajesWebController.aniadirMensajeInformacion("La linea seleccionada no tiene categorias asignadas");
				} else {
					this.dcLineaBien.addAll(UtilSelectItems.getInstancia().cargarSelectItemsGenerico(listLineaBien, "linBienPk", "linBienNombre"));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargarDcCategoriaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError("No se pudo obtener las categorias de la base de datos");
		}
	}
	
	/**
	 * @return the dcEmpleadosEmpresa
	 */
	public List<SelectItem> getDcEmpleadosEmpresa() {
		
		try {
			if (CollectionUtils.isEmpty(dcEmpleadosEmpresa)) {
				slf4jLogger.info("cargar dcEmpleadosEmpresa");
				dcEmpleadosEmpresa = UtilSelectItems.getInstancia()
						.cargarSelectItemEmpleados(servicioInventario, this.transaccionMasivaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargar getDcEmpleadosEmpresa {}", e.getCause().getMessage());
			e.printStackTrace();
		}
		
		return dcEmpleadosEmpresa;
	}
	
	/**
	 * @return the idTransaccionSeleccionado
	 */
	public String getIdTransaccionSeleccionado() {
		return idTransaccionSeleccionado;
	}

	/**
	 * @param idTransaccionSeleccionado the idTransaccionSeleccionado to set
	 */
	public void setIdTransaccionSeleccionado(String idTransaccionSeleccionado) {
		this.idTransaccionSeleccionado = idTransaccionSeleccionado;
	}

	/**
	 * @return the idCategoriaBienSeleccionado
	 */
	public Integer getIdCategoriaBienSeleccionado() {
		return idCategoriaBienSeleccionado;
	}

	/**
	 * @param idCategoriaBienSeleccionado the idCategoriaBienSeleccionado to set
	 */
	public void setIdCategoriaBienSeleccionado(Integer idCategoriaBienSeleccionado) {
		this.idCategoriaBienSeleccionado = idCategoriaBienSeleccionado;
	}

	/**
	 * @return the idLineaBienSeleccionado
	 */
	public Integer getIdLineaBienSeleccionado() {
		return idLineaBienSeleccionado;
	}

	/**
	 * @param idLineaBienSeleccionado the idLineaBienSeleccionado to set
	 */
	public void setIdLineaBienSeleccionado(Integer idLineaBienSeleccionado) {
		this.idLineaBienSeleccionado = idLineaBienSeleccionado;
	}

	/**
	 * @return the dcLineaBien
	 */
	public List<SelectItem> getDcLineaBien() {
		return dcLineaBien;
	}

	/**
	 * @return the idCustudioAsignado
	 */
	public Integer getIdCustudioAsignado() {
		return idCustudioAsignado;
	}

	/**
	 * @param idCustudioAsignado the idCustudioAsignado to set
	 */
	public void setIdCustudioAsignado(Integer idCustudioAsignado) {
		this.idCustudioAsignado = idCustudioAsignado;
	}

	/**
	 * @return the verTablaResultadoAsignar
	 */
	public Boolean getVerTablaResultadoAsignar() {
		return verTablaResultadoAsignar;
	}

	/**
	 * @param verTablaResultadoAsignar the verTablaResultadoAsignar to set
	 */
	public void setVerTablaResultadoAsignar(Boolean verTablaResultadoAsignar) {
		this.verTablaResultadoAsignar = verTablaResultadoAsignar;
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

	/**
	 * @return the idCustudioReasignado
	 */
	public Integer getIdCustudioReasignado() {
		return idCustudioReasignado;
	}

	/**
	 * @param idCustudioReasignado the idCustudioReasignado to set
	 */
	public void setIdCustudioReasignado(Integer idCustudioReasignado) {
		this.idCustudioReasignado = idCustudioReasignado;
	}
	
}
