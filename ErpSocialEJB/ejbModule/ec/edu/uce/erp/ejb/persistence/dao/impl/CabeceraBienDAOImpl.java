/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.CabeceraBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CabeceraBien;

/**
 * @author 
 *
 */
@Stateless
public class CabeceraBienDAOImpl extends AbstractFacadeImpl<CabeceraBien> implements CabeceraBienDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(CabeceraBienDAOImpl.class);
	
	public CabeceraBienDAOImpl () {}
	
	public CabeceraBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<CabeceraBien> obtenerCabeceraBienCriterios( CabeceraBien cabeceraBien) throws SeguridadesException {
		slf4jLogger.info("obtenerCabeceraBienCriterios");
		return null;
	}

}
