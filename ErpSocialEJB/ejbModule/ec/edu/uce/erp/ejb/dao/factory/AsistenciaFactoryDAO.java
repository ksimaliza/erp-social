package ec.edu.uce.erp.ejb.dao.factory;

import javax.ejb.Local;

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

@Local
public interface AsistenciaFactoryDAO {

	DiaDAO getDiaDAOImpl();

	EmpleadoDAO getEmpleadoDAOImpl();

	FaltaDAO getFaltaDAOImpl();

	HorarioDAO getHorarioDAOImpl();

	HorarioEmpleadoDAO getHorarioEmpleadoDAOImpl();

	ParametroAsistenciaDAO getParametroDAOImpl();

	PermisoDAO getPermisoDAOImpl();

	RegistroDAO getRegistroDAOImpl();

	TipoDAO getTipoDAOImpl();

	DiaNoLaboralDAO getDiaNoLaboralDAOImpl();

	
	

}
