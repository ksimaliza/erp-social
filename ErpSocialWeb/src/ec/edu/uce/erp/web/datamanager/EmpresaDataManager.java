/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Empresa;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean(name="empresaDataManager")
public class EmpresaDataManager implements Serializable{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDataManager.class);
	
	private static final long serialVersionUID = 1L;
	private Empresa empresaInstancia;
	private Empresa empresaEditar;
	private Empresa empresaBuscar;
	
	private List<Empresa> listaEmpresa;
	
	public EmpresaDataManager () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		
		this.empresaInstancia = new Empresa();
		this.empresaEditar = new Empresa();
		this.empresaBuscar = new Empresa();
		this.listaEmpresa = new ArrayList<Empresa>();
	}

	/**
	 * @return the empresaInstancia
	 */
	public Empresa getEmpresaInstancia() {
		return empresaInstancia;
	}

	/**
	 * @param empresaInstancia the empresaInstancia to set
	 */
	public void setEmpresaInstancia(Empresa empresaInstancia) {
		this.empresaInstancia = empresaInstancia;
	}

	/**
	 * @return the empresaEditar
	 */
	public Empresa getEmpresaEditar() {
		return empresaEditar;
	}

	/**
	 * @param empresaEditar the empresaEditar to set
	 */
	public void setEmpresaEditar(Empresa empresaEditar) {
		this.empresaEditar = empresaEditar;
	}

	/**
	 * @return the empresaBuscar
	 */
	public Empresa getEmpresaBuscar() {
		return empresaBuscar;
	}

	/**
	 * @param empresaBuscar the empresaBuscar to set
	 */
	public void setEmpresaBuscar(Empresa empresaBuscar) {
		this.empresaBuscar = empresaBuscar;
	}

	/**
	 * @return the listaEmpresa
	 */
	public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

	/**
	 * @param listaEmpresa the listaEmpresa to set
	 */
	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	
}
