/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.util.dto.CredencialesDTO;

/**
 * @author 
 *
 */
public class LoginVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private CredencialesDTO credencialesDTO;
	private Usuario usuario;
	private List<Modulo> colModuloUsuario;
	private String ipLoginUser;
	
	public LoginVO () {
		this.credencialesDTO = new CredencialesDTO();
		this.usuario = new Usuario();
		this.colModuloUsuario = new ArrayList<Modulo>();
	}

	/**
	 * @return the credencialesDTO
	 */
	public CredencialesDTO getCredencialesDTO() {
		return credencialesDTO;
	}

	/**
	 * @param credencialesDTO the credencialesDTO to set
	 */
	public void setCredencialesDTO(CredencialesDTO credencialesDTO) {
		this.credencialesDTO = credencialesDTO;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the colModuloUsuario
	 */
	public List<Modulo> getColModuloUsuario() {
		return colModuloUsuario;
	}

	/**
	 * @param colModuloUsuario the colModuloUsuario to set
	 */
	public void setColModuloUsuario(List<Modulo> colModuloUsuario) {
		this.colModuloUsuario = colModuloUsuario;
	}

	/**
	 * @return the ipLoginUser
	 */
	public String getIpLoginUser() {
		return ipLoginUser;
	}

	/**
	 * @param ipLoginUser the ipLoginUser to set
	 */
	public void setIpLoginUser(String ipLoginUser) {
		this.ipLoginUser = ipLoginUser;
	}

	
	
}
