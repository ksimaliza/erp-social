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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.RegistroDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;


public class RegistroDAOImpl extends AbstractFacadeImpl<RegistroDTO> implements RegistroDAO {

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(RegistroDAOImpl.class);
	
	public RegistroDAOImpl() {
		super();
	}

	public RegistroDAOImpl(EntityManager entityManager) {
		super(entityManager);
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
	
	
	
	

}
