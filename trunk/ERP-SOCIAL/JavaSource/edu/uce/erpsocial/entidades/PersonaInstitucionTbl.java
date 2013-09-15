package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the persona_institucion_tbl database table.
 * 
 */
@Entity
@Table(name="persona_institucion_tbl")
public class PersonaInstitucionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="per_institucion_pk")
	private Integer perInstitucionPk;

	//bi-directional many-to-one association to PersonaInstEstadoDocTbl
	@OneToMany(mappedBy="personaInstitucionTbl")
	private Set<PersonaInstEstadoDocTbl> personaInstEstadoDocTbls;

	//bi-directional many-to-one association to InstitucionTbl
    @ManyToOne
	@JoinColumn(name="ins_fk")
	private InstitucionTbl institucionTbl;

	//bi-directional many-to-one association to PersonaTbl
    @ManyToOne
	@JoinColumn(name="per_fk")
	private PersonaTbl personaTbl;

    public PersonaInstitucionTbl() {
    }

	public Integer getPerInstitucionPk() {
		return this.perInstitucionPk;
	}

	public void setPerInstitucionPk(Integer perInstitucionPk) {
		this.perInstitucionPk = perInstitucionPk;
	}

	public Set<PersonaInstEstadoDocTbl> getPersonaInstEstadoDocTbls() {
		return this.personaInstEstadoDocTbls;
	}

	public void setPersonaInstEstadoDocTbls(Set<PersonaInstEstadoDocTbl> personaInstEstadoDocTbls) {
		this.personaInstEstadoDocTbls = personaInstEstadoDocTbls;
	}
	
	public InstitucionTbl getInstitucionTbl() {
		return this.institucionTbl;
	}

	public void setInstitucionTbl(InstitucionTbl institucionTbl) {
		this.institucionTbl = institucionTbl;
	}
	
	public PersonaTbl getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(PersonaTbl personaTbl) {
		this.personaTbl = personaTbl;
	}
	
}