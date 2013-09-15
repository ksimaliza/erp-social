package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the empleado_tbl database table.
 * 
 */
@Entity
@Table(name="empleado_tbl")
public class EmpleadoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="emp_pk")
	private Integer empPk;

	@Column(name="emp_afiliacion")
	private Integer empAfiliacion;

	@Column(name="emp_codigo")
	private Integer empCodigo;

    @Temporal( TemporalType.DATE)
	@Column(name="emp_fecha_ingreso")
	private Date empFechaIngreso;

	//bi-directional many-to-one association to InstitucionTbl
    @ManyToOne
	@JoinColumn(name="ins_fk")
	private InstitucionTbl institucionTbl;

	//bi-directional many-to-one association to PersonaTbl
    @ManyToOne
	@JoinColumn(name="per_fk")
	private PersonaTbl personaTbl;

	//bi-directional many-to-one association to InventarioTbl
	@OneToMany(mappedBy="empleadoTbl")
	private Set<InventarioTbl> inventarioTbls;

    public EmpleadoTbl() {
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

	public Date getEmpFechaIngreso() {
		return this.empFechaIngreso;
	}

	public void setEmpFechaIngreso(Date empFechaIngreso) {
		this.empFechaIngreso = empFechaIngreso;
	}

	public InstitucionTbl getInstitucionTbl() {
		return this.institucionTbl;
	}

	public void setInstitucionTbl(InstitucionTbl institucionTbl) {
		this.institucionTbl = institucionTbl;
	}
	
	public PersonaTbl getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(PersonaTbl personaTbl) {
		this.personaTbl = personaTbl;
	}
	
	public Set<InventarioTbl> getInventarioTbls() {
		return this.inventarioTbls;
	}

	public void setInventarioTbls(Set<InventarioTbl> inventarioTbls) {
		this.inventarioTbls = inventarioTbls;
	}
	
}