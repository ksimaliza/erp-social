package ec.edu.uce.erp.ejb.persistence.dao.impl;


import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.MatrimonioDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioDTO;

public class MatrimonioDAOImpl extends AbstractFacadeImpl<MatrimonioDTO> implements MatrimonioDAO{

	public MatrimonioDAOImpl() {
		super();
		
	}

	public MatrimonioDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
