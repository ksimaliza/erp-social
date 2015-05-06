package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
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
		
		
	public void registrarTipo () {
		
		slf4jLogger.info("registrarTipo");
		try {
			this.tipoDataManager.getTipoInsertar().setTipEmpresa(getEmpresaCode());
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
		try {
			tipoDataManager.gettipoBuscar().setTipEmpresa(getEmpresaCode());
			this.tipoDataManager.setListTipo(servicioAsistencia.readTipo(tipoDataManager.gettipoBuscar()));
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

	public void cancel()
	{
		tipoDataManager.setTipoInsertar(new TipoDTO());
		RequestContext.getCurrentInstance().execute("dlgNuevoTipoNicho.hide()");
	}

	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
		
}
