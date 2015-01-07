package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the euc_defuncion_vie database table.
 * 
 */
@Entity
@Table(name="euc_defuncion_vie")
@NamedQuery(name="DefuncionListDTO.findAll", query="SELECT d FROM DefuncionListDTO d")
public class DefuncionListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cat_canton")
	private String catCanton;

	@Column(name="cat_parroquia")
	private String catParroquia;

	@Column(name="cat_provincia")
	private String catProvincia;

	@Column(name="def_acta")
	private String defActa;

	@Column(name="def_canton")
	private Integer defCanton;

	@Column(name="def_causa_muerte")
	private String defCausaMuerte;

	@Id
	@Column(name="def_codigo")
	private Integer defCodigo;

	@Column(name="def_conyugue")
	private Integer defConyugue;

	@Column(name="def_doctor_certifica")
	private Integer defDoctorCertifica;

	@Column(name="def_estado_civil")
	private Integer defEstadoCivil;

	@Column(name="def_fecha")
	@Temporal(TemporalType.DATE)
	private Date defFecha;

	@Column(name="def_fecha_difunto")
	private Timestamp defFechaDifunto;

	@Column(name="def_madre")
	private Integer defMadre;

	@Column(name="def_nota_marginal")
	private String defNotaMarginal;
	
	@Column(name="def_empresa")
	private Integer defEmpresa;

	@Column(name="def_padre")
	private Integer defPadre;

	@Column(name="def_pagina")
	private String defPagina;

	@Column(name="def_parroquia")
	private Integer defParroquia;

	@Column(name="def_persona")
	private Integer defPersona;

	@Column(name="def_provincia")
	private Integer defProvincia;

	@Column(name="def_sacerdote")
	private Integer defSacerdote;

	@Column(name="def_tomo")
	private String defTomo;

	@Column(name="doc_apellidos")
	private String docApellidos;

	@Column(name="doc_ci")
	private String docCi;

	@Column(name="doc_codigo")
	private Integer docCodigo;

	@Column(name="doc_nombres")
	private String docNombres;

	@Column(name="doc_persona")
	private Integer docPersona;

	@Column(name="madre_apellidos")
	private String madreApellidos;

	@Column(name="madre_ci")
	private String madreCi;

	@Column(name="madre_nombres")
	private String madreNombres;

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


	public DefuncionListDTO() {
	}
	
	public DefuncionListDTO(Integer defParroquia,
			Integer defProvincia,
			Integer defCanton,
			String perCi,
			String perApellidos,
			String perNombres,
			String sacNombres,
			String sacApellidos,
			Date defFecha) {
			super();
			this.defParroquia=defParroquia;
			this.defProvincia=defProvincia;
			this.defCanton=defCanton;
			this.perCi = perCi;
			this.perApellidos = perApellidos;
			this.perNombres = perNombres;
			this.sacNombres = sacNombres;
			this.sacApellidos = sacApellidos;
			this.defFecha=defFecha;
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

	public String getDefActa() {
		return this.defActa;
	}

	public void setDefActa(String defActa) {
		this.defActa = defActa;
	}

	public Integer getDefCanton() {
		return this.defCanton;
	}

	public void setDefCanton(Integer defCanton) {
		this.defCanton = defCanton;
	}

	public String getDefCausaMuerte() {
		return this.defCausaMuerte;
	}

	public void setDefCausaMuerte(String defCausaMuerte) {
		this.defCausaMuerte = defCausaMuerte;
	}

	public Integer getDefCodigo() {
		return this.defCodigo;
	}

	public void setDefCodigo(Integer defCodigo) {
		this.defCodigo = defCodigo;
	}

	public Integer getDefConyugue() {
		return this.defConyugue;
	}

	public void setDefConyugue(Integer defConyugue) {
		this.defConyugue = defConyugue;
	}

	public Integer getDefDoctorCertifica() {
		return this.defDoctorCertifica;
	}

	public void setDefDoctorCertifica(Integer defDoctorCertifica) {
		this.defDoctorCertifica = defDoctorCertifica;
	}

	public Integer getDefEstadoCivil() {
		return this.defEstadoCivil;
	}

	public void setDefEstadoCivil(Integer defEstadoCivil) {
		this.defEstadoCivil = defEstadoCivil;
	}

	public Date getDefFecha() {
		return this.defFecha;
	}

	public void setDefFecha(Date defFecha) {
		this.defFecha = defFecha;
	}

	public Timestamp getDefFechaDifunto() {
		return this.defFechaDifunto;
	}

	public void setDefFechaDifunto(Timestamp defFechaDifunto) {
		this.defFechaDifunto = defFechaDifunto;
	}

	public Integer getDefMadre() {
		return this.defMadre;
	}

	public void setDefMadre(Integer defMadre) {
		this.defMadre = defMadre;
	}

	public String getDefNotaMarginal() {
		return this.defNotaMarginal;
	}

	public void setDefNotaMarginal(String defNotaMarginal) {
		this.defNotaMarginal = defNotaMarginal;
	}

	public Integer getDefPadre() {
		return this.defPadre;
	}

	public void setDefPadre(Integer defPadre) {
		this.defPadre = defPadre;
	}

	public String getDefPagina() {
		return this.defPagina;
	}

	public void setDefPagina(String defPagina) {
		this.defPagina = defPagina;
	}

	public Integer getDefParroquia() {
		return this.defParroquia;
	}

	public void setDefParroquia(Integer defParroquia) {
		this.defParroquia = defParroquia;
	}

	public Integer getDefPersona() {
		return this.defPersona;
	}

	public void setDefPersona(Integer defPersona) {
		this.defPersona = defPersona;
	}

	public Integer getDefProvincia() {
		return this.defProvincia;
	}

	public void setDefProvincia(Integer defProvincia) {
		this.defProvincia = defProvincia;
	}

	public Integer getDefSacerdote() {
		return this.defSacerdote;
	}

	public void setDefSacerdote(Integer defSacerdote) {
		this.defSacerdote = defSacerdote;
	}

	public String getDefTomo() {
		return this.defTomo;
	}

	public void setDefTomo(String defTomo) {
		this.defTomo = defTomo;
	}

	public String getDocApellidos() {
		return this.docApellidos;
	}

	public void setDocApellidos(String docApellidos) {
		this.docApellidos = docApellidos;
	}

	public String getDocCi() {
		return this.docCi;
	}

	public void setDocCi(String docCi) {
		this.docCi = docCi;
	}

	public Integer getDocCodigo() {
		return this.docCodigo;
	}

	public void setDocCodigo(Integer docCodigo) {
		this.docCodigo = docCodigo;
	}

	public String getDocNombres() {
		return this.docNombres;
	}

	public void setDocNombres(String docNombres) {
		this.docNombres = docNombres;
	}

	public Integer getDocPersona() {
		return this.docPersona;
	}

	public void setDocPersona(Integer docPersona) {
		this.docPersona = docPersona;
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

	public Integer getDefEmpresa() {
		return defEmpresa;
	}

	public void setDefEmpresa(Integer defEmpresa) {
		this.defEmpresa = defEmpresa;
	}
	
	

}