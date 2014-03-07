package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.ProfesorDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.ProfesorDTO;

public class ProfesorDAOImpl extends AbstractFacadeImpl<ProfesorDTO> implements ProfesorDAO {

	public ProfesorDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfesorDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
