/**
 * 
 */
package ec.edu.uce.erp.common.test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;

/**
 * @author 
 *
 */
public class TestUtil {
	
	
	public static void main (String ...args) {
		testObtenerGet ();
	}
	
	public static void testObtenerGet () {
		DetalleBien detalleBien = new DetalleBien();
		detalleBien.setDetBienEstado("ok");
		
		System.out.println(invocarGet(detalleBien, "detBienEstado"));
	}
	
	private static String invocarGet (Object object, String propertyName) {
		
		try {
			return BeanUtils.getProperty(object, propertyName);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			System.out.println("Error: " + e.toString());
		}
		
		return null;
	}

}
