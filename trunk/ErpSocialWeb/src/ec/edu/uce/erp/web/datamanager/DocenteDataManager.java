package ec.edu.uce.erp.web.datamanager;



import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import ec.edu.uce.erp.ejb.persistence.vo.ProfesorVO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;


@SessionScoped
@ManagedBean (name = "docenteDataManager")
public class DocenteDataManager extends BaseDataManager {

	/**
	 * 
	 */
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private ProfesorVO profesorInstancia;
	private ProfesorVO profesorEditar;
	private ProfesorVO profesorBuscar;
	


	public DocenteDataManager() {
		super();
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.profesorInstancia = new ProfesorVO();		
		this.profesorBuscar = new ProfesorVO();
		this.profesorEditar = new ProfesorVO();
			
	}

	public ProfesorVO getProfesorInstancia() {
		return profesorInstancia;
	}

	public void setProfesorInstancia(ProfesorVO profesorInstancia) {
		this.profesorInstancia = profesorInstancia;
	}

	public ProfesorVO getProfesorEditar() {
		return profesorEditar;
	}

	public void setProfesorEditar(ProfesorVO profesorEditar) {
		this.profesorEditar = profesorEditar;
	}

	public ProfesorVO getProfesorBuscar() {
		return profesorBuscar;
	}

	public void setProfesorBuscar(ProfesorVO profesorBuscar) {
		this.profesorBuscar = profesorBuscar;
	}

		
	
}

	

	
