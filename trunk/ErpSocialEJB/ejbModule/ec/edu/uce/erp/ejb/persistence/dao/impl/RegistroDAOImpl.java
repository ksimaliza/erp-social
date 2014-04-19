package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.RegistroDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;

public class RegistroDAOImpl extends AbstractFacadeImpl<RegistroDTO> implements RegistroDAO {

	public RegistroDAOImpl() {
		super();
	}

	public RegistroDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}
