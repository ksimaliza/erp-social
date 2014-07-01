/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.util.dto.ActaBienDTO;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.VistaBienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="transaccionBienController")
public class TransaccionBienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(TransaccionBienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{vistaBienDataManager}")
	private VistaBienDataManager vistaBienDataManager;
	
	/**
	 * @param vistaBienDataManager the vistaBienDataManager to set
	 */
	public void setVistaBienDataManager(VistaBienDataManager vistaBienDataManager) {
		this.vistaBienDataManager = vistaBienDataManager;
	}
	
	public TransaccionBienController () {}
	

	/**
	 * Buscar bienes a trav&eacute;s de una vista
	 */
	public void buscarVistaBien () {
		
		slf4jLogger.info("buscarVistaBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienBuscar().setEmrPk(this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.vistaBienDataManager.getVistaBienBuscar().setTraEstado(this.vistaBienDataManager.getEstadoActivo());
			this.vistaBienDataManager.getVistaBienBuscar().setPerCi(this.vistaBienDataManager.getIdCIEmpleadoSeleccionado());
			
			if (StringUtils.isNotBlank(this.vistaBienDataManager.getIdDcTipoBienSelec())) {
				this.vistaBienDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(this.vistaBienDataManager.getIdDcTipoBienSelec());
			}
			
			this.vistaBienDataManager.getVistaBienBuscar().setCatBienPk(this.vistaBienDataManager.getIdCategoriaBienSeleccionado());
			this.vistaBienDataManager.getVistaBienBuscar().setLinBienPk(this.vistaBienDataManager.getIdLineaBienSeleccionado());
			
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.vistaBienDataManager.getVistaBienBuscar());
			
			this.vistaBienDataManager.getListVistaBien().clear();
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.vistaBienDataManager.setListVistaBien(listVistaBien);
			} else {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				
			}
//			this.vistaBienDataManager.limpiarCatalogos();
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	
	public String asignarBien () {
		
		slf4jLogger.info("asignarBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienEditar().setEmpAsignadoFk(this.vistaBienDataManager.getIdCustudioAsignado());
			VistaBien vistaBien = servicioInventario.asignarBien(this.vistaBienDataManager.getVistaBienEditar());
			
			if (vistaBien != null) {
				int posicion = this.vistaBienDataManager.getListVistaBien().indexOf(this.vistaBienDataManager.getVistaBienEditar());
				this.vistaBienDataManager.getListVistaBien().remove(this.vistaBienDataManager.getVistaBienEditar());
				this.vistaBienDataManager.getListVistaBien().add(posicion, vistaBien);
				MensajesWebController.aniadirMensajeInformacion("Bien asignado correctamente");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
		return "administracionBien";
		
	}
	
	public void reasignarBien () {
		
		slf4jLogger.info("reasignarBien");
		
		try {
			
			this.vistaBienDataManager.getVistaBienEditar().setEmpAsignadoFk(this.vistaBienDataManager.getIdCustudioReasignado());
			VistaBien vistaBien = servicioInventario.reasignarBien(this.vistaBienDataManager.getVistaBienEditar());
			
			if (vistaBien != null) {
				int posicion = this.vistaBienDataManager.getListVistaBien().indexOf(this.vistaBienDataManager.getVistaBienEditar());
				this.vistaBienDataManager.getListVistaBien().remove(this.vistaBienDataManager.getVistaBienEditar());
				this.vistaBienDataManager.getListVistaBien().add(posicion, vistaBien);
				this.vistaBienDataManager.setIdCustudioReasignado(0);
				MensajesWebController.aniadirMensajeInformacion("Bien reasignado correctamente");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void validarAsignacionCustodio () {
		slf4jLogger.info("validarAsignacionCustodio");
		
		if (this.vistaBienDataManager.getVistaBienEditar().getEmpAsignadoFk().intValue() == this.vistaBienDataManager.getIdCustudioReasignado().intValue()){
			MensajesWebController.aniadirMensajeError("El custodio a reemplazar no puede ser el mismo");
			this.vistaBienDataManager.setIdCustudioReasignado(0);
		}
		
	}
	
	public void obtenerTrazabilidadBien () {
		slf4jLogger.info("obtenerTrazabilidadBien");
		
		try {
			VistaTransaccion vistaTransaccion = new VistaTransaccion();
			vistaTransaccion.setBieFk(this.vistaBienDataManager.getVistaBienEditar().getBiePk());
			this.vistaBienDataManager.setListVistaTransaccion(servicioInventario.obtenerVistaTransaccionCriterios(vistaTransaccion));
		} catch (SeguridadesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generarActaBien () {
		
		List<ActaBienDTO> listActaBien = new ArrayList<ActaBienDTO>();
		ActaBienDTO actaBienDTO = new ActaBienDTO();
		actaBienDTO.setTituloActa("Acta bienes");
		listActaBien.add(actaBienDTO);
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), listActaBien, "actaAsignacionBien");
		ReporteUtil.generarReporte(jasperPrint, "pdf", "actaBien");
		
	}
	
	public void buscarEmpleado () {
		slf4jLogger.info("buscarEmpleado");
		
		try {
			this.vistaBienDataManager.getListVistaEmpleado().clear();
			this.vistaBienDataManager.getVistaEmpleadoBuscar().setEmrFk(this.vistaBienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			List<VistaEmpleado> listVistaEmpleado = servicioInventario.obtenerEmpleadoEmpresa(this.vistaBienDataManager.getVistaEmpleadoBuscar());
			if (CollectionUtils.isEmpty(listVistaEmpleado)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.vistaBienDataManager.setListVistaEmpleado(listVistaEmpleado);
			}
			
		} catch (SeguridadesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void asignarDatosEmpleadoSeleccionado (SelectEvent event) {
		VistaEmpleado vistaEmpleado = (VistaEmpleado)event.getObject();
		this.vistaBienDataManager.setIdCIEmpleadoSeleccionado(vistaEmpleado.getPerCi());
		slf4jLogger.info("buscarEmpleado");
	}
	
	public void limpiarFiltrosBusqueda () {
		this.vistaBienDataManager.setVistaBienBuscar(new VistaBien());
		this.vistaBienDataManager.setIdCIEmpleadoSeleccionado(null);
		this.vistaBienDataManager.setIdDcTipoBienSelec(null);
		this.vistaBienDataManager.setIdCategoriaBienSeleccionado(null);
		this.vistaBienDataManager.setIdLineaBienSeleccionado(null);
		this.vistaBienDataManager.getDcLineaBien().clear();
	}
	
}
