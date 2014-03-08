package ec.edu.uce.erp.ejb.persistence.entity.matricula;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mat_estudiante database table.
 * 
 */
@Entity
@Table(name="mat_estudiante")
@NamedQuery(name="EstudianteDTO.findAll", query="SELECT e FROM EstudianteDTO e")
public class EstudianteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_ESTUDIANTE_ESTCODIGO_GENERATOR", sequenceName="MAT_ESTUDIANTE_EST_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_ESTUDIANTE_ESTCODIGO_GENERATOR")
	@Column(name="est_codigo")
	private Integer estCodigo;

	@Column(name="est_persona")
	private Integer estPersona;

	//bi-directional many-to-one association to EstudianteRepresentanteDTO
	@OneToMany(mappedBy="matEstudiante")
	private List<EstudianteRepresentanteDTO> matEstudianteRepresentantes;

	//bi-directional many-to-one association to MatriculaDTO
	@OneToMany(mappedBy="matEstudiante")
	private List<MatriculaDTO> matMatriculas;

	public EstudianteDTO() {
	}

	public Integer getEstCodigo() {
		return this.estCodigo;
	}

	public void setEstCodigo(Integer estCodigo) {
		this.estCodigo = estCodigo;
	}

	public Integer getEstPersona() {
		return this.estPersona;
	}

	public void setEstPersona(Integer estPersona) {
		this.estPersona = estPersona;
	}

	public List<EstudianteRepresentanteDTO> getMatEstudianteRepresentantes() {
		return this.matEstudianteRepresentantes;
	}

	public void setMatEstudianteRepresentantes(List<EstudianteRepresentanteDTO> matEstudianteRepresentantes) {
		this.matEstudianteRepresentantes = matEstudianteRepresentantes;
	}

	public EstudianteRepresentanteDTO addMatEstudianteRepresentante(EstudianteRepresentanteDTO matEstudianteRepresentante) {
		getMatEstudianteRepresentantes().add(matEstudianteRepresentante);
		matEstudianteRepresentante.setMatEstudiante(this);

		return matEstudianteRepresentante;
	}

	public EstudianteRepresentanteDTO removeMatEstudianteRepresentante(EstudianteRepresentanteDTO matEstudianteRepresentante) {
		getMatEstudianteRepresentantes().remove(matEstudianteRepresentante);
		matEstudianteRepresentante.setMatEstudiante(null);

		return matEstudianteRepresentante;
	}

	public List<MatriculaDTO> getMatMatriculas() {
		return this.matMatriculas;
	}

	public void setMatMatriculas(List<MatriculaDTO> matMatriculas) {
		this.matMatriculas = matMatriculas;
	}

	public MatriculaDTO addMatMatricula(MatriculaDTO matMatricula) {
		getMatMatriculas().add(matMatricula);
		matMatricula.setMatEstudiante(this);

		return matMatricula;
	}

	public MatriculaDTO removeMatMatricula(MatriculaDTO matMatricula) {
		getMatMatriculas().remove(matMatricula);
		matMatricula.setMatEstudiante(null);

		return matMatricula;
	}

}