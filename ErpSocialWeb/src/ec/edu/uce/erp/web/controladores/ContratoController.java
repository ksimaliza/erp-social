package ec.edu.uce.erp.web.controladores;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ContratoVO;
import ec.edu.uce.erp.ejb.persistence.vo.SepulturaVO;
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
		buscarSepulturas();
	}
	
	
	public void registrarContrato () {
		slf4jLogger.info("registrarContrato");
		ContratoVO contratoVO;
		CatalogoEucaristiaDTO formaPago;
		try {
			Calendar fechaInicio = Calendar.getInstance();
			Calendar fechaFin = Calendar.getInstance();
			fechaInicio.setTimeInMillis(contratoDataManager.getFechaInicio().getTime());
			fechaFin.setTimeInMillis(contratoDataManager.getFechaFin().getTime());
			//comprueba que las fechas de inicio y fin sean correctas
			int diasInicio=fechaInicio.get(Calendar.YEAR)*365;
			diasInicio=diasInicio+fechaInicio.get(Calendar.MONTH)*30;
			diasInicio=diasInicio+fechaInicio.get(Calendar.DAY_OF_MONTH);
			int diasFin=fechaFin.get(Calendar.YEAR)*365;
			diasFin=diasFin+fechaFin.get(Calendar.MONTH)*30;
			diasFin=diasFin+fechaFin.get(Calendar.DAY_OF_MONTH);
			System.out.println("diasInicio: "+diasInicio);
			System.out.println("diasFin: "+diasFin);
			if(diasInicio==diasFin)
			{
				MensajesWebController.aniadirMensajeError("Fecha Inicio  coincide con  Fecha Final");
				return;
			}
			if(diasInicio>diasFin)
			{
				MensajesWebController.aniadirMensajeError("Fecha Inicio excede a Fecha Final");
				return;
			}
			if(contratoDataManager.getDifuntoInsertar().getPerCi().toString().equals(contratoDataManager.getBeneficiariInsertar().getPerCi().toString()))
			{
				MensajesWebController.aniadirMensajeError("El difunto no puede ser beneficiario");
				return;
			}
			contratoVO=new ContratoVO();
			formaPago=new CatalogoEucaristiaDTO();
			contratoVO.setContratoDTO(contratoDataManager.getContratoDTO());
			formaPago.setCatCodigo(contratoDataManager.getFormaPagoCodigo());
			contratoVO.setFormaPago(formaPago);
			contratoVO.setBeneficiario(contratoDataManager.getBeneficiariInsertar());
			contratoVO.getContratoDTO().setConDifunto(contratoDataManager.getDifuntoInsertar().getPerPk());
			contratoVO.getContratoDTO().setConMesesPorPagar(contratoVO.getContratoDTO().getConMesesArrendamiento());
			contratoVO.getContratoDTO().setConValorSaldo(contratoVO.getContratoDTO().getConValorTotal());
			contratoVO.getContratoDTO().setConNicho(contratoDataManager.getSepulturaListDTO().getNicCodigo());
			
			contratoVO.getContratoDTO().setConFechaFin(new Timestamp(contratoDataManager.getFechaFin().getTime()));
			contratoVO.getContratoDTO().setConFechaInicio(new Timestamp(contratoDataManager.getFechaInicio().getTime()));
			contratoVO.getContratoDTO().setConEmpresa(getEmpresaTbl().getEmrPk());
			
			ContratoDTO contratoNuevo=this.servicioEucaristia.createOrUpdateContrato(contratoVO);
			contratoDataManager.setExportDesactivado(false);						
			ContratoListDTO con=new ContratoListDTO();
			con.setConCodigo(contratoNuevo.getConCodigo());
			con.setConBeneficiario(contratoNuevo.getConBeneficiario());
			con.setConDifunto(contratoNuevo.getConDifunto());
			
			cargarDatosContrato(con);
			
			if (contratoNuevo!= null) {
			/*	contratoDataManager.setContratoDTO(new ContratoDTO());
				contratoDataManager.setNichoCodigo(0);
				contratoDataManager.setFormaPagoCodigo(0);
				contratoDataManager.setFechaFin(new Date());
				contratoDataManager.setFechaInicio(new Date());
				contratoDataManager.setBeneficiariInsertar(new Persona());
				*/											
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.contrato.registrar.exito");
			}
			buscarContrato();
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarDifunto () {
		slf4jLogger.info("buscarDifunto");
		
		List<Persona> listaDifunto=null;
		SepulturaListDTO difunto=new SepulturaListDTO();
		List<SepulturaListDTO> list=null;
		
		try {
			buscarSepulturas();
			for (int i = 0; i < contratoDataManager.getSepulturasVO().size(); i++) {
				if(contratoDataManager.getSepulturasVO().get(i).getSepultura().getSepCodigo()==contratoDataManager.getSepulturaCodigo())
				contratoDataManager.getDifuntoInsertar().setPerCi(contratoDataManager.getSepulturasVO().get(i).getDefuncionPersona().getPerCi());
			}
			if(contratoDataManager.getDifuntoInsertar().getPerCi()!=null && contratoDataManager.getDifuntoInsertar().getPerCi()!="" )
			{
				contratoDataManager.getDifuntoInsertar().setPerNombres(null);
				contratoDataManager.getDifuntoInsertar().setPerApellidos(null);
				contratoDataManager.getDifuntoInsertar().setPerPk(null);
				listaDifunto=this.servicioAdministracion.buscarPersona(contratoDataManager.getDifuntoInsertar());					
				difunto.setPerCi(contratoDataManager.getDifuntoInsertar().getPerCi());
				list=this.servicioEucaristia.readSepultura(difunto);
				
				if ((CollectionUtils.isEmpty(listaDifunto) && listaDifunto.size()==0)||CollectionUtils.isEmpty(list) && list.size()==0) {
					contratoDataManager.setDesactivado(true);
					MensajesWebController.aniadirMensajeAdvertencia("Difunto no Encontrado. Ingresar informaci�n en Sepultura");
					
				} else {
					
					for (SepulturaListDTO sepulturaListDTO : list) {
						if(sepulturaListDTO.getSepCodigo()==contratoDataManager.getSepulturaCodigo() || contratoDataManager.getSepulturaCodigo()==-1 )
						{
							//pues el sistema no permite ingresar personas con diferente c�dula
							//por tanto es muy seguro que la c�dula es de una �nica persona
							contratoDataManager.setDifuntoInsertar(listaDifunto.get(0));
							contratoDataManager.setSepulturaListDTO(sepulturaListDTO);
							//para el caso de que se este editando un contrato
							contratoDataManager.setSepulturaCodigo(sepulturaListDTO.getSepCodigo());
						}
					}
					
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBautizado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarFormaPago () {
		slf4jLogger.info("buscarFormaPago");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			
			listaCatalogo=this.servicioEucaristia.buscarCatalogoPorId(34);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				
			} else {
				this.contratoDataManager.setFormaPagoListDTOs(listaCatalogo);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarFormaPago {} ", e);
			//MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarSepulturas () {
		slf4jLogger.info("buscarSepulturas");
		
		List<SepulturaVO> listaSepulturas=null;
		List<ContratoListDTO> listaContrato=null;
		List<SepulturaVO> listaSepulturasFiltrada=null;
		try {
			contratoDataManager.getContratoListDTO().setConEmpresa(getEmpresaTbl().getEmrPk());
			listaContrato=this.servicioEucaristia.buscarContrato(contratoDataManager.getContratoListDTO());
			
			listaSepulturas=this.servicioEucaristia.obtenerSepulturasActivasPorIdEmpresa(getEmpresaTbl().getEmrPk());
		
			if (CollectionUtils.isEmpty(listaSepulturas) && listaSepulturas.size()==0) {
				this.contratoDataManager.setSepulturasVO(new ArrayList<SepulturaVO>());
				MensajesWebController.aniadirMensajeAdvertencia("erp.despacho.contrato.sepultura.busqueda.vacia");	
			} else {
				if (!CollectionUtils.isEmpty(listaContrato) && listaContrato.size()!=0) {
					listaSepulturasFiltrada=new ArrayList<SepulturaVO>();
					
					for (SepulturaVO sepulturaVO : listaSepulturas) {
						Boolean estaRegistrada=false;
						for ( ContratoListDTO contrato : listaContrato) {
							if(contrato.getConDifunto().equals(sepulturaVO.getSepultura().getSepDifunto()) )estaRegistrada=true;
							if(contrato.getConDifunto().equals(sepulturaVO.getSepultura().getSepDifunto()) && this.contratoDataManager.getContratoDTO().getConCodigo()!=null && this.contratoDataManager.getContratoDTO().getConCodigo().equals(contrato.getConCodigo()))
							{
								listaSepulturasFiltrada.add(sepulturaVO);
								System.out.println("Si entró aqui: ");
							}
							
								System.out.println("For interno: "+estaRegistrada);
								System.out.println("De lista de contrato: "+contrato.getConCodigo() +" "+"Contrato interno: "+this.contratoDataManager.getContratoDTO().getConCodigo());
								System.out.println("contrato difunto: "+contrato.getConDifunto() +" "+"sepultura difunto: "+sepulturaVO.getSepultura().getSepDifunto());
						}
						
						if(!estaRegistrada)listaSepulturasFiltrada.add(sepulturaVO);
						System.out.println("For externo: "+estaRegistrada);
					}
					if (CollectionUtils.isEmpty(listaSepulturasFiltrada) && listaSepulturasFiltrada.size()==0) {
						this.contratoDataManager.setSepulturasVO(new ArrayList<SepulturaVO>());
						MensajesWebController.aniadirMensajeAdvertencia("erp.despacho.contrato.sepultura.busqueda.vacia");
					}else
					this.contratoDataManager.setSepulturasVO(listaSepulturasFiltrada);
				}else
				this.contratoDataManager.setSepulturasVO(listaSepulturas);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarFormaPago {} ", e);
			//MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarContrato () {
		slf4jLogger.info("buscarContrato");
		
		List<ContratoListDTO> listaContrato=null;
		try {
			contratoDataManager.getContratoListDTO().setConEmpresa(getEmpresaTbl().getEmrPk());
			listaContrato=this.servicioEucaristia.buscarContrato(contratoDataManager.getContratoListDTO());
			if (CollectionUtils.isEmpty(listaContrato) && listaContrato.size()==0) {
				this.contratoDataManager.setContratoListDTOs(new ArrayList<ContratoListDTO>());
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
			if(contratoDataManager.getBeneficiariInsertar().getPerCi()!=null && contratoDataManager.getBeneficiariInsertar().getPerCi()!="" )
			{
				contratoDataManager.getBeneficiariInsertar().setPerNombres(null);
				contratoDataManager.getBeneficiariInsertar().setPerApellidos(null);
				//contratoDataManager.getBeneficiariInsertar().setPerPk(null);
				listaBautizado=this.servicioAdministracion.buscarPersona(contratoDataManager.getBeneficiariInsertar());
								
				if (CollectionUtils.isEmpty(listaBautizado) && listaBautizado.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.contratoDataManager.setBeneficiariInsertar(listaBautizado.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBeneficiario {} ", e);
			//MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void calcularValorTotal()
	{
		slf4jLogger.info("calcularValorTotal");
		BigDecimal valor;
		try {
		valor=new BigDecimal(servicioEucaristia.calcularValorTotal(contratoDataManager.getContratoDTO()).doubleValue());
		valor = valor.setScale(2, BigDecimal.ROUND_UP);
		contratoDataManager.getContratoDTO().setConValorTotal(valor);
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al calcularValorTotal {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al calcularValorTotal seleccionado");
		}
	}
	

	public void cargarDatosContrato(ContratoListDTO contrato) {
		try {
			
			ContratoVO contratoEncontrado=servicioEucaristia.obtenerContratoPorId(contrato);
			contratoDataManager.setBeneficiariInsertar(contratoEncontrado.getBeneficiario());
			contratoDataManager.setContratoDTO(contratoEncontrado.getContratoDTO());
			contratoDataManager.setDifuntoInsertar(contratoEncontrado.getDifunto());
			buscarDifunto();
			contratoDataManager.setFormaPagoCodigo(contratoEncontrado.getContratoDTO().getConFormaPago());
			buscarFormaPago();
			contratoDataManager.setFechaFin(contratoEncontrado.getContratoDTO().getConFechaFin());
			contratoDataManager.setFechaInicio(contratoEncontrado.getContratoDTO().getConFechaInicio());
			buscarContrato();
			this.contratoDataManager.setExportDesactivado(false);
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosContrato {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosContrato seleccionado");
		}
	}
	

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
	public void exportar()
	{
		Date fechaActual = new Date();
		//DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
				
		DateFormat pequena = DateFormat.getDateInstance(DateFormat.SHORT);
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		List<CatalogoEucaristiaDTO> listaFormaPagos=null;
		DefuncionListDTO defuncionAux=new DefuncionListDTO();
		defuncionAux.setPerCi(contratoDataManager.getDifuntoInsertar().getPerCi());
		List<DefuncionListDTO> listaDefunciones=null;
		 try {
			listaDefunciones= servicioEucaristia.buscarDefuncion(defuncionAux);
		
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al exportar defuncion no encontrados {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al exportar defunciones no encontrados");
		
		}
		
		mapParametros.put("beneficiario", contratoDataManager.getBeneficiariInsertar().getPerApellidos().toUpperCase()+" "+contratoDataManager.getBeneficiariInsertar().getPerNombres().toUpperCase());
		mapParametros.put("beneficiarioCedula", contratoDataManager.getBeneficiariInsertar().getPerCi());
		mapParametros.put("difunto", contratoDataManager.getDifuntoInsertar().getPerNombres()+" "+ contratoDataManager.getDifuntoInsertar().getPerApellidos());
		mapParametros.put("tipoNicho", contratoDataManager.getSepulturaListDTO().getTniDescripcion());
		mapParametros.put("numeroNicho", contratoDataManager.getSepulturaListDTO().getNicDescripcion());
		mapParametros.put("seccionNicho", contratoDataManager.getSepulturaListDTO().getSeccion());
		mapParametros.put("nivelNicho", contratoDataManager.getSepulturaListDTO().getNniDescripcion());
		mapParametros.put("mesesArrendamiento", contratoDataManager.getContratoDTO().getConMesesArrendamiento());
		mapParametros.put("fechaInicio", pequena.format(StringToDate(contratoDataManager.getContratoDTO().getConFechaInicio().toString().substring(2, 10))));
		if (!CollectionUtils.isEmpty(listaDefunciones) && listaDefunciones != null)
			for (int i = 0; i < contratoDataManager.getContratoListDTOs().size(); i++)
				if (contratoDataManager.getContratoListDTOs().get(i).getParroquia().equals(listaDefunciones.get(0).getCatParroquia())) {
					mapParametros.put("parroquia", contratoDataManager.getContratoListDTOs().get(i).getParroquia());
					mapParametros.put("parroquiaEncabezado", "\"" + contratoDataManager.getContratoListDTOs().get(i).getParroquia().toUpperCase() + "\"");
				}
		
		mapParametros.put("fecha", pequena.format(fechaActual).toString());
		mapParametros.put("valorPagar", contratoDataManager.getContratoDTO().getConValorMes().toString());
		if (!CollectionUtils.isEmpty(contratoDataManager.getFormaPagoListDTOs()) && contratoDataManager.getFormaPagoListDTOs() != null)
		{
			for (CatalogoEucaristiaDTO formaPagoDTO : contratoDataManager.getFormaPagoListDTOs()) {
				if(formaPagoDTO.getCatCodigo().equals(contratoDataManager.getContratoDTO().getConFormaPago()))
					mapParametros.put("formaPago", formaPagoDTO.getCatDescripcion());
			}
		
		}
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "comprobanteContrato", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.contratoDataManager.getFormatoPdf(), "Comprobante Contrato");
		
	}
	
	public void exportarPdf(ContratoListDTO contrato)
	{
		cargarDatosContrato(contrato);
		exportar();
	}
	
	public void exportarPdfDeLista(ContratoListDTO contrato)
	{
		cargarDatosContratoEditar(contrato);
		exportar();
	}
	
	public void limpiarFormulario()
	{
		contratoDataManager.setSepulturaCodigo(0);
		contratoDataManager.setDifuntoInsertar(new Persona());
		contratoDataManager.setContratoDTO(new ContratoDTO());
		contratoDataManager.setNichoCodigo(0);
		contratoDataManager.setFormaPagoCodigo(0);
		contratoDataManager.setFechaFin(new Date());
		contratoDataManager.setFechaInicio(new Date());
		contratoDataManager.setBeneficiariInsertar(new Persona());
		contratoDataManager.setSepulturaListDTO(new SepulturaListDTO());
		contratoDataManager.setSepulturasVO(new ArrayList<SepulturaVO>());
		contratoDataManager.setExportDesactivado(true);
		buscarSepulturas();
	}
	
	public void cargarDatosContratoEditar(ContratoListDTO contrato)
	{
		
		contratoDataManager.setSepulturaCodigo(-1);
		cargarDatosContrato(contrato);
	}
	
	public Date StringToDate(String strFecha){
		 SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yy-MM-dd");
		 Date fecha = null;

		     try {
				fecha = formatoDelTexto.parse(strFecha);
			

		     } catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     return fecha;
		 }
	
	public void obtenerNumMeses() throws SeguridadesException {
		slf4jLogger.info("obtenerNumMeses");
		contratoDataManager.getContratoDTO().setConValorMes(null);
		contratoDataManager.getContratoDTO().setConValorTotal(null);
		contratoDataManager.getContratoDTO().setConMesesArrendamiento(null);
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();
		Calendar diferenciaFechas = Calendar.getInstance();
		fechaInicio.setTimeInMillis(contratoDataManager.getFechaInicio().getTime());
		fechaFin.setTimeInMillis(contratoDataManager.getFechaFin().getTime());
		int diasInicio=fechaInicio.get(Calendar.YEAR)*365;
		diasInicio=diasInicio+fechaInicio.get(Calendar.MONTH)*30;
		diasInicio=diasInicio+fechaInicio.get(Calendar.DAY_OF_MONTH);
		int diasFin=fechaFin.get(Calendar.YEAR)*365;
		diasFin=diasFin+fechaFin.get(Calendar.MONTH)*30;
		diasFin=diasFin+fechaFin.get(Calendar.DAY_OF_MONTH);
		System.out.println("diasInicio: "+diasInicio);
		System.out.println("diasFin: "+diasFin);
		if(diasInicio==diasFin)
		{
			MensajesWebController.aniadirMensajeError("Fecha Inicio  coincide con  Fecha Final");
			return;
		}
		if(diasInicio>diasFin)
		{
			MensajesWebController.aniadirMensajeError("Fecha Inicio excede a Fecha Final");
			return;
		}
		diferenciaFechas.setTimeInMillis(fechaFin.getTimeInMillis()-fechaInicio.getTimeInMillis());
		int meses=(diferenciaFechas.get(Calendar.YEAR)-1970)*12;
		meses=meses+diferenciaFechas.get(Calendar.MONTH);
		if(diferenciaFechas.get(Calendar.DAY_OF_MONTH)>=30)meses=meses+1;
		this.contratoDataManager.getContratoDTO().setConMesesArrendamiento(meses);
	}
	public void actualizarCampo() {
		slf4jLogger.info("actualizarCampo");
		contratoDataManager.setFechaFin(null);
	}
}
