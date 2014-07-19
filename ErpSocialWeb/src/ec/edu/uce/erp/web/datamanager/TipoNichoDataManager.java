package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name ="tipoNichoDataManager")

public class TipoNichoDataManager extends BaseDataManager {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(TipoNichoDataManager.class);
	
	private static final long serialVersionUID = 1L;

	private TipoNichoDTO tipoNichoInsertar;
	private List<TipoNichoDTO> tipoNichoDTOs;
	private TipoNichoDTO tipoNichoBuscar;
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		this.tipoNichoDTOs=new ArrayList<TipoNichoDTO>();
		this.tipoNichoInsertar=new TipoNichoDTO();
		this.tipoNichoBuscar=new TipoNichoDTO();
	
	}

	public TipoNichoDTO getTipoNichoInsertar() {
		return tipoNichoInsertar;
	}

	public void setTipoNichoInsertar(TipoNichoDTO tipoNichoInsertar) {
		this.tipoNichoInsertar = tipoNichoInsertar;
	}

	public List<TipoNichoDTO> getTipoNichoDTOs() {
		return tipoNichoDTOs;
	}

	public void setTipoNichoDTOs(List<TipoNichoDTO> tipoNichoDTOs) {
		this.tipoNichoDTOs = tipoNichoDTOs;
	}

	public TipoNichoDTO getTipoNichoBuscar() {
		return tipoNichoBuscar;
	}

	public void setTipoNichoBuscar(TipoNichoDTO tipoNichoBuscar) {
		this.tipoNichoBuscar = tipoNichoBuscar;
	}
	
	
}
