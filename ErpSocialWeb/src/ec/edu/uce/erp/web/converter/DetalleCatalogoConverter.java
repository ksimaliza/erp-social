/**
 * 
 */
package ec.edu.uce.erp.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;

/**
 * @author
 * 
 */
@FacesConverter(value = "ec.edu.uce.erp.web.converter.detalleCatalogoConverter")
public class DetalleCatalogoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			DetalleCatalogo m = (DetalleCatalogo) value;
			return m.getDetCatalogoDescripcion();
		}
		return null;
	}

}
