package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.NivelDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;

public class NivelDAOImpl extends AbstractFacadeImpl<NivelDTO> implements NivelDAO {

	public NivelDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NivelDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
