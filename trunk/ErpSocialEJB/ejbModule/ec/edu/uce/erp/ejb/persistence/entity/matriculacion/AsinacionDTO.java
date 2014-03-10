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
	@SequenceGenerator(name="MAT_ASINACION_ASICODIGO_GENERATOR", sequenceName="MAT_ASINACION_ASI_CODIGO_SEQ")
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

}