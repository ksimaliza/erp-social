package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="prov_pk")
	private Integer provPk;

	@Column(name="prov_apellido")
	private String provApellido;

	@Column(name="prov_direccion")
	private String provDireccion;

	@Column(name="prov_nombre")
	private String provNombre;

	@Column(name="prov_telefono")
	private String provTelefono;

	//bi-directional many-to-one association to IngresoTbl
	@OneToMany(mappedBy="proveedor")
	private Set<IngresoTbl> ingresoTbls;

    public Proveedor() {
    }

	public Integer getProvPk() {
		return this.provPk;
	}

	public void setProvPk(Integer provPk) {
		this.provPk = provPk;
	}

	public String getProvApellido() {
		return this.provApellido;
	}

	public void setProvApellido(String provApellido) {
		this.provApellido = provApellido;
	}

	public String getProvDireccion() {
		return this.provDireccion;
	}

	public void setProvDireccion(String provDireccion) {
		this.provDireccion = provDireccion;
	}

	public String getProvNombre() {
		return this.provNombre;
	}

	public void setProvNombre(String provNombre) {
		this.provNombre = provNombre;
	}

	public String getProvTelefono() {
		return this.provTelefono;
	}

	public void setProvTelefono(String provTelefono) {
		this.provTelefono = provTelefono;
	}

	public Set<IngresoTbl> getIngresoTbls() {
		return this.ingresoTbls;
	}

	public void setIngresoTbls(Set<IngresoTbl> ingresoTbls) {
		this.ingresoTbls = ingresoTbls;
	}
	
}