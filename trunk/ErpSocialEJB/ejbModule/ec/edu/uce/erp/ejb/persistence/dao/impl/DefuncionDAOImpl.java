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
import ec.edu.uce.erp.ejb.persistence.dao.DefuncionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;

public class DefuncionDAOImpl extends AbstractFacadeImpl<DefuncionDTO> implements DefuncionDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(DefuncionDAOImpl.class);
	
	public DefuncionDAOImpl() {
		super();
		
	}

	public DefuncionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<DefuncionListDTO> obtenerDefuncion(DefuncionListDTO defuncion) throws SeguridadesException {
		
		slf4jLogger.info("obtenerDefuncion");
		List<DefuncionListDTO> defuncionEncontrada = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<DefuncionListDTO> cq=cb.createQuery(DefuncionListDTO.class);
		Root<DefuncionListDTO> from = cq.from(DefuncionListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<DefuncionListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(defuncion.getPerNombres())) {
			Expression<String> nombreDifunto = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(defuncion.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreDifunto);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(defuncion.getPerApellidos())) {
					Expression<String> apellidoDifunto = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(defuncion.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoDifunto);
					criteriaList.add(predicate);
				}
		//por cedula
				if (!StringUtils.isEmpty(defuncion.getPerCi())) {
					Expression<String> cedulaDifunto = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(defuncion.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaDifunto);
					criteriaList.add(predicate);
				}
				//por empresa
				if (defuncion.getDefEmpresa()!=null) {
					predicate = cb.equal(from.get("defEmpresa"), defuncion.getDefEmpresa());
					criteriaList.add(predicate);
				}
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<DefuncionListDTO> typedQuery = entityManager.createQuery(select);
		defuncionEncontrada = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return defuncionEncontrada;
}
	
	@Override
	public List<DefuncionListDTO> getDistinctReporteDefuncionByAnd(DefuncionListDTO objetoDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<DefuncionListDTO> cq;
		Root<DefuncionListDTO> from;
		List<DefuncionListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(DefuncionListDTO.class);
			
			from= cq.from(DefuncionListDTO.class);
			
			cq.multiselect(
					from.get("defParroquia"),
					from.get("defProvincia"),
					from.get("defCanton"),
					from.get("perCi"),
					from.get("perApellidos"),
					from.get("perNombres"),
					from.get("sacNombres"),
					from.get("sacApellidos"),
					from.get("defFecha")
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
	        	predicateList.add(cb.between(from.get("defFecha").as(Timestamp.class), objetoDTO.getFechaDesde(), objetoDTO.getFechaHasta()));	        
	
	        if(!predicateList.isEmpty())
	        	cq.where(cb.and(predicateList.toArray(new Predicate[0])));		
			
			TypedQuery<DefuncionListDTO> tq=entityManager.createQuery(cq);
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
