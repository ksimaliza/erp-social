/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.security.Parametro;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean(name = "parametroDataManager")
public class ParametroDataManager extends BaseDataManager {

	private static final long serialVersionUID = 1L;
	
	private Parametro parametroEditar;
	private Parametro parametroBuscar;
	private List<Parametro> listParametro;
	
	public ParametroDataManager () {
		super();
	}
	
	@PostConstruct
	public void init() {
		this.parametroEditar = new Parametro();
		this.parametroBuscar = new Parametro();
		this.listParametro = new ArrayList<Parametro>();
	}

	/**
	 * @return the parametroEditar
	 */
	public Parametro getParametroEditar() {
		return parametroEditar;
	}

	/**
	 * @param parametroEditar the parametroEditar to set
	 */
	public void setParametroEditar(Parametro parametroEditar) {
		this.parametroEditar = parametroEditar;
	}

	/**
	 * @return the parametroBuscar
	 */
	public Parametro getParametroBuscar() {
		return parametroBuscar;
	}

	/**
	 * @param parametroBuscar the parametroBuscar to set
	 */
	public void setParametroBuscar(Parametro parametroBuscar) {
		this.parametroBuscar = parametroBuscar;
	}

	/**
	 * @return the listParametro
	 */
	public List<Parametro> getListParametro() {
		return listParametro;
	}

	/**
	 * @param listParametro the listParametro to set
	 */
	public void setListParametro(List<Parametro> listParametro) {
		this.listParametro = listParametro;
	}

}
