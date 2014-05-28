package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.NivelNichoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;

public class NivelNichoDAOImpl extends AbstractFacadeImpl<NivelNichoDTO> implements NivelNichoDAO {

	public NivelNichoDAOImpl() {
		super();
		
	}

	public NivelNichoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
