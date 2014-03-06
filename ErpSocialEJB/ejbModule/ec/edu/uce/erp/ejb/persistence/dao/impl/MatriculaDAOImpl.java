package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.MatriculaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.MatriculaDTO;

public class MatriculaDAOImpl extends AbstractFacadeImpl<MatriculaDTO> implements MatriculaDAO{

	public MatriculaDAOImpl() {
		super();
	}

	public MatriculaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
}
