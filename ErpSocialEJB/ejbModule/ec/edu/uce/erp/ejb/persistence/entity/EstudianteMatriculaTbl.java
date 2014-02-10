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
 * The persistent class for the estudiante_matricula_tbl database table.
 * 
 */
@Entity
@Table(name="estudiante_matricula_tbl")
@NamedQuery(name="EstudianteMatriculaTbl.findAll", query="SELECT e FROM EstudianteMatriculaTbl e")
public class EstudianteMatriculaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTUDIANTE_MATRICULA_TBL_ESTMATRICULAPK_GENERATOR", sequenceName="ESTUDIANTE_MATRICULA_TBL_EST_MATRICULA_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTUDIANTE_MATRICULA_TBL_ESTMATRICULAPK_GENERATOR")
	@Column(name="est_matricula_pk")
	private Integer estMatriculaPk;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="est_fk")
	private Estudiante estudianteTbl;

	//bi-directional many-to-one association to Matricula
	@ManyToOne
	@JoinColumn(name="mat_pk")
	private Matricula matriculaTbl;

	public EstudianteMatriculaTbl() {
	}

	public Integer getEstMatriculaPk() {
		return this.estMatriculaPk;
	}

	public void setEstMatriculaPk(Integer estMatriculaPk) {
		this.estMatriculaPk = estMatriculaPk;
	}

	public Estudiante getEstudianteTbl() {
		return this.estudianteTbl;
	}

	public void setEstudianteTbl(Estudiante estudianteTbl) {
		this.estudianteTbl = estudianteTbl;
	}

	public Matricula getMatriculaTbl() {
		return this.matriculaTbl;
	}

	public void setMatriculaTbl(Matricula matriculaTbl) {
		this.matriculaTbl = matriculaTbl;
	}

}