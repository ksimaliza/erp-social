package ec.edu.uce.erp.ejb.servicio.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.AsistenciaFactoryDAO;
import ec.edu.uce.erp.ejb.dao.factory.FactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioEmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EmpleadoVO;
import ec.edu.uce.erp.ejb.persistence.vo.FaltaVO;
import ec.edu.uce.erp.ejb.persistence.vo.PermisoVO;
import ec.edu.uce.erp.ejb.persistence.vo.RegistroAsistenciaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;

@Stateless
public class ServicioAsistenciaImpl implements ServicioAsistencia{
	
	@EJB
	private AsistenciaFactoryDAO asistenciaFactoryDAO;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioAsistenciaImpl.class);
	
	@EJB
	private FactoryDAO factoryDAO;
	
	/*Dia*/
	@Override
	public DiaDTO createOrUpdateDia(DiaDTO diaDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateDia");
		try {
		if(diaDTO.getDiaCodigo()!=null)
			return asistenciaFactoryDAO.getDiaDAOImpl().update(diaDTO);
		else
			return asistenciaFactoryDAO.getDiaDAOImpl().create(diaDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateDia {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteDia(DiaDTO diaDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteDia");
		try {
		if(diaDTO.getDiaCodigo()!=null)
			asistenciaFactoryDAO.getDiaDAOImpl().remove(diaDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteDia {}", e.toString());
			throw new SeguridadesException(e);
		}
	}
	
	/*Empleado*/
	@Override
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public EmpleadoDTO createOrUpdateEmpleado(EmpleadoVO empleadoVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateEmpleado");
		Persona personanueva;
		Empleado empleadonuevo;
		try {
			if(empleadoVO.getEmpleado().getEmpCodigo()!=null)
			{
				factoryDAO.getPersonaDAOImpl().update(empleadoVO.getPersona());
				factoryDAO.getEmpleadoeDAOImpl().update(empleadoVO.getEmpleado());
				return asistenciaFactoryDAO.getEmpleadoDAOImpl().update(empleadoVO.getEmpleadoDTO());
			}
			else
			{
				//empleadoVO.getPersona().setSegtUsuario(new Usuario());
				personanueva=factoryDAO.getPersonaDAOImpl().create(empleadoVO.getPersona());
				empleadoVO.getEmpleado().setPersonaTbl(personanueva);
				empleadonuevo=factoryDAO.getEmpleadoeDAOImpl().create(empleadoVO.getEmpleado());
				empleadoVO.getEmpleadoDTO().setAemEmpleado(empleadonuevo.getEmpPk());
				return asistenciaFactoryDAO.getEmpleadoDAOImpl().create(empleadoVO.getEmpleadoDTO());
			} 
		}
		catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateEmpleado {}", e.toString());
			throw new SeguridadesException(e);
		}
	}
	
	@Override
	public EmpleadoDTO readEmpleadoByCredentials(EmpleadoDTO empleado) throws SeguridadesException{
		slf4jLogger.info("readEmpleadoByCredentials");
		EmpleadoDTO resultado = null;
		try {
			resultado = asistenciaFactoryDAO.getEmpleadoDAOImpl().findByCredentials(empleado);
		} catch (Exception e) {
			slf4jLogger.info("Error al readEmpleadoByCredentials {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener empleado de la base de datos");
		}
		return resultado;	
	}
	
	@Override
	public void deleteEmpleado(EmpleadoDTO empleadoDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteEmpleado");
		try {
		if(empleadoDTO.getAemCodigo()!=null)
			asistenciaFactoryDAO.getEmpleadoDAOImpl().remove(empleadoDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteEmpleado {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	@Override
	public List<EmpleadoListDTO> readEmpleado(EmpleadoListDTO empleado) throws SeguridadesException {
		slf4jLogger.info("readEmpleado");
		List<EmpleadoListDTO> listResultado = null;
		try {
			listResultado = asistenciaFactoryDAO.getEmpleadoDAOImpl().findAll(empleado);
		} catch (Exception e) {
			slf4jLogger.info("Error al readEmpleado {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener empleado de la base de datos");
		}
		return listResultado;
	}
	
	
	/*Falta*/
	@Override
	public FaltaDTO createOrUpdateFalta(FaltaVO faltaVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateFalta");
		EmpleadoDTO empleado;
		
		try {
			if(faltaVO.getFalta().getFalCodigo()!=null){
				empleado = asistenciaFactoryDAO.getEmpleadoDAOImpl().find(faltaVO.getEmpleado().getAemCodigo());
				faltaVO.getFalta().setAsiEmpleado(empleado);
				return asistenciaFactoryDAO.getFaltaDAOImpl().update(faltaVO.getFalta());
			}
			else {
				empleado = asistenciaFactoryDAO.getEmpleadoDAOImpl().find(faltaVO.getEmpleado().getAemCodigo());
				faltaVO.getFalta().setAsiEmpleado(empleado);
				return asistenciaFactoryDAO.getFaltaDAOImpl().create(faltaVO.getFalta());
			} 
		}		
		catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateFalta {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteFalta(FaltaDTO faltaDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteEmpleado");
		try {
		if(faltaDTO.getFalCodigo()!=null)
			asistenciaFactoryDAO.getFaltaDAOImpl().remove(faltaDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteEmpleado {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public List<FaltaListDTO> readFalta(FaltaListDTO falta) throws SeguridadesException {
		slf4jLogger.info("readFalta");
		List<FaltaListDTO> listResultado = null;
		try {
			listResultado = asistenciaFactoryDAO.getFaltaDAOImpl().findAll(falta);
		} catch (Exception e) {
			slf4jLogger.info("Error al readFalta {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener falta de la base de datos");
		}
		return listResultado;
	}


	/*Permiso*/
	@Override
	public PermisoDTO createOrUpdatePermiso(PermisoVO permisoVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdatePermiso");
		EmpleadoDTO empleado;
		try {
			if(permisoVO.getPermiso().getPerCodigo()!=null){
				empleado = asistenciaFactoryDAO.getEmpleadoDAOImpl().find(permisoVO.getEmpleado().getAemCodigo());
				permisoVO.getPermiso().setAsiEmpleado(empleado);
				return asistenciaFactoryDAO.getPermisoDAOImpl().update(permisoVO.getPermiso());
			}
			else{
				empleado = asistenciaFactoryDAO.getEmpleadoDAOImpl().find(permisoVO.getEmpleado().getAemCodigo());
				permisoVO.getPermiso().setAsiEmpleado(empleado);
				return asistenciaFactoryDAO.getPermisoDAOImpl().create(permisoVO.getPermiso());
			}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdatePermiso {}", e.toString());
			throw new SeguridadesException(e);
		}		
	}
	
	
	@Override
	public void deletePermiso(PermisoDTO permisoDTO) throws SeguridadesException
	{
		slf4jLogger.info("deletePermiso");
		try {
		if(permisoDTO.getPerCodigo()!=null)
			asistenciaFactoryDAO.getPermisoDAOImpl().remove(permisoDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deletePermiso {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
			
	
	/*RegistroAsistencia*/
	@Override
	public RegistroDTO createOrUpdateRegistroAsistencia(RegistroAsistenciaVO registroAsistencia) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateRegistroAsistencia");
		RegistroDTO registro=null;
		try {
			EmpleadoDTO empleado=asistenciaFactoryDAO.getEmpleadoDAOImpl().findByCredentials(registroAsistencia.getEmpleadoDTO());
			if(empleado!=null)
			{	
				registro= asistenciaFactoryDAO.getRegistroDAOImpl().getUltimate(empleado);
				if(registro!=null)
				{
					if(registro.getRasHoraSalida()!=null){
						registroAsistencia.getRegistroDTO().setAsiEmpleado(empleado);
						registroAsistencia.getRegistroDTO().setRasHoraInicio(new Timestamp(new Date().getTime()));
						registro= asistenciaFactoryDAO.getRegistroDAOImpl().create(registroAsistencia.getRegistroDTO());					
					}
					else{
						registro.setRasHoraSalida(new Timestamp(new Date().getTime()));
						registro= asistenciaFactoryDAO.getRegistroDAOImpl().update(registro);
					}
				}
				else{
					registroAsistencia.getRegistroDTO().setAsiEmpleado(empleado);
					registroAsistencia.getRegistroDTO().setRasHoraInicio(new Timestamp(new Date().getTime()));
					registro=asistenciaFactoryDAO.getRegistroDAOImpl().create(registroAsistencia.getRegistroDTO());					
				}
			}
			else
			{
				throw new SeguridadesException("Datos incorrectos");
			}
		}catch(Exception e){
			slf4jLogger.info("Error al readFalta {}", e.getMessage());
			throw new SeguridadesException("No se pudo encontraro datos de empleado");	
		}
		return registro;
	}
	
	
	@Override
	public void deleteRegistro(RegistroDTO registroDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteRegistro");
		try {
		if(registroDTO.getRasCodigo()!=null)
			asistenciaFactoryDAO.getRegistroDAOImpl().remove(registroDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteRegistro {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	
	/*Horario*/
	@Override
	public HorarioDTO createOrUpdateHorario(HorarioDTO horarioDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateFalta");
		try {
		if(horarioDTO.getHorCodigo()!=null)
			return asistenciaFactoryDAO.getHorarioDAOImpl().update(horarioDTO);
		else
			return asistenciaFactoryDAO.getHorarioDAOImpl().create(horarioDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateFalta {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteHorario(HorarioDTO horarioDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteHorario");
		try {
		if(horarioDTO.getHorCodigo()!=null)
			asistenciaFactoryDAO.getHorarioDAOImpl().remove(horarioDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteHorario {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	/*Horario Empleado*/
	@Override
	public HorarioEmpleadoDTO createOrUpdateHorarioEmpleado(HorarioEmpleadoDTO horarioempleadoDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateHorarioEmpleado");
		try {
		if(horarioempleadoDTO.getHemCodigo()!=null)
			return asistenciaFactoryDAO.getHorarioEmpleadoDAOImpl().update(horarioempleadoDTO);
		else
			return asistenciaFactoryDAO.getHorarioEmpleadoDAOImpl().create(horarioempleadoDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateHorarioEmpleado {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteHorarioEmpleado(HorarioEmpleadoDTO horarioempleadoDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteHorarioEmpleado");
		try {
		if(horarioempleadoDTO.getHemCodigo()!=null)
			asistenciaFactoryDAO.getHorarioEmpleadoDAOImpl().remove(horarioempleadoDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteHorarioEmpleado {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	/*Tipo*/
	@Override
	public TipoDTO createOrUpdateTipo(TipoDTO tipoDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateRegistro");
		try {
		if(tipoDTO.getTipCodigo()!=null)
			return asistenciaFactoryDAO.getTipoDAOImpl().update(tipoDTO);
		else
			return asistenciaFactoryDAO.getTipoDAOImpl().create(tipoDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateTipo {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
	
	@Override
	public void deleteTipo(TipoDTO tipoDTO) throws SeguridadesException
	{
		slf4jLogger.info("deleteTipo");
		try {
		if(tipoDTO.getTipCodigo()!=null)
			asistenciaFactoryDAO.getTipoDAOImpl().remove(tipoDTO);		
		else 
			throw new SeguridadesException("no existe una coincidencia");
		} catch (Exception e) {
			slf4jLogger.info("error al deleteTipo {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}


	@Override
	public PermisoDTO createOrUpdateHorarioPermiso(PermisoDTO permisoDTO)
			throws SeguridadesException {
		// TODO Auto-generated method stub
		return null;
	}

}
