/**
 * 
 */
package ec.edu.uce.erp.common.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author 
 *
 */
public final class UtilReflection {
	
	public static String invocarGet (Object object, String propertyName) {
		
		try {
			return BeanUtils.getProperty(object, propertyName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			System.out.println("Error: " + e.toString());
		}
		
		return null;
	}

}
