package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the persona_inst_estado_doc_tbl database table.
 * 
 */
@Entity
@Table(name="persona_inst_estado_doc_tbl")
public class PersonaInstEstadoDocTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="per_ins_est_pk")
	private Integer perInsEstPk;

	//bi-directional many-to-one association to EstadoDocumentoTbl
    @ManyToOne
	@JoinColumn(name="est_documento_fk")
	private EstadoDocumentoTbl estadoDocumentoTbl;

	//bi-directional many-to-one association to PersonaInstitucionTbl
    @ManyToOne
	@JoinColumn(name="per_institucion_fk")
	private PersonaInstitucionTbl personaInstitucionTbl;

    public PersonaInstEstadoDocTbl() {
    }

	public Integer getPerInsEstPk() {
		return this.perInsEstPk;
	}

	public void setPerInsEstPk(Integer perInsEstPk) {
		this.perInsEstPk = perInsEstPk;
	}

	public EstadoDocumentoTbl getEstadoDocumentoTbl() {
		return this.estadoDocumentoTbl;
	}

	public void setEstadoDocumentoTbl(EstadoDocumentoTbl estadoDocumentoTbl) {
		this.estadoDocumentoTbl = estadoDocumentoTbl;
	}
	
	public PersonaInstitucionTbl getPersonaInstitucionTbl() {
		return this.personaInstitucionTbl;
	}

	public void setPersonaInstitucionTbl(PersonaInstitucionTbl personaInstitucionTbl) {
		this.personaInstitucionTbl = personaInstitucionTbl;
	}
	
}