package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "reporteCarnetDataManager")
public class ReporteCarnetDataManager extends BaseDataManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MatriculaVieDTO matriculaVieDTO;
	private List<MatriculaVieDTO> matriculaVieDTOList;
	
	public ReporteCarnetDataManager()
	{
		matriculaVieDTO=new MatriculaVieDTO();
		matriculaVieDTOList=new ArrayList<MatriculaVieDTO>();
	}

	
	public MatriculaVieDTO getMatriculaVieDTO() {
		return matriculaVieDTO;
	}

	public void setMatriculaVieDTO(MatriculaVieDTO matriculaVieDTO) {
		this.matriculaVieDTO = matriculaVieDTO;
	}


	public List<MatriculaVieDTO> getMatriculaVieDTOList() {
		return matriculaVieDTOList;
	}


	public void setMatriculaVieDTOList(List<MatriculaVieDTO> matriculaVieDTOList) {
		this.matriculaVieDTOList = matriculaVieDTOList;
	}
	
	
	
}
