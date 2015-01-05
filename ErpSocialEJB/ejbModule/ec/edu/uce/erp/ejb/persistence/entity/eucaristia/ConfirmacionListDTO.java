package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the euc_confirmacion_vie database table.
 * 
 */
@Entity
@Table(name="euc_confirmacion_vie")
@NamedQuery(name="ConfirmacionListDTO.findAll", query="SELECT c FROM ConfirmacionListDTO c")
public class ConfirmacionListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cat_canton")
	private String catCanton;

	@Column(name="cat_parroquia")
	private String catParroquia;

	@Column(name="cat_provincia")
	private String catProvincia;

	@Column(name="con_acta")
	private String conActa;

	@Column(name="con_canton")
	private Integer conCanton;

	@Column(name="con_certificado_por")
	private Integer conCertificadoPor;

	@Id
	@Column(name="con_codigo")
	private Integer conCodigo;

	@Column(name="con_confirmado")
	private Integer conConfirmado;

	@Column(name="con_estado")
	private Integer conEstado;

	@Column(name="con_fecha")
	@Temporal(TemporalType.DATE)
	private Date conFecha;

	@Column(name="con_fecha_aprobacion_curso")
	@Temporal(TemporalType.DATE)
	private Date conFechaAprobacionCurso;

	@Column(name="con_madre")
	private Integer conMadre;

	@Column(name="con_nota_marginal")
	private String conNotaMarginal;

	@Column(name="con_padre")
	private Integer conPadre;

	@Column(name="con_padrino")
	private Integer conPadrino;

	@Column(name="con_pagina")
	private String conPagina;

	@Column(name="con_parroquia")
	private Integer conParroquia;

	@Column(name="con_provincia")
	private Integer conProvincia;

	@Column(name="con_sacerdote")
	private Integer conSacerdote;

	@Column(name="con_tipo")
	private Integer conTipo;

	@Column(name="con_toma")
	private String conToma;
	
	@Column(name="con_empresa")
	private Integer conEmpresa;

	@Column(name="madre_apellidos")
	private String madreApellidos;

	@Column(name="madre_ci")
	private String madreCi;

	@Column(name="madre_nombres")
	private String madreNombres;

	@Column(name="pad_apellidos")
	private String padApellidos;

	@Column(name="pad_ci")
	private String padCi;

	@Column(name="pad_nombres")
	private String padNombres;

	@Column(name="padre_apellidos")
	private String padreApellidos;

	@Column(name="padre_ci")
	private String padreCi;

	@Column(name="padre_nombres")
	private String padreNombres;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_celular")
	private String perCelular;

	@Column(name="per_ci")
	private String perCi;

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

	@Column(name="sac_apellidos")
	private String sacApellidos;

	@Column(name="sac_ci")
	private String sacCi;

	@Column(name="sac_codigo")
	private Integer sacCodigo;

	@Column(name="sac_nombres")
	private String sacNombres;

	@Column(name="sac_persona")
	private Integer sacPersona;
	
	@Transient
	private Date fechaDesde;
	
	@Transient
	private Date fechaHasta;
	

	public ConfirmacionListDTO() {
	}

	public ConfirmacionListDTO(Integer conParroquia,
			Integer conProvincia,
			Integer conCanton,
			String perCi,
			String perApellidos,
			String perNombres,
			String sacNombres,
			String sacApellidos,
			Date conFecha,
			Date conFechaAprobacionCurso
			) {
			super();
			this.conParroquia=conParroquia;
			this.conProvincia=conProvincia;
			this.conCanton=conCanton;
			this.perCi = perCi;
			this.perApellidos = perApellidos;
			this.perNombres = perNombres;
			this.sacNombres = sacNombres;
			this.sacApellidos = sacApellidos;
			this.conFecha=conFecha;
			this.conFechaAprobacionCurso=conFechaAprobacionCurso;
	}
	
	public String getCatCanton() {
		return this.catCanton;
	}

	public void setCatCanton(String catCanton) {
		this.catCanton = catCanton;
	}

	public String getCatParroquia() {
		return this.catParroquia;
	}

	public void setCatParroquia(String catParroquia) {
		this.catParroquia = catParroquia;
	}

	public String getCatProvincia() {
		return this.catProvincia;
	}

	public void setCatProvincia(String catProvincia) {
		this.catProvincia = catProvincia;
	}

	public String getConActa() {
		return this.conActa;
	}

	public void setConActa(String conActa) {
		this.conActa = conActa;
	}

	public Integer getConCanton() {
		return this.conCanton;
	}

	public void setConCanton(Integer conCanton) {
		this.conCanton = conCanton;
	}

	public Integer getConCertificadoPor() {
		return this.conCertificadoPor;
	}

	public void setConCertificadoPor(Integer conCertificadoPor) {
		this.conCertificadoPor = conCertificadoPor;
	}

	public Integer getConCodigo() {
		return this.conCodigo;
	}

	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
	}

	public Integer getConConfirmado() {
		return this.conConfirmado;
	}

	public void setConConfirmado(Integer conConfirmado) {
		this.conConfirmado = conConfirmado;
	}

	public Integer getConEstado() {
		return this.conEstado;
	}

	public void setConEstado(Integer conEstado) {
		this.conEstado = conEstado;
	}

	public Date getConFecha() {
		return this.conFecha;
	}

	public void setConFecha(Date conFecha) {
		this.conFecha = conFecha;
	}

	public Date getConFechaAprobacionCurso() {
		return this.conFechaAprobacionCurso;
	}

	public void setConFechaAprobacionCurso(Date conFechaAprobacionCurso) {
		this.conFechaAprobacionCurso = conFechaAprobacionCurso;
	}

	public Integer getConMadre() {
		return this.conMadre;
	}

	public void setConMadre(Integer conMadre) {
		this.conMadre = conMadre;
	}

	public String getConNotaMarginal() {
		return this.conNotaMarginal;
	}

	public void setConNotaMarginal(String conNotaMarginal) {
		this.conNotaMarginal = conNotaMarginal;
	}

	public Integer getConPadre() {
		return this.conPadre;
	}

	public void setConPadre(Integer conPadre) {
		this.conPadre = conPadre;
	}

	public Integer getConPadrino() {
		return this.conPadrino;
	}

	public void setConPadrino(Integer conPadrino) {
		this.conPadrino = conPadrino;
	}

	public String getConPagina() {
		return this.conPagina;
	}

	public void setConPagina(String conPagina) {
		this.conPagina = conPagina;
	}

	public Integer getConParroquia() {
		return this.conParroquia;
	}

	public void setConParroquia(Integer conParroquia) {
		this.conParroquia = conParroquia;
	}

	public Integer getConProvincia() {
		return this.conProvincia;
	}

	public void setConProvincia(Integer conProvincia) {
		this.conProvincia = conProvincia;
	}

	public Integer getConSacerdote() {
		return this.conSacerdote;
	}

	public void setConSacerdote(Integer conSacerdote) {
		this.conSacerdote = conSacerdote;
	}

	public Integer getConTipo() {
		return this.conTipo;
	}

	public void setConTipo(Integer conTipo) {
		this.conTipo = conTipo;
	}

	public String getConToma() {
		return this.conToma;
	}

	public void setConToma(String conToma) {
		this.conToma = conToma;
	}
	
	public Integer getConEmpresa() {
		return this.conEmpresa;
	}

	public void setConEmpresa(Integer conEmpresa) {
		this.conEmpresa = conEmpresa;
	}

	public String getMadreApellidos() {
		return this.madreApellidos;
	}

	public void setMadreApellidos(String madreApellidos) {
		this.madreApellidos = madreApellidos;
	}

	public String getMadreCi() {
		return this.madreCi;
	}

	public void setMadreCi(String madreCi) {
		this.madreCi = madreCi;
	}

	public String getMadreNombres() {
		return this.madreNombres;
	}

	public void setMadreNombres(String madreNombres) {
		this.madreNombres = madreNombres;
	}

	public String getPadApellidos() {
		return this.padApellidos;
	}

	public void setPadApellidos(String padApellidos) {
		this.padApellidos = padApellidos;
	}

	public String getPadCi() {
		return this.padCi;
	}

	public void setPadCi(String padCi) {
		this.padCi = padCi;
	}

	public String getPadNombres() {
		return this.padNombres;
	}

	public void setPadNombres(String padNombres) {
		this.padNombres = padNombres;
	}

	public String getPadreApellidos() {
		return this.padreApellidos;
	}

	public void setPadreApellidos(String padreApellidos) {
		this.padreApellidos = padreApellidos;
	}

	public String getPadreCi() {
		return this.padreCi;
	}

	public void setPadreCi(String padreCi) {
		this.padreCi = padreCi;
	}

	public String getPadreNombres() {
		return this.padreNombres;
	}

	public void setPadreNombres(String padreNombres) {
		this.padreNombres = padreNombres;
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

	public String getSacApellidos() {
		return this.sacApellidos;
	}

	public void setSacApellidos(String sacApellidos) {
		this.sacApellidos = sacApellidos;
	}

	public String getSacCi() {
		return this.sacCi;
	}

	public void setSacCi(String sacCi) {
		this.sacCi = sacCi;
	}

	public Integer getSacCodigo() {
		return this.sacCodigo;
	}

	public void setSacCodigo(Integer sacCodigo) {
		this.sacCodigo = sacCodigo;
	}

	public String getSacNombres() {
		return this.sacNombres;
	}

	public void setSacNombres(String sacNombres) {
		this.sacNombres = sacNombres;
	}

	public Integer getSacPersona() {
		return this.sacPersona;
	}

	public void setSacPersona(Integer sacPersona) {
		this.sacPersona = sacPersona;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}