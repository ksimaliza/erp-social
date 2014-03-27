package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the categoria_bien_tbl database table.
 * 
 */
@Entity
@Table(name="categoria_bien_tbl")
@NamedQuery(name="CategoriaBien.findAll", query="SELECT c FROM CategoriaBien c")
public class CategoriaBien implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CategoriaBienPK id;

	@Column(name="cat_bien_descripcion")
	private String catBienDescripcion;

	@Column(name="cat_bien_estado")
	private String catBienEstado;

	@Column(name="cat_bien_nombre")
	private String catBienNombre;

	//bi-directional many-to-one association to LineaBien
	@ManyToOne
	@JoinColumn(name="lin_bien_pk", referencedColumnName="lin_bien_pk", insertable=false, updatable=false)
	private LineaBien lineaBienTbl;

	public CategoriaBien() {
	}

	public CategoriaBienPK getId() {
		return this.id;
	}

	public void setId(CategoriaBienPK id) {
		this.id = id;
	}

	public String getCatBienDescripcion() {
		return this.catBienDescripcion;
	}

	public void setCatBienDescripcion(String catBienDescripcion) {
		this.catBienDescripcion = catBienDescripcion;
	}

	public String getCatBienEstado() {
		return this.catBienEstado;
	}

	public void setCatBienEstado(String catBienEstado) {
		this.catBienEstado = catBienEstado;
	}

	public String getCatBienNombre() {
		return this.catBienNombre;
	}

	public void setCatBienNombre(String catBienNombre) {
		this.catBienNombre = catBienNombre;
	}

	public LineaBien getLineaBienTbl() {
		return this.lineaBienTbl;
	}

	public void setLineaBienTbl(LineaBien lineaBienTbl) {
		this.lineaBienTbl = lineaBienTbl;
	}

}