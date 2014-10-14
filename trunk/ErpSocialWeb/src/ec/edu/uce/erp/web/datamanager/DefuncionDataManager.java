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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "defuncionDataManager")

public class DefuncionDataManager extends BaseDataManager {
private static final Logger slf4jLogger = LoggerFactory.getLogger(DefuncionDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private  Persona difuntoInsertar;
	private  Persona conyugeInsertar;
	private  Persona madreInsertar;
	private  Persona padreInsertar;
	private  Persona difuntoBuscar;
	private  Persona conyugeBuscar;
	private List<DefuncionListDTO> defuncionDTOs;
	private DefuncionDTO defuncionInsertar;
	private Date fechaMuerteInsertar;
	private Date fechaSepelioInsertar;
	private int provinciaCodigo;
	private int cantonCodigo;
	private int parroquiaCodigo;
	private List<CatalogoEucaristiaDTO> catalogoEucaristiaDTOs;
	private int sacerdoteCodigo;
	private int doctorCodigo;

	private List<DoctorListDTO> doctorListDTO;
	private List<CatalogoEucaristiaDTO> provinciasEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> cantonEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> parroquiaEucaristiaDTOs;
	private int estadoCivilCodigo;
	private List<CatalogoEucaristiaDTO> estadoCivils;
	
	private List<SacerdoteListDTO> sacerdoteListDTO;
	private DefuncionListDTO defuncionListDTO;
	

	private Boolean exportDesactivado;
	


	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.conyugeInsertar=new Persona();
		this.difuntoInsertar=new Persona();
		this.madreInsertar=new Persona();
		this.padreInsertar=new Persona();
		this.conyugeBuscar=new Persona();
		this.difuntoBuscar=new Persona();
		this.defuncionDTOs= new ArrayList<DefuncionListDTO>();
		this.fechaMuerteInsertar=new Date();
		this.fechaSepelioInsertar=new Date();
		this.catalogoEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.doctorListDTO=new ArrayList<DoctorListDTO>();
		this.sacerdoteListDTO=new ArrayList<SacerdoteListDTO>();
		this.defuncionInsertar=new DefuncionDTO();
		this.provinciasEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.cantonEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.parroquiaEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.defuncionListDTO=new DefuncionListDTO();
		this.estadoCivils=new ArrayList<CatalogoEucaristiaDTO>();
		exportDesactivado=true;
			
	}

	
	public DefuncionListDTO getDefuncionListDTO() {
		return defuncionListDTO;
	}


	public void setDefuncionListDTO(DefuncionListDTO defuncionListDTO) {
		this.defuncionListDTO = defuncionListDTO;
	}

	public int getEstadoCivilCodigo() {
		return estadoCivilCodigo;
	}


	public void setEstadoCivilCodigo(int estadoCivilCodigo) {
		this.estadoCivilCodigo = estadoCivilCodigo;
	}


	public List<CatalogoEucaristiaDTO> getEstadoCivils() {
		return estadoCivils;
	}


	public void setEstadoCivils(List<CatalogoEucaristiaDTO> estadoCivils) {
		this.estadoCivils = estadoCivils;
	}


	public Persona getDifuntoInsertar() {
		return difuntoInsertar;
	}


	public void setDifuntoInsertar(Persona difuntoInsertar) {
		this.difuntoInsertar = difuntoInsertar;
	}


	public Persona getConyugeInsertar() {
		return conyugeInsertar;
	}


	public void setConyugeInsertar(Persona conyugeInsertar) {
		this.conyugeInsertar = conyugeInsertar;
	}


	public List<DefuncionListDTO> getDefuncionDTOs() {
		return defuncionDTOs;
	}


	public void setDefuncionDTOs(List<DefuncionListDTO> defuncionDTOs) {
		this.defuncionDTOs = defuncionDTOs;
	}


	public DefuncionDTO getDefuncionInsertar() {
		return defuncionInsertar;
	}


	public void setDefuncionInsertar(DefuncionDTO defuncionInsertar) {
		this.defuncionInsertar = defuncionInsertar;
	}


	public Date getFechaMuerteInsertar() {
		return fechaMuerteInsertar;
	}


	public void setFechaMuerteInsertar(Date fechaMuerteInsertar) {
		this.fechaMuerteInsertar = fechaMuerteInsertar;
	}


	public Date getFechaSepelioInsertar() {
		return fechaSepelioInsertar;
	}


	public void setFechaSepelioInsertar(Date fechaSepelioInsertar) {
		this.fechaSepelioInsertar = fechaSepelioInsertar;
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


	


	public List<CatalogoEucaristiaDTO> getCatalogoEucaristiaDTOs() {
		return catalogoEucaristiaDTOs;
	}


	public void setCatalogoEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> catalogoEucaristiaDTOs) {
		this.catalogoEucaristiaDTOs = catalogoEucaristiaDTOs;
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


	public List<DoctorListDTO> getDoctorListDTO() {
		return doctorListDTO;
	}


	public void setDoctorListDTO(List<DoctorListDTO> doctorListDTO) {
		this.doctorListDTO = doctorListDTO;
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





	public int getParroquiaCodigo() {
		return parroquiaCodigo;
	}


	public void setParroquiaCodigo(int parroquiaCodigo) {
		this.parroquiaCodigo = parroquiaCodigo;
	}


	public List<CatalogoEucaristiaDTO> getParroquiaEucaristiaDTOs() {
		return parroquiaEucaristiaDTOs;
	}


	public void setParroquiaEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> parroquiaEucaristiaDTOs) {
		this.parroquiaEucaristiaDTOs = parroquiaEucaristiaDTOs;
	}


	public List<SacerdoteListDTO> getSacerdoteListDTO() {
		return sacerdoteListDTO;
	}


	public void setSacerdoteListDTO(List<SacerdoteListDTO> sacerdoteListDTO) {
		this.sacerdoteListDTO = sacerdoteListDTO;
	}


	public Persona getDifuntoBuscar() {
		return difuntoBuscar;
	}


	public void setDifuntoBuscar(Persona difuntoBuscar) {
		this.difuntoBuscar = difuntoBuscar;
	}


	public Persona getConyugeBuscar() {
		return conyugeBuscar;
	}


	public void setConyugeBuscar(Persona conyugeBuscar) {
		this.conyugeBuscar = conyugeBuscar;
	}


	public Persona getMadreInsertar() {
		return madreInsertar;
	}


	public void setMadreInsertar(Persona madreInsertar) {
		this.madreInsertar = madreInsertar;
	}


	public Persona getPadreInsertar() {
		return padreInsertar;
	}


	public void setPadreInsertar(Persona padreInsertar) {
		this.padreInsertar = padreInsertar;
	}


	public Boolean getExportDesactivado() {
		return exportDesactivado;
	}


	public void setExportDesactivado(Boolean exportDesactivado) {
		this.exportDesactivado = exportDesactivado;
	}


	

}
