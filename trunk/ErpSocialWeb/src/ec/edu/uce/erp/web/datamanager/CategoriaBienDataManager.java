/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBienPK;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean(name="categoriaBienDataManager")
public class CategoriaBienDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(CategoriaBienDataManager.class);
	
	private CategoriaBien categoriaBienInstancia;
	private CategoriaBien categoriaBienBuscar;
	private CategoriaBien categoriaBienEditar;
	private List<CategoriaBien> listCategoriaBien;
	
	@EJB
	public ServicioInventario servicioInventario;
	
	private List<SelectItem> dcLineaBien;
	
	public CategoriaBienDataManager () {
		super();
		this.categoriaBienInstancia = new CategoriaBien();
//		this.categoriaBienInstancia.setId(new CategoriaBienPK());
		this.categoriaBienInstancia.setUsuarioRegistro(getUsuarioSession());
		this.categoriaBienBuscar = new CategoriaBien();
//		this.categoriaBienBuscar.setId(new CategoriaBienPK());
		this.categoriaBienEditar = new CategoriaBien();
		this.listCategoriaBien = new ArrayList<CategoriaBien>();
	}

	/**
	 * @return the categoriaBienInstancia
	 */
	public CategoriaBien getCategoriaBienInstancia() {
		return categoriaBienInstancia;
	}

	/**
	 * @param categoriaBienInstancia the categoriaBienInstancia to set
	 */
	public void setCategoriaBienInstancia(CategoriaBien categoriaBienInstancia) {
		this.categoriaBienInstancia = categoriaBienInstancia;
	}

	/**
	 * @return the categoriaBienBuscar
	 */
	public CategoriaBien getCategoriaBienBuscar() {
		return categoriaBienBuscar;
	}

	/**
	 * @param categoriaBienBuscar the categoriaBienBuscar to set
	 */
	public void setCategoriaBienBuscar(CategoriaBien categoriaBienBuscar) {
		this.categoriaBienBuscar = categoriaBienBuscar;
	}

	/**
	 * @return the categoriaBienEditar
	 */
	public CategoriaBien getCategoriaBienEditar() {
		return categoriaBienEditar;
	}

	/**
	 * @param categoriaBienEditar the categoriaBienEditar to set
	 */
	public void setCategoriaBienEditar(CategoriaBien categoriaBienEditar) {
		this.categoriaBienEditar = categoriaBienEditar;
	}

	/**
	 * @return the listCategoriaBien
	 */
	public List<CategoriaBien> getListCategoriaBien() {
		return listCategoriaBien;
	}

	/**
	 * @param listCategoriaBien the listCategoriaBien to set
	 */
	public void setListCategoriaBien(List<CategoriaBien> listCategoriaBien) {
		this.listCategoriaBien = listCategoriaBien;
	}

	/**
	 * @return the dcCategoriaBien
	 * @throws SeguridadesException 
	 */
	public List<SelectItem> getDcLineaBien() throws SeguridadesException {
		
		if (dcLineaBien == null) {
			slf4jLogger.info("cargar catalogoCategoriaBien");
			dcLineaBien = UtilSelectItems.getInstancia().cargarSelectItemLineaBien(servicioInventario);
		}
		
		return dcLineaBien;
	}
	
}
