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
 * The persistent class for the padre_representante_tbl database table.
 * 
 */
@Entity
@Table(name="padre_representante_tbl")
@NamedQuery(name="PadreRepresentante.findAll", query="SELECT p FROM PadreRepresentante p")
public class PadreRepresentante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PADRE_REPRESENTANTE_TBL_PADREPRESENTANTEPK_GENERATOR", sequenceName="PADRE_REPRESENTANTE_TBL_PAD_REPRESENTANTE_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PADRE_REPRESENTANTE_TBL_PADREPRESENTANTEPK_GENERATOR")
	@Column(name="pad_representante_pk")
	private Integer padRepresentantePk;

	//bi-directional many-to-one association to PadreEstudiante
	@OneToMany(mappedBy="padreRepresentanteTbl")
	private List<PadreEstudiante> padreEstudianteTbls;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="per_fk")
	private Persona personaTbl;

	public PadreRepresentante() {
	}

	public Integer getPadRepresentantePk() {
		return this.padRepresentantePk;
	}

	public void setPadRepresentantePk(Integer padRepresentantePk) {
		this.padRepresentantePk = padRepresentantePk;
	}

	public List<PadreEstudiante> getPadreEstudianteTbls() {
		return this.padreEstudianteTbls;
	}

	public void setPadreEstudianteTbls(List<PadreEstudiante> padreEstudianteTbls) {
		this.padreEstudianteTbls = padreEstudianteTbls;
	}

	public PadreEstudiante addPadreEstudianteTbl(PadreEstudiante padreEstudianteTbl) {
		getPadreEstudianteTbls().add(padreEstudianteTbl);
		padreEstudianteTbl.setPadreRepresentanteTbl(this);

		return padreEstudianteTbl;
	}

	public PadreEstudiante removePadreEstudianteTbl(PadreEstudiante padreEstudianteTbl) {
		getPadreEstudianteTbls().remove(padreEstudianteTbl);
		padreEstudianteTbl.setPadreRepresentanteTbl(null);

		return padreEstudianteTbl;
	}

	public Persona getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(Persona personaTbl) {
		this.personaTbl = personaTbl;
	}

}