package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the not_materia_tbl database table.
 * 
 */
@Entity
@Table(name="not_materia_tbl")
public class NotMateriaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="not_materia_pk")
	private Integer notMateriaPk;

	//bi-directional many-to-one association to MateriaTbl
    @ManyToOne
	@JoinColumn(name="mat_fk")
	private MateriaTbl materiaTbl;

	//bi-directional many-to-one association to NotasTbl
    @ManyToOne
	@JoinColumn(name="not_fk")
	private NotasTbl notasTbl;

    public NotMateriaTbl() {
    }

	public Integer getNotMateriaPk() {
		return this.notMateriaPk;
	}

	public void setNotMateriaPk(Integer notMateriaPk) {
		this.notMateriaPk = notMateriaPk;
	}

	public MateriaTbl getMateriaTbl() {
		return this.materiaTbl;
	}

	public void setMateriaTbl(MateriaTbl materiaTbl) {
		this.materiaTbl = materiaTbl;
	}
	
	public NotasTbl getNotasTbl() {
		return this.notasTbl;
	}

	public void setNotasTbl(NotasTbl notasTbl) {
		this.notasTbl = notasTbl;
	}
	
}