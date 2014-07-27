/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_ESTADO_CONSERVACION;
import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_BIEN;
import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_INGRESO_BIEN;

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
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean(name="inventarioDataManager")
public class InventarioDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(InventarioDataManager.class);
	
	@EJB
	public ServicioInventario servicioInventario;
	
	private Bien bienInstancia;
	private Bien bienEditar;
	private List<Bien> listBien;
	
	private VistaBien vistaBienBuscar;
	private VistaBien vistaBienEditar;
	private List<VistaBien> listVistaBien;
	
	private List<VistaTransaccion> listVistaTransaccion;
	
	private List<SelectItem> dcTipoBien;
	private List<SelectItem> dcEstadoConservacion;
	private List<SelectItem> dcTipoIngresoBien;
	
	private Integer idCategoriaBienSeleccionado;
	private Integer idLineaBienSeleccionado;
	private String idDcEstadoConservacionSelec;
	private String idDcTipoBienSelec;
	private String idDcTipoIngresoBienSelect;
	
	public InventarioDataManager () {
		super();
		this.bienInstancia = new Bien();
		this.bienInstancia.setUsuarioRegistro(getUsuarioSession());
		this.bienEditar = new Bien();
		this.listBien = new ArrayList<Bien>();
		
		this.vistaBienBuscar = new VistaBien();
		this.listVistaBien = new ArrayList<VistaBien>();
		
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

	/**
	 * @return the dcTipoBien
	 * @throws SeguridadesException 
	 */
	public List<SelectItem> getDcTipoBien() throws SeguridadesException {
		
		if (CollectionUtils.isEmpty(dcTipoBien)) {
			slf4jLogger.info("cargar catalogoTipoBien");
			dcTipoBien = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_TIPO_BIEN, servicioInventario);
		}
		
		return dcTipoBien;
	}

	/**
	 * @return the dcEstadoConservacion
	 */
	public List<SelectItem> getDcEstadoConservacion() throws SeguridadesException {
		
		if (CollectionUtils.isEmpty(dcEstadoConservacion)) {
			slf4jLogger.info("cargar getDcEstadoConservacion");
			dcEstadoConservacion = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_ESTADO_CONSERVACION, servicioInventario);
		}
		return dcEstadoConservacion;
	} //
	
	/**
	 * @return the dcTipoIngresoBien
	 */
	public List<SelectItem> getDcTipoIngresoBien() throws SeguridadesException {
		if (CollectionUtils.isEmpty(dcTipoIngresoBien)) {
			slf4jLogger.info("cargar getDcTipoIngresoBien");
			dcTipoIngresoBien = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_TIPO_INGRESO_BIEN, servicioInventario);
		}
		return dcTipoIngresoBien;
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

	/**
	 * @return the idDcEstadoConservacionSelec
	 */
	public String getIdDcEstadoConservacionSelec() {
		return idDcEstadoConservacionSelec;
	}

	/**
	 * @param idDcEstadoConservacionSelec the idDcEstadoConservacionSelec to set
	 */
	public void setIdDcEstadoConservacionSelec(String idDcEstadoConservacionSelec) {
		this.idDcEstadoConservacionSelec = idDcEstadoConservacionSelec;
	}

	/**
	 * @return the idDcTipoBienSelec
	 */
	public String getIdDcTipoBienSelec() {
		return idDcTipoBienSelec;
	}

	/**
	 * @param idDcTipoBienSelec the idDcTipoBienSelec to set
	 */
	public void setIdDcTipoBienSelec(String idDcTipoBienSelec) {
		this.idDcTipoBienSelec = idDcTipoBienSelec;
	}

	/**
	 * @return the idDcTipoIngresoBienSelect
	 */
	public String getIdDcTipoIngresoBienSelect() {
		return idDcTipoIngresoBienSelect;
	}

	/**
	 * @param idDcTipoIngresoBienSelect the idDcTipoIngresoBienSelect to set
	 */
	public void setIdDcTipoIngresoBienSelect(String idDcTipoIngresoBienSelect) {
		this.idDcTipoIngresoBienSelect = idDcTipoIngresoBienSelect;
	}

	/**
	 * @return the listVistaTransaccion
	 */
	public List<VistaTransaccion> getListVistaTransaccion() {
		return listVistaTransaccion;
	}

	/**
	 * @param listVistaTransaccion the listVistaTransaccion to set
	 */
	public void setListVistaTransaccion(List<VistaTransaccion> listVistaTransaccion) {
		this.listVistaTransaccion = listVistaTransaccion;
	}
	
}
