package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


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

	@Column(name="bau_ciudad")
	private Integer bauCiudad;

	@Id
	@Column(name="bau_codigo")
	private Integer bauCodigo;

	@Column(name="bau_fecha_aprobacion_cruso")
	private Timestamp bauFechaAprobacionCruso;

	@Column(name="bau_fecha_bautizo")
	private Timestamp bauFechaBautizo;

	@Column(name="bau_madrina")
	private Integer bauMadrina;

	@Column(name="bau_nota_marginal")
	private String bauNotaMarginal;

	@Column(name="bau_padrino")
	private Integer bauPadrino;

	@Column(name="bau_pagina")
	private String bauPagina;

	@Column(name="bau_provincia")
	private Integer bauProvincia;

	@Column(name="bau_sacerdote")
	private Integer bauSacerdote;

	@Column(name="bau_toma")
	private String bauToma;

	
	@Column(name="doc_codigo")
	private Integer docCodigo;

	@Column(name="doc_persona")
	private Integer docPersona;

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


	@Column(name="sac_codigo")
	private Integer sacCodigo;

	@Column(name="sac_persona")
	private Integer sacPersona;

	public BautizoListDTO() {
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

	public Integer getBauCiudad() {
		return this.bauCiudad;
	}

	public void setBauCiudad(Integer bauCiudad) {
		this.bauCiudad = bauCiudad;
	}

	public Integer getBauCodigo() {
		return this.bauCodigo;
	}

	public void setBauCodigo(Integer bauCodigo) {
		this.bauCodigo = bauCodigo;
	}

	public Timestamp getBauFechaAprobacionCruso() {
		return this.bauFechaAprobacionCruso;
	}

	public void setBauFechaAprobacionCruso(Timestamp bauFechaAprobacionCruso) {
		this.bauFechaAprobacionCruso = bauFechaAprobacionCruso;
	}

	public Timestamp getBauFechaBautizo() {
		return this.bauFechaBautizo;
	}

	public void setBauFechaBautizo(Timestamp bauFechaBautizo) {
		this.bauFechaBautizo = bauFechaBautizo;
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

	public Integer getDocCodigo() {
		return this.docCodigo;
	}

	public void setDocCodigo(Integer docCodigo) {
		this.docCodigo = docCodigo;
	}

	public Integer getDocPersona() {
		return this.docPersona;
	}

	public void setDocPersona(Integer docPersona) {
		this.docPersona = docPersona;
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

	public Integer getSacCodigo() {
		return this.sacCodigo;
	}

	public void setSacCodigo(Integer sacCodigo) {
		this.sacCodigo = sacCodigo;
	}

	public Integer getSacPersona() {
		return this.sacPersona;
	}

	public void setSacPersona(Integer sacPersona) {
		this.sacPersona = sacPersona;
	}

}