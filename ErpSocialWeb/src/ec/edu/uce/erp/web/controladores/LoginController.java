/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.EncriptacionUtil;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.util.dto.CredencialesDTO;
import ec.edu.uce.erp.ejb.persistence.vo.LoginVO;
import ec.edu.uce.erp.ejb.servicio.ServicioLogin;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.LoginDataManager;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "loginController") 
public class LoginController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(LoginController.class);
	
	@EJB
	private ServicioLogin servicioLogin;
	
	@ManagedProperty(value="#{loginDataManager}")
	private LoginDataManager loginDataManager;
	
	/**
	 * @param loginDataManager the loginDataManager to set
	 */
	public void setLoginDataManager(LoginDataManager loginDataManager) {
		this.loginDataManager = loginDataManager;
	}
	
	public LoginController () {}
	
	/*
	 * Metodos
	 */
	
	/**
	 * M&eacute;todo para realizar el login de un usuario al sistema
	 * @return
	 */
	public String autenticarUsuario(){
		
		String salida = null;
		
		try {
			
			LoginVO loginVO = new LoginVO();
			loginVO.setCredencialesDTO(loginDataManager.getCredencialesDTO());
			
			loginVO = servicioLogin.autenticarUsuario(loginVO);
			
			if (loginVO == null || loginVO.getUsuario() == null) {
				
				loginDataManager.setCredencialesDTO(new CredencialesDTO());
				MensajesWebController.aniadirMensajeError("No se encontro al usuario");
				
			} else {
				
				if (loginVO.getUsuario().getNpDebeCambiarClave()) {
					MensajesWebController.aniadirMensajeInformacion("Debe cambiar su clave para continuar usando el sistema");
					salida = "cambiarClave";
				} else {
					salida = "homeUsuario";
				}
				getExternalContext().getSessionMap().put("usuario", loginVO.getUsuario());
				
				getExternalContext().getSessionMap().put("loginVO", loginVO);
				loginDataManager.setCredencialesDTO(new CredencialesDTO());
				loginVO = null;
			}
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
		return salida;
	}
	
	
	public String cambiarContrasenia () {
		
		String salida = null;
		
		try {
			
			LoginVO loginVO = (LoginVO)getSessionParameter("loginVO");
			
			if (validarContraseniaUsuario(this.loginDataManager.getCredencialesDTO(), loginVO.getUsuario())) {
				
				if (validarContraseniaIgual(this.loginDataManager.getCredencialesDTO())) {
					
					if (validarContraseniaNueva(this.loginDataManager.getCredencialesDTO())) {
						
						loginVO.getUsuario().setNpPasswordTemp(this.loginDataManager.getCredencialesDTO().getPasswordTemp());
						
						if (!contraseniaRepetida(loginVO.getUsuario())) {
							slf4jLogger.info("Se va actualizar la contrasenia del usuario");
							
							loginVO.getUsuario().setNpPasswordTemp(loginDataManager.getCredencialesDTO().getPassword());
							loginVO.getUsuario().setPassUsuario(loginDataManager.getCredencialesDTO().getPasswordTemp());
							
							Usuario usuarioLogin = servicioLogin.cambiarClaveUsuario(loginVO.getUsuario());
							
							if (usuarioLogin == null) {
								loginDataManager.setCredencialesDTO(new CredencialesDTO());
								MensajesWebController.aniadirMensajeError("Error al cambiar la clave del usuario");
							} else {
								loginDataManager.setCredencialesDTO(new CredencialesDTO());
								getExternalContext().getSessionMap().remove("usuario");
								getExternalContext().getSessionMap().put("usuario", usuarioLogin);
								usuarioLogin = null;
								MensajesWebController.aniadirMensajeInformacion("Clave cambiada con exito");
								salida = "retornarCambiarClave";
							}
						} else {
							MensajesWebController.aniadirMensajeError("La contrasenia ingresada ya ha sido utilizada antes");
						}
						
					} else {
						MensajesWebController.aniadirMensajeError("La clave nueva no coincide");
					}
					
				} else {
					MensajesWebController.aniadirMensajeError("La clave actual y la nueva no pueden ser iguales");
				}
				
			} else {
				MensajesWebController.aniadirMensajeError("La clave actual es incorrecta");
			}
			
		} catch (Exception e) {
			slf4jLogger.info("Error al cambiar la clave del usuario {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
		
		return salida;
	}
	
	/**
	 * verificar si la contrasenia nueva no ha sido utilizada antes
	 * @param segtUsuario
	 * @return <FALSE> si la clave no se ha usado
	 * @throws SeguridadesException
	 */
	private Boolean contraseniaRepetida(Usuario usuarioDTO) throws SeguridadesException{
		return servicioLogin.validarClaveUsuario(usuarioDTO);
	}
	
	/**
	 * M&eacute;todo para cerrar la sesion del usuario 
	 * @return
	 */
	public String cerrarSesion(){
		slf4jLogger.info("metodo cerrarSesion");
		try {
			getExternalContext().getSessionMap().clear();
			getExternalContext().getFlash().clear();
			((HttpServletRequest)getExternalContext().getRequest()).getSession(Boolean.FALSE).invalidate();
			getExternalContext().redirect(getExternalContext().getRequestContextPath());
			getFacesContext().responseComplete();
			
		} catch (IOException e) {
			throw new FacesException(e);
		}
		
		return "salir?faces-redirect=true";
		
	}
	
	/**
	 * Si la contrasenia actual corresponde a la del usuario que esta en session
	 * @param credencialesDTO
	 * @param usuarioSession
	 * @return
	 */
	private Boolean validarContraseniaUsuario(CredencialesDTO credencialesDTO, Usuario usuarioSession){
		
		String claveEncriptada = EncriptacionUtil.getInstancia().encriptar(credencialesDTO.getPassword());
		return claveEncriptada.equals(usuarioSession.getPassUsuario());
	}
	
	/**
	 * si la contrasenia actual y la nueva son iguales
	 * @param credencialesDTO
	 * @return <code>TRUE</code> si las contrasenias no son iguales
	 */
	private Boolean validarContraseniaIgual(CredencialesDTO credencialesDTO){
		return !credencialesDTO.getPassword().equals(credencialesDTO.getPasswordTemp());
	}
	
	/**
	 * Validar si la contrasenia nueva coincide
	 * @return
	 */
	private Boolean validarContraseniaNueva(CredencialesDTO credencialesDTO){
		return credencialesDTO.getPasswordTemp().equals(credencialesDTO.getPasswordTempConfirmar());
	}
	
	/*
	 * Propiedades
	 */

}
