/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.ejb.persistence.dao.EmpresaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.FactoryDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HistorialClaveDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.dao.MenuDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ModuloDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ParametroDAO;
import ec.edu.uce.erp.ejb.persistence.dao.PerfilDAO;
import ec.edu.uce.erp.ejb.persistence.dao.PersonaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.UsuarioDAO;

/**
 * @author 
 *
 */
@Stateless
public class FactoryDAOImpl implements FactoryDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private UsuarioDAO usuarioDAO;
	private EmpresaDAO empresaDAO;
	private ModuloDAO moduloDAO;
	private PerfilDAO perfilDAO;
	private MenuDAO menuDAO;
	
	private HistoricoTransaccioneDAO historicoTransaccioneDAO;
	private HistorialClaveDAO historialClaveDAO;
	
	private ParametroDAO parametroDAO;
	
	private PersonaDAO personaDAO;

	@Override
	public UsuarioDAO getUsuarioDAOImpl() {
		if (usuarioDAO == null) {
			usuarioDAO = new UsuarioDAOImpl(entityManager);
		}
		return usuarioDAO;
	}
	
	@Override
	public EmpresaDAO getEmpresaDAOImpl() {
		if (empresaDAO == null) {
			empresaDAO = new EmpresaDAOImpl(entityManager);
		}
		return empresaDAO;
	}

	@Override
	public ModuloDAO getModuloDAOImpl() {
		if (moduloDAO == null) {
			moduloDAO = new ModuloDAOImpl(entityManager);
		}
		return moduloDAO;
	}

	@Override
	public PerfilDAO getPerfilDAOImpl() {
		if (perfilDAO == null) {
			perfilDAO = new PerfilDAOImpl(entityManager);
		}
		return perfilDAO;
	}

	@Override
	public HistoricoTransaccioneDAO getHistoricoTransaccioneDAOImpl() {
		if (historicoTransaccioneDAO == null) {
			historicoTransaccioneDAO = new HistoricoTransaccioneDAOImpl(entityManager);
		}
		return historicoTransaccioneDAO;
	}

	@Override
	public MenuDAO getMenuDAOImpl() {
		if (menuDAO == null) {
			menuDAO = new MenuDAOImpl(entityManager);
		}
		return menuDAO;
	}

	@Override
	public HistorialClaveDAO getHistorialClaveDAOImpl() {
		if (historialClaveDAO == null) {
			historialClaveDAO = new HistorialClaveDAOImpl(entityManager);
		}
		
		return historialClaveDAO;
	}

	@Override
	public ParametroDAO getParametroDAOImpl() {
		if (parametroDAO == null) {
			parametroDAO = new ParametroDAOImpl(entityManager);
		}
		return parametroDAO;
	}

	@Override
	public PersonaDAO getPersonaDAOImpl() {
		if (personaDAO == null) {
			personaDAO = new PersonaDAOImpl(entityManager);
		}
		return personaDAO;
	}

}
