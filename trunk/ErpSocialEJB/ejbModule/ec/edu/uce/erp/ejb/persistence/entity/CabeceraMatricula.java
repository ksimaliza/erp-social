package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the cabecera_matricula_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_matricula_tbl")
@NamedQuery(name="CabeceraMatricula.findAll", query="SELECT c FROM CabeceraMatricula c")
public class CabeceraMatricula implements Serializable {
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

	//bi-directional many-to-one association to DetalleMatricula
	@OneToMany(mappedBy="cabeceraMatriculaTbl")
	private List<DetalleMatricula> detalleMatriculaTbls;

	public CabeceraMatricula() {
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

	public List<DetalleMatricula> getDetalleMatriculaTbls() {
		return this.detalleMatriculaTbls;
	}

	public void setDetalleMatriculaTbls(List<DetalleMatricula> detalleMatriculaTbls) {
		this.detalleMatriculaTbls = detalleMatriculaTbls;
	}

	public DetalleMatricula addDetalleMatriculaTbl(DetalleMatricula detalleMatriculaTbl) {
		getDetalleMatriculaTbls().add(detalleMatriculaTbl);
		detalleMatriculaTbl.setCabeceraMatriculaTbl(this);

		return detalleMatriculaTbl;
	}

	public DetalleMatricula removeDetalleMatriculaTbl(DetalleMatricula detalleMatriculaTbl) {
		getDetalleMatriculaTbls().remove(detalleMatriculaTbl);
		detalleMatriculaTbl.setCabeceraMatriculaTbl(null);

		return detalleMatriculaTbl;
	}

}