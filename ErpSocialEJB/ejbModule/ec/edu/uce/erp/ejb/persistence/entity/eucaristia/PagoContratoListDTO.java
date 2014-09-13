package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the euc_pago_contrato_vie database table.
 * 
 */
@Entity
@Table(name="euc_pago_contrato_vie")
@NamedQuery(name="PagoContratoListDTO.findAll", query="SELECT p FROM PagoContratoListDTO p")
public class PagoContratoListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String benapellidos;

	private String benci;

	private String bennombres;

	@Column(name="cat_codigo")
	private Integer catCodigo;

	@Column(name="cat_descripcion")
	private String catDescripcion;

	@Column(name="cat_predecesor")
	private Integer catPredecesor;

	@Column(name="con_anio_arrendamiento")
	private Integer conAnioArrendamiento;

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

	@Column(name="con_nicho")
	private Integer conNicho;

	@Column(name="con_observacion")
	private String conObservacion;

	@Column(name="meses_faltantes")
	private Integer mesesFaltantes;

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

	@Column(name="pag_codigo")
	private Integer pagCodigo;

	@Column(name="pag_contrato")
	private Integer pagContrato;

	@Column(name="pag_fecha")
	private Timestamp pagFecha;

	@Column(name="pag_meses_pagados")
	private Integer pagMesesPagados;

	@Column(name="pag_valor")
	private BigDecimal pagValor;

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

	public PagoContratoListDTO() {
	}

	public String getBenapellidos() {
		return this.benapellidos;
	}

	public void setBenapellidos(String benapellidos) {
		this.benapellidos = benapellidos;
	}

	public String getBenci() {
		return this.benci;
	}

	public void setBenci(String benci) {
		this.benci = benci;
	}

	public String getBennombres() {
		return this.bennombres;
	}

	public void setBennombres(String bennombres) {
		this.bennombres = bennombres;
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

	public Integer getConAnioArrendamiento() {
		return this.conAnioArrendamiento;
	}

	public void setConAnioArrendamiento(Integer conAnioArrendamiento) {
		this.conAnioArrendamiento = conAnioArrendamiento;
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

	public Integer getMesesFaltantes() {
		return this.mesesFaltantes;
	}

	public void setMesesFaltantes(Integer mesesFaltantes) {
		this.mesesFaltantes = mesesFaltantes;
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

	public Integer getPagMesesPagados() {
		return this.pagMesesPagados;
	}

	public void setPagMesesPagados(Integer pagMesesPagados) {
		this.pagMesesPagados = pagMesesPagados;
	}

	public BigDecimal getPagValor() {
		return this.pagValor;
	}

	public void setPagValor(BigDecimal pagValor) {
		this.pagValor = pagValor;
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