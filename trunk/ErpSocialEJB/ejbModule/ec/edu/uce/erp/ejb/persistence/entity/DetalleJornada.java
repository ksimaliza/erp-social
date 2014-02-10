package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the detalle_jornada_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_jornada_tbl")
@NamedQuery(name="DetalleJornada.findAll", query="SELECT d FROM DetalleJornada d")
public class DetalleJornada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DETALLE_JORNADA_TBL_DETJORNADAPK_GENERATOR", sequenceName="DETALLE_JORNADA_TBL_DET_JORNADA_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETALLE_JORNADA_TBL_DETJORNADAPK_GENERATOR")
	@Column(name="det_jornada_pk")
	private Integer detJornadaPk;

	@Column(name="det_jornada_descripcion")
	private String detJornadaDescripcion;

	@Column(name="det_jornada_dia")
	private String detJornadaDia;

	@Temporal(TemporalType.DATE)
	@Column(name="det_jornada_hora_entrada")
	private Date detJornadaHoraEntrada;

	@Temporal(TemporalType.DATE)
	@Column(name="det_jornada_hora_salida")
	private Date detJornadaHoraSalida;

	//bi-directional many-to-one association to Jornada
	@ManyToOne
	@JoinColumn(name="jor_fk")
	private Jornada jornadaTbl;

	public DetalleJornada() {
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

	public Date getDetJornadaHoraEntrada() {
		return this.detJornadaHoraEntrada;
	}

	public void setDetJornadaHoraEntrada(Date detJornadaHoraEntrada) {
		this.detJornadaHoraEntrada = detJornadaHoraEntrada;
	}

	public Date getDetJornadaHoraSalida() {
		return this.detJornadaHoraSalida;
	}

	public void setDetJornadaHoraSalida(Date detJornadaHoraSalida) {
		this.detJornadaHoraSalida = detJornadaHoraSalida;
	}

	public Jornada getJornadaTbl() {
		return this.jornadaTbl;
	}

	public void setJornadaTbl(Jornada jornadaTbl) {
		this.jornadaTbl = jornadaTbl;
	}

}