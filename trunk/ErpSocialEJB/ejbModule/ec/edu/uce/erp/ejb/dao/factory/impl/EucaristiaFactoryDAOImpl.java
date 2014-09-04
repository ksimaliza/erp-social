package ec.edu.uce.erp.ejb.dao.factory.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.ejb.dao.factory.EucaristiaFactoryDAO;
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
import ec.edu.uce.erp.ejb.persistence.dao.PagoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.PrimeraComunionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.SacerdoteDAO;
import ec.edu.uce.erp.ejb.persistence.dao.SepulturaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.TipoNichoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.impl.AutorizacionExhumacionDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.BautizoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.CatalogoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ConfirmacionDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ContratoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.DefuncionDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.DoctorDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.EucaristiaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ExumacionDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.MatrimonioDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.NichoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.NivelNichoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.PagoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.PrimeraComunionDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.SacerdoteDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.SepulturaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.TipoNichoDAOImpl;

@Stateless
public class EucaristiaFactoryDAOImpl implements EucaristiaFactoryDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	private BautizoDAO bautizoDAO;
	private ConfirmacionDAO confirmacionDAO;
	private ContratoDAO contratoDAO;
	private DefuncionDAO defuncionDAO;
	private EucaristiaDAO eucaristiaDAO;
	private ExumacionDAO exumacionDAO;
	private MatrimonioDAO matrimonioDAO;
	private NichoDAO nichoDAO;
	private NivelNichoDAO nivelNichoDAO;
	private PrimeraComunionDAO primeraComunionDAO;
	private SacerdoteDAO sacerdoteDAO;
	private TipoNichoDAO tipoNichoDAO;
	private DoctorDAO doctorDAO;
	private CatalogoDAO catalogoDAO;
	private AutorizacionExhumacionDAO autorizacionExhumacionDAO;
	private SepulturaDAO sepulturaDAO;
	private PagoDAO pagoDAO;
	
	
	@Override
	public BautizoDAO getBautizoDAOImpl()
	{
		if (bautizoDAO == null) {
			bautizoDAO = new BautizoDAOImpl(entityManager);
		}
		return bautizoDAO;
	}
	
	@Override
	public ConfirmacionDAO getConfirmacionDAOImpl()
	{
		if (confirmacionDAO == null) {
			confirmacionDAO = new ConfirmacionDAOImpl(entityManager);
		}
		return confirmacionDAO;
	}
	
	@Override
	public ContratoDAO getContratoDAOImpl()
	{
		if (contratoDAO == null) {
			contratoDAO = new ContratoDAOImpl(entityManager);
		}
		return contratoDAO;
	}
	
	@Override
	public DefuncionDAO getDefuncionDAOImpl()
	{
		if (defuncionDAO == null) {
			defuncionDAO = new DefuncionDAOImpl(entityManager);
		}
		return defuncionDAO;
	}
	
	@Override
	public EucaristiaDAO getEucaristiaDAOImpl()
	{
		if (eucaristiaDAO == null) {
			eucaristiaDAO = new EucaristiaDAOImpl(entityManager);
		}
		return eucaristiaDAO;
	}
	
	@Override
	public ExumacionDAO getExumacionDAOImpl()
	{
		if (exumacionDAO == null) {
			exumacionDAO = new ExumacionDAOImpl(entityManager);
		}
		return exumacionDAO;
	}
	
	@Override
	public MatrimonioDAO getMatrimonioDAOImpl()
	{
		if (matrimonioDAO == null) {
			matrimonioDAO = new MatrimonioDAOImpl(entityManager);
		}
		return matrimonioDAO;
	}
	
	@Override
	public NichoDAO getNichoDAOImpl()
	{
		if (nichoDAO == null) {
			nichoDAO = new NichoDAOImpl(entityManager);
		}
		return nichoDAO;
	}
	
	@Override
	public NivelNichoDAO getNivelNichoDAOImpl()
	{
		if (nivelNichoDAO == null) {
			nivelNichoDAO = new NivelNichoDAOImpl(entityManager);
		}
		return nivelNichoDAO;
	}
	
	@Override
	public PrimeraComunionDAO getPrimeraComunionDAOImpl()
	{
		if (primeraComunionDAO == null) {
			primeraComunionDAO = new PrimeraComunionDAOImpl(entityManager);
		}
		return primeraComunionDAO;
	}
	
	@Override
	public SacerdoteDAO getSacerdoteDAOImpl()
	{
		if (sacerdoteDAO == null) {
			sacerdoteDAO = new SacerdoteDAOImpl(entityManager);
		}
		return sacerdoteDAO;
	}
	
	@Override
	public TipoNichoDAO getTipoNichoDAOImpl()
	{
		if (tipoNichoDAO == null) {
			tipoNichoDAO = new TipoNichoDAOImpl(entityManager);
		}
		return tipoNichoDAO;
	}
	
	@Override
	public DoctorDAO getDoctorDAOImpl()
	{
		if (doctorDAO == null) {
			doctorDAO = new DoctorDAOImpl(entityManager);
		}
		return doctorDAO;
	}
	
	@Override
	public CatalogoDAO getCatalogoDAOImpl()
	{
		if (catalogoDAO == null) {
			catalogoDAO = new CatalogoDAOImpl(entityManager);
		}
		return catalogoDAO;
	}
	
	@Override
	public AutorizacionExhumacionDAO getAutorizacionExhumacionDAOImpl()
	{
		if (autorizacionExhumacionDAO == null) {
			autorizacionExhumacionDAO = new AutorizacionExhumacionDAOImpl(entityManager);
		}
		return autorizacionExhumacionDAO;
	}
	
	@Override
	public SepulturaDAO getSepulturaDAOImpl()
	{
		if (sepulturaDAO == null) {
			sepulturaDAO = new SepulturaDAOImpl(entityManager);
		}
		return sepulturaDAO;
	}
	
	
	@Override
	public PagoDAO getPagoDAOImpl()
	{
		if (pagoDAO == null) {
			pagoDAO = new PagoDAOImpl(entityManager);
		}
		return pagoDAO;
	}
}
