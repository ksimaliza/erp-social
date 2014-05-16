package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the mat_matricula_detalle database table.
 * 
 */
@Entity
@Table(name="mat_matricula_detalle")
@NamedQuery(name="MatriculaDetalleDTO.findAll", query="SELECT m FROM MatriculaDetalleDTO m")
public class MatriculaDetalleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_MATRICULA_DETALLE_MATCODIGO_GENERATOR", sequenceName="MAT_MATRICULA_DETALLE_MAT_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_MATRICULA_DETALLE_MATCODIGO_GENERATOR")
	@Column(name="mat_codigo")
	private Integer matCodigo;

	//bi-directional many-to-one association to AsinacionDTO
	@ManyToOne
	@JoinColumn(name="mat_asignacion")
	private AsinacionDTO matAsinacion;

	//bi-directional many-to-one association to MatriculaDTO
	@ManyToOne
	@JoinColumn(name="mat_matricula")
	private MatriculaDTO matMatriculaBean;

	
	@OneToMany(mappedBy="matMatriculaDetalleBean")
	private List<NotaDTO> notNotas;

	
	public MatriculaDetalleDTO() {
	}

	public Integer getMatCodigo() {
		return this.matCodigo;
	}

	public void setMatCodigo(Integer matCodigo) {
		this.matCodigo = matCodigo;
	}

	public AsinacionDTO getMatAsinacion() {
		return this.matAsinacion;
	}

	public void setMatAsinacion(AsinacionDTO matAsinacion) {
		this.matAsinacion = matAsinacion;
	}

	public MatriculaDTO getMatMatriculaBean() {
		return this.matMatriculaBean;
	}

	public void setMatMatriculaBean(MatriculaDTO matMatriculaBean) {
		this.matMatriculaBean = matMatriculaBean;
	}

	public List<NotaDTO> getNotNotas() {
		return notNotas;
	}

	public void setNotNotas(List<NotaDTO> notNotas) {
		this.notNotas = notNotas;
	}

}