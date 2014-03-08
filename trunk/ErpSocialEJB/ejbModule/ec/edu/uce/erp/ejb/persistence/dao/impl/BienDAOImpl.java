/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

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
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtener al buscarBienCriterios {}", e);
			throw new SeguridadesException(e);
		} finally {
			predicate = null; criteriaList = null;
		}
		
		return listBien;
		
	}

}
