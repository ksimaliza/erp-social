package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the cabecera_bien_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_bien_tbl")
public class CabeceraBienTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cab_bien_pk")
	private String cabBienPk;

	@Column(name="cab_bien_archivo")
	private String cabBienArchivo;

	@Column(name="cab_bien_campo")
	private String cabBienCampo;

	@Column(name="cab_bien_descripcion")
	private String cabBienDescripcion;

	@Column(name="cab_bien_niveles")
	private Integer cabBienNiveles;

	//bi-directional many-to-one association to DetalleBienTbl
	@OneToMany(mappedBy="cabeceraBienTbl")
	private Set<DetalleBienTbl> detalleBienTbls;

    public CabeceraBienTbl() {
    }

	public String getCabBienPk() {
		return this.cabBienPk;
	}

	public void setCabBienPk(String cabBienPk) {
		this.cabBienPk = cabBienPk;
	}

	public String getCabBienArchivo() {
		return this.cabBienArchivo;
	}

	public void setCabBienArchivo(String cabBienArchivo) {
		this.cabBienArchivo = cabBienArchivo;
	}

	public String getCabBienCampo() {
		return this.cabBienCampo;
	}

	public void setCabBienCampo(String cabBienCampo) {
		this.cabBienCampo = cabBienCampo;
	}

	public String getCabBienDescripcion() {
		return this.cabBienDescripcion;
	}

	public void setCabBienDescripcion(String cabBienDescripcion) {
		this.cabBienDescripcion = cabBienDescripcion;
	}

	public Integer getCabBienNiveles() {
		return this.cabBienNiveles;
	}

	public void setCabBienNiveles(Integer cabBienNiveles) {
		this.cabBienNiveles = cabBienNiveles;
	}

	public Set<DetalleBienTbl> getDetalleBienTbls() {
		return this.detalleBienTbls;
	}

	public void setDetalleBienTbls(Set<DetalleBienTbl> detalleBienTbls) {
		this.detalleBienTbls = detalleBienTbls;
	}
	
}