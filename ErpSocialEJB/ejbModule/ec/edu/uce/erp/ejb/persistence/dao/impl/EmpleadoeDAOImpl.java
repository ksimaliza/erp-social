package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.EmpleadoeDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Empleado;

public class EmpleadoeDAOImpl extends AbstractFacadeImpl<Empleado> implements EmpleadoeDAO{

	public EmpleadoeDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpleadoeDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
