package ec.edu.uce.erp.ejb.persistence.entity.inventory;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the marca_bien_tbl database table.
 * 
 */
@Entity
@Table(name="marca_bien_tbl")
@NamedQuery(name="MarcaBien.findAll", query="SELECT m FROM MarcaBien m")
public class MarcaBien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MARCA_BIEN_TBL_MARBIENPK_GENERATOR", sequenceName="MARCA_BIEN_TBL_MAR_BIEN_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MARCA_BIEN_TBL_MARBIENPK_GENERATOR")
	@Column(name="mar_bien_pk")
	private Integer marBienPk;

	@Column(name="mar_bien_descripcion")
	private String marBienDescripcion;

	@Column(name="mar_bien_estado")
	private String marBienEstado;

	@Column(name="mar_bien_nombre")
	private String marBienNombre;

	public MarcaBien() {
	}

	public Integer getMarBienPk() {
		return this.marBienPk;
	}

	public void setMarBienPk(Integer marBienPk) {
		this.marBienPk = marBienPk;
	}

	public String getMarBienDescripcion() {
		return this.marBienDescripcion;
	}

	public void setMarBienDescripcion(String marBienDescripcion) {
		this.marBienDescripcion = marBienDescripcion;
	}

	public String getMarBienEstado() {
		return this.marBienEstado;
	}

	public void setMarBienEstado(String marBienEstado) {
		this.marBienEstado = marBienEstado;
	}

	public String getMarBienNombre() {
		return this.marBienNombre;
	}

	public void setMarBienNombre(String marBienNombre) {
		this.marBienNombre = marBienNombre;
	}

}