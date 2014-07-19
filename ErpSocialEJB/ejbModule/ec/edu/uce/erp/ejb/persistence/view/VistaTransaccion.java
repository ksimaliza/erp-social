package ec.edu.uce.erp.ejb.persistence.view;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the transaccion_view database table.
 * 
 */
@Entity
@Table(name="transaccion_view")
@NamedQuery(name="VistaTransaccion.findAll", query="SELECT v FROM VistaTransaccion v")
public class VistaTransaccion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="tra_pk")
	private Integer traPk;

	@Column(name="bie_fk")
	private Integer bieFk;

	@Column(name="cab_bien_est_conserv_fk")
	private String cabBienEstConservFk;

	@Column(name="cab_bien_tip_bie_fk")
	private String cabBienTipBieFk;

	@Column(name="det_bien_est_conserv_nivel1_fk")
	private String detBienEstConservNivel1Fk;

	@Column(name="det_bien_tip_bie_nivel1")
	private String detBienTipBieNivel1;
	
	@Column(name="cab_bien_tip_baj_fk")
	private String cabBienTipoBajaFk;
	
	@Column(name="det_bien_tip_baj_nivel1")
	private String detBienTipoBajaNivel1;

	@Column(name="emp_asignado_fk")
	private Integer empAsignadoFk;

	@Column(name="emp_reasignado_fk")
	private Integer empReasignadoFk;

	@Column(name="est_conserv_descripcion")
	private String estConservDescripcion;

	@Column(name="nombre_emp_asignado")
	private String nombreEmpAsignado;

	@Column(name="nombre_emp_reasignado")
	private String nombreEmpReasignado;

	@Column(name="tip_bie_descripcion")
	private String tipBieDescripcion;
	
	@Column(name="tipo_baja_descripcion")
	private String tipoBajaDescripcion;

	@Column(name="tra_estado")
	private String traEstado;

	@Column(name="tra_fecha_fin")
	private Timestamp traFechaFin;

	@Column(name="tra_fecha_inicio")
	private Timestamp traFechaInicio;
	
	public VistaTransaccion() {
	}

	public Integer getBieFk() {
		return this.bieFk;
	}

	public void setBieFk(Integer bieFk) {
		this.bieFk = bieFk;
	}

	public String getCabBienEstConservFk() {
		return this.cabBienEstConservFk;
	}

	public void setCabBienEstConservFk(String cabBienEstConservFk) {
		this.cabBienEstConservFk = cabBienEstConservFk;
	}

	public String getCabBienTipBieFk() {
		return this.cabBienTipBieFk;
	}

	public void setCabBienTipBieFk(String cabBienTipBieFk) {
		this.cabBienTipBieFk = cabBienTipBieFk;
	}

	public String getDetBienEstConservNivel1Fk() {
		return this.detBienEstConservNivel1Fk;
	}

	public void setDetBienEstConservNivel1Fk(String detBienEstConservNivel1Fk) {
		this.detBienEstConservNivel1Fk = detBienEstConservNivel1Fk;
	}

	public String getDetBienTipBieNivel1() {
		return this.detBienTipBieNivel1;
	}

	public void setDetBienTipBieNivel1(String detBienTipBieNivel1) {
		this.detBienTipBieNivel1 = detBienTipBieNivel1;
	}

	public Integer getEmpAsignadoFk() {
		return this.empAsignadoFk;
	}

	public void setEmpAsignadoFk(Integer empAsignadoFk) {
		this.empAsignadoFk = empAsignadoFk;
	}

	public Integer getEmpReasignadoFk() {
		return this.empReasignadoFk;
	}

	public void setEmpReasignadoFk(Integer empReasignadoFk) {
		this.empReasignadoFk = empReasignadoFk;
	}

	public String getEstConservDescripcion() {
		return this.estConservDescripcion;
	}

	public void setEstConservDescripcion(String estConservDescripcion) {
		this.estConservDescripcion = estConservDescripcion;
	}

	public String getNombreEmpAsignado() {
		return this.nombreEmpAsignado;
	}

	public void setNombreEmpAsignado(String nombreEmpAsignado) {
		this.nombreEmpAsignado = nombreEmpAsignado;
	}

	public String getNombreEmpReasignado() {
		return this.nombreEmpReasignado;
	}

	public void setNombreEmpReasignado(String nombreEmpReasignado) {
		this.nombreEmpReasignado = nombreEmpReasignado;
	}

	public String getTipBieDescripcion() {
		return this.tipBieDescripcion;
	}

	public void setTipBieDescripcion(String tipBieDescripcion) {
		this.tipBieDescripcion = tipBieDescripcion;
	}

	public String getTraEstado() {
		return this.traEstado;
	}

	public void setTraEstado(String traEstado) {
		this.traEstado = traEstado;
	}

	public Timestamp getTraFechaFin() {
		return this.traFechaFin;
	}

	public void setTraFechaFin(Timestamp traFechaFin) {
		this.traFechaFin = traFechaFin;
	}

	public Timestamp getTraFechaInicio() {
		return this.traFechaInicio;
	}

	public void setTraFechaInicio(Timestamp traFechaInicio) {
		this.traFechaInicio = traFechaInicio;
	}

	public Integer getTraPk() {
		return this.traPk;
	}

	public void setTraPk(Integer traPk) {
		this.traPk = traPk;
	}

}