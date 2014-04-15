package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "faltaDataManager")
public class FaltaDataManager extends BaseDataManager {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	
	private FaltaDTO faltaInsertar;
	private Empleado empleadoInsertar;
	private EmpleadoDTO empleadoDTOInsertar;
		
	private List<EmpleadoListDTO> empleadoList;
	
	private List<FaltaListDTO> faltaList;
	
	private FaltaListDTO faltaBuscar;
	
	private Object empleadoCodigo;
	
	private Date fechaFalta;
	
	public FaltaDataManager() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		empleadoList=new ArrayList<EmpleadoListDTO>();
		faltaInsertar=new FaltaDTO();
		empleadoInsertar=new Empleado();
		empleadoDTOInsertar=new EmpleadoDTO();
		faltaBuscar=new FaltaListDTO();
		faltaList=new ArrayList<FaltaListDTO>();
	}

	public List<EmpleadoListDTO> getEmpleadoList() {
		return empleadoList;
	}

	public void setEmpleadoList(List<EmpleadoListDTO> empleadoList) {
		this.empleadoList = empleadoList;
	}

	public Object getEmpleadoCodigo() {
		return empleadoCodigo;
	}

	public void setEmpleadoCodigo(Object empleadoCodigo) {
		this.empleadoCodigo = empleadoCodigo;
	}

	public FaltaDTO getFaltaInsertar() {
		return faltaInsertar;
	}

	public void setFaltaInsertar(FaltaDTO faltaInsertar) {
		this.faltaInsertar = faltaInsertar;
	}

	public Empleado getEmpleadoInsertar() {
		return empleadoInsertar;
	}

	public void setEmpleadoInsertar(Empleado empleadoInsertar) {
		this.empleadoInsertar = empleadoInsertar;
	}

	public EmpleadoDTO getEmpleadoDTOInsertar() {
		return empleadoDTOInsertar;
	}

	public void setEmpleadoDTOInsertar(EmpleadoDTO empleadoDTOInsertar) {
		this.empleadoDTOInsertar = empleadoDTOInsertar;
	}

	public Date getFechaFalta() {
		return fechaFalta;
	}

	public void setFechaFalta(Date fechaFalta) {
		this.fechaFalta = fechaFalta;
	}

	public FaltaListDTO getFaltaBuscar() {
		return faltaBuscar;
	}

	public void setFaltaBuscar(FaltaListDTO faltaBuscar) {
		this.faltaBuscar = faltaBuscar;
	}

	public List<FaltaListDTO> getFaltaList() {
		return faltaList;
	}

	public void setFaltaList(List<FaltaListDTO> faltaList) {
		this.faltaList = faltaList;
	}
	
	
}
