package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the detalle_matricula_tbl database table.
 * 
 */
@Embeddable
public class DetalleMatriculaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cab_matricula_fk", insertable=false, updatable=false)
	private String cabMatriculaFk;

	@Column(name="det_matricula_nivel1")
	private String detMatriculaNivel1;

	public DetalleMatriculaPK() {
	}
	public String getCabMatriculaFk() {
		return this.cabMatriculaFk;
	}
	public void setCabMatriculaFk(String cabMatriculaFk) {
		this.cabMatriculaFk = cabMatriculaFk;
	}
	public String getDetMatriculaNivel1() {
		return this.detMatriculaNivel1;
	}
	public void setDetMatriculaNivel1(String detMatriculaNivel1) {
		this.detMatriculaNivel1 = detMatriculaNivel1;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleMatriculaPK)) {
			return false;
		}
		DetalleMatriculaPK castOther = (DetalleMatriculaPK)other;
		return 
			this.cabMatriculaFk.equals(castOther.cabMatriculaFk)
			&& this.detMatriculaNivel1.equals(castOther.detMatriculaNivel1);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cabMatriculaFk.hashCode();
		hash = hash * prime + this.detMatriculaNivel1.hashCode();
		
		return hash;
	}
}