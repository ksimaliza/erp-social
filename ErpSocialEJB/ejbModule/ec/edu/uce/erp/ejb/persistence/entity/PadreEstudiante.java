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
 * The persistent class for the padre_estudiante_tbl database table.
 * 
 */
@Entity
@Table(name="padre_estudiante_tbl")
@NamedQuery(name="PadreEstudiante.findAll", query="SELECT p FROM PadreEstudiante p")
public class PadreEstudiante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PADRE_ESTUDIANTE_TBL_PADESTUDIANTEPK_GENERATOR", sequenceName="PADRE_ESTUDIANTE_TBL_PAD_ESTUDIANTE_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PADRE_ESTUDIANTE_TBL_PADESTUDIANTEPK_GENERATOR")
	@Column(name="pad_estudiante_pk")
	private Integer padEstudiantePk;

	//bi-directional many-to-one association to Estudiante
	@ManyToOne
	@JoinColumn(name="est_fk")
	private Estudiante estudianteTbl;

	//bi-directional many-to-one association to PadreRepresentante
	@ManyToOne
	@JoinColumn(name="pad_representante_fk")
	private PadreRepresentante padreRepresentanteTbl;

	public PadreEstudiante() {
	}

	public Integer getPadEstudiantePk() {
		return this.padEstudiantePk;
	}

	public void setPadEstudiantePk(Integer padEstudiantePk) {
		this.padEstudiantePk = padEstudiantePk;
	}

	public Estudiante getEstudianteTbl() {
		return this.estudianteTbl;
	}

	public void setEstudianteTbl(Estudiante estudianteTbl) {
		this.estudianteTbl = estudianteTbl;
	}

	public PadreRepresentante getPadreRepresentanteTbl() {
		return this.padreRepresentanteTbl;
	}

	public void setPadreRepresentanteTbl(PadreRepresentante padreRepresentanteTbl) {
		this.padreRepresentanteTbl = padreRepresentanteTbl;
	}

}