package ec.edu.uce.erp.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtil {

	
	public static Timestamp stringToTimestamp(String stringDate){
		Timestamp timestamp = null;
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = dateFormat.parse(stringDate);
		    timestamp = new Timestamp(parsedDate.getTime());
		}catch(Exception e){//this generic but you csan control another types of exception
			e.printStackTrace();
		}
		return timestamp;
	}
	
	public static String addSecond(String time,int segundo)
	{
		String newTime = null;
		 SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		 Date d;
		try {
			d = df.parse(time);
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(d);
			 cal.add(Calendar.SECOND, segundo);
			 newTime = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		 return newTime;
	}

	public static String addMinute(String time,int minuto)
	{
		String newTime = null;
		 SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		 Date d;
		try {
			d = df.parse(time);
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(d);
			 cal.add(Calendar.MINUTE, minuto);
			 newTime = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		 return newTime;
	}

	
	
	public static Date convertTimetoDate(String time)
	{
		SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
		java.util.Date d = null;
		try {
			d = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static int getYear(Timestamp da) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static int getMonth(Timestamp da) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}

	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month+1;
	}

	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	public static Calendar getCalendar(int year, int month, int day) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal;
	}

	public static Calendar getDate(Timestamp time) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		return cal;
	}

	public static Timestamp getTimeNowTimestamp() {
		Calendar calendar = Calendar.getInstance();
		return new Timestamp(calendar.getTimeInMillis());
	}
}
