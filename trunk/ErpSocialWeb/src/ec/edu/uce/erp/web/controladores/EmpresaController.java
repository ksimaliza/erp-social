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
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
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
			this.empresaDataManager.getEmpresaEditar().setUsuarioRegistro(this.empresaDataManager.getUsuarioSession());
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
				this.empresaDataManager.getEmpresaEditar().setUsuarioRegistro(this.empresaDataManager.getUsuarioSession());
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
				
				this.empresaDataManager.getEmpresaEditar().setUsuarioRegistro(this.empresaDataManager.getUsuarioSession());
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
	
	public void cargarDatosEmpresa (Empresa empresa) {
		try {
			Empresa empresaEditar = servicioAdministracion.obtenerEmpresaPorId(empresa.getEmrPk());
			
			if (empresaEditar == null) {
				this.empresaDataManager.setEmpresaEditar(empresa);
			} else {
				this.empresaDataManager.setEmpresaEditar(empresaEditar);
			}
			this.validarDesactivarEmpresa(this.empresaDataManager.getEmpresaEditar());
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargar los datos de la empresa seleccionada {}", e.toString());
			MensajesWebController.aniadirMensajeError("Error al cargar los datos de la empresa seleccionada");
		}
	}
	
	private void validarDesactivarEmpresa (Empresa empresa) {
		if (CollectionUtils.isNotEmpty(empresa.getSegtUsuarios()) || CollectionUtils.isNotEmpty(empresa.getSegtModulos())){
			empresa.setNpMensajeEditar(UtilAplication
					.appendStringBuilder("La empresa: ", empresa.getEmrNombre(),
							" tiene usuarios o m\u00F3dulos relacionados, est\u00E1 seguro de continuar?").toString());
		} else {
			empresa.setNpMensajeEditar(UtilAplication.appendStringBuilder("Seguro desea desactivar la empresa ", empresa.getEmrNombre(), " ?").toString());
		}
	}
	
	public void obtenerModulosEmpresa (Empresa empresa) {
		try {
			if (CollectionUtils.isEmpty(empresa.getNpColModulos())) {
				Modulo modulo = new Modulo();
				modulo.setNpIdEmpresa(empresa.getEmrPk());
				empresa.setNpColModulos(this.servicioAdministracion.buscarModulos(modulo));
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al obtenerModulosEmpresa {}", e.toString());
			MensajesWebController.aniadirMensajeError("Error al cargar los modulos de la empresa seleccionada");
		}
	}

	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}
