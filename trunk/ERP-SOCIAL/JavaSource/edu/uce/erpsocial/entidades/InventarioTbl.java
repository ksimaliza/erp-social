package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inventario_tbl database table.
 * 
 */
@Entity
@Table(name="inventario_tbl")
public class InventarioTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="inv_pk")
	private Integer invPk;

	@Column(name="inv_codigo")
	private String invCodigo;

	@Column(name="inv_descripcion")
	private String invDescripcion;

	//bi-directional many-to-one association to BienTbl
    @ManyToOne
	@JoinColumn(name="bien_fk")
	private BienTbl bienTbl;

	//bi-directional many-to-one association to EmpleadoTbl
    @ManyToOne
	@JoinColumn(name="emp_fk")
	private EmpleadoTbl empleadoTbl;

    public InventarioTbl() {
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

	public BienTbl getBienTbl() {
		return this.bienTbl;
	}

	public void setBienTbl(BienTbl bienTbl) {
		this.bienTbl = bienTbl;
	}
	
	public EmpleadoTbl getEmpleadoTbl() {
		return this.empleadoTbl;
	}

	public void setEmpleadoTbl(EmpleadoTbl empleadoTbl) {
		this.empleadoTbl = empleadoTbl;
	}
	
}