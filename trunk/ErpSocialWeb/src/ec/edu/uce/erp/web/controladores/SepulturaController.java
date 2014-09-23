package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.SepulturaVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.SepulturaDataManager;

@ViewScoped
@ManagedBean (name = "sepulturaController")
public class SepulturaController extends BaseController {
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(SepulturaController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	
	
	@ManagedProperty(value="#{sepulturaDataManager}")
	private SepulturaDataManager sepulturaDataManager;


	public SepulturaDataManager getSepulturaDataManager() {
		return sepulturaDataManager;
	}


	public void setSepulturaDataManager(SepulturaDataManager sepulturaDataManager) {
		this.sepulturaDataManager = sepulturaDataManager;
	}

	public SepulturaController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarNicho();
		
		
	
	}
	
	
	public void registrarSepultura () {
		
		slf4jLogger.info("registrarSepultura");
		
		SepulturaVO sepulturaVO;
		NichoDTO nichoDTO;
		DefuncionDTO defuncion;	
		
		try {
		
			defuncion=new DefuncionDTO();
			sepulturaVO=new SepulturaVO();
			nichoDTO=new NichoDTO();
			sepulturaVO.setSepultura(sepulturaDataManager.getSepulturaDTO());
			nichoDTO.setNicCodigo(sepulturaDataManager.getCodigoNicho());
			sepulturaVO.setNichoDTO(nichoDTO);
			defuncion.setDefPersona(sepulturaDataManager.getDefuncionlistDTO().getDefPersona());
			
			sepulturaVO.getSepultura().setSepDifunto(defuncion.getDefPersona());
					
			SepulturaDTO sepulturaNueva=this.servicioEucaristia.createOrUpdateSepultura(sepulturaVO);
												
			if (sepulturaNueva!= null) {
				sepulturaDataManager.setCodigoNicho(0);
				sepulturaDataManager.setSepulturaDTO(new SepulturaDTO());
																		
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.sepultura.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarNicho() {
		slf4jLogger.info("buscarNicho");
		List<NichoListDTO> listResultado=new ArrayList<NichoListDTO>();
		
		try {
			
			listResultado = this.servicioEucaristia.buscarNicho(new NichoListDTO());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.sepulturaDataManager.setNichoListDTOs2(listResultado);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el buscarNicho {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void buscarNicho2() {
		slf4jLogger.info("buscarNicho");
		List<NichoListDTO> listResultado=new ArrayList<NichoListDTO>();
		
		try {
			
			NichoListDTO nicho=new NichoListDTO();
			nicho.setNicCodigo(this.sepulturaDataManager.getCodigoNicho());
			
			listResultado = this.servicioEucaristia.buscarNicho(nicho);
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.sepulturaDataManager.setNichoListDTO(listResultado.get(0));
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el buscarNicho {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	
	public void buscarSepultura()
	{
		try {
			sepulturaDataManager.setSepulturaListDTOs(this.servicioEucaristia.readSepultura(this.sepulturaDataManager.getSepulturaListDTO()));
			this.sepulturaDataManager.setSepulturaListDTO(new SepulturaListDTO());
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSepultura {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	

	
	public void buscarDefuncion () {
		slf4jLogger.info("buscarDefuncion");
		
		List<DefuncionListDTO> listaDefuncion=null;
		
		try {
		 
			listaDefuncion=this.servicioEucaristia.buscarDefuncion(sepulturaDataManager.getDefuncionlistDTO());
					
			if (CollectionUtils.isEmpty(listaDefuncion) && listaDefuncion.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.sepulturaDataManager.setDefuncionlistDTO(listaDefuncion.get(0));
						
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDefuncion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}

	public void cargarDatosSepultura (SepulturaListDTO sepultura) {
		try {
			SepulturaVO sepulturaEncontrado=servicioEucaristia.obtenerSepulturaPorId(sepultura);
			this.sepulturaDataManager.setCodigoNicho(sepulturaEncontrado.getSepultura().getSepNicho());
			this.sepulturaDataManager.setSepulturaDTO(sepulturaEncontrado.getSepultura());
			
													
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosSepultura {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosSepultura seleccionado");
		}
	}


	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
