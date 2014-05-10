package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.DiaNoLaboralDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaNoLaboralDTO;

public class DiaNoLaboralDAOImpl extends AbstractFacadeImpl<DiaNoLaboralDTO> implements DiaNoLaboralDAO{

	public DiaNoLaboralDAOImpl() {
		super();
	}

	public DiaNoLaboralDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
	
	
}
