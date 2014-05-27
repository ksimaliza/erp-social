package ec.edu.uce.erp.web.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.TiempoDTO;


@SessionScoped
@ManagedBean (name = "horarioDataManager")
public class HorarioDataManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String horaDesdeCode;
	private String horaHastaCode;
	
	private String minutoDesdeCode;
	private String minutoHastaCode;
 
	
	private List<TiempoDTO> horaDesde;
	private List<TiempoDTO> horaHasta;
	
	private List<TiempoDTO> minutoDesde;
	private List<TiempoDTO> minutoHasta;
	
	
	private List<TipoDTO> tipoList;
	private String tipoCode;
	
	private List<TiempoDTO> diaList;
	private String diaCoda;
	
	
	public HorarioDataManager() {
		horaDesde=new ArrayList<TiempoDTO>();
		horaHasta=new ArrayList<TiempoDTO>();
		
		minutoDesde=new ArrayList<TiempoDTO>();
		minutoHasta=new ArrayList<TiempoDTO>();
		
		tipoList=new ArrayList<TipoDTO>();
	}


	public String getHoraDesdeCode() {
		return horaDesdeCode;
	}


	public void setHoraDesdeCode(String horaDesdeCode) {
		this.horaDesdeCode = horaDesdeCode;
	}


	public String getHoraHastaCode() {
		return horaHastaCode;
	}


	public void setHoraHastaCode(String horaHastaCode) {
		this.horaHastaCode = horaHastaCode;
	}


	public String getMinutoDesdeCode() {
		return minutoDesdeCode;
	}


	public void setMinutoDesdeCode(String minutoDesdeCode) {
		this.minutoDesdeCode = minutoDesdeCode;
	}


	public String getMinutoHastaCode() {
		return minutoHastaCode;
	}


	public void setMinutoHastaCode(String minutoHastaCode) {
		this.minutoHastaCode = minutoHastaCode;
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


	public List<TipoDTO> getTipoList() {
		return tipoList;
	}


	public void setTipoList(List<TipoDTO> tipoList) {
		this.tipoList = tipoList;
	}


	public String getTipoCode() {
		return tipoCode;
	}


	public void setTipoCode(String tipoCode) {
		this.tipoCode = tipoCode;
	}


	public List<TiempoDTO> getDiaList() {
		return diaList;
	}


	public void setDiaList(List<TiempoDTO> diaList) {
		this.diaList = diaList;
	}


	public String getDiaCoda() {
		return diaCoda;
	}


	public void setDiaCoda(String diaCoda) {
		this.diaCoda = diaCoda;
	}


		
}
