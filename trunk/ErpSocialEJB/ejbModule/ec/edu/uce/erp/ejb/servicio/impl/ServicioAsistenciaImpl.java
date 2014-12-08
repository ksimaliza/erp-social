package ec.edu.uce.erp.ejb.servicio.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.CalendarUtil;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.dao.factory.AsistenciaFactoryDAO;
import ec.edu.uce.erp.ejb.dao.factory.FactoryDAO;
import ec.edu.uce.erp.ejb.persistence.dao.CatalogoAsistenciaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
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

	@Override
	public List<DiaDTO> readDiaAll() throws SeguridadesException
	{
		slf4jLogger.info("readDiaAll");
		try {
			return asistenciaFactoryDAO.getDiaDAOImpl().getByAnd(new DiaDTO());
		} catch (Exception e) {
			slf4jLogger.info("error al readDiaAll {}", e.toString());
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
		EmpleadoDTO empleadoDTO;
		try {
			empleadoDTO=new EmpleadoDTO();
			empleadoDTO.setAemUsuario(empleadoVO.getEmpleadoDTO().getAemUsuario());
			
			if(empleadoVO.getEmpleadoDTO().getAemCodigo()!=null)
			{
				personanueva=factoryDAO.getPersonaDAOImpl().update(empleadoVO.getPersona());
				empleadoVO.getEmpleado().setPersonaTbl(personanueva);
				empleadoVO.getEmpleado().setPerFk(personanueva.getPerPk());
				empleadonuevo=factoryDAO.getEmpleadoeDAOImpl().update(empleadoVO.getEmpleado());
				empleadoVO.getEmpleadoDTO().setAemEmpleado(empleadonuevo.getEmpPk());
				return asistenciaFactoryDAO.getEmpleadoDAOImpl().update(empleadoVO.getEmpleadoDTO());
			}
			else
			{
				if(!factoryDAO.getEmpleadoDAOImpl().getByAnd(empleadoDTO).isEmpty())
				{
					throw new SeguridadesException("El usuario ya a sido registrado");
				}
				else	
				{
					if(empleadoVO.getPersona().getPerPk()!=null)
						personanueva=factoryDAO.getPersonaDAOImpl().update(empleadoVO.getPersona());
					else
						personanueva=factoryDAO.getPersonaDAOImpl().create(empleadoVO.getPersona());
						
					empleadoVO.getEmpleado().setPersonaTbl(personanueva);
					empleadoVO.getEmpleado().setPerFk(personanueva.getPerPk());
					empleadonuevo=factoryDAO.getEmpleadoeDAOImpl().create(empleadoVO.getEmpleado());
					empleadoVO.getEmpleadoDTO().setAemEmpleado(empleadonuevo.getEmpPk());
					return asistenciaFactoryDAO.getEmpleadoDAOImpl().create(empleadoVO.getEmpleadoDTO());
				}
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
	
	@Override
	public EmpleadoVO obtenerEmpleadoPorId(EmpleadoListDTO empleadoListDTO) throws SeguridadesException {
		slf4jLogger.info("obtenerEmpleadoPorId");
		EmpleadoVO empleado=new EmpleadoVO();
		empleado.setPersona(factoryDAO.getPersonaDAOImpl().find(empleadoListDTO.getPerPk()));
		empleado.setEmpleado(factoryDAO.getEmpleadoeDAOImpl().find(empleadoListDTO.getAemEmpleado()));
		empleado.setEmpleadoDTO(asistenciaFactoryDAO.getEmpleadoDAOImpl().find(empleadoListDTO.getAemCodigo()));
		
		return empleado;
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
	
	@Override
	public List<FaltaListDTO> readFalta2(FaltaListDTO falta) throws SeguridadesException {
		slf4jLogger.info("readFalta");
		List<FaltaListDTO> listResultado = null;
		try {
			listResultado = asistenciaFactoryDAO.getFaltaDAOImpl().getByAnd(falta);
		} catch (Exception e) {
			slf4jLogger.info("Error al readFalta {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener falta de la base de datos");
		}
		return listResultado;
	}

	
	@Override
	public List<FaltaListDTO> readFaltaReport(FaltaListDTO falta) throws SeguridadesException {
		slf4jLogger.info("readFalta");
		List<FaltaListDTO> listResultado = null;
		try {
			listResultado = asistenciaFactoryDAO.getFaltaDAOImpl().getDistinctByAnd(falta);
		} catch (Exception e) {
			slf4jLogger.info("Error al readFalta {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener falta de la base de datos");
		}
		return listResultado;
	}

	
	
	@Override
	public List<EmpleadoAtrasoListDTO> readAtraso(EmpleadoAtrasoListDTO atraso) throws SeguridadesException {
		slf4jLogger.info("readAtraso");
		List<EmpleadoAtrasoListDTO> listResultado = null;
		try {
			atraso.setRasTipoEntrada("Atraso");
			slf4jLogger.info("setRasTipoEntrada",atraso.getRasTipoEntrada());
			listResultado = asistenciaFactoryDAO.getRegistroDAOImpl().getByAnd(atraso);
		} catch (Exception e) {
			slf4jLogger.info("Error al readFalta {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener falta de la base de datos");
		}
		return listResultado;
	}
	
	@Override
	public List<EmpleadoAtrasoListDTO> readAtrasoReport(EmpleadoAtrasoListDTO atraso) throws SeguridadesException {
		slf4jLogger.info("readAtraso");
		List<EmpleadoAtrasoListDTO> listResultado = null;
		try {
			listResultado = asistenciaFactoryDAO.getRegistroDAOImpl().getDistinctByAnd(atraso);
		} catch (Exception e) {
			slf4jLogger.info("Error al readFalta {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener falta de la base de datos");
		}
		return listResultado;
	}
	
	
	@Override
	public List<HorasTrabajadasListDTO> readHoras(HorasTrabajadasListDTO horas) throws SeguridadesException {
		slf4jLogger.info("readHoras");
		List<HorasTrabajadasListDTO> listResultado = null;
		try {
			listResultado = asistenciaFactoryDAO.getRegistroDAOImpl().getByAndHoras(horas);
		} catch (Exception e) {
			slf4jLogger.info("Error al readHoras {}", e.getMessage());
			throw new SeguridadesException("No se pudo readHoras  de la base de datos");
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
			
	
	@Override
	public List<PermisoListDTO> readPermiso(PermisoListDTO permiso) throws SeguridadesException {
		slf4jLogger.info("readPermiso");
		List<PermisoListDTO> listResultado = null;
		try {
			listResultado = asistenciaFactoryDAO.getPermisoDAOImpl().findAll(permiso);
		} catch (Exception e) {
			slf4jLogger.info("Error al readFalta {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener falta de la base de datos");
		}
		return listResultado;
	}

	
	/*RegistroAsistencia*/
	@Override
	public RegistroDTO createOrUpdateRegistroAsistencia(RegistroAsistenciaVO registroAsistencia) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateRegistroAsistencia");
		RegistroDTO registro=null;
		
		EmpleadoDTO empleado;
		
		try {
			
			empleado=asistenciaFactoryDAO.getEmpleadoDAOImpl().findByCredentials(registroAsistencia.getEmpleadoDTO());
			if(empleado!=null)
			{	
				registro= asistenciaFactoryDAO.getRegistroDAOImpl().getUltimate(empleado);
				if(registro!=null)
				{
					registroAsistencia.getRegistroDTO().setRasEntidad(empleado.getAemEmpresa());
					if(registro.getRasHoraSalida()!=null){
						if(CalendarUtil.getDay(registro.getRasHoraInicio())<CalendarUtil.getDay(new Timestamp(new Date().getTime()))){
							registro.setRasHoraSalida(Timestamp.valueOf(CalendarUtil.getYear(registro.getRasHoraInicio()).toString()+"-"+CalendarUtil.getMonth(registro.getRasHoraInicio()).toString()+"-"+CalendarUtil.getDay(registro.getRasHoraInicio()).toString()+" 23:59:00.000"));
							registro= asistenciaFactoryDAO.getRegistroDAOImpl().update(registro);
							registro.setRasHoraSalida(null);
						}
						registro= asistenciaFactoryDAO.getRegistroDAOImpl().create(verificarEntrada(registroAsistencia,empleado).getRegistroDTO());					
					}
					else{
						registro.setRasHoraSalida(new Timestamp(new Date().getTime()));
						registro= asistenciaFactoryDAO.getRegistroDAOImpl().update(registro);
					}
				}
				else{
					registro=asistenciaFactoryDAO.getRegistroDAOImpl().create(verificarEntrada(registroAsistencia,empleado).getRegistroDTO());					
				}
			}
			else
			{
				throw new SeguridadesException("Datos incorrectos");
			}
		}catch(Exception e){
			slf4jLogger.info("Error al readFalta {}", e.getMessage());
			throw new SeguridadesException("No se pudo encontraron datos de empleado");	
		}
		return registro;
	}
	
	private RegistroAsistenciaVO verificarEntrada(RegistroAsistenciaVO registroAsistencia,EmpleadoDTO empleado)
	{
		Timestamp actual;
		ParametroDTO parametro;
		String hora,valor;
		List<ParametroDTO> parametroList;
		try{
			parametro=new ParametroDTO();
			
			parametro.setPasEntidad(empleado.getAemEmpresa());
			parametroList= asistenciaFactoryDAO.getParametroDAOImpl().getByAnd(parametro);
			
			verificarFaltas(registroAsistencia);
			actual=new Timestamp(new Date().getTime());
			
			//A tiempo
			valor=parametroList.get(0).getPasValor();
			hora=UtilAplication.fechaActualConFormato("yyyy-MM-dd")+" "+valor;
			if(actual.getTime()<Timestamp.valueOf(hora).getTime())
				registroAsistencia.getRegistroDTO().setRasTipoEntrada("A tiempo");
			else if(actual.getTime()>Timestamp.valueOf(hora).getTime()&& actual.getTime()< Timestamp.valueOf(CalendarUtil.addMinute(hora, "yyyy-MM-dd hh:mm:ss", Integer.valueOf(parametroList.get(3).getPasValor()))).getTime()){
				registroAsistencia.getRegistroDTO().setRasTipoEntrada("Atraso");
			}
			else if(actual.getTime()>=Timestamp.valueOf(CalendarUtil.addMinute(hora, "yyyy-MM-dd hh:mm:ss", Integer.valueOf(parametroList.get(3).getPasValor()))).getTime()){
				registroAsistencia.getRegistroDTO().setRasTipoEntrada("Falta");
				FaltaDTO falta=new FaltaDTO();
				falta.setAsiEmpleado(empleado);
				falta.setFalDescripcion("Falta por ingreso pasado intervalo");
				falta.setFalFecha(actual);
				asistenciaFactoryDAO.getFaltaDAOImpl().create(falta);
			}
			registroAsistencia.getRegistroDTO().setRasEntidad(empleado.getAemEmpresa());
			registroAsistencia.getRegistroDTO().setAsiEmpleado(empleado);
			registroAsistencia.getRegistroDTO().setRasHoraInicio(actual);
		}
		catch(Exception e)
		{
			slf4jLogger.info("Error al verificarEntrada {}", e.getMessage());
			return null;
		}
		return registroAsistencia;
	}
	
	private void verificarFaltas(RegistroAsistenciaVO registroAsistencia) throws SeguridadesException
	{
		Timestamp actual,anterior;
		try{
			actual=new Timestamp(new Date().getTime());
			for(int i=7;i==0;i--)
			{
				 anterior=CalendarUtil.addDay(actual, -i);
			}
		}
		catch(Exception e)
		{
			slf4jLogger.info("Error al verificarFaltas {}", e.getMessage());
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
	public List<TipoDTO> buscarTipo(TipoDTO tipo) throws SeguridadesException {
		slf4jLogger.info("buscarTipo");
		List<TipoDTO> listTipo = null;
		try {
			listTipo = asistenciaFactoryDAO.getTipoDAOImpl().obtenerTipo(tipo);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarTipo {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
		return listTipo;
	}

	@Override
	public List<TipoDTO> readAllTipo() throws SeguridadesException {
		slf4jLogger.info("buscarTipo");
		List<TipoDTO> listTipo = null;
		try {
			listTipo = asistenciaFactoryDAO.getTipoDAOImpl().obtenerTipo(new TipoDTO());
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarTipo {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
		return listTipo;
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
	public TipoDTO obtenerTipoPorId(Integer id) throws SeguridadesException {
		slf4jLogger.info("obtenerTipoPorId");
		
		TipoDTO tipo=new TipoDTO();
		
		tipo = asistenciaFactoryDAO.getTipoDAOImpl().find(id);
		
		return tipo;
	}



	@Override
	public PermisoDTO createOrUpdateHorarioPermiso(PermisoDTO permisoDTO)
			throws SeguridadesException {
		return null;
	}


	@Override
	public ParametroDTO createOrUpdateParametro(ParametroDTO parametroDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateParametro");
		ParametroDTO par,par2;
		List<ParametroDTO> parametroList;
		try {
			if(parametroDTO.getPasCodigo()!=null)
				par2= asistenciaFactoryDAO.getParametroDAOImpl().update(parametroDTO);
			else
			{
				par=new ParametroDTO();
				par2=new ParametroDTO();
				par=(ParametroDTO) BeanUtils.cloneBean(parametroDTO);
				par.setPasCodigo(null);
				par.setPasDescripcion(null);
				par.setPasValor(null);
				
				parametroList=asistenciaFactoryDAO.getParametroDAOImpl().getByAnd(par);
				
				if(parametroList.size()>0)
				{
					par=parametroList.get(0);
					par.setPasValor(parametroDTO.getPasValor());
					parametroDTO.setPasCodigo(par.getPasCodigo());
					par2= asistenciaFactoryDAO.getParametroDAOImpl().update(par);
				}
				else
					par2= asistenciaFactoryDAO.getParametroDAOImpl().create(parametroDTO);
			}
			return par2;	
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateParametro {}", e.toString());
			throw new SeguridadesException(e);
		}
		finally{
			parametroList=null;
			par=null;
			parametroDTO=null;
		}
		
	}

	
	@Override
	public List<ParametroDTO> buscarParametro(ParametroDTO parametro) throws SeguridadesException {
		List<ParametroDTO> listParametro = null;
		try {
			listParametro = asistenciaFactoryDAO.getParametroDAOImpl().buscarTodos();
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarParametrosCriterios {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
		return listParametro;
	}

	@Override
	@TransactionAttribute (TransactionAttributeType.REQUIRED)
	public ParametroDTO actualizarParametroAsistencia(ParametroDTO parametro) throws SeguridadesException {
		ParametroDTO parametroUpdate = null;
		
		try {
			parametroUpdate = asistenciaFactoryDAO.getParametroDAOImpl().update(parametro);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al actualizarParametro {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
		
		return parametroUpdate;
	}
	
	@Override
	public ParametroDTO obtenerParametroPorId(Integer id) throws SeguridadesException {
		slf4jLogger.info("obtenerParametroPorId");
		
		ParametroDTO par=new ParametroDTO();
		
		par = asistenciaFactoryDAO.getParametroDAOImpl().find(id);
		
		return par;
	}
	
	
	/*Anio*/
	@Override
	public List<AnioDTO> readAnioActual() throws SeguridadesException
	{
		List<AnioDTO> anioList=new ArrayList<AnioDTO>();
		AnioDTO anioDTO;
		anioDTO=new AnioDTO();
		anioDTO.setCodigo(CalendarUtil.getYear());
		anioDTO.setDescripcion(String.valueOf(CalendarUtil.getYear()));
		anioList.add(anioDTO);
		return anioList;
	}
	
	@Override
	public void createDiaNoLaboralSabadoDomingo(int year) throws SeguridadesException
	{
		Date fechaInicial= CalendarUtil.convertStringtoDate(String.valueOf(year)+"-01-01");
		Date fechaFinal= CalendarUtil.convertStringtoDate(String.valueOf(year)+"-12-31");
		DiaNoLaboralDTO diaNo;
		try {
			
			if(!asistenciaFactoryDAO.getDiaNoLaboralDAOImpl().getAll(year).isEmpty())
			{
				throw new Exception("Ya se ha generado para este año");
			}
			
			Calendar aux=Calendar.getInstance();
			while (fechaInicial.before(fechaFinal)) {
	
				aux= CalendarUtil.getDate(new Timestamp(fechaInicial.getTime()));
				
				if (aux.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY|| aux.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
										
					diaNo=new DiaNoLaboralDTO();
					diaNo.setDnlAnio(year);
					diaNo.setDnlMes(CalendarUtil.getMonth(new Timestamp(fechaInicial.getTime())));
					diaNo.setDnlDia(CalendarUtil.getDay(new Timestamp(fechaInicial.getTime())));
					diaNo.setDnlObservacion("Fin de Semana");
					asistenciaFactoryDAO.getDiaNoLaboralDAOImpl().create(diaNo);
				}
				
				fechaInicial=CalendarUtil.addDay(fechaInicial, 1);
			}
		} catch (Exception e) {
			slf4jLogger.info("Error al actualizarParametro {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
	}
	
	@Override
	public List<DiaNoLaboralDTO> readDiaNoLaboral(int year) throws SeguridadesException
	{
		try {
			return asistenciaFactoryDAO.getDiaNoLaboralDAOImpl().getAll(year);
		} catch (Exception e) {
			slf4jLogger.info("Error al readDiaNoLaboral {}" , e.getMessage());
			throw new SeguridadesException(e);
		}		
	}
	
	
	@Override
	public void createDiaNoLaboral(DiaNoLaboralDTO diaNoLaboral) throws SeguridadesException
	{
		try {
			asistenciaFactoryDAO.getDiaNoLaboralDAOImpl().create(diaNoLaboral);
		} catch (Exception e) {
			slf4jLogger.info("Error al createDiaNoLaboral {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
	}
	
	@Override
	public List<TiempoDTO> readHour() throws SeguridadesException
	{
		List<TiempoDTO> listParametro = null;
		TiempoDTO tiempo;
		try {
			listParametro =new ArrayList<TiempoDTO>();
			for(int i=0;i<=23;i++)
			{
				tiempo=new TiempoDTO();
				tiempo.setCodigo(String.valueOf(i).toString().length()==1?"0"+String.valueOf(i):String.valueOf(i));
				tiempo.setDescripcion(String.valueOf(i).toString().length()==1?"0"+String.valueOf(i):String.valueOf(i));
				listParametro.add(tiempo);
			}
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarParametrosCriterios {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
		return listParametro;
	}

	@Override
	public List<TiempoDTO> readMinute() throws SeguridadesException
	{
		List<TiempoDTO> listParametro = null;
		TiempoDTO tiempo;
		try {
			listParametro =new ArrayList<TiempoDTO>();
			for(int i=0;i<=59;i++)
			{
				tiempo=new TiempoDTO();
				tiempo.setCodigo(String.valueOf(i).toString().length()==1?"0"+String.valueOf(i):String.valueOf(i));
				tiempo.setDescripcion(String.valueOf(i).toString().length()==1?"0"+String.valueOf(i):String.valueOf(i));
				listParametro.add(tiempo);
			}
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarParametrosCriterios {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
		return listParametro;		
	}

	
	@Override
	public List<TiempoDTO> readDay() throws SeguridadesException
	{
		List<TiempoDTO> listParametro = null;
		TiempoDTO tiempo;
		try {
			listParametro =new ArrayList<TiempoDTO>();
			tiempo=new TiempoDTO();
			tiempo.setCodigo("1");
			tiempo.setDescripcion("Lunes");
			listParametro.add(tiempo);
			
			tiempo=new TiempoDTO();
			tiempo.setCodigo("2");
			tiempo.setDescripcion("Martes");
			listParametro.add(tiempo);

			tiempo=new TiempoDTO();
			tiempo.setCodigo("3");
			tiempo.setDescripcion("Miesrcoles");
			listParametro.add(tiempo);

			tiempo=new TiempoDTO();
			tiempo.setCodigo("4");
			tiempo.setDescripcion("Jueves");
			listParametro.add(tiempo);

			tiempo=new TiempoDTO();
			tiempo.setCodigo("5");
			tiempo.setDescripcion("Viernes");
			listParametro.add(tiempo);

			tiempo=new TiempoDTO();
			tiempo.setCodigo("6");
			tiempo.setDescripcion("Sábado");
			listParametro.add(tiempo);

			tiempo=new TiempoDTO();
			tiempo.setCodigo("7");
			tiempo.setDescripcion("Domingo");
			listParametro.add(tiempo);

		} catch (Exception e) {
			slf4jLogger.info("Error al buscarParametrosCriterios {}" , e.getMessage());
			throw new SeguridadesException(e);
		}
		return listParametro;		
	}
	
	
	
	@Override
	public FaltaDTO obtenerFaltaPorId(FaltaListDTO faltaListDTO) throws SeguridadesException {
		slf4jLogger.info("obtenerFaltaPorId");
		FaltaDTO falta=new FaltaDTO();
		falta = asistenciaFactoryDAO.getFaltaDAOImpl().find(faltaListDTO.getAemCodigo());
		return falta;
	}

	
	@Override
	public List<EmpleadoAtrasoListDTO> buscarHoras(EmpleadoAtrasoListDTO empleadoAtrasoListDTO,FiltroFechaDTO filtro) throws SeguridadesException {
		slf4jLogger.info("buscarHoras");
		List<EmpleadoAtrasoListDTO> listHoras = null;
		try {
			listHoras=asistenciaFactoryDAO.getRegistroDAOImpl().obtenerHoras(empleadoAtrasoListDTO, filtro);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarHoras {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarHoras de la base de datos");
		}
		
		return listHoras;
	}
	
	//Catologo
	@Override
	public List<CatalogoAsistenciaDTO> readAll() throws SeguridadesException {
		slf4jLogger.info("buscarHoras");
		List<CatalogoAsistenciaDTO> listHoras = null;
		try {
			listHoras=asistenciaFactoryDAO.getCatalogoAsistenciaDAOImpl().getByAnd(new CatalogoAsistenciaDTO());
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarHoras {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarHoras de la base de datos");
		}
		return listHoras;
	}

	
	//Catalogo Asistencia
	@Override
	public List<ParametroCatalogoDTO> read(ParametroCatalogoDTO parametroCatalogoDTO) throws SeguridadesException {
		slf4jLogger.info("buscarHoras");
		List<ParametroCatalogoDTO> listHoras = null;
		try {
			listHoras=asistenciaFactoryDAO.getParametroCatalogoDAOImpl().getByAnd(parametroCatalogoDTO);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarHoras {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarHoras de la base de datos");
		}
		return listHoras;
	}
	
	@Override
	public ParametroCatalogoDTO getParametroCatalogById(int id) throws SeguridadesException {
		slf4jLogger.info("buscarHoras");
		ParametroCatalogoDTO listHoras = null;
		try {
			listHoras=asistenciaFactoryDAO.getParametroCatalogoDAOImpl().find(id);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarHoras {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarHoras de la base de datos");
		}
		return listHoras;
	}
	
}
	


