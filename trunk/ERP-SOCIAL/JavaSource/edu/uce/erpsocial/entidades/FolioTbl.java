package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the folio_tbl database table.
 * 
 */
@Entity
@Table(name="folio_tbl")
public class FolioTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="fol_pk")
	private Integer folPk;

	@Column(name="fol_acta")
	private Integer folActa;

	@Column(name="fol_pagina")
	private Integer folPagina;

	@Column(name="fol_tomo")
	private Integer folTomo;

	//bi-directional many-to-one association to CertificadoTbl
    @ManyToOne
	@JoinColumn(name="cer_fk")
	private CertificadoTbl certificadoTbl;

    public FolioTbl() {
    }

	public Integer getFolPk() {
		return this.folPk;
	}

	public void setFolPk(Integer folPk) {
		this.folPk = folPk;
	}

	public Integer getFolActa() {
		return this.folActa;
	}

	public void setFolActa(Integer folActa) {
		this.folActa = folActa;
	}

	public Integer getFolPagina() {
		return this.folPagina;
	}

	public void setFolPagina(Integer folPagina) {
		this.folPagina = folPagina;
	}

	public Integer getFolTomo() {
		return this.folTomo;
	}

	public void setFolTomo(Integer folTomo) {
		this.folTomo = folTomo;
	}

	public CertificadoTbl getCertificadoTbl() {
		return this.certificadoTbl;
	}

	public void setCertificadoTbl(CertificadoTbl certificadoTbl) {
		this.certificadoTbl = certificadoTbl;
	}
	
}