package ec.edu.uce.erp.web.common.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Clase para leer los recursos desde un archivo de propiedades
 * @author 
 *
 */
public final class MessagesWebApplicacion {
	
	static Locale locale = new Locale("es", "ES");
	
	private static final String BUNDLE_NAME = "ec.edu.uce.erp.web.common.resources.WebApplicationResources";
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale);
	
	private MessagesWebApplicacion () {}
	
	public static String getString(String key) throws MissingResourceException{
		return RESOURCE_BUNDLE.getString(key);
	}
	
	public static Integer getInteger(String key) throws MissingResourceException{
		return Integer.valueOf(getString(key));
	}

}
