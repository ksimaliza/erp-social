package ec.edu.uce.erp.ejb.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.AsistenciaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioEmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;

@Stateless
public class ServicioAsistenciaImpl implements ServicioAsistencia{
	@EJB
	private AsistenciaFactoryDAO asistenciaFactoryDAO;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioAsistenciaImpl.class);
	
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
	
	@Override
	public EmpleadoDTO createOrUpdateEmpleado(EmpleadoDTO empleadoDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateEmpleado");
		try {
		if(empleadoDTO.getAemCodigo()!=null)
			return asistenciaFactoryDAO.getEmpleadoDAOImpl().update(empleadoDTO);
		else
			return asistenciaFactoryDAO.getEmpleadoDAOImpl().create(empleadoDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateEmpleado {}", e.toString());
			throw new SeguridadesException(e);
		}
		
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
	public FaltaDTO createOrUpdateFalta(FaltaDTO faltaDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateFalta");
		try {
		if(faltaDTO.getFalCodigo()!=null)
			return asistenciaFactoryDAO.getFaltaDAOImpl().update(faltaDTO);
		else
			return asistenciaFactoryDAO.getFaltaDAOImpl().create(faltaDTO);
		} catch (Exception e) {
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
	
	@Override
	public PermisoDTO createOrUpdatePermiso(PermisoDTO permisoDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdatePermiso");
		try {
		if(permisoDTO.getPerCodigo()!=null)
			return asistenciaFactoryDAO.getPermisoDAOImpl().update(permisoDTO);
		else
			return asistenciaFactoryDAO.getPermisoDAOImpl().create(permisoDTO);
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
	
	@Override
	public RegistroDTO createOrUpdateRegistro(RegistroDTO registroDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateRegistro");
		try {
		if(registroDTO.getRasCodigo()!=null)
			return asistenciaFactoryDAO.getRegistroDAOImpl().update(registroDTO);
		else
			return asistenciaFactoryDAO.getRegistroDAOImpl().create(registroDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateRegistro {}", e.toString());
			throw new SeguridadesException(e);
		}
		
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
