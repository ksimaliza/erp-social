package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_comunion_vie database table.
 * 
 */
@Entity
@Table(name="euc_comunion_vie")
@NamedQuery(name="ComunionListDTO.findAll", query="SELECT c FROM ComunionListDTO c")
public class ComunionListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cat_canton")
	private String catCanton;

	@Column(name="cat_parroquia")
	private String catParroquia;

	@Column(name="cat_provincia")
	private String catProvincia;

	@Column(name="pad_apellidos")
	private String padApellidos;

	@Column(name="pad_ci")
	private String padCi;

	@Column(name="pad_nombres")
	private String padNombres;

	@Column(name="pco_acta")
	private String pcoActa;

	@Column(name="pco_asignado")
	private Integer pcoAsignado;

	@Column(name="pco_canton")
	private Integer pcoCanton;

	@Column(name="pco_certificado_por")
	private Integer pcoCertificadoPor;

	@Column(name="pco_codigo")
	private Integer pcoCodigo;

	@Column(name="pco_estado")
	private Integer pcoEstado;

	@Column(name="pco_fecha_aprobacion_curso")
	private Timestamp pcoFechaAprobacionCurso;

	@Column(name="pco_fecha_hora")
	private Timestamp pcoFechaHora;

	@Column(name="pco_lugar")
	private String pcoLugar;

	@Column(name="pco_nota_marginal")
	private String pcoNotaMarginal;

	@Column(name="pco_padrino")
	private Integer pcoPadrino;

	@Column(name="pco_pagina")
	private String pcoPagina;

	@Column(name="pco_parroquia")
	private Integer pcoParroquia;

	@Column(name="pco_provincia")
	private Integer pcoProvincia;

	@Column(name="pco_sacerdote")
	private Integer pcoSacerdote;

	@Column(name="pco_tomo")
	private String pcoTomo;

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

	@Id
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

	public ComunionListDTO() {
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

	public String getPcoActa() {
		return this.pcoActa;
	}

	public void setPcoActa(String pcoActa) {
		this.pcoActa = pcoActa;
	}

	public Integer getPcoAsignado() {
		return this.pcoAsignado;
	}

	public void setPcoAsignado(Integer pcoAsignado) {
		this.pcoAsignado = pcoAsignado;
	}

	public Integer getPcoCanton() {
		return this.pcoCanton;
	}

	public void setPcoCanton(Integer pcoCanton) {
		this.pcoCanton = pcoCanton;
	}

	public Integer getPcoCertificadoPor() {
		return this.pcoCertificadoPor;
	}

	public void setPcoCertificadoPor(Integer pcoCertificadoPor) {
		this.pcoCertificadoPor = pcoCertificadoPor;
	}

	public Integer getPcoCodigo() {
		return this.pcoCodigo;
	}

	public void setPcoCodigo(Integer pcoCodigo) {
		this.pcoCodigo = pcoCodigo;
	}

	public Integer getPcoEstado() {
		return this.pcoEstado;
	}

	public void setPcoEstado(Integer pcoEstado) {
		this.pcoEstado = pcoEstado;
	}

	public Timestamp getPcoFechaAprobacionCurso() {
		return this.pcoFechaAprobacionCurso;
	}

	public void setPcoFechaAprobacionCurso(Timestamp pcoFechaAprobacionCurso) {
		this.pcoFechaAprobacionCurso = pcoFechaAprobacionCurso;
	}

	public Timestamp getPcoFechaHora() {
		return this.pcoFechaHora;
	}

	public void setPcoFechaHora(Timestamp pcoFechaHora) {
		this.pcoFechaHora = pcoFechaHora;
	}

	public String getPcoLugar() {
		return this.pcoLugar;
	}

	public void setPcoLugar(String pcoLugar) {
		this.pcoLugar = pcoLugar;
	}

	public String getPcoNotaMarginal() {
		return this.pcoNotaMarginal;
	}

	public void setPcoNotaMarginal(String pcoNotaMarginal) {
		this.pcoNotaMarginal = pcoNotaMarginal;
	}

	public Integer getPcoPadrino() {
		return this.pcoPadrino;
	}

	public void setPcoPadrino(Integer pcoPadrino) {
		this.pcoPadrino = pcoPadrino;
	}

	public String getPcoPagina() {
		return this.pcoPagina;
	}

	public void setPcoPagina(String pcoPagina) {
		this.pcoPagina = pcoPagina;
	}

	public Integer getPcoParroquia() {
		return this.pcoParroquia;
	}

	public void setPcoParroquia(Integer pcoParroquia) {
		this.pcoParroquia = pcoParroquia;
	}

	public Integer getPcoProvincia() {
		return this.pcoProvincia;
	}

	public void setPcoProvincia(Integer pcoProvincia) {
		this.pcoProvincia = pcoProvincia;
	}

	public Integer getPcoSacerdote() {
		return this.pcoSacerdote;
	}

	public void setPcoSacerdote(Integer pcoSacerdote) {
		this.pcoSacerdote = pcoSacerdote;
	}

	public String getPcoTomo() {
		return this.pcoTomo;
	}

	public void setPcoTomo(String pcoTomo) {
		this.pcoTomo = pcoTomo;
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