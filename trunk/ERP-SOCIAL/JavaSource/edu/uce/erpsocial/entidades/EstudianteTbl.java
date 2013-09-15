package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the estudiante_tbl database table.
 * 
 */
@Entity
@Table(name="estudiante_tbl")
public class EstudianteTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="est_pk")
	private Integer estPk;

	//bi-directional many-to-one association to EstudianteMatriculaTbl
	@OneToMany(mappedBy="estudianteTbl")
	private Set<EstudianteMatriculaTbl> estudianteMatriculaTbls;

	//bi-directional many-to-one association to PersonaTbl
    @ManyToOne
	@JoinColumn(name="per_fk")
	private PersonaTbl personaTbl;

	//bi-directional many-to-one association to NotasTbl
	@OneToMany(mappedBy="estudianteTbl")
	private Set<NotasTbl> notasTbls;

	//bi-directional many-to-one association to PadreEstudianteTbl
	@OneToMany(mappedBy="estudianteTbl")
	private Set<PadreEstudianteTbl> padreEstudianteTbls;

    public EstudianteTbl() {
    }

	public Integer getEstPk() {
		return this.estPk;
	}

	public void setEstPk(Integer estPk) {
		this.estPk = estPk;
	}

	public Set<EstudianteMatriculaTbl> getEstudianteMatriculaTbls() {
		return this.estudianteMatriculaTbls;
	}

	public void setEstudianteMatriculaTbls(Set<EstudianteMatriculaTbl> estudianteMatriculaTbls) {
		this.estudianteMatriculaTbls = estudianteMatriculaTbls;
	}
	
	public PersonaTbl getPersonaTbl() {
		return this.personaTbl;
	}

	public void setPersonaTbl(PersonaTbl personaTbl) {
		this.personaTbl = personaTbl;
	}
	
	public Set<NotasTbl> getNotasTbls() {
		return this.notasTbls;
	}

	public void setNotasTbls(Set<NotasTbl> notasTbls) {
		this.notasTbls = notasTbls;
	}
	
	public Set<PadreEstudianteTbl> getPadreEstudianteTbls() {
		return this.padreEstudianteTbls;
	}

	public void setPadreEstudianteTbls(Set<PadreEstudianteTbl> padreEstudianteTbls) {
		this.padreEstudianteTbls = padreEstudianteTbls;
	}
	
}