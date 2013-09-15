package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the estado_documento_tbl database table.
 * 
 */
@Entity
@Table(name="estado_documento_tbl")
public class EstadoDocumentoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="est_documento_pk")
	private Integer estDocumentoPk;

	@Column(name="est_documento_descripcion")
	private String estDocumentoDescripcion;

	@Column(name="est_documento_nombre")
	private String estDocumentoNombre;

	//bi-directional many-to-one association to DocumentoTbl
	@OneToMany(mappedBy="estadoDocumentoTbl")
	private Set<DocumentoTbl> documentoTbls;

	//bi-directional many-to-one association to PersonaInstEstadoDocTbl
	@OneToMany(mappedBy="estadoDocumentoTbl")
	private Set<PersonaInstEstadoDocTbl> personaInstEstadoDocTbls;

	//bi-directional many-to-one association to TransicionEstadosTbl
	@OneToMany(mappedBy="estadoDocumentoTbl")
	private Set<TransicionEstadosTbl> transicionEstadosTbls;

    public EstadoDocumentoTbl() {
    }

	public Integer getEstDocumentoPk() {
		return this.estDocumentoPk;
	}

	public void setEstDocumentoPk(Integer estDocumentoPk) {
		this.estDocumentoPk = estDocumentoPk;
	}

	public String getEstDocumentoDescripcion() {
		return this.estDocumentoDescripcion;
	}

	public void setEstDocumentoDescripcion(String estDocumentoDescripcion) {
		this.estDocumentoDescripcion = estDocumentoDescripcion;
	}

	public String getEstDocumentoNombre() {
		return this.estDocumentoNombre;
	}

	public void setEstDocumentoNombre(String estDocumentoNombre) {
		this.estDocumentoNombre = estDocumentoNombre;
	}

	public Set<DocumentoTbl> getDocumentoTbls() {
		return this.documentoTbls;
	}

	public void setDocumentoTbls(Set<DocumentoTbl> documentoTbls) {
		this.documentoTbls = documentoTbls;
	}
	
	public Set<PersonaInstEstadoDocTbl> getPersonaInstEstadoDocTbls() {
		return this.personaInstEstadoDocTbls;
	}

	public void setPersonaInstEstadoDocTbls(Set<PersonaInstEstadoDocTbl> personaInstEstadoDocTbls) {
		this.personaInstEstadoDocTbls = personaInstEstadoDocTbls;
	}
	
	public Set<TransicionEstadosTbl> getTransicionEstadosTbls() {
		return this.transicionEstadosTbls;
	}

	public void setTransicionEstadosTbls(Set<TransicionEstadosTbl> transicionEstadosTbls) {
		this.transicionEstadosTbls = transicionEstadosTbls;
	}
	
}