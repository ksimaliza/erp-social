package ec.edu.uce.erp.ejb.persistence.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the modulo_menu_view database table.
 * 
 */
@Entity
@Table(name="modulo_menu_view")
@NamedQuery(name="VistaModuloMenu.findAll", query="SELECT v FROM VistaModuloMenu v")
public class VistaModuloMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_vista")
	private Long idVista;

	@Column(name="desc_menu")
	private String descMenu;

	@Column(name="desc_modulo")
	private String descModulo;

	@Column(name="id_menu")
	private Integer idMenu;

	@Column(name="id_modulo")
	private Integer idModulo;

	@Column(name="id_perfil")
	private Integer idPerfil;
	
	@Column(name="modulo_estado")
	private String moduloEstado;

	@Column(name="nombre_menu")
	private String nombreMenu;

	@Column(name="nombre_modulo")
	private String nombreModulo;

	@Column(name="nombre_perfil")
	private String nombrePerfil;

	private Integer orden;

	@Column(name="perfil_estado")
	private String perfilEstado;

	@Column(name="url_menu")
	private String urlMenu;

	public VistaModuloMenu() {
	}

	public String getDescMenu() {
		return this.descMenu;
	}

	public void setDescMenu(String descMenu) {
		this.descMenu = descMenu;
	}

	public String getDescModulo() {
		return this.descModulo;
	}

	public void setDescModulo(String descModulo) {
		this.descModulo = descModulo;
	}

	public Integer getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public Integer getIdModulo() {
		return this.idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	public Integer getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdVista() {
		return this.idVista;
	}

	public void setIdVista(Long idVista) {
		this.idVista = idVista;
	}

	public String getModuloEstado() {
		return this.moduloEstado;
	}

	public void setModuloEstado(String moduloEstado) {
		this.moduloEstado = moduloEstado;
	}

	public String getNombreMenu() {
		return this.nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getNombreModulo() {
		return this.nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getPerfilEstado() {
		return this.perfilEstado;
	}

	public void setPerfilEstado(String perfilEstado) {
		this.perfilEstado = perfilEstado;
	}

	public String getUrlMenu() {
		return this.urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

}