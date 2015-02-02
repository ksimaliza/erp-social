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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ContratoVO;
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
			contratoDTO.setConEmpresa(pagoContratoDataManager.getContratoListDTO().getConEmpresa());
			pago.setContratoDTO(contratoDTO);
			pago.getPago().setPagContratoFechaIncio(contratoDTO.getConFechaInicio());
			pago.getPago().setPagContratoFechaFin(contratoDTO.getConFechaFin());
			pago.getPago().setPagNichoDescripcion(pagoContratoDataManager.getContratoListDTO().getNicDescripcion());
			pago.getPago().setPagContrato(contratoDTO.getConCodigo());
			pago.getPago().setPagEmpresa(getEmpresaTbl().getEmrPk());
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
			
                this.pagoContratoDataManager.setDesactivado(true);
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
		PagoContratoListDTO pagoSinActualizar=null;
		try {
			Integer sumaContratoMeses=null; 
			Integer mesesContratoPagar=null;
			PagoContratoListDTO pagoObtener= new PagoContratoListDTO();
			pagoObtener.setPagCodigo(this.pagoContratoDataManager.getPagoContratoListDTOEditar().getPagCodigo());
			pagoSinActualizar=this.servicioEucaristia.readPago(pagoObtener).get(0);
			if(pagoSinActualizar!=null){
				 sumaContratoMeses=pagoSinActualizar.getPagMesesPagados().intValue()+pagoContratoDataManager.getPagoContratoListDTOEditar().getConMesesPorPagar().intValue();
				System.out.println("Meses Pagados anterio: "+pagoSinActualizar.getPagMesesPagados());
				System.out.println("Meses Contrato meses por pagar: "+pagoContratoDataManager.getPagoContratoListDTOEditar().getConMesesPorPagar());
				System.out.println("Suma de meses por pagar: "+sumaContratoMeses);
			       if(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagMesesPagados()>sumaContratoMeses) {
			    	   pagoContratoDataManager.getPagoContratoListDTOEditar().setPagMesesPagados(null);
						MensajesWebController.aniadirMensajeAdvertencia("Meses a Pagar Contrato excede a Meses de Pago Pendiete");
						return; 
			       }
			        mesesContratoPagar=sumaContratoMeses.intValue()-pagoContratoDataManager.getPagoContratoListDTOEditar().getPagMesesPagados().intValue();
			       ContratoListDTO contrato=new ContratoListDTO();
			       contrato.setConCodigo(pagoContratoDataManager.getPagoContratoListDTOEditar().getConCodigo());
			       ContratoListDTO contratoConsulta=servicioEucaristia.buscarContrato(contrato).get(0);
			       if(contratoConsulta!=null){
			    	   BigDecimal valorContratoSaldo=contratoConsulta.getConValorMes().multiply(BigDecimal.valueOf(mesesContratoPagar));
			    	   contratoConsulta.setConMesesPorPagar(mesesContratoPagar);
			    	   contratoConsulta.setConValorSaldo(valorContratoSaldo);
			    	   ContratoVO contratoVO =this.servicioEucaristia.obtenerContratoPorId(contratoConsulta);
			    	   if(contratoVO!=null)  {
			    		   contratoVO.getContratoDTO().setConMesesPorPagar(mesesContratoPagar);
			    		   contratoVO.getContratoDTO().setConValorSaldo(valorContratoSaldo);
			    		   CatalogoEucaristiaDTO formaPago=new CatalogoEucaristiaDTO();
			    		   formaPago.setCatCodigo(pagoContratoDataManager.getPagoContratoListDTOEditar().getConFormaPago());
			    		   contratoVO.setFormaPago(formaPago);
			    		   servicioEucaristia.createOrUpdateContrato(contratoVO);
			    	   }
			       }
			}
				
			pago=new PagoDTO();
			pago.setPagCodigo(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagCodigo());
			pago.setPagValorPagado(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagValorPagado());
			pago.setPagFecha(new Timestamp(pagoContratoDataManager.getFechaPago().getTime()));
			pago.setPagMesesPagados(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagMesesPagados());
			pago.setPagMesesFaltantes(mesesContratoPagar);
			PagoDTO pagoActualizado=this.servicioEucaristia.updatePagoContrato(pago);
			if(pagoActualizado!=null){
				PagoContratoListDTO pagoContratoListDTO1= new PagoContratoListDTO();
				pagoContratoListDTO1.setPagCodigo(pagoActualizado.getPagCodigo());
				cargarDatosPagoContrato(this.servicioEucaristia.readPago(pagoContratoListDTO1).get(0));
				
			}
			
			MensajesWebController.aniadirMensajeInformacion("erp.despacho.contrato.pago.actualizar");
			buscarPagoContrato();
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError("Error Actualizar Pago Contrato: "+e.getMessage());
		}
	}
	
	
	public void buscarPagoContrato()
	{
		try {
			this.pagoContratoDataManager.getPagoContratoListDTO().setPagEmpresa(getEmpresaTbl().getEmrPk());
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
			if(this.pagoContratoDataManager.getContratoListDTO().getPerCi()!=null && this.pagoContratoDataManager.getContratoListDTO().getPerCi()!="" )
			{
				this.pagoContratoDataManager.getContratoListDTO().setConEmpresa(getEmpresaTbl().getEmrPk());
				listaContrato=this.servicioEucaristia.buscarContrato(this.pagoContratoDataManager.getContratoListDTO());
				if (CollectionUtils.isEmpty(listaContrato) && listaContrato.size()==0) {
					pagoContratoDataManager.setDesactivado(true);
					MensajesWebController.aniadirMensajeAdvertencia("Busqueda vac�a. Ingrese informaci�n en Contrato");
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
					MensajesWebController.aniadirMensajeAdvertencia("Busqueda vac�a. Ingrese informaci�n en Contrato");
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
			this.pagoContratoDataManager.setDesactivado(false);
			this.pagoContratoDataManager.setPagoContratoListDTOEditar(pagoContratoListDTO);
			ContratoListDTO contrato= new ContratoListDTO();
			contrato.setConCodigo(pagoContratoDataManager.getPagoContratoListDTOEditar().getConCodigo());
			List<ContratoListDTO> contratos=servicioEucaristia.buscarContrato(contrato);
			this.pagoContratoDataManager.setContratoListDTO(contratos.get(0));
			this.pagoContratoDataManager.setFechaPago( new Date(this.pagoContratoDataManager.getPagoContratoListDTOEditar().getPagFecha().getTime()));
			buscarContrato2();										
		} catch (Exception e) {
			slf4jLogger.info("Error al cargarDatosSepultura {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosSepultura seleccionado");
		}
	}
	public void calcularValorPagarEditar()
	{
		slf4jLogger.info("calcularValorPagarEditar");
		try {
			//pagoContratoDataManager.setContratoListDTO(servicioEucaristia.buscarContrato(pagoContratoDataManager.getContratoListDTO()).get(0));
			PagoContratoListDTO pagoContrato=new PagoContratoListDTO();
			BigDecimal valorPagar;
			pagoContrato.setPagCodigo(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagCodigo());
			PagoContratoListDTO pagoContratoObtenido=this.servicioEucaristia.readPago(pagoContrato).get(0);
				if(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagMesesPagados().intValue()>(pagoContratoObtenido.getPagMesesPagados().intValue()+pagoContratoDataManager.getContratoListDTO().getConMesesPorPagar().intValue()))
				{
					pagoContratoDataManager.getPagoContratoListDTOEditar().setPagMesesPagados(null);
				MensajesWebController.aniadirMensajeAdvertencia("Meses a Pagar Contrato excede a Meses de Pago Pendiete");
				return;
				}
				PagoDTO pago=new PagoDTO();
				pago.setPagMesesPagados(pagoContratoDataManager.getPagoContratoListDTOEditar().getPagMesesPagados());
			valorPagar=new BigDecimal(servicioEucaristia.calcularValorPagar(pago,pagoContratoDataManager.getContratoListDTO()).doubleValue());		
			valorPagar = valorPagar.setScale(2, BigDecimal.ROUND_UP);
			pagoContratoDataManager.getPagoContratoListDTOEditar().setPagValorPagado(valorPagar);
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al calcularValorPagar {}", e.getMessage());
				MensajesWebController.aniadirMensajeError("Error al calcularValorTotalEditar seleccionado");
			}
	}
	public void calcularValorPagar()
	{
		slf4jLogger.info("calcularValorPagar");
		BigDecimal valorPagar;
		try {
			if(pagoContratoDataManager.getContratoListDTO().getConMesesPorPagar().equals(0))
			{
				pagoContratoDataManager.getPagoDTO().setPagMesesPagados(null);
				pagoContratoDataManager.setDesactivado(true);
			MensajesWebController.aniadirMensajeAdvertencia("No tiene pagos pendientes por el contrato Actual");
			return;
			}
			if(pagoContratoDataManager.getPagoDTO().getPagMesesPagados()>pagoContratoDataManager.getContratoListDTO().getConMesesPorPagar())
			{
				pagoContratoDataManager.getPagoDTO().setPagMesesPagados(null);
			MensajesWebController.aniadirMensajeAdvertencia("Meses a Pagar Contrato excede a Meses de Pago Pendiete");
			return;
			}
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
		mapParametros.put("mesPago", pagoContratoDataManager.getContratoListDTO().getConMesesPorPagar().toString()+"/"+pagoContratoDataManager.getContratoListDTO().getConMesesArrendamiento().toString());
		mapParametros.put("fechaPago", pagoContratoDataManager.getPagoContratoListDTOEditar().getPagFecha().toString().substring(2, 10));
		
		if (!CollectionUtils.isEmpty(listaDefunciones) && listaDefunciones != null)
			for (int i = 0; i < listaDefunciones.size(); i++)
			{
				System.out.println(pagoContratoDataManager.getPagoContratoListDTOEditar().getConDifunto());
		        System.out.println(listaDefunciones.get(i).getDefPersona());
				if (pagoContratoDataManager.getPagoContratoListDTOEditar().getConDifunto().equals(listaDefunciones.get(i).getDefPersona())) {
					  System.out.println(listaDefunciones.get(i).getDefPersona());
					mapParametros.put("parroquiaCabecera", "\"" + listaDefunciones.get(i).getCatParroquia().toUpperCase() +"\"");
					mapParametros.put("parroquiaFechaActual", listaDefunciones.get(i).getCatParroquia()+", "+String.valueOf(full.format(fechaActual).charAt(0)).toUpperCase() +full.format(fechaActual).substring(1));
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
	public void exportarPdf1()
	{
		List<PagoContratoListDTO> pagoContratoListDTOs=null;
		PagoContratoListDTO pagoContratoListDTO= new PagoContratoListDTO();
		pagoContratoListDTO.setPagCodigo(pagoContratoDataManager.getPagoDTO().getPagCodigo());
		pagoContratoListDTO.setConCodigo(pagoContratoDataManager.getPagoDTO().getPagContrato());
		pagoContratoListDTO.setPagEmpresa(getEmpresaTbl().getEmrPk());
		try {
			pagoContratoListDTOs=this.servicioEucaristia.readPago(pagoContratoListDTO);
			if (!CollectionUtils.isEmpty(pagoContratoListDTOs) && pagoContratoListDTOs != null)
				cargarDatosPagoContrato(pagoContratoListDTOs.get(0));
				exportar();
				pagoContratoDataManager.setDesactivado(true);
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al exportar pago contrato no encontrado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al exportar pago contrato no encontrado");
		
		}
		
	}
	public void limpiarFormulario()
	{
		System.out.println("limpiarFormulario");
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
