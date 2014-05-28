package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.DefuncionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;

public class DefuncionDAOImpl extends AbstractFacadeImpl<DefuncionDTO> implements DefuncionDAO {

	public DefuncionDAOImpl() {
		super();
		
	}

	public DefuncionDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}

}
