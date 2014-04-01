package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the linea_bien_tbl database table.
 * 
 */
@Embeddable
public class LineaBienPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="lin_bien_pk")
	private Integer linBienPk;

	@Column(name="cat_bien_pk", insertable=false, updatable=false)
	private Integer catBienPk;

	public LineaBienPK() {
	}
	public Integer getLinBienPk() {
		return this.linBienPk;
	}
	public void setLinBienPk(Integer linBienPk) {
		this.linBienPk = linBienPk;
	}
	public Integer getCatBienPk() {
		return this.catBienPk;
	}
	public void setCatBienPk(Integer catBienPk) {
		this.catBienPk = catBienPk;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LineaBienPK)) {
			return false;
		}
		LineaBienPK castOther = (LineaBienPK)other;
		return 
			this.linBienPk.equals(castOther.linBienPk)
			&& this.catBienPk.equals(castOther.catBienPk);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.linBienPk.hashCode();
		hash = hash * prime + this.catBienPk.hashCode();
		
		return hash;
	}
}