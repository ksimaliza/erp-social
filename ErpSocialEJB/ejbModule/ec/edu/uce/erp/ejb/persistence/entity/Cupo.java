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
 * The persistent class for the cupo_tbl database table.
 * 
 */
@Entity
@Table(name="cupo_tbl")
@NamedQuery(name="Cupo.findAll", query="SELECT c FROM Cupo c")
public class Cupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUPO_TBL_CUPPK_GENERATOR", sequenceName="CUPO_TBL_CUP_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUPO_TBL_CUPPK_GENERATOR")
	@Column(name="cup_pk")
	private Integer cupPk;

	@Column(name="cup_cantidad")
	private Integer cupCantidad;

	@Column(name="cup_estado")
	private String cupEstado;

	//bi-directional many-to-one association to Curso
	@OneToMany(mappedBy="cupoTbl")
	private List<Curso> cursoTbls;

	public Cupo() {
	}

	public Integer getCupPk() {
		return this.cupPk;
	}

	public void setCupPk(Integer cupPk) {
		this.cupPk = cupPk;
	}

	public Integer getCupCantidad() {
		return this.cupCantidad;
	}

	public void setCupCantidad(Integer cupCantidad) {
		this.cupCantidad = cupCantidad;
	}

	public String getCupEstado() {
		return this.cupEstado;
	}

	public void setCupEstado(String cupEstado) {
		this.cupEstado = cupEstado;
	}

	public List<Curso> getCursoTbls() {
		return this.cursoTbls;
	}

	public void setCursoTbls(List<Curso> cursoTbls) {
		this.cursoTbls = cursoTbls;
	}

	public Curso addCursoTbl(Curso cursoTbl) {
		getCursoTbls().add(cursoTbl);
		cursoTbl.setCupoTbl(this);

		return cursoTbl;
	}

	public Curso removeCursoTbl(Curso cursoTbl) {
		getCursoTbls().remove(cursoTbl);
		cursoTbl.setCupoTbl(null);

		return cursoTbl;
	}

}