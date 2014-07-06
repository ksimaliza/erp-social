package ec.edu.uce.erp.web.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

/**
 * Validar si el usuario seleccion almenos un menu
 * 
 * @author
 * 
 */
@FacesValidator(value="ec.edu.uce.erp.web.validador.MenuUsuarioValidator")
public class MenuUsuarioValidator implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		// ConstantesApp.LongitudCedula
		final Integer legthCedula = 10;
		final Integer num_provincias = 24;
			
		String cedula = String.valueOf(value);
		
		try {
			
			if (StringUtils.isNotBlank(cedula)) {
				
				//verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
				int prov = Integer.parseInt(cedula.substring(0, 2));
				
				if (!((prov > 0) && (prov <= num_provincias))) {
					lanzarExcepcion("Cedula incorrecta.");
				}
				
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
							lanzarExcepcion("Cedula incorrecta.");
						}
					} else {
						lanzarExcepcion("Cedula incorrecta.");
					}
				} else {
					lanzarExcepcion("Cedula con longitud incorrecta.");
				}
				
			}
		} catch (NumberFormatException e) {
			lanzarExcepcion("Cedula con formato incorrecto.");
		}
	}
	
	private void lanzarExcepcion (String mensaje) {
		FacesMessage msg = new FacesMessage(mensaje, mensaje);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(msg);
	}
}
