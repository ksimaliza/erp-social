package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.ParaleloDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.ParaleloDTO;

public class ParaleloDAOImpl extends AbstractFacadeImpl<ParaleloDTO> implements ParaleloDAO{

	public ParaleloDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParaleloDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	
}
