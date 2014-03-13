package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the asi_falta database table.
 * 
 */
@Entity
@Table(name="asi_falta")
@NamedQuery(name="FaltaDTO.findAll", query="SELECT f FROM FaltaDTO f")
public class FaltaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_FALTA_FALCODIGO_GENERATOR", sequenceName="ASI_FALTA_FAL_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_FALTA_FALCODIGO_GENERATOR")
	@Column(name="fal_codigo")
	private Integer falCodigo;

	@Column(name="fal_fecha")
	private Timestamp falFecha;

	//bi-directional many-to-one association to EmpleadoDTO
	@ManyToOne
	@JoinColumn(name="fal_empleado")
	private EmpleadoDTO asiEmpleado;

	public FaltaDTO() {
	}

	public Integer getFalCodigo() {
		return this.falCodigo;
	}

	public void setFalCodigo(Integer falCodigo) {
		this.falCodigo = falCodigo;
	}

	public Timestamp getFalFecha() {
		return this.falFecha;
	}

	public void setFalFecha(Timestamp falFecha) {
		this.falFecha = falFecha;
	}

	public EmpleadoDTO getAsiEmpleado() {
		return this.asiEmpleado;
	}

	public void setAsiEmpleado(EmpleadoDTO asiEmpleado) {
		this.asiEmpleado = asiEmpleado;
	}

}