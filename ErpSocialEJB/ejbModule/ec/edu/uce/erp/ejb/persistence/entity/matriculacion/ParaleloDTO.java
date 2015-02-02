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
	@SequenceGenerator(name="MAT_PARALELO_PARCODIGO_GENERATOR", sequenceName="MAT_PARALELO_PAR_CODIGO_SEQ" , allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_PARALELO_PARCODIGO_GENERATOR")
	@Column(name="par_codigo")
	private Integer parCodigo;

	@Column(name="par_descripcion")
	private String parDescripcion;
	
	@Column(name="par_empresa")
	private Integer parEmpresa;

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

	public Integer getParEmpresa() {
		return parEmpresa;
	}

	public void setParEmpresa(Integer parEmpresa) {
		this.parEmpresa = parEmpresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parCodigo == null) ? 0 : parCodigo.hashCode());
		result = prime * result + ((parEmpresa == null) ? 0 : parEmpresa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParaleloDTO other = (ParaleloDTO) obj;
		if (parCodigo == null) {
			if (other.parCodigo != null)
				return false;
		} else if (!parCodigo.equals(other.parCodigo))
			return false;
		if (parEmpresa == null) {
			if (other.parEmpresa != null)
				return false;
		} else if (!parEmpresa.equals(other.parEmpresa))
			return false;
		return true;
	}

	

}