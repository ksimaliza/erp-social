package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteAsignadoVieDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;


@SessionScoped
@ManagedBean (name = "reporteDocenteDataManager")
public class ReporteDocenteDataManager extends BaseDataManager{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private DocenteAsignadoVieDTO docenteAsignadoVieDTO;
	private List<DocenteAsignadoVieDTO> docenteAsignadoVieDTOList;
	
	
	public ReporteDocenteDataManager() {
		docenteAsignadoVieDTO=new DocenteAsignadoVieDTO();
		docenteAsignadoVieDTOList=new ArrayList<DocenteAsignadoVieDTO>();
	}


	public DocenteAsignadoVieDTO getDocenteAsignadoVieDTO() {
		return docenteAsignadoVieDTO;
	}


	public void setDocenteAsignadoVieDTO(DocenteAsignadoVieDTO docenteAsignadoVieDTO) {
		this.docenteAsignadoVieDTO = docenteAsignadoVieDTO;
	}


	public List<DocenteAsignadoVieDTO> getDocenteAsignadoVieDTOList() {
		return docenteAsignadoVieDTOList;
	}


	public void setDocenteAsignadoVieDTOList(
			List<DocenteAsignadoVieDTO> docenteAsignadoVieDTOList) {
		this.docenteAsignadoVieDTOList = docenteAsignadoVieDTOList;
	}

	
	
}
