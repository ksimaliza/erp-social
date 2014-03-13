package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.HorarioEmpleadoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioEmpleadoDTO;

public class HorarioEmpleadoDAOImpl extends AbstractFacadeImpl<HorarioEmpleadoDTO> implements HorarioEmpleadoDAO{

	public HorarioEmpleadoDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HorarioEmpleadoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
