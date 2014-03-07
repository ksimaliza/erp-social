package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.NivelParaleloDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.NivelParaleloDTO;

public class NivelParaleloDAOImpl extends AbstractFacadeImpl<NivelParaleloDTO> implements NivelParaleloDAO{

	public NivelParaleloDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NivelParaleloDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	

}
