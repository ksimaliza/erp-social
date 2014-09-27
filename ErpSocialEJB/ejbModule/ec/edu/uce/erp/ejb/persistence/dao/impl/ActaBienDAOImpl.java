package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.ActaBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.ActaBien;

@Stateless
public class ActaBienDAOImpl extends AbstractFacadeImpl<ActaBien> implements ActaBienDAO {
	
private static final Logger slf4jLogger = LoggerFactory.getLogger(ActaBienDAOImpl.class);
	
	public ActaBienDAOImpl () {}
	
	public ActaBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<ActaBien> obtenerActaBienCriterios(ActaBien actaBien) throws SeguridadesException {
		// TODO Auto-generated method stub
		return null;
	}

}
