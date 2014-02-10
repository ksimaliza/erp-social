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
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "moduloDataManager")
public class ModuloDataManager implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ModuloDataManager.class);
	
	private Modulo moduloInstancia;
	private Modulo moduloEditar;
	private Modulo moduloBuscar;
	private List<Modulo> moduloCol;
	
	public ModuloDataManager () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		this.moduloInstancia = new Modulo();
		this.moduloInstancia.setEmpresaTbls(new ArrayList<Empresa>());
		this.moduloEditar = new Modulo();
		this.moduloInstancia.setEmpresaTbls(new ArrayList<Empresa>());
		this.moduloBuscar = new Modulo();
		this.moduloInstancia.setEmpresaTbls(new ArrayList<Empresa>());
		this.moduloCol = new ArrayList<Modulo>();
	}

	/**
	 * @return the moduloInstancia
	 */
	public Modulo getModuloInstancia() {
		return moduloInstancia;
	}

	/**
	 * @param moduloInstancia the moduloInstancia to set
	 */
	public void setModuloInstancia(Modulo moduloInstancia) {
		this.moduloInstancia = moduloInstancia;
	}

	/**
	 * @return the moduloEditar
	 */
	public Modulo getModuloEditar() {
		return moduloEditar;
	}

	/**
	 * @param moduloEditar the moduloEditar to set
	 */
	public void setModuloEditar(Modulo moduloEditar) {
		this.moduloEditar = moduloEditar;
	}

	/**
	 * @return the moduloCol
	 */
	public List<Modulo> getModuloCol() {
		return moduloCol;
	}

	/**
	 * @param moduloCol the moduloCol to set
	 */
	public void setModuloCol(List<Modulo> moduloCol) {
		this.moduloCol = moduloCol;
	}

	/**
	 * @return the moduloBuscar
	 */
	public Modulo getModuloBuscar() {
		return moduloBuscar;
	}

	/**
	 * @param moduloBuscar the moduloBuscar to set
	 */
	public void setModuloBuscar(Modulo moduloBuscar) {
		this.moduloBuscar = moduloBuscar;
	}
	
}
