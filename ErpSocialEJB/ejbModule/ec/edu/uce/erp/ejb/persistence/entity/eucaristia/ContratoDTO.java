package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the euc_contrato database table.
 * 
 */
@Entity
@Table(name="euc_contrato")
@NamedQuery(name="ContratoDTO.findAll", query="SELECT c FROM ContratoDTO c")
public class ContratoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_CONTRATO_CONCODIGO_GENERATOR", sequenceName="EUC_CONTRATO_CON_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_CONTRATO_CONCODIGO_GENERATOR")
	@Column(name="con_codigo")
	private Integer conCodigo;

	@Column(name="con_beneficiario")
	private Integer conBeneficiario;

	@Column(name="con_difunto")
	private Integer conDifunto;

	@Column(name="con_fecha_fin")
	private Timestamp conFechaFin;

	@Column(name="con_fecha_inicio")
	private Timestamp conFechaInicio;

	@Column(name="con_forma_pago")
	private Integer conFormaPago;

	@Column(name="con_meses_arrendamiento")
	private Integer conMesesArrendamiento;

	@Column(name="con_meses_por_pagar")
	private Integer conMesesPorPagar;

	@Column(name="con_nicho")
	private Integer conNicho;

	@Column(name="con_observacion")
	private String conObservacion;

	@Column(name="con_valor_mes")
	private BigDecimal conValorMes;

	@Column(name="con_valor_total")
	private BigDecimal conValorTotal;

	public ContratoDTO() {
	}

	public Integer getConCodigo() {
		return this.conCodigo;
	}

	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
	}

	public Integer getConBeneficiario() {
		return this.conBeneficiario;
	}

	public void setConBeneficiario(Integer conBeneficiario) {
		this.conBeneficiario = conBeneficiario;
	}

	public Integer getConDifunto() {
		return this.conDifunto;
	}

	public void setConDifunto(Integer conDifunto) {
		this.conDifunto = conDifunto;
	}

	public Timestamp getConFechaFin() {
		return this.conFechaFin;
	}

	public void setConFechaFin(Timestamp conFechaFin) {
		this.conFechaFin = conFechaFin;
	}

	public Timestamp getConFechaInicio() {
		return this.conFechaInicio;
	}

	public void setConFechaInicio(Timestamp conFechaInicio) {
		this.conFechaInicio = conFechaInicio;
	}

	public Integer getConFormaPago() {
		return this.conFormaPago;
	}

	public void setConFormaPago(Integer conFormaPago) {
		this.conFormaPago = conFormaPago;
	}

	public Integer getConMesesArrendamiento() {
		return this.conMesesArrendamiento;
	}

	public void setConMesesArrendamiento(Integer conMesesArrendamiento) {
		this.conMesesArrendamiento = conMesesArrendamiento;
	}

	public Integer getConMesesPorPagar() {
		return this.conMesesPorPagar;
	}

	public void setConMesesPorPagar(Integer conMesesPorPagar) {
		this.conMesesPorPagar = conMesesPorPagar;
	}

	public Integer getConNicho() {
		return this.conNicho;
	}

	public void setConNicho(Integer conNicho) {
		this.conNicho = conNicho;
	}

	public String getConObservacion() {
		return this.conObservacion;
	}

	public void setConObservacion(String conObservacion) {
		this.conObservacion = conObservacion;
	}

	public BigDecimal getConValorMes() {
		return this.conValorMes;
	}

	public void setConValorMes(BigDecimal conValorMes) {
		this.conValorMes = conValorMes;
	}

	public BigDecimal getConValorTotal() {
		return this.conValorTotal;
	}

	public void setConValorTotal(BigDecimal conValorTotal) {
		this.conValorTotal = conValorTotal;
	}

}