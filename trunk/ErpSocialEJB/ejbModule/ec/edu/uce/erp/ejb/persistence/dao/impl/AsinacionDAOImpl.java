package ec.edu.uce.erp.ejb.persistence.dao.impl;

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
import ec.edu.uce.erp.ejb.persistence.dao.AsinacionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;

public class AsinacionDAOImpl extends AbstractFacadeImpl<AsinacionDTO> implements AsinacionDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(FaltaDAOImpl.class);	
	
	public AsinacionDAOImpl() {
		super();
		
	}

	public AsinacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
	
	}
	
	@Override
	public List<AsinacionListDTO> findAll(AsinacionListDTO asinacion) throws SeguridadesException
	{
		slf4jLogger.info("findAll");
		List<AsinacionListDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<AsinacionListDTO> cq=cb.createQuery(AsinacionListDTO.class);
		Root<AsinacionListDTO> from = cq.from(AsinacionListDTO.class);
		
		CriteriaQuery<AsinacionListDTO> select = cq.select(from);
		
		//por profesor
		if (!StringUtils.isEmpty(asinacion.getPerApellidos())) {
			Expression<String> apellidos = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(asinacion.getPerApellidos())));
			predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidos);
			criteriaList.add(predicate);
	}
		//por materia
		if (!StringUtils.isEmpty(asinacion.getPerNombres())) {
			Expression<String> nombres = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(asinacion.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombres);
			criteriaList.add(predicate);
		}
				
		if(criteriaList!=null && criteriaList.size()>0)
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<AsinacionListDTO> typedQuery = entityManager.createQuery(select);
		resultado = typedQuery.getResultList();
		
		criteriaList = new ArrayList<Predicate>();
		
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;
	}
	
}
