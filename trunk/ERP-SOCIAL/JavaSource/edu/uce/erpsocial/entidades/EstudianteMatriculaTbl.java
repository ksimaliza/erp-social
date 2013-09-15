package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the estudiante_matricula_tbl database table.
 * 
 */
@Entity
@Table(name="estudiante_matricula_tbl")
public class EstudianteMatriculaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="est_matricula_pk")
	private Integer estMatriculaPk;

	//bi-directional many-to-one association to EstudianteTbl
    @ManyToOne
	@JoinColumn(name="est_fk")
	private EstudianteTbl estudianteTbl;

	//bi-directional many-to-one association to MatriculaTbl
    @ManyToOne
	@JoinColumn(name="mat_pk")
	private MatriculaTbl matriculaTbl;

    public EstudianteMatriculaTbl() {
    }

	public Integer getEstMatriculaPk() {
		return this.estMatriculaPk;
	}

	public void setEstMatriculaPk(Integer estMatriculaPk) {
		this.estMatriculaPk = estMatriculaPk;
	}

	public EstudianteTbl getEstudianteTbl() {
		return this.estudianteTbl;
	}

	public void setEstudianteTbl(EstudianteTbl estudianteTbl) {
		this.estudianteTbl = estudianteTbl;
	}
	
	public MatriculaTbl getMatriculaTbl() {
		return this.matriculaTbl;
	}

	public void setMatriculaTbl(MatriculaTbl matriculaTbl) {
		this.matriculaTbl = matriculaTbl;
	}
	
}