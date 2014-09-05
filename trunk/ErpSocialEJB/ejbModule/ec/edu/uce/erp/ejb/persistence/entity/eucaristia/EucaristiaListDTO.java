package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the euc_eucaristia_vie database table.
 * 
 */
@Entity
@Table(name="euc_eucaristia_vie")
@NamedQuery(name="EucaristiaListDTO.findAll", query="SELECT e FROM EucaristiaListDTO e")
public class EucaristiaListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="euc_codigo")
	private Integer eucCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name="euc_fecha_hora")
	private Date eucFechaHora;

	@Column(name="euc_intencion")
	private String eucIntencion;

	@Column(name="euc_sacerdote")
	private Integer eucSacerdote;

	@Column(name="euc_valor")
	private BigDecimal eucValor;

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

	@Column(name="per_telefono")
	private String perTelefono;

	@Id
	@Column(name="sac_codigo")
	private Integer sacCodigo;

	@Column(name="sac_persona")
	private Integer sacPersona;

	public EucaristiaListDTO() {
	}

	public Integer getEucCodigo() {
		return this.eucCodigo;
	}

	public void setEucCodigo(Integer eucCodigo) {
		this.eucCodigo = eucCodigo;
	}

	public Date getEucFechaHora() {
		return this.eucFechaHora;
	}

	public void setEucFechaHora(Date eucFechaHora) {
		this.eucFechaHora = eucFechaHora;
	}

	public String getEucIntencion() {
		return this.eucIntencion;
	}

	public void setEucIntencion(String eucIntencion) {
		this.eucIntencion = eucIntencion;
	}

	public Integer getEucSacerdote() {
		return this.eucSacerdote;
	}

	public void setEucSacerdote(Integer eucSacerdote) {
		this.eucSacerdote = eucSacerdote;
	}

	public BigDecimal getEucValor() {
		return this.eucValor;
	}

	public void setEucValor(BigDecimal eucValor) {
		this.eucValor = eucValor;
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