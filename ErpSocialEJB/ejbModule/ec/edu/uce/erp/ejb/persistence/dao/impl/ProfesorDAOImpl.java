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
import ec.edu.uce.erp.ejb.persistence.dao.ProfesorDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;

public class ProfesorDAOImpl extends AbstractFacadeImpl<ProfesorDTO> implements ProfesorDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public ProfesorDAOImpl() {
		super();
		
	}

	public ProfesorDAOImpl(EntityManager entityManager) {
		super(entityManager);

	}
	
	@Override
	public List<DocenteListDTO> obtenerDocente(DocenteListDTO docente) throws SeguridadesException {
		
		slf4jLogger.info("obtenerDocente");
		List<DocenteListDTO> docentesEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<DocenteListDTO> cq=cb.createQuery(DocenteListDTO.class);
		Root<DocenteListDTO> from = cq.from(DocenteListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<DocenteListDTO> select = cq.select(from);
		
		//por ci
				if (!StringUtils.isEmpty(docente.getPerCi())) {
					Expression<String> ci = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(docente.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), ci);
					criteriaList.add(predicate);
				}
		
		//por nombre
		if (!StringUtils.isEmpty(docente.getPerNombres())) {
			Expression<String> nombreDocente = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(docente.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreDocente);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(docente.getPerApellidos())) {
					Expression<String> apellidoEstudiante = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(docente.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoEstudiante);
					criteriaList.add(predicate);
				}
		
		//por empresa
				if (docente.getProEmpresa()!=null) {
					predicate = cb.equal(from.get("proEmpresa"),docente.getProEmpresa());
					criteriaList.add(predicate);
				}
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<DocenteListDTO> typedQuery = entityManager.createQuery(select);
		docentesEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return docentesEncontrados;
}
		

	@Override
	public List<DocenteListDTO> getByAnd(DocenteListDTO objectDTO) throws SeguridadesException
	{
		CriteriaBuilder cb;
		CriteriaQuery<DocenteListDTO> cq;
		Root<DocenteListDTO> from;
		List<DocenteListDTO> list;
		Predicate predicate;
		List<Predicate> predicateList = null;
		String fieldName;
		Method getter;
		Object value;
		Field[] fields;
		try{
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(DocenteListDTO.class);
			
			from= cq.from(DocenteListDTO.class);
			
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
			
			TypedQuery<DocenteListDTO> tq=entityManager.createQuery(cq);
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
