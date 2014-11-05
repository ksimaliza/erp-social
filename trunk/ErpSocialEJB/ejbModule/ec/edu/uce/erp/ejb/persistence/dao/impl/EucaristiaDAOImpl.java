package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
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
import ec.edu.uce.erp.ejb.persistence.dao.EucaristiaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.FiltroFechaDTO;

public class EucaristiaDAOImpl extends AbstractFacadeImpl<EucaristiaDTO> implements EucaristiaDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EucaristiaDAOImpl.class);
	
	public EucaristiaDAOImpl() {
		super();
		
	}

	public EucaristiaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

	@Override
	public List<EucaristiaListDTO> obtenerEucaristia(EucaristiaListDTO eucaristiaListDTO, FiltroFechaDTO filtro) throws SeguridadesException {
		
		slf4jLogger.info("obtenerEucaristia");
		List<EucaristiaListDTO> eucaristiaEncontrada = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		CriteriaBuilder cb;
		CriteriaQuery<EucaristiaListDTO> cq;
		Root<EucaristiaListDTO> from;
		
		CriteriaQuery<EucaristiaListDTO> select;
		try {
			cb=entityManager.getCriteriaBuilder();
			cq=cb.createQuery(EucaristiaListDTO.class);
			from = cq.from(EucaristiaListDTO.class);
			
			criteriaList = new ArrayList<Predicate>();
		
		
			select = cq.select(from);
		
			//por apellidos sacerdote
			if (!StringUtils.isEmpty(eucaristiaListDTO.getPerApellidos())) {
				Expression<String> sacerdoteApellidos = 
						cb.upper(cb.literal
								(UtilAplication.concatenarPorcenteje(eucaristiaListDTO.getPerApellidos())));
				predicate = cb.like(cb.upper(from.<String>get("perApellidos")), sacerdoteApellidos);
				criteriaList.add(predicate);
			}
		
			//por nombre sacerdote
			if (!StringUtils.isEmpty(eucaristiaListDTO.getPerNombres())) {
				Expression<String> sacerdoteNombres = 
						cb.upper(cb.literal
								(UtilAplication.concatenarPorcenteje(eucaristiaListDTO.getPerNombres())));
				predicate = cb.like(cb.upper(from.<String>get("perNombres")), sacerdoteNombres);
				criteriaList.add(predicate);
			}
				
			//por fecha
			if (eucaristiaListDTO.getEucFechaHora()!=null) {
				predicate=cb.equal(from.get("eucFechaHora").as(Date.class), eucaristiaListDTO.getEucFechaHora());
				criteriaList.add(predicate);
			}
				
			//por año
			if (filtro.getAnio()!=null){
				predicate=cb.equal(cb.function("year", Integer.class, from.get("eucFechaHora")),filtro.getAnio());
				criteriaList.add(predicate);
			}
			
			//por mes
			if (filtro.getMes()!=null){
				predicate=cb.equal(cb.function("month", Integer.class, from.get("eucFechaHora")),filtro.getMes());
				criteriaList.add(predicate);
			}
			
			//por dia
			if (filtro.getDia()!=null){
				predicate=cb.equal(cb.function("day", Integer.class, from.get("eucFechaHora")),filtro.getDia());
				criteriaList.add(predicate);
			}
		
		
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<EucaristiaListDTO> typedQuery = entityManager.createQuery(select);
		eucaristiaEncontrada = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return eucaristiaEncontrada;
}
	
	
	
	
}
