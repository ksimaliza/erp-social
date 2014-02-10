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
 * The persistent class for the cabecera_catalogo_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_catalogo_tbl")
@NamedQuery(name="CabeceraCatalogo.findAll", query="SELECT c FROM CabeceraCatalogo c")
public class CabeceraCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cab_catalogo_pk")
	private String cabCatalogoPk;

	@Column(name="cab_catalogo_archivo")
	private String cabCatalogoArchivo;

	@Column(name="cab_catalogo_campo")
	private String cabCatalogoCampo;

	@Column(name="cab_catalogo_descripcion")
	private String cabCatalogoDescripcion;

	@Column(name="cab_catalogo_niveles")
	private Integer cabCatalogoNiveles;

	//bi-directional many-to-one association to DetalleCatalogo
	@OneToMany(mappedBy="cabeceraCatalogoTbl")
	private List<DetalleCatalogo> detalleCatalogoTbls;

	public CabeceraCatalogo() {
	}

	public String getCabCatalogoPk() {
		return this.cabCatalogoPk;
	}

	public void setCabCatalogoPk(String cabCatalogoPk) {
		this.cabCatalogoPk = cabCatalogoPk;
	}

	public String getCabCatalogoArchivo() {
		return this.cabCatalogoArchivo;
	}

	public void setCabCatalogoArchivo(String cabCatalogoArchivo) {
		this.cabCatalogoArchivo = cabCatalogoArchivo;
	}

	public String getCabCatalogoCampo() {
		return this.cabCatalogoCampo;
	}

	public void setCabCatalogoCampo(String cabCatalogoCampo) {
		this.cabCatalogoCampo = cabCatalogoCampo;
	}

	public String getCabCatalogoDescripcion() {
		return this.cabCatalogoDescripcion;
	}

	public void setCabCatalogoDescripcion(String cabCatalogoDescripcion) {
		this.cabCatalogoDescripcion = cabCatalogoDescripcion;
	}

	public Integer getCabCatalogoNiveles() {
		return this.cabCatalogoNiveles;
	}

	public void setCabCatalogoNiveles(Integer cabCatalogoNiveles) {
		this.cabCatalogoNiveles = cabCatalogoNiveles;
	}

	public List<DetalleCatalogo> getDetalleCatalogoTbls() {
		return this.detalleCatalogoTbls;
	}

	public void setDetalleCatalogoTbls(List<DetalleCatalogo> detalleCatalogoTbls) {
		this.detalleCatalogoTbls = detalleCatalogoTbls;
	}

	public DetalleCatalogo addDetalleCatalogoTbl(DetalleCatalogo detalleCatalogoTbl) {
		getDetalleCatalogoTbls().add(detalleCatalogoTbl);
		detalleCatalogoTbl.setCabeceraCatalogoTbl(this);

		return detalleCatalogoTbl;
	}

	public DetalleCatalogo removeDetalleCatalogoTbl(DetalleCatalogo detalleCatalogoTbl) {
		getDetalleCatalogoTbls().remove(detalleCatalogoTbl);
		detalleCatalogoTbl.setCabeceraCatalogoTbl(null);

		return detalleCatalogoTbl;
	}

}