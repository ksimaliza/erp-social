package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the matricula_tbl database table.
 * 
 */
@Entity
@Table(name="matricula_tbl")
public class MatriculaTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mat_pk")
	private String matPk;

	@Column(name="mat_codigo")
	private String matCodigo;

	@Column(name="mat_estado")
	private String matEstado;

    @Temporal( TemporalType.DATE)
	@Column(name="mat_fecha")
	private Date matFecha;

	//bi-directional many-to-one association to EstudianteMatriculaTbl
	@OneToMany(mappedBy="matriculaTbl")
	private Set<EstudianteMatriculaTbl> estudianteMatriculaTbls;

	//bi-directional many-to-one association to AnioLectivoTbl
    @ManyToOne
	@JoinColumn(name="ani_lectivo_pk")
	private AnioLectivoTbl anioLectivoTbl;

	//bi-directional many-to-one association to CursoTbl
    @ManyToOne
	@JoinColumn(name="cur_fk")
	private CursoTbl cursoTbl;

	//bi-directional many-to-one association to DetalleMatriculaTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_matricula_tip_fk", referencedColumnName="cab_matricula_fk"),
		@JoinColumn(name="det_matricula_tip_nivel1_fk", referencedColumnName="det_matricula_nivel1")
		})
	private DetalleMatriculaTbl detalleMatriculaTbl1;

	//bi-directional many-to-one association to DetalleMatriculaTbl
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cab_matricula_num_fk", referencedColumnName="cab_matricula_fk"),
		@JoinColumn(name="det_matricula_num_nivel1_fk", referencedColumnName="det_matricula_nivel1")
		})
	private DetalleMatriculaTbl detalleMatriculaTbl2;

    public MatriculaTbl() {
    }

	public String getMatPk() {
		return this.matPk;
	}

	public void setMatPk(String matPk) {
		this.matPk = matPk;
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

	public Set<EstudianteMatriculaTbl> getEstudianteMatriculaTbls() {
		return this.estudianteMatriculaTbls;
	}

	public void setEstudianteMatriculaTbls(Set<EstudianteMatriculaTbl> estudianteMatriculaTbls) {
		this.estudianteMatriculaTbls = estudianteMatriculaTbls;
	}
	
	public AnioLectivoTbl getAnioLectivoTbl() {
		return this.anioLectivoTbl;
	}

	public void setAnioLectivoTbl(AnioLectivoTbl anioLectivoTbl) {
		this.anioLectivoTbl = anioLectivoTbl;
	}
	
	public CursoTbl getCursoTbl() {
		return this.cursoTbl;
	}

	public void setCursoTbl(CursoTbl cursoTbl) {
		this.cursoTbl = cursoTbl;
	}
	
	public DetalleMatriculaTbl getDetalleMatriculaTbl1() {
		return this.detalleMatriculaTbl1;
	}

	public void setDetalleMatriculaTbl1(DetalleMatriculaTbl detalleMatriculaTbl1) {
		this.detalleMatriculaTbl1 = detalleMatriculaTbl1;
	}
	
	public DetalleMatriculaTbl getDetalleMatriculaTbl2() {
		return this.detalleMatriculaTbl2;
	}

	public void setDetalleMatriculaTbl2(DetalleMatriculaTbl detalleMatriculaTbl2) {
		this.detalleMatriculaTbl2 = detalleMatriculaTbl2;
	}
	
}