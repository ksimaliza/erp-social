package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the detalle_matricula_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_matricula_tbl")
public class DetalleMatriculaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleMatriculaTblPK id;

	@Column(name="det_matricula_descripcion")
	private String detMatriculaDescripcion;

	@Column(name="det_matricula_estado")
	private String detMatriculaEstado;

	//bi-directional many-to-one association to CabeceraMatriculaTbl
    @ManyToOne
	@JoinColumn(name="cab_matricula_fk")
	private CabeceraMatriculaTbl cabeceraMatriculaTbl;

	//bi-directional many-to-one association to MatriculaTbl
	@OneToMany(mappedBy="detalleMatriculaTbl1")
	private Set<MatriculaTbl> matriculaTbls1;

	//bi-directional many-to-one association to MatriculaTbl
	@OneToMany(mappedBy="detalleMatriculaTbl2")
	private Set<MatriculaTbl> matriculaTbls2;

    public DetalleMatriculaTbl() {
    }

	public DetalleMatriculaTblPK getId() {
		return this.id;
	}

	public void setId(DetalleMatriculaTblPK id) {
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

	public CabeceraMatriculaTbl getCabeceraMatriculaTbl() {
		return this.cabeceraMatriculaTbl;
	}

	public void setCabeceraMatriculaTbl(CabeceraMatriculaTbl cabeceraMatriculaTbl) {
		this.cabeceraMatriculaTbl = cabeceraMatriculaTbl;
	}
	
	public Set<MatriculaTbl> getMatriculaTbls1() {
		return this.matriculaTbls1;
	}

	public void setMatriculaTbls1(Set<MatriculaTbl> matriculaTbls1) {
		this.matriculaTbls1 = matriculaTbls1;
	}
	
	public Set<MatriculaTbl> getMatriculaTbls2() {
		return this.matriculaTbls2;
	}

	public void setMatriculaTbls2(Set<MatriculaTbl> matriculaTbls2) {
		this.matriculaTbls2 = matriculaTbls2;
	}
	
}