package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.datamanager.PartidaBautizoDataManager;

@ViewScoped
@ManagedBean (name = "exhumacionController")
public class ExhumacionController extends BaseController{
	
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ExhumacionController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@ManagedProperty(value="#{partidaBautizoDataManager}")
	private PartidaBautizoDataManager partidaBautizoDataManager;

}
