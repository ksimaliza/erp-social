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
import ec.edu.uce.erp.ejb.persistence.dao.HorarioEmpleadoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioEmpleadoDTO;

public class HorarioEmpleadoDAOImpl extends AbstractFacadeImpl<HorarioEmpleadoDTO> implements HorarioEmpleadoDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(HorarioEmpleadoDAOImpl.class);
	
	public HorarioEmpleadoDAOImpl() {
		super();
	}

	public HorarioEmpleadoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<HorarioEmpleadoDTO> getByAnd(HorarioEmpleadoDTO objectDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<HorarioEmpleadoDTO> cq;
		Root<HorarioEmpleadoDTO> from;
		List<HorarioEmpleadoDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(HorarioEmpleadoDTO.class);
			
			from= cq.from(HorarioEmpleadoDTO.class);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objectDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID"))
				{
				    getter = objectDTO.getClass().getMethod("get" + String.valueOf(fieldName.charAt(0)).toUpperCase() +
				            fieldName.substring(1));
				    
				    value = getter.invoke(objectDTO, new Object[0]);
				
				    if(value!=null)
				    {
				    	predicate=cb.equal(from.get(fieldName), value);
				    	predicateList.add(predicate);                	
				    }
				}
	        }
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<HorarioEmpleadoDTO> tq=entityManager.createQuery(cq);
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
