package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.CatalogoAsistenciaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroCatalogoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "parametroAsistenciaDataManager")
public class ParametroAsistenciaDataManager extends BaseDataManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private ParametroCatalogoDTO parametroActualizar;
	private ParametroDTO parametroBuscar;
	private List<ParametroDTO> listParametro;
	
	private ParametroDTO parametroInsertar;
	
	private List<EmpleadoListDTO> empleadoList;
	
	private List<CatalogoAsistenciaDTO> catalogoList;
	
	private int empleadoCodigo;
	private int empleadoCodigoBuscar;
	
	private int catalogoCodigo;
	
	private List<ParametroCatalogoDTO> parametroCatalogoList;
	
	public ParametroAsistenciaDataManager() {
		empleadoList=new ArrayList<EmpleadoListDTO>();
		catalogoList=new ArrayList<CatalogoAsistenciaDTO>();
		parametroCatalogoList=new ArrayList<ParametroCatalogoDTO>();
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		parametroBuscar=new ParametroDTO();
		parametroActualizar=new ParametroCatalogoDTO();
		listParametro = new ArrayList<ParametroDTO>();
		parametroInsertar= new ParametroDTO();
		
	}

	public ParametroCatalogoDTO getParametroActualizar() {
		return parametroActualizar;
	}

	public ParametroDTO getParametroBuscar() {
		return parametroBuscar;
	}

	public void setParametroBuscar(ParametroDTO parametroBuscar) {
		this.parametroBuscar = parametroBuscar;
	}

	public void setParametroActualizar(ParametroCatalogoDTO parametroActualizar) {
		this.parametroActualizar = parametroActualizar;
	}

	public List<ParametroDTO> getListParametro() {
		return listParametro;
	}

	public void setListParametro(List<ParametroDTO> listParametro) {
		this.listParametro = listParametro;
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

	public List<CatalogoAsistenciaDTO> getCatalogoList() {
		return catalogoList;
	}

	public void setCatalogoList(List<CatalogoAsistenciaDTO> catalogoList) {
		this.catalogoList = catalogoList;
	}

	public int getEmpleadoCodigo() {
		return empleadoCodigo;
	}

	public void setEmpleadoCodigo(int empleadoCodigo) {
		this.empleadoCodigo = empleadoCodigo;
	}

	public List<ParametroCatalogoDTO> getParametroCatalogoList() {
		return parametroCatalogoList;
	}

	public void setParametroCatalogoList(
			List<ParametroCatalogoDTO> parametroCatalogoList) {
		this.parametroCatalogoList = parametroCatalogoList;
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


	
	
	
	
}
