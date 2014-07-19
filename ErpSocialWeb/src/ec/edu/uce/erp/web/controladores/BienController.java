/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;

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
import org.primefaces.context.RequestContext;
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
import ec.edu.uce.erp.web.datamanager.BienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="bienController")
public class BienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{bienDataManager}")
	private BienDataManager bienDataManager;
	
	//catalogos
	private List<SelectItem> dcCategoriaBien;
	private List<SelectItem> dcMarcaBien;
	private List<SelectItem> dcLineaBien;
	
	/**
	 * @param bienDataManager the bienDataManager to set
	 */
	public void setBienDataManager(BienDataManager bienDataManager) {
		this.bienDataManager = bienDataManager;
	}
	
	public BienController () {}
	
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
	
	public void registrarBien () {
		
		slf4jLogger.info("registrarBien");
		
		try {
			
			this.bienDataManager.getBienInstancia().setEmrPk(this.bienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.bienDataManager.getBienInstancia().setCatBienPk(this.bienDataManager.getIdCategoriaBienSeleccionado());
			this.bienDataManager.getBienInstancia().setLinBienPk(this.bienDataManager.getIdLineaBienSeleccionado());
			this.bienDataManager.getBienInstancia().setNpIdDcEstadoConservacion(this.bienDataManager.getIdDcEstadoConservacionSelec());
			this.bienDataManager.getBienInstancia().setDetCatalogoTipoIngresoBien(this.bienDataManager.getIdDcTipoIngresoBienSelect());
			this.bienDataManager.getBienInstancia().setUsuarioRegistro(this.bienDataManager.getUsuarioSession());
			if (this.bienDataManager.getBienInstancia().getMarBienPk().intValue()==0) {
				this.bienDataManager.getBienInstancia().setMarBienPk(null);
			}
			
			Bien bien = servicioInventario.registrarBien(this.bienDataManager.getBienInstancia());
			
			if (bien != null) {
				
				VistaBien vistaBienBuscar = new VistaBien();
				vistaBienBuscar.setBiePk(bien.getBiePk());
				vistaBienBuscar.setBieEstado(ESTADO_ACTIVO);
				vistaBienBuscar.setEmrPk(bien.getEmrPk());
				
				List<VistaBien> listVistaBien = this.servicioInventario.buscarVistaBienCriterios(vistaBienBuscar);
				
				this.bienDataManager.getListVistaBien().add(listVistaBien.iterator().next());
				this.bienDataManager.setBienInstancia(new Bien());
				this.resetControllerCatalogoValues();
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.registro.exito");
			}
			
		} catch (SeguridadesException e) {
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", e);
			slf4jLogger.info("error al registrarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void editarBien () {
		slf4jLogger.info("editarBien");
		try {
			
			this.bienDataManager.getBienEditar().setEmrPk(this.bienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.bienDataManager.getBienEditar().setCatBienPk(this.bienDataManager.getIdCategoriaBienSeleccionado());
			this.bienDataManager.getBienEditar().setLinBienPk(this.bienDataManager.getIdLineaBienSeleccionado());
			this.bienDataManager.getBienEditar().setDetCatalogoTipoIngresoBien(this.bienDataManager.getIdDcTipoIngresoBienSelect());
			this.bienDataManager.getBienEditar().setNpIdDcEstadoConservacion(this.bienDataManager.getIdDcEstadoConservacionSelec());
			this.bienDataManager.getBienEditar().setUsuarioRegistro(this.bienDataManager.getUsuarioSession());
			
			VistaBien vistaBienUpdate = servicioInventario.actualizarBien(this.bienDataManager.getBienEditar());
			
			if (vistaBienUpdate != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
				int posicion = this.bienDataManager.getListVistaBien().indexOf(this.bienDataManager.getVistaBienEditar());
				this.bienDataManager.getListVistaBien().remove(this.bienDataManager.getVistaBienEditar());
				this.bienDataManager.getListVistaBien().add(posicion, vistaBienUpdate);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al editarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void buscarBienes () {
		
		slf4jLogger.info("buscarBienes");
		
		try {
			
			this.bienDataManager.getVistaBienBuscar().setEmrPk(this.bienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.bienDataManager.getVistaBienBuscar().setTraEstado(this.bienDataManager.getEstadoActivo());
			
			if (StringUtils.isBlank(bienDataManager.getIdDcTipoBienSelec())) {
				this.bienDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(null);
			} else {
				this.bienDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(bienDataManager.getIdDcTipoBienSelec());
			}
			
			if (this.bienDataManager.getIdCategoriaBienBuscarSeleccionado() == null || this.bienDataManager.getIdCategoriaBienBuscarSeleccionado()<=0) {
				this.bienDataManager.getVistaBienBuscar().setCatBienPk(null);
			} else {
				this.bienDataManager.getVistaBienBuscar().setCatBienPk(this.bienDataManager.getIdCategoriaBienBuscarSeleccionado());
				
				if (this.bienDataManager.getIdLineaBienSeleccionado() == null || this.bienDataManager.getIdLineaBienSeleccionado()<=0) {
					this.bienDataManager.getVistaBienBuscar().setLinBienPk(null);
				} else {
					this.bienDataManager.getVistaBienBuscar().setLinBienPk(this.bienDataManager.getIdLineaBienSeleccionado());
				}
				
			}
			
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.bienDataManager.getVistaBienBuscar());
			
			this.bienDataManager.getListVistaBien().clear();
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.bienDataManager.setListVistaBien(listVistaBien);
			} else {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void asignarDatosBienDesdeVista(VistaBien vistaBien) {
		
		slf4jLogger.info("asignarDatosBienDesdeVista");
		try {
			
			Bien bienBuscar = new Bien();
			bienBuscar.setBiePk(vistaBien.getBiePk());
			bienBuscar.setEmrPk(vistaBien.getEmrPk());
			this.bienDataManager.setVistaBienEditar(vistaBien);
			List<Bien> listBien = servicioInventario.buscarBienCriterios(bienBuscar);
			
			if (CollectionUtils.isNotEmpty(listBien) && listBien.size()==1) {
				
				Bien bienEditar = listBien.iterator().next();
				this.bienDataManager.setIdDcTipoIngresoBienSelect(bienEditar.getDetCatalogoTipoIngresoBien());
				this.bienDataManager.setIdCategoriaBienSeleccionado(bienEditar.getCatBienPk());
				this.bienDataManager.setIdLineaBienSeleccionado(bienEditar.getLinBienPk());
				this.bienDataManager.setIdDcEstadoConservacionSelec(vistaBien.getDetBienEstConservNivel1Fk());
				this.cargarDcLineaBien(this.bienDataManager.getIdCategoriaBienSeleccionado());
				this.bienDataManager.setBienEditar(bienEditar);
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
				lineaBien.setLinBienEstado(this.bienDataManager.getEstadoActivo());
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
			vistaTransaccion.setBieFk(this.bienDataManager.getVistaBienEditar().getBiePk());
			this.bienDataManager.setListVistaTransaccion(servicioInventario.obtenerVistaTransaccionCriterios(vistaTransaccion));
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al obtenerTrazabilidadBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	/**
	 * Poner en null los valores de los catalogos que administra el controlador
	 */
	public void resetControllerCatalogoValues () {
		this.bienDataManager.setIdDcTipoIngresoBienSelect(null);
		this.bienDataManager.setIdDcTipoBienSelec(null);
		this.bienDataManager.setIdDcEstadoConservacionSelec(null);
		this.bienDataManager.refrescarObjetos();
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

}
