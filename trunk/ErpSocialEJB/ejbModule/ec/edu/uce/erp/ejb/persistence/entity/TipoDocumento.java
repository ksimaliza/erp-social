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
 * The persistent class for the tipo_documento_tbl database table.
 * 
 */
@Entity
@Table(name="tipo_documento_tbl")
@NamedQuery(name="TipoDocumento.findAll", query="SELECT t FROM TipoDocumento t")
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_DOCUMENTO_TBL_TIPDOCUMENTOPK_GENERATOR", sequenceName="TIPO_DOCUMENTO_TBL_TIP_DOCUMENTO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_DOCUMENTO_TBL_TIPDOCUMENTOPK_GENERATOR")
	@Column(name="tip_documento_pk")
	private Integer tipDocumentoPk;

	@Column(name="tip_documento_descripcion")
	private String tipDocumentoDescripcion;

	@Column(name="tip_documento_estado")
	private String tipDocumentoEstado;

	@Column(name="tip_documento_nombre")
	private String tipDocumentoNombre;

	//bi-directional many-to-one association to Documento
	@OneToMany(mappedBy="tipoDocumentoTbl")
	private List<Documento> documentoTbls;

	//bi-directional many-to-one association to ValidacionDocumento
	@OneToMany(mappedBy="tipoDocumentoTbl")
	private List<ValidacionDocumento> validacionDocumentoTbls;

	public TipoDocumento() {
	}

	public Integer getTipDocumentoPk() {
		return this.tipDocumentoPk;
	}

	public void setTipDocumentoPk(Integer tipDocumentoPk) {
		this.tipDocumentoPk = tipDocumentoPk;
	}

	public String getTipDocumentoDescripcion() {
		return this.tipDocumentoDescripcion;
	}

	public void setTipDocumentoDescripcion(String tipDocumentoDescripcion) {
		this.tipDocumentoDescripcion = tipDocumentoDescripcion;
	}

	public String getTipDocumentoEstado() {
		return this.tipDocumentoEstado;
	}

	public void setTipDocumentoEstado(String tipDocumentoEstado) {
		this.tipDocumentoEstado = tipDocumentoEstado;
	}

	public String getTipDocumentoNombre() {
		return this.tipDocumentoNombre;
	}

	public void setTipDocumentoNombre(String tipDocumentoNombre) {
		this.tipDocumentoNombre = tipDocumentoNombre;
	}

	public List<Documento> getDocumentoTbls() {
		return this.documentoTbls;
	}

	public void setDocumentoTbls(List<Documento> documentoTbls) {
		this.documentoTbls = documentoTbls;
	}

	public Documento addDocumentoTbl(Documento documentoTbl) {
		getDocumentoTbls().add(documentoTbl);
		documentoTbl.setTipoDocumentoTbl(this);

		return documentoTbl;
	}

	public Documento removeDocumentoTbl(Documento documentoTbl) {
		getDocumentoTbls().remove(documentoTbl);
		documentoTbl.setTipoDocumentoTbl(null);

		return documentoTbl;
	}

	public List<ValidacionDocumento> getValidacionDocumentoTbls() {
		return this.validacionDocumentoTbls;
	}

	public void setValidacionDocumentoTbls(List<ValidacionDocumento> validacionDocumentoTbls) {
		this.validacionDocumentoTbls = validacionDocumentoTbls;
	}

	public ValidacionDocumento addValidacionDocumentoTbl(ValidacionDocumento validacionDocumentoTbl) {
		getValidacionDocumentoTbls().add(validacionDocumentoTbl);
		validacionDocumentoTbl.setTipoDocumentoTbl(this);

		return validacionDocumentoTbl;
	}

	public ValidacionDocumento removeValidacionDocumentoTbl(ValidacionDocumento validacionDocumentoTbl) {
		getValidacionDocumentoTbls().remove(validacionDocumentoTbl);
		validacionDocumentoTbl.setTipoDocumentoTbl(null);

		return validacionDocumentoTbl;
	}

}