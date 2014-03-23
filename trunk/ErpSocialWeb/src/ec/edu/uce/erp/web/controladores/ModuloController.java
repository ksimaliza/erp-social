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
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ModuloDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name = "moduloController")
public class ModuloController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ModuloController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{moduloDataManager}")
	private ModuloDataManager moduloDataManager;

	/**
	 * @param moduloDataManager the moduloDataManager to set
	 */
	public void setModuloDataManager(ModuloDataManager moduloDataManager) {
		this.moduloDataManager = moduloDataManager;
	}
	
	private List<Object> empresasSeleccionadas;
	
	public ModuloController () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		this.empresasSeleccionadas = new ArrayList<Object>();
	}
	
	/*
	 * Metodos
	 */
	
	/**
	 * Registrar un m&oacute;dulo en la bd
	 */
	public void registrarModulo () {
		slf4jLogger.info("registrarModulo");
		try {
			
			moduloDataManager.getModuloInstancia().setEmpresaTbls(this.asignarEmpresasSeleccionadas());
			Modulo moduloNuevo = servicioAdministracion.registrarModulo(moduloDataManager.getModuloInstancia());
			
			if (moduloNuevo != null) {
				moduloDataManager.getModuloCol().add(moduloNuevo);
				moduloDataManager.setModuloInstancia(new Modulo());
				MensajesWebController.aniadirMensajeInformacion("erp.modulo.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void actualizarModulo () {
		slf4jLogger.info("actualizarModulo");
		
		try {
			
			this.moduloDataManager.getModuloEditar().setUsuarioRegistro(this.moduloDataManager.getUsuarioSession());
			this.moduloDataManager.getModuloEditar().setEmpresaTbls(this.asignarEmpresasSeleccionadas());
			this.servicioAdministracion.actualizarModulo(this.moduloDataManager.getModuloEditar());
			MensajesWebController.aniadirMensajeInformacion("erp.modulo.actualizar.exito");
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void activarModulo () {
		
		slf4jLogger.info("activarModulo");
		
		try {
			
			if (this.moduloDataManager.getModuloEditar()!=null) {
				this.moduloDataManager.getModuloEditar().setUsuarioRegistro(this.moduloDataManager.getUsuarioSession());
				this.moduloDataManager.getModuloEditar().setEstado(moduloDataManager.getEstadoActivo());
				this.servicioAdministracion.actualizarModulo(this.moduloDataManager.getModuloEditar());
				MensajesWebController.aniadirMensajeInformacion("erp.modulo.actualizar.exito");
			}
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void inactivarModulo () {
		
		slf4jLogger.info("inactivarModulo");
		
		try {
			
			if (this.moduloDataManager.getModuloEditar()!=null) {
				this.moduloDataManager.getModuloEditar().setUsuarioRegistro(this.moduloDataManager.getUsuarioSession());
				this.moduloDataManager.getModuloEditar().setEstado(moduloDataManager.getEstadoInactivo());
				this.servicioAdministracion.actualizarModulo(this.moduloDataManager.getModuloEditar());
				MensajesWebController.aniadirMensajeInformacion("erp.modulo.actualizar.exito");
			}
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	/**
	 * Buscar Modulos
	 */
	public void buscarModulos () {
		slf4jLogger.info("buscarModulos");
		
		try {
			
			List<Modulo> listaModulos = this.servicioAdministracion.buscarModulos(this.moduloDataManager.getModuloBuscar());
			
			if (CollectionUtils.isEmpty(listaModulos) && listaModulos.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.moduloDataManager.setModuloCol(listaModulos);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el modulo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Empresa> asignarEmpresasSeleccionadas() {
		
		List<Empresa> empresaCol = null;
		
		if (CollectionUtils.isNotEmpty(getEmpresasSeleccionadas())) {
			
			empresaCol = (List<Empresa>) CollectionUtils.collect(getEmpresasSeleccionadas(), new Transformer() {
				
				@Override
				public Object transform(Object arg0) {
					Empresa empresa = new Empresa();
					empresa.setEmrPk(Integer.valueOf(arg0.toString()));
					return empresa;
				}
			});
		}
		
		return empresaCol;
	}
	
	public void asignarDatosModulo (Modulo modulo) {
		slf4jLogger.info("asignarDatosModulo");
		
		getEmpresasSeleccionadas().clear();
		
		if (CollectionUtils.isNotEmpty(modulo.getEmpresaTbls())) {
			for (Empresa empresa : modulo.getEmpresaTbls()) {
				getEmpresasSeleccionadas().add(empresa.getEmrPk());
			}
		}
		
	}
	
	/*
	 * Propiedades
	 */

	/**
	 * @return the empresasSeleccionadas
	 */
	public List<Object> getEmpresasSeleccionadas() {
		return empresasSeleccionadas;
	}

	/**
	 * @param empresasSeleccionadas the empresasSeleccionadas to set
	 */
	public void setEmpresasSeleccionadas(List<Object> empresasSeleccionadas) {
		this.empresasSeleccionadas = empresasSeleccionadas;
	}

}
