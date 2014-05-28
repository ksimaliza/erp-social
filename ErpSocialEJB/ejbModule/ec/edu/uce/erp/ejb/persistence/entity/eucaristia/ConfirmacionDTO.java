package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_confirmacion database table.
 * 
 */
@Entity
@Table(name="euc_confirmacion")
@NamedQuery(name="ConfirmacionDTO.findAll", query="SELECT c FROM ConfirmacionDTO c")
public class ConfirmacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_CONFIRMACION_CONCODIGO_GENERATOR", sequenceName="EUC_CONFIRMACION_CON_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_CONFIRMACION_CONCODIGO_GENERATOR")
	@Column(name="con_codigo")
	private Integer conCodigo;

	@Column(name="con_acta")
	private String conActa;

	@Column(name="con_confirmado")
	private Integer conConfirmado;

	@Column(name="con_estado")
	private Integer conEstado;

	@Column(name="con_fecha")
	private Timestamp conFecha;

	@Column(name="con_fecha_aprobacion_curso")
	private Timestamp conFechaAprobacionCurso;

	@Column(name="con_lugar")
	private String conLugar;

	@Column(name="con_nota_marginal")
	private String conNotaMarginal;

	@Column(name="con_padrino")
	private Integer conPadrino;

	@Column(name="con_pagina")
	private String conPagina;

	@Column(name="con_toma")
	private String conToma;

	//bi-directional many-to-one association to SacerdoteDTO
	@ManyToOne
	@JoinColumn(name="con_sacerdote")
	private SacerdoteDTO eucSacerdote;

	public ConfirmacionDTO() {
	}

	public Integer getConCodigo() {
		return this.conCodigo;
	}

	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
	}

	public String getConActa() {
		return this.conActa;
	}

	public void setConActa(String conActa) {
		this.conActa = conActa;
	}

	public Integer getConConfirmado() {
		return this.conConfirmado;
	}

	public void setConConfirmado(Integer conConfirmado) {
		this.conConfirmado = conConfirmado;
	}

	public Integer getConEstado() {
		return this.conEstado;
	}

	public void setConEstado(Integer conEstado) {
		this.conEstado = conEstado;
	}

	public Timestamp getConFecha() {
		return this.conFecha;
	}

	public void setConFecha(Timestamp conFecha) {
		this.conFecha = conFecha;
	}

	public Timestamp getConFechaAprobacionCurso() {
		return this.conFechaAprobacionCurso;
	}

	public void setConFechaAprobacionCurso(Timestamp conFechaAprobacionCurso) {
		this.conFechaAprobacionCurso = conFechaAprobacionCurso;
	}

	public String getConLugar() {
		return this.conLugar;
	}

	public void setConLugar(String conLugar) {
		this.conLugar = conLugar;
	}

	public String getConNotaMarginal() {
		return this.conNotaMarginal;
	}

	public void setConNotaMarginal(String conNotaMarginal) {
		this.conNotaMarginal = conNotaMarginal;
	}

	public Integer getConPadrino() {
		return this.conPadrino;
	}

	public void setConPadrino(Integer conPadrino) {
		this.conPadrino = conPadrino;
	}

	public String getConPagina() {
		return this.conPagina;
	}

	public void setConPagina(String conPagina) {
		this.conPagina = conPagina;
	}

	public String getConToma() {
		return this.conToma;
	}

	public void setConToma(String conToma) {
		this.conToma = conToma;
	}

	public SacerdoteDTO getEucSacerdote() {
		return this.eucSacerdote;
	}

	public void setEucSacerdote(SacerdoteDTO eucSacerdote) {
		this.eucSacerdote = eucSacerdote;
	}

}