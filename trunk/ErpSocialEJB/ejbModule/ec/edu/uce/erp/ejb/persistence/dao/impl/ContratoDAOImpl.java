package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.ContratoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;

public class ContratoDAOImpl extends AbstractFacadeImpl<ContratoDTO> implements ContratoDAO {

	public ContratoDAOImpl() {
		super();
		
	}

	public ContratoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
