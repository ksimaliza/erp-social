/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.view.VistaHistoricoTransaccion;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

/**
 * @author
 *
 */
@SessionScoped
@ManagedBean (name = "historicoTransaccionDataManager")
public class VistaHistoricoTransaccionDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	
	private VistaHistoricoTransaccion historicoTransaccionBuscar;
	private List<VistaHistoricoTransaccion> listaVistaHistoricoTransaccion;
	
	public VistaHistoricoTransaccionDataManager () {
		super();
	}
	
	@PostConstruct
	public void inicializarObjetos() {
		this.historicoTransaccionBuscar = new VistaHistoricoTransaccion();
		this.listaVistaHistoricoTransaccion = new ArrayList<VistaHistoricoTransaccion>();
	}

	/**
	 * @return the historicoTransaccionBuscar
	 */
	public VistaHistoricoTransaccion getHistoricoTransaccionBuscar() {
		return historicoTransaccionBuscar;
	}

	/**
	 * @param historicoTransaccionBuscar the historicoTransaccionBuscar to set
	 */
	public void setHistoricoTransaccionBuscar(
			VistaHistoricoTransaccion historicoTransaccionBuscar) {
		this.historicoTransaccionBuscar = historicoTransaccionBuscar;
	}

	/**
	 * @return the listaVistaHistoricoTransaccion
	 */
	public List<VistaHistoricoTransaccion> getListaVistaHistoricoTransaccion() {
		return listaVistaHistoricoTransaccion;
	}

	/**
	 * @param listaVistaHistoricoTransaccion the listaVistaHistoricoTransaccion to set
	 */
	public void setListaVistaHistoricoTransaccion(
			List<VistaHistoricoTransaccion> listaVistaHistoricoTransaccion) {
		this.listaVistaHistoricoTransaccion = listaVistaHistoricoTransaccion;
	}
	

}
