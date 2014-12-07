package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catalogo_tipo_tbl database table.
 * 
 */
@Entity
@Table(name="catalogo_tipo_tbl")
@NamedQuery(name="CatalogoTipo.findAll", query="SELECT c FROM CatalogoTipo c")
public class CatalogoTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="catalogo_tipo_id")
	private Long catalogoTipoId;

	@Column(name="catalogo_tipo_desc")
	private String catalogoTipoDesc;

	@Column(name="catalogo_tipo_estado")
	private String catalogoTipoEstado;

	@Column(name="catalogo_tipo_nombre")
	private String catalogoTipoNombre;

	//bi-directional many-to-one association to CatalogoValor
	@OneToMany(mappedBy="catalogoTipoTbl")
	private List<CatalogoValor> catalogoValorTbls;

	public CatalogoTipo() {
	}

	public Long getCatalogoTipoId() {
		return this.catalogoTipoId;
	}

	public void setCatalogoTipoId(Long catalogoTipoId) {
		this.catalogoTipoId = catalogoTipoId;
	}

	public String getCatalogoTipoDesc() {
		return this.catalogoTipoDesc;
	}

	public void setCatalogoTipoDesc(String catalogoTipoDesc) {
		this.catalogoTipoDesc = catalogoTipoDesc;
	}

	public String getCatalogoTipoEstado() {
		return this.catalogoTipoEstado;
	}

	public void setCatalogoTipoEstado(String catalogoTipoEstado) {
		this.catalogoTipoEstado = catalogoTipoEstado;
	}

	public String getCatalogoTipoNombre() {
		return this.catalogoTipoNombre;
	}

	public void setCatalogoTipoNombre(String catalogoTipoNombre) {
		this.catalogoTipoNombre = catalogoTipoNombre;
	}

	public List<CatalogoValor> getCatalogoValorTbls() {
		return this.catalogoValorTbls;
	}

	public void setCatalogoValorTbls(List<CatalogoValor> catalogoValorTbls) {
		this.catalogoValorTbls = catalogoValorTbls;
	}

	public CatalogoValor addCatalogoValorTbl(CatalogoValor catalogoValorTbl) {
		getCatalogoValorTbls().add(catalogoValorTbl);
		catalogoValorTbl.setCatalogoTipoTbl(this);

		return catalogoValorTbl;
	}

	public CatalogoValor removeCatalogoValorTbl(CatalogoValor catalogoValorTbl) {
		getCatalogoValorTbls().remove(catalogoValorTbl);
		catalogoValorTbl.setCatalogoTipoTbl(null);

		return catalogoValorTbl;
	}

}