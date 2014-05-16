package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the asi_empleado_vie database table.
 * 
 */
@Entity
@Table(name="asi_empleado_vie")
@NamedQuery(name="EmpleadoListDTO.findAll", query="SELECT e FROM EmpleadoListDTO e")
public class EmpleadoListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="aem_clave")
	private String aemClave;

	@Id
	@Column(name="aem_codigo")
	private Integer aemCodigo;

	@Column(name="aem_codigo_registro")
	private String aemCodigoRegistro;

	@Column(name="aem_empleado")
	private Integer aemEmpleado;

	@Column(name="aem_estado")
	private Boolean aemEstado;

	@Column(name="aem_usuario")
	private String aemUsuario;

	@Column(name="emp_codigo")
	private Integer empCodigo;

	@Column(name="emp_pk")
	private Integer empPk;

	@Column(name="emr_celular")
	private String emrCelular;

	@Column(name="emr_direccion")
	private String emrDireccion;

	@Column(name="emr_email")
	private String emrEmail;

	@Column(name="emr_estado")
	private String emrEstado;

	@Column(name="emr_fk")
	private Integer emrFk;

	@Column(name="emr_foto")
	private String emrFoto;

	@Column(name="emr_nombre")
	private String emrNombre;

	@Column(name="emr_pagina")
	private String emrPagina;

	@Column(name="emr_pk")
	private Integer emrPk;

	@Column(name="emr_ruc")
	private String emrRuc;

	@Column(name="emr_telefono")
	private String emrTelefono;

	@Column(name="hol_ent_sal_fk")
	private Integer holEntSalFk;

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

	@Column(name="tip_empresa_fk")
	private Integer tipEmpresaFk;

	public EmpleadoListDTO() {
	}

	public String getAemClave() {
		return this.aemClave;
	}

	public void setAemClave(String aemClave) {
		this.aemClave = aemClave;
	}

	public Integer getAemCodigo() {
		return this.aemCodigo;
	}

	public void setAemCodigo(Integer aemCodigo) {
		this.aemCodigo = aemCodigo;
	}

	public String getAemCodigoRegistro() {
		return this.aemCodigoRegistro;
	}

	public void setAemCodigoRegistro(String aemCodigoRegistro) {
		this.aemCodigoRegistro = aemCodigoRegistro;
	}

	public Integer getAemEmpleado() {
		return this.aemEmpleado;
	}

	public void setAemEmpleado(Integer aemEmpleado) {
		this.aemEmpleado = aemEmpleado;
	}

	public Boolean getAemEstado() {
		return this.aemEstado;
	}

	public void setAemEstado(Boolean aemEstado) {
		this.aemEstado = aemEstado;
	}

	public String getAemUsuario() {
		return this.aemUsuario;
	}

	public void setAemUsuario(String aemUsuario) {
		this.aemUsuario = aemUsuario;
	}

	public Integer getEmpCodigo() {
		return this.empCodigo;
	}

	public void setEmpCodigo(Integer empCodigo) {
		this.empCodigo = empCodigo;
	}

	public Integer getEmpPk() {
		return this.empPk;
	}

	public void setEmpPk(Integer empPk) {
		this.empPk = empPk;
	}

	public String getEmrCelular() {
		return this.emrCelular;
	}

	public void setEmrCelular(String emrCelular) {
		this.emrCelular = emrCelular;
	}

	public String getEmrDireccion() {
		return this.emrDireccion;
	}

	public void setEmrDireccion(String emrDireccion) {
		this.emrDireccion = emrDireccion;
	}

	public String getEmrEmail() {
		return this.emrEmail;
	}

	public void setEmrEmail(String emrEmail) {
		this.emrEmail = emrEmail;
	}

	public String getEmrEstado() {
		return this.emrEstado;
	}

	public void setEmrEstado(String emrEstado) {
		this.emrEstado = emrEstado;
	}

	public Integer getEmrFk() {
		return this.emrFk;
	}

	public void setEmrFk(Integer emrFk) {
		this.emrFk = emrFk;
	}

	public String getEmrFoto() {
		return this.emrFoto;
	}

	public void setEmrFoto(String emrFoto) {
		this.emrFoto = emrFoto;
	}

	public String getEmrNombre() {
		return this.emrNombre;
	}

	public void setEmrNombre(String emrNombre) {
		this.emrNombre = emrNombre;
	}

	public String getEmrPagina() {
		return this.emrPagina;
	}

	public void setEmrPagina(String emrPagina) {
		this.emrPagina = emrPagina;
	}

	public Integer getEmrPk() {
		return this.emrPk;
	}

	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}

	public String getEmrRuc() {
		return this.emrRuc;
	}

	public void setEmrRuc(String emrRuc) {
		this.emrRuc = emrRuc;
	}

	public String getEmrTelefono() {
		return this.emrTelefono;
	}

	public void setEmrTelefono(String emrTelefono) {
		this.emrTelefono = emrTelefono;
	}

	public Integer getHolEntSalFk() {
		return this.holEntSalFk;
	}

	public void setHolEntSalFk(Integer holEntSalFk) {
		this.holEntSalFk = holEntSalFk;
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

	public Integer getTipEmpresaFk() {
		return this.tipEmpresaFk;
	}

	public void setTipEmpresaFk(Integer tipEmpresaFk) {
		this.tipEmpresaFk = tipEmpresaFk;
	}

}