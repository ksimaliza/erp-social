package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the sepulcro_tbl database table.
 * 
 */
@Entity
@Table(name="sepulcro_tbl")
public class SepulcroTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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

	//bi-directional many-to-one association to CementerioTbl
    @ManyToOne
	@JoinColumn(name="cem_fk")
	private CementerioTbl cementerioTbl;

	//bi-directional many-to-one association to ContratoTbl
    @ManyToOne
	@JoinColumn(name="con_fk")
	private ContratoTbl contratoTbl;

	//bi-directional many-to-one association to DetalleCementerioTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_cementerio_tip_sep_fk", referencedColumnName="cab_cementerio_fk"),
		@JoinColumn(name="det_cementerio_tip_sep_nivel1", referencedColumnName="det_cementerio_nivel1")
		})
	private DetalleCementerioTbl detalleCementerioTbl;

    public SepulcroTbl() {
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

	public CementerioTbl getCementerioTbl() {
		return this.cementerioTbl;
	}

	public void setCementerioTbl(CementerioTbl cementerioTbl) {
		this.cementerioTbl = cementerioTbl;
	}
	
	public ContratoTbl getContratoTbl() {
		return this.contratoTbl;
	}

	public void setContratoTbl(ContratoTbl contratoTbl) {
		this.contratoTbl = contratoTbl;
	}
	
	public DetalleCementerioTbl getDetalleCementerioTbl() {
		return this.detalleCementerioTbl;
	}

	public void setDetalleCementerioTbl(DetalleCementerioTbl detalleCementerioTbl) {
		this.detalleCementerioTbl = detalleCementerioTbl;
	}
	
}