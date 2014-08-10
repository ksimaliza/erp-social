package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "parametroAsistenciaDataManager")

public class ParametroAsistenciaDataManager extends BaseDataManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private ParametroDTO parametroActualizar;
	private ParametroDTO parametroBuscar;
	private List<ParametroDTO> listParametro;
	
	private ParametroDTO parametroInsertar;
	
	
	public ParametroAsistenciaDataManager() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		parametroBuscar=new ParametroDTO();
		parametroActualizar=new ParametroDTO();
		listParametro = new ArrayList<ParametroDTO>();
		parametroInsertar= new ParametroDTO();
		
	}

	public ParametroDTO getParametroActualizar() {
		return parametroActualizar;
	}

	public ParametroDTO getParametroBuscar() {
		return parametroBuscar;
	}

	public void setParametroBuscar(ParametroDTO parametroBuscar) {
		this.parametroBuscar = parametroBuscar;
	}

	public void setParametroActualizar(ParametroDTO parametroActualizar) {
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


	
	
	
	
}
