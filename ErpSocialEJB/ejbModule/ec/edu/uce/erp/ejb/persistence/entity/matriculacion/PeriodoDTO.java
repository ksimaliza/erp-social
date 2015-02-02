package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;
import java.util.Date;


/**
 * The persistent class for the mat_periodo database table.
 * 
 */
@Entity
@Table(name="mat_periodo")
@NamedQuery(name="PeriodoDTO.findAll", query="SELECT p FROM PeriodoDTO p")
public class PeriodoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_PERIODO_PERCODIGO_GENERATOR", sequenceName="MAT_PERIODO_PER_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_PERIODO_PERCODIGO_GENERATOR")
	@Column(name="per_codigo")
	private Integer perCodigo;

	@Column(name="per_descripcion")
	private String perDescripcion;
	
	@Column(name="per_empresa")
	private Integer perEmpresa;
	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha_inicio")
	private Date perFechaInicio;
	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha_final")
	private Date perFechaFinal;

	//bi-directional many-to-one association to AsinacionDTO
	@OneToMany(mappedBy="matPeriodo")
	private List<AsinacionDTO> matAsinacions;

	public PeriodoDTO() {
	}

	public Integer getPerCodigo() {
		return this.perCodigo;
	}

	public void setPerCodigo(Integer perCodigo) {
		this.perCodigo = perCodigo;
	}

	public String getPerDescripcion() {
		return this.perDescripcion;
	}

	public void setPerDescripcion(String perDescripcion) {
		this.perDescripcion = perDescripcion;
	}

	public List<AsinacionDTO> getMatAsinacions() {
		return this.matAsinacions;
	}

	public void setMatAsinacions(List<AsinacionDTO> matAsinacions) {
		this.matAsinacions = matAsinacions;
	}

	public AsinacionDTO addMatAsinacion(AsinacionDTO matAsinacion) {
		getMatAsinacions().add(matAsinacion);
		matAsinacion.setMatPeriodo(this);

		return matAsinacion;
	}

	public AsinacionDTO removeMatAsinacion(AsinacionDTO matAsinacion) {
		getMatAsinacions().remove(matAsinacion);
		matAsinacion.setMatPeriodo(null);

		return matAsinacion;
	}

	public Integer getPerEmpresa() {
		return perEmpresa;
	}

	public void setPerEmpresa(Integer perEmpresa) {
		this.perEmpresa = perEmpresa;
	}
	
	public Date getPerFechaInicio() {
		return perFechaInicio;
	}

	public void setPerFechaInicio(Date perFechaInicio) {
		this.perFechaInicio = perFechaInicio;
	}

	public Date getPerFechaFinal() {
		return perFechaFinal;
	}

	public void setPerFechaFinal(Date perFechaFinal) {
		this.perFechaFinal = perFechaFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perCodigo == null) ? 0 : perCodigo.hashCode());
		result = prime * result + ((perEmpresa == null) ? 0 : perEmpresa.hashCode());
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
		PeriodoDTO other = (PeriodoDTO) obj;
		if (perCodigo == null) {
			if (other.perCodigo != null)
				return false;
		} else if (!perCodigo.equals(other.perCodigo))
			return false;
		if (perEmpresa == null) {
			if (other.perEmpresa != null)
				return false;
		} else if (!perEmpresa.equals(other.perEmpresa))
			return false;
		return true;
	}



}