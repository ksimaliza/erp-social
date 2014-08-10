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
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.TipoDataManager;


@ViewScoped
@ManagedBean (name = "tipoController")

public class TipoController extends BaseController {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final Logger slf4jLogger = LoggerFactory.getLogger(TipoController.class);
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{tipoDataManager}")
	private TipoDataManager tipoDataManager;

	
		public void setTipoDataManager(TipoDataManager tipoDataManager) {
		this.tipoDataManager = tipoDataManager;
	}
		
		public TipoController() {
			
		}
		
		
		/*
		 * Medodos
		 */
		
		public void registrarTipo () {
			
			slf4jLogger.info("registrarTipo");
			try {
				TipoDTO tipoNuevo = this.servicioAsistencia.createOrUpdateTipo(this.tipoDataManager.getTipoInsertar());
				if (tipoNuevo != null) {
					tipoDataManager.setTipoInsertar(new TipoDTO());
					MensajesWebController.aniadirMensajeInformacion("erp.asistencia.tipo.registrar.exito");
				}
				
			} catch (SeguridadesException e) {
				slf4jLogger.info(e.toString());
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
			
		}
		
		public void buscarTipo () {
			slf4jLogger.info("buscarTipo");
			
			List<TipoDTO> listaTipo=null;
			
			try {
								
				listaTipo = this.servicioAsistencia.buscarTipo(tipoDataManager.gettipoBuscar());
				
				if (CollectionUtils.isEmpty(listaTipo) && listaTipo.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.tipoDataManager.setListTipo(listaTipo);
				}
				
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al buscarTipo {} ", e);
				MensajesWebController.aniadirMensajeError(e.getMessage());
			}
			
		}
		
		public void cargarDatosTipo (TipoDTO tipo) {
			try {
				TipoDTO tipoEncontrado = servicioAsistencia.obtenerTipoPorId(tipo.getTipCodigo());
				this.tipoDataManager.setTipoInsertar(tipoEncontrado);
				
														
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al cargar los cargarDatosTipo seleccionado {}", e.getMessage());
				MensajesWebController.aniadirMensajeError("Error al cargarDatosTipo seleccionado");
			}
		}

		@Override
		protected void refrescarFormulario() {
			// TODO Auto-generated method stub
			
		}
		
}
