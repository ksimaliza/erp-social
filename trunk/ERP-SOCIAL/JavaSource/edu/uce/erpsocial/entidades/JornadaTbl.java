package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the jornada_tbl database table.
 * 
 */
@Entity
@Table(name="jornada_tbl")
public class JornadaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="jor_pk")
	private Integer jorPk;

	@Column(name="car_empleado_fk")
	private Integer carEmpleadoFk;

	@Column(name="jor_tipo")
	private String jorTipo;

	//bi-directional many-to-one association to DetalleJornadaTbl
	@OneToMany(mappedBy="jornadaTbl")
	private Set<DetalleJornadaTbl> detalleJornadaTbls;

    public JornadaTbl() {
    }

	public Integer getJorPk() {
		return this.jorPk;
	}

	public void setJorPk(Integer jorPk) {
		this.jorPk = jorPk;
	}

	public Integer getCarEmpleadoFk() {
		return this.carEmpleadoFk;
	}

	public void setCarEmpleadoFk(Integer carEmpleadoFk) {
		this.carEmpleadoFk = carEmpleadoFk;
	}

	public String getJorTipo() {
		return this.jorTipo;
	}

	public void setJorTipo(String jorTipo) {
		this.jorTipo = jorTipo;
	}

	public Set<DetalleJornadaTbl> getDetalleJornadaTbls() {
		return this.detalleJornadaTbls;
	}

	public void setDetalleJornadaTbls(Set<DetalleJornadaTbl> detalleJornadaTbls) {
		this.detalleJornadaTbls = detalleJornadaTbls;
	}
	
}