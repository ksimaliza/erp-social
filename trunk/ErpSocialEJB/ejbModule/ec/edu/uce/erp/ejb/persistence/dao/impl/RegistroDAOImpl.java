package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.RegistroDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorasTrabajadasListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.MesRegistroVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.AnioDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.FiltroFechaDTO;


public class RegistroDAOImpl extends AbstractFacadeImpl<RegistroDTO> implements RegistroDAO {

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(RegistroDAOImpl.class);
	
	public RegistroDAOImpl() {
		super();
	}

	public RegistroDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<AnioDTO> getYear() throws SeguridadesException
	{
		List<AnioDTO> result = null;
		try {
			CriteriaBuilder cb=entityManager.getCriteriaBuilder();
			CriteriaQuery<AnioDTO> cq=cb.createQuery(AnioDTO.class);
			Root<AnioDTO> from = cq.from(AnioDTO.class);
						
			CriteriaQuery<AnioDTO> select = cq.select(from);
			
			TypedQuery<AnioDTO> typedQuery = entityManager.createQuery(select);
			
			if(!typedQuery.getResultList().isEmpty())
				result = typedQuery.getResultList();	

		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}		
		return result;
	}
	
	@Override
	public List<MesRegistroVieDTO> getMonth() throws SeguridadesException
	{
		List<MesRegistroVieDTO> result = null;
		try {
			CriteriaBuilder cb=entityManager.getCriteriaBuilder();
			CriteriaQuery<MesRegistroVieDTO> cq=cb.createQuery(MesRegistroVieDTO.class);
			Root<MesRegistroVieDTO> from = cq.from(MesRegistroVieDTO.class);
						
			CriteriaQuery<MesRegistroVieDTO> select = cq.select(from);
			
			TypedQuery<MesRegistroVieDTO> typedQuery = entityManager.createQuery(select);
			
			if(!typedQuery.getResultList().isEmpty())
				result = typedQuery.getResultList();	

		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}		
		return result;
	}
	

	
	@Override
	public RegistroDTO getUltimate(EmpleadoDTO empleado) throws SeguridadesException
	{
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		RegistroDTO result = null;

		try {
			CriteriaBuilder cb=entityManager.getCriteriaBuilder();
			CriteriaQuery<RegistroDTO> cq=cb.createQuery(RegistroDTO.class);
			Root<RegistroDTO> from = cq.from(RegistroDTO.class);
		
			CriteriaQuery<RegistroDTO> select = cq.select(from);
			
			predicate=cb.equal(from.get("asiEmpleado"), empleado);
			criteriaList.add(predicate);
	
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
	
			cq.orderBy(cb.desc(from.get("rasHoraInicio")));
			
			TypedQuery<RegistroDTO> typedQuery = entityManager.createQuery(select);
			
			if(!typedQuery.getResultList().isEmpty())
				result = typedQuery.getResultList().get(0);	

		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}		
		return result;
	}
	
	@Override
	public List<EmpleadoAtrasoListDTO> getByAnd(EmpleadoAtrasoListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<EmpleadoAtrasoListDTO> cq;
		Root<EmpleadoAtrasoListDTO> from;
		List<EmpleadoAtrasoListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(EmpleadoAtrasoListDTO.class);
			
			from= cq.from(EmpleadoAtrasoListDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objetoDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = objetoDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(objetoDTO, new Object[0]);
				
				    if(value!=null && value!="")
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<EmpleadoAtrasoListDTO> tq=entityManager.createQuery(cq);
			list=tq.getResultList();
			
			return list;
			
		}catch(Exception e){
			slf4jLogger.info(e.toString());
			throw new SeguridadesException(e);
		}finally{
			predicate=null;
			predicateList=null;
		}		
	}


