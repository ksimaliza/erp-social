/**
 * 
 */
package ec.edu.uce.erp.web.controladores.componentes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;

/**
 * @author
 *
 */
@FacesComponent("ec.edu.uce.erp.web.controladores.componentes.BuscarUsuarioComponent")
public class BuscarUsuarioComponent extends UINamingContainer {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BuscarUsuarioComponent.class);
	private Integer emrPk;
	
	private VistaEmpleado vistaEmpleado;
	private List<VistaEmpleado> listVistaEmpleado;
	private ServicioInventario servicioInventario;
	private VistaEmpleado vistaEmpleadoSeleccionado;
	
	public BuscarUsuarioComponent (ServicioInventario servicioInventario, Integer emrPk) {
		this.vistaEmpleado = new VistaEmpleado();
		this.vistaEmpleadoSeleccionado = new VistaEmpleado();
		this.servicioInventario = servicioInventario;
		this.listVistaEmpleado = new ArrayList<VistaEmpleado>();
		this.emrPk = emrPk;
	}
	
	public Boolean getRenderUserId() {
		return (Boolean) getStateHelper().eval("txtNombre", Boolean.FALSE);
	}

	public void setRenderUserId(Boolean renderUserId) {
		getStateHelper().put("txtNombre", renderUserId);
		
	}
	
	public void buscarEmpleado () throws IOException {
		slf4jLogger.info("buscarEmpleado");
		try {
			encodeAll(getFacesContext());
			getAttributes().get("txtNombre");
			if (vistaEmpleado != null) {
				
				this.listVistaEmpleado.clear();
				vistaEmpleado.setEmrFk(emrPk);
				List<VistaEmpleado> listVistaEmpleado = servicioInventario.obtenerEmpleadoEmpresa(vistaEmpleado);
				if (CollectionUtils.isEmpty(listVistaEmpleado)) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					setListVistaEmpleado(listVistaEmpleado);
				}
			} else {
				slf4jLogger.info("vistaEmpleado null");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarEmpleado {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void asignarDatosEmpleadoSeleccionado (SelectEvent event) {
		this.vistaEmpleadoSeleccionado = (VistaEmpleado)event.getObject();
	}
	
	/**
	 * @return the vistaEmpleado
	 */
	public VistaEmpleado getVistaEmpleado() {
		return vistaEmpleado;
	}

	/**
	 * @param vistaEmpleado the vistaEmpleado to set
	 */
	public void setVistaEmpleado(VistaEmpleado vistaEmpleado) {
		this.vistaEmpleado = vistaEmpleado;
	}

	/**
	 * @return the listVistaEmpleado
	 */
	public List<VistaEmpleado> getListVistaEmpleado() {
		return listVistaEmpleado;
	}

	/**
	 * @param listVistaEmpleado the listVistaEmpleado to set
	 */
	public void setListVistaEmpleado(List<VistaEmpleado> listVistaEmpleado) {
		this.listVistaEmpleado = listVistaEmpleado;
	}

	/**
	 * @return the servicioInventario
	 */
	public ServicioInventario getServicioInventario() {
		return servicioInventario;
	}

	/**
	 * @param servicioInventario the servicioInventario to set
	 */
	public void setServicioInventario(ServicioInventario servicioInventario) {
		this.servicioInventario = servicioInventario;
	}

	/**
	 * @return the vistaEmpleadoSeleccionado
	 */
	public VistaEmpleado getVistaEmpleadoSeleccionado() {
		return vistaEmpleadoSeleccionado;
	}

	/**
	 * @param vistaEmpleadoSeleccionado the vistaEmpleadoSeleccionado to set
	 */
	public void setVistaEmpleadoSeleccionado(VistaEmpleado vistaEmpleadoSeleccionado) {
		this.vistaEmpleadoSeleccionado = vistaEmpleadoSeleccionado;
	}

	/**
	 * @return the emrPk
	 */
	public Integer getEmrPk() {
		return emrPk;
	}

	/**
	 * @param emrPk the emrPk to set
	 */
	public void setEmrPk(Integer emrPk) {
		this.emrPk = emrPk;
	}
	
}
