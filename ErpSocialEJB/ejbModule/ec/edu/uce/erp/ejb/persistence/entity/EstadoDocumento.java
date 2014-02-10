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
 * The persistent class for the estado_documento_tbl database table.
 * 
 */
@Entity
@Table(name="estado_documento_tbl")
@NamedQuery(name="EstadoDocumento.findAll", query="SELECT e FROM EstadoDocumento e")
public class EstadoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTADO_DOCUMENTO_TBL_ESTDOCUMENTOPK_GENERATOR", sequenceName="ESTADO_DOCUMENTO_TBL_EST_DOCUMENTO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTADO_DOCUMENTO_TBL_ESTDOCUMENTOPK_GENERATOR")
	@Column(name="est_documento_pk")
	private Integer estDocumentoPk;

	@Column(name="est_documento_descripcion")
	private String estDocumentoDescripcion;

	@Column(name="est_documento_nombre")
	private String estDocumentoNombre;

	//bi-directional many-to-one association to Documento
	@OneToMany(mappedBy="estadoDocumentoTbl")
	private List<Documento> documentoTbls;

	//bi-directional many-to-one association to PersonaEmpEstadoDoc
	@OneToMany(mappedBy="estadoDocumentoTbl")
	private List<PersonaEmpEstadoDoc> personaEmpEstadoDocTbls;

	//bi-directional many-to-one association to TransicionEstados
	@OneToMany(mappedBy="estadoDocumentoTbl")
	private List<TransicionEstados> transicionEstadosTbls;

	public EstadoDocumento() {
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

	public List<Documento> getDocumentoTbls() {
		return this.documentoTbls;
	}

	public void setDocumentoTbls(List<Documento> documentoTbls) {
		this.documentoTbls = documentoTbls;
	}

	public Documento addDocumentoTbl(Documento documentoTbl) {
		getDocumentoTbls().add(documentoTbl);
		documentoTbl.setEstadoDocumentoTbl(this);

		return documentoTbl;
	}

	public Documento removeDocumentoTbl(Documento documentoTbl) {
		getDocumentoTbls().remove(documentoTbl);
		documentoTbl.setEstadoDocumentoTbl(null);

		return documentoTbl;
	}

	public List<PersonaEmpEstadoDoc> getPersonaEmpEstadoDocTbls() {
		return this.personaEmpEstadoDocTbls;
	}

	public void setPersonaEmpEstadoDocTbls(List<PersonaEmpEstadoDoc> personaEmpEstadoDocTbls) {
		this.personaEmpEstadoDocTbls = personaEmpEstadoDocTbls;
	}

	public PersonaEmpEstadoDoc addPersonaEmpEstadoDocTbl(PersonaEmpEstadoDoc personaEmpEstadoDocTbl) {
		getPersonaEmpEstadoDocTbls().add(personaEmpEstadoDocTbl);
		personaEmpEstadoDocTbl.setEstadoDocumentoTbl(this);

		return personaEmpEstadoDocTbl;
	}

	public PersonaEmpEstadoDoc removePersonaEmpEstadoDocTbl(PersonaEmpEstadoDoc personaEmpEstadoDocTbl) {
		getPersonaEmpEstadoDocTbls().remove(personaEmpEstadoDocTbl);
		personaEmpEstadoDocTbl.setEstadoDocumentoTbl(null);

		return personaEmpEstadoDocTbl;
	}

	public List<TransicionEstados> getTransicionEstadosTbls() {
		return this.transicionEstadosTbls;
	}

	public void setTransicionEstadosTbls(List<TransicionEstados> transicionEstadosTbls) {
		this.transicionEstadosTbls = transicionEstadosTbls;
	}

	public TransicionEstados addTransicionEstadosTbl(TransicionEstados transicionEstadosTbl) {
		getTransicionEstadosTbls().add(transicionEstadosTbl);
		transicionEstadosTbl.setEstadoDocumentoTbl(this);

		return transicionEstadosTbl;
	}

	public TransicionEstados removeTransicionEstadosTbl(TransicionEstados transicionEstadosTbl) {
		getTransicionEstadosTbls().remove(transicionEstadosTbl);
		transicionEstadosTbl.setEstadoDocumentoTbl(null);

		return transicionEstadosTbl;
	}

}