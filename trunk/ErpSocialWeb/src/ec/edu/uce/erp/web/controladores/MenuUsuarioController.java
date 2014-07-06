/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ec.edu.uce.erp.common.util.MessagesApplicacion;
import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;
import ec.edu.uce.erp.ejb.persistence.entity.security.MenuUsuario;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
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
		
		this.model = new DefaultMenuModel();
		
		if (getSessionParameter("usuario") != null && getSessionParameter("loginVO") != null) {
			
			Usuario usuario = (Usuario)getSessionParameter("usuario");
			
			Map<Integer, String> mapMenuUsuario = new HashMap<Integer, String>();
			
			if (!usuario.getNpDebeCambiarClave()) {
				
				LoginVO loginVO = (LoginVO)getSessionParameter("loginVO");
				List<Modulo> colModuloUsuario = loginVO.getColModuloUsuario();
				if (CollectionUtils.isNotEmpty(colModuloUsuario)) {
					
					for (Modulo modulo : colModuloUsuario) {
						
						Boolean agregarModulo = Boolean.FALSE;
						
						for (Menu menu : modulo.getSegtMenus()) {
							for (MenuUsuario menuUsuario : usuario.getSegtMenuUsuarios()) {
								if (menu.getIdMenu().intValue() == menuUsuario.getIdMenu().intValue()) {
									agregarModulo = Boolean.TRUE;
									break;
								}
							}
							if (agregarModulo) {
								break;
							}
						}
						
						if (agregarModulo) {
							
							DefaultSubMenu defaultSubMenu = new DefaultSubMenu(modulo.getNombreModulo());
							
							for (Menu menuDTO : modulo.getSegtMenus()) {
								
								Boolean agregarMenu = Boolean.FALSE;
								
								for (MenuUsuario menuUsuario : usuario.getSegtMenuUsuarios()) {
									if (menuDTO.getIdMenu().intValue() == menuUsuario.getIdMenu().intValue()) {
										agregarMenu = Boolean.TRUE;
										break;
									}
								}
								
								if (agregarMenu) {
									
									DefaultMenuItem item = new DefaultMenuItem(menuDTO.getNombreMenu());
									item.setUrl(menuDTO.getUrlMenu());
									item.setAjax(true);
									item.setTitle(menuDTO.getDescMenu());
									defaultSubMenu.addElement(item);
									
									mapMenuUsuario.put(menuDTO.getIdMenu(), menuDTO.getUrlMenu());
								}
								
								
							}
							
							model.addElement(defaultSubMenu);
						}
						
					}
					
				}
				
			}
			
			//se agregar las opciones que todo usuario debe tener asignadas
			mapMenuUsuario.put(0, MessagesApplicacion.getString("erp.seguridades.pagina.home.usuario"));
			mapMenuUsuario.put(-1, MessagesApplicacion.getString("erp.seguridades.pagina.home.usuario"));
			mapMenuUsuario.put(-2, MessagesApplicacion.getString("erp.seguridades.pagina.cambiar.clave.usuario"));
			
			getExternalContext().getSessionMap().put("menuUsuario", mapMenuUsuario);
			
		}
		
	}

	public MenuModel getModel() {
		return model;
	}
	
	public Usuario getUsuarioSession () {
		
		if (getSessionParameter("usuario") != null) {
			return (Usuario)getSessionParameter("usuario");
		}
		
		return null;
	}


}
