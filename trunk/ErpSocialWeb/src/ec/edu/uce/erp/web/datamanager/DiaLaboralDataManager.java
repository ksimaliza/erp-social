package ec.edu.uce.erp.web.datamanager;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaNoLaboralDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "diaLaboralDataManager")
public class DiaLaboralDataManager extends BaseDataManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Object anio;
	
	private Date fecha;
	
	private String observacion;
	
	private DiaNoLaboralDTO diaNoLaboral;
	
	
	public DiaLaboralDataManager() {
		diaNoLaboral=new DiaNoLaboralDTO();
	}

	public Object getAnio() {
		return anio;
	}

	public void setAnio(Object anio) {
		this.anio = anio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public DiaNoLaboralDTO getDiaNoLaboral() {
		return diaNoLaboral;
	}

	public void setDiaNoLaboral(DiaNoLaboralDTO diaNoLaboral) {
		this.diaNoLaboral = diaNoLaboral;
	}
}
