package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the anio_lectivo_tbl database table.
 * 
 */
@Entity
@Table(name="anio_lectivo_tbl")
@NamedQuery(name="AnioLectivo.findAll", query="SELECT a FROM AnioLectivo a")
public class AnioLectivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ANIO_LECTIVO_TBL_ANILECTIVOPK_GENERATOR", sequenceName="ANIO_LECTIVO_TBL_ANI_LECTIVO_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANIO_LECTIVO_TBL_ANILECTIVOPK_GENERATOR")
	@Column(name="ani_lectivo_pk")
	private Integer aniLectivoPk;

	@Column(name="ani_lectivo_estado")
	private String aniLectivoEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="ani_lectivo_fecha_fin")
	private Date aniLectivoFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="ani_lectivo_fecha_inicio")
	private Date aniLectivoFechaInicio;

	@Column(name="ani_lectivo_nombre")
	private String aniLectivoNombre;

	//bi-directional many-to-one association to MatAnio
	@OneToMany(mappedBy="anioLectivoTbl")
	private List<MatAnio> matAnioTbls;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="anioLectivoTbl")
	private List<Matricula> matriculaTbls;

	public AnioLectivo() {
	}

	public Integer getAniLectivoPk() {
		return this.aniLectivoPk;
	}

	public void setAniLectivoPk(Integer aniLectivoPk) {
		this.aniLectivoPk = aniLectivoPk;
	}

	public String getAniLectivoEstado() {
		return this.aniLectivoEstado;
	}

	public void setAniLectivoEstado(String aniLectivoEstado) {
		this.aniLectivoEstado = aniLectivoEstado;
	}

	public Date getAniLectivoFechaFin() {
		return this.aniLectivoFechaFin;
	}

	public void setAniLectivoFechaFin(Date aniLectivoFechaFin) {
		this.aniLectivoFechaFin = aniLectivoFechaFin;
	}

	public Date getAniLectivoFechaInicio() {
		return this.aniLectivoFechaInicio;
	}

	public void setAniLectivoFechaInicio(Date aniLectivoFechaInicio) {
		this.aniLectivoFechaInicio = aniLectivoFechaInicio;
	}

	public String getAniLectivoNombre() {
		return this.aniLectivoNombre;
	}

	public void setAniLectivoNombre(String aniLectivoNombre) {
		this.aniLectivoNombre = aniLectivoNombre;
	}

	public List<MatAnio> getMatAnioTbls() {
		return this.matAnioTbls;
	}

	public void setMatAnioTbls(List<MatAnio> matAnioTbls) {
		this.matAnioTbls = matAnioTbls;
	}

	public MatAnio addMatAnioTbl(MatAnio matAnioTbl) {
		getMatAnioTbls().add(matAnioTbl);
		matAnioTbl.setAnioLectivoTbl(this);

		return matAnioTbl;
	}

	public MatAnio removeMatAnioTbl(MatAnio matAnioTbl) {
		getMatAnioTbls().remove(matAnioTbl);
		matAnioTbl.setAnioLectivoTbl(null);

		return matAnioTbl;
	}

	public List<Matricula> getMatriculaTbls() {
		return this.matriculaTbls;
	}

	public void setMatriculaTbls(List<Matricula> matriculaTbls) {
		this.matriculaTbls = matriculaTbls;
	}

	public Matricula addMatriculaTbl(Matricula matriculaTbl) {
		getMatriculaTbls().add(matriculaTbl);
		matriculaTbl.setAnioLectivoTbl(this);

		return matriculaTbl;
	}

	public Matricula removeMatriculaTbl(Matricula matriculaTbl) {
		getMatriculaTbls().remove(matriculaTbl);
		matriculaTbl.setAnioLectivoTbl(null);

		return matriculaTbl;
	}

}