package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "NivelDataManager")
public class NivelDataManager extends BaseDataManager{
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private NivelDTO nivelInstancia;
	private NivelDTO nivelEditar;
	private NivelDTO nivelBuscar;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.nivelInstancia = new NivelDTO();		
		this.nivelEditar = new NivelDTO();
		this.nivelBuscar = new NivelDTO();
			
	}

	public NivelDTO getNivelInstancia() {
		return nivelInstancia;
	}

	public void setNivelInstancia(NivelDTO nivelInstancia) {
		this.nivelInstancia = nivelInstancia;
	}

	public NivelDTO getNivelEditar() {
		return nivelEditar;
	}

	public void setNivelEditar(NivelDTO nivelEditar) {
		this.nivelEditar = nivelEditar;
	}

	public NivelDTO getNivelBuscar() {
		return nivelBuscar;
	}

	public void setNivelBuscar(NivelDTO nivelBuscar) {
		this.nivelBuscar = nivelBuscar;
	}
	
	

}
