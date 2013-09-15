package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the detalle_jornada_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_jornada_tbl")
public class DetalleJornadaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="det_jornada_pk")
	private Integer detJornadaPk;

	@Column(name="det_jornada_descripcion")
	private String detJornadaDescripcion;

	@Column(name="det_jornada_dia")
	private String detJornadaDia;

	@Column(name="det_jornada_hora_entrada")
	private Timestamp detJornadaHoraEntrada;

	@Column(name="det_jornada_hora_salida")
	private Timestamp detJornadaHoraSalida;

	//bi-directional many-to-one association to JornadaTbl
    @ManyToOne
	@JoinColumn(name="jor_fk")
	private JornadaTbl jornadaTbl;

    public DetalleJornadaTbl() {
    }

	public Integer getDetJornadaPk() {
		return this.detJornadaPk;
	}

	public void setDetJornadaPk(Integer detJornadaPk) {
		this.detJornadaPk = detJornadaPk;
	}

	public String getDetJornadaDescripcion() {
		return this.detJornadaDescripcion;
	}

	public void setDetJornadaDescripcion(String detJornadaDescripcion) {
		this.detJornadaDescripcion = detJornadaDescripcion;
	}

	public String getDetJornadaDia() {
		return this.detJornadaDia;
	}

	public void setDetJornadaDia(String detJornadaDia) {
		this.detJornadaDia = detJornadaDia;
	}

	public Timestamp getDetJornadaHoraEntrada() {
		return this.detJornadaHoraEntrada;
	}

	public void setDetJornadaHoraEntrada(Timestamp detJornadaHoraEntrada) {
		this.detJornadaHoraEntrada = detJornadaHoraEntrada;
	}

	public Timestamp getDetJornadaHoraSalida() {
		return this.detJornadaHoraSalida;
	}

	public void setDetJornadaHoraSalida(Timestamp detJornadaHoraSalida) {
		this.detJornadaHoraSalida = detJornadaHoraSalida;
	}

	public JornadaTbl getJornadaTbl() {
		return this.jornadaTbl;
	}

	public void setJornadaTbl(JornadaTbl jornadaTbl) {
		this.jornadaTbl = jornadaTbl;
	}
	
}