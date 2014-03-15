package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.edu.uce.erp.ejb.persistence.entity.Ingreso;
import ec.edu.uce.erp.ejb.persistence.entity.Transaccion;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the bien_tbl database table.
 * 
 */
@Entity
@Table(name="bien_tbl")
@NamedQuery(name="Bien.findAll", query="SELECT b FROM Bien b")
public class Bien extends AuditoriaUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BIEN_TBL_BIEPK_GENERATOR", sequenceName="BIEN_TBL_BIE_PK_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BIEN_TBL_BIEPK_GENERATOR")
	@Column(name="bie_pk")
	private Integer biePk;

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

	@Column(name="bie_ubicacion")
	private String bieUbicacion;
	
	@Column(name="cab_bien_tip_bie_fk")
	private String cabCatalogoTipoBien;
	
	@Column(name="det_bien_tip_bie_nivel1")
	private String detCatalogoTipoBien;
	
	@Column(name="cab_bien_est_fk")
	private String cabEstadoBien;
	
	@Column(name="det_bien_est_nivel1")
	private String detEstadoBien;
	
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
	 * Estado del bien: Se definira como activo, inactivo dentro de un catalogo.
	 */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_est_fk", referencedColumnName="cab_bien_fk", unique=false, nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="det_bien_est_nivel1", referencedColumnName="det_bien_nivel1", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private DetalleBien dcEstadoBien;

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

	//bi-directional many-to-one association to Inventario
	@ManyToOne
	@JoinColumn(name="inv_fk")
	private Inventario inventarioTbl;

	//bi-directional many-to-one association to Ingreso
	@OneToMany(mappedBy="bienTbl")
	private List<Ingreso> ingresoTbls;

	//bi-directional many-to-one association to Transaccion
	@OneToMany(mappedBy="bienTbl")
	private List<Transaccion> transaccionTbls;
	
	@Column(name="bie_notas")
	private String bieNotas;

	public Bien() {
	}

	public Integer getBiePk() {
		return this.biePk;
	}

	public void setBiePk(Integer biePk) {
		this.biePk = biePk;
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

	public String getBieUbicacion() {
		return this.bieUbicacion;
	}

	public void setBieUbicacion(String bieUbicacion) {
		this.bieUbicacion = bieUbicacion;
	}

	public Inventario getInventarioTbl() {
		return this.inventarioTbl;
	}

	public void setInventarioTbl(Inventario inventarioTbl) {
		this.inventarioTbl = inventarioTbl;
	}

	public List<Ingreso> getIngresoTbls() {
		return this.ingresoTbls;
	}

	public void setIngresoTbls(List<Ingreso> ingresoTbls) {
		this.ingresoTbls = ingresoTbls;
	}

	public Ingreso addIngresoTbl(Ingreso ingresoTbl) {
		getIngresoTbls().add(ingresoTbl);
		ingresoTbl.setBienTbl(this);

		return ingresoTbl;
	}

	public Ingreso removeIngresoTbl(Ingreso ingresoTbl) {
		getIngresoTbls().remove(ingresoTbl);
		ingresoTbl.setBienTbl(null);

		return ingresoTbl;
	}

	public List<Transaccion> getTransaccionTbls() {
		return this.transaccionTbls;
	}

	public void setTransaccionTbls(List<Transaccion> transaccionTbls) {
		this.transaccionTbls = transaccionTbls;
	}

	public Transaccion addTransaccionTbl(Transaccion transaccionTbl) {
		getTransaccionTbls().add(transaccionTbl);
		transaccionTbl.setBienTbl(this);

		return transaccionTbl;
	}

	public Transaccion removeTransaccionTbl(Transaccion transaccionTbl) {
		getTransaccionTbls().remove(transaccionTbl);
		transaccionTbl.setBienTbl(null);

		return transaccionTbl;
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
	 * @return the dcEstadoBien
	 */
	public DetalleBien getDcEstadoBien() {
		return dcEstadoBien;
	}

	/**
	 * @param dcEstadoBien the dcEstadoBien to set
	 */
	public void setDcEstadoBien(DetalleBien dcEstadoBien) {
		this.dcEstadoBien = dcEstadoBien;
	}

	/**
	 * @return the cabEstadoBien
	 */
	public String getCabEstadoBien() {
		return cabEstadoBien;
	}

	/**
	 * @param cabEstadoBien the cabEstadoBien to set
	 */
	public void setCabEstadoBien(String cabEstadoBien) {
		this.cabEstadoBien = cabEstadoBien;
	}

	/**
	 * @return the detEstadoBien
	 */
	public String getDetEstadoBien() {
		return detEstadoBien;
	}

	/**
	 * @param detEstadoBien the detEstadoBien to set
	 */
	public void setDetEstadoBien(String detEstadoBien) {
		this.detEstadoBien = detEstadoBien;
	}

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
	 * @return the bieNotas
	 */
	public String getBieNotas() {
		return bieNotas;
	}

	/**
	 * @param bieNotas the bieNotas to set
	 */
	public void setBieNotas(String bieNotas) {
		this.bieNotas = bieNotas;
	}

}