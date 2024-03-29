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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
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
		buscarNivel();
		buscarDocente();
		buscarMateria();
		buscarPeriodo();
		
	}
	
	public void registrarAsinacion () {
		
		slf4jLogger.info("registrarAsinacion");
		AsinacionDTO asinacion;
		NivelParaleloDTO nivelParalelo;
		NivelDTO nivelDTO;
		ParaleloDTO paraleloDTO;
		ProfesorDTO profesor;
		MateriaDTO materia;
		PeriodoDTO periodo;
				
		try {
			
			asinacion = new AsinacionDTO();
			nivelParalelo = new NivelParaleloDTO();
			profesor = new ProfesorDTO();
			materia = new MateriaDTO();
			periodo = new PeriodoDTO();
			nivelDTO = new NivelDTO();
			paraleloDTO= new ParaleloDTO();
		
			
			asinacion.setAsiEmpresa(getEmpresaCode());
			nivelDTO.setNivCodigo(asinacionDataManager.getNivelCodigo());
			paraleloDTO.setParCodigo(asinacionDataManager.getParaleloCodigo());
						
			nivelParalelo.setMatNivel(nivelDTO);
			nivelParalelo.setMatParalelo(paraleloDTO);
			
			profesor.setProCodigo(asinacionDataManager.getProfesorCodigo());
			materia.setMtrCodigo(asinacionDataManager.getMateriaCodigo());
			periodo.setPerCodigo(asinacionDataManager.getPeriodoCodigo());
							
			asinacion.setMatNivelParalelo(nivelParalelo);
			asinacion.setMatProfesor(profesor);
			asinacion.setMatMateria(materia);
			asinacion.setMatPeriodo(periodo);
						
			AsinacionDTO asinacionNueva=this.servicioMatricula.createOrUpdateAsinacion(asinacion);
			MensajesWebController.aniadirMensajeInformacion("erp.matricula.asinacion.registrar.exito");			
			if (asinacionNueva != null) {
				asinacionDataManager.setAsinacionInsertar(new AsinacionDTO());
				asinacionDataManager.setNivelParaleloInsertar(new NivelParaleloDTO());
				asinacionDataManager.setProfesorInsertar(new ProfesorDTO());
				asinacionDataManager.setMateriaInsertar(new MateriaDTO());
				asinacionDataManager.setPeriodoInsertar(new PeriodoDTO());
				
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.asinacion.registrar.exito");
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarAsignacion()
	{
		AsinacionListDTO asinacionListDTO;
		List<AsinacionListDTO> asinacionListDTOs=null; 
		
		try {
			asinacionListDTO=new AsinacionListDTO();
			asinacionListDTO.setAsiEmpresa(getEmpresaCode());
			asinacionListDTO.setNpaNivel(asinacionDataManager.getNivelCodigo());
			asinacionListDTO.setNpaParalelo(asinacionDataManager.getParaleloCodigo());
			asinacionListDTOs = this.servicioMatricula.readAsinacion(asinacionListDTO);
			
			
			if (CollectionUtils.isEmpty(asinacionListDTOs) && asinacionListDTOs.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.asinacionDataManager.setAsinacionList(new ArrayList<AsinacionListDTO>());
			} else {
				this.asinacionDataManager.setAsinacionList(asinacionListDTOs);
				
			}
			
		
		
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar asignacion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	
	public void buscarNivelParalelo () {
		slf4jLogger.info("buscarNivelParalelo");
		
		List<NivelParaleloDTO> listaNivelParalelo=null;
		NivelParaleloDTO nivelParaleloDTO;
		NivelDTO nivelDTO;
		try {
			nivelParaleloDTO=new NivelParaleloDTO();
			nivelDTO=new NivelDTO();
			nivelParaleloDTO.setNpaEmpresa(getEmpresaCode());
			nivelDTO.setNivEmpresa(getEmpresaCode());
			nivelDTO.setNivCodigo(asinacionDataManager.getNivelCodigo());
			nivelParaleloDTO.setMatNivel(nivelDTO);
			listaNivelParalelo = this.servicioMatricula.buscarNivelParalelo(nivelParaleloDTO);							
			this.asinacionDataManager.setNivelParaleloList(listaNivelParalelo);				
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarNivel {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarNivel () {
		slf4jLogger.info("buscarNivel");
		
		List<NivelDTO> listaNivel=null;
		NivelDTO nivelDTO;
		try {
							
					
			nivelDTO=new NivelDTO();
			nivelDTO.setNivEmpresa(getEmpresaCode());
			listaNivel = this.servicioMatricula.buscarNivel(nivelDTO);
			
			if (CollectionUtils.isEmpty(listaNivel) && listaNivel.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.asinacionDataManager.setNivelList(listaNivel);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarNivel {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarDocente () {
		slf4jLogger.info("buscarDocente");
		
		List<DocenteListDTO> listadocentes=null;
		DocenteListDTO docenteListDTO;
		
		
		try {
			docenteListDTO=new DocenteListDTO();
			docenteListDTO.setProEmpresa(getEmpresaCode());
							
			listadocentes = this.servicioMatricula.buscarProfesor(docenteListDTO);
			
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
		MateriaDTO  materiaDTO;
		
		try {
							
			materiaDTO=new MateriaDTO();
			materiaDTO.setMtrEmpresa(getEmpresaCode());
			listamaterias = this.servicioMatricula.buscarMateria(materiaDTO);
			
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
		PeriodoDTO periodoDTO;
		
		try {
							
			periodoDTO=new PeriodoDTO();
			periodoDTO.setPerEmpresa(getEmpresaCode());
			listaperiodos = this.servicioMatricula.buscarPeriodo(periodoDTO);
			
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

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
