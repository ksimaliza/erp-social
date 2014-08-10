/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.controladores.componentes.BuscarUsuarioComponent;
import ec.edu.uce.erp.web.datamanager.BajasBienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="bajasBienController")
public class BajasBienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BajasBienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{bajasBienDataManager}")
	private BajasBienDataManager bajasBienDataManager;
	
	private BuscarUsuarioComponent buscarUsuarioComponent;
	
	/**
	 * @param bajasBienDataManager the bajasBienDataManager to set
	 */
	public void setBajasBienDataManager(BajasBienDataManager bajasBienDataManager) {
		this.bajasBienDataManager = bajasBienDataManager;
	}

	public BajasBienController () {}
	
	@PostConstruct
	public void inicializarObjetos() {
		this.buscarUsuarioComponent = new BuscarUsuarioComponent(servicioInventario);
	}
	

	/**
	 * Buscar bienes a trav&eacute;s de una vista
	 */
	public void buscarVistaBien () {
		
		slf4jLogger.info("buscarVistaBien");
		
		try {
			
			this.bajasBienDataManager.getVistaBienBuscar().setEmrPk(this.bajasBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.bajasBienDataManager.getVistaBienBuscar().setTraEstado(this.bajasBienDataManager.getEstadoActivo());
			this.bajasBienDataManager.getVistaBienBuscar().setPerCi(this.buscarUsuarioComponent.getVistaEmpleadoSeleccionado().getPerCi());
			this.bajasBienDataManager.getVistaBienBuscar().setBieEstado(this.bajasBienDataManager.getEstadoActivo());
			
			if (StringUtils.isNotBlank(this.bajasBienDataManager.getIdDcTipoBienSelec())) {
				this.bajasBienDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(this.bajasBienDataManager.getIdDcTipoBienSelec());
			}
			
			this.bajasBienDataManager.getVistaBienBuscar().setCatBienPk(this.bajasBienDataManager.getIdCategoriaBienSeleccionado());
			this.bajasBienDataManager.getVistaBienBuscar().setLinBienPk(this.bajasBienDataManager.getIdLineaBienSeleccionado());
			
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.bajasBienDataManager.getVistaBienBuscar());
			
			this.bajasBienDataManager.getListVistaBien().clear();
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.bajasBienDataManager.setListVistaBien(listVistaBien);
			} else {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	
	
	public void darBajaBien () {
		slf4jLogger.info("darBajaBien");
		
		try {
			this.bajasBienDataManager.getVistaBienEditar().setDetCatalogoTipoBaja(this.bajasBienDataManager.getIdDcBajaBienSelec());
			VistaBien vistaBien = servicioInventario.darBajaBien(this.bajasBienDataManager.getVistaBienEditar());
			
			if (vistaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("Baja realizada correctamente");
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al darBajaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void obtenerTrazabilidadBien () {
		slf4jLogger.info("obtenerTrazabilidadBien");
		
		try {
			VistaTransaccion vistaTransaccion = new VistaTransaccion();
			vistaTransaccion.setBieFk(this.bajasBienDataManager.getVistaBienEditar().getBiePk());
			this.bajasBienDataManager.setListVistaTransaccion(servicioInventario.obtenerVistaTransaccionCriterios(vistaTransaccion));
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al obtenerTrazabilidadBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void buscarEmpleado () {
		slf4jLogger.info("buscarEmpleado");
		
		try {
			this.bajasBienDataManager.getListVistaEmpleado().clear();
			this.bajasBienDataManager.getVistaEmpleadoBuscar().setEmrFk(this.bajasBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			List<VistaEmpleado> listVistaEmpleado = servicioInventario.obtenerEmpleadoEmpresa(this.bajasBienDataManager.getVistaEmpleadoBuscar());
			if (CollectionUtils.isEmpty(listVistaEmpleado)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.bajasBienDataManager.setListVistaEmpleado(listVistaEmpleado);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarEmpleado {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void limpiarFiltrosBusqueda () {
		this.bajasBienDataManager.setVistaBienBuscar(new VistaBien());
		this.bajasBienDataManager.setIdDcTipoBienSelec(null);
		this.bajasBienDataManager.setIdCategoriaBienSeleccionado(null);
		this.bajasBienDataManager.setIdLineaBienSeleccionado(null);
		this.bajasBienDataManager.getDcLineaBien().clear();
		this.buscarUsuarioComponent.setVistaEmpleadoSeleccionado(new VistaEmpleado());
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
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}


	
}
