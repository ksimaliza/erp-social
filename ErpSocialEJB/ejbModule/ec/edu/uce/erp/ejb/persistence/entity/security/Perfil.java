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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the segt_perfil database table.
 * 
 */
@Entity
@Table(name="segt_perfil")
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEGT_PERFIL_IDPERFIL_GENERATOR", sequenceName="SEGT_PERFIL_ID_PERFIL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGT_PERFIL_IDPERFIL_GENERATOR")
	@Column(name="id_perfil")
	private Integer idPerfil;

	@Column(name="desc_perfil")
	private String descPerfil;

	private String estado;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	@Column(name="nombre_perfil")
	private String nombrePerfil;

	//bi-directional many-to-many association to Modulo
	@ManyToMany
	@JoinTable(
		name="segt_modulo_perfil"
		, joinColumns={
			@JoinColumn(name="id_perfil")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_modulo")
			}
		)
	private List<Modulo> segtModulos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="segtPerfil")
	private List<Usuario> segtUsuarios;
	
	@Transient
	private Empresa npEmpresaDTO;
	
	@Transient
	private List<Modulo> npColModulos;

	public Perfil() {
	}

	public Integer getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescPerfil() {
		return this.descPerfil;
	}

	public void setDescPerfil(String descPerfil) {
		this.descPerfil = descPerfil;
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

	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
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
		segtUsuario.setSegtPerfil(this);

		return segtUsuario;
	}

	public Usuario removeSegtUsuario(Usuario segtUsuario) {
		getSegtUsuarios().remove(segtUsuario);
		segtUsuario.setSegtPerfil(null);

		return segtUsuario;
	}

	/**
	 * @return the npEmpresaDTO
	 */
	public Empresa getNpEmpresaDTO() {
		return npEmpresaDTO;
	}

	/**
	 * @param npEmpresaDTO the npEmpresaDTO to set
	 */
	public void setNpEmpresaDTO(Empresa npEmpresaDTO) {
		this.npEmpresaDTO = npEmpresaDTO;
	}

	/**
	 * @return the npColModulos
	 */
	public List<Modulo> getNpColModulos() {
		return npColModulos;
	}

	/**
	 * @param npColModulos the npColModulos to set
	 */
	public void setNpColModulos(List<Modulo> npColModulos) {
		this.npColModulos = npColModulos;
	}

}