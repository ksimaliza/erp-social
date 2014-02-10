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
 * The persistent class for the profesor_tbl database table.
 * 
 */
@Entity
@Table(name="profesor_tbl")
@NamedQuery(name="Profesor.findAll", query="SELECT p FROM Profesor p")
public class Profesor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROFESOR_TBL_PROTBLPK_GENERATOR", sequenceName="PROFESOR_TBL_PRO_TBL_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROFESOR_TBL_PROTBLPK_GENERATOR")
	@Column(name="pro_tbl_pk")
	private Integer proTblPk;

	//bi-directional many-to-one association to MateriaProfesor
	@OneToMany(mappedBy="profesorTbl")
	private List<MateriaProfesor> materiaProfesorTbls;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_fk")
	private Persona personaTbl;

	public Profesor() {
	}

	public Integer getProTblPk() {
		return this.proTblPk;
	}

	public void setProTblPk(Integer proTblPk) {
		this.proTblPk = proTblPk;
	}

	public List<MateriaProfesor> getMateriaProfesorTbls() {
		return this.materiaProfesorTbls;
	}

	public void setMateriaProfesorTbls(List<MateriaProfesor> materiaProfesorTbls) {
		this.materiaProfesorTbls = materiaProfesorTbls;
	}

	public MateriaProfesor addMateriaProfesorTbl(MateriaProfesor materiaProfesorTbl) {
		getMateriaProfesorTbls().add(materiaProfesorTbl);
		materiaProfesorTbl.setProfesorTbl(this);

		return materiaProfesorTbl;
	}

	public MateriaProfesor removeMateriaProfesorTbl(MateriaProfesor materiaProfesorTbl) {
		getMateriaProfesorTbls().remove(materiaProfesorTbl);
		materiaProfesorTbl.setProfesorTbl(null);

		return materiaProfesorTbl;
	}

	public Persona getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(Persona personaTbl) {
		this.personaTbl = personaTbl;
	}

}