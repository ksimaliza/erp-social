/**
 * 
 */
package ec.edu.uce.erp.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

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
	
	/**
	 * Buscar un objeto en una <code>Collection</code> por un atributo cualquiera
	 * @param listaObjetos colecci&oacute;n donde se va ha buscar el objeto
	 * @param atributo campo por el que se va buscar en la colecci&oacute;n
	 * @param parametro valor con el que se va ha comparar los objetos de la colecci&oacute;n
	 * @return <code>Object</code> si se encuentra una coincidencia
	 */
	public Object buscarObjetoLista(List<?> listaObjetos, final String atributo, final String parametro) {
		
		Object objetoEncontrado = CollectionUtils.find(listaObjetos, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Object obj = invocarGet(arg0, atributo);
				return obj.toString().equals(parametro);
			}
		});
		
		return objetoEncontrado;
	}

}
