package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the materia_tbl database table.
 * 
 */
@Entity
@Table(name="materia_tbl")
public class MateriaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mat_pk")
	private Integer matPk;

	@Column(name="mat_nombre")
	private String matNombre;

	//bi-directional many-to-one association to MateriaProfesorTbl
	@OneToMany(mappedBy="materiaTbl")
	private Set<MateriaProfesorTbl> materiaProfesorTbls;

	//bi-directional many-to-one association to NotMateriaTbl
	@OneToMany(mappedBy="materiaTbl")
	private Set<NotMateriaTbl> notMateriaTbls;

    public MateriaTbl() {
    }

	public Integer getMatPk() {
		return this.matPk;
	}

	public void setMatPk(Integer matPk) {
		this.matPk = matPk;
	}

	public String getMatNombre() {
		return this.matNombre;
	}

	public void setMatNombre(String matNombre) {
		this.matNombre = matNombre;
	}

	public Set<MateriaProfesorTbl> getMateriaProfesorTbls() {
		return this.materiaProfesorTbls;
	}

	public void setMateriaProfesorTbls(Set<MateriaProfesorTbl> materiaProfesorTbls) {
		this.materiaProfesorTbls = materiaProfesorTbls;
	}
	
	public Set<NotMateriaTbl> getNotMateriaTbls() {
		return this.notMateriaTbls;
	}

	public void setNotMateriaTbls(Set<NotMateriaTbl> notMateriaTbls) {
		this.notMateriaTbls = notMateriaTbls;
	}
	
}