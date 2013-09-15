package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the padre_representante_tbl database table.
 * 
 */
@Entity
@Table(name="padre_representante_tbl")
public class PadreRepresentanteTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pad_representante_pk")
	private Integer padRepresentantePk;

	//bi-directional many-to-one association to PadreEstudianteTbl
	@OneToMany(mappedBy="padreRepresentanteTbl")
	private Set<PadreEstudianteTbl> padreEstudianteTbls;

	//bi-directional many-to-one association to PersonaTbl
    @ManyToOne
	@JoinColumn(name="per_fk")
	private PersonaTbl personaTbl;

    public PadreRepresentanteTbl() {
    }

	public Integer getPadRepresentantePk() {
		return this.padRepresentantePk;
	}

	public void setPadRepresentantePk(Integer padRepresentantePk) {
		this.padRepresentantePk = padRepresentantePk;
	}

	public Set<PadreEstudianteTbl> getPadreEstudianteTbls() {
		return this.padreEstudianteTbls;
	}

	public void setPadreEstudianteTbls(Set<PadreEstudianteTbl> padreEstudianteTbls) {
		this.padreEstudianteTbls = padreEstudianteTbls;
	}
	
	public PersonaTbl getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(PersonaTbl personaTbl) {
		this.personaTbl = personaTbl;
	}
	
}