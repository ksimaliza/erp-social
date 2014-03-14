/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_ESTADO_BIEN;
import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_ESTADO_CONSERVACION;
import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_BIEN;

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
	private Bien bienBuscar;
	private List<Bien> listBien;
	
	private List<SelectItem> dcTipoBien;
	private List<SelectItem> dcEstadoBien;
	private List<SelectItem> dcEstadoConservacion;
	
	public BienDataManager () {
		super();
		this.bienInstancia = new Bien();
		this.bienInstancia.setUsuarioRegistro(getUsuarioSession());
		this.bienEditar = new Bien();
		this.bienBuscar = new Bien();
		this.listBien = new ArrayList<Bien>();
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
	 * @return the bienBuscar
	 */
	public Bien getBienBuscar() {
		return bienBuscar;
	}

	/**
	 * @param bienBuscar the bienBuscar to set
	 */
	public void setBienBuscar(Bien bienBuscar) {
		this.bienBuscar = bienBuscar;
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
	 * @return the dcEstadoBien
	 */
	public List<SelectItem> getDcEstadoBien() throws SeguridadesException {
		
		if (CollectionUtils.isEmpty(dcTipoBien)) {
			slf4jLogger.info("cargar catalogoTipoIngreso");
			dcEstadoBien = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_ESTADO_BIEN, servicioInventario);
		}
		return dcEstadoBien;
	}

	/**
	 * @return the dcEstadoConservacion
	 */
	public List<SelectItem> getDcEstadoConservacion() throws SeguridadesException {
		
		if (CollectionUtils.isEmpty(dcTipoBien)) {
			slf4jLogger.info("cargar catalogoTipoIngreso");
			dcEstadoConservacion = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_ESTADO_CONSERVACION, servicioInventario);
		}
		return dcEstadoConservacion;
	}
	
}
