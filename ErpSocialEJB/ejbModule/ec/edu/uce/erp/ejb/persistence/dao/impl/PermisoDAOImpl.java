package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.PermisoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;

public class PermisoDAOImpl extends AbstractFacadeImpl<PermisoDTO> implements PermisoDAO{

	public PermisoDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PermisoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
