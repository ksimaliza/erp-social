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
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ProfesorVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.DocenteDataManager;


@ViewScoped
@ManagedBean (name = "docenteController")
public class DocenteController {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DocenteController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{docenteDataManager}")
	private DocenteDataManager docenteDataManager;
	
	
	public void setDocenteDataManager(DocenteDataManager docenteDataManager) {
		this.docenteDataManager = docenteDataManager;
	}
	

	public DocenteController () {}
	
	
	/*
	 * Medodos
	 */
	
	public void registrarDocente () {
		
		slf4jLogger.info("registrarDocente");
		ProfesorVO profesorVO;
		
		try {
			profesorVO=new ProfesorVO();
			profesorVO.setProfesor(docenteDataManager.getProfesorInstancia());
			profesorVO.setPersona(docenteDataManager.getPersonaInstancia());
			ProfesorDTO profesorNuevo = this.servicioMatricula.createOrUpdateProfesor(profesorVO);
			
			if (profesorNuevo != null) {
				docenteDataManager.setProfesorInstancia(new ProfesorDTO());
				docenteDataManager.setPersonaInstancia(new Persona());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.docente.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarDocente () {
		slf4jLogger.info("buscarDocente");
		
		List<DocenteListDTO> listadocentes=null;
		
		try {
							
			listadocentes = this.servicioMatricula.buscarProfesor(docenteDataManager.getProfesorBuscar());
			
			if (CollectionUtils.isEmpty(listadocentes) && listadocentes.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.docenteDataManager.setListaDocenteListDTOs(listadocentes);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDocente {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	

	
	
	
	
}
