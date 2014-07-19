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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "partidaMatrimonioDataManager")

public class PartidaMatrimonioDataManager extends BaseDataManager{
private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaMatrimonioDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private  Persona novioInsertar;
	private  Persona noviaInsertar;
	private  Persona mad_noviaInsertar;
	private  Persona mad_novioInsertar;
	private  Persona pad_noviaInsertar;
	private  Persona pad_novioInsertar;
	private SacerdoteDTO sacerdoteInsertar;
	private List<MatrimonioDTO> matrimonioDTOs;
	private Date fechaMatrInsertar;
	private Date fechaApCurInsertar;
	private MatrimonioListDTO matrimonioListDTO;
	private List<MatrimonioListDTO> matrimonioListDTOs;
		
	private List<Persona> novioListDTO;
	private List<Persona> noviaListDTO;
	
	private MatrimonioDTO matrimonioDTO;
	
	private int sacerdoteCodigo;
	
	private List<SacerdoteListDTO> sacerdoteListDTO;
	
	
	private int tipoCodigo;
	private List<CatalogoEucaristiaDTO> tipoEucaristiaDTOs;
	/*private CatalogoEucaristiaDTO provincia;
	private CatalogoEucaristiaDTO ciudad;
	private CatalogoEucaristiaDTO canton;*/
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.novioInsertar = new Persona();
		this.noviaInsertar = new Persona();
		this.mad_novioInsertar= new Persona();
		this.mad_noviaInsertar= new Persona();
		this.pad_novioInsertar= new Persona();
		this.pad_noviaInsertar= new Persona();
		this.matrimonioDTOs = new ArrayList<MatrimonioDTO>();
		fechaMatrInsertar=new Date();
		fechaApCurInsertar=new Date();
		matrimonioDTO=new MatrimonioDTO();
		this.noviaListDTO=new ArrayList<Persona>();
		this.novioListDTO=new ArrayList<Persona>();
		this.matrimonioListDTOs=new ArrayList<MatrimonioListDTO>();
		this.matrimonioListDTO=new MatrimonioListDTO();
		this.sacerdoteListDTO=new ArrayList<SacerdoteListDTO>();
		this.tipoEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.sacerdoteInsertar=new SacerdoteDTO();
		
	}


	public Persona getNovioInsertar() {
		return novioInsertar;
	}


	public void setNovioInsertar(Persona novioInsertar) {
		this.novioInsertar = novioInsertar;
	}


	public Persona getNoviaInsertar() {
		return noviaInsertar;
	}


	public void setNoviaInsertar(Persona noviaInsertar) {
		this.noviaInsertar = noviaInsertar;
	}


	public Persona getMad_noviaInsertar() {
		return mad_noviaInsertar;
	}


	public void setMad_noviaInsertar(Persona mad_noviaInsertar) {
		this.mad_noviaInsertar = mad_noviaInsertar;
	}


	public Persona getMad_novioInsertar() {
		return mad_novioInsertar;
	}


	public void setMad_novioInsertar(Persona mad_novioInsertar) {
		this.mad_novioInsertar = mad_novioInsertar;
	}


	public Persona getPad_noviaInsertar() {
		return pad_noviaInsertar;
	}


	public void setPad_noviaInsertar(Persona pad_noviaInsertar) {
		this.pad_noviaInsertar = pad_noviaInsertar;
	}


	public Persona getPad_novioInsertar() {
		return pad_novioInsertar;
	}


	public void setPad_novioInsertar(Persona pad_novioInsertar) {
		this.pad_novioInsertar = pad_novioInsertar;
	}


	public SacerdoteDTO getSacerdoteInsertar() {
		return sacerdoteInsertar;
	}


	public void setSacerdoteInsertar(SacerdoteDTO sacerdoteInsertar) {
		this.sacerdoteInsertar = sacerdoteInsertar;
	}


	public List<MatrimonioDTO> getMatrimonioDTOs() {
		return matrimonioDTOs;
	}


	public void setMatrimonioDTOs(List<MatrimonioDTO> matrimonioDTOs) {
		this.matrimonioDTOs = matrimonioDTOs;
	}


	public Date getFechaMatrInsertar() {
		return fechaMatrInsertar;
	}


	public void setFechaMatrInsertar(Date fechaMatrInsertar) {
		this.fechaMatrInsertar = fechaMatrInsertar;
	}


	public Date getFechaApCurInsertar() {
		return fechaApCurInsertar;
	}


	public void setFechaApCurInsertar(Date fechaApCurInsertar) {
		this.fechaApCurInsertar = fechaApCurInsertar;
	}


	public MatrimonioListDTO getMatrimonioListDTO() {
		return matrimonioListDTO;
	}


	public void setMatrimonioListDTO(MatrimonioListDTO matrimonioListDTO) {
		this.matrimonioListDTO = matrimonioListDTO;
	}


	public List<MatrimonioListDTO> getMatrimonioListDTOs() {
		return matrimonioListDTOs;
	}


	public void setMatrimonioListDTOs(List<MatrimonioListDTO> matrimonioListDTOs) {
		this.matrimonioListDTOs = matrimonioListDTOs;
	}


	public List<Persona> getNovioListDTO() {
		return novioListDTO;
	}


	public void setNovioListDTO(List<Persona> novioListDTO) {
		this.novioListDTO = novioListDTO;
	}


	public List<Persona> getNoviaListDTO() {
		return noviaListDTO;
	}


	public void setNoviaListDTO(List<Persona> noviaListDTO) {
		this.noviaListDTO = noviaListDTO;
	}


	public MatrimonioDTO getMatrimonioDTO() {
		return matrimonioDTO;
	}


	public void setMatrimonioDTO(MatrimonioDTO matrimonioDTO) {
		this.matrimonioDTO = matrimonioDTO;
	}


	public int getSacerdoteCodigo() {
		return sacerdoteCodigo;
	}


	public void setSacerdoteCodigo(int sacerdoteCodigo) {
		this.sacerdoteCodigo = sacerdoteCodigo;
	}


	public List<SacerdoteListDTO> getSacerdoteListDTO() {
		return sacerdoteListDTO;
	}


	public void setSacerdoteListDTO(List<SacerdoteListDTO> sacerdoteListDTO) {
		this.sacerdoteListDTO = sacerdoteListDTO;
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
	
	
}
