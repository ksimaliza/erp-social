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
import ec.edu.uce.erp.ejb.persistence.dao.ConfirmacionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;

public class ConfirmacionDAOImpl extends AbstractFacadeImpl<ConfirmacionDTO> implements ConfirmacionDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ConfirmacionDAOImpl.class);
	
	public ConfirmacionDAOImpl() {
		super();
		
	}

	public ConfirmacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	@Override
	public List<ConfirmacionListDTO> obtenerConfirmacion(ConfirmacionListDTO confirmacion) throws SeguridadesException {
		
		slf4jLogger.info("obtenerConfirmacion");
		List<ConfirmacionListDTO> confirmacionEncontrada = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ConfirmacionListDTO> cq=cb.createQuery(ConfirmacionListDTO.class);
		Root<ConfirmacionListDTO> from = cq.from(ConfirmacionListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<ConfirmacionListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(confirmacion.getPerNombres())) {
			Expression<String> nombreConfirmado = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(confirmacion.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreConfirmado);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(confirmacion.getPerApellidos())) {
					Expression<String> apellidoConfirmado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(confirmacion.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoConfirmado);
					criteriaList.add(predicate);
				}
		//por cedula
				if (!StringUtils.isEmpty(confirmacion.getPerCi())) {
					Expression<String> cedulaConfirmado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(confirmacion.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaConfirmado);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<ConfirmacionListDTO> typedQuery = entityManager.createQuery(select);
		confirmacionEncontrada = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return confirmacionEncontrada;
}
}
