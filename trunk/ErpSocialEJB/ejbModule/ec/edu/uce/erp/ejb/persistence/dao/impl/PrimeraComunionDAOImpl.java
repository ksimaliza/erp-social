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
import ec.edu.uce.erp.ejb.persistence.dao.PrimeraComunionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;

public class PrimeraComunionDAOImpl extends AbstractFacadeImpl<PrimeraComunionDTO> implements PrimeraComunionDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PrimeraComunionDAOImpl.class);
	
	public PrimeraComunionDAOImpl() {
		super();
		
	}

	public PrimeraComunionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<ComunionListDTO> obtenerComunion(ComunionListDTO comunion) throws SeguridadesException {
		
		slf4jLogger.info("obtenerComunion");
		List<ComunionListDTO> comunionEncontrada = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ComunionListDTO> cq=cb.createQuery(ComunionListDTO.class);
		Root<ComunionListDTO> from = cq.from(ComunionListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<ComunionListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(comunion.getPerNombres())) {
			Expression<String> nombreAsignado = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(comunion.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreAsignado);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(comunion.getPerApellidos())) {
					Expression<String> apellidoAsignado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(comunion.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoAsignado);
					criteriaList.add(predicate);
				}
		//por cedula
				if (!StringUtils.isEmpty(comunion.getPerCi())) {
					Expression<String> cedulaAsignado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(comunion.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaAsignado);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<ComunionListDTO> typedQuery = entityManager.createQuery(select);
		comunionEncontrada = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return comunionEncontrada;
}
	

}
