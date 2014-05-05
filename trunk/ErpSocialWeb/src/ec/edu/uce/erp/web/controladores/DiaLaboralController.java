package ec.edu.uce.erp.web.controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.datamanager.DiaLaboralDataManager;

@ViewScoped
@ManagedBean (name = "diaLaboralController")
public class DiaLaboralController extends BaseController{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DiaLaboralController.class);
	
	
	@ManagedProperty(value="#{diaLaboralDataManager}")
	private DiaLaboralDataManager diaLaboralDataManager;


	public DiaLaboralDataManager getDiaLaboralDataManager() {
		return diaLaboralDataManager;
	}


	public void setDiaLaboralDataManager(DiaLaboralDataManager diaLaboralDataManager) {
		this.diaLaboralDataManager = diaLaboralDataManager;
	}

	public DiaLaboralController() {
	
	}
	
	public void sabadoDomingo()
	{
		slf4jLogger.info("sabadoDomingo");
		
	}
	
}
