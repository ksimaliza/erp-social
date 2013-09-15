package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the materia_profesor_tbl database table.
 * 
 */
@Entity
@Table(name="materia_profesor_tbl")
public class MateriaProfesorTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mat_profesor_pk")
	private Integer matProfesorPk;

	//bi-directional many-to-one association to MateriaTbl
    @ManyToOne
	@JoinColumn(name="mat_fk")
	private MateriaTbl materiaTbl;

	//bi-directional many-to-one association to ProfesorTbl
    @ManyToOne
	@JoinColumn(name="pro_fk")
	private ProfesorTbl profesorTbl;

    public MateriaProfesorTbl() {
    }

	public Integer getMatProfesorPk() {
		return this.matProfesorPk;
	}

	public void setMatProfesorPk(Integer matProfesorPk) {
		this.matProfesorPk = matProfesorPk;
	}

	public MateriaTbl getMateriaTbl() {
		return this.materiaTbl;
	}

	public void setMateriaTbl(MateriaTbl materiaTbl) {
		this.materiaTbl = materiaTbl;
	}
	
	public ProfesorTbl getProfesorTbl() {
		return this.profesorTbl;
	}

	public void setProfesorTbl(ProfesorTbl profesorTbl) {
		this.profesorTbl = profesorTbl;
	}
	
}