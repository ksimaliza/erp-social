package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.HorarioDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioDTO;

public class HorarioDAOImpl extends AbstractFacadeImpl<HorarioDTO> implements HorarioDAO {

	public HorarioDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HorarioDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
