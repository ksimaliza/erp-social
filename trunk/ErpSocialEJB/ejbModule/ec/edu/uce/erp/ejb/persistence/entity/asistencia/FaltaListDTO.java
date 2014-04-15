package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the asi_empleado_falta_vie database table.
 * 
 */
@Entity
@Table(name="asi_empleado_falta_vie")
@NamedQuery(name="FaltaListDTO.findAll", query="SELECT f FROM FaltaListDTO f")
public class FaltaListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="aem_clave")
	private String aemClave;

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

	@Id
	@Column(name="fal_codigo")
	private Integer falCodigo;

	@Column(name="fal_descripcion")
	private String falDescripcion;

	@Column(name="fal_fecha")
	private Timestamp falFecha;

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

	public FaltaListDTO() {
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

	public Integer getFalCodigo() {
		return this.falCodigo;
	}

	public void setFalCodigo(Integer falCodigo) {
		this.falCodigo = falCodigo;
	}

	public String getFalDescripcion() {
		return this.falDescripcion;
	}

	public void setFalDescripcion(String falDescripcion) {
		this.falDescripcion = falDescripcion;
	}

	public Timestamp getFalFecha() {
		return this.falFecha;
	}

	public void setFalFecha(Timestamp falFecha) {
		this.falFecha = falFecha;
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

}