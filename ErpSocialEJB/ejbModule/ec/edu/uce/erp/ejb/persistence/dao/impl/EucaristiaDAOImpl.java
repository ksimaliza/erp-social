package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.EucaristiaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;

public class EucaristiaDAOImpl extends AbstractFacadeImpl<EucaristiaDTO> implements EucaristiaDAO {

	public EucaristiaDAOImpl() {
		super();
		
	}

	public EucaristiaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
