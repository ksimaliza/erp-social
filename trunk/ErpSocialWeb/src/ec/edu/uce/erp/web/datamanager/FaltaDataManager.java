package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.vo.FaltaVO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "faltaDataManager")
public class FaltaDataManager extends BaseDataManager {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private FaltaVO faltaInstancia;
	private FaltaVO faltaEditar;
	private FaltaVO faltaBuscar;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.faltaInstancia = new FaltaVO();		
		this.faltaEditar = new FaltaVO();
		this.faltaBuscar = new FaltaVO();
			
	}

	public FaltaVO getFaltaInstancia() {
		return faltaInstancia;
	}

	public void setFaltaInstancia(FaltaVO faltaInstancia) {
		this.faltaInstancia = faltaInstancia;
	}

	public FaltaVO getFaltaEditar() {
		return faltaEditar;
	}

	public void setFaltaEditar(FaltaVO faltaEditar) {
		this.faltaEditar = faltaEditar;
	}

	public FaltaVO getFaltaBuscar() {
		return faltaBuscar;
	}

	public void setFaltaBuscar(FaltaVO faltaBuscar) {
		this.faltaBuscar = faltaBuscar;
	}
	
	
}
