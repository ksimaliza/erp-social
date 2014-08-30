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
//		testObtenerGet ();
		testCodigoBien ();
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
	
	public static void testCodigoBien () {
		
		String line = "empresa uno tres";
		
		
		String [] array = line.split(" ");
		StringBuilder salida = new StringBuilder();
		
		for (int a = 0; a<array.length; a++) {
			System.out.println("elemento: " + array[a]);
			salida.append(array[a].substring(0, 1).toUpperCase()).append(".");
		}
		
		System.out.println("salida: " + salida.toString());
	}

}
