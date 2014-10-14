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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ConfirmacionVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.PartidaConfirmacionDataManager;

@ViewScoped
@ManagedBean (name = "partidaConfirmacionController")

public class PartidaConfirmacionController  extends BaseController{
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaConfirmacionController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{partidaConfirmacionDataManager}")
	private PartidaConfirmacionDataManager partidaConfirmacionDataManager;

	public PartidaConfirmacionDataManager getPartidaConfirmacionDataManager() {
		return partidaConfirmacionDataManager;
	}

	public void setPartidaConfirmacionDataManager(
			PartidaConfirmacionDataManager partidaConfirmacionDataManager) {
		this.partidaConfirmacionDataManager = partidaConfirmacionDataManager;
	}

	public PartidaConfirmacionController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarSacerdote();
		buscarTipo ();
		buscarProvincia();
		buscarEstado();
		
	}
public void registrarConfirmacion () {
		
		slf4jLogger.info("registrarConfirmacion");
		ConfirmacionVO confirmacionVO;
		SacerdoteDTO sacerdoteDTO;
		CatalogoEucaristiaDTO provincia;
		CatalogoEucaristiaDTO canton;
		CatalogoEucaristiaDTO parroquia;
		CatalogoEucaristiaDTO estado;
		CatalogoEucaristiaDTO tipo;
		
		try {
			
			confirmacionVO=new ConfirmacionVO();
			sacerdoteDTO=new SacerdoteDTO();
			provincia=new CatalogoEucaristiaDTO();
			canton=new CatalogoEucaristiaDTO();
			parroquia=new CatalogoEucaristiaDTO();
			estado=new CatalogoEucaristiaDTO();
			tipo=new CatalogoEucaristiaDTO();
			
			partidaConfirmacionDataManager.getConfirmacionDTO().setConCertificadoPor(getPersonaCode());
			
			confirmacionVO.setConfirmado(partidaConfirmacionDataManager.getConfirmadoInsertar());
			confirmacionVO.setMad_pad(partidaConfirmacionDataManager.getMad_padInsertar());
			confirmacionVO.setMadrePersona(partidaConfirmacionDataManager.getMadreInsertar());
			confirmacionVO.setPadrePersona(partidaConfirmacionDataManager.getPadreInsertar());
			
			confirmacionVO.setConfirmacion(partidaConfirmacionDataManager.getConfirmacionDTO());
									
			
			sacerdoteDTO.setSacCodigo(partidaConfirmacionDataManager.getSacerdoteCodigo());
			confirmacionVO.setSacerdote(sacerdoteDTO);
			
			provincia.setCatCodigo(partidaConfirmacionDataManager.getProvincia());
			canton.setCatCodigo(partidaConfirmacionDataManager.getCanton());
			parroquia.setCatCodigo(partidaConfirmacionDataManager.getParroquia());
			estado.setCatCodigo(partidaConfirmacionDataManager.getEstadoCodigo());
			tipo.setCatCodigo(partidaConfirmacionDataManager.getTipoCodigo());
			
			confirmacionVO.getConfirmacion().setConProvincia(provincia.getCatCodigo());
			confirmacionVO.getConfirmacion().setConCanton(canton.getCatCodigo());
			confirmacionVO.getConfirmacion().setConParroquia(parroquia.getCatCodigo());
			confirmacionVO.getConfirmacion().setConEstado(estado.getCatCodigo());
			confirmacionVO.getConfirmacion().setConTipo(tipo.getCatCodigo());
			
			if(partidaConfirmacionDataManager.getFechaApCInsertar().getTime()>partidaConfirmacionDataManager.getFechaComunionInsertar().getTime())
			{
				MensajesWebController.aniadirMensajeError("Ingrese fecha de Aprobación de curso correcta");
				return;
			}
			
			confirmacionVO.getConfirmacion().setConFechaAprobacionCurso(new Timestamp(partidaConfirmacionDataManager.getFechaApCInsertar().getTime()));
			confirmacionVO.getConfirmacion().setConFecha(new Timestamp(partidaConfirmacionDataManager.getFechaComunionInsertar().getTime()));
			ConfirmacionDTO confirmacionNuevo= this.servicioEucaristia.createOrUpdateConfirmacion(confirmacionVO);

			partidaConfirmacionDataManager.setExportDesactivado(false);
			
			if (confirmacionNuevo != null) {
				
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.partida.confirmacion.registrar.exito");
			}
			buscarPartidaConfirmacion();
			
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
				this.partidaConfirmacionDataManager.setSacerdoteListDTO(listaSacerdote);
					
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarConfirmado () {
		slf4jLogger.info("buscarConfirmado");
		
		List<Persona> listaConfirmado=null;
		BautizoListDTO bautizo=new BautizoListDTO();
		List<BautizoListDTO> list=null;
		
		try {
			if(partidaConfirmacionDataManager.getConfirmadoInsertar().getPerCi()!=null && partidaConfirmacionDataManager.getConfirmadoInsertar().getPerCi()!="" )
			{
				partidaConfirmacionDataManager.getConfirmadoInsertar().setPerNombres(null);
				partidaConfirmacionDataManager.getConfirmadoInsertar().setPerApellidos(null);
				listaConfirmado=this.servicioAdministracion.buscarPersona(partidaConfirmacionDataManager.getConfirmadoInsertar());					
				bautizo.setPerCi(partidaConfirmacionDataManager.getConfirmadoInsertar().getPerCi());
				list=this.servicioEucaristia.buscarPartidaBautizo(bautizo);
				
				if (CollectionUtils.isEmpty(listaConfirmado) && listaConfirmado.size()==0){
					//MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaConfirmacionDataManager.setConfirmadoInsertar(listaConfirmado.get(0));
					if(!CollectionUtils.isEmpty(list) && list.size()!=0)
						this.partidaConfirmacionDataManager.setBautizoListDTO(list.get(0));			
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBautizado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	
	public void buscarMadrina () {
		slf4jLogger.info("buscarMadrina");
		
		List<Persona> listaMad_Pad=null;
		
		try {
			if(partidaConfirmacionDataManager.getMad_padInsertar().getPerCi()!=null && partidaConfirmacionDataManager.getMad_padInsertar().getPerCi()!="" )
			{
				partidaConfirmacionDataManager.getMad_padInsertar().setPerApellidos(null);
				partidaConfirmacionDataManager.getMad_padInsertar().setPerNombres(null);
				listaMad_Pad=this.servicioAdministracion.buscarPersona(partidaConfirmacionDataManager.getMad_padInsertar());
											
				if (CollectionUtils.isEmpty(listaMad_Pad) && listaMad_Pad.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaConfirmacionDataManager.setMad_padInsertar(listaMad_Pad.get(0));
								
				}
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadrina {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMadre () {
		slf4jLogger.info("buscarMadre");
		
		List<Persona> listaPersona=null;
		
		try {
			if(partidaConfirmacionDataManager.getMadreInsertar().getPerCi()!=null && partidaConfirmacionDataManager.getMadreInsertar().getPerCi()!="" )
			{
				partidaConfirmacionDataManager.getMadreInsertar().setPerNombres(null);
				partidaConfirmacionDataManager.getMadreInsertar().setPerApellidos(null);
				listaPersona=this.servicioAdministracion.buscarPersona(partidaConfirmacionDataManager.getMadreInsertar());
								
				if (CollectionUtils.isEmpty(listaPersona) && listaPersona.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaConfirmacionDataManager.setMadreInsertar(listaPersona.get(0));
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
			if(partidaConfirmacionDataManager.getPadreInsertar().getPerCi()!=null && partidaConfirmacionDataManager.getPadreInsertar().getPerCi()!="" )
			{
				partidaConfirmacionDataManager.getPadreInsertar().setPerNombres(null);
				partidaConfirmacionDataManager.getPadreInsertar().setPerApellidos(null);
				listaPersona=this.servicioAdministracion.buscarPersona(partidaConfirmacionDataManager.getPadreInsertar());
								
				if (CollectionUtils.isEmpty(listaPersona) && listaPersona.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaConfirmacionDataManager.setPadreInsertar(listaPersona.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadre {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void buscarPartidaConfirmacion () {
		slf4jLogger.info("buscarPartidaConfirmacion");
		
		List<ConfirmacionListDTO> listaConfirmacion=null;
		
		try {
			
			listaConfirmacion=this.servicioEucaristia.buscarPartidaConfirmacion(partidaConfirmacionDataManager.getConfirmacionListDTO());
								
			
			if (CollectionUtils.isEmpty(listaConfirmacion) && listaConfirmacion.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setConfirmacionListDTOs(listaConfirmacion);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPartidaBautizo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	
	public void buscarTipo () {
		slf4jLogger.info("buscarTipo");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(11);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setTipoEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarTipo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
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
				this.partidaConfirmacionDataManager.setProvinciaEucaristiaDTOs(listaCatalogo);
				
				
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
			cat.setCatCodigo(partidaConfirmacionDataManager.getProvincia());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setCantonEucaristiaDTOs(listaCatalogo);
				
				
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
			cat.setCatCodigo(partidaConfirmacionDataManager.getCanton());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setParroquiaEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarParroquia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarEstado () {
		slf4jLogger.info("buscarEstado");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(21);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaConfirmacionDataManager.setEstadoEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarEstado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosConfirmacion (ConfirmacionListDTO confirmacion) {
		try {
			
			ConfirmacionVO confirmacionEncontrada=servicioEucaristia.obtenerConfirmacionPorId(confirmacion);
			this.partidaConfirmacionDataManager.setConfirmadoInsertar(confirmacionEncontrada.getConfirmado());
			this.partidaConfirmacionDataManager.setConfirmacionDTO(confirmacionEncontrada.getConfirmacion());
			this.partidaConfirmacionDataManager.setMad_padInsertar(confirmacionEncontrada.getMad_pad());
			this.partidaConfirmacionDataManager.setMadreInsertar(confirmacionEncontrada.getMadrePersona());
			this.partidaConfirmacionDataManager.setPadreInsertar(confirmacionEncontrada.getPadrePersona());
			this.partidaConfirmacionDataManager.setSacerdoteCodigo(confirmacionEncontrada.getConfirmacion().getEucSacerdote().getSacCodigo());
			this.partidaConfirmacionDataManager.setEstadoCodigo(confirmacionEncontrada.getConfirmacion().getConEstado());
			this.partidaConfirmacionDataManager.setProvincia(confirmacionEncontrada.getConfirmacion().getConProvincia());
			buscarCanton();
			this.partidaConfirmacionDataManager.setCanton(confirmacionEncontrada.getConfirmacion().getConCanton());
			buscarParroquia();
			this.partidaConfirmacionDataManager.setParroquia(confirmacionEncontrada.getConfirmacion().getConParroquia());
			this.partidaConfirmacionDataManager.setFechaApCInsertar(confirmacionEncontrada.getConfirmacion().getConFechaAprobacionCurso());
			this.partidaConfirmacionDataManager.setFechaComunionInsertar(confirmacionEncontrada.getConfirmacion().getConFecha());
			buscarConfirmado();
			this.partidaConfirmacionDataManager.setTipoCodigo(confirmacionEncontrada.getConfirmacion().getConTipo());
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosConfirmacion {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosConfirmacion seleccionado");
		}
	}
	
	public void exportar()
	{
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
				
		DateFormat pequeña = DateFormat.getDateInstance(DateFormat.SHORT);
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		
		mapParametros.put("canton", partidaConfirmacionDataManager.getCantonEucaristiaDTOs().get(0).getCatDescripcion().toUpperCase());
		mapParametros.put("parroquiaCabecera", "\"" + partidaConfirmacionDataManager.getParroquiaEucaristiaDTOs().get(0).getCatDescripcion().toUpperCase() +"\"");
		mapParametros.put("parroquia", partidaConfirmacionDataManager.getParroquiaEucaristiaDTOs().get(0).getCatDescripcion().toUpperCase());
		mapParametros.put("tomo", partidaConfirmacionDataManager.getConfirmacionDTO().getConToma());
		mapParametros.put("pagina", partidaConfirmacionDataManager.getConfirmacionDTO().getConPagina());
		mapParametros.put("acta", partidaConfirmacionDataManager.getConfirmacionDTO().getConActa());
		mapParametros.put("fechaConfirmacion", pequeña.format(partidaConfirmacionDataManager.getFechaComunionInsertar()));
		mapParametros.put("confirmado", partidaConfirmacionDataManager.getConfirmadoInsertar().getPerApellidos().toUpperCase() + " "+   partidaConfirmacionDataManager.getConfirmadoInsertar().getPerNombres().toUpperCase());
		mapParametros.put("sacerdote", partidaConfirmacionDataManager.getSacerdoteListDTO().get(0).getPerApellidos().toUpperCase() + " "+  partidaConfirmacionDataManager.getSacerdoteListDTO().get(0).getPerNombres().toUpperCase());
		mapParametros.put("padrino", partidaConfirmacionDataManager.getMad_padInsertar().getPerApellidos().toUpperCase() + " "+ partidaConfirmacionDataManager.getMad_padInsertar().getPerNombres().toUpperCase());
		mapParametros.put("parroquiafechaActual", partidaConfirmacionDataManager.getParroquiaEucaristiaDTOs().get(0).getCatDescripcion()+ ", "+full.format(fechaActual));
		mapParametros.put("notaMarginal", partidaConfirmacionDataManager.getConfirmacionDTO().getConNotaMarginal());
		mapParametros.put("provincia", partidaConfirmacionDataManager.getProvinciaEucaristiaDTOs().get(0).getCatDescripcion().toUpperCase());
		mapParametros.put("madre", partidaConfirmacionDataManager.getMadreInsertar().getPerApellidos().toUpperCase() + " "+ partidaConfirmacionDataManager.getMadreInsertar().getPerNombres().toUpperCase());
		mapParametros.put("padre", partidaConfirmacionDataManager.getPadreInsertar().getPerApellidos().toUpperCase() +  " "+ partidaConfirmacionDataManager.getPadreInsertar().getPerApellidos().toUpperCase());
		mapParametros.put("daFe", getUsuario().getNpNombresCompletos());
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "certificadoConfirmacion", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.partidaConfirmacionDataManager.getFormatoPdf(), "certificadoConfirmacion");
	}
	
	public void exportarPdf(ConfirmacionListDTO confirmacion)
	{
		cargarDatosConfirmacion(confirmacion);
		exportar();
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void cancel()
	{
		partidaConfirmacionDataManager.setConfirmadoInsertar(new Persona());		
		partidaConfirmacionDataManager.setMad_padInsertar(new Persona());
		partidaConfirmacionDataManager.setPadreInsertar(new Persona());
		partidaConfirmacionDataManager.setMadreInsertar(new Persona());
		partidaConfirmacionDataManager.setBautizoListDTO(new BautizoListDTO());
		partidaConfirmacionDataManager.setConfirmacionDTO(new ConfirmacionDTO());
		partidaConfirmacionDataManager.setSacerdoteCodigo(0);
		partidaConfirmacionDataManager.setTipoCodigo(0);
		partidaConfirmacionDataManager.setProvincia(0);
		partidaConfirmacionDataManager.setCanton(0);
		partidaConfirmacionDataManager.setParroquia(0);
		partidaConfirmacionDataManager.setFechaApCInsertar(new Date(null));
		partidaConfirmacionDataManager.setFechaComunionInsertar(new Date(null));
		partidaConfirmacionDataManager.setEstadoCodigo(0);
		RequestContext.getCurrentInstance().execute("dlgNuevaPartidaConfirmacion.hide()");
	}
	
	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
