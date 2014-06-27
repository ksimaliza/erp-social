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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "partidaPrimeraComunionDataManager")
public class PartidaPrimeraComunionDataManager extends BaseDataManager{
private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaConfirmacionDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private  Persona asignadoInsertar;
	private  Persona mad_padInsertar;
	private SacerdoteDTO sacerdoteInsertar;
	private List<PrimeraComunionDTO> comunionBuscar;
	private Date fechaComunionInsertar;
	private Date fechaApComInsertar;
	private ComunionListDTO comunionListDTO;
	private List<ComunionListDTO> comunionListDTOs;
	
	private List<Persona> asignadoListDTO;
	
	private PrimeraComunionDTO primeraComunionInsertar;
	
	private int sacerdoteCodigo;
	private int doctorCodigo;
	private List<SacerdoteListDTO> sacerdoteListDTO;
	private List<DoctorListDTO> doctorListDTO;
	
	private int tipoCodigo;
	private List<CatalogoEucaristiaDTO> tipoEucaristiaDTOs;
	private CatalogoEucaristiaDTO provincia;
	private CatalogoEucaristiaDTO ciudad;
	private CatalogoEucaristiaDTO canton;
	private BautizoListDTO bautizoListDTO;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.asignadoInsertar = new Persona();		
		this.mad_padInsertar= new Persona();
		this.comunionBuscar = new ArrayList<PrimeraComunionDTO>();
		fechaComunionInsertar=new Date();
		fechaApComInsertar=new Date();
		primeraComunionInsertar=new PrimeraComunionDTO();
		this.asignadoListDTO=new ArrayList<Persona>();
		this.comunionListDTO=new ComunionListDTO();
		this.comunionListDTOs=new ArrayList<ComunionListDTO>();
		this.tipoEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.provincia=new CatalogoEucaristiaDTO();
		this.ciudad=new CatalogoEucaristiaDTO();
		this.canton=new CatalogoEucaristiaDTO();
		this.bautizoListDTO=new BautizoListDTO();
		
		
	}

	public Persona getAsignadoInsertar() {
		return asignadoInsertar;
	}

	public void setAsignadoInsertar(Persona asignadoInsertar) {
		this.asignadoInsertar = asignadoInsertar;
	}

	public Persona getMad_padInsertar() {
		return mad_padInsertar;
	}

	public void setMad_padInsertar(Persona mad_padInsertar) {
		this.mad_padInsertar = mad_padInsertar;
	}

	public SacerdoteDTO getSacerdoteInsertar() {
		return sacerdoteInsertar;
	}

	public void setSacerdoteInsertar(SacerdoteDTO sacerdoteInsertar) {
		this.sacerdoteInsertar = sacerdoteInsertar;
	}

	public List<PrimeraComunionDTO> getComunionBuscar() {
		return comunionBuscar;
	}

	public void setComunionBuscar(List<PrimeraComunionDTO> comunionBuscar) {
		this.comunionBuscar = comunionBuscar;
	}

	public Date getFechaComunionInsertar() {
		return fechaComunionInsertar;
	}

	public void setFechaComunionInsertar(Date fechaComunionInsertar) {
		this.fechaComunionInsertar = fechaComunionInsertar;
	}

	public Date getFechaApComInsertar() {
		return fechaApComInsertar;
	}

	public void setFechaApComInsertar(Date fechaApComInsertar) {
		this.fechaApComInsertar = fechaApComInsertar;
	}

	public ComunionListDTO getComunionListDTO() {
		return comunionListDTO;
	}

	public void setComunionListDTO(ComunionListDTO comunionListDTO) {
		this.comunionListDTO = comunionListDTO;
	}

	public List<ComunionListDTO> getComunionListDTOs() {
		return comunionListDTOs;
	}

	public void setComunionListDTOs(List<ComunionListDTO> comunionListDTOs) {
		this.comunionListDTOs = comunionListDTOs;
	}

	public List<Persona> getAsignadoListDTO() {
		return asignadoListDTO;
	}

	public void setAsignadoListDTO(List<Persona> asignadoListDTO) {
		this.asignadoListDTO = asignadoListDTO;
	}

	public PrimeraComunionDTO getPrimeraComunionInsertar() {
		return primeraComunionInsertar;
	}

	public void setPrimeraComunionInsertar(
			PrimeraComunionDTO primeraComunionInsertar) {
		this.primeraComunionInsertar = primeraComunionInsertar;
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

	public int getTipoCodigo() {
		return tipoCodigo;
	}

	public void setTipoCodigo(int tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}

	public List<CatalogoEucaristiaDTO> getTipoEucaristiaDTOs() {
		return tipoEucaristiaDTOs;
	}

	public void setTipoEucaristiaDTOs(List<CatalogoEucaristiaDTO> tipoEucaristiaDTOs) {
		this.tipoEucaristiaDTOs = tipoEucaristiaDTOs;
	}

	public CatalogoEucaristiaDTO getProvincia() {
		return provincia;
	}

	public void setProvincia(CatalogoEucaristiaDTO provincia) {
		this.provincia = provincia;
	}

	public CatalogoEucaristiaDTO getCiudad() {
		return ciudad;
	}

	public void setCiudad(CatalogoEucaristiaDTO ciudad) {
		this.ciudad = ciudad;
	}

	public CatalogoEucaristiaDTO getCanton() {
		return canton;
	}

	public void setCanton(CatalogoEucaristiaDTO canton) {
		this.canton = canton;
	}

	public BautizoListDTO getBautizoListDTO() {
		return bautizoListDTO;
	}

	public void setBautizoListDTO(BautizoListDTO bautizoListDTO) {
		this.bautizoListDTO = bautizoListDTO;
	}
	

}
