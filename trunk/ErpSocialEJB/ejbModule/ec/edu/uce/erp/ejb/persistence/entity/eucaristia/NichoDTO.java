package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

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


/**
 * The persistent class for the euc_nicho database table.
 * 
 */
@Entity
@Table(name="euc_nicho")
@NamedQuery(name="NichoDTO.findAll", query="SELECT n FROM NichoDTO n")
public class NichoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_NICHO_NICCODIGO_GENERATOR", sequenceName="EUC_NICHO_NIC_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_NICHO_NICCODIGO_GENERATOR")
	@Column(name="nic_codigo")
	private Integer nicCodigo;

	@Column(name="nic_descripcion")
	private String nicDescripcion;
	
	@Column(name="nic_seccion")
	private Integer nicSeccion;
	
	@Column(name="nic_empresa")
	private Integer nicEmpresa;
	
	@Column(name="nic_estado")
	private String nicEstado;

	/*//bi-directional many-to-one association to ContratoDTO
	@OneToMany(mappedBy="eucNicho")
	private List<ContratoDTO> eucContratos;
*/
	//bi-directional many-to-one association to TipoNichoDTO
	@ManyToOne
	@JoinColumn(name="nic_tipo")
	private  TipoNichoDTO eucTipoNicho;

	//bi-directional many-to-one association to NivelNichoDTO
	@ManyToOne
	@JoinColumn(name="nni_nivel")
	private NivelNichoDTO eucNivelNicho;

	public NichoDTO() {
	}

	public Integer getNicCodigo() {
		return this.nicCodigo;
	}

	public void setNicCodigo(Integer nicCodigo) {
		this.nicCodigo = nicCodigo;
	}

	public String getNicDescripcion() {
		return this.nicDescripcion;
	}

	public void setNicDescripcion(String nicDescripcion) {
		this.nicDescripcion = nicDescripcion;
	}

	/*public List<ContratoDTO> getEucContratos() {
		return this.eucContratos;
	}

	public void setEucContratos(List<ContratoDTO> eucContratos) {
		this.eucContratos = eucContratos;
	}

	public ContratoDTO addEucContrato(ContratoDTO eucContrato) {
		getEucContratos().add(eucContrato);
		eucContrato.setEucNicho(this);

		return eucContrato;
	}

	public ContratoDTO removeEucContrato(ContratoDTO eucContrato) {
		getEucContratos().remove(eucContrato);
		eucContrato.setEucNicho(null);

		return eucContrato;
	}*/

	public NivelNichoDTO getEucNivelNicho() {
		return this.eucNivelNicho;
	}

	public void setEucNivelNicho(NivelNichoDTO eucNivelNicho) {
		this.eucNivelNicho = eucNivelNicho;
	}

	public TipoNichoDTO getEucTipoNicho() {
		return this.eucTipoNicho;
	}

	public void setEucTipoNicho(TipoNichoDTO eucTipoNicho) {
		this.eucTipoNicho = eucTipoNicho;
	}

	public Integer getNicSeccion() {
		return nicSeccion;
	}

	public void setNicSeccion(Integer nicSeccion) {
		this.nicSeccion = nicSeccion;
	}

	public Integer getNicEmpresa() {
		return nicEmpresa;
	}

	public void setNicEmpresa(Integer nicEmpresa) {
		this.nicEmpresa = nicEmpresa;
	}

	public String getNicEstado() {
		return nicEstado;
	}

	public void setNicEstado(String nicEstado) {
		this.nicEstado = nicEstado;
	}
    
}