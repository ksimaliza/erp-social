package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
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

import ec.edu.uce.erp.ejb.persistence.entity.inventario.Proveedor;


/**
 * The persistent class for the ingreso_tbl database table.
 * 
 */
@Entity
@Table(name="ingreso_tbl")
@NamedQuery(name="Ingreso.findAll", query="SELECT i FROM Ingreso i")
public class Ingreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INGRESO_TBL_INGPK_GENERATOR", sequenceName="INGRESO_TBL_ING_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INGRESO_TBL_INGPK_GENERATOR")
	@Column(name="ing_pk")
	private Integer ingPk;

	@Column(name="ing_cantidad")
	private Integer ingCantidad;

	@Column(name="ing_cod_bien")
	private Integer ingCodBien;

	@Temporal(TemporalType.DATE)
	@Column(name="ing_fecha")
	private Date ingFecha;

	@Column(name="ing_valor_unitario")
	private Integer ingValorUnitario;

	//bi-directional many-to-one association to Bien
	@ManyToOne
	@JoinColumn(name="bie_fk")
	private Bien bienTbl;

	//bi-directional many-to-one association to DetalleBien
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_bien_tip_ing_fk", referencedColumnName="cab_bien_fk"),
		@JoinColumn(name="det_bien_tip_ing_nivel1", referencedColumnName="det_bien_nivel1")
		})
	private DetalleBien detalleBienTbl;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="prov_pk")
	private Proveedor proveedor;

	public Ingreso() {
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

	public Integer getIngCodBien() {
		return this.ingCodBien;
	}

	public void setIngCodBien(Integer ingCodBien) {
		this.ingCodBien = ingCodBien;
	}

	public Date getIngFecha() {
		return this.ingFecha;
	}

	public void setIngFecha(Date ingFecha) {
		this.ingFecha = ingFecha;
	}

	public Integer getIngValorUnitario() {
		return this.ingValorUnitario;
	}

	public void setIngValorUnitario(Integer ingValorUnitario) {
		this.ingValorUnitario = ingValorUnitario;
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

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}