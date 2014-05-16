package ec.edu.uce.erp.web.datamanager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean (name = "horarioDataManager")
public class HorarioDataManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Map<String, String> horaDesde;
	private Map<String, String> horaHasta;
	
	private Map<String, String> minutoDesde;
	private Map<String, String> minutiHasta;
	
	
	public HorarioDataManager() {
		horaDesde=new HashMap<String, String>();
		horaHasta=new HashMap<String, String>();
	}

	public Map<String, String> getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(Map<String, String> horaDesde) {
		this.horaDesde = horaDesde;
	}

	public Map<String, String> getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(Map<String, String> horaHasta) {
		this.horaHasta = horaHasta;
	}
	
	
}
