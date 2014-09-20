package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ContratoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
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
		DefuncionDTO defuncion;
		
					
		try {
		
			defuncion=new DefuncionDTO();
			contratoVO=new ContratoVO();
			nichoDTO=new NichoDTO();
			formaPago=new CatalogoEucaristiaDTO();
		
			
			
			contratoVO.setContratoDTO(contratoDataManager.getContratoDTO());
			
			
			nichoDTO.setNicCodigo(contratoDataManager.getNichoCodigo());
			formaPago.setCatCodigo(contratoDataManager.getFormaPagoCodigo());
			contratoVO.setFormaPago(formaPago);
			contratoVO.setNichoDTO(nichoDTO);
			contratoVO.setBeneficiario(contratoDataManager.getBeneficiariInsertar());
			
			defuncion.setDefPersona(contratoDataManager.getDefuncionListDTO().getDefPersona());
			contratoVO.getContratoDTO().setConDifunto(defuncion.getDefPersona());
			contratoVO.getContratoDTO().setConFechaFin(new Timestamp(contratoDataManager.getFechaFin().getTime()));
			contratoVO.getContratoDTO().setConFechaInicio(new Timestamp(contratoDataManager.getFechaInicio().getTime()));
			
			contratoVO.getContratoDTO().setConMesesPorPagar(contratoVO.getContratoDTO().getConMesesArrendamiento());
			
			ContratoDTO contratoNuevo=this.servicioEucaristia.createOrUpdateContrato(contratoVO);
			contratoDataManager.setExportDesactivado(false);						
			
			ContratoListDTO con=new ContratoListDTO();
			con.setConCodigo(contratoNuevo.getConCodigo());
			con.setConBeneficiario(contratoNuevo.getConBeneficiario());
			con.setConDifunto(contratoNuevo.getConDifunto());
			con.setNicCodigo(contratoNuevo.getEucNicho().getNicCodigo());
			
			cargarDatosContrato(con);
			
			
			if (contratoNuevo!= null) {
//				contratoDataManager.setContratoDTO(new ContratoDTO());
//				contratoDataManager.setNichoCodigo(0);
//				contratoDataManager.setFormaPagoCodigo(0);
//				contratoDataManager.setFechaFin(new Date());
//				contratoDataManager.setFechaInicio(new Date());
//				contratoDataManager.setBeneficiariInsertar(new Persona());
															
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
	
	public void buscarNicho2() {
		slf4jLogger.info("buscarNicho");
		List<NichoListDTO> listResultado=new ArrayList<NichoListDTO>();
		
		try {
			
			NichoListDTO nicho=new NichoListDTO();
			nicho.setNicCodigo(this.contratoDataManager.getNichoCodigo());
			
			listResultado = this.servicioEucaristia.buscarNicho(nicho);
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.contratoDataManager.setNichoListDTO(listResultado.get(0));
			
				
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
	
	
	public void buscarBeneficiario () {
		slf4jLogger.info("buscarBeneficiario");
		
		List<Persona> listaBautizado=null;
		
		try {
			contratoDataManager.getBeneficiariInsertar().setPerNombres(null);
			contratoDataManager.getBeneficiariInsertar().setPerApellidos(null);
			
			listaBautizado=this.servicioAdministracion.buscarPersona(contratoDataManager.getBeneficiariInsertar());
							
			if (CollectionUtils.isEmpty(listaBautizado) && listaBautizado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.contratoDataManager.setBeneficiariInsertar(listaBautizado.get(0));
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBeneficiario {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}

	public void cargarDatosContrato(ContratoListDTO contrato) {
		try {
			
			ContratoVO contratoEncontrado=servicioEucaristia.obtenerContratoPorId(contrato);
			contratoDataManager.setBeneficiariInsertar(contratoEncontrado.getBeneficiario());
			contratoDataManager.setContratoDTO(contratoEncontrado.getContratoDTO());
			contratoDataManager.setDifuntoInsertar(contratoEncontrado.getDifunto());
			contratoDataManager.setFormaPagoCodigo(contratoEncontrado.getContratoDTO().getConFormaPago());
			contratoDataManager.setFechaFin(contratoEncontrado.getContratoDTO().getConFechaFin());
			contratoDataManager.setFechaInicio(contratoEncontrado.getContratoDTO().getConFechaInicio());
			contratoDataManager.setNichoCodigo(contratoEncontrado.getContratoDTO().getEucNicho().getNicCodigo());
			contratoDataManager.setNichoDTOEditar(contratoEncontrado.getNichoDTO());
			
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosContrato {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosContrato seleccionado");
		}
	}
	
	
	/*public void actualizar()
	{
		ContratoDTO contrato;
		try {
			contrato=new ContratoDTO();
			contrato.setConCodigo(contratoDataManager.getContratoListDTOEditar().getConCodigo());
			contrato.setConAnioArrendamiento(contratoDataManager.getContratoListDTOEditar().getConAnioArrendamiento());
			contrato.setConBeneficiario(contratoDataManager.getContratoListDTOEditar().getConBeneficiario());
			contrato.setConFechaFin(contratoDataManager.getContratoListDTOEditar().getConFechaFin());
			contrato.setConFechaInicio(contratoDataManager.getContratoListDTOEditar().getConFechaInicio());
			contrato.setConFormaPago(contratoDataManager.getContratoListDTOEditar().getConFormaPago());
			contrato.setConObservacion(contratoDataManager.getContratoListDTOEditar().getConObservacion());
						
			servicioEucaristia.updateContrato(contrato);
			
			MensajesWebController.aniadirMensajeInformacion("erp.despacho.contrato.actualizar.exito");
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	*/

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

	public void exportar()
	{
		
		//Date fechaActual = new Date();
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("beneficiarioNombre", contratoDataManager.getBeneficiariInsertar().getPerNombres());
		mapParametros.put("beneficiarioApellido", contratoDataManager.getBeneficiariInsertar().getPerApellidos());
		mapParametros.put("beneficiarioCedula", contratoDataManager.getBeneficiariInsertar().getPerCi());
		mapParametros.put("tipoNicho", contratoDataManager.getNichoListDTOs().get(0).getTniDescripcion());
		mapParametros.put("numeroNicho", contratoDataManager.getNichoListDTOs().get(0).getNicDescripcion());
		mapParametros.put("seccionNicho", contratoDataManager.getNichoListDTOs().get(0).getCatDescripcion());
		mapParametros.put("nivelNicho", contratoDataManager.getNichoListDTOs().get(0).getNniDescripcion());
		mapParametros.put("difuntoApellido", contratoDataManager.getDifuntoInsertar().getPerApellidos());
		mapParametros.put("difuntoNombre", contratoDataManager.getDifuntoInsertar().getPerNombres());
		mapParametros.put("mesesArrendamiento", contratoDataManager.getContratoDTO().getConMesesArrendamiento());
		mapParametros.put("fechaInicio", contratoDataManager.getContratoDTO().getConFechaInicio());
		mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
		//mapParametros.put("fecha", fechaActual);
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "comprobanteContrato", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.contratoDataManager.getFormatoPdf(), "actaBien");
		
	}
	
}
