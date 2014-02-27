/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.Proveedor;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "proveedorDataManager")
public class ProveedorDataManager extends BaseDataManager{

	private static final long serialVersionUID = 1L;
	
	private Proveedor proveedorInstancia;
	private Proveedor proveedorEditar;
	private Proveedor proveedorBuscar;
	
	private List<Proveedor> listaProveedor;
	
	public ProveedorDataManager () {
		super();
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		this.proveedorInstancia = new Proveedor();
		this.proveedorInstancia.setUsuarioRegistro(getUsuarioSession());
		this.proveedorEditar = new Proveedor();
		this.proveedorBuscar = new Proveedor();
		this.listaProveedor = new ArrayList<Proveedor>();
	}

	/**
	 * @return the proveedorInstancia
	 */
	public Proveedor getProveedorInstancia() {
		return proveedorInstancia;
	}

	/**
	 * @param proveedorInstancia the proveedorInstancia to set
	 */
	public void setProveedorInstancia(Proveedor proveedorInstancia) {
		this.proveedorInstancia = proveedorInstancia;
	}

	/**
	 * @return the proveedorEditar
	 */
	public Proveedor getProveedorEditar() {
		return proveedorEditar;
	}

	/**
	 * @param proveedorEditar the proveedorEditar to set
	 */
	public void setProveedorEditar(Proveedor proveedorEditar) {
		this.proveedorEditar = proveedorEditar;
	}

	/**
	 * @return the proveedorBuscar
	 */
	public Proveedor getProveedorBuscar() {
		return proveedorBuscar;
	}

	/**
	 * @param proveedorBuscar the proveedorBuscar to set
	 */
	public void setProveedorBuscar(Proveedor proveedorBuscar) {
		this.proveedorBuscar = proveedorBuscar;
	}

	/**
	 * @return the listaProveedor
	 */
	public List<Proveedor> getListaProveedor() {
		return listaProveedor;
	}

	/**
	 * @param listaProveedor the listaProveedor to set
	 */
	public void setListaProveedor(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

}
