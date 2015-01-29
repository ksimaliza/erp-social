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
 * The persistent class for the euc_matrimonio_vie database table.
 * 
 */
@Entity
@Table(name="euc_matrimonio_vie")
@NamedQuery(name="MatrimonioListDTO.findAll", query="SELECT m FROM MatrimonioListDTO m")
public class MatrimonioListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cat_canton")
	private String catCanton;

	@Column(name="cat_parroquia")
	private String catParroquia;

	@Column(name="cat_provincia")
	private String catProvincia;

	@Column(name="mad_novia_apellidos")
	private String madNoviaApellidos;

	@Column(name="mad_novia_ci")
	private String madNoviaCi;

	@Column(name="mad_novia_nombres")
	private String madNoviaNombres;

	@Column(name="mad_novio_apellidos")
	private String madNovioApellidos;

	@Column(name="mad_novio_ci")
	private String madNovioCi;

	@Column(name="mad_novio_nombres")
	private String madNovioNombres;

	@Column(name="madre_novia_apellidos")
	private String madreNoviaApellidos;

	@Column(name="madre_novia_ci")
	private String madreNoviaCi;

	@Column(name="madre_novia_nombres")
	private String madreNoviaNombres;

	@Column(name="madre_novio_apellidos")
	private String madreNovioApellidos;

	@Column(name="madre_novio_ci")
	private String madreNovioCi;

	@Column(name="madre_novio_nombres")
	private String madreNovioNombres;

	@Column(name="mat_acta")
	private String matActa;

	@Column(name="mat_canton")
	private Integer matCanton;

	@Column(name="mat_certificado_novia")
	private String matCertificadoNovia;

	@Column(name="mat_certificado_novio")
	private String matCertificadoNovio;

	@Column(name="mat_certificado_por")
	private Integer matCertificadoPor;

	@Id
	@Column(name="mat_codigo")
	private Integer matCodigo;

	@Column(name="mat_fecha")
	@Temporal(TemporalType.DATE)
	private Date matFecha;

	@Column(name="mat_fecha_aprobacion_curso")
	@Temporal(TemporalType.DATE)
	private Date matFechaAprobacionCurso;

	@Column(name="mat_madre_novia")
	private Integer matMadreNovia;

	@Column(name="mat_madre_novio")
	private Integer matMadreNovio;

	@Column(name="mat_madrina_novia")
	private Integer matMadrinaNovia;

	@Column(name="mat_madrina_novio")
	private Integer matMadrinaNovio;

	@Column(name="mat_nota_marginal")
	private String matNotaMarginal;

	@Column(name="mat_novia")
	private Integer matNovia;

	@Column(name="mat_novio")
	private Integer matNovio;

	@Column(name="mat_padre_novia")
	private Integer matPadreNovia;

	@Column(name="mat_padre_novio")
	private Integer matPadreNovio;

	@Column(name="mat_padrino_novia")
	private Integer matPadrinoNovia;

	@Column(name="mat_padrino_novio")
	private Integer matPadrinoNovio;

	@Column(name="mat_pagina")
	private String matPagina;

	@Column(name="mat_parroquia")
	private Integer matParroquia;

	@Column(name="mat_parroquia_novia")
	private String matParroquiaNovia;

	@Column(name="mat_parroquia_novio")
	private String matParroquiaNovio;

	@Column(name="mat_provincia")
	private Integer matProvincia;

	@Column(name="mat_sacerdote")
	private Integer matSacerdote;

	@Column(name="mat_tomo")
	private String matTomo;

	@Column(name="novio_apellidos")
	private String novioApellidos;

	@Column(name="novio_cedula")
	private String novioCedula;

	@Column(name="novio_celular")
	private String novioCelular;

	@Column(name="novio_codigo")
	private Integer novioCodigo;

	@Column(name="novio_direccion")
	private String novioDireccion;

	@Column(name="novio_email")
	private String novioEmail;

	@Temporal(TemporalType.DATE)
	@Column(name="novio_fecha_nac")
	private Date novioFechaNac;

	@Column(name="novio_foto")
	private String novioFoto;

	@Column(name="novio_nombres")
	private String novioNombres;

	@Column(name="novio_telefono")
	private String novioTelefono;

	@Column(name="pad_novia_apellidos")
	private String padNoviaApellidos;

	@Column(name="pad_novia_ci")
	private String padNoviaCi;

	@Column(name="pad_novia_nombres")
	private String padNoviaNombres;

	@Column(name="pad_novio_apellidos")
	private String padNovioApellidos;

	@Column(name="pad_novio_ci")
	private String padNovioCi;

	@Column(name="pad_novio_nombres")
	private String padNovioNombres;

	@Column(name="padre_novia_apellidos")
	private String padreNoviaApellidos;

	@Column(name="padre_novia_ci")
	private String padreNoviaCi;

	@Column(name="padre_novia_nombres")
	private String padreNoviaNombres;

	@Column(name="padre_novio_apellidos")
	private String padreNovioApellidos;

	@Column(name="padre_novio_ci")
	private String padreNovioCi;

	@Column(name="padre_novio_nombres")
	private String padreNovioNombres;

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
	
	@Column(name="mat_empresa")
	private Integer matEmpresa;

	public MatrimonioListDTO() {
	}
	
	public MatrimonioListDTO(
			String novioCedula,
			String novioApellidos,
			String novioNombres,
			String perCi,
			String perApellidos,
			String perNombres,
			String sacNombres,
			String sacApellidos,
			Date matFecha,
			Date matFechaAprobacionCurso) {
			super();
			this.novioCedula = novioCedula;
			this.novioApellidos = novioApellidos;
			this.novioNombres = novioNombres;
			this.perCi = perCi;
			this.perApellidos = perApellidos;
			this.perNombres = perNombres;
			this.sacNombres = sacNombres;
			this.sacApellidos = sacApellidos;
			this.matFecha=matFecha;
			this.matFechaAprobacionCurso=matFechaAprobacionCurso;
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

	public String getMadNoviaApellidos() {
		return this.madNoviaApellidos;
	}

	public void setMadNoviaApellidos(String madNoviaApellidos) {
		this.madNoviaApellidos = madNoviaApellidos;
	}

	public String getMadNoviaCi() {
		return this.madNoviaCi;
	}

	public void setMadNoviaCi(String madNoviaCi) {
		this.madNoviaCi = madNoviaCi;
	}

	public String getMadNoviaNombres() {
		return this.madNoviaNombres;
	}

	public void setMadNoviaNombres(String madNoviaNombres) {
		this.madNoviaNombres = madNoviaNombres;
	}

	public String getMadNovioApellidos() {
		return this.madNovioApellidos;
	}

	public void setMadNovioApellidos(String madNovioApellidos) {
		this.madNovioApellidos = madNovioApellidos;
	}

	public String getMadNovioCi() {
		return this.madNovioCi;
	}

	public void setMadNovioCi(String madNovioCi) {
		this.madNovioCi = madNovioCi;
	}

	public String getMadNovioNombres() {
		return this.madNovioNombres;
	}

	public void setMadNovioNombres(String madNovioNombres) {
		this.madNovioNombres = madNovioNombres;
	}

	public String getMadreNoviaApellidos() {
		return this.madreNoviaApellidos;
	}

	public void setMadreNoviaApellidos(String madreNoviaApellidos) {
		this.madreNoviaApellidos = madreNoviaApellidos;
	}

	public String getMadreNoviaCi() {
		return this.madreNoviaCi;
	}

	public void setMadreNoviaCi(String madreNoviaCi) {
		this.madreNoviaCi = madreNoviaCi;
	}

	public String getMadreNoviaNombres() {
		return this.madreNoviaNombres;
	}

	public void setMadreNoviaNombres(String madreNoviaNombres) {
		this.madreNoviaNombres = madreNoviaNombres;
	}

	public String getMadreNovioApellidos() {
		return this.madreNovioApellidos;
	}

	public void setMadreNovioApellidos(String madreNovioApellidos) {
		this.madreNovioApellidos = madreNovioApellidos;
	}

	public String getMadreNovioCi() {
		return this.madreNovioCi;
	}

	public void setMadreNovioCi(String madreNovioCi) {
		this.madreNovioCi = madreNovioCi;
	}

	public String getMadreNovioNombres() {
		return this.madreNovioNombres;
	}

	public void setMadreNovioNombres(String madreNovioNombres) {
		this.madreNovioNombres = madreNovioNombres;
	}

	public String getMatActa() {
		return this.matActa;
	}

	public void setMatActa(String matActa) {
		this.matActa = matActa;
	}

	public Integer getMatCanton() {
		return this.matCanton;
	}

	public void setMatCanton(Integer matCanton) {
		this.matCanton = matCanton;
	}

	public String getMatCertificadoNovia() {
		return this.matCertificadoNovia;
	}

	public void setMatCertificadoNovia(String matCertificadoNovia) {
		this.matCertificadoNovia = matCertificadoNovia;
	}

	public String getMatCertificadoNovio() {
		return this.matCertificadoNovio;
	}

	public void setMatCertificadoNovio(String matCertificadoNovio) {
		this.matCertificadoNovio = matCertificadoNovio;
	}

	public Integer getMatCertificadoPor() {
		return this.matCertificadoPor;
	}

	public void setMatCertificadoPor(Integer matCertificadoPor) {
		this.matCertificadoPor = matCertificadoPor;
	}

	public Integer getMatCodigo() {
		return this.matCodigo;
	}

	public void setMatCodigo(Integer matCodigo) {
		this.matCodigo = matCodigo;
	}

	public Date getMatFecha() {
		return this.matFecha;
	}

	public void setMatFecha(Date matFecha) {
		this.matFecha = matFecha;
	}

	public Date getMatFechaAprobacionCurso() {
		return this.matFechaAprobacionCurso;
	}

	public void setMatFechaAprobacionCurso(Date matFechaAprobacionCurso) {
		this.matFechaAprobacionCurso = matFechaAprobacionCurso;
	}

	public Integer getMatMadreNovia() {
		return this.matMadreNovia;
	}

	public void setMatMadreNovia(Integer matMadreNovia) {
		this.matMadreNovia = matMadreNovia;
	}

	public Integer getMatMadreNovio() {
		return this.matMadreNovio;
	}

	public void setMatMadreNovio(Integer matMadreNovio) {
		this.matMadreNovio = matMadreNovio;
	}

	public Integer getMatMadrinaNovia() {
		return this.matMadrinaNovia;
	}

	public void setMatMadrinaNovia(Integer matMadrinaNovia) {
		this.matMadrinaNovia = matMadrinaNovia;
	}

	public Integer getMatMadrinaNovio() {
		return this.matMadrinaNovio;
	}

	public void setMatMadrinaNovio(Integer matMadrinaNovio) {
		this.matMadrinaNovio = matMadrinaNovio;
	}

	public String getMatNotaMarginal() {
		return this.matNotaMarginal;
	}

	public void setMatNotaMarginal(String matNotaMarginal) {
		this.matNotaMarginal = matNotaMarginal;
	}

	public Integer getMatNovia() {
		return this.matNovia;
	}

	public void setMatNovia(Integer matNovia) {
		this.matNovia = matNovia;
	}

	public Integer getMatNovio() {
		return this.matNovio;
	}

	public void setMatNovio(Integer matNovio) {
		this.matNovio = matNovio;
	}

	public Integer getMatPadreNovia() {
		return this.matPadreNovia;
	}

	public void setMatPadreNovia(Integer matPadreNovia) {
		this.matPadreNovia = matPadreNovia;
	}

	public Integer getMatPadreNovio() {
		return this.matPadreNovio;
	}

	public void setMatPadreNovio(Integer matPadreNovio) {
		this.matPadreNovio = matPadreNovio;
	}

	public Integer getMatPadrinoNovia() {
		return this.matPadrinoNovia;
	}

	public void setMatPadrinoNovia(Integer matPadrinoNovia) {
		this.matPadrinoNovia = matPadrinoNovia;
	}

	public Integer getMatPadrinoNovio() {
		return this.matPadrinoNovio;
	}

	public void setMatPadrinoNovio(Integer matPadrinoNovio) {
		this.matPadrinoNovio = matPadrinoNovio;
	}

	public String getMatPagina() {
		return this.matPagina;
	}

	public void setMatPagina(String matPagina) {
		this.matPagina = matPagina;
	}

	public Integer getMatParroquia() {
		return this.matParroquia;
	}

	public void setMatParroquia(Integer matParroquia) {
		this.matParroquia = matParroquia;
	}

	public String getMatParroquiaNovia() {
		return this.matParroquiaNovia;
	}

	public void setMatParroquiaNovia(String matParroquiaNovia) {
		this.matParroquiaNovia = matParroquiaNovia;
	}

	public String getMatParroquiaNovio() {
		return this.matParroquiaNovio;
	}

	public void setMatParroquiaNovio(String matParroquiaNovio) {
		this.matParroquiaNovio = matParroquiaNovio;
	}

	public Integer getMatProvincia() {
		return this.matProvincia;
	}

	public void setMatProvincia(Integer matProvincia) {
		this.matProvincia = matProvincia;
	}

	public Integer getMatSacerdote() {
		return this.matSacerdote;
	}

	public void setMatSacerdote(Integer matSacerdote) {
		this.matSacerdote = matSacerdote;
	}

	public String getMatTomo() {
		return this.matTomo;
	}

	public void setMatTomo(String matTomo) {
		this.matTomo = matTomo;
	}

	public String getNovioApellidos() {
		return this.novioApellidos;
	}

	public void setNovioApellidos(String novioApellidos) {
		this.novioApellidos = novioApellidos;
	}

	public String getNovioCedula() {
		return this.novioCedula;
	}

	public void setNovioCedula(String novioCedula) {
		this.novioCedula = novioCedula;
	}

	public String getNovioCelular() {
		return this.novioCelular;
	}

	public void setNovioCelular(String novioCelular) {
		this.novioCelular = novioCelular;
	}

	public Integer getNovioCodigo() {
		return this.novioCodigo;
	}

	public void setNovioCodigo(Integer novioCodigo) {
		this.novioCodigo = novioCodigo;
	}

	public String getNovioDireccion() {
		return this.novioDireccion;
	}

	public void setNovioDireccion(String novioDireccion) {
		this.novioDireccion = novioDireccion;
	}

	public String getNovioEmail() {
		return this.novioEmail;
	}

	public void setNovioEmail(String novioEmail) {
		this.novioEmail = novioEmail;
	}

	public Date getNovioFechaNac() {
		return this.novioFechaNac;
	}

	public void setNovioFechaNac(Date novioFechaNac) {
		this.novioFechaNac = novioFechaNac;
	}

	public String getNovioFoto() {
		return this.novioFoto;
	}

	public void setNovioFoto(String novioFoto) {
		this.novioFoto = novioFoto;
	}

	public String getNovioNombres() {
		return this.novioNombres;
	}

	public void setNovioNombres(String novioNombres) {
		this.novioNombres = novioNombres;
	}

	public String getNovioTelefono() {
		return this.novioTelefono;
	}

	public void setNovioTelefono(String novioTelefono) {
		this.novioTelefono = novioTelefono;
	}

	public String getPadNoviaApellidos() {
		return this.padNoviaApellidos;
	}

	public void setPadNoviaApellidos(String padNoviaApellidos) {
		this.padNoviaApellidos = padNoviaApellidos;
	}

	public String getPadNoviaCi() {
		return this.padNoviaCi;
	}

	public void setPadNoviaCi(String padNoviaCi) {
		this.padNoviaCi = padNoviaCi;
	}

	public String getPadNoviaNombres() {
		return this.padNoviaNombres;
	}

	public void setPadNoviaNombres(String padNoviaNombres) {
		this.padNoviaNombres = padNoviaNombres;
	}

	public String getPadNovioApellidos() {
		return this.padNovioApellidos;
	}

	public void setPadNovioApellidos(String padNovioApellidos) {
		this.padNovioApellidos = padNovioApellidos;
	}

	public String getPadNovioCi() {
		return this.padNovioCi;
	}

	public void setPadNovioCi(String padNovioCi) {
		this.padNovioCi = padNovioCi;
	}

	public String getPadNovioNombres() {
		return this.padNovioNombres;
	}

	public void setPadNovioNombres(String padNovioNombres) {
		this.padNovioNombres = padNovioNombres;
	}

	public String getPadreNoviaApellidos() {
		return this.padreNoviaApellidos;
	}

	public void setPadreNoviaApellidos(String padreNoviaApellidos) {
		this.padreNoviaApellidos = padreNoviaApellidos;
	}

	public String getPadreNoviaCi() {
		return this.padreNoviaCi;
	}

	public void setPadreNoviaCi(String padreNoviaCi) {
		this.padreNoviaCi = padreNoviaCi;
	}

	public String getPadreNoviaNombres() {
		return this.padreNoviaNombres;
	}

	public void setPadreNoviaNombres(String padreNoviaNombres) {
		this.padreNoviaNombres = padreNoviaNombres;
	}

	public String getPadreNovioApellidos() {
		return this.padreNovioApellidos;
	}

	public void setPadreNovioApellidos(String padreNovioApellidos) {
		this.padreNovioApellidos = padreNovioApellidos;
	}

	public String getPadreNovioCi() {
		return this.padreNovioCi;
	}

	public void setPadreNovioCi(String padreNovioCi) {
		this.padreNovioCi = padreNovioCi;
	}

	public String getPadreNovioNombres() {
		return this.padreNovioNombres;
	}

	public void setPadreNovioNombres(String padreNovioNombres) {
		this.padreNovioNombres = padreNovioNombres;
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

	public Integer getMatEmpresa() {
		return matEmpresa;
	}

	public void setMatEmpresa(Integer matEmpresa) {
		this.matEmpresa = matEmpresa;
	}
    
	
}