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
import ec.edu.uce.erp.ejb.persistence.dao.NichoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;

public class NichoDAOImpl extends AbstractFacadeImpl<NichoDTO> implements NichoDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(NichoDAOImpl.class);
	public NichoDAOImpl() {
		super();
		
	}

	public NichoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

	@Override
	public List<NichoListDTO> obtenerNicho(NichoListDTO nichoListDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerMateria");
		List<NichoListDTO> nichoEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<NichoListDTO> cq=cb.createQuery(NichoListDTO.class);
		Root<NichoListDTO> from = cq.from(NichoListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<NichoListDTO> select = cq.select(from);
		
		//por descripcion
		if (!StringUtils.isEmpty(nichoListDTO.getNicDescripcion())) {
			Expression<String> descNicho = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(nichoListDTO.getNicDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("nicDescripcion")), descNicho);
			criteriaList.add(predicate);
		}
		
		//por codigo
		if (nichoListDTO.getNicCodigo()!=null) {
			predicate = cb.equal(from.get("nicCodigo"), nichoListDTO.getNicCodigo());
			criteriaList.add(predicate);
		}
		
		//por empresa
		if (nichoListDTO.getNicEmpresa()!=null) {
			predicate = cb.equal(from.get("nicEmpresa"), nichoListDTO.getNicEmpresa());
			criteriaList.add(predicate);
		}
		//por estado
		if (StringUtils.isNotBlank(nichoListDTO.getNicEstado())) {
			predicate = cb.equal(from.get("nicEstado"), nichoListDTO.getNicEstado());
			criteriaList.add(predicate);
		}
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<NichoListDTO> typedQuery = entityManager.createQuery(select);
		nichoEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return nichoEncontrado;
}
	
	
	@Override
	public List<NichoListDTO> getByAnd(NichoListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<NichoListDTO> cq;
		Root<NichoListDTO> from;
		List<NichoListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(NichoListDTO.class);
			
			from= cq.from(NichoListDTO.class);
			
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
			
			TypedQuery<NichoListDTO> tq=entityManager.createQuery(cq);
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
