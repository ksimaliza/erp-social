package ec.edu.uce.erp.ejb.persistence.entity.eucaristia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the euc_sepultura database table.
 * 
 */
@Entity
@Table(name="euc_sepultura")
@NamedQuery(name="SepulturaDTO.findAll", query="SELECT s FROM SepulturaDTO s")
public class SepulturaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EUC_SEPULTURA_SEPCODIGO_GENERATOR", sequenceName="EUC_SEPULTURA_SEP_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EUC_SEPULTURA_SEPCODIGO_GENERATOR")
	@Column(name="sep_codigo")
	private Integer sepCodigo;

	@Column(name="sep_difunto")
	private Integer sepDifunto;

	@Column(name="sep_nicho")
	private Integer sepNicho;

	@Column(name="sep_observacion")
	private String sepObservacion;
	
	@Column(name="sep_empresa")
	private Integer sepEmpresa;

	public SepulturaDTO() {
	}

	public Integer getSepCodigo() {
		return this.sepCodigo;
	}

	public void setSepCodigo(Integer sepCodigo) {
		this.sepCodigo = sepCodigo;
	}

	public Integer getSepDifunto() {
		return this.sepDifunto;
	}

	public void setSepDifunto(Integer sepDifunto) {
		this.sepDifunto = sepDifunto;
	}

	public Integer getSepNicho() {
		return this.sepNicho;
	}

	public void setSepNicho(Integer sepNicho) {
		this.sepNicho = sepNicho;
	}

	public String getSepObservacion() {
		return this.sepObservacion;
	}

	public void setSepObservacion(String sepObservacion) {
		this.sepObservacion = sepObservacion;
	}

	public Integer getSepEmpresa() {
		return sepEmpresa;
	}

	public void setSepEmpresa(Integer sepEmpresa) {
		this.sepEmpresa = sepEmpresa;
	}
   
}