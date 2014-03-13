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

@Local
public interface ServicioAsistencia {

	DiaDTO createOrUpdateDia(DiaDTO diaDTO) throws SeguridadesException;

	void deleteDia(DiaDTO diaDTO) throws SeguridadesException;

	EmpleadoDTO createOrUpdateEmpleado(EmpleadoDTO empleadoDTO)
			throws SeguridadesException;

	void deleteEmpleado(EmpleadoDTO empleadoDTO) throws SeguridadesException;

	FaltaDTO createOrUpdateFalta(FaltaDTO faltaDTO) throws SeguridadesException;

	void deleteFalta(FaltaDTO faltaDTO) throws SeguridadesException;

	HorarioDTO createOrUpdateHorario(HorarioDTO horarioDTO)
			throws SeguridadesException;

	void deleteHorario(HorarioDTO horarioDTO) throws SeguridadesException;

	void deleteHorarioEmpleado(HorarioEmpleadoDTO horarioempleadoDTO)
			throws SeguridadesException;

	HorarioEmpleadoDTO createOrUpdateHorarioEmpleado(
			HorarioEmpleadoDTO horarioempleadoDTO) throws SeguridadesException;

	PermisoDTO createOrUpdateHorarioPermiso(PermisoDTO permisoDTO)
			throws SeguridadesException;

	PermisoDTO createOrUpdatePermiso(PermisoDTO permisoDTO)
			throws SeguridadesException;

	void deletePermiso(PermisoDTO permisoDTO) throws SeguridadesException;

	RegistroDTO createOrUpdateRegistro(RegistroDTO registroDTO)
			throws SeguridadesException;

	void deleteRegistro(RegistroDTO registroDTO) throws SeguridadesException;

	TipoDTO createOrUpdateTipo(TipoDTO tipoDTO) throws SeguridadesException;

	void deleteTipo(TipoDTO tipoDTO) throws SeguridadesException;



}
