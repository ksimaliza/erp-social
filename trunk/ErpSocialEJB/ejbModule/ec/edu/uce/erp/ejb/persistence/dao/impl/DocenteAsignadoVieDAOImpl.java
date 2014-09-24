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
import ec.edu.uce.erp.ejb.persistence.dao.DocenteAsignadoVieDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteAsignadoVieDTO;

public class DocenteAsignadoVieDAOImpl extends AbstractFacadeImpl<DocenteAsignadoVieDTO> implements DocenteAsignadoVieDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MatriculaVieDAOImpl.class);

	public DocenteAsignadoVieDAOImpl() {
		super();
	}

	public DocenteAsignadoVieDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}


	@Override
	public List<DocenteAsignadoVieDTO> getByAndDistinct(DocenteAsignadoVieDTO objectDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<DocenteAsignadoVieDTO> cq;
		Root<DocenteAsignadoVieDTO> from;
		List<DocenteAsignadoVieDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(DocenteAsignadoVieDTO.class);
			
			from= cq.from(DocenteAsignadoVieDTO.class);
			
			cq.multiselect(from.get("proCodigo"),
					from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres")).distinct(true);
			
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
			
			TypedQuery<DocenteAsignadoVieDTO> tq=entityManager.createQuery(cq);
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
