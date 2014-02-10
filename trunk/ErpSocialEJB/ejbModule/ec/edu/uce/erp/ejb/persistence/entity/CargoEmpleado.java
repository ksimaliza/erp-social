package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cargo_empleado_tbl database table.
 * 
 */
@Entity
@Table(name="cargo_empleado_tbl")
@NamedQuery(name="CargoEmpleado.findAll", query="SELECT c FROM CargoEmpleado c")
public class CargoEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CARGO_EMPLEADO_TBL_CAREMPLEADOPK_GENERATOR", sequenceName="CARGO_EMPLEADO_TBL_CAR_EMPLEADO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARGO_EMPLEADO_TBL_CAREMPLEADOPK_GENERATOR")
	@Column(name="car_empleado_pk")
	private Integer carEmpleadoPk;

	@Column(name="car_empleado_desc_cargo")
	private String carEmpleadoDescCargo;

	@Column(name="car_empleado_estado")
	private String carEmpleadoEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="car_empleado_fecha_desde")
	private Date carEmpleadoFechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="car_empleado_fecha_hasta")
	private Date carEmpleadoFechaHasta;

	@Column(name="car_empleado_remuneracion")
	private BigDecimal carEmpleadoRemuneracion;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="car_fk")
	private Cargo cargoTbl;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="emp_fk")
	private Empleado empleadoTbl;

	//bi-directional many-to-one association to DetalleVacaciones
	@OneToMany(mappedBy="cargoEmpleadoTbl")
	private List<DetalleVacaciones> detalleVacacionesTbls;

	//bi-directional many-to-one association to Jornada
	@OneToMany(mappedBy="cargoEmpleadoTbl")
	private List<Jornada> jornadaTbls;

	public CargoEmpleado() {
	}

	public Integer getCarEmpleadoPk() {
		return this.carEmpleadoPk;
	}

	public void setCarEmpleadoPk(Integer carEmpleadoPk) {
		this.carEmpleadoPk = carEmpleadoPk;
	}

	public String getCarEmpleadoDescCargo() {
		return this.carEmpleadoDescCargo;
	}

	public void setCarEmpleadoDescCargo(String carEmpleadoDescCargo) {
		this.carEmpleadoDescCargo = carEmpleadoDescCargo;
	}

	public String getCarEmpleadoEstado() {
		return this.carEmpleadoEstado;
	}

	public void setCarEmpleadoEstado(String carEmpleadoEstado) {
		this.carEmpleadoEstado = carEmpleadoEstado;
	}

	public Date getCarEmpleadoFechaDesde() {
		return this.carEmpleadoFechaDesde;
	}

	public void setCarEmpleadoFechaDesde(Date carEmpleadoFechaDesde) {
		this.carEmpleadoFechaDesde = carEmpleadoFechaDesde;
	}

	public Date getCarEmpleadoFechaHasta() {
		return this.carEmpleadoFechaHasta;
	}

	public void setCarEmpleadoFechaHasta(Date carEmpleadoFechaHasta) {
		this.carEmpleadoFechaHasta = carEmpleadoFechaHasta;
	}

	public BigDecimal getCarEmpleadoRemuneracion() {
		return this.carEmpleadoRemuneracion;
	}

	public void setCarEmpleadoRemuneracion(BigDecimal carEmpleadoRemuneracion) {
		this.carEmpleadoRemuneracion = carEmpleadoRemuneracion;
	}

	public Cargo getCargoTbl() {
		return this.cargoTbl;
	}

	public void setCargoTbl(Cargo cargoTbl) {
		this.cargoTbl = cargoTbl;
	}

	public Empleado getEmpleadoTbl() {
		return this.empleadoTbl;
	}

	public void setEmpleadoTbl(Empleado empleadoTbl) {
		this.empleadoTbl = empleadoTbl;
	}

	public List<DetalleVacaciones> getDetalleVacacionesTbls() {
		return this.detalleVacacionesTbls;
	}

	public void setDetalleVacacionesTbls(List<DetalleVacaciones> detalleVacacionesTbls) {
		this.detalleVacacionesTbls = detalleVacacionesTbls;
	}

	public DetalleVacaciones addDetalleVacacionesTbl(DetalleVacaciones detalleVacacionesTbl) {
		getDetalleVacacionesTbls().add(detalleVacacionesTbl);
		detalleVacacionesTbl.setCargoEmpleadoTbl(this);

		return detalleVacacionesTbl;
	}

	public DetalleVacaciones removeDetalleVacacionesTbl(DetalleVacaciones detalleVacacionesTbl) {
		getDetalleVacacionesTbls().remove(detalleVacacionesTbl);
		detalleVacacionesTbl.setCargoEmpleadoTbl(null);

		return detalleVacacionesTbl;
	}

	public List<Jornada> getJornadaTbls() {
		return this.jornadaTbls;
	}

	public void setJornadaTbls(List<Jornada> jornadaTbls) {
		this.jornadaTbls = jornadaTbls;
	}

	public Jornada addJornadaTbl(Jornada jornadaTbl) {
		getJornadaTbls().add(jornadaTbl);
		jornadaTbl.setCargoEmpleadoTbl(this);

		return jornadaTbl;
	}

	public Jornada removeJornadaTbl(Jornada jornadaTbl) {
		getJornadaTbls().remove(jornadaTbl);
		jornadaTbl.setCargoEmpleadoTbl(null);

		return jornadaTbl;
	}

}