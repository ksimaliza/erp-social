package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the catalogo_valor_tbl database table.
 * 
 */
@Entity
@Table(name="catalogo_valor_tbl")
@NamedQuery(name="CatalogoValor.findAll", query="SELECT c FROM CatalogoValor c")
public class CatalogoValor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CatalogoValorPK id;

	@Column(name="catalogo_valor_estado")
	private String catalogoValorEstado;

	@Column(name="catalogo_valor_nombre")
	private String catalogoValorNombre;
	
//	@Column(name="catalogo_tipo_id")
//	private Long catalogoTipoId;

	//bi-directional many-to-one association to CatalogoTipo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="catalogo_tipo_id", referencedColumnName="catalogo_tipo_id", unique=false, nullable=true, insertable=false, updatable=false)
	})
	private CatalogoTipo catalogoTipoTbl;

	public CatalogoValor() {
	}

	public CatalogoValorPK getId() {
		return this.id;
	}

	public void setId(CatalogoValorPK id) {
		this.id = id;
	}

	public String getCatalogoValorEstado() {
		return this.catalogoValorEstado;
	}

	public void setCatalogoValorEstado(String catalogoValorEstado) {
		this.catalogoValorEstado = catalogoValorEstado;
	}

	public String getCatalogoValorNombre() {
		return this.catalogoValorNombre;
	}

	public void setCatalogoValorNombre(String catalogoValorNombre) {
		this.catalogoValorNombre = catalogoValorNombre;
	}

	public CatalogoTipo getCatalogoTipoTbl() {
		return this.catalogoTipoTbl;
	}

	public void setCatalogoTipoTbl(CatalogoTipo catalogoTipoTbl) {
		this.catalogoTipoTbl = catalogoTipoTbl;
	}

}