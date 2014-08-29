package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "reporteAtrasoDataManager")

public class ReporteAtrasoDataManager extends BaseDataManager {
private static final long serialVersionUID = 1L;

	
	private List<EmpleadoAtrasoListDTO> atrasoList;
	private List<EmpleadoListDTO> empleadoList;
	private RegistroDTO registroDTO;
	private EmpleadoAtrasoListDTO empleadoAtrasoListDTO;
	private List<EmpleadoAtrasoListDTO> atrasoList2;
	
	
	public ReporteAtrasoDataManager() {
	
		atrasoList=new ArrayList<EmpleadoAtrasoListDTO>();
		empleadoList=new ArrayList<EmpleadoListDTO>();
		registroDTO=new RegistroDTO();
		empleadoAtrasoListDTO=new EmpleadoAtrasoListDTO();
		atrasoList2=new ArrayList<EmpleadoAtrasoListDTO>();
		
		
		
	}


	public List<EmpleadoAtrasoListDTO> getAtrasoList() {
		return atrasoList;
	}


	public List<EmpleadoListDTO> getEmpleadoList() {
		return empleadoList;
	}


	public RegistroDTO getRegistroDTO() {
		return registroDTO;
	}


	public void setAtrasoList(List<EmpleadoAtrasoListDTO> atrasoList) {
		this.atrasoList = atrasoList;
	}


	public void setEmpleadoList(List<EmpleadoListDTO> empleadoList) {
		this.empleadoList = empleadoList;
	}


	public void setRegistroDTO(RegistroDTO registroDTO) {
		this.registroDTO = registroDTO;
	}


	public EmpleadoAtrasoListDTO getEmpleadoAtrasoListDTO() {
		return empleadoAtrasoListDTO;
	}


	public void setEmpleadoAtrasoListDTO(EmpleadoAtrasoListDTO empleadoAtrasoListDTO) {
		this.empleadoAtrasoListDTO = empleadoAtrasoListDTO;
	}


	public List<EmpleadoAtrasoListDTO> getAtrasoList2() {
		return atrasoList2;
	}


	public void setAtrasoList2(List<EmpleadoAtrasoListDTO> atrasoList2) {
		this.atrasoList2 = atrasoList2;
	}

	
	
	
	
}
