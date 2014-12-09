package ec.edu.uce.erp.ejb.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.CatalogoAsistenciaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaNoLaboralDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioEmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorasTrabajadasListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroCatalogoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.AnioDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.FiltroFechaDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.TiempoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EmpleadoVO;
import ec.edu.uce.erp.ejb.persistence.vo.FaltaVO;
import ec.edu.uce.erp.ejb.persistence.vo.PermisoVO;
import ec.edu.uce.erp.ejb.persistence.vo.RegistroAsistenciaVO;

@Local
public interface ServicioAsistencia{

	DiaDTO createOrUpdateDia(DiaDTO diaDTO) throws SeguridadesException;

	void deleteDia(DiaDTO diaDTO) throws SeguridadesException;

	EmpleadoDTO createOrUpdateEmpleado(EmpleadoVO empleadoVO)
			throws SeguridadesException;

	EmpleadoDTO readEmpleadoByCredentials(EmpleadoDTO empleado)
			throws SeguridadesException;

	void deleteRegistro(RegistroDTO registroDTO) throws SeguridadesException;

	RegistroDTO createOrUpdateRegistroAsistencia(
			RegistroAsistenciaVO registroAsistencia)
			throws SeguridadesException;

	void deleteEmpleado(EmpleadoDTO empleadoDTO) throws SeguridadesException;

	TipoDTO createOrUpdateTipo(TipoDTO tipoDTO) throws SeguridadesException;

	void deleteHorarioEmpleado(HorarioEmpleadoDTO horarioempleadoDTO)
			throws SeguridadesException;

	void deleteHorario(HorarioDTO horarioDTO) throws SeguridadesException;

	List<PermisoListDTO> readPermiso(PermisoListDTO permiso)
			throws SeguridadesException;

	HorarioDTO createOrUpdateHorario(HorarioDTO horarioDTO)
			throws SeguridadesException;

	ParametroDTO createOrUpdateParametro(ParametroDTO parametroDTO)
			throws SeguridadesException;

	PermisoDTO createOrUpdateHorarioPermiso(PermisoDTO permisoDTO)
			throws SeguridadesException;

	void deleteTipo(TipoDTO tipoDTO) throws SeguridadesException;

	TipoDTO obtenerTipoPorId(Integer id) throws SeguridadesException;

	List<ParametroDTO> buscarParametro(ParametroDTO parametro)
			throws SeguridadesException;

	ParametroDTO actualizarParametroAsistencia(ParametroDTO parametro)
			throws SeguridadesException;

	ParametroDTO obtenerParametroPorId(Integer id) throws SeguridadesException;

	List<AnioDTO> readAnioActual() throws SeguridadesException;

	void createDiaNoLaboral(DiaNoLaboralDTO diaNoLaboral)
			throws SeguridadesException;

	List<TiempoDTO> readHour() throws SeguridadesException;

	List<TiempoDTO> readMinute() throws SeguridadesException;

	List<EmpleadoListDTO> readEmpleado(EmpleadoListDTO empleado)
			throws SeguridadesException;

	FaltaDTO createOrUpdateFalta(FaltaVO faltaVO) throws SeguridadesException;

	void deleteFalta(FaltaDTO faltaDTO) throws SeguridadesException;

	PermisoDTO createOrUpdatePermiso(PermisoVO permisoVO)
			throws SeguridadesException;

	HorarioEmpleadoDTO createOrUpdateHorarioEmpleado(
			HorarioEmpleadoDTO horarioempleadoDTO) throws SeguridadesException;

	void deletePermiso(PermisoDTO permisoDTO) throws SeguridadesException;

	List<FaltaListDTO> readFalta(FaltaListDTO falta)
			throws SeguridadesException;

	List<TipoDTO> buscarTipo(TipoDTO tipo) throws SeguridadesException;

	List<TiempoDTO> readDay() throws SeguridadesException;

	List<DiaDTO> readDiaAll() throws SeguridadesException;

	List<TipoDTO> readAllTipo() throws SeguridadesException;

	EmpleadoVO obtenerEmpleadoPorId(EmpleadoListDTO empleadoListDTO)
			throws SeguridadesException;


	FaltaDTO obtenerFaltaPorId(FaltaListDTO faltaListDTO)
			throws SeguridadesException;

	List<FaltaListDTO> readFalta2(FaltaListDTO falta)
			throws SeguridadesException;

	List<EmpleadoAtrasoListDTO> readAtraso(EmpleadoAtrasoListDTO atraso)
			throws SeguridadesException;

	List<EmpleadoAtrasoListDTO> buscarHoras(
			EmpleadoAtrasoListDTO empleadoAtrasoListDTO, FiltroFechaDTO filtro)
			throws SeguridadesException;

	List<HorasTrabajadasListDTO> readHoras(HorasTrabajadasListDTO horas)
			throws SeguridadesException;

	List<FaltaListDTO> readFaltaReport(FaltaListDTO falta)
			throws SeguridadesException;

	List<EmpleadoAtrasoListDTO> readAtrasoReport(EmpleadoAtrasoListDTO atraso)
			throws SeguridadesException;

	List<CatalogoAsistenciaDTO> readAll() throws SeguridadesException;

	List<ParametroCatalogoDTO> read(ParametroCatalogoDTO parametroCatalogoDTO)
			throws SeguridadesException;

	ParametroCatalogoDTO getParametroCatalogById(int id)
			throws SeguridadesException;

	void createDiaNoLaboralSabadoDomingo(int year, int entidad)
			throws SeguridadesException;

	List<DiaNoLaboralDTO> readDiaNoLaboral(int year, int entidad)
			throws SeguridadesException;
	

}
	


