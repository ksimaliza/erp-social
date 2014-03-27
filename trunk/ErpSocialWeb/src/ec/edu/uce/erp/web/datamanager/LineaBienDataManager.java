/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

/**
 * @author
 *
 */
@SessionScoped
@ManagedBean (name = "lineaBienDataManager")
public class LineaBienDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	
	private LineaBien lineaBienInstancia;
	private LineaBien lineaBienEditar;
	private LineaBien lineaBienBuscar;
	
	private List<LineaBien> listLineaBien;
	
	public LineaBienDataManager () {
		super();
		this.lineaBienInstancia = new LineaBien();
		this.lineaBienInstancia.setUsuarioRegistro(getUsuarioSession());
		this.lineaBienEditar = new LineaBien();
		this.lineaBienBuscar = new LineaBien();
		this.listLineaBien = new ArrayList<LineaBien>();
	}

	/**
	 * @return the lineaBienInstancia
	 */
	public LineaBien getLineaBienInstancia() {
		return lineaBienInstancia;
	}

	/**
	 * @param lineaBienInstancia the lineaBienInstancia to set
	 */
	public void setLineaBienInstancia(LineaBien lineaBienInstancia) {
		this.lineaBienInstancia = lineaBienInstancia;
	}

	/**
	 * @return the lineaBienEditar
	 */
	public LineaBien getLineaBienEditar() {
		return lineaBienEditar;
	}

	/**
	 * @param lineaBienEditar the lineaBienEditar to set
	 */
	public void setLineaBienEditar(LineaBien lineaBienEditar) {
		this.lineaBienEditar = lineaBienEditar;
	}

	/**
	 * @return the lineaBienBuscar
	 */
	public LineaBien getLineaBienBuscar() {
		return lineaBienBuscar;
	}

	/**
	 * @param lineaBienBuscar the lineaBienBuscar to set
	 */
	public void setLineaBienBuscar(LineaBien lineaBienBuscar) {
		this.lineaBienBuscar = lineaBienBuscar;
	}

	/**
	 * @return the listLineaBien
	 */
	public List<LineaBien> getListLineaBien() {
		return listLineaBien;
	}

	/**
	 * @param listLineaBien the listLineaBien to set
	 */
	public void setListLineaBien(List<LineaBien> listLineaBien) {
		this.listLineaBien = listLineaBien;
	}

}
