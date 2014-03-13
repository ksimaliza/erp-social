/**
 * 
 */
package ec.edu.uce.erp.web.common.util;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogoPK;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;

/**
 * @author
 *
 */
public final class UtilSelectItems {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(UtilSelectItems.class);
	
	private static final UtilSelectItems INSTANCIA = new UtilSelectItems();
	
	/**
	 * Constructor privado
	 */
	private UtilSelectItems() {}
	
	/**
	 * Devolvemos la instancia de la clase
	 * @return INSTANCIA
	 */
	public static UtilSelectItems getInstancia(){
		return INSTANCIA;
	}
	
	public List<SelectItem> cargarSelectItems (List<DetalleCatalogo> listDetalleCatalogo, String idDetalleCatalogo, ServicioAdministracion servicioAdministracion) throws SeguridadesException {
		
		slf4jLogger.info("cargarSelectItems");
		
		List<SelectItem> listSelectItem = new ArrayList<SelectItem>();
		
		if (CollectionUtils.isEmpty(listDetalleCatalogo)) {
			listDetalleCatalogo = consultarDetalleCatalogo(idDetalleCatalogo, servicioAdministracion);
		}
		
		if (CollectionUtils.isNotEmpty(listDetalleCatalogo)) {
			CollectionUtils.collect(listDetalleCatalogo, new Transformer() {
				@Override
				public Object transform(final Object arg0) {
					final DetalleCatalogo detalleCatalogo = (DetalleCatalogo)arg0;
					return new SelectItem(detalleCatalogo.getId().getDetCatalogoNivel1(), detalleCatalogo.getDetCatalogoDescripcion());
				}
			}, listSelectItem);
		}
		
		return listSelectItem;
		
	}
	
	private List<DetalleCatalogo> consultarDetalleCatalogo (String idDetalleCatalogo, ServicioAdministracion servicioAdministracion) throws SeguridadesException {
		DetalleCatalogo detalleCatalogo = new DetalleCatalogo();
		detalleCatalogo.setId(new DetalleCatalogoPK());
		detalleCatalogo.getId().setCabCatalogoFk(idDetalleCatalogo);
		detalleCatalogo.setDetCatalogoEstado(ESTADO_ACTIVO);
		return servicioAdministracion.buscarDetalleCatalogoCriterios(detalleCatalogo);
	}
}
