package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.NichoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;

public class NichoDAOImpl extends AbstractFacadeImpl<NichoDTO> implements NichoDAO {

	public NichoDAOImpl() {
		super();
		
	}

	public NichoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
