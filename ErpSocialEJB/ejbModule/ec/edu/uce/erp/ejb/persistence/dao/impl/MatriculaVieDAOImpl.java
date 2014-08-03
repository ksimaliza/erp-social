package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.MatriculaVieDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;

public class MatriculaVieDAOImpl extends AbstractFacadeImpl<MatriculaVieDTO> implements MatriculaVieDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EstudianteListDAOImpl.class);
	
	public MatriculaVieDAOImpl() {
		super();
	}

	public MatriculaVieDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
	@Override
	public List<MatriculaVieDTO> getByAnd(MatriculaVieDTO objectDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<MatriculaVieDTO> cq;
		Root<MatriculaVieDTO> from;
		List<MatriculaVieDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(MatriculaVieDTO.class);
			
			from= cq.from(MatriculaVieDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objectDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	        	
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID")){
					
				    getter = objectDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(objectDTO, new Object[0]);
				
				    if(value!=null){
				    	
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<MatriculaVieDTO> tq=entityManager.createQuery(cq);
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
	public List<MatriculaVieDTO> getBySubquery(MatriculaVieDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<MatriculaVieDTO> cq;
		Root<MatriculaVieDTO> from;
		List<MatriculaVieDTO> list;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(MatriculaVieDTO.class);
			
			from=cq.from(MatriculaVieDTO.class);
			
	        cq.where(from.get("perPk").in(getSubquery(objetoDTO)));
	        				
	        
			TypedQuery<MatriculaVieDTO> tq=entityManager.createQuery(cq);
			list=tq.getResultList();
			
			return list;
			
		}catch(Exception e){
			slf4jLogger.info(e.toString());
			throw new SeguridadesException(e);
		}finally{
			
		}
	}

	@SuppressWarnings({"rawtypes", "unchecked" })
	private Subquery<MatriculaVieDTO> getSubquery(MatriculaVieDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<MatriculaVieDTO> cq;
		
		Subquery<MatriculaVieDTO> subquery;
		Root from ;

		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(MatriculaVieDTO.class);
			
			subquery=cq.subquery(MatriculaVieDTO.class);
			from = subquery.from(MatriculaVieDTO.class);
			
			subquery.select(from.get("perPk"));
			
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
	        	subquery.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			return subquery;			
			
		}catch(Exception e){
			slf4jLogger.info(e.toString());
			throw new SeguridadesException(e);
		}finally{
			predicate=null;
			predicateList=null;
		}				
	}
	
	
	@Override
	public List<MatriculaVieDTO> getByAndDistinct(MatriculaVieDTO objectDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<MatriculaVieDTO> cq;
		Root<MatriculaVieDTO> from;
		List<MatriculaVieDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(MatriculaVieDTO.class);
			
			from= cq.from(MatriculaVieDTO.class);
			
			cq.multiselect(from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres"),
					from.get("nivDescaripcion"),
					from.get("parDescripcion"),
					from.get("perDescripcion"),
					from.get("perFechaNac"),
					from.get("emrNombre"),
					from.get("emrNombre")).distinct(true);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objectDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	        	
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID")){
					
				    getter = objectDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(objectDTO, new Object[0]);
				
				    if(value!=null){
				    	
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<MatriculaVieDTO> tq=entityManager.createQuery(cq);
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
