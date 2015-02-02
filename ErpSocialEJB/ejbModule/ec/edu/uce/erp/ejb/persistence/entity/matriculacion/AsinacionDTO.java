package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the mat_asinacion database table.
 * 
 */
@Entity
@Table(name="mat_asinacion")
@NamedQuery(name="AsinacionDTO.findAll", query="SELECT a FROM AsinacionDTO a")
public class AsinacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_ASINACION_ASICODIGO_GENERATOR", sequenceName="MAT_ASINACION_ASI_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_ASINACION_ASICODIGO_GENERATOR")
	@Column(name="asi_codigo")
	private Integer asiCodigo;

	//bi-directional many-to-one association to MateriaDTO
	@ManyToOne
	@JoinColumn(name="asi_materia")
	private MateriaDTO matMateria;

	//bi-directional many-to-one association to NivelParaleloDTO
	@ManyToOne
	@JoinColumn(name="asi_nivel_paralelo")
	private NivelParaleloDTO matNivelParalelo;

	//bi-directional many-to-one association to PeriodoDTO
	@ManyToOne
	@JoinColumn(name="asi_periodo")
	private PeriodoDTO matPeriodo;

	//bi-directional many-to-one association to ProfesorDTO
	@ManyToOne
	@JoinColumn(name="asi_profesor")
	private ProfesorDTO matProfesor;

	//bi-directional many-to-one association to MatriculaDetalleDTO
	@OneToMany(mappedBy="matAsinacion")
	private List<MatriculaDetalleDTO> matMatriculaDetalles;
	
	@Column(name="asi_empresa")
	private Integer asiEmpresa;
	

	public AsinacionDTO() {
	}

	public Integer getAsiCodigo() {
		return this.asiCodigo;
	}

	public void setAsiCodigo(Integer asiCodigo) {
		this.asiCodigo = asiCodigo;
	}

	public MateriaDTO getMatMateria() {
		return this.matMateria;
	}

	public void setMatMateria(MateriaDTO matMateria) {
		this.matMateria = matMateria;
	}

	public NivelParaleloDTO getMatNivelParalelo() {
		return this.matNivelParalelo;
	}

	public void setMatNivelParalelo(NivelParaleloDTO matNivelParalelo) {
		this.matNivelParalelo = matNivelParalelo;
	}

	public PeriodoDTO getMatPeriodo() {
		return this.matPeriodo;
	}

	public void setMatPeriodo(PeriodoDTO matPeriodo) {
		this.matPeriodo = matPeriodo;
	}

	public ProfesorDTO getMatProfesor() {
		return this.matProfesor;
	}

	public void setMatProfesor(ProfesorDTO matProfesor) {
		this.matProfesor = matProfesor;
	}

	public List<MatriculaDetalleDTO> getMatMatriculaDetalles() {
		return this.matMatriculaDetalles;
	}

	public void setMatMatriculaDetalles(List<MatriculaDetalleDTO> matMatriculaDetalles) {
		this.matMatriculaDetalles = matMatriculaDetalles;
	}

	public MatriculaDetalleDTO addMatMatriculaDetalle(MatriculaDetalleDTO matMatriculaDetalle) {
		getMatMatriculaDetalles().add(matMatriculaDetalle);
		matMatriculaDetalle.setMatAsinacion(this);

		return matMatriculaDetalle;
	}

	public MatriculaDetalleDTO removeMatMatriculaDetalle(MatriculaDetalleDTO matMatriculaDetalle) {
		getMatMatriculaDetalles().remove(matMatriculaDetalle);
		matMatriculaDetalle.setMatAsinacion(null);

		return matMatriculaDetalle;
	}

	public Integer getAsiEmpresa() {
		return asiEmpresa;
	}

	public void setAsiEmpresa(Integer asiEmpresa) {
		this.asiEmpresa = asiEmpresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asiEmpresa == null) ? 0 : asiEmpresa.hashCode());
		result = prime * result + ((matNivelParalelo == null) ? 0 : matNivelParalelo.hashCode());
		result = prime * result + ((matPeriodo == null) ? 0 : matPeriodo.hashCode());
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
		AsinacionDTO other = (AsinacionDTO) obj;
		if (asiEmpresa == null) {
			if (other.asiEmpresa != null)
				return false;
		} else if (!asiEmpresa.equals(other.asiEmpresa))
			return false;
		if (matNivelParalelo == null) {
			if (other.matNivelParalelo != null)
				return false;
		} else if (!matNivelParalelo.equals(other.matNivelParalelo))
			return false;
		if (matPeriodo == null) {
			if (other.matPeriodo != null)
				return false;
		} else if (!matPeriodo.equals(other.matPeriodo))
			return false;
		return true;
	}

	
	
}