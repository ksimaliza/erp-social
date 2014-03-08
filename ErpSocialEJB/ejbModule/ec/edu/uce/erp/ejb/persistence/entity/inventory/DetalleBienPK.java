package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the detalle_bien_tbl database table.
 * 
 */
@Embeddable
public class DetalleBienPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cab_bien_fk", insertable=false, updatable=false)
	private String cabBienFk;

	@Column(name="det_bien_nivel1")
	private String detBienNivel1;

	public DetalleBienPK() {
	}
	public String getCabBienFk() {
		return this.cabBienFk;
	}
	public void setCabBienFk(String cabBienFk) {
		this.cabBienFk = cabBienFk;
	}
	public String getDetBienNivel1() {
		return this.detBienNivel1;
	}
	public void setDetBienNivel1(String detBienNivel1) {
		this.detBienNivel1 = detBienNivel1;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleBienPK)) {
			return false;
		}
		DetalleBienPK castOther = (DetalleBienPK)other;
		return 
			this.cabBienFk.equals(castOther.cabBienFk)
			&& this.detBienNivel1.equals(castOther.detBienNivel1);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cabBienFk.hashCode();
		hash = hash * prime + this.detBienNivel1.hashCode();
		
		return hash;
	}
}