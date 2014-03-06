package ec.edu.uce.erp.ejb.persistence.entity.matricula;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


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
	@SequenceGenerator(name="MAT_PERIODO_PERCODIGO_GENERATOR", sequenceName="MAT_PERIODO_PER_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_PERIODO_PERCODIGO_GENERATOR")
	@Column(name="per_codigo")
	private Integer perCodigo;

	@Column(name="per_descripcion")
	private String perDescripcion;

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

}