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
 * The persistent class for the inventario_tbl database table.
 * 
 */
@Entity
@Table(name="inventario_tbl")
@NamedQuery(name="Inventario.findAll", query="SELECT i FROM Inventario i")
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INVENTARIO_TBL_INVPK_GENERATOR", sequenceName="INVENTARIO_TBL_INV_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INVENTARIO_TBL_INVPK_GENERATOR")
	@Column(name="inv_pk")
	private Integer invPk;

	@Column(name="inv_codigo")
	private String invCodigo;

	@Column(name="inv_descripcion")
	private String invDescripcion;

	@Column(name="inv_estado")
	private String invEstado;

	//bi-directional many-to-one association to Bien
	@OneToMany(mappedBy="inventarioTbl")
	private List<Bien> bienTbls;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="emp_fk")
	private Empleado empleadoTbl;

	public Inventario() {
	}

	public Integer getInvPk() {
		return this.invPk;
	}

	public void setInvPk(Integer invPk) {
		this.invPk = invPk;
	}

	public String getInvCodigo() {
		return this.invCodigo;
	}

	public void setInvCodigo(String invCodigo) {
		this.invCodigo = invCodigo;
	}

	public String getInvDescripcion() {
		return this.invDescripcion;
	}

	public void setInvDescripcion(String invDescripcion) {
		this.invDescripcion = invDescripcion;
	}

	public String getInvEstado() {
		return this.invEstado;
	}

	public void setInvEstado(String invEstado) {
		this.invEstado = invEstado;
	}

	public List<Bien> getBienTbls() {
		return this.bienTbls;
	}

	public void setBienTbls(List<Bien> bienTbls) {
		this.bienTbls = bienTbls;
	}

	public Bien addBienTbl(Bien bienTbl) {
		getBienTbls().add(bienTbl);
		bienTbl.setInventarioTbl(this);

		return bienTbl;
	}

	public Bien removeBienTbl(Bien bienTbl) {
		getBienTbls().remove(bienTbl);
		bienTbl.setInventarioTbl(null);

		return bienTbl;
	}

	public Empleado getEmpleadoTbl() {
		return this.empleadoTbl;
	}

	public void setEmpleadoTbl(Empleado empleadoTbl) {
		this.empleadoTbl = empleadoTbl;
	}

}