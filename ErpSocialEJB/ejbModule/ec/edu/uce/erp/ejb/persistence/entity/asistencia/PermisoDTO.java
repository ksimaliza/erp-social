package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the asi_permiso database table.
 * 
 */
@Entity
@Table(name="asi_permiso")
@NamedQuery(name="PermisoDTO.findAll", query="SELECT p FROM PermisoDTO p")
public class PermisoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_PERMISO_PERCODIGO_GENERATOR", sequenceName="ASI_PERMISO_PER_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_PERMISO_PERCODIGO_GENERATOR")
	@Column(name="per_codigo")
	private Integer perCodigo;

	@Column(name="per_fecha")
	private Timestamp perFecha;

	//bi-directional many-to-one association to EmpleadoDTO
	@ManyToOne
	@JoinColumn(name="per_empleado")
	private EmpleadoDTO asiEmpleado;

	public PermisoDTO() {
	}

	public Integer getPerCodigo() {
		return this.perCodigo;
	}

	public void setPerCodigo(Integer perCodigo) {
		this.perCodigo = perCodigo;
	}

	public Timestamp getPerFecha() {
		return this.perFecha;
	}

	public void setPerFecha(Timestamp perFecha) {
		this.perFecha = perFecha;
	}

	public EmpleadoDTO getAsiEmpleado() {
		return this.asiEmpleado;
	}

	public void setAsiEmpleado(EmpleadoDTO asiEmpleado) {
		this.asiEmpleado = asiEmpleado;
	}

}