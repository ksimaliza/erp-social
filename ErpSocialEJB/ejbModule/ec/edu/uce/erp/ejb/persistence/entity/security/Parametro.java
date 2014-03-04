package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the erpt_parametro database table.
 * 
 */
@Entity
@Table(name="erpt_parametro")
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p")
public class Parametro extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ERPT_PARAMETRO_IDPARAMETRO_GENERATOR", sequenceName="ERPT_PARAMETRO_ID_PARAMETRO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ERPT_PARAMETRO_IDPARAMETRO_GENERATOR")
	@Column(name="id_parametro")
	private Integer idParametro;

	@Column(name="descripcion_parametro")
	private String descripcionParametro;

	private String estado;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	@Column(name="nombre_parametro")
	private String nombreParametro;

	@Column(name="valor_parametro")
	private String valorParametro;

	public Parametro() {
	}

	public Integer getIdParametro() {
		return this.idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getDescripcionParametro() {
		return this.descripcionParametro;
	}

	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public String getValorParametro() {
		return this.valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

}