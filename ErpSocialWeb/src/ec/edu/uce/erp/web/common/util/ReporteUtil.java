/**
 * 
 */
package ec.edu.uce.erp.web.common.util;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.UtilAplication;

/**
 * @author
 *
 */
public class ReporteUtil {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteUtil.class);
	
	/**
	 * M&eacute;todo gen&eacute;rico para generar un reporte
	 * @param jp nombre del archivo jasper report
	 * @param tipoReporte pdf o excel
	 * @param nombreReporte el nombre con el que se quiere generar el reporte
	 */
	public static void generarReporte(JasperPrint jp, String tipoReporte, String nombreReporte){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		OutputStream outputStream = null; 
		
		try {
			JRExporter jRExporter = null;
			HttpServletResponse httpServletResponse = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			httpServletResponse.setContentType(tipoReporte);
			outputStream = httpServletResponse.getOutputStream();
			
			if("application/vnd.ms-excel".equals(tipoReporte)){
				
				jRExporter = new JRXlsExporter();
				jRExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
				jRExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
				jRExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				jRExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				jRExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				jRExporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
				jRExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
				jRExporter.setParameter(JRXlsExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED, Boolean.FALSE);
				jRExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
				jRExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				httpServletResponse
						.setHeader(
								"Content-disposition", UtilAplication
										.appendStringBuilder("attachment; filename=", nombreReporte, 
												UtilAplication.fechaActualConFormato("yyyy-MM-dd"),".xls").toString());
				
			}else{ //pdf
				
				jRExporter = new JRPdfExporter();
				jRExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
				jRExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
				
				httpServletResponse.setHeader(
						"Content-disposition",
						UtilAplication.appendStringBuilder("attachment; filename=", nombreReporte,
								UtilAplication.fechaActualConFormato("yyyy-MM-dd"), ".pdf").toString());
			}
			
			if (jRExporter != null){
				jRExporter.exportReport();
			}
				
			facesContext.responseComplete();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al exportar el reporte: {}", e.toString());
		}
	}

	/**
	 * M&eacute;todo gen&eacute;rico para compilar el reporte con la data que se obtiene de la aplicaci&oacute;n
	 * @param facesContext
	 * @param listaObjeto
	 * @param nombreArchivo Nombre del archivo .jrxml que se va ha compilar
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JasperPrint jasperPrint (FacesContext facesContext,List listaObjeto,String nombreArchivo){
		
		JasperPrint jp = null;
		try {
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaObjeto);
			String reportePath = facesContext.getExternalContext().getRealPath("/paginas/reportes/"+nombreArchivo+".jrxml");
			JasperReport jr = JasperCompileManager.compileReport(reportePath);
			jp = JasperFillManager.fillReport(jr,new HashMap(),beanCollectionDataSource);
			String pdfPath = facesContext.getExternalContext().getRealPath("/paginas/reportes/"+nombreArchivo+".pdf");
			JasperExportManager.exportReportToPdfFile(jp, pdfPath);
		} catch (Exception e) {
			slf4jLogger.info("Error al compilar el reporte: {}", e.toString());
		}
		
		return jp;
	}
	
}
