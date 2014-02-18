package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mat_estudiante_representante database table.
 * 
 */
@Entity
@Table(name="mat_estudiante_representante")
@NamedQuery(name="EstudianteRepresentanteDTO.findAll", query="SELECT e FROM EstudianteRepresentanteDTO e")
public class EstudianteRepresentanteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_ESTUDIANTE_REPRESENTANTE_ESRCODIGO_GENERATOR", sequenceName="MAT_ESTUDIANTE_REPRESENTANTE_ESR_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_ESTUDIANTE_REPRESENTANTE_ESRCODIGO_GENERATOR")
	@Column(name="esr_codigo")
	private Integer esrCodigo;

	//bi-directional many-to-one association to EstudianteDTO
	@ManyToOne
	@JoinColumn(name="esr_esrudiante")
	private EstudianteDTO matEstudiante;

	//bi-directional many-to-one association to RepresentanteDTO
	@ManyToOne
	@JoinColumn(name="esr_representante")
	private RepresentanteDTO matRepresentante;

	public EstudianteRepresentanteDTO() {
	}

	public Integer getEsrCodigo() {
		return this.esrCodigo;
	}

	public void setEsrCodigo(Integer esrCodigo) {
		this.esrCodigo = esrCodigo;
	}

	public EstudianteDTO getMatEstudiante() {
		return this.matEstudiante;
	}

	public void setMatEstudiante(EstudianteDTO matEstudiante) {
		this.matEstudiante = matEstudiante;
	}

	public RepresentanteDTO getMatRepresentante() {
		return this.matRepresentante;
	}

	public void setMatRepresentante(RepresentanteDTO matRepresentante) {
		this.matRepresentante = matRepresentante;
	}

}