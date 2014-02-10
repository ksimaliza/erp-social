package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the paralelo_tbl database table.
 * 
 */
@Entity
@Table(name="paralelo_tbl")
@NamedQuery(name="Paralelo.findAll", query="SELECT p FROM Paralelo p")
public class Paralelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PARALELO_TBL_PARPK_GENERATOR", sequenceName="PARALELO_TBL_PAR_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARALELO_TBL_PARPK_GENERATOR")
	@Column(name="par_pk")
	private Integer parPk;

	@Column(name="par_estado")
	private String parEstado;

	@Column(name="par_nombre")
	private String parNombre;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="paraleloTbl")
	private List<Curso> cursoTbls;

	public Paralelo() {
	}

	public Integer getParPk() {
		return this.parPk;
	}

	public void setParPk(Integer parPk) {
		this.parPk = parPk;
	}

	public String getParEstado() {
		return this.parEstado;
	}

	public void setParEstado(String parEstado) {
		this.parEstado = parEstado;
	}

	public String getParNombre() {
		return this.parNombre;
	}

	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	public List<Curso> getCursoTbls() {
		return this.cursoTbls;
	}

	public void setCursoTbls(List<Curso> cursoTbls) {
		this.cursoTbls = cursoTbls;
	}

	public Curso addCursoTbl(Curso cursoTbl) {
		getCursoTbls().add(cursoTbl);
		cursoTbl.setParaleloTbl(this);

		return cursoTbl;
	}

	public Curso removeCursoTbl(Curso cursoTbl) {
		getCursoTbls().remove(cursoTbl);
		cursoTbl.setParaleloTbl(null);

		return cursoTbl;
	}

}