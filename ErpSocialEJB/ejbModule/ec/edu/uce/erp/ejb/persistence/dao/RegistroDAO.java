package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorasTrabajadasListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.MesRegistroVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.AnioDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.FiltroFechaDTO;

public interface RegistroDAO extends AbstractFacade<RegistroDTO>{

	RegistroDTO getUltimate(EmpleadoDTO empleado) throws SeguridadesException;


	List<EmpleadoAtrasoListDTO> obtenerHoras(
			EmpleadoAtrasoListDTO empleadoAtrasoListDTO, FiltroFechaDTO filtro)
			throws SeguridadesException;


	List<MesRegistroVieDTO> getMonth() throws SeguridadesException;


	List<AnioDTO> getYear() throws SeguridadesException;


	List<HorasTrabajadasListDTO> getByAndHoras(HorasTrabajadasListDTO objetoDTO)
			throws SeguridadesException;


	List<EmpleadoAtrasoListDTO> getByAnd(EmpleadoAtrasoListDTO objetoDTO)
			throws SeguridadesException;


	List<EmpleadoAtrasoListDTO> getDistinctByAnd(EmpleadoAtrasoListDTO objetoDTO)
			throws SeguridadesException;

}
