/**
 * 
 */
package ec.edu.uce.erp.web.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

/**
 * @author
 *
 */
@FacesValidator(value="ec.edu.uce.erp.web.validador.EmailValidator")
public class EmailValidator implements Validator{
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
 
	private Pattern pattern;
	private Matcher matcher;
 
	public EmailValidator(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if (StringUtils.isNotBlank(value.toString())) {
			matcher = pattern.matcher(value.toString());
			if(!matcher.matches()){
				
				FacesMessage msg = new FacesMessage("E-mail validation failed.", "Invalid E-mail format.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
				
			}
		}
	}

}
