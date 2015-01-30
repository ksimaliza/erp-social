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



}