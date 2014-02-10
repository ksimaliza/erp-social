/**
 * 
 */
package ec.edu.uce.erp.web.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author
 *
 */
public class MessageProvider {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MessageProvider.class);
	private static final MessageProvider INSTANCIA = new MessageProvider();
	private ResourceBundle resourceBundle;
	
	/**
	 * Constructor privado
	 */
	private MessageProvider(){}
	
	/**
	 * Devolvemos la instancia de la clase
	 * @return INSTANCIA
	 */
	public static MessageProvider getInstancia(){
		return INSTANCIA;
	}

	/**
	 * @return the resourceBundle
	 */
	private ResourceBundle getResourceBundle() {
		
		if(resourceBundle == null){
			FacesContext context = FacesContext.getCurrentInstance();
			resourceBundle = context.getApplication().getResourceBundle(context, "i18n");
		}
		return resourceBundle;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		
		String result = null;
		try {
			result = getResourceBundle().getString(key);
		} catch (MissingResourceException e) {
			slf4jLogger.info("No se encontro el recurso: {}, error: {}", key, e.toString());
		}
		if (result == null) {
			result = key;
		}
		
		return result;
	}

}
