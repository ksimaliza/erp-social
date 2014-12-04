package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;


@ViewScoped
@ManagedBean (name = "seccionNichoController")

public class SeccionNichoController extends BaseController {
	private static final long serialVersionUID = 1L;

	//private static final Logger slf4jLogger = LoggerFactory.getLogger(SeccionNichoController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;


	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
