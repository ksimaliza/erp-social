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
import ec.edu.uce.erp.ejb.persistence.dao.FaltaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;

public class FaltaDAOImpl extends AbstractFacadeImpl<FaltaDTO> implements FaltaDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(FaltaDAOImpl.class);
	
	public FaltaDAOImpl() {
		super();
	}

	public FaltaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
	@Override
	public List<FaltaListDTO> findAll(FaltaListDTO falta) throws SeguridadesException
	{
		slf4jLogger.info("findAll");
		List<FaltaListDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<FaltaListDTO> cq=cb.createQuery(FaltaListDTO.class);
		Root<FaltaListDTO> from = cq.from(FaltaListDTO.class);
		
		CriteriaQuery<FaltaListDTO> select = cq.select(from);
		
		
		
		//por cedula
		if (!StringUtils.isEmpty(falta.getPerCi())) {
			Expression<String> cedula = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(falta.getPerCi())));
			predicate = cb.like(cb.upper(from.<String>get("perCi")), cedula);
			criteriaList.add(predicate);
		}
		
		//por nombre
		if (!StringUtils.isEmpty(falta.getPerNombres())) {
			Expression<String> nombre = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(falta.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombre);
			criteriaList.add(predicate);
		}
		//por apellidos
		if (!StringUtils.isEmpty(falta.getPerApellidos())) {
			Expression<String> apellido = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(falta.getPerApellidos())));
			predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellido);
			criteriaList.add(predicate);
		}
				
		if(criteriaList!=null && criteriaList.size()>0)
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<FaltaListDTO> typedQuery = entityManager.createQuery(select);
		resultado = typedQuery.getResultList();
		
		criteriaList = new ArrayList<Predicate>();
		
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;
	}
	
	
	@Override
	public List<FaltaListDTO> getByAnd(FaltaListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<FaltaListDTO> cq;
		Root<FaltaListDTO> from;
		List<FaltaListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(FaltaListDTO.class);
			
			from= cq.from(FaltaListDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objetoDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = objetoDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(objetoDTO, new Object[0]);
				
				    if(value!=null)
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<FaltaListDTO> tq=entityManager.createQuery(cq);
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
	public List<FaltaListDTO> getDistinctByAnd(FaltaListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<FaltaListDTO> cq;
		Root<FaltaListDTO> from;
		List<FaltaListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(FaltaListDTO.class);
			
			from= cq.from(FaltaListDTO.class);
			
			cq.multiselect(
					from.get("aemEmpleado"),
					from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres"),
					from.get("perDireccion"),
					from.get("falFecha")
					).distinct(true);
			
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
			
			TypedQuery<FaltaListDTO> tq=entityManager.createQuery(cq);
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
	
}
