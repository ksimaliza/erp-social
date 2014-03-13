package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asi_parametro database table.
 * 
 */
@Entity
@Table(name="asi_parametro")
@NamedQuery(name="ParametroDTO.findAll", query="SELECT p FROM ParametroDTO p")
public class ParametroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_PARAMETRO_PASCODIGO_GENERATOR", sequenceName="ASI_PARAMETRO_PAS_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_PARAMETRO_PASCODIGO_GENERATOR")
	@Column(name="pas_codigo")
	private Integer pasCodigo;

	@Column(name="pas_descripcion")
	private String pasDescripcion;

	@Column(name="pas_entidad")
	private Integer pasEntidad;

	@Column(name="pas_valor")
	private String pasValor;

	public ParametroDTO() {
	}

	public Integer getPasCodigo() {
		return this.pasCodigo;
	}

	public void setPasCodigo(Integer pasCodigo) {
		this.pasCodigo = pasCodigo;
	}

	public String getPasDescripcion() {
		return this.pasDescripcion;
	}

	public void setPasDescripcion(String pasDescripcion) {
		this.pasDescripcion = pasDescripcion;
	}

	public Integer getPasEntidad() {
		return this.pasEntidad;
	}

	public void setPasEntidad(Integer pasEntidad) {
		this.pasEntidad = pasEntidad;
	}

	public String getPasValor() {
		return this.pasValor;
	}

	public void setPasValor(String pasValor) {
		this.pasValor = pasValor;
	}

}