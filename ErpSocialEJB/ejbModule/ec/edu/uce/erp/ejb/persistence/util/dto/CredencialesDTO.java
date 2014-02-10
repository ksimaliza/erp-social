/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.io.Serializable;

/**
 * DTO para trasportar las credenciales del usuario a trav&eacute;s de las diferentes capas
 * @author
 *
 */
public class CredencialesDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String usuario;
	private String password;
	private String passwordTemp;
	private String passwordTempConfirmar;
	
	public CredencialesDTO(){}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordTemp
	 */
	public String getPasswordTemp() {
		return passwordTemp;
	}

	/**
	 * @param passwordTemp the passwordTemp to set
	 */
	public void setPasswordTemp(String passwordTemp) {
		this.passwordTemp = passwordTemp;
	}

	/**
	 * @return the passwordTempConfirmar
	 */
	public String getPasswordTempConfirmar() {
		return passwordTempConfirmar;
	}

	/**
	 * @param passwordTempConfirmar the passwordTempConfirmar to set
	 */
	public void setPasswordTempConfirmar(String passwordTempConfirmar) {
		this.passwordTempConfirmar = passwordTempConfirmar;
	}
	
}
