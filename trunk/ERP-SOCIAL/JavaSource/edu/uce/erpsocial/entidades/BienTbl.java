package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the bien_tbl database table.
 * 
 */
@Entity
@Table(name="bien_tbl")
public class BienTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="bie_pk")
	private Integer biePk;

	@Column(name="bie_color")
	private String bieColor;

	@Column(name="bie_costo_venta")
	private BigDecimal bieCostoVenta;

	@Column(name="bie_modelo")
	private String bieModelo;

	@Column(name="bie_nombre")
	private String bieNombre;

	//bi-directional many-to-one association to DetalleBienTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_est_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_est_nivel1", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBienTbl detalleBienTbl1;

	//bi-directional many-to-one association to DetalleBienTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_tip_bie_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_tip_bie_nivel1", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBienTbl detalleBienTbl2;

	//bi-directional many-to-one association to IngresoTbl
	@OneToMany(mappedBy="bienTbl")
	private Set<IngresoTbl> ingresoTbls;

	//bi-directional many-to-one association to InventarioTbl
	@OneToMany(mappedBy="bienTbl")
	private Set<InventarioTbl> inventarioTbls;

    public BienTbl() {
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

	public DetalleBienTbl getDetalleBienTbl1() {
		return this.detalleBienTbl1;
	}

	public void setDetalleBienTbl1(DetalleBienTbl detalleBienTbl1) {
		this.detalleBienTbl1 = detalleBienTbl1;
	}
	
	public DetalleBienTbl getDetalleBienTbl2() {
		return this.detalleBienTbl2;
	}

	public void setDetalleBienTbl2(DetalleBienTbl detalleBienTbl2) {
		this.detalleBienTbl2 = detalleBienTbl2;
	}
	
	public Set<IngresoTbl> getIngresoTbls() {
		return this.ingresoTbls;
	}

	public void setIngresoTbls(Set<IngresoTbl> ingresoTbls) {
		this.ingresoTbls = ingresoTbls;
	}
	
	public Set<InventarioTbl> getInventarioTbls() {
		return this.inventarioTbls;
	}

	public void setInventarioTbls(Set<InventarioTbl> inventarioTbls) {
		this.inventarioTbls = inventarioTbls;
	}
	
}