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
	
	
	public static Date addMinute(Timestamp time,int minuto)
	{
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MINUTE, minuto);
        return cal.getTime();
	}
	
	
	public static String addMinute(String time,String format,int minuto)
	{
		String newTime = null;
		 SimpleDateFormat df = new SimpleDateFormat(format);
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
	
	public static Date addDay(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
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

	public static Date convertStringtoDate(String date)
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static Date convertStringtoDate(String date,String format)
	{
		SimpleDateFormat df=new SimpleDateFormat(format);
		java.util.Date d = null;
		try {
			d = df.parse(date);
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

	public static Integer getYear(Timestamp da) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		Integer year = cal.get(Calendar.YEAR);
		return year;
	}

	public static Integer getMonth(Timestamp da) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		Integer month = cal.get(Calendar.MONTH);
		return month + 1;
	}

	public static Integer getDay(Timestamp da) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		Integer day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static Integer getDayOfWeek(Timestamp da) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		Integer day = cal.get(Calendar.DAY_OF_WEEK);
		return day;
	}
	

	public static Integer getDayOfMonth(Timestamp da) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		Integer day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static Integer getDayOfYear(Timestamp da) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(da);
		Integer day = cal.get(Calendar.DAY_OF_YEAR);
		return day;
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
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		return cal;
	}

	public static Timestamp getTimeNowTimestamp() {
		Calendar calendar = Calendar.getInstance();
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	public static String extractTime(Timestamp timestamp)
	{
		Date date = new Date(timestamp.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	public static String extractTime(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	
	public static Timestamp addDay(Timestamp time,int day)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		cal.add(Calendar.DAY_OF_YEAR, day);
		return new Timestamp(cal.getTime().getTime());
	}

	public static Calendar getCalendar(int hour,int minute)
	{
		Calendar cal = Calendar.getInstance();       // get calendar instance
		cal.set(Calendar.HOUR_OF_DAY, hour);            // set hour to midnight
		cal.set(Calendar.MINUTE, minute);                 // set minute in hour
		cal.set(Calendar.SECOND, 0);                 // set second in minute
		cal.set(Calendar.MILLISECOND, 0); 
		return cal;		
	}

	
}
