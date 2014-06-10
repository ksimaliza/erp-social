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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "partidaBautizoDataManager")

public class PartidaBautizoDataManager extends BaseDataManager{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private  Persona bautizadoInsertar;
	private  Persona madrinaInsertar;
	private  Persona padrinoInsertar;
	private SacerdoteDTO sacerdoteInsertar;
	private List<BautizoDTO> bautizoDTOs;
	private Date fechaBautizoInsertar;
	private Date fechaApCInsertar;
	private int provinciaCodigo;
	
	private BautizoDTO bautizoDTO;
	
	private int sacerdoteCodigo;
	private int doctorCodigo;
	private List<SacerdoteListDTO> sacerdoteListDTO;
	private List<DoctorListDTO> doctorListDTO;
	
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
		
			
	}

	public int getProvinciaCodigo() {
		return provinciaCodigo;
	}

	public void setProvinciaCodigo(int provinciaCodigo) {
		this.provinciaCodigo = provinciaCodigo;
	}

	public Persona getBautizadoInsertar() {
		return bautizadoInsertar;
	}

	public void setBautizadoInsertar(Persona bautizadoInsertar) {
		this.bautizadoInsertar = bautizadoInsertar;
	}

	public Persona getMadrinaInsertar() {
		return madrinaInsertar;
	}

	public void setMadrinaInsertar(Persona madrinaInsertar) {
		this.madrinaInsertar = madrinaInsertar;
	}

	public Persona getPadrinoInsertar() {
		return padrinoInsertar;
	}

	public void setPadrinoInsertar(Persona padrinoInsertar) {
		this.padrinoInsertar = padrinoInsertar;
	}

	public SacerdoteDTO getSacerdoteInsertar() {
		return sacerdoteInsertar;
	}

	public void setSacerdoteInsertar(SacerdoteDTO sacerdoteInsertar) {
		this.sacerdoteInsertar = sacerdoteInsertar;
	}

	public List<BautizoDTO> getBautizoDTOs() {
		return bautizoDTOs;
	}

	public void setBautizoDTOs(List<BautizoDTO> bautizoDTOs) {
		this.bautizoDTOs = bautizoDTOs;
	}

	public Date getFechaBautizoInsertar() {
		return fechaBautizoInsertar;
	}

	public void setFechaBautizoInsertar(Date fechaBautizoInsertar) {
		this.fechaBautizoInsertar = fechaBautizoInsertar;
	}

	public Date getFechaApCInsertar() {
		return fechaApCInsertar;
	}

	public void setFechaApCInsertar(Date fechaApCInsertar) {
		this.fechaApCInsertar = fechaApCInsertar;
	}

	public BautizoDTO getBautizoDTO() {
		return bautizoDTO;
	}

	public void setBautizoDTO(BautizoDTO bautizoDTO) {
		this.bautizoDTO = bautizoDTO;
	}

	public int getSacerdoteCodigo() {
		return sacerdoteCodigo;
	}

	public void setSacerdoteCodigo(int sacerdoteCodigo) {
		this.sacerdoteCodigo = sacerdoteCodigo;
	}

	public int getDoctorCodigo() {
		return doctorCodigo;
	}

	public void setDoctorCodigo(int doctorCodigo) {
		this.doctorCodigo = doctorCodigo;
	}

	public List<SacerdoteListDTO> getSacerdoteListDTO() {
		return sacerdoteListDTO;
	}

	public void setSacerdoteListDTO(List<SacerdoteListDTO> sacerdoteListDTO) {
		this.sacerdoteListDTO = sacerdoteListDTO;
	}

	public List<DoctorListDTO> getDoctorListDTO() {
		return doctorListDTO;
	}

	public void setDoctorListDTO(List<DoctorListDTO> doctorListDTO) {
		this.doctorListDTO = doctorListDTO;
	}




	
	
	
}