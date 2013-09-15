package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the cabecera_ubicacion_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_ubicacion_tbl")
public class CabeceraUbicacionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cab_ubicacion_pk")
	private String cabUbicacionPk;

	@Column(name="cab_ubicacion_archivo")
	private String cabUbicacionArchivo;

	@Column(name="cab_ubicacion_campo")
	private String cabUbicacionCampo;

	@Column(name="cab_ubicacion_descripcion")
	private String cabUbicacionDescripcion;

	@Column(name="cab_ubicacion_niveles")
	private Integer cabUbicacionNiveles;

	//bi-directional many-to-one association to DetalleUbicacionTbl
	@OneToMany(mappedBy="cabeceraUbicacionTbl")
	private Set<DetalleUbicacionTbl> detalleUbicacionTbls;

    public CabeceraUbicacionTbl() {
    }

	public String getCabUbicacionPk() {
		return this.cabUbicacionPk;
	}

	public void setCabUbicacionPk(String cabUbicacionPk) {
		this.cabUbicacionPk = cabUbicacionPk;
	}

	public String getCabUbicacionArchivo() {
		return this.cabUbicacionArchivo;
	}

	public void setCabUbicacionArchivo(String cabUbicacionArchivo) {
		this.cabUbicacionArchivo = cabUbicacionArchivo;
	}

	public String getCabUbicacionCampo() {
		return this.cabUbicacionCampo;
	}

	public void setCabUbicacionCampo(String cabUbicacionCampo) {
		this.cabUbicacionCampo = cabUbicacionCampo;
	}

	public String getCabUbicacionDescripcion() {
		return this.cabUbicacionDescripcion;
	}

	public void setCabUbicacionDescripcion(String cabUbicacionDescripcion) {
		this.cabUbicacionDescripcion = cabUbicacionDescripcion;
	}

	public Integer getCabUbicacionNiveles() {
		return this.cabUbicacionNiveles;
	}

	public void setCabUbicacionNiveles(Integer cabUbicacionNiveles) {
		this.cabUbicacionNiveles = cabUbicacionNiveles;
	}

	public Set<DetalleUbicacionTbl> getDetalleUbicacionTbls() {
		return this.detalleUbicacionTbls;
	}

	public void setDetalleUbicacionTbls(Set<DetalleUbicacionTbl> detalleUbicacionTbls) {
		this.detalleUbicacionTbls = detalleUbicacionTbls;
	}
	
}