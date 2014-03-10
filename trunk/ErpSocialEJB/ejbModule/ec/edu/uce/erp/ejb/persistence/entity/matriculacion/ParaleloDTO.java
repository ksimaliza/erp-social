package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the mat_paralelo database table.
 * 
 */
@Entity
@Table(name="mat_paralelo")
@NamedQuery(name="ParaleloDTO.findAll", query="SELECT p FROM ParaleloDTO p")
public class ParaleloDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_PARALELO_PARCODIGO_GENERATOR", sequenceName="MAT_PARALELO_PAR_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_PARALELO_PARCODIGO_GENERATOR")
	@Column(name="par_codigo")
	private Integer parCodigo;

	@Column(name="par_descripcion")
	private String parDescripcion;

	//bi-directional many-to-one association to NivelParaleloDTO
	@OneToMany(mappedBy="matParalelo")
	private List<NivelParaleloDTO> matNivelParalelos;

	public ParaleloDTO() {
	}

	public Integer getParCodigo() {
		return this.parCodigo;
	}

	public void setParCodigo(Integer parCodigo) {
		this.parCodigo = parCodigo;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public List<NivelParaleloDTO> getMatNivelParalelos() {
		return this.matNivelParalelos;
	}

	public void setMatNivelParalelos(List<NivelParaleloDTO> matNivelParalelos) {
		this.matNivelParalelos = matNivelParalelos;
	}

	public NivelParaleloDTO addMatNivelParalelo(NivelParaleloDTO matNivelParalelo) {
		getMatNivelParalelos().add(matNivelParalelo);
		matNivelParalelo.setMatParalelo(this);

		return matNivelParalelo;
	}

	public NivelParaleloDTO removeMatNivelParalelo(NivelParaleloDTO matNivelParalelo) {
		getMatNivelParalelos().remove(matNivelParalelo);
		matNivelParalelo.setMatParalelo(null);

		return matNivelParalelo;
	}

}