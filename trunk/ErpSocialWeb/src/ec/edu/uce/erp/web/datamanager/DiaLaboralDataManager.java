package ec.edu.uce.erp.web.datamanager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "diaLaboralDataManager")
public class DiaLaboralDataManager extends BaseDataManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Object anio;
	
	public DiaLaboralDataManager() {
	
	}

	public Object getAnio() {
		return anio;
	}

	public void setAnio(Object anio) {
		this.anio = anio;
	}
}
