package ec.edu.uce.erp.web.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.MessagesApplicacion;
import ec.edu.uce.erp.common.util.SeguridadesException;

public final class JsfUtil {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(JsfUtil.class);

	
	public static String saveToDiskUpdload(byte[] bytefile,String fileName)
	{
		String pathDir,pathFile = null;
		String[] split;
		File f;
		try {
			split= fileName.split("\\.");
			fileName=JsfUtil.getRandomName(split[split.length-1]);
			
//			pathDir=getRealPath()+"\\images\\tmp\\upload\\";
			
			String pathRetalivaImagenes=File.separator+"images"+File.separator+"tmp"+File.separator+"upload"+File.separator;
			
			pathDir=getRealPath()+pathRetalivaImagenes;
			
			slf4jLogger.info("pathDir: "+pathDir);
			
			pathFile=pathDir+File.separator+fileName;
			
			f= new File(pathFile);
			if(f.exists())
				f.delete();
			
			f= new File(pathDir);
			if(!f.exists())
				f.mkdirs();
			FileOutputStream fos=new FileOutputStream(pathFile);
			fos.write(bytefile);
			fos.close();
			if(fileName.split("\\.")[1].equals("pdf")){
//				pathFile="\\images\\tmp\\upload\\"+fileName;
				pathFile=pathRetalivaImagenes+fileName;
			} else{
//				pathFile="\\images\\tmp\\upload\\"+fileName;
				pathFile=pathRetalivaImagenes+fileName;
			}
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
	
	public static String getRandomName(String extension)
	{
		return UUID.randomUUID().toString()+"."+extension;	
	}
	
	public static String getRealPath()
	{
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		String deploymentDirectoryPath = ctx.getRealPath("/");
		return deploymentDirectoryPath;
	}
	
	public static String descargarArchivo (String nombreArchivo, byte[] bytes) throws SeguridadesException {
		
		ServletContext servletContext = 
				(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String path = new StringBuilder().append(servletContext.getRealPath("")).append(nombreArchivo).toString();
		
		try {
			
			File imgDir = new File(new StringBuilder()
					.append(servletContext.getRealPath("")).append(MessagesApplicacion.getString("erp.ruta.relativa.imagenes")).toString());
			
			if (!imgDir.exists()) {
				imgDir.mkdirs();
			}
			
			File file = new File(path);
			if(!file.exists()) {
				InputStream in = new ByteArrayInputStream(bytes);
				FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
				
				int c = 0;
				while ((c = in.read()) >= 0) {
					out.write(c);
				}
				out.flush();
				out.close();
				
			}
			
			return file.getPath();
			
		} catch (FileNotFoundException e) {
			slf4jLogger.error("No se encontro el archivo expecificado {}", e.toString());
			throw new SeguridadesException("No se encontro el archivo expecificado", e);
		} catch (IOException e) {
			slf4jLogger.error("Error al descargar el archivo desde la BD {}", e.toString());
			throw new SeguridadesException("Error al descargar el archivo desde la BD", e);
		} catch (Exception e) { 
			slf4jLogger.error("Error al descargar el archivo desde la BD {}", e.toString());
			throw new SeguridadesException("Error al descargar el archivo desde la BD", e);
		}
	}
}
