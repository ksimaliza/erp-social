/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_ESTADO_CONSERVACION;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean(name="bienDataManager")
public class BienDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BienDataManager.class);
	
	@EJB
	public ServicioInventario servicioInventario;
	
	private Bien bienInstancia;
	private Bien bienEditar;
//	private Bien bienBuscar;
	private List<Bien> listBien;
	
	private VistaBien vistaBienBuscar;
	private VistaBien vistaBienEditar;
	private List<VistaBien> listVistaBien;
	
//	private List<SelectItem> dcTipoBien;
//	private List<SelectItem> dcEstadoBien;
	private List<SelectItem> dcEstadoConservacion;
	private List<SelectItem> dcCategoriaBien;
	private List<SelectItem> dcLineaBien;
	private List<SelectItem> dcMarcaBien;
	
	private Integer idCategoriaBienSeleccionado;
	private Integer idLineaBienSeleccionado;
	
	public BienDataManager () {
		super();
		this.bienInstancia = new Bien();
		this.bienInstancia.setUsuarioRegistro(getUsuarioSession());
		this.bienEditar = new Bien();
//		this.bienBuscar = new Bien();
		this.listBien = new ArrayList<Bien>();
		
		this.vistaBienBuscar = new VistaBien();
		this.listVistaBien = new ArrayList<VistaBien>();
		
		this.dcLineaBien = new ArrayList<SelectItem>();
		
	}
	
	public void cargarDcLineaBien () {
	
		try {
			
			if (idCategoriaBienSeleccionado!=null && idCategoriaBienSeleccionado>0) {
				
				slf4jLogger.info("cargarDcLineaBien");
				
				this.dcLineaBien.clear();
				
				LineaBien lineaBien = new LineaBien();
				lineaBien.setCatBienPk(idCategoriaBienSeleccionado);
				lineaBien.setLinBienEstado(getEstadoActivo());
				List<LineaBien> listLineaBien = servicioInventario.buscarLineaBienCriterios(lineaBien);
				if (CollectionUtils.isEmpty(listLineaBien)){
	//				MensajesWebController.aniadirMensajeInformacion("La linea seleccionada no tiene categorias asignadas");
				} else {
					this.dcLineaBien.addAll(UtilSelectItems.getInstancia().cargarSelectItemsGenerico(listLineaBien, "linBienPk", "linBienNombre"));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargarDcCategoriaBien {}", e.getCause().getMessage());
	//		MensajesWebController.aniadirMensajeError("No se pudo obtener las categorias de la base de datos");
		}
	
	}
	
	/**
	 * @return the bienInstancia
	 */
	public Bien getBienInstancia() {
		return bienInstancia;
	}

	/**
	 * @param bienInstancia the bienInstancia to set
	 */
	public void setBienInstancia(Bien bienInstancia) {
		this.bienInstancia = bienInstancia;
	}

	/**
	 * @return the bienEditar
	 */
	public Bien getBienEditar() {
		return bienEditar;
	}

	/**
	 * @param bienEditar the bienEditar to set
	 */
	public void setBienEditar(Bien bienEditar) {
		this.bienEditar = bienEditar;
	}

//	/**
//	 * @return the bienBuscar
//	 */
//	public Bien getBienBuscar() {
//		return bienBuscar;
//	}
//
//	/**
//	 * @param bienBuscar the bienBuscar to set
//	 */
//	public void setBienBuscar(Bien bienBuscar) {
//		this.bienBuscar = bienBuscar;
//	}

	/**
	 * @return the listBien
	 */
	public List<Bien> getListBien() {
		return listBien;
	}

	/**
	 * @param listBien the listBien to set
	 */
	public void setListBien(List<Bien> listBien) {
		this.listBien = listBien;
	}

//	/**
//	 * @return the dcTipoBien
//	 * @throws SeguridadesException 
//	 */
//	public List<SelectItem> getDcTipoBien() throws SeguridadesException {
//		
//		if (CollectionUtils.isEmpty(dcTipoBien)) {
//			slf4jLogger.info("cargar catalogoTipoBien");
//			dcTipoBien = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_TIPO_BIEN, servicioInventario);
//		}
//		
//		return dcTipoBien;
//	}

//	/**
//	 * @return the dcEstadoBien
//	 */
//	public List<SelectItem> getDcEstadoBien() throws SeguridadesException {
//		
//		if (CollectionUtils.isEmpty(dcEstadoBien)) {
//			slf4jLogger.info("cargar getDcEstadoBien");
//			dcEstadoBien = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_ESTADO_BIEN, servicioInventario);
//		}
//		return dcEstadoBien;
//	}

	/**
	 * @return the dcEstadoConservacion
	 */
	public List<SelectItem> getDcEstadoConservacion() throws SeguridadesException {
		
		if (CollectionUtils.isEmpty(dcEstadoConservacion)) {
			slf4jLogger.info("cargar getDcEstadoConservacion");
			dcEstadoConservacion = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_ESTADO_CONSERVACION, servicioInventario);
		}
		return dcEstadoConservacion;
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

	/**
	 * @return the dcMarcaBien
	 */
	public List<SelectItem> getDcMarcaBien() {
		
		try {
			if (CollectionUtils.isEmpty(dcMarcaBien)) {
				slf4jLogger.info("cargar getDcMarcaBien");
				dcMarcaBien = UtilSelectItems.getInstancia().cargarSelectItemMarcaBien(servicioInventario);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargar getDcMarcaBien {}", e.getCause().getMessage());
			e.printStackTrace();
		}
		
		return dcMarcaBien;
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
	 * @return the dcLineaBien
	 */
	public List<SelectItem> getDcLineaBien() {
		return dcLineaBien;
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
	 * @return the listVistaBien
	 */
	public List<VistaBien> getListVistaBien() {
		return listVistaBien;
	}

	/**
	 * @param listVistaBien the listVistaBien to set
	 */
	public void setListVistaBien(List<VistaBien> listVistaBien) {
		this.listVistaBien = listVistaBien;
	}

	/**
	 * @return the vistaBienBuscar
	 */
	public VistaBien getVistaBienBuscar() {
		return vistaBienBuscar;
	}

	/**
	 * @param vistaBienBuscar the vistaBienBuscar to set
	 */
	public void setVistaBienBuscar(VistaBien vistaBienBuscar) {
		this.vistaBienBuscar = vistaBienBuscar;
	}

	/**
	 * @return the vistaBienEditar
	 */
	public VistaBien getVistaBienEditar() {
		return vistaBienEditar;
	}

	/**
	 * @param vistaBienEditar the vistaBienEditar to set
	 */
	public void setVistaBienEditar(VistaBien vistaBienEditar) {
		this.vistaBienEditar = vistaBienEditar;
	}

	
}
