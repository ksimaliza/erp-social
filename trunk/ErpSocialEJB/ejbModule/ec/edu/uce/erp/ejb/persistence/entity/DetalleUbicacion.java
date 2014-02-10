package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the detalle_ubicacion_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_ubicacion_tbl")
@NamedQuery(name="DetalleUbicacion.findAll", query="SELECT d FROM DetalleUbicacion d")
public class DetalleUbicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleUbicacionPK id;

	@Column(name="det_ubicacion_descripcion")
	private String detUbicacionDescripcion;

	@Column(name="det_ubicacion_estado")
	private String detUbicacionEstado;

	//bi-directional many-to-one association to CabeceraUbicacion
	@ManyToOne
	@JoinColumn(name="cab_ubicacion_fk", insertable=false, updatable=false)
	private CabeceraUbicacion cabeceraUbicacionTbl;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="detalleUbicacionTbl")
	private List<Persona> personaTbls;

	public DetalleUbicacion() {
	}

	public DetalleUbicacionPK getId() {
		return this.id;
	}

	public void setId(DetalleUbicacionPK id) {
		this.id = id;
	}

	public String getDetUbicacionDescripcion() {
		return this.detUbicacionDescripcion;
	}

	public void setDetUbicacionDescripcion(String detUbicacionDescripcion) {
		this.detUbicacionDescripcion = detUbicacionDescripcion;
	}

	public String getDetUbicacionEstado() {
		return this.detUbicacionEstado;
	}

	public void setDetUbicacionEstado(String detUbicacionEstado) {
		this.detUbicacionEstado = detUbicacionEstado;
	}

	public CabeceraUbicacion getCabeceraUbicacionTbl() {
		return this.cabeceraUbicacionTbl;
	}

	public void setCabeceraUbicacionTbl(CabeceraUbicacion cabeceraUbicacionTbl) {
		this.cabeceraUbicacionTbl = cabeceraUbicacionTbl;
	}

	public List<Persona> getPersonaTbls() {
		return this.personaTbls;
	}

	public void setPersonaTbls(List<Persona> personaTbls) {
		this.personaTbls = personaTbls;
	}

	public Persona addPersonaTbl(Persona personaTbl) {
		getPersonaTbls().add(personaTbl);
		personaTbl.setDetalleUbicacionTbl(this);

		return personaTbl;
	}

	public Persona removePersonaTbl(Persona personaTbl) {
		getPersonaTbls().remove(personaTbl);
		personaTbl.setDetalleUbicacionTbl(null);

		return personaTbl;
	}

}