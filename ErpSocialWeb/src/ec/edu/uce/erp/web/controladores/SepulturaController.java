package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
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
		
		try {
		
			sepulturaVO=new SepulturaVO();
			nichoDTO=new NichoDTO();
			sepulturaVO.setSepultura(sepulturaDataManager.getSepulturaDTO());
			nichoDTO.setNicCodigo(sepulturaDataManager.getCodigoNicho());
			sepulturaVO.setNichoDTO(nichoDTO);
						
			sepulturaVO.setDefuncionPersona(sepulturaDataManager.getDefuncionInsertar());
			sepulturaVO.getSepultura().setSepDifunto(sepulturaDataManager.getDefuncionInsertar().getPerPk());
					
			SepulturaDTO sepulturaNueva=this.servicioEucaristia.createOrUpdateSepultura(sepulturaVO);
												
			if (sepulturaNueva!= null) {
				sepulturaDataManager.setCodigoNicho(0);
				sepulturaDataManager.setSepulturaDTO(new SepulturaDTO());
				sepulturaDataManager.setDefuncionInsertar(new Persona());
																		
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.sepultura.registrar.exito");
			}
			buscarSepultura();
			
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
	

	public void buscarDifunto () {
		slf4jLogger.info("buscarDifunto");
		
		List<Persona> listaDifunto=null;
		DefuncionListDTO difunto=new DefuncionListDTO();
		List<DefuncionListDTO> list=null;
		
		try {
			if(sepulturaDataManager.getDefuncionInsertar().getPerCi()!=null && sepulturaDataManager.getDefuncionInsertar().getPerCi()!="" )
			{
				sepulturaDataManager.getDefuncionInsertar().setPerNombres(null);
				sepulturaDataManager.getDefuncionInsertar().setPerApellidos(null);
				listaDifunto=this.servicioAdministracion.buscarPersona(sepulturaDataManager.getDefuncionInsertar());					
				difunto.setPerCi(sepulturaDataManager.getDefuncionInsertar().getPerCi());
				list=this.servicioEucaristia.buscarDefuncion(difunto);
				
				if ((CollectionUtils.isEmpty(listaDifunto) && listaDifunto.size()==0)||CollectionUtils.isEmpty(list) && list.size()==0) {
					sepulturaDataManager.setDesactivado(true);
					MensajesWebController.aniadirMensajeAdvertencia("Difunto no encontrado. Ingresar información en Defunción");
				} else {
					sepulturaDataManager.setDefuncionInsertar(listaDifunto.get(0));
					sepulturaDataManager.setDefuncionlistDTO(list.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarBautizado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	public void cargarDatosSepultura (SepulturaListDTO sepultura) {
		try {
			SepulturaVO sepulturaEncontrado=servicioEucaristia.obtenerSepulturaPorId(sepultura);
			
			this.sepulturaDataManager.setDefuncionInsertar(sepulturaEncontrado.getDefuncionPersona());
			this.sepulturaDataManager.setSepulturaDTO(sepulturaEncontrado.getSepultura());
			this.sepulturaDataManager.setCodigoNicho(sepulturaEncontrado.getSepultura().getSepNicho());
			buscarNicho2();
			//buscarDefuncion();
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosSepultura {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosSepultura seleccionado");
		}
	}

	public void cancel()
	{
		sepulturaDataManager.setCodigoNicho(0);
		sepulturaDataManager.setSepulturaDTO(new SepulturaDTO());
		sepulturaDataManager.setDefuncionInsertar(new Persona());
		sepulturaDataManager.setNichoListDTO(new NichoListDTO());
		sepulturaDataManager.setDesactivado(false);
		RequestContext.getCurrentInstance().execute("dlgNuevoEstudiante.hide()");
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
