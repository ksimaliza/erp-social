package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the transaccion_tbl database table.
 * 
 */
@Entity
@Table(name="transaccion_tbl")
@NamedQuery(name="Transaccion.findAll", query="SELECT t FROM Transaccion t")
public class Transaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TRANSACCION_TBL_TRAPK_GENERATOR", sequenceName="TRANSACCION_TBL_TRA_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSACCION_TBL_TRAPK_GENERATOR")
	@Column(name="tra_pk")
	private Integer traPk;

	@Column(name="tra_emp_asignado")
	private String traEmpAsignado;

	@Column(name="tra_emp_reasinado")
	private String traEmpReasinado;

	@Column(name="tra_estado")
	private String traEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="tra_fecha")
	private Date traFecha;
	
	@Column(name="tra_fecha_inicio")
	private Timestamp fechaInicio;
	
	@Column(name="tra_fecha_fin")
	private Timestamp fechaFin;

	//bi-directional many-to-one association to Bien
	@ManyToOne
	@JoinColumn(name="bie_fk")
	private Bien bienTbl;

	//bi-directional many-to-one association to DetalleBien
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_tra_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_tra_nivel1", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBien detalleBienTbl;

	public Transaccion() {
	}

	public Integer getTraPk() {
		return this.traPk;
	}

	public void setTraPk(Integer traPk) {
		this.traPk = traPk;
	}

	public String getTraEmpAsignado() {
		return this.traEmpAsignado;
	}

	public void setTraEmpAsignado(String traEmpAsignado) {
		this.traEmpAsignado = traEmpAsignado;
	}

	public String getTraEmpReasinado() {
		return this.traEmpReasinado;
	}

	public void setTraEmpReasinado(String traEmpReasinado) {
		this.traEmpReasinado = traEmpReasinado;
	}

	public String getTraEstado() {
		return this.traEstado;
	}

	public void setTraEstado(String traEstado) {
		this.traEstado = traEstado;
	}

	public Date getTraFecha() {
		return this.traFecha;
	}

	public void setTraFecha(Date traFecha) {
		this.traFecha = traFecha;
	}

	public Bien getBienTbl() {
		return this.bienTbl;
	}

	public void setBienTbl(Bien bienTbl) {
		this.bienTbl = bienTbl;
	}

	public DetalleBien getDetalleBienTbl() {
		return this.detalleBienTbl;
	}

	public void setDetalleBienTbl(DetalleBien detalleBienTbl) {
		this.detalleBienTbl = detalleBienTbl;
	}

	/**
	 * @return the fechaInicio
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Timestamp getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

}