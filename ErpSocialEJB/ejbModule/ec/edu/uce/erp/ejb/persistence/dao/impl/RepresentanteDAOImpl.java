package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.RepresentanteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;

public class RepresentanteDAOImpl extends AbstractFacadeImpl<RepresentanteDTO> implements RepresentanteDAO {

	public RepresentanteDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RepresentanteDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
