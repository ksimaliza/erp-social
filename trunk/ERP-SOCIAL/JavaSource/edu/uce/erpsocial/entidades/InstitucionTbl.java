package edu.uce.erpsocial.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the institucion_tbl database table.
 * 
 */
@Entity
@Table(name="institucion_tbl")
public class InstitucionTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ins_pk")
	private Integer insPk;

	@Column(name="ins_celular")
	private String insCelular;

	@Column(name="ins_direccion")
	private String insDireccion;

	@Column(name="ins_email")
	private String insEmail;

	@Column(name="ins_foto")
	private String insFoto;

	@Column(name="ins_nombre")
	private String insNombre;

	@Column(name="ins_pagina")
	private String insPagina;

	@Column(name="ins_ruc")
	private String insRuc;

	@Column(name="ins_telefono")
	private String insTelefono;

	@Column(name="tip_institucion_fk")
	private Integer tipInstitucionFk;

	//bi-directional many-to-one association to CarpetaTbl
	@OneToMany(mappedBy="institucionTbl")
	private Set<CarpetaTbl> carpetaTbls;

	//bi-directional many-to-one association to CementerioTbl
	@OneToMany(mappedBy="institucionTbl")
	private Set<CementerioTbl> cementerioTbls;

	//bi-directional many-to-one association to EmpleadoTbl
	@OneToMany(mappedBy="institucionTbl")
	private Set<EmpleadoTbl> empleadoTbls;

	//bi-directional many-to-one association to EucaristiaTbl
	@OneToMany(mappedBy="institucionTbl")
	private Set<EucaristiaTbl> eucaristiaTbls;

	//bi-directional many-to-one association to HolguraEntradaSalidaTbl
	@OneToMany(mappedBy="institucionTbl")
	private Set<HolguraEntradaSalidaTbl> holguraEntradaSalidaTbls;

	//bi-directional many-to-one association to PersonaInstitucionTbl
	@OneToMany(mappedBy="institucionTbl")
	private Set<PersonaInstitucionTbl> personaInstitucionTbls;

    public InstitucionTbl() {
    }

	public Integer getInsPk() {
		return this.insPk;
	}

	public void setInsPk(Integer insPk) {
		this.insPk = insPk;
	}

	public String getInsCelular() {
		return this.insCelular;
	}

	public void setInsCelular(String insCelular) {
		this.insCelular = insCelular;
	}

	public String getInsDireccion() {
		return this.insDireccion;
	}

	public void setInsDireccion(String insDireccion) {
		this.insDireccion = insDireccion;
	}

	public String getInsEmail() {
		return this.insEmail;
	}

	public void setInsEmail(String insEmail) {
		this.insEmail = insEmail;
	}

	public String getInsFoto() {
		return this.insFoto;
	}

	public void setInsFoto(String insFoto) {
		this.insFoto = insFoto;
	}

	public String getInsNombre() {
		return this.insNombre;
	}

	public void setInsNombre(String insNombre) {
		this.insNombre = insNombre;
	}

	public String getInsPagina() {
		return this.insPagina;
	}

	public void setInsPagina(String insPagina) {
		this.insPagina = insPagina;
	}

	public String getInsRuc() {
		return this.insRuc;
	}

	public void setInsRuc(String insRuc) {
		this.insRuc = insRuc;
	}

	public String getInsTelefono() {
		return this.insTelefono;
	}

	public void setInsTelefono(String insTelefono) {
		this.insTelefono = insTelefono;
	}

	public Integer getTipInstitucionFk() {
		return this.tipInstitucionFk;
	}

	public void setTipInstitucionFk(Integer tipInstitucionFk) {
		this.tipInstitucionFk = tipInstitucionFk;
	}

	public Set<CarpetaTbl> getCarpetaTbls() {
		return this.carpetaTbls;
	}

	public void setCarpetaTbls(Set<CarpetaTbl> carpetaTbls) {
		this.carpetaTbls = carpetaTbls;
	}
	
	public Set<CementerioTbl> getCementerioTbls() {
		return this.cementerioTbls;
	}

	public void setCementerioTbls(Set<CementerioTbl> cementerioTbls) {
		this.cementerioTbls = cementerioTbls;
	}
	
	public Set<EmpleadoTbl> getEmpleadoTbls() {
		return this.empleadoTbls;
	}

	public void setEmpleadoTbls(Set<EmpleadoTbl> empleadoTbls) {
		this.empleadoTbls = empleadoTbls;
	}
	
	public Set<EucaristiaTbl> getEucaristiaTbls() {
		return this.eucaristiaTbls;
	}

	public void setEucaristiaTbls(Set<EucaristiaTbl> eucaristiaTbls) {
		this.eucaristiaTbls = eucaristiaTbls;
	}
	
	public Set<HolguraEntradaSalidaTbl> getHolguraEntradaSalidaTbls() {
		return this.holguraEntradaSalidaTbls;
	}

	public void setHolguraEntradaSalidaTbls(Set<HolguraEntradaSalidaTbl> holguraEntradaSalidaTbls) {
		this.holguraEntradaSalidaTbls = holguraEntradaSalidaTbls;
	}
	
	public Set<PersonaInstitucionTbl> getPersonaInstitucionTbls() {
		return this.personaInstitucionTbls;
	}

	public void setPersonaInstitucionTbls(Set<PersonaInstitucionTbl> personaInstitucionTbls) {
		this.personaInstitucionTbls = personaInstitucionTbls;
	}
	
}