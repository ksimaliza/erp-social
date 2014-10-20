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
import ec.edu.uce.erp.ejb.persistence.dao.RepresentanteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteListDTO;

public class RepresentanteDAOImpl extends AbstractFacadeImpl<RepresentanteDTO> implements RepresentanteDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public RepresentanteDAOImpl() {
		super();
	
	}

	public RepresentanteDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	
	@Override
	public List<RepresentanteListDTO> getByAnd(RepresentanteListDTO objectDTO) throws SeguridadesException {
		
		CriteriaBuilder cb;
		CriteriaQuery<RepresentanteListDTO> cq;
		Root<RepresentanteListDTO> from;
		List<RepresentanteListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(RepresentanteListDTO.class);
			
			from= cq.from(RepresentanteListDTO.class);
			
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
			
			TypedQuery<RepresentanteListDTO> tq=entityManager.createQuery(cq);
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
	
	
	@Override
	public List<RepresentanteListDTO> obtenerRepresentante(RepresentanteListDTO representante) throws SeguridadesException {
		
		slf4jLogger.info("obtenerRepresentante");
		List<RepresentanteListDTO> representantesEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<RepresentanteListDTO> cq=cb.createQuery(RepresentanteListDTO.class);
		Root<RepresentanteListDTO> from = cq.from(RepresentanteListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
			
		
		CriteriaQuery<RepresentanteListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(representante.getPerNombres())) {
			Expression<String> nombreRepresentante = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(representante.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreRepresentante);
			criteriaList.add(predicate);
		}
		//por apellidos
		if (!StringUtils.isEmpty(representante.getPerApellidos())) {
			Expression<String> apellidoRepresentante = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(representante.getPerApellidos())));
			predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoRepresentante);
			criteriaList.add(predicate);
		}
		//por empresa
		if (representante.getRepEmpresa()!=null) {
			predicate = cb.equal(from.get("repEmpresa"), representante.getRepEmpresa());
			criteriaList.add(predicate);
		}		
						
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<RepresentanteListDTO> typedQuery = entityManager.createQuery(select);
		representantesEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return representantesEncontrados;
}
		

}
