package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ContratoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ContratoDataManager;

@ViewScoped
@ManagedBean (name = "contratoController")
public class ContratoController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ContratoController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	
	@ManagedProperty(value="#{contratoDataManager}")
	private ContratoDataManager contratoDataManager;
	
	
	
	public ContratoDataManager getContratoDataManager() {
		return contratoDataManager;
	}

	public void setContratoDataManager(ContratoDataManager contratoDataManager) {
		this.contratoDataManager = contratoDataManager;
	}


public ContratoController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		/*buscarFormaPago();
		buscarNicho();*/
		
	
	}
	
	
	public void registrarContrato () {
		
		slf4jLogger.info("registrarContrato");
		
		ContratoVO contratoVO;
		NichoDTO nichoDTO;
		CatalogoEucaristiaDTO formaPago;
		
					
		try {
		
			contratoVO=new ContratoVO();
			nichoDTO=new NichoDTO();
			formaPago=new CatalogoEucaristiaDTO();
			
			contratoVO.setDifunto(contratoDataManager.getDifuntoInsertar());
			contratoVO.setContratoDTO(contratoDataManager.getContratoDTO());
			nichoDTO.setNicCodigo(contratoDataManager.getNichoCodigo());
			formaPago.setCatCodigo(contratoDataManager.getFormaPagoCodigo());
			contratoVO.setFormaPago(formaPago);
			contratoVO.setNichoDTO(nichoDTO);
			contratoVO.getContratoDTO().setConFechaFin(new Timestamp(contratoDataManager.getFechaFin().getTime()));
			contratoVO.getContratoDTO().setConFechaInicio(new Timestamp(contratoDataManager.getFechaInicio().getTime()));
			
			ContratoDTO contratoNuevo=this.servicioEucaristia.createOrUpdateContrato(contratoVO);
									
			if (contratoNuevo!= null) {
				contratoDataManager.setContratoDTO(new ContratoDTO());
				contratoDataManager.setDifuntoInsertar(new Persona());
				contratoDataManager.setNichoCodigo(0);
				contratoDataManager.setFormaPagoCodigo(0);
				contratoDataManager.setFechaFin(new Date());
				contratoDataManager.setFechaInicio(new Date());
															
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.contrato.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	


	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
