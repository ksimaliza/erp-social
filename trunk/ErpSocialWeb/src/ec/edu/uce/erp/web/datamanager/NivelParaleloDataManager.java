package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.web.common.controladores.BaseController;


@SessionScoped
@ManagedBean (name = "nivelParaleloDataManager")
public class NivelParaleloDataManager extends BaseController{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
		
	private List<NivelParaleloDTO> nivelParaleloList;
	private NivelParaleloDTO nivelParaleloInsertar;
	
	private int nivelCodigo;
	private int paraleloCodigo;

	private NivelParaleloDTO nivelParaleloBuscar;
	
	private List<NivelDTO> nivelList;
	
	private List<ParaleloDTO> paraleloList;

	
	
	
	public NivelParaleloDataManager() {
		
	}

	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		
		nivelParaleloList=new ArrayList<NivelParaleloDTO>();
		nivelParaleloBuscar=new NivelParaleloDTO();
		paraleloList=new ArrayList<ParaleloDTO>();
		nivelList= new ArrayList<NivelDTO>();		
		nivelParaleloInsertar=new NivelParaleloDTO();
	}

	
	public List<NivelParaleloDTO> getNivelParaleloList() {
		return nivelParaleloList;
	}
	
	public void setNivelParaleloList(List<NivelParaleloDTO> nivelParaleloList) {
		this.nivelParaleloList = nivelParaleloList;
	}
	
	public NivelParaleloDTO getNivelParaleloBuscar() {
		return nivelParaleloBuscar;
	}
	
	public void setNivelParaleloBuscar(NivelParaleloDTO nivelParaleloBuscar) {
		this.nivelParaleloBuscar = nivelParaleloBuscar;
	}
	
	public List<NivelDTO> getNivelList() {
		return nivelList;
	}
	
	public void setNivelList(List<NivelDTO> nivelList) {
		this.nivelList = nivelList;
	}
	
	public List<ParaleloDTO> getParaleloList() {
		return paraleloList;
	}
	
	public void setParaleloList(List<ParaleloDTO> paraleloList) {
		this.paraleloList = paraleloList;
	}
	
	public int getNivelCodigo() {
		return nivelCodigo;
	}
	
	public void setNivelCodigo(int nivelCodigo) {
		this.nivelCodigo = nivelCodigo;
	}

	public int getParaleloCodigo() {
		return paraleloCodigo;
	}

	public void setParaleloCodigo(int paraleloCodigo) {
		this.paraleloCodigo = paraleloCodigo;
	}

	public NivelParaleloDTO getNivelParaleloInsertar() {
		return nivelParaleloInsertar;
	}

	public void setNivelParaleloInsertar(NivelParaleloDTO nivelParaleloInsertar) {
		this.nivelParaleloInsertar = nivelParaleloInsertar;
	}

}
