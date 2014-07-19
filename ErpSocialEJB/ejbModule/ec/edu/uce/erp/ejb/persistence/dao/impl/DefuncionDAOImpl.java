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
import ec.edu.uce.erp.ejb.persistence.dao.DefuncionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;

public class DefuncionDAOImpl extends AbstractFacadeImpl<DefuncionDTO> implements DefuncionDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(DefuncionDAOImpl.class);
	
	public DefuncionDAOImpl() {
		super();
		
	}

	public DefuncionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	@Override
	public List<DefuncionListDTO> obtenerDefuncion(DefuncionListDTO defuncion) throws SeguridadesException {
		
		slf4jLogger.info("obtenerDefuncion");
		List<DefuncionListDTO> defuncionEncontrada = null;
		
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<DefuncionListDTO> cq=cb.createQuery(DefuncionListDTO.class);
		Root<DefuncionListDTO> from = cq.from(DefuncionListDTO.class);
		
		criteriaList = new ArrayList<Predicate>();
		
		CriteriaQuery<DefuncionListDTO> select = cq.select(from);
		
		//por nombre
		if (!StringUtils.isEmpty(defuncion.getPerNombres())) {
			Expression<String> nombreDifunto = 
					cb.upper(cb.literal
							(UtilAplication.concatenarPorcenteje(defuncion.getPerNombres())));
			predicate = cb.like(cb.upper(from.<String>get("perNombres")), nombreDifunto);
			criteriaList.add(predicate);
		}
		//por apellidos
				if (!StringUtils.isEmpty(defuncion.getPerApellidos())) {
					Expression<String> apellidoDifunto = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(defuncion.getPerApellidos())));
					predicate = cb.like(cb.upper(from.<String>get("perApellidos")), apellidoDifunto);
					criteriaList.add(predicate);
				}
		//por cedula
				if (!StringUtils.isEmpty(defuncion.getPerCi())) {
					Expression<String> cedulaDifunto = 
							cb.upper(cb.literal
									(UtilAplication.concatenarPorcenteje(defuncion.getPerCi())));
					predicate = cb.like(cb.upper(from.<String>get("perCi")), cedulaDifunto);
					criteriaList.add(predicate);
				}
		
				
		cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
		
		TypedQuery<DefuncionListDTO> typedQuery = entityManager.createQuery(select);
		defuncionEncontrada = typedQuery.getResultList();
		
	} catch (Exception e) {
		slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
		throw new SeguridadesException(e);
	}
	
	return defuncionEncontrada;
}

}
