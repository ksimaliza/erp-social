package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "parametroAsistenciaDataManager")
public class ParametroAsistenciaDataManager extends BaseDataManager {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private ParametroDTO parametroActualizar;
	private ParametroDTO parametroBuscar;
	private List<ParametroDTO> parametroList;
	
	private ParametroDTO parametroInsertar;
	
	private List<EmpleadoListDTO> empleadoList;
	
	private int empleadoCodigo;
	private int empleadoCodigoBuscar;
	
	private int catalogoCodigo;
	
	
	public ParametroAsistenciaDataManager() {
		empleadoList=new ArrayList<EmpleadoListDTO>();
		
		parametroList=new ArrayList<ParametroDTO>();
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		parametroBuscar=new ParametroDTO();
		parametroActualizar=new ParametroDTO();

		parametroInsertar= new ParametroDTO();
		
	}

	public ParametroDTO getParametroBuscar() {
		return parametroBuscar;
	}

	public void setParametroBuscar(ParametroDTO parametroBuscar) {
		this.parametroBuscar = parametroBuscar;
	}

	public ParametroDTO getParametroInsertar() {
		return parametroInsertar;
	}

	public void setParametroInsertar(ParametroDTO parametroInsertar) {
		this.parametroInsertar = parametroInsertar;
	}

	public List<EmpleadoListDTO> getEmpleadoList() {
		return empleadoList;
	}

	public void setEmpleadoList(List<EmpleadoListDTO> empleadoList) {
		this.empleadoList = empleadoList;
	}

	public int getEmpleadoCodigo() {
		return empleadoCodigo;
	}

	public void setEmpleadoCodigo(int empleadoCodigo) {
		this.empleadoCodigo = empleadoCodigo;
	}

	public int getEmpleadoCodigoBuscar() {
		return empleadoCodigoBuscar;
	}

	public void setEmpleadoCodigoBuscar(int empleadoCodigoBuscar) {
		this.empleadoCodigoBuscar = empleadoCodigoBuscar;
	}

	public int getCatalogoCodigo() {
		return catalogoCodigo;
	}

	public void setCatalogoCodigo(int catalogoCodigo) {
		this.catalogoCodigo = catalogoCodigo;
	}

	public ParametroDTO getParametroActualizar() {
		return parametroActualizar;
	}

	public void setParametroActualizar(ParametroDTO parametroActualizar) {
		this.parametroActualizar = parametroActualizar;
	}

	public List<ParametroDTO> getParametroList() {
		return parametroList;
	}

	public void setParametroList(List<ParametroDTO> parametroList) {
		this.parametroList = parametroList;
	}

	
}
