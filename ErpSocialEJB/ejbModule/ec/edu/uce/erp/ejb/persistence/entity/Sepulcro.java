package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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


/**
 * The persistent class for the sepulcro_tbl database table.
 * 
 */
@Entity
@Table(name="sepulcro_tbl")
@NamedQuery(name="Sepulcro.findAll", query="SELECT s FROM Sepulcro s")
public class Sepulcro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEPULCRO_TBL_SEPPK_GENERATOR", sequenceName="SEPULCRO_TBL_SEP_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEPULCRO_TBL_SEPPK_GENERATOR")
	@Column(name="sep_pk")
	private Integer sepPk;

	@Column(name="sep_costo_arriendo_anual")
	private BigDecimal sepCostoArriendoAnual;

	@Column(name="sep_costo_venta")
	private BigDecimal sepCostoVenta;

	@Column(name="sep_estado_actual")
	private String sepEstadoActual;

	@Column(name="sep_nivel")
	private Integer sepNivel;

	@Column(name="sep_numero")
	private Integer sepNumero;

	@Column(name="sep_seccion")
	private String sepSeccion;

	//bi-directional many-to-one association to Cementerio
	@ManyToOne
	@JoinColumn(name="cem_fk")
	private Cementerio cementerioTbl;

	//bi-directional many-to-one association to Contrato
	@ManyToOne
	@JoinColumn(name="con_fk")
	private Contrato contratoTbl;

	//bi-directional many-to-one association to DetalleCementerio
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_cementerio_tip_sep_fk", referencedColumnName="cab_cementerio_fk"),
		@JoinColumn(name="det_cementerio_tip_sep_nivel1", referencedColumnName="det_cementerio_nivel1")
		})
	private DetalleCementerio detalleCementerioTbl;

	public Sepulcro() {
	}

	public Integer getSepPk() {
		return this.sepPk;
	}

	public void setSepPk(Integer sepPk) {
		this.sepPk = sepPk;
	}

	public BigDecimal getSepCostoArriendoAnual() {
		return this.sepCostoArriendoAnual;
	}

	public void setSepCostoArriendoAnual(BigDecimal sepCostoArriendoAnual) {
		this.sepCostoArriendoAnual = sepCostoArriendoAnual;
	}

	public BigDecimal getSepCostoVenta() {
		return this.sepCostoVenta;
	}

	public void setSepCostoVenta(BigDecimal sepCostoVenta) {
		this.sepCostoVenta = sepCostoVenta;
	}

	public String getSepEstadoActual() {
		return this.sepEstadoActual;
	}

	public void setSepEstadoActual(String sepEstadoActual) {
		this.sepEstadoActual = sepEstadoActual;
	}

	public Integer getSepNivel() {
		return this.sepNivel;
	}

	public void setSepNivel(Integer sepNivel) {
		this.sepNivel = sepNivel;
	}

	public Integer getSepNumero() {
		return this.sepNumero;
	}

	public void setSepNumero(Integer sepNumero) {
		this.sepNumero = sepNumero;
	}

	public String getSepSeccion() {
		return this.sepSeccion;
	}

	public void setSepSeccion(String sepSeccion) {
		this.sepSeccion = sepSeccion;
	}

	public Cementerio getCementerioTbl() {
		return this.cementerioTbl;
	}

	public void setCementerioTbl(Cementerio cementerioTbl) {
		this.cementerioTbl = cementerioTbl;
	}

	public Contrato getContratoTbl() {
		return this.contratoTbl;
	}

	public void setContratoTbl(Contrato contratoTbl) {
		this.contratoTbl = contratoTbl;
	}

	public DetalleCementerio getDetalleCementerioTbl() {
		return this.detalleCementerioTbl;
	}

	public void setDetalleCementerioTbl(DetalleCementerio detalleCementerioTbl) {
		this.detalleCementerioTbl = detalleCementerioTbl;
	}

}