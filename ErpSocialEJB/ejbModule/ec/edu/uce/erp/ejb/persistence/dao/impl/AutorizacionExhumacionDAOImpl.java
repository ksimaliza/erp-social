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
import ec.edu.uce.erp.ejb.persistence.dao.AutorizacionExhumacionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionListDTO;

public class AutorizacionExhumacionDAOImpl extends AbstractFacadeImpl<AutorizaExhumacionDTO> implements AutorizacionExhumacionDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(AutorizacionExhumacionDAOImpl.class);

	public AutorizacionExhumacionDAOImpl() {
		super();
		
	}

	public AutorizacionExhumacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	
	@Override
	public List<AutorizaExhumacionListDTO> obtenerAutorizacion(AutorizaExhumacionListDTO autorizaExhumacionListDTO) throws SeguridadesException {
		
		slf4jLogger.info("obtenerAutorizacion");
		List<AutorizaExhumacionListDTO> autorizacionEncontrada = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<AutorizaExhumacionListDTO> cq=cb.createQuery(AutorizaExhumacionListDTO.class);
		Root<AutorizaExhumacionListDTO> from = cq.from(AutorizaExhumacionListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<AutorizaExhumacionListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(autorizaExhumacionListDTO.getPerNombres())) {
			Expression<String> nombreSacerdote = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(autorizaExhumacionListDTO.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreSacerdote);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(autorizaExhumacionListDTO.getPerApellidos())) {
					Expression<String> apellidoSacerdote = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(autorizaExhumacionListDTO.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoSacerdote);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<AutorizaExhumacionListDTO> typedQuery = entityManager.createQuery(select);
		autorizacionEncontrada = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return autorizacionEncontrada;
}
	
	
	
}
