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
 * The persistent class for the persona_emp_estado_doc_tbl database table.
 * 
 */
@Entity
@Table(name="persona_emp_estado_doc_tbl")
@NamedQuery(name="PersonaEmpEstadoDoc.findAll", query="SELECT p FROM PersonaEmpEstadoDoc p")
public class PersonaEmpEstadoDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERSONA_EMP_ESTADO_DOC_TBL_PEREMPESTPK_GENERATOR", sequenceName="PERSONA_EMP_ESTADO_DOC_TBL_PER_EMP_EST_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONA_EMP_ESTADO_DOC_TBL_PEREMPESTPK_GENERATOR")
	@Column(name="per_emp_est_pk")
	private Integer perEmpEstPk;

	//bi-directional many-to-one association to EstadoDocumento
	@ManyToOne
	@JoinColumn(name="est_documento_fk")
	private EstadoDocumento estadoDocumentoTbl;

	//bi-directional many-to-one association to PersonaEmpresa
	@ManyToOne
	@JoinColumn(name="per_empresa_fk")
	private PersonaEmpresa personaEmpresaTbl;

	public PersonaEmpEstadoDoc() {
	}

	public Integer getPerEmpEstPk() {
		return this.perEmpEstPk;
	}

	public void setPerEmpEstPk(Integer perEmpEstPk) {
		this.perEmpEstPk = perEmpEstPk;
	}

	public EstadoDocumento getEstadoDocumentoTbl() {
		return this.estadoDocumentoTbl;
	}

	public void setEstadoDocumentoTbl(EstadoDocumento estadoDocumentoTbl) {
		this.estadoDocumentoTbl = estadoDocumentoTbl;
	}

	public PersonaEmpresa getPersonaEmpresaTbl() {
		return this.personaEmpresaTbl;
	}

	public void setPersonaEmpresaTbl(PersonaEmpresa personaEmpresaTbl) {
		this.personaEmpresaTbl = personaEmpresaTbl;
	}

}