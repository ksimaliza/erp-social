package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.EstudianteRepresentanteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.EstudianteRepresentanteDTO;

public class EstudianteRepresentanteDAOImpl extends AbstractFacadeImpl<EstudianteRepresentanteDTO> implements EstudianteRepresentanteDAO{

	public EstudianteRepresentanteDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstudianteRepresentanteDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
