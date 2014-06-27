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
import ec.edu.uce.erp.ejb.persistence.dao.PeriodoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;

public class PeriodoDAOImpl extends AbstractFacadeImpl<PeriodoDTO> implements PeriodoDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public PeriodoDAOImpl() {
		super();

	}

	public PeriodoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	
	}

	
	@Override
	public List<PeriodoDTO> obtenerPeriodo(PeriodoDTO periodo) throws SeguridadesException {
		
		slf4jLogger.info("obtenerPeriodo");
		List<PeriodoDTO> periodosEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<PeriodoDTO> cq=cb.createQuery(PeriodoDTO.class);
		Root<PeriodoDTO> from = cq.from(PeriodoDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<PeriodoDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(periodo.getPerDescripcion())) {
			Expression<String> nombrePeriodo = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(periodo.getPerDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("perDescripcion")), nombrePeriodo);
			criteriaList.add(predicate);
		}
		
		//por empresa
				if (periodo.getPerEmpresa()!=null) {
					predicate = cb.equal(from.get("perEmpresa"), periodo.getPerEmpresa());
					criteriaList.add(predicate);
				}
			
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<PeriodoDTO> typedQuery = entityManager.createQuery(select);
		periodosEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return periodosEncontrados;
}
	
	
}
