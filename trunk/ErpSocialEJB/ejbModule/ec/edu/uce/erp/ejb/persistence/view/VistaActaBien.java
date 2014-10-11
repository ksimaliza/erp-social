package ec.edu.uce.erp.ejb.persistence.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the acta_bien_view database table.
 * 
 */
@Entity
@Table(name="acta_bien_view")
@NamedQuery(name="VistaActaBien.findAll", query="SELECT v FROM VistaActaBien v")
public class VistaActaBien implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="act_bie_pk")
	private Integer actBiePk;

	@Temporal(TemporalType.DATE)
	@Column(name="act_bie_fecha_gen")
	private Date actBieFechaGen;

	@Column(name="act_bie_num")
	private String actBieNum;

	@Column(name="bie_codigo")
	private String bieCodigo;

	@Column(name="bie_color")
	private String bieColor;

	@Column(name="bie_costo_venta")
	private BigDecimal bieCostoVenta;

	@Column(name="bie_estado")
	private String bieEstado;

	@Column(name="bie_estado_string")
	private String bieEstadoString;

	@Column(name="bie_estado_uso")
	private String bieEstadoUso;

	@Column(name="bie_estado_uso_string")
	private String bieEstadoUsoString;

	@Column(name="bie_modelo")
	private String bieModelo;

	@Column(name="bie_nombre")
	private String bieNombre;

	@Column(name="bie_notas")
	private String bieNotas;

	@Id
	@Column(name="bie_pk")
	private Integer biePk;

	@Column(name="bie_ubicacion")
	private String bieUbicacion;

	@Column(name="cab_bien_est_conserv_fk")
	private String cabBienEstConservFk;

	@Column(name="cab_bien_tip_baj_fk")
	private String cabBienTipBajFk;

	@Column(name="cab_bien_tip_bie_fk")
	private String cabBienTipBieFk;

	@Column(name="cat_bien_estado")
	private String catBienEstado;

	@Column(name="cat_bien_nombre")
	private String catBienNombre;

	@Column(name="cat_bien_pk")
	private Integer catBienPk;

	@Column(name="det_bien_est_conserv_nivel1_fk")
	private String detBienEstConservNivel1Fk;

	@Column(name="det_bien_tip_baj_nivel1")
	private String detBienTipBajNivel1;

	@Column(name="det_bien_tip_bie_nivel1")
	private String detBienTipBieNivel1;

	@Column(name="emp_asignado_fk")
	private Integer empAsignadoFk;

	@Column(name="emp_reasignado_fk")
	private Integer empReasignadoFk;

	@Column(name="emr_pk")
	private Integer emrPk;

	@Column(name="estado_conservacion_bien")
	private String estadoConservacionBien;

	@Column(name="lin_bien_estado")
	private String linBienEstado;

	@Column(name="lin_bien_nombre")
	private String linBienNombre;

	@Column(name="lin_bien_pk")
	private Integer linBienPk;

	@Column(name="mar_bien_estado")
	private String marBienEstado;

	@Column(name="mar_bien_nombre")
	private String marBienNombre;

	@Column(name="mar_bien_pk")
	private Integer marBienPk;

	@Column(name="nombres_completos")
	private String nombresCompletos;

	@Column(name="per_ci")
	private String perCi;

	@Column(name="tipo_ingreso_bien")
	private String tipoIngresoBien;

	@Column(name="tra_descripcion")
	private String traDescripcion;

	@Column(name="tra_estado")
	private String traEstado;

	@Column(name="tra_fecha_inicio")
	private Timestamp traFechaInicio;
	
	@Transient
	private Collection<Integer> npColActBiePk;

	public VistaActaBien() {
	}

	public Date getActBieFechaGen() {
		return this.actBieFechaGen;
	}

	public void setActBieFechaGen(Date actBieFechaGen) {
		this.actBieFechaGen = actBieFechaGen;
	}

	public String getActBieNum() {
		return this.actBieNum;
	}

	public void setActBieNum(String actBieNum) {
		this.actBieNum = actBieNum;
	}

	public String getBieCodigo() {
		return this.bieCodigo;
	}

	public void setBieCodigo(String bieCodigo) {
		this.bieCodigo = bieCodigo;
	}

	public String getBieColor() {
		return this.bieColor;
	}

	public void setBieColor(String bieColor) {
		this.bieColor = bieColor;
	}

	public BigDecimal getBieCostoVenta() {
		return this.bieCostoVenta;
	}

	public void setBieCostoVenta(BigDecimal bieCostoVenta) {
		this.bieCostoVenta = bieCostoVenta;
	}

	public String getBieEstado() {
		return this.bieEstado;
	}

	public void setBieEstado(String bieEstado) {
		this.bieEstado = bieEstado;
	}

	public String getBieEstadoString() {
		return this.bieEstadoString;
	}

	public void setBieEstadoString(String bieEstadoString) {
		this.bieEstadoString = bieEstadoString;
	}

	public String getBieEstadoUso() {
		return this.bieEstadoUso;
	}

	public void setBieEstadoUso(String bieEstadoUso) {
		this.bieEstadoUso = bieEstadoUso;
	}

	public String getBieEstadoUsoString() {
		return this.bieEstadoUsoString;
	}

	public void setBieEstadoUsoString(String bieEstadoUsoString) {
		this.bieEstadoUsoString = bieEstadoUsoString;
	}

	public String getBieModelo() {
		return this.bieModelo;
	}

	public void setBieModelo(String bieModelo) {
		this.bieModelo = bieModelo;
	}

	public String getBieNombre() {
		return this.bieNombre;
	}

	public void setBieNombre(String bieNombre) {
		this.bieNombre = bieNombre;
	}

	public String getBieNotas() {
		return this.bieNotas;
	}

	public void setBieNotas(String bieNotas) {
		this.bieNotas = bieNotas;
	}

	public Integer getBiePk() {
		return this.biePk;
	}

	public void setBiePk(Integer biePk) {
		this.biePk = biePk;
	}

	public String getBieUbicacion() {
		return this.bieUbicacion;
	}

	public void setBieUbicacion(String bieUbicacion) {
		this.bieUbicacion = bieUbicacion;
	}

	public String getCabBienEstConservFk() {
		return this.cabBienEstConservFk;
	}

	public void setCabBienEstConservFk(String cabBienEstConservFk) {
		this.cabBienEstConservFk = cabBienEstConservFk;
	}

	public String getCabBienTipBajFk() {
		return this.cabBienTipBajFk;
	}

	public void setCabBienTipBajFk(String cabBienTipBajFk) {
		this.cabBienTipBajFk = cabBienTipBajFk;
	}

	public String getCabBienTipBieFk() {
		return this.cabBienTipBieFk;
	}

	public void setCabBienTipBieFk(String cabBienTipBieFk) {
		this.cabBienTipBieFk = cabBienTipBieFk;
	}

	public String getCatBienEstado() {
		return this.catBienEstado;
	}

	public void setCatBienEstado(String catBienEstado) {
		this.catBienEstado = catBienEstado;
	}

	public String getCatBienNombre() {
		return this.catBienNombre;
	}

	public void setCatBienNombre(String catBienNombre) {
		this.catBienNombre = catBienNombre;
	}

	public Integer getCatBienPk() {
		return this.catBienPk;
	}

	public void setCatBienPk(Integer catBienPk) {
		this.catBienPk = catBienPk;
	}

	public String getDetBienEstConservNivel1Fk() {
		return this.detBienEstConservNivel1Fk;
	}

	public void setDetBienEstConservNivel1Fk(String detBienEstConservNivel1Fk) {
		this.detBienEstConservNivel1Fk = detBienEstConservNivel1Fk;
	}

	public String getDetBienTipBajNivel1() {
		return this.detBienTipBajNivel1;
	}

	public void setDetBienTipBajNivel1(String detBienTipBajNivel1) {
		this.detBienTipBajNivel1 = detBienTipBajNivel1;
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

	public Integer getEmrPk() {
		return this.emrPk;
	}

	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}

	public String getEstadoConservacionBien() {
		return this.estadoConservacionBien;
	}

	public void setEstadoConservacionBien(String estadoConservacionBien) {
		this.estadoConservacionBien = estadoConservacionBien;
	}

	public String getLinBienEstado() {
		return this.linBienEstado;
	}

	public void setLinBienEstado(String linBienEstado) {
		this.linBienEstado = linBienEstado;
	}

	public String getLinBienNombre() {
		return this.linBienNombre;
	}

	public void setLinBienNombre(String linBienNombre) {
		this.linBienNombre = linBienNombre;
	}

	public Integer getLinBienPk() {
		return this.linBienPk;
	}

	public void setLinBienPk(Integer linBienPk) {
		this.linBienPk = linBienPk;
	}

	public String getMarBienEstado() {
		return this.marBienEstado;
	}

	public void setMarBienEstado(String marBienEstado) {
		this.marBienEstado = marBienEstado;
	}

	public String getMarBienNombre() {
		return this.marBienNombre;
	}

	public void setMarBienNombre(String marBienNombre) {
		this.marBienNombre = marBienNombre;
	}

	public Integer getMarBienPk() {
		return this.marBienPk;
	}

	public void setMarBienPk(Integer marBienPk) {
		this.marBienPk = marBienPk;
	}

	public String getNombresCompletos() {
		return this.nombresCompletos;
	}

	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}

	public String getPerCi() {
		return this.perCi;
	}

	public void setPerCi(String perCi) {
		this.perCi = perCi;
	}

	public String getTipoIngresoBien() {
		return this.tipoIngresoBien;
	}

	public void setTipoIngresoBien(String tipoIngresoBien) {
		this.tipoIngresoBien = tipoIngresoBien;
	}

	public String getTraDescripcion() {
		return this.traDescripcion;
	}

	public void setTraDescripcion(String traDescripcion) {
		this.traDescripcion = traDescripcion;
	}

	public String getTraEstado() {
		return this.traEstado;
	}

	public void setTraEstado(String traEstado) {
		this.traEstado = traEstado;
	}

	public Timestamp getTraFechaInicio() {
		return this.traFechaInicio;
	}

	public void setTraFechaInicio(Timestamp traFechaInicio) {
		this.traFechaInicio = traFechaInicio;
	}

	/**
	 * @return the actBiePk
	 */
	public Integer getActBiePk() {
		return actBiePk;
	}

	/**
	 * @param actBiePk the actBiePk to set
	 */
	public void setActBiePk(Integer actBiePk) {
		this.actBiePk = actBiePk;
	}

	/**
	 * @return the npColActBiePk
	 */
	public Collection<Integer> getNpColActBiePk() {
		return npColActBiePk;
	}

	/**
	 * @param npColActBiePk the npColActBiePk to set
	 */
	public void setNpColActBiePk(Collection<Integer> npColActBiePk) {
		this.npColActBiePk = npColActBiePk;
	}

}