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
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.MatriculaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.datamanager.MatriculaDataManager;

@ViewScoped
@ManagedBean (name = "matriculaController")
public class MatriculaController extends BaseController {

	private static final long serialVersionUID = 1L;
	
		private static final Logger slf4jLogger = LoggerFactory.getLogger(AsinacionController.class);
		
		@EJB
		private ServicioMatricula servicioMatricula;
		private PeriodoDTO anioLectivoVigente;
		private List<PeriodoDTO> listaPeriodoDTOs;
		
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
		
		@SuppressWarnings("unused")
		@PostConstruct
		private void init(){
			//buscar();
			buscarEstudiantes();
			buscarNivel();
			obtenerAnioLectivoVigente();
		}
		
		public void registrarMatricula () {
			
			slf4jLogger.info("registrarMatricula");
			MatriculaVO matriculaVO;			
			
			EstudianteDTO estudianteDTO;
			List<MatriculaDTO> matriculado; 
			
			try {
				matriculaVO=new MatriculaVO();
				
				estudianteDTO=new EstudianteDTO();
				matriculado= new ArrayList<MatriculaDTO>();
				
				if(matriculaDataManager.getAsinacionList()!=null&&matriculaDataManager.getAsinacionList().size()<=0)
				{
					MensajesWebController.aniadirMensajeError("No existen datos del curso o paralelo en este Período.");
					return;
				}

				if(matriculaDataManager.getMatriculaInsertar().getRegFotoByte()==null)
				{
					MensajesWebController.aniadirMensajeError("Seleccione una Foto actualizada para este Período.");
					return;
				}
				
				//Agregado Ks
				if(matriculaDataManager.getEstudianteCodigo()==0)
				{
					MensajesWebController.aniadirMensajeError("Seleccione un estudiante antes de Continuar con la matrícula.");
					return;
				}

				
				estudianteDTO.setEstCodigo(matriculaDataManager.getEstudianteCodigo());
				matriculaDataManager.getMatriculaInsertar().setMatEstudiante(estudianteDTO);
				matriculaDataManager.getMatriculaInsertar().setRegFecha(new Timestamp(matriculaDataManager.getFechaInsertar().getTime()));
				matriculaDataManager.getMatriculaInsertar().setRegPeriodo(anioLectivoVigente.getPerCodigo());
				matriculaDataManager.getMatriculaInsertar().setRegEmpresa(getEmpresaCode());
				matriculaVO.setMatricula(matriculaDataManager.getMatriculaInsertar());
				matriculaVO.setAsignacion(matriculaDataManager.getAsinacionList());
				
				matriculado=servicioMatricula.readMatricula(matriculaDataManager.getMatriculaInsertar());
				
				if (matriculado.size()>0)
				{
					MensajesWebController.aniadirMensajeInformacion("El estudiante ya se encuentra matriculado en el Período actual");
				}
				else
				{
					servicioMatricula.updateEstadoEstudiante(matriculaDataManager.getEstudianteCodigo());
					servicioMatricula.createOrUpdateMatricula(matriculaVO);	
					MensajesWebController.aniadirMensajeInformacion("erp.matricula.registrar.exito");
					cancel();
				}
			} catch (SeguridadesException e) {
				slf4jLogger.info(e.toString());
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
			
		}
		
		
		public void buscarEstudiantes () {
			slf4jLogger.info("buscarEstudiantes");
			
			List<EstudianteListDTO> listaestudiantes=null;
			EstudianteListDTO estudianteListDTO;
			
			try {
				
				estudianteListDTO=new EstudianteListDTO();
				estudianteListDTO.setEstEmpresa(getEmpresaCode());
								
				listaestudiantes = this.servicioMatricula.buscarEstudiante(estudianteListDTO);
				
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
			NivelDTO nivelDTO;
			
			
			try {
				nivelDTO=new NivelDTO();
				nivelDTO.setNivEmpresa(getEmpresaCode());
								
				listaNivel = this.servicioMatricula.buscarNivel(nivelDTO);
				
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
				nivelDTO.setNivEmpresa(getEmpresaCode());
				nivelDTO.setNivCodigo(matriculaDataManager.getNivelCodigo());
				nivelParaleloDTO.setNpaEmpresa(getEmpresaCode());
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
				asinacionListDTO.setAsiEmpresa(getEmpresaCode());
				asinacionListDTO.setPerCodigo(anioLectivoVigente.getPerCodigo());
				asinacionListDTO.setNpaNivel(matriculaDataManager.getNivelCodigo());
				asinacionListDTO.setNpaParalelo(matriculaDataManager.getParaleloCodigo());
				asinacionListDTOs = this.servicioMatricula.readAsinacion(asinacionListDTO);
				
				
				if (CollectionUtils.isEmpty(asinacionListDTOs) && asinacionListDTOs.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
					this.matriculaDataManager.setAsinacionList(new ArrayList<AsinacionListDTO>());
				} else {
					this.matriculaDataManager.setAsinacionList(asinacionListDTOs);
				}
				
			
			
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al buscar asignacion {} ", e);
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
		}
		
		
		public void handleFileUpload(FileUploadEvent event) {
			matriculaDataManager.getMatriculaInsertar().setRegFoto(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), event.getFile().getFileName()));
			matriculaDataManager.getMatriculaInsertar().setRegFotoByte(event.getFile().getContents());
	    }

		@Override
		public void refrescarFormulario() {
			// TODO Auto-generated method stub
			
		}
		
		public void cancel()
		{
			matriculaDataManager.setMatriculaInsertar(new MatriculaDTO());
			matriculaDataManager.setAsinacionList(new ArrayList<AsinacionListDTO>());
		}
		
		public void obtenerAnioLectivoVigente() {
			try {
				this.setAnioLectivoVigente(servicioMatricula.obtenerAnioLectivoVigente(getEmpresaCode()));
                this.setListaPeriodoDTOs(servicioMatricula.buscarPeriodo(new PeriodoDTO()));

			} catch (SeguridadesException e) {
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
		}

		public PeriodoDTO getAnioLectivoVigente() {
			return anioLectivoVigente;
		}

		public void setAnioLectivoVigente(PeriodoDTO anioLectivoVigente) {
			this.anioLectivoVigente = anioLectivoVigente;
		}

		public List<PeriodoDTO> getListaPeriodoDTOs() {
			return listaPeriodoDTOs;
		}

		public void setListaPeriodoDTOs(List<PeriodoDTO> listaPeriodoDTOs) {
			this.listaPeriodoDTOs = listaPeriodoDTOs;
		}
		
		

}
