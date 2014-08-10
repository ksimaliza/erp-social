package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.DefuncionVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.DefuncionDataManager;

@ViewScoped
@ManagedBean (name = "defuncionController")
public class DefuncionController extends BaseController {
	
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(DefuncionController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{defuncionDataManager}")
	private DefuncionDataManager defuncionDataManager;

	public DefuncionDataManager getDefuncionDataManager() {
		return defuncionDataManager;
	}

	public void setDefuncionDataManager(DefuncionDataManager defuncionDataManager) {
		this.defuncionDataManager = defuncionDataManager;
	}
	
	public DefuncionController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarSacerdote();
		buscarProvincia();
		buscarDoctor();
		buscarEstadoCivil();
		
	}
	
public void registrarDefuncion () {
		
		slf4jLogger.info("registrarDefuncion");
		DefuncionVO defuncionVO;
		SacerdoteDTO sacerdoteDTO;
		DoctorDTO doctorDTO;
		CatalogoEucaristiaDTO provincia;
		CatalogoEucaristiaDTO canton;
		CatalogoEucaristiaDTO parroquia;
		
		try {
			defuncionVO=new DefuncionVO();
			sacerdoteDTO=new SacerdoteDTO();
			doctorDTO=new DoctorDTO();
			provincia=new CatalogoEucaristiaDTO();
			canton=new CatalogoEucaristiaDTO();
			parroquia=new CatalogoEucaristiaDTO();
			
			defuncionVO.setDifunto(defuncionDataManager.getDifuntoInsertar());
			defuncionVO.setConyuge(defuncionDataManager.getConyugeInsertar());
			defuncionVO.setDefuncion(defuncionDataManager.getDefuncionInsertar());
			
			sacerdoteDTO.setSacCodigo(defuncionDataManager.getSacerdoteCodigo());
			doctorDTO.setDocCodigo(defuncionDataManager.getDoctorCodigo());
	
			defuncionVO.setDoctor(doctorDTO);
			defuncionVO.setSacerdote(sacerdoteDTO);
			
			provincia.setCatCodigo(defuncionDataManager.getProvinciaCodigo());
			canton.setCatCodigo(defuncionDataManager.getCantonCodigo());
			parroquia.setCatCodigo(defuncionDataManager.getParroquiaCodigo());
			
			defuncionVO.getDefuncion().setDefProvincia(provincia.getCatCodigo());
			defuncionVO.getDefuncion().setDefCanton(canton.getCatCodigo());
			defuncionVO.getDefuncion().setDefParroquia(parroquia.getCatCodigo());
			
			defuncionVO.getDefuncion().setDefFecha(new Timestamp(defuncionDataManager.getFechaSepelioInsertar().getTime()));
			defuncionVO.getDefuncion().setDefFechaDifunto(new Timestamp(defuncionDataManager.getFechaMuerteInsertar().getTime()));
			
			DefuncionDTO defuncionNueva= this.servicioEucaristia.createOrUpdateDefuncion(defuncionVO);
							
			if (defuncionNueva != null) {
				defuncionDataManager.setDifuntoInsertar(new Persona());
				defuncionDataManager.setConyugeInsertar(new Persona());
				defuncionDataManager.setDefuncionInsertar(new DefuncionDTO());
				defuncionDataManager.setSacerdoteCodigo(0);
				defuncionDataManager.setDoctorCodigo(0);
				
				defuncionDataManager.setFechaMuerteInsertar(new Date());
				defuncionDataManager.setFechaSepelioInsertar(new Date());
							
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.partida.bautizo.registrar.exito");
			}
			
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
				this.defuncionDataManager.setSacerdoteListDTO(listaSacerdote);			
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarDoctor () {
		slf4jLogger.info("buscarDoctor");
		
		List<DoctorListDTO> listaDoctor=null;
		
		try {
							
			listaDoctor=this.servicioEucaristia.buscarDoctor(new DoctorListDTO());
			
			if (CollectionUtils.isEmpty(listaDoctor) && listaDoctor.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setDoctorListDTO(listaDoctor);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDoctor {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	
	public void buscarDifunto () {
		slf4jLogger.info("buscarDifunto");
		
		List<Persona> listaDifunto=null;
		
		try {
			
			defuncionDataManager.getDifuntoInsertar().setPerNombres(null);
			defuncionDataManager.getDifuntoInsertar().setPerApellidos(null);
			listaDifunto=this.servicioAdministracion.buscarPersona(defuncionDataManager.getDifuntoInsertar());
										
			if (CollectionUtils.isEmpty(listaDifunto) && listaDifunto.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setDifuntoInsertar(listaDifunto.get(0));
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDifunto {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	public void buscarConyuge () {
		slf4jLogger.info("buscarConyuge");
		
		List<Persona> listaConyuge=null;
		
		try {
			
			defuncionDataManager.getConyugeInsertar().setPerNombres(null);
			defuncionDataManager.getConyugeInsertar().setPerApellidos(null);
			listaConyuge=this.servicioAdministracion.buscarPersona(defuncionDataManager.getConyugeInsertar());
										
			if (CollectionUtils.isEmpty(listaConyuge) && listaConyuge.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setConyugeInsertar(listaConyuge.get(0));
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarConyuge {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarDefuncion () {
		slf4jLogger.info("buscarDefuncion");
		
		List<DefuncionListDTO> listaDefuncion=null;
		
		try {
			
			listaDefuncion=this.servicioEucaristia.buscarDefuncion(defuncionDataManager.getDefuncionListDTO());
					
			if (CollectionUtils.isEmpty(listaDefuncion) && listaDefuncion.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setDefuncionDTOs(listaDefuncion);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDefuncion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	public void cargarDatosDefuncion (DefuncionListDTO defuncion) {
		try {
			
			DefuncionVO defuncionEncontrada=servicioEucaristia.obtenerDefuncionPorId(defuncion);
			this.defuncionDataManager.setDifuntoInsertar(defuncionEncontrada.getDifunto());
			this.defuncionDataManager.setConyugeInsertar(defuncionEncontrada.getConyuge());
			this.defuncionDataManager.setDefuncionInsertar(defuncionEncontrada.getDefuncion());
			this.defuncionDataManager.setDoctorCodigo(defuncionEncontrada.getDoctor().getDocCodigo());
			this.defuncionDataManager.setSacerdoteCodigo(defuncionEncontrada.getSacerdote().getSacCodigo());
			this.defuncionDataManager.setCantonCodigo(defuncionEncontrada.getCanton().getCatCodigo());
			this.defuncionDataManager.setParroquiaCodigo(defuncionEncontrada.getParroquia().getCatCodigo());
				
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosConfirmacion {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosConfirmacion seleccionado");
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
				this.defuncionDataManager.setProvinciasEucaristiaDTOs(listaCatalogo);
				
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
			cat.setCatCodigo(defuncionDataManager.getProvinciaCodigo());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setCantonEucaristiaDTOs(listaCatalogo);
				
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
			cat.setCatCodigo(defuncionDataManager.getCantonCodigo());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setParroquiaEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarParroquia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarEstadoCivil () {
		slf4jLogger.info("buscarEstadoCivil");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(14);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setEstadoCivils(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarEstadoCivil {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}

	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
	


}
