package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.BautizoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;

public class BautizoDAOImpl extends AbstractFacadeImpl<BautizoDTO>implements BautizoDAO {

	public BautizoDAOImpl() {
		super();
		
	}

	public BautizoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}
