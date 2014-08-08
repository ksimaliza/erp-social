package ec.edu.uce.erp.ejb.persistence.entity.matriculacion;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mat_asinacion_vie database table.
 * 
 */
@Entity
@Table(name="mat_asinacion_vie")
@NamedQuery(name="AsinacionListDTO.findAll", query="SELECT a FROM AsinacionListDTO a")
public class AsinacionListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="asi_codigo")
	private Integer asiCodigo;

	@Column(name="asi_empresa")
	private Integer asiEmpresa;

	@Column(name="asi_materia")
	private Integer asiMateria;

	@Column(name="asi_nivel_paralelo")
	private Integer asiNivelParalelo;

	@Column(name="asi_periodo")
	private Integer asiPeriodo;

	@Column(name="asi_profesor")
	private Integer asiProfesor;

	@Column(name="mtr_codigo")
	private Integer mtrCodigo;

	@Column(name="mtr_empresa")
	private Integer mtrEmpresa;

	@Column(name="mtr_nombe")
	private String mtrNombe;

	@Column(name="niv_codigo")
	private Integer nivCodigo;

	@Column(name="niv_descaripcion")
	private String nivDescaripcion;

	@Column(name="niv_empresa")
	private Integer nivEmpresa;

	@Column(name="npa_codigo")
	private Integer npaCodigo;

	@Column(name="npa_empresa")
	private Integer npaEmpresa;

	@Column(name="npa_nivel")
	private Integer npaNivel;

	@Column(name="npa_paralelo")
	private Integer npaParalelo;

	@Column(name="par_codigo")
	private Integer parCodigo;

	@Column(name="par_descripcion")
	private String parDescripcion;

	@Column(name="par_empresa")
	private Integer parEmpresa;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_celular")
	private String perCelular;

	@Column(name="per_ci")
	private String perCi;

	@Column(name="per_codigo")
	private Integer perCodigo;

	@Column(name="per_descripcion")
	private String perDescripcion;

	@Column(name="per_direccion")
	private String perDireccion;

	@Column(name="per_email")
	private String perEmail;

	@Column(name="per_empresa")
	private Integer perEmpresa;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha_nac")
	private Date perFechaNac;

	@Column(name="per_foto")
	private String perFoto;

	@Column(name="per_nombres")
	private String perNombres;

	@Column(name="per_pk")
	private Integer perPk;

	@Column(name="per_telefono")
	private String perTelefono;

	@Column(name="pro_codigo")
	private Integer proCodigo;

	@Column(name="pro_empresa")
	private Integer proEmpresa;

	@Column(name="pro_persona")
	private Integer proPersona;

	public AsinacionListDTO() {
	}

	public Integer getAsiCodigo() {
		return this.asiCodigo;
	}

	public void setAsiCodigo(Integer asiCodigo) {
		this.asiCodigo = asiCodigo;
	}

	public Integer getAsiEmpresa() {
		return this.asiEmpresa;
	}

	public void setAsiEmpresa(Integer asiEmpresa) {
		this.asiEmpresa = asiEmpresa;
	}

	public Integer getAsiMateria() {
		return this.asiMateria;
	}

	public void setAsiMateria(Integer asiMateria) {
		this.asiMateria = asiMateria;
	}

	public Integer getAsiNivelParalelo() {
		return this.asiNivelParalelo;
	}

	public void setAsiNivelParalelo(Integer asiNivelParalelo) {
		this.asiNivelParalelo = asiNivelParalelo;
	}

	public Integer getAsiPeriodo() {
		return this.asiPeriodo;
	}

	public void setAsiPeriodo(Integer asiPeriodo) {
		this.asiPeriodo = asiPeriodo;
	}

	public Integer getAsiProfesor() {
		return this.asiProfesor;
	}

	public void setAsiProfesor(Integer asiProfesor) {
		this.asiProfesor = asiProfesor;
	}

	public Integer getMtrCodigo() {
		return this.mtrCodigo;
	}

	public void setMtrCodigo(Integer mtrCodigo) {
		this.mtrCodigo = mtrCodigo;
	}

	public Integer getMtrEmpresa() {
		return this.mtrEmpresa;
	}

	public void setMtrEmpresa(Integer mtrEmpresa) {
		this.mtrEmpresa = mtrEmpresa;
	}

	public String getMtrNombe() {
		return this.mtrNombe;
	}

	public void setMtrNombe(String mtrNombe) {
		this.mtrNombe = mtrNombe;
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

	public Integer getNivEmpresa() {
		return this.nivEmpresa;
	}

	public void setNivEmpresa(Integer nivEmpresa) {
		this.nivEmpresa = nivEmpresa;
	}

	public Integer getNpaCodigo() {
		return this.npaCodigo;
	}

	public void setNpaCodigo(Integer npaCodigo) {
		this.npaCodigo = npaCodigo;
	}

	public Integer getNpaEmpresa() {
		return this.npaEmpresa;
	}

	public void setNpaEmpresa(Integer npaEmpresa) {
		this.npaEmpresa = npaEmpresa;
	}

	public Integer getNpaNivel() {
		return this.npaNivel;
	}

	public void setNpaNivel(Integer npaNivel) {
		this.npaNivel = npaNivel;
	}

	public Integer getNpaParalelo() {
		return this.npaParalelo;
	}

	public void setNpaParalelo(Integer npaParalelo) {
		this.npaParalelo = npaParalelo;
	}

	public Integer getParCodigo() {
		return this.parCodigo;
	}

	public void setParCodigo(Integer parCodigo) {
		this.parCodigo = parCodigo;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public Integer getParEmpresa() {
		return this.parEmpresa;
	}

	public void setParEmpresa(Integer parEmpresa) {
		this.parEmpresa = parEmpresa;
	}

	public String getPerApellidos() {
		return this.perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerCelular() {
		return this.perCelular;
	}

	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	public String getPerCi() {
		return this.perCi;
	}

	public void setPerCi(String perCi) {
		this.perCi = perCi;
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

	public String getPerDireccion() {
		return this.perDireccion;
	}

	public void setPerDireccion(String perDireccion) {
		this.perDireccion = perDireccion;
	}

	public String getPerEmail() {
		return this.perEmail;
	}

	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}

	public Integer getPerEmpresa() {
		return this.perEmpresa;
	}

	public void setPerEmpresa(Integer perEmpresa) {
		this.perEmpresa = perEmpresa;
	}

	public Date getPerFechaNac() {
		return this.perFechaNac;
	}

	public void setPerFechaNac(Date perFechaNac) {
		this.perFechaNac = perFechaNac;
	}

	public String getPerFoto() {
		return this.perFoto;
	}

	public void setPerFoto(String perFoto) {
		this.perFoto = perFoto;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public Integer getPerPk() {
		return this.perPk;
	}

	public void setPerPk(Integer perPk) {
		this.perPk = perPk;
	}

	public String getPerTelefono() {
		return this.perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	public Integer getProCodigo() {
		return this.proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}

	public Integer getProEmpresa() {
		return this.proEmpresa;
	}

	public void setProEmpresa(Integer proEmpresa) {
		this.proEmpresa = proEmpresa;
	}

	public Integer getProPersona() {
		return this.proPersona;
	}

	public void setProPersona(Integer proPersona) {
		this.proPersona = proPersona;
	}

}