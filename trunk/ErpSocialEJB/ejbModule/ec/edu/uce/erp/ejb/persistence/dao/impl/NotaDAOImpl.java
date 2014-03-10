package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.NotaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;

public class NotaDAOImpl extends AbstractFacadeImpl<NotaDTO> implements NotaDAO {

	public NotaDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
