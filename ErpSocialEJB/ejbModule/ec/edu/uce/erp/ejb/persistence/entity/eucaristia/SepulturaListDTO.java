package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the euc_sepultura_vie database table.
 * 
 */
@Entity
@Table(name="euc_sepultura_vie")
@NamedQuery(name="SepulturaListDTO.findAll", query="SELECT s FROM SepulturaListDTO s")
public class SepulturaListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

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
	private Timestamp defFecha;

	@Column(name="def_fecha_difunto")
	private Timestamp defFechaDifunto;

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

	@Column(name="nic_codigo")
	private Integer nicCodigo;

	@Column(name="nic_descripcion")
	private String nicDescripcion;

	@Column(name="nic_seccion")
	private Integer nicSeccion;

	@Column(name="nic_tipo")
	private Integer nicTipo;

	@Column(name="nni_descripcion")
	private String nniDescripcion;

	@Column(name="nni_nivel")
	private Integer nniNivel;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_ci")
	private String perCi;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha_nac")
	private Date perFechaNac;

	@Column(name="per_nombres")
	private String perNombres;

	private String seccion;

	@Column(name="sep_codigo")
	private Integer sepCodigo;

	@Column(name="sep_difunto")
	private Integer sepDifunto;

	@Column(name="sep_nicho")
	private Integer sepNicho;

	@Column(name="sep_observacion")
	private String sepObservacion;

	@Column(name="tni_descripcion")
	private String tniDescripcion;

	public SepulturaListDTO() {
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

	public Timestamp getDefFecha() {
		return this.defFecha;
	}

	public void setDefFecha(Timestamp defFecha) {
		this.defFecha = defFecha;
	}

	public Timestamp getDefFechaDifunto() {
		return this.defFechaDifunto;
	}

	public void setDefFechaDifunto(Timestamp defFechaDifunto) {
		this.defFechaDifunto = defFechaDifunto;
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

	public Integer getNicCodigo() {
		return this.nicCodigo;
	}

	public void setNicCodigo(Integer nicCodigo) {
		this.nicCodigo = nicCodigo;
	}

	public String getNicDescripcion() {
		return this.nicDescripcion;
	}

	public void setNicDescripcion(String nicDescripcion) {
		this.nicDescripcion = nicDescripcion;
	}

	public Integer getNicSeccion() {
		return this.nicSeccion;
	}

	public void setNicSeccion(Integer nicSeccion) {
		this.nicSeccion = nicSeccion;
	}

	public Integer getNicTipo() {
		return this.nicTipo;
	}

	public void setNicTipo(Integer nicTipo) {
		this.nicTipo = nicTipo;
	}

	public String getNniDescripcion() {
		return this.nniDescripcion;
	}

	public void setNniDescripcion(String nniDescripcion) {
		this.nniDescripcion = nniDescripcion;
	}

	public Integer getNniNivel() {
		return this.nniNivel;
	}

	public void setNniNivel(Integer nniNivel) {
		this.nniNivel = nniNivel;
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

	public String getSeccion() {
		return this.seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public Integer getSepCodigo() {
		return this.sepCodigo;
	}

	public void setSepCodigo(Integer sepCodigo) {
		this.sepCodigo = sepCodigo;
	}

	public Integer getSepDifunto() {
		return this.sepDifunto;
	}

	public void setSepDifunto(Integer sepDifunto) {
		this.sepDifunto = sepDifunto;
	}

	public Integer getSepNicho() {
		return this.sepNicho;
	}

	public void setSepNicho(Integer sepNicho) {
		this.sepNicho = sepNicho;
	}

	public String getSepObservacion() {
		return this.sepObservacion;
	}

	public void setSepObservacion(String sepObservacion) {
		this.sepObservacion = sepObservacion;
	}

	public String getTniDescripcion() {
		return this.tniDescripcion;
	}

	public void setTniDescripcion(String tniDescripcion) {
		this.tniDescripcion = tniDescripcion;
	}

}