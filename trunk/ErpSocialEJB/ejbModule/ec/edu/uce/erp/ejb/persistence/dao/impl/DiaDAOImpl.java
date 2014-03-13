package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.DiaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaDTO;

public class DiaDAOImpl extends AbstractFacadeImpl<DiaDTO> implements DiaDAO{

	public DiaDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
