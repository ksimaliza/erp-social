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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.MatrimonioVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.PartidaMatrimonioDataManager;

@ViewScoped
@ManagedBean (name = "partidaMatrimonioController")


public class PartidaMatrimonioController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(PartidaMatrimonioController.class);
	
	
	@EJB
	private ServicioEucaristia servicioEucaristia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{partidaMatrimonioDataManager}")
	private PartidaMatrimonioDataManager partidaMatrimonioDataManager;

	

	public PartidaMatrimonioDataManager getPartidaMatrimonioDataManager() {
		return partidaMatrimonioDataManager;
	}

	public void setPartidaMatrimonioDataManager(
			PartidaMatrimonioDataManager partidaMatrimonioDataManager) {
		this.partidaMatrimonioDataManager = partidaMatrimonioDataManager;
	}

	public PartidaMatrimonioController() {
		
	}
	
	@PostConstruct
	public void inicializarObjetos () {
	buscarSacerdote();
	buscarProvincia();
	
		
	}
public void registrarMatrimonio () {
		
		slf4jLogger.info("registrarMatrimonio");
		MatrimonioVO matrimonioVO;
		SacerdoteDTO sacerdoteDTO;
		CatalogoEucaristiaDTO provincia;
		CatalogoEucaristiaDTO canton;
		CatalogoEucaristiaDTO parroquia;
		
		try {
			
			matrimonioVO=new MatrimonioVO();
			sacerdoteDTO=new SacerdoteDTO();
			provincia=new CatalogoEucaristiaDTO();
			canton=new CatalogoEucaristiaDTO();
			parroquia=new CatalogoEucaristiaDTO();
			
			partidaMatrimonioDataManager.getMatrimonioDTO().setMatCertificadoPor(getPersonaCode());
			matrimonioVO.setNovio(partidaMatrimonioDataManager.getNovioInsertar());
			matrimonioVO.setNovia(partidaMatrimonioDataManager.getNoviaInsertar());
			matrimonioVO.setMad_novia(partidaMatrimonioDataManager.getMad_noviaInsertar());
			matrimonioVO.setMad_novio(partidaMatrimonioDataManager.getMad_novioInsertar());
			matrimonioVO.setPad_novia(partidaMatrimonioDataManager.getPad_noviaInsertar());
			matrimonioVO.setPad_novio(partidaMatrimonioDataManager.getPad_novioInsertar());
			matrimonioVO.setPadre_novia(partidaMatrimonioDataManager.getPadre_noviaInsertar());
			matrimonioVO.setPadre_novio(partidaMatrimonioDataManager.getPadre_novioInsertar());
			matrimonioVO.setMadre_novia(partidaMatrimonioDataManager.getMadre_noviaInsertar());
			matrimonioVO.setMadre_novio(partidaMatrimonioDataManager.getMadre_novioInsertar());
			
			matrimonioVO.setMatrimonio(partidaMatrimonioDataManager.getMatrimonioDTO());
			
			provincia.setCatCodigo(partidaMatrimonioDataManager.getProvincia());
			canton.setCatCodigo(partidaMatrimonioDataManager.getCanton());
			parroquia.setCatCodigo(partidaMatrimonioDataManager.getParroquia());
			
			
			matrimonioVO.getMatrimonio().setMatProvincia(provincia.getCatCodigo());
			matrimonioVO.getMatrimonio().setMatCanton(canton.getCatCodigo());
			matrimonioVO.getMatrimonio().setMatParroquia(parroquia.getCatCodigo());
			
			sacerdoteDTO.setSacCodigo(partidaMatrimonioDataManager.getSacerdoteCodigo());
			
			matrimonioVO.setSacerdote(sacerdoteDTO);
			
			partidaMatrimonioDataManager.getMatrimonioDTO().setMatEmpresa(getEmpresaTbl().getEmrPk());
			
			System.out.println("Cedula Novio: "+partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString());
			
			//novio no repetido en los demas campos
			if(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString()) ||
               partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString()))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de Novio repetida en otro campo");
				return;
			}
			
			//novia no repetido en los demas campos
			if(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString()) ||
               partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de Novia repetida en otro campo");
				return;
			}
			
			//madre novio no repetido en los campos no permitidos
			if(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString()))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de madre del novio repetida en otro campo no permitido");
				return;
			}
			
			//padre novio no repetido en los campos no permitidos
			if(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString()))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de padre del novio repetida en otro campo no permitido");
				return;
			}
			
			//padrino novio no repetido en los campos no permitidos
			if(!partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString().equals("")
					   && (partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString())))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de padrino del novio repetida en otro campo no permitido");
				return;
			}
			
			//madrina novio no repetido en los campos no permitidos
			if(!partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString().equals("")
					   && (partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString())))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de madrina del novio repetida en otro campo no permitido");
				return;
			}
			//validacion datos relacionados con los datos de la novia
			
			//madre novia no repetido en los campos no permitidos
			if(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString()))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de madre del novia repetida en otro campo no permitido");
				return;
			}
			
			//padre novia no repetido en los campos no permitidos
			if(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString()))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de padre de la novia repetida en otro campo no permitido");
				return;
			}
			
			//padrino novia no repetido en los campos no permitidos
			if(!partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString().equals("")
					   && (partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString())))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de padrino de la novia repetida en otro campo no permitido");
				return;
			}
			
			//madrina novia no repetido en los campos no permitidos
			if(!partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString().equals("")
					   && (partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNovioInsertar().getPerCi().toString()) || 
			   partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi().toString()) ||
			   partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi().toString().equals(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi().toString())))
			   
			   {
				MensajesWebController.aniadirMensajeError("Cedula de madrina de la novia repetida en otro campo no permitido");
				return;
			}
			if(partidaMatrimonioDataManager.getFechaApCurInsertar().getTime()>partidaMatrimonioDataManager.getFechaMatrInsertar().getTime())
			{
				MensajesWebController.aniadirMensajeError("Ingrese fecha de Aprobacion del curso correcta");
				return;
			}
			matrimonioVO.getMatrimonio().setMatFechaAprobacionCurso(new Timestamp(partidaMatrimonioDataManager.getFechaApCurInsertar().getTime()));
			matrimonioVO.getMatrimonio().setMatFecha(new Timestamp(partidaMatrimonioDataManager.getFechaMatrInsertar().getTime()));
			matrimonioVO.getMatrimonio().setMatEmpresa(getEmpresaTbl().getEmrPk());
					
			MatrimonioDTO matrimonioNuevo= this.servicioEucaristia.createOrUpdateMatrimonio(matrimonioVO);
				
			partidaMatrimonioDataManager.setExportDesactivado(false);
			
			MatrimonioListDTO matrimonio=new MatrimonioListDTO();
			matrimonio.setMatNovia(matrimonioNuevo.getMatNovia());
			matrimonio.setMatNovio(matrimonioNuevo.getMatNovio());
			matrimonio.setMatMadrinaNovia(matrimonioNuevo.getMatMadrinaNovia());
			matrimonio.setMatMadrinaNovio(matrimonioNuevo.getMatMadrinaNovio());
			matrimonio.setMatPadrinoNovia(matrimonioNuevo.getMatPadrinoNovia());
			matrimonio.setMatPadrinoNovio(matrimonioNuevo.getMatPadrinoNovio());
			matrimonio.setMatPadreNovia(matrimonioNuevo.getMatPadreNovia());
			matrimonio.setMatPadreNovio(matrimonioNuevo.getMatPadreNovio());
			matrimonio.setMatMadreNovia(matrimonioNuevo.getMatMadreNovia());
			matrimonio.setMatMadreNovio(matrimonioNuevo.getMatMadreNovio());
			matrimonio.setMatCodigo(matrimonioNuevo.getMatCodigo());
			matrimonio.setMatSacerdote(matrimonioNuevo.getEucSacerdote().getSacCodigo());
			
			cargarDatosMatrimonio(matrimonio);
			
			if (matrimonioNuevo != null) {
				
				MensajesWebController.aniadirMensajeInformacion("erp.despacho.partida.matrimonio.registrar.exito");
			}
			buscarPartidaMatrimonio();
			
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
				this.partidaMatrimonioDataManager.setSacerdoteListDTO(listaSacerdote);
					
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	
	public void buscarNovio () {
		slf4jLogger.info("buscarNovio");
		
		List<Persona> listaNovio=null;
		
		try {
			if(partidaMatrimonioDataManager.getNovioInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getNovioInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getNovioInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getNovioInsertar().setPerNombres(null);
				
				listaNovio=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getNovioInsertar());
											
				if (CollectionUtils.isEmpty(listaNovio) && listaNovio.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setNovioInsertar(listaNovio.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarNovio {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
		
	}
	
	
	public void buscarNovia () {
		slf4jLogger.info("buscarNovia");
		
		List<Persona> listaNovia=null;
		
		try {
			if(partidaMatrimonioDataManager.getNoviaInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getNoviaInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getNoviaInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getNoviaInsertar().setPerNombres(null);
				
				listaNovia=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getNoviaInsertar());
											
				if (CollectionUtils.isEmpty(listaNovia) && listaNovia.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setNoviaInsertar(listaNovia.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarNovia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMad_Novia () {
		slf4jLogger.info("buscarMad_Novia");
		
		List<Persona> listaMad_Novia=null;
		
		try {
			if(partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getMad_noviaInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getMad_noviaInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getMad_noviaInsertar().setPerNombres(null);
				
				listaMad_Novia=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getMad_noviaInsertar());
											
				if (CollectionUtils.isEmpty(listaMad_Novia) && listaMad_Novia.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setMad_noviaInsertar(listaMad_Novia.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMad_Novia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMad_Novio () {
		slf4jLogger.info("buscarMad_Novio");
		
		List<Persona> listaMad_Novio=null;
		
		try {
			if(partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getMad_novioInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getMad_novioInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getMad_novioInsertar().setPerNombres(null);
				
				listaMad_Novio=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getMad_novioInsertar());
											
				if (CollectionUtils.isEmpty(listaMad_Novio) && listaMad_Novio.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setMad_novioInsertar(listaMad_Novio.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMad_Novio {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarPad_Novia () {
		slf4jLogger.info("buscarPad_Novia");
		
		List<Persona> listaPad_Novia=null;
		
		try {
			if(partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getPad_noviaInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getPad_noviaInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getPad_noviaInsertar().setPerNombres(null);
				
				listaPad_Novia=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getPad_noviaInsertar());
											
				if (CollectionUtils.isEmpty(listaPad_Novia) && listaPad_Novia.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setPad_noviaInsertar(listaPad_Novia.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPad_Novia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarPad_Novio () {
		slf4jLogger.info("buscarPad_Novio");
		
		List<Persona> listaPad_Novio=null;
		
		try {
			if(partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getPad_novioInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getPad_novioInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getPad_novioInsertar().setPerNombres(null);
				
				listaPad_Novio=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getPad_novioInsertar());
											
				if (CollectionUtils.isEmpty(listaPad_Novio) && listaPad_Novio.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setPad_novioInsertar(listaPad_Novio.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPad_Novio {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMadre_Novia () {
		slf4jLogger.info("buscarMadre_Novia");
		
		List<Persona> listaMadre_Novia=null;
		
		try {
			if(partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getMadre_noviaInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getMadre_noviaInsertar().setPerNombres(null);
				
				listaMadre_Novia=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getMadre_noviaInsertar());
											
				if (CollectionUtils.isEmpty(listaMadre_Novia) && listaMadre_Novia.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setMadre_noviaInsertar(listaMadre_Novia.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadre_Novia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarPadre_Novia () {
		slf4jLogger.info("buscarPadre_Novia");
		
		List<Persona> listaPadre_Novia=null;
		
		try {
			if(partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getPadre_noviaInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getPadre_noviaInsertar().setPerNombres(null);
				
				listaPadre_Novia=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getPadre_noviaInsertar());
											
				if (CollectionUtils.isEmpty(listaPadre_Novia) && listaPadre_Novia.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setPadre_noviaInsertar(listaPadre_Novia.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPadre_Novia {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarMadre_Novio () {
		slf4jLogger.info("buscarMadre_Novio");
		
		List<Persona> listaMadre_Novio=null;
		
		try {
			if(partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getMadre_novioInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getMadre_novioInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getMadre_novioInsertar().setPerNombres(null);
				
				listaMadre_Novio=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getMadre_novioInsertar());
											
				if (CollectionUtils.isEmpty(listaMadre_Novio) && listaMadre_Novio.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setMadre_novioInsertar(listaMadre_Novio.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadre_Novio {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarPadre_Novio () {
		slf4jLogger.info("buscarPadre_Novio");
		
		List<Persona> listaPadre_Novio=null;
		
		try {
			if(partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi()!=null && partidaMatrimonioDataManager.getPadre_novioInsertar().getPerCi()!="" )
			{
				partidaMatrimonioDataManager.getPadre_novioInsertar().setPerApellidos(null);
				partidaMatrimonioDataManager.getPadre_novioInsertar().setPerNombres(null);
				
				listaPadre_Novio=this.servicioAdministracion.buscarPersona(partidaMatrimonioDataManager.getPadre_novioInsertar());
											
				if (CollectionUtils.isEmpty(listaPadre_Novio) && listaPadre_Novio.size()==0) {
					MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.partidaMatrimonioDataManager.setPadre_novioInsertar(listaPadre_Novio.get(0));
								
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPadre_Novio {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscarPartidaMatrimonio () {
		slf4jLogger.info("buscarPartidaMatrimonio");
		
		List<MatrimonioListDTO> listaMatrimonio=null;
		
		try {
			partidaMatrimonioDataManager.getMatrimonioListDTO().setMatEmpresa(getEmpresaTbl().getEmrPk());
			listaMatrimonio=this.servicioEucaristia.buscarPartidaMatrimonio(partidaMatrimonioDataManager.getMatrimonioListDTO());
			if (CollectionUtils.isEmpty(listaMatrimonio) && listaMatrimonio.size()==0) {
				partidaMatrimonioDataManager.setMatrimonioListDTOs(new ArrayList<MatrimonioListDTO>());
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				
				this.partidaMatrimonioDataManager.setMatrimonioListDTOs(listaMatrimonio);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPartidaMatrimonio {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void cargarDatosMatrimonio (MatrimonioListDTO matrimonio) {
		try {
			
			MatrimonioVO matrimonioEncontrado=servicioEucaristia.obtenerMatrimonioPorId(matrimonio);
			this.partidaMatrimonioDataManager.setNovioInsertar(matrimonioEncontrado.getNovio());
			this.partidaMatrimonioDataManager.setNoviaInsertar(matrimonioEncontrado.getNovia());
			this.partidaMatrimonioDataManager.setMatrimonioDTO(matrimonioEncontrado.getMatrimonio());
			this.partidaMatrimonioDataManager.setMad_noviaInsertar(matrimonioEncontrado.getMad_novia());
			this.partidaMatrimonioDataManager.setMad_novioInsertar(matrimonioEncontrado.getMad_novio());
			this.partidaMatrimonioDataManager.setPad_noviaInsertar(matrimonioEncontrado.getPad_novia());
			this.partidaMatrimonioDataManager.setPad_novioInsertar(matrimonioEncontrado.getPad_novio());
			this.partidaMatrimonioDataManager.setPadre_novioInsertar(matrimonioEncontrado.getPadre_novio());
			this.partidaMatrimonioDataManager.setMadre_novioInsertar(matrimonioEncontrado.getMadre_novio());
			this.partidaMatrimonioDataManager.setPadre_noviaInsertar(matrimonioEncontrado.getPadre_novia());
			this.partidaMatrimonioDataManager.setMadre_noviaInsertar(matrimonioEncontrado.getMadre_novia());
			this.partidaMatrimonioDataManager.setSacerdoteCodigo(matrimonioEncontrado.getMatrimonio().getEucSacerdote().getSacCodigo());
			this.partidaMatrimonioDataManager.setProvincia(matrimonioEncontrado.getMatrimonio().getMatProvincia());
			buscarCanton();
			this.partidaMatrimonioDataManager.setCanton(matrimonioEncontrado.getMatrimonio().getMatCanton());
			buscarParroquia();
			this.partidaMatrimonioDataManager.setParroquia(matrimonioEncontrado.getMatrimonio().getMatParroquia());
			this.partidaMatrimonioDataManager.setFechaApCurInsertar(matrimonioEncontrado.getMatrimonio().getMatFechaAprobacionCurso());
			this.partidaMatrimonioDataManager.setFechaMatrInsertar(matrimonioEncontrado.getMatrimonio().getMatFecha());
			this.partidaMatrimonioDataManager.setExportDesactivado(false);
			//partidaMatrimonioDataManager.getMatrimonioDTO().setMatCertificadoPor(PersonaCertificado);
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosMatrimonio {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosMatrimonio seleccionado");
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
				this.partidaMatrimonioDataManager.setProvinciaEucaristiaDTOs(listaCatalogo);
				
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
			cat.setCatCodigo(partidaMatrimonioDataManager.getProvincia());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaMatrimonioDataManager.setCantonEucaristiaDTOs(listaCatalogo);
				
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
			cat.setCatCodigo(partidaMatrimonioDataManager.getCanton());
			listaCatalogo=this.servicioEucaristia.buscarCatalogo(cat);
			
			if (CollectionUtils.isEmpty(listaCatalogo) && listaCatalogo.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.partidaMatrimonioDataManager.setParroquiaEucaristiaDTOs(listaCatalogo);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarCiudad {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void exportar()
	{
		Date fechaActual = new Date();
		DateFormat full = DateFormat.getDateInstance(DateFormat.FULL);
				
		DateFormat pequena = DateFormat.getDateInstance(DateFormat.SHORT);
		
		Map<String, Object> mapParametros = new HashMap<String, Object>();
		
		if (partidaMatrimonioDataManager.getCantonEucaristiaDTOs() != null)
			for (int i = 0; i < partidaMatrimonioDataManager
					.getCantonEucaristiaDTOs().size(); i++)
				if (partidaMatrimonioDataManager.getCantonEucaristiaDTOs().get(i)
						.getCatCodigo().equals(partidaMatrimonioDataManager
						.getCanton()))
		mapParametros.put("canton", partidaMatrimonioDataManager.getCantonEucaristiaDTOs().get(i).getCatDescripcion().toUpperCase());
		if (partidaMatrimonioDataManager.getParroquiaEucaristiaDTOs() != null)
			for (int i = 0; i < partidaMatrimonioDataManager
					.getParroquiaEucaristiaDTOs().size(); i++)
				if (partidaMatrimonioDataManager.getParroquiaEucaristiaDTOs()
						.get(i).getCatCodigo().equals(partidaMatrimonioDataManager
						.getParroquia())) {
					mapParametros.put("parroquiaCabecera", "\"" + partidaMatrimonioDataManager.getParroquiaEucaristiaDTOs().get(i).getCatDescripcion().toUpperCase() +"\"");
					mapParametros.put("parroquia", partidaMatrimonioDataManager.getParroquiaEucaristiaDTOs().get(i).getCatDescripcion().toUpperCase());
					mapParametros.put("parroquiaMinusculas", partidaMatrimonioDataManager.getParroquiaEucaristiaDTOs().get(i).getCatDescripcion());
					mapParametros.put("parroquiafechaActual", partidaMatrimonioDataManager.getParroquiaEucaristiaDTOs().get(i).getCatDescripcion()+ ", "+String.valueOf(full.format(fechaActual).charAt(0)).toUpperCase() +full.format(fechaActual).substring(1));
					}
		
		mapParametros.put("tomo", partidaMatrimonioDataManager.getMatrimonioDTO().getMatTomo());
		mapParametros.put("pagina", partidaMatrimonioDataManager.getMatrimonioDTO().getMatPagina());
		mapParametros.put("acta", partidaMatrimonioDataManager.getMatrimonioDTO().getMatActa());
		mapParametros.put("fechaMatrimonio", pequena.format(partidaMatrimonioDataManager.getFechaMatrInsertar()));
		mapParametros.put("novio", partidaMatrimonioDataManager.getNovioInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getNovioInsertar().getPerNombres().toUpperCase());
		mapParametros.put("novia", partidaMatrimonioDataManager.getNoviaInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getNoviaInsertar().getPerNombres().toUpperCase());
		mapParametros.put("daFe", partidaMatrimonioDataManager.getDaFe().toUpperCase());
		//cuando el novio tiene padrino
		if(partidaMatrimonioDataManager.getPad_novioInsertar().getPerApellidos()!=null &&  partidaMatrimonioDataManager.getPad_novioInsertar().getPerNombres()!=null)
		mapParametros.put("padrinoNovio", partidaMatrimonioDataManager.getPad_novioInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getPad_novioInsertar().getPerNombres().toUpperCase());
		else mapParametros.put("padrinoNovio", "");
		//cuando el novio tiene padrino
		if(partidaMatrimonioDataManager.getMad_novioInsertar().getPerApellidos()!=null &&  partidaMatrimonioDataManager.getMad_novioInsertar().getPerNombres()!=null)
		mapParametros.put("madrinaNovio", partidaMatrimonioDataManager.getMad_novioInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getMad_novioInsertar().getPerNombres().toUpperCase());
		else mapParametros.put("madrinaNovio","");
		//cuando la novia tiene padrino
		if(partidaMatrimonioDataManager.getPad_noviaInsertar().getPerApellidos()!=null &&  partidaMatrimonioDataManager.getPad_noviaInsertar().getPerNombres()!=null)
		mapParametros.put("padrinoNovia", partidaMatrimonioDataManager.getPad_noviaInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getPad_noviaInsertar().getPerNombres().toUpperCase());
		else mapParametros.put("padrinoNovia","");
		//cuando la novia tiene madrina
		if(partidaMatrimonioDataManager.getMad_noviaInsertar().getPerApellidos()!=null &&  partidaMatrimonioDataManager.getMad_noviaInsertar().getPerNombres()!=null)
		mapParametros.put("madrinaNovia", partidaMatrimonioDataManager.getMad_noviaInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getMad_noviaInsertar().getPerNombres().toUpperCase());
		else mapParametros.put("madrinaNovia","");
		mapParametros.put("notaMarginal", partidaMatrimonioDataManager.getMatrimonioDTO().getMatNotaMarginal());
		if (partidaMatrimonioDataManager.getParroquiaEucaristiaDTOs() != null)
			for (int i = 0; i < partidaMatrimonioDataManager
					.getProvinciaEucaristiaDTOs().size(); i++)
				if (partidaMatrimonioDataManager.getProvinciaEucaristiaDTOs()
						.get(i).getCatCodigo().equals(partidaMatrimonioDataManager
						.getProvincia()))
		mapParametros.put("provincia", partidaMatrimonioDataManager.getProvinciaEucaristiaDTOs().get(i).getCatDescripcion().toUpperCase());
		mapParametros.put("padreNovio", partidaMatrimonioDataManager.getPadre_novioInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getPadre_novioInsertar().getPerNombres().toUpperCase());
		mapParametros.put("madreNovio", partidaMatrimonioDataManager.getMadre_novioInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getMadre_novioInsertar().getPerNombres().toUpperCase());
		mapParametros.put("padreNovia", partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getPadre_noviaInsertar().getPerNombres().toUpperCase());
		mapParametros.put("madreNovia", partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerApellidos().toUpperCase() + " "+   partidaMatrimonioDataManager.getMadre_noviaInsertar().getPerNombres().toUpperCase());
		mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), "partidaMatrimonio", mapParametros);
		ReporteUtil.generarReporte(jasperPrint, this.partidaMatrimonioDataManager.getFormatoPdf(), "partidaMatrimonio");
	
	}
	
	public void cargarDatosMatrimonioEditar (MatrimonioListDTO matrimonioListDTO) {
		cargarDatosMatrimonio (matrimonioListDTO);
		buscarSacerdoteEditar();
	}
	
	public void buscarSacerdoteEditar () {
		slf4jLogger.info("buscarSacerdoteEditar");
		
		List<SacerdoteListDTO> listaSacerdote=null;
		
		try {
			this.partidaMatrimonioDataManager.setSacerdoteListDTO(new ArrayList<SacerdoteListDTO>());
			buscarSacerdote();
			SacerdoteListDTO sacerdoteListDTO= new SacerdoteListDTO();
			sacerdoteListDTO.setSacCodigo(this.partidaMatrimonioDataManager.getSacerdoteCodigo());
			listaSacerdote=this.servicioEucaristia.buscarSacerdote(sacerdoteListDTO);
			List<SacerdoteListDTO> listaSacerdoteActivos=this.partidaMatrimonioDataManager.getSacerdoteListDTO();
			Boolean estaActivo=false;
			for (SacerdoteListDTO sacerdote : listaSacerdoteActivos) {
				if(sacerdote.getSacCodigo().equals(listaSacerdote.get(0).getSacCodigo()))estaActivo=true;
			}
			if(!estaActivo) this.partidaMatrimonioDataManager.getSacerdoteListDTO().add(listaSacerdote.get(0));
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarSacerdote {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	public void exportarPdf(MatrimonioListDTO matrimonio)
	{
		cargarDatosMatrimonio(matrimonio);
		exportar();
	}
	
	public void limpiarFormulario()
	{
		partidaMatrimonioDataManager.setNovioInsertar(new Persona());
		partidaMatrimonioDataManager.setNoviaInsertar(new Persona());
		partidaMatrimonioDataManager.setMad_noviaInsertar(new Persona());
		partidaMatrimonioDataManager.setPad_noviaInsertar(new Persona());
		partidaMatrimonioDataManager.setMad_novioInsertar(new Persona());
		partidaMatrimonioDataManager.setPad_novioInsertar(new Persona());
		partidaMatrimonioDataManager.setMadre_novioInsertar(new Persona());
		partidaMatrimonioDataManager.setMadre_noviaInsertar(new Persona());
		partidaMatrimonioDataManager.setPadre_novioInsertar(new Persona());
		partidaMatrimonioDataManager.setPadre_noviaInsertar(new Persona());
		partidaMatrimonioDataManager.setMatrimonioDTO(new MatrimonioDTO());
		partidaMatrimonioDataManager.setSacerdoteCodigo(0);
		partidaMatrimonioDataManager.setFechaApCurInsertar(new Date());
		partidaMatrimonioDataManager.setFechaMatrInsertar(new Date());
		partidaMatrimonioDataManager.setProvincia((0));
		partidaMatrimonioDataManager.setCanton((0));
		partidaMatrimonioDataManager.setParroquia((0));
		partidaMatrimonioDataManager.setExportDesactivado(true);
		buscarSacerdote();
	}
	

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}
