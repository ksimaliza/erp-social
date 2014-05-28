package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.PrimeraComunionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;

public class PrimeraComunionDAOImpl extends AbstractFacadeImpl<PrimeraComunionDTO> implements PrimeraComunionDAO{

	public PrimeraComunionDAOImpl() {
		super();
		
	}

	public PrimeraComunionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
