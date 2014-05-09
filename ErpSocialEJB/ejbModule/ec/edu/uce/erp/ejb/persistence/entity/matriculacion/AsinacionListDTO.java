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
@NamedQuery(name="AsinacionListDTO.findAll", query="SELECT m FROM AsinacionListDTO m")
public class AsinacionListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="asi_codigo")
	private Integer asiCodigo;

	@Column(name="asi_materia")
	private Integer asiMateria;

	@Column(name="asi_nivel_paralelo")
	private Integer asiNivelParalelo;

	@Column(name="asi_periodo")
	private Integer asiPeriodo;

	@Column(name="asi_profesor")
	private Integer asiProfesor;

	@Column(name="cab_catalogo_est_fk")
	private String cabCatalogoEstFk;

	@Column(name="cab_catalogo_fun_fk")
	private String cabCatalogoFunFk;

	@Column(name="cab_catalogo_gen_fk")
	private String cabCatalogoGenFk;

	@Column(name="cab_catalogo_nes_fk")
	private String cabCatalogoNesFk;

	@Column(name="cab_catalogo_pro_fk")
	private String cabCatalogoProFk;

	@Column(name="cab_catalogo_tip_fk")
	private String cabCatalogoTipFk;

	@Column(name="cab_ubicacion_fk")
	private String cabUbicacionFk;

	@Column(name="det_catalogo_est_nivel1_fk")
	private String detCatalogoEstNivel1Fk;

	@Column(name="det_catalogo_fun_nivel1_fk")
	private String detCatalogoFunNivel1Fk;

	@Column(name="det_catalogo_gen_nivel1_fk")
	private String detCatalogoGenNivel1Fk;

	@Column(name="det_catalogo_nes_nivel1_fk")
	private String detCatalogoNesNivel1Fk;

	@Column(name="det_catalogo_pro_nivel1_fk")
	private String detCatalogoProNivel1Fk;

	@Column(name="det_catalogo_tip_nivel1_fk")
	private String detCatalogoTipNivel1Fk;

	@Column(name="det_ubicacion_nivel1_fk")
	private String detUbicacionNivel1Fk;

	@Column(name="det_ubicacion_nivel2_fk")
	private String detUbicacionNivel2Fk;

	@Column(name="det_ubicacion_nivel3_fk")
	private String detUbicacionNivel3Fk;

	@Column(name="det_ubicacion_nivel4_fk")
	private String detUbicacionNivel4Fk;

	@Column(name="id_usuario")
	private Integer idUsuario;

	@Column(name="mtr_codigo")
	private Integer mtrCodigo;

	@Column(name="mtr_nombe")
	private String mtrNombe;

	@Column(name="npa_codigo")
	private Integer npaCodigo;

	@Column(name="npa_nivel")
	private Integer npaNivel;

	@Column(name="npa_paralelo")
	private Integer npaParalelo;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_celular")
	private String perCelular;

	@Column(name="per_ci")
	private String perCi;
