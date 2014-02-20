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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the segt_modulo database table.
 * 
 */
@Entity
@Table(name="segt_modulo")
@NamedQuery(name="Modulo.findAll", query="SELECT m FROM Modulo m")
public class Modulo extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEGT_MODULO_IDMODULO_GENERATOR", sequenceName="SEGT_MODULO_ID_MODULO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGT_MODULO_IDMODULO_GENERATOR")
	@Column(name="id_modulo")
	private Integer idModulo;

	@Column(name="desc_modulo")
	private String descModulo;

	private String estado;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	@Column(name="nombre_modulo")
	private String nombreModulo;

	//bi-directional many-to-many association to Empresa
	@ManyToMany
	@JoinTable(
		name="segt_empresa_modulo"
		, joinColumns={
			@JoinColumn(name="id_modulo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="emr_pk")
			}
		)
	private List<Empresa> empresaTbls;

	//bi-directional many-to-many association to Menu
//	@ManyToMany
//	@JoinTable(
//		name="segt_modulo_menu"
//		, joinColumns={
//			@JoinColumn(name="id_modulo")
//			}
//		, inverseJoinColumns={
//			@JoinColumn(name="id_menu")
//			}
//		)
	@ManyToMany(mappedBy="segtModulos")
	private List<Menu> segtMenus;

	//bi-directional many-to-many association to Perfil
	@ManyToMany(mappedBy="segtModulos")
	private List<Perfil> segtPerfils;
	
	@Transient
	private Integer npIdEmpresa;

	public Modulo() {
	}

	public Integer getIdModulo() {
		return this.idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	public String getDescModulo() {
		return this.descModulo;
	}

	public void setDescModulo(String descModulo) {
		this.descModulo = descModulo;
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

	public String getNombreModulo() {
		return this.nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	public List<Empresa> getEmpresaTbls() {
		return this.empresaTbls;
	}

	public void setEmpresaTbls(List<Empresa> empresaTbls) {
		this.empresaTbls = empresaTbls;
	}

	public List<Menu> getSegtMenus() {
		return this.segtMenus;
	}

	public void setSegtMenus(List<Menu> segtMenus) {
		this.segtMenus = segtMenus;
	}

	public List<Perfil> getSegtPerfils() {
		return this.segtPerfils;
	}

	public void setSegtPerfils(List<Perfil> segtPerfils) {
		this.segtPerfils = segtPerfils;
	}

	public Integer getNpIdEmpresa() {
		return npIdEmpresa;
	}

	public void setNpIdEmpresa(Integer npIdEmpresa) {
		this.npIdEmpresa = npIdEmpresa;
	}

}