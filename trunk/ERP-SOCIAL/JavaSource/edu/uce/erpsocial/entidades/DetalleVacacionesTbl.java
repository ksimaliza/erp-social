package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the detalle_vacaciones_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_vacaciones_tbl")
public class DetalleVacacionesTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="det_vacaciones_pk")
	private Integer detVacacionesPk;

	@Column(name="car_empleado_fk")
	private Integer carEmpleadoFk;

	@Column(name="det_vacaciones_descripcion")
	private String detVacacionesDescripcion;

    @Temporal( TemporalType.DATE)
	@Column(name="det_vacaciones_fecha_fin")
	private Date detVacacionesFechaFin;

    @Temporal( TemporalType.DATE)
	@Column(name="det_vacaciones_fecha_inicio")
	private Date detVacacionesFechaInicio;

	//bi-directional many-to-one association to VacacionesTbl
    @ManyToOne
	@JoinColumn(name="vac_fk")
	private VacacionesTbl vacacionesTbl;

    public DetalleVacacionesTbl() {
    }

	public Integer getDetVacacionesPk() {
		return this.detVacacionesPk;
	}

	public void setDetVacacionesPk(Integer detVacacionesPk) {
		this.detVacacionesPk = detVacacionesPk;
	}

	public Integer getCarEmpleadoFk() {
		return this.carEmpleadoFk;
	}

	public void setCarEmpleadoFk(Integer carEmpleadoFk) {
		this.carEmpleadoFk = carEmpleadoFk;
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

	public VacacionesTbl getVacacionesTbl() {
		return this.vacacionesTbl;
	}

	public void setVacacionesTbl(VacacionesTbl vacacionesTbl) {
		this.vacacionesTbl = vacacionesTbl;
	}
	
}