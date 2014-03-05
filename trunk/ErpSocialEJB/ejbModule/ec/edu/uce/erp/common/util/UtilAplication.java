/**
 * 
 */
package ec.edu.uce.erp.common.util;

import static ec.edu.uce.erp.common.util.ConstantesApplication.PORCENTAJE;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author
 * 
 */
public final class UtilAplication {

	private UtilAplication() {
	}

	public static Timestamp obtenerFechaActual() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static String fechaActualConFormato (String pattern) {
		return new SimpleDateFormat(pattern).format(obtenerFechaActual());
	}

	/**
	 * Concatenar <code>StringBuilder</code>
	 * @param values
	 * @return
	 */
	public static StringBuilder appendStringBuilder(Object... values) {
		
		StringBuilder builder = new StringBuilder();
		
		for (Object value : values) {
			if (value == null) {
				builder.append(String.valueOf(value));
			}
			builder.append(value);
		}
		return builder;
	}
	
	/**
	 * M&eacute;todo para concatener el simbolo % a un parametro
	 * @param parametro
	 * @return <code>parametro</code> con el formato <code>%parametro%</code>
	 */
	public static String concatenarPorcenteje(String parametro){
		return appendStringBuilder(PORCENTAJE, parametro, PORCENTAJE).toString();
	}
	
	/**
	 * Sumar dias a una fecha en formato Timestamp
	 * @param timestamp
	 * @param numDias
	 * @return
	 */
	public static Timestamp sumarDiasTimestamp(Timestamp timestamp, Long numeroDias) {
		// 24 * 3600 * 1000
		Long total = Long.valueOf(timestamp.getTime()) + numeroDias * 1000L * 60L * 30L * 24L;
		return new Timestamp(total);
	}

}
