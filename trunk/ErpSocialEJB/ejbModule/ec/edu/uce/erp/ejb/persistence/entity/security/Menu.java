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

import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaUtil;


/**
 * The persistent class for the segt_menu database table.
 * 
 */
@Entity
@Table(name="segt_menu")
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu extends AuditoriaUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEGT_MENU_IDMENU_GENERATOR", sequenceName="SEGT_MENU_ID_MENU_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGT_MENU_IDMENU_GENERATOR")
	@Column(name="id_menu")
	private Integer idMenu;

	@Column(name="desc_menu")
	private String descMenu;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	@Column(name="nombre_menu")
	private String nombreMenu;

	@Column(name="url_menu")
	private String urlMenu;

	//bi-directional many-to-many association to Modulo
//	@ManyToMany(mappedBy="segtMenus")
	@ManyToMany
	@JoinTable(
		name="segt_modulo_menu"
		, joinColumns={
			@JoinColumn(name="id_menu")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_modulo")
			}
		)
	private List<Modulo> segtModulos;

	public Menu() {
	}

	public Integer getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getDescMenu() {
		return this.descMenu;
	}

	public void setDescMenu(String descMenu) {
		this.descMenu = descMenu;
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

	public String getNombreMenu() {
		return this.nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getUrlMenu() {
		return this.urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

	public List<Modulo> getSegtModulos() {
		return this.segtModulos;
	}

	public void setSegtModulos(List<Modulo> segtModulos) {
		this.segtModulos = segtModulos;
	}

}