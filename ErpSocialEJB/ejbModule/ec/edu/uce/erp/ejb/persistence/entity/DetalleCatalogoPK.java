package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the detalle_catalogo_tbl database table.
 * 
 */
@Embeddable
public class DetalleCatalogoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cab_catalogo_fk")
	private String cabCatalogoFk;

	@Column(name="det_catalogo_nivel1")
	private String detCatalogoNivel1;

	public DetalleCatalogoPK() {
	}
	public String getCabCatalogoFk() {
		return this.cabCatalogoFk;
	}
	public void setCabCatalogoFk(String cabCatalogoFk) {
		this.cabCatalogoFk = cabCatalogoFk;
	}
	public String getDetCatalogoNivel1() {
		return this.detCatalogoNivel1;
	}
	public void setDetCatalogoNivel1(String detCatalogoNivel1) {
		this.detCatalogoNivel1 = detCatalogoNivel1;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleCatalogoPK)) {
			return false;
		}
		DetalleCatalogoPK castOther = (DetalleCatalogoPK)other;
		return 
			this.cabCatalogoFk.equals(castOther.cabCatalogoFk)
			&& this.detCatalogoNivel1.equals(castOther.detCatalogoNivel1);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cabCatalogoFk.hashCode();
		hash = hash * prime + this.detCatalogoNivel1.hashCode();
		
		return hash;
	}
}