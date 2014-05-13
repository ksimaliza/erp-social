package ec.edu.uce.erp.web.datamanager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.web.common.controladores.BaseController;

@SessionScoped
@ManagedBean (name = "matriculaDataManager")
public class MatriculaDataManager extends BaseController {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private int estudianteCodigo;
	private int nivelCodigo;
	private int paraleloCodigo;
	
	
	private List<NivelDTO> nivelList;
	
	private List<EstudianteListDTO> estudianteList;
	
	private List<ParaleloDTO> paraleloList;
	private Date fechaInsertar;
	
	
	
public MatriculaDataManager() {
	
		
	}

@PostConstruct
public void inicializarObjetos () {
	slf4jLogger.info("inicializarObjetos");
	nivelList=new ArrayList<NivelDTO>();
	estudianteList=new ArrayList<EstudianteListDTO>();
	paraleloList=new ArrayList<ParaleloDTO>();
	
	
}

public Date getFechaInsertar() {
	return fechaInsertar;
}

public void setFechaInsertar(Date fechaInsertar) {
	this.fechaInsertar = fechaInsertar;
}

public int getEstudianteCodigo() {
	return estudianteCodigo;
}

public void setEstudianteCodigo(int estudianteCodigo) {
	this.estudianteCodigo = estudianteCodigo;
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

public List<ParaleloDTO> getParaleloList() {
	return paraleloList;
}

public void setParaleloList(List<ParaleloDTO> paraleloList) {
	this.paraleloList = paraleloList;
}


}
