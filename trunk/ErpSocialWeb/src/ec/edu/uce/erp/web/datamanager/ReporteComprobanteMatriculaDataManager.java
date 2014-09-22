package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "reporteComprobanteMatriculaDataManager")
public class ReporteComprobanteMatriculaDataManager extends BaseDataManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int paraleloCodigo;
	private int nivelCodigo;
	
	private List<NivelParaleloDTO> nivelParaleloList;
	private List<NivelDTO> nivelList;
	
	private MatriculaVieDTO matriculaVieDTO;
	
	private List<MatriculaVieDTO> matriculaVieList;
	
	public ReporteComprobanteMatriculaDataManager() {
		nivelParaleloList=new ArrayList<NivelParaleloDTO>();
		nivelList=new ArrayList<NivelDTO>();
		matriculaVieDTO=new MatriculaVieDTO();
		
		matriculaVieList=new ArrayList<MatriculaVieDTO>();
	}


	public int getParaleloCodigo() {
		return paraleloCodigo;
	}


	public void setParaleloCodigo(int paraleloCodigo) {
		this.paraleloCodigo = paraleloCodigo;
	}


	public int getNivelCodigo() {
		return nivelCodigo;
	}


	public void setNivelCodigo(int nivelCodigo) {
		this.nivelCodigo = nivelCodigo;
	}


	public List<NivelParaleloDTO> getNivelParaleloList() {
		return nivelParaleloList;
	}


	public void setNivelParaleloList(List<NivelParaleloDTO> nivelParaleloList) {
		this.nivelParaleloList = nivelParaleloList;
	}


	public List<NivelDTO> getNivelList() {
		return nivelList;
	}


	public void setNivelList(List<NivelDTO> nivelList) {
		this.nivelList = nivelList;
	}


	public MatriculaVieDTO getMatriculaVieDTO() {
		return matriculaVieDTO;
	}


	public void setMatriculaVieDTO(MatriculaVieDTO matriculaVieDTO) {
		this.matriculaVieDTO = matriculaVieDTO;
	}


	public List<MatriculaVieDTO> getMatriculaVieList() {
		return matriculaVieList;
	}


	public void setMatriculaVieList(List<MatriculaVieDTO> matriculaVieList) {
		this.matriculaVieList = matriculaVieList;
	}
	
	
	
}
