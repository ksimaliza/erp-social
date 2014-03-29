/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;
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
	
	private List<SelectItem> dcLineaBien;
	private List<SelectItem> dcCategoriaBien;
	
	private Integer idLineaBienSeleccionado;
	private Integer idCategoriaBienSeleccionado;

	/**
	 * @param bienDataManager the bienDataManager to set
	 */
	public void setBienDataManager(BienDataManager bienDataManager) {
		this.bienDataManager = bienDataManager;
	}
	
	public BienController () {
		this.dcCategoriaBien = new ArrayList<SelectItem>();
	}
	
	public void registrarBien () {
		slf4jLogger.info("registrarBien");
	}
	
	public void editarBien () {
		slf4jLogger.info("editarBien");
	}
	
	public void buscarBienes () {
		slf4jLogger.info("registrarBien");
	}
	
	public void cargarDcCategoriaBien () {
		
		try {
			
			if (idLineaBienSeleccionado!=null && idLineaBienSeleccionado!=0) {
				
				slf4jLogger.info("cargarDcCategoriaBien");
				
				this.dcCategoriaBien.clear();
				
				CategoriaBien categoriaBien = new CategoriaBien();
				categoriaBien.setLinBienPk(idLineaBienSeleccionado);
				categoriaBien.setCatBienEstado(this.bienDataManager.getEstadoActivo());
				List<CategoriaBien> listCategoriaBien = servicioInventario.buscarCategoriaBienCriterios(categoriaBien);
				if (CollectionUtils.isEmpty(listCategoriaBien)){
					MensajesWebController.aniadirMensajeInformacion("La linea seleccionada no tiene categorias asignadas");
				} else {
					this.dcCategoriaBien.addAll(UtilSelectItems.getInstancia().cargarSelectItemsGenerico(listCategoriaBien, "catBienPk", "catBienNombre"));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargarDcCategoriaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError("No se pudo obtener las categorias de la base de datos");
		}
		
	}
	
	/**
	 * @return the dcLineaBien
	 */
	public List<SelectItem> getDcLineaBien() throws SeguridadesException {
		
		if (dcLineaBien == null){
			slf4jLogger.info("cargar catalogoLineaBien");
			dcLineaBien = UtilSelectItems.getInstancia().cargarSelectItemLineaBien(servicioInventario);
		}
		
		return dcLineaBien;
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

}
