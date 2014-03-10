package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.PeriodoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;

public class PeriodoDAOImpl extends AbstractFacadeImpl<PeriodoDTO> implements PeriodoDAO{

	public PeriodoDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PeriodoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
