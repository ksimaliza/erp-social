package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.TipoNichoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;

public class TipoNichoDAOImpl extends AbstractFacadeImpl<TipoNichoDTO> implements TipoNichoDAO {

	public TipoNichoDAOImpl() {
		super();
		
	}

	public TipoNichoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
