package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

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
	
	
public void registrarAsinacion () {
		
		slf4jLogger.info("registrarAsinacion");
		AsinacionDTO asinacion=new AsinacionDTO();
		try {
			asinacionDataManager.getNivelParaleloInsertar().setNpaCodigo(asinacionDataManager.getNivelParaleloInsertar().getNpaCodigo());
			asinacionDataManager.getProfesorInsertar().setProCodigo(asinacionDataManager.getProfesorInsertar().getProCodigo());
			asinacionDataManager.getMateriaInsertar().setMtrCodigo(asinacionDataManager.getMateriaInsertar().getMtrCodigo());
			asinacionDataManager.getPeriodoInsertar().setPerCodigo(asinacionDataManager.getPeriodoInsertar().getPerCodigo());
			asinacion.setMatNivelParalelo(asinacionDataManager.getNivelParaleloInsertar());
			asinacion.setMatProfesor(asinacionDataManager.getProfesorInsertar());
			asinacion.setMatMateria(asinacionDataManager.getMateriaInsertar());
			asinacion.setMatPeriodo(asinacionDataManager.getPeriodoInsertar());
						
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
	
}
