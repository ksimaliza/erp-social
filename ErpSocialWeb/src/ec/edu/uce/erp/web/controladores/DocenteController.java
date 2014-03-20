package ec.edu.uce.erp.web.controladores;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;
import ec.edu.uce.erp.ejb.persistence.vo.ProfesorVO;

import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.DocenteDataManager;


@ViewScoped
@ManagedBean (name = "DocenteController")
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
		try {
			ProfesorDTO profesorNuevo = this.servicioMatricula.createOrUpdateProfesor(this.docenteDataManager.getProfesorInstancia());
			if (profesorNuevo != null) {
				docenteDataManager.setProfesorInstancia(new ProfesorVO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.docente.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	/**
	 * Buscar un docente en la bd
	 */
	//public void buscarDocente () {
		
	//	slf4jLogger.info("buscarDocente");
		//try {
			
		//	List<ProfesorDTO> docenteCol = this.servicioMatricula.;
			
		//	if (CollectionUtils.isEmpty(empresaCol) || empresaCol.size() < 0) {
		//		MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
		//		empresaDataManager.setListaEmpresa(new ArrayList<Empresa>());
		//	} else {
		//		empresaDataManager.setListaEmpresa(empresaCol);
		//	}
			
		//} catch (SeguridadesException e) {
		//	slf4jLogger.info("error al buscarEmpresa {}", e.toString());
		//	MensajesWebController.aniadirMensajeError(e.getMessage());
		//}
		
	//}
	
	
	
}
