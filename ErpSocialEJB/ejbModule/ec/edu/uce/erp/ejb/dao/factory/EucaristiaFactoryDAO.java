package ec.edu.uce.erp.ejb.dao.factory;

import javax.ejb.Local;

import ec.edu.uce.erp.ejb.persistence.dao.AutorizacionExhumacionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.BautizoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.CatalogoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ConfirmacionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ContratoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.DefuncionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.DoctorDAO;
import ec.edu.uce.erp.ejb.persistence.dao.EucaristiaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ExumacionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.MatrimonioDAO;
import ec.edu.uce.erp.ejb.persistence.dao.NichoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.NivelNichoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.PrimeraComunionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.SacerdoteDAO;
import ec.edu.uce.erp.ejb.persistence.dao.TipoNichoDAO;

@Local
public interface EucaristiaFactoryDAO {

	BautizoDAO getBautizoDAOImpl();

	ConfirmacionDAO getConfirmacionDAOImpl();

	ContratoDAO getContratoDAOImpl();

	DefuncionDAO getDefuncionDAOImpl();

	EucaristiaDAO getEucaristiaDAOImpl();

	ExumacionDAO getExumacionDAOImpl();

	MatrimonioDAO getMatrimonioDAOImpl();

	NichoDAO getNichoDAOImpl();

	NivelNichoDAO getNivelNichoDAOImpl();

	PrimeraComunionDAO getPrimeraComunionDAOImpl();

	SacerdoteDAO getSacerdoteDAOImpl();

	TipoNichoDAO getTipoNichoDAOImpl();

	DoctorDAO getDoctorDAOImpl();

	CatalogoDAO getCatalogoDAOImpl();

	AutorizacionExhumacionDAO getAutorizacionExhumacionDAOImpl();

}
