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
import ec.edu.uce.erp.ejb.persistence.dao.TipoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;

public class TipoDAOImpl extends AbstractFacadeImpl<TipoDTO> implements TipoDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public TipoDAOImpl() {
		super();
	}

	public TipoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<TipoDTO> obtenerTipo(TipoDTO tipo) throws SeguridadesException {
		
		slf4jLogger.info("obtenerTipo");
		List<TipoDTO> tiposEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoDTO> cq=cb.createQuery(TipoDTO.class);
		Root<TipoDTO> from = cq.from(TipoDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<TipoDTO> select = cq.select(from);
		
		//por descripcion
		if (!StringUtils.isEmpty(tipo.getTipDescripcion())) {
			Expression<String> descripcion = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(tipo.getTipDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("tipDescripcion")), descripcion);
			criteriaList.add(predicate);
		}
		
			
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<TipoDTO> typedQuery = entityManager.createQuery(select);
		tiposEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return tiposEncontrados;
}
	
	
	
	@Override
	public List<TipoDTO> getByAnd(TipoDTO objetoDTO) throws SecurityException
	{
		CriteriaBuilder cb;
		CriteriaQuery<TipoDTO> cq;
		Root<TipoDTO> from;
		List<TipoDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		TypedQuery<TipoDTO> typedQuery;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(TipoDTO.class);
			from= cq.from(TipoDTO.class);
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
			typedQuery=entityManager.createQuery(cq);
			list=typedQuery.getResultList();
			return list;
		}catch(Exception e){
			slf4jLogger.info(e.toString());
			throw new SecurityException(e);
		}finally{
			 predicate=null;
             predicateList=null;
             cb=null;
             cq=null;
             typedQuery=null;
             from=null;
             fieldName=null;
             getter=null;
             value=null;
             fields=null;		
        }		
	}
	

}
