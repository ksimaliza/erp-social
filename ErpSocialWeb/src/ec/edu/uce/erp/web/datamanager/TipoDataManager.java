package ec.edu.uce.erp.web.datamanager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.web.common.controladores.BaseController;

@SessionScoped
@ManagedBean (name = "tipoDataManager")

public class TipoDataManager extends BaseController {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	
	private TipoDTO tipoInsertar;
	private TipoDTO tipoActualizar;
	private TipoDTO TipoBuscar;
	private List<TipoDTO> listTipo;
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.tipoInsertar = new TipoDTO();		
		this.tipoActualizar = new TipoDTO();
		this.TipoBuscar = new TipoDTO();
			
	}


	public TipoDTO getTipoInsertar() {
		return tipoInsertar;
	}


	public void setTipoInsertar(TipoDTO tipoInsertar) {
		this.tipoInsertar = tipoInsertar;
	}


	public TipoDTO getTipoActualizar() {
		return tipoActualizar;
	}


	public void setTipoActualizar(TipoDTO tipoActualizar) {
		this.tipoActualizar = tipoActualizar;
	}


	public TipoDTO getTipoBuscar() {
		return TipoBuscar;
	}


	public void setTipoBuscar(TipoDTO tipoBuscar) {
		TipoBuscar = tipoBuscar;
	}


	public List<TipoDTO> getListTipo() {
		return listTipo;
	}


	public void setListTipo(List<TipoDTO> listTipo) {
		this.listTipo = listTipo;
	}
	
	
	
}
