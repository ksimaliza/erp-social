/**
 * 
 */
package ec.edu.uce.erp.web.common.controladores;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/**
 * @author 
 *
 */
public abstract class BaseController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Recupera el valor asociado a un parametro de la p&aacute;gina JSF
	 * 
	 * @param parametro
	 * @return
	 */
	protected String recuperarParametro(String parametro) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		return map.get(parametro);
	}
	
	/**
	 * 
	 * @param parametro
	 * @return
	 */
	protected Object recuperarParametroObject(String parametro) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		return map.get(parametro);
	}
	
	/**
	 * Obtener un parametro se sesion
	 * @param parametro
	 * @return
	 */
	protected Object getSessionParameter(String parametro) {
		Map<String, Object> map = getExternalContext().getSessionMap();
		return map.get(parametro);
	}
	
	/**
	 * 
	 * @return
	 */
	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}
	
	/**
	 * 
	 * @return
	 */
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	/**
	 * Generar archivo excel con datos de tabla de primefaces
	 * @param document
	 */
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);

			cell.setCellStyle(cellStyle);
		}
	}
}
