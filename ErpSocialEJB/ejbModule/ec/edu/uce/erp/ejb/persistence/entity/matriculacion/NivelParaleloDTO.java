package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the mat_nivel_paralelo database table.
 * 
 */
@Entity
@Table(name="mat_nivel_paralelo")
@NamedQuery(name="NivelParaleloDTO.findAll", query="SELECT n FROM NivelParaleloDTO n")
public class NivelParaleloDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_NIVEL_PARALELO_NPACODIGO_GENERATOR", sequenceName="MAT_NIVEL_PARALELO_NPA_CODIGO_SEQ" , allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_NIVEL_PARALELO_NPACODIGO_GENERATOR")
	@Column(name="npa_codigo")
	private Integer npaCodigo;

	//bi-directional many-to-one association to AsinacionDTO
	@OneToMany(mappedBy="matNivelParalelo")
	private List<AsinacionDTO> matAsinacions;

	//bi-directional many-to-one association to NivelDTO
	@ManyToOne
	@JoinColumn(name="npa_nivel")
	private NivelDTO matNivel;

	//bi-directional many-to-one association to ParaleloDTO
	@ManyToOne
	@JoinColumn(name="npa_paralelo")
	private ParaleloDTO matParalelo;
	
	@Column(name="npa_empresa")
	private Integer npaEmpresa;

	public NivelParaleloDTO() {
	}

	public Integer getNpaCodigo() {
		return this.npaCodigo;
	}

	public void setNpaCodigo(Integer npaCodigo) {
		this.npaCodigo = npaCodigo;
	}

	public List<AsinacionDTO> getMatAsinacions() {
		return this.matAsinacions;
	}

	public void setMatAsinacions(List<AsinacionDTO> matAsinacions) {
		this.matAsinacions = matAsinacions;
	}

	public AsinacionDTO addMatAsinacion(AsinacionDTO matAsinacion) {
		getMatAsinacions().add(matAsinacion);
		matAsinacion.setMatNivelParalelo(this);

		return matAsinacion;
	}

	public AsinacionDTO removeMatAsinacion(AsinacionDTO matAsinacion) {
		getMatAsinacions().remove(matAsinacion);
		matAsinacion.setMatNivelParalelo(null);

		return matAsinacion;
	}

	public NivelDTO getMatNivel() {
		return this.matNivel;
	}

	public void setMatNivel(NivelDTO matNivel) {
		this.matNivel = matNivel;
	}

	public ParaleloDTO getMatParalelo() {
		return this.matParalelo;
	}

	public void setMatParalelo(ParaleloDTO matParalelo) {
		this.matParalelo = matParalelo;
	}

	public Integer getNpaEmpresa() {
		return npaEmpresa;
	}

	public void setNpaEmpresa(Integer npaEmpresa) {
		this.npaEmpresa = npaEmpresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matNivel == null) ? 0 : matNivel.hashCode());
		result = prime * result + ((matParalelo == null) ? 0 : matParalelo.hashCode());
		result = prime * result + ((npaCodigo == null) ? 0 : npaCodigo.hashCode());
		result = prime * result + ((npaEmpresa == null) ? 0 : npaEmpresa.hashCode());
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
		NivelParaleloDTO other = (NivelParaleloDTO) obj;
		if (matNivel == null) {
			if (other.matNivel != null)
				return false;
		} else if (!matNivel.equals(other.matNivel))
			return false;
		if (matParalelo == null) {
			if (other.matParalelo != null)
				return false;
		} else if (!matParalelo.equals(other.matParalelo))
			return false;
		if (npaCodigo == null) {
			if (other.npaCodigo != null)
				return false;
		} else if (!npaCodigo.equals(other.npaCodigo))
			return false;
		if (npaEmpresa == null) {
			if (other.npaEmpresa != null)
				return false;
		} else if (!npaEmpresa.equals(other.npaEmpresa))
			return false;
		return true;
	}

	

}