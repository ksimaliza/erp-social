package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the segt_usuario database table.
 * 
 */
@Entity
@Table(name="segt_usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEGT_USUARIO_IDUSUARIO_GENERATOR", sequenceName="SEGT_USUARIO_ID_USUARIO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGT_USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="id_usuario")
	private Integer idUsuario;

	@Column(name="apellidos_usuario")
	private String apellidosUsuario;

	@Column(name="ci_usuario")
	private String ciUsuario;

	@Column(name="email_usuario")
	private String emailUsuario;

	private String estado;

	@Column(name="fecha_cambio_clave")
	private Timestamp fechaCambioClave;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	@Column(name="fecha_ultimo_ingreso")
	private Timestamp fechaUltimoIngreso;

	@Column(name="login_usuario")
	private String loginUsuario;

	@Column(name="nombres_usuario")
	private String nombresUsuario;

	@Column(name="pass_usuario")
	private String passUsuario;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="segtUsuario")
	private List<Persona> personaTbls;

	//bi-directional many-to-one association to HistorialClave
	@OneToMany(mappedBy="segtUsuario")
	private List<HistorialClave> segtHistorialClaves;

//	//bi-directional many-to-one association to HistoricoTransaccione
//	@OneToMany(mappedBy="segtUsuario")
//	private List<HistoricoTransaccione> segtHistoricoTransacciones;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="emr_pk")
	private Empresa empresaTbl;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil segtPerfil;
	
	@Transient
	private String npPasswordTemp;
	
	@Transient
	private Boolean npDebeCambiarClave;
	
	@Transient
	private Integer npIdEmpresa;

	public Usuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidosUsuario() {
		return this.apellidosUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public String getCiUsuario() {
		return this.ciUsuario;
	}

	public void setCiUsuario(String ciUsuario) {
		this.ciUsuario = ciUsuario;
	}

	public String getEmailUsuario() {
		return this.emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaCambioClave() {
		return this.fechaCambioClave;
	}

	public void setFechaCambioClave(Timestamp fechaCambioClave) {
		this.fechaCambioClave = fechaCambioClave;
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

	public Timestamp getFechaUltimoIngreso() {
		return this.fechaUltimoIngreso;
	}

	public void setFechaUltimoIngreso(Timestamp fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}

	public String getLoginUsuario() {
		return this.loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getNombresUsuario() {
		return this.nombresUsuario;
	}

	public void setNombresUsuario(String nombresUsuario) {
		this.nombresUsuario = nombresUsuario;
	}

	public String getPassUsuario() {
		return this.passUsuario;
	}

	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}

	public List<Persona> getPersonaTbls() {
		return this.personaTbls;
	}

	public void setPersonaTbls(List<Persona> personaTbls) {
		this.personaTbls = personaTbls;
	}

	public Persona addPersonaTbl(Persona personaTbl) {
		getPersonaTbls().add(personaTbl);
		personaTbl.setSegtUsuario(this);

		return personaTbl;
	}

	public Persona removePersonaTbl(Persona personaTbl) {
		getPersonaTbls().remove(personaTbl);
		personaTbl.setSegtUsuario(null);

		return personaTbl;
	}

	public List<HistorialClave> getSegtHistorialClaves() {
		return this.segtHistorialClaves;
	}

	public void setSegtHistorialClaves(List<HistorialClave> segtHistorialClaves) {
		this.segtHistorialClaves = segtHistorialClaves;
	}

	public HistorialClave addSegtHistorialClave(HistorialClave segtHistorialClave) {
		getSegtHistorialClaves().add(segtHistorialClave);
		segtHistorialClave.setSegtUsuario(this);

		return segtHistorialClave;
	}

	public HistorialClave removeSegtHistorialClave(HistorialClave segtHistorialClave) {
		getSegtHistorialClaves().remove(segtHistorialClave);
		segtHistorialClave.setSegtUsuario(null);

		return segtHistorialClave;
	}

//	public List<HistoricoTransaccione> getSegtHistoricoTransacciones() {
//		return this.segtHistoricoTransacciones;
//	}
//
//	public void setSegtHistoricoTransacciones(List<HistoricoTransaccione> segtHistoricoTransacciones) {
//		this.segtHistoricoTransacciones = segtHistoricoTransacciones;
//	}

//	public HistoricoTransaccione addSegtHistoricoTransaccione(HistoricoTransaccione segtHistoricoTransaccione) {
//		getSegtHistoricoTransacciones().add(segtHistoricoTransaccione);
//		segtHistoricoTransaccione.setSegtUsuario(this);
//
//		return segtHistoricoTransaccione;
//	}
//
//	public HistoricoTransaccione removeSegtHistoricoTransaccione(HistoricoTransaccione segtHistoricoTransaccione) {
//		getSegtHistoricoTransacciones().remove(segtHistoricoTransaccione);
//		segtHistoricoTransaccione.setSegtUsuario(null);
//
//		return segtHistoricoTransaccione;
//	}

	public Empresa getEmpresaTbl() {
		return this.empresaTbl;
	}

	public void setEmpresaTbl(Empresa empresaTbl) {
		this.empresaTbl = empresaTbl;
	}

	public Perfil getSegtPerfil() {
		return this.segtPerfil;
	}

	public void setSegtPerfil(Perfil segtPerfil) {
		this.segtPerfil = segtPerfil;
	}

	/**
	 * @return the npPasswordTemp
	 */
	public String getNpPasswordTemp() {
		return npPasswordTemp;
	}

	/**
	 * @param npPasswordTemp the npPasswordTemp to set
	 */
	public void setNpPasswordTemp(String npPasswordTemp) {
		this.npPasswordTemp = npPasswordTemp;
	}

	/**
	 * @return the npDebeCambiarClave
	 */
	public Boolean getNpDebeCambiarClave() {
		return npDebeCambiarClave;
	}

	/**
	 * @param npDebeCambiarClave the npDebeCambiarClave to set
	 */
	public void setNpDebeCambiarClave(Boolean npDebeCambiarClave) {
		this.npDebeCambiarClave = npDebeCambiarClave;
	}
	
	public String getNpNombresCompletos () {
		return this.nombresUsuario + " " + this.apellidosUsuario;
	}

	public Integer getNpIdEmpresa() {
		return npIdEmpresa;
	}

	public void setNpIdEmpresa(Integer npIdEmpresa) {
		this.npIdEmpresa = npIdEmpresa;
	}

}