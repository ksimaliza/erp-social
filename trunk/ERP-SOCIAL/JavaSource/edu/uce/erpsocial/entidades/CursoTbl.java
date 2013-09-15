package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the curso_tbl database table.
 * 
 */
@Entity
@Table(name="curso_tbl")
public class CursoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cur_pk")
	private Integer curPk;

	@Column(name="cur_nombre")
	private String curNombre;

	//bi-directional many-to-one association to CupoTbl
    @ManyToOne
	@JoinColumn(name="cup_fk")
	private CupoTbl cupoTbl;

	//bi-directional many-to-one association to ParaleloTbl
    @ManyToOne
	@JoinColumn(name="par_fk")
	private ParaleloTbl paraleloTbl;

	//bi-directional many-to-one association to MatriculaTbl
	@OneToMany(mappedBy="cursoTbl")
	private Set<MatriculaTbl> matriculaTbls;

    public CursoTbl() {
    }

	public Integer getCurPk() {
		return this.curPk;
	}

	public void setCurPk(Integer curPk) {
		this.curPk = curPk;
	}

	public String getCurNombre() {
		return this.curNombre;
	}

	public void setCurNombre(String curNombre) {
		this.curNombre = curNombre;
	}

	public CupoTbl getCupoTbl() {
		return this.cupoTbl;
	}

	public void setCupoTbl(CupoTbl cupoTbl) {
		this.cupoTbl = cupoTbl;
	}
	
	public ParaleloTbl getParaleloTbl() {
		return this.paraleloTbl;
	}

	public void setParaleloTbl(ParaleloTbl paraleloTbl) {
		this.paraleloTbl = paraleloTbl;
	}
	
	public Set<MatriculaTbl> getMatriculaTbls() {
		return this.matriculaTbls;
	}

	public void setMatriculaTbls(Set<MatriculaTbl> matriculaTbls) {
		this.matriculaTbls = matriculaTbls;
	}
	
}