/**
 * 
 */
package ec.edu.uce.erp.web.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

/**
 * Validar si el RUC ingresado es correcto
 * @author 
 *
 */
@FacesValidator(value="ec.edu.uce.erp.web.validador.RucValidator")
public class RucValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String ruc = String.valueOf(value);
		final Integer num_provincias = 24;
		
		
		try {
		
			if (StringUtils.isNotBlank(ruc)) {
				
				//verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
				int prov = Integer.parseInt(ruc.substring(0, 2));
				if (!((prov > 0) && (prov <= num_provincias))) {
					lanzarExcepcion("Ruc incorrecto.");
				}
				
				//verifica que el ruc finalice en 001
//				if (!ruc.substring(10, 13).equals("001")) {
//					lanzarExcepcion("Los tres ultimos digitos no tienen el codigo del RUC 001.");
//				}
				
				int tercerDigito = Integer.parseInt(ruc.substring(2, 3));
				
				if(tercerDigito<6){
					
//					alert('El tercer dígito es menor a 6, por lo \ntanto el usuario es una persona natural.\n');
					
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(ruc.substring(9, 10));
					int suma = 0;
					int digito = 0;
					ruc = ruc.substring(0, 10);
					
					for (int i = 0; i < (ruc.length() - 1); i++) {
						digito = Integer.parseInt(ruc.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}
					
					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						// ruc correcto
						return;
					} else if ((10 - (suma % 10)) == verificador) {
						// ruc correcto
						return;
					} else {
						lanzarExcepcion("Ruc incorrecto.");
					}
					
				} else if (tercerDigito==6) {
//					alert('El tercer dígito es igual a 6, por lo \ntanto el usuario es una entidad pública.\n');
					
					Integer v1,v2,v3,v4,v5,v6,v7,v8,v9;
					Integer sumatoria;
					Integer modulo;
					Integer digito;
					Integer sustraendo;
					int[] d = new int[ruc.length()];
					
					for (int i = 0; i < d.length; i++) {
						d[i] = Integer.parseInt(ruc.charAt(i) + "");
					}
					
					v1 = d[0]* 3;
					v2 = d[1]* 2;
					v3 = d[2]* 7;
					v4 = d[3]* 6;
					v5 = d[4]* 5;
					v6 = d[5]* 4;
					v7 = d[6]* 3;
					v8 = d[7]* 2;
					v9 = d[8];
							
					sumatoria = v1+v2+v3+v4+v5+v6+v7+v8;
					modulo = sumatoria % 11;
					sustraendo = modulo * 11;
					digito = 11-(sumatoria - sustraendo);
//					System.out.println("Digito RUC       --> "+digito);
//					System.out.println("Digito Calculado --> "+v9);
					
					if(digito == v9){
						return;
					}else{
						lanzarExcepcion("Ruc incorrecto.");
					}
					
				} else if (tercerDigito==9) {
//					alert('El tercer dígito es igual a 9, por lo \ntanto el usuario es una sociedad privada.\n');
					
					final int[] coeficientes = {4,3,2,7,6,5,4,3,2};
					final int constante = 11;
					
					//verifica que el último dígito de la cédula sea válido
					int[] d = new int[10];
					int suma = 0;
					
//					Asignamos el string a un array
					for (int i = 0; i < d.length; i++) {
						d[i] = Integer.parseInt(ruc.charAt(i) + "");
					}
					
					for (int i=0; i< d.length - 1; i++) {
						d[i] = d[i] * coeficientes[i];
						suma += d[i];
						//System.out.println("Vector d en " + i + " es " + d[i]);
					}
					
//					System.out.println("Suma es: " + suma);
					int aux, resp;
					aux = suma % constante;
					resp = constante - aux;
					resp = (resp == 10) ? 0 : resp;
//					System.out.println("Aux: " + aux);
//					System.out.println("Resp " + resp);
//					System.out.println("d[9] " + d[9]);
					
					if (resp == d[9]) {
						return;
					} else {
						lanzarExcepcion("Ruc incorrecto.");
					}
					
				} else {
					lanzarExcepcion("Ruc incorrecto.");
				}
			}
		
		} catch (NumberFormatException e) {
			lanzarExcepcion("Ruc con formato incorrecto.");
		}
		
	}
	
	private void lanzarExcepcion (String mensaje) {
		FacesMessage msg = new FacesMessage(mensaje, mensaje);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(msg);
	}

}
