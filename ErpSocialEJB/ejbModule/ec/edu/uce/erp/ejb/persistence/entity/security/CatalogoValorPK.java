package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the catalogo_valor_tbl database table.
 * 
 */
@Embeddable
public class CatalogoValorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="catalogo_valor_id")
	private String catalogoValorId;

	@Column(name="catalogo_tipo_id")
	private Integer catalogoTipoId;

	public CatalogoValorPK() {
	}
	public String getCatalogoValorId() {
		return this.catalogoValorId;
	}
	public void setCatalogoValorId(String catalogoValorId) {
		this.catalogoValorId = catalogoValorId;
	}
	public Integer getCatalogoTipoId() {
		return this.catalogoTipoId;
	}
	public void setCatalogoTipoId(Integer catalogoTipoId) {
		this.catalogoTipoId = catalogoTipoId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CatalogoValorPK)) {
			return false;
		}
		CatalogoValorPK castOther = (CatalogoValorPK)other;
		return 
			this.catalogoValorId.equals(castOther.catalogoValorId)
			&& this.catalogoTipoId.equals(castOther.catalogoTipoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.catalogoValorId.hashCode();
		hash = hash * prime + this.catalogoTipoId.hashCode();
		
		return hash;
	}
}