package ec.edu.uce.erp.web.controladores;

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
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.BautizoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.PartidaBautizoDataManager;

@ViewScoped
@ManagedBean (name = "partidaBautizoController")

public class PartidaBautizoController extends BaseController{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaBautizoController.class);
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{partidaBautizoDataManager}")
	private PartidaBautizoDataManager partidaBautizoDataManager;

	public PartidaBautizoDataManager getPartidaBautizoDataManager() {
		return partidaBautizoDataManager;
	}

	public void setPartidaBautizoDataManager(
			PartidaBautizoDataManager partidaBautizoDataManager) {
		this.partidaBautizoDataManager = partidaBautizoDataManager;
	}
	
	public PartidaBautizoController() {
	
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarSacerdote();
		buscarProvincia();
		buscarEstado();
	}
	
	public void registrarBautizo () {
		
		slf4jLogger.info("registrarBautizo");
		BautizoVO bautizoVO;
		SacerdoteDTO sacerdoteDTO;
		CatalogoEucaristiaDTO provincia;
		CatalogoEucaristiaDTO canton;
		CatalogoEucaristiaDTO parroquia;
		CatalogoEucaristiaDTO estado;

		try {
			
			bautizoVO=new BautizoVO();
			sacerdoteDTO=new SacerdoteDTO();
			provincia=new CatalogoEucaristiaDTO();
			canton=new CatalogoEucaristiaDTO();
			parroquia=new CatalogoEucaristiaDTO();
			estado=new CatalogoEucaristiaDTO();
			
			partidaBautizoDataManager.getBautizoDTO().setBauCertificadoPor(getPersonaCode());
		
			bautizoVO.setBautizado(partidaBautizoDataManager.getBautizadoInsertar());
			bautizoVO.setMadrina(partidaBautizoDataManager.getMadrinaInsertar());
			bautizoVO.setPadrino(partidaBautizoDataManager.getPadrinoInsertar());
			bautizoVO.setMadre(partidaBautizoDataManager.getMadreInsertar());
			bautizoVO.setPadre(partidaBautizoDataManager.getPadreInsertar());
			
			bautizoVO.setBautizo(partidaBautizoDataManager.getBautizoDTO());

			sacerdoteDTO.setSacCodigo(partidaBautizoDataManager.getSacerdoteCodigo());
			bautizoVO.setSacerdote(sacerdoteDTO);
			
			provincia.setCatCodigo(partidaBautizoDataManager.getProvinciaCodigo());
			canton.setCatCodigo(partidaBautizoDataManager.getCantonCodigo());
			parroquia.setCatCodigo(partidaBautizoDataManager.getParroquiaCodigo());
			estado.setCatCodigo(partidaBautizoDataManager.getEstadoCodigo());
			bautizoVO.getBautizo().setBauProvincia(provincia.getCatCodigo());
			bautizoVO.getBautizo().setBauCanton(canton.getCatCodigo());
			bautizoVO.getBautizo().setBauParroquia(parroquia.getCatCodigo());
			bautizoVO.getBautizo().setBauEstado(estado.getCatCodigo());
			
			if(partidaBautizoDataManager.getFechaApCInsertar().getTime()>partidaBautizoDataManager.getFechaBautizoInsertar().getTime())
			{
				MensajesWebController.aniadirMensajeError("Ingrese fecha de Aprobación del Curso Correcta");
				return;
			}
			bautizoVO.getBautizo().setBauFechaAprobacionCruso(new Timestamp(partidaBautizoDataManager.getFechaApCInsertar().getTime()));
			bautizoVO.getBautizo().setBauFechaBautizo(new Timestamp(partidaBautizoDataManager.getFechaBautizoInsertar().getTime()));
			
			BautizoDTO bautizoNuevo=this.servicioEucaristia.createOrUpdateBautizo(bautizoVO);
			
			partidaBautizoDataManager.setExportDesactivado(false);
			
			BautizoListDTO bautizo=new BautizoListDTO();
			bautizo.setBauCodigo(bautizoNuevo.getBauCodigo());
			bautizo.setBauBautizado(bautizoNuevo.getBauBautizado());
			bautizo.setBauMadre(bautizoNuevo.getBauMadre());
			bautizo.setBauPadre(bautizoNuevo.getBauPadre());
			bautizo.setBauMadrina(bautizoNuevo.getBauMadrina());
			bautizo.setBauPadrino(bautizoNuevo.getBauMadrina());
			bautizo.setBauSacerdote(bautizoNuevo.getEucSacerdote().getSacCodigo());
			
			cargarDatosBautizo(bautizo);		
			
			if (bautizoNuevo != null) {
				/*partidaBautizoDataManager.setBautizadoInsertar(new Persona());
				partidaBautizoDataManager.setMadrinaInsertar(new Persona());
				partidaBautizoDataManager.setPadrinoInsertar(new Persona());
				partidaBautizoDataManager.setMadreInsertar(new Persona());
				partidaBautizoDataManager.setPadreInsertar(new Persona());
				partidaBautizoDataManager.setBautizoDTO(new BautizoDTO());
				partidaBautizoDataManager.setSacerdoteCodigo(0);
				partidaBautizoDataManager.setFechaApCInsertar(new Date());
				partidaBautizoDataManager.setFechaBautizoInsertar(new Date());
				partidaBautizoDataManager.setEstadoCodigo(0);*/
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.partida.bautizo.registrar.exito");
			
			}
			buscarPartidaBautizo();
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarSacerdote () {
		slf4jLogger.info("buscarSacerdote");
		List<SacerdoteListDTO> listaSacerdote=null;
		try {
			listaSacerdote=this.servicioEucaristia.buscarSacerdote(new SacerdoteListDTO());
			if (CollectionUtils.isEmpty(listaSacerdote) && listaSacerdote.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaBautizoDataManager.setSacerdoteListDTO(listaSacerdote);			
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarBautizado () {
		slf4jLogger.info("buscarBautizado");
		
		List<Persona> listaBautizado=null;
		
		try {
			if(partidaBautizoDataManager.getBautizadoInsertar().getPerCi()!=null && partidaBautizoDataManager.getBautizadoInsertar().getPerCi()!="" )
			{
				partidaBautizoDataManager.getBautizadoInsertar().setPerNombres(null);
				partidaBautizoDataManager.getBautizadoInsertar().setPerApellidos(null);
				listaBautizado=this.servicioAdministracion.buscarPersona(partidaBautizoDataManager.getBautizadoInsertar());
								
				if (CollectionUtils.isEmpty(listaBautizado) && listaBautizado.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaBautizoDataManager.setBautizadoInsertar(listaBautizado.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBautizado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMadrina () {
		slf4jLogger.info("buscarMadrina");
		List<Persona> listaMadrina=null;
		try {
			if(partidaBautizoDataManager.getMadrinaInsertar().getPerCi()!=null && partidaBautizoDataManager.getMadrinaInsertar().getPerCi()!="" )
			{
				partidaBautizoDataManager.getMadrinaInsertar().setPerNombres(null);
				partidaBautizoDataManager.getMadrinaInsertar().setPerApellidos(null);
				listaMadrina=this.servicioAdministracion.buscarPersona(partidaBautizoDataManager.getMadrinaInsertar());
								
				if (CollectionUtils.isEmpty(listaMadrina) && listaMadrina.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaBautizoDataManager.setMadrinaInsertar(listaMadrina.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadrina {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarPadrino () {
		slf4jLogger.info("buscarPadrino");
		List<Persona> listaPadrino=null;
		try {
			if(partidaBautizoDataManager.getPadrinoInsertar().getPerCi()!=null && partidaBautizoDataManager.getPadrinoInsertar().getPerCi()!="" )
			{
				partidaBautizoDataManager.getPadrinoInsertar().setPerNombres(null);
				partidaBautizoDataManager.getPadrinoInsertar().setPerApellidos(null);
				listaPadrino=this.servicioAdministracion.buscarPersona(partidaBautizoDataManager.getPadrinoInsertar());
								
				if (CollectionUtils.isEmpty(listaPadrino) && listaPadrino.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaBautizoDataManager.setPadrinoInsertar(listaPadrino.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPadrino {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMadre () {
		slf4jLogger.info("buscarMadre");
		
		List<Persona> listaPersona=null;
		
		try {
			if(partidaBautizoDataManager.getMadreInsertar().getPerCi()!=null && partidaBautizoDataManager.getMadreInsertar().getPerCi()!="" )
			{
				partidaBautizoDataManager.getMadreInsertar().setPerNombres(null);
				partidaBautizoDataManager.getMadreInsertar().setPerApellidos(null);
				listaPersona=this.servicioAdministracion.buscarPersona(partidaBautizoDataManager.getMadreInsertar());
								
				if (CollectionUtils.isEmpty(listaPersona) && listaPersona.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaBautizoDataManager.setMadreInsertar(listaPersona.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadre {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarPadre () {
		slf4jLogger.info("buscarPadre");
		
		List<Persona> listaPersona=null;
		
		try {
			if(partidaBautizoDataManager.getPadreInsertar().getPerCi()!=null && partidaBautizoDataManager.getPadreInsertar().getPerCi()!="" )
			{
				partidaBautizoDataManager.getPadreInsertar().setPerNombres(null);
				partidaBautizoDataManager.getPadreInsertar().setPerApellidos(null);
				listaPersona=this.servicioAdministracion.buscarPersona(partidaBautizoDataManager.getPadreInsertar());
								
				if (CollectionUtils.isEmpty(listaPersona) && listaPersona.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaBautizoDataManager.setPadreInsertar(listaPersona.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPadre {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	
	public void buscarPartidaBautizo () {
		slf4jLogger.info("buscarPartidaBautizo");
		
		List<BautizoListDTO> listaBautizo=null;
		
		try {
				listaBautizo=this.servicioEucaristia.buscarPartidaBautizo(partidaBautizoDataManager.getBautizoListDTO());
				if (CollectionUtils.isEmpty(listaBautizo) && listaBautizo.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaBautizoDataManager.setBautizoListDTOs(listaBautizo);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPartidaBautizo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	

	public void cargarDatosBautizo (BautizoListDTO bautizo) {
		try {
			BautizoVO bautizoEncontrado=servicioEucaristia.obtenerBautizoPorId(bautizo);
			this.partidaBautizoDataManager.setBautizadoInsertar(bautizoEncontrado.getBautizado());
			this.partidaBautizoDataManager.setBautizoDTO(bautizoEncontrado.getBautizo());
			this.partidaBautizoDataManager.setMadrinaInsertar(bautizoEncontrado.getMadrina());
			this.partidaBautizoDataManager.setMadreInsertar(bautizoEncontrado.getMadre());
			this.partidaBautizoDataManager.setPadreInsertar(bautizoEncontrado.getPadre());
			this.partidaBautizoDataManager.setPadrinoInsertar(bautizoEncontrado.getPadrino());
			this.partidaBautizoDataManager.setSacerdoteCodigo(bautizoEncontrado.getBautizo().getEucSacerdote().getSacCodigo());
			this.partidaBautizoDataManager.setEstadoCodigo(bautizoEncontrado.getBautizo().getBauEstado());
			this.partidaBautizoDataManager.setProvinciaCodigo(bautizoEncontrado.getBautizo().getBauProvincia());
			buscarCanton();
			this.partidaBautizoDataManager.setCantonCodigo(bautizoEncontrado.getBautizo().getBauCanton());
			buscarParroquia();
			this.partidaBautizoDataManager.setParroquiaCodigo(bautizoEncontrado.getBautizo().getBauParroquia());
			this.partidaBautizoDataManager.setFechaApCInsertar(bautizoEncontrado.getBautizo().getBauFechaAprobacionCruso());
			this.partidaBautizoDataManager.setFechaBautizoInsertar(bautizoEncontrado.getBautizo().getBauFechaBautizo());
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosBautizo {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosBautizo seleccionado");
		}
	}
	
	
	public void buscarProvincia () {
		slf4jLogger.info("buscarCatalogo");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(1);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaBautizoDataManager.setProvinciasEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCatalogo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarCanton () {
		slf4jLogger.info("buscarCanton");
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(partidaBautizoDataManager.getProvinciaCodigo());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaBautizoDataManager.setCantonEucaristiaDTOs(listaCatalogo);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCanton {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarParroquia () {
		slf4jLogger.info("buscarParroquia");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(partidaBautizoDataManager.getCantonCodigo());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaBautizoDataManager.setParroquiaEucaristiaDTOs(listaCatalogo);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCiudad {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarEstado () {
		slf4jLogger.info("buscarEstado");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(18);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaBautizoDataManager.setEstadoEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarEstado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void exportar()
	{
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
				
		DateFormat pequeña = DateFormat.getDateInstance(DateFormat.SHORT);
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		
		mapParametros.put("canton", partidaBautizoDataManager.getCantonEucaristiaDTOs().get(0).getCatDescripcion().toUpperCase());
		mapParametros.put("parroquiaCabecera", "\"" + partidaBautizoDataManager.getParroquiaEucaristiaDTOs().get(0).getCatDescripcion().toUpperCase() +"\"");
		mapParametros.put("parroquia", partidaBautizoDataManager.getParroquiaEucaristiaDTOs().get(0).getCatDescripcion().toUpperCase());
		mapParametros.put("tomo", partidaBautizoDataManager.getBautizoDTO().getBauToma());
		mapParametros.put("pagina", partidaBautizoDataManager.getBautizoDTO().getBauPagina());
		mapParametros.put("acta", partidaBautizoDataManager.getBautizoDTO().getBauActa());
		mapParametros.put("fechaBautizo", pequeña.format(partidaBautizoDataManager.getFechaBautizoInsertar()));
		mapParametros.put("bautizado", partidaBautizoDataManager.getBautizadoInsertar().getPerApellidos().toUpperCase() + " "+   partidaBautizoDataManager.getBautizadoInsertar().getPerNombres().toUpperCase());
		mapParametros.put("sacerdote", partidaBautizoDataManager.getSacerdoteListDTO().get(0).getPerApellidos().toUpperCase() + " "+  partidaBautizoDataManager.getSacerdoteListDTO().get(0).getPerNombres().toUpperCase());
		mapParametros.put("padrino", partidaBautizoDataManager.getPadrinoInsertar().getPerApellidos().toUpperCase() + " "+ partidaBautizoDataManager.getPadrinoInsertar().getPerNombres().toUpperCase());
		mapParametros.put("parroquiafechaActual", partidaBautizoDataManager.getParroquiaEucaristiaDTOs().get(0).getCatDescripcion()+ ", "+full.format(fechaActual));
		mapParametros.put("madrina", partidaBautizoDataManager.getMadrinaInsertar().getPerApellidos().toUpperCase() +  " "+partidaBautizoDataManager.getMadrinaInsertar().getPerNombres().toUpperCase());
		mapParametros.put("notaMarginal", partidaBautizoDataManager.getBautizoDTO().getBauNotaMarginal());
		mapParametros.put("provincia", partidaBautizoDataManager.getProvinciasEucaristiaDTOs().get(0).getCatDescripcion().toUpperCase());
		mapParametros.put("madre", partidaBautizoDataManager.getMadreInsertar().getPerApellidos().toUpperCase() + " "+ partidaBautizoDataManager.getMadreInsertar().getPerNombres().toUpperCase());
		mapParametros.put("padre", partidaBautizoDataManager.getPadreInsertar().getPerApellidos().toUpperCase() +  " "+partidaBautizoDataManager.getPadreInsertar().getPerNombres().toUpperCase());
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "certificadoBautismo", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.partidaBautizoDataManager.getFormatoPdf(), "certificadoBautismo");
	
		
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
	public void exportarPdf(BautizoListDTO bautizo)
	{
		cargarDatosBautizo(bautizo);
		exportar();
	}
	
	public void cancel()
	{
		partidaBautizoDataManager.setBautizadoInsertar(new Persona());
		partidaBautizoDataManager.setMadrinaInsertar(new Persona());
		partidaBautizoDataManager.setPadrinoInsertar(new Persona());
		partidaBautizoDataManager.setMadreInsertar(new Persona());
		partidaBautizoDataManager.setPadreInsertar(new Persona());
		partidaBautizoDataManager.setBautizoDTO(new BautizoDTO());
		partidaBautizoDataManager.setSacerdoteCodigo(0);
		partidaBautizoDataManager.setFechaApCInsertar(new Date());
		partidaBautizoDataManager.setFechaBautizoInsertar(new Date());
		partidaBautizoDataManager.setEstadoCodigo(0);
		RequestContext.getCurrentInstance().execute("dlgNuevaPartidaBautizo.hide()");
	}


}
