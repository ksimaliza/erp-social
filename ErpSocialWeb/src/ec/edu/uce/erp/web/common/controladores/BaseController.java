/**
 * 
 */
package ec.edu.uce.erp.web.common.controladores;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import ec.edu.uce.erp.common.util.ConstantesReporte;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.vo.LoginVO;
import ec.edu.uce.erp.web.common.util.CustomPDFExporter;
/**
 * @author 
 *
 */
public abstract class BaseController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * M&eacute;todo para actualizar la informaci&oacute;n del formulario
	 */
	public abstract void refrescarFormulario ();
	
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
	
	protected ServletContext getServletContext() {
		return (ServletContext)getExternalContext().getContext();
	}
	
	/**
	 * Retorna la ip del usuario que inicio session 
	 * @return
	 */
	protected String getIpLoginUser() {
		HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
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
	
	/**
	 * Generar archivo PDF con datos de tabla de primefaces
	 * @param document
	 * @throws IOException
	 * @throws BadElementException
	 * @throws DocumentException
	 */
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String logo = new StringBuilder()
				.append(servletContext.getRealPath("")).append(File.separator)
				.append("resources").append(File.separator).append("img")
				.append(File.separator).append("uce_logo.png").toString();

		pdf.add(Image.getInstance(logo));
		
	}
	
	public void exportPDF(String tableId, Map<String, String> mapValuesPDF) throws IOException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		UIComponent component = findComponent(tableId);
		if(!(component instanceof DataTable)) {
			throw new FacesException("Unsupported datasource target:\"" + component.getClass().getName() + "\", exporter must target a PrimeFaces DataTable.");
		}
		DataTable table = (DataTable) component;
		
		Exporter exporter = new CustomPDFExporter(mapValuesPDF);
		exporter.export(context, table, mapValuesPDF.get(ConstantesReporte.NOMBRE_ARCHIVO), false, false, "iso-8859-1", null, null);
		context.responseComplete();
	}
	
	public UIComponent findComponent(String id) {

		UIComponent result = null;
		UIComponent root = FacesContext.getCurrentInstance().getViewRoot();
		if (root != null) {
			result = findComponent(root, id);
		}
		return result;

	}

	private UIComponent findComponent(UIComponent root, String id) {

		UIComponent result = null;
		if (root.getId().equals(id))
			return root;

		for (UIComponent child : root.getChildren()) {
			if (child.getId().equals(id)) {
				result = child;
				break;
			}
			result = findComponent(child, id);
			if (result != null)
				break;
		}
		return result;
	}

	protected int getEmpresaCode(){
		return ((LoginVO)getSessionParameter("loginVO")).getUsuario().getEmpresaTbl().getEmrPk();
	}

	protected Empresa getEmpresaTbl(){
		return ((LoginVO)getSessionParameter("loginVO")).getUsuario().getEmpresaTbl();
	}
	
	protected int getPersonaCode(){
		if(((LoginVO)getSessionParameter("loginVO")).getUsuario().getPersonaTbls().size()>0)
			return ((LoginVO)getSessionParameter("loginVO")).getUsuario().getPersonaTbls().get(0).getPerPk();
		else
			return 0;
	}
	
	


}
