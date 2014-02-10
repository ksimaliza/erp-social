package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the persona_certificado_tbl database table.
 * 
 */
@Entity
@Table(name="persona_certificado_tbl")
@NamedQuery(name="PersonaCertificado.findAll", query="SELECT p FROM PersonaCertificado p")
public class PersonaCertificado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERSONA_CERTIFICADO_TBL_PERCERTIFICADOPK_GENERATOR", sequenceName="PERSONA_CERTIFICADO_TBL_PER_CERTIFICADO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONA_CERTIFICADO_TBL_PERCERTIFICADOPK_GENERATOR")
	@Column(name="per_certificado_pk")
	private Integer perCertificadoPk;

	@Column(name="per_certificado_representacion")
	private String perCertificadoRepresentacion;

	//bi-directional many-to-one association to Certificado
	@ManyToOne
	@JoinColumn(name="cer_fk")
	private Certificado certificadoTbl;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_fk")
	private Persona personaTbl;

	public PersonaCertificado() {
	}

	public Integer getPerCertificadoPk() {
		return this.perCertificadoPk;
	}

	public void setPerCertificadoPk(Integer perCertificadoPk) {
		this.perCertificadoPk = perCertificadoPk;
	}

	public String getPerCertificadoRepresentacion() {
		return this.perCertificadoRepresentacion;
	}

	public void setPerCertificadoRepresentacion(String perCertificadoRepresentacion) {
		this.perCertificadoRepresentacion = perCertificadoRepresentacion;
	}

	public Certificado getCertificadoTbl() {
		return this.certificadoTbl;
	}

	public void setCertificadoTbl(Certificado certificadoTbl) {
		this.certificadoTbl = certificadoTbl;
	}

	public Persona getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(Persona personaTbl) {
		this.personaTbl = personaTbl;
	}

}