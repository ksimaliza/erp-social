package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import ec.edu.uce.erp.ejb.persistence.vo.PermisoVO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "PermisoDataManager")
public class PermisoDataManager extends BaseDataManager{
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PeriodoDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private PermisoVO permisoInstancia;
	private PermisoVO permisoEditar;
	private PermisoVO permisoBuscar;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.permisoInstancia = new PermisoVO();		
		this.permisoEditar = new PermisoVO();
		this.permisoBuscar = new PermisoVO();
			
	}

	public PermisoVO getPermisoInstancia() {
		return permisoInstancia;
	}

	public void setPermisoInstancia(PermisoVO permisoInstancia) {
		this.permisoInstancia = permisoInstancia;
	}

	public PermisoVO getPermisoEditar() {
		return permisoEditar;
	}

	public void setPermisoEditar(PermisoVO permisoEditar) {
		this.permisoEditar = permisoEditar;
	}

	public PermisoVO getPermisoBuscar() {
		return permisoBuscar;
	}

	public void setPermisoBuscar(PermisoVO permisoBuscar) {
		this.permisoBuscar = permisoBuscar;
	}

}