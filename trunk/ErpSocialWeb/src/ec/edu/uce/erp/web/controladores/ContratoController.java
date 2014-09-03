package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
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
		buscarFormaPago();
		buscarNicho();
		
	
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
	
	public void buscarDefuncion () {
		slf4jLogger.info("buscarDefuncion");
		
		List<DefuncionListDTO> listaDefuncion=null;
		
		try {
		 
			listaDefuncion=this.servicioEucaristia.buscarDefuncion(new DefuncionListDTO());
					
			if (CollectionUtils.isEmpty(listaDefuncion) && listaDefuncion.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.contratoDataManager.setDefuncionListDTO(listaDefuncion.get(0));
				
						
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDefuncion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarFormaPago () {
		slf4jLogger.info("buscarFormaPago");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(34);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.contratoDataManager.setFormaPagoListDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarFormaPago {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarNicho() {
		slf4jLogger.info("buscarNicho");
		List<NichoListDTO> listResultado=new ArrayList<NichoListDTO>();
		try {
			listResultado = this.servicioEucaristia.buscarNicho(new NichoListDTO());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.contratoDataManager.setNichoListDTOs(listResultado);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el buscarNicho {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	
	
	public void buscarContrato () {
		slf4jLogger.info("buscarContrato");
		
		List<ContratoListDTO> listaContrato=null;
		
		try {
			
			listaContrato=this.servicioEucaristia.buscarContrato(contratoDataManager.getContratoListDTO());
			
			if (CollectionUtils.isEmpty(listaContrato) && listaContrato.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.contratoDataManager.setContratoListDTOs(listaContrato);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarContrato {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	public void cargarDatosContrato (ContratoListDTO contrato) {
		try {
			
			ContratoVO contratoEncontrado=servicioEucaristia.obtenerContratoPorId(contrato);
			this.contratoDataManager.setDifuntoInsertar(contratoEncontrado.getDifunto());
			this.contratoDataManager.setContratoDTO(contratoEncontrado.getContratoDTO());
			this.contratoDataManager.setFormaPagoCodigo(contratoEncontrado.getContratoDTO().getConFormaPago());
			this.contratoDataManager.setNichoCodigo(contratoEncontrado.getContratoDTO().getEucNicho().getNicCodigo());
			
										
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosContrato {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosContrato seleccionado");
		}
	}
	

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
