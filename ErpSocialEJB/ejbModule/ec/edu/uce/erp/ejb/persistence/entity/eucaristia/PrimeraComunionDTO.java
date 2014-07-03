package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_primera_comunion database table.
 * 
 */
@Entity
@Table(name="euc_primera_comunion")
@NamedQuery(name="PrimeraComunionDTO.findAll", query="SELECT p FROM PrimeraComunionDTO p")
public class PrimeraComunionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_PRIMERA_COMUNION_PCOCODIGO_GENERATOR", sequenceName="EUC_PRIMERA_COMUNION_PCO_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_PRIMERA_COMUNION_PCOCODIGO_GENERATOR")
	@Column(name="pco_codigo")
	private Integer pcoCodigo;

	@Column(name="pco_acta")
	private String pcoActa;

	@Column(name="pco_asignado")
	private Integer pcoAsignado;

	@Column(name="pco_canton_parroquia")
	private Integer pcoCantonParroquia;

	@Column(name="pco_certificado_por")
	private Integer pcoCertificadoPor;

	@Column(name="pco_estado")
	private Integer pcoEstado;

	@Column(name="pco_fecha_aprobacion_curso")
	private Timestamp pcoFechaAprobacionCurso;

	@Column(name="pco_fecha_hora")
	private Timestamp pcoFechaHora;

	@Column(name="pco_lugar")
	private String pcoLugar;

	@Column(name="pco_nota_marginal")
	private String pcoNotaMarginal;

	@Column(name="pco_padrino")
	private Integer pcoPadrino;

	@Column(name="pco_pagina")
	private String pcoPagina;

	@Column(name="pco_provincia")
	private Integer pcoProvincia;

	@Column(name="pco_tomo")
	private String pcoTomo;

	//bi-directional many-to-one association to SacerdoteDTO
	@ManyToOne
	@JoinColumn(name="pco_sacerdote")
	private SacerdoteDTO eucSacerdote;

	public PrimeraComunionDTO() {
	}

	public Integer getPcoCodigo() {
		return this.pcoCodigo;
	}

	public void setPcoCodigo(Integer pcoCodigo) {
		this.pcoCodigo = pcoCodigo;
	}

	public String getPcoActa() {
		return this.pcoActa;
	}

	public void setPcoActa(String pcoActa) {
		this.pcoActa = pcoActa;
	}

	public Integer getPcoAsignado() {
		return this.pcoAsignado;
	}

	public void setPcoAsignado(Integer pcoAsignado) {
		this.pcoAsignado = pcoAsignado;
	}

	public Integer getPcoCantonParroquia() {
		return this.pcoCantonParroquia;
	}

	public void setPcoCantonParroquia(Integer pcoCantonParroquia) {
		this.pcoCantonParroquia = pcoCantonParroquia;
	}

	public Integer getPcoCertificadoPor() {
		return this.pcoCertificadoPor;
	}

	public void setPcoCertificadoPor(Integer pcoCertificadoPor) {
		this.pcoCertificadoPor = pcoCertificadoPor;
	}

	public Integer getPcoEstado() {
		return this.pcoEstado;
	}

	public void setPcoEstado(Integer pcoEstado) {
		this.pcoEstado = pcoEstado;
	}

	public Timestamp getPcoFechaAprobacionCurso() {
		return this.pcoFechaAprobacionCurso;
	}

	public void setPcoFechaAprobacionCurso(Timestamp pcoFechaAprobacionCurso) {
		this.pcoFechaAprobacionCurso = pcoFechaAprobacionCurso;
	}

	public Timestamp getPcoFechaHora() {
		return this.pcoFechaHora;
	}

	public void setPcoFechaHora(Timestamp pcoFechaHora) {
		this.pcoFechaHora = pcoFechaHora;
	}

	public String getPcoLugar() {
		return this.pcoLugar;
	}

	public void setPcoLugar(String pcoLugar) {
		this.pcoLugar = pcoLugar;
	}

	public String getPcoNotaMarginal() {
		return this.pcoNotaMarginal;
	}

	public void setPcoNotaMarginal(String pcoNotaMarginal) {
		this.pcoNotaMarginal = pcoNotaMarginal;
	}

	public Integer getPcoPadrino() {
		return this.pcoPadrino;
	}

	public void setPcoPadrino(Integer pcoPadrino) {
		this.pcoPadrino = pcoPadrino;
	}

	public String getPcoPagina() {
		return this.pcoPagina;
	}

	public void setPcoPagina(String pcoPagina) {
		this.pcoPagina = pcoPagina;
	}

	public Integer getPcoProvincia() {
		return this.pcoProvincia;
	}

	public void setPcoProvincia(Integer pcoProvincia) {
		this.pcoProvincia = pcoProvincia;
	}

	public String getPcoTomo() {
		return this.pcoTomo;
	}

	public void setPcoTomo(String pcoTomo) {
		this.pcoTomo = pcoTomo;
	}

	public SacerdoteDTO getEucSacerdote() {
		return this.eucSacerdote;
	}

	public void setEucSacerdote(SacerdoteDTO eucSacerdote) {
		this.eucSacerdote = eucSacerdote;
	}

}