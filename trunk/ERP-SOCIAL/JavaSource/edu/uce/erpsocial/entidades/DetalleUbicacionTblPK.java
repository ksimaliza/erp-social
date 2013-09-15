package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detalle_ubicacion_tbl database table.
 * 
 */
@Embeddable
public class DetalleUbicacionTblPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cab_ubicacion_fk")
	private String cabUbicacionFk;

	@Column(name="det_ubicacion_nivel1")
	private String detUbicacionNivel1;

	@Column(name="det_ubicacion_nivel2")
	private String detUbicacionNivel2;

	@Column(name="det_ubicacion_nivel3")
	private String detUbicacionNivel3;

	@Column(name="det_ubicacion_nivel4")
	private String detUbicacionNivel4;

    public DetalleUbicacionTblPK() {
    }
	public String getCabUbicacionFk() {
		return this.cabUbicacionFk;
	}
	public void setCabUbicacionFk(String cabUbicacionFk) {
		this.cabUbicacionFk = cabUbicacionFk;
	}
	public String getDetUbicacionNivel1() {
		return this.detUbicacionNivel1;
	}
	public void setDetUbicacionNivel1(String detUbicacionNivel1) {
		this.detUbicacionNivel1 = detUbicacionNivel1;
	}
	public String getDetUbicacionNivel2() {
		return this.detUbicacionNivel2;
	}
	public void setDetUbicacionNivel2(String detUbicacionNivel2) {
		this.detUbicacionNivel2 = detUbicacionNivel2;
	}
	public String getDetUbicacionNivel3() {
		return this.detUbicacionNivel3;
	}
	public void setDetUbicacionNivel3(String detUbicacionNivel3) {
		this.detUbicacionNivel3 = detUbicacionNivel3;
	}
	public String getDetUbicacionNivel4() {
		return this.detUbicacionNivel4;
	}
	public void setDetUbicacionNivel4(String detUbicacionNivel4) {
		this.detUbicacionNivel4 = detUbicacionNivel4;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleUbicacionTblPK)) {
			return false;
		}
		DetalleUbicacionTblPK castOther = (DetalleUbicacionTblPK)other;
		return 
			this.cabUbicacionFk.equals(castOther.cabUbicacionFk)
			&& this.detUbicacionNivel1.equals(castOther.detUbicacionNivel1)
			&& this.detUbicacionNivel2.equals(castOther.detUbicacionNivel2)
			&& this.detUbicacionNivel3.equals(castOther.detUbicacionNivel3)
			&& this.detUbicacionNivel4.equals(castOther.detUbicacionNivel4);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cabUbicacionFk.hashCode();
		hash = hash * prime + this.detUbicacionNivel1.hashCode();
		hash = hash * prime + this.detUbicacionNivel2.hashCode();
		hash = hash * prime + this.detUbicacionNivel3.hashCode();
		hash = hash * prime + this.detUbicacionNivel4.hashCode();
		
		return hash;
    }
}