package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the euc_comunion_vie database table.
 * 
 */
@Entity
@Table(name="euc_comunion_vie")
@NamedQuery(name="ComunionListDTO.findAll", query="SELECT c FROM ComunionListDTO c")
public class ComunionListDTO implements Serializable {
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

	@Column(name="bau_parroquia")
	private Integer bauParroquia;

	@Column(name="bau_provincia")
	private Integer bauProvincia;

	@Column(name="bau_sacerdote")
	private Integer bauSacerdote;

	@Column(name="bau_toma")
	private String bauToma;

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

	@Column(name="pco_tipo")
	private Integer pcoTipo;

	@Column(name="pco_tomo")
	private String pcoTomo;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_ci")
	private String perCi;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha_nac")
	private Date perFechaNac;

	@Column(name="per_nombres")
	private String perNombres;

	@Column(name="per_pk")
	private Integer perPk;

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

	public Integer getPcoTipo() {
		return this.pcoTipo;
	}

	public void setPcoTipo(Integer pcoTipo) {
		this.pcoTipo = pcoTipo;
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

	public String getPerCi() {
		return this.perCi;
	}

	public void setPerCi(String perCi) {
		this.perCi = perCi;
	}

	public Date getPerFechaNac() {
		return this.perFechaNac;
	}

	public void setPerFechaNac(Date perFechaNac) {
		this.perFechaNac = perFechaNac;
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