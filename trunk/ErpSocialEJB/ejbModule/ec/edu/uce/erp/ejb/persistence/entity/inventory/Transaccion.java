package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import ec.edu.uce.erp.ejb.persistence.entity.Empleado;


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
	
	@Column(name="bie_fk")
	private Integer bieFk;
	
	@Column(name = "emp_asignado_fk")
	private Integer empAsignadoFk;
	
	@Column(name = "emp_reasignado_fk")
	private Integer empReasignadoFk;

	//bi-directional many-to-one association to Bien
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="bie_fk", referencedColumnName="bie_pk", unique=false, nullable=true, insertable=false, updatable=false)
	})
	private Bien bienTbl;
	
	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="emp_asignado_fk", referencedColumnName="emp_pk", unique=false, nullable=true, insertable=false, updatable=false)
	})
	private Empleado empleadoAsignadoTbl;
	
	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="emp_reasignado_fk", referencedColumnName="emp_pk", unique=false, nullable=true, insertable=false, updatable=false)
	})
	private Empleado empleadoReasignadoTbl;

	//bi-directional many-to-one association to DetalleBien
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_tra_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_tra_nivel1", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBien detalleBienTbl;
	
	@Column(name="cab_bien_tip_bie_fk")
	private String cabCatalogoTipoBien;
	
	@Column(name="det_bien_tip_bie_nivel1")
	private String detCatalogoTipoBien;
	
//	@Column(name="cab_bien_est_fk")
//	private String cabEstadoBien;
//	
//	@Column(name="det_bien_est_nivel1")
//	private String detEstadoBien;
	
	@Column(name="cab_bien_est_conserv_fk")
	private String cabEstadoConservacion;
	
	@Column(name="det_bien_est_conserv_nivel1_fk")
	private String detEstadoConservacion;
	
	//bi-directional many-to-one association to DetalleBien detalleBienTbl1
	/**
	 * Tipo del bien: Ingresado, asignado, reasignado, devuelto se manejar dentro de un cat&acute;logo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cab_bien_tip_bie_fk", referencedColumnName="cab_bien_fk", unique=false, nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="det_bien_tip_bie_nivel1", referencedColumnName="det_bien_nivel1", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private DetalleBien dcTipoBien;
	
	//bi-directional many-to-one association to DetalleBien
	/**
	 * Estado de Conservacion.- Se manejara dentro de una catalogo bueno, malo, regular.
	 */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_est_conserv_fk", referencedColumnName="cab_bien_fk", unique=false, nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="det_bien_est_conserv_nivel1_fk", referencedColumnName="det_bien_nivel1", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private DetalleBien dcEstadoConservacion;

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
	
	/**
	 * @return the cabCatalogoTipoBien
	 */
	public String getCabCatalogoTipoBien() {
		return cabCatalogoTipoBien;
	}

	/**
	 * @param cabCatalogoTipoBien the cabCatalogoTipoBien to set
	 */
	public void setCabCatalogoTipoBien(String cabCatalogoTipoBien) {
		this.cabCatalogoTipoBien = cabCatalogoTipoBien;
	}

	/**
	 * @return the detCatalogoTipoBien
	 */
	public String getDetCatalogoTipoBien() {
		return detCatalogoTipoBien;
	}

	/**
	 * @param detCatalogoTipoBien the detCatalogoTipoBien to set
	 */
	public void setDetCatalogoTipoBien(String detCatalogoTipoBien) {
		this.detCatalogoTipoBien = detCatalogoTipoBien;
	}
	
//	/**
//	 * @return the cabEstadoBien
//	 */
//	public String getCabEstadoBien() {
//		return cabEstadoBien;
//	}
//
//	/**
//	 * @param cabEstadoBien the cabEstadoBien to set
//	 */
//	public void setCabEstadoBien(String cabEstadoBien) {
//		this.cabEstadoBien = cabEstadoBien;
//	}
//
//	/**
//	 * @return the detEstadoBien
//	 */
//	public String getDetEstadoBien() {
//		return detEstadoBien;
//	}
//
//	/**
//	 * @param detEstadoBien the detEstadoBien to set
//	 */
//	public void setDetEstadoBien(String detEstadoBien) {
//		this.detEstadoBien = detEstadoBien;
//	}

	/**
	 * @return the cabEstadoConservacion
	 */
	public String getCabEstadoConservacion() {
		return cabEstadoConservacion;
	}

	/**
	 * @param cabEstadoConservacion the cabEstadoConservacion to set
	 */
	public void setCabEstadoConservacion(String cabEstadoConservacion) {
		this.cabEstadoConservacion = cabEstadoConservacion;
	}

	/**
	 * @return the detEstadoConservacion
	 */
	public String getDetEstadoConservacion() {
		return detEstadoConservacion;
	}

	/**
	 * @param detEstadoConservacion the detEstadoConservacion to set
	 */
	public void setDetEstadoConservacion(String detEstadoConservacion) {
		this.detEstadoConservacion = detEstadoConservacion;
	}
	
	/**
	 * @return the dcEstadoConservacion
	 */
	public DetalleBien getDcEstadoConservacion() {
		return dcEstadoConservacion;
	}

	/**
	 * @param dcEstadoConservacion the dcEstadoConservacion to set
	 */
	public void setDcEstadoConservacion(DetalleBien dcEstadoConservacion) {
		this.dcEstadoConservacion = dcEstadoConservacion;
	}
	
 	/**
	 * @return the dcTipoBien
	 */
	public DetalleBien getDcTipoBien() {
		return dcTipoBien;
	}

	/**
	 * @param dcTipoBien the dcTipoBien to set
	 */
	public void setDcTipoBien(DetalleBien dcTipoBien) {
		this.dcTipoBien = dcTipoBien;
	}

	/**
	 * @return the bieFk
	 */
	public Integer getBieFk() {
		return bieFk;
	}

	/**
	 * @param bieFk the bieFk to set
	 */
	public void setBieFk(Integer bieFk) {
		this.bieFk = bieFk;
	}

	/**
	 * @return the empReasignadoFk
	 */
	public Integer getEmpReasignadoFk() {
		return empReasignadoFk;
	}

	/**
	 * @param empReasignadoFk the empReasignadoFk to set
	 */
	public void setEmpReasignadoFk(Integer empReasignadoFk) {
		this.empReasignadoFk = empReasignadoFk;
	}

	/**
	 * @return the empleadoReasignadoTbl
	 */
	public Empleado getEmpleadoReasignadoTbl() {
		return empleadoReasignadoTbl;
	}

	/**
	 * @param empleadoReasignadoTbl the empleadoReasignadoTbl to set
	 */
	public void setEmpleadoReasignadoTbl(Empleado empleadoReasignadoTbl) {
		this.empleadoReasignadoTbl = empleadoReasignadoTbl;
	}

	/**
	 * @return the empAsignadoFk
	 */
	public Integer getEmpAsignadoFk() {
		return empAsignadoFk;
	}

	/**
	 * @param empAsignadoFk the empAsignadoFk to set
	 */
	public void setEmpAsignadoFk(Integer empAsignadoFk) {
		this.empAsignadoFk = empAsignadoFk;
	}

	/**
	 * @return the empleadoAsignadoTbl
	 */
	public Empleado getEmpleadoAsignadoTbl() {
		return empleadoAsignadoTbl;
	}

	/**
	 * @param empleadoAsignadoTbl the empleadoAsignadoTbl to set
	 */
	public void setEmpleadoAsignadoTbl(Empleado empleadoAsignadoTbl) {
		this.empleadoAsignadoTbl = empleadoAsignadoTbl;
	}

}