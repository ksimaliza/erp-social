package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.FaltaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;

public class FaltaDAOImpl extends AbstractFacadeImpl<FaltaDTO> implements FaltaDAO {

	public FaltaDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FaltaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
