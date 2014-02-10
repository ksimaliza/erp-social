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
 * The persistent class for the mat_anio_tbl database table.
 * 
 */
@Entity
@Table(name="mat_anio_tbl")
@NamedQuery(name="MatAnio.findAll", query="SELECT m FROM MatAnio m")
public class MatAnio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAT_ANIO_TBL_MATANIOPK_GENERATOR", sequenceName="MAT_ANIO_TBL_MAT_ANIO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAT_ANIO_TBL_MATANIOPK_GENERATOR")
	@Column(name="mat_anio_pk")
	private Integer matAnioPk;

	//bi-directional many-to-one association to AnioLectivo
	@ManyToOne
	@JoinColumn(name="ani_lectivo_pk")
	private AnioLectivo anioLectivoTbl;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="mat_pk")
	private Materia materiaTbl;

	public MatAnio() {
	}

	public Integer getMatAnioPk() {
		return this.matAnioPk;
	}

	public void setMatAnioPk(Integer matAnioPk) {
		this.matAnioPk = matAnioPk;
	}

	public AnioLectivo getAnioLectivoTbl() {
		return this.anioLectivoTbl;
	}

	public void setAnioLectivoTbl(AnioLectivo anioLectivoTbl) {
		this.anioLectivoTbl = anioLectivoTbl;
	}

	public Materia getMateriaTbl() {
		return this.materiaTbl;
	}

	public void setMateriaTbl(Materia materiaTbl) {
		this.materiaTbl = materiaTbl;
	}

}