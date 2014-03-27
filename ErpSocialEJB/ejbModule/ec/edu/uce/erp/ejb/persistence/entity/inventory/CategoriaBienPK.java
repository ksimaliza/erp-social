package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the categoria_bien_tbl database table.
 * 
 */
@Embeddable
public class CategoriaBienPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="CATEGORIA_BIEN_TBL_CAT_BIENPK_GENERATOR", sequenceName="CATEGORIA_BIEN_TBL_CAT_BIEN_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIA_BIEN_TBL_CAT_BIENPK_GENERATOR")
	@Column(name="cat_bien_pk")
	private Integer catBienPk;

	@Column(name="lin_bien_pk")
	private Integer linBienPk;

	public CategoriaBienPK() {
	}
	public Integer getCatBienPk() {
		return this.catBienPk;
	}
	public void setCatBienPk(Integer catBienPk) {
		this.catBienPk = catBienPk;
	}
	public Integer getLinBienPk() {
		return this.linBienPk;
	}
	public void setLinBienPk(Integer linBienPk) {
		this.linBienPk = linBienPk;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CategoriaBienPK)) {
			return false;
		}
		CategoriaBienPK castOther = (CategoriaBienPK)other;
		return 
			this.catBienPk.equals(castOther.catBienPk)
			&& this.linBienPk.equals(castOther.linBienPk);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.catBienPk.hashCode();
		hash = hash * prime + this.linBienPk.hashCode();
		
		return hash;
	}
}