/**
 * 
 */
package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBienPK;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;

/**
 * @author 
 *
 */
@SessionScoped
@ManagedBean(name="detalleBienDataManager")
public class DetalleBienDataManager extends BaseDataManager {

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DetalleBienDataManager.class);
	
	@EJB
	public ServicioInventario servicioInventario;
	
	private DetalleBien detalleBienRegistrar;
	private DetalleBien detalleBienBuscar;
	private DetalleBien detalleBienEditar;
	private List<DetalleBien> listDetalleBien;
	
	private List<SelectItem> dcCabeceraBien;
	
	public DetalleBienDataManager () {
		super();
		this.detalleBienRegistrar = new DetalleBien();
		this.detalleBienRegistrar.setId(new DetalleBienPK());
		this.detalleBienBuscar = new DetalleBien();
		this.detalleBienBuscar.setId(new DetalleBienPK());
		this.detalleBienEditar = new DetalleBien();
		this.detalleBienEditar.setId(new DetalleBienPK());
		this.listDetalleBien = new ArrayList<DetalleBien>();
	}

	/**
	 * @return the detalleBienBuscar
	 */
	public DetalleBien getDetalleBienBuscar() {
		return detalleBienBuscar;
	}

	/**
	 * @param detalleBienBuscar the detalleBienBuscar to set
	 */
	public void setDetalleBienBuscar(DetalleBien detalleBienBuscar) {
		this.detalleBienBuscar = detalleBienBuscar;
	}

	/**
	 * @return the detalleBienEditar
	 */
	public DetalleBien getDetalleBienEditar() {
		return detalleBienEditar;
	}

	/**
	 * @param detalleBienEditar the detalleBienEditar to set
	 */
	public void setDetalleBienEditar(DetalleBien detalleBienEditar) {
		this.detalleBienEditar = detalleBienEditar;
	}

	/**
	 * @return the listDetalleBien
	 */
	public List<DetalleBien> getListDetalleBien() {
		return listDetalleBien;
	}

	/**
	 * @param listDetalleBien the listDetalleBien to set
	 */
	public void setListDetalleBien(List<DetalleBien> listDetalleBien) {
		this.listDetalleBien = listDetalleBien;
	}

	/**
	 * @return the dcCabeceraBien
	 */
	public List<SelectItem> getDcCabeceraBien() throws SeguridadesException {
		
		if (CollectionUtils.isEmpty(dcCabeceraBien)) {
			slf4jLogger.info("cargar catalogo cabeceraBien");
			dcCabeceraBien = UtilSelectItems.getInstancia().cargarSelectItemCabeceraBien(servicioInventario);
		}
		
		return dcCabeceraBien;
	}

	/**
	 * @return the detalleBienRegistrar
	 */
	public DetalleBien getDetalleBienRegistrar() {
		return detalleBienRegistrar;
	}

	/**
	 * @param detalleBienRegistrar the detalleBienRegistrar to set
	 */
	public void setDetalleBienRegistrar(DetalleBien detalleBienRegistrar) {
		this.detalleBienRegistrar = detalleBienRegistrar;
	}
	
}
