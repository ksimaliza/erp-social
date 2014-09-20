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

	@Column(name="pag_meses_pagados")
	private Integer pagMesesPagados;
	
	@Column(name="pag_meses_faltantes")
	private Integer pagMesesFaltantes;

	@Column(name="pag_fecha")
	private Timestamp pagFecha;
	
	@Column(name="pag_valor")
	private BigDecimal pagValor;

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

	public BigDecimal getPagValor() {
		return this.pagValor;
	}

	public void setPagValor(BigDecimal pagValor) {
		this.pagValor = pagValor;
	}

	public Integer getPagMesesPagados() {
		return pagMesesPagados;
	}

	public Integer getPagMesesFaltantes() {
		return pagMesesFaltantes;
	}

	public void setPagMesesPagados(Integer pagMesesPagados) {
		this.pagMesesPagados = pagMesesPagados;
	}

	public void setPagMesesFaltantes(Integer pagMesesFaltantes) {
		this.pagMesesFaltantes = pagMesesFaltantes;
	}

}