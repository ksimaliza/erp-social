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
 * The persistent class for the euc_bautizo_vie database table.
 * 
 */
@Entity
@Table(name="euc_bautizo_vie")
@NamedQuery(name="BautizoListDTO.findAll", query="SELECT b FROM BautizoListDTO b")
public class BautizoListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="bau_acta")
	private String bauActa;

	@Column(name="bau_bautizado")
	private Integer bauBautizado;

	@Column(name="bau_canton")
	private Integer bauCanton;

	@Column(name="bau_certificado_por")
	private Integer bauCertificadoPor;

	@Id
	@Column(name="bau_codigo")
	private Integer bauCodigo;

	@Column(name="bau_estado")
	private Integer bauEstado;

	@Column(name="bau_fecha_aprobacion_cruso")
	@Temporal(TemporalType.DATE)
	private Date bauFechaAprobacionCruso;
	
	@Column(name="bau_fecha_bautizo")
	@Temporal(TemporalType.DATE)
	private Date bauFechaBautizo;

	@Column(name="bau_madre")
	private Integer bauMadre;

	@Column(name="bau_madrina")
	private Integer bauMadrina;

	@Column(name="bau_nota_marginal")
	private String bauNotaMarginal;

	@Column(name="bau_padre")
	private Integer bauPadre;

	@Column(name="bau_padrino")
	private Integer bauPadrino;

	@Column(name="bau_pagina")
	private String bauPagina;

	@Column(name="bau_parroquia")
	private Integer bauParroquia;

	@Column(name="bau_provincia")
	private Integer bauProvincia;

	@Column(name="bau_sacerdote")
	private Integer bauSacerdote;

	@Column(name="bau_toma")
	private String bauToma;
	
	@Column(name="bau_empresa")
	private Integer bauEmpresa;

	@Column(name="cat_canton")
	private String catCanton;

	@Column(name="cat_parroquia")
	private String catParroquia;

	@Column(name="cat_provincia")
	private String catProvincia;

	@Column(name="mad_apellidos")
	private String madApellidos;

	@Column(name="mad_ci")
	private String madCi;

	@Column(name="mad_nombres")
	private String madNombres;

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

	public BautizoListDTO() {
	}

	public BautizoListDTO(
			String perCi,
			String perApellidos,
			String perNombres,
			String sacNombres,
			String sacApellidos,
			Date bauFechaAprobacionCruso,
			Date bauFechaBautizo) {
			super();
			this.perCi = perCi;
			this.perApellidos = perApellidos;
			this.perNombres = perNombres;
			this.sacNombres = sacNombres;
			this.sacApellidos = sacApellidos;
			this.bauFechaAprobacionCruso=bauFechaAprobacionCruso;
			this.bauFechaBautizo=bauFechaBautizo;
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
	
	public String getBauActa() {
		return this.bauActa;
	}

	public void setBauActa(String bauActa) {
		this.bauActa = bauActa;
	}

	public Integer getBauBautizado() {
		return this.bauBautizado;
	}

	public void setBauBautizado(Integer bauBautizado) {
		this.bauBautizado = bauBautizado;
	}

	public Integer getBauCanton() {
		return this.bauCanton;
	}

	public void setBauCanton(Integer bauCanton) {
		this.bauCanton = bauCanton;
	}

	public Integer getBauCertificadoPor() {
		return this.bauCertificadoPor;
	}

	public void setBauCertificadoPor(Integer bauCertificadoPor) {
		this.bauCertificadoPor = bauCertificadoPor;
	}

	public Integer getBauCodigo() {
		return this.bauCodigo;
	}

	public void setBauCodigo(Integer bauCodigo) {
		this.bauCodigo = bauCodigo;
	}

	public Integer getBauEstado() {
		return this.bauEstado;
	}

	public void setBauEstado(Integer bauEstado) {
		this.bauEstado = bauEstado;
	}

	public Date getBauFechaAprobacionCruso() {
		return this.bauFechaAprobacionCruso;
	}

	public void setBauFechaAprobacionCruso(Date bauFechaAprobacionCruso) {
		this.bauFechaAprobacionCruso = bauFechaAprobacionCruso;
	}

	public Date getBauFechaBautizo() {
		return this.bauFechaBautizo;
	}

	public void setBauFechaBautizo(Date bauFechaBautizo) {
		this.bauFechaBautizo = bauFechaBautizo;
	}

	public Integer getBauMadre() {
		return this.bauMadre;
	}

	public void setBauMadre(Integer bauMadre) {
		this.bauMadre = bauMadre;
	}

	public Integer getBauMadrina() {
		return this.bauMadrina;
	}

	public void setBauMadrina(Integer bauMadrina) {
		this.bauMadrina = bauMadrina;
	}

	public String getBauNotaMarginal() {
		return this.bauNotaMarginal;
	}

	public void setBauNotaMarginal(String bauNotaMarginal) {
		this.bauNotaMarginal = bauNotaMarginal;
	}

	public Integer getBauPadre() {
		return this.bauPadre;
	}

	public void setBauPadre(Integer bauPadre) {
		this.bauPadre = bauPadre;
	}

	public Integer getBauPadrino() {
		return this.bauPadrino;
	}

	public void setBauPadrino(Integer bauPadrino) {
		this.bauPadrino = bauPadrino;
	}

	public String getBauPagina() {
		return this.bauPagina;
	}

	public void setBauPagina(String bauPagina) {
		this.bauPagina = bauPagina;
	}

	public Integer getBauParroquia() {
		return this.bauParroquia;
	}

	public void setBauParroquia(Integer bauParroquia) {
		this.bauParroquia = bauParroquia;
	}

	public Integer getBauProvincia() {
		return this.bauProvincia;
	}

	public void setBauProvincia(Integer bauProvincia) {
		this.bauProvincia = bauProvincia;
	}

	public Integer getBauSacerdote() {
		return this.bauSacerdote;
	}

	public void setBauSacerdote(Integer bauSacerdote) {
		this.bauSacerdote = bauSacerdote;
	}

	public String getBauToma() {
		return this.bauToma;
	}

	public void setBauToma(String bauToma) {
		this.bauToma = bauToma;
	}
    
	
	
	public Integer getBauEmpresa() {
		return bauEmpresa;
	}

	public void setBauEmpresa(Integer bauEmpresa) {
		this.bauEmpresa = bauEmpresa;
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

	public String getMadApellidos() {
		return this.madApellidos;
	}

	public void setMadApellidos(String madApellidos) {
		this.madApellidos = madApellidos;
	}

	public String getMadCi() {
		return this.madCi;
	}

	public void setMadCi(String madCi) {
		this.madCi = madCi;
	}

	public String getMadNombres() {
		return this.madNombres;
	}

	public void setMadNombres(String madNombres) {
		this.madNombres = madNombres;
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

}