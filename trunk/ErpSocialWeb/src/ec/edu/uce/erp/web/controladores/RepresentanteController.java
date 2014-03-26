package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.vo.RepresentanteVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.RepresentanteDataManager;

@ViewScoped
@ManagedBean (name = "representanteController")
public class RepresentanteController {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{representanteDataManager}")
	private RepresentanteDataManager representanteDataManager;
	
	public void setRepresentanteDataManager(RepresentanteDataManager representanteDataManager) {
		this.representanteDataManager = representanteDataManager;
	}
	

	public RepresentanteController () {}

	
	public void registrarRepresentante () {
		
		slf4jLogger.info("registrarRepresentante");
		RepresentanteVO representanteVO;
		
		try {
			representanteVO=new RepresentanteVO();
			representanteVO.setRepresentante(representanteDataManager.getRepresentanteInstancia());
			representanteVO.setPersona(representanteDataManager.getPersonaInstancia());
			RepresentanteDTO representanteNuevo = this.servicioMatricula.createOrUpdateRepresentante(representanteVO);
			if (representanteNuevo != null) {
				representanteDataManager.setRepresentanteInstancia(new RepresentanteDTO());
				representanteDataManager.setPersonaInstancia(new Persona());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.representante.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}


}
