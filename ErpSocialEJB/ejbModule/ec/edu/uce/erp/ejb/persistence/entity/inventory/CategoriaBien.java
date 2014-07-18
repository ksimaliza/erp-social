package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the categoria_bien_tbl database table.
 * 
 */
@Entity
@Table(name="categoria_bien_tbl")
@NamedQuery(name="CategoriaBien.findAll", query="SELECT c FROM CategoriaBien c")
public class CategoriaBien extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORIA_BIEN_TBL_CATBIENPK_GENERATOR", sequenceName="CATEGORIA_BIEN_TBL_CAT_BIEN_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIA_BIEN_TBL_CATBIENPK_GENERATOR")
	@Column(name="cat_bien_pk")
	private Integer catBienPk;

	@Column(name="cat_bien_descripcion")
	private String catBienDescripcion;

	@Column(name="cat_bien_estado")
	private String catBienEstado;

	@Column(name="cat_bien_nombre")
	private String catBienNombre;
	
	@Column(name="cat_bien_indice")
	private Integer catBienIndice;

	//bi-directional many-to-one association to LineaBien
	@OneToMany(mappedBy="categoriaBienTbl")
	private List<LineaBien> lineaBienTbls;

	public CategoriaBien() {
	}

	public Integer getCatBienPk() {
		return this.catBienPk;
	}

	public void setCatBienPk(Integer catBienPk) {
		this.catBienPk = catBienPk;
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

	public List<LineaBien> getLineaBienTbls() {
		return this.lineaBienTbls;
	}

	public void setLineaBienTbls(List<LineaBien> lineaBienTbls) {
		this.lineaBienTbls = lineaBienTbls;
	}

	public LineaBien addLineaBienTbl(LineaBien lineaBienTbl) {
		getLineaBienTbls().add(lineaBienTbl);
		lineaBienTbl.setCategoriaBienTbl(this);

		return lineaBienTbl;
	}

	public LineaBien removeLineaBienTbl(LineaBien lineaBienTbl) {
		getLineaBienTbls().remove(lineaBienTbl);
		lineaBienTbl.setCategoriaBienTbl(null);

		return lineaBienTbl;
	}

	/**
	 * @return the catBienIndice
	 */
	public Integer getCatBienIndice() {
		return catBienIndice;
	}

	/**
	 * @param catBienIndice the catBienIndice to set
	 */
	public void setCatBienIndice(Integer catBienIndice) {
		this.catBienIndice = catBienIndice;
	}

}