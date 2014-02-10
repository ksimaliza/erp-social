package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the certificado_tbl database table.
 * 
 */
@Entity
@Table(name="certificado_tbl")
@NamedQuery(name="Certificado.findAll", query="SELECT c FROM Certificado c")
public class Certificado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CERTIFICADO_TBL_CERPK_GENERATOR", sequenceName="CERTIFICADO_TBL_CER_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CERTIFICADO_TBL_CERPK_GENERATOR")
	@Column(name="cer_pk")
	private Integer cerPk;

	@Column(name="cer_costo_especie")
	private BigDecimal cerCostoEspecie;

	@Column(name="cer_estado")
	private String cerEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="cer_fecha_creacion")
	private Date cerFechaCreacion;

	@Column(name="cer_observacion")
	private String cerObservacion;

	//bi-directional many-to-one association to TipoCertificado
	@ManyToOne
	@JoinColumn(name="tip_certificado_fk")
	private TipoCertificado tipoCertificadoTbl;

	//bi-directional many-to-one association to Folio
	@OneToMany(mappedBy="certificadoTbl")
	private List<Folio> folioTbls;

	//bi-directional many-to-one association to PersonaCertificado
	@OneToMany(mappedBy="certificadoTbl")
	private List<PersonaCertificado> personaCertificadoTbls;

	public Certificado() {
	}

	public Integer getCerPk() {
		return this.cerPk;
	}

	public void setCerPk(Integer cerPk) {
		this.cerPk = cerPk;
	}

	public BigDecimal getCerCostoEspecie() {
		return this.cerCostoEspecie;
	}

	public void setCerCostoEspecie(BigDecimal cerCostoEspecie) {
		this.cerCostoEspecie = cerCostoEspecie;
	}

	public String getCerEstado() {
		return this.cerEstado;
	}

	public void setCerEstado(String cerEstado) {
		this.cerEstado = cerEstado;
	}

	public Date getCerFechaCreacion() {
		return this.cerFechaCreacion;
	}

	public void setCerFechaCreacion(Date cerFechaCreacion) {
		this.cerFechaCreacion = cerFechaCreacion;
	}

	public String getCerObservacion() {
		return this.cerObservacion;
	}

	public void setCerObservacion(String cerObservacion) {
		this.cerObservacion = cerObservacion;
	}

	public TipoCertificado getTipoCertificadoTbl() {
		return this.tipoCertificadoTbl;
	}

	public void setTipoCertificadoTbl(TipoCertificado tipoCertificadoTbl) {
		this.tipoCertificadoTbl = tipoCertificadoTbl;
	}

	public List<Folio> getFolioTbls() {
		return this.folioTbls;
	}

	public void setFolioTbls(List<Folio> folioTbls) {
		this.folioTbls = folioTbls;
	}

	public Folio addFolioTbl(Folio folioTbl) {
		getFolioTbls().add(folioTbl);
		folioTbl.setCertificadoTbl(this);

		return folioTbl;
	}

	public Folio removeFolioTbl(Folio folioTbl) {
		getFolioTbls().remove(folioTbl);
		folioTbl.setCertificadoTbl(null);

		return folioTbl;
	}

	public List<PersonaCertificado> getPersonaCertificadoTbls() {
		return this.personaCertificadoTbls;
	}

	public void setPersonaCertificadoTbls(List<PersonaCertificado> personaCertificadoTbls) {
		this.personaCertificadoTbls = personaCertificadoTbls;
	}

	public PersonaCertificado addPersonaCertificadoTbl(PersonaCertificado personaCertificadoTbl) {
		getPersonaCertificadoTbls().add(personaCertificadoTbl);
		personaCertificadoTbl.setCertificadoTbl(this);

		return personaCertificadoTbl;
	}

	public PersonaCertificado removePersonaCertificadoTbl(PersonaCertificado personaCertificadoTbl) {
		getPersonaCertificadoTbls().remove(personaCertificadoTbl);
		personaCertificadoTbl.setCertificadoTbl(null);

		return personaCertificadoTbl;
	}

}