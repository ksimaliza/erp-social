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
import ec.edu.uce.erp.ejb.persistence.dao.NivelDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;

public class NivelDAOImpl extends AbstractFacadeImpl<NivelDTO> implements NivelDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public NivelDAOImpl() {
		super();
	
	}

	public NivelDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

	@Override
	public List<NivelDTO> obtenerNivel(NivelDTO nivel) throws SeguridadesException {
		
		slf4jLogger.info("obtenerNivel");
		List<NivelDTO> nivelesEncontrados = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<NivelDTO> cq=cb.createQuery(NivelDTO.class);
		Root<NivelDTO> from = cq.from(NivelDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<NivelDTO> select = cq.select(from);
		
		//por descripcion
		if (!StringUtils.isEmpty(nivel.getNivDescaripcion())) {
			Expression<String> descNivel = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(nivel.getNivDescaripcion())));
			predicate = cb.like(cb.upper(from.<String>get("nivDescaripcion")), descNivel);
			criteriaList.add(predicate);
		}
			
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<NivelDTO> typedQuery = entityManager.createQuery(select);
		nivelesEncontrados = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return nivelesEncontrados;
}
	
	
}
