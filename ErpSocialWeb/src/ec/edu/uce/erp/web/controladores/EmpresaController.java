/**
 * 
 */
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
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.EmpresaDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name = "empresaController")
public class EmpresaController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{empresaDataManager}")
	private EmpresaDataManager empresaDataManager;
	
	/**
	 * @param empresaDataManager the empresaDataManager to set
	 */
	public void setEmpresaDataManager(EmpresaDataManager empresaDataManager) {
		this.empresaDataManager = empresaDataManager;
	}
	
	public EmpresaController () {}
	
	@PostConstruct
	public void inicializarObjetos () {
	}
	
	/*
	 * metodos
	 */
	
	/**
	 * Registrar una empresa la base de datos
	 */
	public void registrarEmpresa () {
		
		slf4jLogger.info("registrarEmpresa");
		try {
			
			Empresa empresaNueva = this.servicioAdministracion.registrarEmpresa(this.empresaDataManager.getEmpresaInstancia());
			if (empresaNueva != null) {
				empresaDataManager.getListaEmpresa().add(empresaNueva);
				empresaDataManager.setEmpresaInstancia(new Empresa());
				MensajesWebController.aniadirMensajeInformacion("erp.empresa.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	/**
	 * Buscar una empresa en la bd
	 */
	public void buscarEmpresa () {
		
		slf4jLogger.info("buscarEmpresa");
		try {
			
			List<Empresa> empresaCol = this.servicioAdministracion.buscarEmpresa(empresaDataManager.getEmpresaBuscar());
			
			if (CollectionUtils.isEmpty(empresaCol) || empresaCol.size() < 0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				empresaDataManager.setListaEmpresa(new ArrayList<Empresa>());
			} else {
				empresaDataManager.setListaEmpresa(empresaCol);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarEmpresa {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	/**
	 * Actualizar empresa
	 */
	public void actualizarEmpresa () {
		slf4jLogger.info("actualizarEmpresa");
		
		try {
			this.servicioAdministracion.actualizarEmpresa(this.empresaDataManager.getEmpresaEditar());
			this.empresaDataManager.setEmpresaEditar(new Empresa());
			MensajesWebController.aniadirMensajeInformacion("erp.empresa.actualizar.exito");
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al actualizarEmpresa {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void desactivarEmpresa () {
		slf4jLogger.info("desactivarEmpresa");
		
		try {
		
			if (this.empresaDataManager.getEmpresaEditar() != null) {
				
				this.empresaDataManager.getEmpresaEditar().setEmrEstado(empresaDataManager.getEstadoInactivo());
				this.servicioAdministracion.actualizarEmpresa(this.empresaDataManager.getEmpresaEditar());
				this.empresaDataManager.setEmpresaEditar(new Empresa());
				
				MensajesWebController.aniadirMensajeInformacion("erp.empresa.actualizar.exito");
				
			}
		
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al desactivarEmpresa {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void activarEmpresa () {
		
		slf4jLogger.info("activarEmpresa");
		
		try {
		
			if (this.empresaDataManager.getEmpresaEditar() != null) {
				
				this.empresaDataManager.getEmpresaEditar().setEmrEstado(empresaDataManager.getEstadoActivo());
				this.servicioAdministracion.actualizarEmpresa(this.empresaDataManager.getEmpresaEditar());
				this.empresaDataManager.setEmpresaEditar(new Empresa());
				
				MensajesWebController.aniadirMensajeInformacion("erp.empresa.actualizar.exito");
				
			}
		
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al activarEmpresa {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
}
