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
import ec.edu.uce.erp.ejb.persistence.dao.FaltaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;

public class FaltaDAOImpl extends AbstractFacadeImpl<FaltaDTO> implements FaltaDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(FaltaDAOImpl.class);
	
	public FaltaDAOImpl() {
		super();
	}

	public FaltaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
	@Override
	public List<FaltaListDTO> findAll(FaltaListDTO falta) throws SeguridadesException
	{
		slf4jLogger.info("findAll");
		List<FaltaListDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<FaltaListDTO> cq=cb.createQuery(FaltaListDTO.class);
		Root<FaltaListDTO> from = cq.from(FaltaListDTO.class);
		
		CriteriaQuery<FaltaListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(falta.getPerNombres())) {
			Expression<String> nombre = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(falta.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombre);
			criteriaList.add(predicate);
		}
		//por apellidos
		if (!StringUtils.isEmpty(falta.getPerApellidos())) {
			Expression<String> apellido = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(falta.getPerApellidos())));
			predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellido);
			criteriaList.add(predicate);
		}
				
		if(criteriaList!=null && criteriaList.size()>0)
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<FaltaListDTO> typedQuery = entityManager.createQuery(select);
		resultado = typedQuery.getResultList();
		
		criteriaList = new ArrayList<Predicate>();
		
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;
	}
}
