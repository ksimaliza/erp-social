package ec.edu.uce.erp.ejb.dao.factory.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.ejb.dao.factory.AsistenciaFactoryDAO;
import ec.edu.uce.erp.ejb.persistence.dao.DiaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.DiaNoLaboralDAO;
import ec.edu.uce.erp.ejb.persistence.dao.EmpleadoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.FaltaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HorarioDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HorarioEmpleadoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ParametroAsistenciaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.PermisoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.RegistroDAO;
import ec.edu.uce.erp.ejb.persistence.dao.TipoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.impl.DiaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.DiaNoLaboralDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.EmpleadoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.FaltaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.HorarioDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.HorarioEmpleadoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ParametroAsistenciaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.PermisoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.RegistroDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.TipoDAOImpl;

@Stateless
public class AsistenciaFactoryDAOImpl implements AsistenciaFactoryDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	private DiaDAO diaDAO;
	private EmpleadoDAO empleadoDAO;
	private FaltaDAO faltaDAO;
	private HorarioDAO horarioDAO;
	private HorarioEmpleadoDAO horarioempleadoDAO;
	private ParametroAsistenciaDAO parametroDAO;
	private PermisoDAO permisoDAO;
	private RegistroDAO registroDAO;
	private TipoDAO tipoDAO;
	private DiaNoLaboralDAO diaNoLaboralDAO;
	
	@Override
	public DiaDAO getDiaDAOImpl()
	{
		if (diaDAO == null) {
			diaDAO = new DiaDAOImpl(entityManager);
		}
		return diaDAO;
	}
	
	@Override
	public EmpleadoDAO getEmpleadoDAOImpl()
	{
		if (empleadoDAO == null) {
			empleadoDAO = new EmpleadoDAOImpl(entityManager);
		}
		return empleadoDAO;
	}
	
	@Override
	public FaltaDAO getFaltaDAOImpl()
	{
		if (faltaDAO == null) {
			faltaDAO = new FaltaDAOImpl(entityManager);
		}
		return faltaDAO;
	}
	
	@Override
	public HorarioDAO getHorarioDAOImpl()
	{
		if (horarioDAO == null) {
			horarioDAO = new HorarioDAOImpl(entityManager);
		}
		return horarioDAO;
	}
	
	@Override
	public HorarioEmpleadoDAO getHorarioEmpleadoDAOImpl()
	{
		if (horarioempleadoDAO == null) {
			horarioempleadoDAO = new HorarioEmpleadoDAOImpl(entityManager);
		}
		return horarioempleadoDAO;
	}
	
	@Override
	public ParametroAsistenciaDAO getParametroDAOImpl()
	{
		if (parametroDAO == null) {
			parametroDAO = new ParametroAsistenciaDAOImpl(entityManager);
		}
		return parametroDAO;
	}
	
	@Override
	public PermisoDAO getPermisoDAOImpl()
	{
		if (permisoDAO == null) {
			permisoDAO = new PermisoDAOImpl(entityManager);
		}
		return permisoDAO;
	}
	
	@Override
	public RegistroDAO getRegistroDAOImpl()
	{
		if (registroDAO == null) {
			registroDAO = new RegistroDAOImpl(entityManager);
		}
		return registroDAO;
	}
	
	@Override
	public TipoDAO getTipoDAOImpl()
	{
		if (tipoDAO == null) {
			tipoDAO = new TipoDAOImpl(entityManager);
		}
		return tipoDAO;
	}
	
	@Override
	public DiaNoLaboralDAO getDiaNoLaboralDAOImpl()
	{
		if (diaNoLaboralDAO == null) {
			diaNoLaboralDAO = new DiaNoLaboralDAOImpl(entityManager);
		}
		return diaNoLaboralDAO;
	}

}
