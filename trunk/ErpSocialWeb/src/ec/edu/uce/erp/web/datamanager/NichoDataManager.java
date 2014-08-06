package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name ="nichoDataManager")
public class NichoDataManager extends BaseDataManager {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(NichoDataManager.class);
	
	private static final long serialVersionUID = 1L;

	private NichoDTO nichoInsertar;
	private NichoDTO nichoBuscar;
	private List<NichoListDTO> nichoDTOs;
	private NichoListDTO nichoListDTO;
	private int codigoSeccion;
	private int codigoTipoNicho;
	private int codigoNivelNicho;
	private List<TipoNichoDTO> tipoNichoDTOs;
	private List<NivelNichoDTO> nivelNichoDTOs;
	private List<CatalogoEucaristiaDTO> seccionEucaristiaDTOs; 
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		this.tipoNichoDTOs=new ArrayList<TipoNichoDTO>();
		this.nivelNichoDTOs=new ArrayList<NivelNichoDTO>();
		this.seccionEucaristiaDTOs=new ArrayList<CatalogoEucaristiaDTO>();
		this.nichoInsertar=new NichoDTO();
		this.nichoListDTO=new NichoListDTO();
		this.nichoDTOs=new ArrayList<NichoListDTO>();
		this.nichoBuscar=new NichoDTO();
	
	}

	public NichoDTO getNichoInsertar() {
		return nichoInsertar;
	}

	public void setNichoInsertar(NichoDTO nichoInsertar) {
		this.nichoInsertar = nichoInsertar;
	}

	public List<NichoListDTO> getNichoDTOs() {
		return nichoDTOs;
	}

	public void setNichoDTOs(List<NichoListDTO> nichoDTOs) {
		this.nichoDTOs = nichoDTOs;
	}

	public NichoListDTO getNichoListDTO() {
		return nichoListDTO;
	}

	public void setNichoListDTO(NichoListDTO nichoListDTO) {
		this.nichoListDTO = nichoListDTO;
	}

	public int getCodigoSeccion() {
		return codigoSeccion;
	}

	public void setCodigoSeccion(int codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	public int getCodigoTipoNicho() {
		return codigoTipoNicho;
	}

	public void setCodigoTipoNicho(int codigoTipoNicho) {
		this.codigoTipoNicho = codigoTipoNicho;
	}

	public int getCodigoNivelNicho() {
		return codigoNivelNicho;
	}

	public void setCodigoNivelNicho(int codigoNivelNicho) {
		this.codigoNivelNicho = codigoNivelNicho;
	}

	public List<TipoNichoDTO> getTipoNichoDTOs() {
		return tipoNichoDTOs;
	}

	public void setTipoNichoDTOs(List<TipoNichoDTO> tipoNichoDTOs) {
		this.tipoNichoDTOs = tipoNichoDTOs;
	}

	public List<NivelNichoDTO> getNivelNichoDTOs() {
		return nivelNichoDTOs;
	}

	public void setNivelNichoDTOs(List<NivelNichoDTO> nivelNichoDTOs) {
		this.nivelNichoDTOs = nivelNichoDTOs;
	}

	public List<CatalogoEucaristiaDTO> getSeccionEucaristiaDTOs() {
		return seccionEucaristiaDTOs;
	}

	public void setSeccionEucaristiaDTOs(
			List<CatalogoEucaristiaDTO> seccionEucaristiaDTOs) {
		this.seccionEucaristiaDTOs = seccionEucaristiaDTOs;
	}

	public NichoDTO getNichoBuscar() {
		return nichoBuscar;
	}

	public void setNichoBuscar(NichoDTO nichoBuscar) {
		this.nichoBuscar = nichoBuscar;
	}
	
	
	

}
