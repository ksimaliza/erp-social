package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the euc_sacerdote database table.
 * 
 */
@Entity
@Table(name="euc_sacerdote")
@NamedQuery(name="SacerdoteDTO.findAll", query="SELECT s FROM SacerdoteDTO s")
public class SacerdoteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_SACERDOTE_SACCODIGO_GENERATOR", sequenceName="EUC_SACERDOTE_SAC_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_SACERDOTE_SACCODIGO_GENERATOR")
	@Column(name="sac_codigo")
	private Integer sacCodigo;

	@Column(name="sac_persona")
	private Integer sacPersona;

	//bi-directional many-to-one association to BautizoDTO
	@OneToMany(mappedBy="eucSacerdote")
	private List<BautizoDTO> eucBautizos;

	//bi-directional many-to-one association to ConfirmacionDTO
	@OneToMany(mappedBy="eucSacerdote")
	private List<ConfirmacionDTO> eucConfirmacions;

	//bi-directional many-to-one association to DefuncionDTO
	@OneToMany(mappedBy="eucSacerdote")
	private List<DefuncionDTO> eucDefuncions;

	//bi-directional many-to-one association to EucaristiaDTO
	@OneToMany(mappedBy="eucSacerdoteBean")
	private List<EucaristiaDTO> eucEucarestias;

	//bi-directional many-to-one association to MatrimonioDTO
	@OneToMany(mappedBy="eucSacerdote")
	private List<MatrimonioDTO> eucMatrimonios;

	//bi-directional many-to-one association to PrimeraComunionDTO
	@OneToMany(mappedBy="eucSacerdote")
	private List<PrimeraComunionDTO> eucPrimeraComunions;

	public SacerdoteDTO() {
	}

	public Integer getSacCodigo() {
		return this.sacCodigo;
	}

	public void setSacCodigo(Integer sacCodigo) {
		this.sacCodigo = sacCodigo;
	}

	public Integer getSacPersona() {
		return this.sacPersona;
	}

	public void setSacPersona(Integer sacPersona) {
		this.sacPersona = sacPersona;
	}

	public List<BautizoDTO> getEucBautizos() {
		return this.eucBautizos;
	}

	public void setEucBautizos(List<BautizoDTO> eucBautizos) {
		this.eucBautizos = eucBautizos;
	}

	public BautizoDTO addEucBautizo(BautizoDTO eucBautizo) {
		getEucBautizos().add(eucBautizo);
		eucBautizo.setEucSacerdote(this);

		return eucBautizo;
	}

	public BautizoDTO removeEucBautizo(BautizoDTO eucBautizo) {
		getEucBautizos().remove(eucBautizo);
		eucBautizo.setEucSacerdote(null);

		return eucBautizo;
	}

	public List<ConfirmacionDTO> getEucConfirmacions() {
		return this.eucConfirmacions;
	}

	public void setEucConfirmacions(List<ConfirmacionDTO> eucConfirmacions) {
		this.eucConfirmacions = eucConfirmacions;
	}

	public ConfirmacionDTO addEucConfirmacion(ConfirmacionDTO eucConfirmacion) {
		getEucConfirmacions().add(eucConfirmacion);
		eucConfirmacion.setEucSacerdote(this);

		return eucConfirmacion;
	}

	public ConfirmacionDTO removeEucConfirmacion(ConfirmacionDTO eucConfirmacion) {
		getEucConfirmacions().remove(eucConfirmacion);
		eucConfirmacion.setEucSacerdote(null);

		return eucConfirmacion;
	}

	public List<DefuncionDTO> getEucDefuncions() {
		return this.eucDefuncions;
	}

	public void setEucDefuncions(List<DefuncionDTO> eucDefuncions) {
		this.eucDefuncions = eucDefuncions;
	}

	public DefuncionDTO addEucDefuncion(DefuncionDTO eucDefuncion) {
		getEucDefuncions().add(eucDefuncion);
		eucDefuncion.setEucSacerdote(this);

		return eucDefuncion;
	}

	public DefuncionDTO removeEucDefuncion(DefuncionDTO eucDefuncion) {
		getEucDefuncions().remove(eucDefuncion);
		eucDefuncion.setEucSacerdote(null);

		return eucDefuncion;
	}

	public List<EucaristiaDTO> getEucEucarestias() {
		return this.eucEucarestias;
	}

	public void setEucEucarestias(List<EucaristiaDTO> eucEucarestias) {
		this.eucEucarestias = eucEucarestias;
	}

	public EucaristiaDTO addEucEucarestia(EucaristiaDTO eucEucarestia) {
		getEucEucarestias().add(eucEucarestia);
		eucEucarestia.setEucSacerdoteBean(this);

		return eucEucarestia;
	}

	public EucaristiaDTO removeEucEucarestia(EucaristiaDTO eucEucarestia) {
		getEucEucarestias().remove(eucEucarestia);
		eucEucarestia.setEucSacerdoteBean(null);

		return eucEucarestia;
	}

	public List<MatrimonioDTO> getEucMatrimonios() {
		return this.eucMatrimonios;
	}

	public void setEucMatrimonios(List<MatrimonioDTO> eucMatrimonios) {
		this.eucMatrimonios = eucMatrimonios;
	}

	public MatrimonioDTO addEucMatrimonio(MatrimonioDTO eucMatrimonio) {
		getEucMatrimonios().add(eucMatrimonio);
		eucMatrimonio.setEucSacerdote(this);

		return eucMatrimonio;
	}

	public MatrimonioDTO removeEucMatrimonio(MatrimonioDTO eucMatrimonio) {
		getEucMatrimonios().remove(eucMatrimonio);
		eucMatrimonio.setEucSacerdote(null);

		return eucMatrimonio;
	}

	public List<PrimeraComunionDTO> getEucPrimeraComunions() {
		return this.eucPrimeraComunions;
	}

	public void setEucPrimeraComunions(List<PrimeraComunionDTO> eucPrimeraComunions) {
		this.eucPrimeraComunions = eucPrimeraComunions;
	}

	public PrimeraComunionDTO addEucPrimeraComunion(PrimeraComunionDTO eucPrimeraComunion) {
		getEucPrimeraComunions().add(eucPrimeraComunion);
		eucPrimeraComunion.setEucSacerdote(this);

		return eucPrimeraComunion;
	}

	public PrimeraComunionDTO removeEucPrimeraComunion(PrimeraComunionDTO eucPrimeraComunion) {
		getEucPrimeraComunions().remove(eucPrimeraComunion);
		eucPrimeraComunion.setEucSacerdote(null);

		return eucPrimeraComunion;
	}

}