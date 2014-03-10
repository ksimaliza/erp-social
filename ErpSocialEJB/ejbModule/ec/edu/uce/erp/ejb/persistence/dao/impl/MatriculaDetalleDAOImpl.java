package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.MatriculaDetalleDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDetalleDTO;

public class MatriculaDetalleDAOImpl extends AbstractFacadeImpl<MatriculaDetalleDTO> implements MatriculaDetalleDAO {

	public MatriculaDetalleDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatriculaDetalleDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
