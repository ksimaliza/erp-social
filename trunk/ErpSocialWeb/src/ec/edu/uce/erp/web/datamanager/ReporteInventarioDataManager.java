package ec.edu.uce.erp.web.datamanager;

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
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.vo.ReporteInventarioVO;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "reporteInventarioDataManager")
public class ReporteInventarioDataManager extends BaseDataManager {

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteInventarioDataManager.class);
	
	@EJB
	public ServicioInventario servicioInventario;
	
	private ReporteInventarioVO reporteInventarioVO;
	private VistaBien vistaBienBuscar;
	
	private Integer idCategoriaBienSeleccionado;
	private Integer idLineaBienSeleccionado;
	private String idDcTipoBienSelec;
	
	private List<SelectItem> dcTipoBien;
	private List<SelectItem> dcCategoriaBien;
	private List<SelectItem> dcLineaBien;
	
	public ReporteInventarioDataManager () {
		this.dcLineaBien = new ArrayList<SelectItem>();
		this.vistaBienBuscar = new VistaBien();
	}
	
	public void cargarDcLineaBien () {
		
		try {
			this.dcLineaBien.clear();
			
			if (idCategoriaBienSeleccionado!=null && idCategoriaBienSeleccionado>0) {
				
				slf4jLogger.info("cargarDcLineaBien");
				
				LineaBien lineaBien = new LineaBien();
				lineaBien.setCatBienPk(idCategoriaBienSeleccionado);
				lineaBien.setLinBienEstado(getEstadoActivo());
				List<LineaBien> listLineaBien = servicioInventario.buscarLineaBienCriterios(lineaBien);
				if (!CollectionUtils.isEmpty(listLineaBien)){
					this.dcLineaBien.addAll(UtilSelectItems.getInstancia().cargarSelectItemsGenerico(listLineaBien, "linBienPk", "linBienNombre"));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargarDcCategoriaBien {}", e.toString());
		}
		
	}
	
	public void cargarCatalogos () {
		
		slf4jLogger.info("cargarCatalogos");
		
		try {
			this.dcCategoriaBien = UtilSelectItems.getInstancia().cargarSelectItemCategoriaBien(servicioInventario);
			this.dcTipoBien = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CATALOGO_TIPO_BIEN, servicioInventario);
			this.dcLineaBien = new ArrayList<SelectItem>();
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargarCatalogos {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
		
	}
	
	public void limpiarFiltrosBusqueda () {
		this.idCategoriaBienSeleccionado = null;
		this.idLineaBienSeleccionado = null;
		this.idDcTipoBienSelec = null;
		this.dcLineaBien = new ArrayList<SelectItem>();
		this.vistaBienBuscar = new VistaBien();
	}
	
	/**
	 * @return the dcTipoBien
	 * @throws SeguridadesException 
	 */
	public List<SelectItem> getDcTipoBien() {
		return dcTipoBien;
	}
	
	/**
	 * @return the dcCategoriaBien
	 */
	public List<SelectItem> getDcCategoriaBien() {
		return dcCategoriaBien;
	}
	
	/**
	 * @return the reporteInventarioVO
	 */
	public ReporteInventarioVO getReporteInventarioVO() {
		return reporteInventarioVO;
	}

	/**
	 * @param reporteInventarioVO the reporteInventarioVO to set
	 */
	public void setReporteInventarioVO(ReporteInventarioVO reporteInventarioVO) {
		this.reporteInventarioVO = reporteInventarioVO;
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
	 * @param dcLineaBien the dcLineaBien to set
	 */
	public void setDcLineaBien(List<SelectItem> dcLineaBien) {
		this.dcLineaBien = dcLineaBien;
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

}
