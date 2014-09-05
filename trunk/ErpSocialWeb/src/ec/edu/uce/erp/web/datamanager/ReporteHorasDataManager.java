package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "reporteHorasDataManager")

public class ReporteHorasDataManager extends BaseDataManager {
	private static final long serialVersionUID = 1L;
	
	private List<EmpleadoAtrasoListDTO> registroList;
	private List<EmpleadoListDTO> empleadoList;
	private RegistroDTO registroDTO;
	private EmpleadoAtrasoListDTO registroListDTO;
	private List<EmpleadoAtrasoListDTO> registroLists;
	private Date fecha;
	
	
	public ReporteHorasDataManager() {
	
		registroList=new ArrayList<EmpleadoAtrasoListDTO>();
		empleadoList=new ArrayList<EmpleadoListDTO>();
		registroDTO=new RegistroDTO();
		registroListDTO=new EmpleadoAtrasoListDTO();
		registroLists=new ArrayList<EmpleadoAtrasoListDTO>();
		fecha=new Date();
		
	}


	public List<EmpleadoAtrasoListDTO> getRegistroList() {
		return registroList;
	}


	public List<EmpleadoListDTO> getEmpleadoList() {
		return empleadoList;
	}


	public RegistroDTO getRegistroDTO() {
		return registroDTO;
	}


	public EmpleadoAtrasoListDTO getRegistroListDTO() {
		return registroListDTO;
	}


	public List<EmpleadoAtrasoListDTO> getRegistroLists() {
		return registroLists;
	}


	public void setRegistroList(List<EmpleadoAtrasoListDTO> registroList) {
		this.registroList = registroList;
	}


	public void setEmpleadoList(List<EmpleadoListDTO> empleadoList) {
		this.empleadoList = empleadoList;
	}


	public void setRegistroDTO(RegistroDTO registroDTO) {
		this.registroDTO = registroDTO;
	}


	public void setRegistroListDTO(EmpleadoAtrasoListDTO registroListDTO) {
		this.registroListDTO = registroListDTO;
	}


	public void setRegistroLists(List<EmpleadoAtrasoListDTO> registroLists) {
		this.registroLists = registroLists;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	



}
