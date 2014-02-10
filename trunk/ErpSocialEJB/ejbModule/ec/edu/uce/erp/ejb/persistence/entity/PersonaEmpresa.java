package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the persona_empresa_tbl database table.
 * 
 */
@Entity
@Table(name="persona_empresa_tbl")
@NamedQuery(name="PersonaEmpresa.findAll", query="SELECT p FROM PersonaEmpresa p")
public class PersonaEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERSONA_EMPRESA_TBL_PEREMPRESAPK_GENERATOR", sequenceName="PERSONA_EMPRESA_TBL_PER_EMPRESA_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONA_EMPRESA_TBL_PEREMPRESAPK_GENERATOR")
	@Column(name="per_empresa_pk")
	private Integer perEmpresaPk;

	//bi-directional many-to-one association to PersonaEmpEstadoDoc
	@OneToMany(mappedBy="personaEmpresaTbl")
	private List<PersonaEmpEstadoDoc> personaEmpEstadoDocTbls;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="emr_fk")
	private Empresa empresaTbl;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_fk")
	private Persona personaTbl;

	public PersonaEmpresa() {
	}

	public Integer getPerEmpresaPk() {
		return this.perEmpresaPk;
	}

	public void setPerEmpresaPk(Integer perEmpresaPk) {
		this.perEmpresaPk = perEmpresaPk;
	}

	public List<PersonaEmpEstadoDoc> getPersonaEmpEstadoDocTbls() {
		return this.personaEmpEstadoDocTbls;
	}

	public void setPersonaEmpEstadoDocTbls(List<PersonaEmpEstadoDoc> personaEmpEstadoDocTbls) {
		this.personaEmpEstadoDocTbls = personaEmpEstadoDocTbls;
	}

	public PersonaEmpEstadoDoc addPersonaEmpEstadoDocTbl(PersonaEmpEstadoDoc personaEmpEstadoDocTbl) {
		getPersonaEmpEstadoDocTbls().add(personaEmpEstadoDocTbl);
		personaEmpEstadoDocTbl.setPersonaEmpresaTbl(this);

		return personaEmpEstadoDocTbl;
	}

	public PersonaEmpEstadoDoc removePersonaEmpEstadoDocTbl(PersonaEmpEstadoDoc personaEmpEstadoDocTbl) {
		getPersonaEmpEstadoDocTbls().remove(personaEmpEstadoDocTbl);
		personaEmpEstadoDocTbl.setPersonaEmpresaTbl(null);

		return personaEmpEstadoDocTbl;
	}

	public Empresa getEmpresaTbl() {
		return this.empresaTbl;
	}

	public void setEmpresaTbl(Empresa empresaTbl) {
		this.empresaTbl = empresaTbl;
	}

	public Persona getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(Persona personaTbl) {
		this.personaTbl = personaTbl;
	}

}