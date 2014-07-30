package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteListDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;

public class EstudianteListDAOImpl extends AbstractFacadeImpl<EstudianteListDTO> implements EstudianteListDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EstudianteListDAOImpl.class);
	
	public EstudianteListDAOImpl() {
		super();
		
	}

	public EstudianteListDAOImpl(EntityManager entityManager) {
		super(entityManager);
			}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstudianteListDTO> obtenerEstudiante(EstudianteListDTO estudiante) throws SeguridadesException {
		
		slf4jLogger.info("obtenerEstudiante");
		
		List<EstudianteListDTO> estudiantes = null;
		
		try {
			
			Query query = entityManager.createQuery("select m from mat_estudiante_vie m");
			estudiantes = query.getResultList();
			
						
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
		
		return estudiantes;
	}
	
	@Override
	public List<EstudianteListDTO> getByAnd(EstudianteListDTO objectDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<EstudianteListDTO> cq;
		Root<EstudianteListDTO> from;
		List<EstudianteListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(EstudianteListDTO.class);
			
			from= cq.from(EstudianteListDTO.class);
			
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
			
			TypedQuery<EstudianteListDTO> tq=entityManager.createQuery(cq);
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
