package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.MateriaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matricula.MateriaDTO;

public class MateriaDAOImpl extends AbstractFacadeImpl<MateriaDTO> implements MateriaDAO  {

	public MateriaDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MateriaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	

}
