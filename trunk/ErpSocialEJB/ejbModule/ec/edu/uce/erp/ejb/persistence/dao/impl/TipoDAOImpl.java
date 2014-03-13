package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.TipoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;

public class TipoDAOImpl extends AbstractFacadeImpl<TipoDTO> implements TipoDAO {

	public TipoDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
