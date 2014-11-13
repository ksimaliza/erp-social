package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "reporteEucaristiaDataManager")

public class ReporteEucaristiaDataManager extends BaseDataManager {
private static final long serialVersionUID = 1L;

private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteEucaristiaDataManager.class);
	
private int codigoProvincia;
private int codigoParroquia;
private int codigoCanton;
private Date desde;
private Date hasta;
private List<CatalogoEucaristiaDTO> listCanton;
private List<CatalogoEucaristiaDTO> listParroquia;
private List<CatalogoEucaristiaDTO> listProvincia;
private List<EucaristiaListDTO> eucaristiaListDTOs;
private EucaristiaListDTO eucaristiaListDTO;
private Boolean exportDesactivado; 

@PostConstruct
public void inicializarObjetos () {
	
	slf4jLogger.info("inicializarObjetos");
	listCanton=new ArrayList<CatalogoEucaristiaDTO>();
	listParroquia=new ArrayList<CatalogoEucaristiaDTO>();
	listProvincia=new ArrayList<CatalogoEucaristiaDTO>();
	eucaristiaListDTOs=new ArrayList<EucaristiaListDTO>();
	eucaristiaListDTO=new EucaristiaListDTO();
	exportDesactivado=true;
}

	public Boolean getExportDesactivado() {
	return exportDesactivado;
}

public void setExportDesactivado(Boolean exportDesactivado) {
	this.exportDesactivado = exportDesactivado;
}

	public int getCodigoProvincia() {
		return codigoProvincia;
	}
	
	public void setCodigoProvincia(int codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	
	public int getCodigoParroquia() {
		return codigoParroquia;
	}
	
	public void setCodigoParroquia(int codigoParroquia) {
		this.codigoParroquia = codigoParroquia;
	}
	
	public int getCodigoCanton() {
		return codigoCanton;
	}
	
	public void setCodigoCanton(int codigoCanton) {
		this.codigoCanton = codigoCanton;
	}
	
	public Date getDesde() {
		return desde;
	}
	
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	
	public Date getHasta() {
		return hasta;
	}
	
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	public List<CatalogoEucaristiaDTO> getListCanton() {
		return listCanton;
	}
	
	public void setListCanton(List<CatalogoEucaristiaDTO> listCanton) {
		this.listCanton = listCanton;
	}
	
	public List<CatalogoEucaristiaDTO> getListParroquia() {
		return listParroquia;
	}
	
	public void setListParroquia(List<CatalogoEucaristiaDTO> listParroquia) {
		this.listParroquia = listParroquia;
	}
	
	public List<CatalogoEucaristiaDTO> getListProvincia() {
		return listProvincia;
	}
	
	public void setListProvincia(List<CatalogoEucaristiaDTO> listProvincia) {
		this.listProvincia = listProvincia;
	}
	
	public List<EucaristiaListDTO> getEucaristiaListDTOs() {
		return eucaristiaListDTOs;
	}
	
	public void setEucaristiaListDTOs(List<EucaristiaListDTO> eucaristiaListDTOs) {
		this.eucaristiaListDTOs = eucaristiaListDTOs;
	}

	public EucaristiaListDTO getEucaristiaListDTO() {
		return eucaristiaListDTO;
	}

	public void setEucaristiaListDTO(EucaristiaListDTO eucaristiaListDTO) {
		this.eucaristiaListDTO = eucaristiaListDTO;
	}
}
