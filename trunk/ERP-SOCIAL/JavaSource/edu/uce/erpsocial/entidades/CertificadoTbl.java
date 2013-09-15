package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the certificado_tbl database table.
 * 
 */
@Entity
@Table(name="certificado_tbl")
public class CertificadoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cer_pk")
	private Integer cerPk;

	@Column(name="cer_costo_especie")
	private BigDecimal cerCostoEspecie;

    @Temporal( TemporalType.DATE)
	@Column(name="cer_fecha_creacion")
	private Date cerFechaCreacion;

	@Column(name="cer_observacion")
	private String cerObservacion;

	//bi-directional many-to-one association to TipoCertificadoTbl
    @ManyToOne
	@JoinColumn(name="tip_certificado_fk")
	private TipoCertificadoTbl tipoCertificadoTbl;

	//bi-directional many-to-one association to FolioTbl
	@OneToMany(mappedBy="certificadoTbl")
	private Set<FolioTbl> folioTbls;

	//bi-directional many-to-one association to PersonaCertificadoTbl
	@OneToMany(mappedBy="certificadoTbl")
	private Set<PersonaCertificadoTbl> personaCertificadoTbls;

    public CertificadoTbl() {
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

	public TipoCertificadoTbl getTipoCertificadoTbl() {
		return this.tipoCertificadoTbl;
	}

	public void setTipoCertificadoTbl(TipoCertificadoTbl tipoCertificadoTbl) {
		this.tipoCertificadoTbl = tipoCertificadoTbl;
	}
	
	public Set<FolioTbl> getFolioTbls() {
		return this.folioTbls;
	}

	public void setFolioTbls(Set<FolioTbl> folioTbls) {
		this.folioTbls = folioTbls;
	}
	
	public Set<PersonaCertificadoTbl> getPersonaCertificadoTbls() {
		return this.personaCertificadoTbls;
	}

	public void setPersonaCertificadoTbls(Set<PersonaCertificadoTbl> personaCertificadoTbls) {
		this.personaCertificadoTbls = personaCertificadoTbls;
	}
	
}