package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.RepresentanteVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.RepresentanteDataManager;

@ViewScoped
@ManagedBean (name = "representanteController")
public class RepresentanteController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			
			representanteDataManager.getRepresentanteInstancia().setRepEmpresa(getEmpresaCode());
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
	
	public void buscarRepresentantes () {
		slf4jLogger.info("buscarDocentes");
		
		List<RepresentanteListDTO> listarepresentante=null;
		
		try {
							
			representanteDataManager.getRepresentanteBuscar().setRepEmpresa(getEmpresaCode());
			listarepresentante = this.servicioMatricula.buscarRepresentante(representanteDataManager.getRepresentanteBuscar());
			
			if (CollectionUtils.isEmpty(listarepresentante) && listarepresentante.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.representanteDataManager.setRepresentanteListDTOs(listarepresentante);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDocentes {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosRepresentante (RepresentanteListDTO representante) {
		try {
			RepresentanteVO representanteEncontrado = servicioMatricula.obtenerRepresentantePorId(representante.getRepPersona(), representante.getRepCodigo());
			
			this.representanteDataManager.setRepresentanteInstancia(representanteEncontrado.getRepresentante());
			this.representanteDataManager.setPersonaInstancia(representanteEncontrado.getPersona());
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosRepresentante {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosRepresentante seleccionado");
		}
	}


	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}


}
