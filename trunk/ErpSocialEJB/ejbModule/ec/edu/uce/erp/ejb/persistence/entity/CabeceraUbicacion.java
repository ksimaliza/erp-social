package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the cabecera_ubicacion_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_ubicacion_tbl")
@NamedQuery(name="CabeceraUbicacion.findAll", query="SELECT c FROM CabeceraUbicacion c")
public class CabeceraUbicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cab_ubicacion_pk")
	private String cabUbicacionPk;

	@Column(name="cab_ubicacion_archivo")
	private String cabUbicacionArchivo;

	@Column(name="cab_ubicacion_campo")
	private String cabUbicacionCampo;

	@Column(name="cab_ubicacion_descripcion")
	private String cabUbicacionDescripcion;

	@Column(name="cab_ubicacion_niveles")
	private Integer cabUbicacionNiveles;

	//bi-directional many-to-one association to DetalleUbicacion
	@OneToMany(mappedBy="cabeceraUbicacionTbl")
	private List<DetalleUbicacion> detalleUbicacionTbls;

	public CabeceraUbicacion() {
	}

	public String getCabUbicacionPk() {
		return this.cabUbicacionPk;
	}

	public void setCabUbicacionPk(String cabUbicacionPk) {
		this.cabUbicacionPk = cabUbicacionPk;
	}

	public String getCabUbicacionArchivo() {
		return this.cabUbicacionArchivo;
	}

	public void setCabUbicacionArchivo(String cabUbicacionArchivo) {
		this.cabUbicacionArchivo = cabUbicacionArchivo;
	}

	public String getCabUbicacionCampo() {
		return this.cabUbicacionCampo;
	}

	public void setCabUbicacionCampo(String cabUbicacionCampo) {
		this.cabUbicacionCampo = cabUbicacionCampo;
	}

	public String getCabUbicacionDescripcion() {
		return this.cabUbicacionDescripcion;
	}

	public void setCabUbicacionDescripcion(String cabUbicacionDescripcion) {
		this.cabUbicacionDescripcion = cabUbicacionDescripcion;
	}

	public Integer getCabUbicacionNiveles() {
		return this.cabUbicacionNiveles;
	}

	public void setCabUbicacionNiveles(Integer cabUbicacionNiveles) {
		this.cabUbicacionNiveles = cabUbicacionNiveles;
	}

	public List<DetalleUbicacion> getDetalleUbicacionTbls() {
		return this.detalleUbicacionTbls;
	}

	public void setDetalleUbicacionTbls(List<DetalleUbicacion> detalleUbicacionTbls) {
		this.detalleUbicacionTbls = detalleUbicacionTbls;
	}

	public DetalleUbicacion addDetalleUbicacionTbl(DetalleUbicacion detalleUbicacionTbl) {
		getDetalleUbicacionTbls().add(detalleUbicacionTbl);
		detalleUbicacionTbl.setCabeceraUbicacionTbl(this);

		return detalleUbicacionTbl;
	}

	public DetalleUbicacion removeDetalleUbicacionTbl(DetalleUbicacion detalleUbicacionTbl) {
		getDetalleUbicacionTbls().remove(detalleUbicacionTbl);
		detalleUbicacionTbl.setCabeceraUbicacionTbl(null);

		return detalleUbicacionTbl;
	}

}