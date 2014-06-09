package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.CatalogoAsistenciaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.CatalogoAsistenciaDTO;

@Stateless
public class CatalogoAsistenciaDAOImpl extends AbstractFacadeImpl<CatalogoAsistenciaDTO> implements CatalogoAsistenciaDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(CatalogoAsistenciaDAOImpl.class);

	public CatalogoAsistenciaDAOImpl() {
		super();
	}

	public CatalogoAsistenciaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<CatalogoAsistenciaDTO> getByAnd(CatalogoAsistenciaDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<CatalogoAsistenciaDTO> cq;
		Root<CatalogoAsistenciaDTO> from;
		List<CatalogoAsistenciaDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(CatalogoAsistenciaDTO.class);
			
			from= cq.from(CatalogoAsistenciaDTO.class);
			
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
			
			TypedQuery<CatalogoAsistenciaDTO> tq=entityManager.createQuery(cq);
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
