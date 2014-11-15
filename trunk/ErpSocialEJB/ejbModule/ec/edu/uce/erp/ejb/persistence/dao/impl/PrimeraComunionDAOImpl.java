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
import ec.edu.uce.erp.ejb.persistence.dao.PrimeraComunionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;

public class PrimeraComunionDAOImpl extends AbstractFacadeImpl<PrimeraComunionDTO> implements PrimeraComunionDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PrimeraComunionDAOImpl.class);
	
	public PrimeraComunionDAOImpl() {
		super();
		
	}

	public PrimeraComunionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<ComunionListDTO> obtenerComunion(ComunionListDTO comunion) throws SeguridadesException {
		
		slf4jLogger.info("obtenerComunion");
		List<ComunionListDTO> comunionEncontrada = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ComunionListDTO> cq=cb.createQuery(ComunionListDTO.class);
		Root<ComunionListDTO> from = cq.from(ComunionListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<ComunionListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(comunion.getPerNombres())) {
			Expression<String> nombreAsignado = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(comunion.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreAsignado);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(comunion.getPerApellidos())) {
					Expression<String> apellidoAsignado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(comunion.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoAsignado);
					criteriaList.add(predicate);
				}
		//por cedula
				if (!StringUtils.isEmpty(comunion.getPerCi())) {
					Expression<String> cedulaAsignado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(comunion.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaAsignado);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<ComunionListDTO> typedQuery = entityManager.createQuery(select);
		comunionEncontrada = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return comunionEncontrada;
}
	
	
	@Override
	public List<ComunionListDTO> getDistinctReporteComunionByAnd(ComunionListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<ComunionListDTO> cq;
		Root<ComunionListDTO> from;
		List<ComunionListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(ComunionListDTO.class);
			
			from= cq.from(ComunionListDTO.class);
			
			cq.multiselect(
					from.get("catParroquia"),
					from.get("catProvincia"),
					from.get("catCanton"),
					from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres"),
					from.get("sacNombres"),
					from.get("sacApellidos"),
					from.get("pcoFechaHora"),
					from.get("pcoFechaAprobacionCurso")
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
	        	predicateList.add(cb.between(from.get("pcoFechaHora").as(Timestamp.class), objetoDTO.getFechaDesde(), objetoDTO.getFechaHasta()));	        
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<ComunionListDTO> tq=entityManager.createQuery(cq);
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
