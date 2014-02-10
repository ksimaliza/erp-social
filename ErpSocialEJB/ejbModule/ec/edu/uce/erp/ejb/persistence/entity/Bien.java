package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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


/**
 * The persistent class for the bien_tbl database table.
 * 
 */
@Entity
@Table(name="bien_tbl")
@NamedQuery(name="Bien.findAll", query="SELECT b FROM Bien b")
public class Bien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BIEN_TBL_BIEPK_GENERATOR", sequenceName="BIEN_TBL_BIE_PK_SEQ")
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

	//bi-directional many-to-one association to DetalleBien
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_tip_bie_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_tip_bie_nivel1", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBien detalleBienTbl1;

	//bi-directional many-to-one association to DetalleBien
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_est_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_est_nivel1", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBien detalleBienTbl2;

	//bi-directional many-to-one association to DetalleBien
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_est_conserv_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_est_conserv_nivel1_fk", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBien detalleBienTbl3;

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

	public DetalleBien getDetalleBienTbl1() {
		return this.detalleBienTbl1;
	}

	public void setDetalleBienTbl1(DetalleBien detalleBienTbl1) {
		this.detalleBienTbl1 = detalleBienTbl1;
	}

	public DetalleBien getDetalleBienTbl2() {
		return this.detalleBienTbl2;
	}

	public void setDetalleBienTbl2(DetalleBien detalleBienTbl2) {
		this.detalleBienTbl2 = detalleBienTbl2;
	}

	public DetalleBien getDetalleBienTbl3() {
		return this.detalleBienTbl3;
	}

	public void setDetalleBienTbl3(DetalleBien detalleBienTbl3) {
		this.detalleBienTbl3 = detalleBienTbl3;
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

}