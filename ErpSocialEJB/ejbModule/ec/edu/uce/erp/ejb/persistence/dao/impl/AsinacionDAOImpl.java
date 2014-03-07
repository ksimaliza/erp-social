package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.AsinacionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.AsinacionDTO;

public class AsinacionDAOImpl extends AbstractFacadeImpl<AsinacionDTO> implements AsinacionDAO {

	public AsinacionDAOImpl() {
		super();
		
	}

	public AsinacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
	
	}

	
}
