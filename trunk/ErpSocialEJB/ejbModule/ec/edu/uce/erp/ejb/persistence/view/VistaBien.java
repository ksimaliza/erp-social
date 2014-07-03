package ec.edu.uce.erp.ejb.persistence.view;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the bien_view database table.
 * 
 */
@Entity
@Table(name="bien_view")
@NamedQuery(name="VistaBien.findAll", query="SELECT v FROM VistaBien v")
public class VistaBien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="bie_color")
	private String bieColor;

	@Column(name="bie_costo_venta")
	private BigDecimal bieCostoVenta;

	@Column(name="bie_estado")
	private String bieEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="bie_fecha_asig")
	private Date bieFechaAsig;

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

//	@Column(name="cab_bien_est_fk")
//	private String cabBienEstFk;
	
	@Column(name = "cab_bien_est_conserv_fk")
	private String cabBienEstConservFk;

	@Column(name="cab_bien_tip_bie_fk")
	private String cabBienTipBieFk;

	@Column(name="cat_bien_nombre")
	private String catBienNombre;

	@Column(name="cat_bien_estado")
	private String catBienEstado;

	@Column(name="cat_bien_pk")
	private Integer catBienPk;

	@Column(name="det_bien_est_conserv_nivel1_fk")
	private String detBienEstConservNivel1Fk;

