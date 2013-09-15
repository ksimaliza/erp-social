package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the formato_documento_tbl database table.
 * 
 */
@Entity
@Table(name="formato_documento_tbl")
public class FormatoDocumentoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="for_documento_pk")
	private Integer forDocumentoPk;

	@Column(name="for_documento_descripcion")
	private String forDocumentoDescripcion;

	@Column(name="for_documento_nombre")
	private String forDocumentoNombre;

	//bi-directional many-to-one association to DocumentoTbl
	@OneToMany(mappedBy="formatoDocumentoTbl")
	private Set<DocumentoTbl> documentoTbls;

    public FormatoDocumentoTbl() {
    }

	public Integer getForDocumentoPk() {
		return this.forDocumentoPk;
	}

	public void setForDocumentoPk(Integer forDocumentoPk) {
		this.forDocumentoPk = forDocumentoPk;
	}

	public String getForDocumentoDescripcion() {
		return this.forDocumentoDescripcion;
	}

	public void setForDocumentoDescripcion(String forDocumentoDescripcion) {
		this.forDocumentoDescripcion = forDocumentoDescripcion;
	}

	public String getForDocumentoNombre() {
		return this.forDocumentoNombre;
	}

	public void setForDocumentoNombre(String forDocumentoNombre) {
		this.forDocumentoNombre = forDocumentoNombre;
	}

	public Set<DocumentoTbl> getDocumentoTbls() {
		return this.documentoTbls;
	}

	public void setDocumentoTbls(Set<DocumentoTbl> documentoTbls) {
		this.documentoTbls = documentoTbls;
	}
	
}