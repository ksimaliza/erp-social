package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_bautizo database table.
 * 
 */
@Entity
@Table(name="euc_bautizo")
@NamedQuery(name="BautizoDTO.findAll", query="SELECT b FROM BautizoDTO b")
public class BautizoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_BAUTIZO_BAUCODIGO_GENERATOR", sequenceName="EUC_BAUTIZO_BAU_CODIGO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_BAUTIZO_BAUCODIGO_GENERATOR")
	@Column(name="bau_codigo")
	private Integer bauCodigo;

	@Column(name="bau_acta")
	private String bauActa;

	@Column(name="bau_bautizado")
	private Integer bauBautizado;

	@Column(name="bau_canton")
	private Integer bauCanton;

	@Column(name="bau_certificado_por")
	private Integer bauCertificadoPor;

	@Column(name="bau_parroquia")
	private Integer bauParroquia;

	@Column(name="bau_fecha_aprobacion_cruso")
	private Timestamp bauFechaAprobacionCruso;

	@Column(name="bau_fecha_bautizo")
	private Timestamp bauFechaBautizo;

	@Column(name="bau_madrina")
	private Integer bauMadrina;
	
	@Column(name="bau_madre")
	private Integer bauMadre;
	
	@Column(name="bau_padre")
	private Integer bauPadre;

	@Column(name="bau_nota_marginal")
	private String bauNotaMarginal;

	@Column(name="bau_padrino")
	private Integer bauPadrino;

	@Column(name="bau_pagina")
	private String bauPagina;

	@Column(name="bau_provincia")
	private Integer bauProvincia;

	

	@Column(name="bau_toma")
	private String bauToma;
	
	@Column(name="bau_estado")
	private Integer bauEstado;
    
	@Column(name="bau_empresa")
	private Integer bauEmpresa;
	
	

	//bi-directional many-to-one association to SacerdoteDTO
	@ManyToOne
	@JoinColumn(name="bau_sacerdote")
	private SacerdoteDTO eucSacerdote;

	public BautizoDTO() {
	}

	public Integer getBauCodigo() {
		return this.bauCodigo;
	}

	public void setBauCodigo(Integer bauCodigo) {
		this.bauCodigo = bauCodigo;
	}

	public String getBauActa() {
		return this.bauActa;
	}

	public void setBauActa(String bauActa) {
		this.bauActa = bauActa;
	}

	public Integer getBauBautizado() {
		return this.bauBautizado;
	}

	public void setBauBautizado(Integer bauBautizado) {
		this.bauBautizado = bauBautizado;
	}

	public Integer getBauCanton() {
		return this.bauCanton;
	}

	public void setBauCanton(Integer bauCanton) {
		this.bauCanton = bauCanton;
	}

	public Integer getBauCertificadoPor() {
		return this.bauCertificadoPor;
	}

	public void setBauCertificadoPor(Integer bauCertificadoPor) {
		this.bauCertificadoPor = bauCertificadoPor;
	}

	

	public Timestamp getBauFechaAprobacionCruso() {
		return this.bauFechaAprobacionCruso;
	}

	public void setBauFechaAprobacionCruso(Timestamp bauFechaAprobacionCruso) {
		this.bauFechaAprobacionCruso = bauFechaAprobacionCruso;
	}

	public Timestamp getBauFechaBautizo() {
		return this.bauFechaBautizo;
	}

	public void setBauFechaBautizo(Timestamp bauFechaBautizo) {
		this.bauFechaBautizo = bauFechaBautizo;
	}

	public Integer getBauMadrina() {
		return this.bauMadrina;
	}

	public void setBauMadrina(Integer bauMadrina) {
		this.bauMadrina = bauMadrina;
	}

	public String getBauNotaMarginal() {
		return this.bauNotaMarginal;
	}

	public void setBauNotaMarginal(String bauNotaMarginal) {
		this.bauNotaMarginal = bauNotaMarginal;
	}

	public Integer getBauPadrino() {
		return this.bauPadrino;
	}

	public void setBauPadrino(Integer bauPadrino) {
		this.bauPadrino = bauPadrino;
	}

	public String getBauPagina() {
		return this.bauPagina;
	}

	public void setBauPagina(String bauPagina) {
		this.bauPagina = bauPagina;
	}

	public Integer getBauProvincia() {
		return this.bauProvincia;
	}

	public void setBauProvincia(Integer bauProvincia) {
		this.bauProvincia = bauProvincia;
	}

	public String getBauToma() {
		return this.bauToma;
	}

	public void setBauToma(String bauToma) {
		this.bauToma = bauToma;
	}

	public SacerdoteDTO getEucSacerdote() {
		return this.eucSacerdote;
	}

	public void setEucSacerdote(SacerdoteDTO eucSacerdote) {
		this.eucSacerdote = eucSacerdote;
	}

	public Integer getBauParroquia() {
		return bauParroquia;
	}

	public void setBauParroquia(Integer bauParroquia) {
		this.bauParroquia = bauParroquia;
	}

	public int getBauEstado() {
		return bauEstado;
	}

	public void setBauEstado(int bauEstado) {
		this.bauEstado = bauEstado;
	}
	
	public Integer getBauMadre() {
		return bauMadre;
	}

	public Integer getBauPadre() {
		return bauPadre;
	}

	public void setBauMadre(Integer bauMadre) {
		this.bauMadre = bauMadre;
	}

	public void setBauPadre(Integer bauPadre) {
		this.bauPadre = bauPadre;
	}

	public void setBauEstado(Integer bauEstado) {
		this.bauEstado = bauEstado;
	}

	public Integer getBauEmpresa() {
		return bauEmpresa;
	}

	public void setBauEmpresa(Integer bauEmpresa) {
		this.bauEmpresa = bauEmpresa;
	}

	

}