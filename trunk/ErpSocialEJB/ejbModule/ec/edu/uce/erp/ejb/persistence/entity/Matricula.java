package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the matricula_tbl database table.
 * 
 */
@Entity
@Table(name="matricula_tbl")
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mat_pk")
	private String matPk;

	@Column(name="emr_fk")
	private Integer emrFk;

	@Column(name="mat_codigo")
	private String matCodigo;

	@Column(name="mat_estado")
	private String matEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="mat_fecha")
	private Date matFecha;

	//bi-directional many-to-one association to EstudianteMatriculaTbl
	@OneToMany(mappedBy="matriculaTbl")
	private List<EstudianteMatriculaTbl> estudianteMatriculaTbls;

	//bi-directional many-to-one association to AnioLectivo
	@ManyToOne
	@JoinColumn(name="ani_lectivo_pk")
	private AnioLectivo anioLectivoTbl;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="cur_fk")
	private Curso cursoTbl;

	//bi-directional many-to-one association to DetalleMatricula
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_matricula_tip_fk", referencedColumnName="cab_matricula_fk"),
		@JoinColumn(name="det_matricula_tip_nivel1_fk", referencedColumnName="det_matricula_nivel1")
		})
	private DetalleMatricula detalleMatriculaTbl;

	public Matricula() {
	}

	public String getMatPk() {
		return this.matPk;
	}

	public void setMatPk(String matPk) {
		this.matPk = matPk;
	}

	public Integer getEmrFk() {
		return this.emrFk;
	}

	public void setEmrFk(Integer emrFk) {
		this.emrFk = emrFk;
	}

	public String getMatCodigo() {
		return this.matCodigo;
	}

	public void setMatCodigo(String matCodigo) {
		this.matCodigo = matCodigo;
	}

	public String getMatEstado() {
		return this.matEstado;
	}

	public void setMatEstado(String matEstado) {
		this.matEstado = matEstado;
	}

	public Date getMatFecha() {
		return this.matFecha;
	}

	public void setMatFecha(Date matFecha) {
		this.matFecha = matFecha;
	}

	public List<EstudianteMatriculaTbl> getEstudianteMatriculaTbls() {
		return this.estudianteMatriculaTbls;
	}

	public void setEstudianteMatriculaTbls(List<EstudianteMatriculaTbl> estudianteMatriculaTbls) {
		this.estudianteMatriculaTbls = estudianteMatriculaTbls;
	}

	public EstudianteMatriculaTbl addEstudianteMatriculaTbl(EstudianteMatriculaTbl estudianteMatriculaTbl) {
		getEstudianteMatriculaTbls().add(estudianteMatriculaTbl);
		estudianteMatriculaTbl.setMatriculaTbl(this);

		return estudianteMatriculaTbl;
	}

	public EstudianteMatriculaTbl removeEstudianteMatriculaTbl(EstudianteMatriculaTbl estudianteMatriculaTbl) {
		getEstudianteMatriculaTbls().remove(estudianteMatriculaTbl);
		estudianteMatriculaTbl.setMatriculaTbl(null);

		return estudianteMatriculaTbl;
	}

	public AnioLectivo getAnioLectivoTbl() {
		return this.anioLectivoTbl;
	}

	public void setAnioLectivoTbl(AnioLectivo anioLectivoTbl) {
		this.anioLectivoTbl = anioLectivoTbl;
	}

	public Curso getCursoTbl() {
		return this.cursoTbl;
	}

	public void setCursoTbl(Curso cursoTbl) {
		this.cursoTbl = cursoTbl;
	}

	public DetalleMatricula getDetalleMatriculaTbl() {
		return this.detalleMatriculaTbl;
	}

	public void setDetalleMatriculaTbl(DetalleMatricula detalleMatriculaTbl) {
		this.detalleMatriculaTbl = detalleMatriculaTbl;
	}

}