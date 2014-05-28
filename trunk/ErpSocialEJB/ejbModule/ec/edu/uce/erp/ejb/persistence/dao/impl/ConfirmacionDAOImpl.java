package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.ConfirmacionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;

public class ConfirmacionDAOImpl extends AbstractFacadeImpl<ConfirmacionDTO> implements ConfirmacionDAO{

	public ConfirmacionDAOImpl() {
		super();
		
	}

	public ConfirmacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
