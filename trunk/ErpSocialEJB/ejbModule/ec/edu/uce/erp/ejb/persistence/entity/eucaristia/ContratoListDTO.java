package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the euc_contrato_vie database table.
 * 
 */
@Entity
@Table(name="euc_contrato_vie")
@NamedQuery(name="ContratoListDTO.findAll", query="SELECT c FROM ContratoListDTO c")
public class ContratoListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ben_apellidos")
	private String benApellidos;

	@Column(name="ben_ci")
	private String benCi;

	@Column(name="ben_nombres")
	private String benNombres;

	@Column(name="cat_codigo")
	private Integer catCodigo;

	@Column(name="cat_descripcion")
	private String catDescripcion;

	@Column(name="cat_predecesor")
	private Integer catPredecesor;

	@Column(name="con_beneficiario")
	private Integer conBeneficiario;

	@Id
	@Column(name="con_codigo")
	private Integer conCodigo;

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

	@Column(name="nic_codigo")
	private Integer nicCodigo;

	@Column(name="nic_descripcion")
	private String nicDescripcion;

	@Column(name="nic_seccion")
	private Integer nicSeccion;

	@Column(name="nic_tipo")
	private Integer nicTipo;

	@Column(name="nni_descripcion")
	private String nniDescripcion;

	@Column(name="nni_nivel")
	private Integer nniNivel;

	@Column(name="per_apellidos")
	private String perApellidos;

	@Column(name="per_ci")
	private String perCi;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha_nac")
	private Date perFechaNac;

	@Column(name="per_nombres")
	private String perNombres;

	private String seccion;

	@Column(name="tni_descripcion")
	private String tniDescripcion;

	public ContratoListDTO() {
	}

	public String getBenApellidos() {
		return this.benApellidos;
	}

	public void setBenApellidos(String benApellidos) {
		this.benApellidos = benApellidos;
	}

	public String getBenCi() {
		return this.benCi;
	}

	public void setBenCi(String benCi) {
		this.benCi = benCi;
	}

	public String getBenNombres() {
		return this.benNombres;
	}

	public void setBenNombres(String benNombres) {
		this.benNombres = benNombres;
	}

	public Integer getCatCodigo() {
		return this.catCodigo;
	}

	public void setCatCodigo(Integer catCodigo) {
		this.catCodigo = catCodigo;
	}

	public String getCatDescripcion() {
		return this.catDescripcion;
	}

	public void setCatDescripcion(String catDescripcion) {
		this.catDescripcion = catDescripcion;
	}

	public Integer getCatPredecesor() {
		return this.catPredecesor;
	}

	public void setCatPredecesor(Integer catPredecesor) {
		this.catPredecesor = catPredecesor;
	}

	public Integer getConBeneficiario() {
		return this.conBeneficiario;
	}

	public void setConBeneficiario(Integer conBeneficiario) {
		this.conBeneficiario = conBeneficiario;
	}

	public Integer getConCodigo() {
		return this.conCodigo;
	}

	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
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

	public Integer getNicCodigo() {
		return this.nicCodigo;
	}

	public void setNicCodigo(Integer nicCodigo) {
		this.nicCodigo = nicCodigo;
	}

	public String getNicDescripcion() {
		return this.nicDescripcion;
	}

	public void setNicDescripcion(String nicDescripcion) {
		this.nicDescripcion = nicDescripcion;
	}

	public Integer getNicSeccion() {
		return this.nicSeccion;
	}

	public void setNicSeccion(Integer nicSeccion) {
		this.nicSeccion = nicSeccion;
	}

	public Integer getNicTipo() {
		return this.nicTipo;
	}

	public void setNicTipo(Integer nicTipo) {
		this.nicTipo = nicTipo;
	}

	public String getNniDescripcion() {
		return this.nniDescripcion;
	}

	public void setNniDescripcion(String nniDescripcion) {
		this.nniDescripcion = nniDescripcion;
	}

	public Integer getNniNivel() {
		return this.nniNivel;
	}

	public void setNniNivel(Integer nniNivel) {
		this.nniNivel = nniNivel;
	}

	public String getPerApellidos() {
		return this.perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerCi() {
		return this.perCi;
	}

	public void setPerCi(String perCi) {
		this.perCi = perCi;
	}

	public Date getPerFechaNac() {
		return this.perFechaNac;
	}

	public void setPerFechaNac(Date perFechaNac) {
		this.perFechaNac = perFechaNac;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public String getSeccion() {
		return this.seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getTniDescripcion() {
		return this.tniDescripcion;
	}

	public void setTniDescripcion(String tniDescripcion) {
		this.tniDescripcion = tniDescripcion;
	}

}