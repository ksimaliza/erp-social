package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;

public interface PeriodoDAO extends AbstractFacade<PeriodoDTO>{

	List<PeriodoDTO> obtenerPeriodo(PeriodoDTO periodo)
			throws SeguridadesException;
	
	public Integer obtenerIdUltimoPeriodo();
	public Integer obtenerIdUltimoPeriodo(Integer idEmpresa);
	

}
