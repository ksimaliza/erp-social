package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ingreso_tbl database table.
 * 
 */
@Entity
@Table(name="ingreso_tbl")
public class IngresoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ing_pk")
	private Integer ingPk;

	@Column(name="ing_cantidad")
	private Integer ingCantidad;

    @Temporal( TemporalType.DATE)
	@Column(name="ing_fecha")
	private Date ingFecha;

	@Column(name="ing_valor_total")
	private Integer ingValorTotal;

	//bi-directional many-to-one association to BienTbl
    @ManyToOne
	@JoinColumn(name="bie_fk")
	private BienTbl bienTbl;

	//bi-directional many-to-one association to DetalleBienTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_tip_ing_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_tip_ing_nivel1", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBienTbl detalleBienTbl;

	//bi-directional many-to-one association to Proveedor
    @ManyToOne
	@JoinColumn(name="prov_pk")
	private Proveedor proveedor;

    public IngresoTbl() {
    }

	public Integer getIngPk() {
		return this.ingPk;
	}

	public void setIngPk(Integer ingPk) {
		this.ingPk = ingPk;
	}

	public Integer getIngCantidad() {
		return this.ingCantidad;
	}

	public void setIngCantidad(Integer ingCantidad) {
		this.ingCantidad = ingCantidad;
	}

	public Date getIngFecha() {
		return this.ingFecha;
	}

	public void setIngFecha(Date ingFecha) {
		this.ingFecha = ingFecha;
	}

	public Integer getIngValorTotal() {
		return this.ingValorTotal;
	}

	public void setIngValorTotal(Integer ingValorTotal) {
		this.ingValorTotal = ingValorTotal;
	}

	public BienTbl getBienTbl() {
		return this.bienTbl;
	}

	public void setBienTbl(BienTbl bienTbl) {
		this.bienTbl = bienTbl;
	}
	
	public DetalleBienTbl getDetalleBienTbl() {
		return this.detalleBienTbl;
	}

	public void setDetalleBienTbl(DetalleBienTbl detalleBienTbl) {
		this.detalleBienTbl = detalleBienTbl;
	}
	
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}