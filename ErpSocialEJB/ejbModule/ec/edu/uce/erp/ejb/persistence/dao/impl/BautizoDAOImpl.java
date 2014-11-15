package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
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
import ec.edu.uce.erp.ejb.persistence.dao.BautizoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;

public class BautizoDAOImpl extends AbstractFacadeImpl<BautizoDTO>implements BautizoDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(BautizoDAOImpl.class);
	
	public BautizoDAOImpl() {
		super();
		
	}

	public BautizoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<BautizoListDTO> obtenerBautizo(BautizoListDTO bautizo) throws SeguridadesException {
		
		slf4jLogger.info("obtenerBautizo");
		List<BautizoListDTO> bautizoEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<BautizoListDTO> cq=cb.createQuery(BautizoListDTO.class);
		Root<BautizoListDTO> from = cq.from(BautizoListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<BautizoListDTO> select = cq.select(from);
		
		//por cedula
		if (!StringUtils.isEmpty(bautizo.getPerCi())) {
			Expression<String> cedulaBautizado = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(bautizo.getPerCi())));
			predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaBautizado);
			criteriaList.add(predicate);
		}
		
		
		//por nombre
		if (!StringUtils.isEmpty(bautizo.getPerNombres())) {
			Expression<String> nombreBautizado = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(bautizo.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreBautizado);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(bautizo.getPerApellidos())) {
					Expression<String> apellidoBautizado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(bautizo.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoBautizado);
					criteriaList.add(predicate);
				}
		
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<BautizoListDTO> typedQuery = entityManager.createQuery(select);
		bautizoEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return bautizoEncontrado;
}

	@Override
	public List<BautizoListDTO> getDistinctReporteBautizoByAnd(BautizoListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<BautizoListDTO> cq;
		Root<BautizoListDTO> from;
		List<BautizoListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(BautizoListDTO.class);
			
			from= cq.from(BautizoListDTO.class);
			
			cq.multiselect(
					from.get("bauParroquia"),
					from.get("bauProvincia"),
					from.get("bauCanton"),
					from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres"),
					from.get("sacNombres"),
					from.get("sacApellidos"),
					from.get("bauFechaAprobacionCruso"),
					from.get("bauFechaBautizo")
					).distinct(true);
			
			predicateList=new ArrayList<Predicate>();
			
			fields = objetoDTO.getClass().getDeclaredFields();

	        for(Field f : fields){
	            fieldName = f.getName();
				if(!fieldName.equals("serialVersionUID")&&!fieldName.equals("fechaDesde")&&!fieldName.equals("fechaHasta"))
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
	        
	        if(objetoDTO.getFechaDesde()!=null && objetoDTO.getFechaHasta()!=null)
	        	predicateList.add(cb.between(from.get("bauFechaBautizo").as(Timestamp.class), objetoDTO.getFechaDesde(), objetoDTO.getFechaHasta()));	        
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<BautizoListDTO> tq=entityManager.createQuery(cq);
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
