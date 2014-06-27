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
import ec.edu.uce.erp.ejb.persistence.dao.ParaleloDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;

public class ParaleloDAOImpl extends AbstractFacadeImpl<ParaleloDTO> implements ParaleloDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public ParaleloDAOImpl() {
		super();
		
	}

	public ParaleloDAOImpl(EntityManager entityManager) {
		super(entityManager);

	}

	@Override
	public List<ParaleloDTO> obtenerParalelo(ParaleloDTO paralelo) throws SeguridadesException {
		
		slf4jLogger.info("obtenerParalelo");
		List<ParaleloDTO> paraleloEncontradas = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ParaleloDTO> cq=cb.createQuery(ParaleloDTO.class);
		Root<ParaleloDTO> from = cq.from(ParaleloDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<ParaleloDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(paralelo.getParDescripcion())) {
			Expression<String> descParalelo = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(paralelo.getParDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("parDescripcion")), descParalelo);
			criteriaList.add(predicate);
		}
		
		//por empresa
		if (paralelo.getParEmpresa()!=null) {
			predicate = cb.equal(from.get("parEmpresa"),paralelo.getParEmpresa());
			criteriaList.add(predicate);
		}
			
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<ParaleloDTO> typedQuery = entityManager.createQuery(select);
		paraleloEncontradas = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return paraleloEncontradas;
}
	
}
