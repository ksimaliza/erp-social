package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the euc_eucarestia database table.
 * 
 */
@Entity
@Table(name="euc_eucarestia")
@NamedQuery(name="EucaristiaDTO.findAll", query="SELECT e FROM EucaristiaDTO e")
public class EucaristiaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_EUCARESTIA_EUCCODIGO_GENERATOR", sequenceName="EUC_EUCARESTIA_EUC_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_EUCARESTIA_EUCCODIGO_GENERATOR")
	@Column(name="euc_codigo")
	private Integer eucCodigo;

	@Column(name="euc_fecha_hora")
	private Timestamp eucFechaHora;

	@Column(name="euc_intencion")
	private String eucIntencion;

	@Column(name="euc_valor")
	private BigDecimal eucValor;
    
	@Column(name="euc_empresa")
	private Integer eucEmpresa;
	//bi-directional many-to-one association to SacerdoteDTO
	@ManyToOne
	@JoinColumn(name="euc_sacerdote")
	private SacerdoteDTO eucSacerdoteBean;

	public EucaristiaDTO() {
	}

	public Integer getEucCodigo() {
		return this.eucCodigo;
	}

	public void setEucCodigo(Integer eucCodigo) {
		this.eucCodigo = eucCodigo;
	}

	public Timestamp getEucFechaHora() {
		return this.eucFechaHora;
	}

	public void setEucFechaHora(Timestamp eucFechaHora) {
		this.eucFechaHora = eucFechaHora;
	}

	public String getEucIntencion() {
		return this.eucIntencion;
	}

	public void setEucIntencion(String eucIntencion) {
		this.eucIntencion = eucIntencion;
	}

	public BigDecimal getEucValor() {
		return this.eucValor;
	}

	public void setEucValor(BigDecimal eucValor) {
		this.eucValor = eucValor;
	}

	public SacerdoteDTO getEucSacerdoteBean() {
		return this.eucSacerdoteBean;
	}

	public void setEucSacerdoteBean(SacerdoteDTO eucSacerdoteBean) {
		this.eucSacerdoteBean = eucSacerdoteBean;
	}

	public Integer getEucEmpresa() {
		return eucEmpresa;
	}

	public void setEucEmpresa(Integer eucEmpresa) {
		this.eucEmpresa = eucEmpresa;
	}
    
	
}