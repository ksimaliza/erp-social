/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Transaccion;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;

/**
 * @author
 *
 */
public class BienVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Bien bien;
	private Transaccion transaccion;
	
	private VistaBien vistaBien;

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
	 * @return the transaccion
	 */
	public Transaccion getTransaccion() {
		return transaccion;
	}

	/**
	 * @param transaccion the transaccion to set
	 */
	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}
	
}
