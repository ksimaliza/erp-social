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
import ec.edu.uce.erp.ejb.persistence.dao.NivelNichoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;

public class NivelNichoDAOImpl extends AbstractFacadeImpl<NivelNichoDTO> implements NivelNichoDAO {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(NivelNichoDAOImpl.class);

	public NivelNichoDAOImpl() {
		super();
		
	}

	public NivelNichoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

	
	@Override
	public List<NivelNichoDTO> obtenerNivelNicho(NivelNichoDTO nivelNichoDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerNivelNicho");
		List<NivelNichoDTO> nivelNichoEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<NivelNichoDTO> cq=cb.createQuery(NivelNichoDTO.class);
		Root<NivelNichoDTO> from = cq.from(NivelNichoDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<NivelNichoDTO> select = cq.select(from);
		
		//por descripcion
		if (!StringUtils.isEmpty(nivelNichoDTO.getNniDescripcion())) {
			Expression<String> descNivelNicho = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(nivelNichoDTO.getNniDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("nniDescripcion")), descNivelNicho);
			criteriaList.add(predicate);
		}
		//por empresa
				if (nivelNichoDTO.getNniEmpresa()!=null) {
					predicate = cb.equal(from.get("nniEmpresa"), nivelNichoDTO.getNniEmpresa());
					criteriaList.add(predicate);
				}
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<NivelNichoDTO> typedQuery = entityManager.createQuery(select);
		nivelNichoEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return nivelNichoEncontrado;
}
}
