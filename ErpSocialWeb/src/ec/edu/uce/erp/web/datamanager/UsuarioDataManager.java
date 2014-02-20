/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "usuarioDataManager")
public class UsuarioDataManager extends BaseDataManager {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioInstancia;
	private Usuario usuarioBuscar;
	private Usuario usuarioEditar;
	
	private List<Usuario> listaUsuario;
	
	public UsuarioDataManager () {
		super();
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		
		this.usuarioInstancia = new Usuario();
		this.usuarioInstancia.setEmpresaTbl(new Empresa());
		this.usuarioInstancia.setSegtPerfil(new Perfil());
		this.usuarioInstancia.setUsuarioRegistro(getUsuarioSession());
		
		this.usuarioBuscar = new Usuario();
		this.usuarioBuscar.setEmpresaTbl(new Empresa());
		this.usuarioBuscar.setSegtPerfil(new Perfil());
		
		this.usuarioEditar = new Usuario();
		this.usuarioEditar.setEmpresaTbl(new Empresa());
		this.usuarioEditar.setSegtPerfil(new Perfil());
		this.usuarioEditar.setUsuarioRegistro(getUsuarioSession());
		
		this.listaUsuario = new ArrayList<Usuario>();
	}

	/**
	 * @return the usuarioInstancia
	 */
	public Usuario getUsuarioInstancia() {
		return usuarioInstancia;
	}

	/**
	 * @param usuarioInstancia the usuarioInstancia to set
	 */
	public void setUsuarioInstancia(Usuario usuarioInstancia) {
		this.usuarioInstancia = usuarioInstancia;
	}

	/**
	 * @return the usuarioBuscar
	 */
	public Usuario getUsuarioBuscar() {
		return usuarioBuscar;
	}

	/**
	 * @param usuarioBuscar the usuarioBuscar to set
	 */
	public void setUsuarioBuscar(Usuario usuarioBuscar) {
		this.usuarioBuscar = usuarioBuscar;
	}

	/**
	 * @return the usuarioEditar
	 */
	public Usuario getUsuarioEditar() {
		return usuarioEditar;
	}

	/**
	 * @param usuarioEditar the usuarioEditar to set
	 */
	public void setUsuarioEditar(Usuario usuarioEditar) {
		this.usuarioEditar = usuarioEditar;
	}

	/**
	 * @return the listaUsuario
	 */
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	/**
	 * @param listaUsuario the listaUsuario to set
	 */
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
}
