package ec.edu.uce.erp.ejb.persistence.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the empleado_view database table.
 * 
 */
@Entity
@Table(name="empleado_view")
@NamedQuery(name="VistaEmpleado.findAll", query="SELECT v FROM VistaEmpleado v")
public class VistaEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="emp_codigo")
	private Integer empCodigo;

	@Id
	@Column(name="emp_pk")
	private Integer empPk;

	@Column(name="emr_direccion")
	private String emrDireccion;

	@Column(name="emr_fk")
	private Integer emrFk;

	@Column(name="emr_nombre")
	private String emrNombre;

	@Column(name="emr_pk")
	private Integer emrPk;

	@Column(name="emr_ruc")
	private String emrRuc;

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

	@Column(name="nombres_completos")
	private String nombresCompletos;

	public VistaEmpleado() {
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

	public String getEmrDireccion() {
		return this.emrDireccion;
	}

	public void setEmrDireccion(String emrDireccion) {
		this.emrDireccion = emrDireccion;
	}

	public Integer getEmrFk() {
		return this.emrFk;
	}

	public void setEmrFk(Integer emrFk) {
		this.emrFk = emrFk;
	}

	public String getEmrNombre() {
		return this.emrNombre;
	}

	public void setEmrNombre(String emrNombre) {
		this.emrNombre = emrNombre;
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

	/**
	 * @return the nombresCompletos
	 */
	public String getNombresCompletos() {
		return nombresCompletos;
	}

	/**
	 * @param nombresCompletos the nombresCompletos to set
	 */
	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}
	
}