//	@Column(name="det_bien_est_nivel1")
//	private String detBienEstNivel1;

	@Column(name="det_bien_tip_bie_nivel1")
	private String detBienTipBieNivel1;

	@Column(name="emr_pk")
	private Integer emrPk;

	@Column(name="lin_bien_nombre")
	private String linBienNombre;

	@Column(name="lin_bien_estado")
	private String linBienEstado;

	@Column(name="lin_bien_pk")
	private Integer linBienPk;

	@Column(name="mar_bien_nombre")
	private String marBienNombre;

	@Column(name="mar_bien_estado")
	private String marBienEstado;

	@Column(name="mar_bien_pk")
	private Integer marBienPk;
	
	@Column(name="tra_estado")
	private String traEstado;
	
	@Column(name = "emp_asignado_fk")
	private Integer empAsignadoFk;
	
	@Column(name = "emp_reasignado_fk")
	private Integer empReasignadoFk;
	
	@Column(name="bie_codigo")
	private String bieCodigo;

	@Column(name="tra_descripcion")
	private String traDescripcion;
	
	@Column(name="nombres_completos")
	private String nombresCompletos;
	
	@Column(name="per_ci")
	private String perCi;
	
	@Transient
	@Temporal(TemporalType.DATE)
	private Date bieFechaReasig;
	
	@Transient
	private Boolean npVerAsignarBien = Boolean.FALSE;
	
	@Transient
	private Boolean npVerTrasladoBien = Boolean.FALSE;
	
	@Transient
	private Boolean npVerBajaBien = Boolean.FALSE;
	
	@Transient
	private Boolean npVerDevolverBien = Boolean.FALSE;

	public VistaBien() {
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

	public Date getBieFechaAsig() {
		return this.bieFechaAsig;
	}

	public void setBieFechaAsig(Date bieFechaAsig) {
		this.bieFechaAsig = bieFechaAsig;
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

//	public String getCabBienEstFk() {
//		return this.cabBienEstFk;
//	}
//
//	public void setCabBienEstFk(String cabBienEstFk) {
//		this.cabBienEstFk = cabBienEstFk;
//	}

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

//	public String getDetBienEstNivel1() {
//		return this.detBienEstNivel1;
//	}
//
//	public void setDetBienEstNivel1(String detBienEstNivel1) {
//		this.detBienEstNivel1 = detBienEstNivel1;
//	}

	public String getDetBienTipBieNivel1() {
		return this.detBienTipBieNivel1;
	}

	public void setDetBienTipBieNivel1(String detBienTipBieNivel1) {
		this.detBienTipBieNivel1 = detBienTipBieNivel1;
	}

	public Integer getEmrPk() {
		return this.emrPk;
	}

	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}

	public String getLinBienEstado() {
		return this.linBienEstado;
	}

	public void setLinBienEstado(String linBienEstado) {
		this.linBienEstado = linBienEstado;
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

	public Integer getMarBienPk() {
		return this.marBienPk;
	}

	public void setMarBienPk(Integer marBienPk) {
		this.marBienPk = marBienPk;
	}

	/**
	 * @return the cabBienEstConservFk
	 */
	public String getCabBienEstConservFk() {
		return cabBienEstConservFk;
	}

	/**
	 * @param cabBienEstConservFk the cabBienEstConservFk to set
	 */
	public void setCabBienEstConservFk(String cabBienEstConservFk) {
		this.cabBienEstConservFk = cabBienEstConservFk;
	}

	/**
	 * @return the catBienNombre
	 */
	public String getCatBienNombre() {
		return catBienNombre;
	}

	/**
	 * @param catBienNombre the catBienNombre to set
	 */
	public void setCatBienNombre(String catBienNombre) {
		this.catBienNombre = catBienNombre;
	}

	/**
	 * @return the linBienNombre
	 */
	public String getLinBienNombre() {
		return linBienNombre;
	}

	/**
	 * @param linBienNombre the linBienNombre to set
	 */
	public void setLinBienNombre(String linBienNombre) {
		this.linBienNombre = linBienNombre;
	}

	/**
	 * @return the marBienNombre
	 */
	public String getMarBienNombre() {
		return marBienNombre;
	}

	/**
	 * @param marBienNombre the marBienNombre to set
	 */
	public void setMarBienNombre(String marBienNombre) {
		this.marBienNombre = marBienNombre;
	}

	/**
	 * @return the npVerTrasladoBien
	 */
	public Boolean getNpVerTrasladoBien() {
		return npVerTrasladoBien;
	}

	/**
	 * @param npVerTrasladoBien the npVerTrasladoBien to set
	 */
	public void setNpVerTrasladoBien(Boolean npVerTrasladoBien) {
		this.npVerTrasladoBien = npVerTrasladoBien;
	}

	/**
	 * @return the npVerAsignarBien
	 */
	public Boolean getNpVerAsignarBien() {
		return npVerAsignarBien;
	}

	/**
	 * @param npVerAsignarBien the npVerAsignarBien to set
	 */
	public void setNpVerAsignarBien(Boolean npVerAsignarBien) {
		this.npVerAsignarBien = npVerAsignarBien;
	}

	/**
	 * @return the traEstado
	 */
	public String getTraEstado() {
		return traEstado;
	}

	/**
	 * @param traEstado the traEstado to set
	 */
	public void setTraEstado(String traEstado) {
		this.traEstado = traEstado;
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
	 * @return the bieCodigo
	 */
	public String getBieCodigo() {
		return bieCodigo;
	}

	/**
	 * @param bieCodigo the bieCodigo to set
	 */
	public void setBieCodigo(String bieCodigo) {
		this.bieCodigo = bieCodigo;
	}

	/**
	 * @return the bieFechaReasig
	 */
	public Date getBieFechaReasig() {
		return bieFechaReasig;
	}

	/**
	 * @param bieFechaReasig the bieFechaReasig to set
	 */
	public void setBieFechaReasig(Date bieFechaReasig) {
		this.bieFechaReasig = bieFechaReasig;
	}

	/**
	 * @return the npVerBajaBien
	 */
	public Boolean getNpVerBajaBien() {
		return npVerBajaBien;
	}

	/**
	 * @param npVerBajaBien the npVerBajaBien to set
	 */
	public void setNpVerBajaBien(Boolean npVerBajaBien) {
		this.npVerBajaBien = npVerBajaBien;
	}

	/**
	 * @return the nombresCompletos
	 */
	public String getNombresCompletos() {
		return nombresCompletos;
	}

	/**
	 * @param nombresCompletos the nombresCompletos to set
	 */
	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}

	/**
	 * @return the perCi
	 */
	public String getPerCi() {
		return perCi;
	}

	/**
	 * @param perCi the perCi to set
	 */
	public void setPerCi(String perCi) {
		this.perCi = perCi;
	}

	/**
	 * @return the traDescripcion
	 */
	public String getTraDescripcion() {
		return traDescripcion;
	}

	/**
	 * @param traDescripcion the traDescripcion to set
	 */
	public void setTraDescripcion(String traDescripcion) {
		this.traDescripcion = traDescripcion;
	}

	/**
	 * @return the npVerDevolverBien
	 */
	public Boolean getNpVerDevolverBien() {
		return npVerDevolverBien;
	}

	/**
	 * @param npVerDevolverBien the npVerDevolverBien to set
	 */
	public void setNpVerDevolverBien(Boolean npVerDevolverBien) {
		this.npVerDevolverBien = npVerDevolverBien;
	}

}