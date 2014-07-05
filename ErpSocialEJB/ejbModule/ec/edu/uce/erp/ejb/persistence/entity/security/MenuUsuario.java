package ec.edu.uce.erp.ejb.persistence.entity.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the segt_menu_usuario database table.
 * 
 */
@Entity
@Table(name="segt_menu_usuario")
@NamedQuery(name="MenuUsuario.findAll", query="SELECT m FROM MenuUsuario m")
public class MenuUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEGT_MENU_USUARIO_IDMENUUSUARIO_GENERATOR", sequenceName="SEGT_MENU_USUARIO_ID_MENU_USUARIO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGT_MENU_USUARIO_IDMENUUSUARIO_GENERATOR")
	@Column(name="id_menu_usuario")
	private Integer idMenuUsuario;

	@Column(name="id_menu")
	private Integer idMenu;

	@Column(name="id_usuario")
	private Integer idUsuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="id_usuario", referencedColumnName="id_usuario", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private Usuario usuario;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_menu", referencedColumnName="id_menu", unique=false, nullable=true, insertable=false, updatable=false)
		})
	private Menu menu;

	public MenuUsuario() {
	}

	public Integer getIdMenuUsuario() {
		return this.idMenuUsuario;
	}

	public void setIdMenuUsuario(Integer idMenuUsuario) {
		this.idMenuUsuario = idMenuUsuario;
	}

	public Integer getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}