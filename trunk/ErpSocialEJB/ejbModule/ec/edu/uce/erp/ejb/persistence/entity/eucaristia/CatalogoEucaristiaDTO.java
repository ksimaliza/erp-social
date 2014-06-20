package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the euc_catalogo database table.
 * 
 */
@Entity
@Table(name="euc_catalogo")
@NamedQuery(name="CatalogoEucaristiaDTO.findAll", query="SELECT c FROM CatalogoEucaristiaDTO c")
public class CatalogoEucaristiaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_CATALOGO_CATCODIGO_GENERATOR", sequenceName="EUC_CATALOGO_CAT_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_CATALOGO_CATCODIGO_GENERATOR")
	@Column(name="cat_codigo")
	private Integer catCodigo;

	@Column(name="cat_descripcion")
	private String catDescripcion;

	//bi-directional many-to-one association to CatalogoDTO
	@ManyToOne
	@JoinColumn(name="cat_predecesor")
	private CatalogoEucaristiaDTO eucCatalogo;

	//bi-directional many-to-one association to CatalogoDTO
	@OneToMany(mappedBy="eucCatalogo")
	private List<CatalogoEucaristiaDTO> eucCatalogos;

	public CatalogoEucaristiaDTO() {
	}

	public Integer getCatCodigo() {
		return this.catCodigo;
	}

	public void setCatCodigo(Integer catCodigo) {
		this.catCodigo = catCodigo;
	}

	public String getCatDescripcion() {
		return this.catDescripcion;
	}

	public void setCatDescripcion(String catDescripcion) {
		this.catDescripcion = catDescripcion;
	}

	public CatalogoEucaristiaDTO getEucCatalogo() {
		return this.eucCatalogo;
	}

	public void setEucCatalogo(CatalogoEucaristiaDTO eucCatalogo) {
		this.eucCatalogo = eucCatalogo;
	}

	public List<CatalogoEucaristiaDTO> getEucCatalogos() {
		return this.eucCatalogos;
	}

	public void setEucCatalogos(List<CatalogoEucaristiaDTO> eucCatalogos) {
		this.eucCatalogos = eucCatalogos;
	}

	public CatalogoEucaristiaDTO addEucCatalogo(CatalogoEucaristiaDTO eucCatalogo) {
		getEucCatalogos().add(eucCatalogo);
		eucCatalogo.setEucCatalogo(this);

		return eucCatalogo;
	}

	public CatalogoEucaristiaDTO removeEucCatalogo(CatalogoEucaristiaDTO eucCatalogo) {
		getEucCatalogos().remove(eucCatalogo);
		eucCatalogo.setEucCatalogo(null);

		return eucCatalogo;
	}

}