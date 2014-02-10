package ec.edu.uce.erp.ejb.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;


/**
 * The persistent class for the empresa_tbl database table.
 * 
 */
@Entity
@Table(name="empresa_tbl")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMPRESA_TBL_EMRPK_GENERATOR", sequenceName="EMPRESA_TBL_EMR_PK_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPRESA_TBL_EMRPK_GENERATOR")
	@Column(name="emr_pk")
	private Integer emrPk;

	@Column(name="emr_celular")
	private String emrCelular;

	@Column(name="emr_direccion")
	private String emrDireccion;

	@Column(name="emr_email")
	private String emrEmail;

	@Column(name="emr_estado")
	private String emrEstado;

	@Column(name="emr_foto")
	private String emrFoto;

	@Column(name="emr_nombre")
	private String emrNombre;

	@Column(name="emr_pagina")
	private String emrPagina;

	@Column(name="emr_ruc")
	private String emrRuc;

	@Column(name="emr_telefono")
	private String emrTelefono;

	//bi-directional many-to-one association to Carpeta
	@OneToMany(mappedBy="empresaTbl")
	private List<Carpeta> carpetaTbls;

	//bi-directional many-to-one association to Cementerio
	@OneToMany(mappedBy="empresaTbl")
	private List<Cementerio> cementerioTbls;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="empresaTbl")
	private List<Empleado> empleadoTbls;

	//bi-directional many-to-one association to HolguraEntradaSalida
	@ManyToOne
	@JoinColumn(name="hol_ent_sal_fk")
	private HolguraEntradaSalida holguraEntradaSalidaTbl;

	//bi-directional many-to-one association to TipoEmpresa
	@ManyToOne
	@JoinColumn(name="tip_empresa_fk")
	private TipoEmpresa tipoEmpresaTbl;

	//bi-directional many-to-one association to Eucaristia
	@OneToMany(mappedBy="empresaTbl")
	private List<Eucaristia> eucaristiaTbls;

	//bi-directional many-to-one association to PersonaEmpresa
	@OneToMany(mappedBy="empresaTbl")
	private List<PersonaEmpresa> personaEmpresaTbls;

	//bi-directional many-to-many association to Modulo
	@ManyToMany(mappedBy="empresaTbls")
	private List<Modulo> segtModulos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="empresaTbl")
	private List<Usuario> segtUsuarios;

	public Empresa() {
	}

	public Integer getEmrPk() {
		return this.emrPk;
	}

	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}

	public String getEmrCelular() {
		return this.emrCelular;
	}

	public void setEmrCelular(String emrCelular) {
		this.emrCelular = emrCelular;
	}

	public String getEmrDireccion() {
		return this.emrDireccion;
	}

	public void setEmrDireccion(String emrDireccion) {
		this.emrDireccion = emrDireccion;
	}

	public String getEmrEmail() {
		return this.emrEmail;
	}

	public void setEmrEmail(String emrEmail) {
		this.emrEmail = emrEmail;
	}

	public String getEmrEstado() {
		return this.emrEstado;
	}

	public void setEmrEstado(String emrEstado) {
		this.emrEstado = emrEstado;
	}

	public String getEmrFoto() {
		return this.emrFoto;
	}

	public void setEmrFoto(String emrFoto) {
		this.emrFoto = emrFoto;
	}

	public String getEmrNombre() {
		return this.emrNombre;
	}

	public void setEmrNombre(String emrNombre) {
		this.emrNombre = emrNombre;
	}

	public String getEmrPagina() {
		return this.emrPagina;
	}

	public void setEmrPagina(String emrPagina) {
		this.emrPagina = emrPagina;
	}

	public String getEmrRuc() {
		return this.emrRuc;
	}

	public void setEmrRuc(String emrRuc) {
		this.emrRuc = emrRuc;
	}

	public String getEmrTelefono() {
		return this.emrTelefono;
	}

	public void setEmrTelefono(String emrTelefono) {
		this.emrTelefono = emrTelefono;
	}

	public List<Carpeta> getCarpetaTbls() {
		return this.carpetaTbls;
	}

	public void setCarpetaTbls(List<Carpeta> carpetaTbls) {
		this.carpetaTbls = carpetaTbls;
	}

	public Carpeta addCarpetaTbl(Carpeta carpetaTbl) {
		getCarpetaTbls().add(carpetaTbl);
		carpetaTbl.setEmpresaTbl(this);

		return carpetaTbl;
	}

	public Carpeta removeCarpetaTbl(Carpeta carpetaTbl) {
		getCarpetaTbls().remove(carpetaTbl);
		carpetaTbl.setEmpresaTbl(null);

		return carpetaTbl;
	}

	public List<Cementerio> getCementerioTbls() {
		return this.cementerioTbls;
	}

	public void setCementerioTbls(List<Cementerio> cementerioTbls) {
		this.cementerioTbls = cementerioTbls;
	}

	public Cementerio addCementerioTbl(Cementerio cementerioTbl) {
		getCementerioTbls().add(cementerioTbl);
		cementerioTbl.setEmpresaTbl(this);

		return cementerioTbl;
	}

	public Cementerio removeCementerioTbl(Cementerio cementerioTbl) {
		getCementerioTbls().remove(cementerioTbl);
		cementerioTbl.setEmpresaTbl(null);

		return cementerioTbl;
	}

	public List<Empleado> getEmpleadoTbls() {
		return this.empleadoTbls;
	}

	public void setEmpleadoTbls(List<Empleado> empleadoTbls) {
		this.empleadoTbls = empleadoTbls;
	}

	public Empleado addEmpleadoTbl(Empleado empleadoTbl) {
		getEmpleadoTbls().add(empleadoTbl);
		empleadoTbl.setEmpresaTbl(this);

		return empleadoTbl;
	}

	public Empleado removeEmpleadoTbl(Empleado empleadoTbl) {
		getEmpleadoTbls().remove(empleadoTbl);
		empleadoTbl.setEmpresaTbl(null);

		return empleadoTbl;
	}

	public HolguraEntradaSalida getHolguraEntradaSalidaTbl() {
		return this.holguraEntradaSalidaTbl;
	}

	public void setHolguraEntradaSalidaTbl(HolguraEntradaSalida holguraEntradaSalidaTbl) {
		this.holguraEntradaSalidaTbl = holguraEntradaSalidaTbl;
	}

	public TipoEmpresa getTipoEmpresaTbl() {
		return this.tipoEmpresaTbl;
	}

	public void setTipoEmpresaTbl(TipoEmpresa tipoEmpresaTbl) {
		this.tipoEmpresaTbl = tipoEmpresaTbl;
	}

	public List<Eucaristia> getEucaristiaTbls() {
		return this.eucaristiaTbls;
	}

	public void setEucaristiaTbls(List<Eucaristia> eucaristiaTbls) {
		this.eucaristiaTbls = eucaristiaTbls;
	}

	public Eucaristia addEucaristiaTbl(Eucaristia eucaristiaTbl) {
		getEucaristiaTbls().add(eucaristiaTbl);
		eucaristiaTbl.setEmpresaTbl(this);

		return eucaristiaTbl;
	}

	public Eucaristia removeEucaristiaTbl(Eucaristia eucaristiaTbl) {
		getEucaristiaTbls().remove(eucaristiaTbl);
		eucaristiaTbl.setEmpresaTbl(null);

		return eucaristiaTbl;
	}

	public List<PersonaEmpresa> getPersonaEmpresaTbls() {
		return this.personaEmpresaTbls;
	}

	public void setPersonaEmpresaTbls(List<PersonaEmpresa> personaEmpresaTbls) {
		this.personaEmpresaTbls = personaEmpresaTbls;
	}

	public PersonaEmpresa addPersonaEmpresaTbl(PersonaEmpresa personaEmpresaTbl) {
		getPersonaEmpresaTbls().add(personaEmpresaTbl);
		personaEmpresaTbl.setEmpresaTbl(this);

		return personaEmpresaTbl;
	}

	public PersonaEmpresa removePersonaEmpresaTbl(PersonaEmpresa personaEmpresaTbl) {
		getPersonaEmpresaTbls().remove(personaEmpresaTbl);
		personaEmpresaTbl.setEmpresaTbl(null);

		return personaEmpresaTbl;
	}

	public List<Modulo> getSegtModulos() {
		return this.segtModulos;
	}

	public void setSegtModulos(List<Modulo> segtModulos) {
		this.segtModulos = segtModulos;
	}

	public List<Usuario> getSegtUsuarios() {
		return this.segtUsuarios;
	}

	public void setSegtUsuarios(List<Usuario> segtUsuarios) {
		this.segtUsuarios = segtUsuarios;
	}

	public Usuario addSegtUsuario(Usuario segtUsuario) {
		getSegtUsuarios().add(segtUsuario);
		segtUsuario.setEmpresaTbl(this);

		return segtUsuario;
	}

	public Usuario removeSegtUsuario(Usuario segtUsuario) {
		getSegtUsuarios().remove(segtUsuario);
		segtUsuario.setEmpresaTbl(null);

		return segtUsuario;
	}

}