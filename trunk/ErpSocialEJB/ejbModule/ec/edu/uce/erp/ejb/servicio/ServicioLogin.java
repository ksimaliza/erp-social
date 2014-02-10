/**
 * 
 */
package ec.edu.uce.erp.ejb.servicio;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.vo.LoginVO;

/**
 * @author 
 *
 */
@Local
public interface ServicioLogin {
	
	/*
	 * Metodos para el servicio de login del usuario
	 */
	
	/**
	 * Se busca un usuario en la BD para autenticar el acceso al sistema
	 * @param usuario
	 * @return
	 * @throws SeguridadesException
	 */
	LoginVO autenticarUsuario(LoginVO loginVO) throws SeguridadesException;
	
	/**
	 * M&eacute;todo para cambiar la contrasenia del usuario
	 * @param usuario
	 * @return
	 * @throws SeguridadesException
	 */
	Usuario cambiarClaveUsuario(Usuario usuario) throws SeguridadesException;
	
	/**
	 * Validar si el usuario ya a usado una clave
	 * @param usuario
	 * @return <code>TRUE</code> si no se ha usado la clave <code>FALSE</code> si la clave ya se ha usado
	 * @throws SeguridadesException
	 */
	Boolean validarClaveUsuario(Usuario usuario) throws SeguridadesException;
	
}
