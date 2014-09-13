package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the asi_empleado_atraso_vie database table.
 * 
 */
@Entity
@Table(name="asi_empleado_atraso_vie")
@NamedQuery(name="EmpleadoAtrasoListDTO.findAll", query="SELECT e FROM EmpleadoAtrasoListDTO e")
public class EmpleadoAtrasoListDTO implements Serializable {
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

	@Id
	@Column(name="emp_pk")
	private Integer empPk;

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

	@Column(name="ras_codigo")
	private Integer rasCodigo;

	@Column(name="ras_empleado")
	private Integer rasEmpleado;

	@Column(name="ras_entidad")
	private Integer rasEntidad;

	@Column(name="ras_hora_inicio")
	private Timestamp rasHoraInicio;

	@Column(name="ras_hora_salida")
	private Timestamp rasHoraSalida;

	@Column(name="ras_tipo_entrada")
	private String rasTipoEntrada;

	@Column(name="ras_tipo_salida")
	private Integer rasTipoSalida;

	public EmpleadoAtrasoListDTO() {
	}

	public EmpleadoAtrasoListDTO(Integer rasEmpleado,String perCi,
			String perApellidos, 
			String perNombres) {
		super();
		this.rasEmpleado=rasEmpleado;
		this.perApellidos = perApellidos;
		this.perCi = perCi;
		this.perNombres = perNombres;
	}

	
	public EmpleadoAtrasoListDTO(Integer rasEmpleado,
			String perCi,
			String perApellidos,
			String perNombres,
			String perDireccion,
			Date perFechaNac) {
		super();
		this.rasEmpleado=rasEmpleado;
		this.perCi = perCi;
		this.perApellidos = perApellidos;
		this.perNombres = perNombres;
		this.perDireccion = perDireccion;
		this.perFechaNac = perFechaNac;
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

	public Integer getRasCodigo() {
		return this.rasCodigo;
	}

	public void setRasCodigo(Integer rasCodigo) {
		this.rasCodigo = rasCodigo;
	}

	public Integer getRasEmpleado() {
		return this.rasEmpleado;
	}

	public void setRasEmpleado(Integer rasEmpleado) {
		this.rasEmpleado = rasEmpleado;
	}

	public Integer getRasEntidad() {
		return this.rasEntidad;
	}

	public void setRasEntidad(Integer rasEntidad) {
		this.rasEntidad = rasEntidad;
	}

	public Timestamp getRasHoraInicio() {
		return this.rasHoraInicio;
	}

	public void setRasHoraInicio(Timestamp rasHoraInicio) {
		this.rasHoraInicio = rasHoraInicio;
	}

	public Timestamp getRasHoraSalida() {
		return this.rasHoraSalida;
	}

	public void setRasHoraSalida(Timestamp rasHoraSalida) {
		this.rasHoraSalida = rasHoraSalida;
	}

	public String getRasTipoEntrada() {
		return this.rasTipoEntrada;
	}

	public void setRasTipoEntrada(String rasTipoEntrada) {
		this.rasTipoEntrada = rasTipoEntrada;
	}

	public Integer getRasTipoSalida() {
		return this.rasTipoSalida;
	}

	public void setRasTipoSalida(Integer rasTipoSalida) {
		this.rasTipoSalida = rasTipoSalida;
	}

}