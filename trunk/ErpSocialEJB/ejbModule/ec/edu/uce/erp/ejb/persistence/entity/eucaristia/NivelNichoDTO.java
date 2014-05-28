package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the euc_nivel_nicho database table.
 * 
 */
@Entity
@Table(name="euc_nivel_nicho")
@NamedQuery(name="NivelNichoDTO.findAll", query="SELECT n FROM NivelNichoDTO n")
public class NivelNichoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_NIVEL_NICHO_NNICODIGO_GENERATOR", sequenceName="EUC_NIVEL_NICHO_NNI_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_NIVEL_NICHO_NNICODIGO_GENERATOR")
	@Column(name="nni_codigo")
	private Integer nniCodigo;

	@Column(name="nni_descripcion")
	private String nniDescripcion;

	//bi-directional many-to-one association to NichoDTO
	@OneToMany(mappedBy="eucNivelNicho")
	private List<NichoDTO> eucNichos;

	public NivelNichoDTO() {
	}

	public Integer getNniCodigo() {
		return this.nniCodigo;
	}

	public void setNniCodigo(Integer nniCodigo) {
		this.nniCodigo = nniCodigo;
	}

	public String getNniDescripcion() {
		return this.nniDescripcion;
	}

	public void setNniDescripcion(String nniDescripcion) {
		this.nniDescripcion = nniDescripcion;
	}

	public List<NichoDTO> getEucNichos() {
		return this.eucNichos;
	}

	public void setEucNichos(List<NichoDTO> eucNichos) {
		this.eucNichos = eucNichos;
	}

	public NichoDTO addEucNicho(NichoDTO eucNicho) {
		getEucNichos().add(eucNicho);
		eucNicho.setEucNivelNicho(this);

		return eucNicho;
	}

	public NichoDTO removeEucNicho(NichoDTO eucNicho) {
		getEucNichos().remove(eucNicho);
		eucNicho.setEucNivelNicho(null);

		return eucNicho;
	}

}