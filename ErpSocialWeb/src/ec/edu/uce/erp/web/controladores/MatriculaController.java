package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
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
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.vo.MatriculaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
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
		}
		
		public void registrarMatricula () {
			
			slf4jLogger.info("registrarMatricula");
			MatriculaVO matriculaVO;			
			MatriculaDTO matriculaDTO;
			EstudianteDTO estudianteDTO;
			List<MatriculaDTO> matriculado; 
			
			try {
				matriculaVO=new MatriculaVO();
				matriculaDTO=new MatriculaDTO();
				estudianteDTO=new EstudianteDTO();
				matriculado= new ArrayList<MatriculaDTO>();
				
				estudianteDTO.setEstCodigo(matriculaDataManager.getEstudianteCodigo());
				matriculaDTO.setMatEstudiante(estudianteDTO);
				matriculaDTO.setRegFecha(new Timestamp(matriculaDataManager.getFechaInsertar().getTime()));
				
				
				matriculaVO.setMatricula(matriculaDTO);
				matriculaVO.setAsignacion(matriculaDataManager.getAsinacionList());
				
				matriculado=servicioMatricula.readMatricula(matriculaDTO);
				
				if (matriculado.size()>0)
				{
					MensajesWebController.aniadirMensajeInformacion("El estudiante ya esta matriculado");
				}
				else
				{
					servicioMatricula.createOrUpdateMatricula(matriculaVO);	
					MensajesWebController.aniadirMensajeInformacion("erp.matricula.registrar.exito");
				}
				} catch (SeguridadesException e) {
					slf4jLogger.info(e.toString());
					MensajesWebController.aniadirMensajeError(e.getMessage());
			}
			
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
		
		public void buscarNivelParalelo () {
			slf4jLogger.info("buscarNivelParalelo");
			
			List<NivelParaleloDTO> listaNivelParalelo=null;
			NivelParaleloDTO nivelParaleloDTO;
			NivelDTO nivelDTO;
			try {
				nivelParaleloDTO=new NivelParaleloDTO();
				nivelDTO=new NivelDTO();
				nivelDTO.setNivCodigo(matriculaDataManager.getNivelCodigo());
				nivelParaleloDTO.setMatNivel(nivelDTO);
				listaNivelParalelo = this.servicioMatricula.buscarNivelParalelo(nivelParaleloDTO);
				
				this.matriculaDataManager.setNivelParaleloList(listaNivelParalelo);
					
				
				
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al buscarNivel {} ", e);
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
			
		}
		
		
		public void buscarAsignacion()
		{
			AsinacionListDTO asinacionListDTO;
			List<AsinacionListDTO> asinacionListDTOs=null; 
			
			try {
				asinacionListDTO=new AsinacionListDTO();
				asinacionListDTO.setNpaNivel(matriculaDataManager.getNivelCodigo());
				asinacionListDTO.setNpaParalelo(matriculaDataManager.getParaleloCodigo());
				asinacionListDTOs = this.servicioMatricula.readAsinacion(asinacionListDTO);
				
				
				if (CollectionUtils.isEmpty(asinacionListDTOs) && asinacionListDTOs.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.matriculaDataManager.setAsinacionList(asinacionListDTOs);
				}
				
			
			
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al buscar asignacion {} ", e);
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
		}
}
