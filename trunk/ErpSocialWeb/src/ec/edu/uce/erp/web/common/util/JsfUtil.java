package ec.edu.uce.erp.web.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class JsfUtil {

	
	public static String saveToDiskUpdload(byte[] bytefile,String fileName)
	{
		String pathDir,pathFile = null;
		File f;
		try {
			//date=String.valueOf(CalendarUtil.getYear())+"\\"+String.valueOf(CalendarUtil.getMonth())+"\\"+String.valueOf(CalendarUtil.getDay());
			
			pathDir=MessageProvider.getInstancia().getValue("com.corvustec.redxxi.path.web")+"\\images\\tmp\\upload\\";
			pathFile=pathDir+"\\"+fileName;
			
			f= new File(pathFile);
			if(f.exists())
				f.delete();
			
			f= new File(pathDir);
			if(!f.exists())
				f.mkdirs();
			FileOutputStream fos=new FileOutputStream(pathFile);
			fos.write(bytefile);
			fos.close();
			if(fileName.split("\\.")[1].equals("pdf"))
				pathFile=getContextPath()+"\\images\\tmp\\upload\\"+fileName;
			else
				pathFile="\\images\\tmp\\upload\\"+fileName;
			pathFile=pathFile.replace('\\', '/');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathFile;
	}
	
	public static String getContextPath()
	{
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		return ctx.getContextPath();
	}
	
	
	
}
