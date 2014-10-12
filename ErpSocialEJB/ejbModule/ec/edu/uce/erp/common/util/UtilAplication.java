/**
 * 
 */
package ec.edu.uce.erp.common.util;

import static ec.edu.uce.erp.common.util.ConstantesApplication.PORCENTAJE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	
	/**
	 * Agregar a una fecha las horas y minutos
	 * @param fecha
	 * @param hora
	 * @param minutos
	 * @return
	 */
	public static Date concatenarFecha(Date fecha, int hora, int minutos, int segundos){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, minutos);
		calendar.set(Calendar.SECOND, segundos);
		Date fechaConcatenada = calendar.getTime();
		
		return fechaConcatenada;
	}

	
	public static String saveToDisk(byte[] bytefile,String relativePath)
	{
		String pathDir,pathFile = null;
		File f;
		try {
			//date=String.valueOf(CalendarUtil.getYear())+"\\"+String.valueOf(CalendarUtil.getMonth())+"\\"+String.valueOf(CalendarUtil.getDay());
			
			pathDir="C:\\JavaEE\\jboss-as-7.1.1.Final\\standalone\\deployments\\ErpSocial.ear\\ErpSocialWeb.war\\images\\tmp\\upload\\";
			pathFile="C:\\JavaEE\\jboss-as-7.1.1.Final\\standalone\\deployments\\ErpSocial.ear\\ErpSocialWeb.war"+relativePath;
			
			f= new File(pathDir);
			if(!f.exists())
				f.mkdirs();
			
			f=new File(pathFile);
			if(!f.exists())
			{
				FileOutputStream fos=new FileOutputStream(pathFile);
				fos.write(bytefile);
				fos.close();
				pathFile=pathFile.replace('\\', '/');
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathFile;
	}

	
	public long getDifferntDateLong(long fecha1,long fecha2)
	{
		return fecha1-fecha2;
	}
	
	public static Boolean isDouble(Object number)
	{
		Boolean flag=Boolean.FALSE;
		try
		{
		  Double.parseDouble(String.valueOf(number));
		  flag=Boolean.TRUE;
		}
		catch(NumberFormatException e)
		{
			flag=Boolean.FALSE;
		}
		return flag;
	}

	
}
