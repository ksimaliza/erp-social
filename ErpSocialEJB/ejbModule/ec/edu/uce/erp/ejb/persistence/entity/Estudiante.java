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
 * The persistent class for the estudiante_tbl database table.
 * 
 */
@Entity
@Table(name="estudiante_tbl")
@NamedQuery(name="Estudiante.findAll", query="SELECT e FROM Estudiante e")
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTUDIANTE_TBL_ESTPK_GENERATOR", sequenceName="ESTUDIANTE_TBL_EST_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTUDIANTE_TBL_ESTPK_GENERATOR")
	@Column(name="est_pk")
	private Integer estPk;

	//bi-directional many-to-one association to EstudianteMatriculaTbl
	@OneToMany(mappedBy="estudianteTbl")
	private List<EstudianteMatriculaTbl> estudianteMatriculaTbls;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_fk")
	private Persona personaTbl;

	//bi-directional many-to-one association to Notas
	@OneToMany(mappedBy="estudianteTbl")
	private List<Notas> notasTbls;

	//bi-directional many-to-one association to PadreEstudiante
	@OneToMany(mappedBy="estudianteTbl")
	private List<PadreEstudiante> padreEstudianteTbls;

	public Estudiante() {
	}

	public Integer getEstPk() {
		return this.estPk;
	}

	public void setEstPk(Integer estPk) {
		this.estPk = estPk;
	}

	public List<EstudianteMatriculaTbl> getEstudianteMatriculaTbls() {
		return this.estudianteMatriculaTbls;
	}

	public void setEstudianteMatriculaTbls(List<EstudianteMatriculaTbl> estudianteMatriculaTbls) {
		this.estudianteMatriculaTbls = estudianteMatriculaTbls;
	}

	public EstudianteMatriculaTbl addEstudianteMatriculaTbl(EstudianteMatriculaTbl estudianteMatriculaTbl) {
		getEstudianteMatriculaTbls().add(estudianteMatriculaTbl);
		estudianteMatriculaTbl.setEstudianteTbl(this);

		return estudianteMatriculaTbl;
	}

	public EstudianteMatriculaTbl removeEstudianteMatriculaTbl(EstudianteMatriculaTbl estudianteMatriculaTbl) {
		getEstudianteMatriculaTbls().remove(estudianteMatriculaTbl);
		estudianteMatriculaTbl.setEstudianteTbl(null);

		return estudianteMatriculaTbl;
	}

	public Persona getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(Persona personaTbl) {
		this.personaTbl = personaTbl;
	}

	public List<Notas> getNotasTbls() {
		return this.notasTbls;
	}

	public void setNotasTbls(List<Notas> notasTbls) {
		this.notasTbls = notasTbls;
	}

	public Notas addNotasTbl(Notas notasTbl) {
		getNotasTbls().add(notasTbl);
		notasTbl.setEstudianteTbl(this);

		return notasTbl;
	}

	public Notas removeNotasTbl(Notas notasTbl) {
		getNotasTbls().remove(notasTbl);
		notasTbl.setEstudianteTbl(null);

		return notasTbl;
	}

	public List<PadreEstudiante> getPadreEstudianteTbls() {
		return this.padreEstudianteTbls;
	}

	public void setPadreEstudianteTbls(List<PadreEstudiante> padreEstudianteTbls) {
		this.padreEstudianteTbls = padreEstudianteTbls;
	}

	public PadreEstudiante addPadreEstudianteTbl(PadreEstudiante padreEstudianteTbl) {
		getPadreEstudianteTbls().add(padreEstudianteTbl);
		padreEstudianteTbl.setEstudianteTbl(this);

		return padreEstudianteTbl;
	}

	public PadreEstudiante removePadreEstudianteTbl(PadreEstudiante padreEstudianteTbl) {
		getPadreEstudianteTbls().remove(padreEstudianteTbl);
		padreEstudianteTbl.setEstudianteTbl(null);

		return padreEstudianteTbl;
	}

}