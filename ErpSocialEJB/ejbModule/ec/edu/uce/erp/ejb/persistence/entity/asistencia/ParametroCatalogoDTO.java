package ec.edu.uce.erp.ejb.persistence.entity.asistencia;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the asi_parametro_catalogo database table.
 * 
 */
@Entity
@Table(name="asi_parametro_catalogo")
@NamedQuery(name="ParametroCatalogoDTO.findAll", query="SELECT p FROM ParametroCatalogoDTO p")
public class ParametroCatalogoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cat_codigo")
	private Integer catCodigo;

	@Column(name="cat_descripcion")
	private String catDescripcion;

	@Column(name="pas_catalogo")
	private Integer pasCatalogo;

	@Id
	@Column(name="pas_codigo")
	private Integer pasCodigo;

	@Column(name="pas_descripcion")
	private String pasDescripcion;

	@Column(name="pas_empleado")
	private Integer pasEmpleado;

	@Column(name="pas_entidad")
	private Integer pasEntidad;

	@Column(name="pas_valor")
	private String pasValor;

	public ParametroCatalogoDTO() {
	}

	public Integer getCatCodigo() {
		return this.catCodigo;
	}

	public void setCatCodigo(Integer catCodigo) {
		this.catCodigo = catCodigo;
	}

	public String getCatDescripcion() {
		return this.catDescripcion;
	}

	public void setCatDescripcion(String catDescripcion) {
		this.catDescripcion = catDescripcion;
	}

	public Integer getPasCatalogo() {
		return this.pasCatalogo;
	}

	public void setPasCatalogo(Integer pasCatalogo) {
		this.pasCatalogo = pasCatalogo;
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

	public Integer getPasEmpleado() {
		return this.pasEmpleado;
	}

	public void setPasEmpleado(Integer pasEmpleado) {
		this.pasEmpleado = pasEmpleado;
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