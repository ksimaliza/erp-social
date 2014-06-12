package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the mat_matricula database table.
 * 
 */
@Entity
@Table(name="mat_matricula")
@NamedQuery(name="MatriculaDTO.findAll", query="SELECT m FROM MatriculaDTO m")
public class MatriculaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_MATRICULA_REGCODIGO_GENERATOR", sequenceName="MAT_MATRICULA_REG_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_MATRICULA_REGCODIGO_GENERATOR")
	@Column(name="reg_codigo")
	private Integer regCodigo;

	@Column(name="reg_fecha")
	private Timestamp regFecha;

	@Column(name="reg_foto")
	private String regFoto;

	@Column(name="reg_foto_byte")
	private byte[] regFotoByte;

	//bi-directional many-to-one association to EstudianteDTO
	@ManyToOne
	@JoinColumn(name="reg_estudiante")
	private EstudianteDTO regEstudiante;

	//bi-directional many-to-one association to MatriculaDetalleDTO
	@OneToMany(mappedBy="matMatriculaBean")
	private List<MatriculaDetalleDTO> matMatriculaDetalles;

	public MatriculaDTO() {
	}

	public Integer getRegCodigo() {
		return this.regCodigo;
	}

	public void setRegCodigo(Integer regCodigo) {
		this.regCodigo = regCodigo;
	}

	public Timestamp getRegFecha() {
		return this.regFecha;
	}

	public void setRegFecha(Timestamp regFecha) {
		this.regFecha = regFecha;
	}

	public String getRegFoto() {
		return regFoto;
	}

	public void setRegFoto(String regFoto) {
		this.regFoto = regFoto;
	}

	public byte[] getRegFotoByte() {
		return regFotoByte;
	}

	public void setRegFotoByte(byte[] regFotoByte) {
		this.regFotoByte = regFotoByte;
	}

	public EstudianteDTO getMatEstudiante() {
		return this.regEstudiante;
	}

	public void setMatEstudiante(EstudianteDTO matEstudiante) {
		this.regEstudiante = matEstudiante;
	}

	public List<MatriculaDetalleDTO> getMatMatriculaDetalles() {
		return this.matMatriculaDetalles;
	}

	public void setMatMatriculaDetalles(List<MatriculaDetalleDTO> matMatriculaDetalles) {
		this.matMatriculaDetalles = matMatriculaDetalles;
	}

	public MatriculaDetalleDTO addMatMatriculaDetalle(MatriculaDetalleDTO matMatriculaDetalle) {
		getMatMatriculaDetalles().add(matMatriculaDetalle);
		matMatriculaDetalle.setMatMatriculaBean(this);

		return matMatriculaDetalle;
	}

	public MatriculaDetalleDTO removeMatMatriculaDetalle(MatriculaDetalleDTO matMatriculaDetalle) {
		getMatMatriculaDetalles().remove(matMatriculaDetalle);
		matMatriculaDetalle.setMatMatriculaBean(null);

		return matMatriculaDetalle;
	}

}