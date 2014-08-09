/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;
import java.util.List;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;

/**
 * @author 
 *
 */
public class ReporteInventarioVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Bien bien;
	private VistaBien vistaBien;
	private List<VistaBien> listVistaBien;
	private List<Bien> listBien;
	
	public ReporteInventarioVO () {}

	/**
	 * @return the vistaBien
	 */
	public VistaBien getVistaBien() {
		return vistaBien;
	}

	/**
	 * @param vistaBien the vistaBien to set
	 */
	public void setVistaBien(VistaBien vistaBien) {
		this.vistaBien = vistaBien;
	}

	/**
	 * @return the bien
	 */
	public Bien getBien() {
		return bien;
	}

	/**
	 * @param bien the bien to set
	 */
	public void setBien(Bien bien) {
		this.bien = bien;
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
