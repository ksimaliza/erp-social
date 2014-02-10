/**
 * 
 */
package ec.edu.uce.erp.web.common.controladores;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ec.edu.uce.erp.web.common.util.MessageProvider;

/**
 * @author 
 *
 */
public final class MensajesWebController extends BaseController{
	
	private static final long serialVersionUID = 1L;

	/**
	 * A&ntilde;ade un mensaje de error a la jeraquia de componetes de la p&aacute;gina JSF
	 * @param mensaje
	 */
	public static void aniadirMensajeError(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageProvider.getInstancia().getValue(mensaje), null));
	}
	
	/**
	 * A&ntilde;ade un mensaje de informaci&iacute;n a la jeraquia de componetes de la p&aacute;gina JSF
	 * @param mensaje
	 */
	public static void aniadirMensajeInformacion(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, MessageProvider.getInstancia().getValue(mensaje), null));
	}
	
	/**
	 * A&ntilde;ade un mensaje de advertencia a la jeraquia de componetes de la p&aacute;gina JSF
	 * @param mensaje
	 */
	public static void aniadirMensajeAdvertencia(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, MessageProvider.getInstancia().getValue(mensaje), null));
	}

}
