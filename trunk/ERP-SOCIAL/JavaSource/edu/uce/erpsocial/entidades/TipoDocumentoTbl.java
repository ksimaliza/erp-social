package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_documento_tbl database table.
 * 
 */
@Entity
@Table(name="tipo_documento_tbl")
public class TipoDocumentoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tip_documento_pk")
	private Integer tipDocumentoPk;

	@Column(name="tip_documento_descripcion")
	private String tipDocumentoDescripcion;

	@Column(name="tip_documento_nombre")
	private String tipDocumentoNombre;

	//bi-directional many-to-one association to DocumentoTbl
	@OneToMany(mappedBy="tipoDocumentoTbl")
	private Set<DocumentoTbl> documentoTbls;

	//bi-directional many-to-one association to ValidacionDocumentoTbl
	@OneToMany(mappedBy="tipoDocumentoTbl")
	private Set<ValidacionDocumentoTbl> validacionDocumentoTbls;

    public TipoDocumentoTbl() {
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

	public String getTipDocumentoNombre() {
		return this.tipDocumentoNombre;
	}

	public void setTipDocumentoNombre(String tipDocumentoNombre) {
		this.tipDocumentoNombre = tipDocumentoNombre;
	}

	public Set<DocumentoTbl> getDocumentoTbls() {
		return this.documentoTbls;
	}

	public void setDocumentoTbls(Set<DocumentoTbl> documentoTbls) {
		this.documentoTbls = documentoTbls;
	}
	
	public Set<ValidacionDocumentoTbl> getValidacionDocumentoTbls() {
		return this.validacionDocumentoTbls;
	}

	public void setValidacionDocumentoTbls(Set<ValidacionDocumentoTbl> validacionDocumentoTbls) {
		this.validacionDocumentoTbls = validacionDocumentoTbls;
	}
	
}