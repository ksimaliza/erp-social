package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.ConstantesApplication;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.DefuncionVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.DefuncionDataManager;

@ViewScoped
@ManagedBean (name = "defuncionController")
public class DefuncionController extends BaseController {
	
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(DefuncionController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{defuncionDataManager}")
	private DefuncionDataManager defuncionDataManager;

	public DefuncionDataManager getDefuncionDataManager() {
		return defuncionDataManager;
	}

	public void setDefuncionDataManager(DefuncionDataManager defuncionDataManager) {
		this.defuncionDataManager = defuncionDataManager;
	}
	
	public DefuncionController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		buscarSacerdote();
		buscarProvincia();
		buscarDoctor();
		buscarEstadoCivil();
		
	}
	
public void registrarDefuncion () {
		
		slf4jLogger.info("registrarDefuncion");
		DefuncionVO defuncionVO;
		SacerdoteDTO sacerdoteDTO;
		DoctorDTO doctorDTO;
		CatalogoEucaristiaDTO provincia;
		CatalogoEucaristiaDTO canton;
		CatalogoEucaristiaDTO parroquia;
		CatalogoEucaristiaDTO estadoCivil;
		DefuncionListDTO defuncionAux=new DefuncionListDTO();
		try {
			 defuncionAux.setPerCi(defuncionDataManager.getDifuntoInsertar().getPerCi());
			 List<DefuncionListDTO> ListaDefunciones= servicioEucaristia.buscarDefuncion(defuncionAux);
			 Boolean esDifunto=false;
			 for (DefuncionListDTO defuncionListDTO : ListaDefunciones) {
					if (defuncionListDTO.getPerCi().equals(defuncionDataManager.getDifuntoInsertar().getPerCi()))
						esDifunto=true;
					}
		   if (!CollectionUtils.isEmpty(ListaDefunciones) && ListaDefunciones.size()!=0 && ListaDefunciones.size()!=0 && defuncionDataManager.getDefuncionInsertar().getDefCodigo()==null && esDifunto)
			      
		   {
			   MensajesWebController.aniadirMensajeAdvertencia("Ya existe difunto con la misma cedula");
			   return;
		   }
		   //si se repite la cedula del defunto en otro campo no permitido 
		   if(defuncionDataManager.getDifuntoInsertar().getPerCi().toString().equals(defuncionDataManager.getPadreInsertar().getPerCi().toString()) ||
			  defuncionDataManager.getDifuntoInsertar().getPerCi().toString().equals(defuncionDataManager.getMadreInsertar().getPerCi().toString()))
				   {
					MensajesWebController.aniadirMensajeError("Cedula de difunto repetida en otro campo");
					return;
				   }
		   //si se repite la cedula de la madre del difunto en un campo no permitido
		   if(defuncionDataManager.getMadreInsertar().getPerCi().toString().equals(defuncionDataManager.getDifuntoInsertar().getPerCi().toString()) ||
		      defuncionDataManager.getMadreInsertar().getPerCi().toString().equals(defuncionDataManager.getPadreInsertar().getPerCi().toString()))
						   {
							MensajesWebController.aniadirMensajeError("Cedula de la madre repetida en otro campo");
							return;
						   }
		 //si se repite la cedula del padre  en un campo no permitido
		   if(defuncionDataManager.getPadreInsertar().getPerCi().toString().equals(defuncionDataManager.getDifuntoInsertar().getPerCi().toString()) ||
		      defuncionDataManager.getPadreInsertar().getPerCi().toString().equals(defuncionDataManager.getMadreInsertar().getPerCi().toString()))
						   {
							MensajesWebController.aniadirMensajeError("Cedula de padre repetida en otro campo");
							return;
						   }
			defuncionVO=new DefuncionVO();
			sacerdoteDTO=new SacerdoteDTO();
			doctorDTO=new DoctorDTO();
			provincia=new CatalogoEucaristiaDTO();
			canton=new CatalogoEucaristiaDTO();
			parroquia=new CatalogoEucaristiaDTO();
			estadoCivil=new CatalogoEucaristiaDTO();
			
			
			defuncionVO.setDifunto(defuncionDataManager.getDifuntoInsertar());
			defuncionVO.setMadre(defuncionDataManager.getMadreInsertar());
			defuncionVO.setPadre(defuncionDataManager.getPadreInsertar());
			defuncionVO.setConyuge(defuncionDataManager.getConyugeInsertar());
			defuncionVO.setDefuncion(defuncionDataManager.getDefuncionInsertar());
			
			sacerdoteDTO.setSacCodigo(defuncionDataManager.getSacerdoteCodigo());
			doctorDTO.setDocCodigo(defuncionDataManager.getDoctorCodigo());
	
			defuncionVO.setDoctor(doctorDTO);
			defuncionVO.setSacerdote(sacerdoteDTO);
			
			provincia.setCatCodigo(defuncionDataManager.getProvinciaCodigo());
			canton.setCatCodigo(defuncionDataManager.getCantonCodigo());
			parroquia.setCatCodigo(defuncionDataManager.getParroquiaCodigo());
			estadoCivil.setCatCodigo(defuncionDataManager.getEstadoCivilCodigo());
			
			
			defuncionVO.getDefuncion().setDefProvincia(provincia.getCatCodigo());
			defuncionVO.getDefuncion().setDefCanton(canton.getCatCodigo());
			defuncionVO.getDefuncion().setDefParroquia(parroquia.getCatCodigo());
			defuncionVO.getDefuncion().setDefEstadoCivil(estadoCivil.getCatCodigo());
			
			if(defuncionDataManager.getFechaMuerteInsertar().getTime()>defuncionDataManager.getFechaSepelioInsertar().getTime())
			{
				MensajesWebController.aniadirMensajeError("Fecha de Muerte no debe exceder a la Fecha de Sepelio");
				return;
			}
			
			defuncionVO.getDefuncion().setDefFecha(new Timestamp(defuncionDataManager.getFechaSepelioInsertar().getTime()));
			defuncionVO.getDefuncion().setDefFechaDifunto(new Timestamp(defuncionDataManager.getFechaMuerteInsertar().getTime()));
			defuncionVO.getDefuncion().setDefEmpresa(getEmpresaTbl().getEmrPk());
			DefuncionDTO defuncionNueva= this.servicioEucaristia.createOrUpdateDefuncion(defuncionVO);
			
			defuncionDataManager.setExportDesactivado(false);
			DefuncionListDTO defuncion=new DefuncionListDTO();
			defuncion.setDefPersona(defuncionNueva.getDefPersona());
			
			defuncion.setDefMadre(defuncionNueva.getDefMadre());
			defuncion.setDefPadre(defuncionNueva.getDefPadre());
			defuncion.setDefCodigo(defuncionNueva.getDefCodigo());
			defuncion.setDefSacerdote(defuncionNueva.getEucSacerdote().getSacCodigo());
			defuncion.setDefDoctorCertifica(defuncionNueva.getDefDoctorCertifica());
			
			if(defuncionDataManager.getConyugeInsertar()!=null)
				defuncion.setDefConyugue(defuncionNueva.getDefConyugue());
			
			cargarDatosDefuncion(defuncion);
			
			if (defuncionNueva != null) {
							
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.defuncion.registrar.exito");
			}
			buscarDefuncion();
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarSacerdote () {
		slf4jLogger.info("buscarSacerdote");
		List<SacerdoteListDTO> listaSacerdote=null;
		try {
			SacerdoteListDTO sacerdoteListDTO= new SacerdoteListDTO();
			sacerdoteListDTO.setSacEstado(ConstantesApplication.ESTADO_ACTIVO);
			sacerdoteListDTO.setSacEmpresa(getEmpresaTbl().getEmrPk());
			listaSacerdote = this.servicioEucaristia.buscarSacerdote(sacerdoteListDTO);
			if (CollectionUtils.isEmpty(listaSacerdote) && listaSacerdote.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setSacerdoteListDTO(listaSacerdote);			
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarDoctor () {
		slf4jLogger.info("buscarDoctor");
		
		List<DoctorListDTO> listaDoctor=null;
		
		try {
			DoctorListDTO doctorListDTO = new DoctorListDTO();
			doctorListDTO.setDocEmpresa(getEmpresaTbl().getEmrPk());
			listaDoctor=this.servicioEucaristia.buscarDoctor(doctorListDTO);
			
			if (CollectionUtils.isEmpty(listaDoctor) && listaDoctor.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setDoctorListDTO(listaDoctor);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDoctor {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	
	public void buscarDifunto () {
		slf4jLogger.info("buscarDifunto");
		
		List<Persona> listaDifunto=null;
		
		try {
			if(defuncionDataManager.getDifuntoInsertar().getPerCi()!=null && defuncionDataManager.getDifuntoInsertar().getPerCi()!="" )
			{
				defuncionDataManager.getDifuntoInsertar().setPerNombres(null);
				defuncionDataManager.getDifuntoInsertar().setPerApellidos(null);
				listaDifunto=this.servicioAdministracion.buscarPersona(defuncionDataManager.getDifuntoInsertar());
											
				if (CollectionUtils.isEmpty(listaDifunto) && listaDifunto.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.defuncionDataManager.setDifuntoInsertar(listaDifunto.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDifunto {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	

	public void buscarConyuge () {
		slf4jLogger.info("buscarConyuge");
		
		List<Persona> listaConyuge=null;
		
		try {
			
			if(defuncionDataManager.getConyugeInsertar().getPerCi()!=null && defuncionDataManager.getConyugeInsertar().getPerCi()!="" )
			{
				defuncionDataManager.getConyugeInsertar().setPerNombres(null);
				defuncionDataManager.getConyugeInsertar().setPerApellidos(null);
				listaConyuge=this.servicioAdministracion.buscarPersona(defuncionDataManager.getConyugeInsertar());
											
				if (CollectionUtils.isEmpty(listaConyuge) && listaConyuge.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.defuncionDataManager.setConyugeInsertar(listaConyuge.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarConyuge {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMadre () {
		slf4jLogger.info("buscarMadre");
		
		List<Persona> listaPersona=null;
		
		try {
			if(defuncionDataManager.getMadreInsertar().getPerCi()!=null && defuncionDataManager.getMadreInsertar().getPerCi()!="" )
			{
				defuncionDataManager.getMadreInsertar().setPerNombres(null);
				defuncionDataManager.getMadreInsertar().setPerApellidos(null);
				listaPersona=this.servicioAdministracion.buscarPersona(defuncionDataManager.getMadreInsertar());
								
				if (CollectionUtils.isEmpty(listaPersona) && listaPersona.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.defuncionDataManager.setMadreInsertar(listaPersona.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadre {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarPadre () {
		slf4jLogger.info("buscarPadre");
		
		List<Persona> listaPersona=null;
		
		try {
			if(defuncionDataManager.getPadreInsertar().getPerCi()!=null && defuncionDataManager.getPadreInsertar().getPerCi()!="" )
			{
				defuncionDataManager.getPadreInsertar().setPerNombres(null);
				defuncionDataManager.getPadreInsertar().setPerApellidos(null);
				listaPersona=this.servicioAdministracion.buscarPersona(defuncionDataManager.getPadreInsertar());
								
				if (CollectionUtils.isEmpty(listaPersona) && listaPersona.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.defuncionDataManager.setPadreInsertar(listaPersona.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPadre {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarDefuncion () {
		slf4jLogger.info("buscarDefuncion");
		
		List<DefuncionListDTO> listaDefuncion=null;
		
		try {
			defuncionDataManager.getDefuncionListDTO().setDefEmpresa(getEmpresaTbl().getEmrPk());
			listaDefuncion=this.servicioEucaristia.buscarDefuncion(defuncionDataManager.getDefuncionListDTO());
			if (CollectionUtils.isEmpty(listaDefuncion) && listaDefuncion.size()==0) {
				defuncionDataManager.setDefuncionDTOs(new ArrayList<DefuncionListDTO>());
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setDefuncionDTOs(listaDefuncion);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarDefuncion {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	

	public void cargarDatosDefuncion (DefuncionListDTO defuncion) {
		try {
			
			DefuncionVO defuncionEncontrada=servicioEucaristia.obtenerDefuncionPorId(defuncion);
			this.defuncionDataManager.setDifuntoInsertar(defuncionEncontrada.getDifunto());
			this.defuncionDataManager.setEstadoCivilCodigo(defuncionEncontrada.getDefuncion().getDefEstadoCivil());
			this.defuncionDataManager.setConyugeInsertar(defuncionEncontrada.getConyuge());
			this.defuncionDataManager.setMadreInsertar(defuncionEncontrada.getMadre());
			this.defuncionDataManager.setPadreInsertar(defuncionEncontrada.getPadre());
			this.defuncionDataManager.setDefuncionInsertar(defuncionEncontrada.getDefuncion());
			this.defuncionDataManager.setDoctorCodigo(defuncionEncontrada.getDefuncion().getDefDoctorCertifica());
			this.defuncionDataManager.setSacerdoteCodigo(defuncionEncontrada.getDefuncion().getEucSacerdote().getSacCodigo());
			this.defuncionDataManager.setProvinciaCodigo(defuncionEncontrada.getDefuncion().getDefProvincia());
			buscarCanton();
			this.defuncionDataManager.setCantonCodigo(defuncionEncontrada.getDefuncion().getDefCanton());
			buscarParroquia();
			this.defuncionDataManager.setParroquiaCodigo(defuncionEncontrada.getDefuncion().getDefParroquia());
			this.defuncionDataManager.setFechaMuerteInsertar(defuncionEncontrada.getDefuncion().getDefFechaDifunto());
			this.defuncionDataManager.setFechaSepelioInsertar(defuncionEncontrada.getDefuncion().getDefFecha());
			this.defuncionDataManager.setExportDesactivado(false);
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosDefuncion {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosDefuncion seleccionado");
		}
	}
	
	
	public void buscarProvincia () {
		slf4jLogger.info("buscarCatalogo");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(1);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setProvinciasEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCatalogo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarCanton () {
		slf4jLogger.info("buscarCanton");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(defuncionDataManager.getProvinciaCodigo());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setCantonEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCanton {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarParroquia () {
		slf4jLogger.info("buscarParroquia");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(defuncionDataManager.getCantonCodigo());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setParroquiaEucaristiaDTOs(listaCatalogo);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarParroquia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarEstadoCivil () {
		slf4jLogger.info("buscarEstadoCivil");
		
		List<CatalogoEucaristiaDTO> listaCatalogo=null;
		
		try {
			CatalogoEucaristiaDTO cat=new CatalogoEucaristiaDTO();
			cat.setCatCodigo(14);
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.defuncionDataManager.setEstadoCivils(listaCatalogo);
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarEstadoCivil {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	
	public void exportar()
	{
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
				
		DateFormat pequena = DateFormat.getDateInstance(DateFormat.SHORT);
		
		defuncionDataManager.setDesactivado(true);
		List<DoctorListDTO> listaDoctor=null;
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		if (defuncionDataManager.getCantonEucaristiaDTOs() != null)
			for (int i = 0; i < defuncionDataManager
					.getCantonEucaristiaDTOs().size(); i++)
				if (defuncionDataManager.getCantonEucaristiaDTOs().get(i)
						.getCatCodigo().equals(defuncionDataManager
						.getCantonCodigo()))
		mapParametros.put("canton", defuncionDataManager.getCantonEucaristiaDTOs().get(i).getCatDescripcion().toUpperCase());
		if (defuncionDataManager.getParroquiaEucaristiaDTOs() != null)
			for (int i = 0; i < defuncionDataManager
					.getParroquiaEucaristiaDTOs().size(); i++)
				if (defuncionDataManager.getParroquiaEucaristiaDTOs()
						.get(i).getCatCodigo().equals(defuncionDataManager
						.getParroquiaCodigo())){
		mapParametros.put("parroquiaCabecera", "\"" + defuncionDataManager.getParroquiaEucaristiaDTOs().get(i).getCatDescripcion().toUpperCase() +"\"");
		mapParametros.put("parroquia", defuncionDataManager.getParroquiaEucaristiaDTOs().get(i).getCatDescripcion().toUpperCase());
		mapParametros.put("parroquiafechaActual", defuncionDataManager.getParroquiaEucaristiaDTOs().get(i).getCatDescripcion()+ ", "+String.valueOf(full.format(fechaActual).charAt(0)).toUpperCase() +full.format(fechaActual).substring(1));
		
				}
		mapParametros.put("tomo", defuncionDataManager.getDefuncionInsertar().getDefTomo());
		mapParametros.put("pagina", defuncionDataManager.getDefuncionInsertar().getDefPagina());
		mapParametros.put("acta", defuncionDataManager.getDefuncionInsertar().getDefActa());
		mapParametros.put("fechaMuerte", pequena.format(defuncionDataManager.getFechaMuerteInsertar()));
		mapParametros.put("fechaSepultura", pequena.format(defuncionDataManager.getFechaSepelioInsertar()));
		mapParametros.put("causa", defuncionDataManager.getDefuncionInsertar().getDefCausaMuerte());
		mapParametros.put("difunto", defuncionDataManager.getDifuntoInsertar().getPerApellidos().toUpperCase() + " "+   defuncionDataManager.getDifuntoInsertar().getPerNombres().toUpperCase());
	
		DoctorListDTO doctorListDTO= new DoctorListDTO();
		doctorListDTO.setDocCodigo(this.defuncionDataManager.getDoctorCodigo());
		try {
			listaDoctor=this.servicioEucaristia.buscarDoctor(doctorListDTO);
		mapParametros.put("doctor", listaDoctor.get(0).getPerApellidos().toUpperCase() + " "+  listaDoctor.get(0).getPerNombres().toUpperCase());
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar Doctor al exportar  {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		mapParametros.put("notaMarginal", defuncionDataManager.getDefuncionInsertar().getDefNotaMarginal());
		if (defuncionDataManager.getParroquiaEucaristiaDTOs() != null)
			for (int i = 0; i < defuncionDataManager
					.getProvinciasEucaristiaDTOs().size(); i++)
				if (defuncionDataManager.getProvinciasEucaristiaDTOs()
						.get(i).getCatCodigo().equals(defuncionDataManager
						.getProvinciaCodigo()))
		mapParametros.put("provincia", defuncionDataManager.getProvinciasEucaristiaDTOs().get(i).getCatDescripcion().toUpperCase());
		mapParametros.put("padre", defuncionDataManager.getPadreInsertar().getPerApellidos().toUpperCase() +  " "+defuncionDataManager.getPadreInsertar().getPerNombres().toUpperCase());
		mapParametros.put("madre", defuncionDataManager.getMadreInsertar().getPerApellidos().toUpperCase() + " "+ defuncionDataManager.getMadreInsertar().getPerNombres().toUpperCase());
		if(defuncionDataManager.getConyugeInsertar()!=null && defuncionDataManager.getConyugeInsertar().getPerApellidos()!=null && defuncionDataManager.getConyugeInsertar().getPerApellidos()!=null )
			mapParametros.put("conyuge", defuncionDataManager.getConyugeInsertar().getPerApellidos().toUpperCase() + " "+  defuncionDataManager.getConyugeInsertar().getPerNombres().toUpperCase());
		else
			mapParametros.put("conyuge", "" );
		mapParametros.put("daFe", defuncionDataManager.getDaFe().toUpperCase());
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "partidaDefuncion", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.defuncionDataManager.getFormatoPdf(), "partidaDefuncion");
	
		}
	
	public void cargarDatosDefuncionEditar (DefuncionListDTO defuncionListDTO) {
		cargarDatosDefuncion (defuncionListDTO);
		buscarSacerdoteEditar();
	}
	
	public void buscarSacerdoteEditar () {
		slf4jLogger.info("buscarSacerdoteEditar");
		
		List<SacerdoteListDTO> listaSacerdote=null;
		
		try {
			this.defuncionDataManager.setSacerdoteListDTO(new ArrayList<SacerdoteListDTO>());
			buscarSacerdote();
			SacerdoteListDTO sacerdoteListDTO= new SacerdoteListDTO();
			sacerdoteListDTO.setSacCodigo(this.defuncionDataManager.getSacerdoteCodigo());
			listaSacerdote=this.servicioEucaristia.buscarSacerdote(sacerdoteListDTO);
			List<SacerdoteListDTO> listaSacerdoteActivos=this.defuncionDataManager.getSacerdoteListDTO();
			Boolean estaActivo=false;
			for (SacerdoteListDTO sacerdote : listaSacerdoteActivos) {
				if(sacerdote.getSacCodigo().equals(listaSacerdote.get(0).getSacCodigo()))estaActivo=true;
			}
			if(!estaActivo) this.defuncionDataManager.getSacerdoteListDTO().add(listaSacerdote.get(0));
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	public void exportarPdf(DefuncionListDTO defuncion)
	{
		cargarDatosDefuncion(defuncion);
		exportar();
	}
	
	public void limpiarFormulario()
	{
		defuncionDataManager.setDifuntoInsertar(new Persona());
		defuncionDataManager.setPadreInsertar(new Persona());
		defuncionDataManager.setMadreInsertar(new Persona());
		defuncionDataManager.setConyugeInsertar(new Persona());
		defuncionDataManager.setDefuncionInsertar(new DefuncionDTO());
		defuncionDataManager.setSacerdoteCodigo(0);
		defuncionDataManager.setDoctorCodigo(0);
		defuncionDataManager.setEstadoCivilCodigo(0);
		defuncionDataManager.setProvinciaCodigo(0);
		defuncionDataManager.setCantonCodigo(0);
		defuncionDataManager.setParroquiaCodigo(0);
		
		defuncionDataManager.setFechaMuerteInsertar(new Date());
		defuncionDataManager.setFechaSepelioInsertar(new Date());
		defuncionDataManager.setExportDesactivado(true);
		buscarSacerdote();
	}

	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
	public void limpiarConyuge() {
		
			defuncionDataManager.setConyugeInsertar(new Persona());
	}


}
