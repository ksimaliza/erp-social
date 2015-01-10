package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the euc_tipo_nicho database table.
 * 
 */
@Entity
@Table(name="euc_tipo_nicho")
@NamedQuery(name="TipoNichoDTO.findAll", query="SELECT t FROM TipoNichoDTO t")
public class TipoNichoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_TIPO_NICHO_TNICODIGO_GENERATOR", sequenceName="EUC_TIPO_NICHO_TNI_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_TIPO_NICHO_TNICODIGO_GENERATOR")
	@Column(name="tni_codigo")
	private Integer tniCodigo;

	@Column(name="tni_descripcion")
	private String tniDescripcion;
  
	@Column(name="tni_empresa")
	private Integer tniEmpresa;
	
	//bi-directional many-to-one association to NichoDTO
	@OneToMany(mappedBy="eucTipoNicho")
	private List<NichoDTO> eucNichos;

	public TipoNichoDTO() {
	}

	public Integer getTniCodigo() {
		return this.tniCodigo;
	}

	public void setTniCodigo(Integer tniCodigo) {
		this.tniCodigo = tniCodigo;
	}

	public String getTniDescripcion() {
		return this.tniDescripcion;
	}

	public void setTniDescripcion(String tniDescripcion) {
		this.tniDescripcion = tniDescripcion;
	}

	public List<NichoDTO> getEucNichos() {
		return this.eucNichos;
	}

	public void setEucNichos(List<NichoDTO> eucNichos) {
		this.eucNichos = eucNichos;
	}

	public NichoDTO addEucNicho(NichoDTO eucNicho) {
		getEucNichos().add(eucNicho);
		eucNicho.setEucTipoNicho(this);

		return eucNicho;
	}

	public NichoDTO removeEucNicho(NichoDTO eucNicho) {
		getEucNichos().remove(eucNicho);
		eucNicho.setEucTipoNicho(null);

		return eucNicho;
	}

	public Integer getTniEmpresa() {
		return tniEmpresa;
	}

	public void setTniEmpresa(Integer tniEmpresa) {
		this.tniEmpresa = tniEmpresa;
	}
 
}