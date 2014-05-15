package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asi_dia_no_laboral database table.
 * 
 */
@Entity
@Table(name="asi_dia_no_laboral")
@NamedQuery(name="DiaNoLaboralDTO.findAll", query="SELECT d FROM DiaNoLaboralDTO d")
public class DiaNoLaboralDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASI_DIA_NO_LABORAL_DNLCODIGO_GENERATOR", sequenceName="ASI_DIA_NO_LABORAL_DNL_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASI_DIA_NO_LABORAL_DNLCODIGO_GENERATOR")
	@Column(name="dnl_codigo")
	private Integer dnlCodigo;

	@Column(name="dnl_anio")
	private Integer dnlAnio;

	@Column(name="dnl_dia")
	private Integer dnlDia;

	@Column(name="dnl_entidad")
	private Integer dnlEntidad;

	@Column(name="dnl_mes")
	private Integer dnlMes;

	@Column(name="dnl_observacion")
	private String dnlObservacion;
	
	
	public DiaNoLaboralDTO() {
	}

	public Integer getDnlCodigo() {
		return this.dnlCodigo;
	}

	public void setDnlCodigo(Integer dnlCodigo) {
		this.dnlCodigo = dnlCodigo;
	}

	public Integer getDnlAnio() {
		return this.dnlAnio;
	}

	public void setDnlAnio(Integer dnlAnio) {
		this.dnlAnio = dnlAnio;
	}

	public Integer getDnlDia() {
		return this.dnlDia;
	}

	public void setDnlDia(Integer dnlDia) {
		this.dnlDia = dnlDia;
	}

	public Integer getDnlEntidad() {
		return this.dnlEntidad;
	}

	public void setDnlEntidad(Integer dnlEntidad) {
		this.dnlEntidad = dnlEntidad;
	}

	public Integer getDnlMes() {
		return this.dnlMes;
	}

	public void setDnlMes(Integer dnlMes) {
		this.dnlMes = dnlMes;
	}

	public String getDnlObservacion() {
		return dnlObservacion;
	}

	public void setDnlObservacion(String dnlObservacion) {
		this.dnlObservacion = dnlObservacion;
	}

}