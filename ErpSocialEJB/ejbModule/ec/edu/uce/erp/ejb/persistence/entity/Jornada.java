package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
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


/**
 * The persistent class for the jornada_tbl database table.
 * 
 */
@Entity
@Table(name="jornada_tbl")
@NamedQuery(name="Jornada.findAll", query="SELECT j FROM Jornada j")
public class Jornada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JORNADA_TBL_JORPK_GENERATOR", sequenceName="JORNADA_TBL_JOR_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JORNADA_TBL_JORPK_GENERATOR")
	@Column(name="jor_pk")
	private Integer jorPk;

	@Column(name="jor_estado")
	private String jorEstado;

	@Column(name="jor_tipo")
	private String jorTipo;

	//bi-directional many-to-one association to DetalleJornada
	@OneToMany(mappedBy="jornadaTbl")
	private List<DetalleJornada> detalleJornadaTbls;

	//bi-directional many-to-one association to CargoEmpleado
	@ManyToOne
	@JoinColumn(name="car_empleado_fk")
	private CargoEmpleado cargoEmpleadoTbl;

	public Jornada() {
	}

	public Integer getJorPk() {
		return this.jorPk;
	}

	public void setJorPk(Integer jorPk) {
		this.jorPk = jorPk;
	}

	public String getJorEstado() {
		return this.jorEstado;
	}

	public void setJorEstado(String jorEstado) {
		this.jorEstado = jorEstado;
	}

	public String getJorTipo() {
		return this.jorTipo;
	}

	public void setJorTipo(String jorTipo) {
		this.jorTipo = jorTipo;
	}

	public List<DetalleJornada> getDetalleJornadaTbls() {
		return this.detalleJornadaTbls;
	}

	public void setDetalleJornadaTbls(List<DetalleJornada> detalleJornadaTbls) {
		this.detalleJornadaTbls = detalleJornadaTbls;
	}

	public DetalleJornada addDetalleJornadaTbl(DetalleJornada detalleJornadaTbl) {
		getDetalleJornadaTbls().add(detalleJornadaTbl);
		detalleJornadaTbl.setJornadaTbl(this);

		return detalleJornadaTbl;
	}

	public DetalleJornada removeDetalleJornadaTbl(DetalleJornada detalleJornadaTbl) {
		getDetalleJornadaTbls().remove(detalleJornadaTbl);
		detalleJornadaTbl.setJornadaTbl(null);

		return detalleJornadaTbl;
	}

	public CargoEmpleado getCargoEmpleadoTbl() {
		return this.cargoEmpleadoTbl;
	}

	public void setCargoEmpleadoTbl(CargoEmpleado cargoEmpleadoTbl) {
		this.cargoEmpleadoTbl = cargoEmpleadoTbl;
	}

}