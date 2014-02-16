package ec.edu.uce.erp.web.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

/**
 * Validar por la cedula de identidad
 * 
 * @author
 * 
 */
@FacesValidator(value="ec.edu.uce.erp.web.validador.CedulaValidator")
public class CedulaValidator implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		// ConstantesApp.LongitudCedula
		final Integer legthCedula = 10;
			
		String cedula = String.valueOf(value);
		
		try {
			
			if (StringUtils.isNotBlank(cedula)) {
				
				// para lanzar la exepcion si se ingreso letras
				Integer.parseInt(cedula);
				
				if (cedula.length() == legthCedula) {
					
					int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
					
					if (tercerDigito < 6) {
						
						// Coeficientes de validacion cedula
						// El decimo digito se lo considera digito verificador
						int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
						int verificador = Integer.parseInt(cedula.substring(9, 10));
						int suma = 0;
						int digito = 0;
						
						for (int i = 0; i < (cedula.length() - 1); i++) {
							digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
							suma += ((digito % 10) + (digito / 10));
						}
						
						if ((suma % 10 == 0) && (suma % 10 == verificador)) {
							// cedula correcta
							return;
						} else if ((10 - (suma % 10)) == verificador) {
							// cedula correcta
							return;
						} else {
							FacesMessage msg = new FacesMessage("Cedula incorrecta.", "Cedula incorrecta.");
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							throw new ValidatorException(msg);
						}
					} else {
						FacesMessage msg = new FacesMessage("Cedula incorrecta.", "Cedula incorrecta.");
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						throw new ValidatorException(msg);
					}
				} else {
					
					FacesMessage msg = new FacesMessage("Cedula con longitud incorrecta.", "Cedula con longitud incorrecta.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);
					
				}
				
			}
		} catch (NumberFormatException e) {
			FacesMessage msg = new FacesMessage("Cedula con formato incorrecto.", "Cedula con formato incorrecto.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
