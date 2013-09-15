package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the cabecera_catalogo_tbl database table.
 * 
 */
@Entity
@Table(name="cabecera_catalogo_tbl")
public class CabeceraCatalogoTbl implements Serializable {
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

	//bi-directional many-to-one association to DetalleCatalogoTbl
	@OneToMany(mappedBy="cabeceraCatalogoTbl")
	private Set<DetalleCatalogoTbl> detalleCatalogoTbls;

    public CabeceraCatalogoTbl() {
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

	public Set<DetalleCatalogoTbl> getDetalleCatalogoTbls() {
		return this.detalleCatalogoTbls;
	}

	public void setDetalleCatalogoTbls(Set<DetalleCatalogoTbl> detalleCatalogoTbls) {
		this.detalleCatalogoTbls = detalleCatalogoTbls;
	}
	
}