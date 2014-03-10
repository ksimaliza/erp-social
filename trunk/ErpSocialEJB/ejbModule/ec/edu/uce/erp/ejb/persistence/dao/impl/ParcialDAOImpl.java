package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.ParcialDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParcialDTO;

public class ParcialDAOImpl extends AbstractFacadeImpl<ParcialDTO> implements ParcialDAO {

	public ParcialDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParcialDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	

}
