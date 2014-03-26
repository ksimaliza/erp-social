package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "periodoDataManager")
public class PeriodoDataManager extends BaseDataManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PeriodoDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private PeriodoDTO periodoInstancia;
	private PeriodoDTO periodoEditar;
	private PeriodoDTO periodoBuscar;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.periodoInstancia = new PeriodoDTO();		
		this.periodoEditar = new PeriodoDTO();
		this.periodoBuscar = new PeriodoDTO();
			
	}

	public PeriodoDTO getPeriodoInstancia() {
		return periodoInstancia;
	}

	public void setPeriodoInstancia(PeriodoDTO periodoInstancia) {
		this.periodoInstancia = periodoInstancia;
	}

	public PeriodoDTO getPeriodoEditar() {
		return periodoEditar;
	}

	public void setPeriodoEditar(PeriodoDTO periodoEditar) {
		this.periodoEditar = periodoEditar;
	}

	public PeriodoDTO getPeriodoBuscar() {
		return periodoBuscar;
	}

	public void setPeriodoBuscar(PeriodoDTO periodoBuscar) {
		this.periodoBuscar = periodoBuscar;
	}
	
	
}
