/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.view.VistaUsuario;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "vistaUsuarioDataManager")
public class VistaUsuarioDataManager extends BaseDataManager {

	private static final long serialVersionUID = 1L;
	
	private VistaUsuario vistaUsuarioBuscar;
	private List<VistaUsuario> listVistaUsuario;
	
	public VistaUsuarioDataManager () {
		super();
	}
	
	@PostConstruct
	public void init() {
		this.vistaUsuarioBuscar = new VistaUsuario();
		this.listVistaUsuario = new ArrayList<VistaUsuario>();
	}

	/**
	 * @return the vistaUsuarioBuscar
	 */
	public VistaUsuario getVistaUsuarioBuscar() {
		return vistaUsuarioBuscar;
	}

	/**
	 * @param vistaUsuarioBuscar the vistaUsuarioBuscar to set
	 */
	public void setVistaUsuarioBuscar(VistaUsuario vistaUsuarioBuscar) {
		this.vistaUsuarioBuscar = vistaUsuarioBuscar;
	}

	/**
	 * @return the listVistaUsuario
	 */
	public List<VistaUsuario> getListVistaUsuario() {
		return listVistaUsuario;
	}

	/**
	 * @param listVistaUsuario the listVistaUsuario to set
	 */
	public void setListVistaUsuario(List<VistaUsuario> listVistaUsuario) {
		this.listVistaUsuario = listVistaUsuario;
	}

}
