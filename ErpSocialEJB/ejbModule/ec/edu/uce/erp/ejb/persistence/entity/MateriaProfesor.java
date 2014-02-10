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
 * The persistent class for the materia_profesor_tbl database table.
 * 
 */
@Entity
@Table(name="materia_profesor_tbl")
@NamedQuery(name="MateriaProfesor.findAll", query="SELECT m FROM MateriaProfesor m")
public class MateriaProfesor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MATERIA_PROFESOR_TBL_MATPROFESORPK_GENERATOR", sequenceName="MATERIA_PROFESOR_TBL_MAT_PROFESOR_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATERIA_PROFESOR_TBL_MATPROFESORPK_GENERATOR")
	@Column(name="mat_profesor_pk")
	private Integer matProfesorPk;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="mat_fk")
	private Materia materiaTbl;

	//bi-directional many-to-one association to Profesor
	@ManyToOne
	@JoinColumn(name="pro_fk")
	private Profesor profesorTbl;

	public MateriaProfesor() {
	}

	public Integer getMatProfesorPk() {
		return this.matProfesorPk;
	}

	public void setMatProfesorPk(Integer matProfesorPk) {
		this.matProfesorPk = matProfesorPk;
	}

	public Materia getMateriaTbl() {
		return this.materiaTbl;
	}

	public void setMateriaTbl(Materia materiaTbl) {
		this.materiaTbl = materiaTbl;
	}

	public Profesor getProfesorTbl() {
		return this.profesorTbl;
	}

	public void setProfesorTbl(Profesor profesorTbl) {
		this.profesorTbl = profesorTbl;
	}

}