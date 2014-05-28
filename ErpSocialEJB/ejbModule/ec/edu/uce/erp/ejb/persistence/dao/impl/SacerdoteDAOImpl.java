package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.SacerdoteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class SacerdoteDAOImpl extends AbstractFacadeImpl<SacerdoteDTO> implements SacerdoteDAO {

	public SacerdoteDAOImpl() {
		super();
		
	}

	public SacerdoteDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
