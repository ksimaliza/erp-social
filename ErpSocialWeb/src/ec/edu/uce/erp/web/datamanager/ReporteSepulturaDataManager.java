package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "reporteSepulturaDataManager")
public class ReporteSepulturaDataManager extends BaseDataManager {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteSepulturaDataManager.class);
		
	private Date desde;
	private Date hasta;
	private List<SepulturaListDTO> sepulturaListDTOs;
	private SepulturaListDTO sepulturaListDTO;
	private Boolean exportDesactivado; 

	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		sepulturaListDTOs=new ArrayList<SepulturaListDTO>();
		sepulturaListDTO=new SepulturaListDTO();
		exportDesactivado=true;
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

	public List<SepulturaListDTO> getSepulturaListDTOs() {
		return sepulturaListDTOs;
	}

	public void setSepulturaListDTOs(List<SepulturaListDTO> sepulturaListDTOs) {
		this.sepulturaListDTOs = sepulturaListDTOs;
	}

	public SepulturaListDTO getSepulturaListDTO() {
		return sepulturaListDTO;
	}

	public void setSepulturaListDTO(SepulturaListDTO sepulturaListDTO) {
		this.sepulturaListDTO = sepulturaListDTO;
	}

	public Boolean getExportDesactivado() {
		return exportDesactivado;
	}

	public void setExportDesactivado(Boolean exportDesactivado) {
		this.exportDesactivado = exportDesactivado;
	}


}
