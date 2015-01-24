package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_exumacion database table.
 * 
 */
@Entity
@Table(name="euc_exumacion")
@NamedQuery(name="ExumacionDTO.findAll", query="SELECT e FROM ExumacionDTO e")
public class ExumacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_EXUMACION_EXUCODIGO_GENERATOR", sequenceName="EUC_EXUMACION_EXU_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_EXUMACION_EXUCODIGO_GENERATOR")
	@Column(name="exu_codigo")
	private Integer exuCodigo;

	@Column(name="exu_acta")
	private String exuActa;

	@Column(name="exu_autoriza")
	private Integer exuAutoriza;

	@Column(name="exu_causa")
	private String exuCausa;

	@Column(name="exu_difunto")
	private Integer exuDifunto;

	@Column(name="exu_fecha_cepelio")
	private Timestamp exuFechaCepelio;

	@Column(name="exu_fecha_exhumacion")
	private Timestamp exuFechaExhumacion;

	@Column(name="exu_pagina")
	private String exuPagina;

	@Column(name="exu_tomo")
	private String exuTomo;
	
	@Column(name="exu_empresa")
	private Integer exuEmpresa;

	public ExumacionDTO() {
	}

	public Integer getExuCodigo() {
		return this.exuCodigo;
	}

	public void setExuCodigo(Integer exuCodigo) {
		this.exuCodigo = exuCodigo;
	}

	public String getExuActa() {
		return this.exuActa;
	}

	public void setExuActa(String exuActa) {
		this.exuActa = exuActa;
	}

	public Integer getExuAutoriza() {
		return this.exuAutoriza;
	}

	public void setExuAutoriza(Integer exuAutoriza) {
		this.exuAutoriza = exuAutoriza;
	}

	public String getExuCausa() {
		return this.exuCausa;
	}

	public void setExuCausa(String exuCausa) {
		this.exuCausa = exuCausa;
	}

	public Integer getExuDifunto() {
		return this.exuDifunto;
	}

	public void setExuDifunto(Integer exuDifunto) {
		this.exuDifunto = exuDifunto;
	}

	public Timestamp getExuFechaCepelio() {
		return this.exuFechaCepelio;
	}

	public void setExuFechaCepelio(Timestamp exuFechaCepelio) {
		this.exuFechaCepelio = exuFechaCepelio;
	}

	public Timestamp getExuFechaExhumacion() {
		return this.exuFechaExhumacion;
	}

	public void setExuFechaExhumacion(Timestamp exuFechaExhumacion) {
		this.exuFechaExhumacion = exuFechaExhumacion;
	}

	public String getExuPagina() {
		return this.exuPagina;
	}

	public void setExuPagina(String exuPagina) {
		this.exuPagina = exuPagina;
	}

	
	
	public Integer getExuEmpresa() {
		return exuEmpresa;
	}

	public void setExuEmpresa(Integer exuEmpresa) {
		this.exuEmpresa = exuEmpresa;
	}

	public String getExuTomo() {
		return this.exuTomo;
	}

	public void setExuTomo(String exuTomo) {
		this.exuTomo = exuTomo;
	}

}