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
import ec.edu.uce.erp.ejb.persistence.dao.BautizoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;

public class BautizoDAOImpl extends AbstractFacadeImpl<BautizoDTO>implements BautizoDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(BautizoDAOImpl.class);
	
	public BautizoDAOImpl() {
		super();
		
	}

	public BautizoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<BautizoListDTO> obtenerBautizo(BautizoListDTO bautizo) throws SeguridadesException {
		
		slf4jLogger.info("obtenerBautizo");
		List<BautizoListDTO> bautizoEncontrado = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<BautizoListDTO> cq=cb.createQuery(BautizoListDTO.class);
		Root<BautizoListDTO> from = cq.from(BautizoListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<BautizoListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(bautizo.getPerNombres())) {
			Expression<String> nombreBautizado = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(bautizo.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreBautizado);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(bautizo.getPerApellidos())) {
					Expression<String> apellidoBautizado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(bautizo.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoBautizado);
					criteriaList.add(predicate);
				}
		//por cedula
				if (!StringUtils.isEmpty(bautizo.getPerCi())) {
					Expression<String> cedulaBautizado = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(bautizo.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaBautizado);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<BautizoListDTO> typedQuery = entityManager.createQuery(select);
		bautizoEncontrado = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return bautizoEncontrado;
}

}
