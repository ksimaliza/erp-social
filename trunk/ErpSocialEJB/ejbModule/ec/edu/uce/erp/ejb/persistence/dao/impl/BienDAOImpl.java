/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.BienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;

/**
 * @author 
 *
 */
@Stateless
public class BienDAOImpl extends AbstractFacadeImpl<Bien> implements BienDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BienDAOImpl.class);
	
	public BienDAOImpl () {}
	
	public BienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Bien> buscarBienCriterios(Bien bien) throws SeguridadesException {
		
		slf4jLogger.info("buscarBienCriterios");
		
		List<Bien> listBien = null;
		
		try {
			
			Predicate predicate = null;
			List<Predicate> criteriaList = new ArrayList<Predicate>();
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Bien> criteriaQuery = criteriaBuilder.createQuery(Bien.class);
			
			Root<Bien> fromVistaBien = criteriaQuery.from(Bien.class);
			criteriaQuery.select(fromVistaBien);
			
			if (bien.getEmrPk()==null) {
				throw new SeguridadesException("El codigo de la empresa es requerido para la busqueda");
			} else {
				//por id de empresa
				predicate = criteriaBuilder.equal(fromVistaBien.get("emrPk"), bien.getEmrPk());
				criteriaList.add(predicate);
			}
			
			//por pk
			if (bien.getBiePk()!=null) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("biePk"), bien.getBiePk());
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(bien.getBieEstado())) {
				predicate = criteriaBuilder.equal(fromVistaBien.get("bieEstado"), bien.getBieEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<Bien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listBien = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtener al buscarBienCriterios {}", e);
			throw new SeguridadesException(e);
		} 
		
		return listBien;
		
	}

}
