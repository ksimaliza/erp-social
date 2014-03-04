/**
 * 
 */
package ec.edu.uce.erp.web.common.controladores;

import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_TIPO_INGRESO;
import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean (name = "selectItemsInventarioController")
public class SelectItemsInventarioController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(SelectItemsInventarioController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	private List<SelectItem> catalogoTipoIngreso;
	
	public SelectItemsInventarioController () {}
	
	@PostConstruct
	public void init () {
		this.catalogoTipoIngreso = new ArrayList<SelectItem>();
	}

	public List<SelectItem> getCatalogoTipoIngreso() {
		
		if (CollectionUtils.isEmpty(catalogoTipoIngreso)) {
			slf4jLogger.info("cargar catalogoTipoIngreso");
			
			DetalleCatalogo dto = new DetalleCatalogo();
			dto.getId().setCabCatalogoFk(ID_CAB_CATALOGO_TIPO_INGRESO);
			dto.setDetCatalogoEstado(ESTADO_ACTIVO);
			
			try {
				List<DetalleCatalogo> listDetalleCatalogo = servicioAdministracion.buscarDetalleCatalogoCriterios(dto);
				
				CollectionUtils.collect(listDetalleCatalogo, new Transformer() {
					@Override
					public Object transform(final Object arg0) {
						final DetalleCatalogo detalleCatalogo = (DetalleCatalogo)arg0;
						return new SelectItem(detalleCatalogo.getId().getDetCatalogoNivel1(), detalleCatalogo.getDetCatalogoDescripcion());
					}
				}, catalogoTipoIngreso);
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al cargar catalogo tipo ingreso {}", e.getMessage());
				MensajesWebController.aniadirMensajeError("Error al cargar catalogo tipo ingreso");
			}
			
		}
		return catalogoTipoIngreso;
	}

}
