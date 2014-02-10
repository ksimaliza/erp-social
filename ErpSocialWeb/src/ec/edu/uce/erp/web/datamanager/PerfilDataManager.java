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

import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "perfilDataManager")
public class PerfilDataManager implements Serializable{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private Perfil perfilInstancia;
	private Perfil perfilEditar;
	private Perfil perfilBuscar;
	
	private List<Perfil> listaPerfiles;
	
	public PerfilDataManager () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.perfilInstancia = new Perfil();
		this.perfilEditar = new Perfil();
		this.perfilBuscar = new Perfil();
		
		this.listaPerfiles = new ArrayList<Perfil>();
	}

	/**
	 * @return the perfilInstancia
	 */
	public Perfil getPerfilInstancia() {
		return perfilInstancia;
	}

	/**
	 * @param perfilInstancia the perfilInstancia to set
	 */
	public void setPerfilInstancia(Perfil perfilInstancia) {
		this.perfilInstancia = perfilInstancia;
	}

	/**
	 * @return the perfilEditar
	 */
	public Perfil getPerfilEditar() {
		return perfilEditar;
	}

	/**
	 * @param perfilEditar the perfilEditar to set
	 */
	public void setPerfilEditar(Perfil perfilEditar) {
		this.perfilEditar = perfilEditar;
	}

	/**
	 * @return the perfilBuscar
	 */
	public Perfil getPerfilBuscar() {
		return perfilBuscar;
	}

	/**
	 * @param perfilBuscar the perfilBuscar to set
	 */
	public void setPerfilBuscar(Perfil perfilBuscar) {
		this.perfilBuscar = perfilBuscar;
	}

	/**
	 * @return the listaPerfiles
	 */
	public List<Perfil> getListaPerfiles() {
		return listaPerfiles;
	}

	/**
	 * @param listaPerfiles the listaPerfiles to set
	 */
	public void setListaPerfiles(List<Perfil> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}

}
