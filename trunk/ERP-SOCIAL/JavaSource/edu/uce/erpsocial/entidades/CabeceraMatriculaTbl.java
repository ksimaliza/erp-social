package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the cabecera_matricula_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_matricula_tbl")
public class CabeceraMatriculaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cab_matricula_pk")
	private String cabMatriculaPk;

	@Column(name="cab_matricula_archivo")
	private String cabMatriculaArchivo;

	@Column(name="cab_matricula_campo")
	private String cabMatriculaCampo;

	@Column(name="cab_matricula_descripcion")
	private String cabMatriculaDescripcion;

	@Column(name="cab_matricula_niveles")
	private Integer cabMatriculaNiveles;

	//bi-directional many-to-one association to DetalleMatriculaTbl
	@OneToMany(mappedBy="cabeceraMatriculaTbl")
	private Set<DetalleMatriculaTbl> detalleMatriculaTbls;

    public CabeceraMatriculaTbl() {
    }

	public String getCabMatriculaPk() {
		return this.cabMatriculaPk;
	}

	public void setCabMatriculaPk(String cabMatriculaPk) {
		this.cabMatriculaPk = cabMatriculaPk;
	}

	public String getCabMatriculaArchivo() {
		return this.cabMatriculaArchivo;
	}

	public void setCabMatriculaArchivo(String cabMatriculaArchivo) {
		this.cabMatriculaArchivo = cabMatriculaArchivo;
	}

	public String getCabMatriculaCampo() {
		return this.cabMatriculaCampo;
	}

	public void setCabMatriculaCampo(String cabMatriculaCampo) {
		this.cabMatriculaCampo = cabMatriculaCampo;
	}

	public String getCabMatriculaDescripcion() {
		return this.cabMatriculaDescripcion;
	}

	public void setCabMatriculaDescripcion(String cabMatriculaDescripcion) {
		this.cabMatriculaDescripcion = cabMatriculaDescripcion;
	}

	public Integer getCabMatriculaNiveles() {
		return this.cabMatriculaNiveles;
	}

	public void setCabMatriculaNiveles(Integer cabMatriculaNiveles) {
		this.cabMatriculaNiveles = cabMatriculaNiveles;
	}

	public Set<DetalleMatriculaTbl> getDetalleMatriculaTbls() {
		return this.detalleMatriculaTbls;
	}

	public void setDetalleMatriculaTbls(Set<DetalleMatriculaTbl> detalleMatriculaTbls) {
		this.detalleMatriculaTbls = detalleMatriculaTbls;
	}
	
}