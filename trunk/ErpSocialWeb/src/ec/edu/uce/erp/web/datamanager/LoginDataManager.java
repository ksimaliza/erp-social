/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.util.dto.CredencialesDTO;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "loginDataManager") 
public class LoginDataManager implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(LoginDataManager.class);
	
	private CredencialesDTO credencialesDTO;
	
	public LoginDataManager () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		this.credencialesDTO = new CredencialesDTO();
	}

	/**
	 * @return the credencialesDTO
	 */
	public CredencialesDTO getCredencialesDTO() {
		return credencialesDTO;
	}

	/**
	 * @param credencialesDTO the credencialesDTO to set
	 */
	public void setCredencialesDTO(CredencialesDTO credencialesDTO) {
		this.credencialesDTO = credencialesDTO;
	}
	

}
