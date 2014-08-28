package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the asi_registro database table.
 * 
 */
@Entity
@Table(name="asi_registro")
@NamedQuery(name="RegistroDTO.findAll", query="SELECT r FROM RegistroDTO r")
public class RegistroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_REGISTRO_RASCODIGO_GENERATOR", sequenceName="ASI_REGISTRO_RAS_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_REGISTRO_RASCODIGO_GENERATOR")
	@Column(name="ras_codigo")
	private Integer rasCodigo;

	@Column(name="ras_hora_inicio")
	private Timestamp rasHoraInicio;

	@Column(name="ras_hora_salida")
	private Timestamp rasHoraSalida;

	@Column(name="ras_tipo_entrada")
	private String rasTipoEntrada;

	@Column(name="ras_tipo_salida")
	private Integer rasTipoSalida;

	@Column(name="ras_entidad")
	private Integer rasEntidad;
	
	//bi-directional many-to-one association to EmpleadoDTO
	@ManyToOne
	@JoinColumn(name="ras_empleado")
	private EmpleadoDTO asiEmpleado;

	public RegistroDTO() {
	}

	public Integer getRasCodigo() {
		return this.rasCodigo;
	}

	public void setRasCodigo(Integer rasCodigo) {
		this.rasCodigo = rasCodigo;
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

	public Integer getRasEntidad() {
		return rasEntidad;
	}

	public void setRasEntidad(Integer rasEntidad) {
		this.rasEntidad = rasEntidad;
	}

	public EmpleadoDTO getAsiEmpleado() {
		return this.asiEmpleado;
	}

	public void setAsiEmpleado(EmpleadoDTO asiEmpleado) {
		this.asiEmpleado = asiEmpleado;
	}

}