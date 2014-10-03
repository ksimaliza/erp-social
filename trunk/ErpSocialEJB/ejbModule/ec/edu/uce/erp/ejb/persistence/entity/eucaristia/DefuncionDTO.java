package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_defuncion database table.
 * 
 */
@Entity
@Table(name="euc_defuncion")
@NamedQuery(name="DefuncionDTO.findAll", query="SELECT d FROM DefuncionDTO d")
public class DefuncionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_DEFUNCION_DEFCODIGO_GENERATOR", sequenceName="EUC_DEFUNCION_DEF_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_DEFUNCION_DEFCODIGO_GENERATOR")
	@Column(name="def_codigo")
	private Integer defCodigo;

	@Column(name="def_acta")
	private String defActa;

	@Column(name="def_canton")
	private Integer defCanton;

	@Column(name="def_causa_muerte")
	private String defCausaMuerte;

	@Column(name="def_parroquia")
	private Integer defParroquia;

	@Column(name="def_conyugue")
	private Integer defConyugue;
	
	@Column(name="def_madre")
	private Integer defMadre;
	
	@Column(name="def_Padre")
	private Integer defPadre;

	@Column(name="def_doctor_certifica")
	private int defDoctorCertifica;

	@Column(name="def_estado_civil")
	private Integer defEstadoCivil;

	@Column(name="def_fecha")
	private Timestamp defFecha;

	@Column(name="def_fecha_difunto")
	private Timestamp defFechaDifunto;

	@Column(name="def_pagina")
	private String defPagina;

	@Column(name="def_persona")
	private Integer defPersona;

	@Column(name="def_provincia")
	private Integer defProvincia;

	@Column(name="def_tomo")
	private String defTomo;

	//bi-directional many-to-one association to SacerdoteDTO
	@ManyToOne
	@JoinColumn(name="def_sacerdote")
	private SacerdoteDTO eucSacerdote;

	public DefuncionDTO() {
	}

	public Integer getDefCodigo() {
		return this.defCodigo;
	}

	public void setDefCodigo(Integer defCodigo) {
		this.defCodigo = defCodigo;
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

	

	public Integer getDefConyugue() {
		return this.defConyugue;
	}

	public void setDefConyugue(Integer defConyugue) {
		this.defConyugue = defConyugue;
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

	public String getDefTomo() {
		return this.defTomo;
	}

	public void setDefTomo(String defTomo) {
		this.defTomo = defTomo;
	}

	public SacerdoteDTO getEucSacerdote() {
		return this.eucSacerdote;
	}

	public void setEucSacerdote(SacerdoteDTO eucSacerdote) {
		this.eucSacerdote = eucSacerdote;
	}

	public Integer getDefParroquia() {
		return defParroquia;
	}

	public void setDefParroquia(Integer defParroquia) {
		this.defParroquia = defParroquia;
	}

	public int getDefDoctorCertifica() {
		return defDoctorCertifica;
	}

	public void setDefDoctorCertifica(int defDoctorCertifica) {
		this.defDoctorCertifica = defDoctorCertifica;
	}

	public Integer getDefMadre() {
		return defMadre;
	}

	public Integer getDefPadre() {
		return defPadre;
	}

	public void setDefMadre(Integer defMadre) {
		this.defMadre = defMadre;
	}

	public void setDefPadre(Integer defPadre) {
		this.defPadre = defPadre;
	}

}