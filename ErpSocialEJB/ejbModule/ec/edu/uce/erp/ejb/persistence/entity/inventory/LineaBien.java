package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;

import javax.persistence.*;

import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;

import java.util.List;


/**
 * The persistent class for the linea_bien_tbl database table.
 * 
 */
@Entity
@Table(name="linea_bien_tbl")
@NamedQuery(name="LineaBien.findAll", query="SELECT l FROM LineaBien l")
public class LineaBien extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LINEA_BIEN_TBL_LINBIENPK_GENERATOR", sequenceName="BIEN_TBL_BIE_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LINEA_BIEN_TBL_LINBIENPK_GENERATOR")
	@Column(name="lin_bien_pk")
	private Integer linBienPk;

	@Column(name="lin_bien_descripcion")
	private String linBienDescripcion;

	@Column(name="lin_bien_estado")
	private String linBienEstado;

	@Column(name="lin_bien_nombre")
	private String linBienNombre;

	//bi-directional many-to-one association to CategoriaBien
	@OneToMany(mappedBy="lineaBienTbl")
	private List<CategoriaBien> categoriaBienTbls;

	public LineaBien() {
	}

	public Integer getLinBienPk() {
		return this.linBienPk;
	}

	public void setLinBienPk(Integer linBienPk) {
		this.linBienPk = linBienPk;
	}

	public String getLinBienDescripcion() {
		return this.linBienDescripcion;
	}

	public void setLinBienDescripcion(String linBienDescripcion) {
		this.linBienDescripcion = linBienDescripcion;
	}

	public String getLinBienEstado() {
		return this.linBienEstado;
	}

	public void setLinBienEstado(String linBienEstado) {
		this.linBienEstado = linBienEstado;
	}

	public String getLinBienNombre() {
		return this.linBienNombre;
	}

	public void setLinBienNombre(String linBienNombre) {
		this.linBienNombre = linBienNombre;
	}

	public List<CategoriaBien> getCategoriaBienTbls() {
		return this.categoriaBienTbls;
	}

	public void setCategoriaBienTbls(List<CategoriaBien> categoriaBienTbls) {
		this.categoriaBienTbls = categoriaBienTbls;
	}

	public CategoriaBien addCategoriaBienTbl(CategoriaBien categoriaBienTbl) {
		getCategoriaBienTbls().add(categoriaBienTbl);
		categoriaBienTbl.setLineaBienTbl(this);

		return categoriaBienTbl;
	}

	public CategoriaBien removeCategoriaBienTbl(CategoriaBien categoriaBienTbl) {
		getCategoriaBienTbls().remove(categoriaBienTbl);
		categoriaBienTbl.setLineaBienTbl(null);

		return categoriaBienTbl;
	}

}