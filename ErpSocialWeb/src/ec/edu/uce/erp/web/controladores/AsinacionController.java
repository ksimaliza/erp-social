package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.AsinacionDataManager;

@ViewScoped
@ManagedBean (name = "asinacionController")
public class AsinacionController extends BaseController{
private static final long serialVersionUID = 1L;

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(AsinacionController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{asinacionDataManager}")
	private AsinacionDataManager asinacionDataManager;

	public AsinacionDataManager getAsinacionDataManager() {
		return asinacionDataManager;
	}

	public void setAsinacionDataManager(AsinacionDataManager asinacionDataManager) {
		this.asinacionDataManager = asinacionDataManager;
	}
	
	public AsinacionController () {}
	
	
	@PostConstruct
	private void init(){
		
		//buscar();
		buscarNivelParalelo();
		buscarDocente();
		buscarMateria();
		buscarPeriodo();
	}
	
	public void registrarAsinacion () {
		
		slf4jLogger.info("registrarAsinacion");
		AsinacionDTO asinacion;
		NivelParaleloDTO nivelParalelo;
		ProfesorDTO profesor;
		MateriaDTO materia;
		PeriodoDTO periodo;
		
		try {
			
			asinacion = new AsinacionDTO();
			nivelParalelo = new NivelParaleloDTO();
			profesor = new ProfesorDTO();
			materia = new MateriaDTO();
			periodo = new PeriodoDTO();
			
			nivelParalelo.setNpaCodigo(asinacionDataManager.getNivelParaleloCodigo());
			profesor.setProCodigo(asinacionDataManager.getNivelParaleloCodigo());
			materia.setMtrCodigo(asinacionDataManager.getMateriaCodigo());
			periodo.setPerCodigo(asinacionDataManager.getPeriodoCodigo());
							
			asinacion.setMatNivelParalelo(nivelParalelo);
			asinacion.setMatProfesor(profesor);
			asinacion.setMatMateria(materia);
			asinacion.setMatPeriodo(periodo);
						
			AsinacionDTO asinacionNueva=this.servicioMatricula.createOrUpdateAsinacion(asinacion);
						
			if (asinacionNueva != null) {
				
				asinacionDataManager.setAsinacionInsertar(new AsinacionDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.asinacion.registrar.exito");
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscar() {
		slf4jLogger.info("buscarAsinacion");
		List<AsinacionListDTO> listResultado=new ArrayList<AsinacionListDTO>();
		try {
			listResultado = this.servicioMatricula.readAsinacion(asinacionDataManager.getAsinacionBuscar());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
			this.asinacionDataManager.setAsinacionList(listResultado);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el buscarAsinacion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void cargarDatosAsinacion (AsinacionDTO asinacion) {
		try {
			
			AsinacionDTO asinacionEncontrada=servicioMatricula.obtenerAsinacionPorId(asinacion.getMatNivelParalelo().getNpaCodigo(), asinacion.getMatProfesor().getProCodigo(), asinacion.getMatMateria().getMtrCodigo(), asinacion.getMatPeriodo().getPerCodigo());
			this.asinacionDataManager.setNivelParaleloInsertar(asinacionEncontrada.getMatNivelParalelo());
			this.asinacionDataManager.setProfesorInsertar(asinacionEncontrada.getMatProfesor());
			this.asinacionDataManager.setMateriaInsertar(asinacionEncontrada.getMatMateria());
			this.asinacionDataManager.setPeriodoInsertar(asinacionEncontrada.getMatPeriodo());			
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosAsinacion seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosAsinacion seleccionado");
		}
	}
	

	
	public void buscarNivelParalelo() {
		slf4jLogger.info("buscarNivelParalelo");
		List<NivelParaleloListDTO> listResultado=new ArrayList<NivelParaleloListDTO>();
		try {
			
			listResultado = this.servicioMatricula.readNivelParalelo(new NivelParaleloListDTO());
						
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.asinacionDataManager.setNivelParaleloList(listResultado);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el NivelParalelo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void buscarDocente () {
		slf4jLogger.info("buscarDocente");
		
		List<DocenteListDTO> listadocentes=null;
		
		try {
							
			listadocentes = this.servicioMatricula.buscarProfesor(new DocenteListDTO());
			
			if (CollectionUtils.isEmpty(listadocentes) && listadocentes.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.asinacionDataManager.setProfesorList(listadocentes);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDocente {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMateria () {
		slf4jLogger.info("buscarMateria");
		
		List<MateriaDTO> listamaterias=null;
		
		try {
							
			listamaterias = this.servicioMatricula.buscarMateria(new MateriaDTO());
			
			if (CollectionUtils.isEmpty(listamaterias) && listamaterias.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.asinacionDataManager.setMateriaList(listamaterias);
				
						
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMateria {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarPeriodo () {
		slf4jLogger.info("buscarPeriodo");
		
		List<PeriodoDTO> listaperiodos=null;
		
		try {
							
			listaperiodos = this.servicioMatricula.buscarPeriodo(new PeriodoDTO());
			
			if (CollectionUtils.isEmpty(listaperiodos) && listaperiodos.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.asinacionDataManager.setPeriodoList(listaperiodos);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPeriodo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	

	
}
