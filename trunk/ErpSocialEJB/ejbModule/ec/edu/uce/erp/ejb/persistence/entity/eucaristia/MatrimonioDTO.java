package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_matrimonio database table.
 * 
 */
@Entity
@Table(name="euc_matrimonio")
@NamedQuery(name="MatrimonioDTO.findAll", query="SELECT m FROM MatrimonioDTO m")
public class MatrimonioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_MATRIMONIO_MATCODIGO_GENERATOR", sequenceName="EUC_MATRIMONIO_MAT_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_MATRIMONIO_MATCODIGO_GENERATOR")
	@Column(name="mat_codigo")
	private Integer matCodigo;

	@Column(name="mat_acta")
	private String matActa;

	@Column(name="mat_certificado_novia")
	private String matCertificadoNovia;

	@Column(name="mat_certificado_novio")
	private String matCertificadoNovio;

	@Column(name="mat_fecha")
	private Timestamp matFecha;

	@Column(name="mat_fecha_aprobacion_curso")
	private Timestamp matFechaAprobacionCurso;

	@Column(name="mat_lugar")
	private String matLugar;

	@Column(name="mat_madrina_novia")
	private Integer matMadrinaNovia;

	@Column(name="mat_madrina_novio")
	private Integer matMadrinaNovio;

	@Column(name="mat_nota_marginal")
	private String matNotaMarginal;

	@Column(name="mat_novia")
	private Integer matNovia;

	@Column(name="mat_novio")
	private Integer matNovio;

	@Column(name="mat_padrino_novia")
	private Integer matPadrinoNovia;

	@Column(name="mat_padrino_novio")
	private Integer matPadrinoNovio;

	@Column(name="mat_pagina")
	private String matPagina;

	@Column(name="mat_parroquia")
	private String matParroquia;

	@Column(name="mat_tomo")
	private String matTomo;

	//bi-directional many-to-one association to SacerdoteDTO
	@ManyToOne
	@JoinColumn(name="mat_sacerdote")
	private SacerdoteDTO eucSacerdote;

	public MatrimonioDTO() {
	}

	public Integer getMatCodigo() {
		return this.matCodigo;
	}

	public void setMatCodigo(Integer matCodigo) {
		this.matCodigo = matCodigo;
	}

	public String getMatActa() {
		return this.matActa;
	}

	public void setMatActa(String matActa) {
		this.matActa = matActa;
	}

	public String getMatCertificadoNovia() {
		return this.matCertificadoNovia;
	}

	public void setMatCertificadoNovia(String matCertificadoNovia) {
		this.matCertificadoNovia = matCertificadoNovia;
	}

	public String getMatCertificadoNovio() {
		return this.matCertificadoNovio;
	}

	public void setMatCertificadoNovio(String matCertificadoNovio) {
		this.matCertificadoNovio = matCertificadoNovio;
	}

	public Timestamp getMatFecha() {
		return this.matFecha;
	}

	public void setMatFecha(Timestamp matFecha) {
		this.matFecha = matFecha;
	}

	public Timestamp getMatFechaAprobacionCurso() {
		return this.matFechaAprobacionCurso;
	}

	public void setMatFechaAprobacionCurso(Timestamp matFechaAprobacionCurso) {
		this.matFechaAprobacionCurso = matFechaAprobacionCurso;
	}

	public String getMatLugar() {
		return this.matLugar;
	}

	public void setMatLugar(String matLugar) {
		this.matLugar = matLugar;
	}

	public Integer getMatMadrinaNovia() {
		return this.matMadrinaNovia;
	}

	public void setMatMadrinaNovia(Integer matMadrinaNovia) {
		this.matMadrinaNovia = matMadrinaNovia;
	}

	public Integer getMatMadrinaNovio() {
		return this.matMadrinaNovio;
	}

	public void setMatMadrinaNovio(Integer matMadrinaNovio) {
		this.matMadrinaNovio = matMadrinaNovio;
	}

	public String getMatNotaMarginal() {
		return this.matNotaMarginal;
	}

	public void setMatNotaMarginal(String matNotaMarginal) {
		this.matNotaMarginal = matNotaMarginal;
	}

	public Integer getMatNovia() {
		return this.matNovia;
	}

	public void setMatNovia(Integer matNovia) {
		this.matNovia = matNovia;
	}

	public Integer getMatNovio() {
		return this.matNovio;
	}

	public void setMatNovio(Integer matNovio) {
		this.matNovio = matNovio;
	}

	public Integer getMatPadrinoNovia() {
		return this.matPadrinoNovia;
	}

	public void setMatPadrinoNovia(Integer matPadrinoNovia) {
		this.matPadrinoNovia = matPadrinoNovia;
	}

	public Integer getMatPadrinoNovio() {
		return this.matPadrinoNovio;
	}

	public void setMatPadrinoNovio(Integer matPadrinoNovio) {
		this.matPadrinoNovio = matPadrinoNovio;
	}

	public String getMatPagina() {
		return this.matPagina;
	}

	public void setMatPagina(String matPagina) {
		this.matPagina = matPagina;
	}

	public String getMatParroquia() {
		return this.matParroquia;
	}

	public void setMatParroquia(String matParroquia) {
		this.matParroquia = matParroquia;
	}

	public String getMatTomo() {
		return this.matTomo;
	}

	public void setMatTomo(String matTomo) {
		this.matTomo = matTomo;
	}

	public SacerdoteDTO getEucSacerdote() {
		return this.eucSacerdote;
	}

	public void setEucSacerdote(SacerdoteDTO eucSacerdote) {
		this.eucSacerdote = eucSacerdote;
	}

}