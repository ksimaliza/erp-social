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
import ec.edu.uce.erp.ejb.persistence.dao.ExumacionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionListDTO;

public class ExumacionDAOImpl extends AbstractFacadeImpl<ExumacionDTO> implements ExumacionDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ExumacionDAOImpl.class);
	
	public ExumacionDAOImpl() {
		super();
		
	}

	public ExumacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

	@Override
	public List<ExumacionListDTO> obtenerExhumacion(ExumacionListDTO exumacionListDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerExhumacion");
		List<ExumacionListDTO> exhumacionEncontrada = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ExumacionListDTO> cq=cb.createQuery(ExumacionListDTO.class);
		Root<ExumacionListDTO> from = cq.from(ExumacionListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<ExumacionListDTO> select = cq.select(from);
		
		//por nombre difunto
		if (!StringUtils.isEmpty(exumacionListDTO.getPerNombres())) {
			Expression<String> nombreDifunto = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(exumacionListDTO.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreDifunto);
			criteriaList.add(predicate);
		}
		//por apellidos difunto
				if (!StringUtils.isEmpty(exumacionListDTO.getPerApellidos())) {
					Expression<String> apellidoDifunto = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(exumacionListDTO.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoDifunto);
					criteriaList.add(predicate);
				}
		//por cedula difunto
				if (!StringUtils.isEmpty(exumacionListDTO.getPerCi())) {
					Expression<String> cedulaDifunto = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(exumacionListDTO.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaDifunto);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<ExumacionListDTO> typedQuery = entityManager.createQuery(select);
		exhumacionEncontrada = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return exhumacionEncontrada;
}
	
	
}
