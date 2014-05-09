package ec.edu.uce.erp.web.datamanager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "paraleloDataManager")
public class ParaleloDataManager extends BaseDataManager{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private ParaleloDTO paraleloInstancia;
	private ParaleloDTO paraleloEditar;
	private ParaleloDTO paraleloBuscar;
	private List<ParaleloDTO> paraleloDTOs;
	private int paraleloCod;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.paraleloInstancia = new ParaleloDTO();		
		this.paraleloEditar = new ParaleloDTO();
		this.paraleloBuscar = new ParaleloDTO();
			
	}

	public ParaleloDTO getParaleloInstancia() {
		return paraleloInstancia;
	}

	public void setParaleloInstancia(ParaleloDTO paraleloInstancia) {
		this.paraleloInstancia = paraleloInstancia;
	}

	public ParaleloDTO getParaleloEditar() {
		return paraleloEditar;
	}

	public void setParaleloEditar(ParaleloDTO paraleloEditar) {
		this.paraleloEditar = paraleloEditar;
	}

	public ParaleloDTO getParaleloBuscar() {
		return paraleloBuscar;
	}

	public void setParaleloBuscar(ParaleloDTO paraleloBuscar) {
		this.paraleloBuscar = paraleloBuscar;
	}

	public List<ParaleloDTO> getParaleloDTOs() {
		return paraleloDTOs;
	}

	public void setParaleloDTOs(List<ParaleloDTO> paraleloDTOs) {
		this.paraleloDTOs = paraleloDTOs;
	}

	public int getParaleloCod() {
		return paraleloCod;
	}

	public void setParaleloCod(int paraleloCod) {
		this.paraleloCod = paraleloCod;
	}
	
	
	
}
