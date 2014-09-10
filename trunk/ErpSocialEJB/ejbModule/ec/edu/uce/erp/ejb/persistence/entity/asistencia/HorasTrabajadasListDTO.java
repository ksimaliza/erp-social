package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the asi_horas_trabajadas_vie database table.
 * 
 */
@Entity
@Table(name="asi_horas_trabajadas_vie")
@NamedQuery(name="HorasTrabajadasListDTO.findAll", query="SELECT h FROM HorasTrabajadasListDTO h")
public class HorasTrabajadasListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="horas_trabajadas")
	private double horasTrabajadas;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_ci")
	private String perCi;

	@Column(name="per_nombres")
	private String perNombres;

	@Id
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

	public HorasTrabajadasListDTO() {
	}

	public double getHorasTrabajadas() {
		return this.horasTrabajadas;
	}

	public void setHorasTrabajadas(double horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
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

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
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