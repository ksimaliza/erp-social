package ec.edu.uce.erp.web.datamanager;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.ejb.persistence.vo.MatriculaVO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@ViewScoped
@ManagedBean (name = "matriculaDataManager")
public class MatriculaDataManager extends BaseDataManager {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private int estudianteCodigo;
	private int paraleloCodigo;
	private int nivelCodigo;
	
	private EstudianteVO estudianteInsertar;
	private MatriculaVO detalleMInsertar;
	private AsinacionDTO asignacionInsertar;
	private MatriculaDTO matriculaInsertar;
	
	
	
	private List<AsinacionListDTO> asinacionList;
	
	private List<NivelParaleloDTO> nivelParaleloList;
	private List<NivelDTO> nivelList;
	
	private List<EstudianteListDTO> estudianteList;
	
	private Date fechaInsertar;
	
	
	
	public MatriculaDataManager() {
	
		
	}

	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		nivelParaleloList=new ArrayList<NivelParaleloDTO>();
		nivelList=new ArrayList<NivelDTO>();
		estudianteList=new ArrayList<EstudianteListDTO>();
		fechaInsertar=new Date();
		estudianteInsertar=new EstudianteVO();
		detalleMInsertar=new MatriculaVO();
		asignacionInsertar=new AsinacionDTO();
		matriculaInsertar=new MatriculaDTO();
	} 

	public int getEstudianteCodigo() {
		return estudianteCodigo;
	}

	public void setEstudianteCodigo(int estudianteCodigo) {
		this.estudianteCodigo = estudianteCodigo;
	}

	public int getParaleloCodigo() {
		return paraleloCodigo;
	}

	public void setParaleloCodigo(int nivelParaleloCodigo) {
		this.paraleloCodigo = nivelParaleloCodigo;
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

	public List<EstudianteListDTO> getEstudianteList() {
		return estudianteList;
	}

	public void setEstudianteList(List<EstudianteListDTO> estudianteList) {
		this.estudianteList = estudianteList;
	}

	public Date getFechaInsertar() {
		return fechaInsertar;
	}

	public void setFechaInsertar(Date fechaInsertar) {
		this.fechaInsertar = fechaInsertar;
	}

	public List<AsinacionListDTO> getAsinacionList() {
		return asinacionList;
	}

	public void setAsinacionList(List<AsinacionListDTO> asinacionList) {
		this.asinacionList = asinacionList;
	}

	public EstudianteVO getEstudianteInsertar() {
		return estudianteInsertar;
	}

	public void setEstudianteInsertar(EstudianteVO estudianteInsertar) {
		this.estudianteInsertar = estudianteInsertar;
	}

	public MatriculaVO getDetalleMInsertar() {
		return detalleMInsertar;
	}

	public void setDetalleMInsertar(MatriculaVO detalleMInsertar) {
		this.detalleMInsertar = detalleMInsertar;
	}

	public AsinacionDTO getAsignacionInsertar() {
		return asignacionInsertar;
	}

	public void setAsignacionInsertar(AsinacionDTO asignacionInsertar) {
		this.asignacionInsertar = asignacionInsertar;
	}

	public MatriculaDTO getMatriculaInsertar() {
		return matriculaInsertar;
	}

	public void setMatriculaInsertar(MatriculaDTO matriculaInsertar) {
		this.matriculaInsertar = matriculaInsertar;
	}
	
	

}
