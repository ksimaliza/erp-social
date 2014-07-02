package ec.edu.uce.erp.ejb.dao.factory;

import javax.ejb.Local;

import ec.edu.uce.erp.ejb.persistence.dao.AsinacionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteDAO;
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteListDAO;
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteRepresentanteDAO;
import ec.edu.uce.erp.ejb.persistence.dao.MateriaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.MatriculaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.MatriculaDetalleDAO;
import ec.edu.uce.erp.ejb.persistence.dao.NivelDAO;
import ec.edu.uce.erp.ejb.persistence.dao.NivelParaleloDAO;
import ec.edu.uce.erp.ejb.persistence.dao.NotaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ParaleloDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ParcialDAO;
import ec.edu.uce.erp.ejb.persistence.dao.PeriodoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ProfesorDAO;
import ec.edu.uce.erp.ejb.persistence.dao.RepNivelEstudianteDAO;
import ec.edu.uce.erp.ejb.persistence.dao.RepresentanteDAO;

@Local
public interface MatriculaFactoryDAO {

	MatriculaDAO getMatriculaDAOImpl();

	NivelDAO getNivelDAOImpl();

	NivelParaleloDAO getNivelParaleloDAOImpl();

	RepresentanteDAO getRepresentanteDAOImpl();

	ProfesorDAO getProfesorDAOImpl();

	PeriodoDAO getPeriodoDAOImpl();

	ParcialDAO getParcialDAOImpl();

	ParaleloDAO getParaleloDAOImpl();

	NotaDAO getNotaDAOImpl();

	MatriculaDetalleDAO getMatriculaDetalleDAOImpl();

	MateriaDAO getMateriaDAOImpl();

	EstudianteRepresentanteDAO getEstudianteRepresentanteDAOImpl();

	EstudianteDAO getEstudianteDAOImpl();

	AsinacionDAO getAsinacionDAOImpl();

	EstudianteListDAO getEstudianteListDAOImpl();

	RepNivelEstudianteDAO getRepNivelEstudianteDAOImpl();

}
