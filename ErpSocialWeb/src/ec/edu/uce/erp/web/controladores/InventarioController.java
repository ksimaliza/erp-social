/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;
import ec.edu.uce.erp.web.datamanager.InventarioDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="inventarioController")
public class InventarioController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(InventarioController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{inventarioDataManager}")
	private InventarioDataManager inventarioDataManager;
	
	//catalogos
	private List<SelectItem> dcCategoriaBien;
	private List<SelectItem> dcMarcaBien;
	private List<SelectItem> dcLineaBien;
	
	/**
	 * @param inventarioDataManager the inventarioDataManager to set
	 */
	public void setInventarioDataManager(InventarioDataManager inventarioDataManager) {
		this.inventarioDataManager = inventarioDataManager;
	}
	
	public InventarioController () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		try {
			this.dcLineaBien = new ArrayList<SelectItem>();
			this.dcCategoriaBien = UtilSelectItems.getInstancia().cargarSelectItemCategoriaBien(servicioInventario);
			this.dcMarcaBien = UtilSelectItems.getInstancia().cargarSelectItemMarcaBien(servicioInventario);
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargar la pantalla Bienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	
	public void buscarBienes () {
		
		slf4jLogger.info("buscarBienes");
		
		try {
			
			this.inventarioDataManager.getVistaBienBuscar().setEmrPk(this.inventarioDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.inventarioDataManager.getVistaBienBuscar().setTraEstado(this.inventarioDataManager.getEstadoActivo());
			
			if (StringUtils.isBlank(inventarioDataManager.getIdDcTipoBienSelec())) {
				this.inventarioDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(null);
			} else {
				this.inventarioDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(inventarioDataManager.getIdDcTipoBienSelec());
			}
			
			if (this.inventarioDataManager.getIdCategoriaBienSeleccionado() == null || this.inventarioDataManager.getIdCategoriaBienSeleccionado()<=0) {
				this.inventarioDataManager.getVistaBienBuscar().setCatBienPk(null);
			} else {
				this.inventarioDataManager.getVistaBienBuscar().setCatBienPk(this.inventarioDataManager.getIdCategoriaBienSeleccionado());
				
				if (this.inventarioDataManager.getIdLineaBienSeleccionado() == null || this.inventarioDataManager.getIdLineaBienSeleccionado()<=0) {
					this.inventarioDataManager.getVistaBienBuscar().setLinBienPk(null);
				} else {
					this.inventarioDataManager.getVistaBienBuscar().setLinBienPk(this.inventarioDataManager.getIdLineaBienSeleccionado());
				}
				
			}
			
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.inventarioDataManager.getVistaBienBuscar());
			
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.inventarioDataManager.setListVistaBien(listVistaBien);
			} else {
				this.inventarioDataManager.getListVistaBien().clear();
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void limpiarFiltrosBusqueda () {
		this.inventarioDataManager.setVistaBienBuscar(new VistaBien());
		this.resetControllerCatalogoValues();
		this.dcLineaBien.clear();
	}
	
	public void asignarDatosBienDesdeVista(VistaBien vistaBien) {
		
		slf4jLogger.info("asignarDatosBienDesdeVista");
		try {
			
			Bien bienBuscar = new Bien();
			bienBuscar.setBiePk(vistaBien.getBiePk());
			bienBuscar.setEmrPk(vistaBien.getEmrPk());
			this.inventarioDataManager.setVistaBienEditar(vistaBien);
			List<Bien> listBien = servicioInventario.buscarBienCriterios(bienBuscar);
			
			if (CollectionUtils.isNotEmpty(listBien) && listBien.size()==1) {
				
				Bien bienEditar = listBien.iterator().next();
				this.inventarioDataManager.setIdDcTipoIngresoBienSelect(bienEditar.getDetCatalogoTipoIngresoBien());
				this.inventarioDataManager.setIdCategoriaBienSeleccionado(bienEditar.getCatBienPk());
				this.inventarioDataManager.setIdLineaBienSeleccionado(bienEditar.getLinBienPk());
				this.inventarioDataManager.setIdDcEstadoConservacionSelec(vistaBien.getDetBienEstConservNivel1Fk());
				this.cargarDcLineaBien(this.inventarioDataManager.getIdCategoriaBienSeleccionado());
				this.inventarioDataManager.setBienEditar(bienEditar);
			} 
			
		} catch (SeguridadesException e) {
			
			slf4jLogger.info("error al asignarDatosBienDesdeVista {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void cargarDcLineaBien (Integer categoriaBienSeleccionado) {
		
		try {
			
			this.dcLineaBien.clear();
			if (categoriaBienSeleccionado!=null && categoriaBienSeleccionado>0) {
				slf4jLogger.info("cargarDcLineaBien");
				LineaBien lineaBien = new LineaBien();
				lineaBien.setCatBienPk(categoriaBienSeleccionado);
				lineaBien.setLinBienEstado(this.inventarioDataManager.getEstadoActivo());
				List<LineaBien> listLineaBien = servicioInventario.buscarLineaBienCriterios(lineaBien);
				if (CollectionUtils.isEmpty(listLineaBien)){
					MensajesWebController.aniadirMensajeInformacion("La categoria seleccionada no tiene lineas asignadas");
				} else {
					this.dcLineaBien.addAll(UtilSelectItems.getInstancia().cargarSelectItemsGenerico(listLineaBien, "linBienPk", "linBienNombre"));
				}
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargarDcCategoriaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError("No se pudo obtener las categorias de la base de datos");
		}
	
	}
	
	public void obtenerTrazabilidadBien () {
		
		slf4jLogger.info("obtenerTrazabilidadBien");
		
		try {
			VistaTransaccion vistaTransaccion = new VistaTransaccion();
			vistaTransaccion.setBieFk(this.inventarioDataManager.getVistaBienEditar().getBiePk());
			this.inventarioDataManager.setListVistaTransaccion(servicioInventario.obtenerVistaTransaccionCriterios(vistaTransaccion));
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al obtenerTrazabilidadBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	/**
	 * Poner en null los valores de los catalogos que administra el controlador
	 */
	public void resetControllerCatalogoValues () {
		this.inventarioDataManager.setIdDcTipoBienSelec(null);
		this.inventarioDataManager.setIdDcTipoIngresoBienSelect(null);
		this.inventarioDataManager.setIdDcEstadoConservacionSelec(null);
		this.inventarioDataManager.setIdCategoriaBienSeleccionado(null);
		this.inventarioDataManager.setIdLineaBienSeleccionado(null);
	}
	
	/**
	 * @return the dcCategoriaBien
	 */
	public List<SelectItem> getDcCategoriaBien() {
		return dcCategoriaBien;
	}

	/**
	 * @param dcCategoriaBien the dcCategoriaBien to set
	 */
	public void setDcCategoriaBien(List<SelectItem> dcCategoriaBien) {
		this.dcCategoriaBien = dcCategoriaBien;
	}
	
	/**
	 * @return the dcMarcaBien
	 */
	public List<SelectItem> getDcMarcaBien() {
		return dcMarcaBien;
	}

	/**
	 * @param dcMarcaBien the dcMarcaBien to set
	 */
	public void setDcMarcaBien(List<SelectItem> dcMarcaBien) {
		this.dcMarcaBien = dcMarcaBien;
	}
	
	/**
	 * @return the dcLineaBien
	 */
	public List<SelectItem> getDcLineaBien() {
		return dcLineaBien;
	}

	/**
	 * @param dcLineaBien the dcLineaBien to set
	 */
	public void setDcLineaBien(List<SelectItem> dcLineaBien) {
		this.dcLineaBien = dcLineaBien;
	}

	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
