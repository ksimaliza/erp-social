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
 * The persistent class for the materia_tbl database table.
 * 
 */
@Entity
@Table(name="materia_tbl")
@NamedQuery(name="Materia.findAll", query="SELECT m FROM Materia m")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MATERIA_TBL_MATPK_GENERATOR", sequenceName="MATERIA_TBL_MAT_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATERIA_TBL_MATPK_GENERATOR")
	@Column(name="mat_pk")
	private Integer matPk;

	@Column(name="mat_estado")
	private String matEstado;

	@Column(name="mat_nombre")
	private String matNombre;

	//bi-directional many-to-one association to MatAnio
	@OneToMany(mappedBy="materiaTbl")
	private List<MatAnio> matAnioTbls;

	//bi-directional many-to-one association to MateriaProfesor
	@OneToMany(mappedBy="materiaTbl")
	private List<MateriaProfesor> materiaProfesorTbls;

	//bi-directional many-to-one association to NotMateria
	@OneToMany(mappedBy="materiaTbl")
	private List<NotMateria> notMateriaTbls;

	public Materia() {
	}

	public Integer getMatPk() {
		return this.matPk;
	}

	public void setMatPk(Integer matPk) {
		this.matPk = matPk;
	}

	public String getMatEstado() {
		return this.matEstado;
	}

	public void setMatEstado(String matEstado) {
		this.matEstado = matEstado;
	}

	public String getMatNombre() {
		return this.matNombre;
	}

	public void setMatNombre(String matNombre) {
		this.matNombre = matNombre;
	}

	public List<MatAnio> getMatAnioTbls() {
		return this.matAnioTbls;
	}

	public void setMatAnioTbls(List<MatAnio> matAnioTbls) {
		this.matAnioTbls = matAnioTbls;
	}

	public MatAnio addMatAnioTbl(MatAnio matAnioTbl) {
		getMatAnioTbls().add(matAnioTbl);
		matAnioTbl.setMateriaTbl(this);

		return matAnioTbl;
	}

	public MatAnio removeMatAnioTbl(MatAnio matAnioTbl) {
		getMatAnioTbls().remove(matAnioTbl);
		matAnioTbl.setMateriaTbl(null);

		return matAnioTbl;
	}

	public List<MateriaProfesor> getMateriaProfesorTbls() {
		return this.materiaProfesorTbls;
	}

	public void setMateriaProfesorTbls(List<MateriaProfesor> materiaProfesorTbls) {
		this.materiaProfesorTbls = materiaProfesorTbls;
	}

	public MateriaProfesor addMateriaProfesorTbl(MateriaProfesor materiaProfesorTbl) {
		getMateriaProfesorTbls().add(materiaProfesorTbl);
		materiaProfesorTbl.setMateriaTbl(this);

		return materiaProfesorTbl;
	}

	public MateriaProfesor removeMateriaProfesorTbl(MateriaProfesor materiaProfesorTbl) {
		getMateriaProfesorTbls().remove(materiaProfesorTbl);
		materiaProfesorTbl.setMateriaTbl(null);

		return materiaProfesorTbl;
	}

	public List<NotMateria> getNotMateriaTbls() {
		return this.notMateriaTbls;
	}

	public void setNotMateriaTbls(List<NotMateria> notMateriaTbls) {
		this.notMateriaTbls = notMateriaTbls;
	}

	public NotMateria addNotMateriaTbl(NotMateria notMateriaTbl) {
		getNotMateriaTbls().add(notMateriaTbl);
		notMateriaTbl.setMateriaTbl(this);

		return notMateriaTbl;
	}

	public NotMateria removeNotMateriaTbl(NotMateria notMateriaTbl) {
		getNotMateriaTbls().remove(notMateriaTbl);
		notMateriaTbl.setMateriaTbl(null);

		return notMateriaTbl;
	}

}