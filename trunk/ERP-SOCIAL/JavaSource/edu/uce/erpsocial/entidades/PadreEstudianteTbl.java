package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the padre_estudiante_tbl database table.
 * 
 */
@Entity
@Table(name="padre_estudiante_tbl")
public class PadreEstudianteTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pad_estudiante_pk")
	private Integer padEstudiantePk;

	//bi-directional many-to-one association to EstudianteTbl
    @ManyToOne
	@JoinColumn(name="est_fk")
	private EstudianteTbl estudianteTbl;

	//bi-directional many-to-one association to PadreRepresentanteTbl
    @ManyToOne
	@JoinColumn(name="pad_representante_fk")
	private PadreRepresentanteTbl padreRepresentanteTbl;

    public PadreEstudianteTbl() {
    }

	public Integer getPadEstudiantePk() {
		return this.padEstudiantePk;
	}

	public void setPadEstudiantePk(Integer padEstudiantePk) {
		this.padEstudiantePk = padEstudiantePk;
	}

	public EstudianteTbl getEstudianteTbl() {
		return this.estudianteTbl;
	}

	public void setEstudianteTbl(EstudianteTbl estudianteTbl) {
		this.estudianteTbl = estudianteTbl;
	}
	
	public PadreRepresentanteTbl getPadreRepresentanteTbl() {
		return this.padreRepresentanteTbl;
	}

	public void setPadreRepresentanteTbl(PadreRepresentanteTbl padreRepresentanteTbl) {
		this.padreRepresentanteTbl = padreRepresentanteTbl;
	}
	
}