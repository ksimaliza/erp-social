package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the euc_autoriza_exhumacion database table.
 * 
 */
@Entity
@Table(name="euc_autoriza_exhumacion")
@NamedQuery(name="AutorizaExhumacionDTO.findAll", query="SELECT a FROM AutorizaExhumacionDTO a")
public class AutorizaExhumacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_AUTORIZA_EXHUMACION_AUTCODIGO_GENERATOR", sequenceName="EUC_AUTORIZA_EXHUMACION_AUT_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_AUTORIZA_EXHUMACION_AUTCODIGO_GENERATOR")
	@Column(name="aut_codigo")
	private Integer autCodigo;

	@Column(name="aut_persona")
	private Integer autPersona;

	public AutorizaExhumacionDTO() {
	}

	public Integer getAutCodigo() {
		return this.autCodigo;
	}

	public void setAutCodigo(Integer autCodigo) {
		this.autCodigo = autCodigo;
	}

	public Integer getAutPersona() {
		return this.autPersona;
	}

	public void setAutPersona(Integer autPersona) {
		this.autPersona = autPersona;
	}

}