package ec.edu.uce.erp.ejb.persistence.dao.impl;


import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.EstudianteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;

public class EstudianteDAOImpl extends AbstractFacadeImpl<EstudianteDTO> implements EstudianteDAO {

	public EstudianteDAOImpl() {
		super();
	}

	public EstudianteDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}


	
	
}
