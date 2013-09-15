package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the vacaciones_tbl database table.
 * 
 */
@Entity
@Table(name="vacaciones_tbl")
public class VacacionesTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vac_pk")
	private Integer vacPk;

	@Column(name="vac_descripcion")
	private String vacDescripcion;

	@Column(name="vac_tipo")
	private String vacTipo;

	//bi-directional many-to-one association to DetalleVacacionesTbl
	@OneToMany(mappedBy="vacacionesTbl")
	private Set<DetalleVacacionesTbl> detalleVacacionesTbls;

    public VacacionesTbl() {
    }

	public Integer getVacPk() {
		return this.vacPk;
	}

	public void setVacPk(Integer vacPk) {
		this.vacPk = vacPk;
	}

	public String getVacDescripcion() {
		return this.vacDescripcion;
	}

	public void setVacDescripcion(String vacDescripcion) {
		this.vacDescripcion = vacDescripcion;
	}

	public String getVacTipo() {
		return this.vacTipo;
	}

	public void setVacTipo(String vacTipo) {
		this.vacTipo = vacTipo;
	}

	public Set<DetalleVacacionesTbl> getDetalleVacacionesTbls() {
		return this.detalleVacacionesTbls;
	}

	public void setDetalleVacacionesTbls(Set<DetalleVacacionesTbl> detalleVacacionesTbls) {
		this.detalleVacacionesTbls = detalleVacacionesTbls;
	}
	
}