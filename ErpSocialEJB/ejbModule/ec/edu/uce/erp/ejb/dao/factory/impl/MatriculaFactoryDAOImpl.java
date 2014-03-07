package ec.edu.uce.erp.ejb.dao.factory.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.ejb.dao.factory.MatriculaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.dao.AsinacionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteDAO;
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
import ec.edu.uce.erp.ejb.persistence.dao.RepresentanteDAO;
import ec.edu.uce.erp.ejb.persistence.dao.impl.AsinacionDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.EstudianteDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.EstudianteRepresentanteDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.MateriaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.MatriculaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.MatriculaDetalleDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.NivelDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.NivelParaleloDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.NotaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ParaleloDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ParcialDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.PeriodoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ProfesorDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.RepresentanteDAOImpl;

@Stateless
public class MatriculaFactoryDAOImpl implements MatriculaFactoryDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	private AsinacionDAO asinacionDAO;
	private EstudianteDAO estudianteDAO;
	private EstudianteRepresentanteDAO estudianteeepresentanteDAO; 
	private MateriaDAO materiaDAO;
	private MatriculaDetalleDAO matriculadetalleDAO;
	private MatriculaDAO matriculaDAO;
	private NivelDAO nivelDAO;
	private NivelParaleloDAO nivelParaleloDAO;
	private NotaDAO notaDAO;
	private ParaleloDAO paraleloDAO;
	private ParcialDAO parcialDAO;
	private PeriodoDAO periodoDAO;
	private ProfesorDAO profesorDAO;
	private RepresentanteDAO representanteDAO;
	
	
	@Override
	public AsinacionDAO getAsinacionDAOImpl()
	{
		if (asinacionDAO == null) {
			asinacionDAO = new AsinacionDAOImpl(entityManager);
		}
		return asinacionDAO;
	}

	@Override
	public EstudianteDAO getEstudianteDAOImpl()
	{
		if (estudianteDAO == null) {
			estudianteDAO = new EstudianteDAOImpl(entityManager);
		}
		return estudianteDAO;
	}

	@Override
	public EstudianteRepresentanteDAO getEstudianteRepresentanteDAOImpl()
	{
		if (estudianteeepresentanteDAO == null) {
			estudianteeepresentanteDAO = new EstudianteRepresentanteDAOImpl(entityManager);
		}
		return estudianteeepresentanteDAO;
	}
	
	@Override
	public MateriaDAO getMateriaDAOImpl()
	{
		if (materiaDAO == null) {
			materiaDAO = new MateriaDAOImpl(entityManager);
		}
		return materiaDAO;
	}
	
	@Override
	public MatriculaDetalleDAO getMatriculaDetalleDAOImpl()
	{
		if (matriculadetalleDAO == null) {
			matriculadetalleDAO = new MatriculaDetalleDAOImpl(entityManager);
		}
		return matriculadetalleDAO;
	}
	
	@Override
	public MatriculaDAO getMatriculaDAOImpl()
	{
		if (matriculaDAO == null) {
			matriculaDAO = new MatriculaDAOImpl(entityManager);
		}
		return matriculaDAO;
	}
	
	@Override
	public NivelDAO getNivelDAOImpl()
	{
		if (nivelDAO == null) {
			nivelDAO = new NivelDAOImpl(entityManager);
		}
		return nivelDAO;
	}
	
	@Override
	public NivelParaleloDAO getNivelParaleloDAOImpl()
	{
		if (nivelParaleloDAO == null) {
			nivelParaleloDAO = new NivelParaleloDAOImpl(entityManager);
		}
		return nivelParaleloDAO;
	}
	
	@Override
	public NotaDAO getNotaDAOImpl()
	{
		if (notaDAO == null) {
			notaDAO = new NotaDAOImpl(entityManager);
		}
		return notaDAO;
	}
	
	@Override
	public ParaleloDAO getParaleloDAOImpl()
	{
		if (paraleloDAO == null) {
			paraleloDAO = new ParaleloDAOImpl(entityManager);
		}
		return paraleloDAO;
	}
	
	@Override
	public ParcialDAO getParcialDAOImpl()
	{
		if (parcialDAO == null) {
			parcialDAO = new ParcialDAOImpl(entityManager);
		}
		return parcialDAO;
	}
	
	@Override
	public PeriodoDAO getPeriodoDAOImpl()
	{
		if (periodoDAO == null) {
			periodoDAO = new PeriodoDAOImpl(entityManager);
		}
		return periodoDAO;
	}
	
	@Override
	public ProfesorDAO getProfesorDAOImpl()
	{
		if (profesorDAO == null) {
			profesorDAO = new ProfesorDAOImpl(entityManager);
		}
		return profesorDAO;
	}
	
	@Override
	public RepresentanteDAO getRepresentanteDAOImpl()
	{
		if (representanteDAO == null) {
			representanteDAO = new RepresentanteDAOImpl(entityManager);
		}
		return representanteDAO;
	}
}
