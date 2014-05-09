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
import ec.edu.uce.erp.ejb.persistence.dao.NivelParaleloDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloListDTO;

public class NivelParaleloDAOImpl extends AbstractFacadeImpl<NivelParaleloDTO> implements NivelParaleloDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(FaltaDAOImpl.class);
	
	public NivelParaleloDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NivelParaleloDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<NivelParaleloListDTO> findAll(NivelParaleloListDTO nivelParalelo) throws SeguridadesException
	{
		slf4jLogger.info("findAll");
		List<NivelParaleloListDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<NivelParaleloListDTO> cq=cb.createQuery(NivelParaleloListDTO.class);
		Root<NivelParaleloListDTO> from = cq.from(NivelParaleloListDTO.class);
		
		CriteriaQuery<NivelParaleloListDTO> select = cq.select(from);
		
		//por paralelo
		if (!StringUtils.isEmpty(nivelParalelo.getParDescripcion())) {
			Expression<String> descripcion = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(nivelParalelo.getParDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("parDescripcioin")), descripcion);
			criteriaList.add(predicate);
		}
		//por nivel
		if (!StringUtils.isEmpty(nivelParalelo.getNivDescaripcion())) {
			Expression<String> descripcionNivel = cb.upper(cb.literal(UtilAplication.concatenarPorcenteje(nivelParalelo.getNivDescaripcion())));
			predicate = cb.like(cb.upper(from.<String>get("nivDescaripcion")), descripcionNivel);
			criteriaList.add(predicate);
		}
				
		if(criteriaList!=null && criteriaList.size()>0)
			cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<NivelParaleloListDTO> typedQuery = entityManager.createQuery(select);
		resultado = typedQuery.getResultList();
		
		criteriaList = new ArrayList<Predicate>();
		
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;
	}

}
