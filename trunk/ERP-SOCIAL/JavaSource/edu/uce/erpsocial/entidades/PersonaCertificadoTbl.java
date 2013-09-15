package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the persona_certificado_tbl database table.
 * 
 */
@Entity
@Table(name="persona_certificado_tbl")
public class PersonaCertificadoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="per_certificado_pk")
	private Integer perCertificadoPk;

	@Column(name="per_certificado_representacion")
	private String perCertificadoRepresentacion;

	//bi-directional many-to-one association to CertificadoTbl
    @ManyToOne
	@JoinColumn(name="cer_fk")
	private CertificadoTbl certificadoTbl;

	//bi-directional many-to-one association to PersonaTbl
    @ManyToOne
	@JoinColumn(name="per_fk")
	private PersonaTbl personaTbl;

    public PersonaCertificadoTbl() {
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

	public CertificadoTbl getCertificadoTbl() {
		return this.certificadoTbl;
	}

	public void setCertificadoTbl(CertificadoTbl certificadoTbl) {
		this.certificadoTbl = certificadoTbl;
	}
	
	public PersonaTbl getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(PersonaTbl personaTbl) {
		this.personaTbl = personaTbl;
	}
	
}