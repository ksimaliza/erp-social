package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "exhumacionDataManager")

public class ExhumacionDataManager extends BaseDataManager {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ExhumacionDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private  Persona difuntoInsertar;
	private ExumacionDTO exumacionDTO;
	private List<ExumacionDTO> exumacionDTOs;
	private Date fechaExumacion;
	private Date fechaCepelio;
	private int provinciaCodigo;
	private int cantonCodigo;
	private int parroquiaCodigo;
	private List<CatalogoEucaristiaDTO> catalogoEucaristiaDTOs;

		
	private List<Persona> bautizadoListDTO;
	
	private BautizoDTO bautizoDTO;
	
	private int sacerdoteCodigo;
	private int doctorCodigo;
	private int estadoCodigo;
	private List<SacerdoteListDTO> sacerdoteListDTO;
	private List<DoctorListDTO> doctorListDTO;
	private List<CatalogoEucaristiaDTO> provinciasEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> cantonEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> parroquiaEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> estadoEucaristiaDTOs;
	
	
	/*
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.bautizadoInsertar = new Persona();		
		this.madrinaInsertar= new Persona();
		this.padrinoInsertar= new Persona();
		this.bautizoDTOs = new ArrayList<BautizoDTO>();
		fechaBautizoInsertar=new Date();
		fechaApCInsertar=new Date();
		bautizoDTO=new BautizoDTO();
		this.bautizadoListDTO=new ArrayList<Persona>();
		this.bautizoListDTO=new BautizoListDTO();
		this.bautizoListDTOs=new ArrayList<BautizoListDTO>();
		this.catalogoEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();		
			
	}
	
*/
}
