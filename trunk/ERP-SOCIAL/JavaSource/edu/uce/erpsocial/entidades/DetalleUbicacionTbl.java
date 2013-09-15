package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the detalle_ubicacion_tbl database table.
 * 
 */
@Entity
@Table(name="detalle_ubicacion_tbl")
public class DetalleUbicacionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleUbicacionTblPK id;

	@Column(name="det_ubicacion_descripcion")
	private String detUbicacionDescripcion;

	@Column(name="det_ubicacion_estado")
	private String detUbicacionEstado;

	//bi-directional many-to-one association to CabeceraUbicacionTbl
    @ManyToOne
	@JoinColumn(name="cab_ubicacion_fk")
	private CabeceraUbicacionTbl cabeceraUbicacionTbl;

	//bi-directional many-to-one association to PersonaTbl
	@OneToMany(mappedBy="detalleUbicacionTbl")
	private Set<PersonaTbl> personaTbls;

    public DetalleUbicacionTbl() {
    }

	public DetalleUbicacionTblPK getId() {
		return this.id;
	}

	public void setId(DetalleUbicacionTblPK id) {
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

	public CabeceraUbicacionTbl getCabeceraUbicacionTbl() {
		return this.cabeceraUbicacionTbl;
	}

	public void setCabeceraUbicacionTbl(CabeceraUbicacionTbl cabeceraUbicacionTbl) {
		this.cabeceraUbicacionTbl = cabeceraUbicacionTbl;
	}
	
	public Set<PersonaTbl> getPersonaTbls() {
		return this.personaTbls;
	}

	public void setPersonaTbls(Set<PersonaTbl> personaTbls) {
		this.personaTbls = personaTbls;
	}
	
}