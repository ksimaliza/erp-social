package ec.edu.uce.erp.web.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "reporteFaltasDataManager")
public class ReporteFaltasDataManager  extends BaseDataManager implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	private List<FaltaListDTO> faltasList;
	private List<EmpleadoListDTO> empleadoList;
	private FaltaListDTO faltaListDTO;
	private List<FaltaListDTO> faltasList2;
	
	
	public ReporteFaltasDataManager() {
	
		faltaListDTO=new FaltaListDTO();
		empleadoList=new ArrayList<EmpleadoListDTO>();
		faltasList=new ArrayList<FaltaListDTO>();
		faltasList2=new ArrayList<FaltaListDTO>();
		
	}


	public List<FaltaListDTO> getFaltasList2() {
		return faltasList2;
	}


	public void setFaltasList2(List<FaltaListDTO> faltasList2) {
		this.faltasList2 = faltasList2;
	}


	public List<FaltaListDTO> getFaltasList() {
		return faltasList;
	}


	public List<EmpleadoListDTO> getEmpleadoList() {
		return empleadoList;
	}


	public FaltaListDTO getFaltaListDTO() {
		return faltaListDTO;
	}


	public void setFaltasList(List<FaltaListDTO> faltasList) {
		this.faltasList = faltasList;
	}


	public void setEmpleadoList(List<EmpleadoListDTO> empleadoList) {
		this.empleadoList = empleadoList;
	}


	public void setFaltaListDTO(FaltaListDTO faltaListDTO) {
		this.faltaListDTO = faltaListDTO;
	}
	
	
}
