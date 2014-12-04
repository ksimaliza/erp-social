package ec.edu.uce.erp.web.controladores;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
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
			
			pago.getPago().setPagFecha(new Timestamp(pagoContratoDataManager.getFechaPago().getTime()));
			pago.getPago().setPagSaldo(pagoContratoDataManager.getContratoListDTO().getConValorSaldo());
			pago.getPago().setPagMesesFaltantes(pagoContratoDataManager.getContratoListDTO().getConMesesPorPagar());
			contratoDTO.setConValorSaldo(pago.getPago().getPagSaldo());
			
			contratoDTO.setConBeneficiario(pagoContratoDataManager.getContratoListDTO().getConBeneficiario());
			contratoDTO.setConDifunto(pagoContratoDataManager.getContratoListDTO().getConDifunto());
			contratoDTO.setConFechaFin(pagoContratoDataManager.getContratoListDTO().getConFechaFin());
			contratoDTO.setConFechaInicio(pagoContratoDataManager.getContratoListDTO().getConFechaInicio());
			contratoDTO.setConFormaPago(pagoContratoDataManager.getContratoListDTO().getConFormaPago());
			contratoDTO.setConMesesArrendamiento(pagoContratoDataManager.getContratoListDTO().getConMesesArrendamiento());
			contratoDTO.setConMesesPorPagar(pagoContratoDataManager.getContratoListDTO().getConMesesPorPagar());
			contratoDTO.setConNicho(pagoContratoDataManager.getContratoListDTO().getConNicho());
			contratoDTO.setConObservacion(pagoContratoDataManager.getContratoListDTO().getConObservacion());
			contratoDTO.setConValorMes(pagoContratoDataManager.getContratoListDTO().getConValorMes());
			contratoDTO.setConValorTotal(pagoContratoDataManager.getContratoListDTO().getConValorTotal());
			
			pago.setContratoDTO(contratoDTO);
			pago.getPago().setPagContrato(contratoDTO.getConCodigo());
			
			if(pagoContratoDataManager.getPagoDTO().getPagMesesPagados()>pagoContratoDataManager.getContratoListDTO().getConMesesArrendamiento())
			{
				MensajesWebController.aniadirMensajeError("Meses a Pagar superior a los Meses de arrendamiento del Contrato");
				return;
			}
			
			if(Double.parseDouble(pagoContratoDataManager.getContratoListDTO().getConValorSaldo().toString())==0)
			{
				pagoContratoDataManager.setDesactivado(true);
				MensajesWebController.aniadirMensajeError("No existen pagos pendientes para este contrato");
				return;
			}
			
			PagoDTO pagoNuevo=this.servicioEucaristia.createOrUpdatePagoContrato(pago);
			pagoContratoDataManager.setExportDesactivado(false);
			
			
			PagoContratoListDTO pago1=new PagoContratoListDTO();
			pago1.setPagCodigo(pagoNuevo.getPagCodigo());
			pago1.setPagContrato(pagoNuevo.getPagContrato());
			pago1.setPagFecha(pagoNuevo.getPagFecha());
			pago1.setPagValorPagado(pagoNuevo.getPagValorPagado());
			pago1.setPagMesesPagados(pagoNuevo.getPagMesesPagados());
			pago1.setPagMesesFaltantes(pagoNuevo.getPagMesesFaltantes());
			pago1.setPagSaldo(pagoNuevo.getPagSaldo());
			
			cargarDatosPagoContrato(pago1);
						
			if (pagoNuevo!= null) {
			/*	pagoContratoDataManager.setPagoDTO(new PagoDTO());
				pagoContratoDataManager.setContratoListDTO(new ContratoListDTO());
				pagoContratoDataManager.setFechaPago(new Date());*/

				MensajesWebController.aniadirMensajeInformacion("erp.despacho.contrato.pago.registrar.exito");
			}
			buscarPagoContrato();
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
			pago.setPagValorPagado(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagValorPagado());
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
			for ( PagoContratoListDTO pagoContratoListDTO: pagoContratoDataManager.getPagoContratoListDTOs()) {
				System.out.println("Código de contrato: "+pagoContratoListDTO.getPagCodigo());
			}
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
			if(this.pagoContratoDataManager.getContratoListDTO().getPerCi()!=null && this.pagoContratoDataManager.getContratoListDTO().getPerCi()!="" )
			{
				listaContrato=this.servicioEucaristia.buscarContrato(this.pagoContratoDataManager.getContratoListDTO());
				if (CollectionUtils.isEmpty(listaContrato) && listaContrato.size()==0) {
					pagoContratoDataManager.setDesactivado(true);
					MensajesWebController.aniadirMensajeAdvertencia("Busqueda vacía. Ingrese información en Contrato");
				} else {
					this.pagoContratoDataManager.setContratoListDTO(listaContrato.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarContrato {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void buscarContrato2 () {
		slf4jLogger.info("buscarContrato");
		List<ContratoListDTO> listaContrato=null;
		try {	
			  listaContrato=this.servicioEucaristia.buscarContrato(this.pagoContratoDataManager.getContratoListDTO());
				if (CollectionUtils.isEmpty(listaContrato) && listaContrato.size()==0) {
					pagoContratoDataManager.setDesactivado(true);
					MensajesWebController.aniadirMensajeAdvertencia("Busqueda vacía. Ingrese información en Contrato");
				} else {
					for (ContratoListDTO contratoListDTO : listaContrato) {
						if(contratoListDTO.getConCodigo()==this.pagoContratoDataManager.getContratoListDTO().getConCodigo())
						this.pagoContratoDataManager.setContratoListDTO(contratoListDTO);
					}
					
				}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarContrato {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	

	public void cargarDatosPagoContrato(PagoContratoListDTO pagoContratoListDTO) {
		try {
			this.pagoContratoDataManager.setPagoContratoListDTOEditar(pagoContratoListDTO);
			this.pagoContratoDataManager.getContratoListDTO().setConCodigo(this.pagoContratoDataManager.getPagoContratoListDTOEditar().getConCodigo());
			buscarContrato2();										
			
		} catch (Exception e) {
			slf4jLogger.info("Error al cargarDatosSepultura {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosSepultura seleccionado");
		}
	}

	public void calcularValorPagar()
	{
		slf4jLogger.info("calcularValorPagar");
		BigDecimal valorPagar;
		try {
		valorPagar=new BigDecimal(servicioEucaristia.calcularValorPagar(pagoContratoDataManager.getPagoDTO(),pagoContratoDataManager.getContratoListDTO()).doubleValue());		
		valorPagar = valorPagar.setScale(2, BigDecimal.ROUND_UP);
		pagoContratoDataManager.getPagoDTO().setPagValorPagado(valorPagar);
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al calcularValorPagar {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al calcularValorTotal seleccionado");
		}
	}
	
	
	public void exportar()
	{
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
				
		//DateFormat pequeña = DateFormat.getDateInstance(DateFormat.SHORT);
		
		DefuncionListDTO defuncionAux=new DefuncionListDTO();
		defuncionAux.setPerCi(pagoContratoDataManager.getPagoContratoListDTOEditar().getPerCi());
		List<DefuncionListDTO> listaDefunciones=null;
		 try {
			listaDefunciones= servicioEucaristia.buscarDefuncion(defuncionAux);
		
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al exportar defuncion no encontrados {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al exportar defunciones no encontrados");
		
		}
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		mapParametros.put("beneficiario", pagoContratoDataManager.getPagoContratoListDTOEditar().getBennombres().toUpperCase()+" "+pagoContratoDataManager.getPagoContratoListDTOEditar().getBenapellidos().toUpperCase());
		mapParametros.put("beneficiarioCedula", pagoContratoDataManager.getPagoContratoListDTOEditar().getBenci());
		mapParametros.put("difunto", pagoContratoDataManager.getPagoContratoListDTOEditar().getPerNombres().toUpperCase()+" "+pagoContratoDataManager.getPagoContratoListDTOEditar().getPerApellidos().toUpperCase());
		mapParametros.put("nicho", pagoContratoDataManager.getPagoContratoListDTOEditar().getNicDescripcion()+" - "+ pagoContratoDataManager.getPagoContratoListDTOEditar().getNniDescripcion()+" - "+pagoContratoDataManager.getPagoContratoListDTOEditar().getSeccion()+" - "+pagoContratoDataManager.getPagoContratoListDTOEditar().getTniDescripcion());
		mapParametros.put("mesPago", pagoContratoDataManager.getPagoContratoListDTOEditar().getPagMesesFaltantes().toString()+"/"+pagoContratoDataManager.getContratoListDTO().getConMesesArrendamiento().toString());
		mapParametros.put("fechaPago", pagoContratoDataManager.getPagoContratoListDTOEditar().getPagFecha().toString().substring(2, 10));
		
		if (!CollectionUtils.isEmpty(listaDefunciones) && listaDefunciones != null)
			for (int i = 0; i < listaDefunciones.size(); i++)
			{
				System.out.println(pagoContratoDataManager.getPagoContratoListDTOEditar().getConDifunto());
		        System.out.println(listaDefunciones.get(i).getDefPersona());
				if (pagoContratoDataManager.getPagoContratoListDTOEditar().getConDifunto().equals(listaDefunciones.get(i).getDefPersona())) {
					  System.out.println(listaDefunciones.get(i).getDefPersona());
					mapParametros.put("parroquiaCabecera", "\"" + listaDefunciones.get(i).getCatParroquia().toUpperCase() +"\"");
					mapParametros.put("parroquiaFechaActual", listaDefunciones.get(i).getCatParroquia()+", "+full.format(fechaActual) );
				}
			}
	    mapParametros.put("valorPagar", pagoContratoDataManager.getPagoContratoListDTOEditar().getPagValorPagado().toString());
		
        mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "comprobantePago", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.pagoContratoDataManager.getFormatoPdf(), "ComprobantePago");
		
	}
	
	public void exportarPdf(PagoContratoListDTO pagoContratoListDTO)
	{
		cargarDatosPagoContrato(pagoContratoListDTO);
		exportar();
	}
	
	public void limpiarFormulario()
	{
		pagoContratoDataManager.setPagoDTO(new PagoDTO());
		pagoContratoDataManager.setContratoListDTO(new ContratoListDTO());
		pagoContratoDataManager.setFechaPago(new Date());
		pagoContratoDataManager.setDesactivado(false);
		pagoContratoDataManager.setExportDesactivado(true);
	}
	
	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

	

}
