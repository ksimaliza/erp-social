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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.NivelParaleloDataManager;

@ViewScoped
@ManagedBean (name = "nivelParaleloController")
public class NivelParaleloController extends BaseController {
	
private static final long serialVersionUID = 1L;

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(NivelParaleloController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{nivelParaleloDataManager}")
	private NivelParaleloDataManager nivelParaleloDataManager;

	public NivelParaleloDataManager getNivelParaleloDataManager() {
		return nivelParaleloDataManager;
	}

	public void setNivelParaleloDataManager(
			NivelParaleloDataManager nivelParaleloDataManager) {
		this.nivelParaleloDataManager = nivelParaleloDataManager;
	}
	
	public NivelParaleloController(){}
	
	@PostConstruct
	private void init(){
		
		//buscar();
		buscarParalelo();
		buscarNivel();
		buscarNivelParalelo();
	}
	
public void registrarNivelParalelo () {
		
		slf4jLogger.info("registrarFalta");
		NivelParaleloDTO nivelParalelo;
		ParaleloDTO paralelo;
		NivelDTO nivel;
		try {
			nivelParalelo=new NivelParaleloDTO();
			
			paralelo=new ParaleloDTO();
			nivel=new NivelDTO();
			
			paralelo.setParCodigo(nivelParaleloDataManager.getParaleloCodigo());
			nivel.setNivCodigo(nivelParaleloDataManager.getNivelCodigo());
			
			nivelParalelo.setMatNivel(nivel);
			nivelParalelo.setMatParalelo(paralelo);
			
			NivelParaleloDTO nivelNuevo=this.servicioMatricula.createOrUpdateNivelParalelo(nivelParalelo);
						
			if (nivelNuevo != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.nivel.paralelo.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscar() {
		slf4jLogger.info("buscarNivelParalelo");
		List<NivelParaleloDTO> listResultado=new ArrayList<NivelParaleloDTO>();
		try {
			listResultado = this.servicioMatricula.buscarNivelParalelo(nivelParaleloDataManager.getNivelParaleloBuscar());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.nivelParaleloDataManager.setNivelParaleloList(listResultado);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el NivelParalelo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void buscarParalelo () {
		slf4jLogger.info("buscarParalelo");
		
		List<ParaleloDTO> listaparalelo=null;
		
		try {
							
			listaparalelo = this.servicioMatricula.buscarParalelo(new ParaleloDTO());
			
			if (CollectionUtils.isEmpty(listaparalelo) && listaparalelo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.nivelParaleloDataManager.setParaleloList(listaparalelo);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarParalelo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarNivel () {
		slf4jLogger.info("buscarNivel");
		
		List<NivelDTO> listaNivel=null;
		
		try {
							
			listaNivel = this.servicioMatricula.buscarNivel(new NivelDTO());
			if (CollectionUtils.isEmpty(listaNivel) && listaNivel.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.nivelParaleloDataManager.setNivelList(listaNivel);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarNivel {} ", e);
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
			nivelDTO.setNivCodigo(nivelParaleloDataManager.getNivelCodigo());
			nivelParaleloDTO.setMatNivel(nivelDTO);
			listaNivelParalelo = this.servicioMatricula.buscarNivelParalelo(nivelParaleloDTO);
			
			//servicioMatricula.readAsinacion(asinacion)
			
			if (CollectionUtils.isEmpty(listaNivelParalelo) && listaNivelParalelo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.nivelParaleloDataManager.setNivelParaleloList(listaNivelParalelo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarNivelParalelo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	


}
