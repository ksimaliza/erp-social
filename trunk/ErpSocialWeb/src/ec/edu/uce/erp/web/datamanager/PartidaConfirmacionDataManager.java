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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@ViewScoped
@ManagedBean (name = "partidaConfirmacionDataManager")

public class PartidaConfirmacionDataManager extends BaseDataManager {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaConfirmacionDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private  Persona confirmadoInsertar;
	private  Persona mad_padInsertar;
	private  Persona madreInsertar;
	private  Persona padreInsertar;
	private SacerdoteDTO sacerdoteInsertar;
	private List<ConfirmacionDTO> confirmacionDTOs;
	private Date fechaComunionInsertar;
	private Date fechaApCInsertar;
	private ConfirmacionListDTO confirmacionListDTO;
	private List<ConfirmacionListDTO> confirmacionListDTOs;
		
	private List<Persona> confirmadoListDTO;
	
	private ConfirmacionDTO confirmacionDTO;
	
	private int sacerdoteCodigo;
	private int doctorCodigo;
	private List<SacerdoteListDTO> sacerdoteListDTO;
	private List<DoctorListDTO> doctorListDTO;
	
	private int tipoCodigo;
	private List<CatalogoEucaristiaDTO> tipoEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> provinciaEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> cantonEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> parroquiaEucaristiaDTOs;
	private List<CatalogoEucaristiaDTO> estadoEucaristiaDTOs;
	private int provincia;
	private int parroquia;
	private int canton;
	private BautizoListDTO bautizoListDTO;
	private int estadoCodigo;
	private Boolean exportDesactivado;
	
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.confirmadoInsertar = new Persona();		
		this.mad_padInsertar= new Persona();
		this.confirmacionDTOs = new ArrayList<ConfirmacionDTO>();
		fechaComunionInsertar=new Date();
		fechaApCInsertar=new Date();
		confirmacionDTO=new ConfirmacionDTO();
		this.confirmadoListDTO=new ArrayList<Persona>();
		this.confirmacionListDTO=new ConfirmacionListDTO();
		this.confirmacionListDTOs=new ArrayList<ConfirmacionListDTO>();
		this.tipoEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.bautizoListDTO=new BautizoListDTO();
		exportDesactivado=true;
		madreInsertar=new Persona();
		padreInsertar=new Persona();
		
	}
	public Persona getConfirmadoInsertar() {
		return confirmadoInsertar;
	}
	public void setConfirmadoInsertar(Persona confirmadoInsertar) {
		this.confirmadoInsertar = confirmadoInsertar;
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
	public List<ConfirmacionDTO> getConfirmacionDTOs() {
		return confirmacionDTOs;
	}
	public void setConfirmacionDTOs(List<ConfirmacionDTO> confirmacionDTOs) {
		this.confirmacionDTOs = confirmacionDTOs;
	}
	public Date getFechaComunionInsertar() {
		return fechaComunionInsertar;
	}
	public void setFechaComunionInsertar(Date fechaComunionInsertar) {
		this.fechaComunionInsertar = fechaComunionInsertar;
	}
	public Date getFechaApCInsertar() {
		return fechaApCInsertar;
	}
	public void setFechaApCInsertar(Date fechaApCInsertar) {
		this.fechaApCInsertar = fechaApCInsertar;
	}
	
	public List<Persona> getConfirmadoListDTO() {
		return confirmadoListDTO;
	}
	public void setConfirmadoListDTO(List<Persona> confirmadoListDTO) {
		this.confirmadoListDTO = confirmadoListDTO;
	}
	public ConfirmacionDTO getConfirmacionDTO() {
		return confirmacionDTO;
	}
	public void setConfirmacionDTO(ConfirmacionDTO confirmacionDTO) {
		this.confirmacionDTO = confirmacionDTO;
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



	public ConfirmacionListDTO getConfirmacionListDTO() {
		return confirmacionListDTO;
	}



	public void setConfirmacionListDTO(ConfirmacionListDTO confirmacionListDTO) {
		this.confirmacionListDTO = confirmacionListDTO;
	}



	public List<ConfirmacionListDTO> getConfirmacionListDTOs() {
		return confirmacionListDTOs;
	}



	public int getTipo() {
		return tipoCodigo;
	}


	public void setTipo(int tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}


	public List<CatalogoEucaristiaDTO> getTipoEucaristiaDTOs() {
		return tipoEucaristiaDTOs;
	}


	public void setTipoEucaristiaDTOs(List<CatalogoEucaristiaDTO> tipoEucaristiaDTOs) {
		this.tipoEucaristiaDTOs = tipoEucaristiaDTOs;
	}


	public void setConfirmacionListDTOs(
			List<ConfirmacionListDTO> confirmacionListDTOs) {
		this.confirmacionListDTOs = confirmacionListDTOs;
	}


	public int getTipoCodigo() {
		return tipoCodigo;
	}


	public void setTipoCodigo(int tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}


	
	public BautizoListDTO getBautizoListDTO() {
		return bautizoListDTO;
	}


	public void setBautizoListDTO(BautizoListDTO bautizoListDTO) {
		this.bautizoListDTO = bautizoListDTO;
	}


	public int getEstadoCodigo() {
		return estadoCodigo;
	}


	public List<CatalogoEucaristiaDTO> getProvinciaEucaristiaDTOs() {
		return provinciaEucaristiaDTOs;
	}


	public void setProvinciaEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> provinciaEucaristiaDTOs) {
		this.provinciaEucaristiaDTOs = provinciaEucaristiaDTOs;
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


	

	public void setEstadoCodigo(int estadoCodigo) {
		this.estadoCodigo = estadoCodigo;
	}


	public List<CatalogoEucaristiaDTO> getEstadoEucaristiaDTOs() {
		return estadoEucaristiaDTOs;
	}


	public void setEstadoEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> estadoEucaristiaDTOs) {
		this.estadoEucaristiaDTOs = estadoEucaristiaDTOs;
	}


	public int getProvincia() {
		return provincia;
	}


	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}


	public int getParroquia() {
		return parroquia;
	}


	public void setParroquia(int parroquia) {
		this.parroquia = parroquia;
	}


	public int getCanton() {
		return canton;
	}


	public void setCanton(int canton) {
		this.canton = canton;
	}


	public Boolean getExportDesactivado() {
		return exportDesactivado;
	}


	public void setExportDesactivado(Boolean exportDesactivado) {
		this.exportDesactivado = exportDesactivado;
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
	
	
}
