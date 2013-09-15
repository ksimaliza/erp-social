package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_certificado_tbl database table.
 * 
 */
@Entity
@Table(name="tipo_certificado_tbl")
public class TipoCertificadoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tip_certificado_pk")
	private Integer tipCertificadoPk;

	@Column(name="tip_certificado_descripcion")
	private String tipCertificadoDescripcion;

	@Column(name="tip_certificado_nombre")
	private String tipCertificadoNombre;

	//bi-directional many-to-one association to CertificadoTbl
	@OneToMany(mappedBy="tipoCertificadoTbl")
	private Set<CertificadoTbl> certificadoTbls;

    public TipoCertificadoTbl() {
    }

	public Integer getTipCertificadoPk() {
		return this.tipCertificadoPk;
	}

	public void setTipCertificadoPk(Integer tipCertificadoPk) {
		this.tipCertificadoPk = tipCertificadoPk;
	}

	public String getTipCertificadoDescripcion() {
		return this.tipCertificadoDescripcion;
	}

	public void setTipCertificadoDescripcion(String tipCertificadoDescripcion) {
		this.tipCertificadoDescripcion = tipCertificadoDescripcion;
	}

	public String getTipCertificadoNombre() {
		return this.tipCertificadoNombre;
	}

	public void setTipCertificadoNombre(String tipCertificadoNombre) {
		this.tipCertificadoNombre = tipCertificadoNombre;
	}

	public Set<CertificadoTbl> getCertificadoTbls() {
		return this.certificadoTbls;
	}

	public void setCertificadoTbls(Set<CertificadoTbl> certificadoTbls) {
		this.certificadoTbls = certificadoTbls;
	}
	
}