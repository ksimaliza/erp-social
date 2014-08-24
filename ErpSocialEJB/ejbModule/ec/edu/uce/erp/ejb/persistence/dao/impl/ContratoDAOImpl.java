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
import ec.edu.uce.erp.ejb.persistence.dao.ContratoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;

public class ContratoDAOImpl extends AbstractFacadeImpl<ContratoDTO> implements ContratoDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ConfirmacionDAOImpl.class);
	public ContratoDAOImpl() {
		super();
		
	}

	public ContratoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

	@Override
	public List<ContratoListDTO> obtenerContrato(ContratoListDTO contratoListDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerContrato");
		List<ContratoListDTO> contratoEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ContratoListDTO> cq=cb.createQuery(ContratoListDTO.class);
		Root<ContratoListDTO> from = cq.from(ContratoListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<ContratoListDTO> select = cq.select(from);
		
		//por nombre difunto
		if (!StringUtils.isEmpty(contratoListDTO.getPerNombres())) {
			Expression<String> nombreDifunto = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(contratoListDTO.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreDifunto);
			criteriaList.add(predicate);
		}
		//por apellidos difunto
				if (!StringUtils.isEmpty(contratoListDTO.getPerApellidos())) {
					Expression<String> apellidoDifunto = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(contratoListDTO.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoDifunto);
					criteriaList.add(predicate);
				}
		//por cedula difunto
				if (!StringUtils.isEmpty(contratoListDTO.getPerCi())) {
					Expression<String> cedulaDifunto = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(contratoListDTO.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaDifunto);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<ContratoListDTO> typedQuery = entityManager.createQuery(select);
		contratoEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return contratoEncontrado;
}
	
}
