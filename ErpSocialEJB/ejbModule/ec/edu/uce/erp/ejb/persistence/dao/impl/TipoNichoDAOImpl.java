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
import ec.edu.uce.erp.ejb.persistence.dao.TipoNichoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;

public class TipoNichoDAOImpl extends AbstractFacadeImpl<TipoNichoDTO> implements TipoNichoDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(TipoNichoDAOImpl.class);
	public TipoNichoDAOImpl() {
		super();
		
	}

	public TipoNichoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<TipoNichoDTO> obtenerTipoNicho(TipoNichoDTO tipoNichoDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerMateria");
		List<TipoNichoDTO> tipoNichoEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoNichoDTO> cq=cb.createQuery(TipoNichoDTO.class);
		Root<TipoNichoDTO> from = cq.from(TipoNichoDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		
		
		CriteriaQuery<TipoNichoDTO> select = cq.select(from);
		
		//por descripcion
		if (!StringUtils.isEmpty(tipoNichoDTO.getTniDescripcion())) {
			Expression<String> descTipoNicho = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(tipoNichoDTO.getTniDescripcion())));
			predicate = cb.like(cb.upper(from.<String>get("tniDescripcion")), descTipoNicho);
			criteriaList.add(predicate);
		}
		
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<TipoNichoDTO> typedQuery = entityManager.createQuery(select);
		tipoNichoEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return tipoNichoEncontrado;
}

}
