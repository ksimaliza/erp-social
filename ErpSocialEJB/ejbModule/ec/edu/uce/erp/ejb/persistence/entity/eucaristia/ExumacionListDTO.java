package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_exumacion_vie database table.
 * 
 */
@Entity
@Table(name="euc_exumacion_vie")
@NamedQuery(name="ExumacionListDTO.findAll", query="SELECT e FROM ExumacionListDTO e")
public class ExumacionListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String apellidosautoriza;

	@Column(name="aut_codigo")
	private Integer autCodigo;

	@Column(name="aut_persona")
	private Integer autPersona;

	private String cedulaautoriza;

	private String celularautoriza;

	private String direccionautoriza;

	private String emailautoriza;

	@Column(name="exu_acta")
	private String exuActa;

	@Column(name="exu_autoriza")
	private Integer exuAutoriza;

	@Column(name="exu_causa")
	private String exuCausa;

	@Id
	@Column(name="exu_codigo")
	private Integer exuCodigo;

	@Column(name="exu_difunto")
	private Integer exuDifunto;

	@Column(name="exu_fecha_cepelio")
	private Timestamp exuFechaCepelio;

	@Column(name="exu_fecha_exhumacion")
	private Timestamp exuFechaExhumacion;

	@Column(name="exu_pagina")
	private String exuPagina;

	@Column(name="exu_tomo")
	private String exuTomo;

	@Temporal(TemporalType.DATE)
	private Date fechanacautoriza;

	private String nombresautoriza;

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

	@Column(name="per_nombres")
	private String perNombres;

	@Column(name="per_telefono")
	private String perTelefono;

	private String telefonoautoriza;

	public ExumacionListDTO() {
	}

	public String getApellidosautoriza() {
		return this.apellidosautoriza;
	}

	public void setApellidosautoriza(String apellidosautoriza) {
		this.apellidosautoriza = apellidosautoriza;
	}

	public Integer getAutCodigo() {
		return this.autCodigo;
	}

	public void setAutCodigo(Integer autCodigo) {
		this.autCodigo = autCodigo;
	}

	public Integer getAutPersona() {
		return this.autPersona;
	}

	public void setAutPersona(Integer autPersona) {
		this.autPersona = autPersona;
	}

	public String getCedulaautoriza() {
		return this.cedulaautoriza;
	}

	public void setCedulaautoriza(String cedulaautoriza) {
		this.cedulaautoriza = cedulaautoriza;
	}

	public String getCelularautoriza() {
		return this.celularautoriza;
	}

	public void setCelularautoriza(String celularautoriza) {
		this.celularautoriza = celularautoriza;
	}

	public String getDireccionautoriza() {
		return this.direccionautoriza;
	}

	public void setDireccionautoriza(String direccionautoriza) {
		this.direccionautoriza = direccionautoriza;
	}

	public String getEmailautoriza() {
		return this.emailautoriza;
	}

	public void setEmailautoriza(String emailautoriza) {
		this.emailautoriza = emailautoriza;
	}

	public String getExuActa() {
		return this.exuActa;
	}

	public void setExuActa(String exuActa) {
		this.exuActa = exuActa;
	}

	public Integer getExuAutoriza() {
		return this.exuAutoriza;
	}

	public void setExuAutoriza(Integer exuAutoriza) {
		this.exuAutoriza = exuAutoriza;
	}

	public String getExuCausa() {
		return this.exuCausa;
	}

	public void setExuCausa(String exuCausa) {
		this.exuCausa = exuCausa;
	}

	public Integer getExuCodigo() {
		return this.exuCodigo;
	}

	public void setExuCodigo(Integer exuCodigo) {
		this.exuCodigo = exuCodigo;
	}

	public Integer getExuDifunto() {
		return this.exuDifunto;
	}

	public void setExuDifunto(Integer exuDifunto) {
		this.exuDifunto = exuDifunto;
	}

	public Timestamp getExuFechaCepelio() {
		return this.exuFechaCepelio;
	}

	public void setExuFechaCepelio(Timestamp exuFechaCepelio) {
		this.exuFechaCepelio = exuFechaCepelio;
	}

	public Timestamp getExuFechaExhumacion() {
		return this.exuFechaExhumacion;
	}

	public void setExuFechaExhumacion(Timestamp exuFechaExhumacion) {
		this.exuFechaExhumacion = exuFechaExhumacion;
	}

	public String getExuPagina() {
		return this.exuPagina;
	}

	public void setExuPagina(String exuPagina) {
		this.exuPagina = exuPagina;
	}

	public String getExuTomo() {
		return this.exuTomo;
	}

	public void setExuTomo(String exuTomo) {
		this.exuTomo = exuTomo;
	}

	public Date getFechanacautoriza() {
		return this.fechanacautoriza;
	}

	public void setFechanacautoriza(Date fechanacautoriza) {
		this.fechanacautoriza = fechanacautoriza;
	}

	public String getNombresautoriza() {
		return this.nombresautoriza;
	}

	public void setNombresautoriza(String nombresautoriza) {
		this.nombresautoriza = nombresautoriza;
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

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public String getPerTelefono() {
		return this.perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	public String getTelefonoautoriza() {
		return this.telefonoautoriza;
	}

	public void setTelefonoautoriza(String telefonoautoriza) {
		this.telefonoautoriza = telefonoautoriza;
	}

}