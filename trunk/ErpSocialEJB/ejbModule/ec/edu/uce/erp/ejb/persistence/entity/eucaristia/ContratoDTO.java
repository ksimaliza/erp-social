package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the euc_contrato database table.
 * 
 */
@Entity
@Table(name="euc_contrato")
@NamedQuery(name="ContratoDTO.findAll", query="SELECT c FROM ContratoDTO c")
public class ContratoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_CONTRATO_CONCODIGO_GENERATOR", sequenceName="EUC_CONTRATO_CON_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_CONTRATO_CONCODIGO_GENERATOR")
	@Column(name="con_codigo")
	private Integer conCodigo;

	@Column(name="con_anio_arrendamiento")
	private Integer conAnioArrendamiento;

	@Column(name="con_beneficiario")
	private Integer conBeneficiario;

	@Column(name="con_difunto")
	private Integer conDifunto;

	@Column(name="con_fecha_fin")
	private Timestamp conFechaFin;

	@Column(name="con_fecha_inicio")
	private Timestamp conFechaInicio;

	@Column(name="con_forma_pago")
	private Integer conFormaPago;

	@Column(name="con_observacion")
	private String conObservacion;

	//bi-directional many-to-one association to NichoDTO
	@ManyToOne
	@JoinColumn(name="con_nicho")
	private NichoDTO eucNicho;

	public ContratoDTO() {
	}

	public Integer getConCodigo() {
		return this.conCodigo;
	}

	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
	}

	public Integer getConAnioArrendamiento() {
		return this.conAnioArrendamiento;
	}

	public void setConAnioArrendamiento(Integer conAnioArrendamiento) {
		this.conAnioArrendamiento = conAnioArrendamiento;
	}

	public Integer getConBeneficiario() {
		return this.conBeneficiario;
	}

	public void setConBeneficiario(Integer conBeneficiario) {
		this.conBeneficiario = conBeneficiario;
	}

	public Integer getConDifunto() {
		return this.conDifunto;
	}

	public void setConDifunto(Integer conDifunto) {
		this.conDifunto = conDifunto;
	}

	public Timestamp getConFechaFin() {
		return this.conFechaFin;
	}

	public void setConFechaFin(Timestamp conFechaFin) {
		this.conFechaFin = conFechaFin;
	}

	public Timestamp getConFechaInicio() {
		return this.conFechaInicio;
	}

	public void setConFechaInicio(Timestamp conFechaInicio) {
		this.conFechaInicio = conFechaInicio;
	}

	public Integer getConFormaPago() {
		return this.conFormaPago;
	}

	public void setConFormaPago(Integer conFormaPago) {
		this.conFormaPago = conFormaPago;
	}

	public String getConObservacion() {
		return this.conObservacion;
	}

	public void setConObservacion(String conObservacion) {
		this.conObservacion = conObservacion;
	}

	public NichoDTO getEucNicho() {
		return this.eucNicho;
	}

	public void setEucNicho(NichoDTO eucNicho) {
		this.eucNicho = eucNicho;
	}

}