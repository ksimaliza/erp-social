package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the detalle_matricula_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_matricula_tbl")
@NamedQuery(name="DetalleMatricula.findAll", query="SELECT d FROM DetalleMatricula d")
public class DetalleMatricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleMatriculaPK id;

	@Column(name="det_matricula_descripcion")
	private String detMatriculaDescripcion;

	@Column(name="det_matricula_estado")
	private String detMatriculaEstado;

	//bi-directional many-to-one association to CabeceraMatricula
	@ManyToOne
	@JoinColumn(name="cab_matricula_fk", insertable=false, updatable=false)
	private CabeceraMatricula cabeceraMatriculaTbl;
	
	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="detalleMatriculaTbl")
	private List<Matricula> matriculaTbls;

	public DetalleMatricula() {
	}

	public DetalleMatriculaPK getId() {
		return this.id;
	}

	public void setId(DetalleMatriculaPK id) {
		this.id = id;
	}

	public String getDetMatriculaDescripcion() {
		return this.detMatriculaDescripcion;
	}

	public void setDetMatriculaDescripcion(String detMatriculaDescripcion) {
		this.detMatriculaDescripcion = detMatriculaDescripcion;
	}

	public String getDetMatriculaEstado() {
		return this.detMatriculaEstado;
	}

	public void setDetMatriculaEstado(String detMatriculaEstado) {
		this.detMatriculaEstado = detMatriculaEstado;
	}

	public CabeceraMatricula getCabeceraMatriculaTbl() {
		return this.cabeceraMatriculaTbl;
	}

	public void setCabeceraMatriculaTbl(CabeceraMatricula cabeceraMatriculaTbl) {
		this.cabeceraMatriculaTbl = cabeceraMatriculaTbl;
	}

	public List<Matricula> getMatriculaTbls() {
		return this.matriculaTbls;
	}

	public void setMatriculaTbls(List<Matricula> matriculaTbls) {
		this.matriculaTbls = matriculaTbls;
	}

	public Matricula addMatriculaTbl(Matricula matriculaTbl) {
		getMatriculaTbls().add(matriculaTbl);
		matriculaTbl.setDetalleMatriculaTbl(this);

		return matriculaTbl;
	}

	public Matricula removeMatriculaTbl(Matricula matriculaTbl) {
		getMatriculaTbls().remove(matriculaTbl);
		matriculaTbl.setDetalleMatriculaTbl(null);

		return matriculaTbl;
	}

}