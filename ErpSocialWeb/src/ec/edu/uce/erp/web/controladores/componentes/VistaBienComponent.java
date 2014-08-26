/**
 * 
 */
package ec.edu.uce.erp.web.controladores.componentes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

/**
 * @author
 *
 */

public class VistaBienComponent extends BaseDataManager {
	
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaBienComponent.class);
	
	private VistaBien vistaBienSeleccionado;
	
	public VistaBienComponent () {
		this.vistaBienSeleccionado = new VistaBien();
	}
	
	public void asignarDatosVistaBien(VistaBien vistaBien) {
		slf4jLogger.info("asignarDatosVistaBien");
		this.vistaBienSeleccionado = null;
		this.vistaBienSeleccionado = vistaBien;
	}

	/**
	 * @return the vistaBienSeleccionado
	 */
	public VistaBien getVistaBienSeleccionado() {
		return vistaBienSeleccionado;
	}

	/**
	 * @param vistaBienSeleccionado the vistaBienSeleccionado to set
	 */
	public void setVistaBienSeleccionado(VistaBien vistaBienSeleccionado) {
		this.vistaBienSeleccionado = vistaBienSeleccionado;
	}
	
}
