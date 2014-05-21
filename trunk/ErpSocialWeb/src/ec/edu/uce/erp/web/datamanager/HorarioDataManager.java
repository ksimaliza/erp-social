package ec.edu.uce.erp.web.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.util.dto.TiempoDTO;


@SessionScoped
@ManagedBean (name = "horarioDataManager")
public class HorarioDataManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<TiempoDTO> horaDesde;
	private List<TiempoDTO> horaHasta;
	
	private List<TiempoDTO> minutoDesde;
	private List<TiempoDTO> minutoHasta;
	
	
	public HorarioDataManager() {
		horaDesde=new ArrayList<TiempoDTO>();
		horaHasta=new ArrayList<TiempoDTO>();
		
		minutoDesde=new ArrayList<TiempoDTO>();
		minutoHasta=new ArrayList<TiempoDTO>();
	}


	public List<TiempoDTO> getHoraDesde() {
		return horaDesde;
	}


	public void setHoraDesde(List<TiempoDTO> horaDesde) {
		this.horaDesde = horaDesde;
	}


	public List<TiempoDTO> getHoraHasta() {
		return horaHasta;
	}


	public void setHoraHasta(List<TiempoDTO> horaHasta) {
		this.horaHasta = horaHasta;
	}


	public List<TiempoDTO> getMinutoDesde() {
		return minutoDesde;
	}


	public void setMinutoDesde(List<TiempoDTO> minutoDesde) {
		this.minutoDesde = minutoDesde;
	}


	public List<TiempoDTO> getMinutoHasta() {
		return minutoHasta;
	}


	public void setMinutoHasta(List<TiempoDTO> minutoHasta) {
		this.minutoHasta = minutoHasta;
	}

	
	
}