	@Override
	public List<HorasTrabajadasListDTO> getByAndHoras(HorasTrabajadasListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<HorasTrabajadasListDTO> cq;
		Root<HorasTrabajadasListDTO> from;
		List<HorasTrabajadasListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(HorasTrabajadasListDTO.class);
			
			from= cq.from(HorasTrabajadasListDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objetoDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = objetoDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(objetoDTO, new Object[0]);
				
				    if(value!=null && value!="")
				    {
				    	if(UtilAplication.isDouble(value))
				    	{
				    		if(Double.valueOf(value.toString())!=0.0)
				    		{
						    	predicate=cb.equal(from.get(fieldName), value);
						    	predicateList.add(predicate);                	
				    		}
				    	}
				    	else
				    	{
					    	predicate=cb.equal(from.get(fieldName), value);
					    	predicateList.add(predicate);                					    		
				    	}
				    }
				}
	        }

	    	predicate=cb.isNotNull(from.get("horasTrabajadas"));
	    	predicateList.add(predicate);                					    		
	        
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<HorasTrabajadasListDTO> tq=entityManager.createQuery(cq);
			list=tq.getResultList();
			
			return list;
			
		}catch(Exception e){
			slf4jLogger.info(e.toString());
			throw new SeguridadesException(e);
		}finally{
			predicate=null;
			predicateList=null;
		}		
	}

	
	@Override
	public List<EmpleadoAtrasoListDTO> obtenerHoras(EmpleadoAtrasoListDTO empleadoAtrasoListDTO, FiltroFechaDTO filtro) throws SeguridadesException {
		
		slf4jLogger.info("obtenerHoras");
		List<EmpleadoAtrasoListDTO> registroEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		CriteriaBuilder cb;
		CriteriaQuery<EmpleadoAtrasoListDTO> cq;
		Root<EmpleadoAtrasoListDTO> from;
		
		CriteriaQuery<EmpleadoAtrasoListDTO> select;
		try {
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(EmpleadoAtrasoListDTO.class);
			from = cq.from(EmpleadoAtrasoListDTO.class);
			
			cq.multiselect(from.get("rasEmpleado"),from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres")).distinct(true);
			
			criteriaList = new ArrayList<Predicate>();
		
			select = cq.select(from);
		
			//por cedula empleado
			if (!StringUtils.isEmpty(empleadoAtrasoListDTO.getPerCi())) {
				Expression<String> empleadoCedula = 
						cb.upper(cb.literal
								(UtilAplication.concatenarPorcenteje(empleadoAtrasoListDTO.getPerCi())));
				predicate = cb.like(cb.upper(from.<String>get("perCi")), empleadoCedula);
				criteriaList.add(predicate);
			}
			
			
			//por apellidos empleado
			if (!StringUtils.isEmpty(empleadoAtrasoListDTO.getPerApellidos())) {
				Expression<String> empleadoApellidos = 
						cb.upper(cb.literal
								(UtilAplication.concatenarPorcenteje(empleadoAtrasoListDTO.getPerApellidos())));
				predicate = cb.like(cb.upper(from.<String>get("perApellidos")), empleadoApellidos);
				criteriaList.add(predicate);
			}
		
			//por nombre empleado
			if (!StringUtils.isEmpty(empleadoAtrasoListDTO.getPerNombres())) {
				Expression<String> empleadoNombres = 
						cb.upper(cb.literal
								(UtilAplication.concatenarPorcenteje(empleadoAtrasoListDTO.getPerNombres())));
				predicate = cb.like(cb.upper(from.<String>get("perNombres")), empleadoNombres);
				criteriaList.add(predicate);
			}
				
				
			//por año
			if (filtro.getAnio()!=null){
				predicate=cb.equal(cb.function("year", Integer.class, from.get("rasHoraInicio")),filtro.getAnio());
				criteriaList.add(predicate);
			}
			
			//por mes
			if (filtro.getMes()!=null){
				predicate=cb.equal(cb.function("month", Integer.class, from.get("rasHoraInicio")),filtro.getMes());
				criteriaList.add(predicate);
			}
			
			//por dia
			if (filtro.getDia()!=null){
				predicate=cb.equal(cb.function("day", Integer.class, from.get("rasHoraInicio")),filtro.getDia());
				criteriaList.add(predicate);
			}
		
		
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<EmpleadoAtrasoListDTO> typedQuery = entityManager.createQuery(select);
		registroEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return registroEncontrado;
	}
	


}
