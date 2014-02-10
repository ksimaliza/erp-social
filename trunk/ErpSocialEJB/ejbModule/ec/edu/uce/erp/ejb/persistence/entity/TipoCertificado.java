package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the tipo_certificado_tbl database table.
 * 
 */
@Entity
@Table(name="tipo_certificado_tbl")
@NamedQuery(name="TipoCertificado.findAll", query="SELECT t FROM TipoCertificado t")
public class TipoCertificado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_CERTIFICADO_TBL_TIPCERTIFICADOPK_GENERATOR", sequenceName="TIPO_CERTIFICADO_TBL_TIP_CERTIFICADO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_CERTIFICADO_TBL_TIPCERTIFICADOPK_GENERATOR")
	@Column(name="tip_certificado_pk")
	private Integer tipCertificadoPk;

	@Column(name="tip_certificado_descripcion")
	private String tipCertificadoDescripcion;

	@Column(name="tip_certificado_estado")
	private String tipCertificadoEstado;

	@Column(name="tip_certificado_nombre")
	private String tipCertificadoNombre;

	//bi-directional many-to-one association to Certificado
	@OneToMany(mappedBy="tipoCertificadoTbl")
	private List<Certificado> certificadoTbls;

	public TipoCertificado() {
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

	public String getTipCertificadoEstado() {
		return this.tipCertificadoEstado;
	}

	public void setTipCertificadoEstado(String tipCertificadoEstado) {
		this.tipCertificadoEstado = tipCertificadoEstado;
	}

	public String getTipCertificadoNombre() {
		return this.tipCertificadoNombre;
	}

	public void setTipCertificadoNombre(String tipCertificadoNombre) {
		this.tipCertificadoNombre = tipCertificadoNombre;
	}

	public List<Certificado> getCertificadoTbls() {
		return this.certificadoTbls;
	}

	public void setCertificadoTbls(List<Certificado> certificadoTbls) {
		this.certificadoTbls = certificadoTbls;
	}

	public Certificado addCertificadoTbl(Certificado certificadoTbl) {
		getCertificadoTbls().add(certificadoTbl);
		certificadoTbl.setTipoCertificadoTbl(this);

		return certificadoTbl;
	}

	public Certificado removeCertificadoTbl(Certificado certificadoTbl) {
		getCertificadoTbls().remove(certificadoTbl);
		certificadoTbl.setTipoCertificadoTbl(null);

		return certificadoTbl;
	}

}