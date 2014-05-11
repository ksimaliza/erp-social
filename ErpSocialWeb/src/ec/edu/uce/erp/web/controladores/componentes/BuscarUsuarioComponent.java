/**
 * 
 */
package ec.edu.uce.erp.web.controladores.componentes;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean(name = "buscarUsuarioComponent")
public class BuscarUsuarioComponent {
	
	private VistaEmpleado vistaEmpleado;
	private List<VistaEmpleado> listVistaEmpleado;
	private ServicioInventario servicioInventario;
	
	public BuscarUsuarioComponent (ServicioInventario servicioInventario) {
		this.vistaEmpleado = new VistaEmpleado();
		this.servicioInventario = servicioInventario;
	}
	
	public List<VistaEmpleado> buscarEmpleados () {
		try {
			return this.servicioInventario.obtenerEmpleadoEmpresa(this.vistaEmpleado);
		} catch (SeguridadesException e) {
			e.printStackTrace();
		}
		return null;
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
	
}
