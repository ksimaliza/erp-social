package ec.edu.uce.erp.ejb.servicio;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioEmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EmpleadoVO;
import ec.edu.uce.erp.ejb.persistence.vo.FaltaVO;
import ec.edu.uce.erp.ejb.persistence.vo.PermisoVO;

@Local
public interface ServicioAsistencia {

	DiaDTO createOrUpdateDia(DiaDTO diaDTO) throws SeguridadesException;

	void deleteDia(DiaDTO diaDTO) throws SeguridadesException;

	void deleteEmpleado(EmpleadoDTO empleadoDTO) throws SeguridadesException;

	void deleteFalta(FaltaDTO faltaDTO) throws SeguridadesException;

	HorarioDTO createOrUpdateHorario(HorarioDTO horarioDTO)
			throws SeguridadesException;

	void deleteHorario(HorarioDTO horarioDTO) throws SeguridadesException;

	void deleteHorarioEmpleado(HorarioEmpleadoDTO horarioempleadoDTO)
			throws SeguridadesException;

	HorarioEmpleadoDTO createOrUpdateHorarioEmpleado(
			HorarioEmpleadoDTO horarioempleadoDTO) throws SeguridadesException;

	void deletePermiso(PermisoDTO permisoDTO) throws SeguridadesException;

	RegistroDTO createOrUpdateRegistro(RegistroDTO registroDTO)
			throws SeguridadesException;

	void deleteRegistro(RegistroDTO registroDTO) throws SeguridadesException;

	TipoDTO createOrUpdateTipo(TipoDTO tipoDTO) throws SeguridadesException;

	void deleteTipo(TipoDTO tipoDTO) throws SeguridadesException;

	PermisoDTO createOrUpdateHorarioPermiso(PermisoDTO permisoDTO)
			throws SeguridadesException;

	
	FaltaDTO createOrUpdateFalta(FaltaVO faltaVO) throws SeguridadesException;

	PermisoDTO createOrUpdatePermiso(PermisoVO permisoVO)
			throws SeguridadesException;



	EmpleadoDTO createOrUpdateEmpleado(EmpleadoVO empleadoVO)
			throws SeguridadesException;



}
