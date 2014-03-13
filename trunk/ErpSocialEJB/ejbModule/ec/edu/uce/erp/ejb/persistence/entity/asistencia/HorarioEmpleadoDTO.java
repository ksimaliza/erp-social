package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asi_horario_empleado database table.
 * 
 */
@Entity
@Table(name="asi_horario_empleado")
@NamedQuery(name="HorarioEmpleadoDTO.findAll", query="SELECT h FROM HorarioEmpleadoDTO h")
public class HorarioEmpleadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_HORARIO_EMPLEADO_HEMCODIGO_GENERATOR", sequenceName="ASI_HORARIO_EMPLEADO_HEM_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_HORARIO_EMPLEADO_HEMCODIGO_GENERATOR")
	@Column(name="hem_codigo")
	private Integer hemCodigo;

	//bi-directional many-to-one association to EmpleadoDTO
	@ManyToOne
	@JoinColumn(name="hem_emleado")
	private EmpleadoDTO asiEmpleado;

	//bi-directional many-to-one association to TipoDTO
	@ManyToOne
	@JoinColumn(name="hem_tipo")
	private TipoDTO asiTipo;

	public HorarioEmpleadoDTO() {
	}

	public Integer getHemCodigo() {
		return this.hemCodigo;
	}

	public void setHemCodigo(Integer hemCodigo) {
		this.hemCodigo = hemCodigo;
	}

	public EmpleadoDTO getAsiEmpleado() {
		return this.asiEmpleado;
	}

	public void setAsiEmpleado(EmpleadoDTO asiEmpleado) {
		this.asiEmpleado = asiEmpleado;
	}

	public TipoDTO getAsiTipo() {
		return this.asiTipo;
	}

	public void setAsiTipo(TipoDTO asiTipo) {
		this.asiTipo = asiTipo;
	}

}