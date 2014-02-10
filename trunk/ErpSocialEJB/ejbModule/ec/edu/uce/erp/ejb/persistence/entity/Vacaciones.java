package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
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


/**
 * The persistent class for the vacaciones_tbl database table.
 * 
 */
@Entity
@Table(name="vacaciones_tbl")
@NamedQuery(name="Vacaciones.findAll", query="SELECT v FROM Vacaciones v")
public class Vacaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VACACIONES_TBL_VACPK_GENERATOR", sequenceName="VACACIONES_TBL_VAC_PK_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VACACIONES_TBL_VACPK_GENERATOR")
	@Column(name="vac_pk")
	private Integer vacPk;

	@Column(name="vac_descripcion")
	private String vacDescripcion;

	@Column(name="vac_estado")
	private String vacEstado;

	@Column(name="vac_tipo")
	private String vacTipo;

	//bi-directional many-to-one association to DetalleVacaciones
	@OneToMany(mappedBy="vacacionesTbl")
	private List<DetalleVacaciones> detalleVacacionesTbls;

	public Vacaciones() {
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

	public String getVacEstado() {
		return this.vacEstado;
	}

	public void setVacEstado(String vacEstado) {
		this.vacEstado = vacEstado;
	}

	public String getVacTipo() {
		return this.vacTipo;
	}

	public void setVacTipo(String vacTipo) {
		this.vacTipo = vacTipo;
	}

	public List<DetalleVacaciones> getDetalleVacacionesTbls() {
		return this.detalleVacacionesTbls;
	}

	public void setDetalleVacacionesTbls(List<DetalleVacaciones> detalleVacacionesTbls) {
		this.detalleVacacionesTbls = detalleVacacionesTbls;
	}

	public DetalleVacaciones addDetalleVacacionesTbl(DetalleVacaciones detalleVacacionesTbl) {
		getDetalleVacacionesTbls().add(detalleVacacionesTbl);
		detalleVacacionesTbl.setVacacionesTbl(this);

		return detalleVacacionesTbl;
	}

	public DetalleVacaciones removeDetalleVacacionesTbl(DetalleVacaciones detalleVacacionesTbl) {
		getDetalleVacacionesTbls().remove(detalleVacacionesTbl);
		detalleVacacionesTbl.setVacacionesTbl(null);

		return detalleVacacionesTbl;
	}

}