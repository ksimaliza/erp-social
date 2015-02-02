package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the mat_nivel database table.
 * 
 */
@Entity
@Table(name="mat_nivel")
@NamedQuery(name="NivelDTO.findAll", query="SELECT n FROM NivelDTO n")
public class NivelDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_NIVEL_NIVCODIGO_GENERATOR", sequenceName="MAT_NIVEL_NIV_CODIGO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_NIVEL_NIVCODIGO_GENERATOR")
	
	@Column(name="niv_codigo")
	private Integer nivCodigo;
	
	@Column(name="niv_descaripcion")
	private String nivDescaripcion;
	
	@Column(name="niv_empresa")
	private Integer nivEmpresa;

	//bi-directional many-to-one association to NivelParaleloDTO
	@OneToMany(mappedBy="matNivel")
	private List<NivelParaleloDTO> matNivelParalelos;

	public NivelDTO() {
	}

	public Integer getNivCodigo() {
		return this.nivCodigo;
	}

	public void setNivCodigo(Integer nivCodigo) {
		this.nivCodigo = nivCodigo;
	}

	public String getNivDescaripcion() {
		return this.nivDescaripcion;
	}

	public void setNivDescaripcion(String nivDescaripcion) {
		this.nivDescaripcion = nivDescaripcion;
	}

	public List<NivelParaleloDTO> getMatNivelParalelos() {
		return this.matNivelParalelos;
	}

	public void setMatNivelParalelos(List<NivelParaleloDTO> matNivelParalelos) {
		this.matNivelParalelos = matNivelParalelos;
	}

	public NivelParaleloDTO addMatNivelParalelo(NivelParaleloDTO matNivelParalelo) {
		getMatNivelParalelos().add(matNivelParalelo);
		matNivelParalelo.setMatNivel(this);

		return matNivelParalelo;
	}

	public NivelParaleloDTO removeMatNivelParalelo(NivelParaleloDTO matNivelParalelo) {
		getMatNivelParalelos().remove(matNivelParalelo);
		matNivelParalelo.setMatNivel(null);

		return matNivelParalelo;
	}

	public Integer getNivEmpresa() {
		return nivEmpresa;
	}

	public void setNivEmpresa(Integer nivEmpresa) {
		this.nivEmpresa = nivEmpresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nivCodigo == null) ? 0 : nivCodigo.hashCode());
		result = prime * result + ((nivEmpresa == null) ? 0 : nivEmpresa.hashCode());
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
		NivelDTO other = (NivelDTO) obj;
		if (nivCodigo == null) {
			if (other.nivCodigo != null)
				return false;
		} else if (!nivCodigo.equals(other.nivCodigo))
			return false;
		if (nivEmpresa == null) {
			if (other.nivEmpresa != null)
				return false;
		} else if (!nivEmpresa.equals(other.nivEmpresa))
			return false;
		return true;
	}	

}