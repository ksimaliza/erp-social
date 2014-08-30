package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name="transaccionMasivaBienDataManager") 
public class TransaccionMasivaBienDataManager  extends BaseDataManager {
	
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(TransaccionMasivaBienDataManager.class);
	
	@EJB
	public ServicioInventario servicioInventario;
	
	private VistaBien vistaBienBuscar;
	private VistaBien vistaBienEditar;
	private List<VistaBien> listVistaBien;
	
	private List<VistaBien> listVistaBienTramitar;
	
	public TransaccionMasivaBienDataManager () {
		slf4jLogger.info("transaccionMasivaBienDataManager");
		this.vistaBienBuscar = new VistaBien();
		this.vistaBienEditar = new VistaBien();
		this.listVistaBien = new ArrayList<VistaBien>();
	}
	
	
	/**
	 * @return the vistaBienBuscar
	 */
	public VistaBien getVistaBienBuscar() {
		return vistaBienBuscar;
	}

	/**
	 * @param vistaBienBuscar the vistaBienBuscar to set
	 */
	public void setVistaBienBuscar(VistaBien vistaBienBuscar) {
		this.vistaBienBuscar = vistaBienBuscar;
	}

	/**
	 * @return the listVistaBien
	 */
	public List<VistaBien> getListVistaBien() {
		return listVistaBien;
	}

	/**
	 * @param listVistaBien the listVistaBien to set
	 */
	public void setListVistaBien(List<VistaBien> listVistaBien) {
		this.listVistaBien = listVistaBien;
	}



	/**
	 * @return the listVistaBienTramitar
	 */
	public List<VistaBien> getListVistaBienTramitar() {
		return listVistaBienTramitar;
	}



	/**
	 * @param listVistaBienTramitar the listVistaBienTramitar to set
	 */
	public void setListVistaBienTramitar(List<VistaBien> listVistaBienTramitar) {
		this.listVistaBienTramitar = listVistaBienTramitar;
	}


	/**
	 * @return the vistaBienEditar
	 */
	public VistaBien getVistaBienEditar() {
		return vistaBienEditar;
	}


	/**
	 * @param vistaBienEditar the vistaBienEditar to set
	 */
	public void setVistaBienEditar(VistaBien vistaBienEditar) {
		this.vistaBienEditar = vistaBienEditar;
	}
	

}
