package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the mat_representante database table.
 * 
 */
@Entity
@Table(name="mat_representante")
@NamedQuery(name="RepresentanteDTO.findAll", query="SELECT r FROM RepresentanteDTO r")
public class RepresentanteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_REPRESENTANTE_REPCODIGO_GENERATOR", sequenceName="MAT_REPRESENTANTE_REP_CODIGO_SEQ",allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_REPRESENTANTE_REPCODIGO_GENERATOR")
	@Column(name="rep_codigo")
	private Integer repCodigo;

	@Column(name="rep_persona")
	private Integer repPersona;

	//bi-directional many-to-one association to EstudianteRepresentanteDTO
	@OneToMany(mappedBy="matRepresentante")
	private List<EstudianteRepresentanteDTO> matEstudianteRepresentantes;

	public RepresentanteDTO() {
	}

	public Integer getRepCodigo() {
		return this.repCodigo;
	}

	public void setRepCodigo(Integer repCodigo) {
		this.repCodigo = repCodigo;
	}

	public Integer getRepPersona() {
		return this.repPersona;
	}

	public void setRepPersona(Integer repPersona) {
		this.repPersona = repPersona;
	}

	public List<EstudianteRepresentanteDTO> getMatEstudianteRepresentantes() {
		return this.matEstudianteRepresentantes;
	}

	public void setMatEstudianteRepresentantes(List<EstudianteRepresentanteDTO> matEstudianteRepresentantes) {
		this.matEstudianteRepresentantes = matEstudianteRepresentantes;
	}

	public EstudianteRepresentanteDTO addMatEstudianteRepresentante(EstudianteRepresentanteDTO matEstudianteRepresentante) {
		getMatEstudianteRepresentantes().add(matEstudianteRepresentante);
		matEstudianteRepresentante.setMatRepresentante(this);

		return matEstudianteRepresentante;
	}

	public EstudianteRepresentanteDTO removeMatEstudianteRepresentante(EstudianteRepresentanteDTO matEstudianteRepresentante) {
		getMatEstudianteRepresentantes().remove(matEstudianteRepresentante);
		matEstudianteRepresentante.setMatRepresentante(null);

		return matEstudianteRepresentante;
	}

}