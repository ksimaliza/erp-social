package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.MateriaDataManager;

@ViewScoped
@ManagedBean (name = "materiaController")

public class MateriaController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(MateriaController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{materiaDataManager}")
	private MateriaDataManager materiaDataManager;
	
	public void setMateriaDataManager(MateriaDataManager materiaDataManager) {
		this.materiaDataManager = materiaDataManager;
	}
	

	public MateriaDataManager getMateriaDataManager() {
		return materiaDataManager;
	}


	public MateriaController() {
		
	}
	
	/*
	 * Medodos
	 */
	
	public void registrarMateria () {
		
		slf4jLogger.info("registrarMateria");
		try {
			
			materiaDataManager.getMateriaInstancia().setMtrEmpresa(getEmpresaCode());
			MateriaDTO materiaNueva = this.servicioMatricula.createOrUpdateMateria(this.materiaDataManager.getMateriaInstancia());
			if (materiaNueva != null) {
				materiaDataManager.setMateriaInstancia(new MateriaDTO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.materia.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMateria () {
		slf4jLogger.info("buscarMateria");
		
		List<MateriaDTO> listamaterias=null;
		
		try {
				
			materiaDataManager.getMateriaBuscar().setMtrEmpresa(getEmpresaCode());
			listamaterias = this.servicioMatricula.buscarMateria(materiaDataManager.getMateriaBuscar());
			
			if (CollectionUtils.isEmpty(listamaterias) && listamaterias.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.materiaDataManager.setMateriaDTOs(listamaterias);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMateria {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosMateria (MateriaDTO materia) {
		try {
			MateriaDTO materiaEncontrado = servicioMatricula.obtenerMateriaPorId(materia.getMtrCodigo());
			
			this.materiaDataManager.setMateriaInstancia(materiaEncontrado);
									
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargar los cargarDatosMateria seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosMateria seleccionado");
		}
	}
	
	
	
}
