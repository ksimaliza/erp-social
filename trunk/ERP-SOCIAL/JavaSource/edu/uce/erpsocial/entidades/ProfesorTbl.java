package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the profesor_tbl database table.
 * 
 */
@Entity
@Table(name="profesor_tbl")
public class ProfesorTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pro_tbl_pk")
	private Integer proTblPk;

	//bi-directional many-to-one association to MateriaProfesorTbl
	@OneToMany(mappedBy="profesorTbl")
	private Set<MateriaProfesorTbl> materiaProfesorTbls;

	//bi-directional many-to-one association to PersonaTbl
    @ManyToOne
	@JoinColumn(name="per_fk")
	private PersonaTbl personaTbl;

    public ProfesorTbl() {
    }

	public Integer getProTblPk() {
		return this.proTblPk;
	}

	public void setProTblPk(Integer proTblPk) {
		this.proTblPk = proTblPk;
	}

	public Set<MateriaProfesorTbl> getMateriaProfesorTbls() {
		return this.materiaProfesorTbls;
	}

	public void setMateriaProfesorTbls(Set<MateriaProfesorTbl> materiaProfesorTbls) {
		this.materiaProfesorTbls = materiaProfesorTbls;
	}
	
	public PersonaTbl getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(PersonaTbl personaTbl) {
		this.personaTbl = personaTbl;
	}
	
}