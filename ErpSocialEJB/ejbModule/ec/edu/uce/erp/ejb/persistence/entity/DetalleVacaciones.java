package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the detalle_vacaciones_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_vacaciones_tbl")
@NamedQuery(name="DetalleVacaciones.findAll", query="SELECT d FROM DetalleVacaciones d")
public class DetalleVacaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETALLE_VACACIONES_TBL_DETVACACIONESPK_GENERATOR", sequenceName="DETALLE_VACACIONES_TBL_DET_VACACIONES_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETALLE_VACACIONES_TBL_DETVACACIONESPK_GENERATOR")
	@Column(name="det_vacaciones_pk")
	private Integer detVacacionesPk;

	@Column(name="det_vacaciones_descripcion")
	private String detVacacionesDescripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="det_vacaciones_fecha_fin")
	private Date detVacacionesFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="det_vacaciones_fecha_inicio")
	private Date detVacacionesFechaInicio;

	//bi-directional many-to-one association to CargoEmpleado
	@ManyToOne
	@JoinColumn(name="car_empleado_fk")
	private CargoEmpleado cargoEmpleadoTbl;

	//bi-directional many-to-one association to Vacaciones
	@ManyToOne
	@JoinColumn(name="vac_fk")
	private Vacaciones vacacionesTbl;

	public DetalleVacaciones() {
	}

	public Integer getDetVacacionesPk() {
		return this.detVacacionesPk;
	}

	public void setDetVacacionesPk(Integer detVacacionesPk) {
		this.detVacacionesPk = detVacacionesPk;
	}

	public String getDetVacacionesDescripcion() {
		return this.detVacacionesDescripcion;
	}

	public void setDetVacacionesDescripcion(String detVacacionesDescripcion) {
		this.detVacacionesDescripcion = detVacacionesDescripcion;
	}

	public Date getDetVacacionesFechaFin() {
		return this.detVacacionesFechaFin;
	}

	public void setDetVacacionesFechaFin(Date detVacacionesFechaFin) {
		this.detVacacionesFechaFin = detVacacionesFechaFin;
	}

	public Date getDetVacacionesFechaInicio() {
		return this.detVacacionesFechaInicio;
	}

	public void setDetVacacionesFechaInicio(Date detVacacionesFechaInicio) {
		this.detVacacionesFechaInicio = detVacacionesFechaInicio;
	}

	public CargoEmpleado getCargoEmpleadoTbl() {
		return this.cargoEmpleadoTbl;
	}

	public void setCargoEmpleadoTbl(CargoEmpleado cargoEmpleadoTbl) {
		this.cargoEmpleadoTbl = cargoEmpleadoTbl;
	}

	public Vacaciones getVacacionesTbl() {
		return this.vacacionesTbl;
	}

	public void setVacacionesTbl(Vacaciones vacacionesTbl) {
		this.vacacionesTbl = vacacionesTbl;
	}

}