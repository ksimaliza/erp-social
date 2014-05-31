package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the euc_costo database table.
 * 
 */
@Entity
@Table(name="euc_costo")
@NamedQuery(name="CostoDTO.findAll", query="SELECT c FROM CostoDTO c")
public class CostoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_COSTO_COSCODIGO_GENERATOR", sequenceName="EUC_COSTO_COS_CODIGO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_COSTO_COSCODIGO_GENERATOR")
	@Column(name="cos_codigo")
	private Integer cosCodigo;

	@Column(name="cos_mes")
	private Integer cosMes;

	@Column(name="cos_valor")
	private BigDecimal cosValor;

	public CostoDTO() {
	}

	public Integer getCosCodigo() {
		return this.cosCodigo;
	}

	public void setCosCodigo(Integer cosCodigo) {
		this.cosCodigo = cosCodigo;
	}

	public Integer getCosMes() {
		return this.cosMes;
	}

	public void setCosMes(Integer cosMes) {
		this.cosMes = cosMes;
	}

	public BigDecimal getCosValor() {
		return this.cosValor;
	}

	public void setCosValor(BigDecimal cosValor) {
		this.cosValor = cosValor;
	}

}