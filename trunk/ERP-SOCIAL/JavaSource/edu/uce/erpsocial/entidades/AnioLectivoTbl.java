package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the anio_lectivo_tbl database table.
 * 
 */
@Entity
@Table(name="anio_lectivo_tbl")
public class AnioLectivoTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ani_lectivo_pk")
	private Integer aniLectivoPk;

	@Column(name="ani_lectivo_estado")
	private String aniLectivoEstado;

    @Temporal( TemporalType.DATE)
	@Column(name="ani_lectivo_fecha_fin")
	private Date aniLectivoFechaFin;

    @Temporal( TemporalType.DATE)
	@Column(name="ani_lectivo_fecha_inicio")
	private Date aniLectivoFechaInicio;

	@Column(name="ani_lectivo_nombre")
	private String aniLectivoNombre;

	//bi-directional many-to-one association to MatriculaTbl
	@OneToMany(mappedBy="anioLectivoTbl")
	private Set<MatriculaTbl> matriculaTbls;

    public AnioLectivoTbl() {
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

	public Set<MatriculaTbl> getMatriculaTbls() {
		return this.matriculaTbls;
	}

	public void setMatriculaTbls(Set<MatriculaTbl> matriculaTbls) {
		this.matriculaTbls = matriculaTbls;
	}
	
}