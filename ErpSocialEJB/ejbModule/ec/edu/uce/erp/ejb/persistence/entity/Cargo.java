package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cargo_tbl database table.
 * 
 */
@Entity
@Table(name="cargo_tbl")
@NamedQuery(name="Cargo.findAll", query="SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CARGO_TBL_CARPK_GENERATOR", sequenceName="CARGO_TBL_CAR_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARGO_TBL_CARPK_GENERATOR")
	@Column(name="car_pk")
	private Integer carPk;

	@Column(name="car_codigo")
	private Integer carCodigo;

	@Column(name="car_estado")
	private String carEstado;

	@Column(name="car_nombre")
	private String carNombre;

	//bi-directional many-to-one association to CargoEmpleado
	@OneToMany(mappedBy="cargoTbl")
	private List<CargoEmpleado> cargoEmpleadoTbls;

	public Cargo() {
	}

	public Integer getCarPk() {
		return this.carPk;
	}

	public void setCarPk(Integer carPk) {
		this.carPk = carPk;
	}

	public Integer getCarCodigo() {
		return this.carCodigo;
	}

	public void setCarCodigo(Integer carCodigo) {
		this.carCodigo = carCodigo;
	}

	public String getCarEstado() {
		return this.carEstado;
	}

	public void setCarEstado(String carEstado) {
		this.carEstado = carEstado;
	}

	public String getCarNombre() {
		return this.carNombre;
	}

	public void setCarNombre(String carNombre) {
		this.carNombre = carNombre;
	}

	public List<CargoEmpleado> getCargoEmpleadoTbls() {
		return this.cargoEmpleadoTbls;
	}

	public void setCargoEmpleadoTbls(List<CargoEmpleado> cargoEmpleadoTbls) {
		this.cargoEmpleadoTbls = cargoEmpleadoTbls;
	}

	public CargoEmpleado addCargoEmpleadoTbl(CargoEmpleado cargoEmpleadoTbl) {
		getCargoEmpleadoTbls().add(cargoEmpleadoTbl);
		cargoEmpleadoTbl.setCargoTbl(this);

		return cargoEmpleadoTbl;
	}

	public CargoEmpleado removeCargoEmpleadoTbl(CargoEmpleado cargoEmpleadoTbl) {
		getCargoEmpleadoTbls().remove(cargoEmpleadoTbl);
		cargoEmpleadoTbl.setCargoTbl(null);

		return cargoEmpleadoTbl;
	}

}