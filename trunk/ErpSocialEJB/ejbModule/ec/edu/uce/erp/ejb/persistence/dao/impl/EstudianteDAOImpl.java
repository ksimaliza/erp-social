package ec.edu.uce.erp.ejb.persistence.dao.impl;


import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.EstudianteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.EstudianteDTO;

public class EstudianteDAOImpl extends AbstractFacadeImpl<EstudianteDTO> implements EstudianteDAO {

	public EstudianteDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstudianteDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	
}
