package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.TipoNotaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.TipoNotaDTO;

public class TipoNotaDAOImpl extends AbstractFacadeImpl<TipoNotaDTO> implements TipoNotaDAO {

	public TipoNotaDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoNotaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	

}
