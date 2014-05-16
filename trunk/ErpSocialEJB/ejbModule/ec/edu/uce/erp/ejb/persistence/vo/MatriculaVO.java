package ec.edu.uce.erp.ejb.persistence.vo;


import java.util.ArrayList;
import java.util.List;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;

public class MatriculaVO implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private List<AsinacionListDTO> asignacion;
	private MatriculaDTO matricula;
	
	public MatriculaVO()  {
		asignacion=new ArrayList<AsinacionListDTO>();
		matricula=new MatriculaDTO();
	}

	public List<AsinacionListDTO> getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(List<AsinacionListDTO> asignacion) {
		this.asignacion = asignacion;
	}

	public MatriculaDTO getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculaDTO matricula) {
		this.matricula = matricula;
	}

	
	
}
