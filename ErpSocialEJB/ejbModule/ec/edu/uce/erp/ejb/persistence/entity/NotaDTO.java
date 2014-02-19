package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the not_nota database table.
 * 
 */
@Entity
@Table(name="not_nota")
@NamedQuery(name="NotaDTO.findAll", query="SELECT n FROM NotaDTO n")
public class NotaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NOT_NOTA_NOTCODIGO_GENERATOR", sequenceName="NOT_NOTA_NOT_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOT_NOTA_NOTCODIGO_GENERATOR")
	@Column(name="not_codigo")
	private Integer notCodigo;

	@Column(name="not_materia")
	private String notMateria;

	@Column(name="not_matricula")
	private Integer notMatricula;

	@Column(name="not_observacion")
	private String notObservacion;

	@Column(name="not_valor")
	private String notValor;

	//bi-directional many-to-one association to ParcialDTO
	@ManyToOne
	@JoinColumn(name="not_parcial")
	private ParcialDTO notParcialBean;

	@ManyToOne
	@JoinColumn(name="not_matricula")
	private MatriculaDetalleDTO matMatriculaDetalleBean;

	
	public NotaDTO() {
	}

	public Integer getNotCodigo() {
		return this.notCodigo;
	}

	public void setNotCodigo(Integer notCodigo) {
		this.notCodigo = notCodigo;
	}

	public String getNotMateria() {
		return this.notMateria;
	}

	public void setNotMateria(String notMateria) {
		this.notMateria = notMateria;
	}

	public Integer getNotMatricula() {
		return this.notMatricula;
	}

	public void setNotMatricula(Integer notMatricula) {
		this.notMatricula = notMatricula;
	}

	public String getNotObservacion() {
		return this.notObservacion;
	}

	public void setNotObservacion(String notObservacion) {
		this.notObservacion = notObservacion;
	}

	public String getNotValor() {
		return this.notValor;
	}

	public void setNotValor(String notValor) {
		this.notValor = notValor;
	}

	public ParcialDTO getNotParcialBean() {
		return this.notParcialBean;
	}

	public void setNotParcialBean(ParcialDTO notParcialBean) {
		this.notParcialBean = notParcialBean;
	}

	public MatriculaDetalleDTO getMatMatriculaDetalleBean() {
		return matMatriculaDetalleBean;
	}

	public void setMatMatriculaDetalleBean(
			MatriculaDetalleDTO matMatriculaDetalleBean) {
		this.matMatriculaDetalleBean = matMatriculaDetalleBean;
	}

}