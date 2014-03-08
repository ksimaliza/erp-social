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
import ec.edu.uce.erp.ejb.persistence.dao.DetalleBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;

/**
 * @author
 *
 */
@Stateless
public class DetalleBienDAOImpl extends AbstractFacadeImpl<DetalleBien> implements DetalleBienDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DetalleBienDAOImpl.class);
	
	public DetalleBienDAOImpl () {}
	
	public DetalleBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<DetalleBien> obtenerDetalleBienCriterios(DetalleBien detalleBien) throws SeguridadesException {
		slf4jLogger.info("obtenerDetalleBienCriterios");
		return null;
	}

}
