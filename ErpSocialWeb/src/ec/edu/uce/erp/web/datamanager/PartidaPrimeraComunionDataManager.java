package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

@ViewScoped
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
	private ComunionListDTO comunionListDTOEditar;
	private List<ComunionListDTO> comunionListDTOs;

	
	private List<Persona> asignadoListDTO;
	
	private PrimeraComunionDTO primeraComunionInsertar;
	
	private int sacerdoteCodigo;
	private int doctorCodigo;
	private List<SacerdoteListDTO> sacerdoteListDTO;
	private List<DoctorListDTO> doctorListDTO;
	
	private int tipoCodigo;
	private List<CatalogoEucaristiaDTO> tipoEucaristiaDTOs;
	private int provinciaCodigo;
	private int cantonCodigo;
	private int parroquiaCodigo;
	private int estadoCodigo;
	private BautizoListDTO bautizoListDTO;
	private List<CatalogoEucaristiaDTO> provinciasEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> cantonEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> parroquiaEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> estadoEucaristiaDTOs;
	
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
		this.bautizoListDTO=new BautizoListDTO();
		this.comunionListDTOEditar=new ComunionListDTO();
		
		
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

	

	public void setBautizoListDTO(BautizoListDTO bautizoListDTO) {
		this.bautizoListDTO = bautizoListDTO;
	}

	public int getProvinciaCodigo() {
		return provinciaCodigo;
	}

	public void setProvinciaCodigo(int provinciaCodigo) {
		this.provinciaCodigo = provinciaCodigo;
	}

	public int getCantonCodigo() {
		return cantonCodigo;
	}

	public void setCantonCodigo(int cantonCodigo) {
		this.cantonCodigo = cantonCodigo;
	}

	public int getParroquiaCodigo() {
		return parroquiaCodigo;
	}

	public void setParroquiaCodigo(int parroquiaCodigo) {
		this.parroquiaCodigo = parroquiaCodigo;
	}

	public int getEstadoCodigo() {
		return estadoCodigo;
	}

	public void setEstadoCodigo(int estadoCodigo) {
		this.estadoCodigo = estadoCodigo;
	}

	public List<CatalogoEucaristiaDTO> getProvinciasEucaristiaDTOs() {
		return provinciasEucaristiaDTOs;
	}

	public void setProvinciasEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> provinciasEucaristiaDTOs) {
		this.provinciasEucaristiaDTOs = provinciasEucaristiaDTOs;
	}

	public List<CatalogoEucaristiaDTO> getCantonEucaristiaDTOs() {
		return cantonEucaristiaDTOs;
	}

	public void setCantonEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> cantonEucaristiaDTOs) {
		this.cantonEucaristiaDTOs = cantonEucaristiaDTOs;
	}

	public List<CatalogoEucaristiaDTO> getParroquiaEucaristiaDTOs() {
		return parroquiaEucaristiaDTOs;
	}

	public void setParroquiaEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> parroquiaEucaristiaDTOs) {
		this.parroquiaEucaristiaDTOs = parroquiaEucaristiaDTOs;
	}

	public List<CatalogoEucaristiaDTO> getEstadoEucaristiaDTOs() {
		return estadoEucaristiaDTOs;
	}

	public void setEstadoEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> estadoEucaristiaDTOs) {
		this.estadoEucaristiaDTOs = estadoEucaristiaDTOs;
	}

	public BautizoListDTO getBautizoListDTO() {
		return bautizoListDTO;
	}

	public ComunionListDTO getComunionListDTOEditar() {
		return comunionListDTOEditar;
	}

	public void setComunionListDTOEditar(ComunionListDTO comunionListDTOEditar) {
		this.comunionListDTOEditar = comunionListDTOEditar;
	}

	

}
