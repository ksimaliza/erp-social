package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
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

import ec.edu.uce.erp.ejb.persistence.entity.inventory.Inventario;


/**
 * The persistent class for the empleado_tbl database table.
 * 
 */
@Entity
@Table(name="empleado_tbl")
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMPLEADO_TBL_EMPPK_GENERATOR", sequenceName="EMPLEADO_TBL_EMP_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLEADO_TBL_EMPPK_GENERATOR")
	@Column(name="emp_pk")
	private Integer empPk;

	@Column(name="emp_afiliacion")
	private Integer empAfiliacion;

	@Column(name="emp_codigo")
	private Integer empCodigo;

	@Column(name="emp_departamento")
	private String empDepartamento;

	@Column(name="emp_estado")
	private String empEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="emp_fecha_ingreso")
	private Date empFechaIngreso;

	//bi-directional many-to-one association to CargoEmpleado
	@OneToMany(mappedBy="empleadoTbl")
	private List<CargoEmpleado> cargoEmpleadoTbls;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="emr_fk")
	private Empresa empresaTbl;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_fk")
	private Persona personaTbl;

	//bi-directional many-to-one association to Inventario
	@OneToMany(mappedBy="empleadoTbl")
	private List<Inventario> inventarioTbls;

	public Empleado() {
	}

	public Integer getEmpPk() {
		return this.empPk;
	}

	public void setEmpPk(Integer empPk) {
		this.empPk = empPk;
	}

	public Integer getEmpAfiliacion() {
		return this.empAfiliacion;
	}

	public void setEmpAfiliacion(Integer empAfiliacion) {
		this.empAfiliacion = empAfiliacion;
	}

	public Integer getEmpCodigo() {
		return this.empCodigo;
	}

	public void setEmpCodigo(Integer empCodigo) {
		this.empCodigo = empCodigo;
	}

	public String getEmpDepartamento() {
		return this.empDepartamento;
	}

	public void setEmpDepartamento(String empDepartamento) {
		this.empDepartamento = empDepartamento;
	}

	public String getEmpEstado() {
		return this.empEstado;
	}

	public void setEmpEstado(String empEstado) {
		this.empEstado = empEstado;
	}

	public Date getEmpFechaIngreso() {
		return this.empFechaIngreso;
	}

	public void setEmpFechaIngreso(Date empFechaIngreso) {
		this.empFechaIngreso = empFechaIngreso;
	}

	public List<CargoEmpleado> getCargoEmpleadoTbls() {
		return this.cargoEmpleadoTbls;
	}

	public void setCargoEmpleadoTbls(List<CargoEmpleado> cargoEmpleadoTbls) {
		this.cargoEmpleadoTbls = cargoEmpleadoTbls;
	}

	public CargoEmpleado addCargoEmpleadoTbl(CargoEmpleado cargoEmpleadoTbl) {
		getCargoEmpleadoTbls().add(cargoEmpleadoTbl);
		cargoEmpleadoTbl.setEmpleadoTbl(this);

		return cargoEmpleadoTbl;
	}

	public CargoEmpleado removeCargoEmpleadoTbl(CargoEmpleado cargoEmpleadoTbl) {
		getCargoEmpleadoTbls().remove(cargoEmpleadoTbl);
		cargoEmpleadoTbl.setEmpleadoTbl(null);

		return cargoEmpleadoTbl;
	}

	public Empresa getEmpresaTbl() {
		return this.empresaTbl;
	}

	public void setEmpresaTbl(Empresa empresaTbl) {
		this.empresaTbl = empresaTbl;
	}

	public Persona getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(Persona personaTbl) {
		this.personaTbl = personaTbl;
	}

	public List<Inventario> getInventarioTbls() {
		return this.inventarioTbls;
	}

	public void setInventarioTbls(List<Inventario> inventarioTbls) {
		this.inventarioTbls = inventarioTbls;
	}

	public Inventario addInventarioTbl(Inventario inventarioTbl) {
		getInventarioTbls().add(inventarioTbl);
		inventarioTbl.setEmpleadoTbl(this);

		return inventarioTbl;
	}

	public Inventario removeInventarioTbl(Inventario inventarioTbl) {
		getInventarioTbls().remove(inventarioTbl);
		inventarioTbl.setEmpleadoTbl(null);

		return inventarioTbl;
	}

}