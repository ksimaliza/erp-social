package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.Ingreso;
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
	private Double bieCostoVenta;

	@Column(name="bie_estado")
	private String bieEstado;
	
	@Column(name="bie_estado_uso")
	private String bieEstadoUso;
	
	@Temporal(TemporalType.DATE)
	@Column(name="bie_fecha_asig")
	private Date bieFechaAsig;

	@Column(name="bie_modelo")
	private String bieModelo;

	@Column(name="bie_nombre")
	private String bieNombre;

	@Column(name="cat_bien_pk")
	private Integer catBienPk;
	
	@Column(name="lin_bien_pk")
	private Integer linBienPk;
	
	@Column(name="mar_bien_pk")
	private Integer marBienPk;
	
	@NotNull (message="El bien debe estar asignado a una empresa")
	@Column(name="emr_pk")
	private Integer emrPk;
	
	@Column(name="bie_codigo")
	private String bieCodigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cat_bien_pk", referencedColumnName="cat_bien_pk", unique=false, nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="lin_bien_pk", referencedColumnName="lin_bien_pk", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private LineaBien lineaBien;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="mar_bien_pk", referencedColumnName="mar_bien_pk", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private MarcaBien marcaBien;
	
	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="emr_pk", referencedColumnName="emr_pk", unique=false, nullable=true, insertable=false, updatable=false)
	})
	private Empresa empresaTbl;
	
	//bi-directional many-to-one association to Inventario
	@ManyToOne
	@JoinColumn(name="inv_fk")
	private Inventario inventarioTbl;

	//bi-directional many-to-one association to Ingreso
	@OneToMany(mappedBy="bienTbl")
	private List<Ingreso> ingresoTbls;

	//bi-directional many-to-one association to Transaccion
	@OneToMany(mappedBy="bienTbl", cascade= CascadeType.ALL)
	private List<Transaccion> transaccionTbls;
	
	@Column(name="bie_notas")
	private String bieNotas;
	
	@Column(name="cab_bien_tip_ing_fk")
	private String cabCatalogoTipoIngresoBien;
	
	@Column(name="det_bien_tip_ing_nivel1")
	private String detCatalogoTipoIngresoBien;
	
	/**
	 * Tipo ingreso: Se definira como donacion o compra dentro de un catalogo.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="cab_bien_tip_ing_fk", referencedColumnName="cab_bien_fk", unique=false, nullable=true, insertable=false, updatable=false),
		@JoinColumn(name="det_bien_tip_ing_nivel1", referencedColumnName="det_bien_nivel1", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private DetalleBien dcTipoIngresoBien;
	
	@Transient
	private String npIdDcEstadoConservacion;
	
	@Transient
	private Integer npCantidadIngresada;
	
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

//	/**
//	 * @return the dcEstadoBien
//	 */
//	public DetalleBien getDcEstadoBien() {
//		return dcEstadoBien;
//	}
//
//	/**
//	 * @param dcEstadoBien the dcEstadoBien to set
//	 */
//	public void setDcEstadoBien(DetalleBien dcEstadoBien) {
//		this.dcEstadoBien = dcEstadoBien;
//	}


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

	/**
	 * @return the catBienPk
	 */
	public Integer getCatBienPk() {
		return catBienPk;
	}

	/**
	 * @param catBienPk the catBienPk to set
	 */
	public void setCatBienPk(Integer catBienPk) {
		this.catBienPk = catBienPk;
	}

	/**
	 * @return the linBienPk
	 */
	public Integer getLinBienPk() {
		return linBienPk;
	}

	/**
	 * @param linBienPk the linBienPk to set
	 */
	public void setLinBienPk(Integer linBienPk) {
		this.linBienPk = linBienPk;
	}

	/**
	 * @return the marBienPk
	 */
	public Integer getMarBienPk() {
		return marBienPk;
	}

	/**
	 * @param marBienPk the marBienPk to set
	 */
	public void setMarBienPk(Integer marBienPk) {
		this.marBienPk = marBienPk;
	}

	/**
	 * @return the lineaBien
	 */
	public LineaBien getLineaBien() {
		return lineaBien;
	}

	/**
	 * @param lineaBien the lineaBien to set
	 */
	public void setLineaBien(LineaBien lineaBien) {
		this.lineaBien = lineaBien;
	}

	/**
	 * @return the marcaBien
	 */
	public MarcaBien getMarcaBien() {
		return marcaBien;
	}

	/**
	 * @param marcaBien the marcaBien to set
	 */
	public void setMarcaBien(MarcaBien marcaBien) {
		this.marcaBien = marcaBien;
	}

	/**
	 * @return the emrPk
	 */
	public Integer getEmrPk() {
		return emrPk;
	}

	/**
	 * @param emrPk the emrPk to set
	 */
	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}

	/**
	 * @return the empresaTbl
	 */
	public Empresa getEmpresaTbl() {
		return empresaTbl;
	}

	/**
	 * @param empresaTbl the empresaTbl to set
	 */
	public void setEmpresaTbl(Empresa empresaTbl) {
		this.empresaTbl = empresaTbl;
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
	 * @return the npIdDcEstadoConservacion
	 */
	public String getNpIdDcEstadoConservacion() {
		return npIdDcEstadoConservacion;
	}

	/**
	 * @param npIdDcEstadoConservacion the npIdDcEstadoConservacion to set
	 */
	public void setNpIdDcEstadoConservacion(String npIdDcEstadoConservacion) {
		this.npIdDcEstadoConservacion = npIdDcEstadoConservacion;
	}

	/**
	 * @return the bieEstadoUso
	 */
	public String getBieEstadoUso() {
		return bieEstadoUso;
	}

	/**
	 * @param bieEstadoUso the bieEstadoUso to set
	 */
	public void setBieEstadoUso(String bieEstadoUso) {
		this.bieEstadoUso = bieEstadoUso;
	}

	/**
	 * @return the cabCatalogoTipoIngresoBien
	 */
	public String getCabCatalogoTipoIngresoBien() {
		return cabCatalogoTipoIngresoBien;
	}

	/**
	 * @param cabCatalogoTipoIngresoBien the cabCatalogoTipoIngresoBien to set
	 */
	public void setCabCatalogoTipoIngresoBien(String cabCatalogoTipoIngresoBien) {
		this.cabCatalogoTipoIngresoBien = cabCatalogoTipoIngresoBien;
	}

	/**
	 * @return the detCatalogoTipoIngresoBien
	 */
	public String getDetCatalogoTipoIngresoBien() {
		return detCatalogoTipoIngresoBien;
	}

	/**
	 * @param detCatalogoTipoIngresoBien the detCatalogoTipoIngresoBien to set
	 */
	public void setDetCatalogoTipoIngresoBien(String detCatalogoTipoIngresoBien) {
		this.detCatalogoTipoIngresoBien = detCatalogoTipoIngresoBien;
	}

	/**
	 * @return the dcTipoIngresoBien
	 */
	public DetalleBien getDcTipoIngresoBien() {
		return dcTipoIngresoBien;
	}

	/**
	 * @param dcTipoIngresoBien the dcTipoIngresoBien to set
	 */
	public void setDcTipoIngresoBien(DetalleBien dcTipoIngresoBien) {
		this.dcTipoIngresoBien = dcTipoIngresoBien;
	}


	/**
	 * @return the bieCostoVenta
	 */
	public Double getBieCostoVenta() {
		return bieCostoVenta;
	}

	/**
	 * @param bieCostoVenta the bieCostoVenta to set
	 */
	public void setBieCostoVenta(Double bieCostoVenta) {
		this.bieCostoVenta = bieCostoVenta;
	}

	/**
	 * @return the npCantidadIngresada
	 */
	public Integer getNpCantidadIngresada() {
		return npCantidadIngresada;
	}

	/**
	 * @param npCantidadIngresada the npCantidadIngresada to set
	 */
	public void setNpCantidadIngresada(Integer npCantidadIngresada) {
		this.npCantidadIngresada = npCantidadIngresada;
	}

}