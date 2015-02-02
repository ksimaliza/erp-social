package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.edu.uce.erp.common.util.UtilAplication;


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
	
	@Column(name="reg_empresa")
	private Integer regEmpresa;
	
	@Transient
	private Boolean regVerificarFoto;
	
	@Transient
	private InputStream fotoStream;

	//bi-directional many-to-one association to EstudianteDTO
	@ManyToOne
	@JoinColumn(name="reg_estudiante")
	private EstudianteDTO regEstudiante;

	//bi-directional many-to-one association to MatriculaDetalleDTO
	@OneToMany(mappedBy="matMatriculaBean")
	private List<MatriculaDetalleDTO> matMatriculaDetalles;
	
	// bi-directional many-to-one association to NotaDTO
	@OneToMany(mappedBy = "matriculaBean")
	private List<NotaTutorDTO> notNotasTutor;

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

	public Integer getRegEmpresa() {
		return regEmpresa;
	}

	public void setRegEmpresa(Integer regEmpresa) {
		this.regEmpresa = regEmpresa;
	}

	public Boolean getRegVerificarFoto() {
		UtilAplication.saveToDisk(this.regFotoByte, this.regFoto);
		return regVerificarFoto;
	}

	public void setRegVerificarFoto(Boolean regVerificarFoto) {
		this.regVerificarFoto = regVerificarFoto;
	}

	public List<NotaTutorDTO> getNotNotasTutor() {
		return notNotasTutor;
	}

	public void setNotNotasTutor(List<NotaTutorDTO> notNotasTutor) {
		this.notNotasTutor = notNotasTutor;
	}
	
	public InputStream getFotoStream() {
		if(this.getRegFotoByte()!=null)
			this.fotoStream=new ByteArrayInputStream(this.getRegFotoByte());
		return fotoStream;
	}

	public void setFotoStream(InputStream fotoStream) {
		this.fotoStream = fotoStream;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((regCodigo == null) ? 0 : regCodigo.hashCode());
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
		MatriculaDTO other = (MatriculaDTO) obj;
		if (regCodigo == null) {
			if (other.regCodigo != null)
				return false;
		} else if (!regCodigo.equals(other.regCodigo))
			return false;
		return true;
	}
	
	

}