package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.PagoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.PagoContratoDataManager;


@ViewScoped
@ManagedBean (name = "pagoContratoController")
public class PagoContratoController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PagoContratoController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	
	@ManagedProperty(value="#{pagoContratoDataManager}")
	private PagoContratoDataManager pagoContratoDataManager;
	
	public PagoContratoDataManager getPagoContratoDataManager() {
		return pagoContratoDataManager;
	}

	public void setPagoContratoDataManager(PagoContratoDataManager pagoContratoDataManager) {
		this.pagoContratoDataManager = pagoContratoDataManager;
	}
	


	public PagoContratoController() {
		
		}
	
	@PostConstruct
	public void inicializarObjetos () {		
	
	}
	
	
	public void registrarPagoContrato() {
		
		slf4jLogger.info("registrarPagoContrato");
		
		PagoVO pago;
		ContratoDTO contratoDTO;
		
		try {
		
			contratoDTO=new ContratoDTO();
			pago=new PagoVO();
			pago.setPago(pagoContratoDataManager.getPagoDTO());
			contratoDTO.setConCodigo(pagoContratoDataManager.getContratoListDTO().getConCodigo());
			pago.getPago().setPagContrato(contratoDTO.getConCodigo());
			pago.getPago().setPagFecha(new Timestamp(pagoContratoDataManager.getFechaPago().getTime()));
			
			
			PagoDTO pagoNuevo=this.servicioEucaristia.createOrUpdatePagoContrato(pago);
			pagoContratoDataManager.setExportDesactivado(false);
			
			/*ContratoListDTO con=new ContratoListDTO();
			con.setConCodigo(contratoNuevo.getConCodigo());
			con.setConBeneficiario(contratoNuevo.getConBeneficiario());
			con.setConDifunto(contratoNuevo.getConDifunto());
			con.setNicCodigo(contratoNuevo.getEucNicho().getNicCodigo());
			
			cargarDatosContrato(con);
			*/
			
			if (pagoNuevo!= null) {
				/*pagoContratoDataManager.setPagoDTO(new PagoDTO());
				pagoContratoDataManager.setContratoListDTO(new ContratoListDTO());
				pagoContratoDataManager.setFechaPago(new Date());*/
				
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.contrato.pago.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void actualizar()
	{
		PagoDTO pago;
		try {
			pago=new PagoDTO();
			pago.setPagCodigo(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagCodigo());
			pago.setPagValor(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagValor());
			pago.setPagFecha(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagFecha());
			pago.setPagMesesPagados(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagMesesPagados());
			servicioEucaristia.updatePagoContrato(pago);
			
			MensajesWebController.aniadirMensajeInformacion("erp.despacho.contrato.pago.actualizar");
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void buscarPagoContrato()
	{
		try {
			
			pagoContratoDataManager.setPagoContratoListDTOs(this.servicioEucaristia.readPago(this.pagoContratoDataManager.getPagoContratoListDTO()));
			this.pagoContratoDataManager.setPagoContratoListDTO(new PagoContratoListDTO());
			
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPagoContrato {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	

	public void buscarContrato () {
		slf4jLogger.info("buscarContrato");
		
		List<ContratoListDTO> listaContrato=null;
		
		try {
			
			listaContrato=this.servicioEucaristia.buscarContrato(new ContratoListDTO());
			
			if (CollectionUtils.isEmpty(listaContrato) && listaContrato.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.pagoContratoDataManager.setContratoListDTO(listaContrato.get(0));
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarContrato {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	

	public void cargarDatosPagoContrato(PagoContratoListDTO pagoContratoListDTO) {
		try {
			this.pagoContratoDataManager.setPagoContratoListDTOEditar(pagoContratoListDTO);
						
													
		} catch (Exception e) {
			slf4jLogger.info("Error al cargarDatosSepultura {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosSepultura seleccionado");
		}
	}

	public void exportar()
	{
		
		//Date fechaActual = new Date();
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("beneficiarioNombre", pagoContratoDataManager.getPagoContratoListDTOEditar().getBennombres());
		mapParametros.put("beneficiarioApellido", pagoContratoDataManager.getPagoContratoListDTOEditar().getBenapellidos());
		mapParametros.put("beneficiarioCedula", pagoContratoDataManager.getPagoContratoListDTOEditar().getBenci());
		mapParametros.put("tipoNicho", pagoContratoDataManager.getPagoContratoListDTOEditar().getTniDescripcion());
		mapParametros.put("numeroNicho", pagoContratoDataManager.getPagoContratoListDTOEditar().getNicDescripcion());
		mapParametros.put("seccionNicho", pagoContratoDataManager.getPagoContratoListDTOEditar().getCatDescripcion());
		mapParametros.put("nivelNicho", pagoContratoDataManager.getPagoContratoListDTOEditar().getNniDescripcion());
		mapParametros.put("difuntoApellido", pagoContratoDataManager.getPagoContratoListDTOEditar().getPerApellidos());
		mapParametros.put("difuntoNombre", pagoContratoDataManager.getPagoContratoListDTOEditar().getPerNombres());
		mapParametros.put("valorPagar", pagoContratoDataManager.getPagoDTO().getPagValor());
		mapParametros.put("fechaPago", pagoContratoDataManager.getPagoDTO().getPagFecha());
		mapParametros.put("empresa", getEmpresaTbl().getEmrNombre());
		//mapParametros.put("fecha", fechaActual);
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "comprobantePago", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.pagoContratoDataManager.getFormatoPdf(), "comprobantePago");
		
	}
	

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

	

}