@Id
	@Column(name="per_codigo")
	private Integer perCodigo;

	@Column(name="per_descripcion")
	private String perDescripcion;

	@Column(name="per_direccion")
	private String perDireccion;

	@Column(name="per_email")
	private String perEmail;

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

	public String getCabCatalogoEstFk() {
		return this.cabCatalogoEstFk;
	}

	public void setCabCatalogoEstFk(String cabCatalogoEstFk) {
		this.cabCatalogoEstFk = cabCatalogoEstFk;
	}

	public String getCabCatalogoFunFk() {
		return this.cabCatalogoFunFk;
	}

	public void setCabCatalogoFunFk(String cabCatalogoFunFk) {
		this.cabCatalogoFunFk = cabCatalogoFunFk;
	}

	public String getCabCatalogoGenFk() {
		return this.cabCatalogoGenFk;
	}

	public void setCabCatalogoGenFk(String cabCatalogoGenFk) {
		this.cabCatalogoGenFk = cabCatalogoGenFk;
	}

	public String getCabCatalogoNesFk() {
		return this.cabCatalogoNesFk;
	}

	public void setCabCatalogoNesFk(String cabCatalogoNesFk) {
		this.cabCatalogoNesFk = cabCatalogoNesFk;
	}

	public String getCabCatalogoProFk() {
		return this.cabCatalogoProFk;
	}

	public void setCabCatalogoProFk(String cabCatalogoProFk) {
		this.cabCatalogoProFk = cabCatalogoProFk;
	}

	public String getCabCatalogoTipFk() {
		return this.cabCatalogoTipFk;
	}

	public void setCabCatalogoTipFk(String cabCatalogoTipFk) {
		this.cabCatalogoTipFk = cabCatalogoTipFk;
	}

	public String getCabUbicacionFk() {
		return this.cabUbicacionFk;
	}

	public void setCabUbicacionFk(String cabUbicacionFk) {
		this.cabUbicacionFk = cabUbicacionFk;
	}

	public String getDetCatalogoEstNivel1Fk() {
		return this.detCatalogoEstNivel1Fk;
	}

	public void setDetCatalogoEstNivel1Fk(String detCatalogoEstNivel1Fk) {
		this.detCatalogoEstNivel1Fk = detCatalogoEstNivel1Fk;
	}

	public String getDetCatalogoFunNivel1Fk() {
		return this.detCatalogoFunNivel1Fk;
	}

	public void setDetCatalogoFunNivel1Fk(String detCatalogoFunNivel1Fk) {
		this.detCatalogoFunNivel1Fk = detCatalogoFunNivel1Fk;
	}

	public String getDetCatalogoGenNivel1Fk() {
		return this.detCatalogoGenNivel1Fk;
	}

	public void setDetCatalogoGenNivel1Fk(String detCatalogoGenNivel1Fk) {
		this.detCatalogoGenNivel1Fk = detCatalogoGenNivel1Fk;
	}

	public String getDetCatalogoNesNivel1Fk() {
		return this.detCatalogoNesNivel1Fk;
	}

	public void setDetCatalogoNesNivel1Fk(String detCatalogoNesNivel1Fk) {
		this.detCatalogoNesNivel1Fk = detCatalogoNesNivel1Fk;
	}

	public String getDetCatalogoProNivel1Fk() {
		return this.detCatalogoProNivel1Fk;
	}

	public void setDetCatalogoProNivel1Fk(String detCatalogoProNivel1Fk) {
		this.detCatalogoProNivel1Fk = detCatalogoProNivel1Fk;
	}

	public String getDetCatalogoTipNivel1Fk() {
		return this.detCatalogoTipNivel1Fk;
	}

	public void setDetCatalogoTipNivel1Fk(String detCatalogoTipNivel1Fk) {
		this.detCatalogoTipNivel1Fk = detCatalogoTipNivel1Fk;
	}

	public String getDetUbicacionNivel1Fk() {
		return this.detUbicacionNivel1Fk;
	}

	public void setDetUbicacionNivel1Fk(String detUbicacionNivel1Fk) {
		this.detUbicacionNivel1Fk = detUbicacionNivel1Fk;
	}

	public String getDetUbicacionNivel2Fk() {
		return this.detUbicacionNivel2Fk;
	}

	public void setDetUbicacionNivel2Fk(String detUbicacionNivel2Fk) {
		this.detUbicacionNivel2Fk = detUbicacionNivel2Fk;
	}

	public String getDetUbicacionNivel3Fk() {
		return this.detUbicacionNivel3Fk;
	}

	public void setDetUbicacionNivel3Fk(String detUbicacionNivel3Fk) {
		this.detUbicacionNivel3Fk = detUbicacionNivel3Fk;
	}

	public String getDetUbicacionNivel4Fk() {
		return this.detUbicacionNivel4Fk;
	}

	public void setDetUbicacionNivel4Fk(String detUbicacionNivel4Fk) {
		this.detUbicacionNivel4Fk = detUbicacionNivel4Fk;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getMtrCodigo() {
		return this.mtrCodigo;
	}

	public void setMtrCodigo(Integer mtrCodigo) {
		this.mtrCodigo = mtrCodigo;
	}

	public String getMtrNombe() {
		return this.mtrNombe;
	}

	public void setMtrNombe(String mtrNombe) {
		this.mtrNombe = mtrNombe;
	}

	public Integer getNpaCodigo() {
		return this.npaCodigo;
	}

	public void setNpaCodigo(Integer npaCodigo) {
		this.npaCodigo = npaCodigo;
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

	public Integer getProPersona() {
		return this.proPersona;
	}

	public void setProPersona(Integer proPersona) {
		this.proPersona = proPersona;
	}

}