package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;
import ec.edu.uce.erp.web.common.controladores.BaseController;

@SessionScoped
@ManagedBean (name = "asinacionDataManager")
public class AsinacionDataManager extends BaseController {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	
	private AsinacionDTO asinacionInsertar;
	private NivelParaleloDTO nivelParaleloInsertar;
	private ProfesorDTO profesorInsertar;
	private MateriaDTO materiaInsertar;	
	private PeriodoDTO periodoInsertar;
	private int nivelParaleloCodigo;
	private int profesorCodigo;
	private int materiaCodigo;
	private int periodoCodigo;
	
	
	private List<AsinacionListDTO> asinacionList;
	

	private AsinacionListDTO asinacionBuscar;
	
	private List<NivelParaleloListDTO> nivelParaleloList;
	
	private List<DocenteListDTO> profesorList;
	
private List<MateriaDTO> materiaList;
	
	private List<PeriodoDTO> periodoList;
	
	
	
public AsinacionDataManager() {
	
		
	}

@PostConstruct
public void inicializarObjetos () {
	slf4jLogger.info("inicializarObjetos");
	asinacionList=new ArrayList<AsinacionListDTO>();
	asinacionInsertar=new AsinacionDTO();
	nivelParaleloInsertar=new NivelParaleloDTO();
	profesorInsertar=new ProfesorDTO();
	materiaInsertar=new MateriaDTO();
	periodoInsertar=new PeriodoDTO();
		asinacionBuscar=new AsinacionListDTO();
		nivelParaleloList= new ArrayList<NivelParaleloListDTO>();
		profesorList= new ArrayList<DocenteListDTO>();
		materiaList= new ArrayList<MateriaDTO>();
		periodoList= new ArrayList<PeriodoDTO>();
}

public AsinacionDTO getAsinacionInsertar() {
	return asinacionInsertar;
}

public void setAsinacionInsertar(AsinacionDTO asinacionInsertar) {
	this.asinacionInsertar = asinacionInsertar;
}

public NivelParaleloDTO getNivelParaleloInsertar() {
	return nivelParaleloInsertar;
}

public void setNivelParaleloInsertar(NivelParaleloDTO nivelParaleloInsertar) {
	this.nivelParaleloInsertar = nivelParaleloInsertar;
}

public ProfesorDTO getProfesorInsertar() {
	return profesorInsertar;
}

public void setProfesorInsertar(ProfesorDTO profesorInsertar) {
	this.profesorInsertar = profesorInsertar;
}

public MateriaDTO getMateriaInsertar() {
	return materiaInsertar;
}

public void setMateriaInsertar(MateriaDTO materiaInsertar) {
	this.materiaInsertar = materiaInsertar;
}

public PeriodoDTO getPeriodoInsertar() {
	return periodoInsertar;
}

public void setPeriodoInsertar(PeriodoDTO periodoInsertar) {
	this.periodoInsertar = periodoInsertar;
}

public List<AsinacionListDTO> getAsinacionList() {
	return asinacionList;
}

public void setAsinacionList(List<AsinacionListDTO> listResultado) {
	this.asinacionList = listResultado;
}

public AsinacionListDTO getAsinacionBuscar() {
	return asinacionBuscar;
}

public void setAsinacionBuscar(AsinacionListDTO asinacionBuscar) {
	this.asinacionBuscar = asinacionBuscar;
}



public List<NivelParaleloListDTO> getNivelParaleloList() {
	return nivelParaleloList;
}

public void setNivelParaleloList(List<NivelParaleloListDTO> nivelParaleloList) {
	this.nivelParaleloList = nivelParaleloList;
}



public List<DocenteListDTO> getProfesorList() {
	return profesorList;
}

public void setProfesorList(List<DocenteListDTO> profesorList) {
	this.profesorList = profesorList;
}

public List<MateriaDTO> getMateriaList() {
	return materiaList;
}

public void setMateriaList(List<MateriaDTO> materiaList) {
	this.materiaList = materiaList;
}

public List<PeriodoDTO> getPeriodoList() {
	return periodoList;
}

public void setPeriodoList(List<PeriodoDTO> periodoList) {
	this.periodoList = periodoList;
}

public int getNivelParaleloCodigo() {
	return nivelParaleloCodigo;
}

public void setNivelParaleloCodigo(int nivelParaleloCodigo) {
	this.nivelParaleloCodigo = nivelParaleloCodigo;
}

public int getProfesorCodigo() {
	return profesorCodigo;
}

public void setProfesorCodigo(int profesorCodigo) {
	this.profesorCodigo = profesorCodigo;
}

public int getMateriaCodigo() {
	return materiaCodigo;
}

public void setMateriaCodigo(int materiaCodigo) {
	this.materiaCodigo = materiaCodigo;
}

public int getPeriodoCodigo() {
	return periodoCodigo;
}

public void setPeriodoCodigo(int periodoCodigo) {
	this.periodoCodigo = periodoCodigo;
}




}
