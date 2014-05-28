package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.ExumacionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;

public class ExumacionDAOImpl extends AbstractFacadeImpl<ExumacionDTO> implements ExumacionDAO{

	public ExumacionDAOImpl() {
		super();
		
	}

	public ExumacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
