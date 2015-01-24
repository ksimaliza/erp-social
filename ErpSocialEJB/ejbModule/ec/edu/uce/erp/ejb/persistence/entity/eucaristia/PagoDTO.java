package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the euc_pago database table.
 * 
 */
@Entity
@Table(name="euc_pago")
@NamedQuery(name="PagoDTO.findAll", query="SELECT p FROM PagoDTO p")
public class PagoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_PAGO_PAGCODIGO_GENERATOR", sequenceName="EUC_PAGO_PAG_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_PAGO_PAGCODIGO_GENERATOR")
	@Column(name="pag_codigo")
	private Integer pagCodigo;

	@Column(name="pag_contrato")
	private Integer pagContrato;

	@Column(name="pag_fecha")
	private Timestamp pagFecha;
	
	@Column(name="pag_contrato_fecha_inicio")
	private Timestamp pagContratoFechaIncio;
	
	@Column(name="pag_contrato_fecha_fin")
	private Timestamp pagContratoFechaFin;
	
	@Column(name="pag_nicho_descripcion")
	private String pagNichoDescripcion;

	@Column(name="pag_meses_faltantes")
	private Integer pagMesesFaltantes;

	@Column(name="pag_meses_pagados")
	private Integer pagMesesPagados;

	@Column(name="pag_saldo")
	private BigDecimal pagSaldo;

	@Column(name="pag_valor_pagado")
	private BigDecimal pagValorPagado;

	@Column(name="pag_valor_total")
	private BigDecimal pagValorTotal;
	
	@Column(name="pag_empresa")
	private Integer pagEmpresa;

	public PagoDTO() {
	}

	public Integer getPagCodigo() {
		return this.pagCodigo;
	}

	public void setPagCodigo(Integer pagCodigo) {
		this.pagCodigo = pagCodigo;
	}

	public Integer getPagContrato() {
		return this.pagContrato;
	}

	public void setPagContrato(Integer pagContrato) {
		this.pagContrato = pagContrato;
	}

	public Timestamp getPagFecha() {
		return this.pagFecha;
	}

	public void setPagFecha(Timestamp pagFecha) {
		this.pagFecha = pagFecha;
	}

	public Integer getPagMesesFaltantes() {
		return this.pagMesesFaltantes;
	}

	public void setPagMesesFaltantes(Integer pagMesesFaltantes) {
		this.pagMesesFaltantes = pagMesesFaltantes;
	}

	public Integer getPagMesesPagados() {
		return this.pagMesesPagados;
	}

	public void setPagMesesPagados(Integer pagMesesPagados) {
		this.pagMesesPagados = pagMesesPagados;
	}

	public BigDecimal getPagSaldo() {
		return this.pagSaldo;
	}

	public void setPagSaldo(BigDecimal pagSaldo) {
		this.pagSaldo = pagSaldo;
	}

	public BigDecimal getPagValorPagado() {
		return this.pagValorPagado;
	}

	public void setPagValorPagado(BigDecimal pagValorPagado) {
		this.pagValorPagado = pagValorPagado;
	}

	public BigDecimal getPagValorTotal() {
		return this.pagValorTotal;
	}

	public void setPagValorTotal(BigDecimal pagValorTotal) {
		this.pagValorTotal = pagValorTotal;
	}

	public Integer getPagEmpresa() {
		return pagEmpresa;
	}

	public void setPagEmpresa(Integer pagEmpresa) {
		this.pagEmpresa = pagEmpresa;
	}

	public Timestamp getPagContratoFechaIncio() {
		return pagContratoFechaIncio;
	}

	public void setPagContratoFechaIncio(Timestamp pagContratoFechaIncio) {
		this.pagContratoFechaIncio = pagContratoFechaIncio;
	}

	public Timestamp getPagContratoFechaFin() {
		return pagContratoFechaFin;
	}

	public void setPagContratoFechaFin(Timestamp pagContratoFechaFin) {
		this.pagContratoFechaFin = pagContratoFechaFin;
	}

	public String getPagNichoDescripcion() {
		return pagNichoDescripcion;
	}

	public void setPagNichoDescripcion(String pagNichoDescripcion) {
		this.pagNichoDescripcion = pagNichoDescripcion;
	}
   
	
}