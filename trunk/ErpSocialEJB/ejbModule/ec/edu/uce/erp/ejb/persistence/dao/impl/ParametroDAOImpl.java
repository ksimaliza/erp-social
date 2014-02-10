/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_INVALIDO;

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
import ec.edu.uce.erp.ejb.persistence.dao.ParametroDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Parametro;

/**
 * @author 
 *
 */
public class ParametroDAOImpl extends AbstractFacadeImpl<Parametro> implements ParametroDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ParametroDAOImpl.class);
	
	public ParametroDAOImpl () {}
	
	public ParametroDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Parametro> buscarParametroCriterios(Parametro parametro) throws SeguridadesException {
		
		slf4jLogger.info("buscarParametroCriterios");
		
		List<Parametro> colParametrosEncontrados = null;
		List<Predicate> criteriaList = null;
		Predicate predicate = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Parametro> criteriaQuery = criteriaBuilder.createQuery(Parametro.class);
			Root<Parametro> rootParametro = criteriaQuery.from(Parametro.class);
			
			criteriaList = new ArrayList<Predicate>();
			
			CriteriaQuery<Parametro> select = criteriaQuery.select(rootParametro);
			
			//por nombre
			if (!StringUtils.isEmpty(parametro.getNombreParametro())) {
				Expression<String> nombreParametro = 
						criteriaBuilder.upper(criteriaBuilder.literal
								(UtilAplication.concatenarPorcenteje(parametro.getNombreParametro())));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(rootParametro.<String>get("nombreParametro")), nombreParametro);
				criteriaList.add(predicate);
			}
			
			//por id
			if (parametro.getIdParametro() != null) {
				predicate = criteriaBuilder.equal(rootParametro.get("idParametro"), parametro.getIdParametro());
				criteriaList.add(predicate);
			}
			
			//por estado -- 
			if (!StringUtils.isEmpty(parametro.getEstado()) && !parametro.getEstado().equals(ESTADO_INVALIDO)) {
				predicate = criteriaBuilder.equal(rootParametro.get("estado"), parametro.getEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<Parametro> typedQuery = entityManager.createQuery(select);
			colParametrosEncontrados = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		
		return colParametrosEncontrados;
	}

}
