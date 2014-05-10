/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.util.dto.ActaBienDTO;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.BienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="bienController")
public class BienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{bienDataManager}")
	private BienDataManager bienDataManager;
	
	/**
	 * @param bienDataManager the bienDataManager to set
	 */
	public void setBienDataManager(BienDataManager bienDataManager) {
		this.bienDataManager = bienDataManager;
	}
	
	public BienController () {}
	
	public void registrarBien () {
		
		slf4jLogger.info("registrarBien");
		
		try {
			
			this.bienDataManager.getBienInstancia().setEmrPk(this.bienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.bienDataManager.getBienInstancia().setCatBienPk(this.bienDataManager.getIdCategoriaBienSeleccionado());
			this.bienDataManager.getBienInstancia().setLinBienPk(this.bienDataManager.getIdLineaBienSeleccionado());
			this.bienDataManager.getBienInstancia().setNpIdDcEstadoConservacion(this.bienDataManager.getIdDcEstadoConservacionSelec());
			this.bienDataManager.getBienInstancia().setUsuarioRegistro(this.bienDataManager.getUsuarioSession());
			if (this.bienDataManager.getBienInstancia().getMarBienPk().intValue()==0) {
				this.bienDataManager.getBienInstancia().setMarBienPk(null);
			}
			this.bienDataManager.getBienInstancia().setBiePk(null);
			
			Bien bienNuevo = servicioInventario.registrarBien(this.bienDataManager.getBienInstancia());
			
			if (bienNuevo != null) {
				VistaBien vistaBien = new VistaBien();
				vistaBien.setBiePk(bienNuevo.getBiePk());
				vistaBien.setBieEstado(this.bienDataManager.getEstadoActivo());
				vistaBien.setEmrPk(this.bienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
				List<VistaBien> listVistaBien = this.servicioInventario.buscarVistaBienCriterios(vistaBien);
				this.bienDataManager.getListVistaBien().add(listVistaBien.iterator().next());
				this.bienDataManager.setBienInstancia(new Bien());
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.registro.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al registrarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void editarBien () {
		slf4jLogger.info("editarBien");
		try {
			
			this.bienDataManager.getBienEditar().setCatBienPk(this.bienDataManager.getIdCategoriaBienSeleccionado());
			this.bienDataManager.getBienEditar().setLinBienPk(this.bienDataManager.getIdLineaBienSeleccionado());
			this.bienDataManager.getBienEditar().setUsuarioRegistro(this.bienDataManager.getUsuarioSession());
			VistaBien vistaBienUpdate = servicioInventario.actualizarBien(this.bienDataManager.getBienEditar());
			
			if (vistaBienUpdate != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
				int posicion = this.bienDataManager.getListVistaBien().indexOf(this.bienDataManager.getVistaBienEditar());
				this.bienDataManager.getListVistaBien().remove(this.bienDataManager.getVistaBienEditar());
				this.bienDataManager.getListVistaBien().add(posicion, vistaBienUpdate);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al editarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void buscarBienes () {
		
		slf4jLogger.info("buscarBienes");
		
		try {
			
			this.bienDataManager.getVistaBienBuscar().setEmrPk(this.bienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.bienDataManager.getVistaBienBuscar().setTraEstado(this.bienDataManager.getEstadoActivo());
			
			if (StringUtils.isNotBlank(bienDataManager.getIdDcTipoBienSelec())) {
				this.bienDataManager.getVistaBienBuscar().setDetBienTipBieNivel1(bienDataManager.getIdDcTipoBienSelec());
			}
			
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.bienDataManager.getVistaBienBuscar());
			
			this.bienDataManager.getListVistaBien().clear();
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.bienDataManager.setListVistaBien(listVistaBien);
			} else {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void asignarDatosBienDesdeVista(VistaBien vistaBien) {
		
		slf4jLogger.info("asignarDatosBienDesdeVista");
		try {
			
			Bien bienBuscar = new Bien();
			bienBuscar.setBiePk(vistaBien.getBiePk());
			bienBuscar.setEmrPk(vistaBien.getEmrPk());
			this.bienDataManager.setVistaBienEditar(vistaBien);
			List<Bien> listBien = servicioInventario.buscarBienCriterios(bienBuscar);
			
			if (CollectionUtils.isNotEmpty(listBien) && listBien.size()==1) {
				
				Bien bienEditar = listBien.iterator().next();
				this.bienDataManager.setIdCategoriaBienSeleccionado(bienEditar.getCatBienPk());
				this.bienDataManager.setIdLineaBienSeleccionado(bienEditar.getLinBienPk());
				this.bienDataManager.setIdDcEstadoConservacionSelec(vistaBien.getDetBienEstConservNivel1Fk());
				this.bienDataManager.cargarDcLineaBien();
				this.bienDataManager.setBienEditar(bienEditar);
			} 
			
		} catch (SeguridadesException e) {
			
			slf4jLogger.info("error al asignarDatosBienDesdeVista {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public String asignarBien () {
		
		slf4jLogger.info("asignarBien");
		
		try {
			
			this.bienDataManager.getVistaBienEditar().setEmpAsignadoFk(this.bienDataManager.getIdCustudioAsignado());
			VistaBien vistaBien = servicioInventario.asignarBien(this.bienDataManager.getVistaBienEditar());
			
			if (vistaBien != null) {
				int posicion = this.bienDataManager.getListVistaBien().indexOf(this.bienDataManager.getVistaBienEditar());
				this.bienDataManager.getListVistaBien().remove(this.bienDataManager.getVistaBienEditar());
				this.bienDataManager.getListVistaBien().add(posicion, vistaBien);
				MensajesWebController.aniadirMensajeInformacion("Bien asignado correctamente");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
		return "administracionBien";
		
	}
	
	public void reasignarBien () {
		
		slf4jLogger.info("reasignarBien");
		
		try {
			
			this.bienDataManager.getVistaBienEditar().setEmpReasignadoFk(this.bienDataManager.getIdCustudioReasignado());
			VistaBien vistaBien = servicioInventario.reasignarBien(this.bienDataManager.getVistaBienEditar());
			
			if (vistaBien != null) {
				int posicion = this.bienDataManager.getListVistaBien().indexOf(this.bienDataManager.getVistaBienEditar());
				this.bienDataManager.getListVistaBien().remove(this.bienDataManager.getVistaBienEditar());
				this.bienDataManager.getListVistaBien().add(posicion, vistaBien);
				MensajesWebController.aniadirMensajeInformacion("Bien reasignado correctamente");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al asignarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void validarAsignacionCustodio () {
		slf4jLogger.info("validarAsignacionCustodio");
		
		if (bienDataManager.getVistaBienEditar().getEmpAsignadoFk().intValue() == bienDataManager.getIdCustudioReasignado().intValue()){
			MensajesWebController.aniadirMensajeError("El custodio a reemplazar no puede ser el mismo");
			bienDataManager.setIdCustudioReasignado(0);
		}
		
	}
	
	public void obtenerTrazabilidadBien () {
		slf4jLogger.info("obtenerTrazabilidadBien");
		
		try {
			VistaTransaccion vistaTransaccion = new VistaTransaccion();
			vistaTransaccion.setBieFk(bienDataManager.getVistaBienEditar().getBiePk());
			bienDataManager.setListVistaTransaccion(servicioInventario.obtenerVistaTransaccionCriterios(vistaTransaccion));
		} catch (SeguridadesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generarActaBien () {
		
		List<ActaBienDTO> listActaBien = new ArrayList<ActaBienDTO>();
		ActaBienDTO actaBienDTO = new ActaBienDTO();
		actaBienDTO.setTituloActa("Acta bienes");
		listActaBien.add(actaBienDTO);
		
		JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), listActaBien, "actaAsignacionBien");
		ReporteUtil.generarReporte(jasperPrint, "pdf", "actaBien");
		
	}
	
	public void reiniciarObjetos () {
		this.bienDataManager.refrescarObjetos();
	}
	
}
