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
 * The persistent class for the folio_tbl database table.
 * 
 */
@Entity
@Table(name="folio_tbl")
@NamedQuery(name="Folio.findAll", query="SELECT f FROM Folio f")
public class Folio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FOLIO_TBL_FOLPK_GENERATOR", sequenceName="FOLIO_TBL_FOL_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FOLIO_TBL_FOLPK_GENERATOR")
	@Column(name="fol_pk")
	private Integer folPk;

	@Column(name="fol_acta")
	private Integer folActa;

	@Column(name="fol_estado")
	private String folEstado;

	@Column(name="fol_pagina")
	private Integer folPagina;

	@Column(name="fol_tomo")
	private Integer folTomo;

	//bi-directional many-to-one association to Certificado
	@ManyToOne
	@JoinColumn(name="cer_fk")
	private Certificado certificadoTbl;

	public Folio() {
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

	public String getFolEstado() {
		return this.folEstado;
	}

	public void setFolEstado(String folEstado) {
		this.folEstado = folEstado;
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

	public Certificado getCertificadoTbl() {
		return this.certificadoTbl;
	}

	public void setCertificadoTbl(Certificado certificadoTbl) {
		this.certificadoTbl = certificadoTbl;
	}

}