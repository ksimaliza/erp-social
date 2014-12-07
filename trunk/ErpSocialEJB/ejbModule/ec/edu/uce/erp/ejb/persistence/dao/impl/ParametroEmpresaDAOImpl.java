/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.ParametroEmpresaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.ParametroEmpresa;

/**
 * @author 
 *
 */
public class ParametroEmpresaDAOImpl extends AbstractFacadeImpl<ParametroEmpresa> implements ParametroEmpresaDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ParametroEmpresaDAOImpl.class);
	
	public ParametroEmpresaDAOImpl () {}
	
	public ParametroEmpresaDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<ParametroEmpresa> obtenerParametroEmpresaCriterios(ParametroEmpresa parametroEmpresa) throws SeguridadesException {
		
		slf4jLogger.info("obtenerParametroEmpresaCriterios");
		
		List<ParametroEmpresa> parametroEmpresaCol = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<ParametroEmpresa> criteriaQuery = criteriaBuilder.createQuery(ParametroEmpresa.class);
			
			Root<ParametroEmpresa> fromParametroEmpresa = criteriaQuery.from(ParametroEmpresa.class);
			criteriaQuery.select(fromParametroEmpresa);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por id empresa
			if (parametroEmpresa.getEmrPk() != null && parametroEmpresa.getEmrPk()!=0) {
				predicate = criteriaBuilder.equal(fromParametroEmpresa.get("emrPk"), parametroEmpresa.getEmrPk());
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(parametroEmpresa.getEstado())) {
				predicate = criteriaBuilder.equal(fromParametroEmpresa.get("estado"), parametroEmpresa.getEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<ParametroEmpresa> typedQuery = entityManager.createQuery(criteriaQuery);
			
			parametroEmpresaCol = typedQuery.getResultList();
			
		} catch (Exception e) {
			
			slf4jLogger.info("Error al obtenerParametroEmpresaCriterios {}", e.toString());
			throw new SeguridadesException("Error al obtenerParametroEmpresaCriterios ", e);
		}
		
		return parametroEmpresaCol;
	}

	@Override
	public ParametroEmpresa obtenerParametroEmpresaUnique(ParametroEmpresa parametroEmpresa) throws SeguridadesException {
		
		slf4jLogger.info("obtenerParametroEmpresaPorCatalogo");
		
		ParametroEmpresa parametroEmpresaReturn = null;
		
		List<ParametroEmpresa> listParametroEmpresa = this.obtenerParametroEmpresaCriterios(parametroEmpresa);
		
		if (CollectionUtils.isNotEmpty(listParametroEmpresa) && listParametroEmpresa.size()==1) {
			parametroEmpresaReturn = listParametroEmpresa.iterator().next();
		} else {
			slf4jLogger.info("No se encontro el p\u00E1rametro o su valor no es \u00FAnico");
			throw new SeguridadesException("No se encontro el p\u00E1rametro o su valor no es \u00FAnico");
		}
		
		return parametroEmpresaReturn;
	}

}
