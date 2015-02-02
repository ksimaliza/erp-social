package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.NotaTutorDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaTutorDTO;

public class NotaTutorDAOImpl extends AbstractFacadeImpl<NotaTutorDTO> implements NotaTutorDAO {

	
	
	public NotaTutorDAOImpl() {
		super();
	
	}

	public NotaTutorDAOImpl(EntityManager entityManager) {
		super(entityManager);
		
	}
	
	
	
	


}
