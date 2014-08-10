/**
 * 
 */
package ec.edu.uce.erp.web.common.controladores;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;
import ec.edu.uce.erp.ejb.persistence.util.dto.AnioDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name = "selectItemsController")
public class SelectItemsController {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(SelectItemsController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	private List<SelectItem> catalogoListaEmpresa;
	private List<SelectItem> catalogoListaPerfiles;
	private List<SelectItem> catalogoListaModulos;
	private List<SelectItem> catalogoListAnio;
	
	public SelectItemsController () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		this.catalogoListaEmpresa = new ArrayList<SelectItem>();
		this.catalogoListaPerfiles = new ArrayList<SelectItem>();
		this.catalogoListaModulos = new ArrayList<SelectItem>();
		this.catalogoListAnio=new ArrayList<SelectItem>();
	}
	
	/**
	 * @return the catalogoListaEmpresa
	 */
	public List<SelectItem> getCatalogoListaEmpresa() {
		
		if (CollectionUtils.isEmpty(catalogoListaEmpresa)) {
			
			slf4jLogger.info("cargar catalogo empresa");
			Empresa dto = new Empresa();
			dto.setEmrEstado(ESTADO_ACTIVO);
			try {
				List<Empresa> empresaCol = servicioAdministracion.buscarEmpresa(dto);
				CollectionUtils.collect(empresaCol, new Transformer() {
					@Override
					public Object transform(final Object arg0) {
						final Empresa empresaDTO = (Empresa)arg0;
						return new SelectItem(empresaDTO.getEmrPk(), empresaDTO.getEmrNombre());
					}
				}, catalogoListaEmpresa);
			} catch (SeguridadesException e) {
				slf4jLogger.info("error al cargar catalogo empresa {}", e.getMessage());
				MensajesWebController.aniadirMensajeError("Error al cargar catalogo empresa");
			}
		}
		
		return catalogoListaEmpresa;
	}

	/**
	 * @return the catalogoListaPerfiles
	 */
	public List<SelectItem> getCatalogoListaPerfiles() {
		
		if (CollectionUtils.isEmpty(this.catalogoListaPerfiles)) {
			slf4jLogger.info("cargar catalogo perfiles");
			try {
				Perfil dto = new Perfil();
				dto.setEstado(ESTADO_ACTIVO);
				List<Perfil> perfilesCol = servicioAdministracion.buscarPerfiles(dto);
				CollectionUtils.collect(perfilesCol, new Transformer() {
					@Override
					public Object transform(final Object arg0) {
						final Perfil perfilDTO = (Perfil)arg0;
						return new SelectItem(perfilDTO.getIdPerfil(), perfilDTO.getNombrePerfil());
					}
				}, catalogoListaPerfiles);
			} catch (SeguridadesException e) {
				slf4jLogger.info("error al cargar catalogo perfiles {}", e.getMessage());
				MensajesWebController.aniadirMensajeError("Error al cargar catalogo perfiles");
			}
		}
		
		return catalogoListaPerfiles;
	}

	/**
	 * @return the catalogoListaModulo
	 */
	public List<SelectItem> getCatalogoListaModulos() {
		
		if (CollectionUtils.isEmpty(this.catalogoListaModulos)) {
			slf4jLogger.info("cargar catalogoListaModulos");
			
			try {
				Modulo moduloDTO = new Modulo();
				moduloDTO.setEstado(ESTADO_ACTIVO);
				List<Modulo> moduloCol = this.servicioAdministracion.buscarModulos(moduloDTO);
				
				CollectionUtils.collect(moduloCol, new Transformer() {
					@Override
					public Object transform(final Object arg0) {
						final Modulo moduloDTO = (Modulo)arg0;
						return new SelectItem(moduloDTO.getIdModulo(), moduloDTO.getNombreModulo());
					}
				}, catalogoListaModulos);
				
			} catch (SeguridadesException e) {
				slf4jLogger.info("error al cargar catalogo modulos {}", e.getMessage());
				MensajesWebController.aniadirMensajeError("Error al cargar catalogo modulos");
			}
			
		}
		
		return catalogoListaModulos;
	}
	
	
/**
 * Comentado por no esta subido la clase AnioDTO
 */
	/**
	 * 
	 * @return catalogoListAnio
	 */
	public List<SelectItem> getCatalogoListAnio() {
		
		if (CollectionUtils.isEmpty(this.catalogoListAnio)) {
			slf4jLogger.info("cargar catalogoListAnio");
			
			try {
				List<AnioDTO> listAnio = servicioAsistencia.readAnioActual();
				
				CollectionUtils.collect(listAnio, new Transformer() {
					@Override
					public Object transform(final Object arg0) {
						final AnioDTO anioDTO = (AnioDTO)arg0;
						return new SelectItem(anioDTO.getCodigo(),anioDTO.getDescripcion());
					}
				}, catalogoListAnio);
				
			} catch (SeguridadesException e) {
				slf4jLogger.info("error al cargar catalogo anio {}", e.getMessage());
				MensajesWebController.aniadirMensajeError("Error al cargar catalogo anio");
			}
			
		}
		
		return catalogoListAnio;
	}
	
}
