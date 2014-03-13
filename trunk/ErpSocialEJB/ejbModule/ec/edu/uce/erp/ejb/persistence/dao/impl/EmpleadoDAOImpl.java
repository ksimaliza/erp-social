package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.EmpleadoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;

public class EmpleadoDAOImpl extends AbstractFacadeImpl<EmpleadoDTO> implements EmpleadoDAO{

	public EmpleadoDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpleadoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
