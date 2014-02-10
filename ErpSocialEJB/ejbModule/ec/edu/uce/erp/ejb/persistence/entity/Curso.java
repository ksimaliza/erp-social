package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the curso_tbl database table.
 * 
 */
@Entity
@Table(name="curso_tbl")
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CURSO_TBL_CURPK_GENERATOR", sequenceName="CURSO_TBL_CUR_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CURSO_TBL_CURPK_GENERATOR")
	@Column(name="cur_pk")
	private Integer curPk;

	@Column(name="cur_estado")
	private String curEstado;

	@Column(name="cur_nombre")
	private String curNombre;

	//bi-directional many-to-one association to Cupo
	@ManyToOne
	@JoinColumn(name="cup_fk")
	private Cupo cupoTbl;

	//bi-directional many-to-one association to Paralelo
	@ManyToOne
	@JoinColumn(name="par_fk")
	private Paralelo paraleloTbl;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="cursoTbl")
	private List<Matricula> matriculaTbls;

	public Curso() {
	}

	public Integer getCurPk() {
		return this.curPk;
	}

	public void setCurPk(Integer curPk) {
		this.curPk = curPk;
	}

	public String getCurEstado() {
		return this.curEstado;
	}

	public void setCurEstado(String curEstado) {
		this.curEstado = curEstado;
	}

	public String getCurNombre() {
		return this.curNombre;
	}

	public void setCurNombre(String curNombre) {
		this.curNombre = curNombre;
	}

	public Cupo getCupoTbl() {
		return this.cupoTbl;
	}

	public void setCupoTbl(Cupo cupoTbl) {
		this.cupoTbl = cupoTbl;
	}

	public Paralelo getParaleloTbl() {
		return this.paraleloTbl;
	}

	public void setParaleloTbl(Paralelo paraleloTbl) {
		this.paraleloTbl = paraleloTbl;
	}

	public List<Matricula> getMatriculaTbls() {
		return this.matriculaTbls;
	}

	public void setMatriculaTbls(List<Matricula> matriculaTbls) {
		this.matriculaTbls = matriculaTbls;
	}

	public Matricula addMatriculaTbl(Matricula matriculaTbl) {
		getMatriculaTbls().add(matriculaTbl);
		matriculaTbl.setCursoTbl(this);

		return matriculaTbl;
	}

	public Matricula removeMatriculaTbl(Matricula matriculaTbl) {
		getMatriculaTbls().remove(matriculaTbl);
		matriculaTbl.setCursoTbl(null);

		return matriculaTbl;
	}

}