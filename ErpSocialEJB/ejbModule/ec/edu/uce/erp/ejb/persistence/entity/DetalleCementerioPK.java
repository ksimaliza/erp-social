package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the detalle_cementerio_tbl database table.
 * 
 */
@Embeddable
public class DetalleCementerioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cab_cementerio_fk", insertable=false, updatable=false)
	private String cabCementerioFk;

	@Column(name="det_cementerio_nivel1")
	private String detCementerioNivel1;

	public DetalleCementerioPK() {
	}
	public String getCabCementerioFk() {
		return this.cabCementerioFk;
	}
	public void setCabCementerioFk(String cabCementerioFk) {
		this.cabCementerioFk = cabCementerioFk;
	}
	public String getDetCementerioNivel1() {
		return this.detCementerioNivel1;
	}
	public void setDetCementerioNivel1(String detCementerioNivel1) {
		this.detCementerioNivel1 = detCementerioNivel1;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleCementerioPK)) {
			return false;
		}
		DetalleCementerioPK castOther = (DetalleCementerioPK)other;
		return 
			this.cabCementerioFk.equals(castOther.cabCementerioFk)
			&& this.detCementerioNivel1.equals(castOther.detCementerioNivel1);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cabCementerioFk.hashCode();
		hash = hash * prime + this.detCementerioNivel1.hashCode();
		
		return hash;
	}
}