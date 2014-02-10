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
 * The persistent class for the formato_documento_tbl database table.
 * 
 */
@Entity
@Table(name="formato_documento_tbl")
@NamedQuery(name="FormatoDocumento.findAll", query="SELECT f FROM FormatoDocumento f")
public class FormatoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FORMATO_DOCUMENTO_TBL_FORDOCUMENTOPK_GENERATOR", sequenceName="FORMATO_DOCUMENTO_TBL_FOR_DOCUMENTO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FORMATO_DOCUMENTO_TBL_FORDOCUMENTOPK_GENERATOR")
	@Column(name="for_documento_pk")
	private Integer forDocumentoPk;

	@Column(name="for_documento_descripcion")
	private String forDocumentoDescripcion;

	@Column(name="for_documento_estado")
	private String forDocumentoEstado;

	@Column(name="for_documento_nombre")
	private String forDocumentoNombre;

	//bi-directional many-to-one association to Documento
	@OneToMany(mappedBy="formatoDocumentoTbl")
	private List<Documento> documentoTbls;

	public FormatoDocumento() {
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

	public String getForDocumentoEstado() {
		return this.forDocumentoEstado;
	}

	public void setForDocumentoEstado(String forDocumentoEstado) {
		this.forDocumentoEstado = forDocumentoEstado;
	}

	public String getForDocumentoNombre() {
		return this.forDocumentoNombre;
	}

	public void setForDocumentoNombre(String forDocumentoNombre) {
		this.forDocumentoNombre = forDocumentoNombre;
	}

	public List<Documento> getDocumentoTbls() {
		return this.documentoTbls;
	}

	public void setDocumentoTbls(List<Documento> documentoTbls) {
		this.documentoTbls = documentoTbls;
	}

	public Documento addDocumentoTbl(Documento documentoTbl) {
		getDocumentoTbls().add(documentoTbl);
		documentoTbl.setFormatoDocumentoTbl(this);

		return documentoTbl;
	}

	public Documento removeDocumentoTbl(Documento documentoTbl) {
		getDocumentoTbls().remove(documentoTbl);
		documentoTbl.setFormatoDocumentoTbl(null);

		return documentoTbl;
	}

}