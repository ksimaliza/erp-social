/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name="menuDataManager")
public class MenuDataManager implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MenuDataManager.class);
	
	private Menu menuInstancia;
	private Menu menuEditar;
	private Menu menuBuscar;
	
	private List<Menu> listaMenu;
	
	public MenuDataManager () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		
		this.menuInstancia = new Menu();
		this.menuEditar = new Menu();
		this.menuBuscar = new Menu();
		
		this.listaMenu = new ArrayList<Menu>();
	}

	/**
	 * @return the menuInstancia
	 */
	public Menu getMenuInstancia() {
		return menuInstancia;
	}

	/**
	 * @param menuInstancia the menuInstancia to set
	 */
	public void setMenuInstancia(Menu menuInstancia) {
		this.menuInstancia = menuInstancia;
	}

	/**
	 * @return the menuEditar
	 */
	public Menu getMenuEditar() {
		return menuEditar;
	}

	/**
	 * @param menuEditar the menuEditar to set
	 */
	public void setMenuEditar(Menu menuEditar) {
		this.menuEditar = menuEditar;
	}

	/**
	 * @return the menuBuscar
	 */
	public Menu getMenuBuscar() {
		return menuBuscar;
	}

	/**
	 * @param menuBuscar the menuBuscar to set
	 */
	public void setMenuBuscar(Menu menuBuscar) {
		this.menuBuscar = menuBuscar;
	}

	/**
	 * @return the listaMenu
	 */
	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	/**
	 * @param listaMenu the listaMenu to set
	 */
	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

}
