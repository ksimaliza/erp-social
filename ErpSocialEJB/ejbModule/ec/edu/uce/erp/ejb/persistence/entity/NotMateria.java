package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the not_materia_tbl database table.
 * 
 */
@Entity
@Table(name="not_materia_tbl")
@NamedQuery(name="NotMateria.findAll", query="SELECT n FROM NotMateria n")
public class NotMateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NOT_MATERIA_TBL_NOTMATERIAPK_GENERATOR", sequenceName="NOT_MATERIA_TBL_NOT_MATERIA_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOT_MATERIA_TBL_NOTMATERIAPK_GENERATOR")
	@Column(name="not_materia_pk")
	private Integer notMateriaPk;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="mat_fk")
	private Materia materiaTbl;

	//bi-directional many-to-one association to Notas
	@ManyToOne
	@JoinColumn(name="not_fk")
	private Notas notasTbl;

	public NotMateria() {
	}

	public Integer getNotMateriaPk() {
		return this.notMateriaPk;
	}

	public void setNotMateriaPk(Integer notMateriaPk) {
		this.notMateriaPk = notMateriaPk;
	}

	public Materia getMateriaTbl() {
		return this.materiaTbl;
	}

	public void setMateriaTbl(Materia materiaTbl) {
		this.materiaTbl = materiaTbl;
	}

	public Notas getNotasTbl() {
		return this.notasTbl;
	}

	public void setNotasTbl(Notas notasTbl) {
		this.notasTbl = notasTbl;
	}

}