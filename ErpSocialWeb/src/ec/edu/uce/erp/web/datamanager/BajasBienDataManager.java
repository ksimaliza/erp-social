/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_BIEN;
import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CAT_BAJA_BIEN;

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
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;

/**
 * @author
 *
 */
@SessionScoped
@ManagedBean (name="bajasBienDataManager") 
public class BajasBienDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BajasBienDataManager.class);
	
	@EJB
	public ServicioInventario servicioInventario;
	
	private VistaBien vistaBienBuscar;
	private VistaBien vistaBienEditar;
	private List<VistaBien> listVistaBien;
	private VistaEmpleado vistaEmpleadoBuscar;
	private VistaEmpleado vistaEmpleadoSeleccionado;
	private List<VistaEmpleado> listVistaEmpleado;
	private List<VistaBien> listVistaBienEditar;
	
	private String idDcTipoBienSelec;
	private String idDcBajaBienSelec;
	private Integer idCategoriaBienSeleccionado;
	private Integer idLineaBienSeleccionado;
	private String idCIEmpleadoSeleccionado;
	
	private List<VistaTransaccion> listVistaTransaccion;
	
	private List<SelectItem> dcTipoBien;
	private List<SelectItem> dcCategoriaBien;
	private List<SelectItem> dcLineaBien;
	private List<SelectItem> dcEmpleadosEmpresa;
	private List<SelectItem> dcBajaBien;
	
	public BajasBienDataManager () {
		this.vistaBienBuscar = new VistaBien();
		this.vistaBienEditar = new VistaBien();
		this.listVistaBien = new ArrayList<VistaBien>();
		this.listVistaBienEditar = new ArrayList<VistaBien>();
		this.listVistaTransaccion = new ArrayList<VistaTransaccion>();
		this.vistaEmpleadoBuscar = new VistaEmpleado();
		this.dcLineaBien = new ArrayList<SelectItem>();
		this.listVistaEmpleado = new ArrayList<VistaEmpleado>();
	}
	
	public void limpiarCatalogos () {
		idDcTipoBienSelec = null;
		idCategoriaBienSeleccionado = 0;
		idLineaBienSeleccionado = 0;
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
	 * @return the dcTipoBien
	 * @throws SeguridadesException 
	 */
	public List<SelectItem> getDcBajaBien() throws SeguridadesException {
		
		if (CollectionUtils.isEmpty(dcBajaBien)) {
			slf4jLogger.info("cargar catalogoTipoBien");
			dcBajaBien = UtilSelectItems.getInstancia().cargarSelectItemsDetBien(ID_CAB_CAT_BAJA_BIEN, servicioInventario);
		}
		
		return dcBajaBien;
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
	 * @return the dcMarcaBien
	 */
	public List<SelectItem> getDcEmpleadosEmpresa() {
		
		try {
			if (CollectionUtils.isEmpty(dcEmpleadosEmpresa)) {
				slf4jLogger.info("cargar dcEmpleadosEmpresa");
				dcEmpleadosEmpresa = UtilSelectItems.getInstancia().cargarSelectItemEmpleados(servicioInventario, getUsuarioSession().getEmpresaTbl().getEmrPk());
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al cargar getDcEmpleadosEmpresa {}", e.getCause().getMessage());
			e.printStackTrace();
		}
		
		return dcEmpleadosEmpresa;
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
	 * @param dcLineaBien the dcLineaBien to set
	 */
	public void setDcLineaBien(List<SelectItem> dcLineaBien) {
		this.dcLineaBien = dcLineaBien;
	}

	/**
	 * @return the vistaEmpleadoBuscar
	 */
	public VistaEmpleado getVistaEmpleadoBuscar() {
		return vistaEmpleadoBuscar;
	}

	/**
	 * @param vistaEmpleadoBuscar the vistaEmpleadoBuscar to set
	 */
	public void setVistaEmpleadoBuscar(VistaEmpleado vistaEmpleadoBuscar) {
		this.vistaEmpleadoBuscar = vistaEmpleadoBuscar;
	}

	/**
	 * @return the listVistaEmpleado
	 */
	public List<VistaEmpleado> getListVistaEmpleado() {
		return listVistaEmpleado;
	}

	/**
	 * @param listVistaEmpleado the listVistaEmpleado to set
	 */
	public void setListVistaEmpleado(List<VistaEmpleado> listVistaEmpleado) {
		this.listVistaEmpleado = listVistaEmpleado;
	}

	/**
	 * @return the vistaEmpleadoSeleccionado
	 */
	public VistaEmpleado getVistaEmpleadoSeleccionado() {
		return vistaEmpleadoSeleccionado;
	}

	/**
	 * @param vistaEmpleadoSeleccionado the vistaEmpleadoSeleccionado to set
	 */
	public void setVistaEmpleadoSeleccionado(VistaEmpleado vistaEmpleadoSeleccionado) {
		this.vistaEmpleadoSeleccionado = vistaEmpleadoSeleccionado;
	}

	/**
	 * @param idCIEmpleadoSeleccionado the idCIEmpleadoSeleccionado to set
	 */
	public void setIdCIEmpleadoSeleccionado(String idCIEmpleadoSeleccionado) {
		this.idCIEmpleadoSeleccionado = idCIEmpleadoSeleccionado;
	}

	/**
	 * @return the idCIEmpleadoSeleccionado
	 */
	public String getIdCIEmpleadoSeleccionado() {
		return idCIEmpleadoSeleccionado;
	}

	/**
	 * @return the listVistaBienEditar
	 */
	public List<VistaBien> getListVistaBienEditar() {
		return listVistaBienEditar;
	}

	/**
	 * @param listVistaBienEditar the listVistaBienEditar to set
	 */
	public void setListVistaBienEditar(List<VistaBien> listVistaBienEditar) {
		this.listVistaBienEditar = listVistaBienEditar;
	}

	/**
	 * @return the idDcBajaBienSelec
	 */
	public String getIdDcBajaBienSelec() {
		return idDcBajaBienSelec;
	}

	/**
	 * @param idDcBajaBienSelec the idDcBajaBienSelec to set
	 */
	public void setIdDcBajaBienSelec(String idDcBajaBienSelec) {
		this.idDcBajaBienSelec = idDcBajaBienSelec;
	}

}

