package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name ="nivelNichoDataManager")
public class NivelNichoDataManager extends BaseDataManager {
private static final Logger slf4jLogger = LoggerFactory.getLogger(NivelNichoDataManager.class);
	
	private static final long serialVersionUID = 1L;

	private NivelNichoDTO nivelNichoInsertar;
	private List<NivelNichoDTO> nivelNichoDTOs;
	private NivelNichoDTO nivelNichoBuscar;
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		this.nivelNichoDTOs=new ArrayList<NivelNichoDTO>();
		this.nivelNichoInsertar=new NivelNichoDTO();
		this.nivelNichoBuscar=new NivelNichoDTO();
	
	}

	public NivelNichoDTO getNivelNichoInsertar() {
		return nivelNichoInsertar;
	}

	public void setNivelNichoInsertar(NivelNichoDTO nivelNichoInsertar) {
		this.nivelNichoInsertar = nivelNichoInsertar;
	}

	public List<NivelNichoDTO> getNivelNichoDTOs() {
		return nivelNichoDTOs;
	}

	public void setNivelNichoDTOs(List<NivelNichoDTO> nivelNichoDTOs) {
		this.nivelNichoDTOs = nivelNichoDTOs;
	}

	public NivelNichoDTO getNivelNichoBuscar() {
		return nivelNichoBuscar;
	}

	public void setNivelNichoBuscar(NivelNichoDTO nivelNichoBuscar) {
		this.nivelNichoBuscar = nivelNichoBuscar;
	}

	
}
