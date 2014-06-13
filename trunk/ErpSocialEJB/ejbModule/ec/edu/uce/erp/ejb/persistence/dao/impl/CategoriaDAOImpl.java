package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.CategoriaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;

public class CategoriaDAOImpl extends AbstractFacadeImpl<CatalogoEucaristiaDTO> implements CategoriaDAO {

	public CategoriaDAOImpl() {
		super();
		
	}

	public CategoriaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	

}
