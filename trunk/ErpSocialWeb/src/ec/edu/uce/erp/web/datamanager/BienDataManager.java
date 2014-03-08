/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean(name="bienDataManager")
public class BienDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	
	private Bien bienInstancia;
	private Bien bienEditar;
	private Bien bienBuscar;
	
	private List<Bien> listBien;
	
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
	
}
