package ec.edu.uce.erp.web.controladores;

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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.AsinacionDataManager;
import ec.edu.uce.erp.web.datamanager.MatriculaDataManager;

@ViewScoped
@ManagedBean (name = "matriculaController")

public class MatriculaController extends BaseController {

	private static final long serialVersionUID = 1L;
	
		private static final Logger slf4jLogger = LoggerFactory.getLogger(AsinacionController.class);
		
		@EJB
		private ServicioMatricula servicioMatricula;
		
		@ManagedProperty(value="#{matriculaDataManager}")
		private MatriculaDataManager matriculaDataManager;

		public MatriculaDataManager getMatriculaDataManager() {
			return matriculaDataManager;
		}

		public void setMatriculaDataManager(MatriculaDataManager matriculaDataManager) {
			this.matriculaDataManager = matriculaDataManager;
		}

		public MatriculaController ()
		{
			
		}
		
		@PostConstruct
		private void init(){
			
			//buscar();
			buscarEstudiantes();
			buscarNivel();
			buscarParalelo();
			
		}
		
		
		
		
		
		
		public void buscarEstudiantes () {
			slf4jLogger.info("buscarEstudiantes");
			
			List<EstudianteListDTO> listaestudiantes=null;
			
			try {
								
				listaestudiantes = this.servicioMatricula.buscarEstudiante(new EstudianteListDTO());
				
				if (CollectionUtils.isEmpty(listaestudiantes) && listaestudiantes.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.matriculaDataManager.setEstudianteList(listaestudiantes);
					
				}
				
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al buscar el estudiante {} ", e);
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
					this.matriculaDataManager.setParaleloList(listaparalelo);
			
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
					this.matriculaDataManager.setNivelList(listaNivel);
				}
				
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al buscarNivel {} ", e);
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
			
		}
		
}
