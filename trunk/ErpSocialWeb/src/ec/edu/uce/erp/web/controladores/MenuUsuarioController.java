/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.vo.LoginVO;
import ec.edu.uce.erp.web.common.controladores.BaseController;

/**
 * @author 
 * 
 */
@ViewScoped
@ManagedBean (name="menuUsuarioController")
public class MenuUsuarioController extends BaseController {

	private static final long serialVersionUID = 1L;

	private MenuModel model;

	public MenuUsuarioController() {
		
		model = new DefaultMenuModel();
		
		if (getSessionParameter("usuario") != null && getSessionParameter("loginVO") != null) {
			
			LoginVO loginVO = (LoginVO)getSessionParameter("loginVO");
			List<Modulo> colModuloUsuario = loginVO.getColModuloUsuario();
			
			if (CollectionUtils.isNotEmpty(colModuloUsuario)) {
				
				Map<Integer, String> mapMenuUsuario = new HashMap<Integer, String>();
				
				for (Modulo modulo : colModuloUsuario) {
					
					Submenu subMenu = new Submenu();
					subMenu.setLabel(modulo.getNombreModulo());
					
					for (Menu menuDTO : modulo.getSegtMenus()) {
						
						MenuItem menuItem = new MenuItem();
						menuItem.setValue(menuDTO.getNombreMenu());
						menuItem.setUrl(menuDTO.getUrlMenu());
						menuItem.setTitle(menuDTO.getDescMenu());
						menuItem.setAjax(true);
						subMenu.getChildren().add(menuItem);
						
						mapMenuUsuario.put(menuDTO.getIdMenu(), menuDTO.getUrlMenu());
						
					}
					
					this.model.addSubmenu(subMenu);
					
				}
				
				getExternalContext().getSessionMap().put("menuUsuario", mapMenuUsuario);
				
			}
			
		}
		
	}

	public MenuModel getModel() {
		return model;
	}

	public void save() {
		addMessage("Data saved");
	}

	public void update() {
		addMessage("Data updated");
	}

	public void delete() {
		addMessage("Data deleted");
	}

	public String redirect() {
		return "home?faces-redirect=true";
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
