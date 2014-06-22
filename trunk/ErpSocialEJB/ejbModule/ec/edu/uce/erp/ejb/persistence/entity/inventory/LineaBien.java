package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the linea_bien_tbl database table.
 * 
 */
@Entity
@Table(name="linea_bien_tbl")
@NamedQuery(name="LineaBien.findAll", query="SELECT l FROM LineaBien l")
public class LineaBien extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private LineaBienPK id;
	
	@Id
	@SequenceGenerator(name="LINEA_BIEN_TBL_LINBIENPK_GENERATOR", sequenceName="LINEA_BIEN_TBL_LIN_BIEN_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LINEA_BIEN_TBL_LINBIENPK_GENERATOR")
	@Column(name="lin_bien_pk")
	private Integer linBienPk;

	@Column(name="cat_bien_pk")
	private Integer catBienPk;

	@Column(name="lin_bien_descripcion")
	private String linBienDescripcion;

	@Column(name="lin_bien_estado")
	private String linBienEstado;

	@Column(name="lin_bien_nombre")
	private String linBienNombre;

	
	//@JoinColumn(name="cab_bien_fk", referencedColumnName="cab_bien_pk", insertable=false, updatable=false)
	@ManyToOne
	@JoinColumn(name="cat_bien_pk", referencedColumnName="cat_bien_pk", insertable=false, updatable=false)
	private CategoriaBien categoriaBienTbl;

	public LineaBien() {
	}

//	public LineaBienPK getId() {
//		return this.id;
//	}
//
//	public void setId(LineaBienPK id) {
//		this.id = id;
//	}

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

	public CategoriaBien getCategoriaBienTbl() {
		return this.categoriaBienTbl;
	}

	public void setCategoriaBienTbl(CategoriaBien categoriaBienTbl) {
		this.categoriaBienTbl = categoriaBienTbl;
	}

	/**
	 * @return the linBienPk
	 */
	public Integer getLinBienPk() {
		return linBienPk;
	}

	/**
	 * @param linBienPk the linBienPk to set
	 */
	public void setLinBienPk(Integer linBienPk) {
		this.linBienPk = linBienPk;
	}

	/**
	 * @return the catBienPk
	 */
	public Integer getCatBienPk() {
		return catBienPk;
	}

	/**
	 * @param catBienPk the catBienPk to set
	 */
	public void setCatBienPk(Integer catBienPk) {
		this.catBienPk = catBienPk;
	}

}